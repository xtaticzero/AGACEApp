/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.reportes.helper;

import java.io.Serializable;
import java.util.Date;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.TipoReportesDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ReporteDTOHelper implements Serializable{
    private static final long serialVersionUID = 3675020399394211597L;
    
    private boolean mostrarUnidad = false;
    private boolean mostrarResultados = false;
    private boolean mostrarFormulario = true;
    private boolean tipoReporteEjecutivo;
    private boolean tipoReporteGerencial;
    private boolean habilitarFechaPeriodos = true;
    private boolean habilitarMesAnio = true;
    private boolean habilitarFechaPeriodoFin = true;
    private boolean habilitarUnidad;
    private boolean reporteEjecutivo = false;
    private boolean mostarCheckCondicion = false;
    private boolean habilitarFirmante = false;
    private boolean habilitarauditor = false;
    private int contadorCondicion = 0;
    private String rfc;
    private String rangoFechasReporte;
    private String pathArchivo;    
    private Date fechaActual;
    private Date fechaMinima;
    
    private ReportesVO reportesVO = new ReportesVO();
    private TipoReportesDTO tipoReporte;
    private GraficaDTO grafica = new GraficaDTO();
    private FececArace unidadAdministrativaRegional;
    
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }
    
    public void setHabilitarUnidad(boolean habilitarUnidad) {
        this.habilitarUnidad = habilitarUnidad;
    }

    public boolean isHabilitarUnidad() {
        return habilitarUnidad;
    }

    public void setReporteEjecutivo(boolean reporteEjecutivo) {
        this.reporteEjecutivo = reporteEjecutivo;
    }

    public boolean isReporteEjecutivo() {
        return reporteEjecutivo;
    }

    public void setContadorCondicion(int contadorCondicion) {
        this.contadorCondicion = contadorCondicion;
    }

    public int getContadorCondicion() {
        return contadorCondicion;
    }

    public void setMostarCheckCondicion(boolean mostarCheckCondicion) {
        this.mostarCheckCondicion = mostarCheckCondicion;
    }

    public boolean isMostarCheckCondicion() {
        return mostarCheckCondicion;
    }

    public void setUnidadAdministrativaRegional(FececArace unidadAdministrativaRegional) {
        this.unidadAdministrativaRegional = unidadAdministrativaRegional;
    }

    public FececArace getUnidadAdministrativaRegional() {
        return unidadAdministrativaRegional;
    }

    public void setHabilitarFirmante(boolean habilitarFirmante) {
        this.habilitarFirmante = habilitarFirmante;
    }

    public boolean isHabilitarFirmante() {
        return habilitarFirmante;
    }

    public void setHabilitarauditor(boolean habilitarauditor) {
        this.habilitarauditor = habilitarauditor;
    }

    public boolean isHabilitarauditor() {
        return habilitarauditor;
    }
    
    public void setMostrarUnidad(boolean mostrarUnidad) {
        this.mostrarUnidad = mostrarUnidad;
    }

    public boolean isMostrarUnidad() {
        return mostrarUnidad;
    }

    public void setGrafica(GraficaDTO grafica) {
        this.grafica = grafica;
    }

    public GraficaDTO getGrafica() {
        return grafica;
    }

    public void setPathArchivo(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    public String getPathArchivo() {
        return pathArchivo;
    }
    
    public void setTipoReporte(TipoReportesDTO tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public TipoReportesDTO getTipoReporte() {
        return tipoReporte;
    }
    
    public void setHabilitarFechaPeriodos(boolean habilitarFechaPeriodos) {
        this.habilitarFechaPeriodos = habilitarFechaPeriodos;
    }

    public boolean isHabilitarFechaPeriodos() {
        return habilitarFechaPeriodos;
    }

    public void setHabilitarMesAnio(boolean habilitarMesAnio) {
        this.habilitarMesAnio = habilitarMesAnio;
    }

    public boolean isHabilitarMesAnio() {
        return habilitarMesAnio;
    }
    
    public void setHabilitarFechaPeriodoFin(boolean habilitarFechaPeriodoFin) {
        this.habilitarFechaPeriodoFin = habilitarFechaPeriodoFin;
    }

    public boolean isHabilitarFechaPeriodoFin() {
        return habilitarFechaPeriodoFin;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima != null ? new Date(fechaMinima.getTime()) : null;
    }

    public Date getFechaMinima() {
        return (fechaMinima != null) ? (Date) fechaMinima.clone() : null;
    }

    public void setRangoFechasReporte(String rangoFechasReporte) {
        this.rangoFechasReporte = rangoFechasReporte;
    }

    public String getRangoFechasReporte() {
        return rangoFechasReporte;
    }

    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }

    public boolean isMostrarResultados() {
        return mostrarResultados;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setTipoReporteEjecutivo(boolean tipoReporteEjecutivo) {
        this.tipoReporteEjecutivo = tipoReporteEjecutivo;
    }

    public boolean isTipoReporteEjecutivo() {
        return tipoReporteEjecutivo;
    }

    public void setTipoReporteGerencial(boolean tipoReporteGerencial) {
        this.tipoReporteGerencial = tipoReporteGerencial;
    }

    public boolean isTipoReporteGerencial() {
        return tipoReporteGerencial;
    }
    
    public void setReportesVO(ReportesVO reportesVO) {
        this.reportesVO = reportesVO;
    }

    public ReportesVO getReportesVO() {
        return reportesVO;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual != null ? new Date(fechaActual.getTime()) : null;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }
    
}
