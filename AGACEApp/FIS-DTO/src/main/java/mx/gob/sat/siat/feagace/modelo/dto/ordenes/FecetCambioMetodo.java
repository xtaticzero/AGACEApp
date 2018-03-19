package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetCambioMetodo extends BaseModel {
    @SuppressWarnings("compatibility:3977039885276145327")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_CAMBIO_METODO in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idCambioMetodo;

    /**
     * This attribute maps to the column ID_ORDEN_ORIGEN in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idOrdenOrigen;

    /**
     * This attribute maps to the column ID_ORDEN_NUEVA in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idOrdenNueva;

    /**
     * This attribute maps to the column ID_METODO_NUEVO in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idMetodoNuevo;

    /**
     * This attribute maps to the column ID_OFICIO in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idOficio;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_CAMBIO_METODO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column ID_PROPUESTA_NUEVA in the FECET_CAMBIO_METODO table.
     */
    private BigDecimal idPropuestaNueva;
    
    
    private String nombreNuevoMetodo;

    /**
     * Method 'getIdCambioMetodo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCambioMetodo() {
        return idCambioMetodo;
    }

    /**
     * Method 'setIdCambioMetodo'
     *
     * @param idCambioMetodo
     */
    public void setIdCambioMetodo(final BigDecimal idCambioMetodo) {
        this.idCambioMetodo = idCambioMetodo;
    }

    /**
     * Method 'getIdOrdenOrigen'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOrdenOrigen() {
        return idOrdenOrigen;
    }

    /**
     * Method 'setIdOrdenOrigen'
     *
     * @param idOrdenOrigen
     */
    public void setIdOrdenOrigen(final BigDecimal idOrdenOrigen) {
        this.idOrdenOrigen = idOrdenOrigen;
    }

    /**
     * Method 'getIdOrdenNueva'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOrdenNueva() {
        return idOrdenNueva;
    }

    /**
     * Method 'setIdOrdenNueva'
     *
     * @param idOrdenNueva
     */
    public void setIdOrdenNueva(final BigDecimal idOrdenNueva) {
        this.idOrdenNueva = idOrdenNueva;
    }

    /**
     * Method 'getIdMetodoNuevo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdMetodoNuevo() {
        return idMetodoNuevo;
    }

    /**
     * Method 'setIdMetodoNuevo'
     *
     * @param idMetodoNuevo
     */
    public void setIdMetodoNuevo(final BigDecimal idMetodoNuevo) {
        this.idMetodoNuevo = idMetodoNuevo;
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
    public void setIdOficio(final BigDecimal idOficio) {
        this.idOficio = idOficio;
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
    public void setIdEstatus(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
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

    public void setIdPropuestaNueva(BigDecimal idPropuestaNueva) {
        this.idPropuestaNueva = idPropuestaNueva;
    }

    public BigDecimal getIdPropuestaNueva() {
        return idPropuestaNueva;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetCambioMetodoPk
     */
    public FecetCambioMetodoPk createPk() {
        return new FecetCambioMetodoPk(idCambioMetodo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dao.ordenes.dto.FecetCambioMetodo: ");
        ret.append("idCambioMetodo=").append(idCambioMetodo);
        ret.append(", idOrdenOrigen=").append(idOrdenOrigen);
        ret.append(", idOrdenNueva=").append(idOrdenNueva);
        ret.append(", idMetodoNuevo=").append(idMetodoNuevo);
        ret.append(", idOficio=").append(idOficio);
        ret.append(", idEstatus=").append(idEstatus);
        ret.append(", fechaCreacion=").append(fechaCreacion);
        ret.append(", idPropuestaNueva=").append(idPropuestaNueva);
        return ret.toString();
    }

    public String getNombreNuevoMetodo() {
        return nombreNuevoMetodo;
    }

    public void setNombreNuevoMetodo(String nombreNuevoMetodo) {
        this.nombreNuevoMetodo = nombreNuevoMetodo;
    }
}
