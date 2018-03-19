package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

/**
 * Interface para la capa de servicio para obtener los datos de la suplencia
 * para los firmantes suplentes
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public interface FecetSuplenciaService {

    List<FecetSuplencia> obtenerSuplentesPorSuplente(BigDecimal suplente);
    
    EmpleadoDTO getEmpleadoFirmante(final String rfc) throws NegocioException;

    List<FecetSuplencia> obtieneRFCSuplente(List<FecetSuplencia> suplente);
    
}
