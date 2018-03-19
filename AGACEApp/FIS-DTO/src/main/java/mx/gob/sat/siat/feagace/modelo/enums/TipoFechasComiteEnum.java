package mx.gob.sat.siat.feagace.modelo.enums;

public enum TipoFechasComiteEnum {
    
    FECHA_PRESENTACION_COMITE(1L, "Fecha de presentación a Comité"),
    FECHA_INFORME_COMITE(2L, "Fecha de informe a Comité"),
    FECHA_PRESENTACION_COMITE_REGIONAL(3L, "Fecha de presentación a Comité Regional"),
    FECHA_INFORME_COMITE_REGIONAL(4L, "Fecha de informe a Comité Regional");
    
    /**
     * ID_TIPO_FECHA_COMITE
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private TipoFechasComiteEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoFechasComiteEnum getById(Long id) {
        for (TipoFechasComiteEnum fechasComite : values()) {
            if (fechasComite.id == id) {
                return fechasComite;
            }
        }
        return null;
    }

}
