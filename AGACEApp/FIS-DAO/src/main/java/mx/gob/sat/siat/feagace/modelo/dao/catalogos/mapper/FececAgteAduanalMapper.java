package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanal;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececAgteAduanalMapper implements ParameterizedRowMapper<FececAgteAduanal> {

    /**
     * Este atributo corresponde al nombre de la columna ID_APOD_LEGAL en la
     * tabla FECEC_AGTE_ADUANAL
     */
    private static final String COLUMN_ID_AGTE_ADUANAL = "ID_AGTE_ADUANAL";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_AGTE_ADUANAL
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_AGTE_ADUANAL
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    public FececAgteAduanal mapRow(ResultSet rs, int row) throws SQLException {
        FececAgteAduanal dto = new FececAgteAduanal();
        dto.setIdAgteAduanal(rs.getBigDecimal(COLUMN_ID_AGTE_ADUANAL));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        return dto;
    }
}
