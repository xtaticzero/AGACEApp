package mx.gob.sat.siat.feagace.negocio.common.impl;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececAuditorDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;
import mx.gob.sat.siat.feagace.negocio.common.AuditorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditorService")
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private FececAuditorDao fececAuditorDao;

    /**
     * Metodo que obtiene los los datos del auditor de acuerdo al
     * registro federal del contribuyente
     * @param rfcAuditor registro federal del contribuyente
     * @return objeto de tipo <code>FececAuditor</code>
     */
    @Override
    public FececAuditor getAuditor(final String rfcAuditor) {
        return fececAuditorDao.findWhereRfcEquals(rfcAuditor).get(0);
    }

}
