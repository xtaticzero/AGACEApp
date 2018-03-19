package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public interface AuditoriaService {
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ACCESAR_PERFIL_CONTIRBUYENTE)
    String accesarPerfilContribuyente(PerfilContribuyenteVO perfil);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ACCESAR_PERFIL_ASOCIADO)
    BigDecimal accesarPerfilAsociado(PerfilContribuyenteVO perfil);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_DETALLE_ORDENES_ASIGANDAS)
    BigDecimal detalleOrden(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_DETALLE_COMPULSAS_TERCEROS)
    BigDecimal detalleCompulsa(FecetCompulsas compulsa);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASOCIAR_APODERADO_LEGAL)
    String dasociarApoderadoLegal(ColaboradorVO colaborador);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASOCIAR_REPRESENTANTE_LEGAL)
    BigDecimal asociarRepresentanteLegal(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASOCIAR_AGENTE_ADUANAL)
    BigDecimal asociarAgenteAduanal(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASOCIAR_APODERADO_REPRESENTANTE_LEGAL)
    BigDecimal asociarApoderadoRepresentante(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CARGAR_PROMOCIONES_ORDEN)
    BigDecimal cargarPromocionOrden(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.SOLICITD_PRORROGA_ORDEN)
    BigDecimal cargarProrrogaOrden(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CARGAR_PROMOCIONES_OFICIO)
    BigDecimal cargarPromocionOficio(FecetOficio oficio);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.SOLICITUD_PRORROGA_OFICIO)
    BigDecimal cargarProrrogaOficio(FecetOficio oficio);        
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.SOLICITUD_OFICIO_PRUEBAS_PERICIALES)
    BigDecimal cargarPruebasPericialesOficio(AgaceOrden oficio);        
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.APROBAR_PRORROGA_ORDEN_AUDITOR)
    BigDecimal aprobarProrrogaOrdenAuditor(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.NO_APROBAR_PRORROGA_ORDEN_AUDITOR)
    BigDecimal noAprobarProrrogaOrdenAuditor(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.APROBAR_PRORROGA_OFICIO_AUDITOR)
    BigDecimal aprobarProrrogaOficioAuditor(FecetOficio oficio);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.NO_APROBAR_PRORROGA_OFICIO_AUDITOR)
    BigDecimal noAprobarProrrogaOficioAuditor(FecetOficio oficio);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASIGNAR_CONSULTA)
    BigDecimal asignarConsulta(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.VALIDAR_FIRMAR_OFICIO_ORDEN)
    BigDecimal crearPistaAuditoriaOficio(FecetOficio oficio);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.APROBAR_PRORROGA_ORDEN_FIRMANTE)
    BigDecimal aprobarProrrogaOrdenFirmante(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.APROBAR_PRORROGA_OFICIO_FIRMANTE)
    BigDecimal aprobarProrrogaOficioFirmante(FecetOficio oficio);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.FIRMAR_REGISTROS_POR_VALIDAR_FIRMANTE)
    BigDecimal firmarPropuesta(FecetPropuesta propuesta);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_GERENCIAL_INSUMOS)
    String generarReporteGerencialInsumos(String rfcEmpleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_GERENCIAL_PROPUESTAS)
    String generarReporteGerencialPropuestas(String rfcEmpleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_GERENCIAL_ORDENES)
    String generarReporteGerencialOrdenes(String rfcEmpleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_EJECUTIVO_INSUMOS)
    String generarReporteEjecutivoInsumos(String rfcEmpleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_EJECUTIVO_PROPUESTAS)
    String generarReporteEjecutivoPropuestas(String rfcEmpleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.GENERAR_REPORTE_EJECUTIVO_ORDENES)
    String generarReporteEjecutivoOrdenes(String rfcEmpleado);

}
