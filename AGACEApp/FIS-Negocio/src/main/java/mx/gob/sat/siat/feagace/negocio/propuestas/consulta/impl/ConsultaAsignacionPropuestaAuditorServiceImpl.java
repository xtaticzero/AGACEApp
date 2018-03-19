/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.consulta.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececFirmanteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FeceaPropuestaAccion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultaAsignacionPropuestaAuditorService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("consultaAsignacionPropuestaAuditorService")
public class ConsultaAsignacionPropuestaAuditorServiceImpl extends BaseBusinessServices implements ConsultaAsignacionPropuestaAuditorService {

    private static final long serialVersionUID = -472495744043920187L;

    private static final BigDecimal AVISO_PROPUESTA_ASIGNADA = BigDecimal.valueOf(34);

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececFirmanteDao fececFirmanteDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;
    @Autowired
    private transient FecetDocRechazoPropuestaDao fecetDocRechazoPropuesta;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FeceaPropuestaAccionDao propuestaAccionDao;
    @Autowired
    private transient FecebAccionPropuestaDao accionPropuestaDao;

    /**
     * Metodo traeDescripcionMetodo
     *
     * @param idMetodo
     * @return
     */
    @Override
    public List<FececMetodo> traeDescripcionMetodo(final BigDecimal idMetodo) {
        return feceaMetodoDao.findWhereIdMetodo(idMetodo);
    }

    /**
     * Metodo traeDescripcionMetodo
     *
     * @param rfcFirmante
     * @return
     */
    @Override
    public List<FececFirmante> getAraceFirmante(final String rfcFirmante) {
        return fececFirmanteDao.findWhereRfcEquals(rfcFirmante);
    }

    /**
     * Metodo traeCausaProgramacion
     *
     * @param idCausaProgramacion
     * @return
     */
    @Override
    public List<FececCausaProgramacion> traeCausaProgramacion(final BigDecimal idCausaProgramacion) {
        return fececCausaProgramacionDao.findWhereIdCausaProgramacionEquals(idCausaProgramacion);
    }

    /**
     * Metodo traeTipoPropuesta
     *
     * @param idTipoPropuesta
     * @return
     */
    @Override
    public List<FececTipoPropuesta> traeTipoPropuesta(final BigDecimal idTipoPropuesta) {
        return fececTipoPropuestaDao.findWhereIdTipoPropuestaEquals(idTipoPropuesta);
    }

    /**
     * Metodo traeImpuestosDescripcion
     *
     * @param idPropuesta
     * @return
     */
    @Override
    public List<FecetImpuestoDescripcion> traeImpuestosDescripcion(final BigDecimal idPropuesta) {
        return fecetImpuestoDao.traeImpuestoDescripcion(idPropuesta);
    }

    @Override
    public List<FecetContadorPropuestasRechazados> getContadorRechazo(final BigDecimal idPropuesta) {
        return fecetRechazoPropuestaDao.getContadorRechazo(idPropuesta);
    }

    @Override
    public List<FecetDocExpediente> getDocumentosRechazados(final BigDecimal idPropuesta) {
        return fecetDocRechazoPropuesta.findWhereIdRechazoPropuestaEquals(idPropuesta);
    }

    /**
     * Metodo traeTipoRevision
     *
     * @param idTipoRevision
     * @return
     */
    @Override
    public List<FececRevision> traeTipoRevision(final BigDecimal idTipoRevision) {
        return fececRevisionDao.findWhereIdRevisionEquals(idTipoRevision);
    }

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarAuditor(String rfcFirmante) {

        return fecetPropuestaDao.getPropuestasAsignarAuditor(rfcFirmante);

    }

    @Override
    public List<FecetDocExpediente> traeExpedientesCargados(final BigDecimal idPropuesta) {
        return fecetDocExpedienteDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FececFirmante> getidAraceFirmante(final String rfcFirmante) {
        return fececFirmanteDao.findWhereRfcEquals(rfcFirmante);
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarAsignacionAuditor(FecetPropuesta propuesta, EmpleadoDTO empleadoFirmante) {

        List<FeceaPropuestaAccion> accionExistente = propuestaAccionDao.getAccionExistente(propuesta.getIdPropuesta());

        if (accionExistente == null || accionExistente.isEmpty()) {
            FeceaPropuestaAccion propuestaAccion = new FeceaPropuestaAccion();
            propuestaAccion.setIdPropuesta(propuesta.getIdPropuesta());
            propuestaAccion.setIdAccion(AccionesFuncionarioEnum.ASIGNADAS_POR_FIRMANTE.getIdAccion());
            propuestaAccionDao.insert(propuestaAccion);

        }

        FecebAccionPropuesta accionPropuesta = new FecebAccionPropuesta();
        accionPropuesta.setIdPropuesta(propuesta.getIdPropuesta());
        accionPropuesta.setIdAccion(AccionesFuncionarioEnum.ASIGNADAS_POR_FIRMANTE.getIdAccion());
        accionPropuesta.setFechaHora(new Date());
        accionPropuesta.setIdEmpleado(empleadoFirmante.getIdEmpleado());
        accionPropuestaDao.insert(accionPropuesta);

        fecetPropuestaDao.actualizarPropuestaAuditor(propuesta);
        return propuesta.getIdPropuesta();
    }

    @Override
    public void guardarAceptarRechazarRechazo(final FecetPropuesta propuesta) {
        fecetPropuestaDao.cambiarEstatusPropuesta(propuesta);
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuestas(List<EmpleadoDTO> list, BigDecimal idArace) {

        List<ContadorInsumosSubAdmin> cont = new ArrayList<ContadorInsumosSubAdmin>();
        ContadorInsumosSubAdmin contador;
        for (EmpleadoDTO empleado : list) {
            contador = fecetPropuestaDao.getContadorPropuestasAuditor(empleado.getRfc(), idArace).get(0);
            contador.setNombre(empleado.getNombreCompleto());
            cont.add(contador);
        }
        return cont;

    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuesta() {
        return fecetPropuestaDao.getContadorPropuestasSubAdministradores();
    }

    @Override
    public void enviarCorreoConfirmacion(PropuestaOrigenCentralRegDTO fecetPropuesta, String rfcAuditor) {
        FecetPropuesta propuesta = fecetPropuesta.getPropuesta();
        Set<String> destinatarios = new TreeSet<String>();
        destinatarios.add(obtenerToCorreo(rfcAuditor));
        destinatarios.add(obtenerToCorreo(propuesta.getRfcCreacion()));
        destinatarios.add(obtenerToCorreo(propuesta.getRfcFirmante()));
        notificacionService.obtenerCorreoCentralAcppce(propuesta.getRfcFirmante(), TipoEmpleadoEnum.FIRMANTE.getId(), propuesta.getIdArace(), destinatarios,
                ClvSubModulosAgace.PROPUESTAS);

        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
        enviarCorreoPropuestas(destinatarios, fecetPropuesta.getPropuesta().getIdRegistro(), ReportType.AVISOS_PROPUESTA_GENERICO);
    }

    private void enviarCorreoPropuestas(Set<String> destinatarios, String idRegistro, ReportType pantalla) {
        Map<String, String> data = notificacionService.obtenerDatosCorreo(AVISO_PROPUESTA_ASIGNADA);
        data.put(Common.ID_REGISTRO.toString(), idRegistro);
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), idRegistro);
        try {
            notificacionService.enviarNotificacionGenerico(data, pantalla, destinatarios);
        } catch (Exception e) {
            logger.error("Error al cargar plantilla para enviar correo a auditor: {}", e);
        }
    }

    private String obtenerToCorreo(String rfc) {
        try {
            return empleadoService.getEmpleadoCompleto(rfc).getCorreo();
        } catch (Exception e) {
            logger.error("Error al obtener el correo del auditor {}: {}", rfc, e.getMessage());
        }
        return "";
    }

    @Override
    public EmpleadoDTO traeDatosFirmante(String rfc) {
        EmpleadoDTO empleado = null;

        try {

            if (empleadoService.validarExistenciaTipoEmpleado(empleadoService.getEmpleadoCompleto(rfc), TipoEmpleadoEnum.FIRMANTE)) {
                empleado = empleadoService.getEmpleadoCompleto(rfc);
            } else {
                throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
            }
        } catch (NegocioException e) {
            logger.error("[{}]", e);
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
        }

        return empleado;
    }

}
