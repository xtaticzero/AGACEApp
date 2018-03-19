/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarProcedenciaInsumoAttributesHelper implements Serializable {

    private static final long serialVersionUID = 5646593452824640075L;

    private boolean visibleUnidad;
    private boolean visibleRevision;
    private boolean visibleFechaPre;
    private boolean visibleFechaInf;
    private boolean mostrarPanelOrdenes = false;
    private boolean mostrarPanelInsumo = false;
    private boolean mostrarOpcionesPropuesta = false;
    private Boolean mostrarTablaArchivosRetro;

    private String estatusContacto;
    private String fechaRegional;
    private String mensajeDuplicidad;
    private String segundoDigito;
    private String quintoOctavoDigito;
    private String novenoDoceavoDigito;
    private String motivoRechazo;
    private String motivoRetroalimentacion;
    private String mensajeAntecedentes;
    private String idInsumoHistorico;

    private Date fechaActual;
    private BigDecimal idAraceSeleccionada;
    private BigDecimal idMetodoSeleccionado;
    private BigDecimal areaOrigen;

    public void setIdInsumoHistorico(String idInsumoHistorico) {
        this.idInsumoHistorico = idInsumoHistorico;
    }

    public String getIdInsumoHistorico() {
        return idInsumoHistorico;
    }

    public void setMostrarTablaArchivosRetro(Boolean mostrarTablaArchivosRetro) {
        this.mostrarTablaArchivosRetro = mostrarTablaArchivosRetro;
    }

    public Boolean getMostrarTablaArchivosRetro() {
        return mostrarTablaArchivosRetro;
    }

    public void setAreaOrigen(BigDecimal areaOrigen) {
        this.areaOrigen = areaOrigen;
    }

    public BigDecimal getAreaOrigen() {
        return areaOrigen;
    }

    public void setMensajeAntecedentes(final String mensajeAntecedentes) {
        this.mensajeAntecedentes = mensajeAntecedentes;
    }

    public String getMensajeAntecedentes() {
        return mensajeAntecedentes;
    }

    public void setEstatusContacto(String estatusContacto) {
        this.estatusContacto = estatusContacto;
    }

    public String getEstatusContacto() {
        return estatusContacto;
    }

    public void setVisibleUnidad(boolean visibleUnidad) {
        this.visibleUnidad = visibleUnidad;
    }

    public boolean isVisibleUnidad() {
        return visibleUnidad;
    }

    public void setVisibleRevision(boolean visibleRevision) {
        this.visibleRevision = visibleRevision;
    }

    public boolean isVisibleRevision() {
        return visibleRevision;
    }

    public void setVisibleFechaPre(boolean visibleFechaPre) {
        this.visibleFechaPre = visibleFechaPre;
    }

    public boolean isVisibleFechaPre() {
        return visibleFechaPre;
    }

    public void setVisibleFechaInf(boolean visibleFechaInf) {
        this.visibleFechaInf = visibleFechaInf;
    }

    public boolean isVisibleFechaInf() {
        return visibleFechaInf;
    }

    public void setFechaRegional(String fechaRegional) {
        this.fechaRegional = fechaRegional;
    }

    public String getFechaRegional() {
        return fechaRegional;
    }

    public void setMensajeDuplicidad(String mensajeDuplicidad) {
        this.mensajeDuplicidad = mensajeDuplicidad;
    }

    public String getMensajeDuplicidad() {
        return mensajeDuplicidad;
    }

    public void setSegundoDigito(String segundoDigito) {
        this.segundoDigito = segundoDigito;
    }

    public String getSegundoDigito() {
        return segundoDigito;
    }

    public void setQuintoOctavoDigito(String quintoOctavoDigito) {
        this.quintoOctavoDigito = quintoOctavoDigito;
    }

    public String getQuintoOctavoDigito() {
        return quintoOctavoDigito;
    }

    public void setNovenoDoceavoDigito(String novenoDoceavoDigito) {
        this.novenoDoceavoDigito = novenoDoceavoDigito;
    }

    public String getNovenoDoceavoDigito() {
        return novenoDoceavoDigito;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public void setMostrarPanelOrdenes(boolean mostrarPanelOrdenes) {
        this.mostrarPanelOrdenes = mostrarPanelOrdenes;
    }

    public boolean isMostrarPanelOrdenes() {
        return mostrarPanelOrdenes;
    }

    public void setMostrarPanelInsumo(boolean mostrarPanelInsumo) {
        this.mostrarPanelInsumo = mostrarPanelInsumo;
    }

    public boolean isMostrarPanelInsumo() {
        return mostrarPanelInsumo;
    }

    public void setMostrarOpcionesPropuesta(boolean mostrarOpcionesPropuesta) {
        this.mostrarOpcionesPropuesta = mostrarOpcionesPropuesta;
    }

    public boolean isMostrarOpcionesPropuesta() {
        return mostrarOpcionesPropuesta;
    }

    public void setIdAraceSeleccionada(BigDecimal idAraceSeleccionada) {
        this.idAraceSeleccionada = idAraceSeleccionada;
    }

    public BigDecimal getIdAraceSeleccionada() {
        return idAraceSeleccionada;
    }

    public void setIdMetodoSeleccionado(BigDecimal idMetodoSeleccionado) {
        this.idMetodoSeleccionado = idMetodoSeleccionado;
    }

    public BigDecimal getIdMetodoSeleccionado() {
        return idMetodoSeleccionado;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRetroalimentacion(String motivoRetroalimentacion) {
        this.motivoRetroalimentacion = motivoRetroalimentacion;
    }

    public String getMotivoRetroalimentacion() {
        return motivoRetroalimentacion;
    }

}
