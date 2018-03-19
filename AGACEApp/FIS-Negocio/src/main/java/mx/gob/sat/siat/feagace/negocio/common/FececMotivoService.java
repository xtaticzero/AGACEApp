package mx.gob.sat.siat.feagace.negocio.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;

/**
 *
 * @author Luis rodriguez
 * @version 1.0
 */
public interface FececMotivoService {

    List<FececMotivo> findWhereTipoMotivoEquals();
}
