package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocOrdenVerifProcedenciaMapper implements ParameterizedRowMapper<FecetDocExpediente> {

    private static final String COLUMN_ID_DOC_ORDEN = "ID_DOC_ORDEN";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_DOC_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetDocExpediente mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocExpediente dto = new FecetDocExpediente();

        dto.setIdDocExpediente(rs.getBigDecimal(COLUMN_ID_DOC_ORDEN));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setNombre(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }
}
