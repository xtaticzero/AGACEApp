/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public interface OficiosService<T> {    
    T consultarPorId(BigDecimal id);
    List<?> getPruebasAlegatosOficio(BigDecimal id);
    Map<String, List<FecetProrrogaOficio>> getProrrogasOficio(BigDecimal id);
    Map<String, List<FecetOficio>> getOficios(BigDecimal id);    
    List<FecetOficioAnexos> getOficiosAnexos(BigDecimal id);  
    List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficio(BigDecimal id);  
}
