/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.carga.helper;

import java.io.Serializable;

/**
 *
 * @author Ing. Juan Adrian Cuervo Zarate
 */

public class CargaValidaBooleanHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -492141917796688464L;

    private boolean procesaInformacionHabilitado;
    private boolean exportarExitoHabilitado;
    private boolean exportarErrorHabilitado;
    private boolean botonRegresarVisible;
    private boolean panelConfirmacionVisible;
    private boolean panelVisibleCorrectos;
    private boolean panelVisibleErroneos;
    private boolean accordionPanelVisible;
    private boolean procesadoExito;
    private boolean validaInformacionHabilitado;
    private boolean cancelarHabilitado;
    private boolean fileHabilitado;
    private boolean eventoActivo;
  

    
    
    public boolean isProcesaInformacionHabilitado() {
        return procesaInformacionHabilitado;
    }
    public void setProcesaInformacionHabilitado(boolean procesaInformacionHabilitado) {
        this.procesaInformacionHabilitado = procesaInformacionHabilitado;
    }
    public boolean isExportarExitoHabilitado() {
        return exportarExitoHabilitado;
    }
    public void setExportarExitoHabilitado(boolean exportarExitoHabilitado) {
        this.exportarExitoHabilitado = exportarExitoHabilitado;
    }
    public boolean isExportarErrorHabilitado() {
        return exportarErrorHabilitado;
    }
    public void setExportarErrorHabilitado(boolean exportarErrorHabilitado) {
        this.exportarErrorHabilitado = exportarErrorHabilitado;
    }
    public boolean isBotonRegresarVisible() {
        return botonRegresarVisible;
    }
    public void setBotonRegresarVisible(boolean botonRegresarVisible) {
        this.botonRegresarVisible = botonRegresarVisible;
    }
    public boolean isPanelConfirmacionVisible() {
        return panelConfirmacionVisible;
    }
    public void setPanelConfirmacionVisible(boolean panelConfirmacionVisible) {
        this.panelConfirmacionVisible = panelConfirmacionVisible;
    }
    public boolean isPanelVisibleCorrectos() {
        return panelVisibleCorrectos;
    }
    public void setPanelVisibleCorrectos(boolean panelVisibleCorrectos) {
        this.panelVisibleCorrectos = panelVisibleCorrectos;
    }
    public boolean isPanelVisibleErroneos() {
        return panelVisibleErroneos;
    }
    public void setPanelVisibleErroneos(boolean panelVisibleErroneos) {
        this.panelVisibleErroneos = panelVisibleErroneos;
    }
    public boolean isAccordionPanelVisible() {
        return accordionPanelVisible;
    }
    public void setAccordionPanelVisible(boolean accordionPanelVisible) {
        this.accordionPanelVisible = accordionPanelVisible;
    }
    public boolean isProcesadoExito() {
        return procesadoExito;
    }
    public void setProcesadoExito(boolean procesadoExito) {
        this.procesadoExito = procesadoExito;
    }
    public boolean isValidaInformacionHabilitado() {
        return validaInformacionHabilitado;
    }
    public void setValidaInformacionHabilitado(boolean validaInformacionHabilitado) {
        this.validaInformacionHabilitado = validaInformacionHabilitado;
    }
    public boolean isCancelarHabilitado() {
        return cancelarHabilitado;
    }
    public void setCancelarHabilitado(boolean cancelarHabilitado) {
        this.cancelarHabilitado = cancelarHabilitado;
    }
    public boolean isFileHabilitado() {
        return fileHabilitado;
    }
    public void setFileHabilitado(boolean fileHabilitado) {
        this.fileHabilitado = fileHabilitado;
    }
    public boolean isEventoActivo() {
        return eventoActivo;
    }
    public void setEventoActivo(boolean eventoActivo) {
        this.eventoActivo = eventoActivo;
    }
    
    
    
    
    
}
