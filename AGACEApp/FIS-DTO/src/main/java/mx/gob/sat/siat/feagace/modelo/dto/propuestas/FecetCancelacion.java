package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;

public class FecetCancelacion extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idCancelacion;
    private BigDecimal idPropuesta;
    private BigDecimal idCausa;
    private String observaciones;
    private Date fechaCreacion;
    private String rfcCancelacion;
    private Date fechaCancelacion;
    private Date fechaRechazo;
    private String descripcionRechazo;
    private BigDecimal idEmpleado;
    private BigDecimal idEstatus;
    private BigDecimal blnEstatus;
    private Long totalDocumentos;
    private FececMotivo fececMotivo;

    public BigDecimal getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(BigDecimal idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(BigDecimal idCausa) {
        this.idCausa = idCausa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public String getRfcCancelacion() {
        return rfcCancelacion;
    }

    public void setRfcCancelacion(String rfcCancelacion) {
        this.rfcCancelacion = rfcCancelacion;
    }

    public Date getFechaCancelacion() {
        return (fechaCancelacion != null) ? (Date) fechaCancelacion.clone() : null;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion != null ? new Date(fechaCancelacion.getTime()) : null;
    }

    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getBlnEstatus() {
        return blnEstatus;
    }

    public void setBlnEstatus(BigDecimal blnEstatus) {
        this.blnEstatus = blnEstatus;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public Long getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(Long totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }
}
