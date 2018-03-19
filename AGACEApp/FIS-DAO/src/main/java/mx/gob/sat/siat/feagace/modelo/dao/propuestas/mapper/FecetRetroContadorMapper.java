/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;

/**
 * @author Sergio.vaca
 *
 */
public class FecetRetroContadorMapper implements ParameterizedRowMapper<FecetRetroContador> {

    private static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";
    private static final String COLUMN_DETALLE = "DETALLE";
    private static final String COLUMN_FECHA_RETROALIMENTACION = "FECHA_RETROALIMENTACION";
    private static final String COLUMN_TOTAL_DOCUMENTOS = "TOTAL_DOCUMENTOS";
    private static final String COLUMN_MOTIVO = "MOTIVO";
    private static final String COLUMN_TIPO_MOTIVO = "TIPO_MOTIVO";

    @Override
    public FecetRetroContador mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRetroContador contador = new FecetRetroContador();
        contador.setIdRetroalimentacion(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION));
        contador.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        contador.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        contador.setDetalle(rs.getString(COLUMN_DETALLE));
        contador.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_RETROALIMENTACION));
        contador.setTotalDocumentos(rs.getInt(COLUMN_TOTAL_DOCUMENTOS));
        contador.setMotivo(rs.getString(COLUMN_MOTIVO));
        contador.setTipoMotivo(rs.getString(COLUMN_TIPO_MOTIVO));
        return contador;
    }

}
