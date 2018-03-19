package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargaFirmaPruebasPericialesService;

public class CargaFirmaPruebasPericialesServiceImpl extends BaseBusinessServices implements CargaFirmaPruebasPericialesService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;
    
    
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesContadorDocs(AgaceOrden orden) {
        return fecetPruebasPericialesDao.getPruebasPericialesContadorDoc(orden.getIdOrden());
    }

}
