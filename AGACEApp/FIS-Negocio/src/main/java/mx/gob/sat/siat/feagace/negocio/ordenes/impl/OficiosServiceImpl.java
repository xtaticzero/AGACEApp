package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.negocio.ordenes.OficiosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("oficiosService")
public class OficiosServiceImpl implements OficiosService<FecetOficio> {

    @Autowired
    private FecetOficioDao fecetOficioDao;
    @Autowired
    private FecetOficioAnexosDao fecetOficioAnexosDao;

    @Override
    public FecetOficio consultarPorId(BigDecimal id) {
        return fecetOficioDao.getOficioById(id);
    }

    @Override
    public List<?> getPruebasAlegatosOficio(BigDecimal id) {
        return Collections.emptyList();
    }

    @Override
    public Map<String, List<FecetProrrogaOficio>> getProrrogasOficio(BigDecimal id) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, List<FecetOficio>> getOficios(BigDecimal id) {
        return Collections.emptyMap();
    }

    @Override
    public List<FecetOficioAnexos> getOficiosAnexos(BigDecimal id) {
        return Collections.emptyList();
    }

    @Override
    public List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficio(BigDecimal id) {
        return Collections.emptyList();
    }
    
    
    

    public FecetOficioDao getFecetOficioDao() {
        return fecetOficioDao;
    }

    public void setFecetOficioDao(FecetOficioDao fecetOficioDao) {
        this.fecetOficioDao = fecetOficioDao;
    }

    public FecetOficioAnexosDao getFecetOficioAnexosDao() {
        return fecetOficioAnexosDao;
    }

    public void setFecetOficioAnexosDao(FecetOficioAnexosDao fecetOficioAnexosDao) {
        this.fecetOficioAnexosDao = fecetOficioAnexosDao;
    }
}
