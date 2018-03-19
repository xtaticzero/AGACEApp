/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaPropuestasHelper extends ConsultaPropuestasAbstract {

    private static final long serialVersionUID = 8867183570157313961L;

    private boolean flgMostrarUnidadesDesahogo;
    private boolean flgMostrarTlbCategorias;
    private boolean flgMostrarTlbSubordinados;
    private boolean flgMostrarTlbPropuestas;
    private boolean flgMostrarDetallePropuesta;
    private boolean flgBtnCentralRegional;

    //Bandera cuando se consultan detalle de subordinados
    private boolean flgRegresarASubordinado;
    private boolean flgPantallaGrupoEstatus;

    private Integer idUnidadAdminSeleccionada;

    private transient ConsultaEjecutivaPropuestasBO consultaPropuestasBO;
    private FiltroConsultaPropuestas filtro;
    private AraceDTO unidadAdminSeleccionada;
    private FecetPropuesta propuestaSeleccionada;

    private List<FecetPropuesta> lstPropuestasResult;
    private transient Map.Entry<AgrupadorEstatusPropuestasEnum, Integer> grupoEstatusSeleccionado;
    private transient Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado;

    public ConsultaPropuestasHelper() {
        flgMostrarUnidadesDesahogo = false;
        flgMostrarTlbCategorias = false;
        flgMostrarTlbSubordinados = false;
        flgMostrarTlbPropuestas = false;
        flgMostrarDetallePropuesta = false;
        flgRegresarASubordinado = false;
        flgPantallaGrupoEstatus = false;
        flgBtnCentralRegional = false;
    }

    public boolean isFlgMostrarUnidadesDesahogo() {
        return flgMostrarUnidadesDesahogo;
    }

    public void setFlgMostrarUnidadesDesahogo(boolean flgMostrarUnidadesDesahogo) {
        this.flgMostrarUnidadesDesahogo = flgMostrarUnidadesDesahogo;
    }

    public boolean isFlgMostrarTlbCategorias() {
        return flgMostrarTlbCategorias;
    }

    public void setFlgMostrarTlbCategorias(boolean flgMostrarTlbCategorias) {
        this.flgMostrarTlbCategorias = flgMostrarTlbCategorias;
    }

    public boolean isFlgMostrarTlbSubordinados() {
        return flgMostrarTlbSubordinados;
    }

    public void setFlgMostrarTlbSubordinados(boolean flgMostrarTlbSubordinados) {
        this.flgMostrarTlbSubordinados = flgMostrarTlbSubordinados;
    }

    public boolean isFlgMostrarTlbPropuestas() {
        return flgMostrarTlbPropuestas;
    }

    public void setFlgMostrarTlbPropuestas(boolean flgMostrarTlbPropuestas) {
        this.flgMostrarTlbPropuestas = flgMostrarTlbPropuestas;
    }

    public boolean isFlgMostrarDetallePropuesta() {
        return flgMostrarDetallePropuesta;
    }

    public void setFlgMostrarDetallePropuesta(boolean flgMostrarDetallePropuesta) {
        this.flgMostrarDetallePropuesta = flgMostrarDetallePropuesta;
    }

    public boolean isFlgRegresarASubordinado() {
        return flgRegresarASubordinado;
    }

    public void setFlgRegresarASubordinado(boolean flgRegresarASubordinado) {
        this.flgRegresarASubordinado = flgRegresarASubordinado;
    }

    public boolean isFlgPantallaGrupoEstatus() {
        return flgPantallaGrupoEstatus;
    }

    public void setFlgPantallaGrupoEstatus(boolean flgPantallaGrupoEstatus) {
        this.flgPantallaGrupoEstatus = flgPantallaGrupoEstatus;
    }

    public Integer getIdUnidadAdminSeleccionada() {
        return idUnidadAdminSeleccionada;
    }

    public void setIdUnidadAdminSeleccionada(Integer idUnidadAdminSeleccionada) {
        this.idUnidadAdminSeleccionada = idUnidadAdminSeleccionada;
    }

    public ConsultaEjecutivaPropuestasBO getConsultaPropuestasBO() {
        return consultaPropuestasBO;
    }

    public void setConsultaPropuestasBO(ConsultaEjecutivaPropuestasBO consultaPropuestasBO) {
        this.consultaPropuestasBO = consultaPropuestasBO;
    }

    public FiltroConsultaPropuestas getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroConsultaPropuestas filtro) {
        this.filtro = filtro;
    }

    public AraceDTO getUnidadAdminSeleccionada() {
        return unidadAdminSeleccionada;
    }

    public void setUnidadAdminSeleccionada(AraceDTO unidadAdminSeleccionada) {
        this.unidadAdminSeleccionada = unidadAdminSeleccionada;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public List<FecetPropuesta> getLstPropuestasResult() {
        return lstPropuestasResult;
    }

    public void setLstPropuestasResult(List<FecetPropuesta> lstPropuestasResult) {
        this.lstPropuestasResult = lstPropuestasResult;
    }

    public Map.Entry<AgrupadorEstatusPropuestasEnum, Integer> getGrupoEstatusSeleccionado() {
        return grupoEstatusSeleccionado;
    }

    public void setGrupoEstatusSeleccionado(Map.Entry<AgrupadorEstatusPropuestasEnum, Integer> grupoEstatusSeleccionado) {
        this.grupoEstatusSeleccionado = grupoEstatusSeleccionado;
    }

    public Map.Entry<EmpleadoDTO, Integer> getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public boolean isFlgBtnCentralRegional() {
        return flgBtnCentralRegional;
    }

    public void setFlgBtnCentralRegional(boolean flgBtnCentralRegional) {
        this.flgBtnCentralRegional = flgBtnCentralRegional;
    }

}
