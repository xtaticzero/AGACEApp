package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodoPk;

public interface FecetCambioMetodoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetCambioMetodoPk
     */
    FecetCambioMetodoPk insert(FecetCambioMetodo dto);

    /**
     * Updates a single row in the FECET_CAMBIO_METODO table.
     */
    void update(FecetCambioMetodo dto);

    /**
     * Deletes a single row in the FECET_CAMBIO_METODO table.
     */
    void delete(FecetCambioMetodoPk pk);

    /**
     * Returns all rows from the FECET_CAMBIO_METODO table that match the
     * criteria 'ID_CAMBIO_METODO = :idCambioMetodo'.
     */
    FecetCambioMetodo findByPrimaryKey(BigDecimal idCambioMetodo);

    /**
     * Returns all rows from the FECET_CAMBIO_METODO table that match the
     * criteria ''.
     */
    List<FecetCambioMetodo> findAll();

    /**
     * @param idOrden
     * @return nuevo metodo
     */
    FecetCambioMetodo getDatosGenerales(BigDecimal idOrden);

}
