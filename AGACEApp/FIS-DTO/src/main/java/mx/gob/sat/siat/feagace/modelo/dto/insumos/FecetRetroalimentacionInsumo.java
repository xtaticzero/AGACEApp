/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;

public class FecetRetroalimentacionInsumo extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 5212449427131115559L;

    /**
     * This attribute maps to the column ID_RETROALIMENTACION_INSUMO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idRetroalimentacionInsumo;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idInsumo;

    private String idRegistroInsumo;

    /**
     * This attribute maps to the column ID_MOTIVO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idMotivo;

    /**
     * This attribute represents whether the primitive attribute idMotivo is null.
     */
    private boolean idMotivoNull = true;

    /**
     * This attribute maps to the column MOTIVO_ACIACE in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String motivoAciace;

    /**
     * This attribute maps to the column MOTIVO_SUBADMINISTRADOR in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String motivoSubadministrador;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column RFC_RETROALIMENTACION in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String rfcRetroalimentacion;

    /**
     * This attribute maps to the column FECHA_RETROALIMENTACION in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaRetroalimentacion;

    /**
     * This attribute maps to the column ESTATUS in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String estatus;

    /**
     * This attribute maps to the column RFC_RECHAZO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String rfcRechazo;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column DESCRIPCION_RECHAZO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private String descripcionRechazo;

    /**
     * This attribute maps to the column DESCRIPCION_RECHAZO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private FececMotivo fececMotivo;

    /**
     * This attribute maps to the column ID_ARACE in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idUnidadAdministrativa;

    /**
     * This attribute maps to the column ID_SUBPROGRAMA in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idSubprograma;

    /**
     * This attribute maps to the column ID_SECTOR in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idSector;

    /**
     * This attribute maps to the column FECHA_INICIO_PERIODO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaInicioPeriodo;

    /**
     * This attribute maps to the column FECHA_FIN_PERIODO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private Date fechaFinPeriodo;

    /**
     * This attribute maps to the column ID_PERIODO in the FECET_RETROALIMENTACION_INSUMO table.
     */
    private BigDecimal idPrioridad;

    private String valorPrioridad;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    private String descripcionSubprograma;

    private String descripcionSector;

    private BigDecimal numeroSolicitudes;

    private BigDecimal numeroSolicitudesRetro;

    private String nombreUnidadAdministrativa;

    public String getNombreUnidadAdministrativa() {
        return nombreUnidadAdministrativa;
    }

    public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
        this.nombreUnidadAdministrativa = nombreUnidadAdministrativa;
    }

    public BigDecimal getNumeroSolicitudes() {
        return numeroSolicitudes;
    }

    public void setNumeroSolicitudes(BigDecimal numeroSolicitudes) {
        this.numeroSolicitudes = numeroSolicitudes;
    }

    public BigDecimal getNumeroSolicitudesRetro() {
        return numeroSolicitudesRetro;
    }

    public void setNumeroSolicitudesRetro(BigDecimal numeroSolicitudesRetro) {
        this.numeroSolicitudesRetro = numeroSolicitudesRetro;
    }

    /**
     * Method 'FecetRetroalimentacionInsumo'
     * 
     */
    public FecetRetroalimentacionInsumo() {
    }

    /**
     * Method 'getIdRetroalimentacionInsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdRetroalimentacionInsumo() {
        return idRetroalimentacionInsumo;
    }

    /**
     * Method 'setIdRetroalimentacionInsumo'
     * 
     * @param idRetroalimentacionInsumo
     */
    public void setIdRetroalimentacionInsumo(BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    /**
     * Method 'getIdInsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    /**
     * Method 'setIdInsumo'
     * 
     * @param idInsumo
     */
    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
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
    public void setIdMotivo(BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
        this.idMotivoNull = false;
    }

    /**
     * Method 'setIdMotivoNull'
     * 
     * @param value
     */
    public void setIdMotivoNull(boolean value) {
        this.idMotivoNull = value;
    }

    /**
     * Method 'isIdMotivoNull'
     * 
     * @return boolean
     */
    public boolean isIdMotivoNull() {
        return idMotivoNull;
    }

    public String getMotivoAciace() {
        return motivoAciace;
    }

    public void setMotivoAciace(String motivoAciace) {
        this.motivoAciace = motivoAciace;
    }

    public String getMotivoSubadministrador() {
        return motivoSubadministrador;
    }

    public void setMotivoSubadministrador(String motivoSubadministrador) {
        this.motivoSubadministrador = motivoSubadministrador;
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
     * Method 'getRfcRetroalimentacion'
     * 
     * @return String
     */
    public String getRfcRetroalimentacion() {
        return rfcRetroalimentacion;
    }

    /**
     * Method 'setRfcRetroalimentacion'
     * 
     * @param rfcRetroalimentacion
     */
    public void setRfcRetroalimentacion(String rfcRetroalimentacion) {
        this.rfcRetroalimentacion = rfcRetroalimentacion;
    }

    /**
     * Method 'getFechaRetroalimentacion'
     * 
     * @return Date
     */
    public Date getFechaRetroalimentacion() {
        return (fechaRetroalimentacion != null) ? (Date) fechaRetroalimentacion.clone() : null;
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
     * Method 'getDescripcionRechazo'
     * 
     * @return String
     */
    public String getDescripcionRechazo() {
        return descripcionRechazo;
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
     * Method 'setDescripcionRechazo'
     * 
     * @param descripcionRechazo
     */
    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    /**
     * Method 'createPk'
     * 
     * @return FecetRetroalimentacionInsumoPk
     */
    public FecetRetroalimentacionInsumoPk createPk() {
        return new FecetRetroalimentacionInsumoPk(idRetroalimentacionInsumo);
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }

    public BigDecimal getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(BigDecimal idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    public BigDecimal getIdSector() {
        return idSector;
    }

    public void setIdSector(BigDecimal idSector) {
        this.idSector = idSector;
    }

    public String getDescripcionSubprograma() {
        return descripcionSubprograma;
    }

    public void setDescripcionSubprograma(String descripcionSubprograma) {
        this.descripcionSubprograma = descripcionSubprograma;
    }

    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime()) : null;
    }

    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()) : null;
    }

    public BigDecimal getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(BigDecimal idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getValorPrioridad() {
        return valorPrioridad;
    }

    public void setValorPrioridad(String valorPrioridad) {
        this.valorPrioridad = valorPrioridad;
    }

    public String getIdRegistroInsumo() {
        return idRegistroInsumo;
    }

    public void setIdRegistroInsumo(String idRegistroInsumo) {
        this.idRegistroInsumo = idRegistroInsumo;
    }
}
