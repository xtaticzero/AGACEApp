package mx.gob.sat.siat.feagace.negocio.consulta.general.impl;

import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesRule;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.ConsultaEjecutivaOrdenesService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;

@Service("consultaGeneralOrdenesService")
public class ConsultaGeneralOrdenesServiceImpl extends ConsultaGeneralService implements ConsultaGeneralOrdenesService {

    private static final long serialVersionUID = 1L;

    @Override
    public ConsultaEjecutivaOrdenesBO getAccesoSuperusuarioAConsultaOrdenes(EmpleadoDTO empleado) throws ConsultaEjecutivaOrdenesServiceException {
        ConsultaEjecutivaOrdenesBO consultaEjecutivaOrdenesBO;
        try {
            
            EmpleadoDTO empleadoDTO = getEmpleadoCompleto(empleado.getRfc());
            
            consultaEjecutivaOrdenesBO = new ConsultaEjecutivaOrdenesBO();
            consultaEjecutivaOrdenesBO.setEmpleadoConsulta(empleadoDTO);
            consultaEjecutivaOrdenesBO.setLstUnidadesAdministrativasDesahogo(getUnidadesAdministrativas(empleadoDTO));
            consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.ESTATUS_DISPONIBLES_CONSULTA_ORDENES);
            consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
            consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.METODOS_DISPONIBLES_CONSULTA_ORDENES);
            consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
            consultaEjecutivaOrdenesBO.setRule(ConsultaEjecutivaOrdenesRule.SEMAFOROS_DISPONIBLES_CONSULTA_ORDENES);
            consultaEjecutivaOrdenesBO.getRule().process(consultaEjecutivaOrdenesBO);
            return consultaEjecutivaOrdenesBO;
        } catch (EmpleadoServiceException ex) {
            throw new ConsultaEjecutivaOrdenesServiceException(ConsultaEjecutivaOrdenesService.MSG_ERROR_REGISTRO_EMPLEADO, ex);
        }
    }

}
