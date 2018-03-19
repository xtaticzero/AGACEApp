package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum TipoAsociacionOficiosEnum {

    RELACION(1, "Relacion"),
    DEPENDENCIA(2, "Dependencia");
    

    private final int idTipoAsociacionOficios;
    private final String nombre;

    private TipoAsociacionOficiosEnum(int idTipoAsociacionOficios, String nombre) {
        this.idTipoAsociacionOficios = idTipoAsociacionOficios;
        this.nombre = nombre;
    }

    public int getIdTipoAsociacionOficios() {
		return idTipoAsociacionOficios;
	}

	public String getNombre() {
        return nombre;
    }

    public BigDecimal getBigIdTipoAsociacionOficios() {
        return BigDecimal.valueOf(idTipoAsociacionOficios);
    }

    public static TipoAsociacionOficiosEnum parse(int idTipoAsociacionOficios) {
        for (TipoAsociacionOficiosEnum tipoAsociacionOficio : TipoAsociacionOficiosEnum.values()) {
            if (tipoAsociacionOficio.getIdTipoAsociacionOficios() == idTipoAsociacionOficios) {
                return tipoAsociacionOficio;
            }
        }
        return null;
    }

}
