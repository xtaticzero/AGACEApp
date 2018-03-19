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
 * This class represents the primary key of the FECEC_SUBPROGRAMA table.
 */
public class FececSubprogramaPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -965870607038577769L;

    private BigDecimal idSubprograma;

    /**
     * Sets the value of idSubprograma
     */
    public void setIdSubprograma(final BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    /**
     * Gets the value of idSubprograma
     */
    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    /**
     * Method 'FececSubprogramaPk'
     *
     */
    public FececSubprogramaPk() {
    }

    /**
     * Method 'FececSubprogramaPk'
     *
     * @param idSubprograma
     */
    public FececSubprogramaPk(final BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

}
