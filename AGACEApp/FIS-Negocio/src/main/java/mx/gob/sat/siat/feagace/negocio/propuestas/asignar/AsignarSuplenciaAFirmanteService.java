package mx.gob.sat.siat.feagace.negocio.propuestas.asignar;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsignarSuplenciaAFirmanteModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteCargaDisponiblesException;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteUpdateException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

public interface AsignarSuplenciaAFirmanteService {
    List<FecetSuplenciaDTO> findListaSuplencia(List<EmpleadoDTO> listaEmp);

    void cargaFirmantesSuplentesDisponibles(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmp)
            throws AsignarSuplenciaAFirmanteCargaDisponiblesException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ELIMINAR_SUPLENCIA_FIRMANTE)
    void eliminarSuplencia(String idSuplencia, String estatus) throws AsignarSuplenciaAFirmanteUpdateException;

    void obtenerEmpleadoASuplir(BigDecimal idEmpleado, AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws NoExisteEmpleadoException;

    void obtenerEmpleadoSuplente(BigDecimal idEmpleado, AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws NoExisteEmpleadoException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ASIGNAR_SUPLENCIA_FIRMANTE)
    void almacenarSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws AsignarSuplenciaAFirmanteUpdateException;

    void obtenerMotivosSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws AsignarSuplenciaAFirmanteUpdateException;

    boolean buscarSuplenciaRegistrada(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel);

    boolean buscarSuplenciaRegistradaSuplir(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel);

    boolean suplenteTieneSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel);

    void actualizaEstatusSuplencias();

    List<EmpleadoDTO> getEmpleadoFirmante(String rfc) throws NegocioException;

}
