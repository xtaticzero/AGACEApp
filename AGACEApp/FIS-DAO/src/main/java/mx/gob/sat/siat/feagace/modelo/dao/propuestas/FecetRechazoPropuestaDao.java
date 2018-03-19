package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public interface FecetRechazoPropuestaDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoPropuestaPk
     */
    FecetRechazoPropuestaPk insert(FecetRechazoPropuesta dto);

    /**
     * Method 'insertarRechazoPropuesta'
     *
     * @param dto
     * @return FecetRechazoPropuestaPk
     */
    FecetRechazoPropuestaPk insertarRechazoPropuesta(FecetRechazoPropuesta dto);

    /**
     * Method 'insertDoc'
     *
     * @param dto
     * @return FecetRechazoPropuestaPk
     */
    FecetRechazoPropuestaPk insertDoc(FecetRechazoPropuesta dto);

    /**
     * Updates a single row in the FECET_RECHAZO_PROPUESTA table.
     */
    void update(FecetRechazoPropuestaPk pk, FecetRechazoPropuesta dto);

    /**
     * Deletes a single row in the FECET_RECHAZO_PROPUESTA table.
     */
    void delete(FecetRechazoPropuestaPk pk);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ID_RECHAZO_PROPUESTA = :idRechazoPropuesta'.
     */
    FecetRechazoPropuesta findByPrimaryKey(BigDecimal idRechazoPropuesta);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria ''.
     */
    List<FecetRechazoPropuesta> findAll();

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ID_MOTIVO = :idMotivo'.
     */
    List<FecetRechazoPropuesta> findByFececMotivo(BigDecimal idMotivo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ID_RECHAZO_PROPUESTA = :idRechazoPropuesta'.
     */
    List<FecetRechazoPropuesta> findWhereIdRechazoPropuestaEquals(BigDecimal idRechazoPropuesta);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetRechazoPropuesta> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ID_MOTIVO = :idMotivo'.
     */
    List<FecetRechazoPropuesta> findWhereIdMotivoEquals(BigDecimal idMotivo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FecetRechazoPropuesta> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    List<FecetRechazoPropuesta> findWhereFechaRechazoEquals(Date fechaRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'FECHA_INFORME_RECHAZO = :fechaInformeRechazo'.
     */
    List<FecetRechazoPropuesta> findWhereFechaInformeRechazoEquals(Date fechaInformeRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    List<FecetRechazoPropuesta> findWhereRfcRechazoEquals(String rfcRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetRechazoPropuesta> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'TIPO = :tipo'.
     */
    List<FecetRechazoPropuesta> findWhereTipoEquals(String tipo);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    List<FecetRechazoPropuesta> findWhereEstatusEquals(String estatus);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'DESCRIPCION_FIRMANTE = :descripcionFirmante'.
     */
    List<FecetRechazoPropuesta> findWhereDescripcionFirmanteEquals(String descripcionFirmante);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    List<FecetRechazoPropuesta> findWhereFechaRechazoFirmanteEquals(Date fechaRechazoFirmante);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'RFC_RECHAZO_FIRMANTE = :rfcRechazoFirmante'.
     */
    List<FecetRechazoPropuesta> findWhereRfcRechazoFirmanteEquals(String rfcRechazoFirmante);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'NOMBRE_ARCHIVO_FIRMANTE = :nombreArchivoFirmante'.
     */
    List<FecetRechazoPropuesta> findWhereNombreArchivoFirmanteEquals(String nombreArchivoFirmante);

    /**
     * Returns all rows from the FECET_RECHAZO_PROPUESTA table that match the
     * criteria 'RUTA_ARCHIVO_FIRMANTE = :rutaArchivoFirmante'.
     */
    List<FecetRechazoPropuesta> findWhereRutaArchivoFirmanteEquals(String rutaArchivoFirmante);

    /**
     * Returns all rows from the consult RECHAZOS_PROPUESTA
     */
    List<ConsultaInformeComiteRechazoPropuesta> findAllRechazos(String rfc, String idEntidad, String idActividadPreponderante, BigDecimal idEstatus, String condicionEmpleado);

    /**
     * Cuenta todas los documentos del rechazo que corresponden a cada insumo.
     *
     * @param one
     * @param propuestaRechazadaPendienteValidacion
     */
    List<FecetContadorPropuestasRechazados> getContadorRechazo(
            final BigDecimal idPropuesta);

    List<FecetContadorPropuestasRechazados> getContadorRechazo(
            final BigDecimal idPropuesta, TipoEstatusEnum propuestaRechazadaPendienteValidacion, BigDecimal blnEstatus);

    /**
     * Method 'getIdRechazoPropuesta'
     *
     * @return BigDecimal
     */
    BigDecimal getIdRechazoPropuesta();

    /**
     * Returns the rows from the FECET_RECHAZO_PROPUESTA table that matches the
     * specified primary-key value.
     */
    FecetRechazoPropuesta findByPrimaryKey(FecetRechazoPropuestaPk pk);

    /**
     * Metodo getIdFecetProrrogaPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetPropuestaRechazo();

    /**
     * Metodo getDocRechazadosByIdPropuesta
     *
     * Returns all rows from the FECET_DOC_RECHAZO_PROPUESTA table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetRechazoPropuesta> getDocRechazadosByIdPropuesta(final BigDecimal idPropuesta);

    /**
     * Metodo getDocRechazadosByIdRechazo
     *
     * Returns all rows from the FECET_DOC_RECHAZO_PROPUESTA table that match
     * the criteria 'ID_RECHAZO_PROPUESTA = :idRechazoPropuesta'.
     */
    List<FecetRechazoPropuesta> getDocRechazadosByIdRechazo(final BigDecimal idRechazoPropuesta);

    /**
     * Metodo findWhereIdPropuestaEqualsOrderByFecha
     *
     * @return List<FecetRechazoPropuesta>
     * @
     */
    List<FecetRechazoPropuesta> findWhereIdPropuestaEqualsOrderByFecha(BigDecimal idPropuesta, BigDecimal idArace, BigDecimal idTipoEmpleado);

    BigDecimal getConsecutivoDocRechazo();

    /**
     * Metodo updateEstatusRechazo
     *
     * @param idPropuesta
     */
    void updateEstatusRechazo(BigDecimal idPropuesta);

    /**
     * Metodo updateEstatusDocRechazo
     *
     * @param idPropuesta
     */
    void updateEstatusDocRechazo(BigDecimal idPropuesta);

    List<FecetRechazoPropuesta> findRechazosByIdPropuestaEquals(BigDecimal idPropuesta);
}
