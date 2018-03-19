package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAdminOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAdminOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficioPk;

@Repository("fecetAdminOficio")
public class FecetAdminOficioDaoImpl extends BaseJDBCDao<FecetAdminOficio> implements FecetAdminOficioDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String getTableName() {
        return "FECEC_ADMIN_OFICIO";
    }

    public FecetAdminOficioPk insert(FecetAdminOficio dto) {
        if (dto.getIdAdminOficio() == null) {
            dto.setIdAdminOficio(getJdbcTemplateBase().queryForObject("SELECT FECEQ_ADMINOFICIO.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }

        getJdbcTemplateBase().update(
                "INSERT INTO " + getTableName()
                + " ( ID_ADMIN_OFICIO, BLN_DOC_REQ, BLN_PRO, BLN_PLAZOS, BLN_ACTIVO, FECHA ) VALUES ( ?, ?, ?, ?, ?,  SYSDATE )",
                dto.getIdAdminOficio(), dto.isBlnDocReq(), dto.isBlnDocPro(), dto.isBlnPlazos(), dto.isBlnActivo());
        return dto.createPk();
    }

    @Override
    public List<FecetAdminOficio> getAdminOficiosActivos() {

        StringBuilder sqlSelect = new StringBuilder();

        sqlSelect.append(
                "SELECT FAO.ID_ADMIN_OFICIO, FAO.BLN_DOC_REQ, FAO.BLN_DOC_PRO, FAO.BLN_PLAZOS, FAO.BLN_ACTIVO, FAO.FECHA FROM \n");
        sqlSelect.append(getTableName());
        sqlSelect.append(" FAO WHERE FAO.BLN_ACTIVO = 1 AND FAO.BLN_DOC_REQ = 1");

        return getJdbcTemplateBase().query(sqlSelect.toString(), new FecetAdminOficioMapper());
    }

}
