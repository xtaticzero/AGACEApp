package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocOrdenMapper implements ParameterizedRowMapper<FecetDocOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_ORDEN en la
     * tabla FECET_DOC_ORDEN
     */
    private static final String COLUMN_ID_DOC_ORDEN = "ID_DOC_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_ORDEN
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna DOCUMENTO_PDF en la
     * tabla FECET_DOC_ORDEN
     */
    private static final String COLUMN_DOCUMENTO_PDF = "DOCUMENTO_PDF";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_ORDEN
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";
    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_FECHA_HORA = "FECHA_HORA";
    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_DOC_ORDEN
     */
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocOrden
     */
    public FecetDocOrden mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetDocOrden dto = new FecetDocOrden();
        dto.setIdDocOrden(rs.getBigDecimal(COLUMN_ID_DOC_ORDEN));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DOCUMENTO_PDF)) {
            dto.setDocumentoPdf(rs.getString(COLUMN_DOCUMENTO_PDF));
        }
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ESTATUS)) {
            dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_TIPO_EMPLEADO)) {
            dto.setDescripcionAccion(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO).toString());
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_EMPLEADO)) {
            dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA_HORA)) {
            dto.setFechaHora(rs.getTimestamp(COLUMN_FECHA_HORA));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_ACTIVO)) {
            dto.setBlnActivo(rs.getInt(COLUMN_BLN_ACTIVO));
        }

        return dto;
    }

}
