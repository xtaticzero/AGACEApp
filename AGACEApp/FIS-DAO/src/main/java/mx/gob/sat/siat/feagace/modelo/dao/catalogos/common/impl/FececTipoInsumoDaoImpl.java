/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececTipoInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.aguilar
 */
@Repository("fececTipoInsumoDao")
public class FececTipoInsumoDaoImpl extends BaseJDBCDao<FececTipoInsumo> implements FececTipoInsumoDao {

    private static final long serialVersionUID = 695024794128576057L;
    private static final String SQL_SELECT = "SELECT ID_TIPO_INSUMO, DESCRIPCION, ID_GENERAL FROM "
            + getTableName();

    @Override
    public List<FececTipoInsumo> findActivos(BigDecimal idGeneral) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_FIN IS NULL  AND ID_GENERAL = ? ORDER BY ID_TIPO_INSUMO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececTipoInsumoMapper(), idGeneral);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_TIPO_INSUMO";
    }

}
