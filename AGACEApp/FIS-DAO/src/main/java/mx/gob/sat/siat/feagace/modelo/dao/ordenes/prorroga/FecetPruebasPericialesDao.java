package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericialesPk;

public interface FecetPruebasPericialesDao extends FirmaDAO {

    FecetPruebasPericialesPk insert(FecetPruebasPericiales dto);

    void update(FecetPruebasPericialesPk pk, FecetPruebasPericiales dto);

    void updatePruebasPericialesFirma(FecetPruebasPericiales dto);

    void validaPruebasPericialesAuditor(final BigDecimal idPruebasPericiales, final String estatusAprobada);

    void validaPruebasPericialesFirmante(final BigDecimal idPruebasPericiales, final String estatusValidada);

    void delete(FecetPruebasPericialesPk pk);

    BigDecimal getIdFecetPruebasPericialesPathDirectorio();

    List<FecetPruebasPericiales> buscaUltimaPruebasPericialesPorOrden(AgaceOrden orden);

    void actualizarPorEstadoYFechaMax(FecetPruebasPericiales pruebasPericiales, AgaceOrden orden);

    FecetPruebasPericiales findByPrimaryKey(BigDecimal idPruebasPericiales);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrden(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getHistoricoPruebasPericialesPorOrden(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrdenFirmada(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrdenPorFirmar(BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrdenFirmante(final BigDecimal idOrden);

    List<FecetPruebasPericiales> findAll();

    List<FecetPruebasPericiales> findWhereIdPruebasPericialesEquals(BigDecimal idPruebaPericial);

    List<FecetPruebasPericiales> findWhereIdOrdenEquals(BigDecimal idOrden);

    List<FecetPruebasPericiales> findWhereFechaCargaEquals(Date fechaCarga);

    List<FecetPruebasPericiales> findWhereAprobadaEquals(final String aprobada);

    List<FecetPruebasPericiales> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente);

    List<FecetPruebasPericiales> findWhereIdAuditorEquals(final BigDecimal idAuditor);

    List<FecetPruebasPericiales> findWhereIdFirmanteEquals(final BigDecimal idFirmante);

    List<FecetPruebasPericiales> findWhereFechaFirmaEquals(Date fechaFirma);

    List<FecetPruebasPericiales> findWhereIdEstatusIdOrdenEquals(final BigDecimal estado, BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrdenEstatusPendiente(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebasPericialesPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden);

    FecetPruebasPericiales findByPrimaryKey(FecetPruebasPericialesPk pk);

    List<FecetPruebasPericiales> getPruebasPericialesContadorDoc(final BigDecimal idOrden);

    List<FecetPruebasPericiales> findWhereIdOrdenEstatusEquals(final BigDecimal idOrden,
            final BigDecimal... idsEstatus);

    void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus);

    void guardarFirma(FirmaDTO firma);

    void updatePruebasPericialesResolucion(FecetPruebasPericiales dto);

    List<FecetPruebasPericiales> getPruebasPericialesPendienteAprobPorOrden(final BigDecimal idOrden);

    List<FecetPruebasPericiales> existeSolicitudPruebaPericial(final BigDecimal idOrden);

    List<FecetPruebasPericiales> existeSolicitudAprobada(final BigDecimal idOrden);

    String obtenerNumeroReferenciaPruePer(Long idPruebasPer);

    void updateNumeroReferenciaPruePer(String noReferencia, Long idPruebasPer);
}
