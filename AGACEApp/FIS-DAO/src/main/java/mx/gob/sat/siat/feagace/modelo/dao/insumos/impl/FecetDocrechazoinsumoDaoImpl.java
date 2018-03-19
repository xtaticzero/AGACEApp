/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocrechazoinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetDocrechazoinsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumoPk;

@Repository("fecetDocrechazoinsumoDao")
public class FecetDocrechazoinsumoDaoImpl extends
        BaseJDBCDao<FecetDocrechazoinsumo> implements FecetDocrechazoinsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -3475242181418356628L;

    private static final String SQL_SELECT = "SELECT ID_DOCRECHAZOINSUMO, ID_RECHAZO_INSUMO, RUTA_ARCHIVO, FECHA_CREACION FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocrechazoinsumoPk
     */
    public FecetDocrechazoinsumoPk insert(FecetDocrechazoinsumo dto) {

        dto.setIdDocrechazoinsumo(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_DOCRECHAZOINSUMO.NEXTVAL FROM DUAL",
                BigDecimal.class));

        getJdbcTemplateBase()
                .update("INSERT INTO "
                        + getTableName()
                        + " ( ID_DOCRECHAZOINSUMO, ID_RECHAZO_INSUMO, RUTA_ARCHIVO, FECHA_CREACION ) VALUES ( ?, ?, ?, ? )",
                        dto.getIdDocrechazoinsumo(), dto.getIdRechazoInsumo(),
                        dto.getRutaArchivo() + dto.getNombreArchivo(),
                        dto.getFechaCreacion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_DOCRECHAZOINSUMO table.
     */
    public void update(FecetDocrechazoinsumoPk pk, FecetDocrechazoinsumo dto) {

        getJdbcTemplateBase()
                .update("UPDATE "
                        + getTableName()
                        + " SET ID_DOCRECHAZOINSUMO = ?, ID_RECHAZO_INSUMO = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ? WHERE ID_DOCRECHAZOINSUMO = ? ",
                        dto.getIdDocrechazoinsumo(), dto.getIdRechazoInsumo(),
                        dto.getRutaArchivo(), dto.getFechaCreacion(),
                        pk.getIdDocrechazoinsumo());

    }

    /**
     * Deletes a single row in the FECET_DOCRECHAZOINSUMO table.
     */
    public void delete(FecetDocrechazoinsumoPk pk) {

        getJdbcTemplateBase().update(
                "DELETE FROM " + getTableName()
                + " WHERE ID_DOCRECHAZOINSUMO = ?",
                pk.getIdDocrechazoinsumo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOCRECHAZOINSUMO";
    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'ID_DOCRECHAZOINSUMO = :idDocrechazoinsumo'.
     */
    public FecetDocrechazoinsumo findByPrimaryKey(BigDecimal idDocrechazoinsumo) {

        List<FecetDocrechazoinsumo> list = getJdbcTemplateBase().query(
                SQL_SELECT + getTableName() + " WHERE ID_DOCRECHAZOINSUMO = ?",
                new FecetDocrechazoinsumoMapper(), idDocrechazoinsumo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria ''.
     */
    public List<FecetDocrechazoinsumo> findAll() {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName() + " ORDER BY ID_DOCRECHAZOINSUMO",
                new FecetDocrechazoinsumoMapper());

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    public List<FecetDocrechazoinsumo> findByFecetRechazoInsumo(
            BigDecimal idRechazoInsumo) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName() + " WHERE ID_RECHAZO_INSUMO = ?",
                new FecetDocrechazoinsumoMapper(), idRechazoInsumo);

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'ID_DOCRECHAZOINSUMO = :idDocrechazoinsumo'.
     */
    public List<FecetDocrechazoinsumo> findWhereIdDocrechazoinsumoEquals(
            BigDecimal idDocrechazoinsumo) {

        return getJdbcTemplateBase()
                .query(SQL_SELECT
                        + getTableName()
                        + " WHERE ID_DOCRECHAZOINSUMO = ? ORDER BY ID_DOCRECHAZOINSUMO",
                        new FecetDocrechazoinsumoMapper(), idDocrechazoinsumo);

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    public List<FecetDocrechazoinsumo> findWhereIdRechazoInsumoEquals(
            BigDecimal idRechazoInsumo) {

        return getJdbcTemplateBase()
                .query(SQL_SELECT
                        + getTableName()
                        + " WHERE ID_RECHAZO_INSUMO = ? ORDER BY ID_RECHAZO_INSUMO",
                        new FecetDocrechazoinsumoMapper(), idRechazoInsumo);

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocrechazoinsumo> findWhereRutaArchivoEquals(
            String rutaArchivo) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName()
                + " WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO",
                new FecetDocrechazoinsumoMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOCRECHAZOINSUMO table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetDocrechazoinsumo> findWhereFechaCreacionEquals(
            Date fechaCreacion) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName()
                + " WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION",
                new FecetDocrechazoinsumoMapper(), fechaCreacion);

    }

    /**
     * Returns the rows from the FECET_DOCRECHAZOINSUMO table that matches the
     * specified primary-key value.
     */
    public FecetDocrechazoinsumo findByPrimaryKey(FecetDocrechazoinsumoPk pk) {
        return findByPrimaryKey(pk.getIdDocrechazoinsumo());
    }
}
