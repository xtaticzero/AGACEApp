/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.consulta;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultarPropuestasAsignadasService {

    List<FececMetodo> traeDescripcionMetodo(final BigDecimal idMetodo);

    List<FececCausaProgramacion> traeCausaProgramacion(final BigDecimal idCausaProgramacion);

    List<FececTipoPropuesta> traeTipoPropuesta(final BigDecimal idTipoPropuesta);

    List<FececRevision> traeTipoRevision(final BigDecimal idTipoRevision);

    List<FecetImpuestoDescripcion> traeImpuestosDescripcion(final BigDecimal idPropuesta);

    List<FecetPropuesta> traePropuestasPorAnalizar(final String rfcAuditor, List<String> parametros,
            List<AraceDTO> araces);

    List<FecetDocExpediente> traeExpedientesCargados(final BigDecimal idPropuesta);

    List<FececMotivo> traeMotivosRechazo();

    List<FececMotivo> traeMotivosRetroalimentacion();

    List<FececMotivo> findMotivosCancelacion();

    void guardarAsignacionFirmante(final FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.GENERAR_REVISION)
    BigDecimal cambiarEstatusPropuestaRechazar(final FecetPropuesta propuesta, String docActualizar,
            List<FecetOficio> lstOficios, BigDecimal estatusEntrante, EmpleadoDTO auditorFirmado)
            throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_PROPUESTA_ANALIZAR_AUDITOR)
    BigDecimal guardarPropuestaRechazada(FecetPropuesta propuesta, final FecetRechazoPropuesta propuestaRechazada,
            List<FecetRechazoPropuesta> documentosRechazo, BigDecimal estatusEntrante) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.TRANSFERIR_PROPUESTA_ANALIZAR_AUDITOR)
    BigDecimal guardarTransferirPropuesta(FecetPropuesta propuesta, final FecetTransferencia transferirPropuesta,
            List<FecetTransferencia> documentosTransferir, BigDecimal estatusEntrante) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.RETROALIMENTAR_PROPUESTA_ANALIZAR_AUDITOR)
    BigDecimal guardarRetroalimentarPropuesta(FecetPropuesta propuesta,
            final FecetRetroalimentacion retroalimentarPropuesta, List<FecetRetroalimentacion> documentosRetroalimentar,
            BigDecimal estatusEntrante) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CANCELAR_PROPUESTA_ANALIZAR_AUDITOR)
    BigDecimal guardarCancelacionPropuesta(FecetPropuesta propuesta, final FecetPropCancelada cancelarPropuesta,
            List<FecetPropCancelada> documentosCancelacion, BigDecimal estatusEntrante) throws NegocioException;

    List<FecetDocOrden> getDocumentosOrdenByIdPropuesta(final BigDecimal idPropuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ACTUALIZAR_PROPUESTA_ANALIZAR_AUDITOR)
    BigDecimal insertarDocumentoOrden(FecetPropuesta propuesta, List<FecetDocOrden> docOrdenActualizado,
            List<FecetOficio> oficiosActualiados, List<ColaboradorVO> colaboradores, boolean muestraAgenteAduanal) throws NegocioException;

    List<FececUnidadAdministrativa> findUnidadesByAuditor(String condicion);

    void insertarAsociadoFuncionario(AsociadoFuncionarioDTO dto, FecetPropuesta propuesta);

    void actualizarAsociadoFuncionario(AsociadoFuncionarioDTO dto);

    List<AsociadoFuncionarioDTO> buscarAsociadoFuncionarioActivoByPropuesta(AsociadoFuncionarioDTO dto);

    List<FecetTipoOficio> getOficiosAdministrables(List<FecetOficio> listaOficios);

    List<AgaceOrden> getOrdenByIdPropuesta(final BigDecimal idPropuesta);

    List<FecetOficio> getOficiosByIdOrden(final BigDecimal idOrden);

    void guardarPapelTrabajo(DocumentoOrdenModel papelTrabajo);

    boolean metodoPermiteOficio(BigDecimal idMetodo);

    EmpleadoDTO obtenerDatosAuditor(String rfcAuditor);

    List<AraceDTO> getAraces();

    EmpleadoDTO obtenerEmpleadoXId(BigDecimal idEmpleado);

    List<EmpleadoDTO> obtenerJefeEnlace(BigDecimal tipoEmpleado, BigDecimal idArace);

    EmpleadoDTO obtenerEmpleadoFirmado(String rfcSesion);

    FecetAsociado traeAgenteAduanal(BigDecimal idOrden);
}
