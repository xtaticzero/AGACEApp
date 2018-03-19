/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.insumos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ConsultaGeneralManagedBean;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ConsultaGeneralInsumosAttributes extends ConsultaGeneralManagedBean {

    private static final long serialVersionUID = 3629332031575371191L;

    private BigDecimal unidadRegistro;

    private BigDecimal unidadAdmonRegistro;

    private BigDecimal unidadAdministrativa;

    private Map<SemaforoEnum, Integer> resumenSemaforos;

    private Map<SemaforoEnum, Integer> resumenSemaforosAdmon;

    private Map<SemaforoEnum, Integer> resumenSemaforosSubAdmon;

    private Map<TipoEstatusEnum, Integer> resumen;

    private Map<EmpleadoDTO, Integer> resumenAdmon;

    private Map<EmpleadoDTO, Integer> resumenSubAdmon;

    private Integer totalEstatus;

    private Integer totalSemaforo;

    private Integer plazoSeleccionado;

    private List<FecetInsumo> insumos;

    private List<FecetInsumo> insumosAdmon;

    private List<FecetInsumo> insumosSubAdmon;

    private List<FecetInsumo> insumosFiltradosxPlazo;

    private List<FecetInsumo> insumosDetalle;

    private List<FecetInsumo> insumosFiltradosDetalle;

    private transient Map.Entry<TipoEstatusEnum, Integer> estatusSeleccionados;

    private transient Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado;

    private transient Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado;

    private FecetInsumo insumosSeleccionado;

    private int numeroSemaforo;

    private RetroalimentacionInsumoDTO retroalimentacionSeleccionada;

    private FecetRechazoInsumo rechazoSeleccionado;

    private List<FecetRechazoInsumo> lstRechazo;

    protected static final int DOS_NEGATIVO = (-1) * Constantes.ENTERO_DOS;
    protected static final int UNO_NEGATIVO = (-1) * Constantes.ENTERO_UNO;

    public int getNumeroSemaforo() {
        return numeroSemaforo;
    }

    public void setNumeroSemaforo(int numeroSemaforo) {
        this.numeroSemaforo = numeroSemaforo;
    }

    public List<FecetInsumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<FecetInsumo> insumos) {
        this.insumos = insumos;
    }

    public BigDecimal getUnidadRegistro() {
        return unidadRegistro;
    }

    public void setUnidadRegistro(BigDecimal unidadRegistro) {
        this.unidadRegistro = unidadRegistro;
    }

    public BigDecimal getUnidadAdmonRegistro() {
        return unidadAdmonRegistro;
    }

    public void setUnidadAdmonRegistro(BigDecimal unidadAdmonRegistro) {
        this.unidadAdmonRegistro = unidadAdmonRegistro;
    }

    public BigDecimal getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(BigDecimal unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Map<TipoEstatusEnum, Integer> getResumen() {
        return resumen;
    }

    public void setResumen(Map<TipoEstatusEnum, Integer> resumen) {
        this.resumen = resumen;
    }

    public Map<SemaforoEnum, Integer> getResumenSemaforos() {
        return resumenSemaforos;
    }

    public void setResumenSemaforos(Map<SemaforoEnum, Integer> resumenSemaforos) {
        this.resumenSemaforos = resumenSemaforos;
    }

    public Integer getTotalEstatus() {
        return totalEstatus;
    }

    public void setTotalEstatus(Integer totalEstatus) {
        this.totalEstatus = totalEstatus;
    }

    public Integer getTotalSemaforo() {
        return totalSemaforo;
    }

    public void setTotalSemaforo(Integer totalSemaforo) {
        this.totalSemaforo = totalSemaforo;
    }

    public Integer getPlazoSeleccionado() {
        return plazoSeleccionado;
    }

    public void setPlazoSeleccionado(Integer plazoSeleccionado) {
        this.plazoSeleccionado = plazoSeleccionado;
    }

    public List<FecetInsumo> getInsumosFiltradosxPlazo() {
        return insumosFiltradosxPlazo;
    }

    public void setInsumosFiltradosxPlazo(List<FecetInsumo> insumosFiltradosxPlazo) {
        this.insumosFiltradosxPlazo = insumosFiltradosxPlazo;
    }

    public List<FecetInsumo> getInsumosDetalle() {
        return insumosDetalle;
    }

    public void setInsumosDetalle(List<FecetInsumo> insumosDetalle) {
        this.insumosDetalle = insumosDetalle;
    }

    public Map.Entry<TipoEstatusEnum, Integer> getEstatusSeleccionados() {
        return estatusSeleccionados;
    }

    public void setEstatusSeleccionados(Map.Entry<TipoEstatusEnum, Integer> estatusSeleccionados) {
        this.estatusSeleccionados = estatusSeleccionados;
    }

    public Map.Entry<SemaforoEnum, Integer> getSemaforoSeleccionado() {
        return semaforoSeleccionado;
    }

    public void setSemaforoSeleccionado(Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado) {
        this.semaforoSeleccionado = semaforoSeleccionado;
    }

    public List<FecetInsumo> getInsumosFiltradosDetalle() {
        return insumosFiltradosDetalle;
    }

    public void setInsumosFiltradosDetalle(List<FecetInsumo> insumosFiltradosDetalle) {
        this.insumosFiltradosDetalle = insumosFiltradosDetalle;
    }

    public FecetInsumo getInsumosSeleccionado() {
        return insumosSeleccionado;
    }

    public void setInsumosSeleccionado(FecetInsumo insumosSeleccionado) {
        this.insumosSeleccionado = insumosSeleccionado;
    }

    public List<FecetInsumo> getInsumosAdmon() {
        return insumosAdmon;
    }

    public void setInsumosAdmon(List<FecetInsumo> insumosAdmon) {
        this.insumosAdmon = insumosAdmon;
    }

    public List<FecetInsumo> getInsumosSubAdmon() {
        return insumosSubAdmon;
    }

    public void setInsumosSubAdmon(List<FecetInsumo> insumosSubAdmon) {
        this.insumosSubAdmon = insumosSubAdmon;
    }

    public Map<SemaforoEnum, Integer> getResumenSemaforosAdmon() {
        return resumenSemaforosAdmon;
    }

    public void setResumenSemaforosAdmon(Map<SemaforoEnum, Integer> resumenSemaforosAdmon) {
        this.resumenSemaforosAdmon = resumenSemaforosAdmon;
    }

    public Map<SemaforoEnum, Integer> getResumenSemaforosSubAdmon() {
        return resumenSemaforosSubAdmon;
    }

    public void setResumenSemaforosSubAdmon(Map<SemaforoEnum, Integer> resumenSemaforosSubAdmon) {
        this.resumenSemaforosSubAdmon = resumenSemaforosSubAdmon;
    }

    public Map<EmpleadoDTO, Integer> getResumenAdmon() {
        return resumenAdmon;
    }

    public void setResumenAdmon(Map<EmpleadoDTO, Integer> resumenAdmon) {
        this.resumenAdmon = resumenAdmon;
    }

    public Map<EmpleadoDTO, Integer> getResumenSubAdmon() {
        return resumenSubAdmon;
    }

    public void setResumenSubAdmon(Map<EmpleadoDTO, Integer> resumenSubAdmon) {
        this.resumenSubAdmon = resumenSubAdmon;
    }

    public Map.Entry<EmpleadoDTO, Integer> getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public RetroalimentacionInsumoDTO getRetroalimentacionSeleccionada() {
        return retroalimentacionSeleccionada;
    }

    public void setRetroalimentacionSeleccionada(RetroalimentacionInsumoDTO retroalimentacionSeleccionada) {
        this.retroalimentacionSeleccionada = retroalimentacionSeleccionada;
    }

    public FecetRechazoInsumo getRechazoSeleccionado() {
        return rechazoSeleccionado;
    }

    public void setRechazoSeleccionado(FecetRechazoInsumo rechazoSeleccionado) {
        this.rechazoSeleccionado = rechazoSeleccionado;
    }

    public List<FecetRechazoInsumo> getLstRechazo() {
        return lstRechazo;
    }

    public void setLstRechazo(List<FecetRechazoInsumo> lstRechazo) {
        this.lstRechazo = lstRechazo;
    }

}
