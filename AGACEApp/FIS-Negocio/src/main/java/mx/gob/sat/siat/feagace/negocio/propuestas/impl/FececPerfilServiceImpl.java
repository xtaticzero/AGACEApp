package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FececPerfilDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;
import mx.gob.sat.siat.feagace.negocio.propuestas.FececPerfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("fececPerfilService")
public class FececPerfilServiceImpl implements FececPerfilService {
    
    @Autowired
    private FececPerfilDao fececPerfilDao;
    
    @Override
    public List<FececPerfil> obtenerPerfiles() {
        return fececPerfilDao.obtenerPerfiles();
    }
}
