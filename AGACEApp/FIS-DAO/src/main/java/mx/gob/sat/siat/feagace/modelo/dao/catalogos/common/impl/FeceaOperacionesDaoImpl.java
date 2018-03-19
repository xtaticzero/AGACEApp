/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import mx.gob.sat.siat.feagace.modelo.dto.common.FeceaOperaciones;
import mx.gob.sat.siat.feagace.modelo.dto.common.FeceaOperacionesPk;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaOperacionesDao;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaOperacionesMapper;

import org.springframework.jdbc.core.JdbcTemplate;

public class FeceaOperacionesDaoImpl implements FeceaOperacionesDao {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT = "SELECT ID_OPERACION, ID_MODULO, ID_SUBMODULO, ID_ACCION, FECHA FROM ";

    /**
     * Method 'getIdConsecutivo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdConsecutivo() {

        return jdbcTemplate.queryForObject("SELECT FECEQ_OPERACIONES.NEXTVAL FROM DUAL", BigDecimal.class);

    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FeceaOperacionesPk
     */
    public FeceaOperacionesPk insert(FeceaOperaciones dto) {

        if (dto.getIdOperacion() == null) {
            dto.setIdOperacion(getIdConsecutivo());
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_OPERACION, ID_MODULO, ID_SUBMODULO, ID_ACCION, FECHA ) VALUES ( ?, ?, ?, ?, ? )");

        jdbcTemplate.update(query.toString(),
                dto.getIdOperacion(), dto.getIdModulo(), dto.getIdSubmodulo(), dto.getIdAccion(),
                dto.getFecha());
        return dto.createPk();
    }

    /**
     * Updates a single row in the FECEA_OPERACIONES table.
     */
    public void update(FeceaOperacionesPk pk, FeceaOperaciones dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_OPERACION = ?, ID_MODULO = ?, ID_SUBMODULO = ?, ID_ACCION = ?, FECHA = ? WHERE ID_OPERACION = ?");
        jdbcTemplate.update(query.toString(),
                dto.getIdOperacion(), dto.getIdModulo(), dto.getIdSubmodulo(), dto.getIdAccion(),
                dto.getFecha(), pk.getIdOperacion());

    }

    /**
     * Deletes a single row in the FECEA_OPERACIONES table.
     */
    public void delete(FeceaOperacionesPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_OPERACION = ?");
        jdbcTemplate.update(query.toString(), pk.getIdOperacion());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEA_OPERACIONES";
    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    public FeceaOperaciones findByPrimaryKey(BigDecimal idOperacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_OPERACION = ?");
        List<FeceaOperaciones> list
                = jdbcTemplate.query(query.toString(), new FeceaOperacionesMapper(),
                        idOperacion);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * ''.
     */
    public List<FeceaOperaciones> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_OPERACION");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper());

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    public List<FeceaOperaciones> findByFececAcciones(BigDecimal idAccion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ACCION = ?");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idAccion);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_MODULO = :idModulo'.
     */
    public List<FeceaOperaciones> findByFececModulos(BigDecimal idModulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_MODULO = ?");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idModulo);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    public List<FeceaOperaciones> findByFececSubmodulos(BigDecimal idSubmodulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_SUBMODULO = ?");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idSubmodulo);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    public List<FeceaOperaciones> findWhereIdOperacionEquals(BigDecimal idOperacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_OPERACION = ? ORDER BY ID_OPERACION");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idOperacion);
    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_MODULO = :idModulo'.
     */
    public List<FeceaOperaciones> findWhereIdModuloEquals(BigDecimal idModulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_MODULO = ? ORDER BY ID_MODULO");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idModulo);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    public List<FeceaOperaciones> findWhereIdSubmoduloEquals(BigDecimal idSubmodulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_SUBMODULO = ? ORDER BY ID_SUBMODULO");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idSubmodulo);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    public List<FeceaOperaciones> findWhereIdAccionEquals(BigDecimal idAccion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ACCION = ? ORDER BY ID_ACCION");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), idAccion);

    }

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'FECHA = :fecha'.
     */
    public List<FeceaOperaciones> findWhereFechaEquals(Date fecha) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA = ? ORDER BY FECHA");
        return jdbcTemplate.query(query.toString(),
                new FeceaOperacionesMapper(), fecha);

    }

    /**
     * Returns the rows from the FECEA_OPERACIONES table that matches the
     * specified primary-key value.
     */
    public FeceaOperaciones findByPrimaryKey(FeceaOperacionesPk pk) {
        return findByPrimaryKey(pk.getIdOperacion());
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
