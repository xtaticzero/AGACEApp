package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenSolicitudHelper;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenUnifHelper;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOrdenVistaHelper;
import mx.gob.sat.siat.feagace.vista.model.ordenes.AnexoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ordenes.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ordenes.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ordenes.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ordenes.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ordenes.ProrrogaOficioConsultaDTO;

@ManagedBean(name = "detalleOrdenUnificadoMB")
@ViewScoped
public class DetalleOrdenUnificadoMB extends DetalleOrdenAbstractMB {

    private static final long serialVersionUID = -4749828988505154961L;

    @PostConstruct
    public void init() {

        if (getSession().getAttribute(ORIGEN_CONSULTA) != null) {

            setHelper(new DetalleOrdenUnifHelper());
            setSolHelper(new DetalleOrdenSolicitudHelper());
            setVistaHelper(new DetalleOrdenVistaHelper());
            setOrdenConsultaSeleccionada(new OrdenConsultaDTO());

            getHelper().setOrigenConsulta((String) getSession().getAttribute(ORIGEN_CONSULTA));
            getHelper().setUserProfile((UserProfileDTO) getSession().getAttribute(USER_PROFILE));
            setOrdenConsultaSeleccionada((OrdenConsultaDTO) getSession().getAttribute(ORDEN_CONSULTA_SELECCIONADA));

            getSession().removeAttribute(ORIGEN_CONSULTA);
            getSession().removeAttribute(ORDEN_CONSULTA_SELECCIONADA);

            tablasDatosAMostrar();

            BigDecimal idOrden = getOrdenConsultaSeleccionada().getIdOrden();
            // Informacion del contribuyente para el panel principal.
            getHelper().setContribuyente(getConsultarReimprimirDocumentosService()
                    .buscarContribuyentePorID(getOrdenConsultaSeleccionada().getIdContribuyente()));
            // Obtiene informacion del firmante de la orden.
            EmpleadoDTO fececEmpleado = getConsultarReimprimirDocumentosService()
                    .buscarEmpleadoPorIdEmpleado(getOrdenConsultaSeleccionada().getIdFirmante());
            getOrdenConsultaSeleccionada().setFirmante(fececEmpleado);
            // Obtener informacion de promociones.
            List<FecetPromocion> listFecetPromocion = getConsultarReimprimirDocumentosService()
                    .buscarPromocionPorOrden(idOrden);
            for (FecetPromocion promocion : listFecetPromocion) {
                promocion.setDescripcionTipoPromocion(
                        BusinessUtil.getTipoPromocionOrdenPorMetodo(getOrdenConsultaSeleccionada().getIdMetodo()));
                promocion.setExtemporanea(getPlazosService().esDocumentoExtemporaneoOrden(
                        getHelper().getOrdenSeleccionada(), promocion.getFechaCarga()) ? "1" : "0");
            }
            List<PromocionConsultaDTO> listPromocionConsultaDTO = getConsultarReimprimirDocumentosHelper()
                    .convertToPromocionConsulta(listFecetPromocion, getOrdenConsultaSeleccionada());
            getOrdenConsultaSeleccionada().setListPromocionConsultaDTO(listPromocionConsultaDTO);
            // Obtener informacion de oficios.
            List<FecetOficio> listFecetOficio = getConsultarReimprimirDocumentosService()
                    .buscarOficiosEnviadosCont(idOrden);
            List<OficioConsultaDTO> listOficioConsultaDTO;
            if (listFecetOficio != null) {
                for (FecetOficio oficio : listFecetOficio) {
                    oficio.getFecetTipoOficio().setNombre(llenarRfcANombreOficio(oficio));
                    getPlazosService().obtenerFechasNotificacion(oficio);
                }
                listOficioConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToOficioConsultaDTO(listFecetOficio, getOrdenConsultaSeleccionada());
            } else {
                listOficioConsultaDTO = new ArrayList<OficioConsultaDTO>();
            }
            getOrdenConsultaSeleccionada().setListOficioConsultaDTO(listOficioConsultaDTO);

            obtenRepresentanteLegal();
            cargaSolicitudContribuyente();

            if (getVistaHelper().isPnlPapelesTrab()) {
                cargarPapelesTrabajoOrden();
            }
            if (getVistaHelper().isPnlOficiosFirm()) {
                getHelper().setListaOficiosFirmados(
                        getSeguimientoOrdenesService().getOficiosFirmados(getOrdenConsultaSeleccionada().getIdOrden()));
                llenarRfcANombreOficio(getHelper().getListaOficiosFirmados());
            }
            if (getVistaHelper().isPnlOficiosHist()) {
                getHelper().setListaOficiosRechazados(getSeguimientoOrdenesService()
                        .getOficiosRechazados(getHelper().getOrdenSeleccionada().getIdOrden()));
            }
            if (getVistaHelper().isPnlAgenteAdnl()) {
                obtenAgenteAduanal();
            }

        } else {
            logger.info("No se encontraron datos en sesión");
        }

    }

    public void redireccionaOficio() throws IOException {

        if (getHelper().getOficioConsultaSeleccionado() != null) {
            List<FecetOficio> listFecetOficio = getConsultarReimprimirDocumentosService()
                    .buscarOficioPorOrden(getOrdenConsultaSeleccionada().getIdOrden());
            for (FecetOficio oficio : listFecetOficio) {
                if (getHelper().getOficioConsultaSeleccionado().getIdOficio().compareTo(oficio.getIdOficio()) == 0) {
                    getHelper().setOficioSeleccionado(oficio);
                    break;
                }
            }
        }

        getSession().setAttribute("muestraPapelesTrabajo", true);
        getSession().setAttribute("oficioSeleccionadoU", getHelper().getOficioSeleccionado());
        getSession().setAttribute("ordenSeleccionadaU", getHelper().getOrdenSeleccionada());

        getSession().setAttribute(ORIGEN_CONSULTA, getHelper().getOrigenConsulta());
        getSession().setAttribute(USER_PROFILE, getHelper().getUserProfile());
        getSession().setAttribute(ORDEN_CONSULTA_SELECCIONADA, getOrdenConsultaSeleccionada());

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/documentacionOficio.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public void redireccionaOficioFirmado() throws IOException {

        getSession().setAttribute("oficioSeleccionadoU", getHelper().getOficioSeleccionado());
        getSession().setAttribute("ordenSeleccionadaU", getHelper().getOrdenSeleccionada());

        getSession().setAttribute(ORIGEN_CONSULTA, getHelper().getOrigenConsulta());
        getSession().setAttribute(USER_PROFILE, getHelper().getUserProfile());
        getSession().setAttribute(ORDEN_CONSULTA_SELECCIONADA, getOrdenConsultaSeleccionada());

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/documentacionOficio.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public void redireccionaCifras() throws IOException {

        getSession().setAttribute("ordenSeleccionadaC", getHelper().getOrdenSeleccionada());

        getSession().setAttribute(ORIGEN_CONSULTA, getHelper().getOrigenConsulta());
        getSession().setAttribute(USER_PROFILE, getHelper().getUserProfile());
        getSession().setAttribute(ORDEN_CONSULTA_SELECCIONADA, getOrdenConsultaSeleccionada());

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/cifras.xhtml?faces-redirect=true";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public void cargaDocumentosAgenteAduanal() throws IOException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("orden", getHelper().getOrdenSeleccionada());
        map.put("agenteAduanal", getHelper().getColaboradoresDTO().getAgenteAduanal());
        map.put("noVerEditables", true);
        getSession().setAttribute("mapOrdenAA", map);

        getSession().setAttribute(ORIGEN_CONSULTA, getHelper().getOrigenConsulta());
        getSession().setAttribute(USER_PROFILE, getHelper().getUserProfile());
        getSession().setAttribute(ORDEN_CONSULTA_SELECCIONADA, getOrdenConsultaSeleccionada());

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/cargaDocsAgenteAduanal.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

    public void cargaSolicitudContribuyente() {
        // OBTENER PRORROGAS
        getSolHelper().setListaProrroga(getSeguimientoOrdenesService()
                .getProrrogaPorOrdenEstatusPendienteAuditor(getHelper().getOrdenSeleccionada().getIdOrden()));
        getSolHelper().setListaSolicitudContribuyente(
                getSolHelper().llenaObjetoSolicitudContProrroga(getSolHelper().getListaProrroga()));

        getSolHelper().setListaSolicitudContribuyenteConcentrado(
                getSolHelper().llenaObjetoSolicitudContFull(getSolHelper().getListaSolicitudContribuyenteConcentrado(),
                        getSolHelper().getListaSolicitudContribuyente()));

        // OBTENER PRORROGAS HISTORICO
        getSolHelper().setListaProrrogaHistorico(getSeguimientoOrdenesService()
                .getHistoricoProrrogaOrden(getHelper().getOrdenSeleccionada().getIdOrden()));

        getSolHelper().setListaSolicitudContribuyenteHistorico(
                getSolHelper().llenaObjetoSolicitudContProrroga(getSolHelper().getListaProrrogaHistorico()));

        getSolHelper().setListaSolicitudContribuyenteConcentradoHistorico(getSolHelper().llenaObjetoSolicitudContFull(
                getSolHelper().getListaSolicitudContribuyenteConcentradoHistorico(),
                getSolHelper().getListaSolicitudContribuyenteHistorico()));

        // OBTENER PRUEBAS PERICIALES
        getSolHelper().setListaPruebasPericiales(getSeguimientoOrdenesService()
                .getPruebaPericialPorOrdenEstatusPendienteAuditor(getHelper().getOrdenSeleccionada().getIdOrden()));

        getSolHelper().setListaSolicitudContribuyente(
                getSolHelper().llenaObjetoSolicitudContPruebasPeri(getSolHelper().getListaPruebasPericiales()));

        getSolHelper().setListaSolicitudContribuyenteConcentrado(
                getSolHelper().llenaObjetoSolicitudContFull(getSolHelper().getListaSolicitudContribuyenteConcentrado(),
                        getSolHelper().getListaSolicitudContribuyente()));

        // OBTENER PRUEBAS PERICIALES HISTORICO
        getSolHelper().setListaPruebasPericialesHistorico(getSeguimientoOrdenesService()
                .getHistoricoPruebasPericiales(getHelper().getOrdenSeleccionada().getIdOrden()));

        getSolHelper().setListaSolicitudContribuyenteHistorico(getSolHelper()
                .llenaObjetoSolicitudContPruebasPeri(getSolHelper().getListaPruebasPericialesHistorico()));

        getSolHelper().setListaSolicitudContribuyenteConcentradoHistorico(getSolHelper().llenaObjetoSolicitudContFull(
                getSolHelper().getListaSolicitudContribuyenteConcentradoHistorico(),
                getSolHelper().getListaSolicitudContribuyenteHistorico()));

    }

    public void obtenRepresentanteLegal() {
        getHelper().setColaboradoresDTO(new ColaboradorDocumentoDTO());
        ColaboradorVO repLegal = new ColaboradorVO();
        repLegal.setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
        getHelper().getColaboradoresDTO().setRepresentanteLegal((getAsociadosService().cargaColaborador(repLegal,
                convertirDTOaAgaceOrden(getOrdenConsultaSeleccionada()))));
        cargarDocRepresentanteLegal();
    }

    public void obtenAgenteAduanal() {
        ColaboradorVO agenteAduanal = new ColaboradorVO();
        agenteAduanal.setTipoAsociado(Constantes.ID_AGENTE_ADUANAL);
        getHelper().getColaboradoresDTO().setAgenteAduanal(
                (getAsociadosService().cargaColaborador(agenteAduanal, getHelper().getOrdenSeleccionada())));
        getHelper().setMostrarBtnDocsAgenteAduanal(validaMediosContacto());
    }

    private boolean validaMediosContacto() {
        boolean tieneMC = false;
        if (!getHelper().getColaboradoresDTO().getAgenteAduanal().isSinColaborador()) {
            FecetContribuyente contribuyenteIDC;
            if (!getHelper().getColaboradoresDTO().getAgenteAduanal().isSinColaborador()) {
                try {
                    contribuyenteIDC = getContribuyenteService()
                            .getContribuyenteIDC(getHelper().getColaboradoresDTO().getAgenteAduanal().getRfc());
                    if (contribuyenteIDC != null) {
                        ValidaMediosContactoBO validaMediosContactoBO = checkMediosContacto(contribuyenteIDC.getRfc());
                        tieneMC = validaMediosContactoBO.isFlag();
                    }
                } catch (NoExisteContribuyenteException nece) {
                    tieneMC = false;
                }
            } else {
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

    private AgaceOrden convertirDTOaAgaceOrden(OrdenConsultaDTO ordenConsultaDTO) {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(ordenConsultaDTO.getIdOrden());

        return orden;
    }

    private void cargarDocRepresentanteLegal() {
        if (getHelper().getColaboradoresDTO().getRepresentanteLegal().getAsociado() != null) {
            getHelper().getColaboradoresDTO()
                    .setDocumentosRepresentante(getFecetDocAsociadoService().obtenerDocumentosPorAsociado(
                                    getHelper().getColaboradoresDTO().getRepresentanteLegal().getAsociado().getIdAsociado()));

        } else {
            getHelper().getColaboradoresDTO().setDocumentosRepresentante(new ArrayList<FecetDocAsociado>());
        }
    }

    public void cargarPapelesTrabajoOrden() {
        getHelper().setListaPapelesTrabajo(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService().getPapelesByIdPropuestaOrIdOrden(
                getOrdenConsultaSeleccionada().getIdPropuesta(), getOrdenConsultaSeleccionada().getIdOrden());
        if (listaPapelTrabajo != null) {
            for (PapelesTrabajo papel : listaPapelTrabajo) {
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getHelper().getListaPapelesTrabajo().add(documento);
            }
        }
    }

    public StreamedContent getArchivoDescargable() {
        StreamedContent content = null;
        try {
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(),
                    getNombreArchivo());
        } catch (final Exception exception) {
            logger.error("No se pudo descargar el archivo. ", exception);
            addErrorMessage("No se encontro el documento seleccionado");
        }
        return content;
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {

        if (getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(getArchivoTempService().consultaArchivoTemp(
                    getHelper().getPapelTrabajoSeleccionado().getIdDocumentoTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream",
                    getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
        }

        return getArchivoGenerico(getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo(),
                getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
    }

    private StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    public void buscarDocPruebasAlegatos() {
        List<FecetAlegato> listFecetAlegato = getConsultarReimprimirDocumentosService()
                .buscarAlegatosPorPromocion(getHelper().getPromocionConsultaSeleccionada().getIdPromocion());
        getHelper().getPromocionConsultaSeleccionada().setListFecetAlegato(listFecetAlegato);
        RequestContext.getCurrentInstance().execute("PF('pnlDocPruebaAlegatos').show();");
    }

    public void buscarProrrogaOficio() {
        List<FecetProrrogaOficio> listFecetProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarProrrogaOficioPorOficio(getHelper().getOficioConsultaSeleccionado().getIdOficio());
        List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta = getConsultarReimprimirDocumentosHelper()
                .convertToProrrogaOficioConsultaDTO(listFecetProrrogaOficio,
                        getHelper().getOficioConsultaSeleccionado(), getOrdenConsultaSeleccionada());
        for (ProrrogaOficioConsultaDTO prorrogaOficioConsulta : listProrrogaOficioConsulta) {
            BigDecimal idOficioProrroga = prorrogaOficioConsulta.getIdProrroga();
            List<FecetFlujoProrrogaOficio> listFecetFlujoProrrogaOficios = getConsultarReimprimirDocumentosService()
                    .buscarFlujoProrrogaOficioPorProrroga(idOficioProrroga);
            if (!listFecetFlujoProrrogaOficios.isEmpty()) {
                FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio = listFecetFlujoProrrogaOficios.get(0);
                FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToFlujoProrrogaOficioConsultaDTO(fecetFlujoProrrogaOficio);
                prorrogaOficioConsulta.setFlujoProrrogaOficioConsultaDTO(flujoProrrogaOficioConsultaDTO);
            }

        }
        getHelper().getOficioConsultaSeleccionado().setListProrrogaOficioConsulta(listProrrogaOficioConsulta);
        RequestContext.getCurrentInstance().execute("PF('dlgOficiosDeRequerimientos').show();");
    }

    public void buscarAnexosOficios() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getHelper().getOficioConsultaSeleccionado().getIdOficio());
        getHelper().getOficioConsultaSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
        RequestContext.getCurrentInstance().execute("PF('dlgAnexosOficios').show();");

    }

    public void buscarOficiosDependientes() {
        List<FecetOficio> listOficioDependiente = getConsultarReimprimirDocumentosService()
                .buscarOficioDependientePorOficio(getHelper().getOficioConsultaSeleccionado().getIdOficio());
        getHelper().getOficioConsultaSeleccionado().setListOficioDependientes(getConsultarReimprimirDocumentosHelper()
                .convertToOficioConsultaDTO(listOficioDependiente, getOrdenConsultaSeleccionada()));
    }

    public void buscarAnexosOficioDependientes() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getHelper().getOficioConsultaSeleccionado().getIdOficio());
        getHelper().getOficioConsultaDependeienteSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
    }

    public void buscarDocPruebasAlegatosOficio() {
        List<FecetAlegatoOficio> listFecetAlegatoOficio = getConsultarReimprimirDocumentosService()
                .buscarAlegatoOficioPorPromocionOficio(
                        getHelper().getPromocionOficioConsultaSeleccionada().getIdPromocionOficio());
        getHelper().getPromocionOficioConsultaSeleccionada().setListFecetAlegatoOficio(listFecetAlegatoOficio);
    }

    public void getAnexosSolicitudContribuyente() {
        if (getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            getAnexosProrrogaOrden();
        } else {
            getAnexosPruebasPericiales();
        }
    }

    public void getAnexosProrrogaOrden() {
        getSolHelper().setListaAnexosSolicitudContribuyenteHistorico(
                getSolHelper().llenaAnexoSolicitudContribuyenteProrroga(getSeguimientoOrdenesService()
                        .getAnexosProrrogaOrdenHistorico(getSolHelper().getSolicitudContribuyenteVOSeleccionada()
                                .getProrrogaOrden().getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden())));
    }

    public void getAnexosPruebasPericiales() {
        getSolHelper().setListaAnexosSolicitudContribuyenteHistorico(
                getSolHelper().llenaAnexoSolicitudContribuyentePruebasPericiales(getSeguimientoOrdenesService()
                        .getAnexosPruebasPericialesHistorico(getSolHelper().getSolicitudContribuyenteVOSeleccionada()
                                .getPruebasPericiales().getFlujoPruebasPericiales().getIdFlujoPruebasPericiales())));
    }

    public void buscarDocProrrogaOficio() {
        List<FecetDocProrrogaOficio> listFecetDocProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarDocProrrogaOficioPorProrroga(
                        getHelper().getProrrogaOficioConsultaSeleccionada().getIdProrroga());
        getHelper().getProrrogaOficioConsultaSeleccionada().setListFecetDocProrrogaOficio(listFecetDocProrrogaOficio);
    }

    public void buscarDocAnexoResolProrrogaOficio() {
        BigDecimal idFlujoProrrogaOficio = getHelper().getFlujoProrrogaOficioConsultaDTOSeleccionada()
                .getIdFlujoProrrogaOficio();
        List<FecetAnexosProrrogaOficio> listFecetAnexosProrrogasOficios = getConsultarReimprimirDocumentosService()
                .buscarAnexosProrrogaOficioPorFlujoProrrogaOficio(idFlujoProrrogaOficio);
        List<AnexoProrrogaOficioConsultaDTO> listAnexosProrrogasOficiosConsulta = getConsultarReimprimirDocumentosHelper()
                .convertToAnexoProrrogaOficioConsultaDTO(listFecetAnexosProrrogasOficios);
        String nombreAuditor = "";
        BigDecimal idAuditor = getHelper().getProrrogaOficioConsultaSeleccionada().getIdAuditor() != null
                ? getHelper().getProrrogaOficioConsultaSeleccionada().getIdAuditor()
                : getOrdenConsultaSeleccionada().getIdAuditor();
        if (idAuditor != null) {
            EmpleadoDTO empleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(idAuditor);
            nombreAuditor = empleado != null ? empleado.getNombre() : "";
        }
        for (AnexoProrrogaOficioConsultaDTO anexoProrrogaOficioConsultaDTO : listAnexosProrrogasOficiosConsulta) {
            anexoProrrogaOficioConsultaDTO.setPresentadoPor(nombreAuditor);
        }
        getHelper().getFlujoProrrogaOficioConsultaDTOSeleccionada()
                .setListAnexosProrrogaOficios(listAnexosProrrogasOficiosConsulta);
    }

    private void llenarRfcANombreOficio(List<FecetOficio> listaOficio) {
        if (listaOficio != null) {
            for (FecetOficio oficio : listaOficio) {
                TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum
                        .parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());

                if (oficioEnum != null) {
                    StringBuilder nombre = new StringBuilder();

                    if (oficio.getIdOficioPrincipal() != null) {
                        FecetOficio oficioPrincipal = getSeguimientoOrdenesService().obtenerOficioById(oficio);
                        if (oficioPrincipal != null) {
                            nombre.append(oficio.getFecetTipoOficio().getNombre());
                            nombre.append(llenarNombreOficio(oficioPrincipal));
                        }
                    } else {
                        nombre.append(llenarNombreOficioCompulsa(oficio));
                    }
                    oficio.getFecetTipoOficio().setNombre(nombre.toString());
                }
            }
        }
    }

    public StreamedContent getArchivoDescargaOficio() {
        return getArchivoGenerico(getHelper().getOficioSeleccionado().getRutaArchivo(),
                getHelper().getOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAcuse() {
        return getDescargaArchivo(getHelper().getOficioSeleccionado().getRutaAcuseNyv(),
                getHelper().getOficioSeleccionado().getNombreAcuseNyv());
    }

    public StreamedContent getArchivoDescargaDocumentacionSolicitudContribuyente() {
        return getDescargaArchivo(getSolHelper().getDocumentacionSolicitudContribuyenteSeleccionado().getRutaArchivo(),
                getSolHelper().getDocumentacionSolicitudContribuyenteSeleccionado().getNombreArchivo());
    }

    public void getDocumentacionSolicitudContribuyente() {
        if (getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            getDocumentacionProrrogaContribuyente();
        } else {
            getDocumentacionPruebaPericialContribuyente();
        }
    }

    public void getDocumentacionProrrogaContribuyente() {
        getSolHelper().setListaDocumentosProrroga(getSeguimientoOrdenesService().getDocumentacionProrrogaContribuyente(
                getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getIdProrrogaOrden()));
        getSolHelper().setListaSolicitudContribuyenteDocVO(getSolHelper()
                .llenaListaSolicitudContribuyenteDocVO(getSolHelper().getListaDocumentosProrroga(), null));
    }

    public void getDocumentacionPruebaPericialContribuyente() {
        getSolHelper().setListaDocumentosPruebaPericial(
                getSeguimientoOrdenesService().getDocumentacionPruebaPericialContribuyente(getSolHelper()
                        .getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getIdPruebasPericiales()));
        getSolHelper().setListaSolicitudContribuyenteDocVO(getSolHelper().llenaListaSolicitudContribuyenteDocVO(null,
                getSolHelper().getListaDocumentosPruebaPericial()));
    }

    public StreamedContent getArchivoDescargaResolucionSolicitudContribuyente() {
        if (getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            return getArchivoDescargaResolucionProrrogaOrden();
        } else {
            return getArchivoGenerico(
                    getSolHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getRutaResolucion(),
                    getSolHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales()
                    .getNombreResolucion());
        }

    }

    public StreamedContent getArchivoDescargaResolucionProrrogaOrden() {
        return getArchivoGenerico(
                getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getRutaResolucion(),
                getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getNombreResolucion());
    }

    public StreamedContent getArchivoDescargaAnexoSolicitudContribuyente() {
        if (getSolHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            return getArchivoDescargaAnexoProrrogaOrden();
        } else {
            return getArchivoGenericoAnexoProrrogaOrden(
                    getSolHelper().getSolicitudContribuyenteAnexoHistoricoVOSeleccionada()
                    .getFecetAnexoPruebasPericiales().getRutaArchivo(),
                    getSolHelper().getSolicitudContribuyenteAnexoHistoricoVOSeleccionada()
                    .getFecetAnexoPruebasPericiales().getNombreArchivo());
        }

    }

    public StreamedContent getArchivoDescargaAnexoProrrogaOrden() {
        return getArchivoGenericoAnexoProrrogaOrden(
                getSolHelper().getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexosProrrogaOrden()
                .getRutaArchivo(),
                getSolHelper().getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexosProrrogaOrden()
                .getNombreArchivo());
    }

    private StreamedContent getArchivoGenericoAnexoProrrogaOrden(final String path, final String nombre) {
        return getDescargaArchivo(path, nombre);
    }

    private String llenarNombreOficio(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder();

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ")
                    .append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ")
                    .append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    private String llenarNombreOficioCompulsa(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder(oficio.getFecetTipoOficio().getNombre());

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    public void regresa() {
        String urlRegreso = (String) getSession().getAttribute("urlRegreso");
        getSession().removeAttribute("urlRegreso");
        try {
            if (!urlRegreso.isEmpty()) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();

                externalContext.redirect(origRequest.getContextPath() + urlRegreso);
            }
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
    }

    private String llenarRfcANombreOficio(FecetOficio oficio) {
        String sb = oficio.getFecetTipoOficio().getNombre();

        TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum
                .parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());

        if (oficioEnum != null) {
            StringBuilder nombre = new StringBuilder();
            if (oficio.getIdOficioPrincipal() != null) {
                FecetOficio oficioPrincipal = getConsultarReimprimirDocumentosService().obtenerOficioById(oficio);
                if (oficioPrincipal != null) {
                    nombre.append(oficio.getFecetTipoOficio().getNombre());
                    nombre.append(llenarNombreOficio(oficioPrincipal));
                }
            } else {
                nombre.append(llenarNombreOficioCompulsa(oficio));
            }
            return nombre.toString();
        }
        return sb;
    }

    public StreamedContent getArchivoDescargar() {
        return getArchivoGenerico(getRutaArchivo(), getNombreArchivo());
    }

    public void getOficiosDependientesEstatusOficioNotificadoContribuyente() {
        getHelper().setListaOficiosDependientes(getSeguimientoOrdenesService()
                .getOficiosDependientesFirmados(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosOficio() {
        getHelper().setListaAnexosOficio(
                getSeguimientoOrdenesService().getAnexosOficio(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosOficioRechazo() {
        getHelper().setListaOficioAnexosRechazo(getSeguimientoOrdenesService()
                .getAnexosOficioRechazo(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getOficiosDependientesEstatusOficioRechazado() {
        getHelper().setListaOficiosDependientes(getSeguimientoOrdenesService().getOficiosDependientesByIdEstatus(
                getHelper().getOficioSeleccionado().getIdOficio(), Constantes.ESTATUS_OFICIO_RECHAZADO));
    }

    public void getAnexosRechazoOficio() {
        getHelper().setListaAnexosRechazoOficio(getSeguimientoOrdenesService().getAnexosRechazoOficio(
                getHelper().getOficioSeleccionado().getFecetRechazoOficio().getIdRechazoOficio()));
    }

    public StreamedContent getArchivoDescargaAnexoRechazoOficio() {
        return getArchivoGenerico(getHelper().getAnexoRechazoOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoRechazoOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAnexoOficio() {
        return getDescargaArchivo(getHelper().getAnexoOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaOfDependiente() {
        return getArchivoGenerico(getHelper().getOfDependienteSeleccionado().getRutaArchivo(),
                getHelper().getOfDependienteSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaOfRechazado() {
        return getArchivoGenerico(getHelper().getOfRechazadoSeleccionado().getRutaArchivo(),
                getHelper().getOfRechazadoSeleccionado().getNombreArchivo());
    }

}
