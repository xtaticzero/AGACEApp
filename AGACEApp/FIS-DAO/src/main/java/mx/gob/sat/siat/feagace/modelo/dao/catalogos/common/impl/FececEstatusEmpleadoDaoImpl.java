/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusEmpleadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FececEstatusEmpleadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleadoPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FececEstatusEmpleadoDaoImpl implements FececEstatusEmpleadoDao {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT = "SELECT ID_ESTATUS_EMPLEADO, DESCRIPCION FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEstatusEmpleadoPk
     */
    public FececEstatusEmpleadoPk insert(FececEstatusEmpleado dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ESTATUS_EMPLEADO, DESCRIPCION ) VALUES ( ?, ? )");
        jdbcTemplate.update(query.toString(), dto.getIdEstatusEmpleado(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_ESTATUS_EMPLEADO table.
     */
    public void update(FececEstatusEmpleadoPk pk, FececEstatusEmpleado dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ESTATUS_EMPLEADO = ?, DESCRIPCION = ? WHERE ID_ESTATUS_EMPLEADO = ?");
        jdbcTemplate.update(query.toString(), dto.getIdEstatusEmpleado(), dto.getDescripcion(),
                pk.getIdEstatusEmpleado());

    }

    /**
     * Deletes a single row in the FECEC_ESTATUS_EMPLEADO table.
     */
    public void delete(FececEstatusEmpleadoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_ESTATUS_EMPLEADO = ?");
        jdbcTemplate.update(query.toString(), pk.getIdEstatusEmpleado());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ESTATUS_EMPLEADO";
    }

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'ID_ESTATUS_EMPLEADO = :idEstatusEmpleado'.
     */
    public FececEstatusEmpleado findByPrimaryKey(BigDecimal idEstatusEmpleado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ESTATUS_EMPLEADO = ?");
        List<FececEstatusEmpleado> list
                = jdbcTemplate.query(query.toString(), new FececEstatusEmpleadoMapper(), idEstatusEmpleado);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria ''.
     */
    public List<FececEstatusEmpleado> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_ESTATUS_EMPLEADO");
        return jdbcTemplate.query(query.toString(), new FececEstatusEmpleadoMapper());

    }

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'ID_ESTATUS_EMPLEADO = :idEstatusEmpleado'.
     */
    public List<FececEstatusEmpleado> findWhereIdEstatusEmpleadoEquals(BigDecimal idEstatusEmpleado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ESTATUS_EMPLEADO = ? ORDER BY ID_ESTATUS_EMPLEADO");
        return jdbcTemplate.query(query.toString(), new FececEstatusEmpleadoMapper(), idEstatusEmpleado);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececEstatusEmpleado> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return jdbcTemplate.query(query.toString(), new FececEstatusEmpleadoMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_ESTATUS_EMPLEADO table that matches the
     * specified primary-key value.
     */
    public FececEstatusEmpleado findByPrimaryKey(FececEstatusEmpleadoPk pk) {
        return findByPrimaryKey(pk.getIdEstatusEmpleado());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
