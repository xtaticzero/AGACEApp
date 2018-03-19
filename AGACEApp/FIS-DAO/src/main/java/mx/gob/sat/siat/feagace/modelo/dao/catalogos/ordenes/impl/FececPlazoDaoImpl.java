/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececPlazoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececPlazoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececPlazo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececPlazoPk;

import org.springframework.stereotype.Repository;

@Repository("fececPlazoDao")
public class FececPlazoDaoImpl extends BaseJDBCDao<FececPlazo> implements FececPlazoDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_CARTA_INV
     */
    private static final String SQL_SELECT = "SELECT ID_PLAZO, DESCRIPCION, DIAS_PLAZO, DIAS_HABILES, FECHA_CREACION FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececPlazoPk
     */
    public FececPlazoPk insert(FececPlazo dto) {

        StringBuilder query = new StringBuilder();

        dto.setIdPlazo(getJdbcTemplateBase().queryForObject(
                "SELECT FECEC_PLAZO.NEXTVAL FROM DUAL", BigDecimal.class));

        query.append("INSERT INTO ").append(getTableName());
        query.append(" ( ID_PLAZO, DESCRIPCION, DIAS_PLAZO, DIAS_HABILES, FECHA_CREACION )");
        query.append("VALUES ( ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdPlazo(),
                dto.getDescripcion(), dto.getDiasPlazo(),
                dto.getDiasHabiles(), dto.getFechaCreacion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_PLAZO table.
     */
    public void update(FececPlazoPk pk, FececPlazo dto) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(" SET ID_PLAZO = ?, DESCRIPCION = ?, DIAS_PLAZO = ?, DIAS_HABILES = ?, FECHA_CREACION = ? ");
        query.append("WHERE ID_PLAZO = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdPlazo(),
                dto.getDescripcion(), dto.getDiasPlazo(),
                dto.getDiasHabiles(), dto.getFechaCreacion(),
                pk.getIdPlazo());

    }

    /**
     * Deletes a single row in the FECEC_PLAZO table.
     */
    public void delete(FececPlazoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_PLAZO = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdPlazo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_PLAZO";
    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'ID_PLAZO = :idPlazo'.
     */
    public FececPlazo findByPrimaryKey(BigDecimal idPlazo) {

        List<FececPlazo> list = getJdbcTemplateBase().query(SQL_SELECT
                + getTableName() + " WHERE ID_PLAZO = ?",
                new FececPlazoMapper(), idPlazo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria ''.
     */
    public List<FececPlazo> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " ORDER BY ID_PLAZO", new FececPlazoMapper());

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'ID_PLAZO = :idPlazo'.
     */
    public List<FececPlazo> findWhereIdPlazoEquals(BigDecimal idPlazo) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " WHERE ID_PLAZO = ? ORDER BY ID_PLAZO",
                new FececPlazoMapper(), idPlazo);

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececPlazo> findWhereDescripcionEquals(String descripcion) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " WHERE DESCRIPCION = ? ORDER BY DESCRIPCION",
                new FececPlazoMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DIAS_PLAZO = :diasPlazo'.
     */
    public List<FececPlazo> findWhereDiasPlazoEquals(Integer diasPlazo) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " WHERE DIAS_PLAZO = ? ORDER BY DIAS_PLAZO",
                new FececPlazoMapper(), diasPlazo);

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DIAS_HABILES = :diasHabiles'.
     */
    public List<FececPlazo> findWhereDiasHabilesEquals(String diasHabiles) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " WHERE DIAS_HABILES = ? ORDER BY DIAS_HABILES",
                new FececPlazoMapper(), diasHabiles);

    }

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FececPlazo> findWhereFechaCreacionEquals(Date fechaCreacion) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName()
                + " WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION",
                new FececPlazoMapper(), fechaCreacion);

    }

    /**
     * Returns the rows from the FECEC_PLAZO table that matches the specified
     * primary-key value.
     */
    public FececPlazo findByPrimaryKey(FececPlazoPk pk) {
        return findByPrimaryKey(pk.getIdPlazo());
    }

}
