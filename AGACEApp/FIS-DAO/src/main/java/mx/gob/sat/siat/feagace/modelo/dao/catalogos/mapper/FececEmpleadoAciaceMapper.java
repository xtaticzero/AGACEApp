package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEmpleadoAciace;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEmpleadoAciaceMapper implements ParameterizedRowMapper<FececEmpleadoAciace> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECEC_EMPLEADO_ACIACE
     */
    private static final String COLUMN_ID_EMPLEADO_ACIACE = "ID_EMPLEADO_ACIACE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_EMPLEADO_ACIACE
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_EMPLEADO_ACIACE
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_EMPLEADO_ACIACE
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_EMPLEADO_ACIACE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececEmpleadoAciace mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEmpleadoAciace dto = new FececEmpleadoAciace();

        dto.setIdEmpleadoAciace(rs.getBigDecimal(COLUMN_ID_EMPLEADO_ACIACE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));

        return dto;
    }
}
