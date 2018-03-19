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


public class FecetRechazoOrden extends BaseModel {
    @SuppressWarnings("compatibility:-65803353566594895")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_RECHAZO_ORDEN in the FECET_RECHAZO_ORDEN table.
     */
    private BigDecimal idRechazoOrden;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_RECHAZO_ORDEN table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_RECHAZO_ORDEN table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the FECET_RECHAZO_ORDEN table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column FECHA_ATENCION in the FECET_RECHAZO_ORDEN table.
     */
    private Date fechaAtencion;

    /**
     * This attribute maps to the column RFC_RECHAZO in the FECET_RECHAZO_ORDEN table.
     */
    private String rfcRechazo;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO_RECHAZO in the FECET_RECHAZO_ORDEN table.
     */
    private String nombreArchivoRechazo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO_RECHAZO in the FECET_RECHAZO_ORDEN table.
     */
    private String rutaArchivoRechazo;

    /**
     * This attribute maps to the column ESTATUS in the FECET_RECHAZO_ORDEN table.
     */
    private String estatus;

    /**
     * This attribute maps to the column RFC_RETRO_AUDITOR in the FECET_RECHAZO_ORDEN table.
     */
    private String rfcRetroAuditor;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO_REEMPLAZADO in the FECET_RECHAZO_ORDEN table.
     */
    private String nombreArchivoReemplazado;

    /**
     * This attribute maps to the column RUTA_ARCHIVO_REEMPLAZADO in the FECET_RECHAZO_ORDEN table.
     */
    private String rutaArchivoReemplazado;
    
    private BigDecimal idEmpleado;

    private BigDecimal idDocOrden;

    private transient InputStream archivoOrden;

    /**
     * Method 'FecetRechazoOrden'
     *
     */
    public FecetRechazoOrden() {
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
     * Method 'getFechaRechazo'
     *
     * @return Date
     */
    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date)fechaRechazo.clone() : null;                
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
     * Method 'getFechaAtencion'
     *
     * @return Date
     */
    public Date getFechaAtencion() {
        return (fechaAtencion != null) ? (Date)fechaAtencion.clone() : null;                
    }

    /**
     * Method 'setFechaAtencion'
     *
     * @param fechaAtencion
     */
    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion != null ? new Date(fechaAtencion.getTime()) : null;
    }

    /**
     * Method 'getRfcRechazo'
     *
     * @return String
     */
    public String getRfcRechazo() {
        return rfcRechazo;
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
     * Method 'getNombreArchivoRechazo'
     *
     * @return String
     */
    public String getNombreArchivoRechazo() {
        return nombreArchivoRechazo;
    }

    /**
     * Method 'setNombreArchivoRechazo'
     *
     * @param nombreArchivoRechazo
     */
    public void setNombreArchivoRechazo(String nombreArchivoRechazo) {
        this.nombreArchivoRechazo = nombreArchivoRechazo;
    }

    /**
     * Method 'getRutaArchivoRechazo'
     *
     * @return String
     */
    public String getRutaArchivoRechazo() {
        return rutaArchivoRechazo;
    }

    /**
     * Method 'setRutaArchivoRechazo'
     *
     * @param rutaArchivoRechazo
     */
    public void setRutaArchivoRechazo(String rutaArchivoRechazo) {
        this.rutaArchivoRechazo = rutaArchivoRechazo;
    }

    /**
     * Method 'getEstatus'
     *
     * @return String
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * Method 'getRfcRetroAuditor'
     *
     * @return String
     */
    public String getRfcRetroAuditor() {
        return rfcRetroAuditor;
    }

    /**
     * Method 'setRfcRetroAuditor'
     *
     * @param rfcRetroAuditor
     */
    public void setRfcRetroAuditor(String rfcRetroAuditor) {
        this.rfcRetroAuditor = rfcRetroAuditor;
    }

    /**
     * Method 'getNombreArchivoReemplazado'
     *
     * @return String
     */
    public String getNombreArchivoReemplazado() {
        return nombreArchivoReemplazado;
    }

    /**
     * Method 'setNombreArchivoReemplazado'
     *
     * @param nombreArchivoReemplazado
     */
    public void setNombreArchivoReemplazado(String nombreArchivoReemplazado) {
        this.nombreArchivoReemplazado = nombreArchivoReemplazado;
    }

    /**
     * Method 'getRutaArchivoReemplazado'
     *
     * @return String
     */
    public String getRutaArchivoReemplazado() {
        return rutaArchivoReemplazado;
    }

    /**
     * Method 'setRutaArchivoReemplazado'
     *
     * @param rutaArchivoReemplazado
     */
    public void setRutaArchivoReemplazado(String rutaArchivoReemplazado) {
        this.rutaArchivoReemplazado = rutaArchivoReemplazado;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetRechazoOrdenPk
     */
    public FecetRechazoOrdenPk createPk() {
        return new FecetRechazoOrdenPk(idRechazoOrden);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetRechazoOrden: ");
        ret.append("idRechazoOrden=");
        ret.append(idRechazoOrden);
        ret.append(", idOrden=");
        ret.append(idOrden);
        ret.append(", descripcion=");
        ret.append(descripcion);
        ret.append(", fechaRechazo=");
        ret.append(fechaRechazo);
        ret.append(", fechaAtencion=");
        ret.append(fechaAtencion);
        ret.append(", rfcRechazo=");
        ret.append(rfcRechazo);
        ret.append(", nombreArchivoRechazo=");
        ret.append(nombreArchivoRechazo);
        ret.append(", rutaArchivoRechazo=");
        ret.append(rutaArchivoRechazo);
        ret.append(", estatus=");
        ret.append(estatus);
        ret.append(", rfcRetroAuditor=");
        ret.append(rfcRetroAuditor);
        ret.append(", nombreArchivoReemplazado=");
        ret.append(nombreArchivoReemplazado);
        ret.append(", rutaArchivoReemplazado=");
        ret.append(rutaArchivoReemplazado);
   
        return ret.toString();
    }

    public void setIdRechazoOrden(BigDecimal idRechazoOrden) {
        this.idRechazoOrden = idRechazoOrden;
    }

    public BigDecimal getIdRechazoOrden() {
        return idRechazoOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setArchivoOrden(InputStream archivoOrden) {
        this.archivoOrden = archivoOrden;
    }

    public InputStream getArchivoOrden() {
        return archivoOrden;
    }


    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }


    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


    public BigDecimal getIdDocOrden() {
        return idDocOrden;
    }


    public void setIdDocOrden(BigDecimal idDocOrden) {
        this.idDocOrden = idDocOrden;
    }


 
    
    
}
