package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechVerifProcedencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que mapea los resultados de la consulta para obtener las propuestas
 * rechazadas en verificacion de procedencia de documento electronico
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropRechVerifProcMapper implements ParameterizedRowMapper<FecetPropRechVerifProcedencia> {

    @Override
    public FecetPropRechVerifProcedencia mapRow(ResultSet rs, int i) throws SQLException {
        FecetPropRechVerifProcedencia propuesta = new FecetPropRechVerifProcedencia();
        propuesta.setIdPropuesta((rs.getBigDecimal("ID_PROPUESTA")).toString());
        propuesta.setIdRegistro(rs.getString("ID_REGISTRO"));
        propuesta.setRfcContribuyente(rs.getString("RFC"));
        propuesta.setPrioridad(rs.getString("PRIORIDAD"));
        propuesta.setMetodo(rs.getString("NOMBRE"));
        propuesta.setPresuntiva(rs.getBigDecimal("PRESUNTIVA"));
        return propuesta;
    }
}
