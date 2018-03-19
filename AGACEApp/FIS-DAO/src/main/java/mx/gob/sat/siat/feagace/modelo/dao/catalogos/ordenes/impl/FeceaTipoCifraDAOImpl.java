package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaTipoCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FeceaTipoCifraDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.cifras.SumatoriaTipoCifraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;

@Component("feceaTipoCifraDAO")
public class FeceaTipoCifraDAOImpl extends BaseJDBCDao<FeceaCifraTipoCifraDTO> implements FeceaTipoCifraDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public List<FeceaCifraTipoCifraDTO> obtenerCifrasPorTipo(int tipoCifra) {
        Object[] object = new Object[]{tipoCifra};
        return getJdbcTemplateBase().query(OBTENER_TIPO_CIFRA, object, new FeceaTipoCifraMapper());
    }

    @Override
    public List<TotalCifrasDTO> obtenerSumatoriaPorTipoCifraIdOrden(BigDecimal idOrden) {
        Object[] object = new Object[]{idOrden};
        return getJdbcTemplateBase().query(TOTAL_TIPO_CIFRAS, object, new SumatoriaTipoCifraMapper());
    }

}
