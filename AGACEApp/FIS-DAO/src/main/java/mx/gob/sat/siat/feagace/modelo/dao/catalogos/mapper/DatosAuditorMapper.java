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

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DatosAuditor;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class DatosAuditorMapper implements ParameterizedRowMapper<DatosAuditor> {

    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return DatosAuditor
     * @throws SQLException
     */
    public DatosAuditor mapRow(ResultSet rs, int rowNum) throws SQLException {
        DatosAuditor auditor = new DatosAuditor();
        auditor.setIdAud(rs.getBigDecimal("ID_AUDITOR"));
        auditor.setRfcAud(rs.getString("RFC"));
        auditor.setNomAud(rs.getString("NOMBRE"));
        auditor.setCorreoAud(rs.getString("CORREO"));

        return auditor;
    }
}
