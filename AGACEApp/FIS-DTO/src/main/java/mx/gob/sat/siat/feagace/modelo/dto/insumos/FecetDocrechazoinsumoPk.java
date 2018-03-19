/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/** 
 * This class represents the primary key of the FECET_DOCRECHAZOINSUMO table.
 */
public class FecetDocrechazoinsumoPk extends BaseModel {
    
    /**
     * Serial
     */
    private static final long serialVersionUID = 446774158675523590L;

    private BigDecimal idDocrechazoinsumo;

    /** 
     * This attribute represents whether the primitive attribute idDocrechazoinsumo is null.
     */
    private boolean idDocrechazoinsumoNull;

    /** 
     * Sets the value of idDocrechazoinsumo
     */
    public void setIdDocrechazoinsumo(BigDecimal idDocrechazoinsumo) {
        this.idDocrechazoinsumo = idDocrechazoinsumo;
    }

    /** 
     * Gets the value of idDocrechazoinsumo
     */
    public BigDecimal getIdDocrechazoinsumo() {
        return idDocrechazoinsumo;
    }

    /**
     * Method 'FecetDocrechazoinsumoPk'
     * 
     */
    public FecetDocrechazoinsumoPk() {
    }

    /**
     * Method 'FecetDocrechazoinsumoPk'
     * 
     * @param idDocrechazoinsumo
     */
    public FecetDocrechazoinsumoPk(final BigDecimal idDocrechazoinsumo) {
        this.idDocrechazoinsumo = idDocrechazoinsumo;
    }

    /** 
     * Sets the value of idDocrechazoinsumoNull
     */
    public void setIdDocrechazoinsumoNull(boolean idDocrechazoinsumoNull) {
        this.idDocrechazoinsumoNull = idDocrechazoinsumoNull;
    }

    /** 
     * Gets the value of idDocrechazoinsumoNull
     */
    public boolean isIdDocrechazoinsumoNull() {
        return idDocrechazoinsumoNull;
    }

}
