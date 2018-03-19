package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FeceaCifraImpuestoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCifraImpuestoConceptoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component("feceaCifraImpuestoDAOImpl")
public class FeceaCifraImpuestoDAOImpl extends BaseJDBCDao<FeceaCifraImpuestoDTO> implements FeceaCifraImpuestoDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertaCifra(final FeceaCifraImpuestoDTO cifra, final BigDecimal idCifra) {
        BigDecimal idCifraDetalle = Constantes.BIG_DECIMAL_CERO;
        try {
            idCifraDetalle = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idCifraDetalle, idCifra,
                cifra.getImpuestoConcepto().getImpuesto().getIdTipoImpuesto(),
                cifra.getImpuestoConcepto().getConcepto().getIdConcepto(), cifra.getTipoCifra().getIdCifra(),
                cifra.getFechaPago(), cifra.getImporte(), cifra.getActualizaciones(), cifra.getMultas(),
                cifra.getRecargos(), cifra.getObservaciones(), cifra.getFechaInicio(), cifra.getFechaFin(),
                cifra.getDerivaAntecedente()};
            getJdbcTemplateBase().update(INSERTA_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraDetalle;
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraImpuestoConcepto(BigDecimal idOrden,
            BigDecimal tipoCifra, BigDecimal impuesto, BigDecimal concepto) {
        Object[] params = new Object[]{idOrden, impuesto, concepto, tipoCifra};
        return getJdbcTemplateBase().query(OBTENER_CIFRAS_METODO, params, new FecetCifraImpuestoConceptoMapper());

    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifra(BigDecimal idOrden,
            BigDecimal tipoCifraTipoCifra) {
        Object[] params = new Object[]{idOrden, tipoCifraTipoCifra};
        return getJdbcTemplateBase().query(OBTENER_CIFRAS_ORDEN_TIPO_CIFRA, params,
                new FecetCifraImpuestoConceptoMapper());
    }

    @Override
    public List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra) {
        Object[] params = new Object[]{idOrden, tipoCifra};
        return getJdbcTemplateBase().query(OBTENER_CIFRAS_ORDEN_TIPO, params, new FecetCifraImpuestoConceptoMapper());

    }

    @Override
    public boolean existeCifra(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto, int concepto) {
        BigDecimal numeroCifras = Constantes.BIG_DECIMAL_CERO;
        Object[] params = new Object[]{idOrden, impuesto, concepto, tipoCifra};
        numeroCifras = getJdbcTemplateBase().queryForObject(EXISTE_CIFRA, BigDecimal.class, params);
        return numeroCifras.intValue() > 0;
    }

    public BigDecimal actualizarCifra(FeceaCifraImpuestoDTO cifra) {
        BigDecimal idCifraDetalle = Constantes.BIG_DECIMAL_CERO;
        try {
            Object[] object = new Object[]{cifra.getFechaPago(), cifra.getImporte(), cifra.getActualizaciones(),
                cifra.getMultas(), cifra.getRecargos(), cifra.getObservaciones(), cifra.getFechaInicio(),
                cifra.getFechaFin(), cifra.getIdCifraImpuesto()};
            getJdbcTemplateBase().update(ACTUALIZAR_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraDetalle;
    }

    @Override
    public BigDecimal actualizarEstatusCifra(BigDecimal idCifra, BigDecimal idEstatus) {
        BigDecimal idCifraDetalle = Constantes.BIG_DECIMAL_CERO;
        try {
            idCifraDetalle = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idEstatus, idCifra};
            getJdbcTemplateBase().update(ACTUALIZAR_ESTATUS_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraDetalle;
    }

    @Override
    public BigDecimal actualizarEstatusImpuesto(BigDecimal idCifraImpuesto, BigDecimal idEstatus) {
        BigDecimal idCifraDetalle = Constantes.BIG_DECIMAL_CERO;
        try {
            idCifraDetalle = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idEstatus, idCifraImpuesto};
            getJdbcTemplateBase().update(ACTUALIZAR_ESTATUS_IMPUESTO, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idCifraDetalle;
    }

}
