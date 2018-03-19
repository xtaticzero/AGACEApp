package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrdenPk;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

public interface FecetDocOrdenDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocOrdenPk
     */
    FecetDocOrdenPk insert(FecetDocOrden dto);

    /**
     * Updates a single row in the FecetDocOrden table.
     */
    void update(FecetDocOrdenPk pk, FecetDocOrden dto);

    /**
     * Deletes a single row in the FecetDocOrden table.
     */
    void delete(FecetDocOrdenPk pk);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_DOC_ORDEN = :idDocOrden'.
     */
    FecetDocOrden findByPrimaryKey(BigDecimal idDocOrden);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * ''.
     */
    List<FecetDocOrden> findAll();

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    List<FecetDocOrden> findByFecetOrden(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_DOC_ORDEN = :idDocOrden'.
     */
    List<FecetDocOrden> findWhereIdDocOrdenEquals(BigDecimal idDocOrden);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    List<FecetDocOrden> findWhereIdOrdenEquals(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombreArchivo'.
     */
    List<FecetDocOrden> findWhereNombreArchivoEquals(String nombreArchivo);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetDocOrden> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetDocOrden> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ESTATUS = :estatus'.
     */
    List<FecetDocOrden> findWhereEstatusEquals(String estatus);

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'DOCUMENTOPDF = :documentoPdf'.
     */
    List<FecetDocOrden> findWhereDocumentoPdfEquals(String documentoPdf);

    /**
     * Returns the rows from the FECET_DOC_ORDEN table that matches the
     * specified primary-key value.
     */
    FecetDocOrden findByPrimaryKey(FecetDocOrdenPk pk);

    /**
     * Returns all rows from the FECET_DOC_ORDEN, FECET_ORDEN table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_DOC_ORDEN, FECET_ORDEN table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetDocOrden> findByIdPropuesta(BigDecimal idPropuesta);

    void updateEstatusDocumento(final BigDecimal idOrden);

    List<FecetDocOrden> findByEstatusActivo(final BigDecimal idOrden);

    List<FecetDocOrden> findByFecetOrdenActivo(BigDecimal idOrden);

    BigDecimal getClaveDocOrden();

    List<FecetDocOrden> findByFecetOrdenPdf(BigDecimal idOrden);

    void updateEstatusDocFechaFin(Date fechaFin, final BigDecimal idDocOrden);

    List<FecetDocOrden> accionIdPropuestaHistorial(BigDecimal idPropuesta);

    void updateActivoDocumento(BigDecimal idOrden);
}
