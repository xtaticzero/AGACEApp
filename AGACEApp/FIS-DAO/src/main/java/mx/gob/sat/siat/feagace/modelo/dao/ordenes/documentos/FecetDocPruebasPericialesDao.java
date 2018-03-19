package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericialesPk;

public interface FecetDocPruebasPericialesDao {

    FecetDocPruebasPericialesPk insert(FecetDocPruebasPericiales dto);

    void update(FecetDocPruebasPericialesPk pk, FecetDocPruebasPericiales dto);

    void delete(FecetDocPruebasPericialesPk pk);

    BigDecimal getIdFecetDocPruebasPericialesPathDirectorio();

    FecetDocPruebasPericiales findByPrimaryKey(BigDecimal idPruebasPericiales);

    List<FecetDocPruebasPericiales> findAll();

    List<FecetDocPruebasPericiales> findWhereIdPruebasPericialesEquals(BigDecimal idPruebasPericiales);

    List<FecetDocPruebasPericiales> findWhereIdDocPruebasPericialesEquals(final BigDecimal idDocPruebasPericiales);

    FecetDocPruebasPericiales findByPrimaryKey(final FecetDocPruebasPericialesPk pk);

}
