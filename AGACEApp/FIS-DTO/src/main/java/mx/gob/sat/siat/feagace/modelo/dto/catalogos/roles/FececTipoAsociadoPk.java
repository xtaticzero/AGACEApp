/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * This class represents the primary key of the FECEC_TIPO_ASOCIADO table.
 */
public class FececTipoAsociadoPk implements Serializable {
    private BigDecimal idTipoAsociado;

    /** 
     * This attribute represents whether the primitive attribute idTipoAsociado is null.
     */
    private boolean idTipoAsociadoNull;

    /** 
     * Sets the value of idTipoAsociado
     */
    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
        this.idTipoAsociado = idTipoAsociado;
    }

    /** 
     * Gets the value of idTipoAsociado
     */
    public BigDecimal getIdTipoAsociado() {
        return idTipoAsociado;
    }

    /**
     * Method 'FececTipoAsociadoPk'
     * 
     */
    public FececTipoAsociadoPk() {
    }

    /**
     * Method 'FececTipoAsociadoPk'
     * 
     * @param idTipoAsociado
     */
    public FececTipoAsociadoPk(final BigDecimal idTipoAsociado) {
        this.idTipoAsociado = idTipoAsociado;
    }

    /** 
     * Sets the value of idTipoAsociadoNull
     */
    public void setIdTipoAsociadoNull(boolean idTipoAsociadoNull) {
        this.idTipoAsociadoNull = idTipoAsociadoNull;
    }

    /** 
     * Gets the value of idTipoAsociadoNull
     */
    public boolean isIdTipoAsociadoNull() {
        return idTipoAsociadoNull;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociadoPk: " );
        ret.append( "idTipoAsociado=");
        ret.append(idTipoAsociado);
        return ret.toString();
    }

}
