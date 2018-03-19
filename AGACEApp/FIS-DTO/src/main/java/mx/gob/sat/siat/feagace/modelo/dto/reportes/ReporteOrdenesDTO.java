package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReporteOrdenesDTO extends BaseModel{

    @SuppressWarnings("compatibility:8705310922930308582")
    private static final long serialVersionUID = 1L;
    
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private String numeroOrden;
    private Date fechaRegistro;
    private String metodo;
    private String rfcContribuyente;
    private String razonSocial;
    private String unidadAdministrativa;
    private BigDecimal idUnidadAdministrativa;   
    private String estatus;
    private String firmante;
    private String auditor;
    private BigDecimal idFirmante;
    private BigDecimal idAuditor;
    private Date fechaFirma;
    private Date fechaEnvioNotificacion;
    private String subprograma;
    private String sector;
    private String actividadPrepoderante;
    private VerificarContribuyenteReporteDTO contribuyente;

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime()): null;
    }

    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()): null;
    }

    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro != null ? new Date(fechaRegistro.getTime()): null;
    }

    public Date getFechaRegistro() {
        return (fechaRegistro != null) ? (Date) fechaRegistro.clone() : null;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
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

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }

    public String getFirmante() {
        return firmante;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma != null ? new Date(fechaFirma.getTime()): null;
    }

    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    public void setFechaEnvioNotificacion(Date fechaEnvioNotificacion) {
        this.fechaEnvioNotificacion = fechaEnvioNotificacion != null ? new Date(fechaEnvioNotificacion.getTime()): null;
    }

    public Date getFechaEnvioNotificacion() {
        return (fechaEnvioNotificacion != null) ? (Date) fechaEnvioNotificacion.clone() : null;
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

    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    public BigDecimal getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(BigDecimal idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }
    
    
    
}
