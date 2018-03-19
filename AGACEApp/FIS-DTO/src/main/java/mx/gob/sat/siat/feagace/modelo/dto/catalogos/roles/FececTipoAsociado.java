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


public class FececTipoAsociado extends BaseModel {
    @SuppressWarnings("compatibility:8379815050806458382")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_TIPO_ASOCIADO in the
     * FECEC_TIPO_ASOCIADO table.
     */
    private BigDecimal idTipoAsociado;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_TIPO_ASOCIADO table.
     */
    private String nombre;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_TIPO_ASOCIADO
     * table.
     */
    private String descripcion;

    /**
     * Method 'FececTipoAsociado'
     *
     */
    public FececTipoAsociado() {
    }

    /**
     * Method 'getIdTipoAsociado'
     *
     * @return long
     */
    public BigDecimal getIdTipoAsociado() {
        return idTipoAsociado;
    }

    /**
     * Method 'setIdTipoAsociado'
     *
     * @param idTipoAsociado
     */
    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
        this.idTipoAsociado = idTipoAsociado;
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
     * Method 'createPk'
     *
     * @return FececTipoAsociadoPk
     */
    public FececTipoAsociadoPk createPk() {
        return new FececTipoAsociadoPk(idTipoAsociado);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado: ");
        ret.append("idTipoAsociado=");
        ret.append(idTipoAsociado);
        ret.append(", descripcion=");
        ret.append(descripcion);
        return ret.toString();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
