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
public final class ConsultaInsumoBeanFactory extends ConsultaEjecutivaInsumosBO {
    private static final long serialVersionUID = 5626559429866578832L;

    private ConsultaInsumoBeanFactory() {
    }

    public static ConsultaEjecutivaInsumosBO getBeanConsultaBO(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado()!=null && !empleado.getDetalleEmpleado().isEmpty()) {
            return ConsultaEjecutivaInsumosBO.getInstansOfBO(empleado);
        }
        return null;
    }
}
