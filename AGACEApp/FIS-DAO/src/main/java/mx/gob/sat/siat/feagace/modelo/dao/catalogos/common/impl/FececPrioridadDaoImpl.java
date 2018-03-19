package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececPrioridadMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;

@Repository("fececPrioridadDao")
public class FececPrioridadDaoImpl extends BaseJDBCDao<FececPrioridad> implements FececPrioridadDao {

    private static final String SQL_SELECT = "SELECT ID_PRIORIDAD, VALOR, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO FROM " + getTableName();

    private static final long serialVersionUID = 1L;

    public static String getTableName() {
        return "FECEC_PRIORIDAD";
    }

    @Override
    public List<FececPrioridad> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_PRIORIDAD ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececPrioridadMapper());
    }

    @Override
    public List<FececPrioridad> findActivos() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE BLN_ACTIVO = 1 ");
        query.append(" ORDER BY ID_PRIORIDAD ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececPrioridadMapper());
    }

    @Override
    public int insert(FececPrioridad dto) {

        dto.setIdPrioridad(getJdbcTemplateBase().queryForObject("SELECT FECEQ_PRIORIDAD.NEXTVAL FROM DUAL",
                BigDecimal.class).intValue());

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_PRIORIDAD, VALOR, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO ) VALUES ( ?, ?, ?, ?, ? )");

        return getJdbcTemplateBase().update(query.toString(), dto.getIdPrioridad(), dto.getPrioridad(),
                dto.getFechaInicio(), dto.getFechaFin(), dto.getBlnActivo());
    }

    @Override
    public List<FececPrioridad> findPrioridadByEquals(String prioridad) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE VALOR = ? AND BLN_ACTIVO = 1 ");
        query.append(" ORDER BY ID_PRIORIDAD ASC");

        return getJdbcTemplateBase().query(query.toString(), new FececPrioridadMapper(), prioridad);
    }
}
