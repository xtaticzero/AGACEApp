package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaTipoParcialidadMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececTipoParcialidadDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;

@Component("fececTipoParcialidadDAO")
public class FececTipoParcialidadDAOImpl extends BaseJDBCDao<FeceaCifraTipoCifraDTO>
        implements FececTipoParcialidadDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String OBTENER_TIPO_PARCIALIDAD = "select id_tipo_parcialidad, tipo_parcialidad from fecec_tipo_parcialidad";

    @Override
    public List<FececTipoParcialidadDTO> obtenerTiposParcialidad() {
        return getJdbcTemplateBase().query(OBTENER_TIPO_PARCIALIDAD, new FeceaTipoParcialidadMapper());
    }

}
