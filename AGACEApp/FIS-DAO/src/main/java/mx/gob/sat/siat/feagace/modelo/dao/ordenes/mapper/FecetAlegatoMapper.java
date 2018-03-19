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
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAlegatoMapper implements ParameterizedRowMapper<FecetAlegato> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ALEGATO en la tabla
     * FECET_ALEGATO
     */
    public static final String COLUMN_ID_ALEGATO = "ID_ALEGATO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROMOCION en la
     * tabla FECET_ALEGATO
     */
    public static final String COLUMN_ID_PROMOCION = "ID_PROMOCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_ALEGATO
     */
    public static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_ALEGATO
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Metodo rowMap Hace un un mapeo y un set de los datos de la tabla
     * FECET_ALEGATO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetAlegato mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAlegato dto = new FecetAlegato();

        dto.setIdAlegato(rs.getBigDecimal(COLUMN_ID_ALEGATO));
        dto.setIdPromocion(rs.getBigDecimal(COLUMN_ID_PROMOCION));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(dto.getRutaArchivo()));

        return dto;
    }
}
