package mx.gob.sat.siat.feagace.negocio.common.impl;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetBitacoraDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pistasAuditoriaService")
public class PistasAuditoriaServiceImpl extends BaseBusinessServices implements PistasAuditoriaService {
    
    @SuppressWarnings("compatibility:-7512425675303608327")
    private static final long serialVersionUID = -5871454158884523603L;
    
    @Autowired
    private transient FecetBitacoraDao fecetBitacoraDao;

    @Override
    public void insertaPistaAuditoria(final FecetBitacora fecetBitacora) {
        fecetBitacoraDao.insert(fecetBitacora);
    }

    public void setFecetBitacoraDao(FecetBitacoraDao fecetBitacoraDao) {
        this.fecetBitacoraDao = fecetBitacoraDao;
    }

    public FecetBitacoraDao getFecetBitacoraDao() {
        return fecetBitacoraDao;
    }
}
