package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;

public interface FececPrioridadDao {

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria ''.
     */
    List<FececPrioridad> findAll();

    List<FececPrioridad> findActivos();

    int insert(FececPrioridad prioridad);

    List<FececPrioridad> findPrioridadByEquals(String prioridad);
}
