/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.UnidadDesahogoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.sql.UnidadDesahogoSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("unidadDesahogoDao")
public class UnidadDesahogoDaoImpl extends BaseJDBCDao<AraceDTO> implements UnidadDesahogoDao {

    private static final long serialVersionUID = 1797864988732453838L;

    @Override
    public List<AraceDTO> getLstUnidadesDesahogo(Integer idEmpleado, TipoEmpleadoEnum tipoEmpleado) {
        Object[] args = {idEmpleado, tipoEmpleado.getId()};
        return getJdbcTemplateBase().query(UnidadDesahogoSQL.SQL_SELECT_UNIDADES_DESAHOGO, args, new RowMapper<AraceDTO>() {
            @Override
            public AraceDTO mapRow(ResultSet rs, int i) throws SQLException {
                AraceDTO arace = new AraceDTO();
                arace.setIdArace(rs.getInt(UnidadDesahogoSQL.COLUM_ID_ARACE));
                return arace;
            }
        });
    }

}
