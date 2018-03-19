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

public class FececSubprograma extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 5863805366297740613L;

    /**
     * This attribute maps to the column ID_SUBPROGRAMA in the FECEC_SUBPROGRAMA table.
     */
    private BigDecimal idSubprograma;

    /**
     * This attribute maps to the column ID_GENERAL in the FECEC_SUBPROGRAMA table.
     */
    private BigDecimal idGeneral;
    
    
    /**
     * This attribute maps to the column CLAVE in the FECEC_SUBPROGRAMA table.
     */
    private String clave;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_SUBPROGRAMA table.
     */
    private String descripcion;

    /**
     * Method 'FececSubprograma'
     *
     */
    public FececSubprograma() {
    }

    /**
     * Method 'getIdSubprograma'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    /**
     * Method 'setIdSubprograma'
     *
     * @param idSubprograma
     */
    public void setIdSubprograma(final BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    /**
     * Method 'getClave'
     *
     * @return String
     */
    public String getClave() {
        return clave;
    }

    /**
     * Method 'setClave'
     *
     * @param clave
     */
    public void setClave(final String clave) {
        this.clave = clave;
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
     * @return FececSubprogramaPk
     */
    public FececSubprogramaPk createPk() {
        return new FececSubprogramaPk(idSubprograma);
    }

    public BigDecimal getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(BigDecimal idGeneral) {
        this.idGeneral = idGeneral;
    }
}
