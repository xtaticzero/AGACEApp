package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficio;

public class FecetAdminOficioMapper implements ParameterizedRowMapper<FecetAdminOficio> {

    private static final String COLUMN_ID_ADMIN_OFICIO = "ID_ADMIN_OFICIO";
    private static final String COLUMN_BLN_DOC_REQ = "BLN_DOC_REQ";
    private static final String COLUMN_BLN_DOC_PRO = "BLN_DOC_PRO";
    private static final String COLUMN_BLN_PLAZOS = "BLN_PLAZOS";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_BLN_ACTIVO_ALIAS = "ADMIN_ACTIVO";
    private static final String COLUMN_FECHA = "FECHA";

    @Override
    public FecetAdminOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetAdminOficio adminOficio = new FecetAdminOficio();
        adminOficio.setIdAdminOficio(rs.getBigDecimal(COLUMN_ID_ADMIN_OFICIO));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_DOC_REQ)) {
            adminOficio.setBlnDocReq(isActive(rs.getBigDecimal(COLUMN_BLN_DOC_REQ)));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_DOC_PRO)) {
            adminOficio.setBlnDocPro(isActive(rs.getBigDecimal(COLUMN_BLN_DOC_PRO)));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_PLAZOS)) {
            adminOficio.setBlnPlazos(isActive(rs.getBigDecimal(COLUMN_BLN_PLAZOS)));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_ACTIVO)) {
            adminOficio.setBlnActivo(isActive(rs.getBigDecimal(COLUMN_BLN_ACTIVO)));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA)) {
            adminOficio.setFecha(rs.getDate(COLUMN_FECHA));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_ACTIVO_ALIAS)) {
            adminOficio.setBlnActivo(isActive(rs.getBigDecimal(COLUMN_BLN_ACTIVO_ALIAS)));
        }
        return adminOficio;
    }

    private boolean isActive(BigDecimal element) {
        return element != null && !BigDecimal.ZERO.equals(element);
    }
}
