/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_ASOCIADOS table.
 */
public class FecetAsociadoPk implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idAsociado;

    /**
     * This attribute represents whether the primitive attribute idAsociado is
     * null.
     */
    private boolean idAsociadoNull;

    /**
     * Sets the value of idAsociado
     */
    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
    }

    /**
     * Gets the value of idAsociado
     */
    public BigDecimal getIdAsociado() {
        return idAsociado;
    }

    /**
     * Method 'FececAsociadosPk'
     *
     */
    public FecetAsociadoPk() {
    }

    /**
     * Method 'FececAsociadosPk'
     *
     * @param idAsociado
     */
    public FecetAsociadoPk(final BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
    }

    /**
     * Sets the value of idAsociadoNull
     */
    public void setIdAsociadoNull(boolean idAsociadoNull) {
        this.idAsociadoNull = idAsociadoNull;
    }

    /**
     * Gets the value of idAsociadoNull
     */
    public boolean isIdAsociadoNull() {
        return idAsociadoNull;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAsociadosPk: ");
        ret.append("idAsociado=");
        ret.append(idAsociado);
        return ret.toString();
    }

}
