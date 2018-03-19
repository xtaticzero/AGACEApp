package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FeceaPropuestaAccion;

public class FeceaPropuestaAccionMapper implements ParameterizedRowMapper<FeceaPropuestaAccion> {

    private static final String COLUMN_ID_ACCION_PROPUESTA = "ID_PROPUESTA_ACCION";

    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    private static final String COLUMN_ID_ACCION = "ID_ACCION";

    private static final String COLUMN_ID_ACCION_ORIGEN = "ID_ACCION_ORIGEN";

    @Override
    public FeceaPropuestaAccion mapRow(ResultSet rs, int rowNum) throws SQLException {

        FeceaPropuestaAccion accion = new FeceaPropuestaAccion();

        accion.setIdPropuestaAccion(rs.getBigDecimal(COLUMN_ID_ACCION_PROPUESTA));
        accion.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        accion.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        accion.setIdAccionOrigen(rs.getBigDecimal(COLUMN_ID_ACCION_ORIGEN));

        return accion;
    }

}
