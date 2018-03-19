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

import org.apache.log4j.Logger;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoPerfilEnum;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FececPerfilService;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetSuplenciaService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;

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

    private transient Logger logger = Logger.getLogger(this.getClass());

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
            addErrorMessage(null, getMessageResourceString("label.error.seleccion.perfil"));
            return "";
        } else if (perfilSeleccionado.compareTo(TipoPerfilEnum.SUPLENTE.getId()) == 0
                && new BigDecimal(suplenteSeleccionado).compareTo(BigDecimal.ZERO) == 0) {
            addErrorMessage(null, getMessageResourceString("label.error.seleccion.suplente"));
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
            EmpleadoDTO empleado;
            try {
                empleado = fecetSuplenciaService.getEmpleadoFirmante(userProfileDTO.getRfc());

                suplentes = fecetSuplenciaService.obtenerSuplentesPorSuplente(empleado.getIdEmpleado());

            } catch (NegocioException e) {
                addErrorMessage(null, "No existen el firmante seleccionado");
            }
            logger.info("Suplentes: " + suplentes.size());
            if (suplentes.isEmpty()) {
                addErrorMessage(null, "No existen suplentes para el firmante");
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

        hoy.set(Calendar.MILLISECOND, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);

        for (FecetSuplencia fs : suplentes) {
            if (fs.getIdFirmanteASuplir().compareTo(new BigDecimal(suplenteSeleccionado)) == 0) {
                suplir = fs;
                break;
            }
        }
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(suplir.getFechaInicio());
        fechaInicio.set(Calendar.MILLISECOND, 0);
        fechaInicio.set(Calendar.SECOND, 0);
        fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
        fechaInicio.set(Calendar.MINUTE, 0);

        Calendar fechaFin = Calendar.getInstance();
        fechaFin.setTime(suplir.getFechaFin());

        fechaFin.set(Calendar.MILLISECOND, 0);
        fechaFin.set(Calendar.SECOND, 0);
        fechaFin.set(Calendar.HOUR_OF_DAY, 0);
        fechaFin.set(Calendar.MINUTE, 0);

        if (hoy.compareTo(fechaInicio) < 0 || hoy.compareTo(fechaFin) > 0) {
            logger.info("El empleado a suplir no esta en el rango de fechas...");
            addErrorMessage(null, "El RFC seleccionado no est\u00e1 en el rango de fechas");
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
}
