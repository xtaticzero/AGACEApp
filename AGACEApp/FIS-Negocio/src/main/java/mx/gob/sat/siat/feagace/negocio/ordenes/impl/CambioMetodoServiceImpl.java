package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCambioMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodoPk;
import mx.gob.sat.siat.feagace.negocio.ordenes.CambioMetodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cambioMetodoService")
public class CambioMetodoServiceImpl implements CambioMetodoService {
    
    @Autowired
    private FecetCambioMetodoDao fecetCambioMetodoDao;

    /**
     * @param dto
     * @return
     */
    @Override
    public FecetCambioMetodoPk guardarCambioMetodo(FecetCambioMetodo dto) {
        return fecetCambioMetodoDao.insert(dto);
    }

    /**
     * @param pk
     * @param dto
     */
    @Override
    public void actualizarCambioMetodo(FecetCambioMetodo dto) {
        fecetCambioMetodoDao.update(dto);
    }

    /**
     * @param pk
     */
    @Override
    public void eliminarCambioMetodo(FecetCambioMetodoPk pk) {
        fecetCambioMetodoDao.delete(pk);
    }

    /**
     * @param idCambioMetodo
     * @return FecetCambioMetodo
     */
    @Override
    public FecetCambioMetodo getCambioMetodoId(BigDecimal idCambioMetodo) {
        return fecetCambioMetodoDao.findByPrimaryKey(idCambioMetodo);
    }

    /**
     * @return List FecetCambioMetodo
     */
    @Override
    public List<FecetCambioMetodo> getListaCambioMetodo() {
        return fecetCambioMetodoDao.findAll();
    }

    /**
     * @return fecetCambioMetodoDao
     */
    public FecetCambioMetodoDao getFecetCambioMetodoDao() {
        return fecetCambioMetodoDao;
    }

    /**
     * @param fecetCambioMetodoDao
     */
    public void setFecetCambioMetodoDao(FecetCambioMetodoDao fecetCambioMetodoDao) {
        this.fecetCambioMetodoDao = fecetCambioMetodoDao;
    }
}
