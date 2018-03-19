/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececApodLegalMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececApodLegalDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegalPk;

import org.springframework.stereotype.Repository;

@Repository("fececApodLegalDao")
public class FececApodLegalDaoImpl extends BaseJDBCDao<FececApodLegal> implements FececApodLegalDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de todas las
     * columnas de la tabla FECEC_APOD_LEGAL
     */
    private static final String SQL_SELECT
            = " SELECT ID_APOD_LEGAL, RFC, NOMBRE, RFC_CONTRIBUYENTE FROM " + getTableName();

    /**
     * Este atributo es una condición WHERE para el query
     */
    private static final String SQL_WHERE = " WHERE ID_APOD_LEGAL = ? ";

    /**
     * Este atributo es una condición ORDER_BY para ordenar los datos de acuerdo
     * al ID_APOD_LEGAL
     */
    private static final String SQL_ORDER_BY = " ORDER BY ID_APOD_LEGAL ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececApodLegalPk
     */
    public FececApodLegalPk insert(FececApodLegal dto) {

        StringBuilder query = new StringBuilder();

        dto.setIdApodLegal(getJdbcTemplateBase().queryForObject("SELECT FECEQ_APOD_LEGAL.NEXTVAL FROM DUAL",
                BigDecimal.class));

        query.append("INSERT INTO ").append(getTableName());
        query.append(" ( ID_APOD_LEGAL, RFC, NOMBRE, RFC_CONTRIBUYENTE ) VALUES ( ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdApodLegal(), dto.getRfc(), dto.getNombre(),
                dto.getRfcContribuyente());

        return dto.createPk();
    }

    /**
     * Updates a single row in the FECEA_APODLEGAL table.
     */
    public void update(FececApodLegalPk pk, FececApodLegal dto) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(" SET ID_APOD_LEGAL = ?, RFC = ?, NOMBRE = ?, RFC_CONTRIBUYENTE = ?");
        query.append("WHERE ID_APOD_LEGAL = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdApodLegal(), dto.getRfc().toUpperCase(),
                dto.getNombre().toUpperCase(), dto.getRfcContribuyente().toUpperCase(),
                pk.getIdApodLegal());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_APOD_LEGAL";
    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'ID_APOD_LEGAL = :idApodLegal'.
     */
    public FececApodLegal findByPrimaryKey(BigDecimal idApodLegal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        List<FececApodLegal> list
                = getJdbcTemplateBase().query(query.toString(), new FececApodLegalMapper(), idApodLegal);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * ''.
     */
    public List<FececApodLegal> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_ORDER_BY);
        return getJdbcTemplateBase().query(query.toString(), new FececApodLegalMapper());

    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'ID_APOD_LEGAL = :idApodLegal'.
     */
    public List<FececApodLegal> findWhereIdApodLegalEquals(BigDecimal idApodLegal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        query.append(SQL_ORDER_BY);
        return getJdbcTemplateBase().query(query.toString(), new FececApodLegalMapper(), idApodLegal);

    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'RFC = :rfc'.
     */
    public List<FececApodLegal> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(), new FececApodLegalMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececApodLegal> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(), new FececApodLegalMapper(),
                nombre);

    }

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'RFC_CONTRIBUYENTE = :rfcContribuyente'.
     */
    public List<FececApodLegal> findWhereRfcContribuyenteEquals(String rfcContribuyente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_CONTRIBUYENTE = ? ORDER BY RFC_CONTRIBUYENTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececApodLegalMapper(), rfcContribuyente);

    }

    /**
     * Returns the rows from the FECEA_APODLEGAL table that matches the
     * specified primary-key value.
     */
    public FececApodLegal findByPrimaryKey(FececApodLegalPk pk) {
        return findByPrimaryKey(pk.getIdApodLegal());
    }

}
