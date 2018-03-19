package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;

public class AsociadoFuncionarioMapper implements ParameterizedRowMapper<AsociadoFuncionarioDTO> {

    public static final String COLUMN_ID_ASOCIADO = "ID_ASOCIADO";
    public static final String COLUMN_ID_PORPUESTA = "ID_PROPUESTA";
    public static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";
    public static final String COLUMN_ID_ORDEN = "ID_ORDEN";
    public static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    public static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public AsociadoFuncionarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AsociadoFuncionarioDTO dto = new AsociadoFuncionarioDTO();

        dto.setIdAsociado(rs.getBigDecimal(COLUMN_ID_ASOCIADO));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PORPUESTA));
        dto.setIdTipoEmpleado(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }

}
