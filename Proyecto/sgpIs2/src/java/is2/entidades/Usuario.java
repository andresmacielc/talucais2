/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is2.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUser", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.idUser = :idUser")
    , @NamedQuery(name = "Usuario.findByIdRol", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.idRol = :idRol")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByFechaCreacionUser", query = "SELECT u FROM Usuario u WHERE u.fechaCreacionUser = :fechaCreacionUser")
    , @NamedQuery(name = "Usuario.findByFechaAsignacionRol", query = "SELECT u FROM Usuario u WHERE u.fechaAsignacionRol = :fechaAsignacionRol")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPK usuarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "status")
    private byte[] status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion_user")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_asignacion_rol")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacionRol;
    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    @ManyToOne
    private Groups idGroup;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public Usuario(UsuarioPK usuarioPK, String nombre, String apellido, byte[] status, String password, String email, Date fechaCreacionUser, Date fechaAsignacionRol) {
        this.usuarioPK = usuarioPK;
        this.nombre = nombre;
        this.apellido = apellido;
        this.status = status;
        this.password = password;
        this.email = email;
        this.fechaCreacionUser = fechaCreacionUser;
        this.fechaAsignacionRol = fechaAsignacionRol;
    }

    public Usuario(BigInteger idUser, BigInteger idRol) {
        this.usuarioPK = new UsuarioPK(idUser, idRol);
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public byte[] getStatus() {
        return status;
    }

    public void setStatus(byte[] status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacionUser() {
        return fechaCreacionUser;
    }

    public void setFechaCreacionUser(Date fechaCreacionUser) {
        this.fechaCreacionUser = fechaCreacionUser;
    }

    public Date getFechaAsignacionRol() {
        return fechaAsignacionRol;
    }

    public void setFechaAsignacionRol(Date fechaAsignacionRol) {
        this.fechaAsignacionRol = fechaAsignacionRol;
    }

    public Groups getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Groups idGroup) {
        this.idGroup = idGroup;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPK != null ? usuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioPK == null && other.usuarioPK != null) || (this.usuarioPK != null && !this.usuarioPK.equals(other.usuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is2.entidades.Usuario[ usuarioPK=" + usuarioPK + " ]";
    }
    
}
