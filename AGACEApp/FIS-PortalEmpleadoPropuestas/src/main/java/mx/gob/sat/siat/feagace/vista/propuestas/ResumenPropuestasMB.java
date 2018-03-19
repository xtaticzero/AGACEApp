package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ContadorPropAsignadasService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasBooleanHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasEstatusHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.managedbean.ConsultarPropuestasAsignadasManagedBean;

@ManagedBean(name = "resumenPropuestasMB")
@ViewScoped
public class ResumenPropuestasMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 8602216293930363482L;

    private ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper;
    private ConsultarPropuestasAsignadasBooleanHelper cpaBooleanHelper;
    private ConsultarPropuestasAsignadasEstatusHelper cpaEstatusHelper;
    @ManagedProperty(value = "#{consultarPropuestasAsignadasManagedBean}")
    private ConsultarPropuestasAsignadasManagedBean consultarPropuestasAsignadasManagedBean;

    private EmpleadoDTO usuarioAuditor;

    @ManagedProperty(value = "#{contadorPropAsignadasService}")
    private transient ContadorPropAsignadasService contadorPropAsignadasService;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;

    @PostConstruct
    public void init() {
        this.consultarPropAsignadaHelper = new ConsultarPropuestasAsignadasHelper();
        this.cpaBooleanHelper = new ConsultarPropuestasAsignadasBooleanHelper();
        this.cpaEstatusHelper = new ConsultarPropuestasAsignadasEstatusHelper();
        this.usuarioAuditor = new EmpleadoDTO();
    }

    public void cargaDatos() {
        traeDatosAuditor();
    }

    private void cargarResumen() {
        logger.debug("Cargando Resumen de Propuestas");
        consultarPropAsignadaHelper.setResumenPropuestasAsignadas(
                contadorPropAsignadasService.getResumenPropuestasAsignadas(getRFCSession()));
    }

    private void traeDatosAuditor() {

        EmpleadoDTO auditorLogueado = consultarPropuestasAsignadasService.obtenerDatosAuditor(getRFCSession());

        if (auditorLogueado != null) {
            setUsuarioAuditor(auditorLogueado);
            cargarResumen();
        } else {
            this.informeErrorSession(new NegocioException("No se encontro el usuario solicitado"));
        }
    }

    public String muestraPropuestas() {
        consultarPropuestasAsignadasManagedBean.listadoPropuestas();
        return "detallePropAsignadas?faces-redirect=true";
    }

    private void informeErrorSession(Exception e) {
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

    public ConsultarPropuestasAsignadasHelper getConsultarPropAsignadaHelper() {
        return consultarPropAsignadaHelper;
    }

    public void setConsultarPropAsignadaHelper(ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper) {
        this.consultarPropAsignadaHelper = consultarPropAsignadaHelper;
    }

    public ContadorPropAsignadasService getContadorPropAsignadasService() {
        return contadorPropAsignadasService;
    }

    public void setContadorPropAsignadasService(ContadorPropAsignadasService contadorPropAsignadasService) {
        this.contadorPropAsignadasService = contadorPropAsignadasService;
    }

    public ConsultarPropuestasAsignadasBooleanHelper getCpaBooleanHelper() {
        return cpaBooleanHelper;
    }

    public void setCpaBooleanHelper(ConsultarPropuestasAsignadasBooleanHelper cpaBooleanHelper) {
        this.cpaBooleanHelper = cpaBooleanHelper;
    }

    public ConsultarPropuestasAsignadasEstatusHelper getCpaEstatusHelper() {
        return cpaEstatusHelper;
    }

    public void setCpaEstatusHelper(ConsultarPropuestasAsignadasEstatusHelper cpaEstatusHelper) {
        this.cpaEstatusHelper = cpaEstatusHelper;
    }

    public ConsultarPropuestasAsignadasManagedBean getConsultarPropuestasAsignadasManagedBean() {
        return consultarPropuestasAsignadasManagedBean;
    }

    public void setConsultarPropuestasAsignadasManagedBean(
            ConsultarPropuestasAsignadasManagedBean consultarPropuestasAsignadasManagedBean) {
        this.consultarPropuestasAsignadasManagedBean = consultarPropuestasAsignadasManagedBean;
    }

    public EmpleadoDTO getUsuarioAuditor() {
        return usuarioAuditor;
    }

    public void setUsuarioAuditor(EmpleadoDTO usuarioAuditor) {
        this.usuarioAuditor = usuarioAuditor;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void setConsultarPropuestasAsignadasService(
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }
}
