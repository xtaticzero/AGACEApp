/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;

/**
 * @author sergio.vaca
 *
 */
public class FecetCancelacionMapper implements ParameterizedRowMapper<FecetCancelacion> {

    private static final String COLUMN_ID_CANCELACION = "ID_CANCELACION";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_CAUSA = "ID_CAUSA";
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_RFC_CANCELACION = "RFC_CANCELACION";
    private static final String COLUMN_FECHA_CANCELACION = "FECHA_CANCELACION";
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";
    private static final String COLUMN_DESCRIPCION_RECHAZO = "DESCRIPCION_RECHAZO";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    private static final String COLUMN_BLN_ESTATUS = "BLN_ESTATUS";
    private static final String COLUMN_TOTAL_DOCUMENTOS = "TOTAL_DOCUMENTOS";
    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    @Override
    public FecetCancelacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetCancelacion cancelacion = new FecetCancelacion();

        cancelacion.setIdCancelacion(rs.getBigDecimal(COLUMN_ID_CANCELACION));
        cancelacion.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        cancelacion.setIdCausa(rs.getBigDecimal(COLUMN_ID_CAUSA));
        cancelacion.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        cancelacion.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        cancelacion.setRfcCancelacion(rs.getString(COLUMN_RFC_CANCELACION));
        cancelacion.setFechaCancelacion(rs.getTimestamp(COLUMN_FECHA_CANCELACION));
        cancelacion.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        cancelacion.setDescripcionRechazo(rs.getString(COLUMN_DESCRIPCION_RECHAZO));
        cancelacion.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        cancelacion.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        cancelacion.setBlnEstatus(rs.getBigDecimal(COLUMN_BLN_ESTATUS));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_TOTAL_DOCUMENTOS)) {
            cancelacion.setTotalDocumentos(rs.getLong(COLUMN_TOTAL_DOCUMENTOS));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_MOTIVO)) {
            cancelacion.setFececMotivo(new FececMotivoMapper().mapRow(rs, rowNum));
        }
        return cancelacion;
    }

}
