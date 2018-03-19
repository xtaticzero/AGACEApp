package mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;

public interface SuspensionService {

    boolean iniciaSuspensionOrdenXAcuerdo(AgaceOrden orden, String numeroAcuerdo, final Date fechaInicio);

    boolean finalizarSuspensionOrdenXAcuerdo(AgaceOrden orden, String numeroAcuerdo, final Date fechaFinalizacion);

    void iniciaSuspensionOrden(AgaceOrden orden, final Date fechaInicio, final Date fechaFin);

    void iniciaSuspensionOrden(AgaceOrden orden, FecetOficio oficio, final Date fechaInicio, final Date fechaFin);

    void iniciarSuspensionNotificable(AgaceOrden orden, NotificableNyV notificable, final Date fechaInicio, final BigDecimal idOficio);

    boolean validarReactivacionPlazo(AgaceOrden orden);

    void finalizarSuspensionOrden(AgaceOrden orden, final Date fechaFinalizacion);

    List<FecetSuspensionDTO> buscarSuspensionIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto);

    List<FecetSuspensionDTO> buscarAllSuspensionIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto);

    boolean estaSuspendida(final BigDecimal idOrden);

    boolean estaSuspendidaPorPruebasPericiales(BigDecimal idOrden);

    List<FecetSuspensionDTO> buscarSuspensionesOrden(final BigDecimal idOrden);

    List<FecetSuspensionDTO> buscarSuspensionesOrdenReactivacion(final BigDecimal idOrden);

    boolean estaSuspendidaReactivacion(BigDecimal idOrden);

    boolean existeAcuerdoConclusivo(String numeroAcuerdoConclusivo);
    
    FecetSuspensionDTO buscarSuspensionAc(BigDecimal idOrden);

}
