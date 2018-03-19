/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetDocTercero extends BaseModel {


    @SuppressWarnings("compatibility:1410214318100768590")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_DOC_TERCERO in the FECET_DOC_TERCERO table.
     */
    private BigDecimal idDocTercero;

    /**
     * This attribute maps to the column ID_COMP_TERCERO in the FECET_DOC_TERCERO table.
     */
    private BigDecimal idCompTercero;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_DOC_TERCERO table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_DOC_TERCERO table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_DOC_TERCERO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column RFC_CARGA in the FECET_DOC_TERCERO table.
     */
    private String rfcCarga;

    /**
     * This attribute maps to the column ACUSE in the FECET_DOC_TERCERO table.
     */
    private String acuse;

    /**
     * Este atributo contiene el tamaño del archivo que se agrego.
     */
    private Long tamanioArchivo;

    /**
     * Este atributo contiene el archivo para ser almacenado en el service
     */
    private transient InputStream archivo;

    /**
     * Method 'FecetDocTercero'
     *
     */
    public FecetDocTercero() {
    }

    /**
     * Method 'getIdDocTercero'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdDocTercero() {
        return idDocTercero;
    }

    /**
     * Method 'setIdDocTercero'
     *
     * @param idDocTercero
     */
    public void setIdDocTercero(BigDecimal idDocTercero) {
        this.idDocTercero = idDocTercero;
    }

    /**
     * Method 'getIdCompTercero'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCompTercero() {
        return idCompTercero;
    }

    /**
     * Method 'setIdCompTercero'
     *
     * @param idCompTercero
     */
    public void setIdCompTercero(BigDecimal idCompTercero) {
        this.idCompTercero = idCompTercero;
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
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     *
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getRfcCarga'
     *
     * @return String
     */
    public String getRfcCarga() {
        return rfcCarga;
    }

    /**
     * Method 'setRfcCarga'
     *
     * @param rfcCarga
     */
    public void setRfcCarga(final String rfcCarga) {
        this.rfcCarga = rfcCarga;
    }

    public void setAcuse(final String acuse) {
        this.acuse = acuse;
    }

    public String getAcuse() {
        return acuse;
    }

    public void setTamanioArchivo(final Long tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public Long getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetDocTerceroPk
     */
    public FecetDocTerceroPk createPk() {
        return new FecetDocTerceroPk(idDocTercero);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetDocTercero: ");
        ret.append("idDocTercero=" + idDocTercero);
        ret.append(", idCompTercero=" + idCompTercero);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", fechaCreacion=" + fechaCreacion);
        ret.append(", rfcCarga=" + rfcCarga);
        ret.append(", acuse=" + acuse);
        return ret.toString();
    }
}
