package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

public class FececAgteAduanalPk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributo que mapea la columna ID_REP_LEGAL
     */
    private BigDecimal idAgteAduanal;

    /**
     * Metodo 'FececAgteAduanalPk'
     */
    public FececAgteAduanalPk() {
        super();
    }

    public FececAgteAduanalPk(final BigDecimal idAgteAduanal) {
        this.idAgteAduanal = idAgteAduanal;
    }

    public void setIdAgteAduanal(final BigDecimal idAgteAduanal) {
        this.idAgteAduanal = idAgteAduanal;
    }

    public BigDecimal getIdAgteAduanal() {
        return idAgteAduanal;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececAgteAduanalPk: ");
        ret.append("idAgteAduanal=" + idAgteAduanal);
        return ret.toString();
    }
}
