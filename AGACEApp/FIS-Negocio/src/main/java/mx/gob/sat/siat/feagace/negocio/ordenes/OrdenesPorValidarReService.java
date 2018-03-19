package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaPorValidar;

public interface OrdenesPorValidarReService {

    /**
     * Regresa una lista de las ordenes con el estatus VALIDAR_IND = 0 y
     * FIRMAR_IND = 0
     * 
     * @param idMetodo
     * @return List
     */
    List<PropuestaPorValidar> getOrdenesPorValidar(BigDecimal idEstatus, BigDecimal idEmpleado, BigDecimal idMetodo,
            BigDecimal idAccionOrigen, EmpleadoDTO empleadoFirmante);

    /**
     * Metodo que cambia el estatus de la orden validada, se cambian a los
     * valores VALIDAR_IND=1 FIRMAR_IND = 0
     * 
     * @param lista
     */
    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_REGISTROS_POR_VALIDAR_FIRMANTE)
    String enviarValidarOrdenes(PropuestaPorValidar propuesta, FecebAccionPropuesta accionPropuesta, FecetPropuesta propuestaOrigen);

    /**
     * Metodo que cambia el estatus de las ordenes por rechazado
     * 
     * @param lista
     */
    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_REGISTROS_POR_VALIDAR_FIRMANTE)
    String enviarRechazoOrden(PropuestaPorValidar propuesta, FecebAccionPropuesta accionPropuesta);

    /**
     * Metodo getListaCadenaOriginal
     * 
     * @param ordenesSeleccionadas
     * @return List Metodo que genera una lista de id para formar la cadena
     *         original de la orden
     */
    List<String> getListaCadenaOriginal(List<AgaceOrden> ordenesSeleccionadas);

    void actualizarRfcAdministrador(final BigDecimal idPropuesta, String rfcAdministrador);

    FecetPropuesta getPropuesta(String idPropuesta);

    List<FecetOficio> getOficiosPorFirmar(BigDecimal idOrden);

    AgaceOrden obtenerOrdenByIdPropuesta(BigDecimal idPropuesta);

    void inactivaDoctoOrden(FecetRechazoOrden rechazoOrden);

    void inactivaOficio(FecetRechazoOficio rechazoOficio);

    List<FecetDocOrden> obtenerDocOrden(BigDecimal idOrden);

    void enviarCorreoAprobar(PropuestaPorValidar propuestaValidar, BigDecimal idLeyenda);

    void enviarCorreoNoAprobar(PropuestaPorValidar propuestaValidar, BigDecimal idLeyenda);

    EmpleadoDTO obtenerDatosFirmanteSuplente(String rfc);

    List<EmpleadoDTO> obtenerAdministradorEmision(BigDecimal idArace);

}
