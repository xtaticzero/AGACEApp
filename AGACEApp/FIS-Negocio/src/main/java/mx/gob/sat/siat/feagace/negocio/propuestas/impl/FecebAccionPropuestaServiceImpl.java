package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecebAccionPropuestaService;

@Service("fecebAccionPropuestaService")
public class FecebAccionPropuestaServiceImpl implements FecebAccionPropuestaService{
    
    @Autowired
    private FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Override
    public List<FecebAccionPropuesta> obtenerAccionesPropuestaSinTabla(BigDecimal idPropuesta) {
      
        return fecebAccionPropuestaDao.obtenerAccionSinAsociarTabla(idPropuesta);
    }

    @Override
    public List<FecebAccionPropuesta> obtenerAccionesPropuestaRechazadas(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
      
        return fecebAccionPropuestaDao.obtenerAccionAsociarRechazo(idPropuesta, accionesFuncionarioEnum);
    }

    @Override
    public List<FecebAccionPropuesta> obtenerAccionesPropuestaCancelada(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        
        return fecebAccionPropuestaDao.obtenerAccionAsociarCancelar(idPropuesta, accionesFuncionarioEnum);
    }
    
    @Override
    public List<FecebAccionPropuesta> obtenerAccionesPropuestaRetroalimentar(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        
        return fecebAccionPropuestaDao.obtenerAccionAsociarRetroalimentar(idPropuesta, accionesFuncionarioEnum);
    }
    
    @Override
    public List<FecebAccionPropuesta> obtenerAccionesPropuestaTransferir(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum){
        
        return fecebAccionPropuestaDao.obtenerAccionAsociarTransferir(idPropuesta, accionesFuncionarioEnum);
    }
    
    
    
}
