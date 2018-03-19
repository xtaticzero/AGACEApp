/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class GrupoUnidadesAdminXGeneralMapper implements RowMapper {

    private static final String GRP_UNID_ID_UNIDAD_ADMIN = "GRP_UNID_ID_UNIDAD_ADMIN";
    private static final String GRP_UNID_ID_GENERAL = "GRP_UNID_ID_GENERAL";

    @Override

    public Object mapRow(ResultSet rs, int i) throws SQLException {
        List<GrupoUnidadesAdminXGeneral> lstGrupos = new ArrayList<GrupoUnidadesAdminXGeneral>();
        GrupoUnidadesAdminXGeneral grupoUnidades = null;
        do {
            grupoUnidades = new GrupoUnidadesAdminXGeneral();

            grupoUnidades.setIdAdmGral(rs.getBigDecimal(GRP_UNID_ID_GENERAL));
            grupoUnidades.setGrupo(new GrupoMapper().mapRow(rs, i));
            grupoUnidades.setReglaNegocio(new ReglaMapper().mapRow(rs, i));
            grupoUnidades.setLstUnidadesAdministrativas(getLstUnidades(grupoUnidades, rs));

            lstGrupos.add(grupoUnidades);

        } while (rs.next());
        
        return lstGrupos;
    }

    private List<AraceDTO> getLstUnidades(GrupoUnidadesAdminXGeneral grupoUnidades, ResultSet rs) throws SQLException {
        List<AraceDTO> lstUnidadesAdm = new ArrayList<AraceDTO>();
        boolean flgMismoGrupo = false;
        do {
            flgMismoGrupo = grupoUnidades != null && grupoUnidades.getGrupo().getIdGrupo().intValue() == rs.getBigDecimal(GrupoMapper.ID_GRUPO).intValue();
            if (flgMismoGrupo) {
                llenaListaUnidades(lstUnidadesAdm, rs);
            } else {
                return lstUnidadesAdm;
            }
        } while (rs.next());

        return lstUnidadesAdm;
    }

    private void llenaListaUnidades(List<AraceDTO> lstUnidadesAdm, ResultSet rs) throws SQLException {
        AraceDTO unidadAdm = new AraceDTO();
        unidadAdm.setIdArace(rs.getInt(GRP_UNID_ID_UNIDAD_ADMIN));
        if (!lstUnidadesAdm.contains(unidadAdm)) {
            lstUnidadesAdm.add(unidadAdm);
        }
    }

}
