package mx.gob.sat.siat.feagace.health.dto;

import mx.gob.sat.siat.base.dto.BaseDataTransferObject;

public class HealthDTO extends BaseDataTransferObject {

    private static final long serialVersionUID = 144733271104570302L;

    private Boolean servicioOk;
    private String respuesta;

    public HealthDTO() {
        super();
    }

    public HealthDTO(boolean servicioOk, String respuesta) {
        this.servicioOk = servicioOk;
        this.respuesta = respuesta;
    }

    public Boolean getServicioOk() {
        return servicioOk;
    }

    public void setServicioOk(Boolean servicioOk) {
        this.servicioOk = servicioOk;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
