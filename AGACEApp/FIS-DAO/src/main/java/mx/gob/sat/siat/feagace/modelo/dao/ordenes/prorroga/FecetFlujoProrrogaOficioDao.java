package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;

public interface FecetFlujoProrrogaOficioDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetFlujoProrrogaOficio
     */
    FecetFlujoProrrogaOficio insert(FecetFlujoProrrogaOficio dto);

    /**
     * Updates a single row in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    void update(FecetFlujoProrrogaOficio dto);

    /**
     * Deletes a single row in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    void delete(final FecetFlujoProrrogaOficio dto);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    FecetFlujoProrrogaOficio findByPrimaryKey(final BigDecimal idFlujoProrrogaOficio);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria ''.
     */
    List<FecetFlujoProrrogaOficio> findAll();

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    List<FecetFlujoProrrogaOficio> findWhereIdFlujoProrrogaOficioEquals(final BigDecimal idFlujoProrrogaOficio);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_PRORROGA_OFICIO = :idProrrogaOficio'.
     */
    List<FecetFlujoProrrogaOficio> findWhereIdProrrogaOficioEquals(final BigDecimal idProrrogaOficio);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetFlujoProrrogaOficio> findWhereFechaCreacionEquals(final Date fechaCreacion);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'JUSTIFICACION = :justificacion'.
     */
    List<FecetFlujoProrrogaOficio> findWhereJustificacionEquals(final String justificacion);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'JUSTIFICACION_FIRMANTE = :justificacionFirmante'.
     */
    List<FecetFlujoProrrogaOficio> findWhereJustificacionFirmanteEquals(final String justificacionFirmante);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'APROBADA = :aprobada'.
     */
    List<FecetFlujoProrrogaOficio> findWhereAprobadaEquals(final String aprobada);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_ESTATUS = :idEstatus'.
     */
    List<FecetFlujoProrrogaOficio> findWhereIdEstatusEquals(final BigDecimal idEstatus);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    List<FecetFlujoProrrogaOficio> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante);

    /**
     * Returns the rows from the FECET_FLUJO_PRORROGA_OFICIO table that matches
     * the specified primary-key value.
     */
    FecetFlujoProrrogaOficio findByPrimaryKey(final FecetFlujoProrrogaOficio dto);

    BigDecimal getIdFecetFlujoProrrogaOficioPathDirectorio();

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    void actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOficio(final BigDecimal idProrrogaOficio);

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_PRORROGA_OFICIO = :idProrrogaOficio'.
     */
    List<FecetFlujoProrrogaOficio> buscarFlujoPorIdProrrogaOficio(BigDecimal idProrrogaOficio);

    /**
     * @param idProrrogaOficio
     * @return ultima Prorroga
     */
    String findLastIdFecetFlujoProrrogaOficio(BigDecimal idProrrogaOficio);
}
