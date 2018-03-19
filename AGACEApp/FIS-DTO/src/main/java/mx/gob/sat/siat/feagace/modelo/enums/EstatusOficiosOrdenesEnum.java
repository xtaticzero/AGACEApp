package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum EstatusOficiosOrdenesEnum {

    OFICIO_PENDIENTE_FIRMA(113, "Oficio pendiente de firma"),
    OFICIO_FIRMADO_ENVIADO_NYV(114, "Oficio firmado y enviado a NYV"),
    OFICIO_NOTIFICADO_CONTRIBUYENTE(115, "Oficio notificado al contribuyente"),
    OFICIO_RECHAZADO(116, "Oficio rechazado");

    private final int idEstatus;
    private final String nombre;

    private EstatusOficiosOrdenesEnum(int idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public static EstatusOficiosOrdenesEnum parse(int idEstatus) {
        for (EstatusOficiosOrdenesEnum estatus : EstatusOficiosOrdenesEnum.values()) {
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
