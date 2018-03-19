package mx.gob.sat.siat.feagace.negocio.common.impl;

import mx.gob.sat.siat.feagace.negocio.common.ConsultaSISEService;
import mx.gob.sat.siat.sise.service.SiseService;
import mx.gob.sat.siat.base.service.BaseBusinessServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("consultaSISEService")
public class ConsultaSISEServiceImpl extends BaseBusinessServices implements ConsultaSISEService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient SiseService siseService;

    @Override
    public boolean verInformacion(String rfc) {
        return siseService.verInformacion(rfc) == 0 ? false : true;
    }
}
