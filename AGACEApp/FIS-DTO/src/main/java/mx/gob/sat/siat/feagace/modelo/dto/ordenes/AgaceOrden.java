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
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegal;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

public class AgaceOrden extends BaseModel implements NotificableNyV {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_ORDEN table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column ID_METODO in the FECET_ORDEN table.
     */
    private BigDecimal idMetodo;

    /**
     * This attribute maps to the column ID_REVISION in the FECET_ORDEN table.
     */
    private BigDecimal idRevision;

    /**
     * This attribute maps to the column NUMERO_ORDEN in the FECET_ORDEN table.
     */
    private String numeroOrden;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_ORDEN
     * table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECET_ORDEN table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column PRIORIDAD in the FECET_ORDEN table.
     */
    private Boolean prioridad;
    
    private String prioridadSugerida;

    /**
     * This attribute represents whether the primitive attribute prioridad is
     * null.
     */
    private boolean prioridadNull = true;

    /**
     * This attribute maps to the column FOLIO_NYV in the FECET_ORDEN table.
     */
    private String folioNYV;

    /**
     * This attribute maps to the column CADENA_ORIGINAL in the FECET_ORDEN
     * table.
     */
    private String cadenaOriginal;

    /**
     * This attribute maps to the column FIRMA_ELECTRONICA in the FECET_ORDEN
     * table.
     */
    private String firmaElectronica;

    /**
     * This attribute maps to the column FECHA_NOTIF_NYV in the FECET_ORDEN
     * table.
     */
    private Date fechaNotifNYV;

    /**
     * This attribute maps to the column FECHA_NOTIF_CONT in the FECET_ORDEN
     * table.
     */
    private Date fechaNotifCont;

    /**
     * This attribute maps to the column FECHA_SURTE_EFECTOS in the FECET_ORDEN
     * table.
     */
    private Date fechaSurteEfectos;

    /**
     * This attribute maps to the column DIAS_RESTANTES_PLAZO in the FECET_ORDEN
     * table.
     */
    private Integer diasRestantesPlazo;

    /**
     * This attribute maps to the column DIAS_HABILES in the FECET_ORDEN table.
     */
    private Boolean diasHabiles;

    /**
     * This attribute maps to the column SUSPENCION_PLAZO in the FECET_ORDEN
     * table.
     */
    private Boolean suspencionPlazo;

    /**
     * This attribute maps to the column DIAS_RESTANTES_DOCUMENTOS in the
     * FECET_ORDEN table.
     */
    private Integer diasRestantesDocumentos;

    /**
     * This attribute maps to the column SEMAFORO in the FECET_ORDEN table.
     */
    private Integer semaforo;

    /**
     * This attribute maps to the column FECHA_INTEGRA_EXP in the FECET_ORDEN
     * table.
     */
    private Date fechaIntegraExp;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE in the FECET_ORDEN
     * table.
     */
    private BigDecimal idContribuyente;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_ORDEN table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column ID_AUDITOR in the FECET_ORDEN table.
     */
    private BigDecimal idAuditor;

    /**
     * This attribute maps to the column ID_FIRMANTE in the FECET_ORDEN table.
     */
    private BigDecimal idFirmante;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_ORDEN table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column ID_REGISTRO_PROPUESTA in the
     * FECET_ORDEN table.
     */
    private String idRegistroPropuesta;

    /**
     * This attribute maps to the column FECHA_REACTIVAR_PLAZO in the
     * FECET_ORDEN table.
     */
    private Date fechaReactivarPlazo;

    /**
     * This attribute maps to the column FECHA_SUSPENCION_PLAZO in the
     * FECET_ORDEN table.
     */
    private Date fechaSuspencionPlazo;

    /**
     * This attribute maps to the column FOLIO_OFICIO in the FECET_ORDEN table.
     */
    private String folioOficio;

    /**
     * Este atributo mapea las columnas de la tabla FECEC_METODO
     */
    private FececMetodo feceaMetodo;

    /**
     * Este atributo mapea las columnas de la tabla FECEC_AGTE_ADUANAL
     */
    private FececAgteAduanal fececAgteAduanal;

    /**
     * Este atributo mapea las columnas de la tabla FECEC_REP_LEGAL
     */
    private FececRepLegal fececRepLegal;

    /**
     * Este atributo mapea el objeto de la tabla detalle de NyV
     */
    private FecetDetalleNyV fecetDetalleNyV;

    /**
     *
     */
    private boolean prioridadAlta;
    private Integer diasResolucionDefinitiva;

    /**
     *
     */
    private transient InputStream archivoOrden;
    private transient InputStream archivoPromocion;

    /**
     * este atributo contendra la opcion para filtrar su el auditor podra cargar
     * de documentos al contribuyente, NO ESTA MAPEADO A LA BASE DE DATOS.
     */
    private boolean cargaDocumentosVigente;

    private FecetContribuyente fecetContribuyente;

    /**
     * este atributo contendra la descripcion de el tiempo restante del plazo,
     * NO ESTA MAPEADO A LA BASE DE DATOS.
     */
    private String descripcionPlazoRestante;

    /**
     * este atributo contendra la descripcion de el tiempo restante del plazo de
     * la carga de documentos, NO ESTA MAPEADO A LA BASE DE DATOS.
     */
    private String descripcionDiasRestanteDocumentos;

    /**
     * este objeto contendra la informacion del estatus NO ESTA MAPEADO A LA
     * BASE DE DATOS.
     */
    private FececEstatus fececEstatus;

    /**
     * esta propiedad contendra la informacion del semaforo a mostrar NO ESTA
     * MAPEADO A LA BASE DE DATOS.
     */
    private String imagenSemaforo;
    
    private String descripcionSemaforo;

    private boolean blnCompulsa;

    private FecetAsociado fecetAsociado;

    private Long idNyV;
    
    private DatosNotificable datosNotificable;
    
    private AgrupadorEstatusOrdenesEnum estatusXGrupo;
    
    private BigDecimal idArace;
    
    private FececArace fececArace;
    
    private List<FecetDocOrden> docOrden;
    
    private List<FecetOficio> oficiosFirmados;
    
    private String descripcionCifras;
    
    private BigDecimal totalCifras;
    
    private boolean mostrarNumeroOrden;
    
    private String numeroReferencia;

    /**
     * Method 'AgaceOrden'
     *
     */
    public AgaceOrden() {
    }

    /**
     * Method 'getIdOrden'
     *
     * @return Long
     */
    public BigDecimal getIdOrden() {
        return idOrden;
    }

    /**
     * Method 'setIdOrden'
     *
     * @param idOrden
     */
    public void setIdOrden(final BigDecimal idOrden) {
        this.idOrden = idOrden;
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
    public void setIdMetodo(final BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    /**
     * Method 'setIdRevision'
     *
     * @param idRevision
     */
    public void setIdRevision(BigDecimal idRevision) {
        this.idRevision = idRevision;
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
     * Method 'getNumeroOrden'
     *
     * @return String
     */
    public String getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * Method 'setNumeroOrden'
     *
     * @param numeroOrden
     */
    public void setNumeroOrden(final String numeroOrden) {
        this.numeroOrden = numeroOrden;
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
    public void setFechaCreacion(final Date fechaCreacion) {
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
     * Method 'setFechabaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
        this.prioridadNull = false;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    /**
     * Method 'setPrioridadNull'
     *
     * @param value
     */
    public void setPrioridadNull(final boolean value) {
        this.prioridadNull = value;
    }

    /**
     * Method 'isPrioridadNull'
     *
     * @return boolean
     */
    public boolean isPrioridadNull() {
        return prioridadNull;
    }

    public void setFolioNYV(String folioNYV) {
        this.folioNYV = folioNYV;
    }

    public String getFolioNYV() {
        return folioNYV;
    }

    public void setCadenaOriginal(final String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setFirmaElectronica(final String firmaElectronica) {
        this.firmaElectronica = firmaElectronica;
    }

    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    /**
     * Method 'getFechaNotifNYV'
     *
     * @return Date
     */
    public Date getFechaNotifNYV() {
        return (fechaNotifNYV != null) ? (Date) fechaNotifNYV.clone() : null;
    }

    /**
     * Method 'setFechaNotifNYV'
     *
     * @param fechaNotifNYV
     */
    public void setFechaNotifNYV(final Date fechaNotifNYV) {
        this.fechaNotifNYV = fechaNotifNYV != null ? new Date(fechaNotifNYV.getTime()) : null;
    }

    /**
     * Method 'getFechaNotifCont'
     *
     * @return Date
     */
    public Date getFechaNotifCont() {
        return (fechaNotifCont != null) ? (Date) fechaNotifCont.clone() : null;
    }

    /**
     * Method 'setFechaNotifCont'
     *
     * @param fechaNotifCont
     */
    public void setFechaNotifCont(final Date fechaNotifCont) {
        this.fechaNotifCont = fechaNotifCont != null ? new Date(fechaNotifCont.getTime()) : null;
    }

    public void setFechaSurteEfectos(final Date fechaSurteEfectos) {
        this.fechaSurteEfectos = fechaSurteEfectos != null ? new Date(fechaSurteEfectos.getTime()) : null;
    }

    public Date getFechaSurteEfectos() {
        return (fechaSurteEfectos != null) ? (Date) fechaSurteEfectos.clone() : null;
    }

    public void setDiasRestantesPlazo(Integer diasRestantesPlazo) {
        this.diasRestantesPlazo = diasRestantesPlazo;
    }

    public Integer getDiasRestantesPlazo() {
        return diasRestantesPlazo;
    }

    public void setDiasHabiles(Boolean diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public Boolean getDiasHabiles() {
        return diasHabiles;
    }

    public void setSuspencionPlazo(Boolean suspencionPlazo) {
        this.suspencionPlazo = suspencionPlazo;
    }

    public Boolean getSuspencionPlazo() {
        return suspencionPlazo;
    }

    public void setDiasRestantesDocumentos(Integer diasRestantesDocumentos) {
        this.diasRestantesDocumentos = diasRestantesDocumentos;
    }

    public Integer getDiasRestantesDocumentos() {
        return diasRestantesDocumentos;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

    public Integer getSemaforo() {
        return semaforo;
    }

    /**
     * Method 'getFechaIntegraExp'
     *
     * @return Date
     */
    public Date getFechaIntegraExp() {
        return (fechaIntegraExp != null) ? (Date) fechaIntegraExp.clone() : null;
    }

    /**
     * Method 'setFechaIntegraExp'
     *
     * @param fechaIntegraExp
     */
    public void setFechaIntegraExp(final Date fechaIntegraExp) {
        this.fechaIntegraExp = fechaIntegraExp != null ? new Date(fechaIntegraExp.getTime()) : null;
    }

    /**
     * Method 'getFechaReactivarPlazo'
     *
     * @return Date
     */
    public Date getFechaReactivarPlazo() {
        return (fechaReactivarPlazo != null) ? (Date) fechaReactivarPlazo.clone() : null;
    }

    /**
     * Method 'setFechaReactivarPlazo'
     *
     * @param fechaReactivarPlazo
     */
    public void setFechaReactivarPlazo(final Date fechaReactivarPlazo) {
        this.fechaReactivarPlazo = fechaReactivarPlazo != null ? new Date(fechaReactivarPlazo.getTime()) : null;
    }

    /**
     * Method 'getFechaSuspencionPlazo'
     *
     * @return Date
     */
    public Date getFechaSuspencionPlazo() {
        return (fechaSuspencionPlazo != null) ? (Date) fechaSuspencionPlazo.clone() : null;
    }

    /**
     * Method 'setFechaIntegraExp'
     *
     * @param fechaSuspencionPlazo
     */
    public void setFechaSuspencionPlazo(final Date fechaSuspencionPlazo) {
        this.fechaSuspencionPlazo = fechaSuspencionPlazo != null ? new Date(fechaSuspencionPlazo.getTime()) : null;
    }

    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdRegistroPropuesta(String idRegistroPropuesta) {
        this.idRegistroPropuesta = idRegistroPropuesta;
    }

    public String getIdRegistroPropuesta() {
        return idRegistroPropuesta;
    }

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    /**
     * Metodo setFececRepLegal
     *
     * @param fececRepLegal
     */
    public void setFececRepLegal(final FececRepLegal fececRepLegal) {
        this.fececRepLegal = fececRepLegal;
    }

    /**
     * Metodo getFececRepLegal
     *
     * @return fececRepLegal
     */
    public FececRepLegal getFececRepLegal() {
        return fececRepLegal;
    }

    /**
     * Metodo setFececAgteAduanal
     *
     * @param fececAgteAduanal
     */
    public void setFececAgteAduanal(final FececAgteAduanal fececAgteAduanal) {
        this.fececAgteAduanal = fececAgteAduanal;
    }

    /**
     * Metodo getFececAgteAduanal
     *
     * @return fececAgteAduanal
     */
    public FececAgteAduanal getFececAgteAduanal() {
        return fececAgteAduanal;
    }

    /**
     * Method setFeceaMetodo
     *
     * @param feceaMetodo
     */
    public void setFeceaMetodo(final FececMetodo feceaMetodo) {
        this.feceaMetodo = feceaMetodo;
    }

    /**
     * Method getFeceaMetodo
     *
     * @return FeceaMetodo
     */
    public FececMetodo getFeceaMetodo() {
        return feceaMetodo;
    }

    /**
     * Method setPrioridadAlta
     *
     * @param prioridadAlta
     */
    public void setPrioridadAlta(final boolean prioridadAlta) {
        this.prioridadAlta = prioridadAlta;
    }

    /**
     * Method isPrioridadAlta
     *
     * @return boolean
     */
    public boolean isPrioridadAlta() {
        return prioridadAlta;
    }

    public void setDiasResolucionDefinitiva(final Integer diasResolucionDefinitiva) {
        this.diasResolucionDefinitiva = diasResolucionDefinitiva;
    }

    public Integer getDiasResolucionDefinitiva() {
        return diasResolucionDefinitiva;
    }

    /**
     * Method setArchivoOrden
     *
     * @param archivoOrden
     */
    public void setArchivoOrden(final InputStream archivoOrden) {
        this.archivoOrden = archivoOrden;
    }

    /**
     * Method getArchivoOrden
     *
     * @return InputStream
     */
    public InputStream getArchivoOrden() {
        return archivoOrden;
    }

    /**
     * Method setCargaDocumentosVigente
     *
     * @param cargaDocumentosVigente
     */
    public void setCargaDocumentosVigente(final boolean cargaDocumentosVigente) {
        this.cargaDocumentosVigente = cargaDocumentosVigente;
    }

    /**
     * Method isCargaDocumentosVigente
     *
     * @return boolean
     */
    public boolean isCargaDocumentosVigente() {
        return cargaDocumentosVigente;
    }

    /**
     * Method setArchivoPromocion
     *
     * @param archivoPromocion
     */
    public void setArchivoPromocion(final InputStream archivoPromocion) {
        this.archivoPromocion = archivoPromocion;
    }

    /**
     * Method getArchivoPromocion
     *
     * @return InputStream
     */
    public InputStream getArchivoPromocion() {
        return archivoPromocion;
    }

    public void setDescripcionPlazoRestante(String descripcionPlazoRestante) {
        this.descripcionPlazoRestante = descripcionPlazoRestante;
    }

    public String getDescripcionPlazoRestante() {
        return descripcionPlazoRestante;
    }

    public String getDescripcionDiasRestanteDocumentos() {
        return descripcionDiasRestanteDocumentos;
    }

    public void setDescripcionDiasRestanteDocumentos(
            String descripcionDiasRestanteDocumentos) {
        this.descripcionDiasRestanteDocumentos = descripcionDiasRestanteDocumentos;
    }

    public void setFececEstatus(final FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setImagenSemaforo(final String imagenSemaforo) {
        this.imagenSemaforo = imagenSemaforo;
    }

    public String getImagenSemaforo() {
        return imagenSemaforo;
    }

    /**
     * Method 'createPk'
     *
     * @return AgaceOrdenPk
     */
    public AgaceOrdenPk createPk() {
        return new AgaceOrdenPk(idOrden);
    }

    public void setFolioOficio(String folioOficio) {
        this.folioOficio = folioOficio;
    }

    public String getFolioOficio() {
        return folioOficio;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.AgaceOrden: ");
        ret.append("idOrden=" + idOrden);
        ret.append(", idMetodo=" + idMetodo);
        ret.append(", idRevision=" + idRevision);
        ret.append(", numeroOrden=" + numeroOrden);
        ret.append(", fechaCreacion=" + fechaCreacion);
        ret.append(", fechaBaja=" + fechaBaja);
        ret.append(", prioridad=" + prioridad);
        ret.append(", folioNYV=" + folioNYV);
        ret.append(", cadenaOriginal=" + cadenaOriginal);
        ret.append(", firmaElectronica=" + firmaElectronica);
        ret.append(", fechaNotifNYV=" + fechaNotifNYV);
        ret.append(", fechaNotifCont=" + fechaNotifCont);
        ret.append(", fechaSurteEfectos=" + fechaSurteEfectos);
        ret.append(", diasRestantesPlazo=" + diasRestantesPlazo);
        ret.append(", diasHabiles=" + diasHabiles);
        ret.append(", suspencionPlazo=" + suspencionPlazo);
        ret.append(", diasRestantesDocumentos=" + diasRestantesDocumentos);
        ret.append(", semaforo=" + semaforo);
        ret.append(", fechaIntegraExp=" + fechaIntegraExp);
        ret.append(", idContribuyente=" + idContribuyente);
        ret.append(", idEstatus=" + idEstatus);
        ret.append(", idAuditor=" + idAuditor);
        ret.append(", idFirmante=" + idFirmante);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", idRegistroPropuesta=" + idRegistroPropuesta);
        ret.append(", cargaDocumentosVigente=" + cargaDocumentosVigente);
        ret.append(", fechaReactivarPlazo=" + fechaReactivarPlazo);
        ret.append(", fechaSuspencionPlazo=" + fechaSuspencionPlazo);
        ret.append(", diasResolucionDefinitiva=" + diasResolucionDefinitiva);

        return ret.toString();
    }

    public void setBlnCompulsa(boolean blnCompulsa) {
        this.blnCompulsa = blnCompulsa;
    }

    public boolean isBlnCompulsa() {
        return blnCompulsa;
    }

    public void setFecetAsociado(FecetAsociado fecetAsociado) {
        this.fecetAsociado = fecetAsociado;
    }

    public FecetAsociado getFecetAsociado() {
        return fecetAsociado;
    }

    public void setIdNyV(Long idNyV) {
        this.idNyV = idNyV;
    }

    public Long getIdNyV() {
        return idNyV;
    }

    public void setFecetDetalleNyV(FecetDetalleNyV fecetDetalleNyV) {
        this.fecetDetalleNyV = fecetDetalleNyV;
    }

    public FecetDetalleNyV getFecetDetalleNyV() {
        return fecetDetalleNyV;
    }

    @Override
    public DescriptorNotificables getDescriptor() {
        return DescriptorNotificables.ORDEN;
    }

    @Override
    public BigDecimal getId() {
        return idOrden;
    }

    public DatosNotificable getDatosNotificable() {
        return datosNotificable;
    }

    public void setDatosNotificable(DatosNotificable datosNotificable) {
        this.datosNotificable = datosNotificable;
    }

    public String getDescripcionSemaforo() {
        return descripcionSemaforo;
    }

    public void setDescripcionSemaforo(String descripcionSemaforo) {
        this.descripcionSemaforo = descripcionSemaforo;
    }

    public String getPrioridadSugerida() {
        return prioridadSugerida;
    }

    public void setPrioridadSugerida(String prioridadSugerida) {
        this.prioridadSugerida = prioridadSugerida;
    }

    public AgrupadorEstatusOrdenesEnum getEstatusXGrupo() {
        return estatusXGrupo;
    }

    public void setEstatusXGrupo(AgrupadorEstatusOrdenesEnum estatusXGrupo) {
        this.estatusXGrupo = estatusXGrupo;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    public FececArace getFececArace() {
        return fececArace;
    }

    public void setFececArace(FececArace fececArace) {
        this.fececArace = fececArace;
    }

    public List<FecetOficio> getOficiosFirmados() {
        return oficiosFirmados;
    }

    public void setOficiosFirmados(List<FecetOficio> oficiosFirmados) {
        this.oficiosFirmados = oficiosFirmados;
    }

    public List<FecetDocOrden> getDocOrden() {
        return docOrden;
    }

    public void setDocOrden(List<FecetDocOrden> docOrden) {
        this.docOrden = docOrden;
    }

    public String getDescripcionCifras() {
        return descripcionCifras;
    }

    public void setDescripcionCifras(String descripcionCifras) {
        this.descripcionCifras = descripcionCifras;
    }

    public BigDecimal getTotalCifras() {
        return totalCifras;
    }

    public void setTotalCifras(BigDecimal totalCifras) {
        this.totalCifras = totalCifras;
    }

    public boolean isMostrarNumeroOrden() {
        return mostrarNumeroOrden;
    }

    public void setMostrarNumeroOrden(boolean mostrarNumeroOrden) {
        this.mostrarNumeroOrden = mostrarNumeroOrden;
    }

    /**
     * @return the numeroReferencia
     */
    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    /**
     * @param numeroReferencia the numeroReferencia to set
     */
    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
    
}
