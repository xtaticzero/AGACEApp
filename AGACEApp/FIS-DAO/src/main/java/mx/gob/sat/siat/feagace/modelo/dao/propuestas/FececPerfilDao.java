package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;

/**
 * Interface para capa de acceso a datos para la tabla FECEC_PERFIL
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public interface FececPerfilDao {

    List<FececPerfil> obtenerPerfiles();

}
