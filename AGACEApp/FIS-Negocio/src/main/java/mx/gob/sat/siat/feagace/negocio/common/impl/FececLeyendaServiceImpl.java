package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.FececLeyendaDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;
import mx.gob.sat.siat.feagace.negocio.common.FececLeyendaService;

@Service("fececLeyendaService")
public class FececLeyendaServiceImpl extends BaseBusinessServices implements FececLeyendaService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FececLeyendaDao fececLeyendaDao;

    public List<FececLeyenda> obtenerLeyendaByIdLeyenda(BigDecimal... idLeyenda) {
        return fececLeyendaDao.obtenerLeyendaById(idLeyenda);
    }

}
