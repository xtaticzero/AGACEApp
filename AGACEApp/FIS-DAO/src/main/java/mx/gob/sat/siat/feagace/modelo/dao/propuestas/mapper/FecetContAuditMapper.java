/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAudit;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetContAuditMapper implements ParameterizedRowMapper<FecetContAudit> {

    /**
     * Este atributo corresponde al nombre de la columna ID_CONT_AUDIT en la
     * tabla FECET_CONT_AUDIT
     */
    private static final String COLUMN_ID_CONT_AUDIT = "ID_CONT_AUDIT";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_CONT_AUDIT
     */
    private static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_CONT_AUDIT
     */
    private static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_CONT_AUDIT
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RFC_AUDITOR en la tabla
     * FECET_CONT_AUDIT
     */
    private static final String COLUMN_RFC_AUDITOR = "RFC_AUDITOR";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_AUDITOR en la
     * tabla FECET_CONT_AUDIT
     */
    private static final String COLUMN_NOMBRE_AUDITOR = "NOMBRE_AUDITOR";

    /**
     * Este método hace un mapeo de los datos almacenados en la tabla
     * FECET_REQUERIMIENTO de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetContAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetContAudit dto = new FecetContAudit();
        dto.setIdContAudit(rs.getBigDecimal(COLUMN_ID_CONT_AUDIT));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setNombreArchivo(rs.getString(COLUMN_NOMBRE_ARCHIVO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setRfcAuditor(rs.getString(COLUMN_RFC_AUDITOR));
        dto.setNombreAuditor(rs.getString(COLUMN_NOMBRE_AUDITOR));
        return dto;

    }
}
