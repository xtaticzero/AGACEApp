/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetErrorNyvDao;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetErrorNyv;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetErrorNyvPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FecetErrorNyvDaoImpl implements FecetErrorNyvDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * Este atributo es un SELECT para seleccionar los datos de las columnas de
     * la tabla FECET_ALEGATO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ERROR_NYV, ID_ORDEN, CODIGO_RESPUESTA, MENSAJE_RESPUESTA, FECHA_RESPUESTA, OBSERVACIONES FROM ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetErrorNyvPk
     */
    public FecetErrorNyvPk insert(FecetErrorNyv dto) {

        StringBuilder query = new StringBuilder();

        dto.setIdErrorNyv(jdbcTemplate.queryForObject(
                "SELECT FECEQ_ERROR_NYV.NEXTVAL FROM DUAL",
                BigDecimal.class));

        query.append("INSERT INTO ").append(getTableName());
        query.append(" ( ID_ERROR_NYV, ID_ORDEN, CODIGO_RESPUESTA, MENSAJE_RESPUESTA, FECHA_RESPUESTA, ");
        query.append("OBSERVACIONES ) VALUES ( ?, ?, ?, ?, ?, ? )");

        jdbcTemplate.update(query.toString(), dto.getIdErrorNyv(),
                dto.getIdOrden(), dto.getCodigoRespuesta(),
                dto.getMensajeRespuesta(), dto.getFechaRespuesta(),
                dto.getObservaciones());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_ERROR_NYV table.
     */
    public void update(FecetErrorNyvPk pk, FecetErrorNyv dto) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(" SET ID_ERROR_NYV = ?, ID_ORDEN = ?, CODIGO_RESPUESTA = ?, MENSAJE_RESPUESTA = ?, ");
        query.append("FECHA_RESPUESTA = ?, OBSERVACIONES = ? WHERE ID_ERROR_NYV = ?");

        jdbcTemplate.update(query.toString(), dto.getIdErrorNyv(),
                dto.getIdOrden(), dto.getCodigoRespuesta(),
                dto.getMensajeRespuesta(), dto.getFechaRespuesta(),
                dto.getObservaciones(), pk.getIdErrorNyv());

    }

    /**
     * Deletes a single row in the FECET_ERROR_NYV table.
     */
    public void delete(FecetErrorNyvPk pk) {

        jdbcTemplate.update("DELETE FROM " + getTableName()
                + " WHERE ID_ERROR_NYV = ?", pk.getIdErrorNyv());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ERROR_NYV";
    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ERROR_NYV = :idErrorNyv'.
     */
    public FecetErrorNyv findByPrimaryKey(final BigDecimal idErrorNyv) {

        List<FecetErrorNyv> list = jdbcTemplate.query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_ERROR_NYV = ?").toString(),
                new FecetErrorNyVMapper(), idErrorNyv);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * ''.
     */
    public List<FecetErrorNyv> findAll() {

        return jdbcTemplate.query(SQL_SELECT.append(getTableName()).append(" ORDER BY ID_ERROR_NYV").toString(),
                new FecetErrorNyVMapper());

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ERROR_NYV = :idErrorNyv'.
     */
    public List<FecetErrorNyv> findWhereIdErrorNyvEquals(long idErrorNyv) {

        return jdbcTemplate.query(SQL_SELECT.append(
                getTableName()).append(" WHERE ID_ERROR_NYV = ? ORDER BY ID_ERROR_NYV").toString(),
                new FecetErrorNyVMapper(), idErrorNyv);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    public List<FecetErrorNyv> findWhereIdOrdenEquals(long idOrden) {

        return jdbcTemplate.query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN").toString(),
                new FecetErrorNyVMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'CODIGO_RESPUESTA = :codigoRespuesta'.
     */
    public List<FecetErrorNyv> findWhereCodigoRespuestaEquals(
            String codigoRespuesta) {

        return jdbcTemplate.query(SQL_SELECT.append(
                getTableName()).append(" WHERE CODIGO_RESPUESTA = ? ORDER BY CODIGO_RESPUESTA").toString(),
                new FecetErrorNyVMapper(), codigoRespuesta);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'MENSAJE_RESPUESTA = :mensajeRespuesta'.
     */
    public List<FecetErrorNyv> findWhereMensajeRespuestaEquals(
            String mensajeRespuesta) {

        return jdbcTemplate
                .query(SQL_SELECT.append(
                                getTableName()).append(" WHERE MENSAJE_RESPUESTA = ? ORDER BY MENSAJE_RESPUESTA").toString(),
                        new FecetErrorNyVMapper(), mensajeRespuesta);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'FECHA_RESPUESTA = :fechaRespuesta'.
     */
    public List<FecetErrorNyv> findWhereFechaRespuestaEquals(Date fechaRespuesta) {

        return jdbcTemplate.query(SQL_SELECT.append(
                getTableName()).append(" WHERE FECHA_RESPUESTA = ? ORDER BY FECHA_RESPUESTA").toString(),
                new FecetErrorNyVMapper(), fechaRespuesta);

    }

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'OBSERVACIONES = :observaciones'.
     */
    public List<FecetErrorNyv> findWhereObservacionesEquals(String observaciones) {

        return jdbcTemplate.query(SQL_SELECT.append(
                getTableName()).append(" WHERE OBSERVACIONES = ? ORDER BY OBSERVACIONES").toString(),
                new FecetErrorNyVMapper(), observaciones);

    }

    /**
     * Returns the rows from the FECET_ERROR_NYV table that matches the
     * specified primary-key value.
     */
    public FecetErrorNyv findByPrimaryKey(FecetErrorNyvPk pk) {
        return findByPrimaryKey(pk.getIdErrorNyv());
    }

    /**
     * Metodo setJdbcTemplate
     *
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Metodo getJdbcTemplate
     *
     * @return JdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
