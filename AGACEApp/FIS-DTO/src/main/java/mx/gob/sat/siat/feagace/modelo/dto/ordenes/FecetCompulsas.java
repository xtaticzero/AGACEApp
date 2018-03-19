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

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;


public class FecetCompulsas extends BaseModel {

    @SuppressWarnings("compatibility:2372907905712600623")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_COMPULSA in the FECET_COMPULSAS table.
     */
    private BigDecimal idCompulsa;

    /**
     * This attribute maps to the column ID_ORDEN_AUDITADA in the FECET_COMPULSAS table.
     */
    private BigDecimal idOrdenAuditada;

    /**
     * This attribute maps to the column ID_ORDEN_COMPULSA in the FECET_COMPULSAS table.
     */
    private BigDecimal idOrdenCompulsa;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE_COMPULSADO in the FECET_COMPULSAS table.
     */
    private BigDecimal idContribuyenteCompulsado;

    /**
     * This attribute maps to the column ID_ASOCIADO in the FECET_COMPULSAS table.
     */
    private BigDecimal idAsociado;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_COMPULSAS table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column ID_OFICIO in the FECET_COMPULSAS table.
     */
    private BigDecimal idOficio;

    /**
     * This attribute maps to the column TIPO_COMPULSA in the FECET_COMPULSAS table.
     */
    private String tipoCompulsa;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_COMPULSAS table.
     */
    private Date fechaCreacion;

    private transient InputStream archivo;

    private AgaceOrden orden;

    private FecetOficio oficio;

    private FececMetodo metodo;
    
    private String nombreAsociado;
    
    private String rfcAsociado;
    
    private String correoAsociado;
    
    private String nombreTerceroCompulsado;
    
    private String rfcTerceroCompulsado;
    
    private boolean mostrarNumeroOrden;

    /**
     * Method 'FecetCompulsas'
     *
     */
    public FecetCompulsas() {
    }

    /**
     * Method 'getIdCompulsa'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCompulsa() {
        return idCompulsa;
    }

    /**
     * Method 'setIdCompulsa'
     *
     * @param idCompulsa
     */
    public void setIdCompulsa(BigDecimal idCompulsa) {
        this.idCompulsa = idCompulsa;
    }

    /**
     * Method 'getIdOrdenAuditada'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOrdenAuditada() {
        return idOrdenAuditada;
    }

    /**
     * Method 'setIdOrdenAuditada'
     *
     * @param idOrdenAuditada
     */
    public void setIdOrdenAuditada(BigDecimal idOrdenAuditada) {
        this.idOrdenAuditada = idOrdenAuditada;
    }

    /**
     * Method 'getIdOrdenCompulsa'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOrdenCompulsa() {
        return idOrdenCompulsa;
    }

    /**
     * Method 'setIdOrdenCompulsa'
     *
     * @param idOrdenCompulsa
     */
    public void setIdOrdenCompulsa(BigDecimal idOrdenCompulsa) {
        this.idOrdenCompulsa = idOrdenCompulsa;
    }

    /**
     * Method 'getIdContribuyenteCompulsado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdContribuyenteCompulsado() {
        return idContribuyenteCompulsado;
    }

    /**
     * Method 'setIdContribuyenteCompulsado'
     *
     * @param idContribuyenteCompulsado
     */
    public void setIdContribuyenteCompulsado(BigDecimal idContribuyenteCompulsado) {
        this.idContribuyenteCompulsado = idContribuyenteCompulsado;
    }

    /**
     * Method 'getIdAsociado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAsociado() {
        return idAsociado;
    }

    /**
     * Method 'setIdAsociado'
     *
     * @param idAsociado
     */
    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
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
     * Method 'getTipoCompulsa'
     *
     * @return String
     */
    public String getTipoCompulsa() {
        return tipoCompulsa;
    }

    /**
     * Method 'setTipoCompulsa'
     *
     * @param tipoCompulsa
     */
    public void setTipoCompulsa(String tipoCompulsa) {
        this.tipoCompulsa = tipoCompulsa;
    }

    /**
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
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
     * Method 'createPk'
     *
     * @return FecetCompulsasPk
     */
    public FecetCompulsasPk createPk() {
        return new FecetCompulsasPk(idCompulsa);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dao.ordenes.dto.FecetCompulsas: ");
        ret.append("idCompulsa=").append(idCompulsa);
        ret.append(", idOrdenAuditada=").append(idOrdenAuditada);
        ret.append(", idOrdenCompulsa=").append(idOrdenCompulsa);
        ret.append(", idContribuyenteCompulsado=").append(idContribuyenteCompulsado);
        ret.append(", idAsociado=").append(idAsociado);
        ret.append(", idEstatus=").append(idEstatus);
        ret.append(", idOficio=").append(idOficio);
        ret.append(", tipoCompulsa=").append(tipoCompulsa);
        ret.append(", fechaCreacion=").append(fechaCreacion);
        return ret.toString();
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setOrden(AgaceOrden orden) {
        this.orden = orden;
    }

    public AgaceOrden getOrden() {
        return orden;
    }

    public void setOficio(FecetOficio oficio) {
        this.oficio = oficio;
    }

    public FecetOficio getOficio() {
        return oficio;
    }


    public void setMetodo(FececMetodo metodo) {
        this.metodo = metodo;
    }

    public FececMetodo getMetodo() {
        return metodo;
    }

    public String getNombreAsociado() {
        return nombreAsociado;
    }

    public void setNombreAsociado(String nombreAsociado) {
        this.nombreAsociado = nombreAsociado;
    }

    public String getRfcAsociado() {
        return rfcAsociado;
    }

    public void setRfcAsociado(String rfcAsociado) {
        this.rfcAsociado = rfcAsociado;
    }

    public String getCorreoAsociado() {
        return correoAsociado;
    }

    public void setCorreoAsociado(String correoAsociado) {
        this.correoAsociado = correoAsociado;
    }

    public String getNombreTerceroCompulsado() {
        return nombreTerceroCompulsado;
    }

    public void setNombreTerceroCompulsado(String nombreTerceroCompulsado) {
        this.nombreTerceroCompulsado = nombreTerceroCompulsado;
    }

    public String getRfcTerceroCompulsado() {
        return rfcTerceroCompulsado;
    }

    public void setRfcTerceroCompulsado(String rfcTerceroCompulsado) {
        this.rfcTerceroCompulsado = rfcTerceroCompulsado;
    }

    public boolean isMostrarNumeroOrden() {
        return mostrarNumeroOrden;
    }

    public void setMostrarNumeroOrden(boolean mostrarNumeroOrden) {
        this.mostrarNumeroOrden = mostrarNumeroOrden;
    }
}
