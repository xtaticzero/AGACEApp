package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRechazoPropuestaVerifProcedenciaMapper implements ParameterizedRowMapper<FecetRechazoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_PROPUESTA en
     * la tabla FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_RECHAZO_PROPUESTA = "ID_RECHAZO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RECHAZO en la tabla
     * FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_RFC_RECHAZO = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INFORME_RECHAZO
     * en la tabla FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_FECHA_INFORME_RECHAZO = "FECHA_INFORME_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_DESCRIPCION_RECHAZO = "DESCRIPCION_RECHAZO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_RECHAZO_PROPUESTA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRechazoPropuesta dto = new FecetRechazoPropuesta();

        dto.setIdRechazoPropuesta(rs.getBigDecimal(COLUMN_ID_RECHAZO_PROPUESTA));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        dto.setRfcRechazo(rs.getString(COLUMN_RFC_RECHAZO));
        dto.setFechaInformeRechazo(rs.getDate(COLUMN_FECHA_INFORME_RECHAZO));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_RECHAZO)) {
            dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION_RECHAZO));
        }

        return dto;
    }
}
