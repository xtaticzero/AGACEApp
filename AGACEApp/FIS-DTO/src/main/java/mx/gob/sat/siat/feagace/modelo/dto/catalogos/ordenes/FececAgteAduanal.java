package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececAgteAduanal extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea los datos de la columna ID_AGTE_ADUANAL de la tabla FECEC_AGTE_ADUANAL
     */
    private BigDecimal idAgteAduanal;

    /**
     * Este atributo mapea los datos de la columna NOMBRE de la tabla FECEC_AGTE_ADUANAL
     */
    private String nombre;

    /**
     * Este atributo mapea los datos de la columna RFC de la tabla FECEC_AGTE_ADUANAL
     */
    private String rfc;

    /**
     * Metodo 'FececAgteAduanal'
     */
    public FececAgteAduanal() {
    }

    /**
     * Metodo 'setIdAgteAduanal'
     * @param idAgteAduanal
     */
    public void setIdAgteAduanal(final BigDecimal idAgteAduanal) {
        this.idAgteAduanal = idAgteAduanal;
    }

    /**
     * Metodo 'getIdAgteAduanal'
     * @return idAgteAduanal
     */
    public BigDecimal getIdAgteAduanal() {
        return idAgteAduanal;
    }

    /**
     * Metodo 'setNombre'
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo 'getNombre'
     * @return nombre
     */
    public String getNombre() {
        return nombre;
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
     * Metodo 'createPk'
     * @return FececRepLegalPk
     */
    public FececAgteAduanalPk createPk() {
        return new FececAgteAduanalPk(idAgteAduanal);
    }

    public boolean valida(final Object other) {
        final FececAgteAduanal cast = (FececAgteAduanal)other;
        if (idAgteAduanal == null ? !cast.idAgteAduanal.equals(idAgteAduanal) :
            !idAgteAduanal.equals(cast.idAgteAduanal)) {
            return false;
        }

        if (rfc == null ? !cast.rfc.equals(rfc) : !rfc.equals(cast.rfc)) {
            return false;
        }

        if (nombre == null ? !cast.nombre.equals(nombre) : !nombre.equals(cast.nombre)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececAgteAduanal: ");
        ret.append("idAgteAduanal=" + idAgteAduanal);
        ret.append(", rfc=" + rfc);
        ret.append(", nombre=" + nombre);
        return ret.toString();
    }
}
