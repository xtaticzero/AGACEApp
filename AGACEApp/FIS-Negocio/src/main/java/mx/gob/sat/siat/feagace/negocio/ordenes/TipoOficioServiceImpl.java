package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetTipoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.negocio.ordenes.OficiosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tipoOficioService")
public abstract class TipoOficioServiceImpl implements OficiosService<FecetTipoOficio> {

    @Autowired
    private FecetTipoOficioDao fecetTipoOficioDao;

    
    public BigDecimal guardar(FecetTipoOficio dto) {
        return fecetTipoOficioDao.insert(dto);
    }

    @Override
    public FecetTipoOficio consultarPorId(BigDecimal id) {
        return fecetTipoOficioDao.getTipoOficioById(id);
    }

}
