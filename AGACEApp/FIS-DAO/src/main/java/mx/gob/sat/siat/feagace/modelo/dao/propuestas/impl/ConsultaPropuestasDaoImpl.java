package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececSubprogramaDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl.FecetContribuyenteDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.common.impl.FeceaMetodoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.ConsultaPropuestasMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ConsultaPropuestasDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusConsultaPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("consultaPropuestasDao")
public class ConsultaPropuestasDaoImpl extends BaseJDBCDao<ConsultaPropuestas> implements ConsultaPropuestasDao {

    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;
    private static final int NUM_4 = 4;
    private static final int NUM_5 = 5;
    private static final int NUM_6 = 6;
    private static final int NUM_7 = 7;

    /**
     *
     */
    private static final long serialVersionUID = -2661766277167761855L;
    private static final String INNER_JOIN = "INNER JOIN ";
    private static final String LEFT_JOIN = "LEFT JOIN ";
    private static final String FORMATO_FECHA_WHERE_YYYY_MM_DD = "','YYYY-MM-DD')";

    public List<ConsultaPropuestas> cargarPropuestasPorArace(EmpleadoDTO empleado, boolean central, Object... args) {

        StringBuilder sql = new StringBuilder();
        sql.append(sqlSelect(central));
        sql.append(empleado.getRfc());
        sql.append("' ");

        if (!central) {
            sql.append("\n OR FECET_PROPUESTA.ID_PROGRAMADOR = ");
            sql.append(empleado.getIdEmpleado());
            sql.append(")");
            sql.append("\n AND FECET_PROPUESTA.ID_ARACE = ");
            sql.append(empleado.getDetalleEmpleado().get(0).getCentral().getIdArace());
        }

        if (args != null && args.length > 0) {
            sql.append(condicionEntidad(args[0]));
            sql.append(condicionRfc(args[1]));
            sql.append(condicionIdRegistro(args[NUM_2]));
            sql.append(condicionIdEstatusPropuestas(args[NUM_3]));
            sql.append(condicionIdUnidadAdministrativa(args[NUM_4]));
            sql.append(condicionFechasInicioFin(args[NUM_5], args[NUM_6], args[NUM_7]));
        }

        logger.info(sql.toString());
        return getJdbcTemplateBase().query(sql.toString(), new ConsultaPropuestasMapper());

    }

    private String sqlSelect(boolean central) {
        StringBuilder sql = new StringBuilder();

        sql.append(
                "SELECT UNIQUE FECET_PROPUESTA.ID_PROPUESTA, FECET_PROPUESTA.ID_ARACE, FECET_PROPUESTA.ID_REGISTRO, FECET_CONTRIBUYENTE.RFC, FECET_CONTRIBUYENTE.NOMBRE NOMBRE_CONT, FECET_PROPUESTA.ID_PROGRAMADOR, \n");
        sql.append(
                " FECEC_ESTATUS.DESCRIPCION AS ESTATUS, FECET_CONTRIBUYENTE.ENTIDAD, FECET_CONTRIBUYENTE.TIPO TIPO_CONTRIBUYENTE, \n");
        sql.append(
                " FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, FECET_PROPUESTA.FECHA_INFORME, FECET_PROPUESTA.FECHA_PRESENTACION, \n");
        sql.append(
                " FECET_RECHAZO_PROPUESTA.DESCRIPCION AS CAUSA, FECET_CONTRIBUYENTE.REGIMEN, FECEC_SUBPROGRAMA.CLAVE, \n");
        sql.append(" FECEC_SUBPROGRAMA.DESCRIPCION SUBPROGRAMA_DESCRIPCION\n");
        sql.append(" FROM ");
        sql.append(PropuestasSQL.NAME_TABLE);
        sql.append("\nINNER JOIN ");
        sql.append(FecetContribuyenteDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE \n");
        sql.append(INNER_JOIN);
        sql.append(FececSubprogramaDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_SUBPROGRAMA = FECEC_SUBPROGRAMA.ID_SUBPROGRAMA \n");
        sql.append(INNER_JOIN);
        sql.append(FeceaMetodoDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_METODO = FECEC_METODO.ID_METODO \n");
        sql.append(INNER_JOIN);
        sql.append(FececEstatusDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_ESTATUS = FECEC_ESTATUS.ID_ESTATUS \n");
        sql.append(LEFT_JOIN);
        sql.append(FecetRechazoPropuestaDaoImpl.getTableName());
        sql.append(" ON FECET_PROPUESTA.ID_PROPUESTA = FECET_RECHAZO_PROPUESTA.ID_PROPUESTA \n");
        sql.append(" AND FECET_PROPUESTA.ID_ESTATUS IN (");
        sql.append(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE.getId());
        sql.append(",");
        sql.append(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR.getId());
        sql.append(") \n");

        if (!central) {
            sql.append(" WHERE (FECET_PROPUESTA.RFC_CREACION = '");
        } else {
            sql.append(" WHERE FECET_PROPUESTA.RFC_CREACION = '");
        }

        return sql.toString();
    }

    private String condicionEntidad(Object obj) {
        String entidad = (String) obj;
        if (entidad != null && !entidad.equalsIgnoreCase("")
                && !entidad.equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA)) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n AND FECET_CONTRIBUYENTE.ENTIDAD LIKE ('%");
            sql.append(entidad.trim());
            sql.append("%')");

            return sql.toString();
        } else {
            return "";
        }
    }

    private String condicionRfc(Object obj) {
        String rfc = (String) obj;
        if (rfc != null && !rfc.equalsIgnoreCase("")) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n AND FECET_CONTRIBUYENTE.RFC LIKE ('%");
            sql.append(rfc.trim());
            sql.append("%')");

            return sql.toString();
        } else {
            return "";
        }
    }

    private String condicionIdRegistro(Object obj) {
        String idRegistro = (String) obj;
        if (idRegistro != null && !idRegistro.equalsIgnoreCase("")) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n AND FECET_PROPUESTA.ID_REGISTRO LIKE ('%");
            sql.append(idRegistro.trim());
            sql.append("%')");

            return sql.toString();
        } else {
            return "";
        }
    }

    private String condicionIdEstatusPropuestas(Object obj) {
        if (obj != null) {
            TipoEstatusConsultaPropuestasEnum idEstatusPropuestas = TipoEstatusConsultaPropuestasEnum
                    .getById(Long.parseLong((String) obj));
            if (idEstatusPropuestas != null) {
                StringBuilder sql = new StringBuilder();
                switch (idEstatusPropuestas) {
                    case PRESENTADA_COMITE:
                        sql.append("\n AND FECET_PROPUESTA.FECHA_PRESENTACION IS NOT NULL ");
                        break;
                    case INFORMADA_COMITE:
                        sql.append("\n AND FECET_PROPUESTA.FECHA_INFORME IS NOT NULL ");
                        break;
                    case APROBADA_COMITE:
                        sql.append("\n AND FECET_PROPUESTA.ID_ESTATUS = ");
                        sql.append(TipoEstatusEnum.PROPUESTA_ASIGNADA_CENTRAL.getId());
                        sql.append("\n AND FECET_PROPUESTA.ID_METODO = ");
                        sql.append(Constantes.ORG.toString());
                        sql.append("\n AND FECET_PROPUESTA.PRIORIDAD IN (");
                        sql.append(Constantes.ENTERO_DOS);
                        sql.append(",");
                        sql.append(Constantes.ENTERO_TRES);
                        sql.append(")");
                        break;
                    case NO_APROBADA_COMITE:
                        sql.append("\n AND FECET_PROPUESTA.ID_ESTATUS IN (");
                        sql.append(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE.getId());
                        sql.append(",");
                        sql.append(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR.getId());
                        sql.append(")");
                        break;
                    default:
                        return "";
                }
                return sql.toString();
            } else {
                return "";
            }
        } else {
            return "";
        }

    }

    private String condicionIdUnidadAdministrativa(Object obj) {
        String idUnidadAdministrativa = (String) obj;
        if (idUnidadAdministrativa != null && !idUnidadAdministrativa.equalsIgnoreCase("")
                && !idUnidadAdministrativa.equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA)) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n AND FECET_PROPUESTA.ID_ARACE = ");
            sql.append(idUnidadAdministrativa);
            return sql.toString();
        } else {
            return "";
        }
    }

    private String condicionFechasInicioFin(Object tipoFecha, Object fechaIni, Object fechaFin) {
        String tipoFechaS = (String) tipoFecha;
        boolean tipoFechaSeleccionaResult = tipoFechaS != null && !tipoFechaS.equalsIgnoreCase("") && !tipoFechaS.equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA);
        boolean fechasValidasResult = fechaIni != null && fechaFin != null;
        if (tipoFechaSeleccionaResult && fechasValidasResult) {
            TipoFechasComiteEnum tipoFechasComite = TipoFechasComiteEnum.getById(Long.parseLong(tipoFechaS));
            StringBuilder sql = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            switch (tipoFechasComite) {
                case FECHA_PRESENTACION_COMITE:
                case FECHA_PRESENTACION_COMITE_REGIONAL:
                    sql.append("\n AND FECET_PROPUESTA.FECHA_PRESENTACION >= TO_DATE('");
                    sql.append(dateFormat.format((Date) fechaIni));
                    sql.append(FORMATO_FECHA_WHERE_YYYY_MM_DD);
                    sql.append("\n AND FECET_PROPUESTA.FECHA_PRESENTACION <= TO_DATE('");
                    sql.append(dateFormat.format((Date) fechaFin));
                    sql.append(FORMATO_FECHA_WHERE_YYYY_MM_DD);
                    break;
                case FECHA_INFORME_COMITE:
                case FECHA_INFORME_COMITE_REGIONAL:
                    sql.append("\n AND FECET_PROPUESTA.FECHA_INFORME >= TO_DATE('");
                    sql.append(dateFormat.format((Date) fechaIni));
                    sql.append(FORMATO_FECHA_WHERE_YYYY_MM_DD);
                    sql.append("\n AND FECET_PROPUESTA.FECHA_INFORME <= TO_DATE('");
                    sql.append(dateFormat.format((Date) fechaFin));
                    sql.append(FORMATO_FECHA_WHERE_YYYY_MM_DD);
                    break;
                default:
                    return "";
            }
            return sql.toString();
        } else {
            return "";
        }

    }
}
