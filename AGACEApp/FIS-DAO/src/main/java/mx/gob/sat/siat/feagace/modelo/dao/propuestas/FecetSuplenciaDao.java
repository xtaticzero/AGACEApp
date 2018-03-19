package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;

/**
 * Interface para la capa DAO para las consultas a la base de datos para la
 * tabla FECET_SUPLENCIA
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public interface FecetSuplenciaDao {

    List<FecetSuplencia> obtenerSuplentesPorSuplente(BigDecimal suplente);

}
