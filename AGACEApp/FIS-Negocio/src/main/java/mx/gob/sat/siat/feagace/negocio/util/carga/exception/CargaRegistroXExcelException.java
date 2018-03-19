package mx.gob.sat.siat.feagace.negocio.util.carga.exception;

import mx.gob.sat.siat.feagace.negocio.exception.AbstractServiceException;

public class CargaRegistroXExcelException extends AbstractServiceException{
    private static final long serialVersionUID = 8014834180871679923L;
    
    private static final String CATEGORY = "agace.negocio.carga.registros.error";
    
    public CargaRegistroXExcelException(String situation) {
        super(CATEGORY, situation);
    }

    public CargaRegistroXExcelException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public CargaRegistroXExcelException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public CargaRegistroXExcelException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
