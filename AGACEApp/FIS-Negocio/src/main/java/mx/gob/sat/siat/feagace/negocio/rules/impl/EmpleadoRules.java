package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

@Component
public class EmpleadoRules extends BaseBusinessRules {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;
    
    public List<FececEmpleado> validarExistenciaEmpleado(
            final List<FececEmpleado> empleados)
            throws NoExisteEmpleadoException {
        if (empleados!= null && empleados.size() > 0) {
            return empleados;
        } else {
            throw new NoExisteEmpleadoException("empleado.no.existente");
        }
    }

}
