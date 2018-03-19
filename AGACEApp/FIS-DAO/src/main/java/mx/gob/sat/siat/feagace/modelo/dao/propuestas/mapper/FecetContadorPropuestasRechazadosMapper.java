/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;

public class FecetContadorPropuestasRechazadosMapper implements ParameterizedRowMapper<FecetContadorPropuestasRechazados> {

    private static final String COLUMN_ID_RECHAZO_PROPUESTA = "ID_RECHAZO_PROPUESTA";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_CONTADOR = "CONTADOR";
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";

    public FecetContadorPropuestasRechazados mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetContadorPropuestasRechazados dto = new FecetContadorPropuestasRechazados();

        dto.setIdRechazoPropuestas(rs.getBigDecimal(COLUMN_ID_RECHAZO_PROPUESTA));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setFechaRechazo(rs.getTimestamp(COLUMN_FECHA_RECHAZO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setContador(rs.getInt(COLUMN_CONTADOR));
        dto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));

        return dto;
    }
}
