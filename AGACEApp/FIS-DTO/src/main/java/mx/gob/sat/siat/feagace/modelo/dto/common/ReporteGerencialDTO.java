package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public class ReporteGerencialDTO {

    private BigDecimal idOrden;
    private String cveOrden;
    private String cveRegistro;
    private String metodo;
    private String rfcContribuyente;
    private String nombrecontribuyente;
    private String rfcRepresentanteLegal;
    private String nombreRepresentanteLegal;
    private String rfcApoderadoLegal;
    private String nombreApoderadoLegal;
    private String rfcAgenteAduanal;
    private String nombreAgenteAduanal;
    private String area;
    private String nombreAuditor;
    private BigDecimal idEstatus;
    private Date fechaRegistro;
    private Date fechaEnvioNotificacion;
    private Date fechaEnvio;
    private BigDecimal idFecetAmplPlazo;
    private BigDecimal idFecetCartaInv;
    private BigDecimal idFecetComplement;
    private BigDecimal idFecetRequerimiento;
    private BigDecimal idFecetObserv;
    private BigDecimal idFecetConclusion;
    private List<FecetAlegato> alegatos;

    public void setCveOrden(String cveOrden) {
        this.cveOrden = cveOrden;
    }

    public String getCveOrden() {
        return cveOrden;
    }

    public void setCveRegistro(String cveRegistro) {
        this.cveRegistro = cveRegistro;
    }

    public String getCveRegistro() {
        return cveRegistro;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setNombrecontribuyente(String nombrecontribuyente) {
        this.nombrecontribuyente = nombrecontribuyente;
    }

    public String getNombrecontribuyente() {
        return nombrecontribuyente;
    }

    public void setRfcRepresentanteLegal(String rfcRepresentanteLegal) {
        this.rfcRepresentanteLegal = rfcRepresentanteLegal;
    }

    public String getRfcRepresentanteLegal() {
        return rfcRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setRfcApoderadoLegal(String rfcApoderadoLegal) {
        this.rfcApoderadoLegal = rfcApoderadoLegal;
    }

    public String getRfcApoderadoLegal() {
        return rfcApoderadoLegal;
    }

    public void setNombreApoderadoLegal(String nombreApoderadoLegal) {
        this.nombreApoderadoLegal = nombreApoderadoLegal;
    }

    public String getNombreApoderadoLegal() {
        return nombreApoderadoLegal;
    }

    public void setRfcAgenteAduanal(String rfcAgenteAduanal) {
        this.rfcAgenteAduanal = rfcAgenteAduanal;
    }

    public String getRfcAgenteAduanal() {
        return rfcAgenteAduanal;
    }

    public void setNombreAgenteAduanal(String nombreAgenteAduanal) {
        this.nombreAgenteAduanal = nombreAgenteAduanal;
    }

    public String getNombreAgenteAduanal() {
        return nombreAgenteAduanal;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setNombreAuditor(String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    public String getNombreAuditor() {
        return nombreAuditor;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro != null ? new Date(fechaRegistro.getTime()) : null;
    }

    public Date getFechaRegistro() {
        return (fechaRegistro != null) ? (Date) fechaRegistro.clone() : null;
    }

    public void setFechaEnvioNotificacion(Date fechaEnvioNotificacion) {
        this.fechaEnvioNotificacion = fechaEnvioNotificacion != null ? new Date(fechaEnvioNotificacion.getTime()) : null;
    }

    public Date getFechaEnvioNotificacion() {
        return (fechaEnvioNotificacion != null) ? (Date) fechaEnvioNotificacion.clone() : null;
    }

    public void setIdFecetAmplPlazo(BigDecimal idFecetAmplPlazo) {
        this.idFecetAmplPlazo = idFecetAmplPlazo;
    }

    public BigDecimal getIdFecetAmplPlazo() {
        return idFecetAmplPlazo;
    }

    public void setIdFecetCartaInv(BigDecimal idFecetCartaInv) {
        this.idFecetCartaInv = idFecetCartaInv;
    }

    public BigDecimal getIdFecetCartaInv() {
        return idFecetCartaInv;
    }

    public void setIdFecetComplement(BigDecimal idFecetComplement) {
        this.idFecetComplement = idFecetComplement;
    }

    public BigDecimal getIdFecetComplement() {
        return idFecetComplement;
    }

    public void setIdFecetRequerimiento(BigDecimal idFecetRequerimiento) {
        this.idFecetRequerimiento = idFecetRequerimiento;
    }

    public BigDecimal getIdFecetRequerimiento() {
        return idFecetRequerimiento;
    }

    public void setIdFecetObserv(BigDecimal idFecetObserv) {
        this.idFecetObserv = idFecetObserv;
    }

    public BigDecimal getIdFecetObserv() {
        return idFecetObserv;
    }

    public void setIdFecetConclusion(BigDecimal idFecetConclusion) {
        this.idFecetConclusion = idFecetConclusion;
    }

    public BigDecimal getIdFecetConclusion() {
        return idFecetConclusion;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio != null ? new Date(fechaEnvio.getTime()) : null;
    }

    public Date getFechaEnvio() {
        return (fechaEnvio != null) ? (Date) fechaEnvio.clone() : null;
    }

    public void setAlegatos(List<FecetAlegato> alegatos) {
        this.alegatos = alegatos;
    }

    public List<FecetAlegato> getAlegatos() {
        return alegatos;
    }
}
