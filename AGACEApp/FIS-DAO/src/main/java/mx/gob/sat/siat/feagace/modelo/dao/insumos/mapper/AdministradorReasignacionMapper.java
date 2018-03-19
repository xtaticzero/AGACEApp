package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.AdministradorReasignacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class AdministradorReasignacionMapper implements ParameterizedRowMapper<AdministradorReasignacion> {

    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    private static final String COLUMN_NOMBRE_ADMINISTRADOR = "NOMBRE";
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC";

    @Override
    public AdministradorReasignacion mapRow(ResultSet resultSet, int i) throws SQLException {
        AdministradorReasignacion administradorReasignacion = new AdministradorReasignacion();
        administradorReasignacion.setIdAdministrador(resultSet.getBigDecimal(COLUMN_ID_EMPLEADO));
        administradorReasignacion.setNombreAdministrador(resultSet.getString(COLUMN_NOMBRE_ADMINISTRADOR));
        administradorReasignacion.setRfcAdministrador(resultSet.getString(COLUMN_RFC_ADMINISTRADOR));

        return administradorReasignacion;
    }
}
