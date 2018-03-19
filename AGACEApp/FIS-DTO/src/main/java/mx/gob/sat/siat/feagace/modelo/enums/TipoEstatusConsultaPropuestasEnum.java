package mx.gob.sat.siat.feagace.modelo.enums;

public enum TipoEstatusConsultaPropuestasEnum {
    
    PRESENTADA_COMITE(1L, "Presentada a Comité"),
    INFORMADA_COMITE(2L, "Informada a Comité"),
    APROBADA_COMITE(3L, "Aprobada en Comité"),
    NO_APROBADA_COMITE(4L, "No Aprobada en Comité");
    
    /**
     * ID_TIPO_ESTATUS_CONSULTA
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private TipoEstatusConsultaPropuestasEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoEstatusConsultaPropuestasEnum getById(Long id) {
        for (TipoEstatusConsultaPropuestasEnum estatusConsultaPropuestas : values()) {
            if (estatusConsultaPropuestas.id == id) {
                return estatusConsultaPropuestas;
            }
        }
        return null;
    }

}
