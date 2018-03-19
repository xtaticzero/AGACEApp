package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ValidacionReportesDTO extends BaseModel{

    @SuppressWarnings("compatibility:-344624512245497765")
    private static final long serialVersionUID = 1L;
    
    private String mensaje;
    private Integer errorTotal;

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setErrorTotal(Integer errorTotal) {
        this.errorTotal = errorTotal;
    }

    public Integer getErrorTotal() {
        return errorTotal;
    }
}
