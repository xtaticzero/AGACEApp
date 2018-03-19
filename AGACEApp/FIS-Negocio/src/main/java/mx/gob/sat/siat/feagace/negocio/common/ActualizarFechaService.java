package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;

public interface ActualizarFechaService {

    AgaceOrden getOrdenByNumeroOrden(String numeroOrden);

    FecetOficio getOficioByIdOficio(BigDecimal idOficio);

    FecetSuspensionDTO getAcByIdOrden(BigDecimal idOrden);

    FecetSuspensionDTO getSuspensionByIdOficio(FecetOficio oficio);

    void actualizarIntegracionExpediente(AgaceOrden orden, Date fechaExpediente);

    void actualizarFechaSurteEfectos(FecetDetalleNyV fecetDetalleNyV);

    void actualizarFechaFirma(FecetOficio oficio);

    void actualizarSuspension(FecetSuspensionDTO suspension);

    FecetSuspensionInsumo getSuspensionInsumoByIdRegistro(String idRegistro);

    FecetInsumo getInsumoByIdRegistro(String idRegistro);

    void actualizarSuspensionInsumo(FecetSuspensionInsumo insumo);

    void actualizarPlazoInsumo(FecetInsumo insumo);

}
