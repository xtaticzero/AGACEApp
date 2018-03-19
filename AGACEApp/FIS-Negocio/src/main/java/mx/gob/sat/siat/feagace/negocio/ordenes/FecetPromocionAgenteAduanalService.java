package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;

/**
 * @author sergio.vaca
 *
 */
public interface FecetPromocionAgenteAduanalService {
    List<FecetPromocionAgenteAduanal> obtenerRegistrosByOrdenAsociado(AgaceOrden orden, ColaboradorVO colaborador);
}
