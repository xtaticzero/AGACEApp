/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;

/**
 * @author sergio.vaca
 *
 */
public interface FecetSuspensionInsumoService {
    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_SUSPENDIDO)
    String insertReasignar(FecetInsumo insumo);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_SUSPENDIDO_REASIGNACION)
    String insertRetroalimentar(FecetInsumo insumo);
    
    void actualizarFechaFinSuspensionInsumo(Date fechaFinSuspension, BigDecimal idSuspensionInsumo);

    List<FecetSuspensionInsumo> obtenerSuspensionByIdInsumo(BigDecimal idInsumo);

    FecetSuspensionInsumo obtenerUltimaSuspension(BigDecimal idInsumo);
}
