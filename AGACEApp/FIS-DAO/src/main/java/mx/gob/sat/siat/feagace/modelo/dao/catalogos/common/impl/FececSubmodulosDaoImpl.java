/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubmodulosDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubmodulosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSubmodulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSubmodulosPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FececSubmodulosDaoImpl implements FececSubmodulosDao {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT = "SELECT ID_SUBMODULO, NOMBRE, DESCRIPCION, FECHA FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubmodulosPk
     */
    public FececSubmodulosPk insert(FececSubmodulos dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_SUBMODULO, NOMBRE, DESCRIPCION, FECHA ) VALUES ( ?, ?, ?, ? )");
        jdbcTemplate.update(query.toString(), dto.getIdSubmodulo(),
                dto.getNombre(), dto.getDescripcion(), dto.getFecha());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_SUBMODULOS table.
     */
    public void update(FececSubmodulosPk pk, FececSubmodulos dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_SUBMODULO = ?, NOMBRE = ?, DESCRIPCION = ?, FECHA = ? WHERE ID_SUBMODULO = ?");
        jdbcTemplate.update(query.toString(), dto.getIdSubmodulo(),
                dto.getNombre(), dto.getDescripcion(), dto.getFecha(),
                pk.getIdSubmodulo());

    }

    /**
     * Deletes a single row in the FECEC_SUBMODULOS table.
     */
    public void delete(FececSubmodulosPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_SUBMODULO = ?");
        jdbcTemplate.update(query.toString(), pk.getIdSubmodulo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_SUBMODULOS";
    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    public FececSubmodulos findByPrimaryKey(BigDecimal idSubmodulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_SUBMODULO = ?");
        List<FececSubmodulos> list = jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper(), idSubmodulo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * ''.
     */
    public List<FececSubmodulos> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_SUBMODULO");
        return jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper());

    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    public List<FececSubmodulos> findWhereIdSubmoduloEquals(
            BigDecimal idSubmodulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_SUBMODULO = ? ORDER BY ID_SUBMODULO");
        return jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper(), idSubmodulo);

    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececSubmodulos> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececSubmodulos> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'FECHA = :fecha'.
     */
    public List<FececSubmodulos> findWhereFechaEquals(Date fecha) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA = ? ORDER BY FECHA");
        return jdbcTemplate.query(query.toString(),
                new FececSubmodulosMapper(), fecha);

    }

    /**
     * Returns the rows from the FECEC_SUBMODULOS table that matches the
     * specified primary-key value.
     */
    public FececSubmodulos findByPrimaryKey(FececSubmodulosPk pk) {
        return findByPrimaryKey(pk.getIdSubmodulo());
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
