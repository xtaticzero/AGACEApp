package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.managedbean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OficioPorFirmarVO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.DocumentacionFirmanteMBSubAbstract;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "documentacionFirmanteMB")
@ViewScoped
public class DocumentacionFirmanteMB extends DocumentacionFirmanteMBSubAbstract {

    private static final long serialVersionUID = 1234342342355L;

    @PostConstruct
    public void init() {
        logger.info(":::::::::::::::::::::::: INIT :::::::::::::::::::::::");
        setListaPromociones(new ArrayList<FecetPromocion>());
        setListaProrrogasPendientes(new ArrayList<FecetProrrogaOrden>());
        setListaProrrogasFirmadas(new ArrayList<FecetProrrogaOrden>());
        setListaPruebasPericialesPendientes(new ArrayList<FecetPruebasPericiales>());
        setListaPruebasPericialesFirmadas(new ArrayList<FecetPruebasPericiales>());
        setListaOficiosPorFirmar(new ArrayList<FecetOficio>());
        setListaOficiosFirmados(new ArrayList<FecetOficio>());
        setListAlegatosAnexos(new ArrayList<FecetAlegato>());
        setMapaOficioOrden(new HashMap<String, List<FecetOficio>>());
        setOficioSeleccionado(new FecetOficio());
        setProrrogaOrdenSeleccionada(new FecetProrrogaOrden());
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
        setOrdenSeleccionado(getFirmanteSeguimientoMB().getOrdenSeleccionada());
        setListaSolicitudContribuyenteFirmadas(new ArrayList<SolicitudContribuyenteVO>());
        setListaSolicitudContribuyentePendientes(new ArrayList<SolicitudContribuyenteVO>());
        setListaSolicitudContribuyenteAux(new ArrayList<SolicitudContribuyenteVO>());

    }

    public void validaMetodo() {
        this.setRenderedProrrogas(false);
        BigDecimal strIdMetodo = getFirmanteSeguimientoMB().getOrdenSeleccionada().getFeceaMetodo().getIdMetodo();
        this.setRenderedProrrogas(strIdMetodo.equals(Constantes.REE) ? false
                : strIdMetodo.equals(Constantes.REE) ? false : true);
    }

    public void cargarColaboradores() {
        setColaboradoresDTO(new ColaboradorDocumentoDTO());
        getColaboradoresDTO().setRepresentanteLegal(getFirmanteSeguimientoMB().getFirmanteSeguimientoService().obtenerColaborador(getFirmanteSeguimientoMB().getOrdenSeleccionada(), Constantes.ID_REPRESENTANTE_LEGAL));
        getColaboradoresDTO().setApoderadoLegal(getFirmanteSeguimientoMB().getFirmanteSeguimientoService().obtenerColaborador(getFirmanteSeguimientoMB().getOrdenSeleccionada(), Constantes.ID_APODERADO_LEGAL));
        getColaboradoresDTO().setAgenteAduanal(getFirmanteSeguimientoMB().getFirmanteSeguimientoService().obtenerColaborador(getFirmanteSeguimientoMB().getOrdenSeleccionada(), Constantes.ID_AGENTE_ADUANAL));
        getColaboradoresDTO().setApoderadoLegalRepresentanteLegal(getFirmanteSeguimientoMB().getFirmanteSeguimientoService().obtenerColaborador(getFirmanteSeguimientoMB().getOrdenSeleccionada(), Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL));
        if (!getColaboradoresDTO().getRepresentanteLegal().isSinColaborador()) {
            getColaboradoresDTO().setDocumentosRepresentante(getFirmanteSeguimientoMB().getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getColaboradoresDTO().getRepresentanteLegal().getAsociado().getIdAsociado()));
        }
        if (!getColaboradoresDTO().getApoderadoLegal().isSinColaborador()) {
            getColaboradoresDTO().setDocumentosAL(getFirmanteSeguimientoMB().getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getColaboradoresDTO().getApoderadoLegal().getAsociado().getIdAsociado()));
        }
        if (!getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().isSinColaborador()) {
            getColaboradoresDTO().setDocumentosALRL(getFirmanteSeguimientoMB().getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().getAsociado().getIdAsociado()));
        }
    }

    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        logger.debug("contentType {} ", event.getFile().getContentType());
        if (OrdenesDocumentacionArchivoUtil.validaContentType(event.getFile().getContentType())) {
            DocumentoVO docAdjunto = new DocumentoVO();
            docAdjunto.setNombreArchivo(event.getFile().getFileName());
            docAdjunto.setContenido(event.getFile().getContents());
            docAdjunto.setInputStream(event.getFile().getInputstream());
            docAdjunto.setContentType(event.getFile().getContentType());
            docAdjunto.setTamanio(event.getFile().getSize());

            for (Iterator<DocumentoVO> iter = getListaDocumentoUpLoad().listIterator(); iter.hasNext();) {
                DocumentoVO a = iter.next();
                if (a.getNombreArchivo().equalsIgnoreCase(docAdjunto.getNombreArchivo())) {
                    logger.error("Documento duplicado ... ");
                    addErrorMessage(null, "Documento Duplicado");
                    iter.remove();
                }
            }
            getListaDocumentoUpLoad().add(docAdjunto);
        } else {
            addErrorMessage(null, "Solo se aceptan archivos tipo Word, Excel o PDF versi\u00f3n 2007 o superior.");
        }
    }

    public void eliminaDocumentoAdjunto() {
        logger.debug("eliminaDocumentoAdjunto");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String nombreDocumentoEliminado = params.get("paramDocumento");
        for (Iterator<DocumentoVO> iter = getListaDocumentoUpLoad().listIterator(); iter.hasNext();) {
            DocumentoVO a = iter.next();
            if (a.getNombreArchivo().equalsIgnoreCase(nombreDocumentoEliminado)) {
                iter.remove();
            }
        }
    }

    public void resetBanderas() {
        setMapaOficioOrden(new HashMap<String, List<FecetOficio>>());
        setMapaProrrogas(new HashMap<String, List<FecetProrrogaOrden>>());
        setListaPromociones(new ArrayList<FecetPromocion>());
        setListaOficiosFirmados(new ArrayList<FecetOficio>());
        setListaOficiosPorFirmar(new ArrayList<FecetOficio>());
        setListaProrrogasPendientes(new ArrayList<FecetProrrogaOrden>());
        setListaProrrogasFirmadas(new ArrayList<FecetProrrogaOrden>());
        setListaPruebasPericialesPendientes(new ArrayList<FecetPruebasPericiales>());
        setListaPruebasPericialesFirmadas(new ArrayList<FecetPruebasPericiales>());
        setListaSolicitudContribuyenteFirmadas(new ArrayList<SolicitudContribuyenteVO>());
        setListaSolicitudContribuyentePendientes(new ArrayList<SolicitudContribuyenteVO>());
        setListaSolicitudContribuyenteAux(new ArrayList<SolicitudContribuyenteVO>());
        setMotivoRechazo("");
        setFirmadoDocumento(false);
        setFirmadoProrroga(false);
        setOficioSeleccionado(new FecetOficio());
        setProrrogaOrdenSeleccionada(new FecetProrrogaOrden());
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
    }

    public void cargaOficiosFirmante() {
        if (getListaOficiosPorFirmar().isEmpty() || getListaOficiosFirmados().isEmpty()) {
            logger.debug("::::: cargaOficiosFirmante :::::: ");
            BigDecimal idOrden = getFirmanteSeguimientoMB().getOrdenSeleccionada().getIdOrden();
            setMapaOficioOrden(getFirmanteSeguimientoService().getOficiosFirmante(idOrden));
            setListaOficiosPorFirmar(getMapaOficioOrden().get(Constantes.GET_OFICIOS_PENDIENTES));
            setListaOficiosFirmados(getMapaOficioOrden().get(Constantes.GET_OFICIOS_FIRMADOS));
        }
    }

    public void cargaProrrogasPendientes() {
        if (getListaProrrogasPendientes().isEmpty()) {
            logger.debug("::::: cargaProrrogasFirmante ::::::");
            BigDecimal idOrden = getFirmanteSeguimientoMB().getOrdenSeleccionada().getIdOrden();
            setMapaProrrogas(getFirmanteSeguimientoService().getProrrogasOrden(idOrden));
            setListaProrrogasPendientes(getMapaProrrogas().get(Constantes.GET_PRORROGAS_PENDIENTES));

            setListaSolicitudContribuyenteAux(getHelper().llenaObjetoSolicitudContProrroga(getListaProrrogasPendientes()));
            setListaSolicitudContribuyentePendientes(getHelper().llenaObjetoSolicitudContFull(getListaSolicitudContribuyentePendientes(), getListaSolicitudContribuyenteAux()));

        }
    }

    public void cargaProrrogasFirmadas() {
        if (getListaProrrogasFirmadas().isEmpty()) {
            logger.debug("::::: cargaProrrogasFirmante ::::::");
            BigDecimal idOrden = getFirmanteSeguimientoMB().getOrdenSeleccionada().getIdOrden();
            setMapaProrrogas(getFirmanteSeguimientoService().getProrrogasOrden(idOrden));
            setListaProrrogasFirmadas(getMapaProrrogas().get(Constantes.GET_PRORROGAS_FIRMADAS));

            setListaSolicitudContribuyenteAux(getHelper().llenaObjetoSolicitudContProrroga(getListaProrrogasFirmadas()));
            setListaSolicitudContribuyenteFirmadas(getHelper().llenaObjetoSolicitudContFull(getListaSolicitudContribuyenteFirmadas(), getListaSolicitudContribuyenteAux()));

        }
    }

    /**
     * @return String
     */
    public String cancelUpLoad() {
        resetBanderas();
        return RETURN_INDEX;
    }

    /**
     * @param event
     */
    public void validaSelectRadioOficio(SelectEvent event) {
        logger.debug("Object event [{}]", event.getObject().toString());
        if (getOficioSeleccionado().getIdOficio() != null) {
            logger.debug("Valida seleccion Oficio " + getOficioSeleccionado().getIdOficio());
            setFirmadoDocumento(true);
        } else {
            setFirmadoDocumento(false);
        }
    }

    /**
     * @param event
     */
    public void validaSeleccionRadioProrroga(SelectEvent event) {
        logger.debug("Object event [{}]", event.getObject().toString());
        if (getProrrogaOrdenSeleccionada().getIdProrrogaOrden() != null) {
            setFirmadoProrroga(true);
        } else {
            setFirmadoProrroga(false);
        }
    }

    /**
     * Anexos de la Pruebas y aAlegatos
     */
    public void getPruebasAlegatosPromocion() {
        logger.debug("getPruebasAlegatosPromocion ... {} ", getPromocionSeleccionada().toString());
        setListAlegatosAnexos(getFirmanteSeguimientoService().getPruebasAlegatosPromocion(getPromocionSeleccionada().getIdPromocion()));
        setIdPromocionSeleccionada(getPromocionSeleccionada().getIdPromocion());
        setFechaEnvioPromocionSeleccionada(getPromocionSeleccionada().getFechaCarga());
        RequestContext.getCurrentInstance().execute("PF('dialogAlegatosAnexos').show();");
    }

    /**
     * Documentos oficios anexos
     */
    public void getOficiosAnexos() {
        logger.debug("getAnexosOficioRechazo Entra por Oficio [ {} ] ",
                getOficioSeleccionado().getIdOficio().toString());
        setListOficioAnexos(new ArrayList<FecetOficioAnexos>());
        if (getListOficioAnexos().isEmpty()) {
            setListOficioAnexos(getFirmanteSeguimientoService().getAnexosOficioRechazo(getOficioSeleccionado().getIdOficio()));
            setFirmadoDocumento(true);
            if (getListOficioAnexos() != null && !getListOficioAnexos().isEmpty()) {
                EmpleadoDTO empleadoDTO;
                try {
                    empleadoDTO = getEmpleadoService().getEmpleadoCompleto(getOrdenSeleccionado().getIdAuditor().intValue());
                    if (empleadoDTO != null) {
                        if (!getEmpleadoService().validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.AUDITOR)) {
                            empleadoDTO = null;
                            setListOficioAnexos(new ArrayList<FecetOficioAnexos>());
                            throw new NoExisteEmpleadoException("No se encuentra el perfil del empleado");
                        }
                        for (FecetOficioAnexos anexo : getListOficioAnexos()) {
                            anexo.setPresentadoPor(empleadoDTO.getNombreCompleto());
                        }

                    }
                } catch (EmpleadoServiceException e) {
                    logger.error(e.getMessage());
                } catch (NoExisteEmpleadoException ne) {
                    logger.error(ne.getMessage());
                }
            }

        }

        RequestContext.getCurrentInstance().execute("PF('dialogOficioAnexos').show();");
        RequestContext.getCurrentInstance().update("documentacionFirmanteMB:tablaOficioAnexo");
    }

    /**
     * OficiosDependientes
     */
    public void getOficiosDependientesFirmados() {
        logger.debug("getOficiosDependientes ... {}", getOficioSeleccionado().getIdOficio());
        setListaOficiosDependientes(getFirmanteSeguimientoService().getOficiosDependientesFirmados(getOficioSeleccionado().getIdOficio()));
        RequestContext.getCurrentInstance().execute("PF('dialogOficiosDependientes').show();");
        RequestContext.getCurrentInstance().update("documentacionFirmanteMB:tablaOficiosDependientes");
    }

    public void getOficiosDependientesPorFirmar() {
        logger.debug("getOficiosDependientes ... {}", getOficioSeleccionado().getIdOficio());
        setFirmadoDocumento(true);
        setListaOficiosDependientes(getFirmanteSeguimientoService().getOficiosDependientes(getOficioSeleccionado().getIdOficio(),
                Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA));
        RequestContext.getCurrentInstance().execute("PF('dialogOficiosDependientes').show();");
        RequestContext.getCurrentInstance().update("documentacionFirmanteMB:tablaOficiosDependientes");
    }

    /**
     * Seccion de Prorroga Prorroga Anexos de resolucion
     */
    public void getAnexosResolucionProrroga() {
        setListaAnexosProrroga(getFirmanteSeguimientoService().getAnexosRechazoProrrogaOrden(getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden()));
        setListaAnexosSolicitudContribuyente(getHelper().llenaAnexoSolicitudContribuyenteProrroga(getListaAnexosProrroga()));
        RequestContext.getCurrentInstance().execute("PF('dlgAnexosRechazoSolicitudContr').show();");
        RequestContext.getCurrentInstance().update("documentacionFirmanteMB:panelAnexosSolicitudContr");

    }

    public void getAnexosResolucionSolicitudContribuyente() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            getAnexosResolucionProrroga();
        } else {
            getAnexosResolucionPruebaPericial();
        }
    }

    public void getAnexosResolucionPruebaPericial() {
        setListaAnexosPruebasPericiales(new ArrayList<FecetAnexoPruebasPericiales>());
        setListaAnexosPruebasPericiales(getFirmanteSeguimientoService().getAnexosRechazoPruebasPericiales(getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales()));
        setListaAnexosSolicitudContribuyente(getHelper().llenaAnexoSolicitudContribuyentePruebasPericiales(getListaAnexosPruebasPericiales()));
        RequestContext.getCurrentInstance().execute("PF('dlgAnexosRechazoSolicitudContr').show();");
        RequestContext.getCurrentInstance().update("documentacionFirmanteMB:panelAnexosSolicitudContr");
    }

    /**
     * Documentacion Contibuyente
     */
    public void getDocumentacionProrrogaContribuyente() {
        setListaDocumentacionSolicitudContribuyente(new ArrayList<SolicitudContribuyenteDocVO>());
        setListaDocumentacionSolicitudContribuyente(getHelper().llenaDocSolicitudContribuyenteProrroga(getFirmanteSeguimientoService().getDocumentacionProrrogaContribuyente(getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden().getIdProrrogaOrden())));

    }

    public void getDocumentacionPruebaPericialContribuyente() {
        setListaDocumentacionSolicitudContribuyente(new ArrayList<SolicitudContribuyenteDocVO>());
        setListaDocumentacionSolicitudContribuyente(getHelper().llenaDocSolicitudContribuyentePruebaPericial(getFirmanteSeguimientoService().getDocumentacionPruebaPericialContribuyente(getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales().getIdPruebasPericiales())));

    }

    public void getDocumentacionSolicitudContribuyente() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            getDocumentacionProrrogaContribuyente();
        } else {
            getDocumentacionPruebaPericialContribuyente();
        }

    }

    /**
     * Seccion de Promociones Promociones y alegatos
     */
    public void cargaPromocion() {
        if (getListaPromociones().isEmpty()) {
            setListaPromociones(getFirmanteSeguimientoService().getPromocionContadorPruebasAlegatos(getFirmanteSeguimientoMB().getOrdenSeleccionada()));
        }
    }

    public String enviarFirma() {
        if (getOficioSeleccionado() != null) {
            OficioPorFirmarVO oficioPorFirmarVO = new OficioPorFirmarVO();
            oficioPorFirmarVO.setOficioSeleccionado(getOficioSeleccionado());
            oficioPorFirmarVO.setOrdenSeleccionado(getOrdenSeleccionado());
            oficioPorFirmarVO.setRfcFirmante(getUserProfile().getRfc());
            oficioPorFirmarVO.setRfcAuditado(getOrdenSeleccionado().getFecetContribuyente().getRfc());
            oficioPorFirmarVO.setClaveAdministracion(getFirmanteSeguimientoService().obtenerIdAraceFromPropuesta(getOrdenSeleccionado().getIdOrden()).toString());
            oficioPorFirmarVO.setOficiosDependientes(getFirmanteSeguimientoService().obtenerOficiosDependientes(getOficioSeleccionado().getIdOficio()));
            getSession().setAttribute("oficioPorFirmar", getOficioSeleccionado());
            getSession().setAttribute("oficioPorFirmarVO", oficioPorFirmarVO);
            return FIRMA;
        } else {
            addErrorMessage(null, "Es necesario seleccionar un oficio.");
            return null;
        }

    }

    public void rechazaFirmaOficio() {

        HttpServletRequest origRequest
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String motivoRechazoOficio;
        motivoRechazoOficio = origRequest.getParameter(PARAM_DESCRIPCION_RECHAZO);
        logger.debug("Rechazo Documento " + motivoRechazoOficio);
        if (motivoRechazoOficio != null && !motivoRechazoOficio.trim().equals("")) {
            try {
                getFirmanteSeguimientoService().rechazaOficio(getOficioSeleccionado(),
                        getFirmanteSeguimientoMB().getOrdenSeleccionada(),
                        getListaDocumentoUpLoad(),
                        Constantes.ESTATUS_OFICIO_RECHAZADO, motivoRechazoOficio);
                RequestContext.getCurrentInstance().execute("PF('motivoRechazoOficio').hide();");
                RequestContext.getCurrentInstance().execute("PF('exitoRechazoOficio').show();");
                resetBanderas();
                cargaOficiosFirmante();
                setFirmadoProrroga(false);
            } catch (IOException e) {
                addErrorMessage("Error Interno ", e.getMessage());
                logger.error(" Error Message {} ", e);
            }
        } else {
            addErrorMessage(null, FacesUtil.getMessageResourceString("error.rechazo.orden.sin.descripcion"));
        }

    }

    /**
     * @return redirecciona a widget para firmar prorroga
     */
    public String enviaFirmarProrroga() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden().setOrden(getFirmanteSeguimientoMB().getOrdenSeleccionada());
            getSession().setAttribute("prorrogaOrdenSeleccionada", getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden());
            return FIRMA_PRORROGA;
        } else {
            addErrorMessage(null, "Debe seleccionar por lo menos un oficio, verifique por favor.");
            return null;
        }
    }

    public String enviaFirmarSolicitudContribuyente() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            return enviaFirmarProrroga();
        } else {
            return enviaFirmarPruebasPericiales();
        }
    }

    public String enviaFirmarPruebasPericiales() {
        if (getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales() != null) {
            getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales().setOrden(getFirmanteSeguimientoMB().getOrdenSeleccionada());
            getSession().setAttribute("pruebaPericialSeleccionada", getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales());
            return FIRMA_PRUEBA_PERICIAL;
        } else {
            addErrorMessage(null, "Debe seleccionar por lo menos un oficio, verifique por favor.");
            return null;
        }
    }

    /**
     * @return String mensaje de confirmacion o error al Rechazar la Prorroga
     */
    public String rechazarFirmadoSolicitudContribuyente() {
        HttpServletRequest origRequest
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String motivoFirmanteRechazo = "";
        motivoFirmanteRechazo = origRequest.getParameter(PARAM_DESCRIPCION_RECHAZO_SOLICITUD_CONTRIBUYENTE);

        if (motivoFirmanteRechazo != null && !motivoFirmanteRechazo.trim().equals("")) {
            if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
                getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden().setOrden(getOrdenSeleccionado());
                getFirmanteSeguimientoService().rechazaFirmaProrrogaFirmante(getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden(),
                        motivoFirmanteRechazo,
                        EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus(),
                        getListaDocumentoUpLoad());

            } else {
                getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales().setOrden(getFirmanteSeguimientoMB().getOrdenSeleccionada());
                getFirmanteSeguimientoService().rechazaFirmaPruebaPericialFirmante(getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales(),
                        motivoFirmanteRechazo,
                        EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_RECHAZADA_FIRMANTE.getBigIdEstatus(),
                        getListaDocumentoUpLoad());
            }
            RequestContext.getCurrentInstance().execute("PF('motivoRechazoSolicitudContr').hide();");
            RequestContext.getCurrentInstance().execute("PF('exitoRechazoSolicitudContr').show();");
            resetBanderas();

        } else {
            addErrorMessage("El campo de Motivo es obligatorio. ");
        }

        return RETURN_INDEX;
    }

    public void validarRechazoSolicitudContribuyente() {
        RequestContext.getCurrentInstance().execute("PF('motivoRechazoSolicitudContr').show();");
    }

    public void validarRechazoOficio() {
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
        RequestContext.getCurrentInstance().execute("PF('motivoRechazoOficio').show();");
    }

    /**
     * Valida Documentos Cargados.
     */
    public boolean validaDocumentos() {
        if (getListaDocumentoUpLoad().isEmpty()) {
            addErrorMessage(null, "Es necesario agregar por lo menos un Archivo. ");
            return false;
        }
        return true;
    }

    public String navegaIndex() {
        resetBanderas();
        return "indexValidarFirmar?faces-redirect=true";
    }

    public void cargaSolicitudContribuyente() {
        //OBTENER PRORROGAS 
        if (getHelper().getListaEstatus() == null || getHelper().getListaEstatus().isEmpty()) {
            getHelper().setListaEstatus(getFirmanteSeguimientoService().getListaEstatus());
        }
        cargaProrrogasPendientes();
        cargaProrrogasFirmadas();

        //OBTENER PRUEBAS PERICIALES
        cargaPruebasPericialesFirmadas();
        cargaPruebasPericialesPendientes();

    }

    public void cargaPruebasPericialesPendientes() {
        if (getListaPruebasPericialesPendientes().isEmpty()) {
            logger.debug("::::: cargaPruebasPericialesFirmante ::::::");
            BigDecimal idOrden = getFirmanteSeguimientoMB().getOrdenSeleccionada().getIdOrden();
            setMapaPruebasPericiales(getFirmanteSeguimientoService().getPruebasPericiales(idOrden));
            setListaPruebasPericialesPendientes(getMapaPruebasPericiales().get(Constantes.GET_PRUEBAS_PERICIALES_PENDIENTES));

            setListaSolicitudContribuyenteAux(getHelper().llenaObjetoSolicitudContPruebasPeri(getListaPruebasPericialesPendientes()));
            setListaSolicitudContribuyentePendientes(getHelper().llenaObjetoSolicitudContFull(getListaSolicitudContribuyentePendientes(), getListaSolicitudContribuyenteAux()));
        }
    }

    public void cargaPruebasPericialesFirmadas() {
        if (getListaPruebasPericialesFirmadas().isEmpty()) {
            logger.debug("::::: cargaPruebasPericialesFirmante ::::::");
            BigDecimal idOrden = getFirmanteSeguimientoMB().getOrdenSeleccionada().getIdOrden();
            setMapaPruebasPericiales(getFirmanteSeguimientoService().getPruebasPericiales(idOrden));
            setListaPruebasPericialesFirmadas(getMapaPruebasPericiales().get(Constantes.GET_PRUEBAS_PERICIALES_FIRMADAS));

            setListaSolicitudContribuyenteAux(getHelper().llenaObjetoSolicitudContPruebasPeri(getListaPruebasPericialesFirmadas()));
            setListaSolicitudContribuyenteFirmadas(getHelper().llenaObjetoSolicitudContFull(getListaSolicitudContribuyenteFirmadas(), getListaSolicitudContribuyenteAux()));

        }
    }

    public void validaSeleccionRadioSolicitudContr(SelectEvent event) {
        logger.debug("Object event [{}]", event.getObject().toString());
        if (getSolicitudContribuyenteVOSeleccionado().getId() != null) {
            setFirmadoProrroga(true);
        } else {
            setFirmadoProrroga(false);
        }
    }

    public void cargaDocumentosAgenteAduanal() {
        setListaPromocionAgenteAduanal(getFirmanteSeguimientoService().getPromocionAgenteAduanalContadorPruebasAlegatos(getOrdenSeleccionado().getIdOrden()));
    }

    public void getPruebasAlegatosPromocionAgenteAduanal() {
        setListaAlegatoAgenteAduanal(getFirmanteSeguimientoService().getAlegatosAgenteAduanal(getPromocionAgenteAduanalSeleccionado().getIdPromocionAgenteAduanal()));

    }

}
