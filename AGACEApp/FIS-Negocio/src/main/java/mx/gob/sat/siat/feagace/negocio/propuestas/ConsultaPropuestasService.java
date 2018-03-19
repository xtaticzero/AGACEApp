package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultaPropuestasService {

    List<AraceDTO> getFececUnidadesAdministrativas();

    List<FececEstados> construyeComboEntidad(EmpleadoDTO empleado);

    List<FececEstados> traeTodasLasEntidades();

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_PROPUESTAS_RECHAZADAS)
    List<ConsultaPropuestas> cargarPropuestasPorArace(EmpleadoDTO empleado, boolean central, Object... args);

    HSSFWorkbook exportarConsultaPropuestas(List<ConsultaPropuestas> listaInformeComite);

    EmpleadoDTO consultaEmpleado(String rfc) throws EmpleadoServiceException, NegocioException;

}
