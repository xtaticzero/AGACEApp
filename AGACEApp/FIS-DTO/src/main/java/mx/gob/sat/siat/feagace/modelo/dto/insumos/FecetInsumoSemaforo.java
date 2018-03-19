/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.common.PrioridadDTO;

/**
 * @author sergio.vaca
 *
 */
public class FecetInsumoSemaforo extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributos relacionados con plazos del insumo semaforo imagenSemaforo
     * descripcionPlazoRestante plazoRestante diasSinAtencion diasDespuesDePlazo
     * esTransferible fechaInicioPlazo diasDespuesDePlazoDescripcion
     * fechaFinPlazo
     */
    private Integer semaforo;

    private String imagenSemaforo;
    
    private String descripcionSemaforo;

    private String descripcionPlazoRestante;

    private int plazoRestante;

    private int diasSinAtencion;

    private int diasDespuesDePlazo;

    private String diasDespuesDePlazoDescripcion;

    private boolean esTransferible;

    private Date fechaInicioPlazo;

    private Date fechaFinPlazo;

    private PrioridadDTO prioridadDto;
    
    private String descripcionRechazo;
    
    private String descripcionInfo;
    
    private String info;
    
    private int row;
    
    private String nombreColumna;
    
    private int cell;
    
    private ReporteIncorrectoDto registroReporte;
    
    private int numeroRegistro;
    
    public String getImagenSemaforo() {
        return imagenSemaforo;
    }

    public void setImagenSemaforo(String imagenSemaforo) {
        this.imagenSemaforo = imagenSemaforo;
    }

    public String getDescripcionPlazoRestante() {
        return descripcionPlazoRestante;
    }

    public void setDescripcionPlazoRestante(String descripcionPlazoRestante) {
        this.descripcionPlazoRestante = descripcionPlazoRestante;
    }
    
    public Integer getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

    public int getDiasSinAtencion() {
        return diasSinAtencion;
    }

    public void setDiasSinAtencion(int diasSinAtencion) {
        this.diasSinAtencion = diasSinAtencion;
    }

    public int getDiasDespuesDePlazo() {
        return diasDespuesDePlazo;
    }

    public void setDiasDespuesDePlazo(int diasDespuesDePlazo) {
        this.diasDespuesDePlazo = diasDespuesDePlazo;
    }

    public boolean isEsTransferible() {
        return esTransferible;
    }

    public void setEsTransferible(boolean esTransferible) {
        this.esTransferible = esTransferible;
    }

    public Date getFechaInicioPlazo() {
        return fechaInicioPlazo != null ? new Date(fechaInicioPlazo.getTime()) : null;
    }

    public void setFechaInicioPlazo(Date fechaInicioPlazo) {
        this.fechaInicioPlazo = fechaInicioPlazo != null ? new Date(fechaInicioPlazo.getTime()) : null;
    }

    public int getPlazoRestante() {
        return plazoRestante;
    }

    public void setPlazoRestante(int plazoRestante) {
        this.plazoRestante = plazoRestante;
    }
    
    public String getDiasDespuesDePlazoDescripcion() {
        return diasDespuesDePlazoDescripcion;
    }

    public void setDiasDespuesDePlazoDescripcion(String diasDespuesDePlazoDescripcion) {
        this.diasDespuesDePlazoDescripcion = diasDespuesDePlazoDescripcion;
    }

    public Date getFechaFinPlazo() {
        return fechaFinPlazo != null ? new Date(fechaFinPlazo.getTime()) : null;
    }

    public void setFechaFinPlazo(Date fechaFinPlazo) {
        this.fechaFinPlazo = fechaFinPlazo != null ? new Date(fechaFinPlazo.getTime()) : null;
    }

    public PrioridadDTO getPrioridadDto() {
        return prioridadDto;
    }

    public void setPrioridadDto(PrioridadDTO prioridadDto) {
        this.prioridadDto = prioridadDto;
    }
    
    public String getDescripcionSemaforo() {
        return descripcionSemaforo;
    }

    public void setDescripcionSemaforo(String descripcionSemaforo) {
        this.descripcionSemaforo = descripcionSemaforo;
    }
    
    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescripcionInfo() {
        return descripcionInfo;
    }

    public void setDescripcionInfo(String descripcionInfo) {
        this.descripcionInfo = descripcionInfo;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    public ReporteIncorrectoDto getRegistroReporte() {
        return registroReporte;
    }

    public void setRegistroReporte(ReporteIncorrectoDto registroReporte) {
        this.registroReporte = registroReporte;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
    
    
}
