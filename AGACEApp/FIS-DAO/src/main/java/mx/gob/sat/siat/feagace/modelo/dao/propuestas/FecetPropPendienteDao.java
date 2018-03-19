package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendientePk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;

public interface FecetPropPendienteDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPropPendientePk
     */
    FecetPropPendientePk insert(FecetPropPendiente dto);

    /**
     * Updates a single row in the FECET_PROP_PENDIENTE table.
     */
    void update(FecetPropPendientePk pk, FecetPropPendiente dto);

    /**
     * Deletes a single row in the FECET_PROP_PENDIENTE table.
     */
    void delete(FecetPropPendientePk pk);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROP_PENDIENTE = :idPropPendiente'.
     */
    FecetPropPendiente findByPrimaryKey(BigDecimal idPropPendiente);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria ''.
     */
    List<FecetPropPendiente> findAll();

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROP_PENDIENTE = :idPropPendiente'.
     */
    List<FecetPropPendiente> findWhereIdPropPendienteEquals(BigDecimal idPropPendiente);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetPropPendiente> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetPropPendiente> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'RFC_CREACION = :rfcCreacion'.
     */
    List<FecetPropPendiente> findWhereRfcCreacionEquals(String rfcCreacion);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'OBSERVACIONES = :observaciones'.
     */
    List<FecetPropPendiente> findWhereObservacionesEquals(String observaciones);

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    List<FecetPropPendiente> findWhereEstatusEquals(String estatus);

    /**
     * Returns the rows from the FECET_PROP_PENDIENTE table that matches the
     * specified primary-key value.
     */
    FecetPropPendiente findByPrimaryKey(FecetPropPendientePk pk);

    int updateEstadoByIdPropuesta(BigDecimal idPropuesta, EstadoBooleanodeRegistroEnum estadoRegistro);

    FecetPropPendientePk insertDoc(FecetPropPendiente dto);

    BigDecimal getConsecutivoPendiente();

    BigDecimal getConsecutivoDocPendiente();

    FecetPropPendiente obtienePendienteIdPropuestaEquals(BigDecimal idPropuesta);

}
