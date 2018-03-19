package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReporteInsumosDTO extends BaseModel{

    @SuppressWarnings("compatibility:-7305190550296501116")
    private static final long serialVersionUID = 1L;
    private String idRegistro;
    private Date fechaRegistro;
    private Date fechaPeriodoPropuestoInicio;
    private Date fechaPeriodoPropuestoFin;
    private String rfcContribuyente;
    private String razonSocial;
    private String entidad;
    private String estatus;
    private Date fechaAprobacion;
    private Date fechaRechazo;
    private String motivoRechazo;
    private String subprograma;
    private String sector;
    private String actividadPrepoderante;
    private VerificarContribuyenteReporteDTO contribuyente;
   
    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro != null ? new Date(fechaRegistro.getTime()): null;
    }

    public Date getFechaRegistro() {
        return (fechaRegistro != null) ? (Date) fechaRegistro.clone() : null;
    }

    public void setFechaPeriodoPropuestoInicio(Date fechaPeriodoPropuestoInicio) {
        this.fechaPeriodoPropuestoInicio = fechaPeriodoPropuestoInicio != null ? new Date(fechaPeriodoPropuestoInicio.getTime()): null;
    }

    public Date getFechaPeriodoPropuestoInicio() {
        return (fechaPeriodoPropuestoInicio != null) ? (Date) fechaPeriodoPropuestoInicio.clone() : null;
    }

    public void setFechaPeriodoPropuestoFin(Date fechaPeriodoPropuestoFin) {
        this.fechaPeriodoPropuestoFin = fechaPeriodoPropuestoFin != null ? new Date(fechaPeriodoPropuestoFin.getTime()): null;
    }

    public Date getFechaPeriodoPropuestoFin() {
        return (fechaPeriodoPropuestoFin != null) ? (Date) fechaPeriodoPropuestoFin.clone() : null;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion != null ? new Date(fechaAprobacion.getTime()): null;
    }

    public Date getFechaAprobacion() {
        return (fechaAprobacion != null) ? (Date) fechaAprobacion.clone() : null;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()): null;
    }

    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setSubprograma(String subprograma) {
        this.subprograma = subprograma;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setActividadPrepoderante(String actividadPrepoderante) {
        this.actividadPrepoderante = actividadPrepoderante;
    }

    public String getActividadPrepoderante() {
        return actividadPrepoderante;
    }

    public void setContribuyente(VerificarContribuyenteReporteDTO contribuyente) {
        this.contribuyente = contribuyente;
    }

    public VerificarContribuyenteReporteDTO getContribuyente() {
        return contribuyente;
    }
}
