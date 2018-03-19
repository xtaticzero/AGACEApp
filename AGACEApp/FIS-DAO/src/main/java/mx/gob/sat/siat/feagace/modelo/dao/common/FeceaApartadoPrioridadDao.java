package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.util.List;

public interface FeceaApartadoPrioridadDao {

    List<String> getPrioridad(String nombreApartado, Integer idSubmodulo);

}
