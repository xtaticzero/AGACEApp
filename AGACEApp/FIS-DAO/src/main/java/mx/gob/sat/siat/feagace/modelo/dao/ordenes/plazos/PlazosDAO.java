package mx.gob.sat.siat.feagace.modelo.dao.ordenes.plazos;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;

public interface PlazosDAO {

    void actualizarEstatusNotificable(NotificableNyV notificableNyV);

    void concluirNotificable(NotificableNyV notificableNyV);

    void actualizaNotificableIdNyV(NotificableNyV notificableNyV);

}
