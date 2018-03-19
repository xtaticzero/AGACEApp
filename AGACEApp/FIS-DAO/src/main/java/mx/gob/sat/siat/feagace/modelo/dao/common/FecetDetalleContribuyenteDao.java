package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyentePk;

public interface FecetDetalleContribuyenteDao {

    BigDecimal getIdConsecutivo();

    FecetDetalleContribuyente findByRfc(String rfc);

    void update(FecetDetalleContribuyente fecetDetalleContribuyente);

    FecetDetalleContribuyentePk insert(FecetDetalleContribuyente fecetDetalleContribuyente);

}
