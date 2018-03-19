package mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.dao;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;

public interface TiemposDAO {

    /**
     * Metodo para la obtencion del plazo mediante el id metodo, la clave del
     * plazo y la clave del tipo de tiempo
     *
     * @author eolf
     * @param idMetodo
     * @param cvePlazo
     * @param cveTipoTiempo
     * @return
     */
    TiempoDTO obtenerTiempoPlazo(final BigDecimal idMetodo, final String cvePlazo, final String cveTipoTiempo);

    /**
     * Metodo para la obtencion del plazo mediante el id metodo, la clave del
     * plazo y clave del tipo tiempo
     *
     * @author eolf
     * @param cvePlazo
     * @param cveTipoTiempo
     * @return
     */
    List<TiempoDTO> obtenerTiempoPlazos(final String cvePlazo, final String cveTipoTiempo);

    /**
     * Metodo para la obtencion del plazo mediante el id metodo, id tipo oficio,
     * la clave del plazo y la clave del tipo de tiempo
     *
     * @author eolf
     * @param idMetodo
     * @param idTipoOficio
     * @param cvePlazo
     * @param cveTipoTiempo
     * @return
     */
    TiempoDTO obtenerTiempoPlazoOficio(final BigDecimal idMetodo, final BigDecimal idTipoOficio,
            final String cvePlazo, final String cveTipoTiempo);

    /**
     * Metodo para la obtencion del plazo mediante la clave del plazo y la clave
     * del tipo de tiempo
     *
     * @author eolf
     * @param cvePlazo
     * @param cveTipoTiempo
     * @return
     */
    TiempoDTO obtenerTiempoPlazo(final String cvePlazo, final String cveTipoTiempo);

    List<TiempoDTO> obtenerValoresDeSemaforo(final int idMetodo);
}
