/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececTipoPropuesta extends BaseModel implements Serializable {
    @SuppressWarnings("compatibility:5678653565262149077")
    private static final long serialVersionUID = 1L;
    
    /**
     * This attribute maps to the column ID_TIPO_PROPUESTA in the FECEC_TIPO_PROPUESTA table.
     */
    private BigDecimal idTipoPropuesta;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_TIPO_PROPUESTA table.
     */
    private String descripcion;

    /**
     * Method 'FececTipoPropuesta'
     *
     */
    public FececTipoPropuesta() {
    }

    /**
     * Method 'getIdTipoPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    /**
     * Method 'setIdTipoPropuesta'
     *
     * @param idTipoPropuesta
     */
    public void setIdTipoPropuesta(final BigDecimal idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
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
     * Method 'createPk'
     *
     * @return FececTipoPropuestaPk
     */
    public FececTipoPropuestaPk createPk() {
        return new FececTipoPropuestaPk(idTipoPropuesta);
    }
}
