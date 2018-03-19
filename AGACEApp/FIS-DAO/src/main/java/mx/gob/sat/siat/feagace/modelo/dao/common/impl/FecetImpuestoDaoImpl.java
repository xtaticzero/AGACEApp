package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetImpuestoConTipoImpuestoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetImpuestoDescripcionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetImpuestoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoPk;

import org.springframework.stereotype.Repository;

@Repository("fecetImpuestoDao")
public class FecetImpuestoDaoImpl extends BaseJDBCDao<FecetImpuesto> implements FecetImpuestoDao {

    @SuppressWarnings("compatibility:9106712534948203801")
    private static final long serialVersionUID = 5988616919471855217L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_IMPUESTO, ID_PROPUESTA, ID_TIPO_IMPUESTO, MONTO,ID_CONCEPTO, FECHA_BAJA FROM ").append(getTableName());

    private StringBuilder getSqlSelectImpuestoDescripcion() {
        StringBuilder query = new StringBuilder();

        query.append("SELECT \n");
        query.append("    FECET_IMPUESTO.MONTO, \n");
        query.append("    FECEC_TIPO_IMPUESTO.DESCRIPCION, \n ");
        query.append("FECEC_CONCEPTOS.DESCRIPCION DESCRIPCION_CONCEPTO, \n ");
        query.append("    FECET_IMPUESTO.ID_PROPUESTA \n ");
        query.append("FROM FECET_IMPUESTO " + "\n");
        query.append(" INNER JOIN \n");
        query.append("    FECEC_TIPO_IMPUESTO ON ( \n");
        query.append("    FECET_IMPUESTO.ID_TIPO_IMPUESTO = \n ");
        query.append("    FECEC_TIPO_IMPUESTO.ID_TIPO_IMPUESTO) \n ");
        query.append(" INNER JOIN \n");
        query.append("    FECEC_CONCEPTOS ON ( \n");
        query.append("    FECEC_CONCEPTOS.ID_CONCEPTO = \n ");
        query.append("    FECET_IMPUESTO.ID_CONCEPTO) \n ");

        return query;
    }

    private StringBuilder getSqlSelectImpuestoYTipo() {
        StringBuilder query = new StringBuilder();

        query.append("SELECT \n ");
        query.append(" FECET_IMPUESTO.ID_IMPUESTO  \n ");
        query.append(", FECET_IMPUESTO.ID_PROPUESTA \n ");
        query.append(", FECET_IMPUESTO.ID_TIPO_IMPUESTO \n ");
        query.append(", FECET_IMPUESTO.MONTO \n ");
        query.append(", FECET_IMPUESTO.ID_CONCEPTO  \n ");
        query.append(", FECET_IMPUESTO.FECHA_BAJA  \n ");
        query.append(", FECEC_TIPO_IMPUESTO.ID_TIPO_IMPUESTO \n ");
        query.append(", FECEC_TIPO_IMPUESTO.DESCRIPCION \n ");
        query.append(", FECEC_TIPO_IMPUESTO.ABREVIATURA \n ");
        query.append(", FECEC_CONCEPTOS.DESCRIPCION as CONCEPTO\n ");
        query.append(" FROM FECET_IMPUESTO  \n ");
        query.append(" INNER JOIN FECEC_TIPO_IMPUESTO \n ");
        query.append("ON FECET_IMPUESTO.ID_TIPO_IMPUESTO = FECEC_TIPO_IMPUESTO.ID_TIPO_IMPUESTO ");
        query.append("INNER JOIN FECEC_CONCEPTOS \n ");
        query.append(" ON FECEC_CONCEPTOS.ID_CONCEPTO= FECET_IMPUESTO.ID_CONCEPTO");

        return query;
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetImpuestoPk
     */
    public FecetImpuestoPk insert(FecetImpuesto dto) {
        dto.setIdImpuesto(getJdbcTemplateBase().queryForObject("SELECT FECEQ_IMPUESTO.NEXTVAL FROM DUAL", BigDecimal.class));

        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_IMPUESTO, ID_PROPUESTA, ID_TIPO_IMPUESTO, MONTO, FECHA_BAJA, ID_CONCEPTO,ID_RETROALIMENTACION ) VALUES ( ?, ?, ?, ?, ?, ?,? )",
                dto.getIdImpuesto(), dto.getIdPropuesta(), dto.getIdTipoImpuesto(), dto.getMonto(),
                dto.getFechaBaja(), dto.getIdConcepto(), dto.getIdRetroalimentacion());

        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_IMPUESTO table.
     */
    public void update(FecetImpuestoPk pk, FecetImpuesto dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_IMPUESTO = ?, ID_PROPUESTA = ?, ID_TIPO_IMPUESTO = ?, MONTO = ?, FECHA_BAJA = ? WHERE ID_IMPUESTO = ?",
                dto.getIdImpuesto(), dto.getIdPropuesta(), dto.getIdTipoImpuesto(), dto.getMonto(),
                dto.getFechaBaja(), pk.getIdImpuesto());

    }

    /**
     * Deletes a single row in the FECET_IMPUESTO table.
     */
    public void delete(FecetImpuestoPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_IMPUESTO = ?", pk.getIdImpuesto());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_IMPUESTO";
    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_IMPUESTO = :idImpuesto'.
     */
    public FecetImpuesto findByPrimaryKey(BigDecimal idImpuesto) {

        List<FecetImpuesto> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_IMPUESTO = ?").toString(),
                        new FecetImpuestoMapper(), idImpuesto);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * ''.
     */
    public List<FecetImpuesto> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_IMPUESTO").toString(),
                new FecetImpuestoMapper());

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    public List<FecetImpuesto> findByFececTipoImpuesto(BigDecimal idTipoImpuesto) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_TIPO_IMPUESTO = ?").toString(),
                new FecetImpuestoMapper(), idTipoImpuesto);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_IMPUESTO = :idImpuesto'.
     */
    public List<FecetImpuesto> findWhereIdImpuestoEquals(BigDecimal idImpuesto) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_IMPUESTO = ? ORDER BY ID_IMPUESTO").toString(),
                new FecetImpuestoMapper(), idImpuesto);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetImpuesto> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? AND FECHA_BAJA is null and ID_RETROALIMENTACION  is null ORDER BY ID_PROPUESTA ");
        return getJdbcTemplateBase().query(query.toString(), new FecetImpuestoMapper(), idPropuesta);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetImpuestoDescripcion> traeImpuestoDescripcion(BigDecimal idPropuesta) {

        return getJdbcTemplateBase().query(
                getSqlSelectImpuestoDescripcion().append(" WHERE FECET_IMPUESTO.ID_PROPUESTA = ? ")
                .append(" AND FECET_IMPUESTO.FECHA_BAJA IS NULL AND FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL order by FECET_IMPUESTO.ID_IMPUESTO ").toString(), new FecetImpuestoDescripcionMapper(),
                idPropuesta);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    public List<FecetImpuesto> findWhereIdTipoImpuestoEquals(BigDecimal idTipoImpuesto) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(" WHERE ID_TIPO_IMPUESTO = ? ORDER BY ID_TIPO_IMPUESTO").toString(),
                new FecetImpuestoMapper(), idTipoImpuesto);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'MONTO = :monto'.
     */
    public List<FecetImpuesto> findWhereMontoEquals(BigDecimal monto) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE MONTO = ? ORDER BY MONTO").toString(),
                new FecetImpuestoMapper(), monto);

    }

    /**
     * Returns the rows from the FECET_IMPUESTO table that matches the specified
     * primary-key value.
     */
    public FecetImpuesto findByPrimaryKey(FecetImpuestoPk pk) {
        return findByPrimaryKey(pk.getIdImpuesto());
    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'PERIODO_INICIAL = :periodoInicial'.
     */
    public List<FecetImpuesto> findWherePeriodoInicialEquals(final Date periodoInicial) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE PERIODO_INICIAL = ? ORDER BY PERIODO_INICIAL").toString(),
                new FecetImpuestoMapper(), periodoInicial);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'PERIODO_FINAL = :periodoFinal'.
     */
    public List<FecetImpuesto> findWherePeriodoFinalEquals(final Date periodoFinal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE PERIODO_FINAL = ? ORDER BY PERIODO_FINAL").toString(),
                new FecetImpuestoMapper(), periodoFinal);

    }

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    public List<FecetImpuesto> findWhereFechaBajaEquals(final Date fechaBaja) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_BAJA = ? ORDER BY FECHA_BAJA").toString(),
                new FecetImpuestoMapper(), fechaBaja);

    }

    /**
     * Regresa la presuntiva de la suma de los impuestos asignados a una
     * Propuesta.
     */
    public BigDecimal getPresuntivaPropuesta(final BigDecimal idPropuesta) {

        final String querySumaPresuntiva = "SELECT SUM(MONTO) FROM FECET_IMPUESTO WHERE ID_PROPUESTA = ?";
        return getJdbcTemplateBase().queryForObject(querySumaPresuntiva, BigDecimal.class, idPropuesta);

    }

    public List<FecetImpuesto> getImpuestosPropuesta(final BigDecimal idPropuesta) {

        StringBuilder sql = new StringBuilder();

        sql.append(getSqlSelectImpuestoYTipo());
        sql.append(" WHERE FECET_IMPUESTO.ID_PROPUESTA = ? ");
        sql.append(" AND FECET_IMPUESTO.FECHA_BAJA IS NULL AND ID_RETROALIMENTACION IS NULL ");
        sql.append(" ORDER BY FECET_IMPUESTO.ID_PROPUESTA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetImpuestoConTipoImpuestoMapper(), idPropuesta);

    }

    public List<FecetImpuesto> getImpuestosRetroPropuesta(final BigDecimal idRetro) {

        StringBuilder sql = new StringBuilder();

        sql.append(getSqlSelectImpuestoYTipo());
        sql.append(" WHERE FECET_IMPUESTO.ID_RETROALIMENTACION = ? ");
        sql.append(" ORDER BY FECET_IMPUESTO.ID_PROPUESTA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetImpuestoConTipoImpuestoMapper(), idRetro);

    }
}
