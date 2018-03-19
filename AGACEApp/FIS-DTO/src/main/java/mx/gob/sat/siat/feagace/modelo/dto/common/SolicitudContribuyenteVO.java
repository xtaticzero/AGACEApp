package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public class SolicitudContribuyenteVO extends BaseModel {
    private static final long serialVersionUID = 1L;
    
    private BigDecimal id;
    private BigDecimal idOrden;
    private Date fechaCarga;
    private String descripcion;
    private String estatus;
    private FecetProrrogaOrden prorrogaOrden;
    private FecetPruebasPericiales pruebasPericiales;
    private Integer totalDocumentosContribuyente;
    private Integer totalDocumentosRechazo;
    private String justificacion;
    private BigDecimal idFlujo;
    private String nombreResolucion;
    private Boolean aprobada;
    private String justificacionFirmante;

    public SolicitudContribuyenteVO() {
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setProrrogaOrden(FecetProrrogaOrden prorrogaOrden) {
        this.prorrogaOrden = prorrogaOrden;
    }

    public FecetProrrogaOrden getProrrogaOrden() {
        return prorrogaOrden;
    }

    public void setPruebasPericiales(FecetPruebasPericiales pruebasPericiales) {
        this.pruebasPericiales = pruebasPericiales;
    }

    public FecetPruebasPericiales getPruebasPericiales() {
        return pruebasPericiales;
    }

    public void setTotalDocumentosContribuyente(Integer totalDocumentosContribuyente) {
        this.totalDocumentosContribuyente = totalDocumentosContribuyente;
    }

    public Integer getTotalDocumentosContribuyente() {
        return totalDocumentosContribuyente;
    }

    public void setTotalDocumentosRechazo(Integer totalDocumentosRechazo) {
        this.totalDocumentosRechazo = totalDocumentosRechazo;
    }

    public Integer getTotalDocumentosRechazo() {
        return totalDocumentosRechazo;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setIdFlujo(BigDecimal idFlujo) {
        this.idFlujo = idFlujo;
    }

    public BigDecimal getIdFlujo() {
        return idFlujo;
    }

    public void setNombreResolucion(String nombreResolucion) {
        this.nombreResolucion = nombreResolucion;
    }

    public String getNombreResolucion() {
        return nombreResolucion;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public Boolean getAprobada() {
        return aprobada;
    }

    public void setJustificacionFirmante(String justificacionFirmante) {
        this.justificacionFirmante = justificacionFirmante;
    }

    public String getJustificacionFirmante() {
        return justificacionFirmante;
    }
}
