/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

/**
 * @author sergio.vaca
 *
 */
public enum AcppceOpcionesEnum {
    REGISTRADOS(1, "Insumos registrados"),
    ASIGNADOS(2, "Insumos asignados a \u00e9sta Unidad Administrativa");
    
    private final int id;
    private final String descripcion;
    
    private AcppceOpcionesEnum(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public final int getId() {
        return id;
    }

    public final String getDescripcion() {
        return descripcion;
    }
}
