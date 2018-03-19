package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;

public interface FecetOficioDao {

    BigDecimal getIdOficio();

    BigDecimal insert(FecetOficio dto);

    void updateFirmante(FecetOficio dto);

    FecetOficio getOficioById(BigDecimal idOficio);

    List<FecetOficio> getOficioByIdOrdenIdEstatus(final BigDecimal idOrden, final BigDecimal... idsEstatus);

    List<FecetOficio> getOficioByIdOrden(final BigDecimal idOrden);

    List<FecetOficio> getOficioByIdOrdenRechazados(final BigDecimal idOrden);

    FecetOficio getOficioObservaciones(final BigDecimal idOrden);

    List<FecetOficio> getOficiosDependientesByIdOficioPrincipal(final BigDecimal idOficio);

    List<FecetOficio> getOficioByIdOrdenEstatusPendienteOFirmadoONotificadoNoSurteEfectos(final BigDecimal idOrden);

    List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden);

    List<FecetOficio> getOficiosByIdEstatus(BigDecimal... idEstatus);

    void updatePlazosOficio(FecetOficio dto);

    List<FecetOficio> getOficioDependienteByIdEstatusOficioPrincipalTipoOficio(final BigDecimal idOficioPrincipal, final BigDecimal idTipoOficio, final BigDecimal idEstatus);

    List<FecetOficio> getOficiosDependientesPorIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus);

    List<FecetOficio> getOficioByIdOficioIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus);

    void updateFechasNotificacion(final BigDecimal idOficio, final Date fechaNotificacionCont, final Date fechaSurteEfectos, final BigDecimal idEstatus);

    List<FecetOficio> obtenerOficioNoAdministrableByIdOrden(BigDecimal idOrden);

    List<FecetOficio> obtenerOficioByIdOrdenOficio(final BigDecimal idOrden, final List<BigDecimal> listIdsOficio);

    FecetOficio getOficioCompulsa(BigDecimal idOrdenAuditada, BigDecimal idOrdenCompulsa);

    void updateOficioIdNyV(FecetOficio oficio, FecetDetalleNyV nYvResult);

    List<FecetOficio> getOficiosPorOrden(final BigDecimal idOrden, final BigDecimal... idsEstatus);

    List<FecetOficio> getOficioPorIdTipoOficioYorden(BigDecimal idOrden, TiposOficiosOrdenesEnum oficio);

    List<FecetOficio> getOficiosDependientesPorIdEstatusContribuyente(final BigDecimal idOficio);

    List<FecetOficio> getOficiosDependientesFirmados(final BigDecimal idOficio);

    List<FecetOficio> getOficioByIdOrdenIdEstatusPorFirmar(final BigDecimal idOrden, final BigDecimal... idsEstatus);

    void updateBlnActivoCero(BigDecimal idOficio);

    List<FecetOficio> getOficiosAdministrablesPorIdOrden(BigDecimal idOrden);

    void updateNumeroOficio(FecetOficio oficio, String numeroOficio);

    List<FecetOficio> getOficiosAsociadosByOrden(final BigDecimal idOrden);

    List<FecetOficio> getOficioIdOrdenHistorial(final BigDecimal idOrden);

    void updateOficioByIdOrden(final BigDecimal idOrden);

    void updateOficioByIdOficio(final BigDecimal idOficio);

    List<FecetOficio> getOficioByIdOficio(final BigDecimal idOficio);

    void updateFechaFirma(FecetOficio oficio);

    List<FecetOficio> getOficioActivosByIdOrden(final BigDecimal idOrden);

    String obtenerNumeroReferenciaOficio(Long idOficio);

    void updateNumeroReferenciaOficio(String noReferencia, Long idOficio);
}
