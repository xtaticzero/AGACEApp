/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.helper;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class PropuestasDaoHelper {

    private PropuestasDaoHelper() {
    }

    public static String getSQLEstatus(List<TipoEstatusEnum> lstEstatus) {
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

    public static CharSequence getSQLIdArace(List<AraceDTO> unidadAdmtvaDesahogoFiltro) {
        if (unidadAdmtvaDesahogoFiltro != null && !unidadAdmtvaDesahogoFiltro.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (AraceDTO arace : unidadAdmtvaDesahogoFiltro) {
                if (countElement == 0) {
                    sb.append(arace.getIdArace());
                } else {
                    sb.append(",");
                    sb.append(arace.getIdArace());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String getSQLAcciones(List<TipoAccionPropuesta> lstAcciones) {
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

    public static String getSQLIdProgramador(List<Integer> idsProgramador) {
        if (idsProgramador != null && !idsProgramador.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = -1;
            for (Integer id : idsProgramador) {
                if (++countElement != 0) {
                    sb.append(", ");
                }
                sb.append(id);
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
