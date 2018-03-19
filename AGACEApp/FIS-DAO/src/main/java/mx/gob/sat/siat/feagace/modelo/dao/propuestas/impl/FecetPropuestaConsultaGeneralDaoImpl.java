package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.helper.PropuestaDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaConsultaGeneralDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.PropuestaDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

@Repository("FecetPropuestaConsultaGeneralDao")
public class FecetPropuestaConsultaGeneralDaoImpl extends BaseJDBCDao<FecetPropuesta> implements FecetPropuestaConsultaGeneralDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String AND = " AND ";

    @Override
    public List<FecetPropuesta> contarEstatusGrupo(AgrupadorEstatusPropuestasEnum grupo, FiltroPropuestas filtroDao) {
        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_HEDER_CONSULTA_ADMINISTRATIVA);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_CONSULTA_ADMINISTRATIVA);
        if (AgrupadorEstatusPropuestasEnum.PROPUESTAS_CANCELADAS.equals(grupo) || AgrupadorEstatusPropuestasEnum.ORDEN_EN_PROCESO.equals(grupo)) {
            query.append(PropuestasSQL.SQL_INNER_ACCION_PROPUESTA);
        }
        query.append(PropuestasSQL.SQL_CONDICION_WHERE);
        if (filtroDao.getEstatusFiltro() != null && !AgrupadorEstatusPropuestasEnum.PROPUESTA_EN_SELECTOR.equals(grupo)) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_ESTATUS.replace(PropuestasSQL.LST_ESTATUS, PropuestaDaoHelper.getSQLEstatusPropuestas(filtroDao.getEstatusFiltro())));
        }

        if (filtroDao.getUnidadAdmtvaDesahogoFiltro() != null
                && filtroDao.getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace() != TipoAraceEnum.ACPPCE.getId()) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_ARACES.replace(PropuestasSQL.ID_TEMPLATE, PropuestaDaoHelper.getSQLUnidadesAdministrativas(filtroDao.getUnidadAdmtvaDesahogoFiltro())));
        }

        if (AgrupadorEstatusPropuestasEnum.PROPUESTAS_CANCELADAS.equals(grupo)) {
            query.append(PropuestasSQL.SQL_CONDICION_ID_ACCION.replace(PropuestasSQL.ID_ACCION, PropuestaDaoHelper.getSQLAccionesPropuesta(filtroDao.getLstAccionesPropuesta())));
        }
        if (AgrupadorEstatusPropuestasEnum.ORDEN_EN_PROCESO.equals(grupo)) {
            query.append(PropuestasSQL.SQL_CONDICION_ID_ACCION_REVISION.replace(PropuestasSQL.ID_ACCION, PropuestaDaoHelper.getSQLAccionesPropuesta(filtroDao.getLstAccionesPropuesta())));
        }

        if (filtroDao.isProgramacion()) {
            //Programacion
            String subQuery = "";
            switch (grupo) {
                case REGISTRADA:
                    subQuery = PropuestasSQL.SQL_CONDICION_REGISTRADA.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(filtroDao.getLstProgramadores()));
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    subQuery = subQuery.replace(PropuestasSQL.RFC_SUBADMINISTRADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(filtroDao.getLstSubadministradores()));
                    query.append(subQuery);
                    break;
                case PROPUESTA_EN_SELECTOR:
                    filtroDao.getEstatusFiltro().remove(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL);
                    query.append(PropuestasSQL.SQL_CONDICION_SELECTOR_ESTATUS.replace(PropuestasSQL.LST_ESTATUS, PropuestaDaoHelper.getSQLEstatusPropuestas(filtroDao.getEstatusFiltro())));
                    query.append(PropuestasSQL.SQL_CONDICION_RFC_ID_PROPGRAMADOR.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao))));
                    subQuery = query.toString();
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    query = new StringBuilder();
                    query.append(subQuery);
                    filtroDao.getEstatusFiltro().add(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL);
                    break;
                case PROPUESTA_ENVIADA_ADACE:
                    subQuery = PropuestasSQL.SQL_CONDICION_ENVIADA_ADACE.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao)));
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    query.append(subQuery);
                    break;
                case PROPUESTA_ABIERTA_ADACE:
                    query.append(PropuestasSQL.ID_PROGRAMADOR_NOT_NULL);
                    query.append(PropuestasSQL.GABINETE_2);
                    query.append(PropuestasSQL.SQL_CONDICION_RFC_ID_PROPGRAMADOR.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao))));
                    subQuery = query.toString();
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    query = new StringBuilder();
                    query.append(subQuery);
                    break;
                case PROPUESTA_PENDIENTE_COMITE_DESCONCENTRADO:
                    query.append(PropuestasSQL.ID_PROGRAMADOR_NOT_NULL);
                    subQuery = query.toString();
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    query = new StringBuilder();
                    query.append(subQuery);
                    break;
                case PROPUESTA_PENDIENTE_POR_COMITE:
                    query.append(PropuestasSQL.ID_PROGRAMADOR_NULL);
                    query.append(PropuestasSQL.SQL_CONDICION_RFCS_PROGRAMADOR.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao))));
                    break;
                case PROPUESTA_NO_APROBADA_COMITE:
                    query.append(PropuestasSQL.SQL_CONDICION_RFC_ID_PROPGRAMADOR.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao))));
                    subQuery = query.toString();
                    subQuery = subQuery.replace(PropuestasSQL.IDS_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresIdsPropuesta(agregarSubadministradores(filtroDao)));
                    query = new StringBuilder();
                    query.append(subQuery);
                    break;
                default:
                    query.append(PropuestasSQL.SQL_CONDICION_RFCS_PROGRAMADOR.replace(PropuestasSQL.RFC_PROGRAMADOR, PropuestaDaoHelper.getSQLProgramadoresPropuesta(agregarSubadministradores(filtroDao))));
                    break;
            }

            for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new PropuestaDetalleMapper())) {
                return lstCont;
            }

        } else {
            if (filtroDao.getEmpleadoConsultaFiltro() != null) {
                if (esRolValido(TipoEmpleadoEnum.FIRMANTE, filtroDao.getEmpleadoConsultaFiltro())) {
                    query.append(PropuestasSQL.SQL_CONDICION_RFC_FIRMANTE);
                    for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new PropuestaDetalleMapper(), filtroDao.getEmpleadoConsultaFiltro().getRfc())) {
                        return lstCont;
                    }
                }

                if (esRolValido(TipoEmpleadoEnum.AUDITOR, filtroDao.getEmpleadoConsultaFiltro())) {
                    query.append(PropuestasSQL.SQL_CONDICION_RFC_AUDITOR);
                    for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new PropuestaDetalleMapper(), filtroDao.getEmpleadoConsultaFiltro().getRfc())) {
                        return lstCont;
                    }
                }
            } else {
                for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new PropuestaDetalleMapper())) {
                    return lstCont;
                }
            }
        }

        return new ArrayList<FecetPropuesta>();
    }

    private static List<EmpleadoDTO> agregarSubadministradores(FiltroPropuestas filtroDao) {
        List<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
        if (filtroDao.getLstProgramadores() != null) {
            empleados.addAll(filtroDao.getLstProgramadores());
        }
        if (filtroDao.getLstSubadministradores() != null) {
            empleados.addAll(filtroDao.getLstSubadministradores());
        }
        return empleados;
    }

    private boolean esRolValido(TipoEmpleadoEnum rolABuscar, EmpleadoDTO empleado) {

        for (DetalleEmpleadoDTO detalleEmpleado : empleado.getDetalleEmpleado()) {
            if (detalleEmpleado.getTipoEmpleado().equals(rolABuscar)) {
                return true;
            }
        }
        return false;
    }
}
