/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;

/**
 * @author Sergio.vaca
 *
 */
public class FecetTransferenciaContadorMapper implements ParameterizedRowMapper<FecetTransferenciaContador> {

    private static final String COLUMN_ID_TRANSFERENCIA = "ID_TRANSFERENCIA";
    private static final String COLUMN_ID_ARACE_ORIGEN = "ID_ARACE_ORIGEN";
    private static final String COLUMN_ID_ARACE_DESTINO = "ID_ARACE_DESTINO";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_FECHA_TRASPASO = "FECHA_TRASPASO";
    private static final String COLUMN_RFC = "RFC_EMPLEADO";
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";
    private static final String COLUMN_ESTATUS = "ID_ESTATUS";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    private static final String COLUMN_BLN_ESTATUS = "BLN_ESTATUS";
    private static final String COLUMN_TOTAL_DOCUMENTOS = "TOTAL_DOCUMENTOS";

    @Override
    public FecetTransferenciaContador mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetTransferenciaContador contador = new FecetTransferenciaContador();

        contador.setIdTransferencia(rs.getBigDecimal(COLUMN_ID_TRANSFERENCIA));
        contador.setIdAraceOrigen(rs.getBigDecimal(COLUMN_ID_ARACE_ORIGEN));
        contador.setIdAraceDestino(rs.getBigDecimal(COLUMN_ID_ARACE_DESTINO));
        contador.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        contador.setFechaTraspaso(rs.getTimestamp(COLUMN_FECHA_TRASPASO));
        contador.setRfc(rs.getString(COLUMN_RFC));
        contador.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        contador.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        contador.setEstatus(rs.getBigDecimal(COLUMN_ESTATUS));
        contador.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        contador.setBlnEstatus(rs.getBigDecimal(COLUMN_BLN_ESTATUS));
        contador.setTotalDocumentos(rs.getInt(COLUMN_TOTAL_DOCUMENTOS));

        return contador;
    }

}
