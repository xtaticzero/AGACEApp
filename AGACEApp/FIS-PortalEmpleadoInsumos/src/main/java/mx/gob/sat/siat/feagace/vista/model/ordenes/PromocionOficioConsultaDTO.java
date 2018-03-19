package mx.gob.sat.siat.feagace.vista.model.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;


public class PromocionOficioConsultaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6091830945963331502L;
    private BigDecimal idPromocionOficio;
    private String tipoPromocionStr;
    private Date fechaEnvio;
    private String nombreArchivo;
    private String rutaArchivo;
    private Long documentosContribuyente;
    private String nombreAcuse;
    private String rutaAcuse;
    private boolean extemporaneo;
    private String presentadoPor;

    private List<FecetAlegatoOficio> listFecetAlegatoOficio;

    public BigDecimal getIdPromocionOficio() {
        return idPromocionOficio;
    }

    public void setIdPromocionOficio(BigDecimal idPromocionOficio) {
        this.idPromocionOficio = idPromocionOficio;
    }

    public Date getFechaEnvio() {
        return (fechaEnvio != null) ? (Date)fechaEnvio.clone() : null;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio != null ? new Date(fechaEnvio.getTime()) : null;
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

    public Long getDocumentosContribuyente() {
        return documentosContribuyente;
    }

    public void setDocumentosContribuyente(Long documentosContribuyente) {
        this.documentosContribuyente = documentosContribuyente;
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

    public String getTipoPromocionStr() {
        return tipoPromocionStr;
    }

    public void setTipoPromocionStr(String tipoPromocionStr) {
        this.tipoPromocionStr = tipoPromocionStr;
    }

    public List<FecetAlegatoOficio> getListFecetAlegatoOficio() {
        return listFecetAlegatoOficio;
    }

    public void setListFecetAlegatoOficio(List<FecetAlegatoOficio> listFecetAlegatoOficio) {
        this.listFecetAlegatoOficio = listFecetAlegatoOficio;
    }


}
