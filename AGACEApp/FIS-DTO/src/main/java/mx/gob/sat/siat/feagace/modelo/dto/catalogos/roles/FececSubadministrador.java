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


public class FececSubadministrador extends BaseModel {
    @SuppressWarnings("compatibility:39019907315692535")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_SUBADMINISTRADOR in the FECEC_SUBADMINISTRADOR table.
     */
    private BigDecimal idSubadministrador;

    /**
     * This attribute maps to the column ID_ADMINISTRADOR in the FECEC_SUBADMINISTRADOR table.
     */
    private BigDecimal idAdministrador;

    /**
     * This attribute maps to the column RFC in the FECEC_SUBADMINISTRADOR table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_SUBADMINISTRADOR table.
     */
    private String nombre;

    /**
     * This attribute maps to the column CORREO in the FECEC_AUDITOR table.
     */
    private String correo;

    /**
     * Method 'FececSubadministrador'
     *
     */
    public FececSubadministrador() {
    }

    /**
     * Method 'getIdSubadministrador'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSubadministrador() {
        return idSubadministrador;
    }

    /**
     * Method 'setIdSubadministrador'
     *
     * @param idSubadministrador
     */
    public void setIdSubadministrador(final BigDecimal idSubadministrador) {
        this.idSubadministrador = idSubadministrador;
    }

    /**
     * Method 'getIdAdministrador'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * Method 'setIdAdministrador'
     *
     * @param idAdministrador
     */
    public void setIdAdministrador(final BigDecimal idAdministrador) {
        this.idAdministrador = idAdministrador;
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
     * @return FececSubadministradorPk
     */
    public FececSubadministradorPk createPk() {
        return new FececSubadministradorPk(idSubadministrador);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececSubadministrador: ");
        ret.append("idSubadministrador=").append(idSubadministrador);
        ret.append(", idAdministrador=").append(idAdministrador);
        ret.append(", rfc=").append(rfc);
        ret.append(", nombre=").append(nombre);
        ret.append(", correo=").append(correo);

        return ret.toString();
    }
}
