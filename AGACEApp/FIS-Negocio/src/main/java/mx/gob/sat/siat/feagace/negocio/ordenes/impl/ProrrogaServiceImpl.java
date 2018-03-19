package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.ProrrogaService;

@Service("prorrogaService")
public class ProrrogaServiceImpl implements ProrrogaService {

    private static final Logger LOG = Logger.getLogger(ProrrogaServiceImpl.class.getName());

    @Autowired
    private FecetProrrogaOrdenDao prorrogaDao;

    @Override
    public List<FecetProrrogaOrden> getUltimaProrroga(AgaceOrden orden) {

        return prorrogaDao.buscaUltimaProrrogaPorOrden(orden);

    }

    @Override
    public void actualizarPorEstadoYFechaMaxPro(FecetProrrogaOrden prorroga, AgaceOrden orden) {

        prorrogaDao.actualizarPorEstadoYFechaMax(prorroga, orden);

    }

    public FecetProrrogaOrdenDao getProrrogaDao() {
        return prorrogaDao;
    }

    public void setProrrogaDao(FecetProrrogaOrdenDao prorrogaDao) {
        this.prorrogaDao = prorrogaDao;
    }

    @Override
    public List<FecetProrrogaOrden> obtenerProrrogasOrdenEstatus(final AgaceOrden orden, final BigDecimal... idEstatus) {
        return prorrogaDao.findWhereIdOrdenEstatusEquals(orden.getIdOrden(), idEstatus);
    }

    @Override
    public FecetProrrogaOrden obtenerProrrogaMasActual(final AgaceOrden orden, final BigDecimal idEstatus) {
        LOG.debug("[obtenerUltimaProrroga]");
        List<FecetProrrogaOrden> prorrogas = obtenerProrrogasOrdenEstatus(orden, idEstatus);
        FecetProrrogaOrden ultimaProrroga = new FecetProrrogaOrden();
        if (!prorrogas.isEmpty()) {
            ultimaProrroga = prorrogas.get(0);
        }
        return ultimaProrroga;
    }
}
