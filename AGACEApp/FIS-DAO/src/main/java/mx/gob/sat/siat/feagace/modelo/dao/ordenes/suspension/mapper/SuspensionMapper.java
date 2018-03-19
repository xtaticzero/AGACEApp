package mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;

public class SuspensionMapper implements ParameterizedRowMapper<FecetSuspensionDTO> {

    private static final String COLUMN_ID_SUSPENSION_TIEMPO = "ID_SUSPENSION_TIEMPO";
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";
    private static final String COLUMN_FECHA_INICIO_SUSPENSION = "FECHA_INICIO_SUSPENSION";
    private static final String COLUMN_FECHA_FIN_SUSPENSION = "FECHA_FIN_SUSPENSION";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";
    private static final String COLUMN_ID_OBJETO = "ID_OBJETO";
    private static final String COLUMN_ID_OFICIO = "ID_OFICIO";
    private static final String COLUMN_ID_NOMBRE = "NOMBRE";

    public FecetSuspensionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetSuspensionDTO dto = new FecetSuspensionDTO();
        dto.setIdSuspensionTiempo(rs.getBigDecimal(COLUMN_ID_SUSPENSION_TIEMPO));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setFechaInicioSuspension(rs.getDate(COLUMN_FECHA_INICIO_SUSPENSION));
        dto.setFechaFinSuspension(rs.getDate(COLUMN_FECHA_FIN_SUSPENSION));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_NOMBRE)) {
            StringBuilder builder = new StringBuilder();
            builder.append(dto.getIdOficio());
            builder.append("-");
            builder.append(rs.getString(COLUMN_ID_NOMBRE));
            dto.setNombreOficio(builder.toString());
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_OBJETO)) {
            dto.setIdObjeto(rs.getBigDecimal(COLUMN_ID_OBJETO));
        }
        return dto;
    }

}
