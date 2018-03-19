package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FececPerfilDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FececPerfilMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececPerfil;

import org.springframework.stereotype.Repository;

@Repository("fececPerfilDao")
public class FececPerfilDaoImpl extends BaseJDBCDao<FececPerfil> implements FececPerfilDao {

    @SuppressWarnings("compatibility:816022337333543443")
    private static final long serialVersionUID = -3914473643382572214L;

    @Override
    public List<FececPerfil> obtenerPerfiles() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ID_PERFIL, DESCRIPCION FROM FECEC_PERFIL ORDER BY ID_PERFIL");
        return getJdbcTemplateBase().query(query.toString(), new FececPerfilMapper());
    }
}
