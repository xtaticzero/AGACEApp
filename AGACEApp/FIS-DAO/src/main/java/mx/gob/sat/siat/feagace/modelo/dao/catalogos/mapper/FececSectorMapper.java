package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececSectorMapper implements ParameterizedRowMapper<FececSector> {

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECEC_SECTOR
     */
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_GENERAL en la tabla
     * FECEC_SECTOR
     */
    private static final String COLUMN_ID_GENERAL = "ID_GENERAL";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_SECTOR
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_SECTOR
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececSector mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececSector dto = new FececSector();

        dto.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_GENERAL)) {
            dto.setIdGeneral(rs.getBigDecimal(COLUMN_ID_GENERAL));
        }

        return dto;
    }
}
