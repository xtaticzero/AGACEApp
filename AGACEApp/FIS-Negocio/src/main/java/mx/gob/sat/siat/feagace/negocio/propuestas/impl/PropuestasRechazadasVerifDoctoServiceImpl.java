package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.correo.services.MailServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleEmpleadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("propuestasRechazadasVerifDoctoService")
public class PropuestasRechazadasVerifDoctoServiceImpl extends PropuestasServiceAbstract implements PropuestasRechazadasVerifDoctoService {

    private static final long serialVersionUID = 1141094131943859997L;

    @Autowired
    private transient FecetDocRechazoPropuestaDao fecetDocRechazoPropuestaDao;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;

    @Autowired
    private transient FecetRechazoPropuestaDao fecetRechazoPropuestaDao;

    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Autowired
    private transient FecetDetalleEmpleadoDao fecetDetalleEmpleadoDao;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    @Qualifier("mailServices")
    private MailServices mailServices;

    @Override
    public List<FecetDocExpediente> buscarDoctosRechazoPorIdRechazo(BigDecimal idRechazo) {
        return fecetDocRechazoPropuestaDao.findWhereIdRechazoPropuestaEquals(idRechazo);
    }

    @Override
    public List<FecetDocExpediente> buscarDoctosOrdenProp(BigDecimal idPropuesta) {
        return fecetDocOrdenDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FecetDocExpediente> buscarDoctosExpedienteProp(BigDecimal idPropuesta) {
        return fecetDocExpedienteDao.findWhereIdPropuestaEqualsOrderByFecha(idPropuesta);
    }

    @Override
    public List<FecetRechazoPropuesta> buscarRechazosPorIdPropuesta(BigDecimal idPropuesta, BigDecimal idArace, BigDecimal idTipoEmpleado) {
        return fecetRechazoPropuestaDao.findRechazosByIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FecetAsociado> getRepresentanteLegal(BigDecimal idPropuesta) {
        return fecetAsociadoDao.getRepresentanteAgenteByFecha(new BigDecimal(2), idPropuesta);
    }

    @Override
    public List<FecetAsociado> getAgenteAduanal(BigDecimal idPropuesta) {
        return fecetAsociadoDao.getRepresentanteAgenteByFecha(new BigDecimal(Constantes.ENTERO_CUATRO), idPropuesta);
    }

    @Override
    public void actualizaEstatusPropuesta(BigDecimal idPropuesta) {
        //idPropuesta idEstatus
        fecetPropuestaDao.actualizarEstatus(idPropuesta,
                ConstantesPropuestas.PROPUESTA_RECHAZADA_ADECUAR_AUDITOR);
    }

    @Override
    public void enviaCorreo(String asunto, String idRegistro, ReportType reportType, String correo) {
        Map<String, String> data = new HashMap<String, String>();
        Set<String> destinatarios = new TreeSet<String>();
        destinatarios.add(correo);
        Calendar date = new GregorianCalendar();
        try {
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(),
                    "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(),
                    NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(),
                    new SimpleDateFormat("HH:mm").format(date.getTime()));
            data.put(Common.SUBJECT.toString(),
                    asunto);
            data.put("Id_Registro", idRegistro);
            getNotificacionService().enviarNotificacion(data, reportType, destinatarios);
        } catch (Exception e) {
            logger.error("Error al enviar el correo: {}", e.getMessage());
        }
    }

    @Override
    public String obtenerToCorreo(String rfc) {
        String correo = "";
        try {
            return empleadoService.getEmpleadoCompleto(rfc).getCorreo();            
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el correo del auditor {}: {}", rfc, e.getMessage());
        }
        return correo;
    }

    @Override
    public String obtenerToCorreoIdEmpleado(BigDecimal idEmpleado) {
        String correo = "";
        try {
            return empleadoService.getEmpleadoCompleto(idEmpleado.intValue()).getCorreo();            
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el correo del empleado {}: {}", idEmpleado, e.getMessage());
        }
        return correo;
    }

    @Override
    public BigDecimal obtenerIdArace(BigDecimal idEmpleado) {
        return fecetDetalleEmpleadoDao.findWhereIdEmpleadoEquals(idEmpleado).get(0).getIdArace();
    }

    public void setFecetDocRechazoPropuestaDao(FecetDocRechazoPropuestaDao fecetDocRechazoPropuestaDao) {
        this.fecetDocRechazoPropuestaDao = fecetDocRechazoPropuestaDao;
    }

    public FecetDocRechazoPropuestaDao getFecetDocRechazoPropuestaDao() {
        return fecetDocRechazoPropuestaDao;
    }

    public void setFecetDocOrdenDao(FecetDocOrdenDao fecetDocOrdenDao) {
        this.fecetDocOrdenDao = fecetDocOrdenDao;
    }

    public FecetDocOrdenDao getFecetDocOrdenDao() {
        return fecetDocOrdenDao;
    }

    public void setFecetDocExpedienteDao(FecetDocExpedienteDao fecetDocExpedienteDao) {
        this.fecetDocExpedienteDao = fecetDocExpedienteDao;
    }

    public FecetDocExpedienteDao getFecetDocExpedienteDao() {
        return fecetDocExpedienteDao;
    }

    public void setFecetRechazoPropuestaDao(FecetRechazoPropuestaDao fecetRechazoPropuestaDao) {
        this.fecetRechazoPropuestaDao = fecetRechazoPropuestaDao;
    }

    public FecetRechazoPropuestaDao getFecetRechazoPropuestaDao() {
        return fecetRechazoPropuestaDao;
    }

    public void setFecetAsociadoDao(FecetAsociadoDao fecetAsociadoDao) {
        this.fecetAsociadoDao = fecetAsociadoDao;
    }

    public FecetAsociadoDao getFecetAsociadoDao() {
        return fecetAsociadoDao;
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setMailServices(MailServices mailServices) {
        this.mailServices = mailServices;
    }

    public MailServices getMailServices() {
        return mailServices;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setFecetDetalleEmpleadoDao(FecetDetalleEmpleadoDao fecetDetalleEmpleadoDao) {
        this.fecetDetalleEmpleadoDao = fecetDetalleEmpleadoDao;
    }

    public FecetDetalleEmpleadoDao getFecetDetalleEmpleadoDao() {
        return fecetDetalleEmpleadoDao;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    @Override
    public BigDecimal obtenerIdEmpleado(String rfc) {
        
        BigDecimal idEmpleado = BigDecimal.ONE;
        
        try {
            EmpleadoDTO empleado = getEmpleadoCompleto(rfc);
            idEmpleado = empleado.getIdEmpleado();
        } catch (EmpleadoServiceException e) {
            logger.error("No se encontro el empleado");
        }
        
        return idEmpleado;
    }
}
