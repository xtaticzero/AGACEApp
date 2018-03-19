package mx.gob.sat.siat.feagace.negocio.common.impl;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececSubadministradorDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministrador;
import mx.gob.sat.siat.feagace.negocio.common.SubAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subAdministradorService")
public class SubAdministradorServiceImpl extends BaseBusinessServices implements SubAdministradorService {

    private static final long serialVersionUID = 4452556316726415934L;
    
    @Autowired
    private transient FececSubadministradorDao subadministradorDao;

    @Override
    public FececSubadministrador getSubAdministrador(final String rfc) {

        return subadministradorDao.findWhereRfcEquals(rfc).get(0);

    }

}
