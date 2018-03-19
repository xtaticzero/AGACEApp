/**
 *
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.validar.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCancelacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ValidarFirmarDoctoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.helper.ValidarFirmarDoctoHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasTransferidasPendValidService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarFirmarDoctoService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

/**
 * @author sergio.vaca
 *
 */
@Service("validarFirmarDoctoService")
public class ValidarFirmarDoctoServiceImpl extends PropuestasServiceAbstract implements ValidarFirmarDoctoService {

    private static final BigDecimal ESTATUS_ACTIVO = BigDecimal.ONE;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private transient ValidarFirmarDoctoHelper validarFirmarDoctoHelper;

    @Autowired
    private transient ValidarFirmarDoctoDao validarFirmarDoctoDao;

    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;

    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;

    @Autowired
    private transient FecetCancelacionDao fecetCancelacionDao;

    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;

    @Autowired
    private transient FecetDocRechazoPropuestaDao fecetDocRechazoPropuestaDao;

    @Autowired
    private transient FecetTransferenciaDao fecetTransferenciaDao;

    @Autowired
    private transient FecetDocTransferenciaDao fecetDocTransferenciaDao;

    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;

    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;

    @Autowired
    private transient FecetRetroalimentacionDao fecetRetroalimentacionDao;

    @Autowired
    private transient PropuestasTransferidasPendValidService propuestasTransferidasPendValidService;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public List<FecetPropuesta> obtenerDetalleResumen(String rfcFirmante, int idOpcion, List<AraceDTO> unidades) {
        List<FecetPropuesta> propuestas = new ArrayList<FecetPropuesta>();
        TiposEstatusPropuestaEnum estatusPropuesta = TiposEstatusPropuestaEnum.obtenerEnumById(idOpcion);
        if (estatusPropuesta != null && rfcFirmante != null && !rfcFirmante.isEmpty()) {
            propuestas.addAll(validarFirmarDoctoDao.obtenerInformadasValidar(rfcFirmante, estatusPropuesta));

            for (AraceDTO detalleUnidad : unidades) {
                for (FecetPropuesta prop : propuestas) {
                    if (detalleUnidad.getIdArace().equals(prop.getIdArace().intValue())) {

                        FececUnidadAdministrativa unidadAdministrativa = new FececUnidadAdministrativa();
                        unidadAdministrativa.setIdUnidadAdministrativa(new BigDecimal(detalleUnidad.getIdArace()));
                        unidadAdministrativa.setNombre(detalleUnidad.getNombre());
                        unidadAdministrativa.setDescripcion(detalleUnidad.getSede());

                        prop.setUnidadAdministrativa(unidadAdministrativa);
                    }
                }
            }

        }
        return propuestas;
    }

    public void obtenerInformacionAdicional(FecetPropuesta propuesta, List<FecetCancelacion> listaCancelacion,
            List<FecetContadorPropuestasRechazados> listaRechazo, List<FecetTransferenciaContador> listaTransferencia,
            List<FecetRetroContador> listaRetroalimentacion, TiposEstatusPropuestaEnum tipoAccion) {
        propuesta.setListaDocumentos(fecetDocExpedienteDao.findWhereIdPropuestaEquals(propuesta.getIdPropuesta()));
        propuesta.setLstImpuestos(fecetImpuestoDao.getImpuestosPropuesta(propuesta.getIdPropuesta()));
        switch (tipoAccion) {
            case CANCELADADAS_NO_APROBAR:
            case CANCELADAS_EMISION:
            case CANCELADAS_VALIDAR:
                listaCancelacion.addAll(fecetCancelacionDao.obtenerJustificacionCancelacion(propuesta.getIdPropuesta(),
                        TipoEstatusEnum.CANCELACION_PENDIENTE_DE_VALIDACION, ESTATUS_ACTIVO));
                break;
            case RECHAZADAS_EMISION:
            case RECHAZADAS_NO_APROBAR:
            case RECHAZADAS_VALIDAR:
                listaRechazo.addAll(fecetRechazoPropuestaDao.getContadorRechazo(propuesta.getIdPropuesta(),
                        TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION, ESTATUS_ACTIVO));
                break;
            case TRANSFERIDAS_EMISION:
            case TRANSFERIDAS_NO_APROBAR:
            case TRANSFERIDAS_VALIDAR:
                listaTransferencia.addAll(unidadTrasferecia(fecetTransferenciaDao.obtenerTransferenciasByIdPropuesta(propuesta.getIdPropuesta(),
                        TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO, ESTATUS_ACTIVO)));
                break;
            case RETROALIMENTADAS_EMISION:
            case RETROALIMENTADAS_NO_APROBAR:
            case RETROALIMENTADAS_VALIDAR:
                listaRetroalimentacion.addAll(validarFirmarDoctoDao.obtenerRetroByIdPropuesta(propuesta.getIdPropuesta(),
                        TipoEstatusEnum.RETROALIMENTACION_PENDIENTE_DE_VALIDACION, ESTATUS_ACTIVO));
                break;
            default:
                break;
        }
    }

    @Override
    public BigDecimal rechazarProceso(FecetPropuesta propuesta, TiposEstatusPropuestaEnum estatus, String observaciones, BigDecimal idEmpleado,
            List<FecetRetroContador> retro) {
        actualizarPropuesta(propuesta, estatus, observaciones, idEmpleado, null, false, retro);
        return propuesta.getIdPropuesta();
    }

    @Override
    public BigDecimal aceptarProceso(FecetPropuesta propuesta, TiposEstatusPropuestaEnum estatus, List<FecetTransferenciaContador> transferencias,
            BigDecimal idEmpleado, List<FecetRetroContador> retro) {
        BigDecimal idArace = null;
        if (transferencias != null && !transferencias.isEmpty()) {
            idArace = transferencias.get(0).getIdAraceDestino();
        }
        actualizarPropuesta(propuesta, estatus, null, idEmpleado, idArace, true, retro);
        return propuesta.getIdPropuesta();
    }

    public List<DocumentoVista> obtenerDocumentosCancelacion(FecetCancelacion cancelacion) {
        List<FecetDocCancelacion> elementos = fecetCancelacionDao.obtenerDoctosCancelacionById(cancelacion.getIdCancelacion(), ESTATUS_ACTIVO);
        return validarFirmarDoctoHelper.obtenerDocumentos(elementos);
    }

    public List<DocumentoVista> obtenerDocumentosRechazo(FecetContadorPropuestasRechazados rechazoActual) {
        List<FecetDocExpediente> elementos = fecetDocRechazoPropuestaDao.obtenerDoctosRechazoByIdPropuesta(rechazoActual.getIdRechazoPropuestas(),
                ESTATUS_ACTIVO);
        return validarFirmarDoctoHelper.obtenerDocumentosExpediente(elementos);
    }

    public List<DocumentoVista> obtenerDocumentosTransferencia(FecetTransferenciaContador transferenciaActual) {
        List<FecetDocTransferencia> elementos = fecetDocTransferenciaDao.obtenerDocumentosByIdTransferencia(transferenciaActual.getIdTransferencia(),
                ESTATUS_ACTIVO);
        return validarFirmarDoctoHelper.obtenerDocumentosTransferencia(elementos);
    }

    public List<DocumentoVista> obtenerDocumentosRetroalimentacion(FecetRetroContador retroActual) {
        return validarFirmarDoctoDao.obtenerDoctosRetroByIdRetro(retroActual.getIdRetroalimentacion(), ESTATUS_ACTIVO);
    }

    private void actualizarPropuesta(FecetPropuesta propuesta, TiposEstatusPropuestaEnum estatusActual, String observaciones, BigDecimal idEmpleado,
            BigDecimal idAraceDestino, boolean isAprobada, List<FecetRetroContador> retro) {
        int estatusNuevoPropuesta = 0;
        BigDecimal idAccion = null, idLeyenda = null, idArace = null;
        boolean apagarRetro = false;
        boolean insertaAsociativaRetro = false;
        boolean trasfe = false;
        switch (estatusActual) {
            case CANCELADADAS_NO_APROBAR:
            case CANCELADAS_EMISION:
            case CANCELADAS_VALIDAR:
                if (isAprobada) {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_CONCLUIDA.getId()));
                    idAccion = AccionesFuncionarioEnum.APROBACION_CANCELACION.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaAprobada();
                } else {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.NO_APROBADA_LA_CANCELACION.getId()));
                    idAccion = AccionesFuncionarioEnum.NO_APROBACION_CANCELACION.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaNoAprobada();
                }
                break;
            case RECHAZADAS_EMISION:
            case RECHAZADAS_NO_APROBAR:
            case RECHAZADAS_VALIDAR:
                if (isAprobada) {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_CONCLUIDA.getId()));
                    idAccion = AccionesFuncionarioEnum.APROBACION_RECHAZO.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaAprobada();
                } else {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_NO_APROBADO_EL_RECHAZO.getId()));
                    idAccion = AccionesFuncionarioEnum.NO_APROBACION_RECHAZO.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaNoAprobada();
                }
                break;
            case TRANSFERIDAS_EMISION:
            case TRANSFERIDAS_NO_APROBAR:
            case TRANSFERIDAS_VALIDAR:
                if (isAprobada) {
                    idArace = idAraceDestino;
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_TRANSFERIDA.getId()));
                    idAccion = AccionesFuncionarioEnum.APROBACION_TRANSFERENCIA.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaAprobada();
                    updateIdProgramador(propuesta, idArace);
                    trasfe = true;
                } else {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.FIRMANTE_TRANSFERENCIA_NO_APROBADA.getId()));
                    idAccion = AccionesFuncionarioEnum.NO_APROBACION_TRANSFERENCIA.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaNoAprobada();
                    trasfe = false;
                }
                break;
            case RETROALIMENTADAS_EMISION:
            case RETROALIMENTADAS_NO_APROBAR:
            case RETROALIMENTADAS_VALIDAR:
                if (isAprobada) {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION.getId()));
                    idAccion = AccionesFuncionarioEnum.RETROALIMENTACION_APROBADA.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaAprobada();
                    insertaAsociativaRetro = true;
                } else {
                    estatusNuevoPropuesta = Integer.parseInt(String.valueOf(TipoEstatusEnum.NO_APROBADA_LA_RETROALIMENTACION.getId()));
                    idAccion = AccionesFuncionarioEnum.RETROALIMENTACION_NO_APROBADA.getIdAccion();
                    idLeyenda = estatusActual.getIdLeyendaNoAprobada();
                    apagarRetro = true;
                }
                break;
            default:
                break;
        }
        if (estatusNuevoPropuesta > 0 && idAccion != null) {
            validarFirmarDoctoDao.actualizarEstatus(propuesta.getIdPropuesta(), estatusNuevoPropuesta, idArace);
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(), idAccion, null);
            fecebAccionPropuestaDao.insert(validarFirmarDoctoHelper.creaAccionPropuesta(propuesta, idAccion, observaciones, idEmpleado));
            if (apagarRetro && retro != null && !retro.isEmpty()) {
                validarFirmarDoctoDao.apagarRetroalimentacion(retro.get(0).getIdRetroalimentacion());
            }
            if (insertaAsociativaRetro) {
                FecetRetroalimentacion retroalimentacion = validarFirmarDoctoHelper.creaAsociativaRetro(propuesta.getIdPropuesta(),
                        retro.get(0).getIdRetroalimentacion());
                fecetRetroalimentacionDao.insertarAsociaRetro(retroalimentacion);
            }
            if (trasfe) {
                validarFirmarDoctoDao.actualizarAuditorFirma(propuesta.getIdPropuesta());
                validarFirmarDoctoDao.actualizarAdminSubadminOrdenes(propuesta.getIdPropuesta());
            }

            enviarCorreo(propuesta, idLeyenda);
        }
    }

    private void enviarCorreo(FecetPropuesta propuesta, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), Constantes.USUARIO_AUDITOR, destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, propuesta.getUnidadAdministrativa().getIdUnidadAdministrativa(), destinatarios,
                ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());
        try {
            notificacionService.enviarNotificacionGenerico(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    private void updateIdProgramador(FecetPropuesta propuesta, BigDecimal idArace) {

        BigDecimal idProgramador = BigDecimal.ZERO;
        BigDecimal idTipoEmpleado = null;
        List<ProgramadorPropuestaAsignadaDTO> programadores;

        if (idArace.intValue() == Constantes.ENTERO_DIECINUEVE || idArace.intValue() == Constantes.ENTERO_VEINTE) {
            EmpleadoDTO empleadoCreacion = null;
            try {
                empleadoCreacion = getEmpleadoCompleto(propuesta.getRfcCreacion());
                idTipoEmpleado = empleadoCreacion.getDetalleEmpleado().get(0).getTipoEmpleado().getBigId();
                if (idTipoEmpleado.intValue() == Constantes.ENTERO_CUATRO) {
                    programadores = propuestasTransferidasPendValidService.getProgramadorTransferencia(BigDecimal.ONE);
                    if (programadores.size() > 0) {
                        idProgramador = programadores.get(0).getIdEmpleado();
                    }
                } else {

                    idProgramador = empleadoCreacion.getIdEmpleado();
                }
            } catch (EmpleadoServiceException e) {
                logger.error("No se encuentra el empleado", e);
            }
        } else {
            programadores = propuestasTransferidasPendValidService.getProgramadorTransferencia(idArace);
            if (programadores.size() > 0) {
                idProgramador = programadores.get(0).getIdEmpleado();
            }
        }
        if (idProgramador != null) {
            propuestasTransferidasPendValidService.actualizarIdProgramador(propuesta.getIdPropuesta(), idProgramador);
        }

    }

    @Override
    public EmpleadoDTO obtenerDatosFirmante(String rfc) {
        EmpleadoDTO firmanteSuplente = null;
        try {
            firmanteSuplente = getEmpleadoCompleto(rfc);
        } catch (EmpleadoServiceException e) {
            logger.error("No se encontro el Empleado solicitado.");
        }
        return firmanteSuplente;
    }

    @Override
    public List<AraceDTO> obtenerUnidades() {

        
        return new ArrayList<AraceDTO>();
    }

    public List<FecetTransferenciaContador> unidadTrasferecia(List<FecetTransferenciaContador> listaTransferencia) {
        for (FecetTransferenciaContador lista : listaTransferencia) {
            for (AraceDTO unidad : obtenerUnidades()) {
                if (unidad.getIdArace().equals(lista.getIdAraceDestino().intValue())) {
                    FececUnidadAdministrativa arace = new FececUnidadAdministrativa();
                    arace.setIdUnidadAdministrativa(new BigDecimal(unidad.getIdArace()));
                    arace.setDescripcion(unidad.getSede());
                    arace.setNombre(unidad.getNombre());
                    lista.setFececUnidadAdministrativa(arace);
                }
            }
        }

        return listaTransferencia;
    }
}
