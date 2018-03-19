/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.asignar;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultaAsignacionPropuestaAuditorService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.helper.AsignarPropuestaAuditorHelper;

@ViewScoped
@ManagedBean(name = "asignarPropuestaAuditorManagedBean")
public class AsignarPropuestaAuditorManagedBean extends AccesoUsuarioMBAbstract {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadas;
    private List<PropuestaOrigenCentralRegDTO> listaPropuestasPorAsignar;

    private FecetPropuesta propuestaSeleccionada;
    private Boolean muestraPropuestas;
    private Boolean muestraDialogoAsignar;

    @ManagedProperty(value = "#{consultaAsignacionPropuestaAuditorService}")
    private transient ConsultaAsignacionPropuestaAuditorService consultaAsignacionPropuestaAuditorService;

    @ManagedProperty(value = "#{asignarPropuestaAuditorHelper}")
    private AsignarPropuestaAuditorHelper asignarPropuestaAuditorHelper;
    private List<EmpleadoDTO> listaAuditores;
    private BigDecimal idAraceFirmante;
    private String rfcAuditorSeleccionado;
    private List<ContadorInsumosSubAdmin> listaContadorPropuestas;
    private EmpleadoDTO empleadoFirmante;

    private String mensaje1;
    private String mensaje2;

    private EmpleadoDTO auditorSeleccionado;

    @PostConstruct
    public void init() {
        this.listaPropuestasAsignadas = new ArrayList<PropuestaOrigenCentralRegDTO>();
        this.listaPropuestasPorAsignar = new ArrayList<PropuestaOrigenCentralRegDTO>();

        this.muestraDialogoAsignar = false;
        this.muestraPropuestas = true;
        this.listaAuditores = new ArrayList<EmpleadoDTO>();
        this.idAraceFirmante = null;
        this.rfcAuditorSeleccionado = null;
        this.propuestaSeleccionada = null;

        this.auditorSeleccionado = new EmpleadoDTO();
        limpiaFiltros("formPropuestas:tablaPropuestasAsignar");

        this.empleadoFirmante = consultaAsignacionPropuestaAuditorService.traeDatosFirmante(getRFCSession());
        cargaPropuestaAsignados();
        cargaidAraceFirmante();
        cargaAuditor();
        cargaContadorPropuestas();
    }

    private void limpiaFiltros(final String componente) {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(componente);
            dataTable.setFilteredValue(null);
            dataTable.reset();
        } catch (Exception e) {
            logger.error("No existe el componente: [{}]", componente);
        }

    }

    public void validaSeleccionAuditor() {
        if (this.listaPropuestasAsignadas != null && !listaPropuestasAsignadas.isEmpty()) {
            if (!this.rfcAuditorSeleccionado.equals("-1")) {
                this.muestraDialogoAsignar = true;
                auditorSeleccionado = asignarPropuestaAuditorHelper.obtenerAuditor(rfcAuditorSeleccionado,
                        listaAuditores);
                mensaje1 = asignarPropuestaAuditorHelper.crearMensaje1(this.listaPropuestasAsignadas.size(),
                        auditorSeleccionado.getNombre());
                mensaje2 = asignarPropuestaAuditorHelper.crearMensaje2(this.listaPropuestasAsignadas.size(),
                        auditorSeleccionado.getNombre());
                RequestContext.getCurrentInstance().execute("PF('confirmarAsignarPropuesta').show();");
            } else {
                this.muestraDialogoAsignar = false;
                addErrorMessage(null, Constantes.VERIFICAR, "Se debe seleccionar un Auditor");
            }
        } else {
            this.muestraDialogoAsignar = false;
            addErrorMessage(null, Constantes.VERIFICAR, "Se debe seleccionar una propuesta");
        }
    }

    public void asignarAuditor() {
        for (PropuestaOrigenCentralRegDTO propuestasSeleccionadas : listaPropuestasAsignadas) {
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(propuestasSeleccionadas.getPropuesta().getIdPropuesta());
            propuestasSeleccionadas.getPropuesta()
                    .setIdArace(new BigDecimal(empleadoFirmante.getDetalleEmpleado().get(0).getCentral().getIdArace()));
            propuesta.setRfcAuditor(rfcAuditorSeleccionado);
            FececEstatus fececEstatus = new FececEstatus();
            fececEstatus.setIdEstatus(BigDecimal.valueOf(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR.getId()));
            propuesta.setFececEstatus(fececEstatus);
            consultaAsignacionPropuestaAuditorService.guardarAsignacionAuditor(propuesta, empleadoFirmante);
            consultaAsignacionPropuestaAuditorService.enviarCorreoConfirmacion(propuestasSeleccionadas,
                    rfcAuditorSeleccionado);
        }
        init();
        RequestContext.getCurrentInstance().execute("PF('msg2').show();");
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public List<TipoFechasComiteEnum> getEstatusFechas() {
        return Arrays.asList(TipoFechasComiteEnum.values());
    }

    public boolean filtraFecha(Object value, Object filter, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return sdf.format(value).contains(String.valueOf(filterText));
    }

    private void cargaPropuestaAsignados() {

        this.listaPropuestasPorAsignar = (consultaAsignacionPropuestaAuditorService
                .getPropuestasAsignarAuditor(getRFCSession()));

    }

    private void cargaidAraceFirmante() {
        this.idAraceFirmante = new BigDecimal(empleadoFirmante.getDetalleEmpleado().get(0).getCentral().getIdArace());
    }

    private void cargaAuditor() {

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subor = empleadoFirmante.getSubordinados();

        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> suborFirma = subor.get(TipoEmpleadoEnum.FIRMANTE);
        this.listaAuditores = suborFirma.get(TipoEmpleadoEnum.AUDITOR);

    }

    private void cargaContadorPropuestas() {
        logger.debug("contadorPropuestas");
        this.listaContadorPropuestas = consultaAsignacionPropuestaAuditorService.getContadorPropuestas(listaAuditores,
                new BigDecimal(this.empleadoFirmante.getDetalleEmpleado().get(0).getCentral().getIdArace()));
        logger.debug("contador [{}] ", this.listaContadorPropuestas);
    }

    public void setlistaPropuestasPorAsignar(List<PropuestaOrigenCentralRegDTO> listaPropuestasPorAsignar) {
        this.listaPropuestasPorAsignar = listaPropuestasPorAsignar;
    }

    public List<PropuestaOrigenCentralRegDTO> getlistaPropuestasPorAsignar() {
        return listaPropuestasPorAsignar;
    }

    public void setpropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetPropuesta getpropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setmuestraPropuestas(Boolean muestraPropuestas) {
        this.muestraPropuestas = muestraPropuestas;
    }

    public Boolean getmuestraPropuestas() {
        return muestraPropuestas;
    }

    public void setlistaContadorPropuestas(final List<ContadorInsumosSubAdmin> listaContadorPropuestas) {
        this.listaContadorPropuestas = listaContadorPropuestas;
    }

    public List<ContadorInsumosSubAdmin> getlistaContadorPropuestas() {
        return listaContadorPropuestas;
    }

    public void setMuestraDialogoAsignar(Boolean muestraDialogoAsignar) {
        this.muestraDialogoAsignar = muestraDialogoAsignar;
    }

    public Boolean getMuestraDialogoAsignar() {
        return muestraDialogoAsignar;
    }

    public void setConsultaAsignacionPropuestaAuditorService(
            ConsultaAsignacionPropuestaAuditorService consultaAsignacionPropuestaAuditorService) {
        this.consultaAsignacionPropuestaAuditorService = consultaAsignacionPropuestaAuditorService;
    }

    public ConsultaAsignacionPropuestaAuditorService getConsultaAsignacionPropuestaAuditorService() {
        return consultaAsignacionPropuestaAuditorService;
    }

    public void setListaPropuestasPorAsignar(List<PropuestaOrigenCentralRegDTO> listaPropuestasPorAsignar) {
        this.listaPropuestasPorAsignar = listaPropuestasPorAsignar;
    }

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasPorAsignar() {
        return listaPropuestasPorAsignar;
    }

    public List<EmpleadoDTO> getListaAuditores() {
        return listaAuditores;
    }

    public void setListaAuditores(List<EmpleadoDTO> listaAuditores) {
        this.listaAuditores = listaAuditores;
    }

    public EmpleadoDTO getEmpleadoFirmante() {
        return empleadoFirmante;
    }

    public void setEmpleadoFirmante(EmpleadoDTO empleadoFirmante) {
        this.empleadoFirmante = empleadoFirmante;
    }

    public void setRfcAuditorSeleccionado(String rfcAuditorSeleccionado) {
        this.rfcAuditorSeleccionado = rfcAuditorSeleccionado;
    }

    public String getRfcAuditorSeleccionado() {
        return rfcAuditorSeleccionado;
    }

    public void setIdAraceFirmante(BigDecimal idAraceFirmante) {
        this.idAraceFirmante = idAraceFirmante;
    }

    public BigDecimal getIdAraceFirmante() {
        return idAraceFirmante;
    }

    public void setListaPropuestasAsignadas(List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignadas) {
        this.listaPropuestasAsignadas = listaPropuestasAsignadas;
    }

    public List<PropuestaOrigenCentralRegDTO> getListaPropuestasAsignadas() {
        return listaPropuestasAsignadas;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public EmpleadoDTO getAuditorSeleccionado() {
        return auditorSeleccionado;
    }

    public void setAuditorSeleccionado(EmpleadoDTO auditorSeleccionado) {
        this.auditorSeleccionado = auditorSeleccionado;
    }

    public void setAsignarPropuestaAuditorHelper(AsignarPropuestaAuditorHelper asignarPropuestaAuditorHelper) {
        this.asignarPropuestaAuditorHelper = asignarPropuestaAuditorHelper;
    }

    public AsignarPropuestaAuditorHelper getAsignarPropuestaAuditorHelper() {
        return asignarPropuestaAuditorHelper;
    }
}
