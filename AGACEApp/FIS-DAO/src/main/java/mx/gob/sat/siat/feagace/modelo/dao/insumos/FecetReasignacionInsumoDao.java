package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumoPk;

public interface FecetReasignacionInsumoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     *
     * @return FecetReasignacionInsumoPk
     */
    FecetReasignacionInsumoPk insert(FecetReasignacionInsumo dto);

    BigDecimal update(FecetReasignacionInsumo dto);

    /**
     * Returns all rows from the FECET_REASIGNACION_INSUMO table that match the
     * criteria 'RFC_ADMINISTRADOR_ORIGEN = :rfcAdministradorOrigen'.
     */
    List<FecetReasignacionInsumo> findByAdministradorEstatus(String rfcAdministrador, BigDecimal estatus);

    List<FecetInsumo> findReasingacionByAdministradorEstatus(String administradorDestino, BigDecimal estatus);

    List<FecetReasignacionInsumo> findByIdMetodo(BigDecimal idInsumo);
}
