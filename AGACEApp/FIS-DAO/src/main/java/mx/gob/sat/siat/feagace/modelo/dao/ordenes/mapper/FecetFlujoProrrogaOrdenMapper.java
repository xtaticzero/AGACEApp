package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetFlujoProrrogaOrdenMapper implements ParameterizedRowMapper<FecetFlujoProrrogaOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_FLUJO_PRORROGA_ORDEN
     * en la tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_ID_FLUJO_PRORROGA_ORDEN = "ID_FLUJO_PRORROGA_ORDEN";
    /**
     * Este atributo corresponde al nombre de la columna ID_PRORROGA_ORDEN en la
     * tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_ID_PRORROGA_ORDEN = "ID_PRORROGA_ORDEN";
    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    /**
     * Este atributo corresponde al nombre de la columna JUSTIFICACION en la
     * tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_JUSTIFICACION = "JUSTIFICACION";
    /**
     * Este atributo corresponde al nombre de la columna JUSTIFICACION_FIRMANTE
     * en la tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_JUSTIFICACION_FIRMANTE = "JUSTIFICACION_FIRMANTE";
    /**
     * Este atributo corresponde al nombre de la columna APROBADA en la tabla
     * FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_APROBADA = "APROBADA";
    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO_FIRMANTE
     * en la tabla FECET_FLUJO_PRORROGA_ORDEN
     */
    private static final String COLUMN_FECHA_RECHAZO_FIRMANTE = "FECHA_RECHAZO_FIRMANTE";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_FLUJO_PRORROGA_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetFlujoProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetFlujoProrrogaOrden dto = new FecetFlujoProrrogaOrden();
        dto.setIdFlujoProrrogaOrden(rs.getBigDecimal(COLUMN_ID_FLUJO_PRORROGA_ORDEN));
        dto.setIdProrrogaOrden(rs.getBigDecimal(COLUMN_ID_PRORROGA_ORDEN));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setJustificacion(rs.getString(COLUMN_JUSTIFICACION));
        dto.setJustificacionFirmante(rs.getString(COLUMN_JUSTIFICACION_FIRMANTE));
        if (UtileriasMapperDao.existeColumna(rs, "FLUJO_APROBADA")) {
            dto.setAprobada(rs.getInt("FLUJO_APROBADA") == 1 ? true : false);
        } else {
            dto.setAprobada(rs.getInt(COLUMN_APROBADA) == 1 ? true : false);
        }
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setFechaRechazoFirmante(rs.getTimestamp(COLUMN_FECHA_RECHAZO_FIRMANTE));
        return dto;
    }
}
