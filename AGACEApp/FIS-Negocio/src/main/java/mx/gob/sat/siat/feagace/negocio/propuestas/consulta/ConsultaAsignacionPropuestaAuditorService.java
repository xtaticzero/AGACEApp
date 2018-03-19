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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;

public interface ConsultaAsignacionPropuestaAuditorService {

    List<FececMetodo> traeDescripcionMetodo(final BigDecimal idMetodo);

    List<FececFirmante> getAraceFirmante(final String rfcFirmante);

    List<FececCausaProgramacion> traeCausaProgramacion(final BigDecimal idCausaProgramacion);

    List<FececTipoPropuesta> traeTipoPropuesta(final BigDecimal idTipoPropuesta);

    List<FecetImpuestoDescripcion> traeImpuestosDescripcion(final BigDecimal idPropuesta);

    List<FecetContadorPropuestasRechazados> getContadorRechazo(final BigDecimal idPropuesta);

    List<FecetDocExpediente> getDocumentosRechazados(final BigDecimal idPropuesta);

    List<FececRevision> traeTipoRevision(final BigDecimal idTipoRevision);

    List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarAuditor(final String rfcFirmante);

    List<FececFirmante> getidAraceFirmante(final String rfcFirmante);

    List<FecetDocExpediente> traeExpedientesCargados(final BigDecimal idPropuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ASIGNAR_PROPUESTA_AUDITOR_FIRMANTE)
    BigDecimal guardarAsignacionAuditor(final FecetPropuesta propuesta, EmpleadoDTO empleadoFirmante);

    void guardarAceptarRechazarRechazo(final FecetPropuesta propuesta);

    List<ContadorInsumosSubAdmin> getContadorPropuestas(final List<EmpleadoDTO> listaAuditores, final BigDecimal idArace);

    List<ContadorInsumosSubAdmin> getContadorPropuesta();

    void enviarCorreoConfirmacion(PropuestaOrigenCentralRegDTO fecetPropuesta, String rfcAuditor);

    EmpleadoDTO traeDatosFirmante(String rfc);

}
