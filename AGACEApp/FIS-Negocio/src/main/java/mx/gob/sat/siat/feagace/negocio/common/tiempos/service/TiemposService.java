package mx.gob.sat.siat.feagace.negocio.common.tiempos.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;


/**
 * Interface para funcionalidad de servico de Tiempos
 * @author eolf
 */
public interface TiemposService {
    
    /**
     * Metodo para la obtencion del tiempo de notifiicacion de NyV
     * @author eolf
     * @return
     */
    int obtenerTiempoNotificacionNyV();

    /**
     * Metodo para la obtencion de los tiempos del catalogo de base de datos mediante un idOrden, clave del plazo y clave del tiempo
     * @author eolf
     * @param idMetodo
     * @param cve
     * @return
     */
    TiempoDTO obtenerTiempoPlazo(final BigDecimal idMetodo, final TiemposClaveEnum cve);

  
    
  
    
    /**
     * Metodo para la obtencion de los tiempos del catalogo de base de datos mediante el id metodo, el id del tipo oficio, la clave del plazo y la clave del tiempo
     * @author eolf
     * @param idMetodo
     * @param idTipoOficio
     * @param cve
     * @return
     */
    TiempoDTO obtenerTiempoPlazoOficio(final BigDecimal idMetodo, final BigDecimal idTipoOficio, final TiemposClaveEnum cve);

   
    
    /**
     * Metodo para sumar tiempo a una fecha
     * @author eolf
     * @param plazo
     * @param fecha
     * @return
     */
    Date sumarTiempo(final TiempoDTO plazo, final Date fecha);

    /**
     * Metodo para restar tiempo a una fecha
     * @author eolf
     * @param plazo
     * @param fecha
     * @return
     */
    Date restarTiempo(final TiempoDTO plazo, final Date fecha);

   
    
   
    
    List<TiempoDTO> obtenerValoresDeSemaforo(int idMetodo);
    
    Date obtenerDiasHabiles(Date inicio, int dias);
    
    Date obtenerDiasNaturales(Date inicio, int dias);    
    
    int obtenerDiasRestantesHabiles(Date fechaInicio, Date fechaFin);
    
    int obtenerDiasRestantesHabilesSinUltimoDia(Date fechaInicio, Date fechaFin);
    
    Date restarDiasHabiles(Date fechaInicio, int diasRestar);
    
    int obtenerDiasRestantesNaturales(Date fechaInicio, Date fechaFin);
}
