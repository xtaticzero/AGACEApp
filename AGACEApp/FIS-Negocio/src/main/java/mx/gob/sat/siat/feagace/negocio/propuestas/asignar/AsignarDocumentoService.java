package mx.gob.sat.siat.feagace.negocio.propuestas.asignar;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public interface AsignarDocumentoService {

    EmpleadoDTO verificarTipoEmpleadoAdministrador(String rfc);

    List<FecetPropuesta> obtenerPropuestasPendientesDeValidacion(BigDecimal idArace);

    BigDecimal calcularPresuntivaDePropuesta(BigDecimal idPropuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ASIGNAR_DOCUMENTO_ELECTRONICO_VERIFICACION)
    BigDecimal actualizarPropuesta(BigDecimal idPropuesta, int idEstatus, String rfcSub);

    BigInteger obtenerCantidadDePropuestasAsignadas(String rfcSubAdministrador);

    void enviarCorreo(String[] rfcNotificacion, String idRegistro, Set<String> destinatarios, BigDecimal unidadAdministrativa);

    AgaceOrden obtenerOrdenByIdPropuesta(BigDecimal idPropuesta);

    List<FecetDocOrden> obtenerDocOrden(BigDecimal idOrden);

    List<FecetOficio> getOficiosPorFirmar(BigDecimal idOrden);

    void insertaAccion(FecebAccionPropuesta accionPropuesta);

    void updateAccionIdPropuesta(BigDecimal bigDecimal);
}
