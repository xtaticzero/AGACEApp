package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoSuplenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececMotivoSuplenciaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececMotivoSuplencia;
import org.springframework.stereotype.Repository;

@Repository("fececMotivoSuplenciaDAO")
public class FececMotivoSuplenciaDAOImpl extends BaseJDBCDao<FececMotivoSuplencia> implements FececMotivoSuplenciaDAO {

    @SuppressWarnings("compatibility:-1980439806003978315")
    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = "SELECT ID_MOTIVO_SUPLENCIA, DESCRIPCION  FROM " + getTableName();

    public static String getTableName() {
        return "FECEC_MOTIVO_SUPLENCIA";
    }

    public FececMotivoSuplencia findByPrimaryKey(BigDecimal idMotivo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO_SUPLENCIA = ?");
        List<FececMotivoSuplencia> list = getJdbcTemplateBase().query(query.toString(), new FececMotivoSuplenciaMapper(), idMotivo);
        return list.size() == 0 ? null : list.get(0);

    }

    public List<FececMotivoSuplencia> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_MOTIVO_SUPLENCIA");
        return getJdbcTemplateBase().query(query.toString(), new FececMotivoSuplenciaMapper());

    }
}
