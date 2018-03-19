package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;

public class FecetCifraMapper implements ParameterizedRowMapper<FecetCifraDTO> {

    private static final Logger LOG = Logger.getLogger(FecetCifraMapper.class);

    public static final String COLUMN_ID_CIFRA_TIPO_CIFRA = "ID_CIFRA_TIPO_CIFRA";

    public static final String COLUMN_TOTAL = "TOTAL";

    public static final String COLUMN_NOMBRE = "NOMBRE";

    public static final String COLUMN_CONSECUTIVO = "consecutivo";

    @Override
    public FecetCifraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetCifraDTO cifra = new FecetCifraDTO();
        FeceaCifraTipoCifraDTO tipoCifra = new FeceaCifraTipoCifraDTO();
        tipoCifra.setDescripcion(rs.getString(COLUMN_NOMBRE));
        tipoCifra.setIdCifra(rs.getBigDecimal(COLUMN_ID_CIFRA_TIPO_CIFRA));
        cifra.setTipoCifra(tipoCifra);
        cifra.setTotal(rs.getBigDecimal(COLUMN_TOTAL));
        cifra.setIdCifra(rs.getBigDecimal(COLUMN_ID_CIFRA_TIPO_CIFRA));
        try {
            cifra.setConsecutivo(rs.getBigDecimal(COLUMN_CONSECUTIVO));
        } catch (Exception e) {
            LOG.error("Error al obtener el consecutivo.");
        }
        return cifra;
    }

}
