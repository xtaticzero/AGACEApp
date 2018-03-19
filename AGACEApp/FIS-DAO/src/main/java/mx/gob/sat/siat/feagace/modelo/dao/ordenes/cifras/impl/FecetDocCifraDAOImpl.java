package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras.FecetDocCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.cifras.FecetDocCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component("fecetDocCifraDAO")
public class FecetDocCifraDAOImpl extends BaseJDBCDao<FecetDocCifraDTO> implements FecetDocCifraDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal insertaDocumento(final FecetDocCifraDTO documento, final BigDecimal idFeceaCifraImpuesto) {
        BigDecimal idDocumento = Constantes.BIG_DECIMAL_CERO;
        try {
            idDocumento = getJdbcTemplateBase().queryForObject(SELECT_SEQUENCE, BigDecimal.class);
            Object[] param = new Object[]{idDocumento, idFeceaCifraImpuesto, documento.getRutaArchivo()
                + documento.getNombre(),
                documento.getFechaFinal()};
            getJdbcTemplateBase().update(INSERTA_DOCUMENTO, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idDocumento;
    }

    public List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto) {
        Object[] params = new Object[]{idCifraImpuesto};
        return getJdbcTemplateBase().query(OBTENER_DOCUMENTOS, params, new FecetDocCifraMapper());
    }

    public BigDecimal actualizarEstatusDocumento(BigDecimal idFeceaCifraImpuesto, BigDecimal estatus) {
        try {
            Object[] param = new Object[]{estatus, idFeceaCifraImpuesto};
            getJdbcTemplateBase().update(ACTUALIZAR_ESTATUS, param);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return idFeceaCifraImpuesto;
    }

}
