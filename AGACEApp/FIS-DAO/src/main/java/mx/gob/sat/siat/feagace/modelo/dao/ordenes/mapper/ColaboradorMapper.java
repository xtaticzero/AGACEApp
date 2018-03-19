package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ColaboradorMapper implements ParameterizedRowMapper<ColaboradorVO> {

    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_RFC = "RFC";
    private static final String COLUMN_CORREO = "CORREO";

    public ColaboradorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColaboradorVO dto = new ColaboradorVO();
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setCorreo(rs.getString(COLUMN_CORREO));
        return dto;
    }
}
