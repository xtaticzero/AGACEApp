package mx.gob.sat.siat.feagace.modelo.dao.common.tiempos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TipoTiempoDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Mapper para Tiempos/Plazos
 *
 * @author eolf
 */
public class TiemposMapper implements ParameterizedRowMapper<TiempoDTO> {

    private static final String ID_TIEMPO = "ID_TIEMPO";
    private static final String CLAVE = "CLAVE";
    private static final String ID_TIPO_TIEMPO = "ID_TIPO_TIEMPO";
    private static final String ID_AGRUPADOR_TIEMPO = "ID_AGRUPADOR_TIEMPO";
    private static final String TIEMPO = "TIEMPO";
    private static final String ID_TIPO_PLAZO = "ID_TIPO_PLAZO";
    private static final String ID_METODO = "ID_METODO";
    private static final String FECHA_CREACION = "FECHA_CREACION";
    private static final String FECHA_BAJA = "FECHA_BAJA";
    private static final String TTCLAVE = "TTCLAVE";
    private static final String ID_OBJETO = "ID_OBJETO";

    /**
     * Hace un mapeo y set de la datos de la tabla FECEC_TIEMPOS
     *
     * @author eolf
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public TiempoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiempoDTO tiempo = new TiempoDTO();
        tiempo.setIdTiempo(rs.getBigDecimal(ID_TIEMPO));
        tiempo.setClave(rs.getString(CLAVE));
        tiempo.setIdTipoTiempo(rs.getBigDecimal(ID_TIPO_TIEMPO));
        tiempo.setIdAgrupadorTiempo(rs.getBigDecimal(ID_AGRUPADOR_TIEMPO));
        tiempo.setTiempo(rs.getInt(TIEMPO));
        tiempo.setIdTipoPlazo(rs.getBigDecimal(ID_TIPO_PLAZO));
        tiempo.setIdMetodo(rs.getBigDecimal(ID_METODO));
        tiempo.setFechaCreacion(rs.getDate(FECHA_CREACION));
        if (UtileriasMapperDao.existeColumna(rs, FECHA_BAJA)) {
            tiempo.setFechaBaja(rs.getDate(FECHA_BAJA));
        }
        if (UtileriasMapperDao.existeColumna(rs, TTCLAVE)) {
            llenarTipoTiempo(rs, tiempo);
        }
        if (UtileriasMapperDao.existeColumna(rs, ID_OBJETO)) {
            tiempo.setIdObjeto(rs.getInt(ID_OBJETO));
        }
        return tiempo;
    }

    private void llenarTipoTiempo(ResultSet rs, TiempoDTO tiempo) throws SQLException {
        if (tiempo.getTipoTiempoDTO() == null) {
            tiempo.setTipoTiempoDTO(new TipoTiempoDTO());
        }
        tiempo.getTipoTiempoDTO().setIdTipoTiempo(rs.getBigDecimal(ID_TIPO_TIEMPO));
        tiempo.getTipoTiempoDTO().setClave(rs.getString(TTCLAVE));
    }
}
