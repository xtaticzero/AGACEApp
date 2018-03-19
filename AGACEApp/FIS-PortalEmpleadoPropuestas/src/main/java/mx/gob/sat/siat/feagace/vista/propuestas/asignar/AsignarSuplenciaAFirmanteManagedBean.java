package mx.gob.sat.siat.feagace.vista.propuestas.asignar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsignarSuplenciaAFirmanteModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteUpdateException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarSuplenciaAFirmanteService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;

@ViewScoped
@ManagedBean(name = "asignarSuplenciaAFirmanteManagedBean")
public class AsignarSuplenciaAFirmanteManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = 5395866612201205706L;
    @ManagedProperty(value = "#{asignarSuplenciaAFirmanteService}")
    private transient AsignarSuplenciaAFirmanteService asignarSuplenciaAFirmanteService;

    private AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel;
    private boolean panelTablaSuplencias;
    private boolean panelDatosSuplencias;
    private boolean mostrarCheckBox;
    private List<EmpleadoDTO> listaEmp = new ArrayList<EmpleadoDTO>();
    private BigDecimal seleccionMotivo;
    private BigDecimal seleccionFirmanteASuplir;
    private BigDecimal seleccionFirmanteSuplente;
    private Date fechaLimiteInicial;
    private Date fechaLimiteFinal;
    private boolean botonAsignarHabilitado;
    private boolean panelConfirmacionVisible;
    private EmpleadoDTO empleadoDTO;

    @PostConstruct
    public void init() {
        try {
            panelTablaSuplencias = true;
            panelDatosSuplencias = false;
            setBotonAsignarHabilitado(true);

            listaEmp = asignarSuplenciaAFirmanteService.getEmpleadoFirmante(getRFCSession());

            if (!listaEmp.isEmpty()) {
                setEmpleadoDTO(empleadoDTO);
                logger.info("listaEmp.get(0) [{}] ", listaEmp.get(0));
                asignarSuplenciaAFirmanteModel = new AsignarSuplenciaAFirmanteModel();
                asignarSuplenciaAFirmanteModel.setNuevaSuplencia(new FecetSuplenciaDTO());
                asignarSuplenciaAFirmanteModel.setListaSuplencias(
                        asignarSuplenciaAFirmanteService.findListaSuplencia(listaEmp));
            }

        } catch (NegocioException e) {
            logger.error(e.getMessage());
            addErrorMessage(null, "Error Loggeo", "Error al consultar el servicio");
        }
    }

    public void limpiarFormulario() {
        asignarSuplenciaAFirmanteModel = new AsignarSuplenciaAFirmanteModel();
        asignarSuplenciaAFirmanteModel.setNuevaSuplencia(new FecetSuplenciaDTO());
        seleccionFirmanteSuplente = null;
        listaEmp = null;
        mostrarCheckBox = false;
        setBotonAsignarHabilitado(true);
        setPanelConfirmacionVisible(false);
        cargaInformacionNuevaSuplencia();
    }

    public void cargaInformacionNuevaSuplencia() {
        try {
            seleccionMotivo = Constantes.BIG_DECIMAL_CERO;
            seleccionFirmanteASuplir = Constantes.BIG_DECIMAL_CERO;
            seleccionFirmanteSuplente = Constantes.BIG_DECIMAL_CERO;
            panelTablaSuplencias = false;
            panelDatosSuplencias = true;

            asignarSuplenciaAFirmanteModel.setComboFirmanteASuplir(listaEmp);
            asignarSuplenciaAFirmanteService.obtenerMotivosSuplencia(asignarSuplenciaAFirmanteModel);
            this.setFechaLimiteInicial(getSystemDate());
            this.setFechaLimiteFinal(getSystemDate());
        } catch (AsignarSuplenciaAFirmanteUpdateException e) {
            logger.error(e.getMessage());
            addErrorMessage(Constantes.FALLO_CONSULTA);
        }
    }

    public void selectedFirmanteASuplir() {
        try {
            if (seleccionFirmanteASuplir != null && seleccionFirmanteASuplir.intValue() > 0) {
                asignarSuplenciaAFirmanteService.obtenerEmpleadoASuplir(seleccionFirmanteASuplir,
                        asignarSuplenciaAFirmanteModel);
            } else {
                asignarSuplenciaAFirmanteModel.setFirmanteASuplir(null);
            }
        } catch (NoExisteEmpleadoException e) {
            logger.error(e.getMessage());
            addErrorMessage(Constantes.FALLO_CONSULTA);
        }
        limpiaFechaFinal();
    }

    public void selectedMotivo() {
        if (seleccionMotivo != null && seleccionMotivo.intValue() > 0) {
            asignarSuplenciaAFirmanteModel.getNuevaSuplencia().setIdMotivoSuplencia(seleccionMotivo);
        } else {
            asignarSuplenciaAFirmanteModel.getNuevaSuplencia().setIdMotivoSuplencia(null);
        }
        limpiaFechaFinal();
    }

    public void selectedDisponibles() {
        try {
            if (!validaCamposNuevaSupencia()) {
                return;
            }
            asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                    .setIdFirmanteASuplir(asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getIdEmpleado());
            if (asignarSuplenciaAFirmanteService.buscarSuplenciaRegistrada(asignarSuplenciaAFirmanteModel)) {
                if (asignarSuplenciaAFirmanteService.buscarSuplenciaRegistradaSuplir(asignarSuplenciaAFirmanteModel)) {
                    asignarSuplenciaAFirmanteService.cargaFirmantesSuplentesDisponibles(asignarSuplenciaAFirmanteModel, empleadoDTO, listaEmp);
                } else {
                    asignarSuplenciaAFirmanteModel.setCombolistaFirmanteSuplente(null);
                    botonAsignarHabilitado = true;
                    addWarningMessage(Constantes.MSJ_SUPLENCIA_YA_EXISTE);
                }
            } else {
                asignarSuplenciaAFirmanteModel.setCombolistaFirmanteSuplente(null);
                botonAsignarHabilitado = true;
                addWarningMessage(Constantes.MSJ_SUPLENCIA_ASIGNADA);
            }
            seleccionFirmanteSuplente = Constantes.BIG_DECIMAL_CERO;
        } catch (Exception e) {
            botonAsignarHabilitado = true;
            logger.error(e.getMessage());
            /**
             * addWarningMessage(e.getMesa);
             */
        }
    }

    public void limpiaFechaFinal() {
        asignarSuplenciaAFirmanteModel.setCombolistaFirmanteSuplente(null);
        getAsignarSuplenciaAFirmanteModel().getNuevaSuplencia().setFechaFin(null);
        asignarSuplenciaAFirmanteModel.setFirmanteSuplente(new EmpleadoDTO());
        botonAsignarHabilitado = true;
        seleccionFirmanteSuplente = Constantes.BIG_DECIMAL_CERO;
    }

    public void selectedFirmanteSuplente() {
        try {
            if (seleccionFirmanteSuplente != null && seleccionFirmanteSuplente.intValue() > 0) {
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().setIdFrimanteSuplente(seleccionFirmanteSuplente);
                if (asignarSuplenciaAFirmanteService.buscarSuplenciaRegistrada(asignarSuplenciaAFirmanteModel)) {
                    asignarSuplenciaAFirmanteService.obtenerEmpleadoSuplente(seleccionFirmanteSuplente,
                            asignarSuplenciaAFirmanteModel);
                } else {
                    addWarningMessage(Constantes.MSJ_SUPLENCIA_REGISTRO_A_ALMACENADO);
                }
                botonAsignarHabilitado = false;
            } else {
                asignarSuplenciaAFirmanteModel.setFirmanteSuplente(null);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            addErrorMessage(Constantes.FALLO_CONSULTA);
        }
    }

    private static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    public void eliminarSuplencia() {
        String id = MetodosGenericos.getParametro("id").toString();
        String estatus = MetodosGenericos.getParametro("estatus").toString();

        try {
            asignarSuplenciaAFirmanteService.eliminarSuplencia(id, estatus);

            asignarSuplenciaAFirmanteModel.setListaSuplencias(
                    asignarSuplenciaAFirmanteService.findListaSuplencia(listaEmp));
            panelDatosSuplencias = false;
            addMessage("La suplencia se cancel\u00f3 correctamente");
        } catch (AsignarSuplenciaAFirmanteUpdateException e) {
            logger.error(e.getMessage());
            addErrorMessage("Error al cancelar suplencia " + e.getSituation());
        }
    }

    public void asignarSuplencia() {
        if (!validaCamposNuevaSupencia()) {
            return;
        } else if (asignarSuplenciaAFirmanteModel.getFirmanteSuplente() == null) {
            addWarningMessage("Seleccione un firmante suplente");
            return;
        }
        asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                .setIdFirmanteASuplir(asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getIdEmpleado());
        asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                .setNombreFirmanteASuplir(asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getNombre());
        asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                .setIdFrimanteSuplente(asignarSuplenciaAFirmanteModel.getFirmanteSuplente().getIdEmpleado());
        asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                .setNombreFirmanteSuplente(asignarSuplenciaAFirmanteModel.getFirmanteSuplente().getNombre());
        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().setIdStatus(new BigDecimal(2L));
        try {
            asignarSuplenciaAFirmanteService.almacenarSuplencia(asignarSuplenciaAFirmanteModel);
            limpiarFormulario();
            cargaInformacionNuevaSuplencia();
            init();
            addMessage("La suplencia se almacen\u00f3 correctamente");
        } catch (AsignarSuplenciaAFirmanteUpdateException e) {
            logger.error(e.getMessage());
            addErrorMessage("Error al almacenar la suplencia");
        }
    }

    public void prevalidacionAsignacion() {
        try {
            asignarSuplenciaAFirmanteModel.getNuevaSuplencia()
                    .setIdFirmanteASuplir(asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getIdEmpleado());
            if (asignarSuplenciaAFirmanteService.suplenteTieneSuplencia(asignarSuplenciaAFirmanteModel)) {
                asignarSuplencia();
            } else {
                setPanelConfirmacionVisible(true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            addErrorMessage("Error al almacenar la suplencia");
        }
    }

    private boolean validaCamposNuevaSupencia() {
        if (asignarSuplenciaAFirmanteModel.getFirmanteASuplir() == null) {
            addWarningMessage("Seleccione un firmante a suplir");
            return false;
        } else if (asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getIdMotivoSuplencia() == null) {
            addWarningMessage("Seleccione un motivo de suplencia");
            return false;
        } else if (asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio() == null) {
            addWarningMessage("Seleccione la fecha inicial");
            return false;
        } else if (asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin() == null) {
            addWarningMessage("Seleccione la fecha final");
            return false;
        } else if (asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin()
                .before(asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio())) {
            addWarningMessage("La fecha inicial no puede ser mayor a fecha final");
            return false;
        }
        return true;
    }

    public void cancelarBtn() throws AsignarSuplenciaAFirmanteUpdateException {
        init();
    }

    public void setAsignarSuplenciaAFirmanteService(AsignarSuplenciaAFirmanteService asignarSuplenciaAFirmanteService) {
        this.asignarSuplenciaAFirmanteService = asignarSuplenciaAFirmanteService;
    }

    public AsignarSuplenciaAFirmanteService getAsignarSuplenciaAFirmanteService() {
        return asignarSuplenciaAFirmanteService;
    }

    public void setAsignarSuplenciaAFirmanteModel(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) {
        this.asignarSuplenciaAFirmanteModel = asignarSuplenciaAFirmanteModel;
    }

    public AsignarSuplenciaAFirmanteModel getAsignarSuplenciaAFirmanteModel() {
        return asignarSuplenciaAFirmanteModel;
    }

    public void setPanelTablaSuplencias(boolean panelTablaSuplencias) {
        this.panelTablaSuplencias = panelTablaSuplencias;
    }

    public boolean isPanelTablaSuplencias() {
        return panelTablaSuplencias;
    }

    public void setPanelDatosSuplencias(boolean panelDatosSuplencias) {
        this.panelDatosSuplencias = panelDatosSuplencias;
    }

    public boolean isPanelDatosSuplencias() {
        return panelDatosSuplencias;
    }

    public void setListaEmp(List<EmpleadoDTO> listaEmp) {
        this.listaEmp = listaEmp;
    }

    public List<EmpleadoDTO> getListaEmp() {
        return listaEmp;
    }

    public void setSeleccionMotivo(BigDecimal seleccionMotivo) {
        this.seleccionMotivo = seleccionMotivo;
    }

    public BigDecimal getSeleccionMotivo() {
        return seleccionMotivo;
    }

    public void setSeleccionFirmanteASuplir(BigDecimal seleccionFirmanteASuplir) {
        this.seleccionFirmanteASuplir = seleccionFirmanteASuplir;
    }

    public BigDecimal getSeleccionFirmanteASuplir() {
        return seleccionFirmanteASuplir;
    }

    public void setSeleccionFirmanteSuplente(BigDecimal seleccionFirmanteSuplente) {
        this.seleccionFirmanteSuplente = seleccionFirmanteSuplente;
    }

    public BigDecimal getSeleccionFirmanteSuplente() {
        return seleccionFirmanteSuplente;
    }

    public void setMostrarCheckBox(boolean mostrarCheckBox) {
        this.mostrarCheckBox = mostrarCheckBox;
    }

    public boolean isMostrarCheckBox() {
        return mostrarCheckBox;
    }

    public void setFechaLimiteInicial(Date fechaLimiteInicial) {
        this.fechaLimiteInicial = fechaLimiteInicial != null ? new Date(fechaLimiteInicial.getTime()) : null;
    }

    public Date getFechaLimiteInicial() {
        return (fechaLimiteInicial != null) ? (Date) fechaLimiteInicial.clone() : null;

    }

    public void setFechaLimiteFinal(Date fechaLimiteFinal) {
        this.fechaLimiteFinal = fechaLimiteFinal != null ? new Date(fechaLimiteFinal.getTime()) : null;
    }

    public Date getFechaLimiteFinal() {
        return (fechaLimiteFinal != null) ? (Date) fechaLimiteFinal.clone() : null;

    }

    public boolean isBotonAsignarHabilitado() {
        return botonAsignarHabilitado;
    }

    public void setBotonAsignarHabilitado(boolean botonAsignarHabilitado) {
        this.botonAsignarHabilitado = botonAsignarHabilitado;
    }

    public boolean isPanelConfirmacionVisible() {
        return panelConfirmacionVisible;
    }

    public void setPanelConfirmacionVisible(boolean panelConfirmacionVisible) {
        this.panelConfirmacionVisible = panelConfirmacionVisible;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }
}
