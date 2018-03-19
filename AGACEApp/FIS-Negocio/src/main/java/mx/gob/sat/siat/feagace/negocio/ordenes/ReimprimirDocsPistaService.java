/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ReimprimirDocsPistaService {

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PROMOCION_PROPUESTAS)
    BigDecimal descargaDocumentoPromocion(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PROMOCION_ANEXO_PROPUESTAS)
    BigDecimal descargaDocumentoPromocionAnexo(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_OFICIO_PROPUESTAS)
    BigDecimal descargaOficio(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_ANEXO_OFICIO_PROPUESTAS)
    BigDecimal descargaAnexoOficio(AgaceOrden orden);
    
    /*Por Implementar*/
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_ANEXO_OFICIO_DEPENDIENTE_PROPUESTAS)
    BigDecimal descargaAnexoOficioDependiente(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_OFICIO_DEPENDIENTE_PROPUESTAS)
    BigDecimal descargaOficioDependiente(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RECHAZO_ANEXO_OFICIO)
    BigDecimal descargaRechazoAnexoOficio(FecetPropuesta propuesta);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RECHAZO_OFICIO_DEPENDIENTE)
    BigDecimal descargaRechazoOficioDependiente(FecetPropuesta propuesta);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RECHAZO_ANEXO_OFICIO_DEPENDIENTE)
    BigDecimal descargaRechazoAnexoOficioDependiente(FecetPropuesta propuesta);
    
    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RECHAZO_OFICIO)
    BigDecimal descargaRechazoOficio(FecetPropuesta propuesta);
    
    
    
    
}
