package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTerceroPk;

public interface FecetDocTerceroDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocTerceroPk
     */
    FecetDocTerceroPk insert(FecetDocTercero dto);

    /**
     * Updates a single row in the FECET_DOC_TERCERO table.
     */
    void update(FecetDocTerceroPk pk, FecetDocTercero dto);

    /**
     * Deletes a single row in the FECET_DOC_TERCERO table.
     */
    void delete(FecetDocTerceroPk pk);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_DOC_TERCERO = :idDocTercero'.
     */
    FecetDocTercero findByPrimaryKey(BigDecimal idDocTercero);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * ''.
     */
    List<FecetDocTercero> findAll();

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_DOC_TERCERO = :idDocTercero'.
     */
    List<FecetDocTercero> findWhereIdDocTerceroEquals(BigDecimal idDocTercero);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ID_COMP_TERCERO = :idCompTercero'.
     */
    List<FecetDocTercero> findWhereIdCompTerceroEquals(BigDecimal idCompTercero);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombreArchivo'.
     */
    List<FecetDocTercero> findWhereNombreArchivoEquals(String nombreArchivo);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetDocTercero> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetDocTercero> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'RFC_CARGA = :rfcCarga'.
     */
    List<FecetDocTercero> findWhereRfcCargaEquals(final String rfcCarga);

    /**
     * Returns all rows from the FECET_DOC_TERCERO table that match the criteria
     * 'ACUSE = :acuse'.
     */
    List<FecetDocTercero> findWhereAcuseEquals(final String acuse);

    /**
     * Returns the rows from the FECET_DOC_TERCERO table that matches the
     * specified primary-key value.
     */
    FecetDocTercero findByPrimaryKey(FecetDocTerceroPk pk);

    /**
     * Obtiene el acuse para la carga de documentos de la compulsa
     *
     * @return String
     */
    String getAcuse();

}
