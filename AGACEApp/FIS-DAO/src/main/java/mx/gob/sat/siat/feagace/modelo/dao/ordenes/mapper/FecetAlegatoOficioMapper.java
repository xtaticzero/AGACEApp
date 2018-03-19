package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAlegatoOficioMapper implements ParameterizedRowMapper<FecetAlegatoOficio> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ALEGATO en la tabla
     * FECET_ALEGATO
     */
    public static final String COLUMN_ID_ALEGATO_OFICIO = "ID_ALEGATO_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROMOCION en la
     * tabla FECET_ALEGATO
     */
    public static final String COLUMN_ID_PROMOCION_OFICIO = "ID_PROMOCION_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_ALEGATO
     */
    public static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_ALEGATO
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_ALEGATO
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    @Override
    public FecetAlegatoOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetAlegatoOficio dto = new FecetAlegatoOficio();
        dto.setIdAlegatoOficio(rs.getBigDecimal(COLUMN_ID_ALEGATO_OFICIO));
        dto.setIdPromocionOficio(rs.getBigDecimal(COLUMN_ID_PROMOCION_OFICIO));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(dto.getRutaArchivo()));
        return dto;
    }
}
