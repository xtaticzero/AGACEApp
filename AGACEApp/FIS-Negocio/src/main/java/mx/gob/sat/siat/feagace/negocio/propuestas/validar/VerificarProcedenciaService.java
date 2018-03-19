package mx.gob.sat.siat.feagace.negocio.propuestas.validar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

public interface VerificarProcedenciaService {

    List<FecetPropuesta> obtenerPropuestasAsignadasASubAdmin(String rfcSubAdmin);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_DETALLE_PROPUESTA_EMISION_ORDENES)
    boolean validaEstatusContribuyente(String rfc);

    boolean validaPPEE(String rfc);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APPROBAR_PROPUESTA_EMISION_ORDENES)
    BigDecimal aprobarPropuesta(CargaDocumentoElectronicoDTO cargaDocElectronicoDTO);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_PROPUESTA_EMISION_ORDENES)
    BigDecimal rechazarPropuesta(FecetPropuesta propuesta, FecetContribuyente contribuyenteIdc,
            List<FecetDocExpediente> docOrden);

    List<FecetDocExpediente> obtenerDocsOrden(BigDecimal idPropuesta);

    void enviarCorreo(Map<String, String> data, ReportType pantalla, Set<String> destinatarios);

    
    CargaDocumentoElectronicoDTO validarContribuyenteVersusIdc(String rfc, BigDecimal idContribuyente)
            throws NegocioException;

    void actualizaContribuyente(FecetContribuyente contribuyenteIdc);

    List<FecetOficio> oficiosPorFirmar(BigDecimal idPropuesta);

    void inactivaDoctoOrden(BigDecimal idDocOrden);

    void inactivaOficio(BigDecimal idOficio);

    List<FecetDocOrden> obtenerOrden(BigDecimal idPropuesta);

    List<FecetDocOrden> obtenerDocsOrdenHistorial(BigDecimal idPropuesta);

    List<FecetOficio> oficiosHistorial(BigDecimal idOrden);

    void rechazoOficio(FecetRechazoOficio rechazoOficio);

    void rechazoDoctoOrden(FecetRechazoOrden rechazoDocOrden);

    EmpleadoDTO getDatosEmpleadoSesion(String rfcSesion);

}
