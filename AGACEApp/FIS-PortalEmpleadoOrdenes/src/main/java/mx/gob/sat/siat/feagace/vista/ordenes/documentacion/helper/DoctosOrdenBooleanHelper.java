/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenBooleanHelper extends DoctosOrdenAttributeHelperAbstract {

    private static final long serialVersionUID = -4623905067829977334L;
    
    private boolean mostrarOrdenesSeguimiento = true;
    private boolean mostrarDocumentosSeguimiento = false;
    private boolean prorrogasVisible;
    private boolean solicitudContrVisible;
    private boolean adjuntarArchivo;
    private boolean fieldSetMedidasApremio;
    private boolean mostrarBtnDocsAgenteAduanal;
    
    public boolean isSolicitudContrVisible() {
        return solicitudContrVisible;
    }

    public void setSolicitudContrVisible(boolean solicitudContrVisible) {
        this.solicitudContrVisible = solicitudContrVisible;
    }
    
    public boolean isProrrogasVisible() {
        return prorrogasVisible;
    }

    public void setProrrogasVisible(boolean prorrogasVisible) {
        this.prorrogasVisible = prorrogasVisible;
    }
    
    public void setMostrarOrdenesSeguimiento(final boolean mostrarOrdenesSeguimiento) {
        this.mostrarOrdenesSeguimiento = mostrarOrdenesSeguimiento;
    }

    public boolean isMostrarOrdenesSeguimiento() {
        return mostrarOrdenesSeguimiento;
    }
    
    public void setMostrarDocumentosSeguimiento(final boolean mostrarDocumentosSeguimiento) {
        this.mostrarDocumentosSeguimiento = mostrarDocumentosSeguimiento;
    }

    public boolean isMostrarDocumentosSeguimiento() {
        return mostrarDocumentosSeguimiento;
    }
    
    public boolean isAdjuntarArchivo() {
        return adjuntarArchivo;
    }

    public void setAdjuntarArchivo(boolean adjuntarArchivo) {
        this.adjuntarArchivo = adjuntarArchivo;
    }

    public boolean isFieldSetMedidasApremio() {
        return fieldSetMedidasApremio;
    }

    public void setFieldSetMedidasApremio(boolean fieldSetMedidasApremio) {
        this.fieldSetMedidasApremio = fieldSetMedidasApremio;
    }
    
    public boolean isMostrarBtnDocsAgenteAduanal() {
        return mostrarBtnDocsAgenteAduanal;
    }

    public void setMostrarBtnDocsAgenteAduanal(boolean mostrarBtnDocsAgenteAduanal) {
        this.mostrarBtnDocsAgenteAduanal = mostrarBtnDocsAgenteAduanal;
    }

}
