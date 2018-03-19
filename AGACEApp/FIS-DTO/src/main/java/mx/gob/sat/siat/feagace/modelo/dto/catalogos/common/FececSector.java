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

public class FececSector extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -5008443574406335221L;

    /**
     * This attribute maps to the column ID_SECTOR in the FECEC_SECTOR table.
     */
    private BigDecimal idSector;

        /**
     * This attribute maps to the column ID_GENERAL in the FECEC_SECTOR table.
     */
    private BigDecimal idGeneral;
    
    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_SECTOR table.
     */
    private String descripcion;

    /**
     * Method 'FececSector'
     *
     */
    public FececSector() {
    }

    /**
     * Method 'getIdSector'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSector() {
        return idSector;
    }

    /**
     * Method 'setIdSector'
     *
     * @param idSector
     */
    public void setIdSector(final BigDecimal idSector) {
        this.idSector = idSector;
    }

    /**
     * Method 'getDescripcion'
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method 'setDescripcion'
     *
     * @param descripcion
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'createPk'
     *
     * @return FececSectorPk
     */
    public FececSectorPk createPk() {
        return new FececSectorPk(idSector);
    }

    public BigDecimal getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(BigDecimal idGeneral) {
        this.idGeneral = idGeneral;
    }       

}
