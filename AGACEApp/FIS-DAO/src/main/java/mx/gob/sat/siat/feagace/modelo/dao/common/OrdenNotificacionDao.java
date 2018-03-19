package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;

public interface OrdenNotificacionDao {

    List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden, BigDecimal idPromocion);

    List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden);

    List<String> getLstNombreAuditor(BigDecimal idAuditor, BigDecimal idOrden);

    List<String> getLstCorreoFirmante(BigDecimal idFirmante, BigDecimal idOrden);

    List<String> getLstCorreoAuditor(BigDecimal idAuditor, BigDecimal idOrden);

    List<String> getLstIdOrdenxOficio(BigDecimal idOficio);

    List<OrdenNotificacion> findDetalleContribuyentePromocionOficio(BigDecimal idOrden, BigDecimal idPromocionOficio);

    List<OrdenNotificacion> findDetalleContribuyenteProrroga(BigDecimal idOrden, BigDecimal idProrroga);

    List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficio(BigDecimal idOrden, BigDecimal idProrrogaOficio);

    List<OrdenNotificacion> findDetalleContribuyentePruebaPericial(BigDecimal idOrden, BigDecimal idPruebaPericial);

    List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficioCompulsa(BigDecimal idOrden);

}
