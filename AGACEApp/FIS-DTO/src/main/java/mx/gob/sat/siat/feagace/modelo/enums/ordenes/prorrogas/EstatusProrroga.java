package mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas;

import java.math.BigDecimal;

public enum EstatusProrroga {
    
    PRORROGA_PENDIENTE_APROBACION(121, "Prorroga pendiente de aprobacion"),
    RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV(125, "Resolucion de prorroga firmada y enviada a NyV"),
    PRORROGA_NOTIFICADA_CONTRIBUYENTE(127, "Prorroga notificada al contribuyente"),
    PRORROGA_ERROR_NOTIFICAR_CONTRIBUYENTE(128, "Prorroga con error al notificar al contribuyente"),
    RESOLUCION_PRORROGA_CONCLUIDA(129, "Resolucion de prorroga concluida");

    private final int idEstatus;
    private final String nombre;

    private EstatusProrroga(int idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public EstatusProrroga parse(int idEstatus) {
        for (EstatusProrroga estatus : EstatusProrroga.values()) {
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
