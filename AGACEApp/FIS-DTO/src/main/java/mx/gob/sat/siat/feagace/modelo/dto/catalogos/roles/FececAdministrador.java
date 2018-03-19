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

public class FececAdministrador extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 6962288202678170303L;

    /**
     * This attribute maps to the column ID_ADMINISTRADOR in the
     * FECEC_ADMINISTRADOR table.
     */
    private BigDecimal idAdministrador;

    /**
     * This attribute maps to the column ID_ARACE in the FECEC_ADMINISTRADOR
     * table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column ID_CENTRAL in the FECEC_ADMINISTRADOR
     * table.
     */
    private BigDecimal idCentral;

    /**
     * This attribute maps to the column RFC in the FECEC_ADMINISTRADOR table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_ADMINISTRADOR
     * table.
     */
    private String nombre;

    /**
     * This attribute maps to the column CORREO in the FECEC_AUDITOR table.
     */
    private String correo;

    /**
     * Method 'FececAdministrador'
     *
     */
    public FececAdministrador() {
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
     * @return FececAdministradorPk
     */
    public FececAdministradorPk createPk() {
        return new FececAdministradorPk(idAdministrador);
    }
    
}
