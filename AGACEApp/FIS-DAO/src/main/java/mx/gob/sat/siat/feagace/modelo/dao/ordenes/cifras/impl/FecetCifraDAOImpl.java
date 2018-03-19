package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component("fecetCifraDAO")
public class FecetCifraDAOImpl extends BaseJDBCDao<FecetCifraDTO> implements FecetCifraDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertarCifra(final FecetCifraDTO cifra, final BigDecimal idOrden) {
        BigDecimal idCifra = Constantes.BIG_DECIMAL_CERO;
        try {
            idCifra = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] param = new Object[]{idCifra, idOrden, cifra.getFechaInicio(), cifra.getFechaFin()};
            getJdbcTemplateBase().update(INSERTAR_CIFRA, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifra;
    }

    @Override
    public List<FecetCifraDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra) {
        Object[] params = new Object[]{idOrden, tipoCifra};
        return getJdbcTemplateBase().query(CONSULTA_CIFRAS_ORDEN_TIPO, params, new FecetCifraMapper());
    }

    @Override
    public BigDecimal actualizarEstatusCifra(BigDecimal idDetalleCifra, BigDecimal estatus) {
        BigDecimal idCifra = Constantes.BIG_DECIMAL_CERO;
        try {
            Object[] param = new Object[]{estatus, idDetalleCifra};
            getJdbcTemplateBase().update(ACTUALIZA_ESTATUS, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifra;
    }

    @Override
    public BigDecimal obtenerIdDetalleCifra(BigDecimal idOrden, BigDecimal tipoCifra) {
        BigDecimal idCifra = null;
        try {
            Object[] params = new Object[]{tipoCifra, idOrden};
            idCifra = getJdbcTemplateBase().queryForObject(OBTENER_ID_DETALLE_CIFRA, params, BigDecimal.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return idCifra;
    }

}
