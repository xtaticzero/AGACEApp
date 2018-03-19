package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class OrdenNotificacion extends BaseModel{
    public OrdenNotificacion() {
        super();
    }
    
    /**
     * This attribute maps to the column folioCarga
     */
    private BigDecimal folioCarga;


    /**
     * This attribute maps to the column idMetodo
     */
    private BigDecimal idMetodo;
    
    /**
     * This attribute maps to the column descripcionMetodo
     */
    private String descripcionMetodo;
    
    /**
     * This attribute maps to the column idOrden
     */
    private BigDecimal idOrden;
    
    /**
     * This attribute maps to the column numeroOrden
     */
    private String numeroOrden;
    
    /**
     * This attribute maps to the column idContribuyente
     */
    private BigDecimal idContribuyente;
    
    /**
     * This attribute maps to the column nombreContribuyente
     */
    private String nombreContribuyente;
    
    /**
     * This attribute maps to the column rfcContribuyente
     */
    private String rfcContribuyente;
    
    /**
     * This attribute maps to the column Folio Oficio
     */
    private String folioOficio;


    public void setFolioCarga(BigDecimal folioCarga) {
        this.folioCarga = folioCarga;
    }

    public BigDecimal getFolioCarga() {
        return folioCarga;
    }

    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setDescripcionMetodo(String descripcionMetodo) {
        this.descripcionMetodo = descripcionMetodo;
    }

    public String getDescripcionMetodo() {
        return descripcionMetodo;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setFolioOficio(String folioOficio) {
        this.folioOficio = folioOficio;
    }

    public String getFolioOficio() {
        return folioOficio;
    }
}
