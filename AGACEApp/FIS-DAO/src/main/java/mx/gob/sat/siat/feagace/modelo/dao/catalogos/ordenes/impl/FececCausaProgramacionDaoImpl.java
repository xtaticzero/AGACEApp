package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececCausaProgramacionMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacionPk;

@Repository("fececCausaProgramacionDao")
public class FececCausaProgramacionDaoImpl extends BaseJDBCDao<FececCausaProgramacion> implements FececCausaProgramacionDao {

    @SuppressWarnings("compatibility:-8125147878505583754")
    private static final long serialVersionUID = 5063887757805590068L;

    private static final String SQL_SELECT = "SELECT ID_CAUSA_PROGRAMACION, DESCRIPCION FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececCausaProgramacionPk
     */
    public FececCausaProgramacionPk insert(FececCausaProgramacion dto) {

        dto.setIdCausaProgramacion(getJdbcTemplateBase().queryForObject("SELECT FECEQ_CAUSA_PROGRAMACION.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_CAUSA_PROGRAMACION, DESCRIPCION ) VALUES ( ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdCausaProgramacion(),
                dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_CAUSA_PROGRAMACION table.
     */
    public void update(FececCausaProgramacionPk pk, FececCausaProgramacion dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_CAUSA_PROGRAMACION = ?, DESCRIPCION = ? WHERE ID_CAUSA_PROGRAMACION = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdCausaProgramacion(), dto.getDescripcion(), pk.getIdCausaProgramacion());

    }

    /**
     * Deletes a single row in the FECEC_CAUSA_PROGRAMACION table.
     */
    public void delete(FececCausaProgramacionPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_CAUSA_PROGRAMACION = ?");
        getJdbcTemplateBase().update(query.toString(),
                pk.getIdCausaProgramacion());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_CAUSA_PROGRAMACION";
    }

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'ID_CAUSA_PROGRAMACION = :idCausaProgramacion'.
     */
    public FececCausaProgramacion findByPrimaryKey(BigDecimal idCausaProgramacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CAUSA_PROGRAMACION = ?");
        List<FececCausaProgramacion> list
                = getJdbcTemplateBase().query(query.toString(), new FececCausaProgramacionMapper(),
                        idCausaProgramacion);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria ''.
     */
    public List<FececCausaProgramacion> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_CAUSA_PROGRAMACION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececCausaProgramacionMapper());

    }

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'ID_CAUSA_PROGRAMACION = :idCausaProgramacion'.
     */
    public List<FececCausaProgramacion> findWhereIdCausaProgramacionEquals(BigDecimal idCausaProgramacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CAUSA_PROGRAMACION = ? ORDER BY ID_CAUSA_PROGRAMACION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececCausaProgramacionMapper(), idCausaProgramacion);

    }

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececCausaProgramacion> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? AND BLN_ACTIVO = 1 ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececCausaProgramacionMapper(), descripcion.toUpperCase());

    }

    /**
     * Returns the rows from the FECEC_CAUSA_PROGRAMACION table that matches the
     * specified primary-key value.
     */
    public FececCausaProgramacion findByPrimaryKey(FececCausaProgramacionPk pk) {
        return findByPrimaryKey(pk.getIdCausaProgramacion());
    }

    @Override
    public List<FececCausaProgramacion> findActivos() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE BLN_ACTIVO = 1 ORDER BY ID_CAUSA_PROGRAMACION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececCausaProgramacionMapper());
    }

}
