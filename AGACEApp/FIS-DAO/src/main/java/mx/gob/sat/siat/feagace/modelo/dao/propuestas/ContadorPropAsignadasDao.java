package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;

public interface ContadorPropAsignadasDao {

    List<ContadorPropuestasAsignadas> getResumenPropuestasAsignadas(String rfcAuditor);
}
