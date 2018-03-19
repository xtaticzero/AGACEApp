/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetAlegato extends BaseModel {


    @SuppressWarnings("compatibility:-8312032667339192284")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ALEGATO in the FECET_ALEGATO table.
     */
    private BigDecimal idAlegato;

    /**
     * This attribute maps to the column ID_PROMOCION in the FECET_ALEGATO table.
     */
    private BigDecimal idPromocion;

    /**
     * This attribute maps to the column FECHA_CARGA in the FECET_ALEGATO table.
     */
    private Date fechaCarga;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_ALEGATO table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_ALEGATO table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column ARCHIVO_PRUEBAS_ALEGATOS in the FECET_ALEGATO table.
     */
    private transient InputStream archivo;

    private Long tamanioArchivo;
    
    private BigDecimal idArchivoTemp;

    /**
     * Method 'FecetAlegato'
     *
     */
    public FecetAlegato() {
    }

    /**
     * Method 'createPk'
     *
     * @return FecetAlegatoPk
     */
    public FecetAlegatoPk createPk() {
        return new FecetAlegatoPk(idAlegato);
    }

    /**
     * Method 'getIdAlegato'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAlegato() {
        return idAlegato;
    }

    /**
     * Method 'setIdAlegato'
     *
     * @param idAlegato
     */
    public void setIdAlegato(final BigDecimal idAlegato) {
        this.idAlegato = idAlegato;
    }

    /**
     * Method 'getIdPromocion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPromocion() {
        return idPromocion;
    }

    /**
     * Method 'setIdPromocion'
     *
     * @param idPromocion
     */
    public void setIdPromocion(final BigDecimal idPromocion) {
        this.idPromocion = idPromocion;
    }

    /**
     * Method 'getFechaCarga'
     *
     * @return Date
     */
    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    /**
     * Method 'setFechaCarga'
     *
     * @param fechaCarga
     */
    public void setFechaCarga(final Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
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
    public void setNombreArchivo(final String nombreArchivo) {
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
    public void setRutaArchivo(final String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetAlegato: ");
        ret.append("idAlegato=" + idAlegato);
        ret.append(", idPromocion=" + idPromocion);
        ret.append(", fechaCarga=" + fechaCarga);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        return ret.toString();
    }

    /**
     * Metodo setArchivo
     * @param archivo
     */
    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    /**
     * Metodo getArchivo
     * @return InputStream
     */
    public InputStream getArchivo() {
        return archivo;
    }

    public Long getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Long tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }
}
