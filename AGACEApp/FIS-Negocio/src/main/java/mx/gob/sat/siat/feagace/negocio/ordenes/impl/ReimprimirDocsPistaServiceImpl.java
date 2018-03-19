/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.ordenes.ReimprimirDocsPistaService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("reimprimirDocsPistaService")
public class ReimprimirDocsPistaServiceImpl extends BaseBusinessServices implements ReimprimirDocsPistaService{
    private static final long serialVersionUID = 808309156697394498L;

    
    @PistaAuditoria
    @Override
    public BigDecimal descargaDocumentoPromocion(AgaceOrden orden){
        return orden.getIdOrden();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaDocumentoPromocionAnexo(AgaceOrden orden) {
        return orden.getIdOrden();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaOficio(AgaceOrden orden) {
        return orden.getIdOrden();
    }
    
    @PistaAuditoria
    @Override
    public BigDecimal descargaAnexoOficio(AgaceOrden orden) {
        return orden.getIdOrden();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaAnexoOficioDependiente(AgaceOrden orden) {
        return orden.getIdOrden();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaOficioDependiente(AgaceOrden orden) {
        return orden.getIdOrden();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaRechazoAnexoOficio(FecetPropuesta propuesta) {
        return propuesta.getIdPropuesta();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaRechazoOficioDependiente(FecetPropuesta propuesta) {
        return propuesta.getIdPropuesta();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaRechazoAnexoOficioDependiente(FecetPropuesta propuesta) {
        return propuesta.getIdPropuesta();
    }

    @PistaAuditoria
    @Override
    public BigDecimal descargaRechazoOficio(FecetPropuesta propuesta) {
        return propuesta.getIdPropuesta();
    }
}
