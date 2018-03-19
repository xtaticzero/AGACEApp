package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;

public class FececLeyendaMapper implements ParameterizedRowMapper<FececLeyenda> {

    private static final String COLUMN_ID_LEYENDA = "ID_LEYENDA";
    private static final String COLUMN_NOMBRE_LEYENDA = "NOMBRE_LEYENDA";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    public FececLeyenda mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececLeyenda dto = new FececLeyenda();
        dto.setIdLeyenda(rs.getBigDecimal(COLUMN_ID_LEYENDA));
        dto.setNombreLeyenda(rs.getString(COLUMN_NOMBRE_LEYENDA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }
}
