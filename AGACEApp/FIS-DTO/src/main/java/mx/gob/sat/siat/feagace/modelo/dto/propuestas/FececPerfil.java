package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * Clase que encapsula la informacion del perfil
 * 
 * @author Luis Rodriguez
 * @version 1.0
 * @see BaseModel
 */
public class FececPerfil extends BaseModel {

    @SuppressWarnings("compatibility:1122091938054933495")
    private static final long serialVersionUID = 4752250423185124298L;
    
    private Integer id;
    private String descripcion;
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
