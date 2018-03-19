/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarProcedenciaInsumoAttributesHelper extends ValidarProcedenciaInsumoAttributesAbstract {

    private static final long serialVersionUID = 5646593452824640075L;

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
    
    private boolean tipoEmpleado;
    public void setIdInsumoHistorico(String idInsumoHistorico) {
        this.idInsumoHistorico = idInsumoHistorico;
    }

    public String getIdInsumoHistorico() {
        return idInsumoHistorico;
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

    public boolean isTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(boolean tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }        

}
