package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.util.Arrays;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class DocumentoVO extends BaseModel {
    
    private static final long serialVersionUID = -8783155136215569555L;

    private int numero;
    private String nombreArchivo;
    private transient InputStream inputStream;
    private long tamanio;
    private byte[] contenido;
    private String contentType;


    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getTamanio() {
        return tamanio;
    }

    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }

    public byte[] getContenido() {
        //151214 EOLF: Se modifico el valor del retorno porque es un array
        return (contenido != null) ? Arrays.copyOf(contenido, contenido.length) : new byte[0];
    }

    public void setContenido(byte[] contenido) {
        //151214 EOLF: Se modifico el valor de asignacion porque es un array
        this.contenido = (contenido != null) ? Arrays.copyOf(contenido, contenido.length) : new byte[0];
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
