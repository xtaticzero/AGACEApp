package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FiltroOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;

public interface AgaceOrdenDao {

    AgaceOrdenPk insert(AgaceOrden dto);

    void update(AgaceOrdenPk pk, AgaceOrden dto);

    void updateFechaBaja(AgaceOrdenPk pk);

    void updateFechasValidas(AgaceOrdenPk pk, AgaceOrden dto);

    void updateEstatus(AgaceOrdenPk pk, AgaceOrden dto);

    AgaceOrden findByPrimaryKey(BigDecimal idOrden);

    List<AgaceOrden> findAll();

    List<AgaceOrden> findWhereIdOrdenEquals(long idOrden);

    List<AgaceOrden> findWhereIdMetodoEquals(long idMetodo);

    List<AgaceOrden> findWhereNumeroOrdenEquals(String numeroOrden);

    List<AgaceOrden> findWhereFechaCreacionEquals(Date fechaCreacion);

    List<AgaceOrden> findWhereFechaBajaEquals(Date fechaBaja);

    List<AgaceOrden> findWherePrioridadEquals(Boolean prioridad);

    List<AgaceOrden> findWhereFechaNotifNYVEquals(Date fechaNotifNYV);

    List<AgaceOrden> findWhereFechaNotifContEquals(Date fechaNotifCont);

    List<AgaceOrden> findWhereFechaIntegraExpEquals(Date fechaIntegraExp);

    List<AgaceOrden> findWhereFechaSurteEfectosEquals(final Date fechaSurteEfectos);

    List<AgaceOrden> findWhereEstatusEquals(final BigDecimal idEstatus);

    AgaceOrden findByIdPropuesta(final BigDecimal idPropuesta);

    AgaceOrden findByPrimaryKey(AgaceOrdenPk pk);

    List<AgaceOrden> getListaAuditor(String rfcAuditor);

    List<AgaceOrden> getOrdenesPorFirmarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo, BigDecimal idEmpleado);

    List<AgaceOrden> getOrdenesReimprimirDocumentacion();

    List<AgaceOrden> getOrdenesSeguimientoAuditor(final BigDecimal idEmpleado);

    List<AgaceOrden> getPropuestasOrdenesPorAsociado(final BigDecimal idEmpleado);

    void updateFolioNyV(AcuseDTO acuseDto);

    Long getClaveOrden();

    BigDecimal getNextOValueOrden();

    List<AgaceOrden> traeOrdenesSeguimientoAuditor(String rfcAuditor);

    List<AgaceOrden> getOrdenesContribuyente(final String rfcContribuyente, BigDecimal idEstatus);

    void updatePlazosOrden(AgaceOrden dto);

    List<ContadorOrdenes> numeroOrdenesValidarFirmar(BigDecimal idEmpleado, String rfcFirmante);

    List<AgaceOrden> obtenerOrdenesFirmadas(BigDecimal idEstatus, BigDecimal idEmpleado, BigDecimal idMetodo);

    void updateNumOrdenAndNumFolio(String numOrden, String folioOficio, BigDecimal idPropuesta, final int idEstatus);

    void integraExpediente(final AgaceOrden orden, final Date fechaExpediente);

    void reactivaPlazoREE(final AgaceOrden orden);

    void reactivaPlazoAcuerdoConclusivo(final AgaceOrden orden);

    List<AgaceOrden> findWhereIdPropuestaEquals(final BigDecimal idPropuesta);

    void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont, final Date fechaSurteEfectos, int idEstatus);

    long getCantidadMaximaOrdenesNotificadas();

    List<AgaceOrden> getOrdenesNotificadasPaginado(int limiteInicial, int limiteFinal, final List<Integer> idsOrdenesQuitar);

    void updateDatosDeNyv(AgaceOrden dto);

    List<AgaceOrden> getOrdenesAsociado(final String rfcAsociado, BigDecimal idTipoAsociado, String rfcContribuyente);

    void updateOrdenIdNyV(AgaceOrden orden, FecetDetalleNyV nYvResult);

    List<AgaceOrden> getOrdenesContribuyenteCriterio(final String numeroOrden, final String idRegistroPropuesta, final String rfcContribuyente,
            final String idEmpleado, final Integer idArace);

    List<AgaceOrden> getOrdenesAsociadoConsulta(final String rfcAsociado, BigDecimal idTipoAsociado, String rfcContribuyente);

    RespuestaOrdenAcuerdoConclusivoDTO consultaOrdenAcuerdosConclusivos(final String numeroOrden);

    List<AgaceOrden> consultaOrdenes(FiltroOrdenes filtroDao);

    Integer countOrdenesXMetodo(TipoMetodoEnum metodo, List<AraceDTO> lstUnidAdministrativas, BigDecimal idEmpleado, BigDecimal idTipoEmpleado,
            Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatusValidos);

    BigDecimal obtenerIdAraceFromPropuesta(BigDecimal idOrden);

    void updateAuditorFrimante(BigDecimal idPropuesta, BigDecimal idAuditor, BigDecimal idFirmante);

    List<AgaceOrden> obtenerOrden(BigDecimal idOrden);

    AgaceOrden findByNumeroOrden(String numeroOrden);

    String obtenerNumeroReferenciaOrden(Long idOrden);

    void updateNumeroReferenciaOrden(String noReferencia, Long idOrden);

}
