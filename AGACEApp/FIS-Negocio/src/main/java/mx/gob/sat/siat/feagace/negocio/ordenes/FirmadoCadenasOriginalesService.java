package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenFirmada;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;

public interface FirmadoCadenasOriginalesService {

    List<OrdenPorFirmar> getOrdenesPorFirmarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo, BigDecimal idEmpleado);

    BigDecimal enviarRechazoOrdenNew(final AgaceOrden rechazoSeleccionado,
            final String rechazoDescripcion);

    void initializer();

    int findOrdenRechazoEstatus(final AgaceOrden rechazoSeleccionado);

    List<OrdenFirmada> obtenerOrdenesFirmadas(BigDecimal idEmpleado, BigDecimal idMetodo);

}
