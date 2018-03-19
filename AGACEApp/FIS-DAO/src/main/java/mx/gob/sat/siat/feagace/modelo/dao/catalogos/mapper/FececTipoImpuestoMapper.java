package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececTipoImpuestoMapper implements ParameterizedRowMapper<FececTipoImpuesto> {

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_IMPUESTO en la
     * tabla FECET_TIPO_IMPUESTO
     */
    private static final String COLUMN_ID_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";

    /**
     * Este atributo corresponde al nombre de la columna ABREVIATURA en la tabla
     * FECET_TIPO_IMPUESTO
     */
    private static final String COLUMN_ABREVIATURA = "ABREVIATURA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_TIPO_IMPUESTO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_TIPO_IMPUESTO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececTipoImpuesto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececTipoImpuesto dto = new FececTipoImpuesto();

        dto.setIdTipoImpuesto(rs.getBigDecimal(COLUMN_ID_TIPO_IMPUESTO));
        dto.setAbreviatura(rs.getString(COLUMN_ABREVIATURA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        return dto;
    }
}
