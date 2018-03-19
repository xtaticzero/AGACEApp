/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;

/**
 * @author sergio.vaca
 *
 */
public class FecetDocAsociadoMapper implements ParameterizedRowMapper<FecetDocAsociado> {

    private static final String COLUMN_ID_DOC_ASOCIADO = "ID_DOC_ASOCIADO";
    private static final String COLUMN_ID_ASOCIADO = "ID_ASOCIADO";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    @Override
    public FecetDocAsociado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocAsociado docAsociado = new FecetDocAsociado();
        docAsociado.setIdDocAsociado(rs.getBigDecimal(COLUMN_ID_DOC_ASOCIADO));
        docAsociado.setIdAsociado(rs.getBigDecimal(COLUMN_ID_ASOCIADO));
        String ruta = rs.getString(COLUMN_RUTA_ARCHIVO);
        docAsociado.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(ruta));
        docAsociado.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(ruta));
        docAsociado.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        docAsociado.setBlnActivo(rs.getBigDecimal(COLUMN_BLN_ACTIVO));
        docAsociado.setFechaFin(rs.getTimestamp(COLUMN_FECHA_FIN));
        return docAsociado;
    }

}
