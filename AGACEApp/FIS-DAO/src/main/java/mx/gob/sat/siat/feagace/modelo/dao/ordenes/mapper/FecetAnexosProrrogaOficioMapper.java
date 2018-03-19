package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAnexosProrrogaOficioMapper implements ParameterizedRowMapper<FecetAnexosProrrogaOficio> {

    private static final String COLUMN_ID_ANEXOS_PRORROGA_OFICIO = "ID_ANEXOS_PRORROGA_OFICIO";
    private static final String COLUMN_ID_FLUJO_PRORROGA_OFICIO = "ID_FLUJO_PRORROGA_OFICIO";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_TIPO_ANEXO = "TIPO_ANEXO";

    @Override
    public FecetAnexosProrrogaOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetAnexosProrrogaOficio dto = new FecetAnexosProrrogaOficio();
        dto.setIdAnexosProrrogaOficio(rs.getBigDecimal(COLUMN_ID_ANEXOS_PRORROGA_OFICIO));
        dto.setIdFlujoProrrogaOficio(rs.getBigDecimal(COLUMN_ID_FLUJO_PRORROGA_OFICIO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setTipoAnexo(rs.getString(COLUMN_TIPO_ANEXO));
        return dto;
    }
}
