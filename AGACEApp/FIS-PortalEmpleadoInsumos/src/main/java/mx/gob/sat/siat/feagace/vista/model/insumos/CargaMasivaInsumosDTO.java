/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ReporteIncorrectoDto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;

/**
 * @author sergio.vaca
 *
 */
public class CargaMasivaInsumosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int contadorCorrectos;
    private int registrosCorrectos;
    private int registrosErroneos;
    private String indiceActivoInicial;
    private String folioCargaDoc;
    private String folioResultado;
    private String nombreArchivo;
    private Date fechaActual;
    private transient UploadedFile archivoCarga;
    private List<FecetInsumo> listRegistrosCargados;
    private List<FecetInsumo> listRegistrosCorrectos;
    private List<FecetInsumo> listRegistrosIncorrectos;
    private Map<Integer, List<ReporteIncorrectoDto>> mapReporte;
    private RegistroInsumosDto registroInsumosDto;
    private List<ResumenCargaMasivaDTO> resumen;
    private int contadorRegistrosSeleccionados;
    private boolean habilitarEliminar;
    private FecetInsumo insumoSeleccionado;
    private String nombreArchivoDescargable;
    private String rutaArchivoDescargable;
    private List<String> tipoFieldset;
    
    
    public String getIndiceActivoInicial() {
        return indiceActivoInicial;
    }

    public void setIndiceActivoInicial(String indiceActivoInicial) {
        this.indiceActivoInicial = indiceActivoInicial;
    }

    public int getContadorCorrectos() {
        return contadorCorrectos;
    }

    public void setContadorCorrectos(int contadorCorrectos) {
        this.contadorCorrectos = contadorCorrectos;
    }

    public Date getFechaActual() {
        return fechaActual != null ? (Date) fechaActual.clone() : null;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual != null ? (Date) fechaActual.clone() : null;
    }

    public String getFolioCargaDoc() {
        return folioCargaDoc;
    }

    public void setFolioCargaDoc(String folioCargaDoc) {
        this.folioCargaDoc = folioCargaDoc;
    }

    public String getFolioResultado() {
        return folioResultado;
    }

    public void setFolioResultado(String folioResultado) {
        this.folioResultado = folioResultado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getRegistrosCorrectos() {
        return registrosCorrectos;
    }

    public void setRegistrosCorrectos(int registrosCorrectos) {
        this.registrosCorrectos = registrosCorrectos;
    }

    public int getRegistrosErroneos() {
        return registrosErroneos;
    }

    public void setRegistrosErroneos(int registrosErroneos) {
        this.registrosErroneos = registrosErroneos;
    }

    public final UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public final void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public final List<FecetInsumo> getListRegistrosCorrectos() {
        return listRegistrosCorrectos;
    }

    public final void setListRegistrosCorrectos(List<FecetInsumo> listRegistrosCorrectos) {
        this.listRegistrosCorrectos = listRegistrosCorrectos;
    }

    public final List<FecetInsumo> getListRegistrosIncorrectos() {
        return listRegistrosIncorrectos;
    }

    public final void setListRegistrosIncorrectos(List<FecetInsumo> listRegistrosIncorrectos) {
        this.listRegistrosIncorrectos = listRegistrosIncorrectos;
    }

    public final RegistroInsumosDto getRegistroInsumosDto() {
        return registroInsumosDto;
    }

    public final void setRegistroInsumosDto(RegistroInsumosDto registroInsumosDto) {
        this.registroInsumosDto = registroInsumosDto;
    }

    public final List<ResumenCargaMasivaDTO> getResumen() {
        return resumen;
    }

    public final void setResumen(List<ResumenCargaMasivaDTO> resumen) {
        this.resumen = resumen;
    }

    public List<FecetInsumo> getListRegistrosCargados() {
        return listRegistrosCargados;
    }

    public void setListRegistrosCargados(List<FecetInsumo> listRegistrosCargados) {
        this.listRegistrosCargados = listRegistrosCargados;
    }
    
    public Map<Integer, List<ReporteIncorrectoDto>> getMapReporte() {
        return mapReporte;
    }

    public void setMapReporte(Map<Integer, List<ReporteIncorrectoDto>> mapReporte) {
        this.mapReporte = mapReporte;
    }  

    public int getContadorRegistrosSeleccionados() {
        return contadorRegistrosSeleccionados;
    }

    public void setContadorRegistrosSeleccionados(int contadorRegistrosSeleccionados) {
        this.contadorRegistrosSeleccionados = contadorRegistrosSeleccionados;
    }

    public boolean isHabilitarEliminar() {
        return habilitarEliminar;
    }

    public void setHabilitarEliminar(boolean habilitarEliminar) {
        this.habilitarEliminar = habilitarEliminar;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }    

    public String getNombreArchivoDescargable() {
        return nombreArchivoDescargable;
    }

    public void setNombreArchivoDescargable(String nombreArchivoDescargable) {
        this.nombreArchivoDescargable = nombreArchivoDescargable;
    }
    
    public String getRutaArchivoDescargable() {
        return rutaArchivoDescargable;
    }

    public void setRutaArchivoDescargable(String rutaArchivoDescargable) {
        this.rutaArchivoDescargable = rutaArchivoDescargable;
    }

    public List<String> getTipoFieldset() {
        return tipoFieldset;
    }

    public void setTipoFieldset(List<String> tipoFieldset) {
        this.tipoFieldset = tipoFieldset;
    }
    
    
}
