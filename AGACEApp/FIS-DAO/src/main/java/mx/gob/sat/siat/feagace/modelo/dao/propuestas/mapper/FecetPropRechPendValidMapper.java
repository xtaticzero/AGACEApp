package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechPendientesValidacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que mapea los resultados de la consulta para obtener las propuestas
 * pendientes de validacion
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropRechPendValidMapper implements ParameterizedRowMapper<FecetPropRechPendientesValidacion> {

    @Override
    public FecetPropRechPendientesValidacion mapRow(ResultSet rs, int i) throws SQLException {
        FecetPropRechPendientesValidacion propuesta = new FecetPropRechPendientesValidacion();
        propuesta.setIdRegistro((rs.getBigDecimal("ID_PROPUESTA")).toString());
        propuesta.setRfcContribuyente(rs.getString("RFC"));
        propuesta.setPrioridad(rs.getString("PRIORIDAD"));
        propuesta.setMetodo(rs.getString("ABREVIATURA"));
        propuesta.setPresuntiva(rs.getBigDecimal("PRESUNTIVA"));
        return propuesta;
    }
}
