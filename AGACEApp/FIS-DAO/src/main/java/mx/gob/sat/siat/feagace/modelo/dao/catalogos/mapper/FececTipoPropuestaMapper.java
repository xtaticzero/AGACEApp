package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;

public class FececTipoPropuestaMapper implements ParameterizedRowMapper<FececTipoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_PROPUESTA en la
     * tabla FECEC_TIPO_PROPUESTA
     */
    private static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_TIPO_PROPUESTA
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_TIPO_PROPUESTA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececTipoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececTipoPropuesta dto = new FececTipoPropuesta();

        dto.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
