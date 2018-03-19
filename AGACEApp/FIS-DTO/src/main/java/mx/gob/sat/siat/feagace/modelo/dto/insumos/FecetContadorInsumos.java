/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumoPk;

public class FecetContadorInsumos implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_RETROALIMENTACION_INSUMO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idRetroalimentacionInsumo;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idInsumo;

    /**
     * This attribute maps to the column FECHA_RETROALIMENTACION in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaRetroalimentacion;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String rutaArchivo;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private int contador;

    /**
     * This attribute counts how many files there are in a insumo.
     */
    private transient InputStream archivo;


    /**
     * Method 'FecetRetroalimentacionInsumo'
     *
     */
    public FecetContadorInsumos() {
    }

    /**
     * Method 'getDescripcion'
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method 'setDescripcion'
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Method 'getNombreArchivo'
     *
     * @return String
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Method 'setNombreArchivo'
     *
     * @param nombreArchivo
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Method 'getRutaArchivo'
     *
     * @return String
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Method 'setRutaArchivo'
     *
     * @param rutaArchivo
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Method 'getFechaRetroalimentacion'
     *
     * @return Date
     */
    public Date getFechaRetroalimentacion() {
        return (fechaRetroalimentacion != null) ? (Date)fechaRetroalimentacion.clone() : null;
    }

    /**
     * Method 'setFechaRetroalimentacion'
     *
     * @param fechaRetroalimentacion
     */
    public void setFechaRetroalimentacion(Date fechaRetroalimentacion) {
        this.fechaRetroalimentacion = fechaRetroalimentacion != null ? new Date(fechaRetroalimentacion.getTime()) : null;
    }

    /**
     * Method 'getArchivo'
     *
     * @return archivo
     */
    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetRetroalimentacionInsumoPk
     */
    public FecetRetroalimentacionInsumoPk createPk() {
        return new FecetRetroalimentacionInsumoPk(idRetroalimentacionInsumo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetRetroalimentacionInsumo: ");
        ret.append("idRetroalimentacionInsumo=" + idRetroalimentacionInsumo);
        ret.append(", idInsumo=" + idInsumo);
        ret.append(", fechaRetroalimentacion=" + fechaRetroalimentacion);
        ret.append(", descripcion=" + descripcion);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", contador="+contador);
        return ret.toString();
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdRetroalimentacionInsumo(BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    public BigDecimal getIdRetroalimentacionInsumo() {
        return idRetroalimentacionInsumo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }
}

