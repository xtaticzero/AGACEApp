package mx.gob.sat.siat.feagace.vista.ordenes.firma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetDocAsociadoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmanteSeguimientoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

@ManagedBean(name = "firmanteSeguimientoMB")
@SessionScoped
public class FirmanteSeguimientoMB extends AbstractManagedBean {

    private static final long serialVersionUID = 16230484544L;
    @ManagedProperty(value = "#{firmanteSuplenteOrden}")
    private FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB;
    @ManagedProperty(value = "#{firmanteSeguimientoService}")
    private transient FirmanteSeguimientoService firmanteSeguimientoService;
    @ManagedProperty(value = "#{fecetDocAsociadoService}")
    private transient FecetDocAsociadoService fecetDocAsociadoService;

    private AgaceOrden ordenSeleccionada;
    private AgaceOrden ordenSeleccionDescarga;
    private transient StreamedContent archivoSeleccionDescarga;
    private EmpleadoDTO empleadoDTO;
    private List<AgaceOrden> listaPorFirmarSeguimiento;

    public void cargaOrdenesDocumentosFirmar() {
        this.listaPorFirmarSeguimiento = new ArrayList<AgaceOrden>();
        try {
            getUsuarioFirmante();
            getSession().setAttribute("empleadoFirmante", empleadoDTO);
            this.listaPorFirmarSeguimiento = firmanteSeguimientoService.getOrdenesFirmarDocumentos(this.empleadoDTO.getIdEmpleado());

        } catch (NoExisteEmpleadoException e) {
            addErrorMessage(e.getMessage(), getRFCSession());
            logger.error(e.getMessage());
        }
    }

    private void getUsuarioFirmante() throws NoExisteEmpleadoException {
        this.empleadoDTO = new EmpleadoDTO();
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

    public List<String> getPrioridadRegistrada() {
        List<String> prioridades = new ArrayList<String>();
        if (listaPorFirmarSeguimiento != null && !listaPorFirmarSeguimiento.isEmpty()) {
            for (AgaceOrden registro : listaPorFirmarSeguimiento) {
                if (!prioridades.contains(registro.getPrioridadSugerida())) {
                    prioridades.add(registro.getPrioridadSugerida());
                }
            }
        }
        return prioridades;
    }

    public void setFirmanteSeguimientoService(FirmanteSeguimientoService firmanteSeguimientoService) {
        this.firmanteSeguimientoService = firmanteSeguimientoService;
    }

    public FirmanteSeguimientoService getFirmanteSeguimientoService() {
        return firmanteSeguimientoService;
    }

    public void setListaPorFirmarSeguimiento(List<AgaceOrden> listaPorFirmarSeguimiento) {
        this.listaPorFirmarSeguimiento = listaPorFirmarSeguimiento;
    }

    public List<AgaceOrden> getListaPorFirmarSeguimiento() {
        return listaPorFirmarSeguimiento;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        final String nombre = CargaArchivoUtilOrdenes.getNombreArchivoOrdenPdf(this.ordenSeleccionDescarga);
        final String path = RutaArchivosUtilOrdenes.armarRutaDocOrden(this.ordenSeleccionDescarga) + nombre;
        logger.info("path {} ---- ", path);
        logger.info("nombre {} ---- ", nombre);
        archivoSeleccionDescarga = getDescargaArchivo(path, nombre);
        return archivoSeleccionDescarga;
    }

    public void setArchivoSeleccionDescarga(StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public final FirmanteOrdenSuplenteMB getFirmanteOrdenSuplenteMB() {
        return firmanteOrdenSuplenteMB;
    }

    public final void setFirmanteOrdenSuplenteMB(FirmanteOrdenSuplenteMB firmanteOrdenSuplenteMB) {
        this.firmanteOrdenSuplenteMB = firmanteOrdenSuplenteMB;
    }

    public final FecetDocAsociadoService getFecetDocAsociadoService() {
        return fecetDocAsociadoService;
    }

    public final void setFecetDocAsociadoService(FecetDocAsociadoService fecetDocAsociadoService) {
        this.fecetDocAsociadoService = fecetDocAsociadoService;
    }
}
