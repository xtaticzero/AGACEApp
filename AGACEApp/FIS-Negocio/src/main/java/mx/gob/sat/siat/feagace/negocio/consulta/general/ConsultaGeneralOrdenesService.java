package mx.gob.sat.siat.feagace.negocio.consulta.general;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;

public interface ConsultaGeneralOrdenesService {
    
    ConsultaEjecutivaOrdenesBO getAccesoSuperusuarioAConsultaOrdenes(EmpleadoDTO empleado) throws ConsultaEjecutivaOrdenesServiceException;

}
