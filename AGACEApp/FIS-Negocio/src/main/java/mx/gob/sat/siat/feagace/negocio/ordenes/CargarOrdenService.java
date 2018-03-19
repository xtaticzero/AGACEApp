package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CargarOrdenService {

    List<FececMetodo> getMetodos();

    boolean subirArchivoServidor(final AgaceOrden orden) throws NegocioException;

    void guardarOrdenesLista(List<AgaceOrden> listaOrdenes);

    BigDecimal guardarOrden(final AgaceOrden orden);

    List<AgaceOrden> getListaAuditor(final String rfcAuditor);

    void borrarOrdenAuditor(final AgaceOrden orden);

    void enviarOrdenes(final List<AgaceOrden> lista);

    void acutalizarOrden(AgaceOrdenPk pk, final AgaceOrden dto);

    AgaceOrden getOrdenById(BigDecimal idOrden);
    

    /**
     * Metodo getClaveOrden
     *
     * @param idArace
     * @param cambioMetodo
     * @return String
     */
    String getClaveOrden(final BigDecimal idArace, final FecetCambioMetodo cambioMetodo);
}
