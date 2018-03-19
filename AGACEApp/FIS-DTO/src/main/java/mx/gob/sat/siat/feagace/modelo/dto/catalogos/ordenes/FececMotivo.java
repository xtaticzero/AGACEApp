/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececMotivo extends BaseModel implements Serializable {
    /**
     * This attribute maps to the column ID_MOTIVO in the FECEC_MOTIVO table.
     */
    private BigDecimal idMotivo;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_MOTIVO table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column TIPO_MOTIVO in the FECEC_MOTIVO table.
     */
    private String tipoMotivo;

    /**
     * Method 'FececMotivo'
     *
     */
    public FececMotivo() {
    }

    /**
     * Method 'getIdMotivo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    /**
     * Method 'setIdMotivo'
     *
     * @param idMotivo
     */
    public void setIdMotivo(final BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    /**
     * Method 'getDescripcion'
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method 'setDescripcion'
     *
     * @param descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'getTipoMotivo'
     *
     * @return String
     */
    public String getTipoMotivo() {
        return tipoMotivo;
    }

    /**
     * Method 'setTipoMotivo'
     *
     * @param tipoMotivo
     */
    public void setTipoMotivo(final String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    /**
     * Method 'createPk'
     *
     * @return FececMotivoPk
     */
    public FececMotivoPk createPk() {
        return new FececMotivoPk(idMotivo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececMotivo: ");
        ret.append("idMotivo=" + idMotivo);
        ret.append(", descripcion=" + descripcion);
        ret.append(", tipoMotivo=" + tipoMotivo);
        return ret.toString();
    }

}
