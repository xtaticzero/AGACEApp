/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DoctoOrdenEliminarDelegateMB extends DoctoOrdenCargaOficioAnexoDelegateMB {

    private static final long serialVersionUID = -4118330272924202324L;
    
    private static final String MENSAJE_PAPELES_TRABAJO_EXITO = "Se realiz\u00f3 la eliminaci\u00f3n del documento ";

    public void eliminarAnexoOficio2aCartaInvitacion() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexos2aCartaInv());
    }

    public void eliminarAnexoOficio2daCartaInvitacionMasiva(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarAnexoOficio2daCartaInvitacionMasiva() {
        eliminarAnexoOficio2daCartaInvitacionMasiva(getLstOficioAnexoHelper().getListaAnexos2aCartaInvitacionMasiva());
    }

    public void eliminarAnexoOficio2aCartaInvitacionMasiva() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexos2aCartaInvitacionMasiva());
    }

    public void eliminarAnexoOficioConclusionUCAMCA() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosConclusionUCAMCA());
    }

    public void eliminarAnexoOficioSegundoRequerimiento() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosSegundoRequerimiento());
    }

    public void eliminarAnexoOficioMulta() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosMulta());
    }

    public void eliminarAnexoOficioMultaOrden() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosMultaOrden());
    }
    
    
    public void eliminarAnexoOficioBajaPadron() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosBajaPadron());
    }

    public void eliminarAnexoResolucionDefinitiva() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosResolucionDefinitiva());
    }

    public void eliminarAnexoPruebasPericiales() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosPruebasPericiales());
    }

    public void eliminarAnexoOficioAvisoContribuyente() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente());
    }
    
    public void eliminarAnexoOficioAvisoContribuyenteOrden() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyenteOrden());
    }

    public void eliminarAnexoPruebasDesahogo() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosPruebasDesahogo());
    }

    public void eliminarAnexoConclusionRevision() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosConclusionRevision());
    }

    public void eliminarAnexoCancelacionOrden() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosCancelacionOrden());
    }

    public void eliminarAnexoSinObservaciones() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosSinObservaciones());
    }
    
    public void eliminarAnexoMedidasApremio() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosMedidasApremio());
    }

    public void eliminarAnexoOficioCompInternacional() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosCompInternacional());
    }

    public void eliminarDocumentoIdenticoAnexosLista(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarAnexoOficioCancelacionOrden() {
        eliminarAnexoOficioCancelacionOrden(getLstOficioAnexoHelper().getListaAnexosCancelacionOrden());
    }

    public void eliminarAnexoOficioCancelacionOrden(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarPapelTrabajo(){
        DocumentoOrdenModel papelTrabajo = getDtoHelper().getPapelTrabajoSeleccionado();
        
        if(papelTrabajo.isIsEliminar() && getLstHelper().getListaPapelesTrabajo().contains(papelTrabajo) ){
            getLstHelper().getListaPapelesTrabajo().remove(papelTrabajo);
            FacesUtil.addInfoMessage("msgPapelesTrabajo", MENSAJE_PAPELES_TRABAJO_EXITO, papelTrabajo.getPapelesTrabajo().getNombreArchivo());
            getAttributeHelper().setNumeroDocumentoPapelTrabajo(getAttributeHelper().getNumeroDocumentoPapelTrabajo()-1);
        }
        
        
        if(!papelTrabajo.isIsEliminar() && getLstHelper().getListaPapelesTrabajo().contains(papelTrabajo) ){
            getLstHelper().getListaPapelesTrabajo().remove(papelTrabajo);
            papelTrabajo.getPapelesTrabajo().setBlnActivo(BigDecimal.ZERO);
            papelTrabajo.getPapelesTrabajo().setFechaFin(new Date());
            getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papelTrabajo.getPapelesTrabajo());
            FacesUtil.addInfoMessage("msgPapelesTrabajo", MENSAJE_PAPELES_TRABAJO_EXITO, papelTrabajo.getPapelesTrabajo().getNombreArchivo());           
        }
        
    }
    
    public void eliminarPapelTrabajoOficio(){
        DocumentoOrdenModel papelTrabajo = getDtoHelper().getPapelTrabajoSeleccionado();
        
        if(papelTrabajo.isIsEliminar() && getLstHelper().getListaPapelesTrabajoOficio().contains(papelTrabajo) ){
            getLstHelper().getListaPapelesTrabajoOficio().remove(papelTrabajo);
            FacesUtil.addInfoMessage("msgPapelesTrabajoOficio", MENSAJE_PAPELES_TRABAJO_EXITO, papelTrabajo.getPapelesTrabajo().getNombreArchivo());
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(getAttributeHelper().getNumeroDocumentoPapelTrabajoOficio()-1);
        }
        
       
        if(!papelTrabajo.isIsEliminar() && getLstHelper().getListaPapelesTrabajoOficio().contains(papelTrabajo) ){
            getLstHelper().getListaPapelesTrabajoOficio().remove(papelTrabajo);
            papelTrabajo.getPapelesTrabajo().setBlnActivo(BigDecimal.ZERO);
            papelTrabajo.getPapelesTrabajo().setFechaFin(new Date());
            getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papelTrabajo.getPapelesTrabajo());
            FacesUtil.addInfoMessage("msgPapelesTrabajoOficio", MENSAJE_PAPELES_TRABAJO_EXITO, papelTrabajo.getPapelesTrabajo().getNombreArchivo());
        }
        
        
        RequestContext context = RequestContext.getCurrentInstance();
        StringBuilder update = new StringBuilder("formDocumentacionOrden:panelTabsDocumentos:");
        update.append( getAttributeHelper().getFieldsetActivoPapelTrabajo());            
        context.update(update.toString());
        
    }
    
    

}
