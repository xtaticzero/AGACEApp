package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;

public interface FecetAnexosProrrogaOficioDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexosProrrogaOrdenPk
     */
    BigDecimal insert(FecetAnexosProrrogaOficio dto);

    /**
     * Metodo getIdFecetAnexosProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetAnexosProrrogaOficioPathDirectorio();

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    void update(BigDecimal pk, FecetAnexosProrrogaOficio dto);

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_PRORROGA = :idProrroga'.
     */
    FecetAnexosProrrogaOficio findByPrimaryKey(BigDecimal idProrroga);

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria ''.
     */
    List<FecetAnexosProrrogaOficio> findAll();

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    List<FecetAnexosProrrogaOficio> findWhereIdFlujoProrrogaOficioEquals(final BigDecimal idFlujoProrrogaOficio);
}
