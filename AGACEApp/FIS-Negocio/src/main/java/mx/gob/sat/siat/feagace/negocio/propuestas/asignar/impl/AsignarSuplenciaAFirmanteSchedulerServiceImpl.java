package mx.gob.sat.siat.feagace.negocio.propuestas.asignar.impl;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsignarSuplenciaAFirmanteDAO;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteUpdateException;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarSuplenciaAFirmanteSchedulerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("asignarSuplenciaAFirmanteSchedulerService")
public class AsignarSuplenciaAFirmanteSchedulerServiceImpl extends BaseBusinessServices implements AsignarSuplenciaAFirmanteSchedulerService {


    @SuppressWarnings("compatibility:-208434800345676593")
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient AsignarSuplenciaAFirmanteDAO asignarSuplenciaAFirmanteDAO;

    @Override
    public void cancelaSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException {
        asignarSuplenciaAFirmanteDAO.cancelarSuplenciaScheduler();
    }
    
    @Override
    public void activaSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException {
        asignarSuplenciaAFirmanteDAO.activarSuplenciaScheduler();
    }
    
    @Override
    public void terminarSuplenciaScheduler() throws AsignarSuplenciaAFirmanteUpdateException {
        asignarSuplenciaAFirmanteDAO.terminarSuplenciaScheduler();
    }


    public void setAsignarSuplenciaAFirmanteDAO(AsignarSuplenciaAFirmanteDAO asignarSuplenciaAFirmanteDAO) {
        this.asignarSuplenciaAFirmanteDAO = asignarSuplenciaAFirmanteDAO;
    }

    public AsignarSuplenciaAFirmanteDAO getAsignarSuplenciaAFirmanteDAO() {
        return asignarSuplenciaAFirmanteDAO;
    }
}
