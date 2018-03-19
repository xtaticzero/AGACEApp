package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

public interface FecebAccionPropuestaService {

    List<FecebAccionPropuesta> obtenerAccionesPropuestaSinTabla(BigDecimal idPropuesta);
     
    List<FecebAccionPropuesta> obtenerAccionesPropuestaRechazadas(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum);
    
    List<FecebAccionPropuesta> obtenerAccionesPropuestaCancelada(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum);
    
    List<FecebAccionPropuesta> obtenerAccionesPropuestaRetroalimentar(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum);
    
    List<FecebAccionPropuesta> obtenerAccionesPropuestaTransferir(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum);
    
}
