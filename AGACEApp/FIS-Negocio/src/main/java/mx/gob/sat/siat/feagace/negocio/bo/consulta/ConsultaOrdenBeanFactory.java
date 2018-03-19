package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

public class ConsultaOrdenBeanFactory extends ConsultaEjecutivaOrdenesBO {

    private static final long serialVersionUID = 5158572625235212884L;
    
    public ConsultaOrdenBeanFactory(){
    }
    
    public static ConsultaEjecutivaOrdenesBO getBeanConsultaBO(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado()!=null && !empleado.getDetalleEmpleado().isEmpty()) {
            return ConsultaEjecutivaOrdenesBO.getInstansOfBO(empleado);
        }
        return null;
    }

}
