package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;

public interface FecetPropuestaConsultaGeneralDao {

    List<FecetPropuesta> contarEstatusGrupo(AgrupadorEstatusPropuestasEnum grupo, FiltroPropuestas filtroDao);
}
