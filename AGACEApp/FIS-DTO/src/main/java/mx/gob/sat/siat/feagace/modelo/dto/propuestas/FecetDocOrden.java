package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocOrden extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 2793440543999967807L;

    /**
     * This attribute maps to the column ID_DOC_ORDEN in the FECET_DOC_ORDEN table.
     */
    private BigDecimal idDocOrden;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_DOC_ORDEN table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_DOC_ORDEN table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_DOC_ORDEN table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column DOCUMENTO_PDF in the FECET_DOC_ORDEN table.
     */
    private String documentoPdf;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_DOC_ORDEN table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column ESTATUS in the FECET_DOC_ORDEN table.
     */
    private String estatus;

    /**
     * Este atributo contiene el archivo para ser almacenado en el service
     */
    private transient InputStream archivo;

    /**
     * This attribute maps to the column BLN_ACTIVO in the FECET_DOC_ORDEN table.
     */
    private int blnActivo;

    /**
     * This attribute maps to the column FECHA_FIN in the FECET_DOC_ORDEN table.
     */
    private Date fechaFin;
    /**
     * This attribute maps to the column ID_EMPLEADO in the FECET_DOC_ORDEN table.
     */
    private BigDecimal idEmpleado;
    /**
     * This attribute maps to the column descripcion in the FECET_DOC_ORDEN table.
     */
    private String descripcionAccion;
    /**
     * This attribute maps to the column descripcion in the FECET_DOC_ORDEN table.
     */

    private String descripcionEmpleado;

    /**
     * This attribute maps to the column fecha_hora in the FECET_DOC_ORDEN table.
     */
    private Date fechaHora;

    private BigDecimal idDocumentoTemp;

    /**
     * Method 'FecetDocExpInsumo'
     * 
     */
    public FecetDocOrden() {
    }

    public BigDecimal getIdDocOrden() {
        return idDocOrden;
    }

    public void setIdDocOrden(BigDecimal idDocOrden) {
        this.idDocOrden = idDocOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getDocumentoPdf() {
        return documentoPdf;
    }

    public void setDocumentoPdf(String documentoPdf) {
        this.documentoPdf = documentoPdf;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public void setBlnActivo(int blnActivo) {
        this.blnActivo = blnActivo;
    }

    public int getBlnActivo() {
        return blnActivo;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    /**
     * Method 'createPk'
     * 
     * @return FecetDocOrdenPk
     */
    public FecetDocOrdenPk createPk() {
        return new FecetDocOrdenPk(idDocOrden);
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDescripcionAccion() {
        return descripcionAccion;
    }

    public void setDescripcionAccion(String descripcionAccion) {
        this.descripcionAccion = descripcionAccion;
    }

    public String getDescripcionEmpleado() {
        return descripcionEmpleado;
    }

    public void setDescripcionEmpleado(String descripcionEmpleado) {
        this.descripcionEmpleado = descripcionEmpleado;
    }

    public Date getFechaHora() {
        return (fechaHora != null) ? (Date) fechaHora.clone() : null;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora != null ? new Date(fechaHora.getTime()) : null;
    }

    public void setIdDocumentoTemp(BigDecimal idDocumentoTemp) {
        this.idDocumentoTemp = idDocumentoTemp;
    }

    public BigDecimal getIdDocumentoTemp() {
        return idDocumentoTemp;
    }
}
