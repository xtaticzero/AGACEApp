package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetOficioPendienteAnexosMapper implements ParameterizedRowMapper<FecetOficioAnexos> {

    /**
     * Este atributo corresponde al nombre de la columna ID_OFICIO_ANEXOS en la
     * tabla FECET_OFICIO_ANEXOS
     */
    public static final String COLUMN_ID_OFICIO_ANEXOS = "ID_OFICIO_ANEXOS";

    /**
     * Este atributo corresponde al nombre de la columna ID_OFICIO en la tabla
     * FECET_OFICIO_ANEXOS
     */
    public static final String COLUMN_ID_OFICIO = "ID_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_OFICIO_ANEXOS
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_OFICIO_ANEXOS
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_OFICIO_ANEXOS
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    @Override
    public FecetOficioAnexos mapRow(ResultSet rs, int i) throws SQLException {
        FecetOficioAnexos dto = new FecetOficioAnexos();

        dto.setIdOficioAnexos(rs.getBigDecimal(COLUMN_ID_OFICIO_ANEXOS));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(dto.getRutaArchivo()));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));

        return dto;
    }
}
