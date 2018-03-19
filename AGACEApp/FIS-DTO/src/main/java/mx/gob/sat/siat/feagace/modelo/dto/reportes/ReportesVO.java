package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ReportesVO extends BaseModel {

    @SuppressWarnings("compatibility:159188906797060233")
    private static final long serialVersionUID = -1880131929768740071L;

    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private String rfc;
    private BigDecimal entidadId;
    private BigDecimal estatusId;
    private BigDecimal subprogramaId;
    private BigDecimal sectorId;
    private BigDecimal actividadPreponderanteId;
    private String entidad;
    private String actividad;
    private String tipoGrafica;
    private Integer mesInicial;
    private Integer anioInicial;
    private Integer mesFinal;
    private Integer anioFinal;
    private String tipoFecha;
    private String tipoReporteEjecutivoSeleccionado;
    private BigDecimal araceId;
    private Date fechaInicio;
    private Date fechaFinal;
    private String numeroPropuesta;
    private BigDecimal metodoId;
    private BigDecimal tipoRevisionId;
    private BigDecimal tipoPropuestaId;
    private BigDecimal causaProgramacionId;
    private BigDecimal presuntivaInicio;
    private BigDecimal presuntivaFinal;
    private BigDecimal firmanteId;
    private BigDecimal auditorId;
    private boolean mostrarMetodo;
    private boolean mostrarEntidad;
    private boolean mostrarEstatus;
    private boolean mostrarUnidad;
    private boolean activarMetodo;
    private boolean activarEntidad;
    private boolean activarEstatus;
    private boolean activarUnidad;
    private String reporte;
    private List<String> lstRfcCreacion;

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime()) : null;
    }

    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()) : null;
    }

    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setEntidadId(BigDecimal entidadId) {
        this.entidadId = entidadId;
    }

    public BigDecimal getEntidadId() {
        return entidadId;
    }

    public void setEstatusId(BigDecimal estatusId) {
        this.estatusId = estatusId;
    }

    public BigDecimal getEstatusId() {
        return estatusId;
    }

    public void setSubprogramaId(BigDecimal subprogramaId) {
        this.subprogramaId = subprogramaId;
    }

    public BigDecimal getSubprogramaId() {
        return subprogramaId;
    }

    public void setSectorId(BigDecimal sectorId) {
        this.sectorId = sectorId;
    }

    public BigDecimal getSectorId() {
        return sectorId;
    }

    public void setActividadPreponderanteId(BigDecimal actividadPreponderanteId) {
        this.actividadPreponderanteId = actividadPreponderanteId;
    }

    public BigDecimal getActividadPreponderanteId() {
        return actividadPreponderanteId;
    }

    public void setEntidad(String estado) {
        this.entidad = estado;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setTipoGrafica(String tipoGrafica) {
        this.tipoGrafica = tipoGrafica;
    }

    public String getTipoGrafica() {
        return tipoGrafica;
    }

    public void setMesInicial(Integer mesInicial) {
        this.mesInicial = mesInicial;
    }

    public Integer getMesInicial() {
        return mesInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setMesFinal(Integer mesFinal) {
        this.mesFinal = mesFinal;
    }

    public Integer getMesFinal() {
        return mesFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
    }

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoReporteEjecutivoSeleccionado(String tipoReporteEjecutivoSeleccionado) {
        this.tipoReporteEjecutivoSeleccionado = tipoReporteEjecutivoSeleccionado;
    }

    public String getTipoReporteEjecutivoSeleccionado() {
        return tipoReporteEjecutivoSeleccionado;
    }

    public void setAraceId(BigDecimal araceId) {
        this.araceId = araceId;
    }

    public BigDecimal getAraceId() {
        return araceId;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal != null ? new Date(fechaFinal.getTime()) : null;
    }

    public Date getFechaFinal() {
        return (fechaFinal != null) ? (Date) fechaFinal.clone() : null;
    }

    public void setNumeroPropuesta(String numeroPropuesta) {
        this.numeroPropuesta = numeroPropuesta;
    }

    public String getNumeroPropuesta() {
        return numeroPropuesta;
    }

    public void setMetodoId(BigDecimal metodoId) {
        this.metodoId = metodoId;
    }

    public BigDecimal getMetodoId() {
        return metodoId;
    }

    public void setTipoRevisionId(BigDecimal tipoRevisionId) {
        this.tipoRevisionId = tipoRevisionId;
    }

    public BigDecimal getTipoRevisionId() {
        return tipoRevisionId;
    }

    public void setTipoPropuestaId(BigDecimal tipoPropuestaId) {
        this.tipoPropuestaId = tipoPropuestaId;
    }

    public BigDecimal getTipoPropuestaId() {
        return tipoPropuestaId;
    }

    public void setCausaProgramacionId(BigDecimal causaProgramacionId) {
        this.causaProgramacionId = causaProgramacionId;
    }

    public BigDecimal getCausaProgramacionId() {
        return causaProgramacionId;
    }

    public void setPresuntivaInicio(BigDecimal presuntivaInicio) {
        this.presuntivaInicio = presuntivaInicio;
    }

    public BigDecimal getPresuntivaInicio() {
        return presuntivaInicio;
    }

    public void setPresuntivaFinal(BigDecimal presuntivaFinal) {
        this.presuntivaFinal = presuntivaFinal;
    }

    public BigDecimal getPresuntivaFinal() {
        return presuntivaFinal;
    }

    public void setFirmanteId(BigDecimal firmanteId) {
        this.firmanteId = firmanteId;
    }

    public BigDecimal getFirmanteId() {
        return firmanteId;
    }

    public void setAuditorId(BigDecimal auditorId) {
        this.auditorId = auditorId;
    }

    public BigDecimal getAuditorId() {
        return auditorId;
    }

    public void setMostrarMetodo(boolean mostrarMetodo) {
        this.mostrarMetodo = mostrarMetodo;
    }

    public boolean isMostrarMetodo() {
        return mostrarMetodo;
    }

    public void setMostrarEntidad(boolean mostrarEntidad) {
        this.mostrarEntidad = mostrarEntidad;
    }

    public boolean isMostrarEntidad() {
        return mostrarEntidad;
    }

    public void setMostrarEstatus(boolean mostrarEstatus) {
        this.mostrarEstatus = mostrarEstatus;
    }

    public boolean isMostrarEstatus() {
        return mostrarEstatus;
    }

    public void setMostrarUnidad(boolean mostrarUnidad) {
        this.mostrarUnidad = mostrarUnidad;
    }

    public boolean isMostrarUnidad() {
        return mostrarUnidad;
    }

    public void setActivarMetodo(boolean activarMetodo) {
        this.activarMetodo = activarMetodo;
    }

    public boolean isActivarMetodo() {
        return activarMetodo;
    }

    public void setActivarEntidad(boolean activarEntidad) {
        this.activarEntidad = activarEntidad;
    }

    public boolean isActivarEntidad() {
        return activarEntidad;
    }

    public void setActivarEstatus(boolean activarEstatus) {
        this.activarEstatus = activarEstatus;
    }

    public boolean isActivarEstatus() {
        return activarEstatus;
    }

    public void setActivarUnidad(boolean activarUnidad) {
        this.activarUnidad = activarUnidad;
    }

    public boolean isActivarUnidad() {
        return activarUnidad;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public List<String> getLstRfcCreacion() {
        return lstRfcCreacion;
    }

    public void setLstRfcCreacion(List<String> lstRfcCreacion) {
        this.lstRfcCreacion = lstRfcCreacion;
    }

}
