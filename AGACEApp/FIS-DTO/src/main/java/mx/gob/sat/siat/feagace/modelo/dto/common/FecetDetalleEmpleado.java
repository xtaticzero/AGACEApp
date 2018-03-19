/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetDetalleEmpleado extends BaseModel {

    @SuppressWarnings("compatibility:-780650645419004982")
    private static final long serialVersionUID = 5647031089350258151L;

    /**
     * This attribute maps to the column ID_DETALLE_EMPLEADO in the
     * FECET_DETALLE_EMPLEADO table.
     */
    private BigDecimal idDetalleEmpleado;

    /**
     * This attribute maps to the column ID_EMPLEADO in the
     * FECET_DETALLE_EMPLEADO table.
     */
    private BigDecimal idEmpleado;

    /**
     * This attribute maps to the column ID_TIPO_EMPLEADO in the
     * FECET_DETALLE_EMPLEADO table.
     */
    private BigDecimal idTipoEmpleado;

    /**
     * This attribute maps to the column ID_CENTRAL in the
     * FECET_DETALLE_EMPLEADO table.
     */
    private BigDecimal idCentral;

    /**
     * This attribute maps to the column ID_ARACE in the FECET_DETALLE_EMPLEADO
     * table.
     */
    private BigDecimal idArace;

    /**
     * Method 'FecetDetalleEmpleado'
     * 
     */
    public FecetDetalleEmpleado() {
    }

    /**
     * Method 'getIdDetalleEmpleado'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdDetalleEmpleado() {
        return idDetalleEmpleado;
    }

    /**
     * Method 'setIdDetalleEmpleado'
     * 
     * @param idDetalleEmpleado
     */
    public void setIdDetalleEmpleado(BigDecimal idDetalleEmpleado) {
        this.idDetalleEmpleado = idDetalleEmpleado;
    }

    /**
     * Method 'getIdEmpleado'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Method 'setIdEmpleado'
     * 
     * @param idEmpleado
     */
    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Method 'getIdTipoEmpleado'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    /**
     * Method 'setIdTipoEmpleado'
     * 
     * @param idTipoEmpleado
     */
    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
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
    public void setIdCentral(BigDecimal idCentral) {
        this.idCentral = idCentral;
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
    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Method 'createPk'
     * 
     * @return FecetDetalleEmpleadoPk
     */
    public FecetDetalleEmpleadoPk createPk() {
        return new FecetDetalleEmpleadoPk(idDetalleEmpleado);
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */

}
