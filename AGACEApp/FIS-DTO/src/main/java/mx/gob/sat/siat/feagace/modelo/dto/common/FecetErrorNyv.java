/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class FecetErrorNyv implements Serializable {
    /**
     * This attribute maps to the column ID_ERROR_NYV in the FECET_ERROR_NYV table.
     */
    private BigDecimal idErrorNyv;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_ERROR_NYV table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column CODIGO_RESPUESTA in the FECET_ERROR_NYV table.
     */
    private String codigoRespuesta;

    /**
     * This attribute maps to the column MENSAJE_RESPUESTA in the FECET_ERROR_NYV table.
     */
    private String mensajeRespuesta;

    /**
     * This attribute maps to the column FECHA_RESPUESTA in the FECET_ERROR_NYV table.
     */
    private Date fechaRespuesta;

    /**
     * This attribute maps to the column OBSERVACIONES in the FECET_ERROR_NYV table.
     */
    private String observaciones;

    /**
     * Method 'FecetErrorNyv'
     *
     */
    public FecetErrorNyv() {
    }

    /**
     * Method 'getIdErrorNyv'
     *
     * @return long
     */
    public BigDecimal getIdErrorNyv() {
        return idErrorNyv;
    }

    /**
     * Method 'setIdErrorNyv'
     *
     * @param idErrorNyv
     */
    public void setIdErrorNyv(BigDecimal idErrorNyv) {
        this.idErrorNyv = idErrorNyv;
    }

    /**
     * Method 'getIdOrden'
     *
     * @return long
     */
    public BigDecimal getIdOrden() {
        return idOrden;
    }

    /**
     * Method 'setIdOrden'
     *
     * @param idOrden
     */
    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * Method 'getCodigoRespuesta'
     *
     * @return String
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Method 'setCodigoRespuesta'
     *
     * @param codigoRespuesta
     */
    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    /**
     * Method 'getMensajeRespuesta'
     *
     * @return String
     */
    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    /**
     * Method 'setMensajeRespuesta'
     *
     * @param mensajeRespuesta
     */
    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    /**
     * Method 'getFechaRespuesta'
     *
     * @return Date
     */
    public Date getFechaRespuesta() {
        return (fechaRespuesta != null) ? (Date)fechaRespuesta.clone() : null;
    }

    /**
     * Method 'setFechaRespuesta'
     *
     * @param fechaRespuesta
     */
    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta != null ? new Date(fechaRespuesta.getTime()) : null;
    }

    /**
     * Method 'getObservaciones'
     *
     * @return String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Method 'setObservaciones'
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetErrorNyvPk
     */
    public FecetErrorNyvPk createPk() {
        return new FecetErrorNyvPk(idErrorNyv);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetErrorNyv: ");
        ret.append("idErrorNyv=" + idErrorNyv);
        ret.append(", idOrden=" + idOrden);
        ret.append(", codigoRespuesta=" + codigoRespuesta);
        ret.append(", mensajeRespuesta=" + mensajeRespuesta);
        ret.append(", fechaRespuesta=" + fechaRespuesta);
        ret.append(", observaciones=" + observaciones);
        return ret.toString();
    }

}
