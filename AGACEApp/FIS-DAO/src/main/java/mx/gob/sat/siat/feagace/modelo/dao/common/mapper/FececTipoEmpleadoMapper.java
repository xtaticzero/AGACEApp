package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececTipoEmpleadoMapper implements ParameterizedRowMapper<FececTipoEmpleado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_EMPLEADO en la
     * tabla FECET_TIPO_EMPLEADO
     */
    private static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_TIPO_EMPLEADO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_TIPO_EMPLEADO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececTipoEmpleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececTipoEmpleado dto = new FececTipoEmpleado();

        dto.setIdTipoEmpleado(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
