package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetAnexoPruebasPericialesPk implements Serializable {


    @SuppressWarnings("compatibility:8329666620396520431")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idAnexoPruebasPericiales;

    public FecetAnexoPruebasPericialesPk() {       
    }
    
    public void setIdAnexoPruebasPericiales(final BigDecimal idAnexoPruebasPericiales) {
        this.idAnexoPruebasPericiales = idAnexoPruebasPericiales;
    }

    /** 
     * Gets the value of idAnexosProrrogaOrden
     */
    public BigDecimal getIdAnexoPruebasPericiales() {
        return idAnexoPruebasPericiales;
    }

    

    /**
     * Method 'FecetAnexosProrrogaOrdenPk'
     * 
     * @param idAnexosProrrogaOrden
     */
    public FecetAnexoPruebasPericialesPk(final BigDecimal idAnexoPruebasPericiales) {
        this.idAnexoPruebasPericiales = idAnexoPruebasPericiales;
    }
}
