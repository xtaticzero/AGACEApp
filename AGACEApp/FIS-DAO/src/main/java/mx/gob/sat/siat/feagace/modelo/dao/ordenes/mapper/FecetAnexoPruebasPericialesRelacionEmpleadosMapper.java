package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;

public class FecetAnexoPruebasPericialesRelacionEmpleadosMapper implements ParameterizedRowMapper<FecetAnexoPruebasPericiales> {

    @Override
    public FecetAnexoPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAnexoPruebasPericiales dto = new FecetAnexoPruebasPericiales();
        FececEmpleado empleado = new FececEmpleado();
        dto.setFechaCreacion(rs.getDate("FECHA_CREACION"));
        dto.setRutaArchivo(rs.getString("RUTA_ARCHIVO"));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString("RUTA_ARCHIVO")));
        dto.setIdAnexoPruebasPericiales(rs.getBigDecimal("ID_ANEXO_PRUEBAS_PERICIALES"));
        empleado.setIdEmpleado(rs.getBigDecimal("ID_EMPLEADO"));
        dto.setFececEmpleado(empleado);
        return dto;
    }
}
