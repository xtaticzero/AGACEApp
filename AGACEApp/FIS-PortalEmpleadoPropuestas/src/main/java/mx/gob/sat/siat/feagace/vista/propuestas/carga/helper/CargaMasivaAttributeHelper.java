package mx.gob.sat.siat.feagace.vista.propuestas.carga.helper;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ing. Julio Cesar Morales Miranda
 */

public class CargaMasivaAttributeHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5338597175136962509L;

    private String indiceActivoInicial;
    private int contadorCorrectos;
    private Date fechaActual;
    private String folioCargaDoc;
    private String folioResultado;
    private String nombreArchivo;
    private int registrosCorrectos;
    private int registrosErroneos;

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

}
