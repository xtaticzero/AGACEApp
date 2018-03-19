package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;

/**
 * Interface para la capa de servicio para obtener los perfiles del firmante
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public interface FececPerfilService {

    List<FececPerfil> obtenerPerfiles();
}
