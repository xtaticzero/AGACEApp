package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececAdministradorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececAdministradorDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministradorPk;

@Repository("fececAdministradorDao")
public class FececAdministradorDaoImpl extends BaseJDBCDao<FececAdministrador>
        implements FececAdministradorDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = 3120432782572481073L;

    private static final String SQL_SELECT = "SELECT ID_ADMINISTRADOR, ID_ARACE, ID_CENTRAL, RFC, NOMBRE, CORREO FROM "
            + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececAdministradorPk
     */
    public FececAdministradorPk insert(FececAdministrador dto) {

        dto.setIdAdministrador(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_ADMINISTRADOR.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ADMINISTRADOR, ID_ARACE, ID_CENTRAL, RFC, NOMBRE, CORREO ) VALUES ( ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(),
                dto.getIdAdministrador(), dto.getIdArace(), dto.getIdCentral(),
                dto.getRfc(), dto.getNombre(), dto.getCorreo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_ADMINISTRADOR table.
     */
    public void update(FececAdministradorPk pk, FececAdministrador dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ADMINISTRADOR = ?, ID_ARACE = ?, ID_CENTRAL = ?, RFC = ?, NOMBRE = ?, CORREO = ? WHERE ID_ADMINISTRADOR = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdAdministrador(), dto.getIdArace(), dto.getIdCentral(),
                dto.getRfc(), dto.getNombre(), dto.getCorreo(),
                pk.getIdAdministrador());

    }

    /**
     * Deletes a single row in the FECEC_ADMINISTRADOR table.
     */
    public void delete(FececAdministradorPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ADMINISTRADOR = ?, ID_ARACE = ?, ID_CENTRAL = ?, RFC = ?, NOMBRE = ?, CORREO = ? WHERE ID_ADMINISTRADOR = ?");
        getJdbcTemplateBase()
                .update("DELETE FROM " + getTableName()
                        + " WHERE ID_ADMINISTRADOR = ?",
                        pk.getIdAdministrador());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ADMINISTRADOR";
    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    public FececAdministrador findByPrimaryKey(BigDecimal idAdministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ADMINISTRADOR = ?");
        List<FececAdministrador> list = getJdbcTemplateBase().query(
                query.toString(), new FececAdministradorMapper(),
                idAdministrador);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria ''.
     */
    public List<FececAdministrador> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_ADMINISTRADOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper());

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    public List<FececAdministrador> findByFececCentral(BigDecimal idCentral) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CENTRAL = ?");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), idCentral);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    public List<FececAdministrador> findWhereIdAdministradorEquals(
            BigDecimal idAdministrador) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ADMINISTRADOR = ? ORDER BY ID_ADMINISTRADOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), idAdministrador);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ARACE = :idArace'.
     */
    public List<FececAdministrador> findWhereIdAraceEquals(BigDecimal idArace) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), idArace);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    public List<FececAdministrador> findWhereIdCentralEquals(
            BigDecimal idCentral) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CENTRAL = ? ORDER BY ID_CENTRAL");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), idCentral);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'RFC = :rfc'.
     */
    public List<FececAdministrador> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    public List<FececAdministrador> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'CORREO = :correo'.
     */
    public List<FececAdministrador> findWhereCorreoEquals(final String correo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE CORREO = ? ORDER BY CORREO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececAdministradorMapper(), correo);

    }

    /**
     * Returns the rows from the FECEC_ADMINISTRADOR table that matches the
     * specified primary-key value.
     */
    public FececAdministrador findByPrimaryKey(FececAdministradorPk pk) {
        return findByPrimaryKey(pk.getIdAdministrador());
    }

}
