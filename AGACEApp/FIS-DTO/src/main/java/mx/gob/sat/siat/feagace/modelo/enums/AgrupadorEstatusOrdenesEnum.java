package mx.gob.sat.siat.feagace.modelo.enums;

public enum AgrupadorEstatusOrdenesEnum {
    
    SOLICITUD_ORDEN(1, "Solicitud de orden"),
    ORDEN_EMITIDA(2, "Orden Emitida"),
    ORDEN_EN_PROCESO(3, "Orden en Proceso"),
    ORDEN_CONCLUIDA_CAMBIO_METODO(4, "Orden Concluida por Cambio de Método"),
    ORDEN_CONCLUIDA(5, "Orden Concluida");
    
    private final int idGrupo;
    private final String descripcion;

    private AgrupadorEstatusOrdenesEnum(int idGrupo, String descripcion) {
        this.idGrupo = idGrupo;
        this.descripcion = descripcion;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
