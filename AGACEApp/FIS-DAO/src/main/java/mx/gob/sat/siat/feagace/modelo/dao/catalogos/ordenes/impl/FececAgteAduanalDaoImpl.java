package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececAgteAduanalMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececAgteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanalPk;

import org.springframework.stereotype.Repository;

@Repository("fececAgteAduanalDao")
public class FececAgteAduanalDaoImpl extends BaseJDBCDao<FececAgteAduanal> implements FececAgteAduanalDao {

    /**
     * Este atributo es una condicion SELECT para seleccionar los datos de la
     * tabla FECEC_REP_LEGAL
     */
    private static final String SQL_SELECT = "SELECT ID_AGTE_ADUANAL, NOMBRE, RFC FROM " + getTableName();

    /**
     * Este atributo es una condicion WHERE para el query
     */
    private static final String SQL_WHERE = " WHERE ID_AGTE_ADUANAL = ? ";

    /**
     * Este atributo es una condicion ORDER BY para ordenar los datos
     */
    private static final String SQL_ORDER_BY = " ORDER BY ID_AGTE_ADUANAL ";

    /**
     * Este atributo es una condicion UPDATE para el query
     */
    private static final String UPDATE = " UPDATE ";

    /**
     * Metodo 'insert'
     *
     * @param dto
     * @return FececAgteAduanalPk
     * @
     */
    public FececAgteAduanalPk insert(final FececAgteAduanal dto) {

        dto.setIdAgteAduanal(getJdbcTemplateBase().queryForObject("SELECT FECEQ_AGTE_ADUANAL.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO ");
        query.append(getTableName());
        query.append(" (ID_AGTE_ADUANAL, NOMBRE, RFC) VALUES (?, ?, ?) ");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdAgteAduanal(), dto.getNombre(), dto.getRfc());

        return dto.createPk();
    }

    /**
     * Metodo 'update'
     *
     * @param pk
     * @param dto
     * @
     */
    public void update(final FececAgteAduanalPk pk, final FececAgteAduanal dto) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(getTableName());
        query.append(" SET ID_REP_LEGAL = ? , NOMBRE = ? , RFC = ? ");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdAgteAduanal(), dto.getNombre(), dto.getRfc(), pk.getIdAgteAduanal());

    }

    /**
     * Metodo 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_AGTE_ADUANAL";
    }

    /**
     * Metodo 'findByPrimaryKey'
     *
     * @param idAgteAduanal
     * @return
     * @
     */
    public FececAgteAduanal findByPrimaryKey(final BigDecimal idAgteAduanal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);

        List<FececAgteAduanal> lista
                = getJdbcTemplateBase().query(query.toString(), new FececAgteAduanalMapper(), idAgteAduanal);
        return lista.isEmpty() ? null : lista.get(0);

    }

    /**
     * Metodo 'findAll'
     *
     * @return List
     * @
     */
    public List<FececAgteAduanal> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_ORDER_BY);
        return getJdbcTemplateBase().query(query.toString(), new FececAgteAduanalMapper());

    }

    /**
     * Metodo 'findWhereIdAgteAduanal'
     *
     * @param idAgteAduanal
     * @return List
     * @
     */
    public List<FececAgteAduanal> findWhereIdAgteAduanalEquals(final BigDecimal idAgteAduanal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        return getJdbcTemplateBase().query(query.toString(), new FececAgteAduanalMapper(), idAgteAduanal);

    }

    /**
     * Metodo 'findWhereRfcEquals'
     *
     * @param rfc
     * @return List
     * @
     */
    public List<FececAgteAduanal> findWhereRfcEquals(final String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FececAgteAduanalMapper(), rfc);

    }

    /**
     * Metodo 'findWhereNombreEquals'
     *
     * @param nombre
     * @return List
     * @
     */
    public List<FececAgteAduanal> findWhereNombreEquals(final String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FececAgteAduanalMapper(), nombre);

    }

    /**
     * Metodo findByPrimaryKey
     *
     * @param pk
     * @return FececRepLegal
     * @
     */
    public FececAgteAduanal findByPrimaryKey(final FececAgteAduanalPk pk) {
        return findByPrimaryKey(pk.getIdAgteAduanal());
    }
}
