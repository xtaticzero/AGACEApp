package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AvisoRechazoReasignacionInsumoAdministrador {
    ID_REGISTRO("id_registro"),
    NOMBRE_ADMINISTRADOR("nombre_administrador");

    private String fieldName;

    private AvisoRechazoReasignacionInsumoAdministrador(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
