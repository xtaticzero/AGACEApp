package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.CargaDocumentosAAHelper;

@SessionScoped
@ManagedBean(name = "documentacionAgenteAduanalMB")
public class DocumentacionAgenteAduanalMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;
    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    private AgaceOrden ordenSeleccionado;
    private ColaboradorVO agenteAduanal;
    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaPromocionSeleccionada;
    private transient StreamedContent archivoDescargaAnexoSeleccionada;
    private transient UploadedFile archivoPromocion;
    private transient UploadedFile archivoPruebasAlegatos;
    private List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanal;
    private FecetAlegatoAgenteAduanal alegatoAgenteAduanalSeleccionado;
    private List<FecetPromocionAgenteAduanal> listaPromocionesCargadas;
    private List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanalCargadas;
    private List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal;
    private FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado;

    private boolean visibleBtnEliminarOrden;
    private boolean mostrarPruebasAlegatos;
    private boolean paginaCargada;
    private boolean noVerEditables;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {
        HashMap mapOrden = (HashMap) getSession().getAttribute("mapOrdenAA");
        setNoVerEditables(false);
        if (mapOrden != null && mapOrden.containsKey("noVerEditables")) {
            setNoVerEditables((Boolean) mapOrden.get("noVerEditables"));
        }

        if (!paginaCargada) {
            if (mapOrden != null) {
                setOrdenSeleccionado((AgaceOrden) mapOrden.get("orden"));
                setAgenteAduanal((ColaboradorVO) mapOrden.get("agenteAduanal"));
                cargaHistorialDocumentosAgenteAduanal();
                getSession().removeAttribute("mapOrdenAA");
                paginaCargada = true;
            } else {
                addErrorMessage(null, "Error al obtener archivos de session", "");
            }
        }

    }

    public void cargaHistorialDocumentosAgenteAduanal() {
        setListaPromocionAgenteAduanal(getSeguimientoOrdenesService()
                .getPromocionAgenteAduanalContadorPruebasAlegatos(getOrdenSeleccionado().getIdOrden()));
    }

    public void getPruebasAlegatosPromocion() {
        setListaAlegatoAgenteAduanal(getSeguimientoOrdenesService()
                .getAlegatosAgenteAduanal(getPromocionAgenteAduanalSeleccionado().getIdPromocionAgenteAduanal()));

    }

    public void handleFileUploadPromocion(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        if (getListaPromocionesCargadas() == null) {
            setArchivoPromocion(event.getFile());
            FecetPromocionAgenteAduanal dto = new FecetPromocionAgenteAduanal();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(
                    CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getArchivoPromocion().getFileName())));
            dto.setArchivo(getArchivoPromocion().getInputstream());
            setListaPromocionesCargadas(new ArrayList<FecetPromocionAgenteAduanal>());

            archivoTemp.setSessionUUID(getUserProfile().getRfc());
            archivoTemp.setArchivoByte(getArchivoPromocion().getContents());
            archivoTemp.setFecha(fecha);

            dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

            getListaPromocionesCargadas().add(dto);
            setVisibleBtnEliminarOrden(true);
            addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
        } else {
            addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
        }
        setMostrarPruebasAlegatos(true);

    }

    public void handleFileUploadPruebasAlegatos(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        setArchivoPruebasAlegatos(event.getFile());
        FecetAlegatoAgenteAduanal dto = new FecetAlegatoAgenteAduanal();
        final Date fecha = new Date();
        dto.setFechaCarga(fecha);
        dto.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(
                CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getArchivoPruebasAlegatos().getFileName())));
        dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
        if (CargaDocumentosAAHelper.checarDuplicadoPruebasAlegatoAgenteAduanal(dto.getNombreArchivo(),
                getListaAlegatoAgenteAduanalCargadas())) {
            addErrorMessage(null, "No puede subir el mismo archivo", "");

        } else {
            if (getListaAlegatoAgenteAduanalCargadas() == null || getListaAlegatoAgenteAduanalCargadas().isEmpty()) {
                setListaAlegatoAgenteAduanalCargadas(new ArrayList<FecetAlegatoAgenteAduanal>());
            }
            try {
                dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
                archivoTemp.setSessionUUID(getUserProfile().getRfc());
                archivoTemp.setArchivoByte(getArchivoPruebasAlegatos().getContents());
                archivoTemp.setFecha(fecha);

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getListaAlegatoAgenteAduanalCargadas().add(dto);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                RequestContext.getCurrentInstance().update("formCargaDocAgenteAduanal:flUpPruebas");
            } catch (IOException e) {
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }
    }

    public void limpiarPromocion() {
        if (getListaPromocionesCargadas() != null) {
            getListaPromocionesCargadas().clear();
            setListaPromocionesCargadas(null);
            setListaAlegatoAgenteAduanalCargadas(null);
            setArchivoPromocion(null);
            setMostrarPruebasAlegatos(false);
            setPromocionAgenteAduanalSeleccionado(null);
            setVisibleBtnEliminarOrden(false);
            RequestContext.getCurrentInstance().update("formCargaDocAgenteAduanal:flUpPromo");
        }
    }

    public void descartarPruebaLista() {
        List<FecetAlegatoAgenteAduanal> listaNueva = new ArrayList<FecetAlegatoAgenteAduanal>();
        for (FecetAlegatoAgenteAduanal alegato : getListaAlegatoAgenteAduanalCargadas()) {
            if (!alegato.getNombreArchivo().equals(getAlegatoAgenteAduanalSeleccionado().getNombreArchivo())) {
                listaNueva.add(alegato);
            }
        }
        setListaAlegatoAgenteAduanalCargadas(listaNueva);
    }

    public void guardar() {
        if (!getListaPromocionesCargadas().isEmpty()) {
            FecetPromocionAgenteAduanal promocion = (FecetPromocionAgenteAduanal) getListaPromocionesCargadas().get(0);
            promocion.setRutaArchivo(armaRutaPromocion(promocion));
            promocion.setAsociado(getAgenteAduanal().getAsociado());
            promocion.setIdOrden(getOrdenSeleccionado().getIdOrden());
            promocion.setIdAsociadoCarga(getAgenteAduanal().getAsociado().getIdAsociado());
            promocion.setIdEmpleado((getOrdenSeleccionado().getIdAuditor()));
            FecetPromocionAgenteAduanalPk promocionInsertada = getSeguimientoOrdenesService()
                    .guardaDocumentoPromocionAgenteAduanal(promocion);
            escribeArchivoPromocion(promocion);
            for (FecetAlegatoAgenteAduanal alegato : getListaAlegatoAgenteAduanalCargadas()) {
                alegato.setIdPromocionAgeneteAduanal(promocionInsertada.getIdPromocionAgenteAduanal());
                alegato.setRutaArchivo(armaRutaAlegato(promocion, alegato));
                getSeguimientoOrdenesService().guardaDocumentoAlegatoAgenteAduanal(alegato);
                escribeArchivoAlegato(alegato);
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('cargaExito').show();");
        } else {
            addErrorMessage(null, "Error, debe cargar al menos una promoción", "");
        }
    }

    private String armaRutaPromocion(FecetPromocionAgenteAduanal promocion) {
        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM,
                new Date()));
        String ruta = RutaArchivosUtilPropuestas.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_AA_CONTRIBUYENTE,
                getOrdenSeleccionado().getFecetContribuyente().getRfc(), getOrdenSeleccionado().getNumeroOrden(),
                getUserProfile().getRfc(), getAgenteAduanal().getRfc(), carpetaUnica);
        StringBuilder rutaArchivo = new StringBuilder(ruta);
        return rutaArchivo.append(promocion.getNombreArchivo()).toString();
    }

    private void escribeArchivoPromocion(FecetPromocionAgenteAduanal promocion) {
        try {
            CargaArchivoUtilPropuestas.guardarArchivoAA(promocion);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void escribeArchivoAlegato(FecetAlegatoAgenteAduanal alegato) {
        try {
            CargaArchivoUtilPropuestas.guardarArchivoAlegatoAA(alegato);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private String armaRutaAlegato(FecetPromocionAgenteAduanal promocion, FecetAlegatoAgenteAduanal alegato) {
        String path = promocion.getRutaArchivo().replace(promocion.getNombreArchivo(), "");
        StringBuilder rutaArchivo = new StringBuilder(path);
        rutaArchivo.append("alegato");
        rutaArchivo.append(Constantes.DIAGONAL);
        rutaArchivo.append(alegato.getNombreArchivo());
        return rutaArchivo.toString();
    }

    public String regresa() {
        limpiarVariables();
        return "documentacionOrden";
    }

    private void limpiarVariables() {
        ordenSeleccionado = null;
        archivoDescargaPromocion = null;
        archivoDescargaPruebasAlegatos = null;
        archivoDescargaPromocionSeleccionada = null;
        archivoDescargaAnexoSeleccionada = null;
        archivoPromocion = null;
        archivoPruebasAlegatos = null;
        listaAlegatoAgenteAduanal = null;
        alegatoAgenteAduanalSeleccionado = null;
        listaPromocionesCargadas = null;
        listaAlegatoAgenteAduanalCargadas = null;
        listaPromocionAgenteAduanal = null;
        promocionAgenteAduanalSeleccionado = null;

        visibleBtnEliminarOrden = false;
        mostrarPruebasAlegatos = false;
        paginaCargada = false;
        noVerEditables = false;

    }

    public void redireccionaRegreso() throws IOException {

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        limpiarVariables();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public List<FecetPromocionAgenteAduanal> getListaPromocionAgenteAduanal() {
        return listaPromocionAgenteAduanal;
    }

    public void setListaPromocionAgenteAduanal(List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal) {
        this.listaPromocionAgenteAduanal = listaPromocionAgenteAduanal;
    }

    public FecetPromocionAgenteAduanal getPromocionAgenteAduanalSeleccionado() {
        return promocionAgenteAduanalSeleccionado;
    }

    public void setPromocionAgenteAduanalSeleccionado(FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado) {
        this.promocionAgenteAduanalSeleccionado = promocionAgenteAduanalSeleccionado;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        archivoDescargaPromocion = getDescargaArchivo(
                getPromocionAgenteAduanalSeleccionado().getRutaArchivo()
                        + getPromocionAgenteAduanalSeleccionado().getNombreArchivo(),
                getPromocionAgenteAduanalSeleccionado().getNombreArchivo());
        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public List<FecetAlegatoAgenteAduanal> getListaAlegatoAgenteAduanal() {
        return listaAlegatoAgenteAduanal;
    }

    public void setListaAlegatoAgenteAduanal(List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanal) {
        this.listaAlegatoAgenteAduanal = listaAlegatoAgenteAduanal;
    }

    public FecetAlegatoAgenteAduanal getAlegatoAgenteAduanalSeleccionado() {
        return alegatoAgenteAduanalSeleccionado;
    }

    public void setAlegatoAgenteAduanalSeleccionado(FecetAlegatoAgenteAduanal alegatoAgenteAduanalSeleccionado) {
        this.alegatoAgenteAduanalSeleccionado = alegatoAgenteAduanalSeleccionado;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        archivoDescargaPruebasAlegatos = getDescargaArchivo(
                getAlegatoAgenteAduanalSeleccionado().getRutaArchivo()
                        + getAlegatoAgenteAduanalSeleccionado().getNombreArchivo(),
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo());
        return archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public List<FecetPromocionAgenteAduanal> getListaPromocionesCargadas() {
        return listaPromocionesCargadas;
    }

    public void setListaPromocionesCargadas(List<FecetPromocionAgenteAduanal> listaPromocionesCargadas) {
        this.listaPromocionesCargadas = listaPromocionesCargadas;
    }

    public UploadedFile getArchivoPromocion() {
        return archivoPromocion;
    }

    public void setArchivoPromocion(UploadedFile archivoPromocion) {
        this.archivoPromocion = archivoPromocion;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public boolean isVisibleBtnEliminarOrden() {
        return visibleBtnEliminarOrden;
    }

    public void setVisibleBtnEliminarOrden(boolean visibleBtnEliminarOrden) {
        this.visibleBtnEliminarOrden = visibleBtnEliminarOrden;
    }

    public StreamedContent getArchivoDescargaPromocionSeleccionada() {
        byte[] archivo = getArchivoTempService().consultaArchivoTemp(
                getPromocionAgenteAduanalSeleccionado().getIdArchivoTemp(), getUserProfile().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.archivoDescargaPromocionSeleccionada = new DefaultStreamedContent(byteArchivo,
                getPromocionAgenteAduanalSeleccionado().getNombreArchivo(),
                getPromocionAgenteAduanalSeleccionado().getNombreArchivo());

        return archivoDescargaPromocionSeleccionada;
    }

    public void setArchivoDescargaPromocionSeleccionada(StreamedContent archivoDescargaPromocionSeleccionada) {
        this.archivoDescargaPromocionSeleccionada = archivoDescargaPromocionSeleccionada;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
    }

    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaAnexoSeleccionada() {
        byte[] archivo = getArchivoTempService().consultaArchivoTemp(
                getAlegatoAgenteAduanalSeleccionado().getIdArchivoTemp(), getUserProfile().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.archivoDescargaAnexoSeleccionada = new DefaultStreamedContent(byteArchivo,
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo(),
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo());
        return archivoDescargaAnexoSeleccionada;

    }

    public void setArchivoDescargaAnexoSeleccionada(StreamedContent archivoDescargaAnexoSeleccionada) {
        this.archivoDescargaAnexoSeleccionada = archivoDescargaAnexoSeleccionada;
    }

    public List<FecetAlegatoAgenteAduanal> getListaAlegatoAgenteAduanalCargadas() {
        return listaAlegatoAgenteAduanalCargadas;
    }

    public void setListaAlegatoAgenteAduanalCargadas(
            List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanalCargadas) {
        this.listaAlegatoAgenteAduanalCargadas = listaAlegatoAgenteAduanalCargadas;
    }

    public UploadedFile getArchivoPruebasAlegatos() {
        return archivoPruebasAlegatos;
    }

    public void setArchivoPruebasAlegatos(UploadedFile archivoPruebasAlegatos) {
        this.archivoPruebasAlegatos = archivoPruebasAlegatos;
    }

    public ColaboradorVO getAgenteAduanal() {
        return agenteAduanal;
    }

    public void setAgenteAduanal(ColaboradorVO agenteAduanal) {
        this.agenteAduanal = agenteAduanal;
    }

    public boolean isNoVerEditables() {
        return noVerEditables;
    }

    public void setNoVerEditables(boolean noVerEditables) {
        this.noVerEditables = noVerEditables;
    }

}
