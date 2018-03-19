package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.common.PistaAuditoriaDescargaDocumentosService;

@Component("auditoriaDocumentos")
public class PistaAuditoriaDescargaDocumentosServiceImpl extends BaseBusinessServices implements PistaAuditoriaDescargaDocumentosService {

    /**
     *
     */
    private static final long serialVersionUID = 413018994902022466L;
    
    private static final String MSG_LOG = "Insertando Pista";

    @Override
    @PistaAuditoria
    public BigDecimal consultarOrdenExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioCompulsaExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarProrrogaOrdenExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarProrrogaOficioExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOrdenExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOficioExterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOrdenInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioCompulsaInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarProrrogaOrdenInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarProrrogaOficioInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOrdenInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOficioInterno(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocion(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionAnexo(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficio(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarAnexoOficio(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioDependiente(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarAnexoOficioDependiente(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarProrroga(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarResolucionProrroga(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarAnexoResolucionProrroga(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarRepresentanteLegal(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionAgenteAduanal(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionAnexoAgenteAduanal(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOficio(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionAnexoOficio(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPromocionOficioProrroga(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioProrrogaResolucion(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarOficioAnexoProrrogaResol(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

    @Override
    @PistaAuditoria
    public BigDecimal consultarPapelTrabajo(AgaceOrden orden) {
        logger.debug(MSG_LOG);
        return orden.getIdOrden();
    }

}
