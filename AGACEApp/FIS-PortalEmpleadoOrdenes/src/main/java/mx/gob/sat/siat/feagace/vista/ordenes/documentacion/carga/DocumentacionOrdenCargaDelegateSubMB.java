package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;

public class DocumentacionOrdenCargaDelegateSubMB extends DocumentacionOrdenCargaDelegateMB {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void cargarTablaAnexosBajaPadron(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosBajaPadron(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosBajaPadron()));
    }

    public void cargarTablaOfResolucionDefinitiva() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA));
        getLstOficioHelper().setListaOfResolucionDefinitiva(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfResolucionDefinitiva(
                cargarTablaOficio(oficio, getStreamedHelper().getOfResolucionDefinitiva(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfResolucionDefinitiva()));
    }

    public void cargarTablaAnexosResolucionDefinitiva(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosResolucionDefinitiva(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosResolucionDefinitiva()));
    }

    public void cargarTablaOfPruebasPericiales() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO));
        getLstOficioHelper().setListaOfPruebasPericiales(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfPruebasPericiales(cargarTablaOficio(oficio, getStreamedHelper().getOfPruebasPericiales(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfPruebasPericiales()));
    }

    public void cargarTablaAnexosPruebasPericiales(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosPruebasPericiales(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosPruebasPericiales()));
    }

    public void cargarTablaOfAvisoContribuyente() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE));
        getLstOficioHelper()
                .setListaOfAvisoContribuyente(cargarTablaOficio(oficio, getStreamedHelper().getOfAvisoContribuyente(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfAvisoContribuyente()));
    }
    
    public void cargarTablaOfMultaOrden() {
        if (getLstOficioHelper().getListaOfMultaOrden() != null  && getLstOficioHelper().getListaOfMultaOrden().isEmpty()) {
            FecetOficio oficio = new FecetOficio();
            oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.MULTA));
            getLstOficioHelper().setListaOfMultaOrden(cargarTablaOficio(oficio, getStreamedHelper().getOfMulta(),
                    getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfMultaOrden()));
        } 
        else{
            addErrorMessage(null, "Archivo inv\u00E1lido", "Solo se puede adjuntar un archivo de multa");
        }            
    }
    
    
    public void cargarTablaOfAvisoContribuyenteOrden() {
        if (getLstOficioHelper().getListaOfAvisoContribuyenteOrden() != null  && getLstOficioHelper().getListaOfAvisoContribuyenteOrden().isEmpty()) {
            FecetOficio oficio = new FecetOficio();
            oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE));
            getLstOficioHelper() 
                .setListaOfAvisoContribuyenteOrden(cargarTablaOficio(oficio, getStreamedHelper().getOfAvisoContribuyente(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfAvisoContribuyenteOrden()));
        }else{
            addErrorMessage(null, "Archivo inv\u00E1lido", "Solo se puede adjuntar un archivo de aviaso de contribuyente");
        }
    }

    public void cargarTablaAnexosAvisoContribuyente(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyente(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente()));
    }
    
    public void cargarTablaAnexosAvisoContribuyenteOrden(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyenteOrden(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosAvisoContribuyenteOrden()));
    }

    public void cargarTablaOfPruebasDesahogo() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.PRUEBAS_DESAHOGO));
        getLstOficioHelper().setListaOfPruebasDesahogo(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfPruebasDesahogo(cargarTablaOficio(oficio, getStreamedHelper().getOfPruebasDesahogo(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfPruebasDesahogo()));
    }

    public void cargarTablaAnexosPruebasDesahogo(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosPruebasDesahogo(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosPruebasDesahogo()));
    }

    public void cargarTablaOfConclusionRevision() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.CONCLUSION_REVISION_ESCRITOS_IMP));
        getLstOficioHelper().setListaOfConclusionRevision(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfConclusionRevision(cargarTablaOficio(oficio, getStreamedHelper().getOfConclusionRevision(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfConclusionRevision()));
    }
    
    public void cargarTablaOfMedidasApremio(int idTipoOficio) {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficioMedidasApremio(new BigDecimal(idTipoOficio)));
        getLstOficioHelper().setListaOfMedidasApremio(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfMedidasApremio(cargarTablaOficio(oficio, getStreamedHelper().getOfMedidasApremio(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfMedidasApremio()));
    }
    
    public void cargarTablaAnexosMedidasApremio(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosMedidasApremio(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosMedidasApremio()));
    }

    public void cargarTablaAnexosConclusionRevision(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosConclusionRevision(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosConclusionRevision()));
    }

    public void cargarTablaOfCancelacionOrden() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.CANCELACION_ORDEN));
        getLstOficioHelper().setListaOfCancelacionOrden(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfCancelacionOrden(cargarTablaOficio(oficio, getStreamedHelper().getOfCancelacionOrden(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfCancelacionOrden()));
    }

    public void cargarTablaAnexosCancelacionOrden(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosCancelacionOrden(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosCancelacionOrden()));
    }

    public void cargarTablaOfSinObservaciones() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.SIN_OBSERVACIONES));
        getLstOficioHelper().setListaOfSinObservaciones(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfSinObservaciones(cargarTablaOficio(oficio, getStreamedHelper().getOfSinObservaciones(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfSinObservaciones()));
    }

    public void cargarTablaAnexosSinObservaciones(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosSinObservaciones(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosSinObservaciones()));
    }

    public void cargarTablaOfConObservaciones() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO));
        getLstOficioHelper().setListaOfConObservaciones(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfConObservaciones(cargarTablaOficio(oficio, getStreamedHelper().getOfConObservaciones(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfConObservaciones()));
    }

    public void cargarTablaAnexosConObservaciones(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosConObservaciones(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosConObservaciones()));
    }

    public void cargarTablaOfRequerimientoReincidencia() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.REQUERIMIENTO_REINCIDENCIA));
        getLstOficioHelper().setListaOfRequerimientoReincidencia(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfRequerimientoReincidencia(cargarTablaOficio(oficio,
                getStreamedHelper().getOfRequerimientoReincidencia(), getDtoHelper().getOrdenSeleccionada(),
                getLstOficioHelper().getListaOfRequerimientoReincidencia()));
    }

    public void cargarTablaAnexosRequerimientoReincidencia(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosRequerimientoReincidencia(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosRequerimientoReincidencia()));
    }

    public void cargarTablaOfAvisoCircunstancial() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL));
        getLstOficioHelper().setListaOfAvisoCircunstancial(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfAvisoCircunstancial(cargarTablaOficio(oficio, getStreamedHelper().getOfAvisoCircunstancial(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfAvisoCircunstancial()));
    }

    public void cargarTablaAnexosAvisoCircunstancial(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosAvisoCircunstancial(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosAvisoCircunstancial()));
    }
    
    public void cargarTablaPapelTrabajo(UploadedFile archivoPapelTrabajo,BigDecimal tipoPapelTrabajo,String idMensaje) {
        PapelesTrabajo papel = new PapelesTrabajo();
        llenarPapelTrabajo(papel,archivoPapelTrabajo);
        List<DocumentoOrdenModel> listaPapelTrabajo = new ArrayList<DocumentoOrdenModel>();
        int numeroDocumentoPapelTrabajo=0;
        if(Constantes.PAPELTRABAJOORDEN.equals(tipoPapelTrabajo)){
            listaPapelTrabajo = getLstHelper().getListaPapelesTrabajo();
            numeroDocumentoPapelTrabajo = getAttributeHelper().getNumeroDocumentoPapelTrabajo();
        }
        if(Constantes.PAPELTRABAJOOFICIO.equals(tipoPapelTrabajo)){
            listaPapelTrabajo = getLstHelper().getListaPapelesTrabajoOficio();
            numeroDocumentoPapelTrabajo = getAttributeHelper().getNumeroDocumentoPapelTrabajoOficio();
            papel.setIdTipoOficio(getAttributeHelper().getIdTipoOficio());
        }
        
        if(!duplicidadPapelTrabajo(papel,listaPapelTrabajo)){            
                FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp(); 
                fecetArchivoTemp.setArchivo(papel.getNombreArchivo());
                fecetArchivoTemp.setSessionUUID(getRFCSession());        
                try {
                    DocumentoOrdenModel papelTrabajo = new DocumentoOrdenModel();
                    papelTrabajo.setIsEliminar(true);
                    fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivoPapelTrabajo.getInputstream()));
                    papelTrabajo.setIdDocumentoTemporal(
                    getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
                    papelTrabajo.setPapelesTrabajo(papel);
                    
                    if(Constantes.PAPELTRABAJOORDEN.equals(tipoPapelTrabajo)){
                        getLstHelper().setListaPapelesTrabajo( ordenarListaPapelTrabajo(papelTrabajo,listaPapelTrabajo));
                        getAttributeHelper().setNumeroDocumentoPapelTrabajo(numeroDocumentoPapelTrabajo+1);
                    }
                    if(Constantes.PAPELTRABAJOOFICIO.equals(tipoPapelTrabajo)){
                        getLstHelper().setListaPapelesTrabajoOficio(ordenarListaPapelTrabajo(papelTrabajo,listaPapelTrabajo));
                        getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(numeroDocumentoPapelTrabajo+1);
                    }
                    
                    addMessage(idMensaje, Constantes.ADJUNTAR_ARCHIVO, papel.getNombreArchivo());
                    
                    RequestContext context = RequestContext.getCurrentInstance();
                    StringBuilder update = new StringBuilder("formDocumentacionOrden:panelTabsDocumentos:");
                    update.append( getAttributeHelper().getFieldsetActivoPapelTrabajo());            
                    context.update(update.toString());
                    
                } catch (IOException e) {
                    logger.error(e.getMessage());            
                }
        }else{
            addErrorMessage(idMensaje,"El archivo ya fue cargado","");
        }

    }
    
    private void llenarPapelTrabajo(PapelesTrabajo papel, UploadedFile archivoPapelTrabajo){
        papel.setFechaCreacion(new Date());
        papel.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(archivoPapelTrabajo.getFileName())));
        papel.setBlnActivo(BigDecimal.ONE);
        papel.setIdPropuesta(getDtoHelper().getOrdenSeleccionada().getIdPropuesta());
        papel.setIdOrden(getDtoHelper().getOrdenSeleccionada().getIdOrden());
    }
    
    private List<DocumentoOrdenModel> ordenarListaPapelTrabajo(DocumentoOrdenModel papelTrabajo,List<DocumentoOrdenModel> listaPapelTrabajo){
        List<DocumentoOrdenModel> listaPapelTrabajoAgregado= new ArrayList<DocumentoOrdenModel>();
        listaPapelTrabajoAgregado.add(papelTrabajo);
        listaPapelTrabajoAgregado.addAll(listaPapelTrabajo);
        return listaPapelTrabajoAgregado;
    }

}
