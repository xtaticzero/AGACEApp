package mx.gob.sat.siat.feagace.negocio.ordenes.suspension.exception;

import mx.gob.sat.siat.base.excepcion.BusinessException;

/**
 * Exceptions para Suspensiones
 * @author eolf
 */
public class SuspensionException extends BusinessException {
    
    @SuppressWarnings("compatibility:9159571335663696090")
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY = "agace.common";

    public SuspensionException(String situation) {
        super(CATEGORY, situation);
    }

    public SuspensionException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public SuspensionException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public SuspensionException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
