/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.propuestas.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaConsultaGeneralDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasRule;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEstatusAccionesBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaPropuestaBeanFactory;
import mx.gob.sat.siat.feagace.negocio.consulta.ServicioConsultaEjecutivaAgaceAbstract;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.ConsultaEjecutivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaPropuestasServiceException;
import mx.gob.sat.siat.feagace.negocio.rules.impl.FiltroConsultaPropuestasRule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("consultaEjecutivaPropuestasService")
public class ConsultaEjecutivaPropuestasServiceImpl extends ServicioConsultaEjecutivaAgaceAbstract
        implements ConsultaEjecutivaPropuestasService {

    private static final long serialVersionUID = 5085709223392635345L;

    private static final int CONSULTA_TIPO_CENTRAL = 1;
    private static final int CONSULTA_TIPO_ADMINISTRADOR = 2;
    private static final int CONSULTA_TIPO_SUBADMINISTRADOR = 4;
    private static final int CONSULTA_TIPO_CONSULTA_AUDITOR = 6;
    private static final int CONSULTA_TIPO_CONSULTA_FIRMANTE = 7;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FecetPropuestaConsultaGeneralDao fecetPropuestaConsultaGeneralDao;

    @Override
    public ConsultaEjecutivaPropuestasBO getAccesoEmpleadoAConsultaPropuestas(EmpleadoDTO empleado) throws ConsultaEjecutivaPropuestasServiceException {
        ConsultaEjecutivaPropuestasBO consultaEjecutivaPropuestaBO;
        try {
            consultaEjecutivaPropuestaBO = ConsultaPropuestaBeanFactory.getBeanConsultaBO(getEmpleadoCompleto(empleado.getRfc()));
            consultaEjecutivaPropuestaBO.setLstUnidadesAdministrativasDesahogo(getUnidadesAdministrativas(empleado));
            consultaEjecutivaPropuestaBO.setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
            if (consultaEjecutivaPropuestaBO.getRule().process(consultaEjecutivaPropuestaBO)) {
                consultaEjecutivaPropuestaBO.setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_CONSULTA_PROPUESTAS);
                consultaEjecutivaPropuestaBO.getRule().process(consultaEjecutivaPropuestaBO);
                consultaEjecutivaPropuestaBO.setRule(ConsultaEjecutivaPropuestasRule.ES_ACPPCE_REGIONAL);
                if (consultaEjecutivaPropuestaBO.getRule().process(consultaEjecutivaPropuestaBO)) {
                    consultaEjecutivaPropuestaBO.setLstProgramadores(getLstSubordinadosPorRelacionEmpleado(consultaEjecutivaPropuestaBO.getEmpleadoConsulta(), consultaEjecutivaPropuestaBO.getRolEmpleado(), TipoEmpleadoEnum.PROGRAMADOR));
                    List<EmpleadoDTO> subadministradores = getLstSubordinadosPorRelacionEmpleado(consultaEjecutivaPropuestaBO.getEmpleadoConsulta(), consultaEjecutivaPropuestaBO.getRolEmpleado(), TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                    consultaEjecutivaPropuestaBO.setLstSubadministradores(subadministradores != null ? subadministradores : new ArrayList<EmpleadoDTO>());
                }
                return consultaEjecutivaPropuestaBO;
            } else {
                throw new ConsultaEjecutivaPropuestasServiceException(ConsultaEjecutivaPropuestasService.MSG_ERROR_USUARIO_SIN_PRIVILEGIOS);
            }

        } catch (EmpleadoServiceException ex) {
            throw new ConsultaEjecutivaPropuestasServiceException(ConsultaEjecutivaPropuestasService.MSG_ERROR_REGISTRO_EMPLEADO, ex);
        }
    }

    @Override
    public ConsultaEjecutivaPropuestasBO consultarPropuestas(ConsultaEjecutivaPropuestasBO consultaBO, FiltroConsultaPropuestas filtroDeBusqueda) throws ConsultaEjecutivaPropuestasServiceException {
        filtroDeBusqueda.setRule(FiltroConsultaPropuestasRule.ES_FILTRO_VALIDO);
        if (filtroDeBusqueda.getRule().process(filtroDeBusqueda)) {
            logger.info("Se consulta por filtro", consultaBO);
            filtroDeBusqueda.setLstProgramadores(consultaBO.getLstProgramadores());
            filtroDeBusqueda.setLstSubadministradores(consultaBO.getLstSubadministradores());

            if (tieneRolDeSubadministrador(consultaBO.getEmpleadoConsulta())) {
                filtroDeBusqueda.getLstSubadministradores().add(consultaBO.getEmpleadoConsulta());
            }

            filtroDeBusqueda.setProgramacion(consultaBO.isProgramacion());
            consultaBO.setLstPropuestasResult(new ArrayList<FecetPropuesta>());
            consultaBO.setLstPropuestasTotalEmpleado(new ArrayList<FecetPropuesta>());
            consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
            consultaBO.setDetalleXEstatus(new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
            List<FecetPropuesta> lstPropuestaXGrupo;

            for (Entry<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> grupoEstatus : consultaBO.getGruposDeEstatusValidos().entrySet()) {
                filtroDeBusqueda.setLstAccionesPropuesta(grupoEstatus.getValue().getAcciones());
                filtroDeBusqueda.setEstatusFiltro(grupoEstatus.getValue().getEstatus());
                lstPropuestaXGrupo = fecetPropuestaDao.consultaEjecutivaPropuestasXFiltro(filtroDeBusqueda.getFiltroDAO(), grupoEstatus.getKey());
                setEstatusGrupo(lstPropuestaXGrupo, grupoEstatus.getKey());
                conteoXDetalle(consultaBO, grupoEstatus.getKey(), lstPropuestaXGrupo);
                consultaBO.getLstPropuestasResult().addAll(lstPropuestaXGrupo);
                if (consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS) || consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS) || consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                    lstPropuestaXGrupo = fecetPropuestaDao.consultaEjecutivaPropuestasXFiltroCentralRegional(filtroDeBusqueda.getFiltroDAO(), grupoEstatus.getKey());
                    setEstatusGrupo(lstPropuestaXGrupo, grupoEstatus.getKey());
                    conteoXDetalle(consultaBO, grupoEstatus.getKey(), lstPropuestaXGrupo);
                    consultaBO.getLstPropuestasResult().addAll(lstPropuestaXGrupo);
                }
            }
            validaDuplicidad(consultaBO.getLstPropuestasResult());
            fillUnidadAdministrativa(consultaBO.getLstPropuestasResult());
        } else {
            throw new ConsultaEjecutivaPropuestasServiceException(MSG_ERROR_PARAMETRO_FILTRO);
        }
        return consultaBO;
    }

    @Override
    public void consultarEstatusPropuestasSuper(ConsultaEjecutivaPropuestasBO consultaBO, FiltroPropuestas filtroDeBusqueda) {
        for (Entry<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> grupoEstatus : consultaBO.getGruposDeEstatusValidos().entrySet()) {
            filtroDeBusqueda.setEstatusFiltro(grupoEstatus.getValue().getEstatus());
            filtroDeBusqueda.setLstAccionesPropuesta(grupoEstatus.getValue().getAcciones());
            List<FecetPropuesta> listaPropuestasXGrupo = fecetPropuestaConsultaGeneralDao.contarEstatusGrupo(grupoEstatus.getKey(), filtroDeBusqueda);
            validaDuplicidadXEstatus(listaPropuestasXGrupo, consultaBO.getLstPropuestasResult());
            setEstatusGrupo(listaPropuestasXGrupo, grupoEstatus.getKey());
            conteoXDetalle(consultaBO, grupoEstatus.getKey(), listaPropuestasXGrupo);
            consultaBO.getLstPropuestasResult().addAll(listaPropuestasXGrupo);
        }
        fillUnidadAdministrativa(consultaBO.getLstPropuestasResult());

    }

    private void setEstatusGrupo(List<FecetPropuesta> lstXGrupo, AgrupadorEstatusPropuestasEnum grupo) {
        if (lstXGrupo != null) {
            for (FecetPropuesta prop : lstXGrupo) {
                prop.setEstatusXGrupo(grupo);
            }
        }
    }

    private void conteoXDetalle(ConsultaEjecutivaPropuestasBO consultaBO, AgrupadorEstatusPropuestasEnum grupo, List<FecetPropuesta> lstPropuestaXGrupo) {
        int valorGrupo = 0;
        boolean flgGrupoExiste = consultaBO.getDetalleXEstatus().containsKey(grupo);
        if (!flgGrupoExiste) {
            consultaBO.getDetalleXEstatus().put(grupo, valorGrupo);
        }

        if (consultaBO.getLstPropuestasResult().containsAll(lstPropuestaXGrupo)) {
            return;
        }
        if (consultaBO.getDetalleXEstatus() == null) {
            consultaBO.setDetalleXEstatus(new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        }
        if (consultaBO.getDetalleXEstatus().containsKey(grupo)) {
            valorGrupo = consultaBO.getDetalleXEstatus().get(grupo);
        }
        if (lstPropuestaXGrupo != null) {
            valorGrupo = valorGrupo + lstPropuestaXGrupo.size();
        }
        consultaBO.getDetalleXEstatus().put(grupo, valorGrupo);
    }

    @Override
    public ConsultaEjecutivaPropuestasBO consultarPropuestasXEstatusoGrupo(ConsultaEjecutivaPropuestasBO consultaBO, FiltroConsultaPropuestas filtroDeBusqueda, AgrupadorEstatusPropuestasEnum grupoSeleccionado) throws ConsultaEjecutivaPropuestasServiceException {
        filtroDeBusqueda.setRule(FiltroConsultaPropuestasRule.ES_FILTRO_VALIDO);
        if (filtroDeBusqueda.getRule().process(filtroDeBusqueda)) {
            try {
                logger.info("Se consulta X EstatusoGrupo", consultaBO);
                filtroDeBusqueda.setLstProgramadores(consultaBO.getLstProgramadores());
                filtroDeBusqueda.setLstSubadministradores(consultaBO.getLstSubadministradores());
                filtroDeBusqueda.setProgramacion(consultaBO.isProgramacion());
                consultaBO.setLstPropuestasXFiltro(new ArrayList<FecetPropuesta>());
                consultaBO.setLstPropuestasTotalEmpleado(new ArrayList<FecetPropuesta>());
                consultaBO.setDetalleXEstatusFiltrado(new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
                consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());

                List<FecetPropuesta> lstPropuestaXGrupo;
                filtroDeBusqueda.setEstatusFiltro(consultaBO.getGruposDeEstatusValidos().get(grupoSeleccionado).getEstatus());
                filtroDeBusqueda.setLstAccionesPropuesta(consultaBO.getGruposDeEstatusValidos().get(grupoSeleccionado).getAcciones());
                lstPropuestaXGrupo = fecetPropuestaDao.consultaEjecutivaPropuestasXFiltro(filtroDeBusqueda.getFiltroDAO(), grupoSeleccionado);
                setEstatusGrupo(lstPropuestaXGrupo, grupoSeleccionado);
                conteoXDetalleXFiltro(consultaBO, grupoSeleccionado, lstPropuestaXGrupo, filtroDeBusqueda);
                consultaBO.getLstPropuestasXFiltro().addAll(lstPropuestaXGrupo);
                if ((consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS) || consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS) || consultaBO.getRolEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) && consultaBO.isProgramacion()) {
                    lstPropuestaXGrupo = fecetPropuestaDao.consultaEjecutivaPropuestasXFiltroCentralRegional(filtroDeBusqueda.getFiltroDAO(), grupoSeleccionado);
                    setEstatusGrupo(lstPropuestaXGrupo, grupoSeleccionado);
                    conteoXDetalleXFiltro(consultaBO, grupoSeleccionado, lstPropuestaXGrupo, filtroDeBusqueda);
                    consultaBO.getLstPropuestasXFiltro().addAll(lstPropuestaXGrupo);
                }
                validaDuplicidad(consultaBO.getLstPropuestasXFiltro());
                fillUnidadAdministrativa(consultaBO.getLstPropuestasXFiltro());
                fillUnidadAdministrativa(consultaBO.getLstPropuestasTotalEmpleado());
                fillUnidadAdministrativa(consultaBO.getLstPropuestasMutiplesFiltros());
                fillUnidadAdministrativa(consultaBO.getLstPropuestasResult());
                setEstatusGrupo(consultaBO.getLstPropuestasXFiltro(), grupoSeleccionado);
                setEstatusGrupo(consultaBO.getLstPropuestasTotalEmpleado(), grupoSeleccionado);

            } catch (EmpleadoServiceException ex) {
                logger.error(ex.getMessage(), ex);
                throw new ConsultaEjecutivaPropuestasServiceException(MSG_ERROR_CONSULTA_CLIENTE, ex);
            }
        } else {
            throw new ConsultaEjecutivaPropuestasServiceException(MSG_ERROR_PARAMETRO_FILTRO);
        }
        return consultaBO;
    }

    private void conteoXDetalleXFiltro(ConsultaEjecutivaPropuestasBO consultaBO, AgrupadorEstatusPropuestasEnum grupo, List<FecetPropuesta> lstPropuestaXGrupo, FiltroConsultaPropuestas filtroDeBusqueda) throws EmpleadoServiceException {
        int valorGrupo = 0;
        if (consultaBO.getDetalleXEstatusFiltrado() != null) {
            consultaBO.setDetalleXEstatusFiltrado(new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        }
        if (consultaBO.getDetalleXEstatusFiltrado().containsKey(grupo)) {
            valorGrupo = consultaBO.getDetalleXEstatus().get(grupo);
        }
        if (lstPropuestaXGrupo != null) {
            valorGrupo = valorGrupo + lstPropuestaXGrupo.size();
            conteoDetalleSubordinado(consultaBO, lstPropuestaXGrupo, grupo, filtroDeBusqueda);
        }
        if (valorGrupo > 0) {
            consultaBO.getDetalleXEstatusFiltrado().put(grupo, valorGrupo);
        }
    }

    private void conteoDetalleSubordinado(ConsultaEjecutivaPropuestasBO consultaBO,
            List<FecetPropuesta> lstPropuestaXGrupo,
            AgrupadorEstatusPropuestasEnum grupo,
            FiltroConsultaPropuestas filtroDeBusqueda) throws EmpleadoServiceException {
        int tipoEmpleado = (int) consultaBO.getRolEmpleado().getId();
        if (consultaBO.getDetalleXEmpleado() == null) {
            consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        }

        if (consultaBO.getLstPropuestasTotalEmpleado().containsAll(lstPropuestaXGrupo)) {
            return;
        }

        if (consultaBO.isIsAcppce() || consultaBO.isProgramacion()) {
            for (EmpleadoDTO empleado : consultaBO.getLstSubordinados()) {
                if (grupo.equals(AgrupadorEstatusPropuestasEnum.PROPUESTAS_CANCELADAS)) {
                    countXProgramadoresSubordinado(consultaBO,
                            empleado,
                            grupo,
                            consultaBO.getRolEmpleado(),
                            filtroDeBusqueda.getLstAccionesPropuesta(),
                            filtroDeBusqueda);
                } else {
                    countXProgramadoresSubordinado(consultaBO,
                            empleado,
                            grupo,
                            consultaBO.getRolEmpleado(),
                            null,
                            filtroDeBusqueda);
                }
            }
        } else {
            for (FecetPropuesta propuesta : lstPropuestaXGrupo) {
                if (consultaBO.getLstSubordinados() != null && !consultaBO.getLstSubordinados().isEmpty()) {
                    for (EmpleadoDTO empleado : consultaBO.getLstSubordinados()) {
                        if (addDetalleXEmpleado(tipoEmpleado, consultaBO.getDetalleXEmpleado(), empleado, propuesta)
                                && !consultaBO.getLstPropuestasTotalEmpleado().contains(propuesta)) {
                            consultaBO.getLstPropuestasTotalEmpleado().add(propuesta);
                        }
                    }
                }
            }
        }

    }

    private void countXProgramadoresSubordinado(ConsultaEjecutivaPropuestasBO consultaBO,
            EmpleadoDTO empleado,
            AgrupadorEstatusPropuestasEnum grupo,
            TipoEmpleadoEnum rolJefe,
            List<TipoAccionPropuesta> lstAcciones,
            FiltroConsultaPropuestas filtroDeBusqueda) throws EmpleadoServiceException {
        int tipoJefe = rolJefe != null ? (int) rolJefe.getId() : 0;
        FiltroPropuestas filtro = new FiltroPropuestas();
        EmpleadoDTO empleadoTmp;
        switch (tipoJefe) {
            case CONSULTA_TIPO_CENTRAL:
                empleadoTmp = getEmpleadoCompleto(empleado.getRfc());
                filtro.setLstProgramadores(getLstSubordinadosPorRelacionEmpleado(empleadoTmp, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.PROGRAMADOR));
                filtro.setLstSubadministradores(getLstSubordinadosPorRelacionEmpleado(empleadoTmp, TipoEmpleadoEnum.ASIGNADOR_INSUMOS, TipoEmpleadoEnum.VALIDADOR_INSUMOS));
                List<EmpleadoDTO> listSubAdministrador = getEmpleadosXUnidadAdmva(empleadoTmp.getDetalleEmpleado().get(0).getCentral().getIdArace(), TipoEmpleadoEnum.VALIDADOR_INSUMOS.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS);
                EmpleadoDTO subAdministrador = new EmpleadoDTO();

                for (EmpleadoDTO subAdministradorTemp : listSubAdministrador) {
                    if (subAdministradorTemp.getRfc().equals(empleado.getRfc())) {
                        subAdministrador = subAdministradorTemp;
                        break;
                    }
                    subAdministrador = null;
                }

                if (filtro.getLstSubadministradores() == null) {
                    filtro.setLstSubadministradores(new ArrayList<EmpleadoDTO>());
                }

                if (subAdministrador != null) {
                    filtro.getLstSubadministradores().add(subAdministrador);
                }

                filtro.setTipoEmpleadoConsulta(TipoEmpleadoEnum.CONSULTOR_INSUMOS);
                break;
            case CONSULTA_TIPO_ADMINISTRADOR:
                empleadoTmp = getEmpleadoCompleto(empleado.getRfc());
                filtro.setLstProgramadores(getLstSubordinadosPorRelacionEmpleado(empleadoTmp, TipoEmpleadoEnum.VALIDADOR_INSUMOS, TipoEmpleadoEnum.PROGRAMADOR));
                filtro.setLstSubadministradores(new ArrayList<EmpleadoDTO>());
                filtro.getLstSubadministradores().add(empleadoTmp);
                filtro.setTipoEmpleadoConsulta(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                break;
            case CONSULTA_TIPO_SUBADMINISTRADOR:
                filtro.setLstProgramadores(null);
                filtro.setLstSubadministradores(null);
                filtro.setTipoEmpleadoConsulta(TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                break;
            default:
                filtro.setLstProgramadores(null);
                filtro.setLstSubadministradores(null);
                break;
        }

        filtro.setVisibilidadACPPCE(filtroDeBusqueda.isVisibilidadACPPCE());
        filtro.setProgramacion(filtroDeBusqueda.isProgramacion());
        filtro.setLstAccionesPropuesta(lstAcciones);
        filtro.setEstatusFiltro(consultaBO.getGruposDeEstatusValidos().get(grupo).getEstatus());

        if (consultaBO.getDetalleXEmpleado() == null) {
            consultaBO.setDetalleXEmpleado(new HashMap<EmpleadoDTO, Integer>());
        }

        filtro.setUnidadAdmtvaDesahogoFiltro(filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro());

        if (filtro.getLstProgramadores() == null || filtro.getLstProgramadores().isEmpty()) {
            consultaBO.getDetalleXEmpleado().put(empleado, 0);
            return;
        }
        List<FecetPropuesta> lstPropuestas = fecetPropuestaDao.consultaEjecutivaPropuestasXFiltro(filtro, grupo);
        if (consultaBO.isIsAcppce()) {
            validarDuplicidadXJefe(lstPropuestas, filtro, consultaBO);
        }
        int cont = lstPropuestas.size();
        addLstPropuestasALstEmpleados(consultaBO, lstPropuestas);
        consultaBO.getDetalleXEmpleado().put(empleado, cont);
    }

    private void validarDuplicidadXJefe(List<FecetPropuesta> lstPropuestas, FiltroPropuestas filtro, ConsultaEjecutivaPropuestasBO consultaBO) {

        List<FecetPropuesta> lstPropuestasTmp = new ArrayList<FecetPropuesta>();
        List<EmpleadoDTO> lstEmpleadosCreacion = new ArrayList<EmpleadoDTO>();
        List<FecetPropuesta> lstPropRepetidas = new ArrayList<FecetPropuesta>();

        lstEmpleadosCreacion.addAll(filtro.getLstSubadministradores());
        lstEmpleadosCreacion.addAll(filtro.getLstProgramadores());

        for (EmpleadoDTO empleado : lstEmpleadosCreacion) {
            for (FecetPropuesta propuesta : lstPropuestas) {
                if (!propuesta.getRfcCreacion().equals(empleado.getRfc())) {
                    lstPropuestasTmp.add(propuesta);
                }
            }
        }

        validaDuplicidad(lstPropuestasTmp);

        if (lstPropuestasTmp != null && !lstPropuestasTmp.isEmpty()) {
            for (FecetPropuesta propuestaTmp : lstPropuestasTmp) {
                if (consultaBO.getLstPropuestasTotalEmpleado().contains(propuestaTmp)) {
                    lstPropRepetidas.add(propuestaTmp);
                }
            }

            lstPropuestas.removeAll(lstPropRepetidas);
        }
    }

    private void addLstPropuestasALstEmpleados(ConsultaEjecutivaPropuestasBO consultaBO, List<FecetPropuesta> lstPropuestas) {
        boolean validacionUno = consultaBO != null && consultaBO.getLstPropuestasTotalEmpleado() != null;
        boolean validacionDos = lstPropuestas != null && !consultaBO.getLstPropuestasTotalEmpleado().containsAll(lstPropuestas);
        if (validacionUno && validacionDos) {
            consultaBO.getLstPropuestasTotalEmpleado().addAll(lstPropuestas);
        }
    }

    private boolean addDetalleXEmpleado(int tipoConteo, Map<EmpleadoDTO, Integer> mapDetalle, EmpleadoDTO empleado, FecetPropuesta propuesta) {
        int conteo = 0;
        boolean seContabilizo = false;
        switch (tipoConteo) {
            case CONSULTA_TIPO_CENTRAL:
                if (mapDetalle.containsKey(empleado)) {
                    conteo = mapDetalle.get(empleado);
                    if (empleado.getRfc().equals(propuesta.getRfcFirmante())) {
                        mapDetalle.put(empleado, ++conteo);
                        seContabilizo = true;
                    }
                } else {
                    mapDetalle.put(empleado, conteo);
                    if (empleado.getRfc().equals(propuesta.getRfcFirmante())) {
                        mapDetalle.put(empleado, ++conteo);
                        seContabilizo = true;
                    }
                }
                break;
            case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                if (mapDetalle.containsKey(empleado)) {
                    conteo = mapDetalle.get(empleado);
                    if (empleado.getRfc().equals(propuesta.getRfcAuditor())) {
                        mapDetalle.put(empleado, ++conteo);
                        seContabilizo = true;
                    }
                } else {
                    mapDetalle.put(empleado, conteo);
                    if (empleado.getRfc().equals(propuesta.getRfcAuditor())) {
                        mapDetalle.put(empleado, ++conteo);
                        seContabilizo = true;
                    }
                }
                break;
            default:
                logger.info("No hay opcion valida");
                break;
        }

        return seContabilizo;
    }

    @Override
    public ConsultaEjecutivaPropuestasBO consultarPropuestasXEmpleadoSubordinado(ConsultaEjecutivaPropuestasBO consultaBO, EmpleadoDTO empleadoSeleccionado) throws ConsultaEjecutivaPropuestasServiceException {
        int tipoConteo = getTipoEmpleado(empleadoSeleccionado);
        if (consultaBO.getLstPropuestasTotalEmpleado() != null) {
            switch (tipoConteo) {
                case CONSULTA_TIPO_CENTRAL:
                    break;
                case CONSULTA_TIPO_ADMINISTRADOR:
                    if (consultaBO.isIsAcppce() || consultaBO.isProgramacion()) {
                        consultaBO.setLstPropuestasXFiltro(consultaBO.getLstPropuestasTotalEmpleado());
                    } else {
                        consultaBO.setLstPropuestasXFiltro(new ArrayList<FecetPropuesta>());
                    }
                    break;
                case CONSULTA_TIPO_SUBADMINISTRADOR:
                    if (consultaBO.isIsAcppce() || consultaBO.isProgramacion()) {
                        consultaBO.setLstPropuestasXFiltro(conteoSubordinado(consultaBO.getLstPropuestasTotalEmpleado(), CONSULTA_TIPO_SUBADMINISTRADOR, empleadoSeleccionado));
                    } else {
                        consultaBO.setLstPropuestasXFiltro(new ArrayList<FecetPropuesta>());
                    }
                    break;
                case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                    if (!consultaBO.isIsAcppce() && !consultaBO.isProgramacion()) {
                        consultaBO.setLstPropuestasXFiltro(conteoSubordinado(consultaBO.getLstPropuestasTotalEmpleado(), CONSULTA_TIPO_CONSULTA_FIRMANTE, empleadoSeleccionado));
                    } else {
                        consultaBO.setLstPropuestasXFiltro(new ArrayList<FecetPropuesta>());
                    }
                    break;
                case CONSULTA_TIPO_CONSULTA_AUDITOR:
                    if (!consultaBO.isIsAcppce() && !consultaBO.isProgramacion()) {
                        consultaBO.setLstPropuestasXFiltro(conteoSubordinado(consultaBO.getLstPropuestasTotalEmpleado(), CONSULTA_TIPO_CONSULTA_AUDITOR, empleadoSeleccionado));
                    } else {
                        consultaBO.setLstPropuestasXFiltro(new ArrayList<FecetPropuesta>());
                    }
                    break;
                default:
                    logger.info("Opcion no valida");
                    break;
            }
        }
        return consultaBO;
    }

    private int getTipoEmpleado(EmpleadoDTO empleado) {
        if (empleado != null) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                return (int) detalle.getTipoEmpleado().getId();
            }
        }
        return 0;
    }

    private List<FecetPropuesta> conteoSubordinado(List<FecetPropuesta> lstOrigen, int tipoSubordinado, EmpleadoDTO empleado) {
        List<FecetPropuesta> lstResult = new ArrayList<FecetPropuesta>();
        List<String> rfcsProgramadores = new ArrayList<String>();
        if (lstOrigen != null) {
            for (FecetPropuesta prop : lstOrigen) {
                switch (tipoSubordinado) {
                    case CONSULTA_TIPO_CONSULTA_FIRMANTE:
                        if (empleado.getRfc().equals(prop.getRfcFirmante())) {
                            lstResult.add(prop);
                        }
                        break;
                    case CONSULTA_TIPO_CONSULTA_AUDITOR:
                        if (empleado.getRfc().equals(prop.getRfcAuditor())) {
                            lstResult.add(prop);
                        }
                        break;
                    case CONSULTA_TIPO_SUBADMINISTRADOR:
                        if (rfcsProgramadores.isEmpty()) {
                            rfcsProgramadores.addAll(generaRfcsProgramadores(empleado));
                            rfcsProgramadores.add(empleado.getRfc());
                        }
                        if (rfcsProgramadores.contains(prop.getRfcCreacion())) {
                            lstResult.add(prop);
                        }
                        break;
                    default:
                        logger.info("Opcion no valida");
                        break;
                }
            }
        }
        fillUnidadAdministrativa(lstResult);
        return lstResult;
    }

    private List<String> generaRfcsProgramadores(EmpleadoDTO empleado) {
        List<String> rfcs = new ArrayList<String>();
        try {
            List<EmpleadoDTO> programadores = getLstSubordinadosPorRelacionEmpleado(getEmpleadoCompleto(empleado.getRfc()), TipoEmpleadoEnum.VALIDADOR_INSUMOS, TipoEmpleadoEnum.PROGRAMADOR);
            if (programadores != null) {
                for (EmpleadoDTO empleadoDTO : programadores) {
                    rfcs.add(empleadoDTO.getRfc());
                }
            }
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener a los programadores", e);
        }
        return rfcs;
    }

    private void validaDuplicidad(List<FecetPropuesta> lstOrigen) {
        List<FecetPropuesta> lstResult = new ArrayList<FecetPropuesta>();

        if (lstOrigen != null) {
            for (FecetPropuesta prop : lstOrigen) {
                if (!lstResult.contains(prop)) {
                    lstResult.add(prop);
                }
            }
            lstOrigen.clear();
            lstOrigen.addAll(lstResult);
        }
    }

    public HSSFWorkbook creaExcel(List<FecetPropuesta> listaPropuestas) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");

        int count = 0;

        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        count = insertaCabecera(Constantes.AGACE, sheet, count, style);

        if (listaPropuestas != null && !listaPropuestas.isEmpty()) {
            count = insertListFecetPropuestas(listaPropuestas, sheet, count);
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
        cell.setCellValue("ID REGISTRO");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.UNO);
        cell.setCellValue(Constantes.RFC);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.DOS);
        cell.setCellValue("PRIORIDAD");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.TRES);
        cell.setCellValue(Constantes.METODOSTR);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CUATRO);
        cell.setCellValue("UNIDAD ADMINISTRATIVA DE ATENCION");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CINCO);
        cell.setCellValue("PRESUNTIVA");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.SEIS);
        cell.setCellValue(Constantes.ESTATUS);
        cell.setCellStyle(style);
        contador++;

        return contador;
    }

    private int insertListFecetPropuestas(List<FecetPropuesta> fecetPropuestas, HSSFSheet sheet, int count) {
        int contador = count;

        for (FecetPropuesta fecetPropuesta : fecetPropuestas) {

            Row row = sheet.createRow(contador);

            Cell cell = row.createCell(Constantes.CERO);
            cell.setCellValue((fecetPropuesta.getIdRegistro() != null) ? fecetPropuesta.getIdRegistro() : "");

            cell = row.createCell(Constantes.UNO);
            cell.setCellValue((fecetPropuesta.getFecetContribuyente().getRfc() != null) ? fecetPropuesta.getFecetContribuyente().getRfc() : "");

            cell = row.createCell(Constantes.DOS);
            cell.setCellValue((fecetPropuesta.getPrioridadSugerida()));

            cell = row.createCell(Constantes.TRES);
            cell.setCellValue((fecetPropuesta.getFeceaMetodo() != null && fecetPropuesta.getFeceaMetodo().getAbreviatura() != null)
                    ? fecetPropuesta.getFeceaMetodo().getAbreviatura() : "");

            cell = row.createCell(Constantes.CUATRO);
            cell.setCellValue((fecetPropuesta.getFececArace().getNombre()));

            cell = row.createCell(Constantes.CINCO);
            cell.setCellValue((fecetPropuesta.getPresuntiva().toString()));

            cell = row.createCell(Constantes.SEIS);
            cell.setCellValue((fecetPropuesta.getEstatusXGrupo() != null && fecetPropuesta.getEstatusXGrupo().getDescripcion() != null)
                    ? fecetPropuesta.getEstatusXGrupo().getDescripcion().toUpperCase() : "");

            contador++;
        }
        return contador;
    }

    private boolean tieneRolDeSubadministrador(EmpleadoDTO empleadoConsulta) {
        for (DetalleEmpleadoDTO detalleEmpleado : empleadoConsulta.getDetalleEmpleado()) {
            if (detalleEmpleado.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                return true;
            }
        }
        return false;
    }

    private void validaDuplicidadXEstatus(List<FecetPropuesta> lstOrigen, List<FecetPropuesta> lstTotalPropuestas) {
        List<FecetPropuesta> lstResult = new ArrayList<FecetPropuesta>();

        for (FecetPropuesta propuestaOrigen : lstOrigen) {
            if (lstTotalPropuestas.contains(propuestaOrigen)) {
                lstResult.add(propuestaOrigen);
            }
        }
        lstOrigen.removeAll(lstResult);
    }
}
