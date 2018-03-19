package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;

public interface FececConceptoDao {

    List<FececConcepto> findAll();

    int insert(FececConcepto concepto);

    List<FececConcepto> findByIdTipoImpuesto(final BigDecimal idTipoImpuesto);

    List<FececConcepto> findByTipoImpuestoName(String impuesto);

    List<FececConcepto> findByNombreConcepto(String concepto);

    List<FececConcepto> findRelacionImpuestoConcepto(BigDecimal idImpuesto);
}
