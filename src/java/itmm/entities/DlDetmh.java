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
import javax.persistence.OneToOne;
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
@Table(name = "DL_DETMH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DlDetmh.findAll", query = "SELECT d FROM DlDetmh d"),
    @NamedQuery(name = "DlDetmh.findByDocumento", query = "SELECT d FROM DlDetmh d WHERE d.documento = :documento"),
    @NamedQuery(name = "DlDetmh.findByFecha", query = "SELECT d FROM DlDetmh d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "DlDetmh.findByHora", query = "SELECT d FROM DlDetmh d WHERE d.hora = :hora"),
    @NamedQuery(name = "DlDetmh.findByNmuestraId", query = "SELECT d FROM DlDetmh d WHERE d.nmuestraId = :nmuestraId"),
    @NamedQuery(name = "DlDetmh.findByValor", query = "SELECT d FROM DlDetmh d WHERE d.valor = :valor"),
    @NamedQuery(name = "DlDetmh.findByMinimo", query = "SELECT d FROM DlDetmh d WHERE d.minimo = :minimo"),
    @NamedQuery(name = "DlDetmh.findByMaximo", query = "SELECT d FROM DlDetmh d WHERE d.maximo = :maximo"),
    @NamedQuery(name = "DlDetmh.UniqueFecha", query = "SELECT DISTINCT(d.fecha) FROM DlDetmh d")})
public class DlDetmh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)     
    @Column(name = "DOCUMENTO")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "NMUESTRA_ID")
    private String nmuestraId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Double valor;
    @Column(name = "MINIMO")
    private Double minimo;
    @Column(name = "MAXIMO")
    private Double maximo;
    @JoinColumn(name = "DOCUMENTO", referencedColumnName = "DOCUMENTO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private DlEncamh dlEncamh;
    @JoinColumn(name = "ANALISIS_ID", referencedColumnName = "ANALISIS_ID")
    @ManyToOne(optional = false)
    private ScAnalisis analisisId;

    public DlDetmh() {
    }

    public DlDetmh(String documento) {
        this.documento = documento;
    }

    public DlDetmh(String documento, Date fecha, Date hora, String nmuestraId) {
        this.documento = documento;
        this.fecha = fecha;
        this.hora = hora;
        this.nmuestraId = nmuestraId;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNmuestraId() {
        return nmuestraId;
    }

    public void setNmuestraId(String nmuestraId) {
        this.nmuestraId = nmuestraId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public DlEncamh getDlEncamh() {
        return dlEncamh;
    }

    public void setDlEncamh(DlEncamh dlEncamh) {
        this.dlEncamh = dlEncamh;
    }

    public ScAnalisis getAnalisisId() {
        return analisisId;
    }

    public void setAnalisisId(ScAnalisis analisisId) {
        this.analisisId = analisisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documento != null ? documento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DlDetmh)) {
            return false;
        }
        DlDetmh other = (DlDetmh) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "itmm.entities.DlDetmh[ documento=" + documento + " ]";
    }
    
}
