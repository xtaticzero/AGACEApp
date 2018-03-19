package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececAdministradorMapper implements ParameterizedRowMapper<FececAdministrador> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ADMINISTRADOR en la
     * tabla FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_ID_ADMINISTRADOR = "ID_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_CENTRAL en la tabla
     * FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_ID_CENTRAL = "ID_CENTRAL";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_ADMINISTRADOR
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ADMINISTRADOR
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececAdministrador mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececAdministrador dto = new FececAdministrador();

        dto.setIdAdministrador(rs.getBigDecimal(COLUMN_ID_ADMINISTRADOR));
        dto.setIdCentral(rs.getBigDecimal(COLUMN_ID_CENTRAL));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));

        return dto;
    }
}
