/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;

public class FececAraceMapper implements ParameterizedRowMapper<FececArace> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_ARACE
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_ARACE
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna SEDE en la tabla
     * FECEC_ARACE
     */
    private static final String COLUMN_SEDE = "SEDE";

    /**
     * Este atributo corresponde al nombre de la columna CENTRAL en la tabla
     * FECEC_ARACE
     */
    private static final String COLUMN_CENTRAL = "CENTRAL";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ARACE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececArace mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececArace dto = new FececArace();

        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setSede(rs.getString(COLUMN_SEDE));
        dto.setCentral(rs.getBoolean(COLUMN_CENTRAL));

        return dto;
    }
}
