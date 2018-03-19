/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.reportes.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteCatalogosModel;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ReporteLstHelper implements Serializable {

    private static final long serialVersionUID = -5517507999608665390L;

    private List<EmpleadoDTO> listaProgramadores;
    private List<EmpleadoDTO> listaSubAdministradores;
    private List<EmpleadoDTO> listaEmpleadosCreanPropuesta;
    private List<EmpleadoDTO> listaAuditores;
    private List<EmpleadoDTO> listaFirmantes;
    private List<String> listaImagenes = new ArrayList<String>();
    private List<FececUnidadAdministrativa> listaUnidad;
    private List<FececUnidadAdministrativa> unidadAdministrativa;
    private List<FececEstatus> listaEstatusInsumos;
    private List<FececEstatus> listaEstatusPropuestas;
    private List<FececEstatus> listaEstatusOrdenes;
    private List<FececSubprograma> listaSubprograma;
    private List<FececSector> listaSector;
    private List<FececActividadPreponderante> listaActividad;
    private List<ReporteCatalogosModel> listaEntidadInsumos;
    private List<ReporteCatalogosModel> listaEntidadPropuestas;
    private List<ReporteCatalogosModel> listaEntidadOrdenes;
    private List<ReporteCatalogosModel> listaAnios;
    private List<ReporteCatalogosModel> listaMesesInicial;
    private List<FececMetodo> listaMetodos;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececRevision> listaTipoRevision;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<ReporteInsumosDTO> listaInsumos = new ArrayList<ReporteInsumosDTO>();
    private List<ReportePropuestasDTO> listaPropuestas = new ArrayList<ReportePropuestasDTO>();
    private List<ReporteOrdenesDTO> listaOrdenes = new ArrayList<ReporteOrdenesDTO>();

    public void setListaEstatusPropuestas(List<FececEstatus> listaEstatusPropuestas) {
        this.listaEstatusPropuestas = listaEstatusPropuestas;
    }

    public List<FececEstatus> getListaEstatusPropuestas() {
        return listaEstatusPropuestas;
    }

    public void setListaEstatusOrdenes(List<FececEstatus> listaEstatusOrdenes) {
        this.listaEstatusOrdenes = listaEstatusOrdenes;
    }

    public List<FececEstatus> getListaEstatusOrdenes() {
        return listaEstatusOrdenes;
    }

    public void setListaEstatusInsumos(List<FececEstatus> listaEstatusInsumos) {
        this.listaEstatusInsumos = listaEstatusInsumos;
    }

    public List<FececEstatus> getListaEstatusInsumos() {
        return listaEstatusInsumos;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaSector(List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaActividad(List<FececActividadPreponderante> listaActividad) {
        this.listaActividad = listaActividad;
    }

    public List<FececActividadPreponderante> getListaActividad() {
        return listaActividad;
    }

    public void setListaEntidadInsumos(List<ReporteCatalogosModel> listaEntidadInsumos) {
        this.listaEntidadInsumos = listaEntidadInsumos;
    }

    public List<ReporteCatalogosModel> getListaEntidadInsumos() {
        return listaEntidadInsumos;
    }

    public void setListaEntidadPropuestas(List<ReporteCatalogosModel> listaEntidadPropuestas) {
        this.listaEntidadPropuestas = listaEntidadPropuestas;
    }

    public List<ReporteCatalogosModel> getListaEntidadPropuestas() {
        return listaEntidadPropuestas;
    }

    public void setListaEntidadOrdenes(List<ReporteCatalogosModel> listaEntidadOrdenes) {
        this.listaEntidadOrdenes = listaEntidadOrdenes;
    }

    public List<ReporteCatalogosModel> getListaEntidadOrdenes() {
        return listaEntidadOrdenes;
    }

    public void setListaInsumos(List<ReporteInsumosDTO> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public List<ReporteInsumosDTO> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaAnios(List<ReporteCatalogosModel> listaAnios) {
        this.listaAnios = listaAnios;
    }

    public List<ReporteCatalogosModel> getListaAnios() {
        return listaAnios;
    }


    public void setListaMesesInicial(List<ReporteCatalogosModel> listaMesesInicial) {
        this.listaMesesInicial = listaMesesInicial;
    }

    public List<ReporteCatalogosModel> getListaMesesInicial() {
        return listaMesesInicial;
    }

    public void setListaMetodos(List<FececMetodo> listaMetodos) {
        this.listaMetodos = listaMetodos;
    }

    public List<FececMetodo> getListaMetodos() {
        return listaMetodos;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setListaPropuestas(List<ReportePropuestasDTO> listaPropuestas) {
        this.listaPropuestas = listaPropuestas;
    }

    public List<ReportePropuestasDTO> getListaPropuestas() {
        return listaPropuestas;
    }

    public void setListaOrdenes(List<ReporteOrdenesDTO> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<ReporteOrdenesDTO> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaImagenes(List<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public List<String> getListaImagenes() {
        return listaImagenes;
    }

    public List<FececUnidadAdministrativa> getListaUnidad() {
        return listaUnidad;
    }

    public void setListaUnidad(List<FececUnidadAdministrativa> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public List<FececUnidadAdministrativa> getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(List<FececUnidadAdministrativa> unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public List<EmpleadoDTO> getListaProgramadores() {
        return listaProgramadores;
    }

    public void setListaProgramadores(List<EmpleadoDTO> listaProgramadores) {
        this.listaProgramadores = listaProgramadores;
    }

    public List<EmpleadoDTO> getListaSubAdministradores() {
        return listaSubAdministradores;
    }

    public void setListaSubAdministradores(List<EmpleadoDTO> listaSubAdministradores) {
        this.listaSubAdministradores = listaSubAdministradores;
    }

    public List<EmpleadoDTO> getListaEmpleadosCreanPropuesta() {
        return listaEmpleadosCreanPropuesta;
    }

    public void setListaEmpleadosCreanPropuesta(List<EmpleadoDTO> listaEmpleadosCreanPropuesta) {
        this.listaEmpleadosCreanPropuesta = listaEmpleadosCreanPropuesta;
    }

    public List<EmpleadoDTO> getListaAuditores() {
        return listaAuditores;
    }

    public void setListaAuditores(List<EmpleadoDTO> listaAuditores) {
        this.listaAuditores = listaAuditores;
    }

    public List<EmpleadoDTO> getListaFirmantes() {
        return listaFirmantes;
    }

    public void setListaFirmantes(List<EmpleadoDTO> listaFirmantes) {
        this.listaFirmantes = listaFirmantes;
    }
    
    
    
}
