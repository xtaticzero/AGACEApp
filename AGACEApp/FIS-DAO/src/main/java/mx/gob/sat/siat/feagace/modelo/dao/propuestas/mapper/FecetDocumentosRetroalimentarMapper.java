package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocumentosRetroalimentarMapper implements ParameterizedRowMapper<FecetRetroalimentacion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_DOC_RETRO_PROPUESTA
     */
    public static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_ID_PROPUESTA en
     * la tabla FECET_DOC_RETRO_PROPUESTA
     */
    public static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_RUTA_ARCHIVO en
     * la tabla FECET_DOC_RETRO_PROPUESTA
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_RETRO_PROPUESTA
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    public FecetRetroalimentacion mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetRetroalimentacion dto = new FecetRetroalimentacion();

        dto.setIdRetroalimentacion(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));

        return dto;
    }

}
