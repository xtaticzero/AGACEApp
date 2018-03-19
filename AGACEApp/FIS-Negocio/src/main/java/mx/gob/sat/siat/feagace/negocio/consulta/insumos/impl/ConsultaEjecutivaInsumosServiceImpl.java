/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.insumos.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AciaceEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaInsumoBeanFactory;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.common.reportes.JaspertReportsService;
import mx.gob.sat.siat.feagace.negocio.consulta.ServicioConsultaEjecutivaAgaceAbstract;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.ConsultaEjecutivaInsumosService;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaInsumosServiceException;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ConsultaEjecutivaInsumosRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.FiltroConsultaInsumosRule;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.AciaceSemaforoEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("consultaEjecutivaInsumosService")
public class ConsultaEjecutivaInsumosServiceImpl extends ServicioConsultaEjecutivaAgaceAbstract implements ConsultaEjecutivaInsumosService {

    private static final long serialVersionUID = 3116231263253265631L;

    private static final int ESTATUS_BLANCO_SEMAFORO = 10;

    private static final int CONSULTA_TIPO_CENTRAL = 1;
    private static final int CONSULTA_TIPO_ADMINISTRADOR = 2;
    private static final int CONSULTA_TIPO_SUBADMINISTRADOR = 4;
    private static final int CONSULTA_TIPO_USUARIO_ACIACE = 5;

    @Autowired
    @Qualifier("plazosServiceInsumos")
    private PlazosServiceInsumos plazosServiceInsumos;

    @Autowired
    @Qualifier("fecetInsumoDao")
    private transient FecetInsumoDao insumoDao;

    @Autowired
    @Qualifier("fecetDocretroinsumoDao")
    private transient FecetDocretroinsumoDao docRetroDao;

    @Autowired
    @Qualifier("jaspertReportsService")
    private JaspertReportsService jaspertReportsService;

    @Autowired
    @Qualifier("fececEstatusDao")
    private transient FececEstatusDao fececEstatusDao;

    @Override
    public ConsultaEjecutivaInsumosBO getAccesoEmpleadoAConsultaInsumos(EmpleadoDTO empleado, Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGruposUnidades) throws ConsultaEjecutivaInsumosServiceException {
        ConsultaEjecutivaInsumosBO consultaEjecutivaInsumosBO;
        try {
            consultaEjecutivaInsumosBO = ConsultaInsumoBeanFactory.getBeanConsultaBO(getEmpleadoCompleto(empleado.getRfc()));
            consultaEjecutivaInsumosBO.setLstGruposValidosXRegla(getServiceCatGrupoDeUnidadAdmin().getLstGruposXGeneralXRegla(empleado, ReglaEnum.RNA037));
            if(consultaEjecutivaInsumosBO.getLstGruposValidosXRegla()==null || consultaEjecutivaInsumosBO.getLstGruposValidosXRegla().isEmpty()){
                throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
            }
            //Bloque para pruebas Eliminar despues de obtener datos reales
            
            //
            consultaEjecutivaInsumosBO.setMapGruposUnidades(mapGruposUnidades);
            consultaEjecutivaInsumosBO.setRule(ConsultaEjecutivaInsumosRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_INSUMOS);
            if (consultaEjecutivaInsumosBO.getRule().process(consultaEjecutivaInsumosBO)) {
                consultaEjecutivaInsumosBO.setRule(ConsultaEjecutivaInsumosRule.ESTATUS_DISPONIBLES_CONSULTA_INSUMOS);
                consultaEjecutivaInsumosBO.getRule().process(consultaEjecutivaInsumosBO);

                return consultaEjecutivaInsumosBO;
            } else {
                throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
            }

        } catch (EmpleadoServiceException ex) {
            throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_REGISTRO_EMPLEADO, ex);
        } catch (CatalogosServiceException ex) {
            logger.error("Error al consultar grupos");
            throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_REGISTRO_EMPLEADO, ex);
        }
    }

    @Override
    public ConsultaEjecutivaInsumosBO consultarInsumos(ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtroDeBusqueda)
            throws ConsultaEjecutivaInsumosServiceException {
        if (consultaBO != null && filtroDeBusqueda != null) {
            TipoEmpleadoEnum tipoUsuario = validarJerarquia(consultaBO);
            consultaBO.setRule(ConsultaEjecutivaInsumosRule.ASIGNA_USUARIO_ACIACE_ADMINISTRADOR);
            consultaBO.getRule().process(consultaBO);
            if (consultaBO.getLstSubordinados() != null) {
                Iterator<EmpleadoDTO> subordinadosIterador = consultaBO.getLstSubordinados().iterator();
                EmpleadoDTO currentEmpleado;
                List<BigDecimal> numerosEmpleado = new ArrayList<BigDecimal>();
                while (subordinadosIterador.hasNext()) {
                    currentEmpleado = subordinadosIterador.next();
                    try {
                        if (!validarExistenciaTipoEmpleado(currentEmpleado, tipoUsuario) || numerosEmpleado.contains(currentEmpleado.getIdEmpleado())) {
                            subordinadosIterador.remove();
                            continue;
                        }
                    } catch (EmpleadoServiceException eme) {
                        logger.error(eme.getMessage(), eme);
                        subordinadosIterador.remove();
                        continue;
                    }

                    numerosEmpleado.add(currentEmpleado.getIdEmpleado());
                }
            }
            int tipoConsulta = (int) consultaBO.getRolEmpleado().getId();
            switch (tipoConsulta) {
                case CONSULTA_TIPO_CENTRAL:
                case CONSULTA_TIPO_ADMINISTRADOR:
                case CONSULTA_TIPO_SUBADMINISTRADOR:
                case CONSULTA_TIPO_USUARIO_ACIACE:
                    consultaInsumos(consultaBO, filtroDeBusqueda);
                    return consultaBO;
                default:
                    throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
            }
        }
        throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
    }

    private void consultaInsumos(ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtroDeBusqueda)
            throws ConsultaEjecutivaInsumosServiceException {
        filtroDeBusqueda.setRule(FiltroConsultaInsumosRule.ES_FILTRO_VALIDO);
        if (filtroDeBusqueda.getRule().process(filtroDeBusqueda)) {
            logger.info("Se consulta por filtro", consultaBO);
            validaRegistrosACPPCERegistrado(consultaBO, filtroDeBusqueda);
            consultaBO.setLstInsumoResult(insumoDao.consultaEjecutiva(filtroDeBusqueda.getFiltroDAO()));
            if (consultaBO.getLstInsumoResult() != null && !consultaBO.getLstInsumoResult().isEmpty()) {
                try {
                    plazosServiceInsumos.inicializarInsumoConPlazos(consultaBO.getLstInsumoResult());
                    asignarEstatusACIACE(consultaBO, null);
                    if (filtroDeBusqueda.getDiasConcluirPlazo() != null) {
                        filtroPlazoParaConcluir(consultaBO.getLstInsumoResult(), filtroDeBusqueda.getDiasConcluirPlazo());
                    }
                    conteoXDetalle(consultaBO, filtroDeBusqueda);
                    completarLstInsumos(consultaBO);
                } catch (Exception e) {
                    throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_PLAZOS, e);
                }
            } else {
                consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
                consultaBO.setDetalleXEstatus(new EnumMap<TipoEstatusEnum, Integer>(TipoEstatusEnum.class));
                consultaBO.setDetalleXSemaforo(new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class));
            }
        } else {
            throw new ConsultaEjecutivaInsumosServiceException(MSG_ERROR_PARAMETRO_FILTRO);
        }
    }

    private void conteoXDetalle(ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtroDeBusqueda) {
        int tipoConsulta = (int) consultaBO.getRolEmpleado().getId();
        consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        consultaBO.setDetalleXEstatus(new EnumMap<TipoEstatusEnum, Integer>(TipoEstatusEnum.class));
        consultaBO.setDetalleXSemaforo(new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class));

        switch (tipoConsulta) {
            case CONSULTA_TIPO_CENTRAL:
                consultaBO.setDetalleXEmpleado(conuntEmpleados(consultaBO.getLstSubordinados(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS, filtroDeBusqueda.getEstatusFiltro(),
                        filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro(), consultaBO));
                consultaBO.setDetalleXEstatus(conuntEstadoInsumo(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.CONSULTOR_INSUMOS, consultaBO.getLstEstatusValidos(),
                        filtroDeBusqueda, consultaBO));
                consultaBO.setDetalleXSemaforo(countXSemaforo(consultaBO.getLstInsumoResult(), consultaBO.isIsAciace()));
                break;
            case CONSULTA_TIPO_ADMINISTRADOR:
                consultaBO.setDetalleXEmpleado(conuntEmpleados(consultaBO.getLstSubordinados(), TipoEmpleadoEnum.VALIDADOR_INSUMOS,
                        filtroDeBusqueda.getEstatusFiltro(), filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro(), consultaBO));
                consultaBO.setDetalleXEstatus(conuntEstadoInsumo(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS,
                        consultaBO.getLstEstatusValidos(), filtroDeBusqueda, consultaBO));
                consultaBO.setDetalleXSemaforo(countXSemaforo(consultaBO.getLstInsumoResult(), consultaBO.isIsAciace()));
                break;
            case CONSULTA_TIPO_SUBADMINISTRADOR:
                consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
                consultaBO.setDetalleXEstatus(conuntEstadoInsumo(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.VALIDADOR_INSUMOS,
                        consultaBO.getLstEstatusValidos(), filtroDeBusqueda, consultaBO));
                consultaBO.setDetalleXSemaforo(countXSemaforo(consultaBO.getLstInsumoResult(), consultaBO.isIsAciace()));
                break;
            case CONSULTA_TIPO_USUARIO_ACIACE:
                consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
                consultaBO.setDetalleXEstatus(conuntEstadoInsumo(consultaBO.getEmpleadoConsulta(), TipoEmpleadoEnum.USUARIO_INSUMOS,
                        consultaBO.getLstEstatusValidos(), filtroDeBusqueda, consultaBO));
                consultaBO.setDetalleXSemaforo(countXSemaforo(consultaBO.getLstInsumoResult(), consultaBO.isIsAciace()));
                break;
            default:
                break;
        }

    }

    private Map<EmpleadoDTO, Integer> conuntEmpleados(List<EmpleadoDTO> lstEmpleados, TipoEmpleadoEnum tipoEmpleado, List<TipoEstatusEnum> lstEstatusInsumo,
            List<AraceDTO> lstUnidAdministrativas, ConsultaEjecutivaInsumosBO consultaBO) {
        Map<EmpleadoDTO, Integer> detalleXEmpleado = new HashMap<EmpleadoDTO, Integer>();
        boolean banderaConsulta = consultaBO.isIsAciace();
        if (lstEmpleados == null) {
            return detalleXEmpleado;
        }

        for (EmpleadoDTO emp : lstEmpleados) {
            if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
                conteoEmpleado(consultaBO, detalleXEmpleado, emp);
            } else {
                int conteo;
                conteo = insumoDao.countInsumosXEmpleado(emp.getRfc(), tipoEmpleado, lstEstatusInsumo, lstUnidAdministrativas, banderaConsulta);
                detalleXEmpleado.put(emp, conteo);
            }
        }

        return detalleXEmpleado;
    }

    private void conteoEmpleado(ConsultaEjecutivaInsumosBO consultaBO, Map<EmpleadoDTO, Integer> detalleXEmpleado, EmpleadoDTO emp) {
        List<String> rfcsSubordinados = new ArrayList<String>();
        boolean buscar = true;
        if (TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(consultaBO.getRolEmpleado())) {
            rfcsSubordinados.add(emp.getRfc());
        } else if (TipoEmpleadoEnum.CONSULTOR_INSUMOS.equals(consultaBO.getRolEmpleado()) && !validarExistenciaArace(consultaBO, emp)) {
            List<String> aux = consultaBO.getSubordinadosAciace().get(emp.getRfc());
            if (aux != null) {
                rfcsSubordinados.addAll(aux);
            }
        } else {
            buscar = false;
        }
        if (buscar) {
            detalleXEmpleado.put(emp, 0);
            int conteo = 0;
            for (FecetInsumo insumo : consultaBO.getLstInsumoResult()) {
                if (rfcsSubordinados.contains(insumo.getRfcCreacion())) {
                    conteo = detalleXEmpleado.get(emp);
                    detalleXEmpleado.put(emp, ++conteo);
                }
            }
        }
    }

    private Map<TipoEstatusEnum, Integer> conuntEstadoInsumo(EmpleadoDTO empleado, TipoEmpleadoEnum tipoEmpleado, List<TipoEstatusEnum> lstEstatusInsumo,
            FiltroConsultaInsumos filtroBusqueda, ConsultaEjecutivaInsumosBO consultaBO) {
        Map<TipoEstatusEnum, Integer> detalleXEstado = new EnumMap<TipoEstatusEnum, Integer>(TipoEstatusEnum.class);
        if (lstEstatusInsumo != null) {
            int conteo;
            List<TipoEstatusEnum> estatusDao = new ArrayList<TipoEstatusEnum>();
            if (consultaBO.isIsAciace()) {
                for (AciaceEstatusEnum estatusACIACE : AciaceEstatusEnum.values()) {
                    estatusDao.clear();
                    estatusDao.addAll(AciaceEstatusEnum.obtenerEstatus(estatusACIACE));
                    conteo = insumoDao.countInsumosXEstatus(empleado.getRfc(), tipoEmpleado, estatusDao, filtroBusqueda.getUnidadAdmtvaDesahogoFiltro(),
                            filtroBusqueda.getFiltroDAO(), consultaBO.isIsAciace());
                    detalleXEstado.put(estatusACIACE.getEstatusPadre(), conteo);
                }
            } else {
                for (TipoEstatusEnum tipoInsumo : lstEstatusInsumo) {
                    estatusDao.clear();
                    estatusDao.add(tipoInsumo);
                    conteo = insumoDao.countInsumosXEstatus(empleado.getRfc(), tipoEmpleado, estatusDao, filtroBusqueda.getUnidadAdmtvaDesahogoFiltro(),
                            filtroBusqueda.getFiltroDAO(), consultaBO.isIsAciace());
                    detalleXEstado.put(tipoInsumo, conteo);
                }
            }
        }

        return detalleXEstado;
    }

    private Map<SemaforoEnum, Integer> countXSemaforo(List<FecetInsumo> lstResultInsumos, boolean isAciace) {
        Map<SemaforoEnum, Integer> detalleXSemaforo = new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class);
        if (isAciace) {
            for (AciaceSemaforoEnum sem : AciaceSemaforoEnum.values()) {
                detalleXSemaforo.put(sem.getSemaforoPadre(), 0);
            }
        } else {
            for (SemaforoEnum sem : SemaforoEnum.values()) {
                detalleXSemaforo.put(sem, 0);
            }
        }
        if (lstResultInsumos != null && !lstResultInsumos.isEmpty()) {
            SemaforoEnum sem = null;
            int contador;
            for (FecetInsumo insumo : lstResultInsumos) {
                sem = SemaforoEnum.obtenerSemaforoById(insumo.getSemaforo());
                if (isAciace) {
                    if (SemaforoEnum.SEMAFORO_MORADO.equals(sem)
                            && insumo.getIdEstatus().longValue() == TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION.getId()) {
                        sem = SemaforoEnum.obtenerSemaforoById(insumo.getSemaforo());
                    } else {
                        sem = AciaceSemaforoEnum.obtenerSemaforoById(insumo.getSemaforo());
                    }
                    insumo.setSemaforo(sem.getValor());
                    insumo.setImagenSemaforo(sem.getTextoImagenSemaforo());
                }
                contador = (detalleXSemaforo.get(sem));
                detalleXSemaforo.put(sem, ++contador);
            }
        }
        return detalleXSemaforo;
    }

    private List<FecetInsumo> filtroPlazoParaConcluir(List<FecetInsumo> lstResultInsumos, Integer plazoXConcluir) {
        List<FecetInsumo> lstFiltradaInsumos = new ArrayList<FecetInsumo>();
        if (lstResultInsumos != null && !lstResultInsumos.isEmpty() && plazoXConcluir != null) {
            for (FecetInsumo insumo : lstResultInsumos) {
                if (insumo.getPlazoRestante() == plazoXConcluir) {
                    lstFiltradaInsumos.add(insumo);
                }
            }
            return lstFiltradaInsumos;
        }
        return lstFiltradaInsumos;
    }

    private void detallesXFiltroEstatusSemaforo(ConsultaEjecutivaInsumosBO consultaBO) {
        consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        consultaBO.setDetalleXSemaforoFiltrado(new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class));

        if (isValidList(consultaBO.getLstSubordinados())) {
            boolean existencia = false;
            for (EmpleadoDTO emp : consultaBO.getLstSubordinados()) {
                if (consultaBO.isCentralACPPCE()) {
                    existencia = validarExistenciaArace(consultaBO, emp);
                    if ((!consultaBO.isIsAciace() && !existencia) || (consultaBO.isIsAciace() && existencia)) {
                        continue;
                    }
                }
                consultaBO.getDetalleXEmpleado().put(emp, 0);
            }
            consultaBO.setLstInsumoTotalEmpleado(new ArrayList<FecetInsumo>());
        }

        if (isValidList(consultaBO.getLstInsumosXFiltro())) {
            if (isValidList(consultaBO.getLstSubordinados())) {
                int tipoConsulta = (int) consultaBO.getRolEmpleado().getId();
                addDetalleEmpleado(consultaBO.getLstInsumosXFiltro(), consultaBO.getDetalleXEmpleado(), tipoConsulta, consultaBO,
                        consultaBO.getLstInsumoTotalEmpleado());
            }
            getDetalleXEstatusFiltrado(consultaBO);
            addDetalleSemaforo(consultaBO, consultaBO.getDetalleXSemaforoFiltrado());
        }

    }

    private void getDetalleXEstatusFiltrado(ConsultaEjecutivaInsumosBO consultaBO) {
        boolean flgValido = isValidList(consultaBO.getLstInsumosXFiltro());
        flgValido = flgValido && isValidList(consultaBO.getLstEstatusValidos());
        consultaBO.setDetalleXEstatusFiltrado(new EnumMap<TipoEstatusEnum, Integer>(TipoEstatusEnum.class));
        TipoEstatusEnum estatusInsumo;

        if (flgValido) {
            for (FecetInsumo insumo : consultaBO.getLstInsumosXFiltro()) {
                estatusInsumo = obtenerEstatusEnumXIdEstatus(insumo.getIdEstatus());
                flgValido = consultaBO.getDetalleXEstatusFiltrado().containsKey(estatusInsumo);
                int valor = 0;
                if (flgValido) {
                    valor = consultaBO.getDetalleXEstatusFiltrado().get(estatusInsumo);
                    consultaBO.getDetalleXEstatusFiltrado().put(estatusInsumo, ++valor);
                } else {
                    consultaBO.getDetalleXEstatusFiltrado().put(estatusInsumo, ++valor);
                }
            }
        }

    }

    private static TipoEstatusEnum obtenerEstatusEnumXIdEstatus(BigDecimal idEstatus) {
        Map<BigDecimal, TipoEstatusEnum> mapEstatus = new HashMap<BigDecimal, TipoEstatusEnum>();
        for (TipoEstatusEnum tipoEstatus : TipoEstatusEnum.values()) {
            mapEstatus.put((BigDecimal.valueOf(tipoEstatus.getId())), tipoEstatus);
        }
        if (idEstatus != null) {
            return mapEstatus.get(idEstatus);
        }
        return null;
    }

    private void addDetalleEmpleado(List<FecetInsumo> lstFiltrada, Map<EmpleadoDTO, Integer> mapDetalleEmpleado, int tipoEmpleado,
            ConsultaEjecutivaInsumosBO consultaBO, List<FecetInsumo> lstTotalEmpleados) {
        int valor;
        boolean isAciace = consultaBO.isIsAciace();
        if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
            isAciace = false;
        }
        if (isValidList(lstFiltrada)) {
            for (FecetInsumo insumo : lstFiltrada) {
                boolean flgRfcAdmin = insumo.getRfcAdministrador() != null;
                boolean flgRfcSubAdmin = insumo.getRfcSubadministrador() != null;
                switch (tipoEmpleado) {
                    case CONSULTA_TIPO_CENTRAL:
                        if (isAciace) {
                            for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                                valor = empleadoKey.getValue();
                                empleadoKey.setValue(++valor);
                                lstTotalEmpleados.add(insumo);
                            }
                        } else {
                            for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                                if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
                                    valor = empleadoKey.getValue();
                                    empleadoKey.setValue(++valor);
                                    lstTotalEmpleados.add(insumo);
                                } else if (flgRfcAdmin && insumo.getRfcAdministrador().equals(empleadoKey.getKey().getRfc())) {
                                    valor = empleadoKey.getValue();
                                    empleadoKey.setValue(++valor);
                                    lstTotalEmpleados.add(insumo);
                                }
                            }
                        }
                        break;
                    case CONSULTA_TIPO_ADMINISTRADOR:
                        if (isAciace) {
                            for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                                valor = empleadoKey.getValue();
                                empleadoKey.setValue(++valor);
                                lstTotalEmpleados.add(insumo);
                            }
                        } else {
                            for (Map.Entry<EmpleadoDTO, Integer> empleadoKey : mapDetalleEmpleado.entrySet()) {
                                if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
                                    if (empleadoKey.getKey().getRfc().equals(insumo.getRfcCreacion())) {
                                        valor = empleadoKey.getValue();
                                        empleadoKey.setValue(++valor);
                                        lstTotalEmpleados.add(insumo);
                                    }
                                } else if (flgRfcSubAdmin && insumo.getRfcSubadministrador().equals(empleadoKey.getKey().getRfc())) {
                                    valor = empleadoKey.getValue();
                                    empleadoKey.setValue(++valor);
                                    lstTotalEmpleados.add(insumo);
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    private void addDetalleSemaforo(ConsultaEjecutivaInsumosBO consultaBO, Map<SemaforoEnum, Integer> mapDetalleSemaforo) {
        if (isValidList(consultaBO.getLstInsumosXFiltro()) && mapDetalleSemaforo != null) {
            for (FecetInsumo insumo : consultaBO.getLstInsumosXFiltro()) {
                int valor = 0;
                if (mapDetalleSemaforo.containsKey(getSemaforoXId(insumo.getSemaforo()))) {
                    valor = mapDetalleSemaforo.get(getSemaforoXId(insumo.getSemaforo()));
                    mapDetalleSemaforo.put(getSemaforoXId(insumo.getSemaforo()), ++valor);
                } else {
                    mapDetalleSemaforo.put(getSemaforoXId(insumo.getSemaforo()), ++valor);
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

    private List<FecetInsumo> getInsumosXSemaforo(List<FecetInsumo> listaXFiltrar, SemaforoEnum tipoSemaforo) {
        List<FecetInsumo> listaFiltrada = new ArrayList<FecetInsumo>();
        if (isValidList(listaXFiltrar)) {
            for (FecetInsumo insumo : listaXFiltrar) {
                if (tipoSemaforo.getValor() == insumo.getSemaforo()) {
                    listaFiltrada.add(insumo);
                }
            }
        }
        return listaFiltrada;
    }

    private List<FecetInsumo> getInsumosXEstatus(List<FecetInsumo> listaXFiltrar, TipoEstatusEnum tipoEstatus) {
        List<FecetInsumo> listaFiltrada = new ArrayList<FecetInsumo>();
        if (isValidList(listaXFiltrar)) {
            for (FecetInsumo insumo : listaXFiltrar) {
                if (insumo.getIdEstatus().longValue() == tipoEstatus.getId()) {
                    listaFiltrada.add(insumo);
                }
            }
        }
        return listaFiltrada;
    }

    @Override
    public ConsultaEjecutivaInsumosBO getInsumosXSemaforoEstatus(ConsultaEjecutivaInsumosBO consultaBO, SemaforoEnum tipoSemaforo,
            TipoEstatusEnum tipoEstatus) {
        List<FecetInsumo> lstFiltrada = consultaBO.getLstInsumoResult();
        asignarEstatusACIACE(consultaBO, lstFiltrada);
        if (tipoSemaforo != null) {
            lstFiltrada = getInsumosXSemaforo(lstFiltrada, tipoSemaforo);
        }
        if (tipoEstatus != null) {
            lstFiltrada = getInsumosXEstatus(lstFiltrada, tipoEstatus);
        }

        consultaBO.setLstInsumosXFiltro(lstFiltrada);
        detallesXFiltroEstatusSemaforo(consultaBO);

        return consultaBO;
    }

    @Override
    public ConsultaEjecutivaInsumosBO getInsumosPlazoXConcluir(ConsultaEjecutivaInsumosBO consultaBO, Integer numeroDias) {
        consultaBO.setLstInsumosXFiltro(new ArrayList<FecetInsumo>());
        if (consultaBO.getLstInsumoResult() != null && !consultaBO.getLstInsumoResult().isEmpty()) {
            consultaBO.setLstInsumosXFiltro(filtroPlazoParaConcluir(consultaBO.getLstInsumoResult(), numeroDias));
        }
        detallesXFiltroEstatusSemaforo(consultaBO);
        return consultaBO;
    }

    @Override
    public ConsultaEjecutivaInsumosBO getInsumosXEmpleadoSemaforoEstadoPlazo(ConsultaEjecutivaInsumosBO consultaBO, List<AraceDTO> unidadesAdmin,
            EmpleadoDTO empleado, List<SemaforoEnum> lstSemaforos, List<TipoEstatusEnum> lstEstatus, Integer numeroDias) {
        List<FecetInsumo> lstOriginalInsumos;
        List<FecetInsumo> lstFiltrada = null;

        FiltroConsultaInsumos filtroConsultaInsumos = new FiltroConsultaInsumos();

        filtroConsultaInsumos.setUnidadAdmtvaDesahogoFiltro(unidadesAdmin);
        filtroConsultaInsumos.setEmpleadoConsultaFiltro(empleado);
        filtroConsultaInsumos.setEstatusFiltro(lstEstatus);

        validaRegistrosACPPCERegistrado(consultaBO, filtroConsultaInsumos);

        lstOriginalInsumos = insumoDao.consultaEjecutiva(filtroConsultaInsumos.getFiltroDAO());
        boolean esValidoSemaforo;
        boolean esValidoNumeroDias = !(numeroDias != null);
        try {
            if (isValidList(lstOriginalInsumos)) {
                plazosServiceInsumos.inicializarInsumoConPlazos(lstOriginalInsumos);
                asignarEstatusACIACE(consultaBO, lstOriginalInsumos);
                lstFiltrada = new ArrayList<FecetInsumo>();
                for (FecetInsumo insumo : lstOriginalInsumos) {
                    esValidoSemaforo = false;
                    if (isValidList(lstSemaforos)) {
                        esValidoSemaforo = lstSemaforos.contains(SemaforoEnum.obtenerSemaforoById(insumo.getSemaforo()));
                    }

                    if (numeroDias != null) {
                        esValidoNumeroDias = insumo.getPlazoRestante() == numeroDias;
                    }

                    if (esValidoNumeroDias && esValidoSemaforo) {
                        if (filtroConsultaInsumos.isCentralACPPCEReg()
                                && !filtroConsultaInsumos.getFiltroDAO().getRfcUsuarioACIACE().contains(insumo.getRfcCreacion())) {
                            continue;
                        } else {
                            lstFiltrada.add(insumo);
                        }
                    }
                }

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        consultaBO.setLstInsumoMutiplesFiltros(null);
        consultaBO.setLstInsumoMutiplesFiltros(lstFiltrada);
        completarLstInsumos(consultaBO);
        return consultaBO;

    }

    private boolean isValidList(List<?> lista) {
        return (lista != null);
    }

    @Override
    public ConsultaEjecutivaInsumosBO consultaHistoricoInsumos(ConsultaEjecutivaInsumosBO consultaBO, FecetInsumo insumoAConsultar) {
        boolean flgIsValid = insumoAConsultar != null && insumoAConsultar.getIdInsumo() != null;
        if (flgIsValid) {
            insumoDao.getRetroalimemtacionesDeInsumo(insumoAConsultar);
            if (insumoAConsultar != null && isValidList(insumoAConsultar.getLstRetroalimentacion())) {
                List<FecetDocretroinsumo> lstDoctos;
                for (RetroalimentacionInsumoDTO retro : insumoAConsultar.getLstRetroalimentacion()) {
                    lstDoctos = docRetroDao.obtenerRetroalimentacionByTipoEmpleado(retro.getIdRetroalimentacionInsumo(),
                            BigDecimal.valueOf(TipoEmpleadoEnum.USUARIO_INSUMOS.getId()));
                    retro.setLstDocsRetroalimentacion(lstDoctos != null ? lstDoctos : new ArrayList<FecetDocretroinsumo>());
                    lstDoctos = docRetroDao.obtenerRetroalimentacionByTipoEmpleado(retro.getIdRetroalimentacionInsumo(),
                            BigDecimal.valueOf(TipoEmpleadoEnum.VALIDADOR_INSUMOS.getId()));
                    retro.setLstDocsSolicitudRetroalimentacion(lstDoctos != null ? lstDoctos : new ArrayList<FecetDocretroinsumo>());
                    retro.setNombreUnidadAdministrativa(insumoAConsultar.getFececUnidadAdministrativa().getNombre());
                }
            }
            insumoDao.getRechazoDeInsumo(insumoAConsultar);
            if (insumoAConsultar != null) {
                consultaBO.setInsumoDetalle(insumoAConsultar);
            }
        }
        return consultaBO;
    }

    @Override
    @PistaAuditoria
    public String injectarPistaDetalleInsumo(FecetInsumo insumoAConsultar) {
        logger.debug("Injectando Pista Auditoria DetalleInsumo");
        return insumoAConsultar.getIdRegistro();
    }

    @Override
    public void generarReporte(List<FecetInsumo> registros, Map<String, Object> parametros, OutputStream salida) throws NoSeGeneroReporteException {
        List<Map<String, Object>> elementos = new ArrayList<Map<String, Object>>();

        Map<String, Object> elemento = null;
        int contador = 0;
        for (final FecetInsumo insumo : registros) {
            elemento = new HashMap<String, Object>();
            elemento.put("contador", "" + (++contador));
            elemento.put("nombreRazonSocial", insumo.getFecetContribuyente().getNombre());
            elemento.put("subPrograma", insumo.getFececSubprograma().getDescripcion());
            elemento.put("idRegistro", insumo.getIdRegistro());
            elemento.put("rfc", insumo.getFecetContribuyente().getRfc());
            elemento.put("sector", insumo.getFececSector().getDescripcion());
            elemento.put("prioridad", insumo.getPrioridadDto().getValor());
            elemento.put("estatus", insumo.getFececEstatus().getDescripcion());
            elemento.put("unidadAdmin", insumo.getFececUnidadAdministrativa().getNombre());
            if (insumo.getSemaforo() == ESTATUS_BLANCO_SEMAFORO) {
                elemento.put("plazo", insumo.getDiasDespuesDePlazoDescripcion());
            } else {
                elemento.put("plazo", insumo.getDescripcionPlazoRestante());
            }
            String semaforo = insumo.getImagenSemaforo() != null ? insumo.getImagenSemaforo() : "";
            semaforo = semaforo.replace("semaforo-", "").toUpperCase();
            elemento.put("semaforo", semaforo);
            elementos.add(elemento);
        }

        byte[] reporte;
        try {
            String rutaAbsoluta = ConstantesReportes.PATH_REPORTE;
            rutaAbsoluta = rutaAbsoluta.concat(ConstantesReportes.REPORTE_INSUMOS);
            rutaAbsoluta = rutaAbsoluta.concat(ConstantesReportes.REPORTE_CONSULTA_ESTATUS_EXCEL);
            reporte = jaspertReportsService.makeReport(rutaAbsoluta, "ConsultaEstatus.xlsx", parametros, elementos);

            salida.write(reporte);
            salida.flush();
        } catch (Exception e) {
            throw new NoSeGeneroReporteException("***Validar ", e);
        }
    }

    private void asignarEstatusACIACE(ConsultaEjecutivaInsumosBO consultaBO, List<FecetInsumo> listaOriginal) {
        if (consultaBO.isIsAciace()) {
            List<TipoEstatusEnum> estatusPadre = AciaceEstatusEnum.obtenerEstatusPadre();
            List<FececEstatus> listEstatus = fececEstatusDao.getEstatusByTipoEstatus(estatusPadre.toArray(new TipoEstatusEnum[estatusPadre.size()]));
            TipoEstatusEnum estatusNuevo;
            SemaforoEnum semaforoNuevo;
            List<FecetInsumo> listaValida;
            if (listaOriginal != null) {
                listaValida = listaOriginal;
            } else {
                listaValida = consultaBO.getLstInsumoResult();
            }
            for (FecetInsumo insumo : listaValida) {
                if (AciaceEstatusEnum.isHijo(insumo.getIdEstatus().longValue())) {
                    estatusNuevo = AciaceEstatusEnum.obtenerEstatusPadre(insumo.getIdEstatus().longValue());
                    if (estatusNuevo == null) {
                        continue;
                    }
                    insumo.setIdEstatus(BigDecimal.valueOf(estatusNuevo.getId()));
                    for (FececEstatus aux : listEstatus) {
                        if (aux.getIdEstatus().equals(insumo.getIdEstatus())) {
                            insumo.setFececEstatus(aux);
                            break;
                        }
                    }
                }
                if (AciaceSemaforoEnum.isHijo(insumo.getSemaforo())) {
                    if (SemaforoEnum.SEMAFORO_MORADO.equals(SemaforoEnum.obtenerSemaforoById(insumo.getSemaforo()))
                            && insumo.getIdEstatus().longValue() == TipoEstatusEnum.SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION.getId()) {
                        continue;
                    }
                    semaforoNuevo = AciaceSemaforoEnum.obtenerSemaforoById(insumo.getSemaforo());
                    insumo.setSemaforo(semaforoNuevo.getValor());
                    insumo.setImagenSemaforo(semaforoNuevo.getTextoImagenSemaforo());
                }
            }
        }
    }

    private void validaRegistrosACPPCERegistrado(ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtroDeBusqueda) {
        filtroDeBusqueda.setRfcUsuarioACIACE(new ArrayList<String>());
        filtroDeBusqueda.setCentralACPPCEReg(false);
        consultaBO.getSubordinadosAciace().clear();
        if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
            List<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
            if (TipoEmpleadoEnum.CONSULTOR_INSUMOS.equals(consultaBO.getRolEmpleado())) {
                EmpleadoDTO registroEmpleadoAux = null;
                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> mapaEmpleadosCadena;
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> empleadoAdministrador;
                List<EmpleadoDTO> subordinadosList;
                for (EmpleadoDTO empleadoSubor : consultaBO.getLstSubordinados()) {
                    if (!esAdministrador(empleadoSubor) || validarExistenciaArace(consultaBO, empleadoSubor)) {
                        continue;
                    }
                    registroEmpleadoAux = obtenerEmpleadoTipo(empleadoSubor.getRfc(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                    if (registroEmpleadoAux == null || registroEmpleadoAux.getSubordinados() == null || registroEmpleadoAux.getSubordinados().isEmpty()) {
                        continue;
                    }
                    mapaEmpleadosCadena = registroEmpleadoAux.getSubordinados();
                    empleadoAdministrador = mapaEmpleadosCadena.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                    if (empleadoAdministrador == null || empleadoAdministrador.isEmpty()) {
                        continue;
                    }
                    subordinadosList = empleadoAdministrador.get(TipoEmpleadoEnum.USUARIO_INSUMOS);
                    if (subordinadosList == null) {
                        subordinadosList = new ArrayList<EmpleadoDTO>();
                    }
                    consultaBO.getSubordinadosAciace().put(empleadoSubor.getRfc(), convertirMap(subordinadosList));
                    empleados.addAll(subordinadosList);
                }
            } else {
                empleados.addAll(consultaBO.getLstSubordinados());
            }
            filtroDeBusqueda.setCentralACPPCEReg(true);
            if (empleados != null && !empleados.isEmpty()) {
                for (EmpleadoDTO empleado : empleados) {
                    filtroDeBusqueda.getRfcUsuarioACIACE().add(empleado.getRfc());
                }
            }
        }
    }

    private List<String> convertirMap(List<EmpleadoDTO> registrosAux) {
        List<String> subordinados = new ArrayList<String>();
        if (registrosAux != null) {
            EmpleadoDTO empleadoActual;
            for (Iterator<EmpleadoDTO> empleadoIterator = registrosAux.iterator(); empleadoIterator.hasNext();) {
                try {
                    empleadoActual = empleadoIterator.next();
                    if (validarExistenciaTipoEmpleado(empleadoActual, TipoEmpleadoEnum.USUARIO_INSUMOS)) {
                        subordinados.add(empleadoActual.getRfc());
                    } else {
                        empleadoIterator.remove();
                    }
                } catch (EmpleadoServiceException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
        return subordinados;
    }

    private boolean esAdministrador(EmpleadoDTO empleadoSubor) {
        boolean resultado = false;
        if (empleadoSubor.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO detalle : empleadoSubor.getDetalleEmpleado()) {
                if (TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(detalle.getTipoEmpleado())) {
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }

    private boolean validarExistenciaArace(ConsultaEjecutivaInsumosBO consultaBO, EmpleadoDTO emp) {
        TipoEmpleadoEnum rolBusqueda;
        switch (consultaBO.getRolEmpleado()) {
            case CONSULTOR_INSUMOS:
                rolBusqueda = TipoEmpleadoEnum.ASIGNADOR_INSUMOS;
                break;
            case ASIGNADOR_INSUMOS:
                rolBusqueda = TipoEmpleadoEnum.VALIDADOR_INSUMOS;
                if ((consultaBO.isCentralACPPCE() && consultaBO.isIsAciace())) {
                    rolBusqueda = TipoEmpleadoEnum.USUARIO_INSUMOS;
                }
                break;
            case VALIDADOR_INSUMOS:
            default:
                rolBusqueda = TipoEmpleadoEnum.USUARIO_INSUMOS;
                break;
        }
        boolean resultado = false;
        if (!TipoEmpleadoEnum.USUARIO_INSUMOS.equals(rolBusqueda)) {
            for (DetalleEmpleadoDTO detalle : emp.getDetalleEmpleado()) {
                if (rolBusqueda.equals(detalle.getTipoEmpleado())) {
                    completarLstDesahogo(emp, MAP_UNIDAD_ADMINISTRATIVA);
                    resultado = detalle.getLstAraces() != null && !detalle.getLstAraces().isEmpty();
                    break;
                }

            }
        }
        return resultado;
    }

    private void completarLstInsumos(ConsultaEjecutivaInsumosBO consultaBO) {
        if (consultaBO != null) {
            fillUnidadAdministrativa(consultaBO.getLstInsumoResult());
            fillUnidadAdministrativa(consultaBO.getLstInsumoMutiplesFiltros());
            fillUnidadAdministrativa(consultaBO.getLstInsumoTotalEmpleado());
            fillUnidadAdministrativa(consultaBO.getLstInsumosXFiltro());
        }
    }

    private TipoEmpleadoEnum validarJerarquia(ConsultaEjecutivaInsumosBO consultaBO) {
        TipoEmpleadoEnum resultado;
        switch (consultaBO.getRolEmpleado()) {
            case CONSULTOR_INSUMOS:
                resultado = TipoEmpleadoEnum.ASIGNADOR_INSUMOS;
                break;
            case ASIGNADOR_INSUMOS:
                resultado = TipoEmpleadoEnum.VALIDADOR_INSUMOS;
                if (consultaBO.isCentralACPPCE() && consultaBO.isIsAciace()) {
                    resultado = TipoEmpleadoEnum.USUARIO_INSUMOS;
                }
                break;
            case VALIDADOR_INSUMOS:
            default:
                resultado = TipoEmpleadoEnum.USUARIO_INSUMOS;
                break;
        }
        return resultado;
    }
}
