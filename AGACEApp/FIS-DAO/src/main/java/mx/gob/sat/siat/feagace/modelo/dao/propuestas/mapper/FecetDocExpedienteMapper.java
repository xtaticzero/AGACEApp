package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocExpedienteMapper implements ParameterizedRowMapper<FecetDocExpediente> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_EXPEDIENTE en la
     * tabla FECET_DOC_EXPEDIENTE
     */
    private static final String COLUMN_ID_DOC_EXPEDIENTE = "ID_DOC_EXPEDIENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_DOC_EXPEDIENTE
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_EXPEDIENTE
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_EXPEDIENTE
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_DOC_EXPEDIENTE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetDocExpediente mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocExpediente dto = new FecetDocExpediente();

        dto.setIdDocExpediente(rs.getBigDecimal(COLUMN_ID_DOC_EXPEDIENTE));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setNombre(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setFechaCreacionTimeStamp(rs.getTimestamp(COLUMN_FECHA_CREACION));

        return dto;
    }
}
