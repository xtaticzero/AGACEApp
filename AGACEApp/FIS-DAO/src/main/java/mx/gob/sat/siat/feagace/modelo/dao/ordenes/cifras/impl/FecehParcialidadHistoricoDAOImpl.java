package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehParcialidadHistoricoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetParcialidadHistoricoCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class FecehParcialidadHistoricoDAOImpl extends BaseJDBCDao<FecetParcialidadCifraDTO>
        implements FecehParcialidadHistoricoDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertarRegistro(BigDecimal idCifra, BigDecimal idCifraHistorico) {
        BigDecimal idHistorico = Constantes.BIG_DECIMAL_CERO;
        try {
            idHistorico = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idHistorico, idCifraHistorico, idCifra};
            getJdbcTemplateBase().update(INSERT_DOC_HIS_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idHistorico;
    }

    @Override
    public FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto) {
        Object[] params = new Object[]{idCifraImpuesto};
        FecetParcialidadCifraDTO parcialidad = null;
        try {
            parcialidad = getJdbcTemplateBase().queryForObject(OBTENER_PARCIALIDAD, params,
                    new FecetParcialidadHistoricoCifraMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
        }
        return parcialidad;
    }

}
