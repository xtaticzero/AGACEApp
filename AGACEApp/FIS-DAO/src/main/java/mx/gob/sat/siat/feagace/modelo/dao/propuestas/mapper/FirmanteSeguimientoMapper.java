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

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentosFirmante;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FirmanteSeguimientoMapper implements ParameterizedRowMapper<DocumentosFirmante> {

    /**
     * Metodo mapRow Hace un mapeo con lo datos de las tabla de
     * DOCUMENTOS_FIRMANTE
     *
     * @param rs
     * @param rowNum
     * @return DocumentosFirmante
     * @throws SQLException
     */
    public DocumentosFirmante mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentosFirmante documento = new DocumentosFirmante();
        documento.setIdOrden(rs.getBigDecimal("ID_ORDEN"));
        documento.setCveOrdenDoc(rs.getString("CVE_ORDEN"));
        documento.setNombreDoc(rs.getString("NOMBRE_ARCHIVO"));
        documento.setRutaDoc(rs.getString("RUTA_ARCHIVO"));
        documento.setEstadoDoc(rs.getString("ESTADO"));
        documento.setFechaDoc(rs.getDate("FECHA_CARGA"));
        documento.setIdDocumento(rs.getBigDecimal("ID_DOCUMENTO"));
        return documento;
    }
}
