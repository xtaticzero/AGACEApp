package mx.gob.sat.siat.feagace.modelo.dao.helper;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.CifrasOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;

public final class OrdenDaoHelper {

    public static final int TIPO_CONSULTA_AUDITOR = 6;
    public static final int TIPO_CONSULTA_FIRMANTE = 7;

    private OrdenDaoHelper() {
        super();
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

    public static String getSQLEstatusOrdenes(List<TipoEstatusEnum> lstEstatus) {
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

    public static String getSQLMetodosOrdenes(List<TipoMetodoEnum> lstMetodos) {
        if (lstMetodos != null && !lstMetodos.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (TipoMetodoEnum metodo : lstMetodos) {
                if (countElement == 0) {
                    sb.append(metodo.getId());
                } else {
                    sb.append(",");
                    sb.append(metodo.getId());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String getSQLCifrasOrdenes(List<CifrasOrdenesEnum> lstCifras) {
        if (lstCifras != null && !lstCifras.isEmpty()) {
            StringBuilder sb = new StringBuilder("");
            int countElement = 0;
            for (CifrasOrdenesEnum cifra : lstCifras) {
                if (countElement == 0) {
                    sb.append(cifra.getIdCifra());
                } else {
                    sb.append(",");
                    sb.append(cifra.getIdCifra());
                }
                countElement++;
            }
            return sb.toString();
        } else {
            return "";
        }
    }

}
