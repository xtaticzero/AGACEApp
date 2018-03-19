package mx.gob.sat.siat.feagace.negocio.reportes;

import java.math.BigDecimal;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.TipoReportesDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

public interface ValidarEmpleadoReportesService {

    TipoReportesDTO validarEmpleado(String rfc) throws NoExisteEmpleadoException;

    List<EmpleadoDTO> obtenerProgramadores(EmpleadoDTO empleado, BigDecimal tipoEmpleado);
    
    List<String> obtenerLstRfcUsuario(TipoEmpleadoEnum tipoEmpleado, TipoAraceEnum unidadAdministrativa);
}
