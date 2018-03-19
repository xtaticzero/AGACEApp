package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;

public class FececConceptoMapper implements ParameterizedRowMapper<FececConcepto> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PRIORIDAD en la
     * tabla FECEC_CONCEPTOS
     */
    private static final String COLUMN_ID_CONCEPTO = "ID_CONCEPTO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_CONCEPTOS
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO en la
     * tabla FECEC_CONCEPTOS
     */
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECEC_CONCEPTOS
     */
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECEC_CONCEPTOS
     */
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_CONCEPTOS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececConcepto mapRow(ResultSet rs, int rowNum) throws SQLException {

        FececConcepto dto = new FececConcepto();

        dto.setIdConcepto(rs.getInt(COLUMN_ID_CONCEPTO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaInicio(rs.getTimestamp(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getTimestamp(COLUMN_FECHA_FIN));
        dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));

        return dto;
    }
}
