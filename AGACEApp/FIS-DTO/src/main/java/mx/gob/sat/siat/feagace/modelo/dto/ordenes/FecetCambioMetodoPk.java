package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_CAMBIO_METODO table.
 */
public class FecetCambioMetodoPk implements Serializable {
    @SuppressWarnings("compatibility:-7759386398612392098")
    private static final long serialVersionUID = 1L;
    private BigDecimal idCambioMetodo;

    /**
     * Sets the value of idCambioMetodo
     */
    public void setIdCambioMetodo(BigDecimal idCambioMetodo) {
        this.idCambioMetodo = idCambioMetodo;
    }

    /**
     * Gets the value of idCambioMetodo
     */
    public BigDecimal getIdCambioMetodo() {
        return idCambioMetodo;
    }

    /**
     * Method 'FecetCambioMetodoPk'
     *
     */
    public FecetCambioMetodoPk() {
    }

    /**
     * Method 'FecetCambioMetodoPk'
     *
     * @param idCambioMetodo
     */
    public FecetCambioMetodoPk(final BigDecimal idCambioMetodo) {
        this.idCambioMetodo = idCambioMetodo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dao.ordenes.dto.FecetCambioMetodoPk: ");
        ret.append("idCambioMetodo=").append(idCambioMetodo);
        return ret.toString();
    }
}
