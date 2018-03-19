package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultaPropuestasOrdenesService {

    List<AgaceOrden> consultarPropuestasOrdenes(final BigDecimal idEmpleado) throws NegocioException;
}
