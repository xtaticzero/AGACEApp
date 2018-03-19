package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececMotivoSuplencia extends BaseModel implements Serializable {

    @SuppressWarnings("compatibility:-3414370298715998338")
    private static final long serialVersionUID = 1L;
    private BigDecimal idMotivo;
    private String descripcion;


    public void setIdMotivo(BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
