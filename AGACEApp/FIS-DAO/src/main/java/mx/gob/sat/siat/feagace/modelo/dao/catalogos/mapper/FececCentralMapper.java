package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentral;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececCentralMapper implements ParameterizedRowMapper<FececCentral> {

    /**
     * Este atributo corresponde al nombre de la columna ID_CENTRAL en la tabla
     * FECEC_CENTRAL
     */
    private static final String COLUMN_ID_CENTRAL = "ID_CENTRAL";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_CENTRAL
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_CENTRAL
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_CENTRAL
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_CENTRAL
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_CENTRAL
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececCentral mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececCentral dto = new FececCentral();

        dto.setIdCentral(rs.getBigDecimal(COLUMN_ID_CENTRAL));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));

        return dto;
    }
}
