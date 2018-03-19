package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleNyVDAO;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetSuspensionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao.SuspensionDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.negocio.common.ActualizarFechaService;

@Service("actualizarFechaService")
public class ActualizarFechaServiceImpl implements ActualizarFechaService {
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient SuspensionDAO suspensionDao;
    @Autowired
    private transient FecetDetalleNyVDAO fecetDetalleNyVDAO;
    @Autowired
    private transient FecetSuspensionInsumoDao fecetSuspensionInsumoDao;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;
    
    @Override
    public AgaceOrden getOrdenByNumeroOrden(String numeroOrden) {       
        return agaceOrdenDao.findByNumeroOrden(numeroOrden);
    }

    @Override
    public FecetOficio getOficioByIdOficio(BigDecimal idOficio) {
        return fecetOficioDao.getOficioById(idOficio);
    }

    @Override
    public FecetSuspensionDTO getAcByIdOrden(BigDecimal idOrden) {
        List<FecetSuspensionDTO> listSuspencion = suspensionDao.buscarSuspension(idOrden);
        for (FecetSuspensionDTO suspension : listSuspencion) {
            if (suspension.getIdObjeto() == null) {
                return suspension;
            }
        }
        return null;
    }

    @Override
    public FecetSuspensionDTO getSuspensionByIdOficio(FecetOficio oficio) {
        List<FecetSuspensionDTO> listSuspencion = suspensionDao.buscarSuspension(oficio.getIdOrden());
        for (FecetSuspensionDTO suspension : listSuspencion) {
            if (suspension.getIdOficio().equals(oficio.getIdOficio())) {
                return suspension;
            }
        }
        return null;
    }

    @Override
    public void actualizarIntegracionExpediente(AgaceOrden orden, Date fechaExpediente) {
        agaceOrdenDao.integraExpediente(orden, fechaExpediente);
    }

    @Override
    public void actualizarFechaSurteEfectos(FecetDetalleNyV fecetDetalleNyV) {
        fecetDetalleNyVDAO.guardaDetalleNyVSurteEfectos(fecetDetalleNyV);
    }

    @Override
    public void actualizarFechaFirma(FecetOficio oficio) {
        fecetOficioDao.updateFechaFirma(oficio);
    }

    @Override
    public void actualizarSuspension(FecetSuspensionDTO suspension) {
        suspensionDao.actualizarFechas(suspension);
    }
    
    @Override
    public FecetSuspensionInsumo getSuspensionInsumoByIdRegistro(String idRegistro) {
        List<FecetSuspensionInsumo> suspensionIsnumo = fecetSuspensionInsumoDao.obtenerSuspensionByRegistroInsumo(idRegistro);
        
        if (suspensionIsnumo != null && !suspensionIsnumo.isEmpty()) {
            return suspensionIsnumo.get(0);
        }
        
        return null;
    }

    @Override
    public FecetInsumo getInsumoByIdRegistro(String idRegistro) {
        return fecetInsumoDao.findByIdRegistro(idRegistro);
    }

    @Override
    public void actualizarSuspensionInsumo(FecetSuspensionInsumo suspensionInsumo) {
       fecetSuspensionInsumoDao.updateSuspensionInsumo(suspensionInsumo);
        
    }

    @Override
    public void actualizarPlazoInsumo(FecetInsumo insumo) {
        fecetInsumoDao.updateFechaInicioPlazo(insumo);
        
    }
}
