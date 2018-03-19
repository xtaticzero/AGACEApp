package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum RechazoInsumo {
    ID_REGISTRO("id_registro"),
    NOMBRE_SUBADMINISTRADOR("nombre_subadministrador");

    private String data;

    private RechazoInsumo(String data) {
        this.data = data;
    }
    
    public String toString() {
        return this.data;
    }
}
