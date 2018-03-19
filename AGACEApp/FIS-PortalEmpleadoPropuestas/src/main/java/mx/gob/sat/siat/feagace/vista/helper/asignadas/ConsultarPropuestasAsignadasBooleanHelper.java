/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultarPropuestasAsignadasBooleanHelper implements Serializable {

    private static final long serialVersionUID = 3502513324066570795L;

    private Boolean muestraDetalleContribuyente;
    private Boolean muestraOrdenOficio;
    private Boolean muestraPropuestas;
    private Boolean muestraBotonGuardarRevision;
    private Boolean muestraFormularioRechazo;
    private Boolean muestraFormularioTransferir;
    private Boolean muestraFormularioRetroalimentar;
    private Boolean muestraDetalle1;
    private Boolean muestraPrevios1;
    private boolean mostrarCargarPromocion;
    private boolean mostrarInfoComplementaria;
    private boolean muestraBotonDetalle;
    private boolean tipoPropuesta;
    private boolean muestraCargaDocumentos;
    private boolean muestraBotonesAccion;
    private boolean muestraActualizaOrden;
    private boolean muestraFormularioCancelar;
    private boolean muestraContribuyenteAnterior;
    private boolean muestraAsignarConsulta;
    private boolean isOrdenActualizable;
    private boolean isOficioActaulizable;
    private boolean muestraDesabilitaPapel;
    private boolean permiteOficios;
    private boolean seActualizoDocumentos;
    private boolean vieneConsultaPropOrd;
    private boolean deshabilitaBtnFormulario;
    private boolean muestraAgenteAduanal;

    public boolean isMuestraDesabilitaPapel() {
        return muestraDesabilitaPapel;
    }

    public void setMuestraDesabilitaPapel(boolean muestraDesabilitaPapel) {
        this.muestraDesabilitaPapel = muestraDesabilitaPapel;
    }

    public boolean isMuestraAsignarConsulta() {
        return muestraAsignarConsulta;
    }

    public void setMuestraAsignarConsulta(boolean muestraAsignarConsulta) {
        this.muestraAsignarConsulta = muestraAsignarConsulta;
    }

    public ConsultarPropuestasAsignadasBooleanHelper() {
        this.muestraDetalleContribuyente = false;
        this.muestraOrdenOficio = false;
        this.mostrarInfoComplementaria = false;
        this.muestraFormularioRechazo = false;
        this.muestraFormularioRetroalimentar = false;
        this.muestraFormularioTransferir = false;
        this.muestraPropuestas = true;
        this.muestraBotonGuardarRevision = false;
        this.muestraBotonDetalle = false;
        this.mostrarCargarPromocion = false;
        this.muestraDetalle1 = false;
        this.muestraPrevios1 = false;
        this.muestraCargaDocumentos = false;
        this.muestraActualizaOrden = false;
        this.muestraBotonesAccion = false;
        this.muestraFormularioCancelar = false;
        this.muestraContribuyenteAnterior = false;
        this.isOrdenActualizable = false;
        this.isOficioActaulizable = false;
        this.muestraDesabilitaPapel = false;
        this.permiteOficios = false;
        this.seActualizoDocumentos = false;
        this.vieneConsultaPropOrd = false;
        this.deshabilitaBtnFormulario = false;
    }

    public Boolean getMuestraDetalleContribuyente() {
        return muestraDetalleContribuyente;
    }

    public void setMuestraDetalleContribuyente(Boolean muestraDetalleContribuyente) {
        this.muestraDetalleContribuyente = muestraDetalleContribuyente;
    }

    public Boolean getMuestraOrdenOficio() {
        return muestraOrdenOficio;
    }

    public void setMuestraOrdenOficio(Boolean muestraOrdenOficio) {
        this.muestraOrdenOficio = muestraOrdenOficio;
    }

    public Boolean getMuestraPropuestas() {
        return muestraPropuestas;
    }

    public void setMuestraPropuestas(Boolean muestraPropuestas) {
        this.muestraPropuestas = muestraPropuestas;
    }

    public Boolean getMuestraBotonGuardarRevision() {
        return muestraBotonGuardarRevision;
    }

    public void setMuestraBotonGuardarRevision(Boolean muestraBotonGuardarRevision) {
        this.muestraBotonGuardarRevision = muestraBotonGuardarRevision;
    }

    public Boolean getMuestraFormularioRechazo() {
        return muestraFormularioRechazo;
    }

    public void setMuestraFormularioRechazo(Boolean muestraFormularioRechazo) {
        this.muestraFormularioRechazo = muestraFormularioRechazo;
    }

    public Boolean getMuestraFormularioTransferir() {
        return muestraFormularioTransferir;
    }

    public void setMuestraFormularioTransferir(Boolean muestraFormularioTransferir) {
        this.muestraFormularioTransferir = muestraFormularioTransferir;
    }

    public Boolean getMuestraFormularioRetroalimentar() {
        return muestraFormularioRetroalimentar;
    }

    public void setMuestraFormularioRetroalimentar(Boolean muestraFormularioRetroalimentar) {
        this.muestraFormularioRetroalimentar = muestraFormularioRetroalimentar;
    }

    public Boolean getMuestraDetalle1() {
        return muestraDetalle1;
    }

    public void setMuestraDetalle1(Boolean muestraDetalle1) {
        this.muestraDetalle1 = muestraDetalle1;
    }

    public Boolean getMuestraPrevios1() {
        return muestraPrevios1;
    }

    public void setMuestraPrevios1(Boolean muestraPrevios1) {
        this.muestraPrevios1 = muestraPrevios1;
    }

    public boolean isMostrarCargarPromocion() {
        return mostrarCargarPromocion;
    }

    public void setMostrarCargarPromocion(boolean mostrarCargarPromocion) {
        this.mostrarCargarPromocion = mostrarCargarPromocion;
    }

    public boolean isMostrarInfoComplementaria() {
        return mostrarInfoComplementaria;
    }

    public void setMostrarInfoComplementaria(boolean mostrarInfoComplementaria) {
        this.mostrarInfoComplementaria = mostrarInfoComplementaria;
    }

    public boolean isMuestraBotonDetalle() {
        return muestraBotonDetalle;
    }

    public void setMuestraBotonDetalle(boolean muestraBotonDetalle) {
        this.muestraBotonDetalle = muestraBotonDetalle;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public boolean isMuestraCargaDocumentos() {
        return muestraCargaDocumentos;
    }

    public void setMuestraCargaDocumentos(boolean muestraCargaDocumentos) {
        this.muestraCargaDocumentos = muestraCargaDocumentos;
    }

    public boolean isMuestraBotonesAccion() {
        return muestraBotonesAccion;
    }

    public void setMuestraBotonesAccion(boolean muestraBotonesAccion) {
        this.muestraBotonesAccion = muestraBotonesAccion;
    }

    public boolean isMuestraActualizaOrden() {
        return muestraActualizaOrden;
    }

    public void setMuestraActualizaOrden(boolean muestraActualizaOrden) {
        this.muestraActualizaOrden = muestraActualizaOrden;
    }

    public boolean isMuestraFormularioCancelar() {
        return muestraFormularioCancelar;
    }

    public void setMuestraFormularioCancelar(boolean muestraFormularioCancelar) {
        this.muestraFormularioCancelar = muestraFormularioCancelar;
    }

    public boolean isMuestraContribuyenteAnterior() {
        return muestraContribuyenteAnterior;
    }

    public void setMuestraContribuyenteAnterior(boolean muestraContribuyenteAnterior) {
        this.muestraContribuyenteAnterior = muestraContribuyenteAnterior;
    }

    public boolean isOrdenActualizable() {
        return isOrdenActualizable;
    }

    public void setOrdenActualizable(boolean isOrdenActualizable) {
        this.isOrdenActualizable = isOrdenActualizable;
    }

    public boolean isOficioActaulizable() {
        return isOficioActaulizable;
    }

    public void setOficioActaulizable(boolean isOficioActaulizable) {
        this.isOficioActaulizable = isOficioActaulizable;
    }

    public boolean isPermiteOficios() {
        return permiteOficios;
    }

    public void setPermiteOficios(boolean permiteOficios) {
        this.permiteOficios = permiteOficios;
    }

    public boolean isSeActualizoDocumentos() {
        return seActualizoDocumentos;
    }

    public void setSeActualizoDocumentos(boolean seActualizoDocumentos) {
        this.seActualizoDocumentos = seActualizoDocumentos;
    }

    public boolean isVieneConsultaPropOrd() {
        return vieneConsultaPropOrd;
    }

    public void setVieneConsultaPropOrd(boolean vieneConsultaPropOrd) {
        this.vieneConsultaPropOrd = vieneConsultaPropOrd;
    }

    public boolean isDeshabilitaBtnFormulario() {
        return deshabilitaBtnFormulario;
    }

    public void setDeshabilitaBtnFormulario(boolean deshabilitaBtnFormulario) {
        this.deshabilitaBtnFormulario = deshabilitaBtnFormulario;
    }

    public boolean isMuestraAgenteAduanal() {
        return muestraAgenteAduanal;
    }

    public void setMuestraAgenteAduanal(boolean muestraAgenteAduanal) {
        this.muestraAgenteAduanal = muestraAgenteAduanal;
    }

}
