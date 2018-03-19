package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;

public class FeceaTipoParcialidadMapper implements ParameterizedRowMapper<FececTipoParcialidadDTO> {

    public static final String COLUMN_ID_TIPO_PARCIALIDAD = "id_tipo_parcialidad";

    public static final String COLUMN_TIPO_PARCIALIDAD = "tipo_parcialidad";

    @Override
    public FececTipoParcialidadDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececTipoParcialidadDTO tipoParcialidad = new FececTipoParcialidadDTO();
        tipoParcialidad.setTipoParcialidad(rs.getString(COLUMN_TIPO_PARCIALIDAD));
        tipoParcialidad.setIdParcialidad(rs.getBigDecimal(COLUMN_ID_TIPO_PARCIALIDAD));
        return tipoParcialidad;
    }

}
