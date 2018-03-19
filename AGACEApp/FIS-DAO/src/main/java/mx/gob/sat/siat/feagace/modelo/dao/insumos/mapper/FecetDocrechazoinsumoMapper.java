package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocrechazoinsumoMapper implements
        ParameterizedRowMapper<FecetDocrechazoinsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOCRECHAZOINSUMO en
     * la tabla FECET_DOCRECHAZOINSUMO
     */
    private static final String COLUMN_ID_DOCRECHAZOINSUMO = "ID_DOCRECHAZOINSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_INSUMO en la
     * tabla FECET_DOCRECHAZOINSUMO
     */
    private static final String COLUMN_ID_RECHAZO_INSUMO = "ID_RECHAZO_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOCRECHAZOINSUMO
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOCRECHAZOINSUMO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocrechazoinsumo
     */
    public FecetDocrechazoinsumo mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetDocrechazoinsumo dto = new FecetDocrechazoinsumo();

        dto.setIdDocrechazoinsumo(rs.getBigDecimal(COLUMN_ID_DOCRECHAZOINSUMO));
        dto.setIdRechazoInsumo(rs.getBigDecimal(COLUMN_ID_RECHAZO_INSUMO));
        dto.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(rs
                .getString(COLUMN_RUTA_ARCHIVO)));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs
                .getString(COLUMN_RUTA_ARCHIVO)));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));

        return dto;

    }

}
