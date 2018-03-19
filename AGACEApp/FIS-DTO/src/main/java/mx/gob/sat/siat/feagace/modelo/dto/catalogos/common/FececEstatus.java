/*
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

import java.math.BigDecimal;

public class FececEstatus extends BaseModel{
    @SuppressWarnings("compatibility:8062654804903305571")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECEC_ESTATUS table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_ESTATUS table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column MODULO in the FECEC_ESTATUS table.
     */
    private String modulo;

    /**
     * Method 'FececEstatus'
     *
     */
    public FececEstatus() {
    }

    /**
     * Method 'getIdEstatus'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'setIdEstatus'
     *
     * @param idEstatus
     */
    public void setIdEstatus(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
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
     * Method 'getModulo'
     *
     * @return String
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * Method 'setModulo'
     *
     * @param modulo
     */
    public void setModulo(final String modulo) {
        this.modulo = modulo;
    }

    /**
     * Method 'createPk'
     *
     * @return FececEstatusPk
     */
    public FececEstatusPk createPk() {
        return new FececEstatusPk(idEstatus);
    }


}
