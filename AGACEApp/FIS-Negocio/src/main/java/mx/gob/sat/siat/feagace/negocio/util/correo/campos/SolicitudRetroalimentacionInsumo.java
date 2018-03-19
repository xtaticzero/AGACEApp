package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum SolicitudRetroalimentacionInsumo {
    ID_REGISTRO("id_registro"),
    NOMBRE_ADM_ORIGEN("Nombre del Administrador Origen"),
    NOMBRE_ADM_DESTINO("Nombre del Administrador destino"),
    ID_REGISTRO_ESPACIO("Id Registro"),
    NOMBRE_SUB_ADMINISTRADOR("Nombre del Subadministrador ");

    private String data;

    private SolicitudRetroalimentacionInsumo(String data) {
        this.data = data;
    }


    public String toString() {
        return data;
    }
}