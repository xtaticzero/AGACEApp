package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables.DescriptorNotificables;

public class FecetPruebasPericiales extends BaseModel implements NotificableNyV {

    private static final long serialVersionUID = 1L;

    private BigDecimal idPruebasPericiales;
    private BigDecimal idOrden;
    private Date fechaCarga;
    private String rutaAcuse;
    private String cadenaContribuyente;
    private String firmaContribuyente;
    private Boolean aprobada;
    private BigDecimal idAsociadoCarga;
    private BigDecimal idAuditor;
    private BigDecimal idFirmante;
    private String rutaResolucion;
    private String cadenaFirmante;
    private String firmaFirmante;
    private Date fechaFirma;
    private BigDecimal idEstatus;
    private String folioNyv;
    private String rutaAcuseNyv;
    private Date fechaNotifNyv;
    private Date fechaNotifCont;
    private Date fechaSurteEfectos;
    private BigDecimal idNyv;
    private transient InputStream archivoAcuse;
    private transient InputStream archivoAcuseNyV;
    private transient InputStream archivoResolucion;
    private DatosNotificable datosNotificable;
    private FececEstatus fececEstatus;
    private FecetDetalleNyV fecetDetalleNyV;
    private String nombreAcuse;
    private String nombreResolucion;
    private FecetAsociado fecetAsociado;
    private FecetFlujoPruebasPericiales flujoPruebasPericiales;
    private Integer totalDocumentosContribuyente;
    private Integer totalDocumentosRechazo;
    private boolean mostrarDocRes;
    private String descripcionEstado;
    private AgaceOrden orden;
    private EmpleadoDTO firmante;
    private String numeroReferencia;

    public FecetPruebasPericiales() {
    }

    public void setIdPruebasPericiales(BigDecimal idPruebasPericiales) {
        this.idPruebasPericiales = idPruebasPericiales;
    }

    public BigDecimal getIdPruebasPericiales() {
        return idPruebasPericiales;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
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

    public void setIdAsociadoCarga(BigDecimal idAsociadoCarga) {
        this.idAsociadoCarga = idAsociadoCarga;
    }

    public BigDecimal getIdAsociadoCarga() {
        return idAsociadoCarga;
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

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma != null ? new Date(fechaFirma.getTime()) : null;
    }

    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setFolioNyv(String folioNyv) {
        this.folioNyv = folioNyv;
    }

    public String getFolioNyv() {
        return folioNyv;
    }

    public void setRutaAcuseNyv(String rutaAcuseNyv) {
        this.rutaAcuseNyv = rutaAcuseNyv;
    }

    public String getRutaAcuseNyv() {
        return rutaAcuseNyv;
    }

    public void setFechaNotifNyv(Date fechaNotifNyv) {
        this.fechaNotifNyv = fechaNotifNyv != null ? new Date(fechaNotifNyv.getTime()) : null;
    }

    public Date getFechaNotifNyv() {
        return (fechaNotifNyv != null) ? (Date) fechaNotifNyv.clone() : null;
    }

    public void setFechaNotifCont(Date fechaNotifCont) {
        this.fechaNotifCont = fechaNotifCont != null ? new Date(fechaNotifCont.getTime()) : null;
    }

    public Date getFechaNotifCont() {
        return (fechaNotifCont != null) ? (Date) fechaNotifCont.clone() : null;
    }

    public void setFechaSurteEfectos(Date fechaSurteEfectos) {
        this.fechaSurteEfectos = fechaSurteEfectos != null ? new Date(fechaSurteEfectos.getTime()) : null;
    }

    public Date getFechaSurteEfectos() {
        return (fechaSurteEfectos != null) ? (Date) fechaSurteEfectos.clone() : null;
    }

    public void setIdNyv(BigDecimal idNyv) {
        this.idNyv = idNyv;
    }

    public BigDecimal getIdNyv() {
        return idNyv;
    }

    public void setArchivoAcuse(InputStream archivoAcuse) {
        this.archivoAcuse = archivoAcuse;
    }

    public InputStream getArchivoAcuse() {
        return archivoAcuse;
    }

    public void setArchivoAcuseNyV(InputStream archivoAcuseNyV) {
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

    public DatosNotificable getDatosNotificable() {
        return datosNotificable;
    }

    public void setDatosNotificable(DatosNotificable datosNotificable) {
        this.datosNotificable = datosNotificable;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    @Override
    public DescriptorNotificables getDescriptor() {
        return DescriptorNotificables.PRUEBAS_PERICIALES;
    }

    @Override
    public BigDecimal getId() {
        return idPruebasPericiales;
    }

    public FecetDetalleNyV getFecetDetalleNyV() {
        return fecetDetalleNyV;
    }

    public void setFecetDetalleNyV(FecetDetalleNyV fecetDetalleNyV) {
        this.fecetDetalleNyV = fecetDetalleNyV;
    }

    public FecetPruebasPericialesPk createPk() {
        return new FecetPruebasPericialesPk(idPruebasPericiales);
    }

    public void setNombreAcuse(String nombreAcuse) {
        this.nombreAcuse = nombreAcuse;
    }

    public String getNombreAcuse() {
        return nombreAcuse;
    }

    public void setNombreResolucion(String nombreResolucion) {
        this.nombreResolucion = nombreResolucion;
    }

    public String getNombreResolucion() {
        return nombreResolucion;
    }

    public void setFecetAsociado(FecetAsociado fecetAsociado) {
        this.fecetAsociado = fecetAsociado;
    }

    public FecetAsociado getFecetAsociado() {
        return fecetAsociado;
    }

    public void setFlujoPruebasPericiales(FecetFlujoPruebasPericiales flujoPruebasPericiales) {
        this.flujoPruebasPericiales = flujoPruebasPericiales;
    }

    public FecetFlujoPruebasPericiales getFlujoPruebasPericiales() {
        return flujoPruebasPericiales;
    }

    public void setTotalDocumentosContribuyente(Integer totalDocumentosContribuyente) {
        this.totalDocumentosContribuyente = totalDocumentosContribuyente;
    }

    public Integer getTotalDocumentosContribuyente() {
        return totalDocumentosContribuyente;
    }

    public void setTotalDocumentosRechazo(Integer totalDocumentosRechazo) {
        this.totalDocumentosRechazo = totalDocumentosRechazo;
    }

    public Integer getTotalDocumentosRechazo() {
        return totalDocumentosRechazo;
    }

    public void setMostrarDocRes(boolean mostrarDocRes) {
        this.mostrarDocRes = mostrarDocRes;
    }

    public boolean isMostrarDocRes() {
        return mostrarDocRes;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setOrden(AgaceOrden orden) {
        this.orden = orden;
    }

    public AgaceOrden getOrden() {
        return orden;
    }

    public EmpleadoDTO getFirmante() {
        return firmante;
    }

    public void setFirmante(EmpleadoDTO firmante) {
        this.firmante = firmante;
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
