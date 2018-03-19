package mx.gob.sat.siat.feagace.vista.propuestas.asignar.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;

public class AsignarPropuestaListaHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5801910495343824825L;

    private List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadas;
    private List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadasPrior3;
    private List<PropuestaOrigenCentralRegDTO> listaPropuestasPorAsignar;
    private List<PropuestaOrigenCentralRegDTO> listaPropuestasPrior3;
    private List<FececFirmante> listaFirmantes;
    private List<FececMetodo> listaMetodos;
    private List<FececTipoPropuesta> listaTipoPropuestas;
    private List<FececRevision> listaTipoRevision;
    private List<FececSubprograma> listaSubprograma;
    private List<TipoFechasComiteEnum> listaFechasComiteEnum;
    private List<ContadorInsumosSubAdmin> listaContadorPropuestas;
    private List<EmpleadoDTO> firmantesEmpleado;
    private List<String> lstPrioridadGrid;
    private List<String> lstPrioridadFiltros;

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasAsignadas() {
        return listaPropuestasAsignadas;
    }

    public void setListaPropuestasAsignadas(List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadas) {
        this.listaPropuestasAsignadas = listaPropuestasAsignadas;
    }

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasAsignadasPrior3() {
        return listaPropuestasAsignadasPrior3;
    }

    public void setListaPropuestasAsignadasPrior3(List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadasPrior3) {
        this.listaPropuestasAsignadasPrior3 = listaPropuestasAsignadasPrior3;
    }

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasPorAsignar() {
        return listaPropuestasPorAsignar;
    }

    public void setListaPropuestasPorAsignar(List<PropuestaOrigenCentralRegDTO> listaPropuestasPorAsignar) {
        this.listaPropuestasPorAsignar = listaPropuestasPorAsignar;
    }

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasPrior3() {
        return listaPropuestasPrior3;
    }

    public void setListaPropuestasPrior3(List<PropuestaOrigenCentralRegDTO> listaPropuestasPrior3) {
        this.listaPropuestasPrior3 = listaPropuestasPrior3;
    }

    public List<FececFirmante> getListaFirmantes() {
        return listaFirmantes;
    }

    public void setListaFirmantes(List<FececFirmante> listaFirmantes) {
        this.listaFirmantes = listaFirmantes;
    }

    public List<FececMetodo> getListaMetodos() {
        return listaMetodos;
    }

    public void setListaMetodos(List<FececMetodo> listaMetodos) {
        this.listaMetodos = listaMetodos;
    }

    public List<FececTipoPropuesta> getListaTipoPropuestas() {
        return listaTipoPropuestas;
    }

    public void setListaTipoPropuestas(List<FececTipoPropuesta> listaTipoPropuestas) {
        this.listaTipoPropuestas = listaTipoPropuestas;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<TipoFechasComiteEnum> getListaFechasComiteEnum() {
        return listaFechasComiteEnum;
    }

    public void setListaFechasComiteEnum(List<TipoFechasComiteEnum> listaFechasComiteEnum) {
        this.listaFechasComiteEnum = listaFechasComiteEnum;
    }

    public List<ContadorInsumosSubAdmin> getListaContadorPropuestas() {
        return listaContadorPropuestas;
    }

    public void setListaContadorPropuestas(List<ContadorInsumosSubAdmin> listaContadorPropuestas) {
        this.listaContadorPropuestas = listaContadorPropuestas;
    }

    public List<EmpleadoDTO> getFirmantesEmpleado() {
        return firmantesEmpleado;
    }

    public void setFirmantesEmpleado(List<EmpleadoDTO> firmantesEmpleado) {
        this.firmantesEmpleado = firmantesEmpleado;
    }

    public List<String> getLstPrioridadGrid() {
        return lstPrioridadGrid;
    }

    public void setLstPrioridadGrid(List<String> lstPrioridadGrid) {
        this.lstPrioridadGrid = lstPrioridadGrid;
    }

    public List<String> getLstPrioridadFiltros() {
        return lstPrioridadFiltros;
    }

    public void setLstPrioridadFiltros(List<String> lstPrioridadFiltros) {
        this.lstPrioridadFiltros = lstPrioridadFiltros;
    }

}