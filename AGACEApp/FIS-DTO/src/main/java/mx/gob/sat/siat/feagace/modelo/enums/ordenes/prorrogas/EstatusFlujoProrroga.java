package mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas;

import java.math.BigDecimal;

public enum EstatusFlujoProrroga {
    
    PRORROGA_RESUELTA_AUDITOR(122, "Prorroga resuelta por el auditor"),
    RESOLUCION_PRORROGA_APROBADA_FIRMANTE(123, "Resolucion de prorroga aprobada por el firmante"),
    RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE(124, "Resolucion de prorroga no aprobada por el firmante");
    
    private final int idEstatus;
    private final String nombre;

    private EstatusFlujoProrroga(int idEstatus, String nombre) {
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
