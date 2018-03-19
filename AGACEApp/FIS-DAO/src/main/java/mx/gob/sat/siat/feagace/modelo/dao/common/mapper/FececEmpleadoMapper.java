package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEmpleadoMapper implements ParameterizedRowMapper<FececEmpleado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_EMPLEADO en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS_EMPLEADO en
     * la tabla FECEC_EMPLEADO
     */
    private static final String COLUMN_ID_ESTATUS_EMPLEADO = "ID_ESTATUS_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECEC_EMPLEADO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_EMPLEADO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececEmpleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEmpleado dto = new FececEmpleado();

        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setCorreo(rs.getString(COLUMN_CORREO));
        dto.setIdEstatusEmpleado(rs.getBigDecimal(COLUMN_ID_ESTATUS_EMPLEADO));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getTimestamp(COLUMN_FECHA_BAJA));

        return dto;
    }
}
