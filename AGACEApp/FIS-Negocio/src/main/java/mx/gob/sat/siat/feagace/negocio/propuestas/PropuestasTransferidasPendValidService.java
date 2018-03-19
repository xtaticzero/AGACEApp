package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;


public interface PropuestasTransferidasPendValidService {
    
    List<FecetTransferencia> buscarTransferenciasPorIdPropuesta(BigDecimal idPropuesta);
    
    List<FecetTransferencia> buscarTransferenciasPorIdTransferencia(BigDecimal idTransferencia);
    
    void actualizaEstatusPropuesta(BigDecimal idPropuesta);
    
    List<ProgramadorPropuestaAsignadaDTO> getProgramadorTransferencia(BigDecimal idArace);
    
    void actualizarIdProgramador(final BigDecimal idPropuesta, final BigDecimal idProgramador);
    
    void actualizaEstatusPropuestaRechazoT(BigDecimal idPropuesta, BigDecimal estatus);
    
    void rechazarPropuesta(FecetPropuesta propuesta, FecetRechazoPropuesta fecetRechazo, List<FecetRechazoPropuesta> listaRechazo);
    
    List<FececMotivo> buscarMotivos(String tipoMotivo);
    
    void desactivaTransferencia(Long idTransferencia);
    
    void updateIdPropuestaNueva(BigDecimal idPropuestaNueva, BigDecimal idTransferencia);
    
    void updateFechaMotivoRechazo(Date fechaRechazo, BigDecimal idMotivo, BigDecimal idTransferencia);
    
    BigDecimal obtenIdProgramadorPropuesta(BigDecimal idPropuesta);
    
    BigDecimal buscarTipoEmpleadoPropuesta(BigDecimal idPropuesta);
    
    List<AgaceOrden> obtenerOrden(BigDecimal idPropuesta);
}
