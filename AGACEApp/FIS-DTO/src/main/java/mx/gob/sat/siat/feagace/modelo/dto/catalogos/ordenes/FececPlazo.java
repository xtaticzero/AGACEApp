/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececPlazo extends BaseModel implements Serializable {
    @SuppressWarnings("compatibility:5540706502326143413")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_PLAZO in the FECEC_PLAZO table.
     */
    private BigDecimal idPlazo;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_PLAZO table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column DIAS_PLAZO in the FECEC_PLAZO table.
     */
    private Integer diasPlazo;

    /**
     * This attribute maps to the column DIAS_HABILES in the FECEC_PLAZO table.
     */
    private String diasHabiles;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECEC_PLAZO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column NOMBREARCHIVO in the FECET_PROMOCION table.
     */
    private String nombreArchivo;

    /**
     * Method 'FececPlazo'
     *
     */
    public FececPlazo() {
    }

    /**
     * Method 'getIdPlazo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPlazo() {
        return idPlazo;
    }

    /**
     * Method 'setIdPlazo'
     *
     * @param idPlazo
     */
    public void setIdPlazo(BigDecimal idPlazo) {
        this.idPlazo = idPlazo;
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
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'getDiasPlazo'
     *
     * @return Integer
     */
    public Integer getDiasPlazo() {
        return diasPlazo;
    }

    /**
     * Method 'setDiasPlazo'
     *
     * @param diasPlazo
     */
    public void setDiasPlazo(Integer diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    /**
     * Method 'getDiasHabiles'
     *
     * @return String
     */
    public String getDiasHabiles() {
        return diasHabiles;
    }

    /**
     * Method 'setDiasHabiles'
     *
     * @param diasHabiles
     */
    public void setDiasHabiles(String diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    /**
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     *
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getNombreArchivo'
     *
     * @return String
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Method 'setNombreArchivo'
     *
     * @param nombreArchivo
     */
    public void setNombreArchivo(final String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Method 'createPk'
     *
     * @return FececPlazoPk
     */
    public FececPlazoPk createPk() {
        return new FececPlazoPk(idPlazo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececPlazo: ");
        ret.append("idPlazo=" + idPlazo);
        ret.append(", descripcion=" + descripcion);
        ret.append(", diasPlazo=" + diasPlazo);
        ret.append(", diasHabiles=" + diasHabiles);
        ret.append(", fechaCreacion=" + fechaCreacion);
        return ret.toString();
    }

}
