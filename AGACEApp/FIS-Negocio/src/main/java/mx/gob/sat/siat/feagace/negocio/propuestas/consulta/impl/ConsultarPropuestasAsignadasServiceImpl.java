/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.consulta.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececUnidadAdministrativaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececMotivoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetTipoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsociadoFuncionarioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCancelacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FeceaPropuestaAccion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("consultarPropuestasAsignadasService")
public class ConsultarPropuestasAsignadasServiceImpl extends PropuestasServiceAbstract
        implements ConsultarPropuestasAsignadasService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FececMotivoDao fececMotivoDao;
    @Autowired
    private transient FecetRetroalimentacionDao fecetRetroalimentacionDao;
    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;
    @Autowired
    private transient FecetTransferenciaDao fecetTransferenciaDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetDocOrdenDao fecetOrdenDao;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;
    @Autowired
    private CargaDocumentoElectronicoService cargaDocumento;
    @Autowired
    private transient FececUnidadAdministrativaDao fececUnidadAdministrativaDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FecetCancelacionDao fecetCancelacionDao;
    @Autowired
    private transient AsociadoFuncionarioDao asociadoFuncionarioDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetTipoOficioDao fecetTipoOficioDao;

    @Override
    public List<FececMetodo> traeDescripcionMetodo(final BigDecimal idMetodo) {
        return feceaMetodoDao.findWhereIdMetodo(idMetodo);
    }

    @Override
    public List<FececCausaProgramacion> traeCausaProgramacion(final BigDecimal idCausaProgramacion) {
        return fececCausaProgramacionDao.findWhereIdCausaProgramacionEquals(idCausaProgramacion);
    }

    @Override
    public List<FececTipoPropuesta> traeTipoPropuesta(final BigDecimal idTipoPropuesta) {
        return fececTipoPropuestaDao.findWhereIdTipoPropuestaEquals(idTipoPropuesta);
    }

    @Override
    public List<FececRevision> traeTipoRevision(final BigDecimal idTipoRevision) {
        return fececRevisionDao.findWhereIdRevisionEquals(idTipoRevision);
    }

    @Override
    public List<FecetImpuestoDescripcion> traeImpuestosDescripcion(final BigDecimal idPropuesta) {
        return fecetImpuestoDao.traeImpuestoDescripcion(idPropuesta);
    }

    @Override
    public List<FecetPropuesta> traePropuestasPorAnalizar(final String rfcAuditor, List<String> parametros,
            List<AraceDTO> araces) {

        List<FecetPropuesta> propuestasAsignadas = fecetPropuestaDao.traePropuestasPorAnalizar(rfcAuditor, parametros);
        EmpleadoDTO empleado = new EmpleadoDTO();
        if (propuestasAsignadas != null && !propuestasAsignadas.isEmpty()) {

            for (FecetPropuesta propuesta : propuestasAsignadas) {
                if (isNoAprobacionValida(propuesta.getIdEstatus())) {
                    BigDecimal idEmpleado = fecebAccionPropuestaDao
                            .obtenerAccionNoAprobacion(propuesta.getIdPropuesta()).get(0).getIdEmpleado();
                    try {
                        empleado = getEmpleadoCompleto(idEmpleado.intValue());
                        empleado.setNombre(empleado.getNombreCompleto());
                        propuesta.setEmpleadoDto(empleado);
                    } catch (EmpleadoServiceException e) {
                        logger.error("No se encuentra el empleado");
                    }
                } else {
                    empleado.setNombre("N/A");
                    propuesta.setEmpleadoDto(empleado);
                }

                for (AraceDTO unidadAdmin : araces) {
                    if (propuesta.getIdArace().intValue() == unidadAdmin.getIdArace().intValue()) {

                        FececArace uniAdmin = new FececArace();

                        uniAdmin.setNombre(unidadAdmin.getNombre());
                        propuesta.setFececArace(uniAdmin);
                    }
                }
            }
        }

        return propuestasAsignadas;
    }

    @Override
    public List<FecetDocExpediente> traeExpedientesCargados(final BigDecimal idPropuesta) {
        List<FecetDocExpediente> expedientesCargados;

        expedientesCargados = fecetDocExpedienteDao.findWhereIdPropuestaEquals(idPropuesta);

        return expedientesCargados;
    }

    @Override
    public List<FececMotivo> traeMotivosRechazo() {
        List<FececMotivo> motivos;

        motivos = fececMotivoDao.findWhereTipoMotivoEquals("RECHAZO");

        return motivos;
    }

    @Override
    public List<FececMotivo> traeMotivosRetroalimentacion() {
        List<FececMotivo> motivos;

        motivos = fececMotivoDao.findWhereTipoMotivoEquals("RETROALIMENTACION");

        return motivos;
    }

    @Override
    public List<FececMotivo> findMotivosCancelacion() {
        return fececMotivoDao.findWhereTipoMotivoEquals("CANCELACION");
    }

    @Override
    public void guardarAsignacionFirmante(final FecetPropuesta propuesta) {

        fecetPropuestaDao.update(new FecetPropuestaPk(propuesta.getIdPropuesta()), propuesta);

    }

    @Override
    @PistaAuditoria
    public BigDecimal cambiarEstatusPropuestaRechazar(FecetPropuesta propuesta, String docActualizar,
            List<FecetOficio> lstOficios, BigDecimal estatusEntrante, EmpleadoDTO auditorFirmado) {

        try {
            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            fecetPropuestaDao.cambiarEstatusPropuestaRechazar(propuesta);
            FecetDocOrden docOrden = fecetOrdenDao.findByIdPropuesta(propuesta.getIdPropuesta()).get(0);

            if (docActualizar.equals("1")) {
                fecetOrdenDao.updateActivoDocumento(docOrden.getIdDocOrden());
                for (FecetOficio oficio : lstOficios) {
                    fecetOficioDao.updateOficioByIdOficio(oficio.getIdOficio());
                }
            }
            if (docActualizar.equals("2")) {
                fecetOrdenDao.updateActivoDocumento(docOrden.getIdDocOrden());
            }
            if (docActualizar.equals("3")) {
                for (FecetOficio oficio : lstOficios) {
                    fecetOficioDao.updateOficioByIdOficio(oficio.getIdOficio());
                }
            }

            accionHistoria.setIdPropuesta(propuesta.getIdPropuesta());
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.VALIDACION_FIRMANTE.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(determinaIdOrigen(estatusEntrante, propuesta));
            accionHistoria.setIdEmpleado(getEmpleadoCompleto(propuesta.getRfcAuditor()).getIdEmpleado());

            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), accionHistoria.getIdAccion(),
                    determinaIdOrigen(estatusEntrante, propuesta));

            AgaceOrden ordenAsociada = agaceOrdenDao.findByIdPropuesta(propuesta.getIdPropuesta());

            if (ordenAsociada != null) {

                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> jefesInmediatos = auditorFirmado
                        .getLstJefesInmediatos();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> auditores = jefesInmediatos.get(TipoEmpleadoEnum.AUDITOR);
                List<EmpleadoDTO> lstFirmantes = auditores.get(TipoEmpleadoEnum.FIRMANTE);

                for (EmpleadoDTO firmanteAsignado : lstFirmantes) {

                    if (firmanteAsignado.getRfc().equals(propuesta.getRfcFirmante())) {
                        ordenAsociada.setIdFirmante(firmanteAsignado.getIdEmpleado());
                    }

                }

                agaceOrdenDao.updateAuditorFrimante(propuesta.getIdPropuesta(), auditorFirmado.getIdEmpleado(),
                        ordenAsociada.getIdFirmante());
            }

            try {
                enviarCorreoConfirmacion(propuesta, LeyendasPropuestasEnum.ASIGNACION_DOCUMENTO_VALIDACION_FIRMANTE.getIdLeyenda());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarPropuestaRechazada(FecetPropuesta propuesta, FecetRechazoPropuesta propuestaRechazada,
            List<FecetRechazoPropuesta> documentosRechazo, BigDecimal estatusEntrante) {

        try {
            BigDecimal idRechazo = BigDecimal.ZERO;
            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            String carpetasRFC = propuesta.getFecetContribuyente().getRfc();
            String carpetaIdRegistro = propuesta.getIdRegistro();
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            propuestaRechazada.setIdRechazoPropuesta(fecetRechazoPropuestaDao.getIdFecetPropuestaRechazo());

            accionHistoria.setIdPropuesta(propuesta.getIdPropuesta());
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.RECHAZAR.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(determinaIdOrigen(estatusEntrante, propuesta));
            accionHistoria.setIdEmpleado(propuestaRechazada.getIdEmpleado());

            fecetPropuestaDao.cambiarEstatusPropuestaRechazar(propuesta);
            fecetRechazoPropuestaDao.updateEstatusRechazo(propuesta.getIdPropuesta());
            idRechazo = fecetRechazoPropuestaDao.insertarRechazoPropuesta(propuestaRechazada).getIdRechazoPropuesta();
            fecetRechazoPropuestaDao.updateEstatusDocRechazo(propuesta.getIdPropuesta());

            accionHistoria.setIdDetalleAccion(idRechazo);
            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), accionHistoria.getIdAccion(),
                    determinaIdOrigen(estatusEntrante, propuesta));

            for (FecetRechazoPropuesta fecetRechazoPropuesta : documentosRechazo) {

                propuestaRechazada.setIdDocRechazo(fecetRechazoPropuestaDao.getConsecutivoDocRechazo());
                propuestaRechazada.setNombreArchivo(fecetRechazoPropuesta.getNombreArchivo());
                propuestaRechazada.setRutaArchivo(
                        RutaArchivosUtil.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_RECHAZO_PROPUESTAS,
                                carpetasRFC, carpetaIdRegistro, carpetaUnica) + propuestaRechazada.getNombreArchivo());
                propuestaRechazada.setArchivo(fecetRechazoPropuesta.getArchivo());
                propuestaRechazada.setFechaCreacion(new Date());
                propuestaRechazada.setFechaFin(null);
                propuestaRechazada.setBlnActivo(new BigDecimal(EstadoBooleanodeRegistroEnum.ACTIVO.getValue()));

                fecetRechazoPropuestaDao.insertDoc(propuestaRechazada);
                CargaArchivoUtil.guardarArchivoPropuestaRechazada(propuestaRechazada);
            }

            try {
                enviarCorreoConfirmacion(propuesta,
                        LeyendasPropuestasEnum.SOLICITUD_VALIDACION_PROPUESTA_RECHAZADA.getIdLeyenda());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_REALIZAR_TRANSACCION + e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarTransferirPropuesta(FecetPropuesta propuesta, FecetTransferencia transferirPropuesta,
            List<FecetTransferencia> documentosTransferir, BigDecimal estatusEntrante) {

        try {
            BigDecimal idTransferencia = BigDecimal.ZERO;
            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            String carpetasRFC = propuesta.getFecetContribuyente().getRfc();
            String carpetaIdRegistro = propuesta.getIdRegistro();
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            transferirPropuesta.setIdTransferencia(fecetTransferenciaDao.getIdFecetTransferencia());

            accionHistoria.setIdPropuesta(propuesta.getIdPropuesta());
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.TRANSFERIR.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(determinaIdOrigen(estatusEntrante, propuesta));
            accionHistoria.setIdEmpleado(transferirPropuesta.getIdEmpleado());

            fecetPropuestaDao.cambiarEstatusPropuestaRechazar(propuesta);
            fecetTransferenciaDao.updateEstatusTransferencia(propuesta.getIdPropuesta());
            idTransferencia = fecetTransferenciaDao.insert(transferirPropuesta).getIdTransferencia();
            fecetTransferenciaDao.updateEstatusDocTransferencia(propuesta.getIdPropuesta());

            accionHistoria.setIdDetalleAccion(idTransferencia);
            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), accionHistoria.getIdAccion(),
                    determinaIdOrigen(estatusEntrante, propuesta));

            for (FecetTransferencia fecetTransferirPropuesta : documentosTransferir) {

                transferirPropuesta.setIdDocTransferencia(fecetTransferenciaDao.getIdDocTransferencia());
                transferirPropuesta.setNombreArchivo(fecetTransferirPropuesta.getNombreArchivo());
                transferirPropuesta.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValida(
                        RutaArchivosEnum.RUTA_DOCUMENTOS_TRANSFERIDAS_PROPUESTAS, carpetasRFC, carpetaIdRegistro,
                        carpetaUnica) + transferirPropuesta.getNombreArchivo());
                transferirPropuesta.setArchivo(fecetTransferirPropuesta.getArchivo());
                transferirPropuesta.setFechaCreacion(new Date());
                transferirPropuesta.setFechaFin(null);
                transferirPropuesta.setBlnActivo(new BigDecimal(EstadoBooleanodeRegistroEnum.ACTIVO.getValue()));

                fecetTransferenciaDao.insertDocTransferencia(transferirPropuesta);
                CargaArchivoUtil.guardarArchivoTransferirPropuesta(transferirPropuesta);
            }
            BigDecimal idLeyenda = BigDecimal.ZERO;

            if (tieneOrden(propuesta.getIdPropuesta())) {
                idLeyenda = LeyendasPropuestasEnum.VALIDACION_TRANSFERENCIA_PROPUESTA_RECHAZADA.getIdLeyenda();
            } else {
                idLeyenda = LeyendasPropuestasEnum.SOLICITUD_VALIDACION_PROPUESTA_TRANSFERIDA.getIdLeyenda();
            }

            try {
                enviarCorreoConfirmacion(propuesta, idLeyenda);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_REALIZAR_TRANSACCION + e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarRetroalimentarPropuesta(FecetPropuesta propuesta,
            FecetRetroalimentacion retroalimentarPropuesta, List<FecetRetroalimentacion> documentosRetroalimentar,
            BigDecimal estatusEntrante) {

        try {
            BigDecimal retroalimentacion = BigDecimal.ZERO;
            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            String carpetasRFC = propuesta.getFecetContribuyente().getRfc();
            String carpetaIdRegistro = propuesta.getIdRegistro();
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            retroalimentarPropuesta.setIdRetroalimentacion(fecetRetroalimentacionDao.getIdFecetRetroalimentacion());

            accionHistoria.setIdPropuesta(propuesta.getIdPropuesta());
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.RETROALIMENTAR.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(determinaIdOrigen(estatusEntrante, propuesta));
            accionHistoria.setIdEmpleado(retroalimentarPropuesta.getIdEmpleado());

            fecetPropuestaDao.cambiarEstatusPropuestaRechazar(propuesta);
            fecetRetroalimentacionDao.updateEstatusRetroalimentacion(propuesta.getIdPropuesta());
            retroalimentacion = fecetRetroalimentacionDao.insertarRetroalimentacion(retroalimentarPropuesta)
                    .getIdRetroalimentacion();
            fecetRetroalimentacionDao.updateEstatusDocRetroalimentacion(propuesta.getIdPropuesta());

            accionHistoria.setIdDetalleAccion(retroalimentacion);
            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), accionHistoria.getIdAccion(),
                    determinaIdOrigen(estatusEntrante, propuesta));

            for (FecetRetroalimentacion fecetRetroalimnetarPropuesta : documentosRetroalimentar) {

                retroalimentarPropuesta
                        .setIdDocRetroalimentacion(fecetRetroalimentacionDao.getConsecutivoDocRetroalimentar());
                retroalimentarPropuesta.setNombreArchivo(fecetRetroalimnetarPropuesta.getNombreArchivo());
                retroalimentarPropuesta.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValida(
                        RutaArchivosEnum.RUTA_DOCUMENTOS_RETROALIMENTACION_PROPUESTAS, carpetasRFC, carpetaIdRegistro,
                        carpetaUnica) + fecetRetroalimnetarPropuesta.getNombreArchivo());
                retroalimentarPropuesta.setArchivo(fecetRetroalimnetarPropuesta.getArchivo());
                retroalimentarPropuesta.setFechaFin(null);
                retroalimentarPropuesta.setBlnActivo(new BigDecimal(EstadoBooleanodeRegistroEnum.ACTIVO.getValue()));
                fecetRetroalimentacionDao.insertarDocRetroalimentacion(retroalimentarPropuesta);
                CargaArchivoUtil.guardarArchivoRetroalimentarPropuesta(retroalimentarPropuesta);
            }
            BigDecimal idLeyenda = BigDecimal.ZERO;

            if (tieneOrden(propuesta.getIdPropuesta())) {
                idLeyenda = LeyendasPropuestasEnum.SOLICITUD_RETROALIMENTACION_PROPUESTA_RECHAZADA.getIdLeyenda();
            } else {
                idLeyenda = LeyendasPropuestasEnum.SOLICITUD_VAIDACION_PROPUESTA_RETROALIMENTADA.getIdLeyenda();
            }

            try {
                enviarCorreoConfirmacion(propuesta, idLeyenda);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_REALIZAR_TRANSACCION + e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarCancelacionPropuesta(FecetPropuesta propuesta, FecetPropCancelada cancelarPropuesta,
            List<FecetPropCancelada> documentosCancelacion, BigDecimal estatusEntrante) throws NegocioException {

        try {
            FecebAccionPropuesta accionHistoria = new FecebAccionPropuesta();
            String carpetasRFC = propuesta.getFecetContribuyente().getRfc();
            String carpetaIdRegistro = propuesta.getIdRegistro();
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));

            accionHistoria.setIdPropuesta(propuesta.getIdPropuesta());
            accionHistoria.setIdDetalleAccion(null);
            accionHistoria.setIdAccion(AccionesFuncionarioEnum.CANCELAR.getIdAccion());
            accionHistoria.setFechaHora(new Date());
            accionHistoria.setIdAccionOrigen(determinaIdOrigen(estatusEntrante, propuesta));
            accionHistoria.setIdEmpleado(cancelarPropuesta.getIdEmpleado());

            fecetPropuestaDao.cambiarEstatusPropuestaRechazar(propuesta);
            fecetCancelacionDao.updateEstatusCancelacion(propuesta.getIdPropuesta());
            BigDecimal idCancelacion = fecetCancelacionDao.insert(cancelarPropuesta).getIdCancelacionPropuesta();
            fecetCancelacionDao.updateEstatusDocCancelacion(idCancelacion);

            accionHistoria.setIdDetalleAccion(idCancelacion);
            fecebAccionPropuestaDao.insert(accionHistoria);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), accionHistoria.getIdAccion(),
                    determinaIdOrigen(estatusEntrante, propuesta));

            for (FecetPropCancelada fecetPropCancelada : documentosCancelacion) {

                cancelarPropuesta.setIdDocCancelacion(fecetCancelacionDao.getConsecutivoDocCancelacion());
                cancelarPropuesta.setNombreArchivo(fecetPropCancelada.getNombreArchivo());
                cancelarPropuesta.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValida(
                        RutaArchivosEnum.RUTA_DOCUMENTOS_CANCELACION_PROPUESTAS, carpetasRFC, carpetaIdRegistro,
                        carpetaUnica) + fecetPropCancelada.getNombreArchivo());
                cancelarPropuesta.setArchivo(fecetPropCancelada.getArchivo());
                cancelarPropuesta.setFechaFin(null);
                cancelarPropuesta.setBlnActivo(new BigDecimal(EstadoBooleanodeRegistroEnum.ACTIVO.getValue()));
                fecetCancelacionDao.insertDocCancelacion(cancelarPropuesta);
                CargaArchivoUtil.guardarArchivoCancelacion(cancelarPropuesta);
            }
            BigDecimal idLeyenda = LeyendasPropuestasEnum.SOLICITUD_VALIDACION_CANCELACION_PROPUESTA.getIdLeyenda();

            try {
                enviarCorreoConfirmacion(propuesta, idLeyenda);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(ConstantesError.ERROR_REALIZAR_TRANSACCION + e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    public List<FececUnidadAdministrativa> findUnidadesByAuditor(String condicion) {
        return fececUnidadAdministrativaDao.findUnidadesByAuditor(condicion);
    }

    @Override
    public List<FecetDocOrden> getDocumentosOrdenByIdPropuesta(BigDecimal idPropuesta) {
        return fecetOrdenDao.findByIdPropuesta(idPropuesta);
    }

    @Override
    @PistaAuditoria
    public BigDecimal insertarDocumentoOrden(FecetPropuesta propuesta, List<FecetDocOrden> docOrdenActualizado,
            List<FecetOficio> oficiosActualiados, List<ColaboradorVO> colaboradores, boolean muestraAgenteAduanal) throws NegocioException {
        try {
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            FecetDocOrden documentoOrden = new FecetDocOrden();
            AgaceOrden orden = new AgaceOrden();
            String carpetaOrden = "documentoOrden";
            String rutaOrdenFinal;
            String diagonal = "/";
            List<FecetDocOrden> ordenes;

            BigDecimal idOrden = agaceOrdenDao.findWhereIdPropuestaEquals(propuesta.getIdPropuesta()).get(0)
                    .getIdOrden();

            if (docOrdenActualizado != null && !docOrdenActualizado.isEmpty()) {
                documentoOrden.setIdOrden(idOrden);
                documentoOrden.setNombreArchivo(docOrdenActualizado.get(0).getNombreArchivo());
                documentoOrden.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPropuesta(propuesta));
                rutaOrdenFinal = documentoOrden.getRutaArchivo() + carpetaOrden + diagonal + carpetaUnica + diagonal;
                documentoOrden.setRutaArchivo(rutaOrdenFinal.concat(documentoOrden.getNombreArchivo()));
                documentoOrden.setDocumentoPdf(String.valueOf(Constantes.ENTERO_CERO));
                documentoOrden.setFechaCreacion(docOrdenActualizado.get(0).getFechaCreacion());
                documentoOrden.setEstatus(String.valueOf(Constantes.ENTERO_UNO));
                documentoOrden.setArchivo(docOrdenActualizado.get(0).getArchivo());
                documentoOrden.setBlnActivo(Constantes.ENTERO_CERO);
                documentoOrden.setFechaFin(null);
                orden.setIdOrden(documentoOrden.getIdOrden());

                ordenes = fecetOrdenDao.findByFecetOrden(documentoOrden.getIdOrden());

                if (ordenes != null && !ordenes.isEmpty() && ordenes.get(0).getBlnActivo() == 1) {
                    fecetOrdenDao.updateEstatusDocFechaFin(new Date(), ordenes.get(0).getIdDocOrden());
                }

                fecetOrdenDao.insert(documentoOrden);
                cargaDocumento.cargaDocumento(rutaOrdenFinal, documentoOrden.getArchivo(),
                        documentoOrden.getNombreArchivo());
            }

            for (FecetOficio oficioNuevo : oficiosActualiados) {
                String carpetaOficio = "documentosOficio";
                String carpetaTipoOficio = "tipoOficio";
                String rutaFinalOficio = null;
                String carpetaUnicaOficio = (DateUtil
                        .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));

                oficioNuevo.setIdOrden(idOrden);
                oficioNuevo.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoPropuesta(propuesta));
                rutaFinalOficio = oficioNuevo.getRutaArchivo() + carpetaOficio + diagonal + carpetaTipoOficio
                        + oficioNuevo.getFecetTipoOficio().getIdTipoOficio() + diagonal + carpetaUnicaOficio + diagonal;
                oficioNuevo.setRutaArchivo(rutaFinalOficio.concat(oficioNuevo.getNombreArchivo()));
                oficioNuevo.setIdAdminOficio(
                        fecetOficioDao.getOficiosAsociadosByOrden(oficioNuevo.getIdOrden()).get(0).getIdAdminOficio());

                fecetOficioDao.updateOficioByIdOrden(idOrden);
                fecetOficioDao.insert(oficioNuevo);
                cargaDocumento.cargaDocumento(rutaFinalOficio, oficioNuevo.getArchivo(),
                        oficioNuevo.getNombreArchivo());
            }

            if (muestraAgenteAduanal && colaboradores != null && !colaboradores.isEmpty()) {
                // verificar si cambio el agente aduanal
                FecetAsociado agenteBD = fecetAsociadoDao.getColaboradorContribuyente(new BigDecimal(Constantes.ENTERO_CUATRO), orden.getIdOrden()).get(0);
                // si cambio poner fecha baja al anterior
                if (agenteBD != null && !agenteBD.getRfc().equals(colaboradores.get(0).getRfc())) {
                    // poner fecha baja al existente
                    fecetAsociadoDao.eliminaColaborador(orden.getIdOrden(), new BigDecimal(Constantes.ENTERO_CUATRO));
                    insertaAsociados(propuesta.getFecetContribuyente().getRfc(), colaboradores, orden);
                }
            }

        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_REALIZAR_TRANSACCION + e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
        return propuesta.getIdPropuesta();
    }

    private void insertaAsociados(String rfcContribuyente, List<ColaboradorVO> colaboradores, AgaceOrden orden) {
        for (ColaboradorVO colaborador : colaboradores) {
            if (!colaborador.getNombre().isEmpty() && !colaborador.getRfc().isEmpty()) {
                FecetAsociado nuevoAsociado = crearAsociado(rfcContribuyente, colaborador, orden);
                if (nuevoAsociado != null) {
                    fecetAsociadoDao.insert(nuevoAsociado);
                }
            }
        }
    }

    private FecetAsociado crearAsociado(String rfcContribuyente, ColaboradorVO colaborador, AgaceOrden orden) {
        FecetAsociado fecetAsociado = null;
        if (colaborador.getRfc() != null || colaborador.getNombre() != null) {
            fecetAsociado = new FecetAsociado();
            fecetAsociado.setIdOrden(orden.getIdOrden());
            fecetAsociado.setRfcContribuyente(rfcContribuyente);
            fecetAsociado.setIdTipoAsociado(new BigDecimal(Constantes.ENTERO_CUATRO));
            fecetAsociado.setNombre(colaborador.getNombre());
            fecetAsociado.setRfc(colaborador.getRfc());
            fecetAsociado.setCorreo(colaborador.getCorreo());
            fecetAsociado.setTipoAsociado(Constantes.CADENA_CERO);
            if (colaborador.isMedioContactoBoolean()) {
                fecetAsociado.setMedioContacto(Constantes.CADENA_UNO);
            } else {
                fecetAsociado.setMedioContacto(Constantes.CADENA_CERO);
            }
            fecetAsociado.setEstatus(String.valueOf(Constantes.ENTERO_UNO));
            fecetAsociado.setMedioContactoBoolean(colaborador.isMedioContactoBoolean());
        }
        return fecetAsociado;
    }

    private void enviarCorreoConfirmacion(FecetPropuesta propuesta, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), Constantes.USUARIO_AUDITOR, destinatarios,
                ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), Constantes.USUARIO_FIRMANTE,
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcCreacion(), Constantes.USUARIO_PROGRAMADOR,
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, propuesta.getIdArace(), destinatarios,
                ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoCentralAcppce(propuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getId(),
                propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());

        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(),e);
        }
    }

    private BigDecimal determinaIdOrigen(BigDecimal estatusEntrante, FecetPropuesta propuesta) {

        if (estatusEntrante.longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId()
                || estatusEntrante
                        .longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO
                                .getId()) {

            List<FeceaPropuestaAccion> accionOrigen = feceaPropuestaAccionDao
                    .getAccionByIdPropuesta(propuesta.getIdPropuesta());

            if (accionOrigen != null && !accionOrigen.isEmpty()) {
                return accionOrigen.get(0).getIdAccion();
            }
        }

        return null;
    }

    private boolean tieneOrden(BigDecimal idPropuesta) {
        List<AgaceOrden> orden = agaceOrdenDao.findWhereIdPropuestaEquals(idPropuesta);
        return orden != null && !orden.isEmpty();
    }

    @Override
    public void insertarAsociadoFuncionario(AsociadoFuncionarioDTO dto, FecetPropuesta propuesta) {
        try {
            asociadoFuncionarioDao.insertar(dto);
            Set<String> destinatarios = new TreeSet<String>();
            destinatarios.add(getEmpleadoCompleto(dto.getIdEmpleado().intValue()).getCorreo());
            notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), Constantes.USUARIO_AUDITOR,
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), Constantes.USUARIO_FIRMANTE,
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(propuesta.getRfcCreacion(), Constantes.USUARIO_PROGRAMADOR,
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, propuesta.getIdArace(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoCentralAcppce(propuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getId(),
                    propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

            Map<String, String> data = notificacionService
                    .obtenerDatosCorreo(LeyendasPropuestasEnum.PROPUESTA_ASIGNADA_CONSULTA.getIdLeyenda());
            data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
            data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());

            enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);

        } catch (EmpleadoServiceException e) {
            logger.error("El Empleado No Existe");
        }
    }

    @Override
    public void actualizarAsociadoFuncionario(AsociadoFuncionarioDTO dto) {
        asociadoFuncionarioDao.update(dto);
    }

    @Override
    public List<AsociadoFuncionarioDTO> buscarAsociadoFuncionarioActivoByPropuesta(AsociadoFuncionarioDTO dto) {
        return asociadoFuncionarioDao.obtenerAsociadoActivoByIdPropuesta(dto);
    }

    @Override
    public List<FecetTipoOficio> getOficiosAdministrables(List<FecetOficio> listaOficios) {

        List<String> lstIdOficios = new ArrayList<String>();
        List<String> lstIdOficiosActivos = new ArrayList<String>();

        for (FecetOficio oficiosXActaulizar : listaOficios) {
            if (!oficiosXActaulizar.isBlnActivo()) {
                lstIdOficios.add(oficiosXActaulizar.getFecetTipoOficio().getIdTipoOficio().toString());
            }
        }

        HashSet<String> oficiosConsultar = new HashSet<String>(lstIdOficios);
        lstIdOficios.clear();
        lstIdOficios.addAll(oficiosConsultar);

        for (FecetOficio oficiosActivos : listaOficios) {
            if (oficiosActivos.isBlnActivo()) {
                lstIdOficiosActivos.add(oficiosActivos.getFecetTipoOficio().getIdTipoOficio().toString());
            }
        }

        String condicionBusqueda = armaCondicionOficios(lstIdOficios, lstIdOficiosActivos);
        List<FecetTipoOficio> oficiosAdmin = null;

        if (!condicionBusqueda.equals("")) {
            oficiosAdmin = fecetTipoOficioDao.getOficiosAdminXActualizar(condicionBusqueda);
        }

        return oficiosAdmin;
    }

    @Override
    public void guardarPapelTrabajo(DocumentoOrdenModel papelTrabajo) {
        try {
            CargaArchivoUtil.guardarArchivoPapelesTrabajoPropuesta(papelTrabajo);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<AgaceOrden> getOrdenByIdPropuesta(BigDecimal idPropuesta) {

        return agaceOrdenDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FecetOficio> getOficiosByIdOrden(BigDecimal idOrden) {

        return fecetOficioDao.getOficiosAsociadosByOrden(idOrden);
    }

    private boolean isNoAprobacionValida(BigDecimal idEstatus) {

        boolean isNoAprobada = false;

        if (idEstatus.longValue() == TipoEstatusEnum.NO_APROBADA_LA_RETROALIMENTACION.getId()) {
            isNoAprobada = true;
        } else if (idEstatus.longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId()) {
            isNoAprobada = true;
        } else if (idEstatus
                .longValue() == TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO
                        .getId()) {
            isNoAprobada = true;
        } else if (idEstatus.longValue() == TipoEstatusEnum.NO_APROBADA_LA_CANCELACION.getId()) {
            isNoAprobada = true;
        } else if (idEstatus.longValue() == TipoEstatusEnum.FIRMANTE_TRANSFERENCIA_NO_APROBADA.getId()) {
            isNoAprobada = true;
        } else if (idEstatus.longValue() == TipoEstatusEnum.PROPUESTA_NO_APROBADO_EL_RECHAZO.getId()) {
            isNoAprobada = true;
        } else {
            isNoAprobada = false;
        }

        return isNoAprobada;
    }

    private String armaCondicionOficios(List<String> lstIdOficios, List<String> lstIdOficiosActivos) {

        for (int i = 0; i < lstIdOficiosActivos.size(); i++) {
            for (int j = 0; j < lstIdOficios.size(); j++) {

                if (lstIdOficios.get(j).equals(lstIdOficiosActivos.get(i))) {
                    lstIdOficios.remove(j);
                }
            }
        }

        StringBuilder condicionBusqueda = new StringBuilder();

        for (int i = 0; i < lstIdOficios.size(); i++) {
            if (i == lstIdOficios.size() - 1) {
                condicionBusqueda.append(lstIdOficios.get(i));
            } else {
                condicionBusqueda.append(lstIdOficios.get(i));
                condicionBusqueda.append(",");
            }
        }

        return condicionBusqueda.toString();
    }

    @Override
    public boolean metodoPermiteOficio(BigDecimal idMetodo) {

        String metodoOficioActivo = "1,0";
        String tipoOficioActivo = "1,0";

        List<FecetTipoOficio> oficiosXMetodo = fecetTipoOficioDao.getOficiosAdminByMetodo(idMetodo, metodoOficioActivo,
                tipoOficioActivo);

        if (oficiosXMetodo != null && !oficiosXMetodo.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public EmpleadoDTO obtenerDatosAuditor(String rfcSesion) {

        EmpleadoDTO empleadoFirmado = new EmpleadoDTO();

        try {

            empleadoFirmado = getEmpleadoCompleto(rfcSesion);

            if (!validarExistenciaTipoEmpleado(empleadoFirmado, TipoEmpleadoEnum.AUDITOR)) {
                empleadoFirmado = null;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            empleadoFirmado = null;
        }

        return empleadoFirmado;
    }

    @Override
    public List<AraceDTO> getAraces() {

        List<AraceDTO> unidadesAdmin = new ArrayList<AraceDTO>();
        try {
            List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

            for (AraceDTO unidad : unidadesAdmin) {
                if (unidad.getIdArace().equals(Constantes.ACPPCE.intValue())
                        || unidad.getIdArace().equals(Constantes.ACIACE.intValue())
                        || unidad.getIdArace().equals(Constantes.PPCE.intValue())) {

                    unidadAdminNoAplicable.add(unidad);
                }
            }

            unidadesAdmin.removeAll(unidadAdminNoAplicable);
        } catch (Exception e) {
            logger.error("No se pudieron consultar las unidades Administrativas");
        }
        return unidadesAdmin;
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoXId(BigDecimal idEmpleado) {

        EmpleadoDTO empleado = new EmpleadoDTO();

        try {
            empleado = getEmpleadoCompleto(idEmpleado.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("No se enontro el Empleado");
        }

        return empleado;
    }

    @Override
    public List<EmpleadoDTO> obtenerJefeEnlace(BigDecimal tipoEmpleado, BigDecimal idArace) {

        List<EmpleadoDTO> jefeDepartamento = new ArrayList<EmpleadoDTO>();

        try {
            jefeDepartamento = getEmpleadosXUnidadAdmva(idArace.intValue(), tipoEmpleado.intValue(),
                    ClvSubModulosAgace.PROPUESTAS);

            for (EmpleadoDTO empleado : jefeDepartamento) {
                empleado.setNombre(empleado.getNombreCompleto());
            }

        } catch (EmpleadoServiceException e) {
            logger.error("No se encontro el Empleado.");
        }

        return jefeDepartamento;
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoFirmado(String rfcSesion) {
        EmpleadoDTO empleadoFirmado = new EmpleadoDTO();

        try {

            empleadoFirmado = getEmpleadoCompleto(rfcSesion);

            if (!validarExistenciaTipoEmpleado(empleadoFirmado, TipoEmpleadoEnum.AUDITOR)
                    && !validarExistenciaTipoEmpleado(empleadoFirmado, TipoEmpleadoEnum.JEFE_DE_DEPARTAMENTO)
                    && !validarExistenciaTipoEmpleado(empleadoFirmado, TipoEmpleadoEnum.ENLACE)) {
                empleadoFirmado = null;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            empleadoFirmado = null;
        }

        return empleadoFirmado;
    }

    public FecetAsociado traeAgenteAduanal(BigDecimal idOrden) {
        return fecetAsociadoDao.getColaboradorContribuyente(new BigDecimal(Constantes.ENTERO_CUATRO), idOrden).get(0);
    }
}
