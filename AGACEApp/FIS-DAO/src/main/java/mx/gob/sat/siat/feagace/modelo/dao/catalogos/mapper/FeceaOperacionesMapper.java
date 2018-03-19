package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FeceaOperaciones;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FeceaOperacionesMapper implements
        ParameterizedRowMapper<FeceaOperaciones> {

    /**
     * Este atributo corresponde al nombre de la columna ID_OPERACION en la
     * tabla FECEA_OPERACIONES
     */
    private static final String COLUMN_ID_OPERACION = "ID_OPERACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_MODULO en la tabla
     * FECEA_OPERACIONES
     */
    private static final String COLUMN_ID_MODULO = "ID_MODULO";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBMODULO en la
     * tabla FECEA_OPERACIONES
     */
    private static final String COLUMN_ID_SUBMODULO = "ID_SUBMODULO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ACCION en la tabla
     * FECEA_OPERACIONES
     */
    private static final String COLUMN_ID_ACCION = "ID_ACCION";

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
    public FeceaOperaciones mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        FeceaOperaciones dto = new FeceaOperaciones();

        dto.setIdOperacion(rs.getBigDecimal(COLUMN_ID_OPERACION));
        dto.setIdModulo(rs.getBigDecimal(COLUMN_ID_MODULO));
        dto.setIdSubmodulo(rs.getBigDecimal(COLUMN_ID_SUBMODULO));
        dto.setIdOperacion(rs.getBigDecimal(COLUMN_ID_OPERACION));
        dto.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        dto.setFecha(rs.getTimestamp(COLUMN_FECHA));

        return dto;
    }
}
