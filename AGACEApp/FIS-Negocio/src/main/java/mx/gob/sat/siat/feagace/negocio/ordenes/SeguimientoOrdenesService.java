package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SeguimientoOrdenesTipoGuardadoEnum;

public interface SeguimientoOrdenesService extends SeguimientoOrden {

    void setListaOficiosEnProcesoVencidos(final List<FecetOficio> listaOficiosEnProcesoVencidos);

    void guardarConclusionUCAMCA(final AgaceOrden orden, final List<FecetOficio> listaOfConclusionUCAMCA,
            final List<FecetOficioAnexos> listaAnexosConclusionUCAMCA) throws NegocioException;

    void guardar2aCartaInvitacion(final AgaceOrden orden, final List<FecetOficio> listaOf2aCartaInvitacion,
            final List<FecetOficioAnexos> listaAnexos2aCartaInvitacion) throws NegocioException;

    void guardar2aCartaInvitacionMasiva(final AgaceOrden orden, final List<FecetOficio> listaOf2aCartaInvitacion,
            final List<FecetOficioAnexos> listaAnexos2aCartaInvitacion) throws NegocioException;

    @PistaAuditoria(idOperacion=ConstantesAuditoria.SOLICITUD_OFICIO_ORDEN)
    BigDecimal guardar(SeguimientoOrdenesTipoGuardadoEnum tipoGuardado, ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException;

    List<FecetOficio> getOficiosFirmados(final BigDecimal idOrden);

    Date integrarExpediente(final AgaceOrden orden);

    void reactivaPlazoOficio(final BigDecimal idOrden, final BigDecimal idOficio);

    void reactivarPlazoAcuerdoConclusivo(final AgaceOrden orden);

    void guardarAvisoContribuyente(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException;

    FecetPropuesta getDetallePropuestaOrden(final String idRegistro);

    void guardarFlujoProrrogaOrdenAprobadaAuditor(final FecetProrrogaOrden prorrogaOrden, String justificacion,
            final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException;

    void guardarFlujoProrrogaOrdenRechazadaAuditor(final FecetProrrogaOrden prorrogaOrden, String justificacionRechazo,
            final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException;

    void guardarFlujoProrrogaOficioRechazadaAuditor(final FecetProrrogaOficio prorrogaOficio, FecetFlujoProrrogaOficio flujoProrroga,
            final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException;

    List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficioHistorico(final BigDecimal idFlujoProrrogaOficio);

    void guardarFlujoProrrogaOficioAprobadaAuditor(final FecetProrrogaOficio prorrogaOficio, FecetFlujoProrrogaOficio flujoProrroga,
            final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException;

    List<FecetProrrogaOficio> getHistoricoProrrogaOficio(final BigDecimal idOficio);

    void guardarCambioMetodoUCAMCA(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO, FecetCambioMetodo fecetCambioMetodo) throws NegocioException;

    void actuallizarResolucion(final FecetProrrogaOrden prorrogaOrden, final List<FecetAnexosProrrogaOrden> listaResolucion) throws NegocioException;

    void actuallizarResolucionOficio(final FecetProrrogaOficio prorrogaOrden, final List<FecetAnexosProrrogaOficio> listaResolucion) throws NegocioException;

    boolean getProrrogasPendientesAprobacionPorOficio(List<FecetOficio> listaOficios);

    void enviarCorreoProrrogaAprobada(AgaceOrden orden, FecetProrrogaOrden prorroga);

    void enviarCorreoProrrogaAprobada(AgaceOrden orden, FecetProrrogaOficio prorroga);

    void enviarCorreoProrrogaRechazada(AgaceOrden orden, FecetProrrogaOrden prorroga);

    void enviarCorreoProrrogaRechazada(AgaceOrden orden, FecetProrrogaOficio prorroga);

    FecetDocOrden obtenerExpedienteOrden(AgaceOrden orden);

    BigDecimal obtenerIdOficioPadre();

    @PistaAuditoria(idOperacion=ConstantesAuditoria.SOLICITUD_OFICIO_ORDEN)
    BigDecimal guardarMedidaApremio(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException;

    void guardarFlujoPruebasPericialesAprobadaAuditor(final FecetPruebasPericiales pruebaPericial, String justificacion,
            final List<FecetAnexoPruebasPericiales> listaAnexos) throws NegocioException;

    void actuallizarResolucionPruebasPericiales(final FecetPruebasPericiales pruebaPercial, final List<FecetAnexoPruebasPericiales> listaResolucion)
            throws NegocioException;

    void guardarFlujoPruebasPericialesRechazadaAuditor(final FecetPruebasPericiales pruebasPericiales, String justificacion,
            final List<FecetAnexoPruebasPericiales> listaAnexos) throws NegocioException;

    FecetOficio obtenerOficioById(final FecetOficio oficio);

    FecetPromocionAgenteAduanalPk guardaDocumentoPromocionAgenteAduanal(FecetPromocionAgenteAduanal promocion);

    void guardaDocumentoAlegatoAgenteAduanal(FecetAlegatoAgenteAduanal alegato);

    boolean validaMetodoProrrogaOficio(AgaceOrden orden, FecetOficio oficio);
    
    void enviarCorreoPruebaPericialAprobada(AgaceOrden orden, FecetPruebasPericiales pruebaPericial);
    
    void enviarCorreoPruebaPericialRechazada(AgaceOrden orden, FecetPruebasPericiales pruebaPericial);

}
