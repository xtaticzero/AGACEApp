package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum CifrasOrdenesEnum {
    CIFRA_COBRADA(BigDecimal.valueOf(1), "Cifra cobrada"),
    CIFRA_VIRTUAL(BigDecimal.valueOf(2), "Cifra virtual"),
    CIFRA_LIQUIDADA(BigDecimal.valueOf(3), "Cifra liquidada");
    
    private BigDecimal idCifra;
    private String descripcion;
    
    CifrasOrdenesEnum(BigDecimal idCifra, String descripcion){
        this.idCifra = idCifra;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdCifra() {
        return idCifra;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
