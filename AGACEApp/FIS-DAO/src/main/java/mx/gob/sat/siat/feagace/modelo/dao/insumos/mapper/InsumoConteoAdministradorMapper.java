/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author sergio.vaca
 *
 */
public class InsumoConteoAdministradorMapper implements RowMapper<Map<String, Long>> {

    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";
    private static final String COLUMN_TOTAL_REGISTROS = "TOTAL_REGISTROS";

    @Override
    public Map<String, Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Long> resultado = new LinkedHashMap<String, Long>();
        do {
            resultado.put(rs.getString(COLUMN_RFC_ADMINISTRADOR), rs.getLong(COLUMN_TOTAL_REGISTROS));
        } while (rs.next());
        return resultado;
    }

}
