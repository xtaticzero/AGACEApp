package mx.gob.sat.siat.feagace.negocio.propuestas.asignar;

import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteUpdateException;

public interface AsignarSuplenciaAFirmanteSchedulerService {
    
    void cancelaSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException;
    
    void activaSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException;
    
    void terminarSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException;
}
