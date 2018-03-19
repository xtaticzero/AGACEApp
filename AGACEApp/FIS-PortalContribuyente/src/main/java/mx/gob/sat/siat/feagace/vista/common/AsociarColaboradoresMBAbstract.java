/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosValidacionService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetDocAsociadoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilContribuyente;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilContribuyente;
import mx.gob.sat.siat.feagace.vista.helper.AsociadosMBHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresAttributeHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresDTOHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresFileStreamedHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresListHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AsociarColaboradoresMBAbstract extends BaseManagedBean {

    private static final long serialVersionUID = -7372112773244404067L;

    protected static final String PERFIL = "perfil";
    protected static final String ORDEN = "orden";
    protected static final String OFICIO = "oficio";
    protected static final String COMPULSA = "compulsa";

    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;
    @ManagedProperty(value = "#{asociadosValidacionService}")
    private transient AsociadosValidacionService asociadosValidacionService;

    @ManagedProperty(value = "#{fecetDocAsociadoService}")
    private transient FecetDocAsociadoService fecetDocAsociadoService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private transient AuditoriaService auditoriaService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;
    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    private transient AsociadosMBHelper helper = new AsociadosMBHelper();
    private AsociarColaboradoresAttributeHelper ascColAttribHelper;
    private AsociarColaboradoresDTOHelper ascColDTOHelper;
    private AsociarColaboradoresListHelper ascColListHelper;
    private AsociarColaboradoresFileStreamedHelper ascColFileStreamedHelper;

    @PostConstruct
    protected void init() {
        logger.info("init() : ascColFileStreamedHelper  ascColDTOHelper");
        ascColFileStreamedHelper = new AsociarColaboradoresFileStreamedHelper();
        ascColDTOHelper = new AsociarColaboradoresDTOHelper();
    }

    public AsociadosValidacionService getAsociadosValidacionService() {
        return asociadosValidacionService;
    }

    public void setAsociadosValidacionService(AsociadosValidacionService asociadosValidacionService) {
        this.asociadosValidacionService = asociadosValidacionService;
    }

    public FecetDocAsociadoService getFecetDocAsociadoService() {
        return fecetDocAsociadoService;
    }

    public void setFecetDocAsociadoService(FecetDocAsociadoService fecetDocAsociadoService) {
        this.fecetDocAsociadoService = fecetDocAsociadoService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public void setHelper(AsociadosMBHelper helper) {
        this.helper = helper;
    }

    public AsociadosMBHelper getHelper() {
        return helper;
    }

    public AsociarColaboradoresAttributeHelper getAscColAttribHelper() {
        return ascColAttribHelper;
    }

    public void setAscColAttribHelper(AsociarColaboradoresAttributeHelper ascColAttribHelper) {
        this.ascColAttribHelper = ascColAttribHelper;
    }

    public AsociarColaboradoresDTOHelper getAscColDTOHelper() {
        return ascColDTOHelper;
    }

    public void setAscColDTOHelper(AsociarColaboradoresDTOHelper ascColDTOHelper) {
        this.ascColDTOHelper = ascColDTOHelper;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public AsociarColaboradoresListHelper getAscColListHelper() {
        return ascColListHelper;
    }

    public void setAscColListHelper(AsociarColaboradoresListHelper ascColListHelper) {
        this.ascColListHelper = ascColListHelper;
    }

    public AsociarColaboradoresFileStreamedHelper getAscColFileStreamedHelper() {
        return ascColFileStreamedHelper;
    }

    public void setAscColFileStreamedHelper(AsociarColaboradoresFileStreamedHelper ascColFileStreamedHelper) {
        this.ascColFileStreamedHelper = ascColFileStreamedHelper;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        getAscColFileStreamedHelper().setArchivoDescargaPromocion(getDescargaArchivo(getAscColDTOHelper().getPromocionSeleccionada().getRutaArchivo(),
                CargaArchivoUtilContribuyente.obtenerNombreArchivo(getAscColDTOHelper().getPromocionSeleccionada().getRutaArchivo())));
        return getAscColFileStreamedHelper().getArchivoDescargaPromocion();
    }

    public StreamedContent getArchivoOrdenSeleccionDescarga() {
        final String nombre
                = CargaArchivoUtilContribuyente.getNombreArchivoOrdenPdf(getAscColDTOHelper().getOrdenSeleccionDescarga());
        final String path
                = RutaArchivosUtilContribuyente.armarRutaDocOrden(getAscColDTOHelper().getOrdenSeleccionDescarga()) + nombre;
        getAscColFileStreamedHelper().setArchivoOrdenSeleccionDescarga(getDescargaArchivo(path, nombre));

        return getAscColFileStreamedHelper().getArchivoOrdenSeleccionDescarga();
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuse() {
        getAscColFileStreamedHelper().setArchivoSelecciondaDescargaAcuse(getDescargaArchivo(getAscColDTOHelper().getPromocionSeleccionada().getRutaAcuse(),
                CargaArchivoUtilContribuyente.obtenerNombreArchivo(getAscColDTOHelper().getPromocionSeleccionada().getRutaAcuse())));
        return getAscColFileStreamedHelper().getArchivoSelecciondaDescargaAcuse();
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        getAscColFileStreamedHelper().setArchivoDescargaPruebasAlegatos(getDescargaArchivo(getAscColDTOHelper().getPruebaAlegatoSeleccionada().getRutaArchivo(),
                getAscColDTOHelper().getPruebaAlegatoSeleccionada().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaPruebasAlegatos();
    }

    public StreamedContent getArchivoDescargaOficio() {
        getAscColFileStreamedHelper().setArchivoDescargaOficio(getDescargaArchivo(getAscColDTOHelper().getOficioSeleccionado().getRutaArchivo(),
                getAscColDTOHelper().getOficioSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaOficio();
    }

    public StreamedContent getArchivoDescargaOficioAcuse() {
        try {
            if (ascColFileStreamedHelper != null) {
                if (ascColDTOHelper != null) {
                    logger.info("ascColDTOHelper : no es null");
                    if (ascColDTOHelper.getOficioSeleccionado() != null) {
                        logger.info("getAscColDTOHelper().getOficioSeleccionado() : ");
                        if (ascColDTOHelper.getOficioSeleccionado().getRutaAcuseNyv() != null && ascColDTOHelper.getOficioSeleccionado().getNombreAcuseNyv() != null) {
                            logger.info("getAscColDTOHelper().getOficioSeleccionado().getRutaAcuseNyv() : ");
                            logger.info(ascColDTOHelper.getOficioSeleccionado().getRutaAcuseNyv());                                                        
                            return getDescargaArchivo(ascColDTOHelper.getOficioSeleccionado().getRutaAcuseNyv(),
                                    ascColDTOHelper.getOficioSeleccionado().getNombreAcuseNyv());
                        }
                        logger.info("ascColDTOHelper.getOficioSeleccionado().getRutaAcuseNyv() : es null");
                    }
                    logger.info("ascColDTOHelper.getOficioSeleccionado() : es null");
                }
                logger.info("ascColDTOHelper : es null");
            }
            logger.info("ascColFileStreamedHelper es null");
            ascColAttribHelper = new AsociarColaboradoresAttributeHelper();            
        } catch (Exception e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontr\u00f3 el documento seleccionado", "");
            getAscColFileStreamedHelper().setArchivoDescargaOficioAcuse(null);
        }

        return null;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuseProrroga() {
        getAscColFileStreamedHelper().setArchivoSelecciondaDescargaAcuseProrroga(getDescargaArchivo(getAscColDTOHelper().getProrrogaOrdenSeleccionada().getRutaAcuse(),
                getAscColDTOHelper().getProrrogaOrdenSeleccionada().getNombreAcuse()));
        return getAscColFileStreamedHelper().getArchivoSelecciondaDescargaAcuseProrroga();
    }

    public StreamedContent getArchivoDescargaDocProrrogaOrdenSeleccionado() {
        getAscColFileStreamedHelper().setArchivoDescargaDocProrrogaOrdenSeleccionado(getDescargaArchivo(getAscColDTOHelper().getDocProrrogaOrdenSeleccionado().getRutaArchivo(),
                getAscColDTOHelper().getDocProrrogaOrdenSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaDocProrrogaOrdenSeleccionado();
    }

    public StreamedContent getArchivoDescargaDocPruebaPericialSeleccionado() {
        getAscColFileStreamedHelper().setArchivoDescargaDocPruebaPericialSeleccionado(getDescargaArchivo(getAscColDTOHelper().getDocPruebaPericialSeleccionado().getRutaArchivo(),
                getAscColDTOHelper().getDocPruebaPericialSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaDocPruebaPericialSeleccionado();
    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        getAscColFileStreamedHelper().setArchivoDescargaDocumentacion(getDescargaArchivo(getAscColDTOHelper().getOfDependienteSeleccionado().getRutaArchivo().concat(getAscColDTOHelper().getOfDependienteSeleccionado().getNombreArchivo()),
                getAscColDTOHelper().getOfDependienteSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaDocumentacion();
    }

    public StreamedContent getArchivoDescargaAnexoOficio() {
        getAscColFileStreamedHelper().setArchivoDescargaAnexoOficio(getDescargaArchivo(getAscColDTOHelper().getAnexoOficioSeleccionado().getRutaArchivo(),
                getAscColDTOHelper().getAnexoOficioSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaAnexoOficio();
    }

    public StreamedContent getArchivoDescargaOficioCompulsa() {
        getAscColFileStreamedHelper().setArchivoDescargaOficioCompulsa(getDescargaArchivo(getAscColDTOHelper().getCompulsaSeleccionada().getOficio().getRutaArchivo(),
                getAscColDTOHelper().getCompulsaSeleccionada().getOficio().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaOficioCompulsa();
    }

    public StreamedContent getArchivoDescargaOfDependiente() {
        getAscColFileStreamedHelper().setArchivoDescargaOfDependiente(getDescargaArchivo(getAscColDTOHelper().getOfDependienteSeleccionado().getRutaArchivo(),
                getAscColDTOHelper().getOfDependienteSeleccionado().getNombreArchivo()));
        return getAscColFileStreamedHelper().getArchivoDescargaOfDependiente();
    }

    public StreamedContent getArchivoDescargaProrrogaResolucionOrden() {
        getAscColFileStreamedHelper().setArchivoDescargaProrrogaResolucionOrden(getDescargaArchivo(getAscColDTOHelper().getProrrogaOrdenSeleccionada().getRutaResolucion(),
                getAscColDTOHelper().getProrrogaOrdenSeleccionada().getNombreResolucion()));
        return getAscColFileStreamedHelper().getArchivoDescargaProrrogaResolucionOrden();
    }

    public StreamedContent getArchivoSelecciondaDescargaAcusePruebaPericial() {
        getAscColFileStreamedHelper().setArchivoSelecciondaDescargaAcusePruebaPericial(getDescargaArchivo(getAscColDTOHelper().getPruebaPericialSeleccionada().getRutaAcuse(),
                getAscColDTOHelper().getPruebaPericialSeleccionada().getNombreAcuse()));
        return getAscColFileStreamedHelper().getArchivoSelecciondaDescargaAcusePruebaPericial();
    }

    public StreamedContent getArchivoDescargaPruebaPericialResolucion() {
        getAscColFileStreamedHelper().setArchivoDescargaPruebaPericialResolucion(getDescargaArchivo(getAscColDTOHelper().getPruebaPericialSeleccionada().getRutaResolucion(),
                getAscColDTOHelper().getPruebaPericialSeleccionada().getNombreResolucion()));
        return getAscColFileStreamedHelper().getArchivoDescargaPruebaPericialResolucion();
    }

    public StreamedContent getArchivoDescargaRL() {

        if (getAscColDTOHelper().getDocumentoSeleccionadoRL().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(getAscColDTOHelper().getDocumentoSeleccionadoRL().getIdTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", getAscColDTOHelper().getDocumentoSeleccionadoRL().getNombreArchivo());
        }

        return getArchivoGenerico(getAscColDTOHelper().getDocumentoSeleccionadoRL().getRutaArchivo(),
                getAscColDTOHelper().getDocumentoSeleccionadoRL().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAL() {

        if (getAscColDTOHelper().getDocumentoSeleccionadoAL().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(getAscColDTOHelper().getDocumentoSeleccionadoAL().getIdTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", getAscColDTOHelper().getDocumentoSeleccionadoAL().getNombreArchivo());
        }

        return getArchivoGenerico(getAscColDTOHelper().getDocumentoSeleccionadoAL().getRutaArchivo(),
                getAscColDTOHelper().getDocumentoSeleccionadoAL().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaALRL() {

        if (getAscColDTOHelper().getDocumentoSeleccionadoALRL().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(getAscColDTOHelper().getDocumentoSeleccionadoALRL().getIdTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", getAscColDTOHelper().getDocumentoSeleccionadoALRL().getNombreArchivo());
        }

        return getArchivoGenerico(getAscColDTOHelper().getDocumentoSeleccionadoALRL().getRutaArchivo(),
                getAscColDTOHelper().getDocumentoSeleccionadoALRL().getNombreArchivo());
    }

    private StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    public void guardarArchivo(FecetDocAsociado documento) {
        InputStream myInputStream = new ByteArrayInputStream(
                getArchivoTempService().consultaArchivoTemp(documento.getIdTemporal(), getRFCSession()));
        documento.setArchivo(myInputStream);
        try {
            CargaArchivoUtilContribuyente.guardarArchivoRL(documento);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        final String nombre = getAscColDTOHelper().getOficioSeleccionadoDescarga().getNombreArchivo();
        final String path = getAscColDTOHelper().getOficioSeleccionadoDescarga().getRutaArchivo();
        getAscColFileStreamedHelper().setArchivoOficioSeleccionDescarga(getDescargaArchivo(path, nombre));

        return getAscColFileStreamedHelper().getArchivoOficioSeleccionDescarga();
    }

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

}
