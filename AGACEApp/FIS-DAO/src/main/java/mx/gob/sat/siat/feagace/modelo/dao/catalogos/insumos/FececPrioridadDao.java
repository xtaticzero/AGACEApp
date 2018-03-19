package mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;

public interface FececPrioridadDao {

    List<FececPrioridad> findAll();

    List<FececPrioridad> findActivos(BigDecimal idGeneral);
}
