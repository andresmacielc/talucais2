/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spis2.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "usuario_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioHistory.findAll", query = "SELECT u FROM UsuarioHistory u")
    , @NamedQuery(name = "UsuarioHistory.findByIdHistory", query = "SELECT u FROM UsuarioHistory u WHERE u.idHistory = :idHistory")
    , @NamedQuery(name = "UsuarioHistory.findByNombre", query = "SELECT u FROM UsuarioHistory u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UsuarioHistory.findByFechaCreacion", query = "SELECT u FROM UsuarioHistory u WHERE u.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "UsuarioHistory.findByStatus", query = "SELECT u FROM UsuarioHistory u WHERE u.status = :status")
    , @NamedQuery(name = "UsuarioHistory.findByDescripcion", query = "SELECT u FROM UsuarioHistory u WHERE u.descripcion = :descripcion")})
public class UsuarioHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_history")
    private Integer idHistory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
    @ManyToOne(optional = false)
    private Sprint idSprint;

    public UsuarioHistory() {
    }

    public UsuarioHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public UsuarioHistory(Integer idHistory, String nombre, Date fechaCreacion, int status, String descripcion) {
        this.idHistory = idHistory;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.descripcion = descripcion;
    }

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sprint getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Sprint idSprint) {
        this.idSprint = idSprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistory != null ? idHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHistory)) {
            return false;
        }
        UsuarioHistory other = (UsuarioHistory) object;
        if ((this.idHistory == null && other.idHistory != null) || (this.idHistory != null && !this.idHistory.equals(other.idHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Spis2.entities.UsuarioHistory[ idHistory=" + idHistory + " ]";
    }
    
}
