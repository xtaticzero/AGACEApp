package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

public interface HistoricoPropuestaService {
    
   
    List<FecetDocExpediente> buscarDoctosRechazoPorIdRechazo(BigDecimal idRechazo);
    List<FecetDocExpediente> obtenerDocumentoByIdRetro(BigDecimal idRetro);
    List<FecetDocExpediente> obtenerDocumentoByIdTransferencia(BigDecimal idTransferencia);
    List<FecetDocExpediente> obtenerDocumentoByIdCancelacion(BigDecimal idCancelacion);
    
    List<FecetOficio> oficiosHistorial(BigDecimal idOrden);
    List<FecetDocOrden> obtenerDocsOrdenHistorial(BigDecimal idPropuesta);
}
