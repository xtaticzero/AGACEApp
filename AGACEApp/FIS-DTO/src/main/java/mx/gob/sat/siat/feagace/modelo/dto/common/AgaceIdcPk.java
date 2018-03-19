/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

/**
 * This class represents the primary key of the AGACE_IDC table.
 */
public class AgaceIdcPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idIdc;

    /**
     * This attribute represents whether the primitive attribute idIdc is null.
     */
    private boolean idIdcNull;

    /**
     * Method 'AgaceIdcPk'
     *
     */
    public AgaceIdcPk() {
    }

    /**
     * Method 'AgaceIdcPk'
     *
     * @param idIdc
     */
    public AgaceIdcPk(final Long idIdc) {
        this.idIdc = idIdc;
    }

    /**
     * Sets the value of idIdc
     */
    public void setIdIdc(final Long idIdc) {
        this.idIdc = idIdc;
    }

    /**
     * Gets the value of idIdc
     */
    public Long getIdIdc() {
        return idIdc;
    }

    /**
     * Sets the value of idIdcNull
     */
    public void setIdIdcNull(final boolean idIdcNull) {
        this.idIdcNull = idIdcNull;
    }

    /**
     * Gets the value of idIdcNull
     */
    public boolean isIdIdcNull() {
        return idIdcNull;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.AgaceIdcPk: ");
        ret.append("idIdc=" + idIdc);
        return ret.toString();
    }

}
