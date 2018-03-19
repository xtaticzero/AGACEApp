/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class ConsultaPropuestaBeanFactory extends ConsultaEjecutivaPropuestasBO {

    private static final long serialVersionUID = -5126767689245260300L;
    
    private ConsultaPropuestaBeanFactory() {
    }

    public static ConsultaEjecutivaPropuestasBO getBeanConsultaBO(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado()!=null && !empleado.getDetalleEmpleado().isEmpty()) {
            return ConsultaEjecutivaPropuestasBO.getInstansOfBO(empleado);
        }
        return null;
    }

}
