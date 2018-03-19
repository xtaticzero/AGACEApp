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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocProrrogaOrdenMapper implements ParameterizedRowMapper<FecetDocProrrogaOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_PRORROGA_ORDEN
     * en la tabla FECET_DOC_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_DOC_PRORROGA_ORDEN = "ID_DOC_PRORROGA_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_PRORROGA_ORDEN en la
     * tabla FECET_DOC_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_PRORROGA_ORDEN = "ID_PRORROGA_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_DOC_PRORROGA_ORDEN
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_PRORROGA_ORDEN
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Metodo mapRow Hace un mapeo de los datos en la tabla
     * FECET_DOC_PRORROGA_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetDocProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocProrrogaOrden dto = new FecetDocProrrogaOrden();

        dto.setIdDocProrrogaOrden(rs.getBigDecimal(COLUMN_ID_DOC_PRORROGA_ORDEN));
        dto.setIdProrrogaOrden(rs.getBigDecimal(COLUMN_ID_PRORROGA_ORDEN));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));

        return dto;
    }
}
