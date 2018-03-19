/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.helper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.util.constantes.AcppceOpcionesEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaInsumosHelper implements Serializable {

    private static final long serialVersionUID = -3978442524410854643L;

    private boolean flgMostrarUnidadesDesahogo;
    private boolean flgMostrarEstatusSemaforo;
    private boolean flgMostrarTlbCategorias;
    private boolean flgMostrarTlbInsumos;
    private boolean flgMostrarDetalleInsumo;
    private boolean flgMostrarLstSemaforoFiltrado;
    private boolean flgPaginaEstatusSemaforos;

    private boolean flgTblRetroRechazo;
    private boolean flgTblDoctosRetro;
    private boolean flgTblDoctosSolicitud;

    private boolean flgTblDoctosRechazo;
    //Bandera cuando se consultan detalle de subordinados
    private boolean flgRegresarASubordinado;
    private boolean flgEstatusFiltrados;

    private Integer plazoSeleccionado;
    private Integer idUnidadAdminSeleccionada;

    private transient ConsultaEjecutivaInsumosBO consultaInsumosBO;
    private FiltroConsultaInsumos filtro;
    private AraceDTO unidadAdminSeleccionada;
    private FecetInsumo insumosSeleccionado;
    private RetroalimentacionInsumoDTO retroalimentacionSeleccionada;
    private FecetRechazoInsumo rechazoSeleccionado;
    private List<FecetRechazoInsumo> lstRechazo;

    private List<FecetInsumo> lstInsumosResult;
    private List<FecetInsumo> lstInsumosFiltered;
    private transient Map.Entry<TipoEstatusEnum, Integer> estatusSeleccionado;
    private transient Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado;
    private transient Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado;
    private transient Map.Entry<SemaforoEnum, Integer> segundoSemaforoSeleccionado;
    private transient AcppceOpcionesEnum opcionSelccionada;

    public ConsultaInsumosHelper() {
        flgMostrarUnidadesDesahogo = false;
        flgMostrarEstatusSemaforo = false;
        flgMostrarTlbCategorias = false;
        flgMostrarTlbInsumos = false;
        flgMostrarDetalleInsumo = false;
        flgMostrarLstSemaforoFiltrado = false;
        plazoSeleccionado = null;
        flgPaginaEstatusSemaforos = false;
        flgTblRetroRechazo = false;
        flgTblDoctosRetro = false;
        flgTblDoctosSolicitud = false;
        flgTblDoctosRechazo = false;
        flgRegresarASubordinado = false;
        flgEstatusFiltrados = false;
                
    }

    public ConsultaEjecutivaInsumosBO getConsultaInsumosBO() {
        return consultaInsumosBO;
    }

    public void setConsultaInsumosBO(ConsultaEjecutivaInsumosBO consultaInsumosBO) {
        this.consultaInsumosBO = consultaInsumosBO;
    }

    public FiltroConsultaInsumos getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroConsultaInsumos filtro) {
        this.filtro = filtro;
    }

    public boolean isFlgMostrarUnidadesDesahogo() {
        return flgMostrarUnidadesDesahogo;
    }

    public void setFlgMostrarUnidadesDesahogo(boolean flgMostrarUnidadesDesahogo) {
        this.flgMostrarUnidadesDesahogo = flgMostrarUnidadesDesahogo;
    }

    public boolean isFlgMostrarEstatusSemaforo() {
        return flgMostrarEstatusSemaforo;
    }

    public void setFlgMostrarEstatusSemaforo(boolean flgMostrarEstatusSemaforo) {
        this.flgMostrarEstatusSemaforo = flgMostrarEstatusSemaforo;
    }

    public boolean isFlgMostrarTlbCategorias() {
        return flgMostrarTlbCategorias;
    }

    public void setFlgMostrarTlbCategorias(boolean flgMostrarTlbCategorias) {
        this.flgMostrarTlbCategorias = flgMostrarTlbCategorias;
    }

    public boolean isFlgMostrarTlbInsumos() {
        return flgMostrarTlbInsumos;
    }

    public void setFlgMostrarTlbInsumos(boolean flgMostrarTlbInsumos) {
        this.flgMostrarTlbInsumos = flgMostrarTlbInsumos;
    }

    public boolean isFlgMostrarDetalleInsumo() {
        return flgMostrarDetalleInsumo;
    }

    public void setFlgMostrarDetalleInsumo(boolean flgMostrarDetalleInsumo) {
        this.flgMostrarDetalleInsumo = flgMostrarDetalleInsumo;
    }

    public AraceDTO getUnidadAdminSeleccionada() {
        return unidadAdminSeleccionada;
    }

    public void setUnidadAdminSeleccionada(AraceDTO unidadAdminSeleccionada) {
        this.unidadAdminSeleccionada = unidadAdminSeleccionada;
    }

    public Integer getIdUnidadAdminSeleccionada() {
        return idUnidadAdminSeleccionada;
    }

    public void setIdUnidadAdminSeleccionada(Integer idUnidadAdminSeleccionada) {
        this.idUnidadAdminSeleccionada = idUnidadAdminSeleccionada;
    }

    public Integer getPlazoSeleccionado() {
        return plazoSeleccionado;
    }

    public void setPlazoSeleccionado(Integer plazoSeleccionado) {
        this.plazoSeleccionado = plazoSeleccionado;
    }

    public List<FecetInsumo> getLstInsumosResult() {
        return lstInsumosResult;
    }

    public void setLstInsumosResult(List<FecetInsumo> lstInsumosResult) {
        this.lstInsumosResult = lstInsumosResult;
    }

    public Map.Entry<TipoEstatusEnum, Integer> getEstatusSeleccionado() {
        return estatusSeleccionado;
    }

    public void setEstatusSeleccionado(Map.Entry<TipoEstatusEnum, Integer> estatusSeleccionado) {
        this.estatusSeleccionado = estatusSeleccionado;
    }

    public Map.Entry<EmpleadoDTO, Integer> getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Map.Entry<SemaforoEnum, Integer> getSemaforoSeleccionado() {
        return semaforoSeleccionado;
    }

    public void setSemaforoSeleccionado(Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado) {
        this.semaforoSeleccionado = semaforoSeleccionado;
    }

    public boolean isFlgMostrarLstSemaforoFiltrado() {
        return flgMostrarLstSemaforoFiltrado;
    }

    public void setFlgMostrarLstSemaforoFiltrado(boolean flgMostrarLstSemaforoFiltrado) {
        this.flgMostrarLstSemaforoFiltrado = flgMostrarLstSemaforoFiltrado;
    }

    public boolean isFlgPaginaEstatusSemaforos() {
        return flgPaginaEstatusSemaforos;
    }

    public void setFlgPaginaEstatusSemaforos(boolean flgPaginaEstatusSemaforos) {
        this.flgPaginaEstatusSemaforos = flgPaginaEstatusSemaforos;
    }

    public FecetInsumo getInsumosSeleccionado() {
        return insumosSeleccionado;
    }

    public void setInsumosSeleccionado(FecetInsumo insumosSeleccionado) {
        this.insumosSeleccionado = insumosSeleccionado;
    }

    public RetroalimentacionInsumoDTO getRetroalimentacionSeleccionada() {
        return retroalimentacionSeleccionada;
    }

    public void setRetroalimentacionSeleccionada(RetroalimentacionInsumoDTO retroalimentacionSeleccionada) {
        this.retroalimentacionSeleccionada = retroalimentacionSeleccionada;
    }

    public Map.Entry<SemaforoEnum, Integer> getSegundoSemaforoSeleccionado() {
        return segundoSemaforoSeleccionado;
    }

    public void setSegundoSemaforoSeleccionado(Map.Entry<SemaforoEnum, Integer> segundoSemaforoSeleccionado) {
        this.segundoSemaforoSeleccionado = segundoSemaforoSeleccionado;
    }

    public boolean isFlgTblDoctosRetro() {
        return flgTblDoctosRetro;
    }

    public void setFlgTblDoctosRetro(boolean flgTblDoctosRetro) {
        this.flgTblDoctosRetro = flgTblDoctosRetro;
    }

    public boolean isFlgTblDoctosSolicitud() {
        return flgTblDoctosSolicitud;
    }

    public void setFlgTblDoctosSolicitud(boolean flgTblDoctosSolicitud) {
        this.flgTblDoctosSolicitud = flgTblDoctosSolicitud;
    }

    public boolean isFlgTblDoctosRechazo() {
        return flgTblDoctosRechazo;
    }

    public void setFlgTblDoctosRechazo(boolean flgTblDoctosRechazo) {
        this.flgTblDoctosRechazo = flgTblDoctosRechazo;
    }

    public boolean isFlgTblRetroRechazo() {
        return flgTblRetroRechazo;
    }

    public void setFlgTblRetroRechazo(boolean flgTblRetroRechazo) {
        this.flgTblRetroRechazo = flgTblRetroRechazo;
    }

    public boolean isFlgRegresarASubordinado() {
        return flgRegresarASubordinado;
    }

    public void setFlgRegresarASubordinado(boolean flgRegresarASubordinado) {
        this.flgRegresarASubordinado = flgRegresarASubordinado;
    }

    public List<FecetRechazoInsumo> getLstRechazo() {
        return lstRechazo;
    }

    public void setLstRechazo(List<FecetRechazoInsumo> lstRechazo) {
        this.lstRechazo = lstRechazo;
    }

    public FecetRechazoInsumo getRechazoSeleccionado() {
        return rechazoSeleccionado;
    }

    public void setRechazoSeleccionado(FecetRechazoInsumo rechazoSeleccionado) {
        this.rechazoSeleccionado = rechazoSeleccionado;
    }

    public boolean isFlgEstatusFiltrados() {
        return flgEstatusFiltrados;
    }

    public void setFlgEstatusFiltrados(boolean flgEstatusFiltrados) {
        this.flgEstatusFiltrados = flgEstatusFiltrados;
    }

    public final List<FecetInsumo> getLstInsumosFiltered() {
        return lstInsumosFiltered;
    }

    public final void setLstInsumosFiltered(List<FecetInsumo> lstInsumosFiltered) {
        this.lstInsumosFiltered = lstInsumosFiltered;
    }

    public final AcppceOpcionesEnum getOpcionSelccionada() {
        return opcionSelccionada;
    }

    public final void setOpcionSelccionada(AcppceOpcionesEnum opcionSelccionada) {
        this.opcionSelccionada = opcionSelccionada;
    }

    public final AcppceOpcionesEnum[] getOpciones() {
        return AcppceOpcionesEnum.values();
    }
}
