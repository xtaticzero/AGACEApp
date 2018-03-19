package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropTransferPendValidacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que mapea los resultados de la consulta para obtener las propuestas
 * transferidas pendientes de validacion
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropTransferPendValidMapper implements ParameterizedRowMapper<FecetPropTransferPendValidacion> {

    @Override
    public FecetPropTransferPendValidacion mapRow(ResultSet rs, int i) throws SQLException {
        FecetPropTransferPendValidacion propuesta = new FecetPropTransferPendValidacion();
        propuesta.setIdPropuesta((rs.getBigDecimal("ID_PROPUESTA")).toString());
        propuesta.setIdRegistro(rs.getString("ID_REGISTRO"));
        propuesta.setRfcContribuyente(rs.getString("RFC"));
        propuesta.setPrioridad(rs.getString("PRIORIDAD"));
        propuesta.setMetodo(rs.getString("NOMBRE"));
        propuesta.setPresuntiva(rs.getBigDecimal("PRESUNTIVA"));
        propuesta.setUnidadAdmvaATransfer(rs.getString("ADACE_DESTINO"));
        return propuesta;
    }
}
