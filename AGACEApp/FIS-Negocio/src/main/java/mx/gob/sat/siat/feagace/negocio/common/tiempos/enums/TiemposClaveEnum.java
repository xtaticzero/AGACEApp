package mx.gob.sat.siat.feagace.negocio.common.tiempos.enums;

import mx.gob.sat.siat.feagace.negocio.common.tiempos.constantes.TipoTiemposClaveConstants;

/**
 * Enum con las distintas claves para la obtencion del Tiempo
 * 
 * @author eolf
 */
public enum TiemposClaveEnum {
    CDO_PLA("CDO", TipoTiemposClaveConstants.PLAZO),
    PTO_PLA("PTO", TipoTiemposClaveConstants.PLAZO),
    PIO_PLA("PIO",TipoTiemposClaveConstants.PLAZO),
    CDO_PRO("CDO", TipoTiemposClaveConstants.PRORROGA),
    GN_MET("GN", "MET"),
    GN_RES("GN", "RES"),
    CDOF_PLA("CDOF", TipoTiemposClaveConstants.PLAZO),
    CDOF_PRO("CDOF", TipoTiemposClaveConstants.PRORROGA),
    NYV_NOT("NYV", "NOT"),
    AGACE_NOT("AGACE", "NOT"),
    COM_PLA("COM", TipoTiemposClaveConstants.PLAZO),
    EXT("EXT", TipoTiemposClaveConstants.PLAZO),
    COM_GEN("COM_GEN", TipoTiemposClaveConstants.PLAZO),
    SDOF_PLA("SDOF", TipoTiemposClaveConstants.PLAZO),
    SUDOF_PLA("SUDOF", TipoTiemposClaveConstants.PLAZO),
    SIDOF_PLA("SIDOF", TipoTiemposClaveConstants.PLAZO),
    SEMA_PLA("SEMA", TipoTiemposClaveConstants.PLAZO),
    SEMN_PLA("SEMN", TipoTiemposClaveConstants.PLAZO),
    SEMR_PLA("SEMR", TipoTiemposClaveConstants.PLAZO),
    SEMINA_PLA("SEMINA", TipoTiemposClaveConstants.INSUMO),
    SEMINN_PLA("SEMINN", TipoTiemposClaveConstants.INSUMO),
    SEMINR_PLA("SEMINR", TipoTiemposClaveConstants.INSUMO),
    PAINS_PLA("PAINS", TipoTiemposClaveConstants.INSUMO),
    PRINS_PLA("PRINS", TipoTiemposClaveConstants.INSUMO);

    private final String clave;
    private final String claveTipoTiempo;

    private TiemposClaveEnum(String clave, String claveTipoTiempo) {
        this.clave = clave;
        this.claveTipoTiempo = claveTipoTiempo;
    }

    /**
     * Metodo para obtener la clave
     * 
     * @author eolf
     * @return
     */
    public String getClave() {
        return this.clave;
    }

    /**
     * Metodo para obtener la clave del tipo tiempo
     * 
     * @author eolf
     * @return
     */
    public String getClaveTipoTiempo() {
        return claveTipoTiempo;
    }
}
