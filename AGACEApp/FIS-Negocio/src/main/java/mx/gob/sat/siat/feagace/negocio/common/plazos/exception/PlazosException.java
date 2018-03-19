package mx.gob.sat.siat.feagace.negocio.common.plazos.exception;

import mx.gob.sat.siat.base.excepcion.BusinessException;

/**
 * Exceptions para Plazos
 * @author eolf
 */
public class PlazosException extends BusinessException {

    @SuppressWarnings("compatibility:1544998904304005355")
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY = "agace.common";

    public PlazosException(String situation) {
        super(CATEGORY, situation);
    }

    public PlazosException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public PlazosException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public PlazosException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
