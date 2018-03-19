/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAlegatoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoPk;

import org.springframework.stereotype.Repository;

@Repository("fecetAlegatoDao")
public class FecetAlegatoDaoImpl extends BaseJDBCDao<FecetAlegato> implements FecetAlegatoDao {

    @SuppressWarnings("compatibility:-7987414018698235432")
    private static final long serialVersionUID = 1L;
    /**
     * Este atributo es un SELECT para seleccionar los datos de las columnas de
     * la tabla FECET_ALEGATO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ALEGATO, ID_PROMOCION, FECHA_CARGA, RUTA_ARCHIVO FROM ").append(getTableName());

    /**
     * Este atributo es una condicion WHERE para seleccionar los datos de
     * acuerdo a un ID_ALEGATO
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_ALEGATO = ? ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAlegatoPk
     */
    public FecetAlegatoPk insert(FecetAlegato dto) {
        if (dto.getIdAlegato() == null) {
            dto.setIdAlegato(getJdbcTemplateBase().queryForObject("SELECT FECEQ_ALEGATO.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ALEGATO, ID_PROMOCION, FECHA_CARGA, RUTA_ARCHIVO ) VALUES ( ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAlegato(), dto.getIdPromocion(), dto.getFechaCarga(),
                dto.getRutaArchivo());
        return dto.createPk();

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ALEGATO";
    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_ALEGATO = :idAlegato'.
     */
    public FecetAlegato findByPrimaryKey(BigDecimal idAlegato) {
        List<FecetAlegato> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(), new FecetAlegatoMapper(), idAlegato);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria ''.
     */
    public List<FecetAlegato> findAll() {
        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_ALEGATO").toString(),
                new FecetAlegatoMapper());

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_ALEGATO = :idAlegato'.
     */
    public List<FecetAlegato> findWhereIdAlegatoEquals(BigDecimal idAlegato) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_ALEGATO = ? ORDER BY ID_ALEGATO").toString(),
                new FecetAlegatoMapper(), idAlegato);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_PROMOCION = :idPromocion'.
     */
    public List<FecetAlegato> findWhereIdPromocionEquals(BigDecimal idPromocion) {
        StringBuilder sql = new StringBuilder();

        sql.append(SQL_SELECT);
        sql.append(" WHERE ID_PROMOCION = ? ORDER BY ID_PROMOCION");
        logger.debug("get Pruebas y alegatos {} " + sql);
        return getJdbcTemplateBase().query(sql.toString(), new FecetAlegatoMapper(), idPromocion);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'FECHA_CARGA = :fechaCarga'.
     */
    public List<FecetAlegato> findWhereFechaCargaEquals(Date fechaCarga) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA").toString(),
                new FecetAlegatoMapper(), fechaCarga);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetAlegato> findWhereRutaArchivoEquals(String rutaArchivo) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO").toString(),
                new FecetAlegatoMapper(), rutaArchivo);

    }

    /**
     * Returns the rows from the FECET_ALEGATO table that matches the specified
     * primary-key value.
     */
    public FecetAlegato findByPrimaryKey(FecetAlegatoPk pk) {

        return findByPrimaryKey(pk.getIdAlegato());

    }

}
