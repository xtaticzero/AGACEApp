package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

public interface PistaAuditoriaDescargaDocumentosService {

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_ORDENES_EXTERNO)
    BigDecimal consultarOrdenExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_OFICIOS_EXTERNO)
    BigDecimal consultarOficioExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_COMPULSAS_EXTERNO)
    BigDecimal consultarOficioCompulsaExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PRORROGA_ORDEN_EXTERNO)
    BigDecimal consultarProrrogaOrdenExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PRORROGA_OFICIOS_EXTERNO)
    BigDecimal consultarProrrogaOficioExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PROMOCION_ORDEN_EXTERNO)
    BigDecimal consultarPromocionOrdenExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PROMOCION_OFICIOS_EXTERNO)
    BigDecimal consultarPromocionOficioExterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_ORDENES_INTERNO)
    BigDecimal consultarOrdenInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_OFICIOS_INTERNO)
    BigDecimal consultarOficioInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_COMPULSAS_INTERNO)
    BigDecimal consultarOficioCompulsaInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PRORROGA_ORDEN_INTERNO)
    BigDecimal consultarProrrogaOrdenInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PRORROGA_OFICIOS_INTERNO)
    BigDecimal consultarProrrogaOficioInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PROMOCION_ORDEN_INTERNO)
    BigDecimal consultarPromocionOrdenInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_REIMPRIMIR_PROMOCION_OFICIOS_INTERNO)
    BigDecimal consultarPromocionOficioInterno(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PROMOCION_PROPUESTAS)
    BigDecimal consultarPromocion(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PROMOCION_ANEXO_PROPUESTAS)
    BigDecimal consultarPromocionAnexo(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_OFICIO_PROPUESTAS)
    BigDecimal consultarOficio(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_ANEXO_OFICIO_PROPUESTAS)
    BigDecimal consultarAnexoOficio(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_OFICIO_DEPENDIENTE_PROPUESTAS)
    BigDecimal consultarOficioDependiente(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_ANEXO_OFICIO_DEPENDIENTE_PROPUESTAS)
    BigDecimal consultarAnexoOficioDependiente(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PRORROGA)
    BigDecimal consultarProrroga(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RESOLUCION_PRORROGA)
    BigDecimal consultarResolucionProrroga(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_ANEXO_RESOLUCION_PRORROGA)
    BigDecimal consultarAnexoResolucionProrroga(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_REPRESENTANTE_LEGAL)
    BigDecimal consultarRepresentanteLegal(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_PROMOCION_AGENTE_ADUANAL)
    BigDecimal consultarPromocionAgenteAduanal(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_PROMOCION_ANEXO_AGENTE_ADUANAL)
    BigDecimal consultarPromocionAnexoAgenteAduanal(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_PROMOCION_OFICIO)
    BigDecimal consultarPromocionOficio(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_PROMOCION_ANEXO_OFICIO)
    BigDecimal consultarPromocionAnexoOficio(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_PROMOCION_PRORROGA_OFICIO)
    BigDecimal consultarPromocionOficioProrroga(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RESOLUCION_PRORROGA_OFICIO)
    BigDecimal consultarOficioProrrogaResolucion(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_RESOLUCION_ANEXO_PRORROGA_OFICIO)
    BigDecimal consultarOficioAnexoProrrogaResol(AgaceOrden orden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.DESCARGA_DOCUMENTO_PAPEL_TRABAJO)
    BigDecimal consultarPapelTrabajo(AgaceOrden orden);

}
