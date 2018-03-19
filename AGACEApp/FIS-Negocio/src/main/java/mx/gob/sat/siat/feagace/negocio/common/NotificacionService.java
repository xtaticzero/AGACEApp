/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.gob.sat.siat.common.correo.model.MailMessageAttachment;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;


/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public interface NotificacionService {

    void enviarNotificacion(Map<String, String> data, ReportType reportType,
            Set<String> destinatarios) throws BusinessException;

    void enviarNotificacionGenerico(Map<String, String> data, ReportType reportType, Set<String> destinatarios, MailMessageAttachment... adjunto)
            throws BusinessException;

    List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden, BigDecimal idPromocion);

    List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden);

    List<String> getLstNombreAuditor(BigDecimal idAuditor, BigDecimal idOrden);

    List<String> getLstCorreoFirmante(BigDecimal idFirmante, BigDecimal idOrden);

    List<String> getLstCorreoAuditor(BigDecimal idAuditor, BigDecimal idOrden);

    List<String> getLstIdOrdenxOficio(BigDecimal idOficio);

    List<OrdenNotificacion> findDetalleContribuyentePromocionOficio(BigDecimal idOrden, BigDecimal idPromocionOficio);

    List<OrdenNotificacion> findDetalleContribuyenteProrrogaOrden(BigDecimal idOrden, BigDecimal idProrrogaOrden);

    List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficio(BigDecimal idOrden, BigDecimal idProrrogaOficio);

    void obtenerCorreoEmpleado(String rfc, BigDecimal tipoEmpleado, Set<String> destinatarios, ClvSubModulosAgace clvModulo);
    
    void obtenerCorreoEmpleado(Integer idEmpleado, BigDecimal tipoEmpleado, Set<String> destinatarios, ClvSubModulosAgace clvModulo);

    void obtenerCorreoEmpleado(BigDecimal tipoEmpleado, BigDecimal unidadAdministrativa, Set<String> destinatarios, ClvSubModulosAgace clvModulo);

    void obtenerCorreoCentralAcppce(String rfcSesion, 
            long tipoEmpleado, 
            BigDecimal unidadAdministrativa, 
            Set<String> destinatarios,
            ClvSubModulosAgace clvModulo);

    Map<String, String> obtenerDatosCorreo(BigDecimal idLeyenda);
    
    List<OrdenNotificacion> findDetalleContribuyentePruebaPericialOrden(BigDecimal idOrden, BigDecimal idProrrogaOrden);
    
    List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficioCompulsa(BigDecimal idOrden);
    
    
}
