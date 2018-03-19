/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class FecetContAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_CONT_AUDIT in the FECET_CONT_AUDIT table.
     */
    private BigDecimal idContAudit;

    /**
     * This attribute maps to the column FECHA_CARGA in the FECET_CONT_AUDIT table.
     */
    private Date fechaCarga;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_CONT_AUDIT table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_CONT_AUDIT table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column RFC_AUDITOR in the FECET_CONT_AUDIT table.
     */
    private String rfcAuditor;

    /**
     * This attribute maps to the column NOMBRE_AUDITOR in the FECET_CONT_AUDIT table.
     */
    private String nombreAuditor;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    /**
     * Method 'FecetContAudit'
     *
     */
    public FecetContAudit() {
    }


    /**
     * Method 'createPk'
     *
     * @return FecetContAuditPk
     */
    public FecetContAuditPk createPk() {
        return new FecetContAuditPk(idContAudit);
    }

    /**
     * Method 'getIdContAudit'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdContAudit() {
        return idContAudit;
    }

    /**
     * Method 'setIdContAudit'
     *
     * @param idContAudit
     */
    public void setIdContAudit(final BigDecimal idContAudit) {
        this.idContAudit = idContAudit;
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
     * Method 'getRfcAuditor'
     *
     * @return String
     */
    public String getRfcAuditor() {
        return rfcAuditor;
    }

    /**
     * Method 'setRfcAuditor'
     *
     * @param rfcAuditor
     */
    public void setRfcAuditor(final String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    /**
     * Method 'getNombreAuditor'
     *
     * @return String
     */
    public String getNombreAuditor() {
        return nombreAuditor;
    }

    /**
     * Method 'setNombreAuditor'
     *
     * @param nombreAuditor
     */
    public void setNombreAuditor(final String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    /**
     * Method 'setArchivo'
     *
     * @param archivo
     */
    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
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
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetContAudit: ");
        ret.append("idContAudit=" + idContAudit);
        ret.append(", fechaCarga=" + fechaCarga);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", rfcAuditor=" + rfcAuditor);
        ret.append(", nombreAuditor=" + nombreAuditor);
        return ret.toString();
    }

}
