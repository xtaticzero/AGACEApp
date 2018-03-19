package mx.gob.sat.siat.feagace.negocio.propuestas.consulta.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ContadorPropAsignadasDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ContadorPropAsignadasService;

@Service("contadorPropAsignadasService")
public class ContadorPropAsignadasServiceImpl implements ContadorPropAsignadasService {

    @Autowired
    private transient ContadorPropAsignadasDao contadorPropAsignadasDao;

    @Override
    public List<ContadorPropuestasAsignadas> getResumenPropuestasAsignadas(String rfcAuditor) {
        return contadorPropAsignadasDao.getResumenPropuestasAsignadas(rfcAuditor);
    }

}
