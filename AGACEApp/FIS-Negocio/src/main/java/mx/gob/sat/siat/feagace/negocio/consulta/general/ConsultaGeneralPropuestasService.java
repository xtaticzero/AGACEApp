package mx.gob.sat.siat.feagace.negocio.consulta.general;

import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;

public interface ConsultaGeneralPropuestasService {

    List<EmpleadoDTO> getEmpleadoSolicitado(Integer unidadAministrativa, Integer perfilUsuario,
            ClvSubModulosAgace claveModulo);

    void consultaPropuestasXSubordinado(ConsultaEjecutivaPropuestasBO consultaPropuestasBO,
            FiltroPropuestas filtroBusqueda, AgrupadorEstatusPropuestasEnum grupoEstatus, Map<EmpleadoDTO, Integer> propuestasEmpleado);
    
    EmpleadoDTO buscarEmpleado(String rfc);
    
    boolean validaPeriflSubadminitrador(EmpleadoDTO empleado);
}
