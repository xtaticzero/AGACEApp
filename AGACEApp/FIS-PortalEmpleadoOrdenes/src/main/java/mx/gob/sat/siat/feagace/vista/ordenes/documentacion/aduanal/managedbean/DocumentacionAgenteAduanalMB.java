package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.aduanal.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.aduanal.DocumentacionAgAduanalAbstractMB;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.CargaDocumentosAAHelper;

@SessionScoped
@ManagedBean(name = "documentacionAgenteAduanalMB")
public class DocumentacionAgenteAduanalMB extends DocumentacionAgAduanalAbstractMB {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {
        HashMap mapOrden = (HashMap) getSession().getAttribute("mapOrdenAA");
        if (mapOrden != null) {
            setNoVerEditables(false);

            if (mapOrden.containsKey("noVerEditables")) {
                setNoVerEditables((Boolean) mapOrden.get("noVerEditables"));
            }
        }

        if (!isPaginaCargada()) {
            if (mapOrden != null) {
                setOrdenSeleccionado((AgaceOrden) mapOrden.get("orden"));
                setAgenteAduanal((ColaboradorVO) mapOrden.get("agenteAduanal"));
                cargaHistorialDocumentosAgenteAduanal();
                setMostrarBtnDocsAgenteAduanal(validaMediosContacto());
                getSession().removeAttribute("mapOrdenAA");
                setPaginaCargada(true);
            } else {
                addErrorMessage(null, "Error al obtener archivos de session", "");
            }
        }

    }

    public void cargaHistorialDocumentosAgenteAduanal() {
        setListaPromocionAgenteAduanal(getSeguimientoOrdenesService().getPromocionAgenteAduanalContadorPruebasAlegatos(getOrdenSeleccionado().getIdOrden()));
    }

    public void getPruebasAlegatosPromocion() {
        setListaAlegatoAgenteAduanal(
                getSeguimientoOrdenesService().getAlegatosAgenteAduanal(getPromocionAgenteAduanalSeleccionado().getIdPromocionAgenteAduanal()));

    }

    private boolean validaMediosContacto() {
        boolean tieneMC = false;
        if (!getAgenteAduanal().isSinColaborador()) {
            try {
                FecetContribuyente contribuyenteIDC = getContribuyenteService().getContribuyenteIDC(getAgenteAduanal().getRfc());
                if (contribuyenteIDC != null) {
                    ValidaMediosContactoBO validaMediosContactoBO = checkMediosContacto(contribuyenteIDC.getRfc());
                    tieneMC = validaMediosContactoBO.isFlag();
                }
            } catch (NoExisteContribuyenteException nece) {
                tieneMC = false;
            }
        } else {
            tieneMC = true;
        }
        return tieneMC;
    }

    protected ValidaMediosContactoBO checkMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        validaMediosContactoBO.setValidaSoloMediosContacto(true);
        validaMediosContactoBO = getAsociadosService().validaMediosContactoAsociado(validaMediosContactoBO);
        return validaMediosContactoBO;
    }

    public void handleFileUploadPromocion(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        if (getListaPromocionesCargadas() == null) {
            setArchivoPromocion(event.getFile());
            FecetPromocionAgenteAduanal dto = new FecetPromocionAgenteAduanal();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(getArchivoPromocion().getFileName())));
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
        dto.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(getArchivoPruebasAlegatos().getFileName())));
        dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
        if (CargaDocumentosAAHelper.checarDuplicadoPruebasAlegatoAgenteAduanal(dto.getNombreArchivo(), getListaAlegatoAgenteAduanalCargadas())) {
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
            FecetPromocionAgenteAduanalPk promocionInsertada = getSeguimientoOrdenesService().guardaDocumentoPromocionAgenteAduanal(promocion);
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
        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
        String ruta = RutaArchivosUtilOrdenes.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_AA_CONTRIBUYENTE,
                getOrdenSeleccionado().getFecetContribuyente().getRfc(), getOrdenSeleccionado().getNumeroOrden(), getUserProfile().getRfc(),
                getAgenteAduanal().getRfc(), carpetaUnica);
        StringBuilder rutaArchivo = new StringBuilder(ruta);
        return rutaArchivo.append(promocion.getNombreArchivo()).toString();
    }

    private void escribeArchivoPromocion(FecetPromocionAgenteAduanal promocion) {
        try {
            CargaArchivoUtilOrdenes.guardarArchivoAA(promocion);
        } catch (IOException e) {
            logger.error("Error al guardar archivo agente aduanal", e);
        }
    }

    private void escribeArchivoAlegato(FecetAlegatoAgenteAduanal alegato) {
        try {
            CargaArchivoUtilOrdenes.guardarArchivoAlegatoAA(alegato);
        } catch (IOException e) {
            logger.error("Error al guardar archivo alegato agente aduanal", e);
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
        setOrdenSeleccionado(null);
        setArchivoDescargaPromocion(null);
        setArchivoDescargaPruebasAlegatos(null);
        setArchivoDescargaPromocionSeleccionada(null);
        setArchivoDescargaAnexoSeleccionada(null);
        setArchivoPromocion(null);
        setArchivoPruebasAlegatos(null);
        setListaAlegatoAgenteAduanal(null);
        setAlegatoAgenteAduanalSeleccionado(null);
        setListaPromocionesCargadas(null);
        setListaAlegatoAgenteAduanalCargadas(null);
        setListaPromocionAgenteAduanal(null);
        setPromocionAgenteAduanalSeleccionado(null);

        setVisibleBtnEliminarOrden(false);
        setMostrarPruebasAlegatos(false);
        setPaginaCargada(false);
        setNoVerEditables(false);

    }

    public void redireccionaRegreso() throws IOException {

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        limpiarVariables();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

}
