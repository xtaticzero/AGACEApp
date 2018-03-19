/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECEC_SECTOR table.
 */
public class FececSectorPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 7612043900565928660L;
    private BigDecimal idSector;

    /**
     * Sets the value of idSector
     */
    public void setIdSector(final BigDecimal idSector) {
        this.idSector = idSector;
    }

    /**
     * Gets the value of idSector
     */
    public BigDecimal getIdSector() {
        return idSector;
    }

    /**
     * Method 'FececSectorPk'
     *
     */
    public FececSectorPk() {
    }

    /**
     * Method 'FececSectorPk'
     *
     * @param idSector
     */
    public FececSectorPk(final BigDecimal idSector) {
        this.idSector = idSector;
    }

}
