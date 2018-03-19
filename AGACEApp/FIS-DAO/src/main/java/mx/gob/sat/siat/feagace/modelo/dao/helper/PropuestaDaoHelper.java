/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.PropuestaDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class PropuestaDaoHelper {

    public static final int TIPO_CONSULTA_CENTRAL = 1;
    public static final int TIPO_CONSULTA_ADMINISTRADOR = 2;
    public static final int TIPO_CONSULTA_SUBADMINISTRADOR = 4;
    public static final int TIPO_CONSULTA_AUDITOR = 6;
    public static final int TIPO_CONSULTA_FIRMANTE = 7;
    protected static final String SELECT = " SELECT \n";
    protected static final String AND = " AND ";
    protected static final String UPDATE = " UPDATE ";
    protected static final int TIPO_CONSULTA_EJECUTIVA = 1;
    protected static final int TIPO_CONSULTA_CENTRAL_REGIONAL = 2;
    private static final String FORMATO_QUERY_DATE_YYYY_MM_DD = "','YYYY-MM-DD')";

    public static final int TIPO_CONSULTA_SUBADMINISTRADOR_EO = 12;

    private static final int PARAMETRO_INICIAL = 0;
    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;
    private static final int NUM_4 = 4;
    private static final int NUM_5 = 5;

    private PropuestaDaoHelper() {
    }

    public static String getParametroAraces(TipoAraceEnum... tiposArace) {
        StringBuilder query = new StringBuilder("");

        for (int i = 0; i < tiposArace.length; i++) {
            if (i == 0) {
                query.append(tiposArace[PARAMETRO_INICIAL].getId());
            } else {
                query.append(", ");
                query.append(tiposArace[i].getId());
            }
        }

        return query.toString();
    }

    public static String getParametrosEstatus(TipoEstatusEnum[] lstEstatusPropuesta) {
        StringBuilder sbQuery = new StringBuilder("");

        for (int i = 0; i < lstEstatusPropuesta.length; i++) {
            if (i == 0) {
                sbQuery.append(lstEstatusPropuesta[PARAMETRO_INICIAL].getId());
            } else {
                sbQuery.append(", ");
                sbQuery.append(lstEstatusPropuesta[i].getId());
            }
        }

        return sbQuery.toString();
    }

    public static String getSQLEstatusPropuestas(List<TipoEstatusEnum> lstEstatus) {
        if (lstEstatus != null && !lstEstatus.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (TipoEstatusEnum estatus : lstEstatus) {
                if (countElement == 0) {
                    sb.append(estatus.getId());
                } else {
                    sb.append(",");
                    sb.append(estatus.getId());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String getSQLUnidadesAdministrativas(List<AraceDTO> lstUnidatesAdmin) {
        if (lstUnidatesAdmin != null && !lstUnidatesAdmin.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (AraceDTO unidadAdmin : lstUnidatesAdmin) {
                if (countElement == 0) {
                    sb.append(unidadAdmin.getIdArace());
                } else {
                    sb.append(",");
                    sb.append(unidadAdmin.getIdArace());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String getSQLAccionesPropuesta(List<TipoAccionPropuesta> lstAcciones) {
        if (lstAcciones != null && !lstAcciones.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (TipoAccionPropuesta accion : lstAcciones) {
                if (countElement == 0) {
                    sb.append(accion.getIdAccion());
                } else {
                    sb.append(",");
                    sb.append(accion.getIdAccion());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static TipoEmpleadoEnum obtenerRolEmpleadoConsulta(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.CONSULTOR_INSUMOS)) {
                    return TipoEmpleadoEnum.CONSULTOR_INSUMOS;
                }
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS)) {
                    return TipoEmpleadoEnum.ASIGNADOR_INSUMOS;
                }
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                    return TipoEmpleadoEnum.VALIDADOR_INSUMOS;
                }
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.FIRMANTE)) {
                    return TipoEmpleadoEnum.FIRMANTE;
                }
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.AUDITOR)) {
                    return TipoEmpleadoEnum.AUDITOR;
                }
            }
        }
        return null;
    }

    public static String getSQLProgramadoresPropuesta(List<EmpleadoDTO> lstProgramadores) {
        if (lstProgramadores != null && !lstProgramadores.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (EmpleadoDTO programador : lstProgramadores) {
                if (countElement == 0) {
                    sb.append("'");
                    sb.append(programador.getRfc());
                    sb.append("'");
                } else {
                    sb.append(",");
                    sb.append("'");
                    sb.append(programador.getRfc());
                    sb.append("'");
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "''";
        }
    }

    public static String getSQLProgramadoresIdsPropuesta(List<EmpleadoDTO> lstProgramadores) {
        if (lstProgramadores != null && !lstProgramadores.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (EmpleadoDTO programador : lstProgramadores) {
                if (countElement == 0) {
                    sb.append(programador.getIdEmpleado());
                } else {
                    sb.append(",");
                    sb.append(programador.getIdEmpleado());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static StringBuilder getPropuestasAsignarFirmanteQuery(final BigDecimal idArace, boolean esPrioridad3) {
        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_HEDER);
        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_JOIN);
        query.append(" WHERE ((FECET_PROPUESTA.ID_ESTATUS = ");
        query.append(TipoEstatusEnum.PROPUESTA_ASIGNADA_CENTRAL.getId());
        query.append(" AND FECET_PROPUESTA.ID_METODO = ");
        query.append(TipoMetodoEnum.ORG.getId());
        if (!esPrioridad3) {
            query.append(" or (FECET_PROPUESTA.ID_ESTATUS = ");
            query.append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId());
            query.append(" and FECET_PROPUESTA.ID_METODO = ");
            query.append(TipoMetodoEnum.ORG.getId());
            query.append(" and FECET_PROPUESTA.PRIORIDAD = 1)");
        }
        query.append(") OR (FECET_PROPUESTA.ID_ESTATUS = ");
        query.append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId());
        query.append(" AND FECET_PROPUESTA.ID_METODO IN (");
        query.append(TipoMetodoEnum.EHO.getId());
        query.append(", ");
        query.append(TipoMetodoEnum.UCA.getId());
        query.append(", ");
        query.append(TipoMetodoEnum.REE.getId());
        query.append(", ");
        query.append(TipoMetodoEnum.MCA.getId());
        query.append(")) OR FECET_PROPUESTA.ID_ESTATUS = ");
        query.append(TipoEstatusEnum.PROPUESTA_TRANSFERIDA.getId());
        query.append(") AND FECET_PROPUESTA.ID_ARACE = ");
        query.append(idArace.toString());
        query.append(" AND FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL");

        return query;
    }

    public static StringBuilder getPropuestasRegionalAsignarFirmanteQuery(final BigDecimal idArace, boolean esPrioridad3) {

        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_HEDER);
        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_JOIN);
        query.append(" WHERE ((FECET_PROPUESTA.ID_ESTATUS = ").append(TipoEstatusEnum.PROPUESTA_ASIGNADA_CENTRAL.getId());
        query.append(" AND FECET_PROPUESTA.ID_METODO = ").append(TipoMetodoEnum.ORG.getId()).append(")");
        if (!esPrioridad3) {
            query.append(" or (FECET_PROPUESTA.ID_ESTATUS IN ( ");
            query.append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId());
            query.append(") and id_programador IS NULL AND FECET_PROPUESTA.FECHA_PRESENTACION IS NULL) ");
            query.append(" or (FECET_PROPUESTA.ID_ESTATUS IN ( ");
            query.append(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL.getId());
            query.append(")");

            query.append(" and FECET_PROPUESTA.ID_METODO = ");
            query.append(TipoMetodoEnum.ORG.getId());
            query.append(" and FECET_PROPUESTA.PRIORIDAD = 1)");
        }
        query.append(" OR ((FECET_PROPUESTA.ID_ESTATUS IN ( ").append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId()).append(") and id_programador IS NULL AND FECET_PROPUESTA.FECHA_PRESENTACION IS NULL )");
        query.append(" OR (FECET_PROPUESTA.ID_ESTATUS IN ( ").append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId()).append(")  and FECET_INSUMO.id_unidad_administrativa=1 and FECET_PROPUESTA.id_arace in (20,19) AND FECET_PROPUESTA.FECHA_PRESENTACION IS NULL )");
        query.append(" OR (FECET_PROPUESTA.ID_ESTATUS IN ( ").append(TipoEstatusEnum.PROPUESTA_REGISTRADA.getId()).append(")  and FECET_INSUMO.id_unidad_administrativa NOT IN (1) and FECET_PROPUESTA.id_arace not in (20,19) ) ");
        query.append(" OR (FECET_PROPUESTA.ID_ESTATUS IN (").append(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL.getId()).append("))");
        query.append(" AND (FECET_PROPUESTA.ID_METODO IN (").append(TipoMetodoEnum.EHO.getId()).append(",").append(TipoMetodoEnum.UCA.getId()).append(",");
        query.append(TipoMetodoEnum.REE.getId()).append(",").append(TipoMetodoEnum.MCA.getId()).append(")))");
        query.append(" OR FECET_PROPUESTA.ID_ESTATUS = ").append(TipoEstatusEnum.PROPUESTA_TRANSFERIDA.getId());
        query.append(" ) AND FECET_PROPUESTA.ID_ARACE = ");
        query.append(idArace.toString());
        query.append(" AND FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL");
        return query;
    }

    public static String getPropuestasAsignarFirmantePrior3Query(StringBuilder queryInicial, List<String> prioridades, final Object... args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio, fechaFin;
        String idMetodo, idTipoPropuesta, idTipoRevision, idSubprograma;
        StringBuilder query = queryInicial;

        if (args[0] != null || args[1] != null) {
            if (args[0] != null && args[1] != null) {
                fechaInicio = (Date) args[0];
                fechaFin = (Date) args[1];

                query.append(" AND FECET_PROPUESTA.FECHA_INICIO_PERIODO >= TO_DATE('");
                query.append(sdf.format(fechaInicio));
                query.append(FORMATO_QUERY_DATE_YYYY_MM_DD);
                query.append(" AND FECET_PROPUESTA.FECHA_FIN_PERIODO <= TO_DATE('");
                query.append(sdf.format(fechaFin));
                query.append(FORMATO_QUERY_DATE_YYYY_MM_DD);

            } else if (args[0] != null && args[1] == null) {
                fechaInicio = (Date) args[0];
                query.append(" AND FECET_PROPUESTA.FECHA_INICIO_PERIODO == TO_DATE('");
                query.append(sdf.format(fechaInicio));
                query.append(FORMATO_QUERY_DATE_YYYY_MM_DD);

            } else {
                fechaFin = (Date) args[1];
                query.append(" AND FECET_PROPUESTA.FECHA_FIN_PERIODO == TO_DATE('");
                query.append(sdf.format(fechaFin));
                query.append(FORMATO_QUERY_DATE_YYYY_MM_DD);
            }
        }
        if (args[NUM_2] != null) {
            idMetodo = (String) args[NUM_2];

            query.append(" AND FECET_PROPUESTA.ID_METODO = ");
            query.append(idMetodo);

        }
        if (args[NUM_3] != null) {
            idTipoPropuesta = (String) args[NUM_3];

            query.append(" AND FECET_PROPUESTA.ID_TIPO_PROPUESTA = ");
            query.append(idTipoPropuesta);

        }
        if (args[NUM_4] != null) {
            idTipoRevision = (String) args[NUM_4];

            query.append(" AND FECET_PROPUESTA.ID_REVISION = ");
            query.append(idTipoRevision);
        }
        if (args[NUM_5] != null) {
            idSubprograma = (String) args[NUM_5];

            query.append(" AND FECET_PROPUESTA.ID_SUBPROGRAMA = ");
            query.append(idSubprograma);
        }

        query.append(" AND FECET_PROPUESTA.PRIORIDAD IN (");
        int cont = 0;
        for (String prioridad : prioridades) {
            query.append(prioridad);
            if (cont++ < prioridades.size() - 1) {
                query.append(",");
            }
        }
        query.append(")");
        query.append(" ORDER BY FECET_PROPUESTA.FECHA_CREACION");

        return query.toString();
    }

    public static List<FecetPropuesta> consultaPropuestas(JdbcTemplate jdbcTemplate, FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo, int tipoAsignacion) {
        if (filtroDao != null) {
            int tipoConsulta = 0;
            TipoEmpleadoEnum tipoEmpleado = filtroDao.getTipoEmpleadoConsulta() != null ? filtroDao.getTipoEmpleadoConsulta() : PropuestaDaoHelper.obtenerRolEmpleadoConsulta(filtroDao.getEmpleadoConsultaFiltro());
            if (tipoEmpleado != null) {
                tipoConsulta = (int) tipoEmpleado.getId();
            }

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
            if (filtroDao.getUnidadAdmtvaDesahogoFiltro() != null) {
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
                return ejecutarQuery(query, jdbcTemplate);
            } else {
                switch (tipoConsulta) {
                    case PropuestaDaoHelper.TIPO_CONSULTA_FIRMANTE:
                        query.append(PropuestasSQL.SQL_CONDICION_RFC_FIRMANTE);
                        break;
                    case PropuestaDaoHelper.TIPO_CONSULTA_AUDITOR:
                        query.append(PropuestasSQL.SQL_CONDICION_RFC_AUDITOR);
                        break;
                    case PropuestaDaoHelper.TIPO_CONSULTA_CENTRAL:
                    default:
                        return ejecutarQuery(query, jdbcTemplate);
                }
                for (List<FecetPropuesta> lstCont : jdbcTemplate.query(query.toString(), new PropuestaDetalleMapper(), filtroDao.getEmpleadoConsultaFiltro().getRfc())) {
                    return lstCont;
                }
            }
        }
        return new ArrayList<FecetPropuesta>();
    }

    private static List<FecetPropuesta> ejecutarQuery(StringBuilder query, JdbcTemplate jdbcTemplate) {
        for (List<FecetPropuesta> lstCont : jdbcTemplate.query(query.toString(), new PropuestaDetalleMapper())) {
            return lstCont;
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

}
