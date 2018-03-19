package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececMotivoMapper implements ParameterizedRowMapper<FececMotivo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_MOTIVO en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_TIPO_MOTIVO = "TIPO_MOTIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_MOTIVO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececMotivo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececMotivo dto = new FececMotivo();

        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setTipoMotivo(rs.getString(COLUMN_TIPO_MOTIVO));

        return dto;
    }
}
