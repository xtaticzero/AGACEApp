package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaPrioridadService;

@Service("consultaPrioridadService")
public class ConsultaPrioridadServiceImpl extends BaseBusinessServices
        implements ConsultaPrioridadService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    @Qualifier("fececPrioridadInsumoDao")
    private transient FececPrioridadDao fececPrioridadDao;

    @Override
    public List<FececPrioridad> findAll() {
        return fececPrioridadDao.findAll();
    }
    
    @Override
    public List<FececPrioridad> findActivos(BigDecimal idGeneral) {
        return fececPrioridadDao.findActivos(idGeneral);
    }
}
