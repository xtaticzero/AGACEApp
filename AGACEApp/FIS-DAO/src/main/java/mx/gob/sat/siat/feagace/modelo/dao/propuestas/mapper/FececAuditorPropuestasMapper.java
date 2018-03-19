package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececAuditorPropuestasMapper implements ParameterizedRowMapper<FececAuditor> {

    /**
     * Este atributo corresponde al nombre de la columna ID_AUDITOR en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_ID_AUDITOR = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECEC_AUDITOR
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna ESTADO en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_ESTADO = "ID_ESTATUS_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_EMPLEADO
     */
    private static final String COLUMN_ID_FIRMANTE = "ID_SUBORDINADO";

    public FececAuditor mapRow(ResultSet rs, int rowNum) throws SQLException {

        FececAuditor auditor = new FececAuditor();
        auditor.setIdAuditor(rs.getBigDecimal(COLUMN_ID_AUDITOR));
        auditor.setRfc(rs.getString(COLUMN_RFC));
        auditor.setNombre(rs.getString(COLUMN_NOMBRE));
        auditor.setCorreo(rs.getString(COLUMN_CORREO));
        auditor.setEstado(rs.getString(COLUMN_ESTADO));
        auditor.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        auditor.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        auditor.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        auditor.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));

        return auditor;
    }
}
