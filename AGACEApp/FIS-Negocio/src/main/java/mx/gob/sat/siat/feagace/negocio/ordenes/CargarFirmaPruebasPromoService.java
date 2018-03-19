package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CargarFirmaPruebasPromoService {

   

    void guardarPromocionOficio(final FecetPromocionOficio promocionOficio,
            List<FecetAlegatoOficio> listaPruebasAlegatosOficio) throws NegocioException;

    List<FecetPromocionOficio> getPromocionOficioContadorPruebasAlegatos(final AgaceOrden orden, final FecetOficio oficio);

    List<FecetPromocionOficio> getPromocionOficioContadorPruebasAlegatos(final FecetOficio oficio);

    List<FecetAlegatoOficio> getPruebasAlegatosPromocionOficio(final BigDecimal idPromocionOficio);

    List<String> getLeyendaDoc(AgaceOrden orden, FecetOficio oficio);

   

    /**
     *
     * @param promocionOficio
     * @param listaDocumentosAlegato
     * @param oficio
     */
    void guardarPromocionPruebasAlegatosOficioAuditor(List<FecetPromocionOficio> listaPromociones,
            List<FecetAlegatoOficio> listaDocumentosAlegato,
            final FecetOficio oficio) throws NegocioException;

    FecetContribuyente obtenerContribuyenteInfo(BigDecimal idOrden);
    
    void enviarCorreoPromocion(FecetPromocion promocionGuardar);
    
    void enviarCorreoPromocionOficio(BigDecimal idOrden, FecetPromocionOficio promocionOficioGuardar);
}
