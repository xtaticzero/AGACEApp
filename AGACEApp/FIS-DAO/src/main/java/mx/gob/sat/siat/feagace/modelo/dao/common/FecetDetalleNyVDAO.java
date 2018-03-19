package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;

public interface FecetDetalleNyVDAO {

    FecetDetalleNyV buscaDetalleNyVPorId(FecetDetalleNyV detalleNyV);

    FecetDetalleNyV guardaFolioNyV(FecetDetalleNyV detalleNyV);

    void guardaDetalleNyV(FecetDetalleNyV detalleNyV);

    void guardaDetalleNyVSurteEfectos(FecetDetalleNyV detalleNyV);

    List<FecetDetalleNyV> obtenerDetalleByIdPadre(FecetDetalleNyV detalleNyV);

    void actualizaNotificableRuta(NotificableNyV notificableNyV, String ruta);

    void actualizaRuta(FecetDetalleNyV detalleNyV);

    void actualizaDatosNyv(FecetDetalleNyV detalleNyV);

    List<FecetDetalleNyV> buscaAllDetalleNyV();
}
