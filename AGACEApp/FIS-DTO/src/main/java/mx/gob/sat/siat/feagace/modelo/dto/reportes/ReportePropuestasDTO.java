package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReportePropuestasDTO extends BaseModel{

    @SuppressWarnings("compatibility:3336311398389965175")
    private static final long serialVersionUID = 1L;
    
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private String registroId;
    private String rfcContribuyente;
    private String razonSocial;
    private String metodo;
    private String estatus;
    private BigDecimal presuntiva;
    private String tipoRevision;
    private String regimen;
    private String subprograma;
    private String sector;
    private String actividadPrepoderante;
    private String causaProgramacion;
    private String unidadAdministrativa;
    private String firmante;
    private String auditor;
    private VerificarContribuyenteReporteDTO contribuyente;
    private String rfcFirmante;
    private String rfcAuditor;
    private BigDecimal idArace;

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

    public void setRegistroId(String registroId) {
        this.registroId = registroId;
    }

    public String getRegistroId() {
        return registroId;
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

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getRegimen() {
        return regimen;
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

    public void setCausaProgramacion(String causaProgramacion) {
        this.causaProgramacion = causaProgramacion;
    }

    public String getCausaProgramacion() {
        return causaProgramacion;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
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

    public void setContribuyente(VerificarContribuyenteReporteDTO contribuyente) {
        this.contribuyente = contribuyente;
    }

    public VerificarContribuyenteReporteDTO getContribuyente() {
        return contribuyente;
    }

    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public String getRfcFirmante() {
        return rfcFirmante;
    }

    public void setRfcAuditor(String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    public String getRfcAuditor() {
        return rfcAuditor;
    }

    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }
}
