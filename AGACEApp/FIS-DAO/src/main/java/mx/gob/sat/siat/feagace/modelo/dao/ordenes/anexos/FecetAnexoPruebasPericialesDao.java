package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericialesPk;

public interface FecetAnexoPruebasPericialesDao {

    FecetAnexoPruebasPericialesPk insert(FecetAnexoPruebasPericiales dto);

    void update(FecetAnexoPruebasPericialesPk pk, FecetAnexoPruebasPericiales dto);

    BigDecimal getIdFecetAnexoPruebasPericialesPathDirectorio();

    FecetAnexoPruebasPericiales findByPrimaryKey(BigDecimal idAnexosProrrogaOrden);

    List<FecetAnexoPruebasPericiales> findAll();

    List<FecetAnexoPruebasPericiales> findWhereIdProrrogaEquals(BigDecimal idAnexosProrrogaOrden);

    FecetAnexoPruebasPericiales findByPrimaryKey(FecetAnexoPruebasPericialesPk pk);

    List<FecetAnexoPruebasPericiales> findWhereIdFlujoPruebasPericialesEquals(final BigDecimal idFlujoPruebasPericiales);

    List<FecetAnexoPruebasPericiales> obtenerAnexosResolucionPruebaPericialAuditor(BigDecimal idFlujoPruebasPericiales);
}
