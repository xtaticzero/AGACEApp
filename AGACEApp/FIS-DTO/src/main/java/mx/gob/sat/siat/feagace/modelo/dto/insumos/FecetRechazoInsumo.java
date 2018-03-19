/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetRechazoInsumo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_RECHAZO_INSUMO in the
     * FECET_RECHAZO_INSUMO table.
     */
    private BigDecimal idRechazoInsumo;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_RECHAZO_INSUMO
     * table.
     */
    private BigDecimal idInsumo;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_RECHAZO_INSUMO
     * table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the
     * FECET_RECHAZO_INSUMO table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column RFC_RECHAZO in the FECET_RECHAZO_INSUMO
     * table.
     */
    private String rfcRechazo;

    private List<FecetDocrechazoinsumo> lstDocsRechazoInsumo;

    /**
     * Method 'FecetRechazoInsumo'
     *
     */
    public FecetRechazoInsumo() {
    }

    /**
     * Method 'getIdRechazoInsumo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRechazoInsumo() {
        return idRechazoInsumo;
    }

    /**
     * Method 'setIdRechazoInsumo'
     *
     * @param idRechazoInsumo
     */
    public void setIdRechazoInsumo(final BigDecimal idRechazoInsumo) {
        this.idRechazoInsumo = idRechazoInsumo;
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
     * Method 'getFechaRechazo'
     *
     * @return Date
     */
    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    /**
     * Method 'setFechaRechazo'
     *
     * @param fechaRechazo
     */
    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(
                fechaRechazo.getTime()) : null;
    }

    /**
     * Method 'getRfcRechazo'
     *
     * @return String
     */
    public String getRfcRechazo() {
        return rfcRechazo;
    }

    /**
     * Method 'setRfcRechazo'
     *
     * @param rfcRechazo
     */
    public void setRfcRechazo(final String rfcRechazo) {
        this.rfcRechazo = rfcRechazo;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetRechazoInsumoPk
     */
    public FecetRechazoInsumoPk createPk() {
        return new FecetRechazoInsumoPk(idRechazoInsumo);
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public List<FecetDocrechazoinsumo> getLstDocsRechazoInsumo() {
        return lstDocsRechazoInsumo;
    }

    public void setLstDocsRechazoInsumo(List<FecetDocrechazoinsumo> lstDocsRechazoInsumo) {
        this.lstDocsRechazoInsumo = lstDocsRechazoInsumo;
    }
    
    public int getNoDocsRechazoInsumo(){
        if (lstDocsRechazoInsumo != null && !lstDocsRechazoInsumo.isEmpty()) {
            return lstDocsRechazoInsumo.size();
        }
        return 0;
    }

}
