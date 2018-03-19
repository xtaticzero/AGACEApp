package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetFlujoProrrogaOficioMapper implements ParameterizedRowMapper<FecetFlujoProrrogaOficio> {

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_FLUJO_PRORROGA_OFICIO en la tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_ID_FLUJO_PRORROGA_OFICIO = "ID_FLUJO_PRORROGA_OFICIO";
    /**
     * Este atributo corresponde al nombre de la columna ID_PRORROGA_OFICIO en
     * la tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_ID_PRORROGA_OFICIO = "ID_PRORROGA_OFICIO";
    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    /**
     * Este atributo corresponde al nombre de la columna JUSTIFICACION en la
     * tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_JUSTIFICACION = "JUSTIFICACION";
    /**
     * Este atributo corresponde al nombre de la columna JUSTIFICACION_FIRMANTE
     * en la tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_JUSTIFICACION_FIRMANTE = "JUSTIFICACION_FIRMANTE";
    /**
     * Este atributo corresponde al nombre de la columna APROBADA en la tabla
     * FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_APROBADA = "APROBADA";
    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO_FIRMANTE
     * en la tabla FECET_FLUJO_PRORROGA_OFICIO
     */
    private static final String COLUMN_FECHA_RECHAZO_FIRMANTE = "FECHA_RECHAZO_FIRMANTE";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_FLUJO_PRORROGA_OFICIO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetFlujoProrrogaOficio mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetFlujoProrrogaOficio dto = new FecetFlujoProrrogaOficio();

        dto.setIdFlujoProrrogaOficio(rs.getBigDecimal(COLUMN_ID_FLUJO_PRORROGA_OFICIO));
        dto.setIdProrrogaOficio(rs.getBigDecimal(COLUMN_ID_PRORROGA_OFICIO));
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
