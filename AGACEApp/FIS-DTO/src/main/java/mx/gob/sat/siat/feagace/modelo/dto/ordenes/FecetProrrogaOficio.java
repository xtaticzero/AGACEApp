package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

public class FecetProrrogaOficio extends BaseModel implements NotificableNyV {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_PRORROGA_OFICIO in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idProrrogaOficio;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idOficio;

    /**
     * This attribute maps to the column FECHA_CARGA in the FECET_PRORROGA_OFICIO table.
     */
    private Date fechaCarga;

    /**
     * This attribute maps to the column NOMBRE_ACUSE in the FECET_PRORROGA_OFICIO table.
     */
    private String nombreAcuse;

    /**
     * This attribute maps to the column RUTA_ACUSE in the FECET_PRORROGA_OFICIO table.
     */
    private String rutaAcuse;

    /**
     * This attribute maps to the column CADENA_CONTRIBUYENTE in the FECET_PRORROGA_OFICIO table.
     */
    private String cadenaContribuyente;

    /**
     * This attribute maps to the column FIRMA_CONTRIBUYENTE in the FECET_PRORROGA_OFICIO table.
     */
    private String firmaContribuyente;

    /**
     * This attribute maps to the column APROBADA in the FECET_PRORROGA_OFICIO table.
     */
    private Boolean aprobada;

    /**
     * This attribute maps to the column ID_ASOCIADO_CARGA in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idAsociadoCarga;

    /**
     * This attribute maps to the column ID_AUDITOR in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idAuditor;

    /**
     * This attribute maps to the column ID_FIRMANTE in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idFirmante;

    /**
     * This attribute maps to the column NOMBRE_RESOLUCION in the FECET_PRORROGA_OFICIO table.
     */
    private String nombreResolucion;

    /**
     * This attribute maps to the column RUTA_RESOLUCION in the FECET_PRORROGA_OFICIO table.
     */
    private String rutaResolucion;

    /**
     * This attribute maps to the column CADENA_FIRMANTE in the FECET_PRORROGA_OFICIO table.
     */
    private String cadenaFirmante;

    /**
     * This attribute maps to the column FIRMA_FIRMANTE in the FECET_PRORROGA_OFICIO table.
     */
    private String firmaFirmante;

    /**
     * This attribute maps to the column FECHA_FIRMA in the FECET_PRORROGA_OFICIO table.
     */
    private Date fechaFirma;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_PRORROGA_OFICIO table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column FOLIO_NYV in the FECET_PRORROGA_OFICIO table.
     */
    private String folioNyV;

    /**
     * This attribute maps to the column FECHA_NOTIF_NYV in the FECET_PRORROGA_OFICIO table.
     */
    private Date fechaNotifNyV;

    /**
     * This attribute maps to the column FECHA_NOTIF_CONT in the FECET_PRORROGA_OFICIO table.
     */
    private Date fechaNotifCont;

    /**
     * This attribute maps to the column FECHA_SURTE_EFECTOS in the FECET_PRORROGA_OFICIO table.
     */
    private Date fechaSuerteEfectos;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivoAcuse;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivoAcuseNyV;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivoResolucion;

    /**
     * Este objeto incluira las propiedades de el contribuyente al que le pertenece esta prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private FececEstatus fececEstatus;

    /**
     * Este objeto incluira las propiedades de el contribuyente al que le pertenece esta prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private FecetAsociado fecetAsociado;

    /**
     * Este objeto incluira las propiedades de el contribuyente al que le pertenece esta prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio;

    /**
     * Este objeto incluira las propiedades del Empledo Auditor al que le pertenece esta prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private FececEmpleado auditor;

    /**
     * Este objeto incluira las propiedades del Empledo Auditor al que le pertenece esta prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private EmpleadoDTO firmante;

    /**
     * Este objeto incluira el numero total de documentos que cargo el contribuyente para solicitar la prorroga CAMPO NO MAPEADO A BASE DE
     * DATOS
     */
    private Integer totalDocumentosContribuyente;

    /**
     * Este objeto incluira la descripcion del estatus de la prorroga CAMPO NO MAPEADO A BASE DE DATOS
     */
    private String descripcionEstado;

    /**
     * Este objeto incluira el numero total de documentos que cargo el contribuyente para solicitar la prorroga CAMPO NO MAPEADO A BASE DE
     * DATOS
     */
    private Integer totalDocumentosRechazo;

    private Long idNyV;

    private FecetDetalleNyV fecetDetalleNyV;

    private List<DocumentoVO> documentosProrroga;

    private AgaceOrden orden;

    private boolean mostrarDocRes;

    private DatosNotificable datosNotificable;
    
    private FecetOficio oficio;
    
    private String numeroReferencia;

    /**
     * Method 'getIdProrrogaOficio'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdProrrogaOficio() {
        return idProrrogaOficio;
    }

    /**
     * Method 'setIdProrrogaOficio'
     * 
     * @param idProrrogaOficio
     */
    public void setIdProrrogaOficio(final BigDecimal idProrrogaOficio) {
        this.idProrrogaOficio = idProrrogaOficio;
    }

    /**
     * Method 'getIdOficio'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdOficio() {
        return idOficio;
    }

    /**
     * Method 'setIdOficio'
     * 
     * @param idOficio
     */
    public void setIdOficio(BigDecimal idOficio) {
        this.idOficio = idOficio;
    }

    /**
     * Method 'getFechaCarga'
     * 
     * @return Date
     */
    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }

    /**
     * Method 'setFechaCarga'
     * 
     * @param fechaCarga
     */
    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;

    }

    /**
     * Method 'getFechaFirma'
     *
     * @return Date
     */
    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    /**
     * Method 'setFechaFirma'
     *
     * @param fechaFirma
     */
    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma != null ? new Date(fechaFirma.getTime()) : null;
    }

    public void setNombreAcuse(String nombreAcuse) {
        this.nombreAcuse = nombreAcuse;
    }

    public String getNombreAcuse() {
        return nombreAcuse;
    }

    public void setRutaAcuse(String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
    }

    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setCadenaContribuyente(String cadenaContribuyente) {
        this.cadenaContribuyente = cadenaContribuyente;
    }

    public String getCadenaContribuyente() {
        return cadenaContribuyente;
    }

    public void setFirmaContribuyente(String firmaContribuyente) {
        this.firmaContribuyente = firmaContribuyente;
    }

    public String getFirmaContribuyente() {
        return firmaContribuyente;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public Boolean getAprobada() {
        return aprobada;
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

    public void setNombreResolucion(String nombreResolucion) {
        this.nombreResolucion = nombreResolucion;
    }

    public String getNombreResolucion() {
        return nombreResolucion;
    }

    public void setRutaResolucion(String rutaResolucion) {
        this.rutaResolucion = rutaResolucion;
    }

    public String getRutaResolucion() {
        return rutaResolucion;
    }

    public void setCadenaFirmante(String cadenaFirmante) {
        this.cadenaFirmante = cadenaFirmante;
    }

    public String getCadenaFirmante() {
        return cadenaFirmante;
    }

    public void setFirmaFirmante(String firmaFirmante) {
        this.firmaFirmante = firmaFirmante;
    }

    public String getFirmaFirmante() {
        return firmaFirmante;
    }

    public void setIdEstatus(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setArchivoAcuse(InputStream archivoAcuse) {
        this.archivoAcuse = archivoAcuse;
    }

    public InputStream getArchivoAcuse() {
        return archivoAcuse;
    }

    public void setArchivoAcuseNyV(final InputStream archivoAcuseNyV) {
        this.archivoAcuseNyV = archivoAcuseNyV;
    }

    public InputStream getArchivoAcuseNyV() {
        return archivoAcuseNyV;
    }

    public void setArchivoResolucion(InputStream archivoResolucion) {
        this.archivoResolucion = archivoResolucion;
    }

    public InputStream getArchivoResolucion() {
        return archivoResolucion;
    }

    public void setTotalDocumentosContribuyente(Integer totalDocumentosContribuyente) {
        this.totalDocumentosContribuyente = totalDocumentosContribuyente;
    }

    public Integer getTotalDocumentosContribuyente() {
        return totalDocumentosContribuyente;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setTotalDocumentosRechazo(Integer totalDocumentosRechazo) {
        this.totalDocumentosRechazo = totalDocumentosRechazo;
    }

    public Integer getTotalDocumentosRechazo() {
        return totalDocumentosRechazo;
    }

    public void setFolioNyV(final String folioNyV) {
        this.folioNyV = folioNyV;
    }

    public String getFolioNyV() {
        return folioNyV;
    }

    public void setFechaNotifNyV(final Date fechaNotifNyV) {
        this.fechaNotifNyV = fechaNotifNyV != null ? new Date(fechaNotifNyV.getTime()) : null;
    }

    public Date getFechaNotifNyV() {
        return (fechaNotifNyV != null) ? (Date) fechaNotifNyV.clone() : null;
    }

    public void setFechaNotifCont(final Date fechaNotifCont) {
        this.fechaNotifCont = fechaNotifCont != null ? new Date(fechaNotifCont.getTime()) : null;
    }

    public Date getFechaNotifCont() {
        return (fechaNotifCont != null) ? (Date) fechaNotifCont.clone() : null;
    }

    public void setFechaSuerteEfectos(final Date fechaSuerteEfectos) {
        this.fechaSuerteEfectos = fechaSuerteEfectos != null ? new Date(fechaSuerteEfectos.getTime()) : null;
    }

    public Date getFechaSuerteEfectos() {
        return (fechaSuerteEfectos != null) ? (Date) fechaSuerteEfectos.clone() : null;
    }

    public void setAuditor(final FececEmpleado auditor) {
        this.auditor = auditor;
    }

    public FececEmpleado getAuditor() {
        return auditor;
    }

    public void setIdAsociadoCarga(BigDecimal idAsociadoCarga) {
        this.idAsociadoCarga = idAsociadoCarga;
    }

    public EmpleadoDTO getFirmante() {
        return firmante;
    }

    public void setFirmante(EmpleadoDTO firmante) {
        this.firmante = firmante;
    }

    public BigDecimal getIdAsociadoCarga() {
        return idAsociadoCarga;
    }

    public void setFecetAsociado(FecetAsociado fecetAsociado) {
        this.fecetAsociado = fecetAsociado;
    }

    public FecetAsociado getFecetAsociado() {
        return fecetAsociado;
    }

    public void setFececEstatus(final FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setFecetFlujoProrrogaOficio(final FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio) {
        this.fecetFlujoProrrogaOficio = fecetFlujoProrrogaOficio;
    }

    public FecetFlujoProrrogaOficio getFecetFlujoProrrogaOficio() {
        return fecetFlujoProrrogaOficio;
    }

    public Long getIdNyV() {
        return idNyV;
    }

    public void setIdNyV(Long idNyV) {
        this.idNyV = idNyV;
    }

    public FecetDetalleNyV getFecetDetalleNyV() {
        return fecetDetalleNyV;
    }

    public void setFecetDetalleNyV(FecetDetalleNyV fecetDetalleNyV) {
        this.fecetDetalleNyV = fecetDetalleNyV;
    }

    @Override
    public DescriptorNotificables getDescriptor() {
        return DescriptorNotificables.PRORROGA_OFICIO;
    }

    @Override
    public BigDecimal getId() {
        return idProrrogaOficio;
    }

    public List<DocumentoVO> getDocumentosProrroga() {
        return documentosProrroga;
    }

    public void setDocumentosProrroga(List<DocumentoVO> documentosProrroga) {
        this.documentosProrroga = documentosProrroga;
    }

    public AgaceOrden getOrden() {
        return orden;
    }

    public void setOrden(AgaceOrden orden) {
        this.orden = orden;
    }

    public void setMostrarDocRes(boolean mostrarDocRes) {
        this.mostrarDocRes = mostrarDocRes;
    }

    public boolean isMostrarDocRes() {
        return mostrarDocRes;
    }

    public DatosNotificable getDatosNotificable() {
        return datosNotificable;
    }

    public void setDatosNotificable(DatosNotificable datosNotificable) {
        this.datosNotificable = datosNotificable;
    }

    public FecetOficio getOficio() {
        return oficio;
    }

    public void setOficio(FecetOficio oficio) {
        this.oficio = oficio;
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
