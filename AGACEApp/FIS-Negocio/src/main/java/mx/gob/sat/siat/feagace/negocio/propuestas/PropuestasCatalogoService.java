package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;

public interface PropuestasCatalogoService {
    
    List<FececMetodo> obtenListaMetodos();
    List<FececTipoPropuesta> obtenListaTipoPropuestas();
    List<FececRevision> obtenListaTipoRevision();
    List<FececSubprograma> obtenListaSubprograma();
    
}
