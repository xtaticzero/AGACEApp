package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEstatusSuplencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEstatusSuplenciaMapper implements ParameterizedRowMapper<FececEstatusSuplencia> {

    private static final String COLUMN_ID_ESTATUS_SUPLENCIA = "ID_ESTATUS_SUPLENCIA";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    public FececEstatusSuplencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEstatusSuplencia dto = new FececEstatusSuplencia();
        dto.setIdEstatusSuplencia(rs.getBigDecimal(COLUMN_ID_ESTATUS_SUPLENCIA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
