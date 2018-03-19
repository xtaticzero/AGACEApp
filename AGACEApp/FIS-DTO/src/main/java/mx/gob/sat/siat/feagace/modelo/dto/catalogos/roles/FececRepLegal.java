package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececRepLegal extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la columna ID_REP_LEGAL de la tabla FECEC_REP_LEGAL
     */
    private BigDecimal idRepLegal;

    /**
     * Este atributo mapea la columna RFC de la tabla FECEC_REP_LEGAL
     */
    private String rfc;

    /**
     * Este atributo mapea la columna NOMBRE de la tabla FECEC_REP_LEGAL
     */
    private String nombre;

    /**
     * Metodo 'FececRepLegal'
     */
    public FececRepLegal() {
    }

    /**
     * Metodo 'createPk'
     * @return FececRepLegalPk
     */
    public FececRepLegalPk createPk() {
        return new FececRepLegalPk(idRepLegal);
    }

    /**
     * Metodo  'setIdRepLegal'
     * @param idRepLegal
     */
    public void setIdRepLegal(final BigDecimal idRepLegal) {
        this.idRepLegal = idRepLegal;
    }

    /**
     * Metodo 'getIdRepLegal'
     * @return idRepLegal
     */
    public BigDecimal getIdRepLegal() {
        return idRepLegal;
    }

    /**
     * Metodo 'setRfc'
     * @param rfc
     */
    public void setRfc(final String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodo 'getRfc'
     * @return rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodo setNombre
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececRepLegal: ");
        ret.append("idRepLegal=").append(idRepLegal);
        ret.append(", rfc=").append(rfc);
        ret.append(", nombre=").append(nombre);
        return ret.toString();
    }
}
