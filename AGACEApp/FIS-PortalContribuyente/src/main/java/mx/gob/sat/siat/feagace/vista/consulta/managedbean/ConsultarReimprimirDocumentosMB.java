package mx.gob.sat.siat.feagace.vista.consulta.managedbean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.excepcion.BusinessException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.consulta.common.ReimprimirDocumentosAbstractSubMB;
import mx.gob.sat.siat.feagace.vista.consulta.helper.ReimprimirDocumentosDTOHelper;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "consultarReimprimirDocumentosMB")
@ViewScoped
public class ConsultarReimprimirDocumentosMB extends ReimprimirDocumentosAbstractSubMB {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
        setReimprimirDocumentosDTOHelper(new ReimprimirDocumentosDTOHelper());
        getReimprimirDocumentosDTOHelper().setAgaceOrden(new AgaceOrden());
        getReimprimirDocumentosDTOHelper().getAgaceOrden().setFecetContribuyente(new FecetContribuyente());
        setVisiblePnlConsultarDocBusqueda(true);
        String rfc = getRFCSession();
        getReimprimirDocumentosDTOHelper().getAgaceOrden().getFecetContribuyente().setRfc(rfc);
        buscarOrdenes();
    }

    public void buscarOrdenes() {
        try {
            getReimprimirDocumentosDTOHelper().setListOrden(buscarOrdenesService(getReimprimirDocumentosDTOHelper().getAgaceOrden()));
            getReimprimirDocumentosDTOHelper().setRepresentante(getConsultarReimprimirDocumentosService()
                    .buscarContribuyentePorRfc(getReimprimirDocumentosDTOHelper().getAgaceOrden().getFecetContribuyente().getRfc()));
            getConsultarReimprimirDocumentosRules().validarRegistroOrdenes(getReimprimirDocumentosDTOHelper().getListOrden());
            getReimprimirDocumentosDTOHelper().setListOrdenConsulta(getConsultarReimprimirDocumentosHelper().convertToOrdenConsulta(
                    getReimprimirDocumentosDTOHelper().getListOrden(), getReimprimirDocumentosDTOHelper().getAgaceOrden().getFecetContribuyente().getRfc()));
            setRenderTablaOrdenesGrl((getReimprimirDocumentosDTOHelper().getListOrdenConsulta() != null
                    && !getReimprimirDocumentosDTOHelper().getListOrdenConsulta().isEmpty()));
        } catch (BusinessException e) {
            setRenderTablaOrdenesGrl(false);
            addErrorMessage(null, e.getMessage());
        }
    }

    public void buscarDocRelacionada() {
        // Carga el representante legal y agente aduanal
        cargarColaboradores();
        BigDecimal idOrden = getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden();
        // Obtiene informacion del firmante de la orden.
        EmpleadoDTO fececEmpleado = getConsultarReimprimirDocumentosService()
                .buscarEmpleadoPorIdEmpleado(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdFirmante());
        getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().setFirmante(fececEmpleado);
        // se llenan datos del contribuyente
        getReimprimirDocumentosDTOHelper().setContribuyente(getConsultarReimprimirDocumentosService()
                .buscarContribuyentePorID(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdContribuyente()));
        // Obtener informacion de promociones.
        List<FecetPromocion> listFecetPromocion = getConsultarReimprimirDocumentosService().buscarPromocionPorOrden(idOrden);
        List<PromocionConsultaDTO> listPromocionConsultaDTO = getConsultarReimprimirDocumentosHelper().convertToPromocionConsulta(listFecetPromocion,
                getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada());
        getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().setListPromocionConsultaDTO(listPromocionConsultaDTO);
        // Obtener informacion de oficios.
        List<FecetOficio> listFecetOficio = getConsultarReimprimirDocumentosService().buscarOficioPorOrden(idOrden);

        List<OficioConsultaDTO> listOficioConsultaDTO = new ArrayList<OficioConsultaDTO>();

        if (!listFecetOficio.isEmpty()) {
            listFecetOficio = removerOficiosCompulsas(listFecetOficio);
            getConsultarReimprimirDocumentosService().filtrarOficios(listFecetOficio);
        }

        if (getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getNumeroOrden().contains("ECOM")) {
            FecetCompulsas compulsa = getConsultarReimprimirDocumentosService().obtenerCompulsa(idOrden);
            List<FecetOficio> listFecetOficioOrdenAuditada = getConsultarReimprimirDocumentosService().buscarOficioPorOrden(compulsa.getIdOrdenAuditada());
            if (listFecetOficioOrdenAuditada != null && !listFecetOficioOrdenAuditada.isEmpty()) {
                for (FecetOficio oficioCompulsa : listFecetOficioOrdenAuditada) {
                    if (oficioCompulsa.getIdOficio().equals(compulsa.getIdOficio())) {
                        listFecetOficio.add(oficioCompulsa);
                        break;
                    }
                }
            }
        }

        if (listFecetOficio != null) {
            for (FecetOficio oficio : listFecetOficio) {
                oficio.getFecetTipoOficio().setNombre(llenarRfcANombreOficio(oficio));
            }
            listOficioConsultaDTO = getConsultarReimprimirDocumentosHelper().convertToOficioConsultaDTO(listFecetOficio,
                    getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada(), null);
        }

        getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().setListOficioConsultaDTO(listOficioConsultaDTO);
        for (OficioConsultaDTO oficio : getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getListOficioConsultaDTO()) {
            FecetOficio fecetOficio = new FecetOficio();
            fecetOficio.setIdOficioPrincipal(oficio.getIdOficio());
            fecetOficio = getConsultarReimprimirDocumentosService().obtenerOficioById(fecetOficio);
            if (fecetOficio.getIdNyV() != 0) {
                getConsultarReimprimirDocumentosService().asignarFechaOficio(fecetOficio);
                oficio.setFechaNotificacion(fecetOficio.getFecetDetalleNyV().getFecNotificacionNyV());
                oficio.setRutaAcuseNyv(fecetOficio.getFecetDetalleNyV().getRutaAcuseNyv());
            }
            if (oficio.getRutaAcuseNyv() != null && !oficio.getRutaAcuseNyv().trim().equals("")) {
                oficio.setNombreAcuseNyv(UtileriasMapperDao.getNameFileFromPath(oficio.getRutaAcuseNyv()));
            }
        }

        buscarOficiosDeRequerimiento(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getListOficioConsultaDTO());
        removerOficiosRechazados(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getListOficioConsultaDTO());
        // Obtener informacon de prorroga.
        List<FecetProrrogaOrden> listProrrogaOrden = getConsultarReimprimirDocumentosService().buscarProrrogaPorOrden(idOrden);
        List<ProrrogaOrdenConsultaDTO> listProrrogaOrdenConsulta = getConsultarReimprimirDocumentosHelper().convertoToProrrogaOrdenConsulta(listProrrogaOrden,
                getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada());
        int indiceProrrogaOrden = 0;
        for (ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaDTO : listProrrogaOrdenConsulta) {
            BigDecimal idOficioProrroga = prorrogaOrdenConsultaDTO.getIdProrroga();
            List<FecetFlujoProrrogaOrden> listFecetFlujoProrrogaOrden = getConsultarReimprimirDocumentosService()
                    .buscarFlujoProrrogaOrdenPorProrroga(idOficioProrroga);
            if (!listFecetFlujoProrrogaOrden.isEmpty()) {
                FecetFlujoProrrogaOrden fecetFlujoProrrogaOrden = listFecetFlujoProrrogaOrden.get(0);
                FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToFlujoProrrogaOrdenConsultaDTO(fecetFlujoProrrogaOrden);
                if (flujoProrrogaOrdenConsultaDTO != null) {
                    flujoProrrogaOrdenConsultaDTO.setMotivo(fecetFlujoProrrogaOrden.getJustificacion());
                }
                if (listProrrogaOrden.get(indiceProrrogaOrden).getFececEstatus() != null && listProrrogaOrden.get(indiceProrrogaOrden).getIdEstatus().equals(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus())
                        || listProrrogaOrden.get(indiceProrrogaOrden).getIdEstatus().equals(EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus())) {
                    if (fecetFlujoProrrogaOrden.getAprobada()) {
                        prorrogaOrdenConsultaDTO.setEstatus(FacesUtil.getMessageResourceString("estatus.firmante.aprobada"));
                    } else {
                        prorrogaOrdenConsultaDTO.setEstatus(FacesUtil.getMessageResourceString("estatus.firmante.no.aprobada"));
                    }
                }
                prorrogaOrdenConsultaDTO.setFlujoProrrogaOrdenConsultaDTO(flujoProrrogaOrdenConsultaDTO);
            }
            indiceProrrogaOrden++;
        }

        getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().setListProrrogaOrdenConsultaDTO(listProrrogaOrdenConsulta);
        ocultarPaneles();
        setVisiblePnlConsultarDocRelacionada(true);
        setVisiblePruebasPericiales(Constantes.REE.equals(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdMetodo()));
        // Se obtienen pruebas periciales
        if (isVisiblePruebasPericiales()) {
            getReimprimirDocumentosDTOHelper().setListaPruebasPericiales(getConsultarReimprimirDocumentosService()
                    .obtenerPruebasPericiales(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden()));
        }
    }

    private void removerOficiosRechazados(List<OficioConsultaDTO> lista) {
        List<OficioConsultaDTO> listaNueva = new ArrayList<OficioConsultaDTO>();
        for (OficioConsultaDTO oficio : lista) {
            if (oficio.getFechaNotificacion() != null) {
                listaNueva.add(oficio);
            }
        }
        lista.clear();
        lista.addAll(listaNueva);
    }

    private List<FecetOficio> removerOficiosCompulsas(List<FecetOficio> lista) {
        List<FecetOficio> listaNueva = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lista) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_TERCERO)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_OTRAS_AUTORIDADES)) {
                List<FecetOficio> avisos = getConsultarReimprimirDocumentosService().obtenerOficiosDependientes(oficio.getIdOficio());
                for (FecetOficio aviso : avisos) {
                    getConsultarReimprimirDocumentosService().obtenerfechaNotificacion(aviso);
                    listaNueva.add(aviso);
                }
            }
        }

        List<FecetOficio> remove = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lista) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_TERCERO)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_OTRAS_AUTORIDADES)) {
                remove.add(oficio);
            }
        }

        lista.removeAll(remove);

        for (FecetOficio oficio : listaNueva) {
            lista.add(oficio);
        }

        return lista;

    }

    private String llenarRfcANombreOficio(FecetOficio oficio) {
        StringBuilder sb = new StringBuilder(oficio.getFecetTipoOficio().getNombre());

        TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());

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
        return sb.toString();
    }

    private String llenarNombreOficio(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder();

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    private String llenarNombreOficioCompulsa(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder(oficio.getFecetTipoOficio().getNombre());

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    public StreamedContent getArchivoDescargable() {
        StreamedContent content = null;
        try {
            if (getReimprimirDocumentosDTOHelper().getRutaArchivo() == null || getReimprimirDocumentosDTOHelper().getNombreArchivo() == null) {
                logger.error("No se pudo descargar el archivo. ");
                addErrorMessage("No se encontro el documento seleccionado");
            } else {
                content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getReimprimirDocumentosDTOHelper().getRutaArchivo(),
                        getReimprimirDocumentosDTOHelper().getNombreArchivo());
            }
        } catch (final Exception exception) {
            logger.error("No se pudo descargar el archivo. ", exception);
            addErrorMessage("No se encontro el documento seleccionado");
        }
        return content;
    }

    public void buscarDocPruebasAlegatos() {
        List<FecetAlegato> listFecetAlegato = getConsultarReimprimirDocumentosService()
                .buscarAlegatosPorPromocion(getReimprimirDocumentosDTOHelper().getPromocionConsultaSeleccionada().getIdPromocion());
        getReimprimirDocumentosDTOHelper().getPromocionConsultaSeleccionada().setListFecetAlegato(listFecetAlegato);
        getReimprimirDocumentosDTOHelper().setIdPromocionSeleccionada(getReimprimirDocumentosDTOHelper().getPromocionConsultaSeleccionada().getIdPromocion());
        getReimprimirDocumentosDTOHelper().setFechaEnvioSeleccionada(getReimprimirDocumentosDTOHelper().getPromocionConsultaSeleccionada().getFechaEnvio());
        // ocultarPaneles()
        // setVisiblePnlDocPruebasAlegatos(true)
    }

    public void buscarDocPruebasAlegatosOficio() {
        List<FecetAlegatoOficio> listFecetAlegatoOficio = getConsultarReimprimirDocumentosService()
                .buscarAlegatoOficioPorPromocionOficio(getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().getIdPromocionOficio());
        getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().setListFecetAlegatoOficio(listFecetAlegatoOficio);
        getReimprimirDocumentosDTOHelper()
                .setIdPromocionOficioSeleccionada(getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().getIdPromocionOficio());
        getReimprimirDocumentosDTOHelper()
                .setFechaEnvioOficioSeleccionada(getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().getFechaEnvio());
        // ocultarPaneles()
        // setVisiblePnlDocOficioPruebasAlegatos(true)

    }

    public void buscarOficiosDependientes() {
        List<FecetOficio> listOficioDependiente = getConsultarReimprimirDocumentosService()
                .buscarOficioDependientePorOficio(getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().getIdOficio());
        getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado()
                .setListOficioDependientes(getConsultarReimprimirDocumentosHelper().convertToOficioConsultaDTO(listOficioDependiente,
                                getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada(), getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado()));
        // ocultarPaneles()
        // setVisiblePnlOficiosDependientes(true)
    }

    public void buscarAnexosOficios() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().getIdOficio());
        getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
        // ocultarPaneles()
        // setVisiblePnlAnexosOficios(true)
    }

    public void buscarDocProrrogaOrden() {
        List<FecetDocProrrogaOrden> listFecetDocProrrogaOrden = getConsultarReimprimirDocumentosService()
                .buscarDocProrrogaOrdenPorProrroga(getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().getIdProrroga());
        getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().setListFecetDocProrrogaOrden(listFecetDocProrrogaOrden);
        getReimprimirDocumentosDTOHelper()
                .setIdProrrogaOrdenSeleccionada(getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().getIdProrroga());
        getReimprimirDocumentosDTOHelper()
                .setFechaEnvioProrrogaOrdenSeleccionada(getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().getFechaSolicitud());
        // ocultarPaneles()
        // setVisiblePnlDocProrrogaOrden(true)

    }

    public void buscarAnexosOficioDependientes() {
        List<FecetOficioAnexos> listFecetOficioAnexo = getConsultarReimprimirDocumentosService()
                .buscarOficioAnexoPorOficio(getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().getIdOficio());
        getReimprimirDocumentosDTOHelper().getOficioConsultaDependeienteSeleccionado().setListFecetOficioAnexo(listFecetOficioAnexo);
        ocultarPaneles();
        setVisiblePnlAnexosOficiosDependientes(true);
    }

    public void buscarDocProrrogaOficio() {
        List<FecetDocProrrogaOficio> listFecetDocProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarDocProrrogaOficioPorProrroga(getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().getIdProrroga());
        getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().setListFecetDocProrrogaOficio(listFecetDocProrrogaOficio);
        getReimprimirDocumentosDTOHelper()
                .setIdProrrogaOficioSeleccionada(getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().getIdProrroga());
        getReimprimirDocumentosDTOHelper()
                .setFechaEnvioProrrogaOficioSeleccionada(getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().getFechaSolicitud());

        // ocultarPaneles()
        // setVisiblePnlDocProrrogaOficio(true)
    }

    private void buscarOficiosDeRequerimiento(List<OficioConsultaDTO> listOficioConsultaDTO) {
        AgaceOrden orden = new AgaceOrden();
        orden.setFeceaMetodo(new FececMetodo());
        orden.getFeceaMetodo().setIdMetodo(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdMetodo());
        for (OficioConsultaDTO oficioConsultaDTO : listOficioConsultaDTO) {
            if (!getConsultarReimprimirDocumentosRules().validaMetodo(orden.getFeceaMetodo().getIdMetodo(), oficioConsultaDTO.getIdTipoOficio())) {
                List<FecetPromocionOficio> listFecetPromocionOficio = getConsultarReimprimirDocumentosService()
                        .buscarPromocionOficioPorOficio(oficioConsultaDTO.getIdOficio());
                List<PromocionOficioConsultaDTO> listPromocionOficioConsultaDTO = getConsultarReimprimirDocumentosHelper().convertToPromocionOficioConsultaDTO(
                        listFecetPromocionOficio, oficioConsultaDTO.getIdTipoOficio(), getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada());
                oficioConsultaDTO.setListPromocionOficioConsulta(listPromocionOficioConsultaDTO);
                oficioConsultaDTO.setOficioConRequerimiento(true);
            } else {
                oficioConsultaDTO.setOficioConRequerimiento(false);
            }
        }
    }

    public void buscarProrrogaOficio() {
        List<FecetProrrogaOficio> listFecetProrrogaOficio = getConsultarReimprimirDocumentosService()
                .buscarProrrogaOficioPorOficio(getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().getIdOficio());
        List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta = getConsultarReimprimirDocumentosHelper().convertToProrrogaOficioConsultaDTO(
                listFecetProrrogaOficio, getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado(),
                getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada());
        int indiceOficio = 0;
        for (ProrrogaOficioConsultaDTO prorrogaOficioConsulta : listProrrogaOficioConsulta) {
            BigDecimal idOficioProrroga = prorrogaOficioConsulta.getIdProrroga();
            List<FecetFlujoProrrogaOficio> listFecetFlujoProrrogaOficios = getConsultarReimprimirDocumentosService()
                    .buscarFlujoProrrogaOficioPorProrroga(idOficioProrroga);
            if (!listFecetFlujoProrrogaOficios.isEmpty()) {
                FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio = listFecetFlujoProrrogaOficios.get(0);
                FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTO = getConsultarReimprimirDocumentosHelper()
                        .convertToFlujoProrrogaOficioConsultaDTO(fecetFlujoProrrogaOficio);
                if (flujoProrrogaOficioConsultaDTO != null) {
                    flujoProrrogaOficioConsultaDTO.setMotivo(fecetFlujoProrrogaOficio.getJustificacion());
                }
                if (listFecetProrrogaOficio.get(indiceOficio).getIdEstatus() != null && (listFecetProrrogaOficio.get(indiceOficio).getIdEstatus().equals(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus())
                        || listFecetProrrogaOficio.get(indiceOficio).getIdEstatus().equals(EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus()))) {
                    if (listFecetProrrogaOficio.get(indiceOficio).getAprobada()) {
                        prorrogaOficioConsulta.setEstatus(FacesUtil.getMessageResourceString("estatus.firmante.aprobada"));
                    } else {
                        prorrogaOficioConsulta.setEstatus(FacesUtil.getMessageResourceString("estatus.firmante.no.aprobada"));
                    }
                }
                prorrogaOficioConsulta.setFlujoProrrogaOficioConsultaDTO(flujoProrrogaOficioConsultaDTO);
            }
            indiceOficio++;
        }
        getReimprimirDocumentosDTOHelper().getOficioConsultaSeleccionado().setListProrrogaOficioConsulta(listProrrogaOficioConsulta);
        // ocultarPaneles()
        // setVisiblePnlOficiosRequerimiento(true)
    }

    public void buscarDocAnexoResolProrrogaOrden() {
        BigDecimal idFlujoProrrogaOrden = getReimprimirDocumentosDTOHelper().getFlujoProrrogaOrdenConsultaDTOSeleccionada().getIdFlujoProrrogaOrden();
        List<FecetAnexosProrrogaOrden> listFecetAnexosProrrogaOrden = getConsultarReimprimirDocumentosService()
                .buscarAnexosProrrogaOrdenPorFlujoProrrogaOrden(idFlujoProrrogaOrden);
        List<AnexoProrrogaOrdenConsultaDTO> listAnexoProrrogaOrdenConsultaDTO = getConsultarReimprimirDocumentosHelper()
                .convertToAnexoProrrogaOrdenConsultaDTO(listFecetAnexosProrrogaOrden);
        String nombreAuditor = "";
        BigDecimal idAuditor = getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().getIdAuditor() != null
                ? getReimprimirDocumentosDTOHelper().getProrrogaOrdenConsultaSeleccionada().getIdAuditor()
                : getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdAuditor();
        if (idAuditor != null) {
            EmpleadoDTO empleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(idAuditor);
            nombreAuditor = empleado != null ? empleado.getNombre() : "";
        }
        for (AnexoProrrogaOrdenConsultaDTO anexoProrrogaOrdenConsultaDTO : listAnexoProrrogaOrdenConsultaDTO) {
            anexoProrrogaOrdenConsultaDTO.setPresentadoPor(nombreAuditor);
        }
        getReimprimirDocumentosDTOHelper().getFlujoProrrogaOrdenConsultaDTOSeleccionada().setListAnexosProrrogaOrden(listAnexoProrrogaOrdenConsultaDTO);
        // ocultarPaneles()
        // setVisiblepnlDocAnexosResolProrrogaOrden(true)
    }

    public void buscarDocAnexoResolProrrogaOficio() {
        BigDecimal idFlujoProrrogaOficio = getReimprimirDocumentosDTOHelper().getFlujoProrrogaOficioConsultaDTOSeleccionada().getIdFlujoProrrogaOficio();
        List<FecetAnexosProrrogaOficio> listFecetAnexosProrrogasOficios = getConsultarReimprimirDocumentosService()
                .buscarAnexosProrrogaOficioPorFlujoProrrogaOficio(idFlujoProrrogaOficio);
        List<AnexoProrrogaOficioConsultaDTO> listAnexosProrrogasOficiosConsulta = getConsultarReimprimirDocumentosHelper()
                .convertToAnexoProrrogaOficioConsultaDTO(listFecetAnexosProrrogasOficios);
        BigDecimal idAuditor = getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().getIdAuditor() != null
                ? getReimprimirDocumentosDTOHelper().getProrrogaOficioConsultaSeleccionada().getIdAuditor()
                : getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdAuditor();
        String nombreAuditor = "";
        if (idAuditor != null) {
            EmpleadoDTO empleado = getConsultarReimprimirDocumentosService().buscarEmpleadoPorIdEmpleado(idAuditor);
            nombreAuditor = empleado != null ? empleado.getNombre() : "";
        }
        for (AnexoProrrogaOficioConsultaDTO anexoProrrogaOficioConsultaDTO : listAnexosProrrogasOficiosConsulta) {
            anexoProrrogaOficioConsultaDTO.setPresentadoPor(nombreAuditor);
        }
        getReimprimirDocumentosDTOHelper().getFlujoProrrogaOficioConsultaDTOSeleccionada().setListAnexosProrrogaOficios(listAnexosProrrogasOficiosConsulta);
        // ocultarPaneles()
        // setVisiblepnlDocAnexosResolProrrogaOficio(true)
    }

    public void buscarDocPruebasOficioAlegatos() {
        List<FecetAlegatoOficio> listFecetAlegatoOficio = getConsultarReimprimirDocumentosService()
                .buscarAlegatoOficioPorPromocionOficio(getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().getIdPromocionOficio());
        getReimprimirDocumentosDTOHelper().getPromocionOficioConsultaSeleccionada().setListFecetAlegatoOficio(listFecetAlegatoOficio);
        ocultarPaneles();
        setVisiblePnlDocOficioPruebasAlegatos(true);
    }

    public void mostrarPantallaOficiosDeRequerimiento() {
        ocultarPaneles();
        setVisiblePnlOficiosRequerimiento(true);
    }

    public void mostrarPantallaConsulta() {
        ocultarPaneles();
        setVisiblePnlConsultarDocBusqueda(true);
    }

    public void mostrarPantallaOficiosDependientes() {
        ocultarPaneles();
        setVisiblePnlOficiosDependientes(true);
    }

    public void mostrarPantallaDocRelacionada() {
        ocultarPaneles();
        setVisiblePnlConsultarDocRelacionada(true);
    }

    public void mostrarPantallaOficiosDeRequerimientos() {
        ocultarPaneles();
        setVisiblePnlOficiosRequerimiento(true);
    }

    public void limpiar() {
        setVisiblePnlConsultarDocBusqueda(true);
        setRenderTablaOrdenesGrl(false);
        getReimprimirDocumentosDTOHelper().getAgaceOrden().setNumeroOrden(null);
        getReimprimirDocumentosDTOHelper().getAgaceOrden().setIdRegistroPropuesta(null);
        getReimprimirDocumentosDTOHelper().getAgaceOrden().getFecetContribuyente().setRfc(null);
        getReimprimirDocumentosDTOHelper().setOrdenConsultaSeleccionada(null);
        getReimprimirDocumentosDTOHelper().setListOrden(null);
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

    public List<AgaceOrden> buscarOrdenesService(AgaceOrden agaceOrden) {
        getReimprimirDocumentosDTOHelper().setPerfilSession((PerfilContribuyenteVO) getSession().getAttribute(PERFIL));
        getSession().removeAttribute(PERFIL);

        List<AgaceOrden> listOrdenTmp = new ArrayList<AgaceOrden>();
        String rfc = null;
        if (Constantes.ID_CONTRIBUYENTE.equals(getReimprimirDocumentosDTOHelper().getPerfilSession().getIdTipoAsociado())) {
            listOrdenTmp.addAll(
                    getConsultarReimprimirDocumentosService().buscarOrdenPorCriterio(null, null, agaceOrden.getFecetContribuyente().getRfc(), null, null));
            rfc = agaceOrden.getFecetContribuyente().getRfc();
        } else {
            if (Constantes.ID_APODERADO_LEGAL.equals(getReimprimirDocumentosDTOHelper().getPerfilSession().getIdTipoAsociado())) {
                listOrdenTmp.addAll(
                        getConsultarReimprimirDocumentosService().buscarOrdenPorCriterio(null, null, getReimprimirDocumentosDTOHelper().getPerfilSession().getRfcContribuyente(), null, null));
                eliminarCompulsas(listOrdenTmp);
                rfc = agaceOrden.getFecetContribuyente().getRfc();
            } else {
                listOrdenTmp.addAll(getConsultarReimprimirDocumentosService().obtenerOrdenesAsociado(getReimprimirDocumentosDTOHelper().getPerfilSession().getRfc(), getReimprimirDocumentosDTOHelper().getPerfilSession().getIdTipoAsociado(),
                        getReimprimirDocumentosDTOHelper().getPerfilSession().getRfcContribuyente()));
                rfc = getReimprimirDocumentosDTOHelper().getPerfilSession().getRfcContribuyente();
            }
        }
        getReimprimirDocumentosDTOHelper().setContribuyente(getConsultarReimprimirDocumentosService().buscarContribuyentePorRfc(rfc));
        return getConsultarReimprimirDocumentosHelper().eliminarOrdenesRepetidas(listOrdenTmp);
    }

    private void eliminarCompulsas(List<AgaceOrden> listOrdenTmp) {
        if (listOrdenTmp != null && !listOrdenTmp.isEmpty()) {
            for (int i = listOrdenTmp.size(); Constantes.ENTERO_CERO < i; i--) {
                if (listOrdenTmp.get(i - Constantes.ENTERO_UNO).isBlnCompulsa()) {
                    listOrdenTmp.remove(i - Constantes.ENTERO_UNO);
                }
            }
        }

    }

    public void mostrarExpedienteOrden() {
        getReimprimirDocumentosDTOHelper().setExpedienteOrden(new ArrayList<FecetDocOrden>());
        getReimprimirDocumentosDTOHelper().setOficiosFirmados(new ArrayList<FecetOficio>());
        getConsultarReimprimirDocumentosService().obtenerDocumentosExpediente(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden(),
                getReimprimirDocumentosDTOHelper().getExpedienteOrden(), getReimprimirDocumentosDTOHelper().getOficiosFirmados());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosFirmadosPdf').show();");
    }

    public void buscarAlegatosAgenteAduanal() {
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setAlegatos(getConsultarReimprimirDocumentosService()
                .obtenerAlegatosAgente(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getDocumentoAgenteSeleccionado()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogAlegatosAgenteAduanal').show()");
    }

    public void obtenerDocPruebaPericialByIdPericial() {
        getReimprimirDocumentosDTOHelper().setListaDocPericiales(getConsultarReimprimirDocumentosService()
                .obtenerDocPruebaPericialByIdPericial(getReimprimirDocumentosDTOHelper().getPruebaPericialSeleccionada().getIdPruebasPericiales()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgDocPruebasPericialesEnviada').show()");
    }

    public StreamedContent getDescargarExpedienteOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarOrdenExterno(orden);
        return getArchivoDescargable();
    }

    public StreamedContent getDescargarExpedienteOficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());

        if (getReimprimirDocumentosDTOHelper().getOficio() != null && getReimprimirDocumentosDTOHelper().getOficio()
                .getIdTipoOficio().equals(new BigDecimal(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getIdTipoOficio()))) {
            getPistaAuditoriaDescargaDocumentosService().consultarOficioCompulsaExterno(orden);
        } else {
            getPistaAuditoriaDescargaDocumentosService().consultarOficioExterno(orden);
        }

        return getArchivoDescargable();
    }

    public StreamedContent getDescargarExpedienteProrrogaOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOrdenExterno(orden);
        return getArchivoDescargable();
    }

    public StreamedContent getDescargarExpedienteProrrogaOficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOficioExterno(orden);
        return getArchivoDescargable();
    }

    public StreamedContent getDescargarExpedientePromocionOrden() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarPromocionOrdenExterno(orden);
        return getArchivoDescargable();
    }

    public StreamedContent getDescargarExpedientePromocionficio() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getPistaAuditoriaDescargaDocumentosService().consultarPromocionOficioExterno(orden);
        return getArchivoDescargable();
    }

}
