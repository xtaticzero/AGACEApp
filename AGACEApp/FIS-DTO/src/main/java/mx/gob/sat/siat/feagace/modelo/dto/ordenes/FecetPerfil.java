package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * Clase que encapsula la informacion del perfil
 * 
 * @author Luis Rodriguez
 * @version 1.0
 * @see BaseModel
 */
public class FecetPerfil extends BaseModel implements Serializable {
    
    @SuppressWarnings("compatibility:-3308984928782631380")
    private static final long serialVersionUID = -9155955749924742954L;

    private Integer id;
    private String perfil;
    
    public FecetPerfil( Integer id, String perfil ) {
        this.id = id;
        this.perfil = perfil;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }
    
}
