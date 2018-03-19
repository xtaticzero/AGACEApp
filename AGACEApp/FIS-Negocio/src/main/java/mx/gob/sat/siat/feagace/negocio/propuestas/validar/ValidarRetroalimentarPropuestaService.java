package mx.gob.sat.siat.feagace.negocio.propuestas.validar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

public interface ValidarRetroalimentarPropuestaService {

    
    BigDecimal marcarPropuestaValida(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_PROPUESTA_POR_VALIDAR)
    BigDecimal marcarAprobarPropuestaValida(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_PROPUESTA_PENDIENTE)
    BigDecimal marcarAprobarPropuestaPendiente(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException;
        
    @PistaAuditoria(idOperacion = ConstantesAuditoria.RECHAZAR_PROPUESTA_POR_VALIDAR)
    BigDecimal marcarPropuestaRechazada(FecetPropuesta fecetPropuesta, List<FecetRechazoPropuesta> lstArchivosRechazoPropuesta)
            throws ValidarRetroalimentarPropuestaException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_PROPUESTA_PENDIENTE)
    BigDecimal marcarPropuestaPendienteRechazada(FecetPropuesta fecetPropuesta, List<FecetRechazoPropuesta> lstArchivosRechazoPropuesta)
            throws ValidarRetroalimentarPropuestaException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.PENDIENTE_PROPUESTA_POR_VALIDAR)
    BigDecimal marcarPropuestaPendiente(FecetPropuesta fecetPropuesta, List<FecetPropPendiente> list, FecetPropPendiente pendiente)
            throws ValidarRetroalimentarPropuestaException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ACEPTAR_PROPUESTA_POR_RETROALIMENTAR)
    BigDecimal marcarPropuestaRetroalimentada(FecetPropuesta fecetPropuesta, FecetRetroalimentacion retroalimentacion,
            List<DocRetroalimentacionDTO> listaDocsRetro) throws ValidarRetroalimentarPropuestaException;

    void descartarImpuesto(final FecetImpuesto fecetImpuesto);

    void actualizarContribuyente(final FecetContribuyente contribuyente);

    Map<String, FecetContribuyente> verificaDatosContribuyente(FecetContribuyente contribuyente) throws ValidarRetroalimentarPropuestaException;

    boolean esProcesable(FecetPropuesta propuesta) throws AgacePropuestasRulesException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.SELECCIONAR_PROPUESTA_POR_RETROALIMENTAR)
    BigDecimal esProcesableRetro(FecetPropuesta propuesta) throws AgacePropuestasRulesException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.SELECCIONAR_PROPUESTA_PENDIENTE)
    BigDecimal esProcesablePendiente(FecetPropuesta propuesta) throws AgacePropuestasRulesException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CAMBIO_METODO)
    BigDecimal esProcesableCambioMetodo(FecetPropuesta propuesta) throws AgacePropuestasRulesException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.SELECCIONAR_PROPUESTA_POR_VALIDAR)
    BigDecimal esProcesableFecha(FecetPropuesta propuesta) throws AgacePropuestasRulesException;

    List<FecetPropuesta> buscaPropuestasPorValidar(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException;

    List<FecetPropuesta> buscaPropuestasMarcadasPendientes(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException;

    List<FecetPropuesta> buscaPropuestasConCambioDeMetodo(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException;

    List<FecetPropuesta> buscaPropuestasRetroalimentacion(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException;

    List<FecetImpuesto> getImpuestosPropuesta(final BigDecimal idPropuesta);

    List<FececTipoImpuesto> getTiposImpuesto();

    List<FecetRetroalimentacion> getLstHistoricoRetroalimentacionPropuesta(final BigDecimal idPropuesta);

    List<FecetRetroalimentacion> getDetalleDocRetroalimentacionByIdRetro(final BigDecimal idRetroalimentacion);

    
    List<FecetRechazoPropuesta> getLstHistoricoRechazoPropuesta(final BigDecimal idPropuesta);

    List<FecetRechazoPropuesta> getDetalleDocRechazoByIdRechazo(final BigDecimal idRechazo);

    EmpleadoDTO getEmpleadoProgramadorActivo(String rfcEmpleado) throws NoExisteEmpleadoException;

    List<AraceDTO> getCatalogoUnidad();

    List<AraceDTO> getCatalogoUnidadPropuestas();

    List<FececSubprograma> getCatalogoSubprograma();

    List<FececMetodo> getCatalogoMetodo();

    List<FececTipoPropuesta> getCatalogoTipoPropuesta();

    List<FececCausaProgramacion> getCatalogoCausaProgramacion();

    List<FececSector> getCatalogoSector();

    List<FececRevision> getCatalogoRevision();

    List<FececTipoImpuesto> getCatalogoImpuesto();

    List<String> getListaCorreosDeNotificacionProgramador(BigDecimal idArace);

    List<FecetDocExpediente> getExpedientePropuesta(final BigDecimal idPropuesta);

    List<FececPrioridad> getCatalogoPrioridad();

    List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto);

    List<FecetRetroalimentacion> getLstHistorialRetroalimentacionPropuesta(final BigDecimal idPropuesta);

    List<FecetRetroalimentacion> getLstOrigenRetroalimentacionPropuesta(final BigDecimal idRetroalimentacion);

    List<FecetImpuesto> getImpuestosRetroPropuesta(final BigDecimal idRetro);

    FecetPropPendiente obtieneArchivoPendientePorValidar(BigDecimal idPropuesta);
}
