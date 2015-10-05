/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itmm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Misael
 */
@Entity
@Table(name = "SC_USUARIOSAUDITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScUsuariosaudita.findAll", query = "SELECT s FROM ScUsuariosaudita s"),
    @NamedQuery(name = "ScUsuariosaudita.findByFecha", query = "SELECT s FROM ScUsuariosaudita s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "ScUsuariosaudita.findByAccion", query = "SELECT s FROM ScUsuariosaudita s WHERE s.accion = :accion"),
    @NamedQuery(name = "ScUsuariosaudita.findByDescripcion", query = "SELECT s FROM ScUsuariosaudita s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "ScUsuariosaudita.findByValNuevo", query = "SELECT s FROM ScUsuariosaudita s WHERE s.valNuevo = :valNuevo"),
    @NamedQuery(name = "ScUsuariosaudita.findByValAnterior", query = "SELECT s FROM ScUsuariosaudita s WHERE s.valAnterior = :valAnterior"),
    @NamedQuery(name = "ScUsuariosaudita.findByCampo", query = "SELECT s FROM ScUsuariosaudita s WHERE s.campo = :campo"),
    @NamedQuery(name = "ScUsuariosaudita.findByUsuario", query = "SELECT s FROM ScUsuariosaudita s WHERE s.usuario = :usuario")})
public class ScUsuariosaudita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 12)
    @Column(name = "ACCION")
    private String accion;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 30)
    @Column(name = "VAL_NUEVO")
    private String valNuevo;
    @Size(max = 30)
    @Column(name = "VAL_ANTERIOR")
    private String valAnterior;
    @Size(max = 20)
    @Column(name = "CAMPO")
    private String campo;
    @Size(max = 8)
    @Column(name = "USUARIO")
    private String usuario;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private ScUsuarios usuarioId;

    public ScUsuariosaudita() {
    }

    public ScUsuariosaudita(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValNuevo() {
        return valNuevo;
    }

    public void setValNuevo(String valNuevo) {
        this.valNuevo = valNuevo;
    }

    public String getValAnterior() {
        return valAnterior;
    }

    public void setValAnterior(String valAnterior) {
        this.valAnterior = valAnterior;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ScUsuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ScUsuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScUsuariosaudita)) {
            return false;
        }
        ScUsuariosaudita other = (ScUsuariosaudita) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.ScUsuariosaudita[ fecha=" + fecha + " ]";
    }
    
}
