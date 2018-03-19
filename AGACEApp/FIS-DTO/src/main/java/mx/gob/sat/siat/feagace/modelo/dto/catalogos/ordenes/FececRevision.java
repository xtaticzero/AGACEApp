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


public class FececRevision extends BaseModel implements Serializable {
    @SuppressWarnings("compatibility:9198101375219222315")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_REVISION in the FECEC_REVISION table.
     */
    private BigDecimal idRevision;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_REVISION table.
     */
    private String descripcion;

    /**
     * Method 'FececRevision'
     *
     */
    public FececRevision() {
    }

    /**
     * Method 'getIdRevision'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRevision() {
        return idRevision;
    }

    /**
     * Method 'setIdRevision'
     *
     * @param idRevision
     */
    public void setIdRevision(final BigDecimal idRevision) {
        this.idRevision = idRevision;
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
     * @return FececRevisionPk
     */
    public FececRevisionPk createPk() {
        return new FececRevisionPk(idRevision);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececRevision: ");
        ret.append("idRevision=" + idRevision);
        ret.append(", descripcion=" + descripcion);
        return ret.toString();
    }

}
