package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;

public class NotificacionDatosContribuyenteMapper implements ParameterizedRowMapper<OrdenNotificacion> {

    private static final String COLUMN_NOMBRE = "NOMBRE";

    private static final String COLUMN_RFC = "RFC";

    public OrdenNotificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrdenNotificacion dto = new OrdenNotificacion();

        dto.setNombreContribuyente(rs.getString(COLUMN_NOMBRE));
        dto.setRfcContribuyente(rs.getString(COLUMN_RFC));

        return dto;
    }

}
