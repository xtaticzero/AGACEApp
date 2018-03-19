package mx.gob.sat.siat.feagace.negocio.common.tiempos.exception;

import mx.gob.sat.siat.base.excepcion.BusinessException;

/**
 * Exceptions para Tiempos
 * @author eolf
 */
public class TiemposException extends BusinessException {

    @SuppressWarnings("compatibility:-7676470857254692288")
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY = "agace.common";

    public TiemposException(String situation) {
        super(CATEGORY, situation);
    }

    public TiemposException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public TiemposException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public TiemposException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
