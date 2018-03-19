package mx.gob.sat.siat.feagace.vista.insumos.validar;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarProcedenciaInsumoService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoAttributesHelper;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoDTOHelper;

public class ValidarProcedenciaFieldAbstractMB extends AbstractManagedBean {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{validarProcedenciaInsumoService}")
    private transient ValidarProcedenciaInsumoService validarProcedenciaInsumoService;

    @ManagedProperty(value = "#{seguimientoInsumosService}")
    private transient SeguimientoInsumosService seguimientoInsumosService;
    
    private ValidarProcedenciaInsumoAttributesHelper vpInsumoAttributesHelper;
    private ValidarProcedenciaInsumoDTOHelper vpInsumoDTOHelper;
    
    private transient UploadedFile archivoRechazo;
    private transient UploadedFile archivoRetroalimentacion;
    private transient UploadedFile archivoCarga;
    private FecetDocExpInsumo documentoSeleccionado;
    private String justificacionDetalle;
    private String rutaArchivoDescargable;
    private String nombreArchivoDescargable;
    
    private String mensajeValidacion;
    
    
    public SeguimientoInsumosService getSeguimientoInsumosService() {
        return seguimientoInsumosService;
    }

    public void setSeguimientoInsumosService(SeguimientoInsumosService seguimientoInsumosService) {
        this.seguimientoInsumosService = seguimientoInsumosService;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        return getDescargaArchivo(this.documentoSeleccionado.getRutaArchivo() + this.documentoSeleccionado.getNombre(),
                this.documentoSeleccionado.getNombre());
    }

    public void setDocumentoSeleccionado(final FecetDocExpInsumo documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpInsumo getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setValidarProcedenciaInsumoService(ValidarProcedenciaInsumoService validarProcedenciaInsumoService) {
        this.validarProcedenciaInsumoService = validarProcedenciaInsumoService;
    }

    public ValidarProcedenciaInsumoService getValidarProcedenciaInsumoService() {
        return validarProcedenciaInsumoService;
    }

    public UploadedFile getArchivoRechazo() {
        return archivoRechazo;
    }

    public void setArchivoRechazo(UploadedFile archivoRechazo) {
        this.archivoRechazo = archivoRechazo;
    }

    public UploadedFile getArchivoRetroalimentacion() {
        return archivoRetroalimentacion;
    }

    public void setArchivoRetroalimentacion(UploadedFile archivoRetroalimentacion) {
        this.archivoRetroalimentacion = archivoRetroalimentacion;
    }

    public void setArchivoCarga(final UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public ValidarProcedenciaInsumoAttributesHelper getVpInsumoAttributesHelper() {
        return vpInsumoAttributesHelper;
    }

    public void setVpInsumoAttributesHelper(ValidarProcedenciaInsumoAttributesHelper vpInsumoAttributesHelper) {
        this.vpInsumoAttributesHelper = vpInsumoAttributesHelper;
    }

    public ValidarProcedenciaInsumoDTOHelper getVpInsumoDTOHelper() {
        return vpInsumoDTOHelper;
    }

    public void setVpInsumoDTOHelper(ValidarProcedenciaInsumoDTOHelper vpInsumoDTOHelper) {
        this.vpInsumoDTOHelper = vpInsumoDTOHelper;
    }
    
    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }    

    public String getJustificacionDetalle() {
        return justificacionDetalle;
    }

    public void setJustificacionDetalle(String justificacionDetalle) {
        this.justificacionDetalle = justificacionDetalle;
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
    
    public StreamedContent getDescargaArchivo() {
        return getDescargaArchivo(getRutaArchivoDescargable() + getNombreArchivoDescargable(), getNombreArchivoDescargable());
    }
    
    
}
