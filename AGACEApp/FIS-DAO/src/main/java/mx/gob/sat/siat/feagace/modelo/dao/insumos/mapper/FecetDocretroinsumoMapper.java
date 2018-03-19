package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocretroinsumoMapper implements ParameterizedRowMapper<FecetDocretroinsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOCRETROINSUMO en la
     * tabla FECET_DOCRETROINSUMO
     */
    private static final String COLUMN_ID_DOCRETROINSUMO = "ID_DOCRETROINSUMO";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_RETROALIMENTACION_INSUMO en la tabla FECET_DOCRETROINSUMO
     */
    private static final String COLUMN_ID_RETROALIMENTACION_INSUMO = "ID_RETROALIMENTACION_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOCRETROINSUMO
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOCRETROINSUMO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    private static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocretroinsumo
     */
    public FecetDocretroinsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocretroinsumo dto = new FecetDocretroinsumo();

        dto.setIdDocretroinsumo(rs.getBigDecimal(COLUMN_ID_DOCRETROINSUMO));
        dto.setIdRetroalimentacionInsumo(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION_INSUMO));
        dto.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(rs
                .getString(COLUMN_RUTA_ARCHIVO)));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs
                .getString(COLUMN_RUTA_ARCHIVO)));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setIdTipoEmpleado(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO));
        return dto;
    }
}
