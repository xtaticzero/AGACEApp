package mx.gob.sat.siat.feagace.modelo.enums;

/**
 * Tipos de perfil para el firmante
 * 
 * @author Luis Rodriguez
 * @version 1.0
 */
public enum TipoPerfilEnum {
    
    FIRMANTE( 1, "Firmante" ),
    SUPLENTE( 2, "Suplente" );
    
    private Integer id;
    private String descripcion;
    
    private TipoPerfilEnum( Integer id, String descripcion ){
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
