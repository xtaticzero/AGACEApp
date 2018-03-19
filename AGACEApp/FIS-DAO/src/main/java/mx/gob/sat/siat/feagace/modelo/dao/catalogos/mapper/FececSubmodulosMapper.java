package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSubmodulos;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececSubmodulosMapper implements ParameterizedRowMapper<FececSubmodulos> {

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBMODULO en la
     * tabla FECET_SUBMODULOS
     */
    private static final String COLUMN_ID_SUBMODULO = "ID_SUBMODULO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_SUBMODULOS
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_DESCRIPCION en
     * la tabla FECEC_SUBMODULOS
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECEC_SUBMODULOS
     */
    private static final String COLUMN_FECHA = "FECHA";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FececSubmodulos mapRow(ResultSet rs, int rowNum) throws SQLException {

        FececSubmodulos dto = new FececSubmodulos();

        dto.setIdSubmodulo(rs.getBigDecimal(COLUMN_ID_SUBMODULO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFecha(rs.getTimestamp(COLUMN_FECHA));

        return dto;
    }
}
