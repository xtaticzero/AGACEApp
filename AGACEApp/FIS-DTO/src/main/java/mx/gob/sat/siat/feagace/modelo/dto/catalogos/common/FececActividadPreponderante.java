/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececActividadPreponderante extends BaseModel implements Serializable {
    /** 
     * This attribute maps to the column ID_ACTIVIDAD_PREPONDERANTE in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    private BigDecimal idActividadPreponderante;

    /** 
     * This attribute maps to the column DESCRIPCION in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    private String descripcion;

    /**
     * Method 'FececActividadPreponderante'
     * 
     */
    public FececActividadPreponderante() {
    }

    /**
     * Method 'getIdActividadPreponderante'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdActividadPreponderante() {
        return idActividadPreponderante;
    }

    /**
     * Method 'setIdActividadPreponderante'
     * 
     * @param idActividadPreponderante
     */
    public void setIdActividadPreponderante(final BigDecimal idActividadPreponderante) {
        this.idActividadPreponderante = idActividadPreponderante;
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
     * @return FececActividadPreponderantePk
     */
    public FececActividadPreponderantePk createPk() {
        return new FececActividadPreponderantePk(idActividadPreponderante);
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dto.FececActividadPreponderante: " );
        ret.append( "idActividadPreponderante=").append(idActividadPreponderante );
        ret.append( ", descripcion=").append(descripcion );
        return ret.toString();
    }

}
