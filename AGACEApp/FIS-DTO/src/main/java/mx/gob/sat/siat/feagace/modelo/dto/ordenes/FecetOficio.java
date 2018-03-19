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
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

public class FecetOficio extends BaseModel implements NotificableNyV {

    private static final long serialVersionUID = 165465456543643654L;

    private BigDecimal idOficio;
    private BigDecimal idOficioPrincipal;
    private Date fechaCreacion;
    private Date fechaFirma;
    private String nombreArchivo;
    private String rutaArchivo;
    private String documentoPdf;
    private String folioNyv;
    private String nombreAcuseNyv;
    private String rutaAcuseNyv;
    private Date fechaNotifNyv;
    private Date fechaNotifCont;
    private Date fechaSurteEfectos;
    private BigDecimal diasRestantesPlazo;
    private String diasHabiles;
    private String suspencionPlazo;
    private BigDecimal diasRestantesDocumentos;
    private Date fechaIntegraExp;
    private BigDecimal idEstatus;
    private BigDecimal idOrden;
    private String cadenaOriginal;
    private String firmaElectronica;
    private Long idNyV;
    private String descripcionPlazoRestante;
    private FececEstatus fececEstatus;

    private FecetTipoOficio fecetTipoOficio;
    private FecetRechazoOficio fecetRechazoOficio;
    private Integer totalAnexosOficio;
    private Integer totalOficiosDependientes;
    private Integer totalAnexosRechazo;

    private boolean mostrarIdOficio;

    private BigDecimal idAdminOficio;
    private boolean blnActivo;
    private String numeroOficio;
    private BigDecimal idEmpleado;
    private String descripcionAccion;
    private String descripcionEmpleado;
    private Date fechaHora;
    private FecetAdminOficio adminOficio;

    private String nombreCompulsado;
    private String rfcCompulsado;
    private String numeroReferencia;

    /**
     * Archivo que representa el Oficio en cuestion
     */
    private transient InputStream archivo;

    /**
     * Archivo que representa los anexos del Oficio en cuestion
     */
    private transient List<InputStream> listaAnexos;

    private FecetDetalleNyV fecetDetalleNyV;

    private DatosNotificable datosNotificable;

    private BigDecimal idOficioTemp;

    public BigDecimal getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(BigDecimal idOficio) {
        this.idOficio = idOficio;
    }

    public BigDecimal getIdOficioPrincipal() {
        return idOficioPrincipal;
    }

    public void setIdOficioPrincipal(BigDecimal idOficioPrincipal) {
        this.idOficioPrincipal = idOficioPrincipal;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
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

    public String getFolioNyv() {
        return folioNyv;
    }

    public void setFolioNyv(String folioNyv) {
        this.folioNyv = folioNyv;
    }

    public String getNombreAcuseNyv() {
        return nombreAcuseNyv;
    }

    public void setNombreAcuseNyv(String nombreAcuseNyv) {
        this.nombreAcuseNyv = nombreAcuseNyv;
    }

    public String getRutaAcuseNyv() {
        return rutaAcuseNyv;
    }

    public void setRutaAcuseNyv(String rutaAcuseNyv) {
        this.rutaAcuseNyv = rutaAcuseNyv;
    }

    public Date getFechaNotifNyv() {
        return (fechaNotifNyv != null) ? (Date) fechaNotifNyv.clone() : null;
    }

    public void setFechaNotifNyv(Date fechaNotifNyv) {
        this.fechaNotifNyv = fechaNotifNyv != null ? new Date(fechaNotifNyv.getTime()) : null;
    }

    public Date getFechaNotifCont() {
        return (fechaNotifCont != null) ? (Date) fechaNotifCont.clone() : null;
    }

    public void setFechaNotifCont(Date fechaNotifCont) {
        this.fechaNotifCont = fechaNotifCont != null ? new Date(fechaNotifCont.getTime()) : null;
    }

    public Date getFechaSurteEfectos() {
        return (fechaSurteEfectos != null) ? (Date) fechaSurteEfectos.clone() : null;
    }

    public void setFechaSurteEfectos(Date fechaSurteEfectos) {
        this.fechaSurteEfectos = fechaSurteEfectos != null ? new Date(fechaSurteEfectos.getTime()) : null;
    }

    public BigDecimal getDiasRestantesPlazo() {
        return diasRestantesPlazo;
    }

    public void setDiasRestantesPlazo(BigDecimal diasRestantesPlazo) {
        this.diasRestantesPlazo = diasRestantesPlazo;
    }

    public String getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(String diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public String getSuspencionPlazo() {
        return suspencionPlazo;
    }

    public void setSuspencionPlazo(String suspencionPlazo) {
        this.suspencionPlazo = suspencionPlazo;
    }

    public BigDecimal getDiasRestantesDocumentos() {
        return diasRestantesDocumentos;
    }

    public void setDiasRestantesDocumentos(BigDecimal diasRestantesDocumentos) {
        this.diasRestantesDocumentos = diasRestantesDocumentos;
    }

    @Override
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setFececEstatus(final FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    public void setFirmaElectronica(String firmaElectronica) {
        this.firmaElectronica = firmaElectronica;
    }

    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    public void setFechaFirma(final Date fechaFirma) {
        this.fechaFirma = fechaFirma != null ? new Date(fechaFirma.getTime()) : null;
    }

    public Date getFechaIntegraExp() {
        return (fechaIntegraExp != null) ? (Date) fechaIntegraExp.clone() : null;
    }

    public void setFechaIntegraExp(final Date fechaIntegraExp) {
        this.fechaIntegraExp = fechaIntegraExp != null ? new Date(fechaIntegraExp.getTime()) : null;
    }

    public void setFecetTipoOficio(final FecetTipoOficio fecetTipoOficio) {
        this.fecetTipoOficio = fecetTipoOficio;
    }

    public FecetTipoOficio getFecetTipoOficio() {
        return fecetTipoOficio;
    }

    public void setTotalAnexosOficio(final Integer totalAnexosOficio) {
        this.totalAnexosOficio = totalAnexosOficio;
    }

    public Integer getTotalAnexosOficio() {
        return totalAnexosOficio;
    }

    public void setTotalOficiosDependientes(final Integer totalOficiosDependientes) {
        this.totalOficiosDependientes = totalOficiosDependientes;
    }

    public Integer getTotalOficiosDependientes() {
        return totalOficiosDependientes;
    }

    public void setFecetRechazoOficio(final FecetRechazoOficio fecetRechazoOficio) {
        this.fecetRechazoOficio = fecetRechazoOficio;
    }

    public FecetRechazoOficio getFecetRechazoOficio() {
        return fecetRechazoOficio;
    }

    public void setTotalAnexosRechazo(final Integer totalAnexosRechazo) {
        this.totalAnexosRechazo = totalAnexosRechazo;
    }

    public Integer getTotalAnexosRechazo() {
        return totalAnexosRechazo;
    }

    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setListaAnexos(List<InputStream> listaAnexos) {
        this.listaAnexos = listaAnexos;
    }

    public List<InputStream> getListaAnexos() {
        return listaAnexos;
    }

    public void setMostrarIdOficio(boolean mostrarIdOficio) {
        this.mostrarIdOficio = mostrarIdOficio;
    }

    public boolean isMostrarIdOficio() {
        return mostrarIdOficio;
    }

    public void setIdNyV(Long idNyV) {
        this.idNyV = idNyV;
    }

    public Long getIdNyV() {
        return idNyV;
    }

    public FecetDetalleNyV getFecetDetalleNyV() {
        return fecetDetalleNyV;
    }

    public void setFecetDetalleNyV(FecetDetalleNyV fecetDetalleNyV) {
        this.fecetDetalleNyV = fecetDetalleNyV;
    }

    @Override
    public DescriptorNotificables getDescriptor() {
        return DescriptorNotificables.OFICIO;
    }

    @Override
    public BigDecimal getId() {
        return idOficio;
    }

    public String getDescripcionPlazoRestante() {
        return descripcionPlazoRestante;
    }

    public void setDescripcionPlazoRestante(String descripcionPlazoRestante) {
        this.descripcionPlazoRestante = descripcionPlazoRestante;
    }

    public DatosNotificable getDatosNotificable() {
        return datosNotificable;
    }

    public void setDatosNotificable(DatosNotificable datosNotificable) {
        this.datosNotificable = datosNotificable;
    }

    public void setIdAdminOficio(BigDecimal idAdminOficio) {
        this.idAdminOficio = idAdminOficio;
    }

    public BigDecimal getIdAdminOficio() {
        return idAdminOficio;
    }

    public void setBlnActivo(boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public boolean isBlnActivo() {
        return blnActivo;
    }

    public void setAdminOficio(FecetAdminOficio adminOficio) {
        this.adminOficio = adminOficio;
    }

    public FecetAdminOficio getAdminOficio() {
        return adminOficio;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
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

    public void setIdOficioTemp(BigDecimal idOficioTemp) {
        this.idOficioTemp = idOficioTemp;
    }

    public BigDecimal getIdOficioTemp() {
        return idOficioTemp;
    }

    public String getNombreCompulsado() {
        return nombreCompulsado;
    }

    public void setNombreCompulsado(String nombreCompulsado) {
        this.nombreCompulsado = nombreCompulsado;
    }

    public String getRfcCompulsado() {
        return rfcCompulsado;
    }

    public void setRfcCompulsado(String rfcCompulsado) {
        this.rfcCompulsado = rfcCompulsado;
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
