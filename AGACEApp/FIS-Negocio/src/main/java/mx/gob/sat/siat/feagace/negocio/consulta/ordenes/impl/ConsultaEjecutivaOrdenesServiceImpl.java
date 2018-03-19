package mx.gob.sat.siat.feagace.negocio.consulta.ordenes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FiltroOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesRule;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaOrdenBeanFactory;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.consulta.ServicioConsultaEjecutivaAgaceAbstract;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.ConsultaEjecutivaOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.FiltroConsultaOrdenesRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Service("consultaEjecutivaOrdenesService")
public class ConsultaEjecutivaOrdenesServiceImpl extends ServicioConsultaEjecutivaAgaceAbstract implements ConsultaEjecutivaOrdenesService {

    private static final long serialVersionUID = -8139497453836438771L;

    private static final int CONSULTA_TIPO_CENTRAL = 1;
    private static final int CONSULTA_TIPO_CONSULTA_AUDITOR = 6;
    private static final int CONSULTA_TIPO_CONSULTA_FIRMANTE = 7;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDAO;

    @Autowired
    private transient PlazosServiceOrdenes plazosService;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;

    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;

    @Autowired
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService;

    @Override
    public ConsultaEjecutivaOrdenesBO getAccesoEmpleadoAConsultaOrdenes(EmpleadoDTO empleado) throws ConsultaEjecutivaOrdenesServiceException {
        ConsultaEjecutivaOrdenesBO consultaEjecutivaOrdenesBO;
        try {
            consultaEjecutivaOrdenesBO = ConsultaOrdenBeanFactory.getBeanConsultaBO(getEmpleadoCompleto(empleado.getRfc()));
            consultaEjecutivaOrdenesBO.setLstUnidadesAdministrativasDesahogo(getUnidadesAdministrativas(empleado));
            consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CONSULTA_ORDENES);
            if (consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO)) {
                consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.ESTATUS_DISPONIBLES_CONSULTA_ORDENES);
                consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
                consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.METODOS_DISPONIBLES_CONSULTA_ORDENES);
                consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
                consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.SEMAFOROS_DISPONIBLES_CONSULTA_ORDENES);
                consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
                return consultaEjecutivaOrdenesBO;
            } else {
                throw new ConsultaEjecutivaOrdenesServiceException(ConsultaEjecutivaOrdenesService.MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
            }
        } catch (EmpleadoServiceException ex) {
            throw new ConsultaEjecutivaOrdenesServiceException(ConsultaEjecutivaOrdenesService.MSG_ERROR_REGISTRO_EMPLEADO, ex);
        }
    }

    @Override
    public ConsultaEjecutivaOrdenesBO consultarOrdenes(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroDeBusqueda)
            throws ConsultaEjecutivaOrdenesServiceException {
        filtroDeBusqueda.setConsultaEmpleado(false);
        if (!filtroDeBusqueda.isVisibilidadACPPCE()) {
            boolean[] condiciones = {consultaBO.getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId()),
                consultaBO.getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId())};
            if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(condiciones)) {
                filtroDeBusqueda.setConsultaEmpleado(true);
                filtroDeBusqueda.setTipoEmpleadoConsulta(consultaBO.getRolEmpleado());
            }
        }
        filtroDeBusqueda.setGruposDeEstatusValidos(consultaBO.getGruposDeEstatusValidos());
        consultaBO.setLstOrdenResult(agaceOrdenDAO.consultaOrdenes(filtroDeBusqueda.getFiltroDAO()));
        if (consultaBO.getLstOrdenResult() != null && !consultaBO.getLstOrdenResult().isEmpty()) {
            try {
                for (AgaceOrden orden : consultaBO.getLstOrdenResult()) {
                    plazosService.asignarFechasNotificacion(orden);
                    if (orden.getFecetDetalleNyV() != null && orden.getFecetDetalleNyV().getFecSurteEfectosNyV() != null) {
                        plazosService.inicializarOrdenConPlazos(orden);
                    } else {
                        orden.setSemaforo(Constantes.ENTERO_DIEZ);
                        orden.setDescripcionPlazoRestante("");
                    }
                    orden.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(orden.getSemaforo()));
                    orden.setDescripcionSemaforo(BusinessUtil.obtenerDescripcionSemaforo(orden.getSemaforo()));
                    orden.setDocOrden(fecetDocOrdenDao.findByFecetOrdenPdf(orden.getIdOrden()));
                    orden.setOficiosFirmados(fecetOficioDao.getOficioByIdOrdenIdEstatus(orden.getIdOrden(),
                            EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(),
                            EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus()));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        conteoXDetalle(consultaBO, filtroDeBusqueda);
        return consultaBO;
    }

    public ConsultaEjecutivaOrdenesBO getOrdenesXSemaforoMetodo(ConsultaEjecutivaOrdenesBO consultaBO, SemaforoEnum tipoSemaforo,
            SemaforoEnum tipoSemaforoFiltrado, TipoMetodoEnum tipoMetodo, AgrupadorEstatusOrdenesEnum grupoEstatusSeleccionado, FiltroOrdenes filtro,
            boolean flgEmpleado) {
        List<AgaceOrden> lstFiltrada = consultaBO.getLstOrdenResult();
        if (tipoSemaforo != null) {
            lstFiltrada = getOrdenesXSemaforo(lstFiltrada, tipoSemaforo);
        }
        if (tipoSemaforoFiltrado != null) {
            lstFiltrada = getOrdenesXSemaforo(lstFiltrada, tipoSemaforoFiltrado);
        }
        if (tipoMetodo != null) {
            lstFiltrada = getOrdenesXMetodo(lstFiltrada, tipoMetodo);
        }
        if (grupoEstatusSeleccionado != null) {
            lstFiltrada = getOrdenesXAgrupadorEstatus(lstFiltrada, grupoEstatusSeleccionado, consultaBO);
        }
        consultaBO.setLstOrdenesXFiltro(lstFiltrada);
        detallesXFiltroSemaforoMetodo(consultaBO, filtro, flgEmpleado);
        return consultaBO;
    }

    private void detallesXFiltroSemaforoMetodo(ConsultaEjecutivaOrdenesBO consultaBO, FiltroOrdenes filtro, boolean flgEmpleado) {
        consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        consultaBO.setDetalleXEmpleadoFiltrado(new HashMap<EmpleadoDTO, Integer>());
        consultaBO.setDetalleXSemaforoFiltrado(new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class));
        boolean listaEmpleadoDto = false;
        int tipoEmpleado = 0;
        if (flgEmpleado) {
            TipoEmpleadoEnum tipoEmpleadoEnum = rolValido(filtro.getEmpleadoConsultaFiltro().getDetalleEmpleado());
            if (tipoEmpleadoEnum != null) {
                tipoEmpleado = (int) tipoEmpleadoEnum.getId();
            }
        } else if (filtro.isVisibilidadACPPCE()) {
            tipoEmpleado = Constantes.ENTERO_UNO;
        } else {
            tipoEmpleado = (int) consultaBO.getRolEmpleado().getId();
        }

        switch (tipoEmpleado) {
            case CONSULTA_TIPO_CENTRAL:
                List<EmpleadoDTO> lstEmpleadoDto = null;
                int idArace = 0;
                if (filtro.isVisibilidadACPPCE()) {
                    idArace = filtro.getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace();
                } else {
                    idArace = getIdUnidadAdministrativa(consultaBO.getEmpleadoConsulta());
                }
                try {
                    lstEmpleadoDto = getEmpleadosXUnidadAdmva(idArace, (int) TipoEmpleadoEnum.FIRMANTE.getId(),
                            ClvSubModulosAgace.REPORTES);
                    if (isValidList(lstEmpleadoDto)) {
                        listaEmpleadoDto = true;
                        for (EmpleadoDTO emp : lstEmpleadoDto) {
                            consultaBO.getDetalleXEmpleado().put(emp, 0);
                        }
                    }
                } catch (EmpleadoServiceException e) {
                    logger.error(e.getMessage(), e);
                }
                break;
            case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                List<EmpleadoDTO> lstAuditor = null;
                try {
                    if (filtro.getEmpleadoConsultaFiltro() != null) {
                        filtro.setEmpleadoConsultaFiltro(getEmpleadoCompleto(filtro.getEmpleadoConsultaFiltro().getIdEmpleado().intValue()));
                        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = filtro.getEmpleadoConsultaFiltro().getSubordinados();
                        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.FIRMANTE);
                        lstAuditor = sub.get(TipoEmpleadoEnum.AUDITOR);
                    }
                    if (isValidList(lstAuditor)) {
                        listaEmpleadoDto = true;
                        for (EmpleadoDTO emp : lstAuditor) {
                            consultaBO.getDetalleXEmpleadoFiltrado().put(emp, 0);
                        }
                    }
                } catch (EmpleadoServiceException e) {
                    logger.error(e.getMessage(), e);
                }
                break;
            default:
                logger.info("Opcion no valida de consulta.");
                break;
        }
        if (isValidList(consultaBO.getLstOrdenesXFiltro())) {
            if (listaEmpleadoDto) {
                consultaBO.setLstOrdenesTotalEmpleado(new ArrayList<AgaceOrden>());
                if (tipoEmpleado == CONSULTA_TIPO_CENTRAL) {
                    addDetalleEmpleado(consultaBO.getLstOrdenesXFiltro(), consultaBO.getDetalleXEmpleado(), tipoEmpleado,
                            consultaBO.getLstOrdenesTotalEmpleado());
                } else if (tipoEmpleado == CONSULTA_TIPO_CONSULTA_FIRMANTE) {
                    addDetalleEmpleado(consultaBO.getLstOrdenesXFiltro(), consultaBO.getDetalleXEmpleadoFiltrado(), tipoEmpleado,
                            consultaBO.getLstOrdenesTotalEmpleado());
                }
            }
            getDetalleXEstatusFiltrado(consultaBO);
            addDetalleSemaforo(consultaBO, consultaBO.getDetalleXSemaforoFiltrado());
        }
    }

    private TipoEmpleadoEnum rolValido(List<DetalleEmpleadoDTO> lstDetalleEmpleado) {
        for (DetalleEmpleadoDTO detalle : lstDetalleEmpleado) {
            if (detalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId())) {
                return detalle.getTipoEmpleado();
            } else if (detalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId())) {
                return detalle.getTipoEmpleado();
            }
        }
        return null;
    }

    private void addDetalleEmpleado(List<AgaceOrden> lstFiltrada, Map<EmpleadoDTO, Integer> mapDetalleEmpleado, int tipoEmpleado,
            List<AgaceOrden> lstTotalEmpleados) {
        int valor;
        if (isValidList(lstFiltrada)) {
            for (AgaceOrden orden : lstFiltrada) {
                boolean flgIdFirmante = orden.getIdFirmante() != null;
                boolean flgIdAuditor = orden.getIdAuditor() != null;
                switch (tipoEmpleado) {
                    case CONSULTA_TIPO_CENTRAL:
                        for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                            if (flgIdFirmante && empleadoKey.getKey() != null && orden.getIdFirmante().equals(empleadoKey.getKey().getIdEmpleado())) {
                                valor = empleadoKey.getValue();
                                empleadoKey.setValue(++valor);
                                lstTotalEmpleados.add(orden);
                            }
                        }
                        break;
                    case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                        for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                            if (flgIdAuditor && empleadoKey.getKey() != null && orden.getIdAuditor().equals(empleadoKey.getKey().getIdEmpleado())) {
                                valor = empleadoKey.getValue();
                                empleadoKey.setValue(++valor);
                                lstTotalEmpleados.add(orden);
                            }
                        }
                        break;
                }
            }
        }
    }

    private void addDetalleSemaforo(ConsultaEjecutivaOrdenesBO consultaBO, Map<SemaforoEnum, Integer> mapDetalleSemaforo) {
        if (isValidList(consultaBO.getLstOrdenesXFiltro()) && mapDetalleSemaforo != null) {
            for (SemaforoEnum sem : consultaBO.getLstSemaforosValidos()) {
                mapDetalleSemaforo.put(sem, 0);
            }
            for (AgaceOrden orden : consultaBO.getLstOrdenesXFiltro()) {
                int valor = 0;
                if (mapDetalleSemaforo.containsKey(getSemaforoXId(orden.getSemaforo()))) {
                    valor = mapDetalleSemaforo.get(getSemaforoXId(orden.getSemaforo()));
                    mapDetalleSemaforo.put(getSemaforoXId(orden.getSemaforo()), ++valor);
                }
            }
            consultaBO.setDetalleXSemaforoFiltrado(mapDetalleSemaforo);
        }

    }

    private SemaforoEnum getSemaforoXId(Integer idSemaforo) {
        for (SemaforoEnum tipoSemaforo : SemaforoEnum.values()) {
            if (tipoSemaforo.getValor() == idSemaforo) {
                return tipoSemaforo;
            }
        }
        return SemaforoEnum.SEMAFORO_VERDE;
    }

    private void getDetalleXEstatusFiltrado(ConsultaEjecutivaOrdenesBO consultaBO) {
        boolean flgValido = isValidList(consultaBO.getLstOrdenesXFiltro());
        consultaBO.setDetalleXEstatusFiltrado(new EnumMap<AgrupadorEstatusOrdenesEnum, Integer>(AgrupadorEstatusOrdenesEnum.class));
        AgrupadorEstatusOrdenesEnum estatusOrden;

        if (flgValido) {
            int valor = 0;
            for (AgrupadorEstatusOrdenesEnum agrupador : AgrupadorEstatusOrdenesEnum.values()) {
                consultaBO.getDetalleXEstatusFiltrado().put(agrupador, valor);
            }
            for (AgaceOrden orden : consultaBO.getLstOrdenesXFiltro()) {
                estatusOrden = obtenerAgrupadorEstatusEnumXIdEstatus(consultaBO, orden.getIdEstatus(), orden.getIdOrden());
                flgValido = estatusOrden != null;
                flgValido = flgValido && consultaBO.getDetalleXEstatusFiltrado().containsKey(estatusOrden);

                if (flgValido) {
                    valor = consultaBO.getDetalleXEstatusFiltrado().get(estatusOrden);
                    consultaBO.getDetalleXEstatusFiltrado().put(estatusOrden, ++valor);
                }
            }
        }
    }

    private AgrupadorEstatusOrdenesEnum obtenerAgrupadorEstatusEnumXIdEstatus(ConsultaEjecutivaOrdenesBO consultaBO, BigDecimal idEstatus, BigDecimal idOrden) {
        for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : consultaBO.getGruposDeEstatusValidos().entrySet()) {
            List<TipoEstatusEnum> listaEstatus = grupoEstatus.getValue();
            for (TipoEstatusEnum estatus : listaEstatus) {
                if (estatus.getId() == idEstatus.longValue()) {
                    if (TipoEstatusEnum.CRON_REGISTRO_NOTIFICADO_AL_CONTRIBUYENTE.getId() == idEstatus.longValue()) {
                        List<FecetOficio> oficios = fecetOficioDao.obtenerOficioNoAdministrableByIdOrden(idOrden);
                        if (oficios != null && !oficios.isEmpty()) {
                            if (AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO.getIdGrupo() == grupoEstatus.getKey().getIdGrupo()) {
                                return grupoEstatus.getKey();
                            } else {
                                break;
                            }
                        } else {
                            if (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == grupoEstatus.getKey().getIdGrupo()) {
                                return grupoEstatus.getKey();
                            } else {
                                break;
                            }
                        }
                    } else {
                        return grupoEstatus.getKey();
                    }
                }
            }
        }
        return null;
    }

    private List<AgaceOrden> getOrdenesXSemaforo(List<AgaceOrden> listaXFiltrar, SemaforoEnum tipoSemaforo) {
        List<AgaceOrden> listaFiltrada = new ArrayList<AgaceOrden>();
        if (isValidList(listaXFiltrar)) {
            for (AgaceOrden orden : listaXFiltrar) {
                if (tipoSemaforo.getValor() == orden.getSemaforo()) {
                    listaFiltrada.add(orden);
                }
            }
        }
        return listaFiltrada;
    }

    private List<AgaceOrden> getOrdenesXMetodo(List<AgaceOrden> listaXFiltrar, TipoMetodoEnum tipoMetodo) {
        List<AgaceOrden> listaFiltrada = new ArrayList<AgaceOrden>();
        if (isValidList(listaXFiltrar)) {
            for (AgaceOrden orden : listaXFiltrar) {
                if (orden.getIdMetodo().longValue() == tipoMetodo.getId()) {
                    listaFiltrada.add(orden);
                }
            }
        }
        return listaFiltrada;
    }

    private List<AgaceOrden> getOrdenesXAgrupadorEstatus(List<AgaceOrden> listaXFiltrar, AgrupadorEstatusOrdenesEnum tipoEstatus,
            ConsultaEjecutivaOrdenesBO consultaBO) {
        List<AgaceOrden> listaFiltrada = new ArrayList<AgaceOrden>();
        if (isValidList(listaXFiltrar)) {
            AgrupadorEstatusOrdenesEnum estatusOrden;
            for (AgaceOrden orden : listaXFiltrar) {
                for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : consultaBO.getGruposDeEstatusValidos().entrySet()) {
                    List<TipoEstatusEnum> listaEstatus = grupoEstatus.getValue();
                    for (TipoEstatusEnum estatus : listaEstatus) {
                        if (estatus.getId() == orden.getIdEstatus().longValue()) {
                            estatusOrden = grupoEstatus.getKey();
                            if (estatusOrden.getIdGrupo() == tipoEstatus.getIdGrupo()) {
                                if (AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO.getIdGrupo() == estatusOrden.getIdGrupo()
                                        || AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == estatusOrden.getIdGrupo()) {
                                    List<FecetOficio> oficios = fecetOficioDao.obtenerOficioNoAdministrableByIdOrden(orden.getIdOrden());
                                    if (oficios != null && !oficios.isEmpty()) {
                                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO.getIdGrupo() == grupoEstatus.getKey().getIdGrupo()) {
                                            listaFiltrada.add(orden);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == grupoEstatus.getKey().getIdGrupo()) {
                                            listaFiltrada.add(orden);
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    listaFiltrada.add(orden);
                                }
                            }
                        }
                    }
                }
            }
        }
        return listaFiltrada;
    }

    private boolean isValidList(List<?> lista) {
        return (lista != null);
    }

    public ConsultaEjecutivaOrdenesBO consultarOrdenesXEstatusoGrupo(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroDeBusqueda,
            AgrupadorEstatusOrdenesEnum grupoSeleccionado) throws ConsultaEjecutivaOrdenesServiceException {
        filtroDeBusqueda.setRule(FiltroConsultaOrdenesRule.ES_FILTRO_VALIDO);
        if (filtroDeBusqueda.getRule().process(filtroDeBusqueda)) {
            logger.info("Se consulta X EstatusoGrupo", consultaBO);
            consultaBO.setLstOrdenesXFiltro(new ArrayList<AgaceOrden>());
            consultaBO.setLstOrdenesTotalEmpleado(new ArrayList<AgaceOrden>());
            consultaBO.setDetalleXEstatusFiltrado(new EnumMap<AgrupadorEstatusOrdenesEnum, Integer>(AgrupadorEstatusOrdenesEnum.class));
            consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());

            List<AgaceOrden> lstOrdenXGrupo;
            filtroDeBusqueda.setEstatusFiltro(consultaBO.getGruposDeEstatusValidos().get(grupoSeleccionado));
            lstOrdenXGrupo = agaceOrdenDAO.consultaOrdenes(filtroDeBusqueda.getFiltroDAO());
            setEstatusGrupo(lstOrdenXGrupo, grupoSeleccionado);
            conteoXDetalleXFiltro(consultaBO, grupoSeleccionado, lstOrdenXGrupo);
            consultaBO.getLstOrdenesXFiltro().addAll(lstOrdenXGrupo);
            validaDuplicidad(consultaBO.getLstOrdenesXFiltro());
            getUnidadAdministrativa(consultaBO.getLstOrdenesXFiltro());
        } else {
            throw new ConsultaEjecutivaOrdenesServiceException(MSG_ERROR_PARAMETRO_FILTRO);
        }
        return consultaBO;
    }

    private void setEstatusGrupo(List<AgaceOrden> lstXGrupo, AgrupadorEstatusOrdenesEnum grupo) {
        if (lstXGrupo != null) {
            for (AgaceOrden orden : lstXGrupo) {
                orden.setEstatusXGrupo(grupo);
            }
        }
    }

    private void conteoXDetalle(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroDeBusqueda) {
        consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        consultaBO.setDetalleXEstatus(new EnumMap<AgrupadorEstatusOrdenesEnum, Integer>(AgrupadorEstatusOrdenesEnum.class));
        consultaBO.setDetalleXSemaforo(new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class));
        consultaBO.setDetalleXMetodo(new EnumMap<TipoMetodoEnum, Integer>(TipoMetodoEnum.class));

        consultaBO.setDetalleXMetodo(countMetodo(consultaBO.getLstMetodosValidos(), filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro(), null, null,
                consultaBO.getGruposDeEstatusValidos()));
        if ((!filtroDeBusqueda.isVisibilidadACPPCE()) && (TipoEmpleadoEnum.FIRMANTE.getBigId().equals(consultaBO.getRolEmpleado().getBigId())
                || TipoEmpleadoEnum.AUDITOR.getBigId().equals(consultaBO.getRolEmpleado().getBigId()))) {
            consultaBO.setDetalleXMetodo(countMetodo(consultaBO.getLstMetodosValidos(), filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro(), consultaBO
                    .getEmpleadoConsulta().getIdEmpleado(), consultaBO.getRolEmpleado().getBigId(), consultaBO.getGruposDeEstatusValidos()));
        }
        consultaBO.setDetalleXSemaforo(countXSemaforo(consultaBO.getLstOrdenResult(), consultaBO.getLstSemaforosValidos()));
    }

    private Map<TipoMetodoEnum, Integer> countMetodo(List<TipoMetodoEnum> lstMetodo, List<AraceDTO> lstUnidAdministrativas, BigDecimal idEmpleado,
            BigDecimal idTipoEmpleado, Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatusValidos) {
        Map<TipoMetodoEnum, Integer> detalleXMetodo = new EnumMap<TipoMetodoEnum, Integer>(TipoMetodoEnum.class);
        if (lstMetodo != null) {
            for (TipoMetodoEnum tipoMetodo : lstMetodo) {
                int conteo;
                conteo = agaceOrdenDAO.countOrdenesXMetodo(tipoMetodo, lstUnidAdministrativas, idEmpleado, idTipoEmpleado, grupoEstatusValidos);
                detalleXMetodo.put(tipoMetodo, conteo);
            }
        }
        return detalleXMetodo;
    }

    private Map<SemaforoEnum, Integer> countXSemaforo(List<AgaceOrden> lstResultOrdenes, List<SemaforoEnum> listaSemaforosValidos) {
        Map<SemaforoEnum, Integer> detalleXSemaforo = new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class);

        for (SemaforoEnum sem : listaSemaforosValidos) {
            detalleXSemaforo.put(sem, 0);
        }

        if (lstResultOrdenes != null && !lstResultOrdenes.isEmpty()) {
            for (AgaceOrden orden : lstResultOrdenes) {
                for (SemaforoEnum sem : SemaforoEnum.values()) {
                    if (sem.getValor() == orden.getSemaforo()) {
                        int tmp = detalleXSemaforo.get(sem);
                        detalleXSemaforo.put(sem, ++tmp);
                    }
                }
            }
        }
        return detalleXSemaforo;
    }

    private void validaDuplicidad(List<AgaceOrden> lstOrigen) {
        List<AgaceOrden> lstResult = new ArrayList<AgaceOrden>();
        if (lstOrigen != null) {
            for (AgaceOrden prop : lstOrigen) {
                if (!lstResult.contains(prop)) {
                    lstResult.add(prop);
                }
            }
            lstOrigen.clear();
            lstOrigen.addAll(lstResult);
        }
    }

    private void getUnidadAdministrativa(List<AgaceOrden> lstOrdenes) {
        if (lstOrdenes != null) {
            for (AgaceOrden orden : lstOrdenes) {
                AraceDTO unidadAdministrativa = MAP_UNIDAD_ADMINISTRATIVA.get(orden.getIdArace().intValue());
                if (unidadAdministrativa != null) {
                    orden.setFececArace(getAraceFromAraceDTO(unidadAdministrativa));
                }
            }
        }
    }

    private void conteoXDetalleXFiltro(ConsultaEjecutivaOrdenesBO consultaBO, AgrupadorEstatusOrdenesEnum grupo, List<AgaceOrden> lstOrdenXGrupo) {
        int valorGrupo = 0;
        if (consultaBO.getDetalleXEstatusFiltrado() != null) {
            consultaBO.setDetalleXEstatusFiltrado(new EnumMap<AgrupadorEstatusOrdenesEnum, Integer>(AgrupadorEstatusOrdenesEnum.class));
        }
        if (consultaBO.getDetalleXEstatusFiltrado().containsKey(grupo)) {
            valorGrupo = consultaBO.getDetalleXEstatus().get(grupo);
        }
        if (lstOrdenXGrupo != null) {
            valorGrupo = valorGrupo + lstOrdenXGrupo.size();
            conteoDetalleSubordinado(consultaBO, lstOrdenXGrupo);
        }
        if (valorGrupo > 0) {
            consultaBO.getDetalleXEstatusFiltrado().put(grupo, valorGrupo);
        }
    }

    private void conteoDetalleSubordinado(ConsultaEjecutivaOrdenesBO consultaBO, List<AgaceOrden> lstOrdenXGrupo) {
        int tipoEmpleado = (int) consultaBO.getRolEmpleado().getId();
        if (consultaBO.getDetalleXEmpleado() == null) {
            consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        }
        if (consultaBO.getLstOrdenesTotalEmpleado().containsAll(lstOrdenXGrupo)) {
            return;
        }
        for (AgaceOrden orden : lstOrdenXGrupo) {
            if (consultaBO.getLstSubordinados() != null && !consultaBO.getLstSubordinados().isEmpty()) {
                for (EmpleadoDTO empleado : consultaBO.getLstSubordinados()) {
                    if (addDetalleXEmpleado(tipoEmpleado, consultaBO.isIsAcppce(), consultaBO.getDetalleXEmpleado(), empleado, orden)
                            && !consultaBO.getLstOrdenesTotalEmpleado().contains(orden)) {
                        consultaBO.getLstOrdenesTotalEmpleado().add(orden);
                    }
                }
            }
        }
    }

    private boolean addDetalleXEmpleado(int tipoConteo, boolean isAcppce, Map<EmpleadoDTO, Integer> mapDetalle, EmpleadoDTO empleado, AgaceOrden orden) {
        int conteo = 0;
        boolean seContabilizo = false;

        if (isAcppce) {
            if (mapDetalle.containsKey(empleado)) {
                conteo = mapDetalle.get(empleado);
                mapDetalle.put(empleado, ++conteo);
            } else {
                mapDetalle.put(empleado, ++conteo);
            }
            seContabilizo = true;
        } else {
            switch (tipoConteo) {
                case CONSULTA_TIPO_CENTRAL:
                    if (mapDetalle.containsKey(empleado)) {
                        conteo = mapDetalle.get(empleado);
                        if (empleado.getIdEmpleado().equals(orden.getIdFirmante())) {
                            mapDetalle.put(empleado, ++conteo);
                            seContabilizo = true;
                        }
                    } else {
                        mapDetalle.put(empleado, conteo);
                        if (empleado.getIdEmpleado().equals(orden.getIdFirmante())) {
                            mapDetalle.put(empleado, ++conteo);
                            seContabilizo = true;
                        }
                    }
                    break;
                case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                    if (mapDetalle.containsKey(empleado)) {
                        conteo = mapDetalle.get(empleado);
                        if (empleado.getIdEmpleado().equals(orden.getIdAuditor())) {
                            mapDetalle.put(empleado, ++conteo);
                            seContabilizo = true;
                        }
                    } else {
                        mapDetalle.put(empleado, conteo);
                        if (empleado.getIdEmpleado().equals(orden.getIdAuditor())) {
                            mapDetalle.put(empleado, ++conteo);
                            seContabilizo = true;
                        }
                    }
                    break;
                case CONSULTA_TIPO_CONSULTA_AUDITOR:
                    break;
            }
        }
        return seContabilizo;
    }

    public FecetPropuesta consultaPropuestaDetalle(String idRegistro) {
        FecetPropuesta detallePropuesta = fecetPropuestaDao.consultaPropuestaDetalle(idRegistro).get(0);
        if (detallePropuesta != null) {
            detallePropuesta.setListaDocumentos(fecetDocExpedienteDao.findWhereIdPropuestaEqualsOrderByFecha(detallePropuesta.getIdPropuesta()));
            detallePropuesta.setLstImpuestos(fecetImpuestoDao.getImpuestosPropuesta(detallePropuesta.getIdPropuesta()));
            detallePropuesta.setListaPapelesTrabajo(consultarPapelesTrabajoService.getPapelesByIdPropuesta(detallePropuesta.getIdPropuesta()));
        }
        return detallePropuesta == null ? new FecetPropuesta() : detallePropuesta;
    }

    public ConsultaEjecutivaOrdenesBO getOrdenesXEmpleadoSemaforoMetodo(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroConsultaOrdenes,
            List<SemaforoEnum> lstSemaforosFiltro) {
        List<AgaceOrden> lstOriginalOrdenes;
        List<AgaceOrden> lstFiltrada = null;

        filtroConsultaOrdenes.setGruposDeEstatusValidos(consultaBO.getGruposDeEstatusValidos());
        lstOriginalOrdenes = agaceOrdenDAO.consultaOrdenes(filtroConsultaOrdenes.getFiltroDAO());
        if (consultaBO.getLstOrdenResult() != null && !consultaBO.getLstOrdenResult().isEmpty()) {
            try {
                for (AgaceOrden orden : lstOriginalOrdenes) {
                    plazosService.asignarFechasNotificacion(orden);
                    if (orden.getFecetDetalleNyV() != null && orden.getFecetDetalleNyV().getFecSurteEfectosNyV() != null) {
                        plazosService.inicializarOrdenConPlazos(orden);
                    } else {
                        orden.setSemaforo(Constantes.ENTERO_DIEZ);
                        orden.setDescripcionPlazoRestante("");
                    }
                    orden.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(orden.getSemaforo()));
                    orden.setDescripcionSemaforo(BusinessUtil.obtenerDescripcionSemaforo(orden.getSemaforo()));
                    orden.setDocOrden(fecetDocOrdenDao.findByFecetOrdenPdf(orden.getIdOrden()));
                    orden.setOficiosFirmados(fecetOficioDao.getOficioByIdOrdenIdEstatus(orden.getIdOrden(),
                            EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(),
                            EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus()));
                }
                lstFiltrada = new ArrayList<AgaceOrden>();
                for (AgaceOrden orden : lstOriginalOrdenes) {
                    if (isValidList(lstSemaforosFiltro)) {
                        for (SemaforoEnum semaforo : lstSemaforosFiltro) {
                            if (orden.getSemaforo() == semaforo.getValor()) {
                                lstFiltrada.add(orden);
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        consultaBO.setLstOrdenesMutiplesFiltros(null);
        consultaBO.setLstOrdenesMutiplesFiltros(lstFiltrada);

        return consultaBO;
    }

    private Integer getIdUnidadAdministrativa(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                return detalle.getCentral().getIdArace();
            }
        }
        return null;
    }

    public boolean tieneOficios(BigDecimal idOrden) {
        List<FecetOficio> oficios = fecetOficioDao.obtenerOficioNoAdministrableByIdOrden(idOrden);
        return oficios != null && !oficios.isEmpty();
    }

    public HSSFWorkbook creaExcel(List<AgaceOrden> listaOrdenes) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");

        int count = 0;

        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        count = insertaCabecera(Constantes.AGACE, sheet, count, style);

        if (listaOrdenes != null && !listaOrdenes.isEmpty()) {
            count = insertListOrdenes(listaOrdenes, sheet, count);
        }
        count++;

        return workbook;
    }

    private int insertaCabecera(String titulo, HSSFSheet sheet, final int count, HSSFCellStyle style) {
        Row row = sheet.createRow(count);
        int contador = count;
        Cell cell = row.createCell(Constantes.CERO);
        cell.setCellValue(titulo);
        cell.setCellStyle(style);

        contador++;
        row = sheet.createRow(contador);
        cell = row.createCell(Constantes.CERO);
        cell.setCellValue("NUMERO ORDEN");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.UNO);
        cell.setCellValue("ID_REGISTRO");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.DOS);
        cell.setCellValue(Constantes.RFC);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.TRES);
        cell.setCellValue("PRIORIDAD");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CUATRO);
        cell.setCellValue(Constantes.METODOSTR);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CINCO);
        cell.setCellValue("DESCRIPCION CIFRAS");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.SEIS);
        cell.setCellValue("CIFRAS");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.SIETE);
        cell.setCellValue("ESTATUS");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.OCHO);
        cell.setCellValue("PLAZO");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_NUEVE);
        cell.setCellValue("SEMAFORO");
        cell.setCellStyle(style);
        contador++;

        return contador;
    }

    private int insertListOrdenes(List<AgaceOrden> ordenes, HSSFSheet sheet, int count) {
        int contador = count;

        for (AgaceOrden orden : ordenes) {

            Row row = sheet.createRow(contador);

            Cell cell = row.createCell(Constantes.CERO);
            cell.setCellValue((orden.getNumeroOrden() != null) ? orden.getNumeroOrden() : "");

            cell = row.createCell(Constantes.UNO);
            cell.setCellValue((orden.getIdRegistroPropuesta()));

            cell = row.createCell(Constantes.DOS);
            cell.setCellValue((orden.getFecetContribuyente().getRfc() != null) ? orden.getFecetContribuyente().getRfc() : "");

            cell = row.createCell(Constantes.TRES);
            cell.setCellValue((orden.getPrioridadSugerida() != null) ? orden.getPrioridadSugerida() : "");

            cell = row.createCell(Constantes.CUATRO);
            cell.setCellValue((orden.getFeceaMetodo() != null && orden.getFeceaMetodo().getAbreviatura() != null)
                    ? orden.getFeceaMetodo().getAbreviatura() : "");

            cell = row.createCell(Constantes.CINCO);
            cell.setCellValue((orden.getDescripcionCifras() != null) ? orden.getDescripcionCifras() : "");

            cell = row.createCell(Constantes.SEIS);
            cell.setCellValue(((orden.getTotalCifras() != null) ? orden.getTotalCifras() : "") + " ");

            cell = row.createCell(Constantes.SIETE);
            cell.setCellValue((orden.getFececEstatus() != null && orden.getFececEstatus().getDescripcion() != null)
                    ? orden.getFececEstatus().getDescripcion().toUpperCase() : "");

            cell = row.createCell(Constantes.OCHO);
            cell.setCellValue(((orden.getDescripcionSemaforo() != null) ? orden.getDescripcionSemaforo() : "") + " ");

            cell = row.createCell(Constantes.ENTERO_NUEVE);
            cell.setCellValue(((orden.getImagenSemaforo() != null) ? orden.getImagenSemaforo() : "") + " ");

            contador++;
        }
        return contador;
    }

}
