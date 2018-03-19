package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececEmpleadoAciaceDao;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEmpleadoAciaceMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEmpleadoAciace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEmpleadoAciacePk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FececEmpleadoAciaceDaoImpl implements FececEmpleadoAciaceDao {

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_EMPLEADO_ACIACE, RFC, NOMBRE, CORREO FROM ").append(getTableName());

    private JdbcTemplate jdbcTemplate;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEmpleadoAciacePk
     */
    public FececEmpleadoAciacePk insert(FececEmpleadoAciace dto) {

        dto.setIdEmpleadoAciace(jdbcTemplate.queryForObject("SELECT FECEQ_EMPLEADO_ACIACE.NEXTVAL FROM DUAL", BigDecimal.class));

        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID_EMPLEADO_ACIACE, RFC, NOMBRE, CORREO ) VALUES ( ?, ?, ?, ? )", dto.getIdEmpleadoAciace(), dto.getRfc(), dto.getNombre(), dto.getCorreo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_EMPLEADO_ACIACE table.
     */
    public void update(FececEmpleadoAciacePk pk, FececEmpleadoAciace dto) {

        jdbcTemplate.update("UPDATE " + getTableName() + " SET ID_EMPLEADO_ACIACE = ?, RFC = ?, NOMBRE = ?, CORREO = ? WHERE ID_EMPLEADO_ACIACE = ?", dto.getIdEmpleadoAciace(), dto.getRfc(), dto.getNombre(), dto.getCorreo(), pk.getIdEmpleadoAciace());

    }

    /**
     * Deletes a single row in the FECEC_EMPLEADO_ACIACE table.
     */
    public void delete(FececEmpleadoAciacePk pk) {

        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE ID_EMPLEADO_ACIACE = ?", pk.getIdEmpleadoAciace());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_EMPLEADO_ACIACE";
    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'ID_EMPLEADO_ACIACE = :idEmpleadoAciace'.
     */
    public FececEmpleadoAciace findByPrimaryKey(BigDecimal idEmpleadoAciace) {

        List<FececEmpleadoAciace> list = jdbcTemplate.query(
                SQL_SELECT.append(" WHERE ID_EMPLEADO_ACIACE = ?").toString(),
                new FececEmpleadoAciaceMapper(), idEmpleadoAciace);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria ''.
     */
    public List<FececEmpleadoAciace> findAll() {

        return jdbcTemplate.query(SQL_SELECT.append(" ORDER BY ID_EMPLEADO_ACIACE").toString(),
                new FececEmpleadoAciaceMapper());

    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'ID_EMPLEADO_ACIACE = :idEmpleadoAciace'.
     */
    public List<FececEmpleadoAciace> findWhereIdEmpleadoAciaceEquals(BigDecimal idEmpleadoAciace) {

        return jdbcTemplate.query(SQL_SELECT.append(
                " WHERE ID_EMPLEADO_ACIACE = ? ORDER BY ID_EMPLEADO_ACIACE").toString(),
                new FececEmpleadoAciaceMapper(), idEmpleadoAciace);

    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'RFC = :rfc'.
     */
    public List<FececEmpleadoAciace> findWhereRfcEquals(String rfc) {

        return jdbcTemplate.query(SQL_SELECT.append(" WHERE RFC = ? ORDER BY RFC").toString(),
                new FececEmpleadoAciaceMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    public List<FececEmpleadoAciace> findWhereNombreEquals(String nombre) {

        return jdbcTemplate.query(SQL_SELECT.append(" WHERE NOMBRE = ? ORDER BY NOMBRE").toString(),
                new FececEmpleadoAciaceMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'CORREO = :correo'.
     */
    public List<FececEmpleadoAciace> findWhereCorreoEquals(final String correo) {

        return jdbcTemplate.query(SQL_SELECT + " WHERE CORREO = ? ORDER BY CORREO",
                new FececEmpleadoAciaceMapper(), correo);

    }

    /**
     * Returns the rows from the FECEC_EMPLEADO_ACIACE table that matches the
     * specified primary-key value.
     */
    public FececEmpleadoAciace findByPrimaryKey(FececEmpleadoAciacePk pk) {
        return findByPrimaryKey(pk.getIdEmpleadoAciace());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
