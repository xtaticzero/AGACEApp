package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum AgrupadorOficiosEnum {

    CONCLUSIVOS(1, "Conclusivos"),
    ADMINISTRABLES(2, "Administrables"),
	MEDIDAS_APREMIO(3, "Medidas de Apremio");
    

    private final int idAgrupadorOficios;
    private final String nombre;

    private AgrupadorOficiosEnum(int idAgrupadorOficios, String nombre) {
        this.idAgrupadorOficios = idAgrupadorOficios;
        this.nombre = nombre;
    }

    public int getIdAgrupadorOficios() {
		return idAgrupadorOficios;
	}

	public String getNombre() {
        return nombre;
    }

    public BigDecimal getBigIdTipoAsociacionOficios() {
        return BigDecimal.valueOf(idAgrupadorOficios);
    }

    public static AgrupadorOficiosEnum parse(int idAgrupadorOficios) {
        for (AgrupadorOficiosEnum tipoAgrupadorOficio : AgrupadorOficiosEnum.values()) {
            if (tipoAgrupadorOficio.getIdAgrupadorOficios() == idAgrupadorOficios) {
                return tipoAgrupadorOficio;
            }
        }
        return null;
    }

}
