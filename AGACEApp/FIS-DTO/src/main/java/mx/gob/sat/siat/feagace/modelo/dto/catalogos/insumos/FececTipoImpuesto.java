/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececTipoImpuesto extends BaseModel implements Serializable {

    @SuppressWarnings("compatibility:7223214892242830264")
    private static final long serialVersionUID = -27011332785125767L;

    /**
     * This attribute maps to the column ID_TIPO_IMPUESTO in the FECEC_TIPO_IMPUESTO table.
     */
    private BigDecimal idTipoImpuesto;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_TIPO_IMPUESTO table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column ABREVIATURA in the FECEC_TIPO_IMPUESTO table.
     */
    private String abreviatura;

    /**
     * Method 'FececTipoImpuesto'
     *
     */
    public FececTipoImpuesto() {
    }

    /**
     * Method 'getIdTipoImpuesto'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    /**
     * Method 'setIdTipoImpuesto'
     *
     * @param idTipoImpuesto
     */
    public void setIdTipoImpuesto(final BigDecimal idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
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
     * Method 'getAbreviatura'
     *
     * @return String
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Method 'setAbreviatura'
     *
     * @param abreviatura
     */
    public void setAbreviatura(final String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * Method 'createPk'
     *
     * @return FececTipoImpuestoPk
     */
    public FececTipoImpuestoPk createPk() {
        return new FececTipoImpuestoPk(idTipoImpuesto);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececTipoImpuesto: ");
        ret.append("idTipoImpuesto=").append(idTipoImpuesto);
        ret.append(", descripcion=").append(descripcion);
        ret.append(", abreviatura=").append(abreviatura);
        return ret.toString();
    }
}
