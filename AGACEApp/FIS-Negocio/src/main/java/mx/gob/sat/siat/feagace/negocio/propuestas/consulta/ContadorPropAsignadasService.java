package mx.gob.sat.siat.feagace.negocio.propuestas.consulta;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;

public interface ContadorPropAsignadasService {

    List<ContadorPropuestasAsignadas> getResumenPropuestasAsignadas(String rfcAuditor);
}
