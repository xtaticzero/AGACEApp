package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AsignacionInsumo {
    NUM_OFICIO("num_oficio");

    private String fieldName;

    private AsignacionInsumo(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
