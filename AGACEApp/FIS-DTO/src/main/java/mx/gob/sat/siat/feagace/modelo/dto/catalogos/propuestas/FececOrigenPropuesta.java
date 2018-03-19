package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececOrigenPropuesta extends BaseModel implements Serializable{

    @SuppressWarnings("compatibility:5175766952646481990")
    private static final long serialVersionUID = 9119507804767520190L;
    
    /**
     * Method 'FececTipoPropuesta'
     *
     */
    public FececOrigenPropuesta() {
    }
    
    /**
     * This attribute maps to the column ID_ORIGEN in the FECEC_ORIGEN_PROPUESTA table.
     */
    private BigDecimal idOrigen;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_ORIGEN_PROPUESTA table.
     */
    private String descripcion;

    public void setIdOrigen(BigDecimal idOrigen) {
        this.idOrigen = idOrigen;
    }

    public BigDecimal getIdOrigen() {
        return idOrigen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
