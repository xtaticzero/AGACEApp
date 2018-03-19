package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEstatusMapper implements ParameterizedRowMapper<FececEstatus> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECEC_ESTATUS
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_ESTATUS
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna MODULO en la tabla
     * FECEC_ESTATUS
     */
    private static final String COLUMN_MODULO = "MODULO";

    private static final String COLUMN_DESC_ESTATUS = "DESCRIPCION_ESTATUS";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_ESTATUS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececEstatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEstatus dto = new FececEstatus();

        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESC_ESTATUS)) {
            dto.setDescripcion(rs.getString(COLUMN_DESC_ESTATUS));
        } else {
            dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        }
        dto.setModulo(rs.getString(COLUMN_MODULO));

        return dto;
    }
}
