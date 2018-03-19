package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.managedbean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.ordenes.firma.FirmanteOrdenSuplenteMB;

@ManagedBean(name = "documentacionPropuestaOrdenFirmanteMB")
@ViewScoped
public class DocumentacionPropuestaOrdenFirmanteMB extends AbstractManagedBean {

    private static final long serialVersionUID = 15432L;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;

    @ManagedProperty(value = "#{firmanteSuplenteOrden}")
    private FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB;

    private FecetPropuesta propuestaSelAnalizar;
    private List<FecetImpuestoDescripcion> listaImpuestosDescripcion;
    private double sumaPresuntiva;
    private List<FecetDocExpediente> listaExpedientes;

    private FecetDocExpediente fecetDocExpedienteSeleccionado;

    private EmpleadoDTO empleadoDTO;

    private transient StreamedContent descargaExpediente;

    @PostConstruct
    public void cargaDetallePropuesta() {
        logger.debug("cargaDetallePropuesta");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String idRegistroPropuestaSeleccionada = params.get("idRegistro");
        try {
            getUsuarioFirmante();
            this.setListaImpuestosDescripcion(new ArrayList<FecetImpuestoDescripcion>());
            this.setListaExpedientes(new ArrayList<FecetDocExpediente>());
            this.setSumaPresuntiva(0F);
            FecetPropuesta propuesta = seguimientoOrdenesService.getDetallePropuestaOrden(idRegistroPropuestaSeleccionada);
            logger.debug("propuesta [{}]", propuesta);
            this.setPropuestaSelAnalizar(propuesta == null ? new FecetPropuesta() : propuesta);
            detallePropuesta();
        } catch (NoExisteEmpleadoException e) {
            logger.error("No se pudo cargar la informacion basica de la pagina", e);
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    private void getUsuarioFirmante() throws NoExisteEmpleadoException {
        String rfcFirmante = getRFCSession();
        if (firmanteOrdenSuplenteMB.isSuplente()) {
            for (FecetSuplencia fs : firmanteOrdenSuplenteMB.getSuplentes()) {
                if (fs.getIdFirmanteASuplir().compareTo(new BigDecimal(firmanteOrdenSuplenteMB.getSuplenteSeleccionado())) == 0) {
                    rfcFirmante = fs.getRfcSuplente();
                    break;
                }
            }
        }
        try {
            empleadoDTO = getEmpleadoService().getEmpleadoCompleto(rfcFirmante);
            if (empleadoDTO != null && !getEmpleadoService().validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.FIRMANTE)) {
                empleadoDTO = null;
                throw new NoExisteEmpleadoException("No se encuentra el perfil del empleado");
            }
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }
    }

    private void detallePropuesta() {
        double presuntivaConvertida = 0F;
        this.setListaImpuestosDescripcion(this.consultarPropuestasAsignadasService.traeImpuestosDescripcion(this.getPropuestaSelAnalizar().getIdPropuesta()));
        for (FecetImpuestoDescripcion impuestoSumar : this.getListaImpuestosDescripcion()) {
            presuntivaConvertida = impuestoSumar.getFecetImpuesto().getMonto().doubleValue();
            this.setSumaPresuntiva(this.getSumaPresuntiva() + presuntivaConvertida);
        }
        this.propuestaSelAnalizar.setFececCausaProgramacion(
                this.getConsultarPropuestasAsignadasService().traeCausaProgramacion(this.getPropuestaSelAnalizar().getIdCausaProgramacion()).get(0));
        this.propuestaSelAnalizar.setFececTipoPropuesta(
                this.getConsultarPropuestasAsignadasService().traeTipoPropuesta(this.getPropuestaSelAnalizar().getIdTipoPropuesta()).get(0));

        if (propuestaSelAnalizar.getIdRevision() != null) {
            this.getPropuestaSelAnalizar()
                    .setFececRevision(this.getConsultarPropuestasAsignadasService().traeTipoRevision(this.propuestaSelAnalizar.getIdRevision()).get(0));
        }

        this.propuestaSelAnalizar
                .setFeceaMetodo(this.getConsultarPropuestasAsignadasService().traeDescripcionMetodo(this.propuestaSelAnalizar.getIdMetodo()).get(0));

        this.setListaExpedientes(this.getConsultarPropuestasAsignadasService().traeExpedientesCargados(this.propuestaSelAnalizar.getIdPropuesta()));
    }

    /**
     * Setter getters
     */
    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void setConsultarPropuestasAsignadasService(ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }

    public FecetPropuesta getPropuestaSelAnalizar() {
        return propuestaSelAnalizar;
    }

    public void setPropuestaSelAnalizar(FecetPropuesta propuestaSelAnalizar) {
        this.propuestaSelAnalizar = propuestaSelAnalizar;
    }

    public double getSumaPresuntiva() {
        return sumaPresuntiva;
    }

    public void setSumaPresuntiva(double sumaPresuntiva) {
        this.sumaPresuntiva = sumaPresuntiva;
    }

    public List<FecetImpuestoDescripcion> getListaImpuestosDescripcion() {
        return listaImpuestosDescripcion;
    }

    public void setListaImpuestosDescripcion(List<FecetImpuestoDescripcion> listaImpuestosDescripcion) {
        this.listaImpuestosDescripcion = listaImpuestosDescripcion;
    }

    public List<FecetDocExpediente> getListaExpedientes() {
        return listaExpedientes;
    }

    public void setListaExpedientes(List<FecetDocExpediente> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public StreamedContent getDescargaExpediente() {
        descargaExpediente = getDescargaArchivo(fecetDocExpedienteSeleccionado.getRutaArchivo(), fecetDocExpedienteSeleccionado.getNombre());
        return descargaExpediente;
    }

    public void setDescargaExpediente(StreamedContent descargaExpediente) {
        this.descargaExpediente = descargaExpediente;
    }

    public FecetDocExpediente getFecetDocExpedienteSeleccionado() {
        return fecetDocExpedienteSeleccionado;
    }

    public void setFecetDocExpedienteSeleccionado(FecetDocExpediente fecetDocExpedienteSeleccionado) {
        this.fecetDocExpedienteSeleccionado = fecetDocExpedienteSeleccionado;
    }

    public final FirmanteOrdenSuplenteMB getFirmanteOrdenSuplenteMB() {
        return firmanteOrdenSuplenteMB;
    }

    public final void setFirmanteOrdenSuplenteMB(FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB) {
        this.firmanteOrdenSuplenteMB = firmanteOrdenSuplenteMB;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

}
