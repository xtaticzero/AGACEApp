package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;

public interface FecetFlujoProrrogaOrdenDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return BigDecimal
     */
    BigDecimal insert(FecetFlujoProrrogaOrden dto);

    /**
     * Updates a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    void update(FecetFlujoProrrogaOrden dto);

    /**
     * Deletes a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    void delete(final FecetFlujoProrrogaOrden dto);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    FecetFlujoProrrogaOrden findByPrimaryKey(final BigDecimal idFlujoProrrogaOrden);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    List<FecetFlujoProrrogaOrden> findAll();

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    List<FecetFlujoProrrogaOrden> findWhereIdFlujoProrrogaOrdenEquals(final BigDecimal idFlujoProrrogaOrden);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrrogaOrden'.
     */
    List<FecetFlujoProrrogaOrden> findWhereIdProrrogaOrdenEquals(final Date idProrrogaOrden);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetFlujoProrrogaOrden> findWhereFechaCreacionEquals(final Date fechaCreacion);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION = :justificacion'.
     */
    List<FecetFlujoProrrogaOrden> findWhereJustificacionEquals(final String justificacion);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION_FIRMANTE = :justificacionFirmante'.
     */
    List<FecetFlujoProrrogaOrden> findWhereJustificacionFirmanteEquals(final String justificacionFirmante);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    List<FecetFlujoProrrogaOrden> findWhereAprobadaEquals(final String aprobada);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_ESTATUS = :idEstatus'.
     */
    List<FecetFlujoProrrogaOrden> findWhereIdEstatusEquals(final BigDecimal idEstatus);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    List<FecetFlujoProrrogaOrden> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante);

    /**
     * Returns the rows from the FECET_FLUJO_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    FecetFlujoProrrogaOrden findByPrimaryKey(final FecetFlujoProrrogaOrden dto);

    /**
     * @return
     */
    BigDecimal getIdFecetFlujoProrrogaOrdenPathDirectorio();

    /**
     * @param idProrrogaOrden
     * @return idFlujoProrrogaOrden
     */
    String findLastIdFecetFlujoProrrogaOrden(BigDecimal idProrrogaOrden);

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     *
     * @param idProrrogaOrden
     */
    void actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOrden(final BigDecimal idProrrogaOrden);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrrogaOrden'.
     *
     */
    List<FecetFlujoProrrogaOrden> buscarFlujoPorIdProrrogaOrden(BigDecimal idProrrogaOrden);

}
