package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAnexosRechazoOficioMapper implements ParameterizedRowMapper<FecetAnexosRechazoOficio> {

    private static final String COLUMN_ID_ANEXOS_RECHAZO_OFICIO = "ID_ANEXOS_RECHAZO_OFICIO";
    private static final String COLUMN_ID_RECHAZO_OFICIO = "ID_RECHAZO_OFICIO";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    @Override
    public FecetAnexosRechazoOficio mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAnexosRechazoOficio dto = new FecetAnexosRechazoOficio();

        dto.setIdAnexosRechazoOficio(rs.getBigDecimal(COLUMN_ID_ANEXOS_RECHAZO_OFICIO));
        dto.setIdRechazoOficio(rs.getBigDecimal(COLUMN_ID_RECHAZO_OFICIO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));

        return dto;
    }
}
