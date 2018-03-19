package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececPrioridadMapper implements ParameterizedRowMapper<FececPrioridad> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PRIORIDAD en la
     * tabla FECEC_PRIORIDAD
     */
    private static final String COLUMN_ID_PRIORIDAD = "ID_PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna VALOR en la tabla
     * FECEC_PRIORIDAD
     */
    private static final String COLUMN_VALOR = "VALOR";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO en la
     * tabla FECEC_PRIORIDAD
     */
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECEC_PRIORIDAD
     */
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECEC_PRIORIDAD
     */
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_PRIORIDAD
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececPrioridad mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececPrioridad dto = new FececPrioridad();

        dto.setIdPrioridad(rs.getInt(COLUMN_ID_PRIORIDAD));
        dto.setPrioridad(rs.getString(COLUMN_VALOR));
        dto.setFechaInicio(rs.getTimestamp(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getTimestamp(COLUMN_FECHA_FIN));
        dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }
}
