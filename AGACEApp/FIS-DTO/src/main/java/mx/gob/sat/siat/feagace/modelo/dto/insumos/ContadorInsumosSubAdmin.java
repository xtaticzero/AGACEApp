package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ContadorInsumosSubAdmin extends BaseModel {
    
    /**
     * Serial
     */
    private static final long serialVersionUID = -6827088792381249987L;
    
    private String nombre;
    private Long totalInsumos;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTotalInsumos(Long totalInsumos) {
        this.totalInsumos = totalInsumos;
    }

    public Long getTotalInsumos() {
        return totalInsumos;
    }
}
