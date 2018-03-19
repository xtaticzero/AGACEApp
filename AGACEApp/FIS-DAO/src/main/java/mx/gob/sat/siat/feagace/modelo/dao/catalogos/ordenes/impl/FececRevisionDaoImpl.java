package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececRevisionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevisionPk;

import org.springframework.stereotype.Repository;

@Repository("fececRevisionDao")
public class FececRevisionDaoImpl extends BaseJDBCDao<FececRevision> implements FececRevisionDao {

    @SuppressWarnings("compatibility:-4269699017096742149")
    private static final long serialVersionUID = -6823334843098691866L;

    private static final String SQL_SELECT = "SELECT ID_REVISION, DESCRIPCION FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececRevisionPk
     */
    public FececRevisionPk insert(FececRevision dto) {

        dto.setIdRevision(getJdbcTemplateBase().queryForObject("SELECT FECEQ_REVISION.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_REVISION, DESCRIPCION ) VALUES ( ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdRevision(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_REVISION table.
     */
    public void update(FececRevisionPk pk, FececRevision dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_REVISION = ?, DESCRIPCION = ? WHERE ID_REVISION = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdRevision(), dto.getDescripcion(), pk.getIdRevision());

    }

    /**
     * Deletes a single row in the FECEC_REVISION table.
     */
    public void delete(FececRevisionPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_REVISION = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdRevision());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_REVISION";
    }

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    public FececRevision findByPrimaryKey(BigDecimal idRevision) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_REVISION = ?");
        List<FececRevision> list = getJdbcTemplateBase().query(query.toString(), new FececRevisionMapper(), idRevision);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * ''.
     */
    public List<FececRevision> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_REVISION");
        return getJdbcTemplateBase().query(query.toString(), new FececRevisionMapper());

    }

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    public List<FececRevision> findWhereIdRevisionEquals(BigDecimal idRevision) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_REVISION = ? ORDER BY ID_REVISION");
        return getJdbcTemplateBase().query(query.toString(), new FececRevisionMapper(), idRevision);

    }

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececRevision> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(), new FececRevisionMapper(), descripcion);

    }

    /**
     * Returns DESCRIPCION from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    public String getDescripcionRevision(final BigDecimal idRevision) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DESCRIPCION FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_REVISION = ?");
        return getJdbcTemplateBase().queryForObject(query.toString(), String.class, idRevision);

    }

    /**
     * Returns the rows from the FECEC_REVISION table that matches the specified
     * primary-key value.
     */
    public FececRevision findByPrimaryKey(FececRevisionPk pk) {
        return findByPrimaryKey(pk.getIdRevision());
    }
}
