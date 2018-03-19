package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.service;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;

public interface GenerarOficioService {
    
    List<TiposOficiosOrdenesEnum> obtenerOficiosGenerar(AgaceOrden orden);
    
    List<FecetTipoOficio> obtenerOficiosGenerarPorIdAgrupacionEstatus(int idAgrupacion, BigDecimal idOrden);
    
    List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden);
    
    boolean getOficiosPorOrdenEnProcesoDeFirma( final BigDecimal idOrden);
    
    boolean validarVisualizarIntegraExpediente(AgaceOrden orden);
    
    boolean validarVisualizarReactivarPlazo(AgaceOrden orden);
    
    boolean validarVisualizarReactivarPlazoAcuerdo(AgaceOrden orden);
    
    boolean validarVisualizacionTabAvisoContribuyenteOpcional(AgaceOrden orden, FecetOficio oficio);

}
