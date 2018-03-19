package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocTerceroDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocTerceroMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTerceroPk;

import org.springframework.stereotype.Repository;

@Repository("fecetDocTerceroDao")
public class FecetDocTerceroDaoImpl extends BaseJDBCDao<FecetDocTercero> implements FecetDocTerceroDao {

    private static final String CEROS_IZQUIERDA_7_POSICIONES = "%07d";

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_DOC_TERCERO, ID_COMP_TERCERO, NOMBRE_ARCHIVO, RUTA_ARCHIVO, FECHA_CREACION, RFC_CARGA, ACUSE FROM ").append(getTableName());

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocTerceroPk
     */
    public FecetDocTerceroPk insert(FecetDocTercero dto) {

        dto.setIdDocTercero(getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_TERCERO.NEXTVAL FROM DUAL",
                BigDecimal.class));

        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_DOC_TERCERO, ID_COMP_TERCERO, NOMBRE_ARCHIVO, RUTA_ARCHIVO, FECHA_CREACION, RFC_CARGA, ACUSE ) VALUES ( ?, ?, ?, ?, ?, ?, ? )",
                dto.getIdDocTercero(), dto.getIdCompTercero(), dto.getNombreArchivo(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getRfcCarga(), dto.getAcuse());

        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_DOC_TERCERO table.
     */
    public void update(FecetDocTerceroPk pk, FecetDocTercero dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_DOC_TERCERO = ?, ID_COMP_TERCERO = ?, NOMBRE_ARCHIVO = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, RFC_CARGA = ?, ACUSE = ? WHERE ID_DOC_TERCERO = ?",
                dto.getIdDocTercero(), dto.getIdCompTercero(), dto.getNombreArchivo(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getRfcCarga(), dto.getAcuse(),
                pk.getIdDocTercero());

    }

    /**
     * Deletes a single row in the FECET_DOC_TERCERO table.
     */
    public void delete(FecetDocTerceroPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_DOC_TERCERO = ?", pk.getIdDocTercero());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_TERCERO";
    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_DOC_TERCERO = :idDocTercero'.
     */
    public FecetDocTercero findByPrimaryKey(BigDecimal idDocTercero) {

        List<FecetDocTercero> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_DOC_TERCERO = ?").toString(), new FecetDocTerceroMapper(),
                        idDocTercero);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * ''.
     */
    public List<FecetDocTercero> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_DOC_TERCERO").toString(),
                new FecetDocTerceroMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_DOC_TERCERO = :idDocTercero'.
     */
    public List<FecetDocTercero> findWhereIdDocTerceroEquals(BigDecimal idDocTercero) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_DOC_TERCERO = ? ORDER BY ID_DOC_TERCERO").toString(),
                new FecetDocTerceroMapper(), idDocTercero);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_COMP_TERCERO = :idCompTercero'.
     */
    public List<FecetDocTercero> findWhereIdCompTerceroEquals(BigDecimal idCompTercero) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_COMP_TERCERO = ? ORDER BY ID_COMP_TERCERO").toString(),
                new FecetDocTerceroMapper(), idCompTercero);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombreArchivo'.
     */
    public List<FecetDocTercero> findWhereNombreArchivoEquals(String nombreArchivo) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE NOMBRE_ARCHIVO = ? ORDER BY NOMBRE_ARCHIVO").toString(),
                new FecetDocTerceroMapper(), nombreArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocTercero> findWhereRutaArchivoEquals(String rutaArchivo) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO").toString(),
                new FecetDocTerceroMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetDocTercero> findWhereFechaCreacionEquals(Date fechaCreacion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION").toString(),
                new FecetDocTerceroMapper(), fechaCreacion);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'RFC_CARGA = :rfcCarga'.
     */
    public List<FecetDocTercero> findWhereRfcCargaEquals(final String rfcCarga) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RFC_CARGA = ? ORDER BY RFC_CARGA").toString(),
                new FecetDocTerceroMapper(), rfcCarga);

    }

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ACUSE = :acuse'.
     */
    public List<FecetDocTercero> findWhereAcuseEquals(final String acuse) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ACUSE = ? ORDER BY ACUSE").toString(),
                new FecetDocTerceroMapper(), acuse);

    }

    /**
     * Returns the rows from the FECET_DOC_TERCERO table that matches the
     * specified primary-key value.
     */
    public FecetDocTercero findByPrimaryKey(FecetDocTerceroPk pk) {
        return findByPrimaryKey(pk.getIdDocTercero());
    }

    /**
     * Obtiene el acuse para la carga de documentos de la compulsa
     *
     * @return String
     */
    public String getAcuse() {

        String acuseReporte = null;

        Long numeroAcuse = 0L;

        numeroAcuse = getJdbcTemplateBase().queryForObject("SELECT FECEQ_ACUSE_COMP_TERCERO.NEXTVAL FROM DUAL", Long.class);

        Date fecha = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        StringBuilder acuse = new StringBuilder();
        acuse.append(dateFormat.format(fecha));
        acuse.append("-");
        acuse.append(getFolioFormato7Digitos(numeroAcuse));

        acuseReporte = acuse.toString();

        return acuseReporte;

    }

    private String getFolioFormato7Digitos(final Long consecutivoAcuse) {
        Formatter fmt;
        fmt = new Formatter();
        return String.valueOf(fmt.format(CEROS_IZQUIERDA_7_POSICIONES, consecutivoAcuse));
    }

}
