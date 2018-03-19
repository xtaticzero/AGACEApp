package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;

public interface FecetAlegatoOficioDao {

    BigDecimal insert(FecetAlegatoOficio dto);

    FecetAlegatoOficio findByPrimaryKey(BigDecimal idAlegatoOficio);

    List<FecetAlegatoOficio> findAll();

    List<FecetAlegatoOficio> findWhereIdPromocionEquals(BigDecimal idPromocionOficio);
}
