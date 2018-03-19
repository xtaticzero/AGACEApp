package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececCentralDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececCentralMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentralPk;

import org.springframework.jdbc.core.JdbcTemplate;

public class FececCentralDaoImpl implements FececCentralDao {

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_CENTRAL, ID_ARACE, RFC, NOMBRE, CORREO FROM ").append(getTableName());

    private JdbcTemplate jdbcTemplate;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececCentralPk
     */
    public FececCentralPk insert(FececCentral dto) {

        dto.setIdCentral(jdbcTemplate.queryForObject("SELECT FECEQ_CENTRAL.NEXTVAL FROM DUAL", BigDecimal.class));

        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID_CENTRAL, ID_ARACE, RFC, NOMBRE, CORREO ) VALUES ( ?, ?, ?, ?, ? )", dto.getIdCentral(), dto.getIdArace(), dto.getRfc(), dto.getNombre(), dto.getCorreo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_CENTRAL table.
     */
    public void update(FececCentralPk pk, FececCentral dto) {

        jdbcTemplate.update("UPDATE " + getTableName() + " SET ID_CENTRAL = ?, ID_ARACE = ?, RFC = ?, NOMBRE = ?, CORREO = ? WHERE ID_CENTRAL = ?", dto.getIdCentral(), dto.getIdArace(), dto.getRfc(), dto.getNombre(), dto.getCorreo(), pk.getIdCentral());

    }

    /**
     * Deletes a single row in the FECEC_CENTRAL table.
     */
    public void delete(FececCentralPk pk) {

        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE ID_CENTRAL = ?", pk.getIdCentral());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_CENTRAL";
    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_CENTRAL = :idCentral'.
     */
    public FececCentral findByPrimaryKey(BigDecimal idCentral) {

        List<FececCentral> list = jdbcTemplate.query(SQL_SELECT.append(" WHERE ID_CENTRAL = ?").toString(),
                new FececCentralMapper(), idCentral);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria ''.
     */
    public List<FececCentral> findAll() {

        return jdbcTemplate.query(SQL_SELECT.append(" ORDER BY ID_CENTRAL").toString(), new FececCentralMapper());

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_CENTRAL = :idCentral'.
     */
    public List<FececCentral> findWhereIdCentralEquals(BigDecimal idCentral) {

        return jdbcTemplate.query(SQL_SELECT.append(" WHERE ID_CENTRAL = ? ORDER BY ID_CENTRAL").toString(),
                new FececCentralMapper(), idCentral);

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    public List<FececCentral> findWhereIdAraceEquals(BigDecimal idArace) {

        return jdbcTemplate.query(SQL_SELECT.append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE").toString(),
                new FececCentralMapper(), idArace);

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'RFC = :rfc'.
     */
    public List<FececCentral> findWhereRfcEquals(String rfc) {

        return jdbcTemplate.query(SQL_SELECT + " WHERE RFC = ? ORDER BY RFC", new FececCentralMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececCentral> findWhereNombreEquals(String nombre) {

        return jdbcTemplate.query(SQL_SELECT.append(" WHERE NOMBRE = ? ORDER BY NOMBRE").toString(),
                new FececCentralMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'CORREO = :correo'.
     */
    public List<FececCentral> findWhereCorreoEquals(final String correo) {

        return jdbcTemplate.query(SQL_SELECT + " WHERE CORREO = ? ORDER BY CORREO",
                new FececCentralMapper(), correo);

    }

    /**
     * Returns the rows from the FECEC_CENTRAL table that matches the specified
     * primary-key value.
     */
    public FececCentral findByPrimaryKey(FececCentralPk pk) {
        return findByPrimaryKey(pk.getIdCentral());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
