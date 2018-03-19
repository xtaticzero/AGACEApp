package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class RepPistaAuditInternaMapper implements ParameterizedRowMapper {

    private static final String FECHA = "FECHA";
    private static final String NOMBRE = "NOMBRE";
    private static final String RFC = "RFC";
    private static final String IPMAQUINA = "IPMAQUINA";
    private static final String NOMBREMAQUINA = "NOMBREMAQUINA";
    private static final String MODULO = "MODULO";
    private static final String OPERACION = "OPERACION";
    private static final String ID_REGISTRO = "ID_REGISTRO";
    private static final String ID_EMPLEADO = "ID_EMPLEADO";

    public RepPistaAuditInternaMapper() {
        super();
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReportePistaAuditoriaInternaDTO pista = new ReportePistaAuditoriaInternaDTO();

        if (UtileriasMapperDao.existeColumna(rs, FECHA)) {
            pista.setFechaYHora(rs.getTimestamp("FECHA"));
        }
        if (UtileriasMapperDao.existeColumna(rs, RFC)) {
            pista.setRfcUsuario(rs.getString("RFC"));
        }
        if (UtileriasMapperDao.existeColumna(rs, NOMBRE)) {
            pista.setNomUsuario(rs.getString("NOMBRE"));
        }
        if (UtileriasMapperDao.existeColumna(rs, IPMAQUINA)) {
            pista.setIpMaquina(rs.getString("IPMAQUINA"));
        }
        if (UtileriasMapperDao.existeColumna(rs, NOMBREMAQUINA)) {
            pista.setNomMaquina(rs.getString("NOMBREMAQUINA"));
        }
        if (UtileriasMapperDao.existeColumna(rs, MODULO)) {
            pista.setModIngreso(rs.getString("MODULO"));
        }
        if (UtileriasMapperDao.existeColumna(rs, OPERACION)) {
            pista.setOperRealizada(rs.getString("OPERACION"));
        }
        if (UtileriasMapperDao.existeColumna(rs, ID_REGISTRO)) {
            pista.setIdRegistro(rs.getString("ID_REGISTRO"));
        }
        if (UtileriasMapperDao.existeColumna(rs, ID_EMPLEADO)) {
            pista.setIdEmpleado(rs.getBigDecimal("ID_EMPLEADO"));
        }

        return pista;
    }
}
