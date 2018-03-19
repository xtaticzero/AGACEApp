package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetSuplenciaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetSuplenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;

import org.springframework.stereotype.Repository;

@Repository("fecetSuplenciaDao")
public class FecetSuplenciaDaoImpl extends BaseJDBCDao<FecetSuplencia> implements FecetSuplenciaDao {

    @SuppressWarnings("compatibility:717364380543817761")
    private static final long serialVersionUID = -973741781490052586L;

    @Override
    public List<FecetSuplencia> obtenerSuplentesPorSuplente(BigDecimal suplente) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT S.ID_SUPLENCIA, S.ID_FIRMANTE_A_SUPLIR, S.ID_FIRMANTE_SUPLENTE, S.ID_MOTIVO_SUPLENCIA, S.FECHA_INICIO, S.FECHA_FIN ");
        query.append("FROM FECET_SUPLENCIA S");
        query.append(" WHERE ID_FIRMANTE_SUPLENTE = ? ");
        query.append("AND ID_ESTATUS_SUPLENCIA IN (1,2) ");
        return getJdbcTemplateBase().query(query.toString(), new FecetSuplenciaMapper(), suplente);
    }
}
