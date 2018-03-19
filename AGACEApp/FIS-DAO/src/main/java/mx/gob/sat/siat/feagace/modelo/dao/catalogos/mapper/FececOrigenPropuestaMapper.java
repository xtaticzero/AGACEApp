package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececOrigenPropuesta;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececOrigenPropuestaMapper implements ParameterizedRowMapper<FececOrigenPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ORIGEN en la tabla
     * FECEC_ORIGEN_PROPUESTA
     */
    private static final String COLUMN_ID_ORIGEN = "ID_ORIGEN";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_ORIGEN_PROPUESTA
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ORIGEN_PROPUESTA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececOrigenPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececOrigenPropuesta dto = new FececOrigenPropuesta();
        dto.setIdOrigen(rs.getBigDecimal(COLUMN_ID_ORIGEN));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        return dto;
    }
}
