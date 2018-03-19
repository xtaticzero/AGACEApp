package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficioPk;

public interface FecetPromocionOficioDao extends FirmaDAO {

    BigDecimal insert(FecetPromocionOficio dto);

    void update(FecetPromocionOficio dto);

    List<FecetPromocionOficio> findWhereIdPromocionEquals(BigDecimal idOrden);

    List<FecetPromocionOficio> findAll();

    List<FecetPromocionOficio> getPromocionContadorPruebasAlegatosOficio(final BigDecimal idOrden);

    List<FecetPromocionOficio> findWhereIdOficioEquals(BigDecimal idOrden);

    BigDecimal getIdPromocionOficio();

    FecetPromocionOficioPk insertarRegistroGuardarArchivoPromocionOficio(FecetPromocionOficio dto);
}
