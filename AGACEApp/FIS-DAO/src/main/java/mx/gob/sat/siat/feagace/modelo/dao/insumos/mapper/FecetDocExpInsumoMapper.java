package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocExpInsumoMapper implements ParameterizedRowMapper<FecetDocExpInsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_EXP_INSUMO en la
     * tabla FECET_DOC_EXP_INSUMO
     */
    private static final String COLUMN_ID_DOC_EXP_INSUMO = "ID_DOC_EXP_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_DOC_EXP_INSUMO
     */
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_RUTA_ARCHIVO
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_RUTA_ARCHIVO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FecetDocExpInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetDocExpInsumo dto = new FecetDocExpInsumo();
        dto.setIdDocExpInsumo(rs.getBigDecimal(COLUMN_ID_DOC_EXP_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setNombre(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        return dto;
    }
}
