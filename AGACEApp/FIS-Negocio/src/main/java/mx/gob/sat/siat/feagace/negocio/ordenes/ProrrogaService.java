package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;


public interface ProrrogaService {
    List<FecetProrrogaOrden> getUltimaProrroga(AgaceOrden orden);

    void actualizarPorEstadoYFechaMaxPro(FecetProrrogaOrden prorroga, AgaceOrden orden);
    
    /**
     * Metodo para la obtencion de las prorrogas de una orden y un estatus especifico
     * @author eolf
     * @param orden
     * @param idEstatus
     * @return
     */
    List<FecetProrrogaOrden> obtenerProrrogasOrdenEstatus(final AgaceOrden orden, final BigDecimal... idEstatus);
    
    /**
     * Metodo para la obtencion de la prorroga mas actual
     * @author eolf
     * @param orden
     * @param idEstatus
     * @return
     */
    FecetProrrogaOrden obtenerProrrogaMasActual(final AgaceOrden orden, final BigDecimal idEstatus);

}
