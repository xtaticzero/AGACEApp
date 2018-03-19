package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.negocio.propuestas.DetalleOrdenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("detalleOrdenService")
public class DetalleOrdenServiceImpl extends BaseBusinessServices implements DetalleOrdenService {

    @SuppressWarnings("compatibility:8652939777091355306")
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;

    @Override
    public AgaceOrden obtieneIdOrden(BigDecimal idOrden) {
        return agaceOrdenDao.findByPrimaryKey(idOrden);
    }

    @Override
    public FecetContribuyente obtieneContribuyentes(BigDecimal idContribuyente) {
        return fecetContribuyenteDao.findByPrimaryKey(idContribuyente);
    }

    @Override
    public List<FececMetodo> obtieneMetodo(BigDecimal idMetodo) {
        return feceaMetodoDao.findWhereIdMetodo(idMetodo);
    }

    @Override
    public List<FecetAsociado> obtieneColaborador(BigDecimal idTipoAsociado, BigDecimal idOrden) {
        return fecetAsociadoDao.getColaboradorContribuyente(idTipoAsociado, idOrden);
    }
}
