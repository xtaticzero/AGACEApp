/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececArace extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -6435484979791614880L;

    /**
     * This attribute maps to the column ID_ARACE in the FECEC_ARACE table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_ARACE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column SEDE in the FECEC_ARACE table.
     */
    private String sede;

    /**
     * This attribute maps to the column CENTRAL in the FECEC_ARACE table.
     */
    private Boolean central;

    /**
     * Method 'FececArace'
     *
     */
    public FececArace() {
    }

    /**
     * Method 'createPk'
     *
     * @return FececAracePk
     */
    public FececAracePk createPk() {
        return new FececAracePk(idArace);
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

    /**
     * Method 'getSede'
     *
     * @return String
     */
    public String getSede() {
        return sede;
    }

    /**
     * Method 'setSede'
     *
     * @param sede
     */
    public void setSede(final String sede) {
        this.sede = sede;
    }

    /**
     * Method 'setCentral'
     *
     * @param central
     */
    public void setCentral(final Boolean central) {
        this.central = central;
    }

    /**
     * Method 'getCentral'
     *
     * @return Boolean
     */
    public Boolean getCentral() {
        return central;
    }
}
