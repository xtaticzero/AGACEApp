package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasCatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("propuestasCatalogoService")
public class PropuestasCatalogoServiceImp implements PropuestasCatalogoService {

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;

    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;

    @Autowired
    private transient FececRevisionDao fececRevisionDao;

    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;

    @Override
    public List<FececMetodo> obtenListaMetodos() {
        return this.feceaMetodoDao.findAll();
    }

    @Override
    public List<FececTipoPropuesta> obtenListaTipoPropuestas() {
        return fececTipoPropuestaDao.findAll();
    }

    @Override
    public List<FececRevision> obtenListaTipoRevision() {
        return fececRevisionDao.findAll();
    }

    @Override
    public List<FececSubprograma> obtenListaSubprograma() {
        List<FececSubprograma> listaSubprograma = null;
        listaSubprograma = fececSubprogramaDao.findAll();
        return listaSubprograma;
    }

}
