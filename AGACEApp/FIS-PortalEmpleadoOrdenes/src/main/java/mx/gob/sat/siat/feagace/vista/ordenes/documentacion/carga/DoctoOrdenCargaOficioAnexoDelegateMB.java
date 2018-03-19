/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DoctoOrdenCargaOficioAnexoDelegateMB extends DoctoOrdenCargaOficioAnexoDelegateSubMB {

    private static final long serialVersionUID = 8713089048469291342L;

    /**
     * *******************************************************************2a
     * UNICA CARTA
     * INVITACION*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficio2aCartaInvitacion(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOf2aCartaInvitacion(event.getFile());
            cargarTablaOf2aCartaInvitacion();
        }
    }

    /**
     * *******************************************************************2a
     * CARTA INVITACION
     * MASIVA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficio2aCartaInvitacionMasiva(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOf2aCartaInvitacionMasiva(event.getFile());
            cargarTablaOf2aCartaInvitacionMasiva();
        }
    }

    /**
     * ***********************************************************************COMPULSA
     * INTERNACIONAL*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioCompInternacional(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfCompInternacional(event.getFile());
            cargarTablaOfCompInternacional();
        }
    }

    /**
     * ***********************************************************************CAMBIO
     * METODO UCA
     * MCA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioCambioMetodoUCAMCA(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfCambioMetodoUCAMCA(event.getFile());
            cargarTablaOfCambioMetodoUCAMCA();
        }
    }

    /**
     * ***********************************************************************CONCLUSION
     * UCA
     * MCA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioConclusionUCAMCA(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfConclusionUCAMCA(event.getFile());
            cargarTablaOfConclusionUCAMCA();
        }
    }

    /**
     * ***********************************************************************SEGUNDO
     * REQUERIMIENTO*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioSegundoRequerimiento(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfSegundoRequerimiento(event.getFile());
            cargarTablaOfSegundoRequerimiento();
        }
    }

    /**
     * ***********************************************************************MULTA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioMulta(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfMulta(event.getFile());
            cargarTablaOfMulta();
        }
    }
    
    public void cargaOficioMultaOrden(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfMulta(event.getFile());
            cargarTablaOfMultaOrden();
        }
    }

    /**
     * ***********************************************************************BAJA
     * PADRON*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioBajaPadron(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfBajaPadron(event.getFile());
            cargarTablaOfBajaPadron();
        }
    }

    /**
     * ***********************************************************************RESOLUCION
     * DEFINITIVA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioResolucionDefinitiva(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfResolucionDefinitiva(event.getFile());
            cargarTablaOfResolucionDefinitiva();
        }
    }

    /**
     * ***********************************************************************PRUEBAS
     * PERICIALES*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioPruebasPericiales(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfPruebasPericiales(event.getFile());
            cargarTablaOfPruebasPericiales();
        }
    }

    /**
     * ***********************************************************************AVISO
     * CONTRIBUYENTE*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioAvisoContribuyente(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfAvisoContribuyente(event.getFile());
            cargarTablaOfAvisoContribuyente();
        }
    }
    
    public void cargaOficioAvisoContribuyenteOrden(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfAvisoContribuyente(event.getFile());
            cargarTablaOfAvisoContribuyenteOrden();
        }
    }

    /**
     * ***********************************************************************PRUEBAS
     * DESAHOGO*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioPruebasDesahogo(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfPruebasDesahogo(event.getFile());
            cargarTablaOfPruebasDesahogo();
        }
    }

    /**
     * ***********************************************************************REQUERIMIENTO
     * POR
     * REINCIDENCIA*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioRequerimientoReincidencia(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfRequerimientoReincidencia(event.getFile());
            cargarTablaOfRequerimientoReincidencia();
        }
    }

    /**
     * ***********************************************************************CON
     * OBSERVACIONES*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioConObservaciones(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfConObservaciones(event.getFile());
            cargarTablaOfConObservaciones();
        }
    }

    /**
     * ***********************************************************************CANCELACION
     * ORDEN*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioCancelacionOrden(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfCancelacionOrden(event.getFile());
            cargarTablaOfCancelacionOrden();
        }
    }

    /**
     * ***********************************************************************SIN
     * OBSERVACIONES*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioSinObservaciones(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfSinObservaciones(event.getFile());
            cargarTablaOfSinObservaciones();
        }
    }

    /**
     * ***********************************************************************CONCLUSION
     * REVISION*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioConclusionRevision(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfConclusionRevision(event.getFile());
            cargarTablaOfConclusionRevision();
        }
    }
    
    /**
     * ***********************************************************************CONCLUSION
     * REVISION*****************************************************************************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaOficioMedidasApremio(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfMedidasApremio(event.getFile());
            cargarTablaOfMedidasApremio(getAttributeHelper().getTipoMedidaApremio());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaAnexos2aCartaInvitacion(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexos2aCartaInv(), event.getFile().getFileName())) {
            cargarTablaAnexos2aCartaInvitacion(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaAnexos2aCartaInvitacionMasiva(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexos2aCartaInvitacionMasiva(), event.getFile().getFileName())) {
            cargarTablaAnexos2aCartaInvitacionMasiva(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaAnexosCompInternacional(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosCompInternacional(), event.getFile().getFileName())) {
            cargarTablaAnexosCompInternacional(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaAnexosConclusionUCAMCA(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosConclusionUCAMCA(), event.getFile().getFileName())) {
            cargarTablaAnexosConclusionUCAMCA(event.getFile());
        }
    }    

}
