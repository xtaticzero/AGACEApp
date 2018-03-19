package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministrador;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececSubadministradorMapper implements ParameterizedRowMapper<FececSubadministrador> {

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBADMINISTRADOR en
     * la tabla FECEC_SUBADMINISTRADOR
     */
    private static final String COLUMN_ID_SUBADMINISTRADOR = "ID_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_PROPUESTA en la
     * tabla FECEC_SUBADMINISTRADOR
     */
    private static final String COLUMN_ID_ADMINISTRADOR = "ID_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_SUBADMINISTRADOR
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_SUBADMINISTRADOR
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_SUBADMINISTRADOR
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_SUBADMINISTRADOR
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececSubadministrador mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececSubadministrador dto = new FececSubadministrador();

        dto.setIdSubadministrador(rs.getBigDecimal(COLUMN_ID_SUBADMINISTRADOR));
        dto.setIdAdministrador(rs.getBigDecimal(COLUMN_ID_ADMINISTRADOR));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));

        return dto;
    }
}
