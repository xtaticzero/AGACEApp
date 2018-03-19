/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_ACTIVIDAD_PREPONDERANTE table.
 */
public class FececActividadPreponderantePk implements Serializable {
    private BigDecimal idActividadPreponderante;

    /**
     * Sets the value of idActividadPreponderante
     */
    public void setIdActividadPreponderante(final BigDecimal idActividadPreponderante) {
        this.idActividadPreponderante = idActividadPreponderante;
    }

    /**
     * Gets the value of idActividadPreponderante
     */
    public BigDecimal getIdActividadPreponderante() {
        return idActividadPreponderante;
    }

    /**
     * Method 'FececActividadPreponderantePk'
     *
     */
    public FececActividadPreponderantePk() {
    }

    /**
     * Method 'FececActividadPreponderantePk'
     *
     * @param idActividadPreponderante
     */
    public FececActividadPreponderantePk(final BigDecimal idActividadPreponderante) {
        this.idActividadPreponderante = idActividadPreponderante;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececActividadPreponderantePk: ");
        ret.append("idActividadPreponderante=").append(idActividadPreponderante);
        return ret.toString();
    }

}
