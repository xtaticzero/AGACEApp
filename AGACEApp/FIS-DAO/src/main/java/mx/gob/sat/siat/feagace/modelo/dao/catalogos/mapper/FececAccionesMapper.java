package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececAcciones;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececAccionesMapper implements ParameterizedRowMapper<FececAcciones> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ACCION en la tabla
     * FECEC_ACCIONES
     */
    private static final String COLUMN_ID_ACCION = "ID_ACCION";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_ACCIONES
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEA_OPERACIONES
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECEA_OPERACIONES
     */
    private static final String COLUMN_FECHA = "FECHA";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FececAcciones mapRow(ResultSet rs, int rowNum) throws SQLException {

        FececAcciones dto = new FececAcciones();

        dto.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFecha(rs.getTimestamp(COLUMN_FECHA));

        return dto;
    }
}
