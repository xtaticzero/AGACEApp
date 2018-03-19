package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;

public class FecetPropCancelada extends BaseModel {

    private static final long serialVersionUID = -1389650761596133244L;

    private BigDecimal idCancelacionPropuesta;

    private BigDecimal idPropuesta;

    private BigDecimal idCausa;

    private String observaciones;

    private Date fechaCreacion;

    private String rfcCreacion;

    private Date fechaCancelacion;

    private Date fechaRechazo;

    private String descripcionRechazo;

    private BigDecimal idEmpleado;

    private BigDecimal idEstatus;

    private BigDecimal blnEstatus;

    private BigDecimal idDocCancelacion;

    private String rutaArchivo;

    private String nombreArchivo;

    private Date fechaFin;

    private BigDecimal blnActivo;

    private transient InputStream archivo;

    private FececMotivo fececMotivo;

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdCausa(BigDecimal idCausa) {
        this.idCausa = idCausa;
    }

    public BigDecimal getIdCausa() {
        return idCausa;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
    }

    public String getRfcCreacion() {
        return rfcCreacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion != null ? new Date(fechaCancelacion.getTime()) : null;
    }

    public Date getFechaCancelacion() {
        return (fechaCancelacion != null) ? (Date) fechaCancelacion.clone() : null;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
    }

    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setBlnEstatus(BigDecimal blnEstatus) {
        this.blnEstatus = blnEstatus;
    }

    public BigDecimal getBlnEstatus() {
        return blnEstatus;
    }

    public void setIdDocCancelacion(BigDecimal idDocCancelacion) {
        this.idDocCancelacion = idDocCancelacion;
    }

    public BigDecimal getIdDocCancelacion() {
        return idDocCancelacion;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }

    public void setIdCancelacionPropuesta(BigDecimal idCancelacionPropuesta) {
        this.idCancelacionPropuesta = idCancelacionPropuesta;
    }

    public BigDecimal getIdCancelacionPropuesta() {
        return idCancelacionPropuesta;
    }

    public FecetPropCanceladaPk createPk() {
        return new FecetPropCanceladaPk(idCancelacionPropuesta);
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPropCancelada: ");
        ret.append("idCancelacionPropuesta=" + idCancelacionPropuesta);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", idCausa=" + idCausa);
        ret.append(", idEstatus=" + idEstatus);
        ret.append(", idEmpleado=" + idEmpleado);
        ret.append(", fechaCancelacion=" + fechaCancelacion);
        ret.append(", fechaCreacion=" + fechaCreacion);
        ret.append(", observaciones=" + observaciones);
        ret.append(", blnEstatus=" + blnEstatus);
        return ret.toString();
    }
}
