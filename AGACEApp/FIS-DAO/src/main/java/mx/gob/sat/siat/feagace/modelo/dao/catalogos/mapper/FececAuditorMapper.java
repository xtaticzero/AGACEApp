/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececAuditorMapper implements ParameterizedRowMapper<FececAuditor> {

    /**
     * Este atributo corresponde al nombre de la columna ID_AUDITOR en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_ID_AUDITOR = "ID_AUDITOR";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_AUDITOR
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
     * FECEC_AUDITOR
     */
    private static final String COLUMN_ESTADO = "ESTADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_AUDITOR
     */
    private static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECEC_AUDITOR de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececAuditor mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececAuditor dto = new FececAuditor();
        dto.setIdAuditor(rs.getBigDecimal(COLUMN_ID_AUDITOR));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setEstado(rs.getString(COLUMN_ESTADO));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        return dto;

    }
}
