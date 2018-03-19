package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ProgramadorPropuestaAsignadaDTO extends BaseModel{

    @SuppressWarnings("compatibility:-2898667132982688952")
    private static final long serialVersionUID = 1996512386907882255L;
    
    private BigDecimal idEmpleado;
    private int totalPropuestas;

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setTotalPropuestas(int totalPropuestas) {
        this.totalPropuestas = totalPropuestas;
    }

    public int getTotalPropuestas() {
        return totalPropuestas;
    }
}
