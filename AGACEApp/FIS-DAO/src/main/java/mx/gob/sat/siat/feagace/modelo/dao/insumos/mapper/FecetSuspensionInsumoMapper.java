package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;

public class FecetSuspensionInsumoMapper implements ParameterizedRowMapper<FecetSuspensionInsumo> {

    private static final String COLUMN_ID_SUSPENSION_INSUMO = "ID_SUSPENSION_INSUMO";
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";
    private static final String COLUMN_FECHA_INICIO_SUSPENSION = "FECHA_INICIO_SUSPENSION";
    private static final String COLUMN_FECHA_FIN_SUSPENSION = "FECHA_FIN_SUSPENSION";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    public FecetSuspensionInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetSuspensionInsumo dto = new FecetSuspensionInsumo();
        dto.setIdSuspensionInsumo(rs.getBigDecimal(COLUMN_ID_SUSPENSION_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setFechaInicioSuspension(rs.getTimestamp(COLUMN_FECHA_INICIO_SUSPENSION));
        dto.setFechaFinSuspension(rs.getTimestamp(COLUMN_FECHA_FIN_SUSPENSION));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        return dto;
    }
}
