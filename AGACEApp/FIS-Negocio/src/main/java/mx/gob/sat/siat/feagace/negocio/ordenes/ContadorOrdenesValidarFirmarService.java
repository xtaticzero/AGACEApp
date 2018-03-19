package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;


public interface ContadorOrdenesValidarFirmarService {
    
    /**
     * Regresa una lista con los totales de las ordenes por validar y firmar, de acuerdo al metodo asignado
     * @return 
     */
    List<ContadorOrdenes> ordenesPorValidarFirmar( BigDecimal idEmpleado, String rfcFirmante );
    
    ResumenPropuestasFirmante obtenerResumenPropuestasFirmante(String rfcFirmante);
    
}
