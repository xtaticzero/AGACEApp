package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AvisoReasignacionInsumoAdministrador {
    ID_REGISTRO("id_registro"),
    NOMBRE_ADMINISTRADOR("nombre_administrador");

    private String fieldName;

    private AvisoReasignacionInsumoAdministrador(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
