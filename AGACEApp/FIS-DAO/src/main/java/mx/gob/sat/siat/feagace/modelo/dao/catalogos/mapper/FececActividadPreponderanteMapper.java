package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececActividadPreponderanteMapper implements ParameterizedRowMapper<FececActividadPreponderante> {

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_ACTIVIDAD_PREPONDERANTE en la tabla FECEC_ACTIVIDAD_PREPONDERANTE
     */
    private static final String COLUMN_ID_ACTIVIDAD_PREPONDERANTE = "ID_ACTIVIDAD_PREPONDERANTE";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_ACTIVIDAD_PREPONDERANTE
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ACTIVIDAD_PREPONDERANTE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececActividadPreponderante mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececActividadPreponderante dto = new FececActividadPreponderante();

        dto.setIdActividadPreponderante(rs.getBigDecimal(COLUMN_ID_ACTIVIDAD_PREPONDERANTE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
