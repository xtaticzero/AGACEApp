package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececEstatusSuplencia  extends BaseModel implements Serializable {


    @SuppressWarnings("compatibility:-1353773660991806176")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idEstatusSuplencia;
    private String descripcion;


    public void setIdEstatusSuplencia(BigDecimal idEstatusSuplencia) {
        this.idEstatusSuplencia = idEstatusSuplencia;
    }

    public BigDecimal getIdEstatusSuplencia() {
        return idEstatusSuplencia;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
