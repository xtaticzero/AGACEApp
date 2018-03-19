/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class UnidadAdminModuloMapper implements RowMapper<ModuloUnidadAdmin> {

    private static final String ID_ADACE_MODULO = "ID_ADACE_MODULO";
    private static final String ID_UNIDAD_ADMINISTRATIVA = "ID_GRUPO_ADMINISTRACION";
    private static final String ID_GENERAL = "ID_GENERAL";
    private static final String FECHA_INICIO = "FECHA_INICIO";
    private static final String FECHA_FIN = "FECHA_FIN";
    private static final String BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public ModuloUnidadAdmin mapRow(ResultSet rs, int i) throws SQLException {
        ModuloUnidadAdmin moduloUnidad = new ModuloUnidadAdmin();

        moduloUnidad.setIdModuloUnidad(rs.getBigDecimal(ID_ADACE_MODULO));
        moduloUnidad.setUnidadAdministrativa(new AraceDTO());
        moduloUnidad.getUnidadAdministrativa().setIdArace(rs.getInt(ID_UNIDAD_ADMINISTRATIVA));
        moduloUnidad.setUnidadGeneral(rs.getBigDecimal(ID_GENERAL));
        moduloUnidad.setFechaInicio(rs.getTimestamp(FECHA_INICIO));
        moduloUnidad.setFechaFin(rs.getTimestamp(FECHA_FIN));
        moduloUnidad.setBlnActivo(rs.getInt(BLN_ACTIVO) > 0);

        moduloUnidad.setModulo(new FececModulosMapper().mapRow(rs, i));

        return moduloUnidad;
    }

}
