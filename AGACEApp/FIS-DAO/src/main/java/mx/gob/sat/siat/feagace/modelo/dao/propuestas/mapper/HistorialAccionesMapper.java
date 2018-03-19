package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;

public class HistorialAccionesMapper implements ParameterizedRowMapper<FecebAccionPropuesta> {

    private static final String COLUMN_ID_ACCION_PROPUESTA = "ID_ACCION_PROPUESTA";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_DETALLE_ACCION = "ID_DETALLE_ACCION";
    private static final String COLUMN_ID_ACCION = "ID_ACCION";
    private static final String COLUMN_ID_ACCION_ORIGEN = "ID_ACCION_ORIGEN";
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";
    private static final String COLUMN_FECHA_HORA = "FECHA_HORA";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    @Override
    public FecebAccionPropuesta mapRow(ResultSet rs, int row) throws SQLException {

        FecebAccionPropuesta fecebAccionPropuesta = new FecebAccionPropuesta();

        fecebAccionPropuesta.setIdAccionPropuesta(rs.getBigDecimal(COLUMN_ID_ACCION_PROPUESTA));
        fecebAccionPropuesta.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        fecebAccionPropuesta.setIdDetalleAccion(rs.getBigDecimal(COLUMN_ID_DETALLE_ACCION));
        fecebAccionPropuesta.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        fecebAccionPropuesta.setIdAccionOrigen(rs.getBigDecimal(COLUMN_ID_ACCION_ORIGEN));
        fecebAccionPropuesta.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        fecebAccionPropuesta.setFechaHora(rs.getTimestamp(COLUMN_FECHA_HORA));
        fecebAccionPropuesta.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));

        return fecebAccionPropuesta;
    }

}
