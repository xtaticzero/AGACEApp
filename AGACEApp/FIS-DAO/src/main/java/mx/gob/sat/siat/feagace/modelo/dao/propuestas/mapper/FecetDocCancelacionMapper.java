/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;

/**
 * @author sergio.vaca
 *
 */
public class FecetDocCancelacionMapper implements ParameterizedRowMapper<FecetDocCancelacion> {

    private static final String COLUMN_ID_DOC_CANCELACION = "ID_DOC_CANCELACION";
    private static final String COLUMN_ID_CANCELACION = "ID_CANCELACION";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public FecetDocCancelacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocCancelacion cancelacion = new FecetDocCancelacion();

        cancelacion.setIdDocCancelacion(rs.getBigDecimal(COLUMN_ID_DOC_CANCELACION));
        cancelacion.setIdCancelacion(rs.getBigDecimal(COLUMN_ID_CANCELACION));
        String ruta = rs.getString(COLUMN_RUTA_ARCHIVO);
        cancelacion.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(ruta));
        cancelacion.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(ruta));
        cancelacion.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        cancelacion.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        cancelacion.setBlnActivo(rs.getBigDecimal(COLUMN_BLN_ACTIVO));

        return cancelacion;
    }

}
