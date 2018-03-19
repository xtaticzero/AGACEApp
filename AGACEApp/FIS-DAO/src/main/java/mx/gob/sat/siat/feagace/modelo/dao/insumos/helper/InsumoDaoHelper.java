/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.helper;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.sql.FecetInsumoSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FiltroInsumos;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class InsumoDaoHelper {

    public static final int TIPO_CONSULTA_CENTRAL = 1;
    public static final int TIPO_CONSULTA_ADMINISTRADOR = 2;
    public static final int TIPO_CONSULTA_SUBADMINISTRADOR = 4;
    public static final int TIPO_CONSULTA_USUARIO_ACIACE = 5;

    private InsumoDaoHelper() {
    }

    public static TipoEmpleadoEnum obtenerRolEmpleadoConsultaInsumo(EmpleadoDTO empleado) {
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
                if (detalle.getTipoEmpleado().equals(TipoEmpleadoEnum.USUARIO_INSUMOS)) {
                    return TipoEmpleadoEnum.USUARIO_INSUMOS;
                }
            }
        }
        return null;
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

    public static String getSQLEstatusInsumo(List<TipoEstatusEnum> lstEstatusInsumo) {
        if (lstEstatusInsumo != null && !lstEstatusInsumo.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (TipoEstatusEnum estatus : lstEstatusInsumo) {
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

    public static String getSQLRFCCreacion(List<String> rfcsCreacion) {
        if (rfcsCreacion != null && !rfcsCreacion.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = -1;
            for (String rfc : rfcsCreacion) {
                if (++countElement != 0) {
                    sb.append(", ");
                }
                sb.append("'");
                sb.append(rfc);
                sb.append("'");
            }
            return sb.toString();
        } else {
            return "''";
        }
    }

    public static String getQueryCount(StringBuilder sbQuery, TipoEmpleadoEnum tipoEmpleado, boolean isAciace, FiltroInsumos filtroInsumos) {
        String query = "";
        StringBuilder sqlQuery = sbQuery;
        int tipoConsulta = (int) tipoEmpleado.getId();
        if (isAciace) {
            switch (tipoConsulta) {
                case TIPO_CONSULTA_CENTRAL:
                    return sbQuery.toString();
                case TIPO_CONSULTA_ADMINISTRADOR:
                    return sbQuery.toString();
                case TIPO_CONSULTA_SUBADMINISTRADOR:
                    return sbQuery.toString();
                case TIPO_CONSULTA_USUARIO_ACIACE:
                    sqlQuery.append(FecetInsumoSQL.CONDICION_RFC_CREACION);
                    query = sqlQuery.toString();
                    query = query.replace(FecetInsumoSQL.RFC_CREACION, FecetInsumoSQL.PARAMETRO_QUERY);
                    break;
                default:
                    return sbQuery.toString();
            }
        } else {
            switch (tipoConsulta) {
                case TIPO_CONSULTA_CENTRAL:
                    return sbQuery.toString();
                case TIPO_CONSULTA_ADMINISTRADOR:
                    if (filtroInsumos == null || !filtroInsumos.isCentralACPPCEReg()) {
                        sqlQuery.append(FecetInsumoSQL.CONDICION_RFC_ADMINISTRADOR);
                        query = sqlQuery.toString();
                        query = query.replace(FecetInsumoSQL.RFC_ADMINISTRADOR, FecetInsumoSQL.PARAMETRO_QUERY);
                    }
                    break;
                case TIPO_CONSULTA_SUBADMINISTRADOR:
                    sqlQuery.append(FecetInsumoSQL.CONDICION_RFC_SUBADMINISTRADOR);
                    query = sqlQuery.toString();
                    query = query.replace(FecetInsumoSQL.RFC_SUBADMINISTRADOR, FecetInsumoSQL.PARAMETRO_QUERY);
                    break;
                case TIPO_CONSULTA_USUARIO_ACIACE:
                    sqlQuery.append(FecetInsumoSQL.CONDICION_RFC_CREACION);
                    query = sqlQuery.toString();
                    query = query.replace(FecetInsumoSQL.RFC_CREACION, FecetInsumoSQL.PARAMETRO_QUERY);
                    break;
                default:
                    return sbQuery.toString();
            }
        }
        return query;
    }
}
