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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;

public class FecetPropuesta extends BaseModel {

    private static final long serialVersionUID = 1L;

    private static final int NUM_7 = 7;
    private static final int NUM_29 = 29;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_PROPUESTA table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE in the FECET_PROPUESTA table.
     */
    private BigDecimal idContribuyente;

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
     * This attribute represents whether the primitive attribute idRevision is null.
     */
    private boolean idRevisionNull = true;

    /**
     * This attribute maps to the column ID_TIPO_PROPUESTA in the FECET_PROPUESTA table.
     */
    private BigDecimal idTipoPropuesta;

    /**
     * This attribute represents whether the primitive attribute idTipoPropuesta is null.
     */
    private boolean idTipoPropuestaNull = true;

    /**
     * This attribute maps to the column ID_CAUSA_PROGRAMACION in the FECET_PROPUESTA table.
     */
    private BigDecimal idCausaProgramacion;

    /**
     * This attribute represents whether the primitive attribute idCausaProgramacion is null.
     */
    private boolean idCausaProgramacionNull = true;

    /**
     * This attribute maps to the column ID_SECTOR in the FECET_PROPUESTA table.
     */
    private BigDecimal idSector;

    /**
     * This attribute maps to the column ID_REGISTRO in the FECET_PROPUESTA table.
     */
    private String idRegistro;

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

    /**
     * This attribute maps to the column FECHA_PRESENTACION_REGIONAL in the FECET_PROPUESTA table.
     */
    private Date fechaPresentacionRegional;
    /**
     * This attribute maps to the column FECHA_INFORME_REGIONAL in the FECET_PROPUESTA table.
     */
    private Date fechaInformeRegional;

    /**
     * This attribute maps to the column PRIORIDAD in the FECET_PROPUESTA table.
     */
    private Boolean prioridad;

    /**
     * This attribute maps to the column ID_ORIGEN in the FECET_PROPUESTA table.
     */
    private BigDecimal origenPropuestaId;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_PROPUESTA table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECET_PROPUESTA table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column RFC_CREACION in the FECET_PROPUESTA table.
     */
    private String rfcCreacion;

    /**
     * This attribute maps to the column RFC_ADMINISTRADOR in the FECET_PROPUESTA table.
     */
    private String rfcAdministrador;

    /**
     * This attribute maps to the column RFC_SUBADMINISTRADOR in the FECET_PROPUESTA table.
     */
    private String rfcSubadministrador;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_PROPUESTA table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column RFC_FIRMANTE in the FECET_PROPUESTA table.
     */
    private String rfcFirmante;

    /**
     * This attribute maps to the column RFC_AUDITOR in the FECET_PROPUESTA table.
     */
    private String rfcAuditor;

    /**
     * This attribute maps to the column INFORME_PRESENTACION in the FECET_PROPUESTA table.
     */
    private String informePresentacion;

    /**
     * Este atributo almacena el objeto del contribuyente en la propuesta
     */
    private FecetContribuyente fecetContribuyente;

    /**
     * Este atributo almacena el objeto del contribuyente en la propuesta
     */
    private List<FecetDocExpediente> listaDocumentos;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_PROPUESTA table.
     */
    private BigDecimal idInsumo;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_PROPUESTA table.
     */
    private String idRegistroInsumo;

    private int sizeListaDocumentos;

    private FececSector fececSector;

    private FececSubprograma fececSubprograma;

    private FececEstatus fececEstatus;

    private AgrupadorEstatusPropuestasEnum estatusXGrupo;

    private EmpleadoDTO empleadoDto;

    private FececMetodo feceaMetodo;

    private FececArace fececArace;

    private FececTipoPropuesta fececTipoPropuesta;

    private FececCausaProgramacion fececCausaProgramacion;

    private FececRevision fececRevision;

    private FececTipoImpuesto fececTipoImpuesto;

    private AgaceOrden agaceOrden;

    private FececMetodo cambioMetodoOrden;

    private List<FecetImpuesto> lstImpuestos;

    private BigDecimal presuntiva;

    private TipoFechasComiteEnum tipoComite;

    private FececUnidadAdministrativa unidadAdministrativa;
    
    private boolean esProcesable;

    public FececUnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(FececUnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public TipoFechasComiteEnum getTipoComite() {
        return tipoComite;
    }

    public void setTipoComite(TipoFechasComiteEnum tipoComite) {
        this.tipoComite = tipoComite;
    }

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;

    /**
     * This attribute maps to the column CONSECUTIVO_ANO in the FECET_PROPUESTA table.
     */
    private BigDecimal consecutivoAnio;

    /**
     * This attribute maps to the column ID_PROGRAMADOR in the FECET_PROPUESTA table.
     */
    private BigDecimal programadorId;

    private String prioridadSugerida;

    private List<PapelesTrabajo> listaPapelesTrabajo;

    /**
     * Method 'FecetPropuesta'
     *
     */
    public FecetPropuesta() {
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
     * Method 'getIdContribuyente'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    /**
     * Method 'setIdContribuyente'
     *
     * @param idContribuyente
     */
    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    /**
     * Method 'getIdArace'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdArace() {
        return idArace;
    }

    /**
     * Method 'setIdArace'
     *
     * @param idArace
     */
    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Method 'getIdSubprograma'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    /**
     * Method 'setIdSubprograma'
     *
     * @param idSubprograma
     */
    public void setIdSubprograma(BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    /**
     * Method 'getIdMetodo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    /**
     * Method 'setIdMetodo'
     *
     * @param idMetodo
     */
    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    /**
     * Method 'getIdRevision'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRevision() {
        return idRevision;
    }

    /**
     * Method 'setIdRevision'
     *
     * @param idRevision
     */
    public void setIdRevision(BigDecimal idRevision) {
        this.idRevision = idRevision;
        this.idRevisionNull = false;
    }

    /**
     * Method 'setIdRevisionNull'
     *
     * @param value
     */
    public void setIdRevisionNull(boolean value) {
        this.idRevisionNull = value;
    }

    /**
     * Method 'isIdRevisionNull'
     *
     * @return boolean
     */
    public boolean isIdRevisionNull() {
        return idRevisionNull;
    }

    /**
     * Method 'getIdTipoPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    /**
     * Method 'setIdTipoPropuesta'
     *
     * @param idTipoPropuesta
     */
    public void setIdTipoPropuesta(BigDecimal idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
        this.idTipoPropuestaNull = false;
    }

    /**
     * Method 'setIdTipoPropuestaNull'
     *
     * @param value
     */
    public void setIdTipoPropuestaNull(boolean value) {
        this.idTipoPropuestaNull = value;
    }

    /**
     * Method 'isIdTipoPropuestaNull'
     *
     * @return boolean
     */
    public boolean isIdTipoPropuestaNull() {
        return idTipoPropuestaNull;
    }

    /**
     * Method 'getIdCausaProgramacion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCausaProgramacion() {
        return idCausaProgramacion;
    }

    /**
     * Method 'setIdCausaProgramacion'
     *
     * @param idCausaProgramacion
     */
    public void setIdCausaProgramacion(BigDecimal idCausaProgramacion) {
        this.idCausaProgramacion = idCausaProgramacion;
        this.idCausaProgramacionNull = false;
    }

    /**
     * Method 'setIdCausaProgramacionNull'
     *
     * @param value
     */
    public void setIdCausaProgramacionNull(boolean value) {
        this.idCausaProgramacionNull = value;
    }

    /**
     * Method 'isIdCausaProgramacionNull'
     *
     * @return boolean
     */
    public boolean isIdCausaProgramacionNull() {
        return idCausaProgramacionNull;
    }

    /**
     * Method 'getIdSector'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSector() {
        return idSector;
    }

    /**
     * Method 'setIdSector'
     *
     * @param idSector
     */
    public void setIdSector(BigDecimal idSector) {
        this.idSector = idSector;
    }

    /**
     * Method 'getFechaInicioPeriodo'
     *
     * @return Date
     */
    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    /**
     * Method 'setFechaInicioPeriodo'
     *
     * @param fechaInicioPeriodo
     */
    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime()) : null;
    }

    /**
     * Method 'getFechaFinPeriodo'
     *
     * @return Date
     */
    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    /**
     * Method 'setFechaFinPeriodo'
     *
     * @param fechaFinPeriodo
     */
    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()) : null;
    }

    /**
     * Method 'getFechaPresentacion'
     *
     * @return Date
     */
    public Date getFechaPresentacion() {
        return (fechaPresentacion != null) ? (Date) fechaPresentacion.clone() : null;
    }

    /**
     * Method 'setFechaPresentacion'
     *
     * @param fechaPresentacion
     */
    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion != null ? new Date(fechaPresentacion.getTime()) : null;
    }

    /**
     * Method 'getFechaInforme'
     *
     * @return Date
     */
    public Date getFechaInforme() {
        return (fechaInforme != null) ? (Date) fechaInforme.clone() : null;
    }

    /**
     * Method 'setFechaInforme'
     *
     * @param fechaInforme
     */
    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme != null ? new Date(fechaInforme.getTime()) : null;
    }

    /**
     * Method 'getPrioridad'
     *
     * @return String
     */
    public Boolean getPrioridad() {
        return prioridad;
    }

    /**
     * Method 'setPrioridad'
     *
     * @param prioridad
     */
    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
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
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
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
     * Method 'getRfcAdministrador'
     *
     * @return String
     */
    public String getRfcAdministrador() {
        return rfcAdministrador;
    }

    /**
     * Method 'setRfcAdministrador'
     *
     * @param rfcAdministrador
     */
    public void setRfcAdministrador(String rfcAdministrador) {
        this.rfcAdministrador = rfcAdministrador;
    }

    /**
     * Method 'getRfcSubadministrador'
     *
     * @return String
     */
    public String getRfcSubadministrador() {
        return rfcSubadministrador;
    }

    /**
     * Method 'setRfcSubadministrador'
     *
     * @param rfcSubadministrador
     */
    public void setRfcSubadministrador(String rfcSubadministrador) {
        this.rfcSubadministrador = rfcSubadministrador;
    }

    /**
     * Method 'getIdEstatus'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'setIdEstatus'
     *
     * @param idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * Method 'getRfcFirmante'
     *
     * @return String
     */
    public String getRfcFirmante() {
        return rfcFirmante;
    }

    /**
     * Method 'setRfcFirmante'
     *
     * @param rfcFirmante
     */
    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
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
    public void setRfcAuditor(String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    /**
     * Method 'getInformePresentacion'
     *
     * @return String
     */
    public String getInformePresentacion() {
        return informePresentacion;
    }

    /**
     * Method 'setInformePresentacion'
     *
     * @param informePresentacion
     */
    public void setInformePresentacion(final String informePresentacion) {
        this.informePresentacion = informePresentacion;
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

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    public void setListaDocumentos(List<FecetDocExpediente> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<FecetDocExpediente> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setSizeListaDocumentos(int sizeListaDocumentos) {
        this.sizeListaDocumentos = sizeListaDocumentos;
    }

    public int getSizeListaDocumentos() {
        return sizeListaDocumentos;
    }

    public void setFececSector(FececSector fececSector) {
        this.fececSector = fececSector;
    }

    public FececSector getFececSector() {
        return fececSector;
    }

    public void setFececSubprograma(FececSubprograma fececSubprograma) {
        this.fececSubprograma = fececSubprograma;
    }

    public FececSubprograma getFececSubprograma() {
        return fececSubprograma;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public AgrupadorEstatusPropuestasEnum getEstatusXGrupo() {
        return estatusXGrupo;
    }

    public void setEstatusXGrupo(AgrupadorEstatusPropuestasEnum estatusXGrupo) {
        this.estatusXGrupo = estatusXGrupo;
    }

    public void setEmpleadoDto(EmpleadoDTO empleadoDto) {
        this.empleadoDto = empleadoDto;
    }

    public EmpleadoDTO getEmpleadoDto() {
        return empleadoDto;
    }

    public void setFeceaMetodo(FececMetodo feceaMetodo) {
        this.feceaMetodo = feceaMetodo;
    }

    public FececMetodo getFeceaMetodo() {
        return feceaMetodo;
    }

    public void setFececArace(FececArace fececArace) {
        this.fececArace = fececArace;
    }

    public FececArace getFececArace() {
        return fececArace;
    }

    public void setFececTipoPropuesta(FececTipoPropuesta fececTipoPropuesta) {
        this.fececTipoPropuesta = fececTipoPropuesta;
    }

    public FececTipoPropuesta getFececTipoPropuesta() {
        return fececTipoPropuesta;
    }

    public void setFececCausaProgramacion(FececCausaProgramacion fececCausaProgramacion) {
        this.fececCausaProgramacion = fececCausaProgramacion;
    }

    public FececCausaProgramacion getFececCausaProgramacion() {
        return fececCausaProgramacion;
    }

    public void setFececRevision(FececRevision fececRevision) {
        this.fececRevision = fececRevision;
    }

    public FececRevision getFececRevision() {
        return fececRevision;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetPropuestaPk
     */
    public FecetPropuestaPk createPk() {
        return new FecetPropuestaPk(idPropuesta);
    }

    public void setFececTipoImpuesto(FececTipoImpuesto fececTipoImpuesto) {
        this.fececTipoImpuesto = fececTipoImpuesto;
    }

    public FececTipoImpuesto getFececTipoImpuesto() {
        return fececTipoImpuesto;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setFechaPresentacionRegional(Date fechaPresentacionRegional) {
        this.fechaPresentacionRegional = fechaPresentacionRegional != null ? new Date(fechaPresentacionRegional.getTime()) : null;
    }

    public Date getFechaPresentacionRegional() {
        return (fechaPresentacionRegional != null) ? (Date) fechaPresentacionRegional.clone() : null;
    }

    public void setFechaInformeRegional(Date fechaInformeRegional) {
        this.fechaInformeRegional = fechaInformeRegional != null ? new Date(fechaInformeRegional.getTime()) : null;
    }

    public Date getFechaInformeRegional() {
        return (fechaInformeRegional != null) ? (Date) fechaInformeRegional.clone() : null;
    }

    public void setOrigenPropuestaId(BigDecimal origenPropuestaId) {
        this.origenPropuestaId = origenPropuestaId;
    }

    public BigDecimal getOrigenPropuestaId() {
        return origenPropuestaId;
    }

    public void setConsecutivoAnio(BigDecimal consecutivoAnio) {
        this.consecutivoAnio = consecutivoAnio;
    }

    public BigDecimal getConsecutivoAnio() {
        return consecutivoAnio;
    }

    public List<FecetImpuesto> getLstImpuestos() {
        return lstImpuestos;
    }

    public void setLstImpuestos(List<FecetImpuesto> lstImpuestos) {
        this.lstImpuestos = lstImpuestos;
    }

    public void setIdRegistroInsumo(String idRegistroInsumo) {
        this.idRegistroInsumo = idRegistroInsumo;
    }

    public String getIdRegistroInsumo() {
        return idRegistroInsumo;
    }

    public void setProgramadorId(BigDecimal programadorId) {
        this.programadorId = programadorId;
    }

    public BigDecimal getProgramadorId() {
        return programadorId;
    }

    public void setAgaceOrden(AgaceOrden agaceOrden) {
        this.agaceOrden = agaceOrden;
    }

    public AgaceOrden getAgaceOrden() {
        return agaceOrden;
    }

    public void setCambioMetodoOrden(FececMetodo cambioMetodoOrden) {
        this.cambioMetodoOrden = cambioMetodoOrden;
    }

    public FececMetodo getCambioMetodoOrden() {
        return cambioMetodoOrden;
    }

    public void setPrioridadSugerida(String prioridadSugerida) {
        this.prioridadSugerida = prioridadSugerida;
    }

    public String getPrioridadSugerida() {
        return prioridadSugerida;
    }

    public List<PapelesTrabajo> getListaPapelesTrabajo() {
        return listaPapelesTrabajo;
    }

    public void setListaPapelesTrabajo(List<PapelesTrabajo> listaPapelesTrabajo) {
        this.listaPapelesTrabajo = listaPapelesTrabajo;
    }

    public boolean isEsProcesable() {
		return esProcesable;
	}

	public void setEsProcesable(boolean esProcesable) {
		this.esProcesable = esProcesable;
	}

	@Override
    public int hashCode() {
        int hash = NUM_7;
        hash = NUM_29 * hash + (this.idPropuesta != null ? this.idPropuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FecetPropuesta other = (FecetPropuesta) obj;
        if (this.idPropuesta != other.idPropuesta && (this.idPropuesta == null || !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        return true;
    }

}
