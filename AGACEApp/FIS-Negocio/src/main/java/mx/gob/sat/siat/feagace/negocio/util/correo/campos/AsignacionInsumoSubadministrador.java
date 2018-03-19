package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AsignacionInsumoSubadministrador {
    ID_REGISTRO("id_registro");

    private String fieldName;

    private AsignacionInsumoSubadministrador(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
