package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetPromocionOficioMapper implements ParameterizedRowMapper<FecetPromocionOficio> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROMOCION en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_ID_PROMOCION_OFICIO = "ID_PROMOCION_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_PROMOCION
     */
    public static final String COLUMN_ID_OFICIO = "ID_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_PROMOCION
     */
    public static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE_CARGA
     * en la tabla FECET_PROMOCION
     */
    public static final String COLUMN_ID_ASOCIADO_CARGA = "ID_ASOCIADO_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ACUSE en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_NOMBRE_ACUSE = "NOMBRE_ACUSE";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ACUSE en la tabla
     * FECET_PROMOCION
     */
    public static final String COLUMN_RUTA_ACUSE = "RUTA_ACUSE";

    /**
     * Este atributo corresponde al nombre de la columna EXTEMPORANEA en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_EXTEMPORANEA = "EXTEMPORANEA";

    public static final String COLUMN_CADENA_ORIGINAL = "CADENA_ORIGINAL";

    public static final String COLUMN_FIRMA_ELECTRONICA = "FIRMA_ELECTRONICA";

    @Override
    public FecetPromocionOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetPromocionOficio dto = new FecetPromocionOficio();
        dto.setIdPromocionOficio(rs.getBigDecimal(COLUMN_ID_PROMOCION_OFICIO));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(dto.getRutaArchivo()));
        dto.setIdAsociadoCarga(rs.getBigDecimal(COLUMN_ID_ASOCIADO_CARGA));
        dto.setRutaAcuse(rs.getString(COLUMN_RUTA_ACUSE));
        dto.setNombreAcuse(UtileriasMapperDao.getNameFileFromPath(dto.getRutaAcuse()));
        dto.setExtemporanea(rs.getString(COLUMN_EXTEMPORANEA));
        dto.setCadenaOriginal(rs.getString(COLUMN_CADENA_ORIGINAL));
        dto.setFirmaElectronica(rs.getString(COLUMN_FIRMA_ELECTRONICA));

        return dto;
    }
}
