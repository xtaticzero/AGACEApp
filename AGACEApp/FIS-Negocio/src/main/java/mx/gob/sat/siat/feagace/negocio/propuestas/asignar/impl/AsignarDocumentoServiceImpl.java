package mx.gob.sat.siat.feagace.negocio.propuestas.asignar.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarDocumentoService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("asignarDocumentoService")
public class AsignarDocumentoServiceImpl extends PropuestasServiceAbstract implements AsignarDocumentoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;

    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;

    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;

    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;

    @Autowired
    private transient NotificacionService notificacionService;

    static final BigDecimal ESTATUS_ENVIADA_PARA_VERIFICACION_PROCEDENCIA = new BigDecimal(51);
    static final BigDecimal ESTATUS_ASIGNADA_A_SUBADMINISTRADOR = new BigDecimal(71);
    static final BigDecimal TIPO_EMPLEADO_ADMIN_EMISOR_ORDENES = new BigDecimal(11);

    @Override
    public EmpleadoDTO verificarTipoEmpleadoAdministrador(String rfc) {

        EmpleadoDTO administradorEmision = new EmpleadoDTO();

        try {
            administradorEmision = getEmpleadoCompleto(rfc);

            if (!validarExistenciaTipoEmpleado(administradorEmision, TipoEmpleadoEnum.ADMINISTRADOR_EMISION_ORDENES)) {
                administradorEmision = null;
            }

        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            administradorEmision = null;
        }

        return administradorEmision;
    }

    @Override
    public List<FecetPropuesta> obtenerPropuestasPendientesDeValidacion(BigDecimal idArace) {

        logger.debug("empleado.getFecetDetalleEmpleado().getIdArace()------ " + idArace);
        List<FecetPropuesta> listaProp = fecetPropuestaDao.findWhereIdEstatusAndRfcAdminArace(ESTATUS_ENVIADA_PARA_VERIFICACION_PROCEDENCIA, idArace);

        for (FecetPropuesta propuesta : listaProp) {
            logger.debug("Si se recorren las propuestas");
            propuesta.setFececSubprograma(fececSubprogramaDao.findWhereIdSubprogramaEquals(propuesta.getIdSubprograma()).get(0));
            propuesta.setFeceaMetodo(feceaMetodoDao.findWhereIdMetodo(propuesta.getIdMetodo()).get(0));

        }
        return listaProp;

    }

    @Override
    public BigDecimal calcularPresuntivaDePropuesta(BigDecimal idPropuesta) {
        BigDecimal presuntiva = BigDecimal.ZERO;

        List<FecetImpuesto> listaImpuestos = fecetImpuestoDao.findWhereIdPropuestaEquals(idPropuesta);

        for (FecetImpuesto imp : listaImpuestos) {
            presuntiva = presuntiva.add(imp.getMonto());
        }

        return presuntiva;
    }

    @Override
    @PistaAuditoria
    public BigDecimal actualizarPropuesta(BigDecimal idPropuesta, int idEstatus, String rfcSub) {
        logger.debug("idPropuesta: " + idPropuesta);
        fecetPropuestaDao.actualizarEstatusAndSubAdministrador(idPropuesta, idEstatus, rfcSub);
        return idPropuesta;
    }

    @Override
    public BigInteger obtenerCantidadDePropuestasAsignadas(String rfcSubAdministrador) {

        return fecetPropuestaDao.getContadorPropuestasAsignadasPorSubAdmin(rfcSubAdministrador, ESTATUS_ASIGNADA_A_SUBADMINISTRADOR);
    }

    @Override
    public void enviarCorreo(String[] rfcNotificacion, String idRegistro, Set<String> destinatarios, BigDecimal unidadAdministrativa) {

        try {

            notificacionService.obtenerCorreoEmpleado(rfcNotificacion[Constantes.ENTERO_UNO], TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(rfcNotificacion[Constantes.ENTERO_DOS], TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(rfcNotificacion[Constantes.ENTERO_CERO], TipoEmpleadoEnum.PROGRAMADOR.getBigId(), destinatarios,
                    ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoEmpleado(rfcNotificacion[Constantes.ENTERO_TRES], TipoEmpleadoEnum.ADMINISTRADOR_EMISION_ORDENES.getBigId(),
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoCentralAcppce(rfcNotificacion[Constantes.ENTERO_TRES], TipoEmpleadoEnum.ADMINISTRADOR_EMISION_ORDENES.getId(),
                    unidadAdministrativa, destinatarios, ClvSubModulosAgace.PROPUESTAS);

            Map<String, String> data = getNotificacionService()
                    .obtenerDatosCorreo(LeyendasPropuestasEnum.ASIGNACION_VERIFICACION_PROCEDENCIA_DOCTO.getIdLeyenda());
            data.put(Common.ID_REGISTRO.toString(), idRegistro);
            data.put(Common.ID_REGISTRO_ESPACIO.toString(), idRegistro);

            getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    @Override
    public List<FecetOficio> getOficiosPorFirmar(BigDecimal idOrden) {
        return fecetOficioDao.getOficioByIdOrden(idOrden);
    }

    @Override
    public AgaceOrden obtenerOrdenByIdPropuesta(BigDecimal idPropuesta) {
        return agaceOrdenDao.findByIdPropuesta(idPropuesta);
    }

    @Override
    public List<FecetDocOrden> obtenerDocOrden(BigDecimal idOrden) {
        return fecetDocOrdenDao.findByFecetOrdenActivo(idOrden);
    }

    @Override
    public void insertaAccion(FecebAccionPropuesta accionPropuesta) {
        fecebAccionPropuestaDao.insert(accionPropuesta);
    }

    @Override
    public void updateAccionIdPropuesta(BigDecimal idPropuesta) {
        feceaPropuestaAccionDao.updateAccionIdPropuesta(idPropuesta, AccionesFuncionarioEnum.VALIDACION_EMISION_ORDENES_11.getIdAccion(), null);
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setFeceaMetodoDao(FeceaMetodoDao feceaMetodoDao) {
        this.feceaMetodoDao = feceaMetodoDao;
    }

    public FeceaMetodoDao getFeceaMetodoDao() {
        return feceaMetodoDao;
    }

    public void setFececSubprogramaDao(FececSubprogramaDao fececSubprogramaDao) {
        this.fececSubprogramaDao = fececSubprogramaDao;
    }

    public FececSubprogramaDao getFececSubprogramaDao() {
        return fececSubprogramaDao;
    }

    public void setFecetImpuestoDao(FecetImpuestoDao fecetImpuestoDao) {
        this.fecetImpuestoDao = fecetImpuestoDao;
    }

    public FecetImpuestoDao getFecetImpuestoDao() {
        return fecetImpuestoDao;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }
}
