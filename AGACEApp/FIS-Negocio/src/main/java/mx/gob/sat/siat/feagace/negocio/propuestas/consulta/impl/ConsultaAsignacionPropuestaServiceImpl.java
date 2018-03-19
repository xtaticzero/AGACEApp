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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececCentralDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececFirmanteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececSubadministradorDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaApartadoPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultaAsignacionPropuestaService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("consultaAsignacionPropuestaService")
public class ConsultaAsignacionPropuestaServiceImpl extends PropuestasServiceAbstract implements ConsultaAsignacionPropuestaService {

    private static final long serialVersionUID = 3281726881876105248L;

    private static final int NUEVE = 9;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FececFirmanteDao fececFirmanteDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FececSubadministradorDao fececSubadministradorDao;
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
    private transient FececCentralDao fececCentralDao;
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FeceaApartadoPrioridadDao feceaApartadoPrioridadDao;

    public List<BigDecimal> listaIdAraceRegional() {
        List<BigDecimal> listaAdaceRegional = new ArrayList<BigDecimal>();
        listaAdaceRegional.add(Constantes.ARACE_PACIFICO_NORTE);
        listaAdaceRegional.add(Constantes.ARACE_NORTE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_NORESTE);
        listaAdaceRegional.add(Constantes.ARACE_OCCIDENTE);
        listaAdaceRegional.add(Constantes.ARACE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_SUR);

        return listaAdaceRegional;
    }

    /**
     * Metodo traeDescripcionMetodo
     *
     * @return
     * @throws NegocioException
     */
    @Override
    public List<FececMetodo> traeDescripcionMetodo(final BigDecimal idMetodo) {

        return feceaMetodoDao.findWhereIdMetodo(idMetodo);

    }

    /**
     * Metodo traeCausaProgramacion
     *
     * @return
     * @throws NegocioException
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
     * @throws NegocioException
     */
    @Override
    public List<FecetImpuestoDescripcion> traeImpuestosDescripcion(final BigDecimal idPropuesta) {

        return fecetImpuestoDao.traeImpuestoDescripcion(idPropuesta);

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
    public List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarFirmante(final BigDecimal idArace, List<String> prioridades, boolean esGridInicial,
            final Object... args) {
        return fecetPropuestaDao.getPropuestasRegionalAsignarFirmante(idArace, prioridades, esGridInicial, args);
    }

    @Override
    public List<FececCentral> getAraceCentral(final String rfcCentral) {
        return fececCentralDao.findWhereRfcEquals(rfcCentral);
    }

    @Override
    public List<FecetDocExpediente> traeExpedientesCargados(final BigDecimal idPropuesta) {
        return fecetDocExpedienteDao.findWhereIdPropuestaEquals(idPropuesta);
    }

    @Override
    public List<FececFirmante> getFirmantes(final BigDecimal idArace) {

        return fececFirmanteDao.findWhereIdAraceEquals(idArace);

    }

    // Metodo obsoleto
    @Override
    public void guardarAsignacionSubadministrador(final FecetPropuesta insumo) {
        fecetPropuestaDao.update(new FecetPropuestaPk(insumo.getIdPropuesta()), insumo);
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardarAsignacionFirmante(final FecetPropuesta propuesta) {
        fecetPropuestaDao.actualizarPropuestaFirmante(propuesta);
        return propuesta.getIdPropuesta();
    }

    public List<ContadorInsumosSubAdmin> getContadorPropuestas(List<EmpleadoDTO> list, BigDecimal idArace) {
        List<ContadorInsumosSubAdmin> cont = new ArrayList<ContadorInsumosSubAdmin>();
        ContadorInsumosSubAdmin contador;
        for (EmpleadoDTO empleado : list) {
            contador = fecetPropuestaDao.getContadorPropuestasFirmante(empleado.getRfc(), idArace).get(0);
            contador.setNombre(empleado.getNombreCompleto());
            cont.add(contador);
        }
        return cont;
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuestas(BigDecimal idAdministrador) {

        return fecetPropuestaDao.getContadorPropuestasSubAdministradores();
    }

    @Override
    public void enviarCorreoConfirmacion(PropuestaOrigenCentralRegDTO propuestaDto, BigDecimal idLeyenda, String rfcSesion, String rfcFirmante) {
        Set<String> destinatarios = new TreeSet<String>();
        notificacionService.obtenerCorreoEmpleado(rfcFirmante, TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(rfcSesion, TipoEmpleadoEnum.CONSULTOR_INSUMOS.getBigId(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuestaDto.getPropuesta().getRfcCreacion(), TipoEmpleadoEnum.PROGRAMADOR.getBigId(), destinatarios,
                ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoCentralAcppce(rfcSesion, TipoEmpleadoEnum.CONSULTOR_INSUMOS.getId(), propuestaDto.getPropuesta().getIdArace(), destinatarios,
                ClvSubModulosAgace.PROPUESTAS);
        // agregar el correo del programador
        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuestaDto.getPropuesta().getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuestaDto.getPropuesta().getIdRegistro());
        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause());
        }
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setFecetDocExpedienteDao(FecetDocExpedienteDao fecetDocExpedienteDao) {
        this.fecetDocExpedienteDao = fecetDocExpedienteDao;
    }

    public FecetDocExpedienteDao getFecetDocExpedienteDao() {
        return fecetDocExpedienteDao;
    }

    public void setFececSubadministradorDao(final FececSubadministradorDao fececSubadministradorDao) {
        this.fececSubadministradorDao = fececSubadministradorDao;
    }

    public FececSubadministradorDao getFececSubadministradorDao() {
        return fececSubadministradorDao;
    }

    public void setFececFirmanteDao(FececFirmanteDao fececFirmanteDao) {
        this.fececFirmanteDao = fececFirmanteDao;
    }

    public FececFirmanteDao getFececFirmanteDao() {
        return fececFirmanteDao;
    }

    public void setFeceaMetodoDao(FeceaMetodoDao feceaMetodoDao) {
        this.feceaMetodoDao = feceaMetodoDao;
    }

    public FeceaMetodoDao getFeceaMetodoDao() {
        return feceaMetodoDao;
    }

    public void setFececCausaProgramacionDao(FececCausaProgramacionDao fececCausaProgramacionDao) {
        this.fececCausaProgramacionDao = fececCausaProgramacionDao;
    }

    public FececCausaProgramacionDao getFececCausaProgramacionDao() {
        return fececCausaProgramacionDao;
    }

    public void setFececTipoPropuestaDao(FececTipoPropuestaDao fececTipoPropuestaDao) {
        this.fececTipoPropuestaDao = fececTipoPropuestaDao;
    }

    public FececTipoPropuestaDao getFececTipoPropuestaDao() {
        return fececTipoPropuestaDao;
    }

    public void setFececRevisionDao(FececRevisionDao fececRevisionDao) {
        this.fececRevisionDao = fececRevisionDao;
    }

    public FececRevisionDao getFececRevisionDao() {
        return fececRevisionDao;
    }

    public void setFecetImpuestoDao(FecetImpuestoDao fecetImpuestoDao) {
        this.fecetImpuestoDao = fecetImpuestoDao;
    }

    public FecetImpuestoDao getFecetImpuestoDao() {
        return fecetImpuestoDao;
    }

    public void setFececCentralDao(FececCentralDao fececCentralDao) {
        this.fececCentralDao = fececCentralDao;
    }

    public FececCentralDao getFececCentralDao() {
        return fececCentralDao;
    }

    public void setFececSubprogramaDao(FececSubprogramaDao fececSubprogramaDao) {
        this.fececSubprogramaDao = fececSubprogramaDao;
    }

    public FececSubprogramaDao getFececSubprogramaDao() {
        return fececSubprogramaDao;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @Override
    public EmpleadoDTO traeDatosCentral(String rfc) {
        EmpleadoDTO empleado = null;

        try {

            if (empleadoService.validarExistenciaTipoEmpleado(getEmpleadoCompleto(rfc), TipoEmpleadoEnum.CONSULTOR_INSUMOS)) {
                empleado = getEmpleadoCompleto(rfc);
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

    @Override
    public List<String> traePrioridadGrid() {
        return feceaApartadoPrioridadDao.getPrioridad("Propuestas Pendientes por Asignar Grid", NUEVE);
    }

    @Override
    public List<String> traePrioridadFiltro() {
        return feceaApartadoPrioridadDao.getPrioridad("Propuestas Pendientes por Asignar Filtros", NUEVE);
    }

}
