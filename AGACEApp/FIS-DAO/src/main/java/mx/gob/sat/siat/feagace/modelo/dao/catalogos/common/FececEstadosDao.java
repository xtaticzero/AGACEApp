package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;

public interface FececEstadosDao {

    List<FececEstados> obtenerEstadosPorIdEmpleado(BigDecimal idUnidad);

    List<FececEstados> obtenerEstados();

}
