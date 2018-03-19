package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececSubprogramaMapper implements ParameterizedRowMapper<FececSubprograma> {

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_CLAVE = "CLAVE";

    /**
     * Este atributo corresponde al nombre de la columna ID_GENERAL en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_ID_GENERAL = "ID_GENERAL";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_SUBPROGRAMA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececSubprograma mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececSubprograma dto = new FececSubprograma();

        dto.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setClave(rs.getString(COLUMN_CLAVE));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_GENERAL)) {
            dto.setIdGeneral(rs.getBigDecimal(COLUMN_ID_GENERAL));
        }

        return dto;
    }
}
