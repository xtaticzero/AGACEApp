/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.enums.CausaRechazoPropuestaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CausaRechazoRetroalimentacionEnum;

public class FecetRechazoPropuesta extends BaseModel {

    private static final long serialVersionUID = -623686790393525596L;

    /**
     * This attribute maps to the column ID_RECHAZO_PROPUESTA in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idRechazoPropuesta;

    /**
     * This attribute maps to the column ID_PROPUESTA in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column ID_MOTIVO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idMotivo;

    /**
     * This attribute maps to the column DESCRIPCION in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column FECHA_INFORME_RECHAZO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private Date fechaInformeRechazo;

    /**
     * This attribute maps to the column RFC_RECHAZO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idEmpleado;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column TIPO in the FECET_RECHAZO_PROPUESTA
     * table.
     */
    private String tipo;

    /**
     * This attribute maps to the column ESTATUS in the FECET_RECHAZO_PROPUESTA
     * table.
     */
    private BigDecimal estatus;

    /**
     * This attribute transfer content from FileUploaded to an attribute for
     * this DTO.
     */
    private transient InputStream archivo;

    /**
     * This attribute maps to the column BLN_ESTATUS in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal blnEstatus;

    /**
     * This attribute maps to the column RFC_RECHAZO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private String rfcRechazo;

    /**
     * This attribute maps to the column RFC_RECHAZO in the
     * FECET_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idEstatus;

    /**
     * Causa Rechazo
     */
    private CausaRechazoPropuestaEnum casuaRechazo;
    
    
    /**
     * Causa Rechazo
     */
    private CausaRechazoRetroalimentacionEnum casuaRechazoRetro;
    
    /**
     * Numero Documentos Rechazo
     */
    private BigDecimal numeroDocumentos;
    
    /**
     * FececMotivo
     */
    private FececMotivo fececMotivo;
    
    /**
     * This attribute maps to the column ID_DOC_RECHAZO in the
     * FECET_DOC_RECHAZO_PROPUESTA table.
     */
    private BigDecimal idDocRechazo;
    
    private Date fechaCreacion;
    
    private Date fechaFin;
    
    private BigDecimal blnActivo;

    /**
     * Method 'FecetRechazoPropuesta'
     *
     */
    public FecetRechazoPropuesta() {
    }

    /**
     * Method 'getIdRechazoPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRechazoPropuesta() {
        return idRechazoPropuesta;
    }

    /**
     * Method 'setIdRechazoPropuesta'
     *
     * @param idRechazoPropuesta
     */
    public void setIdRechazoPropuesta(final BigDecimal idRechazoPropuesta) {
        this.idRechazoPropuesta = idRechazoPropuesta;
    }

    /**
     * Method 'getIdPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    /**
     * Method 'setIdPropuesta'
     *
     * @param idPropuesta
     */
    public void setIdPropuesta(final BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * Method 'getIdMotivo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    /**
     * Method 'setIdMotivo'
     *
     * @param idMotivo
     */
    public void setIdMotivo(final BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
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
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'getFechaRechazo'
     *
     * @return Date
     */
    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    /**
     * Method 'setFechaRechazo'
     *
     * @param fechaRechazo
     */
    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
    }

    /**
     * Method 'getFechaInformeRechazo'
     *
     * @return Date
     */
    public Date getFechaInformeRechazo() {
        return (fechaInformeRechazo != null) ? (Date) fechaInformeRechazo.clone() : null;
    }

    /**
     * Method 'setFechaInformeRechazo'
     *
     * @param fechaInformeRechazo
     */
    public void setFechaInformeRechazo(Date fechaInformeRechazo) {
        this.fechaInformeRechazo = fechaInformeRechazo != null ? new Date(fechaInformeRechazo.getTime()) : null;
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
     * Method 'getTipo'
     *
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Method 'setTipo'
     *
     * @param tipo
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
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
     * Method 'setArchivo'
     *
     * @param estatus
     */
    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    /**
     * Method 'getEstatus'
     *
     * @return estatus
     */
    public BigDecimal getEstatus() {
        return estatus;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetRechazoPropuestaPk
     */
    public FecetRechazoPropuestaPk createPk() {
        return new FecetRechazoPropuestaPk(idRechazoPropuesta);
    }

    /**
     * Method 'setIdEmpleado'
     *
     * @param idEmpleado
     */
    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Method 'getIdEmpleado'
     *
     * @return idEmpleado
     */
    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Method 'setBlnEstatus'
     *
     * @param blnEstatus
     */
    public void setBlnEstatus(BigDecimal blnEstatus) {
        this.blnEstatus = blnEstatus;
    }

    /**
     * Method 'getBlnEstatus'
     *
     * @return blnEstatus
     */
    public BigDecimal getBlnEstatus() {
        return blnEstatus;
    }

    /**
     * Method 'setRfcRechazo'
     *
     * @param rfcRechazo
     */

    public void setRfcRechazo(String rfcRechazo) {
        this.rfcRechazo = rfcRechazo;
    }

    /**
     * Method 'getRfcRechazo'
     *
     * @return rfcRechazo
     */
    public String getRfcRechazo() {
        return rfcRechazo;
    }

    /**
     * Method 'setBlnEstatus'
     *
     * @param blnEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public CausaRechazoPropuestaEnum getCasuaRechazo() {
        return casuaRechazo;
    }

    public void setCasuaRechazo(CausaRechazoPropuestaEnum casuaRechazo) {
        this.casuaRechazo = casuaRechazo;
    }
    
    public void setNumeroDocumentos(BigDecimal numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public BigDecimal getNumeroDocumentos() {
        return numeroDocumentos;
    }
    
    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }
    
    public void setIdDocRechazo(BigDecimal idDocRechazo) {
        this.idDocRechazo = idDocRechazo;
    }

    public BigDecimal getIdDocRechazo() {
        return idDocRechazo;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public CausaRechazoRetroalimentacionEnum getCasuaRechazoRetro() {
        return casuaRechazoRetro;
    }

    public void setCasuaRechazoRetro(CausaRechazoRetroalimentacionEnum casuaRechazoRetro) {
        this.casuaRechazoRetro = casuaRechazoRetro;
    }
    
    
    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetRechazoPropuesta: ");
        ret.append("idRechazoPropuesta=" + idRechazoPropuesta);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", idMotivo=" + idMotivo);
        ret.append(", descripcion=" + descripcion);
        ret.append(", fechaRechazo=" + fechaRechazo);
        ret.append(", fechaInformeRechazo=" + fechaInformeRechazo);
        ret.append(", rfcRechazo=" + rfcRechazo);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", tipo=" + tipo);
        ret.append(", estatus=" + estatus);
        ret.append(", idEmpleado=" + idEmpleado);
        ret.append(", blnEstatus=" + blnEstatus);
        return ret.toString();
    }
}
