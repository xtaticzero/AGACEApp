package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.ReimpresionDocumentosService;

import org.springframework.stereotype.Service;

/**
 * @author 874347
 * @version 1.0
 * @since 2014-05-13 Clase del Servicio donde manda a llamar el metodo del dao
 *
 */
@Service("reimpresionDocumentosService")
public class ReimpresionDocumentosServiceImpl extends OrdenesServiceBase implements ReimpresionDocumentosService {

    private static final long serialVersionUID = -719000739453447116L;

    /**
     *
     * @return regresa el objeto del dao con toda la lista de los datos que se
     * encontraron dentro de la base de datos
     */
    @Override
    public List<AgaceOrden> getOrdenesReimprimirDocumentacion() {

        return getAgaceOrdenDao().getOrdenesReimprimirDocumentacion();

    }
}
