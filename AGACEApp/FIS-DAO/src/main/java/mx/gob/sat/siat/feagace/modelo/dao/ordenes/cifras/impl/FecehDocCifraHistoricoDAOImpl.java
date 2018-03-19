package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecehDocCifraHistoricoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.cifras.FecetDocCifraHistoricoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class FecehDocCifraHistoricoDAOImpl extends BaseJDBCDao<FecetDocCifraDTO> implements FecehDocCifraHistoricoDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertarRegistro(FecetDocCifraDTO documento, BigDecimal idCifraHistorico) {
        BigDecimal idHistorico = Constantes.BIG_DECIMAL_CERO;
        try {
            idHistorico = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] object = new Object[]{idHistorico, idCifraHistorico, documento.getRutaArchivo(), documento.getFechaFinal()};
            getJdbcTemplateBase().update(INSERT_DOC_HIS_CIFRA, object);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idHistorico;
    }

    @Override
    public List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto) {
        Object[] params = new Object[]{idCifraImpuesto};
        return getJdbcTemplateBase().query(OBTENER_DOCUMENTOS, params, new FecetDocCifraHistoricoMapper());
    }

}
