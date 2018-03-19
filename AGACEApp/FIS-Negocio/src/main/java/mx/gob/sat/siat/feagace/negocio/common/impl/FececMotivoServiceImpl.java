package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececMotivoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.negocio.common.FececMotivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("fececMotivoService")
public class FececMotivoServiceImpl implements FececMotivoService {
    
    @Autowired
    private FececMotivoDao fececMotivoDao;
    
    @Override
    public List<FececMotivo> findWhereTipoMotivoEquals() {
        return fececMotivoDao.findWhereTipoMotivoEquals( "RECHAZO" );
    }
}
