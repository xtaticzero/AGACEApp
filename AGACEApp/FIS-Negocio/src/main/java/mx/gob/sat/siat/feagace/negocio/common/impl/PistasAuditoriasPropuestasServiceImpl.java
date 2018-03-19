package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;

@Component("auditoriaPropuestas")
public class PistasAuditoriasPropuestasServiceImpl extends BaseBusinessServices implements PistasAuditoriasPropuestasService {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private static final String MSG_LOG = "Insertando pista";

    @Override
    @PistaAuditoria
    public String pistaCargarDocumentoMasivo(String folio) {
        logger.debug(MSG_LOG);
        return folio;
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaDetallePropuestaPorValidar(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaHistoricoRechazoPropuesta(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaAprobarCanelacionPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaAprobarRechazoPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaAprobarTransferenciaPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaAprobarRetroPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaNoAprobarCanelacionPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaNoAprobarRechazoPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaNoAprobarTransferenciaPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaNoAprobarRetroPendienteValidacion(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaAsignarConsulta(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal pistaGuardarAgenteAduanal(FecetPropuesta propuesta) {
        logger.debug(MSG_LOG);
        return propuesta.getIdPropuesta();
    }

}
