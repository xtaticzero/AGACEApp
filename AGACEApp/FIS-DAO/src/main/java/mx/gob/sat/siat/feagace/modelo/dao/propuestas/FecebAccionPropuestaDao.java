/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

/**
 * @author sergio.vaca
 *
 */
public interface FecebAccionPropuestaDao {

    void insert(FecebAccionPropuesta accionPropuesta);

    List<FecebAccionPropuesta> obtenerAccionSinAsociarTabla(BigDecimal idPropuesta);

    List<FecebAccionPropuesta> obtenerAccionAsociarRechazo(BigDecimal idPropuesta, AccionesFuncionarioEnum... accionesFuncionarioEnum);

    List<FecebAccionPropuesta> obtenerAccionAsociarCancelar(BigDecimal idPropuesta, AccionesFuncionarioEnum... accionesFuncionarioEnum);

    List<FecebAccionPropuesta> obtenerAccionAsociarRetroalimentar(BigDecimal idPropuesta, AccionesFuncionarioEnum... accionesFuncionarioEnum);

    List<FecebAccionPropuesta> obtenerAccionAsociarTransferir(BigDecimal idPropuesta, AccionesFuncionarioEnum... accionesFuncionarioEnum);

    List<FecebAccionPropuesta> obtenerAccionNoAprobacion(BigDecimal idPropuesta);
}
