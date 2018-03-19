/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class GrupoMapper implements RowMapper<GrupoAdministracion> {

    public static final String ID_GRUPO = "GRUP_ID_GRUPO";
    private static final String NOMBRE = "GRUP_NOMBRE";
    private static final String DESCRIPCION = "GRUP_DESCRIPCION";
    private static final String CENTRAL = "GRUP_CENTRAL";
    private static final String FECHA_INICIO = "GRUP_FECHA_INICIO";
    private static final String FECHA_FIN = "GRUP_FECHA_FIN";

    @Override
    public GrupoAdministracion mapRow(ResultSet rs, int i) throws SQLException {
        GrupoAdministracion grupo = new GrupoAdministracion();

        grupo.setIdGrupo(rs.getBigDecimal(ID_GRUPO));
        grupo.setNombre(rs.getString(NOMBRE));
        grupo.setDescripcion(rs.getString(DESCRIPCION));
        grupo.setCentral(rs.getBoolean(CENTRAL));
        grupo.setFechaInicio(rs.getTimestamp(FECHA_INICIO));
        grupo.setFechaFin(rs.getTimestamp(FECHA_FIN));

        return grupo;
    }

}
