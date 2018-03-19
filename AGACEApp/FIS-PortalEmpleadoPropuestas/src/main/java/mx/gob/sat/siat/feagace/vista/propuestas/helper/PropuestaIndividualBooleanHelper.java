/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaIndividualBooleanHelper implements Serializable {

    private static final long serialVersionUID = 985058768738789885L;

    private boolean habilitarBuscarRFC = true;
    private boolean habilitarCamposImpuestos = true;
    private boolean visibleUnidad;
    private boolean visibleRevision;
    private boolean visibleFechaPre;
    private boolean visibleFechaInf;
    private boolean conImpuestos;
    private boolean mostrarFormulario;
    private boolean complementaInsumo;
    private boolean complementaCambioMetodo;
    private boolean habilitaBotonRFC;
    private boolean tipoPropuesta;
    private boolean habilitaImpuesto;
    private boolean habilitaCampoMonto;

    public void setConImpuestos(boolean conImpuestos) {
        this.conImpuestos = conImpuestos;
    }

    public boolean isConImpuestos() {
        return conImpuestos;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public void setComplementaCambioMetodo(boolean complementaCambioMetodo) {
        this.complementaCambioMetodo = complementaCambioMetodo;
    }

    public boolean isComplementaCambioMetodo() {
        return complementaCambioMetodo;
    }

    public void setHabilitaBotonRFC(boolean habilitaBotonRFC) {
        this.habilitaBotonRFC = habilitaBotonRFC;
    }

    public boolean isHabilitaBotonRFC() {
        return habilitaBotonRFC;
    }

    public void setComplementaInsumo(boolean complementaInsumo) {
        this.complementaInsumo = complementaInsumo;
    }

    public boolean isComplementaInsumo() {
        return complementaInsumo;
    }

    public void setHabilitarCamposImpuestos(boolean habilitarCamposImpuestos) {
        this.habilitarCamposImpuestos = habilitarCamposImpuestos;
    }

    public boolean isHabilitarCamposImpuestos() {
        return habilitarCamposImpuestos;
    }

    public void setHabilitarBuscarRFC(boolean habilitarBuscarRFC) {
        this.habilitarBuscarRFC = habilitarBuscarRFC;
    }

    public boolean isHabilitarBuscarRFC() {
        return habilitarBuscarRFC;
    }

    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }

    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }

    public void setVisibleUnidad(boolean visibleUnidad) {
        this.visibleUnidad = visibleUnidad;
    }

    public boolean isVisibleUnidad() {
        return visibleUnidad;
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
    
    public void setVisibleRevision(boolean visibleRevision) {
        this.visibleRevision = visibleRevision;
    }

    public boolean isVisibleRevision() {
        return visibleRevision;
    }

    public void setHabilitaImpuesto(boolean habilitaImpuesto) {
        this.habilitaImpuesto = habilitaImpuesto;
    }

    public boolean isHabilitaImpuesto() {
        return habilitaImpuesto;
    }

    public void setHabilitaCampoMonto(boolean habilitaCampoMonto) {
        this.habilitaCampoMonto = habilitaCampoMonto;
    }

    public boolean isHabilitaCampoMonto() {
        return habilitaCampoMonto;
    }
}
