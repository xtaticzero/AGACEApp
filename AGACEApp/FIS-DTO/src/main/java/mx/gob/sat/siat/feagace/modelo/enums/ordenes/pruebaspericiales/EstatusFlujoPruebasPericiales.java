package mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales;

import java.math.BigDecimal;

public enum EstatusFlujoPruebasPericiales {
    
    PRUEBA_PERICIAL_RESUELTA_AUDITOR(158, "Prueba Pericial resuelta por auditor"),
    RESOLUCION_PRUEBA_PERICIAL_APROBADA_FIRMANTE(149, "Resolucion de pruebas periciales aprobada por el firmante"),
    RESOLUCION_PRUEBA_PERICIAL_RECHAZADA_FIRMANTE(150, "Resolucion de pruebas periciales no aprobada por el firmante");
    
    private final int idEstatus;
    private final String nombre;

    private EstatusFlujoPruebasPericiales(int idEstatus, String nombre) {
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
