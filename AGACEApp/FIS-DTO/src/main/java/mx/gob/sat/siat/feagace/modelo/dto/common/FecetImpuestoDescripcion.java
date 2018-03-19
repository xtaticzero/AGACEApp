
package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;

public class FecetImpuestoDescripcion implements Serializable{    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private FecetImpuesto fecetImpuesto;
    private FececTipoImpuesto fececTipoImpuesto;


    public void setFecetImpuesto(FecetImpuesto fecetImpuesto) {
        this.fecetImpuesto = fecetImpuesto;
    }

    public FecetImpuesto getFecetImpuesto() {
        return fecetImpuesto;
    }

    public void setFececTipoImpuesto(FececTipoImpuesto fececTipoImpuesto) {
        this.fececTipoImpuesto = fececTipoImpuesto;
    }

    public FececTipoImpuesto getFececTipoImpuesto() {
        return fececTipoImpuesto;
    }
}

