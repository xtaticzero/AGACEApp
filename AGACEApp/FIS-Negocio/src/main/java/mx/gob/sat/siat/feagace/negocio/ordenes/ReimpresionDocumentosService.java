package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

/**
 * @author 874347
 * @version 1.0
 * @since 2014-05-13 Interface con los metodos declarados para su utilización en
 * la implementación del servicio.
 */
public interface ReimpresionDocumentosService {

    List<AgaceOrden> getOrdenesReimprimirDocumentacion();
}
