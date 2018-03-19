package mx.gob.sat.siat.feagace.negocio.propuestas.validar.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;
import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRetroPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropPendienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DatosPropuestaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.DatosContribuyenteEnum;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.helper.ValidarRetroalimentarHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.rules.ContribuyenteAmparadoPpeeRule;
import mx.gob.sat.siat.feagace.negocio.rules.ValidarYRetroalimentarPropuestaRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("validarRetroalimentarPropuestaService")
public class ValidarRetroalimentarPropuestaServiceImpl extends BaseBusinessServices implements ValidarRetroalimentarPropuestaService {

    private static final long serialVersionUID = 8883151968158683602L;

    private static final String TIPO_ERROR_NEGOCIO = "reglas.negocio.violada";
    private static final String TIPO_ERRO_BR_AMPARADO_PPE = "reglas.usuario.amparado.ppe";
    private static final String ERROR_EMPLEADO_NO_EXISTE = "empleado.no.existente";
    private static final String ERROR_EMPLEADO_NO_AUTORIZADO = "validar.retroalimentar.empleado.no.autorizado";
    private static final String LOG_INFO = "--- lista notificaciones size:";

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    @Qualifier("fecetRetroalimentacionDao")
    private transient FecetRetroalimentacionDao fecetRetroalimentacionDao;
    @Autowired
    private transient FecetDocRetroPropuestaDao fecetDocRetroPropuestaDao;
    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;
    @Autowired
    @Qualifier("fecetPropPendienteDao")
    private transient FecetPropPendienteDao fecetPropPendienteDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececSectorDao fececSectorDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FececPrioridadDao fececPrioridadDao;
    @Autowired
    private transient FececConceptoDao fececConceptoDao;
    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;
    @Autowired
    @Qualifier("contribuyenteService")
    private ContribuyenteService contribuyenteService;
    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    @Qualifier("amparadoPpeeBR")
    private ContribuyenteAmparadoPpeeRule amparadoPpeeBR;
    @Autowired
    @Qualifier("validarYRetroalimentarPropuestaRule")
    private ValidarYRetroalimentarPropuestaRule validarYRetroalimentarPropuestaRule;
    @Autowired
    @Qualifier("validarRetroalimentarHelper")
    private ValidarRetroalimentarHelper validarRetroalimentarHelper;

    private boolean asignarFirmante;
    private boolean solicitudRetroalimentacion;
    private boolean atencionRechazo;

    @Override
    public BigDecimal marcarPropuestaValida(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException {
        List<String> lstCorreoNotificacion;
        try {
            Long estadoPropuesta = fecetPropuesta.getIdEstatus().longValue();
            setAsignarFirmante(true);
            lstCorreoNotificacion = empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, fecetPropuesta.getIdArace(),
                    ClvSubModulosAgace.PROPUESTAS);
            logger.info(LOG_INFO, lstCorreoNotificacion.size());
            if (esProcesable(fecetPropuesta)) {
                for (TipoEstatusEnum estatus : TipoEstatusEnum.values()) {
                    Long valorEstado = estatus.getId();

                    if ((valorEstado).equals(estadoPropuesta)) {
                        try {
                            if (validarYRetroalimentarPropuestaRule.sePuedeAprobarRechazarPropuesta(estatus)) {
                                fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_ASIGNADA_CENTRAL, fecetPropuesta.getIdPropuesta());
                                fecetPropPendienteDao.updateEstadoByIdPropuesta(fecetPropuesta.getIdPropuesta(), EstadoBooleanodeRegistroEnum.INACTIVO);
                                // Realiza el envio de la notificacion
                                enviarCorreoConfirmacion(fecetPropuesta, lstCorreoNotificacion);
                            }
                        } catch (AgacePropuestasRulesException ex) {
                            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
                        }
                    }
                }

            } else {
                throw new ValidarRetroalimentarPropuestaException(TIPO_ERRO_BR_AMPARADO_PPE);
            }
        } catch (AgacePropuestasRulesException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        }
        return fecetPropuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal marcarAprobarPropuestaValida(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException {
        return marcarPropuestaValida(fecetPropuesta);
    }
    
    @Override
    @PistaAuditoria
    public BigDecimal marcarAprobarPropuestaPendiente(final FecetPropuesta fecetPropuesta) throws ValidarRetroalimentarPropuestaException {
        return marcarPropuestaValida(fecetPropuesta);
    }

    @PistaAuditoria
    @Override
    public BigDecimal marcarPropuestaRechazada(FecetPropuesta fecetPropuesta, List<FecetRechazoPropuesta> lstArchivosRechazoPropuesta)
            throws ValidarRetroalimentarPropuestaException {
        String ruta;
        FecetRechazoPropuesta fecetRechazoPropuesta;
        Long estadoPropuesta = fecetPropuesta.getIdEstatus().longValue();
        setAtencionRechazo(true);
        List<String> lstCorreoNotificacion;

        try {
            lstCorreoNotificacion = empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, fecetPropuesta.getIdArace(),
                    ClvSubModulosAgace.PROPUESTAS);
            logger.info(LOG_INFO, lstCorreoNotificacion.size());
            if (esProcesable(fecetPropuesta)) {
                for (TipoEstatusEnum estatus : TipoEstatusEnum.values()) {
                    Long valorEstado = estatus.getId();

                    if ((valorEstado).equals(estadoPropuesta)) {
                        try {
                            logger.debug("Se trata de Actualizar propuesta " + TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR.getId()
                                    + "Original " + estatus.getId());
                            if (validarYRetroalimentarPropuestaRule.sePuedeAprobarRechazarPropuesta(estatus)) {
                                fecetPropuesta.setIdEstatus(BigDecimal.valueOf(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR.getId()));
                                fecetPropuestaDao.update(new FecetPropuestaPk(fecetPropuesta.getIdPropuesta()), fecetPropuesta);

                                fecetRechazoPropuesta = new FecetRechazoPropuesta();

                                for (FecetRechazoPropuesta rechazo : lstArchivosRechazoPropuesta) {
                                    fecetRechazoPropuesta = rechazo;
                                    fecetRechazoPropuesta.setIdRechazoPropuesta(fecetRechazoPropuestaDao.getIdRechazoPropuesta());
                                    fecetRechazoPropuesta.setIdPropuesta(fecetPropuesta.getIdPropuesta());
                                    fecetRechazoPropuesta.setEstatus(fecetPropuesta.getIdEstatus());
                                    fecetRechazoPropuestaDao.insert(fecetRechazoPropuesta);
                                    break;
                                }

                                for (FecetRechazoPropuesta docRechazo : lstArchivosRechazoPropuesta) {
                                    docRechazo.setIdRechazoPropuesta(fecetRechazoPropuesta.getIdRechazoPropuesta());
                                    docRechazo.setIdPropuesta(fecetPropuesta.getIdPropuesta());
                                    docRechazo.setEstatus(fecetPropuesta.getIdEstatus());
                                    ruta = RutaArchivosUtil.armarRutaDestinorRechazoPropuesta(fecetPropuesta, docRechazo.getIdRechazoPropuesta());
                                    docRechazo.setRutaArchivo(ruta + docRechazo.getNombreArchivo());
                                    fecetRechazoPropuestaDao.insertDoc(docRechazo);
                                }
                                fecetPropPendienteDao.updateEstadoByIdPropuesta(fecetPropuesta.getIdPropuesta(), EstadoBooleanodeRegistroEnum.INACTIVO);
                                for (FecetRechazoPropuesta docRechazo : lstArchivosRechazoPropuesta) {
                                    CargaArchivoUtil.guardarArchivo(docRechazo.getArchivo(), docRechazo.getRutaArchivo(), docRechazo.getNombreArchivo());
                                }
                                // Realiza el envio de la notificacion
                                enviarCorreoConfirmacion(fecetPropuesta, lstCorreoNotificacion);
                            }
                        } catch (AgacePropuestasRulesException ex) {
                            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
                        }
                    }
                }
            } else {
                throw new ValidarRetroalimentarPropuestaException(TIPO_ERRO_BR_AMPARADO_PPE);
            }

        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_DOCUMENTO_RECHAZO, e);
            throw new ValidarRetroalimentarPropuestaException("validar.retroalimentar.archivo.rechazo", e);
        } catch (AgacePropuestasRulesException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        }
        return fecetPropuesta.getIdPropuesta();
    }    
    
    
    @Override
    @PistaAuditoria
    public BigDecimal marcarPropuestaPendienteRechazada(FecetPropuesta fecetPropuesta, List<FecetRechazoPropuesta> lstArchivosRechazoPropuesta)
            throws ValidarRetroalimentarPropuestaException {
        String ruta;
        FecetRechazoPropuesta fecetRechazoPropuesta;
        Long estadoPropuesta = fecetPropuesta.getIdEstatus().longValue();
        setAsignarFirmante(true);
        List<String> lstCorreoNotificacion;

        try {
            lstCorreoNotificacion = empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, fecetPropuesta.getIdArace(),
                    ClvSubModulosAgace.PROPUESTAS);
            logger.info(LOG_INFO, lstCorreoNotificacion.size());
            if (esProcesable(fecetPropuesta)) {
                for (TipoEstatusEnum estatus : TipoEstatusEnum.values()) {
                    Long valorEstado = estatus.getId();

                    if ((valorEstado).equals(estadoPropuesta)) {
                        try {
                            if (validarYRetroalimentarPropuestaRule.sePuedeAprobarRechazarPropuesta(estatus)) {
                                fecetPropuesta.setIdEstatus(BigDecimal.valueOf(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE.getId()));

                                fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE,
                                        fecetPropuesta.getIdPropuesta());

                                fecetRechazoPropuesta = new FecetRechazoPropuesta();

                                for (FecetRechazoPropuesta rechazo : lstArchivosRechazoPropuesta) {
                                    fecetRechazoPropuesta = rechazo;
                                    fecetRechazoPropuesta.setIdRechazoPropuesta(fecetRechazoPropuestaDao.getIdRechazoPropuesta());
                                    fecetRechazoPropuesta.setIdPropuesta(fecetPropuesta.getIdPropuesta());
                                    fecetRechazoPropuesta.setEstatus(fecetPropuesta.getIdEstatus());
                                    fecetRechazoPropuestaDao.insert(fecetRechazoPropuesta);
                                    break;
                                }

                                for (FecetRechazoPropuesta docRechazo : lstArchivosRechazoPropuesta) {
                                    docRechazo.setIdRechazoPropuesta(fecetRechazoPropuesta.getIdRechazoPropuesta());
                                    docRechazo.setIdPropuesta(fecetPropuesta.getIdPropuesta());
                                    docRechazo.setEstatus(fecetPropuesta.getIdEstatus());
                                    ruta = RutaArchivosUtil.armarRutaDestinorRechazoPropuesta(fecetPropuesta, docRechazo.getIdRechazoPropuesta());
                                    docRechazo.setRutaArchivo(ruta + docRechazo.getNombreArchivo());
                                    fecetRechazoPropuestaDao.insertDoc(docRechazo);
                                }

                                fecetPropPendienteDao.updateEstadoByIdPropuesta(fecetPropuesta.getIdPropuesta(), EstadoBooleanodeRegistroEnum.INACTIVO);
                                for (FecetRechazoPropuesta docRechazo : lstArchivosRechazoPropuesta) {
                                    CargaArchivoUtil.guardarArchivo(docRechazo.getArchivo(), docRechazo.getRutaArchivo(), docRechazo.getNombreArchivo());
                                }

                                // Realiza el envio de la notificacion
                                enviarCorreoConfirmacion(fecetPropuesta, lstCorreoNotificacion);

                            }
                        } catch (AgacePropuestasRulesException ex) {
                            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
                        }
                    }
                }
            } else {
                throw new ValidarRetroalimentarPropuestaException(TIPO_ERRO_BR_AMPARADO_PPE);
            }

        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_DOCUMENTO_RECHAZO, e);
            throw new ValidarRetroalimentarPropuestaException("archivo.rechazo", e);
        } catch (AgacePropuestasRulesException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        }
        return fecetPropuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal marcarPropuestaPendiente(FecetPropuesta fecetPropuesta, List<FecetPropPendiente> lstArchivosPendientePropuesta,
            FecetPropPendiente pendiente) throws ValidarRetroalimentarPropuestaException {
        setAsignarFirmante(true);
        List<String> lstCorreoNotificacion;
        try {
            lstCorreoNotificacion = empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, fecetPropuesta.getIdArace(),
                    ClvSubModulosAgace.PROPUESTAS);
            logger.info(LOG_INFO, lstCorreoNotificacion.size());
            if (esProcesable(fecetPropuesta)) {

                String carpetasRFC = fecetPropuesta.getFecetContribuyente().getRfc();
                String carpetaIdRegistro = fecetPropuesta.getIdRegistro();
                String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));

                pendiente.setIdPropuesta(fecetPropuesta.getIdPropuesta());
                pendiente.setFechaCreacion(new Date());
                pendiente.setRfcCreacion(fecetPropuesta.getRfcCreacion());
                pendiente.setEstatus(EstadoBooleanodeRegistroEnum.ACTIVO.getEstado());
                fecetPropPendienteDao.insert(pendiente);

                for (FecetPropPendiente docPendiente : lstArchivosPendientePropuesta) {

                    pendiente.setNombreArchivo(docPendiente.getNombreArchivo());
                    pendiente.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PENDIENTE_PROPUESTAS, carpetasRFC,
                            carpetaIdRegistro, carpetaUnica) + pendiente.getNombreArchivo());
                    pendiente.setArchivo(docPendiente.getArchivo());
                    pendiente.setFechaCreacion(new Date());
                    pendiente.setFechaFin(null);

                    fecetPropPendienteDao.insertDoc(pendiente);
                    CargaArchivoUtil.guardarArchivoPropuestaPendiente(pendiente);
                }

                fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_PENDIENTE, fecetPropuesta.getIdPropuesta());

                // Realiza el envio de notificaciones
                enviarCorreoConfirmacion(fecetPropuesta, lstCorreoNotificacion);
            } else {
                throw new ValidarRetroalimentarPropuestaException(TIPO_ERRO_BR_AMPARADO_PPE);
            }
        } catch (AgacePropuestasRulesException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_DOCUMENTO_RECHAZO, e);
            throw new ValidarRetroalimentarPropuestaException("validar.retroalimentar.archivo.rechazo", e);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        }
        return fecetPropuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal marcarPropuestaRetroalimentada(FecetPropuesta fecetPropuesta, FecetRetroalimentacion retroalimentacion,
            List<DocRetroalimentacionDTO> listaDocsRetro) throws ValidarRetroalimentarPropuestaException {

        Long estadoPropuesta = fecetPropuesta.getIdEstatus().longValue();
        FecetRetroalimentacion retroalimentacionTramite = retroalimentacion;
        setSolicitudRetroalimentacion(true);
        List<String> lstCorreoNotificacion;
        BigDecimal estatusSalida;
        try {
            lstCorreoNotificacion = empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, fecetPropuesta.getIdArace(),
                    ClvSubModulosAgace.PROPUESTAS);

            logger.info(LOG_INFO, lstCorreoNotificacion.size());
            if (esProcesable(fecetPropuesta)) {
                for (TipoEstatusEnum estatus : TipoEstatusEnum.values()) {
                    Long valorEstado = estatus.getId();

                    if ((valorEstado).equals(estadoPropuesta)) {
                        try {
                            if (validarYRetroalimentarPropuestaRule.sePuedeAprobarRechazarPropuesta(estatus)) {
                                // No se usa esta consulta
                                agaceOrdenDao.findWhereIdPropuestaEquals(fecetPropuesta.getIdPropuesta());

                                estatusSalida = BigDecimal.valueOf(TipoEstatusEnum.PROPUESTA_RETROALIMENTADA.getId());
                                fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_RETROALIMENTADA, fecetPropuesta.getIdPropuesta());

                                fecetPropuesta.setIdEstatus(estatusSalida);

                                cargaImpuesto(fecetPropuesta.getLstImpuestos(), retroalimentacion);
                                fecetPropuestaDao.updatePropuesta(new FecetPropuestaPk(fecetPropuesta.getIdPropuesta()), fecetPropuesta, false);

                                BigDecimal idRetroActiva = fecetRetroalimentacionDao.getIdRetroalimentacionXEstadoIdPropuesta(fecetPropuesta.getIdPropuesta(),
                                        EstadoBooleanodeRegistroEnum.ACTIVO);
                                if (idRetroActiva != null) {
                                    fecetRetroalimentacionDao.updateEstadoRetroalimentacion(idRetroActiva, EstadoBooleanodeRegistroEnum.INACTIVO);
                                } else {
                                    throw new ValidarRetroalimentarPropuestaException("datos.retroalimentacion", fecetPropuesta.getIdRegistro());
                                }

                                retroalimentacion.setEstatus(estatusSalida);
                                retroalimentacionTramite.setBlnEstatus(new BigDecimal(EstadoBooleanodeRegistroEnum.ACTIVO.getValue()));
                                retroalimentacionTramite.setIdRetroalimentacionOrigen(idRetroActiva);
                                retroalimentacionTramite = fecetRetroalimentacionDao.insertarRetroalimentacionHistorial(retroalimentacion);
                                fecetRetroalimentacionDao.retroAtendida(idRetroActiva);
                                fecetRetroalimentacionDao.insertarAsociaRetro(retroalimentacionTramite);
                                cargaImpuesto(retroalimentacionTramite.getLstImpuestos(), retroalimentacionTramite);
                                List<DocRetroalimentacionDTO> listaDocsRetroXActualizar = validarRetroalimentarHelper.filtraDocRetroalimentacion(fecetPropuesta,
                                        listaDocsRetro);

                                FecebAccionPropuesta historialAccion = new FecebAccionPropuesta();
                                historialAccion.setFechaHora(new Date());
                                historialAccion.setIdDetalleAccion(retroalimentacionTramite.getIdRetroalimentacion());
                                historialAccion.setIdAccion(AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA.getIdAccion());
                                historialAccion.setIdPropuesta(retroalimentacion.getIdPropuesta());
                                historialAccion.setIdEmpleado(retroalimentacion.getIdEmpleado());
                                feceaPropuestaAccionDao.updateAccionIdPropuesta(retroalimentacion.getIdPropuesta(),
                                        AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA.getIdAccion(), null);
                                fecebAccionPropuestaDao.insert(historialAccion);

                                for (DocRetroalimentacionDTO docRetro : listaDocsRetroXActualizar) {
                                    docRetro.setIdRetroalimentacion(retroalimentacionTramite.getIdRetroalimentacion());
                                    fecetDocRetroPropuestaDao.insert(docRetro);
                                }
                                validarRetroalimentarHelper.guardarDocRetroalimentacion(listaDocsRetroXActualizar);

                                // Envio de notificacion
                                enviarCorreoConfirmacion(fecetPropuesta, lstCorreoNotificacion);
                            }
                        } catch (AgacePropuestasRulesException ex) {
                            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
                        }
                    }
                }
            } else {
                throw new ValidarRetroalimentarPropuestaException(TIPO_ERRO_BR_AMPARADO_PPE);
            }

        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_DOCUMENTO_RECHAZO, e);
            throw new ValidarRetroalimentarPropuestaException("archivo.rechazo", e);
        } catch (AgacePropuestasRulesException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
            throw new ValidarRetroalimentarPropuestaException(TIPO_ERROR_NEGOCIO, ex);
        }
        return fecetPropuesta.getIdPropuesta();
    }

    public void cargaImpuesto(List<FecetImpuesto> lstImpuestos, FecetRetroalimentacion retroalimentacionTramite) {
        for (FecetImpuesto impuestoItem : lstImpuestos) {
            if (impuestoItem.getIdImpuesto() == null || retroalimentacionTramite.getIdRetroalimentacion() != null) {
                FecetImpuesto impuesto = new FecetImpuesto();
                if (retroalimentacionTramite.getIdRetroalimentacion() != null) {
                    impuesto.setIdRetroalimentacion(retroalimentacionTramite.getIdRetroalimentacion());
                }
                impuesto.setIdPropuesta(retroalimentacionTramite.getIdPropuesta());
                impuesto.setIdTipoImpuesto(impuestoItem.getIdTipoImpuesto());
                impuesto.setMonto(impuestoItem.getMonto());
                impuesto.setIdConcepto(impuestoItem.getIdConcepto());
                fecetImpuestoDao.insert(impuesto).getIdImpuesto();

            }
        }
    }

    @Override
    public void descartarImpuesto(final FecetImpuesto fecetImpuesto) {
        final Date fechaBaja = new Date();
        fecetImpuesto.setFechaBaja(fechaBaja);
        fecetImpuestoDao.update(new FecetImpuestoPk(fecetImpuesto.getIdImpuesto()), fecetImpuesto);
    }

    @Override
    public void actualizarContribuyente(final FecetContribuyente contribuyente) {
        fecetContribuyenteDao.update(new FecetContribuyentePk(contribuyente.getIdContribuyente()), contribuyente);
    }

    @Override
    public Map<String, FecetContribuyente> verificaDatosContribuyente(FecetContribuyente contribuyente) throws ValidarRetroalimentarPropuestaException {
        try {
            FecetContribuyente contribuyenteIdC;
            Map<String, FecetContribuyente> mapContribuyentes;
            mapContribuyentes = new HashMap<String, FecetContribuyente>();
            if (contribuyente != null && contribuyente.getRfc() != null) {
                contribuyenteIdC = contribuyenteService.getContribuyenteIDC(contribuyente.getRfc());
                if (!contribuyente.equals(contribuyenteIdC)) {
                    mapContribuyentes.put(DatosContribuyenteEnum.DATOS_CONTRIBUYENTE_ANTERIOR.getLlaveMap(), contribuyente);
                    mapContribuyentes.put(DatosContribuyenteEnum.DATOS_CONTRIBUYENTE_ACTUALIZADOS.getLlaveMap(), contribuyenteIdC);
                } else {
                    mapContribuyentes.put(DatosContribuyenteEnum.DATOS_CONTRIBUYENTE_ANTERIOR.getLlaveMap(), contribuyente);
                }
                return mapContribuyentes;
            }

        } catch (NoExisteContribuyenteException e) {
            throw new ValidarRetroalimentarPropuestaException("datos.contribuyente", e);
        }
        return new HashMap<String, FecetContribuyente>();
    }

    @Override
    public boolean esProcesable(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        BigDecimal idMetodo = propuesta.getIdMetodo();
        if (TipoMetodoEnum.ORG.getId() == idMetodo.longValue()) {
            return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc());
        }
        return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc());

    }

    public BigDecimal esProcesableIdPropuesta(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        BigDecimal idMetodo = propuesta.getIdMetodo();
        if (TipoMetodoEnum.ORG.getId() == idMetodo.longValue()) {
            return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc()) ? propuesta.getIdPropuesta()
                    : Constantes.BIG_DECIMAL_CERO;
        }
        return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc()) ? propuesta.getIdPropuesta()
                : Constantes.BIG_DECIMAL_CERO;

    }

    @Override
    @PistaAuditoria
    public BigDecimal esProcesableRetro(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        return esProcesableIdPropuesta(propuesta);
    }

    @Override
    @PistaAuditoria
    public BigDecimal esProcesableCambioMetodo(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        return esProcesableIdPropuesta(propuesta);
    }

    @Override
    @PistaAuditoria
    public BigDecimal esProcesablePendiente(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        return esProcesableIdPropuesta(propuesta);
    }

    @Override
    public BigDecimal esProcesableFecha(FecetPropuesta propuesta) throws AgacePropuestasRulesException {
        BigDecimal idMetodo = propuesta.getIdMetodo();
        if (TipoMetodoEnum.ORG.getId() == idMetodo.longValue()) {
            return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc())
                    && validarYRetroalimentarPropuestaRule.validarFechaPresentacionAComite(propuesta.getIdRegistro(), propuesta.getFechaPresentacion())
                            ? propuesta.getIdPropuesta() : Constantes.BIG_DECIMAL_CERO;
        }
        return !amparadoPpeeBR.esContribuyenteAmparadoOPpee(propuesta.getFecetContribuyente().getRfc()) ? propuesta.getIdPropuesta()
                : Constantes.BIG_DECIMAL_CERO;

    }

    @Override
    public List<FecetPropuesta> buscaPropuestasPorValidar(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException {
        EmpleadoDTO empleado;
        TipoAraceEnum araceEmpleado;
        TipoEstatusEnum[] tiposEstatus;

        if (validarYRetroalimentarPropuestaRule.esUnProgramadorActivo(rfcEmpleado)) {
            try {
                empleado = getEmpleadoProgramadorActivo(rfcEmpleado);
                DetalleEmpleadoDTO detalle = null;

                if (empleado != null && empleado.getDetalleEmpleado() != null) {
                    for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                        detalle = det;
                        break;
                    }
                    if (detalle != null) {
                        araceEmpleado = getTipoAraceEnumById(detalle.getCentral().getIdArace());

                        List<FecetPropuesta> lstPropuestasCentralARegional;
                        List<FecetPropuesta> lstPropuestasXValidar;

                        if (validarYRetroalimentarPropuestaRule.esCentral(araceEmpleado)) {
                            logger.debug("Puede ver 19 y 20");
                            tiposEstatus = new TipoEstatusEnum[] { TipoEstatusEnum.PROPUESTA_REGISTRADA };

                            lstPropuestasCentralARegional = (obtieneArace(fecetPropuestaDao.getPropuestasAsignacasCentralARegional(empleado.getIdEmpleado(),
                                    TipoEstatusEnum.PROPUESTA_REGISTRADA, TipoMetodoEnum.ORG, Constantes.INDICE_UNO)));

                            lstPropuestasXValidar = (validarRetroalimentarHelper
                                    .filtroLstPropuestas(obtieneArace(fecetPropuestaDao.getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus,
                                            TipoMetodoEnum.ORG, Constantes.CADENA_UNO, TipoAraceEnum.ACOECE, TipoAraceEnum.ACAOCE))));

                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                        validarRetroalimentarHelper.combinatLstPropuesta(lstPropuestasXValidar, lstPropuestasCentralARegional));
                            }

                            return validarRetroalimentarHelper.obtenerArchivosPropuesta(lstPropuestasXValidar);
                        } else if (validarYRetroalimentarPropuestaRule.esRegional(araceEmpleado)) {
                            tiposEstatus = new TipoEstatusEnum[] { TipoEstatusEnum.PROPUESTA_REGISTRADA, TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL };

                            lstPropuestasXValidar = validarRetroalimentarHelper.filtroLstPropuestas(obtieneArace(fecetPropuestaDao
                                    .getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus, TipoMetodoEnum.ORG, Constantes.CADENA_UNO, araceEmpleado)));

                            lstPropuestasCentralARegional = validarRetroalimentarHelper
                                    .filtroLstConfirmRegional(validarRetroalimentarHelper.fitroFoliosPropuestaXVaslidar(
                                            obtieneArace(fecetPropuestaDao.getPropuestasAsignacasCentralARegional(empleado.getIdEmpleado(), tiposEstatus,
                                                    Constantes.CADENA_UNO, TipoMetodoEnum.ORG))));
                            
                            List<FecetPropuesta> propuestasOrigenInsumos = new ArrayList<FecetPropuesta>();
                            for (FecetPropuesta propuesta : lstPropuestasCentralARegional) {
                                boolean idUnidadInsumoNula = propuesta.getUnidadAdministrativa().getIdUnidadAdministrativa() != null
                                        && propuesta.getUnidadAdministrativa().getIdUnidadAdministrativa().intValue() == Constantes.ENTERO_UNO;
                                boolean esPropuestaCentralRegional = (!propuesta.getIdArace().equals(BigDecimal.valueOf(Constantes.ENTERO_DIECINUEVE))
                                        && !propuesta.getIdArace().equals(BigDecimal.valueOf(Constantes.ENTERO_VEINTE)));
                                boolean esEstatusRegistrada = propuesta.getIdEstatus().intValue() == TipoEstatusEnum.PROPUESTA_REGISTRADA.getId();

                                if (idUnidadInsumoNula && esPropuestaCentralRegional && esEstatusRegistrada) {
                                    propuestasOrigenInsumos.add(propuesta);
                                }
                            }
                            lstPropuestasCentralARegional.removeAll(propuestasOrigenInsumos);

                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                        validarRetroalimentarHelper.combinatLstPropuesta(lstPropuestasXValidar, lstPropuestasCentralARegional));
                            }

                            return validarRetroalimentarHelper.obtenerArchivosPropuesta(validarRetroalimentarHelper.filtroLstPropuestas(lstPropuestasXValidar));
                        }
                    }
                }
            } catch (NoExisteEmpleadoException ex) {
                throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_EXISTE, ex);
            }

        }
        throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_AUTORIZADO, rfcEmpleado);
    }

    @Override
    public List<FecetPropuesta> buscaPropuestasMarcadasPendientes(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException {
        EmpleadoDTO empleado;
        TipoAraceEnum araceEmpleado;
        TipoEstatusEnum[] tiposEstatus;

        List<FecetPropuesta> lstPropuestasCentralARegional;
        List<FecetPropuesta> lstPropuestasPendientes;

        if (validarYRetroalimentarPropuestaRule.esUnProgramadorActivo(rfcEmpleado)) {
            try {
                empleado = getEmpleadoProgramadorActivo(rfcEmpleado);
                DetalleEmpleadoDTO detalle = null;

                if (empleado != null && empleado.getDetalleEmpleado() != null) {

                    for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                        detalle = det;
                        break;
                    }

                    if (detalle != null) {
                        araceEmpleado = getTipoAraceEnumById(detalle.getCentral().getIdArace());
                        tiposEstatus = new TipoEstatusEnum[] { TipoEstatusEnum.PROPUESTA_PENDIENTE };
                        if (validarYRetroalimentarPropuestaRule.esCentral(araceEmpleado)) {
                            lstPropuestasCentralARegional = (obtieneArace(fecetPropuestaDao.getPropuestasAsignacasCentralARegional(empleado.getIdEmpleado(),
                                    TipoEstatusEnum.PROPUESTA_PENDIENTE, TipoMetodoEnum.ORG, Constantes.INDICE_UNO)));

                            lstPropuestasPendientes = validarRetroalimentarHelper
                                    .filtroLstPropuestas(obtieneArace(fecetPropuestaDao.getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus,
                                            TipoMetodoEnum.ORG, Constantes.CADENA_UNO, TipoAraceEnum.ACOECE, TipoAraceEnum.ACAOCE)));
                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                        validarRetroalimentarHelper.combinatLstPropuesta(lstPropuestasPendientes, lstPropuestasCentralARegional));
                            }
                            return validarRetroalimentarHelper
                                    .obtenerArchivosPropuesta(validarRetroalimentarHelper.filtroLstPropuestas(lstPropuestasPendientes));

                        } else if (validarYRetroalimentarPropuestaRule.esRegional(araceEmpleado)) {
                            lstPropuestasCentralARegional = validarRetroalimentarHelper
                                    .filtroLstCentralARegional(obtieneArace(fecetPropuestaDao.getPropuestasAsignacasCentralARegional(empleado.getIdEmpleado(),
                                            TipoEstatusEnum.PROPUESTA_PENDIENTE, TipoMetodoEnum.ORG, Constantes.INDICE_UNO)));

                            lstPropuestasPendientes = validarRetroalimentarHelper.obtenerArchivosPropuesta(obtieneArace(fecetPropuestaDao
                                    .getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus, TipoMetodoEnum.ORG, Constantes.CADENA_UNO, araceEmpleado)));

                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                        validarRetroalimentarHelper.combinatLstPropuesta(lstPropuestasPendientes, lstPropuestasCentralARegional));
                            }
                            return validarRetroalimentarHelper
                                    .obtenerArchivosPropuesta(validarRetroalimentarHelper.filtroLstPropuestas(lstPropuestasPendientes));
                        }
                    }
                }

            } catch (NoExisteEmpleadoException ex) {
                throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_EXISTE, ex);
            }

        }
        throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_AUTORIZADO, rfcEmpleado);
    }

    @Override
    public List<FecetPropuesta> buscaPropuestasConCambioDeMetodo(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException {
        EmpleadoDTO empleado;
        TipoAraceEnum araceEmpleado;
        TipoEstatusEnum[] tiposEstatus;
        List<FecetPropuesta> listCambioMetodo = new ArrayList<FecetPropuesta>();
        if (validarYRetroalimentarPropuestaRule.esUnProgramadorActivo(rfcEmpleado)) {
            try {
                empleado = getEmpleadoProgramadorActivo(rfcEmpleado);
                DetalleEmpleadoDTO detalle = null;

                if (empleado != null && empleado.getDetalleEmpleado() != null) {

                    for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                        detalle = det;
                        break;
                    }

                    if (detalle != null) {
                        araceEmpleado = getTipoAraceEnumById(detalle.getCentral().getIdArace());
                        tiposEstatus = new TipoEstatusEnum[] { TipoEstatusEnum.FIRMANTE_ORDEN_CONCLUIDA_POR_CAMBIO_METODO };

                        if (validarYRetroalimentarPropuestaRule.esCentral(araceEmpleado)) {
                            listCambioMetodo = validarRetroalimentarHelper.obtenerArchivosPropuesta(obtieneArace(
                                    fecetPropuestaDao.getPropuestasXCambioMetodo(rfcEmpleado, tiposEstatus, TipoAraceEnum.ACOECE, TipoAraceEnum.ACAOCE)));

                        } else if (validarYRetroalimentarPropuestaRule.esRegional(araceEmpleado)) {
                            listCambioMetodo = validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                    obtieneArace(fecetPropuestaDao.getPropuestasXCambioMetodo(rfcEmpleado, tiposEstatus, araceEmpleado)));
                        }
                    }

                    return listCambioMetodo;
                }

            } catch (NoExisteEmpleadoException ex) {
                throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_EXISTE, ex);
            }

        }
        throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_AUTORIZADO, rfcEmpleado);
    }

    @Override
    public List<FecetPropuesta> buscaPropuestasRetroalimentacion(String rfcEmpleado) throws ValidarRetroalimentarPropuestaException {
        EmpleadoDTO empleado;
        TipoAraceEnum araceEmpleado;
        TipoEstatusEnum[] tiposEstatus;

        if (validarYRetroalimentarPropuestaRule.esUnProgramadorActivo(rfcEmpleado)) {
            try {
                empleado = getEmpleadoProgramadorActivo(rfcEmpleado);
                DetalleEmpleadoDTO detalle = null;

                if (empleado != null && empleado.getDetalleEmpleado() != null) {

                    for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                        detalle = det;
                        break;
                    }

                    if (detalle != null) {
                        araceEmpleado = getTipoAraceEnumById(detalle.getCentral().getIdArace());
                        tiposEstatus = new TipoEstatusEnum[] { TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION,
                                TipoEstatusEnum.PROPUESTA_ENVIADA_SOLICITUD_RETROALIMENTACION };

                        if (validarYRetroalimentarPropuestaRule.esCentral(araceEmpleado)) {

                            List<FecetPropuesta> lstPropuestasCentralARegional = obtieneArace(
                                    fecetPropuestaDao.getPropuestasAsignaSubadminstraACentral(empleado.getIdEmpleado(), tiposEstatus, null));

                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(lstPropuestasCentralARegional);
                            }

                            return validarRetroalimentarHelper
                                    .obtenerArchivosPropuesta(obtieneArace(fecetPropuestaDao.getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus, null,
                                            Constantes.CADENA_CERO, TipoAraceEnum.ACOECE, TipoAraceEnum.ACAOCE)));

                        } else if (validarYRetroalimentarPropuestaRule.esRegional(araceEmpleado)) {

                            List<FecetPropuesta> lstPropuestasXRetroalimentar = obtieneArace(
                                    fecetPropuestaDao.getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus, null, Constantes.CADENA_CERO, araceEmpleado));

                            List<FecetPropuesta> lstPropuestasCentralARegional = obtieneArace(fecetPropuestaDao
                                    .getPropuestasAsignacasCentralARegional(empleado.getIdEmpleado(), tiposEstatus, Constantes.CADENA_CERO, null));

                            if (lstPropuestasCentralARegional != null && !lstPropuestasCentralARegional.isEmpty()) {
                                return validarRetroalimentarHelper.obtenerArchivosPropuesta(
                                        validarRetroalimentarHelper.combinatLstPropuesta(lstPropuestasXRetroalimentar, lstPropuestasCentralARegional));
                            }

                            return validarRetroalimentarHelper.obtenerArchivosPropuesta(obtieneArace(fecetPropuestaDao
                                    .getPropuestasXMetodoEstatusArace(rfcEmpleado, tiposEstatus, null, Constantes.CADENA_CERO, araceEmpleado)));
                        }
                    }
                }
            } catch (NoExisteEmpleadoException ex) {
                throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_EXISTE, ex);
            }

        }
        throw new ValidarRetroalimentarPropuestaException(ERROR_EMPLEADO_NO_AUTORIZADO, rfcEmpleado);
    }

    @Override
    public List<FecetDocExpediente> getExpedientePropuesta(final BigDecimal idPropuesta) {
        return fecetDocExpedienteDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FecetImpuesto> getImpuestosPropuesta(final BigDecimal idPropuesta) {
        return fecetImpuestoDao.getImpuestosPropuesta(idPropuesta);
    }

    @Override
    public List<FecetImpuesto> getImpuestosRetroPropuesta(final BigDecimal idRetro) {
        return fecetImpuestoDao.getImpuestosRetroPropuesta(idRetro);
    }

    @Override
    public List<FececTipoImpuesto> getTiposImpuesto() {

        return fececTipoImpuestoDao.findAll();

    }

    @Override
    public List<FecetRetroalimentacion> getLstHistorialRetroalimentacionPropuesta(final BigDecimal idPropuesta) {

        return fecetRetroalimentacionDao.getDocRetroalimetarHistorialByIdPropuesta(idPropuesta);

    }

    @Override
    public List<FecetRetroalimentacion> getLstOrigenRetroalimentacionPropuesta(final BigDecimal idRetroalimentacion) {

        return fecetRetroalimentacionDao.getDocRetroalimetarByOrigen(idRetroalimentacion);

    }

    @Override
    public List<FecetRetroalimentacion> getLstHistoricoRetroalimentacionPropuesta(final BigDecimal idPropuesta) {

        return fecetRetroalimentacionDao.getDocRetroalimetarByIdPropuesta(idPropuesta);

    }

    @Override
    public List<FecetRetroalimentacion> getDetalleDocRetroalimentacionByIdRetro(final BigDecimal idRetroalimentacion) {

        return validarRetroalimentarHelper.getDocumentosLstRetro(fecetRetroalimentacionDao.getDocRetroalimetarByIdRetroaliemntacion(idRetroalimentacion));

    }

    @Override    
    public List<FecetRechazoPropuesta> getLstHistoricoRechazoPropuesta(final BigDecimal idPropuesta) {
        return fecetRechazoPropuestaDao.getDocRechazadosByIdPropuesta(idPropuesta);
    }

    @Override
    public List<FecetRechazoPropuesta> getDetalleDocRechazoByIdRechazo(final BigDecimal idRechazo) {

        return validarRetroalimentarHelper.getDocumentosLstRechazo(fecetRechazoPropuestaDao.getDocRechazadosByIdRechazo(idRechazo));

    }

    @Override
    public EmpleadoDTO getEmpleadoProgramadorActivo(String rfcEmpleado) throws NoExisteEmpleadoException {
        return getEmpleadoXRfcTipo(rfcEmpleado, TipoEmpleadoEnum.PROGRAMADOR);
    }

    public void setAsignarFirmante(boolean asignarFirmante) {
        this.asignarFirmante = asignarFirmante;
    }

    public boolean isAsignarFirmante() {
        return asignarFirmante;
    }

    public void enviarCorreoConfirmacion(FecetPropuesta fecetPropuesta, List<String> lstCorreoNotificacion) throws ValidarRetroalimentarPropuestaException {
        Set<String> destinatarios = new TreeSet<String>();
        Map<String, String> mapa = new HashMap<String, String>();
        List<String> listaCorreoFinal;
        BigDecimal idLeyenda = BigDecimal.ZERO;
        logger.info(LOG_INFO, lstCorreoNotificacion.size());
        // 1. Destinatarios de correo
        // Se recorre la lista para obtener la lista de correos y se colocan en
        // el mapa para que no esten duplicados
        for (String correo : lstCorreoNotificacion) {
            mapa.put(correo, correo);
        }
        // Se obtienen los valores del mapa y se asignan a una lista final
        listaCorreoFinal = new ArrayList<String>(mapa.values());
        // Se asignan los correos del map a la lista de destinatarios
        for (String listaFinal : listaCorreoFinal) {
            destinatarios.add(listaFinal);
        }

        // 2. Propuestas
        List<DatosPropuestaDTO> propuestasSeleccionadas = new ArrayList<DatosPropuestaDTO>();
        DatosPropuestaDTO datosPropuesta = new DatosPropuestaDTO();
        datosPropuesta.setIdRegistro(fecetPropuesta.getIdRegistro());
        propuestasSeleccionadas.add(datosPropuesta);

        if (isAsignarFirmante()) {
            getNotificacionService().obtenerCorreoEmpleado(fecetPropuesta.getRfcCreacion(), TipoEmpleadoEnum.PROGRAMADOR.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, fecetPropuesta.getIdArace(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            getNotificacionService().obtenerCorreoCentralAcppce(fecetPropuesta.getRfcCreacion(), TipoEmpleadoEnum.PROGRAMADOR.getId(),
                    fecetPropuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
            idLeyenda = LeyendasPropuestasEnum.APROBACION_PROGRAMACION_ASIGNAR_CENTRAL_FIRMANTE.getIdLeyenda();
        } else if (isSolicitudRetroalimentacion()) {
            getNotificacionService().obtenerCorreoEmpleado(fecetPropuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            getNotificacionService().obtenerCorreoEmpleado(fecetPropuesta.getRfcCreacion(), TipoEmpleadoEnum.PROGRAMADOR.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, fecetPropuesta.getIdArace(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            getNotificacionService().obtenerCorreoCentralAcppce(fecetPropuesta.getRfcCreacion(), TipoEmpleadoEnum.PROGRAMADOR.getId(),
                    fecetPropuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
            idLeyenda = LeyendasPropuestasEnum.ATENCION_RETROALIMENTACION_AREA_PROGRAMACION.getIdLeyenda();
        } else {
            idLeyenda = BigDecimal.ZERO;
        }

        setAsignarFirmante(false);
        setSolicitudRetroalimentacion(false);
        
        if (!idLeyenda.equals(BigDecimal.ZERO)) {
           enviarCorreoPropuestas(destinatarios, propuestasSeleccionadas, idLeyenda);   
        }
    }

    public void enviarCorreoPropuestas(Set<String> destinatarios, List<DatosPropuestaDTO> propuestasSeleccionadas, BigDecimal idLeyenda)
            throws ValidarRetroalimentarPropuestaException {
        logger.info("Envia correos de propuestas...");
        for (DatosPropuestaDTO propuesta : propuestasSeleccionadas) {
            Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
            data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
            data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());
            try {
                this.enviarNotificacionCorreos(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
            } catch (Exception e) {
                logger.error("[{1}]", e);
                throw new ValidarRetroalimentarPropuestaException("validar.retroalimentar.error.evio.correo", e);
            }
        }
    }

    public void enviarNotificacionCorreos(Map<String, String> data, ReportType reportType, Set<String> destinatarios)
            throws ValidarRetroalimentarPropuestaException {
        try {
            getNotificacionService().enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (Exception e) {
            logger.error("[{1}]", e);
            throw new ValidarRetroalimentarPropuestaException("validar.retroalimentar.error.evio.correo", e);
        }
    }

    @Override
    public FecetPropPendiente obtieneArchivoPendientePorValidar(BigDecimal idPropuesta) {
        return fecetPropPendienteDao.obtienePendienteIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<String> getListaCorreosDeNotificacionProgramador(BigDecimal idArace) {
        try {
            return empleadoService.getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.PROGRAMADOR, idArace, ClvSubModulosAgace.PROPUESTAS);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<String>();
        }
    }

    public List<FecetPropuesta> obtieneArace(List<FecetPropuesta> propuesta) {
        for (FecetPropuesta lista : propuesta) {
            for (AraceDTO arace : getCatalogoUnidad()) {
                if (lista.getIdArace().intValue() == arace.getIdArace()) {
                    FececArace araces = new FececArace();
                    araces.setIdArace(new BigDecimal(arace.getIdArace()));
                    araces.setNombre(arace.getNombre());
                    lista.setFececArace(araces);
                }
            }
        }
        return propuesta;

    }

    @Override
    public List<AraceDTO> getCatalogoUnidad() {

        try {
            
            List<AraceDTO> unidadesAdmin = new ArrayList<AraceDTO>();
            List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

            for (AraceDTO unidad : unidadesAdmin) {
                if (unidad.getIdArace().equals(Constantes.ACPPCE.intValue()) || unidad.getIdArace().equals(Constantes.ACIACE.intValue())
                        || unidad.getIdArace().equals(Constantes.PPCE.intValue())) {

                    unidadAdminNoAplicable.add(unidad);
                }
            }

            unidadesAdmin.removeAll(unidadAdminNoAplicable);

            return unidadesAdmin;
        } catch (Exception e) {
            logger.error("No se pudieron consultar las Unidades Administrativas");
            return null;
        }
    }

    @Override
    public List<AraceDTO> getCatalogoUnidadPropuestas() {
        return getCatalogoUnidad();
    }

    @Override
    public List<FececSubprograma> getCatalogoSubprograma() {
        return fececSubprogramaDao.findAll();
    }

    @Override
    public List<FececMetodo> getCatalogoMetodo() {
        return feceaMetodoDao.findAll();
    }

    @Override
    public List<FececTipoPropuesta> getCatalogoTipoPropuesta() {
        return fececTipoPropuestaDao.findAll();
    }

    @Override
    public List<FececCausaProgramacion> getCatalogoCausaProgramacion() {
        return fececCausaProgramacionDao.findAll();
    }

    @Override
    public List<FececSector> getCatalogoSector() {
        return fececSectorDao.findAll();
    }

    @Override
    public List<FececRevision> getCatalogoRevision() {
        return fececRevisionDao.findAll();
    }

    @Override
    public List<FececTipoImpuesto> getCatalogoImpuesto() {
        return fececTipoImpuestoDao.findAll();
    }

    @Override
    public List<FececPrioridad> getCatalogoPrioridad() {
        return fececPrioridadDao.findAll();

    }

    @Override
    public List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto) {
        return fececConceptoDao.findByIdTipoImpuesto(idImpuesto);
    }

    public void setSolicitudRetroalimentacion(boolean solicitudRetroalimentacion) {
        this.solicitudRetroalimentacion = solicitudRetroalimentacion;
    }

    public boolean isSolicitudRetroalimentacion() {
        return solicitudRetroalimentacion;
    }

    public void setAtencionRechazo(boolean atencionRechazo) {
        this.atencionRechazo = atencionRechazo;
    }

    public boolean isAtencionRechazo() {
        return atencionRechazo;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    private EmpleadoDTO getEmpleadoXRfcTipo(String rfc, TipoEmpleadoEnum tipo) throws NoExisteEmpleadoException {
        try {

            if (rfc == null || tipo == null) {
                throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE);
            }

            EmpleadoDTO empleado = empleadoService.getEmpleadoCompleto(rfc);
            if (empleado != null && empleado.getDetalleEmpleado() != null) {
                for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                    if (det.getTipoEmpleado().equals(tipo)) {
                        return empleado;
                    }
                }
            }
            throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE, ex);
        }
    }

    private TipoAraceEnum getTipoAraceEnumById(Integer idArace) {
        if (idArace != null) {
            for (TipoAraceEnum tipo : TipoAraceEnum.values()) {
                if ((int) tipo.getId() == idArace) {
                    return tipo;
                }
            }

        }
        return null;
    }

}
