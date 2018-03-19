package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececActividadPreponderanteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececActividadPreponderanteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderantePk;

import org.springframework.stereotype.Repository;

@Repository("fececActividadPreponderanteDao")
public class FececActividadPreponderanteDaoImpl extends BaseJDBCDao<FececActividadPreponderante> implements FececActividadPreponderanteDao {

    private static final String SQL_SELECT = "SELECT ID_ACTIVIDAD_PREPONDERANTE, DESCRIPCION FROM " + getTableName();
    private static final String SQL_NEXTVAL = "SELECT FECEQ_ACTIVIDAD_PREPONDERANTE.NEXTVAL FROM DUAL";
    @SuppressWarnings("compatibility:-7788064780252397057")
    private static final long serialVersionUID = 1L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececActividadPreponderantePk
     */
    public FececActividadPreponderantePk insert(FececActividadPreponderante dto) {

        BigDecimal queryForObject = getJdbcTemplateBase().queryForObject(SQL_NEXTVAL, BigDecimal.class);
        dto.setIdActividadPreponderante(queryForObject);
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ACTIVIDAD_PREPONDERANTE, DESCRIPCION ) VALUES ( ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdActividadPreponderante(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    public void update(FececActividadPreponderantePk pk, FececActividadPreponderante dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ACTIVIDAD_PREPONDERANTE = ?, DESCRIPCION = ? WHERE ID_ACTIVIDAD_PREPONDERANTE = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdActividadPreponderante(), dto.getDescripcion(),
                pk.getIdActividadPreponderante());

    }

    /**
     * Deletes a single row in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    public void delete(FececActividadPreponderantePk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_ACTIVIDAD_PREPONDERANTE = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdActividadPreponderante());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ACTIVIDAD_PREPONDERANTE";
    }

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'ID_ACTIVIDAD_PREPONDERANTE = :idActividadPreponderante'.
     */
    public FececActividadPreponderante findByPrimaryKey(BigDecimal idActividadPreponderante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ACTIVIDAD_PREPONDERANTE = ?");
        List<FececActividadPreponderante> list
                = getJdbcTemplateBase().query(query.toString(), new FececActividadPreponderanteMapper(),
                        idActividadPreponderante);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria ''.
     */
    public List<FececActividadPreponderante> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_ACTIVIDAD_PREPONDERANTE");
        return getJdbcTemplateBase().query(query.toString(), new FececActividadPreponderanteMapper());

    }

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'ID_ACTIVIDAD_PREPONDERANTE = :idActividadPreponderante'.
     */
    public List<FececActividadPreponderante> findWhereIdActividadPreponderanteEquals(BigDecimal idActividadPreponderante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ACTIVIDAD_PREPONDERANTE = ? ORDER BY ID_ACTIVIDAD_PREPONDERANTE");
        return getJdbcTemplateBase().query(query.toString(), new FececActividadPreponderanteMapper(),
                idActividadPreponderante);

    }

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececActividadPreponderante> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(), new FececActividadPreponderanteMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that
     * matches the specified primary-key value.
     */
    public FececActividadPreponderante findByPrimaryKey(FececActividadPreponderantePk pk) {
        return findByPrimaryKey(pk.getIdActividadPreponderante());
    }

}
