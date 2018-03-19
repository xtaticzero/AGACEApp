package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;

public class FeceaTipoCifraMapper implements ParameterizedRowMapper<FeceaCifraTipoCifraDTO> {

    public static final String COLUMN_ID_TIPO_CIFRA = "ID_CIFRA_TIPO_CIFRA";

    public static final String COLUMN_NOMBRE = "nombre";

    @Override
    public FeceaCifraTipoCifraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FeceaCifraTipoCifraDTO tipoCifra = new FeceaCifraTipoCifraDTO();
        tipoCifra.setDescripcion(rs.getString(COLUMN_NOMBRE));
        tipoCifra.setIdCifra(rs.getBigDecimal(COLUMN_ID_TIPO_CIFRA));
        return tipoCifra;
    }

}
