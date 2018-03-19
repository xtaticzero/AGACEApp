package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececTipoAsociadoMapper implements ParameterizedRowMapper<FececTipoAsociado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_EMPLEADO en la
     * tabla FECEC_TIPO_ASOCIADO
     */
    private static final String COLUMN_ID_TIPO_ASOCIADO = "ID_TIPO_ASOCIADO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_TIPO_ASOCIADO
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_TIPO_ASOCIADO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_TIPO_EMPLEADO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececTipoAsociado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececTipoAsociado dto = new FececTipoAsociado();

        dto.setIdTipoAsociado(rs.getBigDecimal(COLUMN_ID_TIPO_ASOCIADO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
