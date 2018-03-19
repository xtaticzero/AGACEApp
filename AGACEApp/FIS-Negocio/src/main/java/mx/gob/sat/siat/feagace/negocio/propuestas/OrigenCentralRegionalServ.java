package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface OrigenCentralRegionalServ {

    List<PropuestaOrigenCentralRegDTO> getPropuestasOrigenCentralRegionales(BigDecimal bigDecimal, BigDecimal idAraceEmpleado) throws NegocioException;

    FecetContribuyente getContribuyente(BigDecimal rfc) throws NegocioException;

    FecetPropuesta obtienePropuestaByFolio(String folio) throws NegocioException;

    FecetPropuesta obtienePropuestaByidPropuesta(BigDecimal idPropuesta) throws NegocioException;

    void rechazarPropuesta(FecetPropuesta propuesta, FecetRechazoPropuesta rechazoPropuesta, List<FecetRechazoPropuesta> listaRechazo) throws NegocioException;
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.COMPLEMENTAR_PROPUESTA_PROGRAMACION_REGIONAL)
    BigDecimal actualizaPropuestaComplementar(FecetPropuesta propuesta) throws NegocioException;
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ENVIAR_COMITE_PROPUESTA_PROGRAMACION_REGIONAL)
    BigDecimal actualizaPropuestaRegistrar(FecetPropuesta propuesta) throws NegocioException;
        
    BigDecimal actualizaPropuesta(FecetPropuesta propuesta) throws NegocioException;

    List<FecetImpuesto> getImpuestosByPropuesta(BigDecimal idPropuesta) throws NegocioException;

    List<FecetRechazoPropuesta> getHistoricoRechazo(BigDecimal idPropuesta);

    List<FecetRechazoPropuesta> getArchivosRechazoByIdRechazo(BigDecimal idRechazo);

    void enviarCorreoConfirmacion(FecetPropuesta propuesta, String rfcProgramador);

}
