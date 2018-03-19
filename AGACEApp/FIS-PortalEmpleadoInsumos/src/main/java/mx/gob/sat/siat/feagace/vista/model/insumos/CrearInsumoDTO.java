package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;

public class CrearInsumoDTO extends CrearInsumoComplexAbstract {

    private static final long serialVersionUID = 2L;

    private transient UploadedFile archivoCarga;
    private EmpleadoDTO empleadoSesion;
    private FecetContribuyente contribuyente;
    private FecetContribuyente contribuyenteDetalle;
    private FecetDocExpInsumo archivoDescarga;
    private FecetDocExpInsumo docSeleccionado;
    private FecetInsumo propuesta;
    private FecetInsumo insumoSeleccionado;
    private FecetInsumo insumoDetalle;
    private FecetInsumo insumoDetalleAuxiliar;
    private InsumoExpedienteDTO insumoExpendiente;
    private transient RegistroInsumosDto insumosProcesados;
    private String justificacion;
    private String justificacionDetalle;
    private List<FecetDocumento> listaDocumentosJustificacion;
    private List<FecetDocumento> listaDocumentosPorBorrarJustificacion;
    
    private FecetDocumento docSeleccionadoJustificacion;
    private int numeroDeDocumentos;
    private String rutaArchivoDescargable;
    private String nombreArchivoDescargable;
    private int desplegarDialogo;        
    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setPropuesta(FecetInsumo propuesta) {
        this.propuesta = propuesta;
    }

    public FecetInsumo getPropuesta() {
        return propuesta;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setDocSeleccionado(FecetDocExpInsumo docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public FecetDocExpInsumo getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public EmpleadoDTO getEmpleadoSesion() {
        return empleadoSesion;
    }

    public void setEmpleadoSesion(EmpleadoDTO empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }

    public FecetInsumo getInsumoDetalle() {
        return insumoDetalle;
    }

    public void setInsumoDetalle(FecetInsumo insumoDetalle) {
        this.insumoDetalle = insumoDetalle;
    }

    public FecetContribuyente getContribuyenteDetalle() {
        return contribuyenteDetalle;
    }

    public void setContribuyenteDetalle(FecetContribuyente contribuyenteDetalle) {
        this.contribuyenteDetalle = contribuyenteDetalle;
    }

    public FecetInsumo getInsumoDetalleAuxiliar() {
        return insumoDetalleAuxiliar;
    }

    public void setInsumoDetalleAuxiliar(FecetInsumo insumoDetalleAuxiliar) {
        this.insumoDetalleAuxiliar = insumoDetalleAuxiliar;
    }

    public InsumoExpedienteDTO getInsumoExpendiente() {
        return insumoExpendiente;
    }

    public void setInsumoExpendiente(InsumoExpedienteDTO insumoExpendiente) {
        this.insumoExpendiente = insumoExpendiente;
    }

    public FecetDocExpInsumo getArchivoDescarga() {
        return archivoDescarga;
    }

    public void setArchivoDescarga(FecetDocExpInsumo archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }

    public final RegistroInsumosDto getInsumosProcesados() {
        return insumosProcesados;
    }

    public final void setInsumosProcesados(RegistroInsumosDto insumosProcesados) {
        this.insumosProcesados = insumosProcesados;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public List<FecetDocumento> getListaDocumentosJustificacion() {
        return listaDocumentosJustificacion;
    }

    public void setListaDocumentosJustificacion(List<FecetDocumento> listaDocumentosJustificacion) {
        this.listaDocumentosJustificacion = listaDocumentosJustificacion;
    }

    public FecetDocumento getDocSeleccionadoJustificacion() {
        return docSeleccionadoJustificacion;
    }

    public void setDocSeleccionadoJustificacion(FecetDocumento docSeleccionadoJustificacion) {
        this.docSeleccionadoJustificacion = docSeleccionadoJustificacion;
    }

    public int getNumeroDeDocumentos() {
        return numeroDeDocumentos;
    }

    public void setNumeroDeDocumentos(int numeroDeDocumentos) {
        this.numeroDeDocumentos = numeroDeDocumentos;
    }

    public String getRutaArchivoDescargable() {
        return rutaArchivoDescargable;
    }

    public void setRutaArchivoDescargable(String rutaArchivoDescargable) {
        this.rutaArchivoDescargable = rutaArchivoDescargable;
    }

    public String getNombreArchivoDescargable() {
        return nombreArchivoDescargable;
    }

    public void setNombreArchivoDescargable(String nombreArchivoDescargable) {
        this.nombreArchivoDescargable = nombreArchivoDescargable;
    }

    public String getJustificacionDetalle() {
        return justificacionDetalle;
    }

    public void setJustificacionDetalle(String justificacionDetalle) {
        this.justificacionDetalle = justificacionDetalle;
    }

    public int getDesplegarDialogo() {
        return desplegarDialogo;
    }

    public void setDesplegarDialogo(int desplegarDialogo) {
        this.desplegarDialogo = desplegarDialogo;
    }

    public List<FecetDocumento> getListaDocumentosPorBorrarJustificacion() {
        return listaDocumentosPorBorrarJustificacion;
    }

    public void setListaDocumentosPorBorrarJustificacion(List<FecetDocumento> listaDocumentosPorBorrarJustificacion) {
        this.listaDocumentosPorBorrarJustificacion = listaDocumentosPorBorrarJustificacion;
    }
    
    
}
