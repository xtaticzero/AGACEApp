package mx.gob.sat.siat.feagace.negocio.common;

import java.util.Date;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.ActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.exception.NyVClientException;

public interface ConsultaRegistraNyVService {

    boolean filtrarNotificablePorFecha(NotificableNyV orden);

    FecetDetalleNyV registrarActoAdministrativo(NotificableNyV oficio, Date fechaDiligencia, FececActosAdm actoBD) throws NegocioException;

    Map<Long, ActoAdministrativo> obtenerListaActoAdministrativo(String claveUnidadAdministrativa);

    void asignarFechaNotificable(NotificableNyV notificable);

    ActoAdministrativo obtenerActoAdministrativo(String claveUnidadAdmin, Long idActoNyv);

    void asignarFechaNyV();
    
    ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) throws NyVClientException;
    
    void registarNumeroReferenciaOrigen(NotificableNyV notificable, RegistroActoAdministrativo registroVO);
}
