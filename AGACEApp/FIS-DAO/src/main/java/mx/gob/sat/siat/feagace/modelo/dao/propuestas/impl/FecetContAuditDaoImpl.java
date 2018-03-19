/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetContAuditDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetContAuditMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAudit;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAuditPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FecetContAuditDaoImpl implements FecetContAuditDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * Este atributo es un SELECT para obtener los datos de la tabla
     * FECET_CONT_AUDIT
     */
    private static final String SQL_SELECT
            = "SELECT ID_CONT_AUDIT, FECHA_CARGA, NOMBRE_ARCHIVO, RUTA_ARCHIVO, RFC_AUDITOR, NOMBRE_AUDITOR FROM ";

    /**
     * Este atributo corresponde a una condicion WHERE para obtener los datos de
     * la tabla FECET_CONT_AUDIT de acuerdo a un ID_CONT_AUDIT
     */
    private static final String SQL_WHERE = " WHERE ID_CONT_AUDIT = ? ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetContAuditPk
     */
    @Override
    public FecetContAuditPk insert(FecetContAudit dto) {

        StringBuilder query = new StringBuilder();

        dto.setIdContAudit(jdbcTemplate.queryForObject("SELECT FECEQ_CONT_AUDIT.NEXTVAL FROM DUAL",
                BigDecimal.class));

        query.append("INSERT INTO ").append(getTableName());
        query.append(" ( ID_CONT_AUDIT, FECHA_CARGA, NOMBRE_ARCHIVO, RUTA_ARCHIVO, RFC_AUDITOR, ");
        query.append("NOMBRE_AUDITOR ) VALUES ( ?, ?, ?, ?, ?, ? )");

        jdbcTemplate.update(query.toString(),
                dto.getIdContAudit(), dto.getFechaCarga(), dto.getNombreArchivo(),
                dto.getRutaArchivo(), dto.getRfcAuditor(), dto.getNombreAuditor());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_CONT_AUDIT table.
     */
    @Override
    public void update(FecetContAuditPk pk, FecetContAudit dto) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(" SET ID_CONT_AUDIT = ?, FECHA_CARGA = ?, NOMBRE_ARCHIVO = ?, RUTA_ARCHIVO = ?, ");
        query.append("RFC_AUDITOR = ?, NOMBRE_AUDITOR = ? WHERE ID_CONT_AUDIT = ?");

        jdbcTemplate.update(query.toString(),
                dto.getIdContAudit(), dto.getFechaCarga(), dto.getNombreArchivo(),
                dto.getRutaArchivo(), dto.getRfcAuditor(), dto.getNombreAuditor(), pk.getIdContAudit());

    }

    /**
     * Updates a single row in the FECET_CONT_AUDIT table.
     */
    @Override
    public void updateFirmado(final BigDecimal idContAudit, final String nombreArchivo,
            final String rutaArchivo) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(" SET NOMBRE_ARCHIVO = ?, RUTA_ARCHIVO = ? WHERE ID_CONT_AUDIT = ?");

        jdbcTemplate.update(query.toString(), nombreArchivo, rutaArchivo, idContAudit);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_CONT_AUDIT";
    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'ID_CONT_AUDIT = :idContAudit'.
     */
    @Override
    public FecetContAudit findByPrimaryKey(BigDecimal idContAudit) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(SQL_WHERE);
        List<FecetContAudit> list
                = jdbcTemplate.query(query.toString(), new FecetContAuditMapper(), idContAudit);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * ''.
     */
    @Override
    public List<FecetContAudit> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_CONT_AUDIT");
        return jdbcTemplate.query(query.toString(),
                new FecetContAuditMapper());
    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'ID_CONT_AUDIT = :idContAudit'.
     */
    @Override
    public List<FecetContAudit> findWhereIdContAuditEquals(BigDecimal idContAudit) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_CONT_AUDIT = ? ORDER BY ID_CONT_AUDIT");
        return jdbcTemplate.query(query.toString(),
                new FecetContAuditMapper(), idContAudit);
    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'FECHA_CARGA = :fechaCarga'.
     */
    @Override
    public List<FecetContAudit> findWhereFechaCargaEquals(Date fechaCarga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA");
        return jdbcTemplate.query(query.toString(),
                new FecetContAuditMapper(), fechaCarga);
    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombreArchivo'.
     */
    @Override
    public List<FecetContAudit> findWhereNombreArchivoEquals(String nombreArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE_ARCHIVO = ? ORDER BY NOMBRE_ARCHIVO");
        return jdbcTemplate.query(query.toString(), new FecetContAuditMapper(),
                nombreArchivo);

    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    @Override
    public List<FecetContAudit> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return jdbcTemplate.query(query.toString(),
                new FecetContAuditMapper(), rutaArchivo);
    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'RFC_AUDITOR = :rfcAuditor'.
     */
    @Override
    public List<FecetContAudit> findWhereRfcAuditorEquals(String rfcAuditor) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC_AUDITOR = ? ORDER BY RFC_AUDITOR");
        return jdbcTemplate.query(query.toString(),
                new FecetContAuditMapper(), rfcAuditor);

    }

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'NOMBRE_AUDITOR = :nombreAuditor'.
     */
    @Override
    public List<FecetContAudit> findWhereNombreAuditorEquals(String nombreAuditor) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE_AUDITOR = ? ORDER BY NOMBRE_AUDITOR");
        return jdbcTemplate.query(query.toString(), new FecetContAuditMapper(),
                nombreAuditor);
    }

    /**
     * Returns the rows from the FECET_CONT_AUDIT table that matches the
     * specified primary-key value.
     */
    @Override
    public FecetContAudit findByPrimaryKey(FecetContAuditPk pk) {
        return findByPrimaryKey(pk.getIdContAudit());
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
