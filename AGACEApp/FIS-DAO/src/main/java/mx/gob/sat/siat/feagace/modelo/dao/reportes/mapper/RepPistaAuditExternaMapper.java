package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;

public class RepPistaAuditExternaMapper implements ParameterizedRowMapper {

    private static final String COLUMN_FECHA = "FECHA";
    private static final String COLUMN_IPMAQUINA = "IPMAQUINA";
    private static final String COLUMN_MODULO = "MODULO";
    private static final String COLUMN_OPERACION = "OPERACION";
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";
    private static final String COLUMN_RFC = "RFC";
    private static final String COLUMN_REP_LEGAL = "REP_LEGAL";
    private static final String COLUMN_APO_LEGAL = "APO_LEGAL";
    private static final String COLUMN_AGE_ADUANAL = "AGE_ADUANAL";
    private static final String COLUMN_APO_LEGAL_REP = "APO_LEGAL_REP_LEGAL";

    public RepPistaAuditExternaMapper() {
        super();
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReportePistaAuditoriaExternaDTO pista = new ReportePistaAuditoriaExternaDTO();

        pista.setFechaYHora(rs.getTimestamp(COLUMN_FECHA));
        pista.setIpMaquina(rs.getString(COLUMN_IPMAQUINA));
        pista.setModIngreso(rs.getString(COLUMN_MODULO));
        pista.setOperRealizada(rs.getString(COLUMN_OPERACION));
        pista.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC)) {
            pista.setRfcContribuyente(rs.getString(COLUMN_RFC));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_REP_LEGAL)) {
            pista.setRfcRepLegal(rs.getString(COLUMN_REP_LEGAL));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_APO_LEGAL)) {
            pista.setRfcApodLegal(rs.getString(COLUMN_APO_LEGAL));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_AGE_ADUANAL)) {
            pista.setRfcAgentAduanal(rs.getString(COLUMN_AGE_ADUANAL));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_APO_LEGAL_REP)) {
            pista.setRfcApodLegalRepLegal(rs.getString(COLUMN_APO_LEGAL_REP));
        }

        return pista;
    }
}
