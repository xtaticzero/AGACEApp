package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAnexosProrrogaOrdenRelacionEmpleadosMapper implements ParameterizedRowMapper<FecetAnexosProrrogaOrden> {

    @Override
    public FecetAnexosProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAnexosProrrogaOrden dto = new FecetAnexosProrrogaOrden();
        FececEmpleado empleado = new FececEmpleado();
        dto.setFechaCreacion(rs.getDate("FECHA_CREACION"));
        dto.setRutaArchivo(rs.getString("RUTA_ARCHIVO"));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString("RUTA_ARCHIVO")));
        dto.setIdAnexosProrrogaOrden(rs.getBigDecimal("ID_ANEXOS_PRORROGA_ORDEN"));
        empleado.setIdEmpleado(rs.getBigDecimal("ID_EMPLEADO"));
        dto.setFececEmpleado(empleado);
        return dto;
    }
}
