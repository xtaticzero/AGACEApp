/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class AsociarColaboradoresAttributeHelper extends AsociarColaboradoresAttributeAbstractHelper implements Serializable {

    private static final long serialVersionUID = 7693713042892766687L;

    private boolean mostrarDatosApoderadoLegal;
    private boolean mostrarDatosAgenteAduanal;
    private boolean mostrarDatosRepresentanteLegal;
    private boolean mostrarDatosApoderadoLegalRL;
    private boolean mostrarGuardarApoderadoLegal;
    private boolean mostrarGuardarAgenteAduanal;
    private boolean mostrarGuardarRepresentanteLegal;
    private boolean mostrarGuardarApoderadoLegalRL;
    private boolean mostrarProrrogas;
    private boolean mostrarBtnProrrogas;
    private boolean mostrarPruebasAlegatos;
    private boolean mostrarPanelOrdenAsociarColaboradores;
    private boolean mostrarPruebasPericiales;
    private boolean mostrarBtnPruebasPericiales;
    private boolean mostrarAdjuntarDocumentoRL;
    private boolean mostrarAdjuntarDocumentoAL;
    private boolean mostrarAdjuntarDocumentoALRL;
    private boolean mostrarCancelarBusquedaAL;
    private boolean mostrarCancelarBusquedaRL;
    private boolean mostrarCancelarBusquedaALRL;
    private boolean mostrarOrdenSinAL;
    private boolean mostrarOrdenSinALRL;
    private boolean mostrarOrdenSinRL;

    public boolean isMostrarAdjuntarDocumentoALRL() {
        return mostrarAdjuntarDocumentoALRL;
    }

    public void setMostrarAdjuntarDocumentoALRL(boolean mostrarAdjuntarDocumentoALRL) {
        this.mostrarAdjuntarDocumentoALRL = mostrarAdjuntarDocumentoALRL;
    }

    public boolean isMostrarAdjuntarDocumentoAL() {
        return mostrarAdjuntarDocumentoAL;
    }

    public void setMostrarAdjuntarDocumentoAL(boolean mostrarAdjuntarDocumentoAL) {
        this.mostrarAdjuntarDocumentoAL = mostrarAdjuntarDocumentoAL;
    }

    private String leyendaOrdenSinAsociado;

    public boolean isMostrarBtnPruebasPericiales() {
        return mostrarBtnPruebasPericiales;
    }

    public void setMostrarBtnPruebasPericiales(boolean mostrarBtnPruebasPericiales) {
        this.mostrarBtnPruebasPericiales = mostrarBtnPruebasPericiales;
    }

    public boolean isMostrarPruebasPericiales() {
        return mostrarPruebasPericiales;
    }

    public void setMostrarPruebasPericiales(boolean mostrarPruebasPericiales) {
        this.mostrarPruebasPericiales = mostrarPruebasPericiales;
    }

    public void setMostrarPanelOrdenAsociarColaboradores(boolean mostrarPanelOrdenAsociarColaboradores) {
        this.mostrarPanelOrdenAsociarColaboradores = mostrarPanelOrdenAsociarColaboradores;
    }

    public boolean isMostrarPanelOrdenAsociarColaboradores() {
        return mostrarPanelOrdenAsociarColaboradores;
    }

    public void setMostrarDatosApoderadoLegal(boolean mostrarDatosApoderadoLegal) {
        this.mostrarDatosApoderadoLegal = mostrarDatosApoderadoLegal;
    }

    public boolean isMostrarDatosApoderadoLegal() {
        return mostrarDatosApoderadoLegal;
    }

    public void setMostrarDatosAgenteAduanal(boolean mostrarDatosAgenteAduanal) {
        this.mostrarDatosAgenteAduanal = mostrarDatosAgenteAduanal;
    }

    public boolean isMostrarDatosAgenteAduanal() {
        return mostrarDatosAgenteAduanal;
    }

    public void setMostrarDatosRepresentanteLegal(boolean mostrarDatosRepresentanteLegal) {
        this.mostrarDatosRepresentanteLegal = mostrarDatosRepresentanteLegal;
    }

    public boolean isMostrarDatosRepresentanteLegal() {
        return mostrarDatosRepresentanteLegal;
    }

    public void setMostrarDatosApoderadoLegalRL(boolean mostrarDatosApoderadoLegalRL) {
        this.mostrarDatosApoderadoLegalRL = mostrarDatosApoderadoLegalRL;
    }

    public boolean isMostrarDatosApoderadoLegalRL() {
        return mostrarDatosApoderadoLegalRL;
    }

    public void setMostrarGuardarApoderadoLegal(boolean mostrarGuardarApoderadoLegal) {
        this.mostrarGuardarApoderadoLegal = mostrarGuardarApoderadoLegal;
    }

    public boolean isMostrarGuardarApoderadoLegal() {
        return mostrarGuardarApoderadoLegal;
    }

    public void setMostrarGuardarAgenteAduanal(boolean mostrarGuardarAgenteAduanal) {
        this.mostrarGuardarAgenteAduanal = mostrarGuardarAgenteAduanal;
    }

    public boolean isMostrarGuardarAgenteAduanal() {
        return mostrarGuardarAgenteAduanal;
    }

    public void setMostrarGuardarRepresentanteLegal(boolean mostrarGuardarRepresentanteLegal) {
        this.mostrarGuardarRepresentanteLegal = mostrarGuardarRepresentanteLegal;
    }

    public boolean isMostrarGuardarRepresentanteLegal() {
        return mostrarGuardarRepresentanteLegal;
    }

    public void setMostrarGuardarApoderadoLegalRL(boolean mostrarGuardarApoderadoLegalRL) {
        this.mostrarGuardarApoderadoLegalRL = mostrarGuardarApoderadoLegalRL;
    }

    public boolean isMostrarGuardarApoderadoLegalRL() {
        return mostrarGuardarApoderadoLegalRL;
    }

    public void setMostrarProrrogas(boolean mostrarProrrogas) {
        this.mostrarProrrogas = mostrarProrrogas;
    }

    public boolean isMostrarProrrogas() {
        return mostrarProrrogas;
    }

    public void setMostrarBtnProrrogas(boolean mostrarBtnProrrogas) {
        this.mostrarBtnProrrogas = mostrarBtnProrrogas;
    }

    public boolean isMostrarBtnProrrogas() {
        return mostrarBtnProrrogas;
    }

    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
    }

    public String getLeyendaOrdenSinAsociado() {
        return leyendaOrdenSinAsociado;
    }

    public void setLeyendaOrdenSinAsociado(String leyendaOrdenSinAsociado) {
        this.leyendaOrdenSinAsociado = leyendaOrdenSinAsociado;
    }

    public boolean isMostrarCancelarBusquedaAL() {
        return mostrarCancelarBusquedaAL;
    }

    public void setMostrarCancelarBusquedaAL(boolean mostrarCancelarBusquedaAL) {
        this.mostrarCancelarBusquedaAL = mostrarCancelarBusquedaAL;
    }

    public boolean isMostrarCancelarBusquedaRL() {
        return mostrarCancelarBusquedaRL;
    }

    public void setMostrarCancelarBusquedaRL(boolean mostrarCancelarBusquedaRL) {
        this.mostrarCancelarBusquedaRL = mostrarCancelarBusquedaRL;
    }

    public boolean isMostrarAdjuntarDocumentoRL() {
        return mostrarAdjuntarDocumentoRL;
    }

    public void setMostrarAdjuntarDocumentoRL(boolean mostrarAdjuntarDocumentoRL) {
        this.mostrarAdjuntarDocumentoRL = mostrarAdjuntarDocumentoRL;
    }

    public boolean isMostrarCancelarBusquedaALRL() {
        return mostrarCancelarBusquedaALRL;
    }

    public void setMostrarCancelarBusquedaALRL(boolean mostrarCancelarBusquedaALRL) {
        this.mostrarCancelarBusquedaALRL = mostrarCancelarBusquedaALRL;
    }

    public boolean isMostrarOrdenSinAL() {
        return mostrarOrdenSinAL;
    }

    public void setMostrarOrdenSinAL(boolean mostrarOrdenSinAL) {
        this.mostrarOrdenSinAL = mostrarOrdenSinAL;
    }

    public boolean isMostrarOrdenSinALRL() {
        return mostrarOrdenSinALRL;
    }

    public void setMostrarOrdenSinALRL(boolean mostrarOrdenSinALRL) {
        this.mostrarOrdenSinALRL = mostrarOrdenSinALRL;
    }

    public boolean isMostrarOrdenSinRL() {
        return mostrarOrdenSinRL;
    }

    public void setMostrarOrdenSinRL(boolean mostrarOrdenSinRL) {
        this.mostrarOrdenSinRL = mostrarOrdenSinRL;
    }

}
