package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;

public interface ConsultaPrioridadService {

    List<FececPrioridad> findAll();
    
    List<FececPrioridad> findActivos(BigDecimal idGeneral);
}
