package mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FececPrioridadMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("fececPrioridadInsumoDao")
public class FececPrioridadDaoImpl extends BaseJDBCDao<FececPrioridad> implements FececPrioridadDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final StringBuilder SQL_SELECT = new StringBuilder("SELECT ID_PRIORIDAD, ID_GENERAL,VALOR, DESCRIPCION,FECHA_INICIO, FECHA_FIN, BLN_ACTIVO \n FROM ").append(getTableName());

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_PRIORIDAD";
    }

    /**
     * Returns all rows from the FECEC_PRIORIDAD table that match the criteria
     * ''.
     */
    public List<FececPrioridad> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" ORDER BY ID_PRIORIDAD");
        return getJdbcTemplateBase().query(sql.toString(), new FececPrioridadMapper());

    }

    public List<FececPrioridad> findActivos(BigDecimal idGeneral) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append("\n WHERE BLN_ACTIVO = ? AND ID_GENERAL = ? ");
        sql.append("\n ORDER BY ID_PRIORIDAD");
        return getJdbcTemplateBase().query(sql.toString(), new FececPrioridadMapper(), Constantes.ENTERO_UNO, idGeneral);

    }
}
