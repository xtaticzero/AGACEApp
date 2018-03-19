package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececRevisionMapper implements ParameterizedRowMapper<FececRevision> {

    /**
     * Este atributo corresponde al nombre de la columna ID_REVISION en la tabla
     * FECEC_REVISION
     */
    private static final String COLUMN_ID_REVISION = "ID_REVISION";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_REVISION
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_REVISION
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececRevision mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececRevision dto = new FececRevision();

        dto.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
