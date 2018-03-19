package mx.gob.sat.siat.feagace.vista.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;

public class PromocionConsultaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4005186736229347098L;
    private BigDecimal idPromocion;
    private String tipoPromocionStr;
    private Date fechaEnvio;

    private String nombreArchivo;
    private String rutaArchivo;
    private Long documentosContribuyente;
    private String nombreAcuse;
    private String rutaAcuse;
    private boolean extemporaneo;
    private String presentadoPor;

    private List<FecetAlegato> listFecetAlegato;

    public BigDecimal getIdPromocion() {
        return idPromocion;
    }

    public String getNombreAcuse() {
        return nombreAcuse;
    }

    public void setNombreAcuse(String nombreAcuse) {
        this.nombreAcuse = nombreAcuse;
    }

    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setRutaAcuse(String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
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

    public void setIdPromocion(BigDecimal idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTipoPromocionStr() {
        return tipoPromocionStr;
    }

    public void setTipoPromocionStr(String tipoPromocionStr) {
        this.tipoPromocionStr = tipoPromocionStr;
    }

    public Date getFechaEnvio() {
        return (fechaEnvio != null) ? (Date)fechaEnvio.clone() : null;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio != null ? new Date(fechaEnvio.getTime()) : null;
    }

    public Long getDocumentosContribuyente() {
        return documentosContribuyente;
    }

    public void setDocumentosContribuyente(Long documentosContribuyente) {
        this.documentosContribuyente = documentosContribuyente;
    }

    public boolean isExtemporaneo() {
        return extemporaneo;
    }

    public void setExtemporaneo(boolean extemporaneo) {
        this.extemporaneo = extemporaneo;
    }

    public String getPresentadoPor() {
        return presentadoPor;
    }

    public void setPresentadoPor(String presentadoPor) {
        this.presentadoPor = presentadoPor;
    }

    public List<FecetAlegato> getListFecetAlegato() {
        return listFecetAlegato;
    }

    public void setListFecetAlegato(List<FecetAlegato> listFecetAlegato) {
        this.listFecetAlegato = listFecetAlegato;
    }

}
