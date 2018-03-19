package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuestaPk;

import org.springframework.stereotype.Repository;

@Repository("fececTipoPropuestaDao")
public class FececTipoPropuestaDaoImpl extends BaseJDBCDao<FececTipoPropuesta> implements FececTipoPropuestaDao {

    @SuppressWarnings("compatibility:2321917234451115400")
    private static final long serialVersionUID = -3338975713599884670L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_TIPO_PROPUESTA, DESCRIPCION FROM ").append(getTableName());

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoPropuestaPk
     */
    public FececTipoPropuestaPk insert(FececTipoPropuesta dto) {

        dto.setIdTipoPropuesta(getJdbcTemplateBase().queryForObject("SELECT FECEQ_TIPO_PROPUESTA.NEXTVAL FROM DUAL", BigDecimal.class));

        getJdbcTemplateBase().update("INSERT INTO " + getTableName() + " ( ID_TIPO_PROPUESTA, DESCRIPCION ) VALUES ( ?, ? )", dto.getIdTipoPropuesta(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_TIPO_PROPUESTA table.
     */
    public void update(FececTipoPropuestaPk pk, FececTipoPropuesta dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName() + " SET ID_TIPO_PROPUESTA = ?, DESCRIPCION = ? WHERE ID_TIPO_PROPUESTA = ?", dto.getIdTipoPropuesta(), dto.getDescripcion(), pk.getIdTipoPropuesta());

    }

    /**
     * Deletes a single row in the FECEC_TIPO_PROPUESTA table.
     */
    public void delete(FececTipoPropuestaPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_TIPO_PROPUESTA = ?", pk.getIdTipoPropuesta());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_TIPO_PROPUESTA";
    }

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'ID_TIPO_PROPUESTA = :idTipoPropuesta'.
     */
    public FececTipoPropuesta findByPrimaryKey(BigDecimal idTipoPropuesta) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        List<FececTipoPropuesta> list = getJdbcTemplateBase().query(
                sql.append(" WHERE ID_TIPO_PROPUESTA = ?").toString(),
                new FececTipoPropuestaMapper(), idTipoPropuesta);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria ''.
     */
    public List<FececTipoPropuesta> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.toString() + " ORDER BY ID_TIPO_PROPUESTA",
                new FececTipoPropuestaMapper());

    }

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'ID_TIPO_PROPUESTA = :idTipoPropuesta'.
     */
    public List<FececTipoPropuesta> findWhereIdTipoPropuestaEquals(BigDecimal idTipoPropuesta) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql + " WHERE ID_TIPO_PROPUESTA = ? ORDER BY ID_TIPO_PROPUESTA",
                new FececTipoPropuestaMapper(), idTipoPropuesta);

    }

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececTipoPropuesta> findWhereDescripcionEquals(String descripcion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION").toString(),
                new FececTipoPropuestaMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_TIPO_PROPUESTA table that matches the
     * specified primary-key value.
     */
    public FececTipoPropuesta findByPrimaryKey(FececTipoPropuestaPk pk) {
        return findByPrimaryKey(pk.getIdTipoPropuesta());
    }
}
