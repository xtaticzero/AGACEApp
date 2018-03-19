package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececRepLegalMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececRepLegalDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegalPk;

import org.springframework.stereotype.Repository;

@Repository("fececRepLegalDao")
public class FececRepLegalDaoImpl extends BaseJDBCDao<FececRepLegal> implements FececRepLegalDao {

    /**
     * Este atributo es una condicion SELECT para seleccionar los datos de la
     * tabla FECEC_REP_LEGAL
     */
    private static final String SQL_SELECT = "SELECT ID_REP_LEGAL, NOMBRE, RFC FROM " + getTableName();

    /**
     * Este atributo es una condicion WHERE para el query
     */
    private static final String SQL_WHERE = " WHERE ID_REP_LEGAL = ? ";

    /**
     * Este atributo es una condicion ORDER BY para ordenar los datos
     */
    private static final String SQL_ORDER_BY = " ORDER BY ID_REP_LEGAL ";

    /**
     * Este atributo es una condicion UPDATE para el query
     */
    private static final String UPDATE = " UPDATE ";

    /**
     * Metodo 'insert'
     *
     * @param dto
     * @return FececRepLegalPk
     * @
     */
    public FececRepLegalPk insert(final FececRepLegal dto) {

        dto.setIdRepLegal(getJdbcTemplateBase().queryForObject("SELECT FECEQ_REP_LEGAL.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO ");
        query.append(getTableName());
        query.append(" (ID_REP_LEGAL, NOMBRE, RFC) VALUES (?, ?, ?) ");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRepLegal(), dto.getNombre(), dto.getRfc());

        return dto.createPk();
    }

    /**
     * Metodo 'update'
     *
     * @param pk
     * @param dto
     * @
     */
    public void update(final FececRepLegalPk pk, final FececRepLegal dto) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(getTableName());
        query.append(" SET ID_REP_LEGAL = ? , NOMBRE = ? , RFC = ? ");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRepLegal(), dto.getNombre(), dto.getRfc(), pk.getIdRepLegal());

    }

    /**
     * Metodo 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_REP_LEGAL";
    }

    /**
     * Metodo 'findByPrimaryKey'
     *
     * @param idRepLegal
     * @return
     * @
     */
    public FececRepLegal findByPrimaryKey(final BigDecimal idRepLegal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);

        List<FececRepLegal> lista = getJdbcTemplateBase().query(query.toString(), new FececRepLegalMapper(), idRepLegal);
        return lista.isEmpty() ? null : lista.get(0);

    }

    /**
     * Metodo 'findAll'
     *
     * @return List
     * @
     */
    public List<FececRepLegal> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_ORDER_BY);
        return getJdbcTemplateBase().query(query.toString(), new FececRepLegalMapper());

    }

    /**
     * Metodo 'findWhereIdRepLegalEquals'
     *
     * @param idRepLegal
     * @return List
     * @
     */
    public List<FececRepLegal> findWhereIdRepLegalEquals(final BigDecimal idRepLegal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        return getJdbcTemplateBase().query(query.toString(), new FececRepLegalMapper(), idRepLegal);

    }

    /**
     * Metodo 'findWhereRfcEquals'
     *
     * @param rfc
     * @return List
     * @
     */
    public List<FececRepLegal> findWhereRfcEquals(final String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FececRepLegalMapper(), rfc);

    }

    /**
     * Metodo 'findWhereNombreEquals'
     *
     * @param nombre
     * @return List
     * @
     */
    public List<FececRepLegal> findWhereNombreEquals(final String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FececRepLegalMapper(), nombre);

    }

    /**
     * Metodo findByPrimaryKey
     *
     * @param pk
     * @return FececRepLegal
     * @
     */
    public FececRepLegal findByPrimaryKey(final FececRepLegalPk pk) {
        return findByPrimaryKey(pk.getIdRepLegal());
    }
}
