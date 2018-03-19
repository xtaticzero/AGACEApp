package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaPk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public interface FecetTransferenciaDao {

    /**
     * Metodo getIdFecetProrrogaPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetTransferencia();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    FecetTransferenciaPk insert(FecetTransferencia dto);

    /**
     * Updates a single row in the FECET_TRANSFERENCIA table.
     */
    void update(FecetTransferenciaPk pk, FecetTransferencia dto);

    /**
     * Deletes a single row in the FECET_TRANSFERENCIA table.
     */
    void delete(FecetTransferenciaPk pk);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_TRANSFERENCIA = :idTransferencia'.
     */
    FecetTransferencia findByPrimaryKey(BigDecimal idTransferencia);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria ''.
     */
    List<FecetTransferencia> findAll();

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_TRANSFERENCIA = :idTransferencia'.
     */
    List<FecetTransferencia> findWhereIdTransferenciaEquals(BigDecimal idTransferencia);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_ARACE_ORIGEN = :idAraceOrigen'.
     */
    List<FecetTransferencia> findWhereIdAraceOrigenEquals(BigDecimal idAraceOrigen);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_ARACE_DESTINO = :idAraceDestino'.
     */
    List<FecetTransferencia> findWhereIdAraceDestinoEquals(BigDecimal idAraceDestino);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'FECHA_TRASPASO = :fechaTraspaso'.
     */
    List<FecetTransferencia> findWhereFechaTraspasoEquals(Date fechaTraspaso);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RFC = :rfc'.
     */
    List<FecetTransferencia> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetTransferencia> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'OBSERVACIONES = :observaciones'.
     */
    List<FecetTransferencia> findWhereObservacionesEquals(final String observaciones);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    List<FecetTransferencia> findWhereEstatusEquals(final String estatus);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'DESCRIPCION_FIRMANTE = :descripcionFirmante'.
     */
    List<FecetTransferencia> findWhereDescripcionFirmanteEquals(String descripcionFirmante);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    List<FecetTransferencia> findWhereFechaRechazoFirmanteEquals(Date fechaRechazoFirmante);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RFC_FIRMANTE = :rfcRechazoFirmante'.
     */
    List<FecetTransferencia> findWhereRfcFirmanteEquals(String rfcFirmante);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'NOMBRE_ARCHIVO_FIRMANTE = :nombreArchivoFirmante'.
     */
    List<FecetTransferencia> findWhereNombreArchivoFirmanteEquals(String nombreArchivoFirmante);

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RUTA_ARCHIVO_FIRMANTE = :rutaArchivoFirmante'.
     */
    List<FecetTransferencia> findWhereRutaArchivoFirmanteEquals(String rutaArchivoFirmante);

    /**
     * Returns the rows from the FECET_TRANSFERENCIA table that matches the
     * specified primary-key value.
     */
    FecetTransferencia findByPrimaryKey(FecetTransferenciaPk pk);

    /**
     * Method 'insertDocTransferencia'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    FecetTransferenciaPk insertDocTransferencia(FecetTransferencia dto);

    /**
     * Method 'getIdDocTransferencia'
     *
     * @return BigDecimal
     */
    BigDecimal getIdDocTransferencia();

    int actualizarEstadoTransferencia(Long idTransferencia, EstadoBooleanodeRegistroEnum estadoBln);

    /**
     * Method 'findWhereIdPropuestaEqualsTransfPendientes' Returns all rows from
     * the FECET_TRANSFERENCIA table that match the criteria 'ID_PROPUESTA =
     * :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    List<FecetTransferencia> findWhereIdPropuestaEqualsTransfPendientes(final BigDecimal idPropuesta);

    /**
     * Method 'findDocWhereIdTransferenciaEquals'
     *
     * @param idTransferencia
     * @return List<FecetTransferencia>
     */
    List<FecetTransferencia> findDocWhereIdTransferenciaEquals(BigDecimal idTransferencia);

    FecetTransferencia obtenerTransferenciaXIdPropuestaBlnEstatus(BigDecimal idPropuesta, EstadoBooleanodeRegistroEnum estDoctos);

    void updateEstatusTransferencia(BigDecimal idPropuesta);

    void updateEstatusDocTransferencia(BigDecimal idPropuesta);

    /**
     * Updates field ID_TRANSFERENCIA_NUEVA in the FECET_TRANSFERENCIA table.
     */
    void updateIdPropuestaNueva(BigDecimal idPropuestaNueva, BigDecimal idTransferencia);

    /**
     * Updates fields FECHA_RECHAZO, MOTIVO_RECHAZO in the FECET_TRANSFERENCIA
     * table.
     */
    void updateTransferenciaRechazo(Date fechaRechazo, BigDecimal idMotivo, BigDecimal idTransferencia);

    List<FecetTransferenciaContador> obtenerTransferenciasByIdPropuesta(BigDecimal idPropuesta, TipoEstatusEnum estatus, BigDecimal blnEstatus);
}
