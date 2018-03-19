package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class GraficaValoresMapper implements ParameterizedRowMapper<GraficaValoresDTO> {

    public GraficaValoresDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        GraficaValoresDTO dto = new GraficaValoresDTO();
        if (UtileriasMapperDao.existeColumna(rs, "MES")) {
            dto.setMes(rs.getString("MES") != null ? rs.getString("MES") : null);
        }
        if (UtileriasMapperDao.existeColumna(rs, "NOMBRE_ESTATUS")) {
            dto.setEstatus(rs.getString("NOMBRE_ESTATUS") != null ? rs.getString("NOMBRE_ESTATUS") : null);
        }
        if (UtileriasMapperDao.existeColumna(rs, "ENTIDAD")) {
            dto.setEntidad(rs.getString("ENTIDAD") != null ? rs.getString("ENTIDAD") : null);
        }

        if (UtileriasMapperDao.existeColumna(rs, "NOMBRE_METODO")) {
            dto.setMetodo(rs.getString("NOMBRE_METODO") != null ? rs.getString("NOMBRE_METODO") : null);
        }

        if (UtileriasMapperDao.existeColumna(rs, "UNIDAD_ADMINISTRATIVA")) {
            dto.setIdUnidad(rs.getBigDecimal("UNIDAD_ADMINISTRATIVA") != null ? rs.getBigDecimal("UNIDAD_ADMINISTRATIVA") : null);
        }

        if (UtileriasMapperDao.existeColumna(rs, "TOTAL")) {
            dto.setCantidad(rs.getBigDecimal("TOTAL") != null ? rs.getInt("TOTAL") : null);
        }
        return dto;
    }
}
