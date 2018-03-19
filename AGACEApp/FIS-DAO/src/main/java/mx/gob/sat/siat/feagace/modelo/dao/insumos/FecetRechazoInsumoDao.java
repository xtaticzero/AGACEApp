package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumoPk;

public interface FecetRechazoInsumoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoInsumoPk
     */
    FecetRechazoInsumoPk insert(FecetRechazoInsumo dto);

    /**
     * Cuenta todas los documentos del rechazo que corresponden a cada insumo.
     */
    List<FecetContadorInsumosRechazados> getContadorRechazo(final BigDecimal idInsumo);

    /**
     * Updates a single row in the FECET_RECHAZO_INSUMO table.
     */
    void update(FecetRechazoInsumoPk pk, FecetRechazoInsumo dto);

    /**
     * Deletes a single row in the FECET_RECHAZO_INSUMO table.
     */
    void delete(FecetRechazoInsumoPk pk);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    FecetRechazoInsumo findByPrimaryKey(BigDecimal idRechazoInsumo);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria ''.
     */
    List<FecetRechazoInsumo> findAll();

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    List<FecetRechazoInsumo> findWhereIdRechazoInsumoEquals(BigDecimal idRechazoInsumo);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetRechazoInsumo> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    List<FecetRechazoInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FecetRechazoInsumo> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    List<FecetRechazoInsumo> findWhereFechaRechazoEquals(Date fechaRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    List<FecetRechazoInsumo> findWhereRfcRechazoEquals(String rfcRechazo);

    /**
     * Returns the rows from the FECET_RECHAZO_INSUMO table that matches the
     * specified primary-key value.
     */
    FecetRechazoInsumo findByPrimaryKey(FecetRechazoInsumoPk pk);

}
