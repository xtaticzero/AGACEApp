package mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales;

import java.math.BigDecimal;

public enum EstatusPruebasPericiales {
    
    PRUEBAS_PERICIALES_PENDIENTE_APROBACION(153, "Prueba Pericial Pendiente de aprobacion"),
    RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV(151, "Resolucion de pruebas periciales firmada y enviada a NyV"),
    PRUEBAS_PERICIALES_NOTIFICADA_CONTRIBUYENTE(154, "Prueba Pericial Notificada al Contribuyente"),
    PRUEBAS_PERICIALES_ERROR_NOTIFICAR_CONTRIBUYENTE(155, "Error al notificar la Prueba Pericial al contribuyente"),
    RESOLUCION_PRUEBAS_PERICIALES_CONCLUIDA(156, "Resolucion de Prueba Pericial Concluida Aprobada");
    
    private final int idEstatus;
    private final String nombre;

    private EstatusPruebasPericiales(int idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public EstatusPruebasPericiales parse(int idEstatus) {
        for (EstatusPruebasPericiales estatus : EstatusPruebasPericiales.values()) {
            if (estatus.getIdEstatus() == idEstatus) {
                return estatus;
            }
        }
        return null;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public BigDecimal getBigIdEstatus() {
        return BigDecimal.valueOf(idEstatus);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
