package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusSuplenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececEstatusSuplenciaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEstatusSuplencia;

import org.springframework.stereotype.Repository;

@Repository("fececEstatusSuplenciaDAO")
public class FececEstatusSuplenciaDAOImpl extends BaseJDBCDao<FececEstatusSuplencia> implements FececEstatusSuplenciaDAO {

    @SuppressWarnings("compatibility:-9059071489207396291")
    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = "SELECT ID_ESTATUS_SUPLENCIA, DESCRIPCION  FROM " + getTableName();

    public static String getTableName() {
        return "FECEC_ESTATUS_SUPLENCIA";
    }

    public FececEstatusSuplencia findByPrimaryKey(BigDecimal idMotivo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ESTATUS_SUPLENCIA = ?");
        List<FececEstatusSuplencia> list = getJdbcTemplateBase().query(query.toString(), new FececEstatusSuplenciaMapper(), idMotivo);
        return list.size() == 0 ? null : list.get(0);

    }

    public List<FececEstatusSuplencia> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_ESTATUS_SUPLENCIA");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusSuplenciaMapper());

    }

}
