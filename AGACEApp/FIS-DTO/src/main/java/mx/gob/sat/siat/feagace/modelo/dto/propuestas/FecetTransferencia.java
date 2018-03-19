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
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetTransferencia extends BaseModel{


    @SuppressWarnings("compatibility:6134549996099777310")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_TRANSFERENCIA in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal idTransferencia;

    /**
     * This attribute maps to the column ID_ARACE_ORIGEN in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal idAraceOrigen;

    /**
     * This attribute maps to the column ID_ARACE_DESTINO in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal idAraceDestino;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column FECHA_TRASPASO in the FECET_TRANSFERENCIA table.
     */
    private Date fechaTraspaso;

    /**
     * This attribute maps to the column RFC in the FECET_TRANSFERENCIA table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_TRANSFERENCIA table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_TRANSFERENCIA table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column OBSERVACIONES in the FECET_TRANSFERENCIA table.
     */
    private String observaciones;


    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    /**
     * This attribute maps to the column ESTATUS in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal estatus;
    
    /**
     * This attribute maps to the column FECHA_RECHAZO in the FECET_TRANSFERENCIA table.
     */
    private Date fechaRechazo;
    
    /**
     * This attribute maps to the column ID_EMPLEADO in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal idEmpleado;
    
    /**
     * This attribute maps to the column BLN_ESTATUS in the FECET_TRANSFERENCIA table.
     */
    private BigDecimal blnEstatus;
    
    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_TRANSFERENCIA table.
     */
    private Date fechaCreacion;
    
    /**
     * This attribute maps to the column ID_DOC_TRANSFERENCIA in the FECET_DOC_TRANSFERENCIA
     */
    private BigDecimal idDocTransferencia;
    
    /**
     * This attribute maps to the column FECHA_FIN in the FECET_DOC_TRANSFERENCIA
     */
    private Date fechaFin;
    
    /**
     * This attribute maps to the column BLN_ACTIVO in the FECET_DOC_TRANSFERENCIA
     */
    private BigDecimal blnActivo;
    
    private List<FecetDocTransferencia> lstDocumentos;



    /**
     * Method 'FecetTransferencia'
     *
     */
    public FecetTransferencia() {
    }

    /**
     * Method 'getIdTransferencia'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Method 'setIdTransferencia'
     *
     * @param idTransferencia
     */
    public void setIdTransferencia(final BigDecimal idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * Method 'getIdAraceOrigen'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAraceOrigen() {
        return idAraceOrigen;
    }

    /**
     * Method 'setIdAraceOrigen'
     *
     * @param idAraceOrigen
     */
    public void setIdAraceOrigen(final BigDecimal idAraceOrigen) {
        this.idAraceOrigen = idAraceOrigen;
    }

    /**
     * Method 'getIdAraceDestino'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAraceDestino() {
        return idAraceDestino;
    }

    /**
     * Method 'setIdAraceDestino'
     *
     * @param idAraceDestino
     */
    public void setIdAraceDestino(final BigDecimal idAraceDestino) {
        this.idAraceDestino = idAraceDestino;
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
     * Method 'getFechaTraspaso'
     *
     * @return Date
     */
    public Date getFechaTraspaso() {
        return (fechaTraspaso != null) ? (Date)fechaTraspaso.clone() : null;
    }

    /**
     * Method 'setFechaTraspaso'
     *
     * @param fechaTraspaso
     */
    public void setFechaTraspaso(Date fechaTraspaso) {
        this.fechaTraspaso = fechaTraspaso != null ? new Date(fechaTraspaso.getTime()) : null;
    }

    /**
     * Method 'getRfc'
     *
     * @return String
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Method 'setRfc'
     *
     * @param rfc
     */
    public void setRfc(final String rfc) {
        this.rfc = rfc;
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
     * Method 'getObservaciones'
     *
     * @return String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Method 'setObservaciones'
     *
     * @param observaciones
     */
    public void setObservaciones(final String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Method 'getEstatus'
     *
     * @return String
     */
    public BigDecimal getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(final BigDecimal estatus) {
        this.estatus = estatus;
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
     * Method 'createPk'
     *
     * @return FecetTransferenciaPk
     */
    public FecetTransferenciaPk createPk() {
        return new FecetTransferenciaPk(idTransferencia);
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
     * Method 'getFechaRechazo'
     *
     * @return Date
     */
    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date)fechaRechazo.clone() : null;
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
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }
    
    /**
     * Method 'setIdDocTransferencia'
     *
     * @param idDocTransferencia
     */
    public void setIdDocTransferencia(BigDecimal idDocTransferencia) {
        this.idDocTransferencia = idDocTransferencia;
    }

    /**
     * Method 'getIdDocTransferencia'
     *
     * @return idDocTransferencia
     */
    public BigDecimal getIdDocTransferencia() {
        return idDocTransferencia;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetTransferencia: ");
        ret.append("idTransferencia=" + idTransferencia);
        ret.append(", idAraceOrigen=" + idAraceOrigen);
        ret.append(", idAraceDestino=" + idAraceDestino);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", fechaTraspaso=" + fechaTraspaso);
        ret.append(", rfc=" + rfc);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", estatus=" + estatus);
        return ret.toString();
    }


    public Date getFechaTraspaso1() {
        return (fechaTraspaso != null) ? (Date)fechaTraspaso.clone() : null;
    }

    public List<FecetDocTransferencia> getLstDocumentos() {
        return lstDocumentos;
    }

    public void setLstDocumentos(List<FecetDocTransferencia> lstDocumentos) {
        this.lstDocumentos = lstDocumentos;
    }
}
