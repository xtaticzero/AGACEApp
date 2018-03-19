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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public class FecetRetroalimentacion extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_RETROALIMENTACION in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal idRetroalimentacion;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column ID_MOTIVO in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal idMotivo;

    private TipoEstatusEnum idEstatus;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_RETROALIMENTACION table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_RETROALIMENTACION table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column RFC_RETROALIMENTACION in the FECET_RETROALIMENTACION table.
     */
    private String rfcRetroalimentacion;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_RETROALIMENTACION table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_RETROALIMENTACION table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_RETROALIMENTACION in the FECET_RETROALIMENTACION table.
     */
    private Date fechaRetroalimentacion;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal estatus;

    /**
     * This attribute maps to the column RFC_RECHAZO in the FECET_RETROALIMENTACION table.
     */
    private String rfcRechazo;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the FECET_RETROALIMENTACION table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column DESCRIPCION_RECHAZO in the FECET_RETROALIMENTACION table.
     */
    private String descripcionRechazo;

    private FececMotivo fececMotivo;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    /**
     * This attribute maps to the column ID_EMPLEADO in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal idEmpleado;

    /**
     * This attribute maps to the column BLN_ESTATUS in the FECET_RETROALIMENTACION table.
     */
    private BigDecimal blnEstatus;

    /**
     * Numero de Documentos de la Retroalimentacion.
     */
    private BigDecimal numeroDocRetroalimentacion;

    /**
     * This attribute maps to the column ID_DOC_RETRO in the FECET_DOC_RETRO_PROPUESTA table.
     */
    private BigDecimal idDocRetroalimentacion;

    /**
     * This attribute maps to the column FECHA_FIN in the FECET_DOC_RETRO_PROPUESTA table.
     */
    private Date fechaFin;

    /**
     * This attribute maps to the column BLN_ACTIVO in the FECET_DOC_RETRO_PROPUESTA table.
     */
    private BigDecimal blnActivo;

    private EstadoBooleanodeRegistroEnum booleanEstatus;

    /**
     * This attribute maps to the column ID_ARACE in the FECET_PROPUESTA table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column ID_SUBPROGRAMA in the FECET_PROPUESTA table.
     */
    private BigDecimal idSubprograma;

    /**
     * This attribute maps to the column ID_METODO in the FECET_PROPUESTA table.
     */
    private BigDecimal idMetodo;

    /**
     * This attribute maps to the column ID_REVISION in the FECET_PROPUESTA table.
     */
    private BigDecimal idRevision;

    /**
     * This attribute maps to the column ID_TIPO_PROPUESTA in the FECET_PROPUESTA table.
     */
    private BigDecimal idTipoPropuesta;

    /**
     * This attribute maps to the column ID_CAUSA_PROGRAMACION in the FECET_PROPUESTA table.
     */
    private BigDecimal idCausaProgramacion;

    /**
     * This attribute represents whether the primitive attribute idCausaProgramacion is null.
     */

    /**
     * This attribute maps to the column ID_SECTOR in the FECET_PROPUESTA table.
     */
    private BigDecimal idSector;

    /**
     * This attribute maps to the column FECHA_INICIO_PERIODO in the FECET_PROPUESTA table.
     */
    private Date fechaInicioPeriodo;

    /**
     * This attribute maps to the column FECHA_FIN_PERIODO in the FECET_PROPUESTA table.
     */
    private Date fechaFinPeriodo;

    /**
     * This attribute maps to the column FECHA_PRESENTACION in the FECET_PROPUESTA table.
     */
    private Date fechaPresentacion;

    /**
     * This attribute maps to the column FECHA_INFORME in the FECET_PROPUESTA table.
     */
    private Date fechaInforme;

    private String prioridadSugerida;

    private FececSector fececSector;

    private FececSubprograma fececSubprograma;

    private FececMetodo feceaMetodo;

    private transient FececArace fececArace;

    private FececTipoPropuesta fececTipoPropuesta;

    private FececCausaProgramacion fececCausaProgramacion;

    private FececRevision fececRevision;

    private FececTipoImpuesto fececTipoImpuesto;

    private List<FecetImpuesto> lstImpuestos;

    private BigDecimal idRetroalimentacionOrigen;

    /**
     * Method 'FecetRetroalimentacion'
     *
     */
    public FecetRetroalimentacion() {
    }

    /**
     * Method 'getIdRetroalimentacion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    /**
     * Method 'setIdRetroalimentacion'
     *
     * @param idRetroalimentacion
     */
    public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
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
     * Method 'setIdMotivo'
     *
     * @param idMotivo
     */
    public void setIdMotivo(final BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
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
    public BigDecimal getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(BigDecimal estatus) {
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
     * Method 'setDescripcionRechazo'
     *
     * @param descripcionRechazo
     */
    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
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
     * Method 'createPk'
     *
     * @return FecetRetroalimentacionPk
     */
    public FecetRetroalimentacionPk createPk() {
        return new FecetRetroalimentacionPk(idRetroalimentacion);
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
     * Method 'setNumeroDocRetroalimentacion'
     *
     * @param numeroDocRetroalimentacion
     */
    public void setNumeroDocRetroalimentacion(BigDecimal numeroDocRetroalimentacion) {
        this.numeroDocRetroalimentacion = numeroDocRetroalimentacion;
    }

    /**
     * Method 'getNumeroDocRetroalimentacion'
     *
     * @return numeroDocRetroalimentacion
     */
    public BigDecimal getNumeroDocRetroalimentacion() {
        return numeroDocRetroalimentacion;
    }

    /**
     * Method 'setIdDocRetroalimentacion'
     *
     * @param idDocRetroalimentacion
     */
    public void setIdDocRetroalimentacion(BigDecimal idDocRetroalimentacion) {
        this.idDocRetroalimentacion = idDocRetroalimentacion;
    }

    /**
     * Method 'getIdDocRetroalimentacion'
     *
     * @return idDocRetroalimentacion
     */
    public BigDecimal getIdDocRetroalimentacion() {
        return idDocRetroalimentacion;
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

    public EstadoBooleanodeRegistroEnum getBooleanEstatus() {
        return booleanEstatus;
    }

    public void setBooleanEstatus(EstadoBooleanodeRegistroEnum booleanEstatus) {
        this.booleanEstatus = booleanEstatus;
    }

    public TipoEstatusEnum getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(TipoEstatusEnum idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public BigDecimal getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(BigDecimal idRevision) {
        this.idRevision = idRevision;
    }

    public BigDecimal getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    public void setIdTipoPropuesta(BigDecimal idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }

    public BigDecimal getIdCausaProgramacion() {
        return idCausaProgramacion;
    }

    public void setIdCausaProgramacion(BigDecimal idCausaProgramacion) {
        this.idCausaProgramacion = idCausaProgramacion;
    }

    public BigDecimal getIdSector() {
        return idSector;
    }

    public void setIdSector(BigDecimal idSector) {
        this.idSector = idSector;
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

    public Date getFechaPresentacion() {
        return (fechaPresentacion != null) ? (Date) fechaPresentacion.clone() : null;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion != null ? new Date(fechaPresentacion.getTime()) : null;
    }

    public Date getFechaInforme() {
        return (fechaInforme != null) ? (Date) fechaInforme.clone() : null;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme != null ? new Date(fechaInforme.getTime()) : null;
    }

    public FececSector getFececSector() {
        return fececSector;
    }

    public void setFececSector(FececSector fececSector) {
        this.fececSector = fececSector;
    }

    public FececSubprograma getFececSubprograma() {
        return fececSubprograma;
    }

    public void setFececSubprograma(FececSubprograma fececSubprograma) {
        this.fececSubprograma = fececSubprograma;
    }

    public FececMetodo getFeceaMetodo() {
        return feceaMetodo;
    }

    public void setFeceaMetodo(FececMetodo feceaMetodo) {
        this.feceaMetodo = feceaMetodo;
    }

    public FececArace getFececArace() {
        return fececArace;
    }

    public void setFececArace(FececArace fececArace) {
        this.fececArace = fececArace;
    }

    public FececTipoPropuesta getFececTipoPropuesta() {
        return fececTipoPropuesta;
    }

    public void setFececTipoPropuesta(FececTipoPropuesta fececTipoPropuesta) {
        this.fececTipoPropuesta = fececTipoPropuesta;
    }

    public FececCausaProgramacion getFececCausaProgramacion() {
        return fececCausaProgramacion;
    }

    public void setFececCausaProgramacion(FececCausaProgramacion fececCausaProgramacion) {
        this.fececCausaProgramacion = fececCausaProgramacion;
    }

    public FececRevision getFececRevision() {
        return fececRevision;
    }

    public void setFececRevision(FececRevision fececRevision) {
        this.fececRevision = fececRevision;
    }

    public FececTipoImpuesto getFececTipoImpuesto() {
        return fececTipoImpuesto;
    }

    public void setFececTipoImpuesto(FececTipoImpuesto fececTipoImpuesto) {
        this.fececTipoImpuesto = fececTipoImpuesto;
    }

    public String getPrioridadSugerida() {
        return prioridadSugerida;
    }

    public void setPrioridadSugerida(String prioridadSugerida) {
        this.prioridadSugerida = prioridadSugerida;
    }

    public List<FecetImpuesto> getLstImpuestos() {
        return lstImpuestos;
    }

    public void setLstImpuestos(List<FecetImpuesto> lstImpuestos) {
        this.lstImpuestos = lstImpuestos;
    }

    public BigDecimal getIdRetroalimentacionOrigen() {
        return idRetroalimentacionOrigen;
    }

    public void setIdRetroalimentacionOrigen(BigDecimal idRetroalimentacionOrigen) {
        this.idRetroalimentacionOrigen = idRetroalimentacionOrigen;
    }

}
