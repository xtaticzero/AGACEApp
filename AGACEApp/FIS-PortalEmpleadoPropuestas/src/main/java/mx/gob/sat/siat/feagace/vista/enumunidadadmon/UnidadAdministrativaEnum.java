package mx.gob.sat.siat.feagace.vista.enumunidadadmon;

import java.math.BigDecimal;

public enum UnidadAdministrativaEnum {

    ACAOCE("ACAOCE", BigDecimal.valueOf(20L)),
    ACOECE("ACOECE",  BigDecimal.valueOf(19L)),
    ADACE_PACIFICO_NORTE("ADACE Pacífico Norte",  BigDecimal.valueOf(41L)),
    ADACE_NORTE_CENTRO("ADACE Norte Centro",  BigDecimal.valueOf(51L)),
    ADACE_NOROESTE("ADACE Noroeste",  BigDecimal.valueOf(31L)),
    ADACE_OCCIDENTE("ADACE Occidente",  BigDecimal.valueOf(67L)),
    ADACE_CENTRO("ADACE Centro",  BigDecimal.valueOf(12L)),
    ADACE_SUR("ADACE Sur",  BigDecimal.valueOf(25L));


    private final String descUnidad;
    private final BigDecimal idUnidad;


    UnidadAdministrativaEnum(String descUnidad, BigDecimal idUnidad) {
        this.idUnidad = idUnidad;
        this.descUnidad = descUnidad;
    }


    public BigDecimal getIdUnidad() {
        return idUnidad;
    }

    public String getDescUnidad() {
        return descUnidad;
    }

    public static UnidadAdministrativaEnum parse(int idUnidadAdministrativa) {
        for (UnidadAdministrativaEnum unidadAdministrativa : UnidadAdministrativaEnum.values()) {
            if (unidadAdministrativa.getIdUnidad().intValue() == idUnidadAdministrativa) {
                return unidadAdministrativa;
            }
        }
        return null;
    }
}

