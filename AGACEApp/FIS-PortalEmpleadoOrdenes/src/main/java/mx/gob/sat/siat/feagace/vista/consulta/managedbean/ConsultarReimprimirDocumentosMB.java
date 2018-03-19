package mx.gob.sat.siat.feagace.vista.consulta.managedbean;

import static mx.gob.sat.siat.feagace.negocio.ordenes.constants.FirmadoOrdenesConstants.EXTENSION_ARCHIVO_ORDEN_PDF;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.consulta.attributes.ReimprimirDoctosAttributesAbstract;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;

@ManagedBean(name = "consultarReimprimirDocumentosMB")
@ViewScoped
public class ConsultarReimprimirDocumentosMB extends ReimprimirDoctosAttributesAbstract {

    private static final long serialVersionUID = 3195300707897808603L;

    @PostConstruct
    public void init() throws NoExisteEmpleadoException, EmpleadoServiceException {
        try {
            setAgaceOrden(new AgaceOrden());
            getAgaceOrden().setFecetContribuyente(new FecetContribuyente());
            setVisiblePnlConsultarDocBusqueda(true);

            if (getSession().getAttribute(AGACE_ORDEN_BUSQ) != null) {
                setAgaceOrden((AgaceOrden) getSession().getAttribute(AGACE_ORDEN_BUSQ));
                getSession().removeAttribute(AGACE_ORDEN_BUSQ);

                buscarOrdenes();
            }
        } catch (Exception e) {
            addErrorMessage("Error al cargar informaci&oacute;n");
            logger.error("Error al cargar informacion", e);
        }

    }

    private EmpleadoDTO obtnerEmpleado() throws NoExisteEmpleadoException, EmpleadoServiceException {
        return getEmpleadoService().getEmpleadoCompleto(getRFCSession());
    }

    public void redirecciona() throws IOException {

        getSession().setAttribute("origenConsulta", "consultarReimprimirDocs");
        getSession().setAttribute("ordenConsultaSeleccionada", getOrdenConsultaSeleccionada());
        getSession().setAttribute("userProfile", super.getUserProfile());

        getSession().setAttribute("urlRegreso", "/faces/consultarDocumentos/consultarReimprimirDocBusqueda.jsf");
        getSession().setAttribute(AGACE_ORDEN_BUSQ, getAgaceOrden());

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public void buscarOrdenes() throws NoExisteEmpleadoException, EmpleadoServiceException {
        setListOrden(buscarOrdenesService(getAgaceOrden()));
        if (getConsultarReimprimirDocumentosRules().validarRegistroOrdenesEmpty(getListOrden())) {
            setRenderTablaOrdenesGrl(false);
            addErrorMessage(INEXISTENCIA_REGISTROS);
        } else {
            setListOrdenConsulta(getConsultarReimprimirDocumentosHelper().convertToOrdenConsulta(getListOrden(), getAgaceOrden().getFecetContribuyente().getRfc()));
            setRenderTablaOrdenesGrl(getListOrdenConsulta() != null && !getListOrdenConsulta().isEmpty());
        }
    }

    public void cargaDocumentosOrdenOficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        orden.setNumeroOrden(getOrdenConsultaSeleccionada().getNumeroOrden());

        setExpediente(getSeguimientoOrdenesService().obtenerExpedienteOrden(orden));
        setListaOficiosAdmin(getSeguimientoOrdenesService().getOficiosAdministrables(getOrdenConsultaSeleccionada().getIdOrden()));
        RequestContext.getCurrentInstance().execute("PF('docDialog').show();");
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        AgaceOrden orden = new AgaceOrden();
        orden.setFecetContribuyente(new FecetContribuyente());
        orden.getFecetContribuyente().setRfc(getOrdenConsultaSeleccionada().getRfc());
        orden.setIdRegistroPropuesta(getOrdenConsultaSeleccionada().getIdRegistro());

        final String nombre = getOrdenConsultaSeleccionada().getNumeroOrden() + EXTENSION_ARCHIVO_ORDEN_PDF;
        final String path = RutaArchivosUtilOrdenes.armarRutaDocOrden(orden) + nombre;

        return getDescargaArchivo(path, nombre);
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        final String nombre = getDocOficioSeleccionado().getNombreArchivo();
        final String path = getDocOficioSeleccionado().getRutaArchivo();

        return getDescargaArchivo(path, nombre);
    }

    public void limpiarOficiosAdmin() {
        setListaOficiosAdmin(new ArrayList<FecetOficio>());
        setExpediente(new FecetDocOrden());
    }

    public void buscarDocRelacionada() {
        BigDecimal idOrden = getOrdenConsultaSeleccionada().getIdOrden();
        // Informacion del contribuyente para el panel principal.
        setContribuyente(getConsultarReimprimirDocumentosService().buscarContribuyentePorID(getOrdenConsultaSeleccionada().getIdContribuyente()));
        // Obtiene informacion del firmante de la orden.
        EmpleadoDTO fececEmpleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(getOrdenConsultaSeleccionada().getIdFirmante());
        getOrdenConsultaSeleccionada().setFirmante(fececEmpleado);
        // Obtener informacion de promociones.
        List<FecetPromocion> listFecetPromocion = getConsultarReimprimirDocumentosService().buscarPromocionPorOrden(idOrden);
        List<PromocionConsultaDTO> listPromocionConsultaDTO = getConsultarReimprimirDocumentosHelper().convertToPromocionConsulta(listFecetPromocion,
                getOrdenConsultaSeleccionada());
        getOrdenConsultaSeleccionada().setListPromocionConsultaDTO(listPromocionConsultaDTO);
        // Obtener informacion de oficios.
        List<FecetOficio> listFecetOficio = getConsultarReimprimirDocumentosService().buscarOficioPorOrden(idOrden);
        List<OficioConsultaDTO> listOficioConsultaDTO = getConsultarReimprimirDocumentosHelper().convertToOficioConsultaDTO(listFecetOficio,
                getOrdenConsultaSeleccionada());
        getOrdenConsultaSeleccionada().setListOficioConsultaDTO(listOficioConsultaDTO);
        buscarOficiosDeRequerimiento(getOrdenConsultaSeleccionada().getListOficioConsultaDTO());
        // Obtener informacon de prorroga.
        List<FecetProrrogaOrden> listProrrogaOrden = getConsultarReimprimirDocumentosService().buscarProrrogaPorOrden(idOrden);
        List<ProrrogaOrdenConsultaDTO> listProrrogaOrdenConsulta = getConsultarReimprimirDocumentosHelper().convertoToProrrogaOrdenConsulta(listProrrogaOrden,
                getOrdenConsultaSeleccionada());
        for (ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaDTO : listProrrogaOrdenConsulta) {
            BigDecimal idOficioProrroga = prorrogaOrdenConsultaDTO.getIdProrroga();
            List<FecetFlujoProrrogaOrden> listFecetFlujoProrrogaOrden = getConsultarReimprimirDocumentosService()
                    .buscarFlujoProrrogaOrdenPorProrroga(idOficioProrroga);
            if (!listFecetFlujoProrrogaOrden.isEmpty()) {
                FecetFlujoProrrogaOrden fecetFlujoProrrogaOrden = listFecetFlujoProrrogaOrden.get(0);
                FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToFlujoProrrogaOrdenConsultaDTO(fecetFlujoProrrogaOrden);
                prorrogaOrdenConsultaDTO.setFlujoProrrogaOrdenConsultaDTO(flujoProrrogaOrdenConsultaDTO);
            }
        }
        getOrdenConsultaSeleccionada().setListProrrogaOrdenConsultaDTO(listProrrogaOrdenConsulta);
        ocultarPaneles();
        setVisiblePnlConsultarDocRelacionada(true);

    }

    public StreamedContent getArchivoDescargable() {
        StreamedContent content = null;
        try {
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error("No se pudo descargar el archivo. ", exception);
            addErrorMessage("No se encontro el documento seleccionado");
        }
        return content;
    }

    /* ELIMINAR */
    public void buscarDocPruebasAlegatos() {
        List<FecetAlegato> listFecetAlegato = getConsultarReimprimirDocumentosService()
                .buscarAlegatosPorPromocion(getPromocionConsultaSeleccionada().getIdPromocion());
        getPromocionConsultaSeleccionada().setListFecetAlegato(listFecetAlegato);
        ocultarPaneles();
        setVisiblePnlDocPruebasAlegatos(true);

    }

    public void buscarDocPruebasAlegatosOficio() {
        List<FecetAlegatoOficio> listFecetAlegatoOficio = getConsultarReimprimirDocumentosService()
                .buscarAlegatoOficioPorPromocionOficio(getPromocionOficioConsultaSeleccionada().getIdPromocionOficio());
        getPromocionOficioConsultaSeleccionada().setListFecetAlegatoOficio(listFecetAlegatoOficio);
        ocultarPaneles();
        setVisiblePnlDocOficioPruebasAlegatos(true);

    }

    public void buscarOficiosDependientes() {
        List<FecetOficio> listOficioDependiente = getConsultarReimprimirDocumentosService()
                .buscarOficioDependientePorOficio(getOficioConsultaSeleccionado().getIdOficio());
        getOficioConsultaSeleccionado().setListOficioDependientes(
                getConsultarReimprimirDocumentosHelper().convertToOficioConsultaDTO(listOficioDependiente, getOrdenConsultaSeleccionada()));
        ocultarPaneles();
        setVisiblePnlOficiosDependientes(true);
    }

    public void buscarAnexosOficios() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getOficioConsultaSeleccionado().getIdOficio());
        getOficioConsultaSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
        ocultarPaneles();
        setVisiblePnlAnexosOficios(true);
    }

    public void buscarAnexosOficioDependientes() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getOficioConsultaSeleccionado().getIdOficio());
        getOficioConsultaDependeienteSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
        ocultarPaneles();
        setVisiblePnlAnexosOficiosDependientes(true);
    }

    public void buscarDocProrrogaOrden() {
        List<FecetDocProrrogaOrden> listFecetDocProrrogaOrden = getConsultarReimprimirDocumentosService()
                .buscarDocProrrogaOrdenPorProrroga(getProrrogaOrdenConsultaSeleccionada().getIdProrroga());
        getProrrogaOrdenConsultaSeleccionada().setListFecetDocProrrogaOrden(listFecetDocProrrogaOrden);
        ocultarPaneles();
        setVisiblePnlDocProrrogaOrden(true);

    }

    public void buscarDocProrrogaOficio() {
        List<FecetDocProrrogaOficio> listFecetDocProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarDocProrrogaOficioPorProrroga(getProrrogaOficioConsultaSeleccionada().getIdProrroga());
        getProrrogaOficioConsultaSeleccionada().setListFecetDocProrrogaOficio(listFecetDocProrrogaOficio);

        ocultarPaneles();
        setVisiblePnlDocProrrogaOficio(true);
    }

    private void buscarOficiosDeRequerimiento(List<OficioConsultaDTO> listOficioConsultaDTO) {
        AgaceOrden orden = new AgaceOrden();
        orden.setFeceaMetodo(new FececMetodo());
        orden.getFeceaMetodo().setIdMetodo(getOrdenConsultaSeleccionada().getIdMetodo());
        for (OficioConsultaDTO oficioConsultaDTO : listOficioConsultaDTO) {
            if (!getConsultarReimprimirDocumentosRules().validaMetodo(orden.getFeceaMetodo().getIdMetodo(), oficioConsultaDTO.getIdTipoOficio())) {
                List<FecetPromocionOficio> listFecetPromocionOficio = getConsultarReimprimirDocumentosService()
                        .buscarPromocionOficioPorOficio(oficioConsultaDTO.getIdOficio());
                List<PromocionOficioConsultaDTO> listPromocionOficioConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToPromocionOficioConsultaDTO(listFecetPromocionOficio, oficioConsultaDTO.getIdTipoOficio(), getOrdenConsultaSeleccionada());
                oficioConsultaDTO.setListPromocionOficioConsulta(listPromocionOficioConsultaDTO);
                oficioConsultaDTO.setOficioConRequerimiento(true);
            } else {
                oficioConsultaDTO.setOficioConRequerimiento(false);
            }
        }
    }

    public void buscarProrrogaOficio() {
        List<FecetProrrogaOficio> listFecetProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarProrrogaOficioPorOficio(getOficioConsultaSeleccionado().getIdOficio());
        List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta = getConsultarReimprimirDocumentosHelper()
                .convertToProrrogaOficioConsultaDTO(listFecetProrrogaOficio, getOficioConsultaSeleccionado(), getOrdenConsultaSeleccionada());
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
        getOficioConsultaSeleccionado().setListProrrogaOficioConsulta(listProrrogaOficioConsulta);
        ocultarPaneles();
        setVisiblePnlOficiosRequerimiento(true);
    }

    public void buscarDocAnexoResolProrrogaOrden() {
        BigDecimal idFlujoProrrogaOrden = getFlujoProrrogaOrdenConsultaDTOSeleccionada().getIdFlujoProrrogaOrden();
        List<FecetAnexosProrrogaOrden> listFecetAnexosProrrogaOrden = getConsultarReimprimirDocumentosService()
                .buscarAnexosProrrogaOrdenPorFlujoProrrogaOrden(idFlujoProrrogaOrden);
        List<AnexoProrrogaOrdenConsultaDTO> listAnexoProrrogaOrdenConsultaDTO = getConsultarReimprimirDocumentosHelper()
                .convertToAnexoProrrogaOrdenConsultaDTO(listFecetAnexosProrrogaOrden);
        BigDecimal idAuditor = getProrrogaOrdenConsultaSeleccionada().getIdAuditor() != null ? getProrrogaOrdenConsultaSeleccionada().getIdAuditor()
                : getOrdenConsultaSeleccionada().getIdAuditor();
        String nombreAuditor = null;
        if (idAuditor != null) {
            EmpleadoDTO empleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(idAuditor);
            nombreAuditor = empleado != null ? empleado.getNombre() : "";
        }
        for (AnexoProrrogaOrdenConsultaDTO anexoProrrogaOrdenConsultaDTO : listAnexoProrrogaOrdenConsultaDTO) {
            anexoProrrogaOrdenConsultaDTO.setPresentadoPor(nombreAuditor);
        }
        getFlujoProrrogaOrdenConsultaDTOSeleccionada().setListAnexosProrrogaOrden(listAnexoProrrogaOrdenConsultaDTO);
        ocultarPaneles();
        setVisiblepnlDocAnexosResolProrrogaOrden(true);
    }

    public void buscarDocAnexoResolProrrogaOficio() {
        BigDecimal idFlujoProrrogaOficio = getFlujoProrrogaOficioConsultaDTOSeleccionada().getIdFlujoProrrogaOficio();
        List<FecetAnexosProrrogaOficio> listFecetAnexosProrrogasOficios = getConsultarReimprimirDocumentosService()
                .buscarAnexosProrrogaOficioPorFlujoProrrogaOficio(idFlujoProrrogaOficio);
        List<AnexoProrrogaOficioConsultaDTO> listAnexosProrrogasOficiosConsulta = getConsultarReimprimirDocumentosHelper()
                .convertToAnexoProrrogaOficioConsultaDTO(listFecetAnexosProrrogasOficios);
        String nombreAuditor = "";
        BigDecimal idAuditor = getProrrogaOficioConsultaSeleccionada().getIdAuditor() != null ? getProrrogaOficioConsultaSeleccionada().getIdAuditor()
                : getOrdenConsultaSeleccionada().getIdAuditor();
        if (idAuditor != null) {
            EmpleadoDTO empleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(idAuditor);
            nombreAuditor = empleado != null ? empleado.getNombre() : "";
        }
        for (AnexoProrrogaOficioConsultaDTO anexoProrrogaOficioConsultaDTO : listAnexosProrrogasOficiosConsulta) {
            anexoProrrogaOficioConsultaDTO.setPresentadoPor(nombreAuditor);
        }
        getFlujoProrrogaOficioConsultaDTOSeleccionada().setListAnexosProrrogaOficios(listAnexosProrrogasOficiosConsulta);
        ocultarPaneles();
        setVisiblepnlDocAnexosResolProrrogaOficio(true);
    }

    public void limpiar() {
        setVisiblePnlConsultarDocBusqueda(true);
        setRenderTablaOrdenesGrl(false);
        getAgaceOrden().setNumeroOrden(null);
        getAgaceOrden().setIdRegistroPropuesta(null);
        getAgaceOrden().getFecetContribuyente().setRfc(null);
        setOrdenConsultaSeleccionada(null);
        setListOrden(null);

    }

    public void ocultarPaneles() {
        setVisiblePnlDocPruebasAlegatos(false);
        setVisiblePnlConsultarDocRelacionada(false);
        setVisiblePnlConsultarDocBusqueda(false);
        setVisiblePnlOficiosDependientes(false);
        setVisiblePnlAnexosOficiosDependientes(false);
        setVisiblePnlAnexosOficios(false);
        setVisiblePnlOficiosRequerimiento(false);
        setVisiblePnlDocOficioPruebasAlegatos(false);
        setVisiblePnlDocProrrogaOrden(false);
        setVisiblePnlDocProrrogaOficio(false);
        setVisiblepnlDocAnexosResolProrrogaOrden(false);
        setVisiblepnlDocAnexosResolProrrogaOficio(false);
    }

    public List<AgaceOrden> buscarOrdenesService(AgaceOrden agaceOrden) throws NoExisteEmpleadoException, EmpleadoServiceException {
        List<AgaceOrden> listOrdenTmp = new ArrayList<AgaceOrden>();

        String rfcBusqueda = getAgaceOrden().getFecetContribuyente().getRfc();
        String numOrden = getAgaceOrden().getNumeroOrden();
        String idRegistro = getAgaceOrden().getIdRegistroPropuesta();

        if (StringUtils.isNotBlank(rfcBusqueda) || StringUtils.isNotBlank(numOrden) || StringUtils.isNotBlank(idRegistro)) {
            getAgaceOrden().setNumeroOrden(StringUtils.upperCase(getAgaceOrden().getNumeroOrden()));
            getAgaceOrden().getFecetContribuyente().setRfc(StringUtils.upperCase(rfcBusqueda));

            EmpleadoDTO empleadoDTO = obtnerEmpleado();
            Integer idArace;

            if (empleadoDTO != null && empleadoDTO.getDetalleEmpleado() != null && !empleadoDTO.getDetalleEmpleado().isEmpty()) {
                DetalleEmpleadoDTO detalleEmpl = empleadoDTO.getDetalleEmpleado().get(0);
                idArace = detalleEmpl.getCentral().getIdArace();
                if (idArace != null && idArace > 0) {
                    listOrdenTmp.addAll(getConsultarReimprimirDocumentosService().buscarOrdenPorCriterio(numOrden, idRegistro, rfcBusqueda,
                            empleadoDTO.getIdEmpleado().abs().toString(), idArace));
                } else {
                    listOrdenTmp.addAll(getConsultarReimprimirDocumentosService().buscarOrdenPorCriterio(numOrden, idRegistro, rfcBusqueda,
                            empleadoDTO.getIdEmpleado().abs().toString(), null));
                }
            }

        }

        listOrdenTmp = getConsultarReimprimirDocumentosHelper().eliminarOrdenesRepetidas(listOrdenTmp);
        return listOrdenTmp;
    }

    public StreamedContent getDescargarExpedienteOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarOrdenInterno(orden);
        return getArchivoSeleccionDescarga();
    }

    public StreamedContent getDescargarExpedienteOficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());

        if (getOficioConsultaSeleccionado() != null && getOficioConsultaSeleccionado()
                .getIdTipoOficio().equals(new BigDecimal(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getIdTipoOficio()))) {
            getPistaAuditoriaDescargaDocumentosService().consultarOficioCompulsaInterno(orden);
        } else {
            getPistaAuditoriaDescargaDocumentosService().consultarOficioExterno(orden);
        }

        return getArchivoSeleccionDescarga();
    }

    public StreamedContent getDescargarExpedienteProrrogaOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOrdenInterno(orden);
        return getArchivoSeleccionDescarga();
    }

    public StreamedContent getDescargarExpedienteProrrogaOficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOficioInterno(orden);
        return getArchivoSeleccionDescarga();
    }

    public StreamedContent getDescargarExpedientePromocionOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarPromocionOrdenInterno(orden);
        return getArchivoSeleccionDescarga();
    }

    public StreamedContent getDescargarExpedientePromocionficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarPromocionOficioInterno(orden);
        return getArchivoSeleccionDescarga();
    }

}
