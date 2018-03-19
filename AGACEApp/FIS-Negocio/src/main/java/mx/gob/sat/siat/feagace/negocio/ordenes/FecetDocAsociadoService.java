package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;

public interface FecetDocAsociadoService {

    List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado);
    
    void insertarDocAsociado(FecetDocAsociado documento);
}
