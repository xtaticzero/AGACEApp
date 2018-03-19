/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.util.ArrayList;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DoctoOrdenLimpiarDelegateMB extends DoctoOrdenEliminarDelegateSubMB {

    private static final long serialVersionUID = -5874925743626637790L;

    public void limpiaFormulario() {
        getAttributeHelper().setRecargarInformacion(true);
    }

    public void limpiar2aCartaInvitacion() {
        getLstOficioHelper().setListaOf2aCartaInv(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacion2aCartaInvitacion() {
        limpiar2aCartaInvitacion();
        getLstOficioAnexoHelper().setListaAnexos2aCartaInv(new ArrayList<FecetOficioAnexos>());
    }

    public void limpierDocumenoCartaInvitacion() {
        getLstOficioAnexoHelper().getListaAnexos2aCartaInv().remove(this.getDtoHelper().getAnexoSeleccionado());
    }

    public void limpiar2aCartaInvitacionMasiva() {
        getLstOficioHelper().setListaOf2aCartaInvitacionMasiva(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacion2aCartaInvitacionMasiva() {
        limpiar2aCartaInvitacionMasiva();
        getLstOficioAnexoHelper().setListaAnexos2aCartaInvitacionMasiva(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarConclusionUCAMCA() {
        getLstOficioHelper().setListaOfConclusionUCAMCA(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionConclusionUCAMCA() {
        limpiarConclusionUCAMCA();
        getLstOficioAnexoHelper().setListaAnexosConclusionUCAMCA(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarSegundoRequerimiento() {
        getLstOficioHelper().setListaOfSegundoRequerimiento(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionSegundoRequerimiento() {
        limpiarSegundoRequerimiento();
        getLstOficioAnexoHelper().setListaAnexosSegundoRequerimiento(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarMulta() {
        getLstOficioHelper().setListaOfMulta(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionMulta() {
        limpiarMulta();
        getLstOficioAnexoHelper().setListaAnexosMulta(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarBajaPadron() {
        getLstOficioHelper().setListaOfBajaPadron(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionBajaPadron() {
        limpiarBajaPadron();
        getLstOficioAnexoHelper().setListaAnexosBajaPadron(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarResolucionDefinitiva() {
        getLstOficioHelper().setListaOfResolucionDefinitiva(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionResolucionDefinitiva() {
        limpiarResolucionDefinitiva();
        getLstOficioAnexoHelper().setListaAnexosResolucionDefinitiva(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarPruebasPericiales() {
        getLstOficioHelper().setListaOfPruebasPericiales(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionPruebasPericiales() {
        limpiarPruebasPericiales();
        getLstOficioAnexoHelper().setListaAnexosPruebasPericiales(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarAvisoContribuyente() {
        getLstOficioHelper().setListaOfAvisoContribuyente(new ArrayList<FecetOficio>());
    }

    

}
