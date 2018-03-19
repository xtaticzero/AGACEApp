/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocumentacionOrdenCargaDelegateMB extends DocumentacionOrdenMBAbstract {

    private static final long serialVersionUID = 7145174871846551294L;

    public void cargarTablaOf2aCartaInvitacion() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION));
        getLstOficioHelper().setListaOf2aCartaInv(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOf2aCartaInv(cargarTablaOficio(oficio, getStreamedHelper().getOf2aCartaInvitacion(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOf2aCartaInv()));
    }

    public void cargarTablaAnexos2aCartaInvitacion(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexos2aCartaInv(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexos2aCartaInv()));
    }

    public void cargarTablaOf2aCartaInvitacionMasiva() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION_MASIVA));
        getLstOficioHelper().setListaOf2aCartaInvitacionMasiva(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOf2aCartaInvitacionMasiva(cargarTablaOficio(oficio,
                getStreamedHelper().getOf2aCartaInvitacionMasiva(), getDtoHelper().getOrdenSeleccionada(),
                getLstOficioHelper().getListaOf2aCartaInvitacionMasiva()));
    }

    public void cargarTablaAnexos2aCartaInvitacionMasiva(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexos2aCartaInvitacionMasiva(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexos2aCartaInvitacionMasiva()));
    }

    /**
     * ***********************************************************************METODOS
     * COMUNES*****************************************************************************
     */
    public List<FecetOficio> cargarTablaOficio(FecetOficio oficio, UploadedFile file, final AgaceOrden orden,
            List<FecetOficio> listaOficioUnico) {
        if (file != null) {
            try {
                oficio.setFechaCreacion(new Date());
                oficio.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                oficio.setArchivo(file.getInputstream());
                oficio.setIdOrden(orden.getIdOrden());
                oficio.setIdEstatus(Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);
                listaOficioUnico.add(oficio);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaOficioUnico;
    }

    public final List<FecetOficioAnexos> cargarTablaAnexos(final UploadedFile file,
            final List<FecetOficioAnexos> listaAnexos) {
        FecetOficioAnexos anexo = new FecetOficioAnexos();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void cargarTablaOfCompInternacional() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL));
        getLstOficioHelper().setListaOfCompInternacional(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfCompInternacional(cargarTablaOficio(oficio, getStreamedHelper().getOfCompInternacional(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfCompInternacional()));
    }

    public void cargarTablaAnexosCompInternacional(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosCompInternacional(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosCompInternacional()));
    }

    public void cargarTablaOfCambioMetodoUCAMCA() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.CAMBIO_METODO));
        getLstOficioHelper().setListaOfCambioMetodoUCAMCA(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfCambioMetodoUCAMCA(cargarTablaOficio(oficio, getStreamedHelper().getOfCambioMetodoUCAMCA(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfCambioMetodoUCAMCA()));
    }

    public void cargarTablaAnexosCambioMetodoUCAMCA(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosCambioMetodoUCAMCA(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosCambioMetodoUCAMCA()));
    }

    
    public void cargarTablaOfConclusionUCAMCA() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.CONCLUSION));
        getLstOficioHelper().setListaOfConclusionUCAMCA(new ArrayList<FecetOficio>());
        getLstOficioHelper()
                .setListaOfConclusionUCAMCA(cargarTablaOficio(oficio, getStreamedHelper().getOfConclusionUCAMCA(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfConclusionUCAMCA()));
    }

    public void cargarTablaAnexosConclusionUCAMCA(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosConclusionUCAMCA(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosConclusionUCAMCA()));
    }

    public void cargarTablaOfSegundoRequerimiento() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.SEGUNDO_REQUERIMIENTO));
        getLstOficioHelper().setListaOfSegundoRequerimiento(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfSegundoRequerimiento(
                cargarTablaOficio(oficio, getStreamedHelper().getOfSegundoRequerimiento(),
                        getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfSegundoRequerimiento()));
    }

    public void cargarTablaAnexosSegundoRequerimiento(UploadedFile file) {
        getLstOficioAnexoHelper().setListaAnexosSegundoRequerimiento(
                cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosSegundoRequerimiento()));
    }

    public void cargarTablaOfMulta() {
        if (getLstOficioHelper().getListaOfMulta() != null && getLstOficioHelper().getListaOfMulta().isEmpty()) {
            FecetOficio oficio = new FecetOficio();
            oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.MULTA));
            getLstOficioHelper().setListaOfMulta(cargarTablaOficio(oficio, getStreamedHelper().getOfMulta(),
                    getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfMulta()));
        } else {
            FacesUtil.addErrorMessage(null, "Archivo inv\u00E1lido", "Solo se puede adjuntar un archivo de multa");
        }
    }

    public void cargarTablaAnexosMulta(UploadedFile file) {
        getLstOficioAnexoHelper()
                .setListaAnexosMulta(cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosMulta()));
    }

    public void cargarTablaOfBajaPadron() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(
                FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.SUSPENSION_EN_PADRON_IMP_EXP));
        getLstOficioHelper().setListaOfBajaPadron(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfBajaPadron(cargarTablaOficio(oficio, getStreamedHelper().getOfBajaPadron(),
                getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfBajaPadron()));
    }
    
    
    

    /**
     * *******************SOLICITUD CONTRIBUYENTE *************************************
     * *****************************************************************************
     */
    
    public void cargaResolucionSolicitudContribuyente(final FileUploadEvent event) {
        if(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null){
            if (validaArchivoCargaWord(event.getFile())
                    && !validaArchivoResolucionProrrogaOrden(getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteResolucionVO()),
                            event.getFile().getFileName())) {
                cargarTablaResolucionProrrogaOrden(event.getFile());
            }
        }
        else{
            if (validaArchivoCargaWord(event.getFile())
                    && !validaArchivoResolucionPruebasPericiales(getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteResolucionVO()),
                            event.getFile().getFileName())) {
                cargarTablaResolucionPruebaPericial(event.getFile());
            }
        }        
    }
    
    public void cargarTablaResolucionProrrogaOrden(UploadedFile file) { 
        getLstHelper().setListaSolicitudContribuyenteResolucionVO(
                cargarTablaResolucionProrrogaOrden(file, getLstHelper().getListaSolicitudContribuyenteResolucionVO()));
    }
    
    public void cargarTablaResolucionPruebaPericial(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteResolucionVO() == null){
            getLstHelper().setListaSolicitudContribuyenteResolucionVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteResolucionVO(
                cargarTablaResolucionPruebasPericiales(file, getLstHelper().getListaSolicitudContribuyenteResolucionVO()));
    }
    
    public final List<SolicitudContribuyenteAnexoVO> cargarTablaResolucionProrrogaOrden(final UploadedFile file,
            final List<SolicitudContribuyenteAnexoVO> listaAnexos) {
        SolicitudContribuyenteAnexoVO anexo = new SolicitudContribuyenteAnexoVO();
        final Date fecha = new Date();       

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setFecetAnexosProrrogaOrden(new FecetAnexosProrrogaOrden());
                anexo.getFecetAnexosProrrogaOrden().setFechaCreacion(fecha);
                anexo.getFecetAnexosProrrogaOrden().setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.getFecetAnexosProrrogaOrden().setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }
    
    public final List<SolicitudContribuyenteAnexoVO> cargarTablaResolucionPruebasPericiales(final UploadedFile file,
            final List<SolicitudContribuyenteAnexoVO> listaAnexos) {
        SolicitudContribuyenteAnexoVO anexo = new SolicitudContribuyenteAnexoVO();
        final Date fecha = new Date();       

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setFecetAnexoPruebasPericiales(new FecetAnexoPruebasPericiales());
                anexo.getFecetAnexoPruebasPericiales().setFechaCreacion(fecha);
                anexo.getFecetAnexoPruebasPericiales().setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.getFecetAnexoPruebasPericiales().setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }
    
    public void cargarTablaAnexosProrrogaOrden(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteAnexoVO() == null){
            getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(
                cargarTablaAnexoProrrogaOrden(file, getLstHelper().getListaSolicitudContribuyenteAnexoVO()));
    }
    
    public void cargarTablaAnexoPruebaPericial(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteAnexoVO() == null){
            getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(
                cargarTablaAnexoPruebasPericiales(file, getLstHelper().getListaSolicitudContribuyenteAnexoVO()));
    }
    
    public final List<SolicitudContribuyenteAnexoVO> cargarTablaAnexoPruebasPericiales(final UploadedFile file,
            final List<SolicitudContribuyenteAnexoVO> listaAnexos) {
        SolicitudContribuyenteAnexoVO anexo = new SolicitudContribuyenteAnexoVO();
        final Date fecha = new Date();       

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setFecetAnexoPruebasPericiales(new FecetAnexoPruebasPericiales());
                anexo.getFecetAnexoPruebasPericiales().setFechaCreacion(fecha);
                anexo.getFecetAnexoPruebasPericiales().setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.getFecetAnexoPruebasPericiales().setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }
    
    public final List<SolicitudContribuyenteAnexoVO> cargarTablaAnexoProrrogaOrden(final UploadedFile file,
            final List<SolicitudContribuyenteAnexoVO> listaAnexos) {
        SolicitudContribuyenteAnexoVO anexo = new SolicitudContribuyenteAnexoVO();
        final Date fecha = new Date();       

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setFecetAnexosProrrogaOrden(new FecetAnexosProrrogaOrden());
                anexo.getFecetAnexosProrrogaOrden().setFechaCreacion(fecha);
                anexo.getFecetAnexosProrrogaOrden().setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.getFecetAnexosProrrogaOrden().setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }
    
    public void cargaAnexosSolicitudContribuyente(final FileUploadEvent event) {
        if(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null){
            if (!validaArchivoDuplicadoAnexosProrrogaOrden(getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteAnexoVO()),
                            event.getFile().getFileName())) {
                cargarTablaAnexosProrrogaOrden(event.getFile());
            }
        }
        else{
            if (!validaArchivoDuplicadoAnexosPruebasPericiales(getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteAnexoVO()),
                            event.getFile().getFileName())) {
                cargarTablaAnexoPruebaPericial(event.getFile());
            }
        }
    }
    
    public void cargaResolucionRechazoSolicitudContribuyente(final FileUploadEvent event) {
        if(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null){
            if (validaArchivoCargaWord(event.getFile())
                    && !validaArchivoResolucionProrrogaOrden(getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()),
                            event.getFile().getFileName())) {
                cargarTablaResolucionRechazoProrrogaOrden(event.getFile());
            }
        }
        else{
            if (validaArchivoCargaWord(event.getFile())
                    && !validaArchivoResolucionPruebasPericiales(getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()),
                            event.getFile().getFileName())) {
                cargarTablaResolucionRechazoPruebaPericial(event.getFile());
            }
        }        
    }
    
    public void cargarTablaResolucionRechazoProrrogaOrden(UploadedFile file) { 
        getLstHelper().setListaSolicitudContribuyenteResolucionRechazoVO(
                cargarTablaResolucionProrrogaOrden(file, getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()));
    }
    
    public void cargarTablaResolucionRechazoPruebaPericial(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO() == null){
            getLstHelper().setListaSolicitudContribuyenteResolucionRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteResolucionRechazoVO(
                cargarTablaResolucionPruebasPericiales(file, getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()));
    }
    
    public void cargaAnexosRechazoSolicitudContribuyente(final FileUploadEvent event) {
        if(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null){
            if (!validaArchivoDuplicadoAnexosProrrogaOrden(getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()),
                            event.getFile().getFileName())) {
                cargarTablaAnexosRechazoProrrogaOrden(event.getFile());
            }
        }
        else{
            if (!validaArchivoDuplicadoAnexosPruebasPericiales(getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()),
                            event.getFile().getFileName())) {
                cargarTablaAnexoRechazoPruebaPericial(event.getFile());
            }
        }
    }
    
    public void cargarTablaAnexosRechazoProrrogaOrden(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO() == null){
            getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(
                cargarTablaAnexoProrrogaOrden(file, getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()));
    }
    
    public void cargarTablaAnexoRechazoPruebaPericial(UploadedFile file) {
        if(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO() == null){
            getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        }
        getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(
                cargarTablaAnexoPruebasPericiales(file, getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()));
    }
    
    public void cargarTablaAnexosMultaOrden(UploadedFile file) {
        getLstOficioAnexoHelper()
                .setListaAnexosMultaOrden(cargarTablaAnexos(file, getLstOficioAnexoHelper().getListaAnexosMultaOrden()));
    }
}

    
