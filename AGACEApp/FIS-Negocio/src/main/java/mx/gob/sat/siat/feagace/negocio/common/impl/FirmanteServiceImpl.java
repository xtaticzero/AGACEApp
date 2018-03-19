package mx.gob.sat.siat.feagace.negocio.common.impl;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececFirmanteDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.negocio.common.FirmanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("firmanteService")
public class FirmanteServiceImpl implements FirmanteService {

    @Autowired
    private FececFirmanteDao fececFirmanteDao;

    @Override
    public FececFirmante getFirmante(final String rfc) {

        return fececFirmanteDao.findWhereRfcEquals(rfc).get(0);

    }

    public void setFececFirmanteDao(final FececFirmanteDao fececFirmanteDao) {
        this.fececFirmanteDao = fececFirmanteDao;
    }

    public FececFirmanteDao getFececFirmanteDao() {
        return fececFirmanteDao;
    }
}
