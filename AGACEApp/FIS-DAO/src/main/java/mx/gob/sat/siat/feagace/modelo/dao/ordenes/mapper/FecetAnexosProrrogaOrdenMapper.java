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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAnexosProrrogaOrdenMapper implements ParameterizedRowMapper<FecetAnexosProrrogaOrden> {

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_ANEXOS_PRORROGA_ORDEN en la tabla FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_ID_ANEXOS_PRORROGA_ORDEN = "ID_ANEXOS_PRORROGA_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_FLUJO_PRORROGA_ORDEN
     * en la tabla FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_ID_FLUJO_PRORROGA_ORDEN = "ID_FLUJO_PRORROGA_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_ANEXO en la tabla
     * FECET_ANEXOS_PROMOCION_ORDEN
     */
    public static final String COLUMN_TIPO_ANEXO = "TIPO_ANEXO";

    /**
     * Metodo mapRow Hace un mapeo de los datos en la tabla
     * FECET_ANEXOS_PROMOCION_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetAnexosProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAnexosProrrogaOrden dto = new FecetAnexosProrrogaOrden();

        dto.setIdAnexosProrrogaOrden(rs.getBigDecimal(COLUMN_ID_ANEXOS_PRORROGA_ORDEN));
        dto.setIdFlujoProrrogaOrden(rs.getBigDecimal(COLUMN_ID_FLUJO_PRORROGA_ORDEN));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setTipoAnexo(rs.getString(COLUMN_TIPO_ANEXO));

        return dto;
    }
}
