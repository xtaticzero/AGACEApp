/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;

/**
 * @author sergio.vaca
 *
 */
public interface FecetSuspensionInsumoDao {

    void insert(FecetSuspensionInsumo dto);

    void actualizarFechaFinSuspensionInsumo(Date fechaFinSuspension, BigDecimal idSuspensionInsumo);

    List<FecetSuspensionInsumo> obtenerSuspensionByIdInsumo(BigDecimal idInsumo);

    FecetSuspensionInsumo obtenerUltimaSuspension(BigDecimal idInsumo);

    FecetSuspensionInsumo obtenerSuspensionByIdAndFechaInicio(BigDecimal idInsumo, Date fechaInicio);

    List<FecetSuspensionInsumo> obtenerSuspensionByRegistroInsumo(String idRegistro);

    void updateSuspensionInsumo(FecetSuspensionInsumo suspensionInsumo);
}
