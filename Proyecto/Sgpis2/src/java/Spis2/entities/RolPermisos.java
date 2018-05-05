/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spis2.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "rol_permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolPermisos.findAll", query = "SELECT r FROM RolPermisos r")
    , @NamedQuery(name = "RolPermisos.findByIdRol", query = "SELECT r FROM RolPermisos r WHERE r.rolPermisosPK.idRol = :idRol")
    , @NamedQuery(name = "RolPermisos.findByIdPermiso", query = "SELECT r FROM RolPermisos r WHERE r.rolPermisosPK.idPermiso = :idPermiso")
    , @NamedQuery(name = "RolPermisos.findByFechaCreacion", query = "SELECT r FROM RolPermisos r WHERE r.fechaCreacion = :fechaCreacion")})
public class RolPermisos implements Serializable {

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolPermisosPK rolPermisosPK;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permisos permisos;

    public RolPermisos() {
    }

    public RolPermisos(RolPermisosPK rolPermisosPK) {
        this.rolPermisosPK = rolPermisosPK;
    }

    public RolPermisos(int idRol, int idPermiso) {
        this.rolPermisosPK = new RolPermisosPK(idRol, idPermiso);
    }

    public RolPermisosPK getRolPermisosPK() {
        return rolPermisosPK;
    }

    public void setRolPermisosPK(RolPermisosPK rolPermisosPK) {
        this.rolPermisosPK = rolPermisosPK;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Permisos getPermisos() {
        return permisos;
    }

    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolPermisosPK != null ? rolPermisosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolPermisos)) {
            return false;
        }
        RolPermisos other = (RolPermisos) object;
        if ((this.rolPermisosPK == null && other.rolPermisosPK != null) || (this.rolPermisosPK != null && !this.rolPermisosPK.equals(other.rolPermisosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Spis2.entities.RolPermisos[ rolPermisosPK=" + rolPermisosPK + " ]";
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}
