package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehHistoricoCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCifraImpuestoConceptoHistoricoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class FecehHistoricoCifraDAOImpl extends BaseJDBCDao<FeceaCifraTipoCifraDTO> implements FecehHistoricoCifraDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertarRegistro(FeceaCifraImpuestoDTO cifraImpuesto, BigDecimal consecutivo) {
        BigDecimal idHistorico = Constantes.BIG_DECIMAL_CERO;
        try {
            idHistorico = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idHistorico, cifraImpuesto.getIdCifraImpuesto(), cifraImpuesto.getFechaPago(),
                cifraImpuesto.getImporte(), cifraImpuesto.getActualizaciones(), cifraImpuesto.getMultas(),
                cifraImpuesto.getRecargos(), cifraImpuesto.getTotal(), cifraImpuesto.getObservaciones(),
                cifraImpuesto.getFechaInicio(), cifraImpuesto.getFechaFin(), consecutivo};
            getJdbcTemplateBase().update(INSERT_HIS_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idHistorico;
    }

    @Override
    public BigDecimal obtenerConsecutivo(BigDecimal idCifraImpuesto) {
        BigDecimal consecutivo = Constantes.BIG_DECIMAL_CERO;
        try {
            Object[] param = new Object[]{idCifraImpuesto};
            consecutivo = getJdbcTemplateBase().queryForObject(SELECT_CONSECUTIVO, param, BigDecimal.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return consecutivo;
    }

    @Override
    public List<FecetCifraDTO> obtenerEncabezadoHistorico(BigDecimal idCifra, BigDecimal idOrden) {
        Object[] params = new Object[]{idCifra, idOrden};
        return getJdbcTemplateBase().query(OBTENER_CIFRAS_HISTORICO_ENCABEZADO, params, new FecetCifraMapper());
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifraHist(BigDecimal idOrden,
            BigDecimal tipoCifraTipoCifra, BigDecimal consecutivo) {
        Object[] params = new Object[]{tipoCifraTipoCifra, consecutivo, idOrden};
        return getJdbcTemplateBase().query(OBTENER_CIFRAS_IMPUESTOS, params,
                new FecetCifraImpuestoConceptoHistoricoMapper());
    }

}
