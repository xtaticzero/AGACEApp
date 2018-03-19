package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegal;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececRepLegalMapper implements ParameterizedRowMapper<FececRepLegal> {

    /**
     * Este atributo corresponde al nombre de la columna ID_APOD_LEGAL en la
     * tabla FECEC_REP_LEGAL
     */
    private static final String COLUMN_ID_REP_LEGAL = "ID_REP_LEGAL";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_REP_LEGAL
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_REP_LEGAL
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Metodo MapRow
     *
     * @param rs
     * @param row
     * @return dto
     * @throws SQLException
     */
    public FececRepLegal mapRow(ResultSet rs, int row) throws SQLException {
        FececRepLegal dto = new FececRepLegal();
        dto.setIdRepLegal(rs.getBigDecimal(COLUMN_ID_REP_LEGAL));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfc(rs.getString(COLUMN_RFC));

        return dto;
    }
}
