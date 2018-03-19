package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;

public interface FecetFlujoPruebasPericialesDao {

    BigDecimal getIdFecetFlujoPruebasPericialesPathDirectorio();

    BigDecimal insert(FecetFlujoPruebasPericiales dto);

    void update(FecetFlujoPruebasPericiales dto);

    void delete(final FecetFlujoPruebasPericiales dto);

    FecetFlujoPruebasPericiales findByPrimaryKey(final BigDecimal idFlujoPruebasPericiales);

    List<FecetFlujoPruebasPericiales> findAll();

    List<FecetFlujoPruebasPericiales> findWhereIdFlujoPruebasPericialesEquals(final BigDecimal idFlujoPruebasPericiales);

    List<FecetFlujoPruebasPericiales> findWhereIdPruebasPericialesEquals(final Date idPruebasPericiales);

    List<FecetFlujoPruebasPericiales> findWhereFechaCreacionEquals(final Date fechaCreacion);

    List<FecetFlujoPruebasPericiales> findWhereJustificacionEquals(final String justificacion);

    List<FecetFlujoPruebasPericiales> findWhereJustificacionFirmanteEquals(final String justificacionFirmante);

    List<FecetFlujoPruebasPericiales> findWhereAprobadaEquals(final String aprobada);

    List<FecetFlujoPruebasPericiales> findWhereIdEstatusEquals(final BigDecimal idEstatus);

    List<FecetFlujoPruebasPericiales> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante);

    FecetFlujoPruebasPericiales findByPrimaryKey(final FecetFlujoPruebasPericiales dto);

    String findLastIdFecetFlujoPruebasPericiales(BigDecimal idPruebasPericiales);

    List<FecetFlujoPruebasPericiales> buscarFlujoPorIdPruebasPericiales(BigDecimal idPruebasPericiales);

    void actualizarFlujoConcluidoRechazadoPorFirmanteByIdPruebasPericiales(final BigDecimal idPruebasPericiales);

    List<FecetFlujoPruebasPericiales> obtenerFlujoPruebaById(BigDecimal idPruebaPericial);

}
