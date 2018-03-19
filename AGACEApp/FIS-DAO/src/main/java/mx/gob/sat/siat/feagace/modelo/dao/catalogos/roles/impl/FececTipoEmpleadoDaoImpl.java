/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececTipoEmpleadoDao;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FececTipoEmpleadoMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleadoPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FececTipoEmpleadoDaoImpl implements FececTipoEmpleadoDao {

    private static final String SQL_SELECT = "SELECT ID_TIPO_EMPLEADO, DESCRIPCION FROM " + getTableName();

    private JdbcTemplate jdbcTemplate;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoEmpleadoPk
     */
    public FececTipoEmpleadoPk insert(FececTipoEmpleado dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_TIPO_EMPLEADO, DESCRIPCION ) VALUES ( ?, ? )");
        jdbcTemplate.update(query.toString(),
                dto.getIdTipoEmpleado(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_TIPO_EMPLEADO table.
     */
    public void update(FececTipoEmpleadoPk pk, FececTipoEmpleado dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_TIPO_EMPLEADO = ?, DESCRIPCION = ? WHERE ID_TIPO_EMPLEADO = ?");
        jdbcTemplate.update(query.toString(),
                dto.getIdTipoEmpleado(), dto.getDescripcion(), pk.getIdTipoEmpleado());

    }

    /**
     * Deletes a single row in the FECEC_TIPO_EMPLEADO table.
     */
    public void delete(FececTipoEmpleadoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_EMPLEADO = ?");
        jdbcTemplate.update(query.toString(), pk.getIdTipoEmpleado());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_TIPO_EMPLEADO";
    }

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    public FececTipoEmpleado findByPrimaryKey(BigDecimal idTipoEmpleado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_TIPO_EMPLEADO = ?");
        List<FececTipoEmpleado> list
                = jdbcTemplate.query(query.toString(), new FececTipoEmpleadoMapper(),
                        idTipoEmpleado);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria ''.
     */
    public List<FececTipoEmpleado> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_TIPO_EMPLEADO");
        return jdbcTemplate.query(query.toString(), new FececTipoEmpleadoMapper());

    }

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    public List<FececTipoEmpleado> findWhereIdTipoEmpleadoEquals(BigDecimal idTipoEmpleado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_TIPO_EMPLEADO = ? ORDER BY ID_TIPO_EMPLEADO");
        return jdbcTemplate.query(query.toString(),
                new FececTipoEmpleadoMapper(), idTipoEmpleado);

    }

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececTipoEmpleado> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return jdbcTemplate.query(query.toString(),
                new FececTipoEmpleadoMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_TIPO_EMPLEADO table that matches the
     * specified primary-key value.
     */
    public FececTipoEmpleado findByPrimaryKey(FececTipoEmpleadoPk pk) {
        return findByPrimaryKey(pk.getIdTipoEmpleado());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
