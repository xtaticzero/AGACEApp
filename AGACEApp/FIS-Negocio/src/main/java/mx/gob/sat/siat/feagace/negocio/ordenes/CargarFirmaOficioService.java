package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;


public interface CargarFirmaOficioService {

    void enviarCorreoOficioOrden(FecetOficio oficio);
    
    void enviarCorreoOficio(BigDecimal idOrden, FecetOficio oficio);
    
    void enviarCorreoOficioRechazo(FecetOficio oficio);
    
    void enviarNotificacionOficioRechazo(AgaceOrden orden, FecetOficio oficio, String descripcionRechazo);
        
}
