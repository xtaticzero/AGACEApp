package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;

/**
 * @author sergio.vaca
 *
 */
public class EmpleadoSuplenteMapper implements ParameterizedRowMapper<EmpleadoSuplenteDTO> {

    private static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";
    private static final String COLUMN_ID_SUPLENTE = "ID_SUPLENTE";
    private static final String COLUMN_MOTIVO = "MOTIVO";

    @Override
    public EmpleadoSuplenteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final EmpleadoSuplenteDTO empleado = new EmpleadoSuplenteDTO();
        empleado.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        empleado.setIdSuplente(rs.getBigDecimal(COLUMN_ID_SUPLENTE));
        empleado.setMotivo(rs.getString(COLUMN_MOTIVO));
        return empleado;
    }

}
