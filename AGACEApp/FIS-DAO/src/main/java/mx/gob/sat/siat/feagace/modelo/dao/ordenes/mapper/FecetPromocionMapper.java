/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetPromocionMapper implements ParameterizedRowMapper<FecetPromocion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROMOCION en la
     * tabla FECET_PROMOCION
     */
    public static final String COLUMN_ID_PROMOCION = "ID_PROMOCION";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_PROMOCION
     */
    public static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_PROMOCION
     */
    public static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

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

    /**
     * Metodo mapRow Hacde un mapeo de los datos en la tabla FECET_PROMOCION
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetPromocion mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPromocion dto = new FecetPromocion();

        dto.setIdPromocion(rs.getBigDecimal(COLUMN_ID_PROMOCION));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setIdAsociadoCarga(rs.getBigDecimal(COLUMN_ID_ASOCIADO_CARGA));
        dto.setRutaAcuse(rs.getString(COLUMN_RUTA_ACUSE));
        dto.setExtemporanea(rs.getString(COLUMN_EXTEMPORANEA));
        dto.setCadenaOriginal(rs.getString(COLUMN_CADENA_ORIGINAL));
        dto.setFirmaElectronica(rs.getString(COLUMN_FIRMA_ELECTRONICA));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(dto.getRutaArchivo()));
        dto.setNombreAcuse(UtileriasMapperDao.getNameFileFromPath(dto.getRutaAcuse()));

        return dto;
    }

}
