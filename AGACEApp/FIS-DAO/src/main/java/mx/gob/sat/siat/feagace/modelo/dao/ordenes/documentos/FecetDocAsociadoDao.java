/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;

/**
 * @author sergio.vaca
 *
 */
public interface FecetDocAsociadoDao {

    List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado);

    void insertar(FecetDocAsociado documento);
}
