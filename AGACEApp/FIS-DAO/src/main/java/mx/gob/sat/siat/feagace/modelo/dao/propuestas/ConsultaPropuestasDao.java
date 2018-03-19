package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;

public interface ConsultaPropuestasDao {

    List<ConsultaPropuestas> cargarPropuestasPorArace(EmpleadoDTO empleado, boolean central, Object... args);
}
