/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class DocumentosFirmante extends BaseModel {

    @SuppressWarnings("compatibility:8080492858239298377")
    private static final long serialVersionUID = 1L;

    /**
     *  Este atributo mapea los datos de la columna ID_ORDEN
     */
    private BigDecimal idOrden;
    
    /**
     * Este atributo mapea los datos de la columna CVE_ORDEN_DOC
     */
    private String cveOrdenDoc;
    
    /**
     * Este atributo mapea los datos de la columna NOMBRE_DOC
     */
    private String nombreDoc;
    
    /**
     * Este atributo mapea los datos de la columna RUTA_DOC
     */
    private String rutaDoc;
    
    /**
     * Este atributo mapea los datos de la columna ESTADO_DOC
     */
    private String estadoDoc;
    
    /**
     * Este atributo mapea los datos de la columna FECHA_DOC
     */
    private Date fechaDoc;
    
    /**
     * Este atributo mapea los datos de la columna TIPO_DOCUMENTO
     */
    private String tipoDocumento;
    
    /**
     *  Este atributo mapea los datos de la columna ID_DOCUMENTO
     */
    private BigDecimal idDocumento;
    
    /**
     * Metodo setIdOrden
     * @param idOrden
     */
    public void setIdOrden(final BigDecimal idOrden) {
        this.idOrden = idOrden;
    }
    
    /**
     * Metodo getIdOrden
     * @return Bigdecimal
     */
    public BigDecimal getIdOrden() {
        return idOrden;
    }
    
    /**
     * Metodo setCveOrdenDoc
     * @param cveOrdenDoc
     */
    public void setCveOrdenDoc(final String cveOrdenDoc) {
        this.cveOrdenDoc = cveOrdenDoc;
    }
    
    /**
     * Metodo getCveOrdenDoc
     * @return String
     */
    public String getCveOrdenDoc() {
        return cveOrdenDoc;
    }
    
    /**
     * Metodo setNombreDoc
     * @param nombreDoc
     */
    public void setNombreDoc(final String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }
    
    /**
     * Metodo getNombreDoc
     * @return String
     */
    public String getNombreDoc() {
        return nombreDoc;
    }
    
    /**
     * Metodo setRutaDoc
     * @param rutaDoc
     */
    public void setRutaDoc(final String rutaDoc) {
        this.rutaDoc = rutaDoc;
    }
    
    /**
     * Metodo getRutaDoc
     * @return String
     */
    public String getRutaDoc() {
        return rutaDoc;
    }
    
    /**
     * Metodo setEstadoDoc
     * @param estadoDoc
     */
    public void setEstadoDoc(final String estadoDoc) {
        this.estadoDoc = estadoDoc;
    }
    
    /**
     * Metodo getEstadoDoc
     * @return String
     */
    public String getEstadoDoc() {
        return estadoDoc;
    }
    
    /**
     * Metodo setFechaDoc
     * @param fechaDoc
     */
    public void setFechaDoc(final Date fechaDoc) {        
        this.fechaDoc = fechaDoc != null ? new Date(fechaDoc.getTime()) : null;
    }
    
    /**
     * Metodo getFechaDoc
     * @return Date
     */
    public Date getFechaDoc() {
        return (fechaDoc != null) ? (Date) fechaDoc.clone() : null;
    }
    
    /**
     * Metodo setTipoDocumento
     * @param tipoDocumento
     */
    public void setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    /**
     * Metodo getTipoDocumento
     * @return String
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setIdDocumento(BigDecimal idDocumento) {
        this.idDocumento = idDocumento;
    }

    public BigDecimal getIdDocumento() {
        return idDocumento;
    }
}
