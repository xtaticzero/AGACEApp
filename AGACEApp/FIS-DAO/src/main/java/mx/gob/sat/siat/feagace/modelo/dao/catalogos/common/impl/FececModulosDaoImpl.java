/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececModulosDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececModulosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import org.springframework.stereotype.Repository;

@Repository("modulosDaoImpl")
public class FececModulosDaoImpl extends BaseJDBCDao<FececModulos> implements FececModulosDao {

    private static final long serialVersionUID = -3947347405388354921L;

    @Override
    public void insert(FececModulos dto) {
        getJdbcTemplateBase().update(SQL_INSERT, dto.getNombre(), dto.getDescripcion());
    }

    @Override
    public void update(FececModulos dto) {
        if (dto != null && dto.getIdModulo() != null) {
            boolean flgParameters = false;
            StringBuilder query = new StringBuilder();
            query.append(SQL_UPDATE_HEADER);

            if (dto.getNombre() != null && !dto.getNombre().isEmpty()) {
                query.append(SQL_PARAMETER_NAME);
                flgParameters = true;
            }

            if (flgParameters) {
                query.append(flgParameters ? "," : "");

            }

            query.append(SQL_UPDATE_DESC);
            query.append(",");
            query.append(SQL_UPDATE_FECHA_BAJA);
            query.append(SQL_UPDATE_FOOTER);

            if (flgParameters) {
                getJdbcTemplateBase().update(query.toString(), dto.getNombre(), dto.getDescripcion(), dto.getFechaBaja(), dto.getIdModulo());
            }
        }
    }

    @Override
    public void delete(FececModulos dto) {
        if (dto != null && dto.getIdModulo() != null) {
            getJdbcTemplateBase().update(SQL_DELETE, dto.getIdModulo());
        }
    }

    @Override
    public FececModulos findByPrimaryKey(BigDecimal idModulo) {
        List<FececModulos> list = getJdbcTemplateBase().query(SQL_SELECT_BY_ID,
                new FececModulosMapper(), idModulo);
        return (list == null || list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<FececModulos> findAll() {
        return getJdbcTemplateBase().query(SQL_SELECT_FULL,
                new FececModulosMapper());

    }

    @Override
    public FececModulos getModuloXNombre(FececModulos modulo) {
        if (modulo != null) {
            Object[] params = {modulo.getNombre()};

            List<FececModulos> lstModulos = getJdbcTemplateBase().query(SQL_SELECT.concat(" WHERE ").concat(SQL_FIND_PARAMETER_NAME),
                    params, new FececModulosMapper());
            if (lstModulos != null && !lstModulos.isEmpty()) {
                return lstModulos.get(0);
            }
        }

        return null;
    }

}
