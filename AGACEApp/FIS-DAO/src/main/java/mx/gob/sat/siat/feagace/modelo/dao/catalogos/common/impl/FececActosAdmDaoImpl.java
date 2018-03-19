/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececActosAdmDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececActosAdmMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;

/**
 * @author sergio.vaca
 *
 */
@Repository("fececActosAdmDao")
public class FececActosAdmDaoImpl extends BaseJDBCDao<FececActosAdm> implements FececActosAdmDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public FececActosAdm obtenerByPk(Long idActoAdmin) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("\nFROM FECEC_ACTOS_ADM");
        query.append("\nWHERE ID_ACTO_ADMON = ?");
        List<FececActosAdm> registros = getJdbcTemplateBase().query(query.toString(), new FececActosAdmMapper(), idActoAdmin);
        return registros != null && !registros.isEmpty() ? registros.get(0) : null;
    }

    @Override
    public FececActosAdm obtenerIdNyv(Long idMetodo, Long idTipoOficio, Long unidadAdministrativa, boolean isActivoNyV, boolean isFaseII) {
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("\nFROM FECEC_ACTOS_ADM");
        query.append("\nWHERE FECHA_FIN IS NULL AND ID_METODO = ?");
        parametros.add(idMetodo);
        if (isActivoNyV && isFaseII) {
            query.append("\nAND ID_UNIDAD_ADMINISTRATIVA = ?");
            parametros.add(unidadAdministrativa);
        }
        List<FececActosAdm> registros = null;
        if (idTipoOficio != null) {
            query.append("\n AND ID_TIPO_OFICIO = ?");
            parametros.add(idTipoOficio);
        } else {
            query.append("\n AND ID_TIPO_OFICIO IS NULL");
        }
        registros = getJdbcTemplateBase().query(query.toString(), new FececActosAdmMapper(), parametros.toArray());
        return registros != null && !registros.isEmpty() ? registros.get(0) : null;
    }
}
