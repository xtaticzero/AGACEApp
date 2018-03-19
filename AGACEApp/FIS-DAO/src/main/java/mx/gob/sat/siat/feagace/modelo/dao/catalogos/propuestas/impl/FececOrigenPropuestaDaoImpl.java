package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececOrigenPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececOrigenPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececOrigenPropuesta;

import org.springframework.stereotype.Repository;

@Repository("fececOrigenPropuestaDao")
public class FececOrigenPropuestaDaoImpl extends BaseJDBCDao<FececOrigenPropuesta> implements FececOrigenPropuestaDao {

    @SuppressWarnings("compatibility:-847364854279307229")
    private static final long serialVersionUID = -6555091351662811000L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ORIGEN, DESCRIPCION FROM ").append(getTableName());

    /**
     * Method 'insert'
     *
     * @param dto
     * @return void
     */
    @Override
    public void insert(FececOrigenPropuesta dto) {
        dto.setIdOrigen(getJdbcTemplateBase().queryForObject("SELECT FECEQ_TIPO_PROPUESTA.NEXTVAL FROM DUAL", BigDecimal.class));
        getJdbcTemplateBase().update("INSERT INTO " + getTableName() + " ( ID_ORIGEN, DESCRIPCION ) VALUES ( ?, ? )", dto.getIdOrigen(), dto.getDescripcion());
    }

    /**
     * Updates a single row in the FECEC_ORIGEN_PROPUESTA table.
     */
    @Override
    public void update(FececOrigenPropuesta dto) {
        getJdbcTemplateBase().update("UPDATE " + getTableName() + " SET ID_ORIGEN = ?, DESCRIPCION = ? WHERE ID_ORIGEN = ?", dto.getIdOrigen(), dto.getDescripcion(), dto.getIdOrigen());
    }

    /**
     * Deletes a single row in the FECEC_ORIGEN_PROPUESTA table.
     */
    @Override
    public void delete(FececOrigenPropuesta dto) {
        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_ORIGEN = ?", dto.getIdOrigen());
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ORIGEN_PROPUESTA";
    }

    /**
     * Returns all rows from the FECEC_ORIGEN_PROPUESTA table that match the
     * criteria 'ID_ORIGEN = :idOrigenPropuesta'.
     */
    @Override
    public FececOrigenPropuesta findByPrimaryKey(BigDecimal idOrigenPropuesta) {

        List<FececOrigenPropuesta> list = getJdbcTemplateBase().query(
                SQL_SELECT.append(" WHERE ID_ORIGEN = ?").toString(),
                new FececOrigenPropuestaMapper(), idOrigenPropuesta);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ORIGEN_PROPUESTA table that match the
     * criteria ''.
     */
    @Override
    public List<FececOrigenPropuesta> findAll() {
        return getJdbcTemplateBase().query(SQL_SELECT.toString() + " ORDER BY ID_ORIGEN",
                new FececOrigenPropuestaMapper());
    }

    /**
     * Returns all rows from the FECEC_ORIGEN_PROPUESTA table that match the
     * criteria 'ID_ORIGEN = :idOrigenPropuesta'.
     */
    @Override
    public List<FececOrigenPropuesta> findWhereIdOrigenPropuestaEquals(BigDecimal idOrigenPropuesta) {
        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE ID_ORIGEN = ? ORDER BY ID_ORIGEN").toString(),
                new FececOrigenPropuestaMapper(), idOrigenPropuesta);
    }

    /**
     * Returns all rows from the FECEC_ORIGEN_PROPUESTA table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    @Override
    public List<FececOrigenPropuesta> findWhereDescripcionEquals(String descripcion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION").toString(),
                new FececOrigenPropuestaMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_ORIGEN_PROPUESTA table that matches the
     * specified primary-key value.
     */
    @Override
    public FececOrigenPropuesta findByPrimaryKey(FececOrigenPropuesta pk) {
        return findByPrimaryKey(pk.getIdOrigen());
    }
}
