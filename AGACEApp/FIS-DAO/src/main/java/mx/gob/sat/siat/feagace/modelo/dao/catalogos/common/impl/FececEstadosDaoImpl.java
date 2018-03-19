package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstadosDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstadosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;

@Repository("fececEstadosDao")
public class FececEstadosDaoImpl extends BaseJDBCDao<FececEstados> implements FececEstadosDao {

    /**
     *
     */
    private static final long serialVersionUID = -3385210157697493063L;

    /**
     * Este atributo es un SELECT para seleccionar los datoas de las columnas de
     * la tabla FECEC_ESTADOS
     */
    private static final String SQL_SELECT = "SELECT FECEC_ESTADOS.ID_ESTADOS, FECEC_ESTADOS.NOMBRE, FECEC_ESTADOS.FECHA_INICIO,"
            + "FECEC_ESTADOS.FECHAFIN, FECEC_ESTADOS.BLN_ACTIVO FROM " + getTableName();

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ESTADOS";
    }

    /**
     * Método para obtener los estados que estan relacionados con el empleado y
     * su respectiva UNIDAD ADMINISTRATIVA
     *
     * @param BigDecimal
     * @return List<FececEstados>
     */
    @Override
    public List<FececEstados> obtenerEstadosPorIdEmpleado(BigDecimal idUnidad) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT.replaceAll("SELECT", "SELECT UNIQUE"));
        query.append(
                " INNER JOIN FECEA_ADMINISTRACION_ESTADO ON (FECEC_ESTADOS.ID_ESTADOS = FECEA_ADMINISTRACION_ESTADO.ID_ESTADOS) \n");
        query.append(" WHERE FECEA_ADMINISTRACION_ESTADO.ID_UNIDAD_ADMINISTRATIVA = ? \n");
        query.append(" ORDER BY FECEC_ESTADOS.ID_ESTADOS ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececEstadosMapper(), idUnidad.toString());

    }

    /**
     * Método para obtener todos los Estados
     *
     * @return List<FececEstados>
     */
    @Override
    public List<FececEstados> obtenerEstados() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY FECEC_ESTADOS.NOMBRE ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececEstadosMapper());

    }
}
