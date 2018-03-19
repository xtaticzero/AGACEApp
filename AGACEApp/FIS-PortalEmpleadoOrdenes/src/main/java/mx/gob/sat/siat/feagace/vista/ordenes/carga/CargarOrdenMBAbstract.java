package mx.gob.sat.siat.feagace.vista.ordenes.carga;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarOrdenService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

public class CargarOrdenMBAbstract extends AbstractManagedBean implements Serializable {

    public static final Logger LOGGER = Logger.getLogger(CargarOrdenMB.class);
    public static final String FORM_RFC = "formOrdenes:txtRFC";

    @SuppressWarnings("compatibility:9088854246416384528")
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{cargarOrdenService}")
    private transient CargarOrdenService cargarOrdenService;
    private AgaceOrden feceaAgaceOrdenCaptura;
    private List<FececMetodo> listaMetodos;
    private List<AgaceOrden> listaOrdenes;
    private List<AgaceOrden> listaEnviarOrdenes;

    private boolean mostrarEscritoHechos;
    private boolean mostrarGabinete;
    private boolean mostrarCartaInvitacion;
    private boolean mostrarRevisionElectronica;
    private boolean mostrarMotivoPrioridadAlta;
    private boolean deshabilitarComboInicialesGabinete;
    private boolean mostrarCargarArchivo;
    private transient UploadedFile archivoOrden;
    private transient StreamedContent archivoSeleccionDescarga;
    private AgaceOrden ordenSeleccionDescarga;
    private String localArace;
    

    protected static final String CAMPO_OBLIGATORIO = "error.campo.obligatorio";

    public void setFeceaAgaceOrdenCaptura(AgaceOrden feceaAgaceOrdenCaptura) {
        this.feceaAgaceOrdenCaptura = feceaAgaceOrdenCaptura;
    }

    public AgaceOrden getFeceaAgaceOrdenCaptura() {
        return feceaAgaceOrdenCaptura;
    }

    public void setListaMetodos(List<FececMetodo> listaMetodos) {
        this.listaMetodos = listaMetodos;
    }

    public List<FececMetodo> getListaMetodos() {
        return listaMetodos;
    }

    public void setMostrarEscritoHechos(boolean mostrarEscritoHechos) {
        this.mostrarEscritoHechos = mostrarEscritoHechos;
    }

    public boolean isMostrarEscritoHechos() {
        return mostrarEscritoHechos;
    }

    public void setMostrarCartaInvitacion(boolean mostrarCartaInvitacion) {
        this.mostrarCartaInvitacion = mostrarCartaInvitacion;
    }

    public boolean isMostrarCartaInvitacion() {
        return mostrarCartaInvitacion;
    }

    public void setMostrarRevisionElectronica(boolean mostrarRevisionElectronica) {
        this.mostrarRevisionElectronica = mostrarRevisionElectronica;
    }

    public boolean isMostrarRevisionElectronica() {
        return mostrarRevisionElectronica;
    }

    public void setMostrarMotivoPrioridadAlta(boolean mostrarMotivoPrioridadAlta) {
        this.mostrarMotivoPrioridadAlta = mostrarMotivoPrioridadAlta;
    }

    public boolean isMostrarMotivoPrioridadAlta() {
        return mostrarMotivoPrioridadAlta;
    }

    public void setArchivoOrden(UploadedFile archivoOrden) {
        this.archivoOrden = archivoOrden;
    }

    public UploadedFile getArchivoOrden() {
        return archivoOrden;
    }

    public void setListaOrdenes(List<AgaceOrden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<AgaceOrden> getListaOrdenes() {
        return listaOrdenes;
    }

    

    public void setArchivoSeleccionDescarga(StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        final String nombre = CargaArchivoUtilOrdenes.getNombreArchivoOrden(ordenSeleccionDescarga);
        final String path = RutaArchivosUtilOrdenes.armarRutaDestinoOrden(ordenSeleccionDescarga) + nombre;
        archivoSeleccionDescarga = getDescargaArchivo(path, nombre);

        return archivoSeleccionDescarga;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    public void setLocalArace(String localArace) {
        this.localArace = localArace;
    }

    public String getLocalArace() {
        return localArace;
    }

    public void setDeshabilitarComboInicialesGabinete(boolean mostrarComboInicialesGabinete) {
        this.deshabilitarComboInicialesGabinete = mostrarComboInicialesGabinete;
    }

    public boolean isDeshabilitarComboInicialesGabinete() {
        return deshabilitarComboInicialesGabinete;
    }

    

    public void setMostrarGabinete(boolean mostrarGabinete) {
        this.mostrarGabinete = mostrarGabinete;
    }

    public boolean isMostrarGabinete() {
        return mostrarGabinete;
    }

    

    public void setListaEnviarOrdenes(List<AgaceOrden> listaEnviarOrdenes) {
        this.listaEnviarOrdenes = listaEnviarOrdenes;
    }

    public List<AgaceOrden> getListaEnviarOrdenes() {
        return listaEnviarOrdenes;
    }

    public void imprimeLog() {
        LOGGER.error("*****ERROR Prueba");
    }

    

    public void setMostrarCargarArchivo(boolean mostrarCargarArchivo) {
        this.mostrarCargarArchivo = mostrarCargarArchivo;
    }

    public boolean isMostrarCargarArchivo() {
        return mostrarCargarArchivo;
    }

    

    public Integer getSizeListaOrdenes() {
        return listaOrdenes.size();
    }
    
    public CargarOrdenService getCargarOrdenService() {
        return cargarOrdenService;
    }

    public void setCargarOrdenService(CargarOrdenService cargarOrdenService) {
        this.cargarOrdenService = cargarOrdenService;
    }
}
