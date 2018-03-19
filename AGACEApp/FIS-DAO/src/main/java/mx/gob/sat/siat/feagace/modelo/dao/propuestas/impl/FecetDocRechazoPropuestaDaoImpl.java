package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocRechazoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.DocRechazoPropuestaSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

import org.springframework.stereotype.Repository;

@Repository("fecetDocRechazoPropuestaDao")
public class FecetDocRechazoPropuestaDaoImpl extends
        BaseJDBCDao<FecetDocExpediente> implements FecetDocRechazoPropuestaDao {

    @SuppressWarnings("compatibility:-1331268212725006616")
    private static final long serialVersionUID = -9083018929778202479L;

    @Override
    public List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(DocRechazoPropuestaSQL.SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocRechazoPropuestaMapper(), idPropuesta);
    }

    @Override
    public List<FecetDocExpediente> findWhereIdRechazoPropuestaEquals(BigDecimal idRechazoPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(DocRechazoPropuestaSQL.SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_PROPUESTA = ? ORDER BY FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocRechazoPropuestaMapper(), idRechazoPropuesta);
    }

    @Override
    public List<FecetDocExpediente> obtenerDoctosRechazoByIdPropuesta(BigDecimal idRechazoPropuesta, BigDecimal blnEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(DocRechazoPropuestaSQL.SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_PROPUESTA = ? AND BLN_ACTIVO = ? ORDER BY FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocRechazoPropuestaMapper(), idRechazoPropuesta, blnEstatus);
    }
}
