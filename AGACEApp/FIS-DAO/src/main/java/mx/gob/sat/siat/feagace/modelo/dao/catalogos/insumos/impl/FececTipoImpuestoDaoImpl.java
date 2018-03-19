package mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoImpuestoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuestoPk;

import org.springframework.stereotype.Repository;

@Repository("fececTipoImpuestoDao")
public class FececTipoImpuestoDaoImpl extends BaseJDBCDao<FececTipoImpuesto> implements FececTipoImpuestoDao {

    @SuppressWarnings("compatibility:4328545397066759284")
    private static final long serialVersionUID = -480268882429456970L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_TIPO_IMPUESTO, DESCRIPCION, ABREVIATURA FROM ").append(getTableName());

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoImpuestoPk
     */
    public FececTipoImpuestoPk insert(FececTipoImpuesto dto) {

        dto.setIdTipoImpuesto(getJdbcTemplateBase().queryForObject("SELECT FECEQ_TIPO_IMPUESTO.NEXTVAL FROM DUAL",
                BigDecimal.class));

        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_TIPO_IMPUESTO, DESCRIPCION, ABREVIATURA ) VALUES ( ?, ?, ? )",
                dto.getIdTipoImpuesto(), dto.getDescripcion(), dto.getAbreviatura());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_TIPO_IMPUESTO table.
     */
    public void update(FececTipoImpuestoPk pk, FececTipoImpuesto dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_TIPO_IMPUESTO = ?, DESCRIPCION = ?, ABREVIATURA = ? WHERE ID_TIPO_IMPUESTO = ?",
                dto.getIdTipoImpuesto(), dto.getDescripcion(), dto.getAbreviatura(),
                pk.getIdTipoImpuesto());

    }

    /**
     * Deletes a single row in the FECEC_TIPO_IMPUESTO table.
     */
    public void delete(FececTipoImpuestoPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_TIPO_IMPUESTO = ?",
                pk.getIdTipoImpuesto());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_TIPO_IMPUESTO";
    }

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    public FececTipoImpuesto findByPrimaryKey(BigDecimal idTipoImpuesto) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        List<FececTipoImpuesto> list
                = getJdbcTemplateBase().query(sql.append(" WHERE ID_TIPO_IMPUESTO = ?").toString(),
                        new FececTipoImpuestoMapper(), idTipoImpuesto);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria ''.
     */
    public List<FececTipoImpuesto> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" ORDER BY ID_TIPO_IMPUESTO");
        return getJdbcTemplateBase().query(sql.toString(), new FececTipoImpuestoMapper());

    }

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    public List<FececTipoImpuesto> findWhereIdTipoImpuestoEquals(BigDecimal idTipoImpuesto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_TIPO_IMPUESTO = ? ORDER BY ID_TIPO_IMPUESTO");

        return getJdbcTemplateBase().query(query.toString(), new FececTipoImpuestoMapper(), idTipoImpuesto);
    }

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececTipoImpuesto> findWhereDescripcionEquals(String descripcion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION").toString(),
                new FececTipoImpuestoMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ABREVIATURA = :abreviatura'.
     */
    public List<FececTipoImpuesto> findWhereAbreviaturaEquals(String abreviatura) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);

        return getJdbcTemplateBase().query(sql.append(" WHERE ABREVIATURA = ? ORDER BY ABREVIATURA").toString(),
                new FececTipoImpuestoMapper(), abreviatura);

    }

    /**
     * Returns the rows from the FECEC_TIPO_IMPUESTO table that matches the
     * specified primary-key value.
     */
    public FececTipoImpuesto findByPrimaryKey(FececTipoImpuestoPk pk) {
        return findByPrimaryKey(pk.getIdTipoImpuesto());
    }
}
