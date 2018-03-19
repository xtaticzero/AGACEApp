/**
 *
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.validar;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;

/**
 * @author sergio.vaca
 *
 */
public interface ValidarFirmarDoctoService {

    List<FecetPropuesta> obtenerDetalleResumen(String rfc, int idOpcion, List<AraceDTO> unidades);

    void obtenerInformacionAdicional(FecetPropuesta propuesta, List<FecetCancelacion> listaCancelacion,
            List<FecetContadorPropuestasRechazados> listaRechazo, List<FecetTransferenciaContador> listaTransferencia,
            List<FecetRetroContador> listRetroalimentacion, TiposEstatusPropuestaEnum tipoAccion);

    List<DocumentoVista> obtenerDocumentosCancelacion(FecetCancelacion cancelacion);

    List<DocumentoVista> obtenerDocumentosRechazo(FecetContadorPropuestasRechazados rechazoActual);

    List<DocumentoVista> obtenerDocumentosTransferencia(FecetTransferenciaContador transferenciaActual);

    List<DocumentoVista> obtenerDocumentosRetroalimentacion(FecetRetroContador retroActual);

    BigDecimal rechazarProceso(FecetPropuesta propuestaActual, TiposEstatusPropuestaEnum estatusActual, String observaciones, BigDecimal idEmpleado, List<FecetRetroContador> retro);

    BigDecimal aceptarProceso(FecetPropuesta propuestaActual, TiposEstatusPropuestaEnum estatusActual, List<FecetTransferenciaContador> transferencias, BigDecimal idEmpleado, List<FecetRetroContador> retro);

    EmpleadoDTO obtenerDatosFirmante(String rfc);

    List<AraceDTO> obtenerUnidades();
}
