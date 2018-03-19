package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class DoctoOrdenCargaOficioAnexoDelegateSubMB extends DocumentacionOrdenCargaDelegateSubMB {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosCambioMetodoUCAMCA(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosCambioMetodoUCAMCA(), event.getFile().getFileName())) {
            cargarTablaAnexosCambioMetodoUCAMCA(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosProrrogaOrden(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosProrrogaOrden(getLstHelper().getListaAnexosProrrogaOrden(), event.getFile().getFileName())) {
            cargarTablaAnexosProrrogaOrden(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosSegundoRequerimiento(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosSegundoRequerimiento(), event.getFile().getFileName())) {
            cargarTablaAnexosSegundoRequerimiento(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosMulta(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosMulta(), event.getFile().getFileName())) {
            cargarTablaAnexosMulta(event.getFile());
        }
    }

    public void cargaAnexosMultaOrden(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosMultaOrden(), event.getFile().getFileName())) {
            cargarTablaAnexosMultaOrden(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosBajaPadron(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosBajaPadron(), event.getFile().getFileName())) {
            cargarTablaAnexosBajaPadron(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosResolucionDefinitiva(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosResolucionDefinitiva(), event.getFile().getFileName())) {
            cargarTablaAnexosResolucionDefinitiva(event.getFile());
        }
    }

    public void cargaAnexosSinObservaciones(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosSinObservaciones(), event.getFile().getFileName())) {
            cargarTablaAnexosSinObservaciones(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosConObservaciones(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosConObservaciones(), event.getFile().getFileName())) {
            cargarTablaAnexosConObservaciones(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosRequerimientoReincidencia(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosRequerimientoReincidencia(), event.getFile().getFileName())) {
            cargarTablaAnexosRequerimientoReincidencia(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosCancelacionOrden(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosCancelacionOrden(), event.getFile().getFileName())) {
            cargarTablaAnexosCancelacionOrden(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosConclusionRevision(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosConclusionRevision(), event.getFile().getFileName())) {
            cargarTablaAnexosConclusionRevision(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosPruebasDesahogo(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosPruebasDesahogo(), event.getFile().getFileName())) {
            cargarTablaAnexosPruebasDesahogo(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosAvisoContribuyente(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente(), event.getFile().getFileName())) {
            cargarTablaAnexosAvisoContribuyente(event.getFile());
        }
    }

    public void cargaAnexosAvisoContribuyenteOrden(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyenteOrden(), event.getFile().getFileName())) {
            cargarTablaAnexosAvisoContribuyenteOrden(event.getFile());
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosPruebasPericiales(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosPruebasPericiales(), event.getFile().getFileName())) {
            cargarTablaAnexosPruebasPericiales(event.getFile());
        }
    }

    public void cargaAnexosMedidasDeApremio(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosMedidasApremio(), event.getFile().getFileName())) {
            cargarTablaAnexosMedidasApremio(event.getFile());
        }
    }

    /**
     * ***********************************************************************
     * SIN
     * OBSERVACIONES************************************************************
     * *****************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaOficioAvisoCircunstancial(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getStreamedHelper().setOfAvisoCircunstancial(event.getFile());
            cargarTablaOfAvisoCircunstancial();
        }
    }

    public void cargaAnexosAvisoCircunstancial(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getLstOficioAnexoHelper().getListaAnexosAvisoCircunstancial(), event.getFile().getFileName())) {
            cargarTablaAnexosAvisoCircunstancial(event.getFile());
        }
    }

    public void fileUploadPapelesTrabajo(FileUploadEvent event) {

        if (validaArchivoCargaWordExcelPdfIdMessage(event.getFile(), "msgPapelesTrabajo")) {
            UploadedFile archivoPapelTrabajo = event.getFile();
            cargarTablaPapelTrabajo(archivoPapelTrabajo, Constantes.PAPELTRABAJOORDEN, "msgPapelesTrabajo");
        }

    }

    public void fileUploadPapelesTrabajoOficio(FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdfIdMessage(event.getFile(), "msgPapelesTrabajoOficio")) {
            UploadedFile archivoPapelTrabajo = event.getFile();
            cargarTablaPapelTrabajo(archivoPapelTrabajo, Constantes.PAPELTRABAJOOFICIO, "msgPapelesTrabajoOficio");
        }
    }

}
