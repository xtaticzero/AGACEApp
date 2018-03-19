package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececMotivoSuplencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececMotivoSuplenciaMapper implements ParameterizedRowMapper<FececMotivoSuplencia> {

    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO_SUPLENCIA";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    public FececMotivoSuplencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececMotivoSuplencia dto = new FececMotivoSuplencia();

        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
