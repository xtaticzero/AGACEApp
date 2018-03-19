package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AsignacionInsumoCentral {
    ID_REGISTRO("id_registro"),
    NUM_REGISTROS("num_registros");

    private String fieldName;

    private AsignacionInsumoCentral(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
