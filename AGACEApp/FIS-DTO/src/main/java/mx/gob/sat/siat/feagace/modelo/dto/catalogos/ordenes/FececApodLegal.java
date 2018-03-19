/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececApodLegal extends BaseModel {


    @SuppressWarnings("compatibility:-1401648156339801464")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column IDAPODLEGAL in the FECEAAPODLEGAL table.
     */
    private BigDecimal idApodLegal;

    /**
     * This attribute maps to the column RFC in the FECEAAPODLEGAL table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEAAPODLEGAL table.
     */
    private String nombre;

    /**
     * This attribute maps to the column RFCCONTRIBUYENTE in the FECEAAPODLEGAL table.
     */
    private String rfcContribuyente;

    /**
     * Method 'FeceaApodLegal'
     *
     */
    public FececApodLegal() {
    }

    /**
     * Method 'getIdApodlegal'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdApodLegal() {
        return idApodLegal;
    }

    /**
     * Method 'setIdApodlegal'
     *
     * @param idApodLegal
     */
    public void setIdApodLegal(final BigDecimal idApodLegal) {
        this.idApodLegal = idApodLegal;
    }

    /**
     * Method 'createPk'
     *
     * @return FeceaApodLegalPk
     */
    public FececApodLegalPk createPk() {
        return new FececApodLegalPk(idApodLegal);
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

    /**
     * Method 'getRfcContribuyente'
     *
     * @return String
     */
    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    /**
     * Method 'setRfcContribuyente'
     *
     * @param rfcContribuyente
     */
    public void setRfcContribuyente(final String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FeceaApodLegal: ");
        ret.append("idApodLegal=" + idApodLegal);
        ret.append(", rfc=" + rfc);
        ret.append(", nombre=" + nombre);
        ret.append(", rfcContribuyente=" + rfcContribuyente);
        return ret.toString();
    }

}
