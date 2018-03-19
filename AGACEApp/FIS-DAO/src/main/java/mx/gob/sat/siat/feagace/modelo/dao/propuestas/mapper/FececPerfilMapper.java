package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que mapea el resultados de la consulta respecto a los datos que se
 * obtienen de los perfiles a suplir
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FececPerfilMapper implements ParameterizedRowMapper<FececPerfil> {

    @Override
    public FececPerfil mapRow(ResultSet rs, int i) throws SQLException {
        FececPerfil perfil = new FececPerfil();
        perfil.setId(rs.getInt("ID_PERFIL"));
        perfil.setDescripcion(rs.getString("DESCRIPCION"));
        return perfil;
    }
}
