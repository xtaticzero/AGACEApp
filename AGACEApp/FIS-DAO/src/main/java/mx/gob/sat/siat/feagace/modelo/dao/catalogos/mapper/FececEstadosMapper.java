package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEstadosMapper implements ParameterizedRowMapper<FececEstados> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTADOS en la tabla
     * FECEC_ESTADOS
     */
    private static final String COLUMN_ID_ESTADOS = "ID_ESTADOS";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_ESTADOS
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO en la
     * tabla FECEC_ESTADOS
     */
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECEC_ESTADOS
     */
    private static final String COLUMN_FECHA_FIN = "FECHAFIN";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECEC_ESTADOS
     */
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ESTADOS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececEstados mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEstados dto = new FececEstados();

        dto.setIdEstado((long) rs.getInt(COLUMN_ID_ESTADOS));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setFechaInicio(rs.getTimestamp(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getTimestamp(COLUMN_FECHA_FIN));
        dto.setActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }

}
