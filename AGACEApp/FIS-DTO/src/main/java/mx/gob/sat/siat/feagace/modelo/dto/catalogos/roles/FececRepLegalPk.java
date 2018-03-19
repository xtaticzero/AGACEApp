package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.io.Serializable;

import java.math.BigDecimal;

public class FececRepLegalPk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributo que mapea la columna ID_REP_LEGAL
     */
    private BigDecimal idRepLegal;

    /**
     * Metodo 'FececRepLegalPk'
     */
    public FececRepLegalPk() {
        super();
    }

    /**
     * Metodo 'FececRepLegalPk'
     * @param idRepLegal
     */
    public FececRepLegalPk(final BigDecimal idRepLegal) {
        this.idRepLegal = idRepLegal;
    }

    /**
     * Metodo 'setIdRepLegal'
     * @param idRepLegal
     */
    public void setIdRepLegal(final BigDecimal idRepLegal) {
        this.idRepLegal = idRepLegal;
    }

    /**
     * Metodo 'getIdRepLegal'
     * @return idRepLegal
     */
    public BigDecimal getIdRepLegal() {
        return idRepLegal;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececRepLegalPk: ");
        ret.append("idRepLegal=").append(idRepLegal);
        return ret.toString();
    }
}
