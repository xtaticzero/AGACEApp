package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpedientePk;

public interface FecetDocExpedienteDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocExpedientePk
     */
    FecetDocExpedientePk insert(FecetDocExpediente dto);

    /**
     * Updates a single row in the FECET_DOC_EXPEDIENTE table.
     *
     * @param pk
     * @param dto
     */
    void update(FecetDocExpedientePk pk, FecetDocExpediente dto);

    /**
     * Deletes a single row in the FECET_DOC_EXPEDIENTE table.
     *
     * @param pk
     */
    void delete(FecetDocExpedientePk pk);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_DOC_EXPEDIENTE = :idDocExpediente'.
     *
     * @param idDocExpediente
     * @return
     */
    FecetDocExpediente findByPrimaryKey(BigDecimal idDocExpediente);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria ''.
     *
     * @return
     */
    List<FecetDocExpediente> findAll();

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_DOC_EXPEDIENTE = :idDocExpediente'.
     *
     * @param idDocExpediente
     * @return
     */
    List<FecetDocExpediente> findWhereIdDocExpedienteEquals(BigDecimal idDocExpediente);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'NOMBRE = :nombre'.
     *
     * @param nombre
     * @return
     */
    List<FecetDocExpediente> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     *
     * @param rutaArchivo
     * @return
     */
    List<FecetDocExpediente> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     *
     * @param fechaCreacion
     * @return
     */
    List<FecetDocExpediente> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns the rows from the FECET_DOC_EXPEDIENTE table that matches the
     * specified primary-key value.
     *
     * @param pk
     * @return
     */
    FecetDocExpediente findByPrimaryKey(FecetDocExpedientePk pk);

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta' order by fecha_creacion desc.
     *
     * @param idPropuesta
     * @return
     */
    List<FecetDocExpediente> findWhereIdPropuestaEqualsOrderByFecha(BigDecimal idPropuesta);

}
