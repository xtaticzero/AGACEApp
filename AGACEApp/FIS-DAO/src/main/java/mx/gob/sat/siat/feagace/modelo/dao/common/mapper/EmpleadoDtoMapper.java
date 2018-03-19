/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;

public class EmpleadoDtoMapper implements ParameterizedRowMapper<EmpleadoDTO> {

    /**
     * Metodo mapRow Hace un mapeo y un set de los datos de la tabla
     * FECEC_EMPLEADO, FECEC_UNIDAD_ADMINISTRATIVA y FECEC_TIPO_EMPLEADO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public EmpleadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEmpleado empleadoAnterior = new FececEmpleadoConAraceTipoMapper().mapRow(rs, rowNum);
        EmpleadoDTO empleado = new EmpleadoDTO();
        empleado.setCorreo(empleadoAnterior.getCorreo());
        empleado.setRfc(empleadoAnterior.getRfc());
        empleado.setNombre(empleadoAnterior.getNombre());
        empleado.setIdEmpleado(empleadoAnterior.getIdEmpleado());
        empleado.setEstatusEmpleado(BigDecimal.ONE.equals(empleadoAnterior.getFececEstatusEmpleado().getIdEstatusEmpleado()) ? EstadoBooleanodeRegistroEnum.ACTIVO : EstadoBooleanodeRegistroEnum.INACTIVO);
        empleado.setDetalleEmpleado(new ArrayList<DetalleEmpleadoDTO>());
        DetalleEmpleadoDTO detalleEmpleado = new DetalleEmpleadoDTO();
        detalleEmpleado.setTipoEmpleado(TipoEmpleadoEnum.parse(empleadoAnterior.getFececTipoEmpleado().getIdTipoEmpleado().intValue()));
        AraceDTO central = new AraceDTO();
        central.setIdArace(empleadoAnterior.getFecetDetalleEmpleado().getIdCentral().intValue());
        central.setCentral(TipoEmpleadoEnum.CONSULTOR_INSUMOS.equals(detalleEmpleado.getTipoEmpleado()) ? EstadoBooleanoEnum.TRUE : EstadoBooleanoEnum.FALSE);
        central.setSede(empleadoAnterior.getSede());
        central.setNombre(empleadoAnterior.getSede());
        detalleEmpleado.setCentral(central);
        empleado.getDetalleEmpleado().add(detalleEmpleado);

        return empleado;
    }
}
