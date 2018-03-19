package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetPropPendiente extends BaseModel {
    private static final long serialVersionUID = -3181178305080378847L;
    /**
     * This attribute maps to the column ID_PROP_PENDIENTE in the FECET_PROP_PENDIENTE table.
     */
    private BigDecimal idPropPendiente;

    /**
     * This attribute maps to the column ID_PROP_PENDIENTE in the FECET_PROP_PENDIENTE table.
     */
    private BigDecimal idDocPendiente;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_PROP_PENDIENTE table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_PROP_PENDIENTE table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_PROP_PENDIENTE table.
     */
    private Date fechaPendiente;

    /**
     * This attribute maps to the column RFC_CREACION in the FECET_PROP_PENDIENTE table.
     */
    private String rfcCreacion;

    /**
     * This attribute maps to the column OBSERVACIONES in the FECET_PROP_PENDIENTE table.
     */
    private String observaciones;

    /**
     * This attribute maps to the column ESTATUS in the FECET_PROP_PENDIENTE table.
     */
    private Character estatus;

    /**
     * Method 'FecetPropPendiente'
     *
     */

    private String nombreArchivo;

    /**
     * This attribute maps to the column archivo in the FECET_PROP_PENDIENTE table.
     */
    private transient InputStream archivo;
    /**
     * This attribute maps to the column rutaArchivo in the FECET_PROP_PENDIENTE table.
     */

    private String rutaArchivo;

    /**
     * This attribute maps to the column fechaFin in the FECET_PROP_PENDIENTE table.
     */

    private Date fechaFin;
    /**
     * This attribute maps to the column blnArchivo in the FECET_PROP_PENDIENTE table.
     */

    private boolean blnArchivo;

    private List<FecetPropPendiente> listDocPendiente;

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public FecetPropPendiente() {
    }

    /**
     * Method 'getIdPropPendiente'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPropPendiente() {
        return idPropPendiente;
    }

    /**
     * Method 'setIdPropPendiente'
     *
     * @param idPropPendiente
     */
    public void setIdPropPendiente(BigDecimal idPropPendiente) {
        this.idPropPendiente = idPropPendiente;
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
    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
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
     * Method 'getRfcCreacion'
     *
     * @return String
     */
    public String getRfcCreacion() {
        return rfcCreacion;
    }

    /**
     * Method 'setRfcCreacion'
     *
     * @param rfcCreacion
     */
    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
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
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Method 'getEstatus'
     *
     * @return String
     */
    public Character getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetPropPendientePk
     */
    public FecetPropPendientePk createPk() {
        return new FecetPropPendientePk(idPropPendiente);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPropPendiente: ");
        ret.append("idPropPendiente=").append(idPropPendiente);
        ret.append(", idPropuesta=").append(idPropuesta);
        ret.append(", fechaCreacion=").append(fechaCreacion);
        ret.append(", rfcCreacion=").append(rfcCreacion);
        ret.append(", observaciones=").append(observaciones);
        ret.append(", estatus=").append(estatus);
        ret.append(", nombreArchivo=").append(nombreArchivo);
        ret.append(", archivo=").append(archivo);
        ret.append(", fechaPendiente=").append(fechaPendiente);
        ret.append(", fechaFin=").append(fechaFin);
        ret.append(", blnArchivo=").append(blnArchivo);

        return ret.toString();
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaPendiente() {
        return (fechaPendiente != null) ? (Date) fechaPendiente.clone() : null;
    }

    public void setFechaPendiente(Date fechaPendiente) {
        this.fechaPendiente = fechaPendiente != null ? new Date(fechaPendiente.getTime()) : null;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public BigDecimal getIdDocPendiente() {
        return idDocPendiente;
    }

    public void setIdDocPendiente(BigDecimal idDocPendiente) {
        this.idDocPendiente = idDocPendiente;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public boolean isBlnArchivo() {
        return blnArchivo;
    }

    public void setBlnArchivo(boolean blnArchivo) {
        this.blnArchivo = blnArchivo;
    }

    public List<FecetPropPendiente> getListDocPendiente() {
        return listDocPendiente;
    }

    public void setListDocPendiente(List<FecetPropPendiente> listDocPendiente) {
        this.listDocPendiente = listDocPendiente;
    }

}
