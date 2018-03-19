package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaApartadoPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;

@Repository("feceaApartadoPrioridadDao")
public class FeceaApartadoPrioridadDaoImpl extends BaseJDBCDao<FececPrioridad> implements FeceaApartadoPrioridadDao {

    private static final long serialVersionUID = 178139772783827512L;

    @Override
    public List<String> getPrioridad(String nombreApartado, Integer idSubmodulo) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT VALOR FROM FECEC_PRIORIDAD WHERE ID_PRIORIDAD IN (");
        query.append("SELECT APARTADO_PRIORIDAD.ID_PRIORIDAD FROM FECEA_APARTADO_PRIORIDAD APARTADO_PRIORIDAD ");
        query.append("INNER JOIN FECEC_APARTADO_PROPUESTA APARTADO_PROPUESTA ON APARTADO_PRIORIDAD.ID_APARTADO_PROPUESTA = APARTADO_PROPUESTA.ID_APARTADO_PROPUESTA ");
        query.append("WHERE APARTADO_PROPUESTA.NOMBRE_APARTADO LIKE '%");
        query.append(nombreApartado);
        query.append("%' AND APARTADO_PROPUESTA.ID_SUBMODULO = ");
        query.append(idSubmodulo);
        query.append(") ORDER BY VALOR ASC ");
        return getJdbcTemplateBase().queryForList(query.toString(), String.class);
    }

}
