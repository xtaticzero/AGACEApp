/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ReglaMapper implements RowMapper<Regla> {

    static final String ID_REGLA = "REG_ID_REGLA";
    static final String CLAVE = "REG_CLAVE";
    static final String DESCRIPCION = "REG_DESCRIPCION";
    static final String FECHA_INICIO = "REG_FECHA_INICIO";
    static final String FECHA_FIN = "REG_FECHA_FIN";

    @Override
    public Regla mapRow(ResultSet rs, int i) throws SQLException {
        Regla regla = new Regla();

        regla.setIdRegla(rs.getBigDecimal(ID_REGLA));
        regla.setClave(rs.getString(CLAVE));
        regla.setDescripcion(rs.getString(DESCRIPCION));
        regla.setFechaInicio(rs.getTimestamp(FECHA_INICIO));
        regla.setFechaFin(rs.getTimestamp(FECHA_FIN));

        return regla;
    }

}
