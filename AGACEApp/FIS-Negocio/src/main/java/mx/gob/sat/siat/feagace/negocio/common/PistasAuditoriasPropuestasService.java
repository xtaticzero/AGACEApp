package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public interface PistasAuditoriasPropuestasService {

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_FOLIO_CARGA)
    String pistaCargarDocumentoMasivo(String folio);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.SELECCIONAR_PROPUESTA_POR_VALIDAR)
    BigDecimal pistaDetallePropuestaPorValidar(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.HISTORICO_RETORALIMENTACION)
    BigDecimal pistaHistoricoRechazoPropuesta(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_CANCELACION_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaAprobarCanelacionPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_RECHAZO_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaAprobarRechazoPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_TRANSFERENCIA_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaAprobarTransferenciaPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.APROBAR_RETROALIMENTACION_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaAprobarRetroPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.RECHAZAR_CANCELACION_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaNoAprobarCanelacionPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_RECHAZO_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaNoAprobarRechazoPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_TRANSFERENCIA_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaNoAprobarTransferenciaPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.NO_APROBAR_RETROALIMENTACION_PROPUESTA_POR_VALIDAR_FIRMANTE)
    BigDecimal pistaNoAprobarRetroPendienteValidacion(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.ASIGNAR_CONSULTA)
    BigDecimal pistaAsignarConsulta(FecetPropuesta propuesta);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.GUARDAR_AGENTE_ADUANAL)
    BigDecimal pistaGuardarAgenteAduanal(FecetPropuesta propuesta);

}
