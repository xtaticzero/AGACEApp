/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.util.*;

import java.math.BigDecimal;

public class FececCentral implements Serializable {
    /**
     * This attribute maps to the column ID_CENTRAL in the FECEC_CENTRAL table.
     */
    private BigDecimal idCentral;

    /**
     * This attribute maps to the column ID_ARACE in the FECEC_CENTRAL table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column RFC in the FECEC_CENTRAL table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_CENTRAL table.
     */
    private String nombre;

    /**
     * This attribute maps to the column CORREO in the FECEC_AUDITOR table.
     */
    private String correo;

    /**
     * Method 'FececCentral'
     *
     */
    public FececCentral() {
    }

    /**
     * Method 'getIdCentral'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCentral() {
        return idCentral;
    }

    /**
     * Method 'setIdCentral'
     *
     * @param idCentral
     */
    public void setIdCentral(final BigDecimal idCentral) {
        this.idCentral = idCentral;
    }

    /**
     * Method 'getIdArace'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdArace() {
        return idArace;
    }

    /**
     * Method 'setIdArace'
     *
     * @param idArace
     */
    public void setIdArace(final BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Method 'getRfc'
     *
     * @return String
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Method 'setRfc'
     *
     * @param rfc
     */
    public void setRfc(final String rfc) {
        this.rfc = rfc;
    }

    /**
     * Method 'getNombre'
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     *
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(final String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    /**
     * Method 'createPk'
     *
     * @return FececCentralPk
     */
    public FececCentralPk createPk() {
        return new FececCentralPk(idCentral);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececCentral: ");
        ret.append("idCentral=" + idCentral);
        ret.append(", idArace=" + idArace);
        ret.append(", rfc=" + rfc);
        ret.append(", nombre=" + nombre);
        ret.append(", correo=" + correo);
        return ret.toString();
    }
}
