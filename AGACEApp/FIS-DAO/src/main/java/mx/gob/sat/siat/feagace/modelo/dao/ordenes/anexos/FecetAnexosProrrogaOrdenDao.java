package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrdenPk;

public interface FecetAnexosProrrogaOrdenDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexosProrrogaOrdenPk
     */
    FecetAnexosProrrogaOrdenPk insert(FecetAnexosProrrogaOrden dto);

    /**
     * Metodo getIdFecetAnexosProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetAnexosProrrogaOrdenPathDirectorio();

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    void update(FecetAnexosProrrogaOrdenPk pk, FecetAnexosProrrogaOrden dto);

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXOS_PRORROGA_ORDEN = :idAnexosProrrogaOrden'.
     */
    FecetAnexosProrrogaOrden findByPrimaryKey(BigDecimal idAnexosProrrogaOrden);

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria ''.
     */
    List<FecetAnexosProrrogaOrden> findAll();

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXOS_PRORROGA_ORDEN = :idAnexosProrrogaOrden'.
     */
    List<FecetAnexosProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idAnexosProrrogaOrden);

    /**
     * Returns the rows from the FECET_ANEXOS_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    FecetAnexosProrrogaOrden findByPrimaryKey(FecetAnexosProrrogaOrdenPk pk);

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    List<FecetAnexosProrrogaOrden> findWhereIdFlujoProrrogaOrdenEquals(final BigDecimal idFlujoProrrogaOrden);

    List<FecetAnexosProrrogaOrden> obtenerAnexosResolucionProrrogaAuditor(BigDecimal idAnexosProrrogaOrden);

}
