package mx.gob.sat.siat.feagace.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoPerfilEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FececPerfilService;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetSuplenciaService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 * Clase que maneja las peticiones para la pantalla de firmante suplente. En
 * caso de que el administrador sea suplente, se deben seleccionar el RFC a
 * quien esta supliendo
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
@ManagedBean(name = "firmanteSuplente")
@SessionScoped
public class FirmanteSuplenteMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = -2506105417033751986L;

    @ManagedProperty(value = "#{fececPerfilService}")
    private transient FececPerfilService fececPerfilService;

    @ManagedProperty(value = "#{fecetSuplenciaService}")
    private transient FecetSuplenciaService fecetSuplenciaService;

    private Integer perfilSeleccionado;
    private Integer suplenteSeleccionado;
    private boolean suplente;
    private List<FececPerfil> perfiles = new ArrayList<FececPerfil>();
    private List<FecetSuplencia> suplentes = new ArrayList<FecetSuplencia>();

    private boolean mostrarRfc = false;

    private transient HttpSession session;

    @PostConstruct
    public void init() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        perfiles = fececPerfilService.obtenerPerfiles();
    }

    /**
     *
     * @return
     */
    public String firmarDocumentos() {
        logger.info("Voy a firmar documentos...");
        suplente = false;
        if (perfilSeleccionado == BigDecimal.ZERO.intValue()) {
            FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("label.error.seleccion.perfil"));
            return "";
        } else if (perfilSeleccionado.compareTo(TipoPerfilEnum.SUPLENTE.getId()) == 0 && new BigDecimal(suplenteSeleccionado).compareTo(BigDecimal.ZERO) == 0) {
            FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("label.error.seleccion.suplente"));
            return "";
        } else if (perfilSeleccionado.intValue() == TipoPerfilEnum.SUPLENTE.getId().intValue()) {
            suplente = true;
        }
        return "indexValidarFirmarOrdenes?faces-redirect=true";
    }

    /**
     * Metodo que maneja el evento de seleccionar el tipo de perfil
     *
     * @param event
     */
    public void handleChange(AjaxBehaviorEvent event) {
        logger.info("Entro al evento...");
        if (perfilSeleccionado.compareTo(TipoPerfilEnum.SUPLENTE.getId()) == 0) {
            UserProfileDTO userProfileDTO = (UserProfileDTO) session.getAttribute("userProfile");
            EmpleadoDTO empleadoDTO;
            try {
                empleadoDTO = getEmpleadoService().getEmpleadoCompleto(userProfileDTO.getRfc());
                if (empleadoDTO != null) {
                    suplentes = fecetSuplenciaService.obtenerSuplentesPorSuplente(empleadoDTO.getIdEmpleado());
                } else {
                    FacesUtil.addErrorMessage(null, "No existen el firmante seleccionado");
                }

            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
            logger.info("Suplentes: " + suplentes.size());
            if (suplentes.isEmpty()) {
                FacesUtil.addErrorMessage(null, "No existen suplentes para el firmante");
                perfilSeleccionado = BigDecimal.ZERO.intValue();
            } else {
                mostrarRfc = true;
            }
        } else {
            mostrarRfc = false;
        }
    }

    /**
     * Metodo para validar el el rfc a quin se va a suplir
     *
     * @param event
     */
    public void handleChangeSuplente(AjaxBehaviorEvent event) {
        logger.info("Voy a validar a quien voy a suplir...");
        FecetSuplencia suplir = new FecetSuplencia();
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);
        for (FecetSuplencia fs : suplentes) {
            if (fs.getIdFirmanteASuplir().compareTo(new BigDecimal(suplenteSeleccionado)) == 0) {
                suplir = fs;
                break;
            }
        }
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(suplir.getFechaInicio());
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.setTime(suplir.getFechaFin());

        // Entra en la condicion si el rango de fechas no coincide
        if (!(hoy.getTime().compareTo(fechaInicio.getTime()) >= 0 && hoy.getTime().compareTo(fechaFin.getTime()) <= 0)) {
            logger.info("El empleado a suplir no esta en el rango de fechas...");
            FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("label.error.suplente.rfc"));
            suplenteSeleccionado = BigDecimal.ZERO.intValue();
        }
    }

    public void setPerfilSeleccionado(Integer perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public Integer getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setMostrarRfc(boolean mostrarRfc) {
        this.mostrarRfc = mostrarRfc;
    }

    public boolean isMostrarRfc() {
        return mostrarRfc;
    }

    public void setSuplenteSeleccionado(Integer suplenteSeleccionado) {
        this.suplenteSeleccionado = suplenteSeleccionado;
    }

    public Integer getSuplenteSeleccionado() {
        return suplenteSeleccionado;
    }

    public void setPerfiles(List<FececPerfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<FececPerfil> getPerfiles() {
        return perfiles;
    }

    public void setFececPerfilService(FececPerfilService fececPerfilService) {
        this.fececPerfilService = fececPerfilService;
    }

    public FececPerfilService getFececPerfilService() {
        return fececPerfilService;
    }

    public void setSuplentes(List<FecetSuplencia> suplentes) {
        this.suplentes = suplentes;
    }

    public List<FecetSuplencia> getSuplentes() {
        return suplentes;
    }

    public void setFecetSuplenciaService(FecetSuplenciaService fecetSuplenciaService) {
        this.fecetSuplenciaService = fecetSuplenciaService;
    }

    public FecetSuplenciaService getFecetSuplenciaService() {
        return fecetSuplenciaService;
    }

    public boolean isSuplente() {
        return suplente;
    }

    public final void setSuplente(boolean suplente) {
        this.suplente = suplente;
    }
}
