package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.cifras;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;

public class SumatoriaTipoCifraMapper implements ParameterizedRowMapper<TotalCifrasDTO> {

    public static final String COLUMN_ID_CIFRA = "id_cifra";

    public static final String COLUMN_CIFRA = "cifra";

    public static final String COLUMN_TOTAL = "total";

    @Override
    public TotalCifrasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TotalCifrasDTO total = new TotalCifrasDTO();
        total.setDescripcionTipo(rs.getString(COLUMN_CIFRA));
        total.setIdTipoCifra(rs.getBigDecimal(COLUMN_ID_CIFRA));
        total.setMontoCifra(rs.getBigDecimal(COLUMN_TOTAL));
        return total;
    }

}
