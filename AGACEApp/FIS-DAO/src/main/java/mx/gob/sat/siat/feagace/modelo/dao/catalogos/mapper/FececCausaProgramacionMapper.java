package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececCausaProgramacionMapper implements ParameterizedRowMapper<FececCausaProgramacion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_CAUSA_PROGRAMACION
     */
    private static final String COLUMN_ID_CAUSA_PROGRAMACION = "ID_CAUSA_PROGRAMACION";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_CAUSA_PROGRAMACION
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_CAUSA_PROGRAMACION
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececCausaProgramacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececCausaProgramacion dto = new FececCausaProgramacion();

        dto.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
