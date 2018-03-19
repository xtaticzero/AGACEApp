package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

public class DatosPropuestaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal idPropuesta;
    private String idRegistro;
    private String revision;
    private String subprograma;
    private String tipoPropuesta;
    private String programacion;
    private String tipoRevision;
    private String periodoInicioPropuesta;
    private String periodoFinPropuesa;
    private List<ImpuestoDTO> listaImpuestos;
    private Integer presuntiva;
    private boolean prioridad;
    private String prioridadSugerida;
    private String rfcRepresent;
    private String nombreRepresent;
    private String emailRepresent;
    private String rfcAgAduanal;
    private String nombreAgAduanal;
    private String emailAgAduanal;
    private String arace;
    private List<FecetDocExpediente> listaDocumentosTabla;
    private List<FecetDocExpediente> listaDocsOrden;
    private List<FecetDocOrden> listaDocsHistorialOrden;
    private List<FecetOficio> listaDocsOficio;
    private List<FecetOficio> listaDocsHistorialOficio;
    private FecetAsociado repLegal;
    private FecetAsociado agenAduanal;
    private boolean tipoActivoPropuesta;
    private String sector;
    private String presentacionComite;
    private String informacionComite;
    private boolean visibleInfo;
    private Date fechaPresentacion;
    private Date fechaInforme;

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getRevision() {
        return revision;
    }

    public void setSubprograma(String subprograma) {
        this.subprograma = subprograma;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setTipoPropuesta(String tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public String getTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setPeriodoInicioPropuesta(String periodoInicioPropuesta) {
        this.periodoInicioPropuesta = periodoInicioPropuesta;
    }

    public String getPeriodoInicioPropuesta() {
        return periodoInicioPropuesta;
    }

    public void setPeriodoFinPropuesa(String periodoFinPropuesa) {
        this.periodoFinPropuesa = periodoFinPropuesa;
    }

    public String getPeriodoFinPropuesa() {
        return periodoFinPropuesa;
    }

    public void setRfcRepresent(String rfcRepresent) {
        this.rfcRepresent = rfcRepresent;
    }

    public String getRfcRepresent() {
        return rfcRepresent;
    }

    public void setNombreRepresent(String nombreRepresent) {
        this.nombreRepresent = nombreRepresent;
    }

    public String getNombreRepresent() {
        return nombreRepresent;
    }

    public void setEmailRepresent(String emailRepresent) {
        this.emailRepresent = emailRepresent;
    }

    public String getEmailRepresent() {
        return emailRepresent;
    }

    public void setRfcAgAduanal(String rfcAgAduanal) {
        this.rfcAgAduanal = rfcAgAduanal;
    }

    public String getRfcAgAduanal() {
        return rfcAgAduanal;
    }

    public void setNombreAgAduanal(String nombreAgAduanal) {
        this.nombreAgAduanal = nombreAgAduanal;
    }

    public String getNombreAgAduanal() {
        return nombreAgAduanal;
    }

    public void setEmailAgAduanal(String emailAgAduanal) {
        this.emailAgAduanal = emailAgAduanal;
    }

    public String getEmailAgAduanal() {
        return emailAgAduanal;
    }

    public void setListaImpuestos(List<ImpuestoDTO> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<ImpuestoDTO> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setPresuntiva(Integer presuntiva) {
        this.presuntiva = presuntiva;
    }

    public Integer getPresuntiva() {
        return presuntiva;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setListaDocumentosTabla(List<FecetDocExpediente> listaDocumentosTabla) {
        this.listaDocumentosTabla = listaDocumentosTabla;
    }

    public List<FecetDocExpediente> getListaDocumentosTabla() {
        return listaDocumentosTabla;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setListaDocsOrden(List<FecetDocExpediente> listaDocsOrden) {
        this.listaDocsOrden = listaDocsOrden;
    }

    public List<FecetDocExpediente> getListaDocsOrden() {
        return listaDocsOrden;
    }

    public void setRepLegal(FecetAsociado repLegal) {
        this.repLegal = repLegal;
    }

    public FecetAsociado getRepLegal() {
        return repLegal;
    }

    public void setAgenAduanal(FecetAsociado agenAduanal) {
        this.agenAduanal = agenAduanal;
    }

    public FecetAsociado getAgenAduanal() {
        return agenAduanal;
    }

    public boolean isTipoActivoPropuesta() {
        return tipoActivoPropuesta;
    }

    public void setTipoActivoPropuesta(boolean tipoActivoPropuesta) {
        this.tipoActivoPropuesta = tipoActivoPropuesta;
    }

    public String getPrioridadSugerida() {
        return prioridadSugerida;
    }

    public void setPrioridadSugerida(String prioridadSugerida) {
        this.prioridadSugerida = prioridadSugerida;
    }

    public String getArace() {
        return arace;
    }

    public void setArace(String arace) {
        this.arace = arace;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public List<FecetOficio> getListaDocsOficio() {
        return listaDocsOficio;
    }

    public void setListaDocsOficio(List<FecetOficio> listaDocsOficio) {
        this.listaDocsOficio = listaDocsOficio;
    }

    public List<FecetDocOrden> getListaDocsHistorialOrden() {
        return listaDocsHistorialOrden;
    }

    public void setListaDocsHistorialOrden(List<FecetDocOrden> listaDocsHistorialOrden) {
        this.listaDocsHistorialOrden = listaDocsHistorialOrden;
    }

    public List<FecetOficio> getListaDocsHistorialOficio() {
        return listaDocsHistorialOficio;
    }

    public void setListaDocsHistorialOficio(List<FecetOficio> listaDocsHistorialOficio) {
        this.listaDocsHistorialOficio = listaDocsHistorialOficio;
    }

    public String getPresentacionComite() {
        return presentacionComite;
    }

    public void setPresentacionComite(String presentacionComite) {
        this.presentacionComite = presentacionComite;
    }

    public boolean isVisibleInfo() {
        return visibleInfo;
    }

    public void setVisibleInfo(boolean visibleInfo) {
        this.visibleInfo = visibleInfo;
    }

    public Date getFechaPresentacion() {
        return (fechaPresentacion != null) ? (Date) fechaPresentacion.clone() : null;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion != null ? new Date(fechaPresentacion.getTime()) : null;
    }

    public String getInformacionComite() {
        return informacionComite;
    }

    public void setInformacionComite(String informacionComite) {
        this.informacionComite = informacionComite;
    }

    public Date getFechaInforme() {
        return (fechaInforme != null) ? (Date) fechaInforme.clone() : null;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme != null ? new Date(fechaInforme.getTime()) : null;
    }
}
