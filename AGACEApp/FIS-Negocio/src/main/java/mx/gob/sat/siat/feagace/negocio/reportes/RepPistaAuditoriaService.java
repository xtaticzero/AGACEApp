package mx.gob.sat.siat.feagace.negocio.reportes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;

public interface RepPistaAuditoriaService {
    
    /**
     * Servicio que proporciona informacion de pistas de audiotoria de las transacciones de los Empleados
     * @param pistaInterDTO
     * @return
     */
    List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaInterDTO);
    
    /**
     * Servicio que proporciona informacion de pistas de audiotoria de las transacciones de los contribuyentes 
     * @param pistaExterDTO
     * @return
     */
    List<ReportePistaAuditoriaExternaDTO> buscaPistaAuditoriaExterna(ReportePistaAuditoriaExternaDTO pistaExterDTO);
    
    List<EmpleadoDTO> obtenerTodosEmpleados();
    
    List<ReportePistaAuditoriaInternaDTO> buscaPistaAuditoriaInterna(ReportePistaAuditoriaInternaDTO pistaInterDTO, List<EmpleadoDTO> lstEmpleados);
}
