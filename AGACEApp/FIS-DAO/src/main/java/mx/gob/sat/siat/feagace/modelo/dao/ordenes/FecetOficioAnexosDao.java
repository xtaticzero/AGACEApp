package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

public interface FecetOficioAnexosDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetOficioAnexos
     */
    FecetOficioAnexos insert(FecetOficioAnexos dto);

    /**
     * Metodo getIdFecetOficioAnexosPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetOficioAnexosPathDirectorio();

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    void update(FecetOficioAnexos dto);

    /**
     * Deletes a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    void delete(FecetOficioAnexos pk);

    /**
     * Returns all rows from the FECET_OFICIO_ANEXOS table that match the
     * criteria 'ID_OFICIO = :idOficio'.
     */
    List<FecetOficioAnexos> getAnexosRechazo(final BigDecimal idOficio);

    /**
     * Returns all rows from the FECET_OFICIO_ANEXOS table that match the
     * criteria 'ID_OFICIO = :idOficio'.
     */
    List<FecetOficioAnexos> getAnexosByIdOficio(final BigDecimal idOficio);

}
