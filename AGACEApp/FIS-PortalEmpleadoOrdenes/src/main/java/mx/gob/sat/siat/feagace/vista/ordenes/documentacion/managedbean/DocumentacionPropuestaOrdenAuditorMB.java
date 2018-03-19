package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.managedbean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

@ManagedBean(name = "documentacionPropuestaOrdenAuditorMB")
@SessionScoped
public class DocumentacionPropuestaOrdenAuditorMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;

    private String rfcAuditor;
    private String nombreAuditor;
    private AgaceOrden ordenSeleccionada;

    private Boolean recargarInformacion;

    private String idRegistroPropuestaSeleccionada;

    private FecetPropuesta propuestaSelAnalizar;
    private List<FecetImpuestoDescripcion> listaImpuestosDescripcion;
    private BigDecimal sumaPresuntiva;
    private List<FecetDocExpediente> listaExpedientes;
    private FecetDocExpediente expedienteSeleccionado;

    private transient StreamedContent archivoDescarga;

    private EmpleadoDTO empleadoDTO;
    private FecetDocExpediente fecetDocExpedienteSeleccionado;
    private transient StreamedContent descargaExpediente;

    public void init() {
        if (recargarInformacion) {
            try {
                getUsuarioAuditor();

                this.rfcAuditor = this.empleadoDTO.getRfc();
                this.nombreAuditor = this.empleadoDTO.getNombre();

                this.listaImpuestosDescripcion = new ArrayList<FecetImpuestoDescripcion>();
                this.listaExpedientes = new ArrayList<FecetDocExpediente>();
                this.sumaPresuntiva = Constantes.BIG_DECIMAL_CERO;

                this.propuestaSelAnalizar = seguimientoOrdenesService.getDetallePropuestaOrden(this.idRegistroPropuestaSeleccionada);

                detallePropuesta();

                this.recargarInformacion = false;
            } catch (NoExisteEmpleadoException e) {
                logger.error("No se pudo cargar la informacion basica de la pagina", e);
                addErrorMessage(null, e.getMessage(), "");
            }
        }
    }

    private void getUsuarioAuditor() throws NoExisteEmpleadoException {
        try {
            empleadoDTO = getEmpleadoService().getEmpleadoCompleto(getRFCSession());
            if (empleadoDTO != null && !getEmpleadoService().validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.AUDITOR)) {
                    empleadoDTO = null;
                    throw new NoExisteEmpleadoException("");
                }
            
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }

    }

    private void detallePropuesta() {
        BigDecimal presuntivaConvertida = Constantes.BIG_DECIMAL_CERO;

        this.listaImpuestosDescripcion = this.consultarPropuestasAsignadasService.traeImpuestosDescripcion(this.propuestaSelAnalizar.getIdPropuesta());
        for (FecetImpuestoDescripcion impuestoSumar : this.listaImpuestosDescripcion) {
            presuntivaConvertida = impuestoSumar.getFecetImpuesto().getMonto();
            this.sumaPresuntiva = this.sumaPresuntiva.add(presuntivaConvertida);
        }
        this.propuestaSelAnalizar.setFececCausaProgramacion(
                this.consultarPropuestasAsignadasService.traeCausaProgramacion(this.propuestaSelAnalizar.getIdCausaProgramacion()).get(0));
        this.propuestaSelAnalizar
                .setFececTipoPropuesta(this.consultarPropuestasAsignadasService.traeTipoPropuesta(this.propuestaSelAnalizar.getIdTipoPropuesta()).get(0));

        if (propuestaSelAnalizar.getIdRevision() != null) {
            this.propuestaSelAnalizar
                    .setFececRevision(this.consultarPropuestasAsignadasService.traeTipoRevision(this.propuestaSelAnalizar.getIdRevision()).get(0));
        }

        this.propuestaSelAnalizar
                .setFeceaMetodo(this.consultarPropuestasAsignadasService.traeDescripcionMetodo(this.propuestaSelAnalizar.getIdMetodo()).get(0));

        this.listaExpedientes = this.consultarPropuestasAsignadasService.traeExpedientesCargados(this.propuestaSelAnalizar.getIdPropuesta());
    }

    public void limpiaFormulario() {
        setRecargarInformacion(true);
    }

    public void setRfcAuditor(String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    public String getRfcAuditor() {
        return rfcAuditor;
    }

    public void setNombreAuditor(String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    public String getNombreAuditor() {
        return nombreAuditor;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setRecargarInformacion(final Boolean recargarInformacion) {
        this.recargarInformacion = recargarInformacion;
    }

    public Boolean getRecargarInformacion() {
        return recargarInformacion;
    }

    public void setIdRegistroPropuestaSeleccionada(final String idRegistroPropuestaSeleccionada) {
        this.idRegistroPropuestaSeleccionada = idRegistroPropuestaSeleccionada;
    }

    public String getIdRegistroPropuestaSeleccionada() {
        return idRegistroPropuestaSeleccionada;
    }

    public void setPropuestaSelAnalizar(final FecetPropuesta propuestaSelAnalizar) {
        this.propuestaSelAnalizar = propuestaSelAnalizar;
    }

    public FecetPropuesta getPropuestaSelAnalizar() {
        return propuestaSelAnalizar;
    }

    public void setListaImpuestosDescripcion(final List<FecetImpuestoDescripcion> listaImpuestosDescripcion) {
        this.listaImpuestosDescripcion = listaImpuestosDescripcion;
    }

    public List<FecetImpuestoDescripcion> getListaImpuestosDescripcion() {
        return listaImpuestosDescripcion;
    }

    public void setConsultarPropuestasAsignadasService(ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void setSumaPresuntiva(BigDecimal sumaPresuntiva) {
        this.sumaPresuntiva = sumaPresuntiva;
    }

    public BigDecimal getSumaPresuntiva() {
        return sumaPresuntiva;
    }

    public void setListaExpedientes(final List<FecetDocExpediente> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public List<FecetDocExpediente> getListaExpedientes() {
        return listaExpedientes;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public StreamedContent getArchivoDescarga() {
        archivoDescarga = getDescargaArchivo(expedienteSeleccionado.getRutaArchivo(), expedienteSeleccionado.getNombre());
        return archivoDescarga;
    }

    public void setArchivoDescarga(StreamedContent archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }

    public FecetDocExpediente getExpedienteSeleccionado() {
        return expedienteSeleccionado;
    }

    public void setExpedienteSeleccionado(FecetDocExpediente expedienteSeleccionado) {
        this.expedienteSeleccionado = expedienteSeleccionado;
    }

    public FecetDocExpediente getFecetDocExpedienteSeleccionado() {
        return fecetDocExpedienteSeleccionado;
    }

    public void setFecetDocExpedienteSeleccionado(FecetDocExpediente fecetDocExpedienteSeleccionado) {
        this.fecetDocExpedienteSeleccionado = fecetDocExpedienteSeleccionado;
    }

    public StreamedContent getDescargaExpediente() {
        descargaExpediente = getDescargaArchivo(fecetDocExpedienteSeleccionado.getRutaArchivo(), fecetDocExpedienteSeleccionado.getNombre());
        return descargaExpediente;
    }

    public void setDescargaExpediente(StreamedContent descargaExpediente) {
        this.descargaExpediente = descargaExpediente;
    }
}
