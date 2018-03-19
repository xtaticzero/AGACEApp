package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetParcialidadCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetParcialidadCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component("fecetParcialidadCifraDAO")
public class FecetParcialidadCifraDAOImpl extends BaseJDBCDao<FecetParcialidadCifraDTO>
        implements FecetParcialidadCifraDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertaParcialidad(final FecetParcialidadCifraDTO parcialidad, final BigDecimal idCifraImpuesto) {
        BigDecimal idParcialidad = Constantes.BIG_DECIMAL_CERO;
        try {
            idParcialidad = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] param = new Object[]{idParcialidad, idCifraImpuesto, parcialidad.getNumeroParcialidades(),
                parcialidad.getTipoParcialidad().getIdParcialidad(), parcialidad.getMontoTotal(),
                parcialidad.getFechaInicio(), parcialidad.getFechaFin()};
            getJdbcTemplateBase().update(INSERTAR_PARCIALIDAD, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idParcialidad;
    }

    @Override
    public FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto) {
        Object[] params = new Object[]{idCifraImpuesto};
        FecetParcialidadCifraDTO parcialidad = null;
        try {
            parcialidad = getJdbcTemplateBase().queryForObject(OBTENER_PARCIALIDAD, params,
                    new FecetParcialidadCifraMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
        }
        return parcialidad;
    }

    @Override
    public BigDecimal actualizarParcialidad(FecetParcialidadCifraDTO parcialidad, BigDecimal idCifraImpuesto) {
        int registrosActualizados = 0;
        try {
            getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] param = new Object[]{parcialidad.getNumeroParcialidades(),
                parcialidad.getTipoParcialidad().getIdParcialidad(), parcialidad.getMontoTotal(), idCifraImpuesto};
            registrosActualizados = getJdbcTemplateBase().update(ACTUALIZAR_PARCIALIDAD, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new BigDecimal(registrosActualizados);
    }

    @Override
    public BigDecimal actualizarEstatusParcialidad(BigDecimal idCifraImpuesto, BigDecimal estatus) {
        try {
            Object[] param = new Object[]{estatus, idCifraImpuesto};
            getJdbcTemplateBase().update(ACTUALIZAR_ESTATUS, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraImpuesto;
    }

    @Override
    public boolean existeParcialidad(BigDecimal idCifraImpuesto) {
        Object[] params = new Object[]{idCifraImpuesto};
        BigDecimal parcialidad = Constantes.BIG_DECIMAL_CERO;
        try {
            parcialidad = getJdbcTemplateBase().queryForObject(EXISTE_PARCIALIDAD, params, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return parcialidad.intValue() > 0;
    }

}
