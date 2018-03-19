package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficio;

public interface FecetAdminOficioDao {

    List<FecetAdminOficio> getAdminOficiosActivos();
}
