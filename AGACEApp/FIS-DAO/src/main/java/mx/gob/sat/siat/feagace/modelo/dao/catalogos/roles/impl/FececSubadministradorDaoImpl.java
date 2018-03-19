package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececSubadministradorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubadministradorMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministradorPk;

import org.springframework.stereotype.Repository;

@Repository("fececSubadministradorDao")
public class FececSubadministradorDaoImpl extends BaseJDBCDao<FececSubadministrador> implements FececSubadministradorDao {

    private static final String SQL_SELECT = "SELECT ID_SUBADMINISTRADOR, ID_ADMINISTRADOR, RFC, NOMBRE, CORREO FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubadministradorPk
     */
    public FececSubadministradorPk insert(FececSubadministrador dto) {

        dto.setIdSubadministrador(getJdbcTemplateBase().queryForObject("SELECT FECEQ_SUBADMINISTRADOR.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_SUBADMINISTRADOR, ID_ADMINISTRADOR, RFC, NOMBRE, CORREO ) VALUES ( ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSubadministrador(), dto.getIdAdministrador(), dto.getRfc(), dto.getNombre(), dto.getCorreo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_SUBADMINISTRADOR table.
     */
    public void update(FececSubadministradorPk pk, FececSubadministrador dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_SUBADMINISTRADOR = ?, ID_ADMINISTRADOR = ?, RFC = ?, NOMBRE = ?, CORREO = ? WHERE ID_SUBADMINISTRADOR = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSubadministrador(), dto.getIdAdministrador(), dto.getRfc(), dto.getNombre(), dto.getCorreo(), pk.getIdSubadministrador());

    }

    /**
     * Deletes a single row in the FECEC_SUBADMINISTRADOR table.
     */
    public void delete(FececSubadministradorPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_SUBADMINISTRADOR = ?");
        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_SUBADMINISTRADOR = ?", pk.getIdSubadministrador());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_SUBADMINISTRADOR";
    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_SUBADMINISTRADOR = :idSubadministrador'.
     */
    public FececSubadministrador findByPrimaryKey(BigDecimal idSubadministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SUBADMINISTRADOR = ?");

        List<FececSubadministrador> list = getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), idSubadministrador);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria ''.
     */
    public List<FececSubadministrador> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_SUBADMINISTRADOR");
        return getJdbcTemplateBase().query(query.toString(), new FececSubadministradorMapper());

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    public List<FececSubadministrador> findByFececAdministrador(BigDecimal idAdministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ADMINISTRADOR = ?");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), idAdministrador);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_SUBADMINISTRADOR = :idSubadministrador'.
     */
    public List<FececSubadministrador> findWhereIdSubadministradorEquals(BigDecimal idSubadministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SUBADMINISTRADOR = ? ORDER BY ID_SUBADMINISTRADOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), idSubadministrador);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    public List<FececSubadministrador> findWhereIdAdministradorEquals(BigDecimal idAdministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ADMINISTRADOR = ? ORDER BY ID_ADMINISTRADOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), idAdministrador);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'RFC = :rfc'.
     */
    public List<FececSubadministrador> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    public List<FececSubadministrador> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'CORREO = :correo'.
     */
    public List<FececSubadministrador> findWhereCorreoEquals(final String correo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE CORREO = ? ORDER BY CORREO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubadministradorMapper(), correo);

    }

    /**
     * Returns the rows from the FECEC_SUBADMINISTRADOR table that matches the
     * specified primary-key value.
     */
    public FececSubadministrador findByPrimaryKey(FececSubadministradorPk pk) {
        return findByPrimaryKey(pk.getIdSubadministrador());
    }

}
