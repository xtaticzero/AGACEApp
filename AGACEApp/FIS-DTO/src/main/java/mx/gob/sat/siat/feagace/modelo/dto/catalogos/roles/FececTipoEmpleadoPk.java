package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_TIPO_EMPLEADO table.
 */
public class FececTipoEmpleadoPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idTipoEmpleado;

    /**
     * Sets the value of idTipoEmpleado
     */
    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    /**
     * Gets the value of idTipoEmpleado
     */
    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    /**
     * Method 'FececTipoEmpleadoPk'
     * 
     */
    public FececTipoEmpleadoPk() {
    }

    /**
     * Method 'FececTipoEmpleadoPk'
     * 
     * @param idTipoEmpleado
     */
    public FececTipoEmpleadoPk(final BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececTipoEmpleadoPk: ");
        ret.append("idTipoEmpleado=").append(idTipoEmpleado);
        return ret.toString();
    }

}
