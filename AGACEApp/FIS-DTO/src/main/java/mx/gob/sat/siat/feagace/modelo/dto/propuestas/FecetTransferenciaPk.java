/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_TRANSFERENCIA table.
 */
public class FecetTransferenciaPk implements Serializable {
    private BigDecimal idTransferencia;

    /**
     * Sets the value of idTransferencia
     */
    public void setIdTransferencia(final BigDecimal idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * Gets the value of idTransferencia
     */
    public BigDecimal getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Method 'FecetTransferenciaPk'
     *
     */
    public FecetTransferenciaPk() {
    }

    /**
     * Method 'FecetTransferenciaPk'
     *
     * @param idTransferencia
     */
    public FecetTransferenciaPk(final BigDecimal idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetTransferenciaPk: ");
        ret.append("idTransferencia=" + idTransferencia);
        return ret.toString();
    }

}
