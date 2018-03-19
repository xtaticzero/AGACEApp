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

/**
 * This class represents the primary key of the FECEC_ARACE table.
 */
public class FececAracePk extends BaseModel {

    private static final long serialVersionUID = 1L;
    private BigDecimal idArace;

    /**
     * Method 'FececAracePk'
     *
     */
    public FececAracePk() {
    }

    /**
     * Method 'FececAracePk'
     *
     * @param idArace
     */
    public FececAracePk(final BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Sets the value of idArace
     */
    public void setIdArace(final BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Gets the value of idArace
     */
    public BigDecimal getIdArace() {
        return idArace;
    }

}
