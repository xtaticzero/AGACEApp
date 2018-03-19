package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public interface DocumentacionOficioFirmanteService {
    /**
     * @param oficio
     * @return  List<FecetPromocionOficio>
     */
    List<FecetPromocionOficio> getPruebasAlegatosOficio(final FecetOficio oficio);
    /**
     * @param idPromocionOficio
     * @return  List<FecetAlegatoOficio> 
     */
    List<FecetAlegatoOficio> getPruebasAlegatosPromocion(BigDecimal idPromocionOficio);
    /**
     * @param idProrrogaOficio
     * @return   List<FecetDocProrrogaOficio>
     */
    List<FecetDocProrrogaOficio> getDocumentacionProrrogaContribuyenteOficio(final BigDecimal idProrrogaOficio);
    /**
     * @param idOficio
     * @return  List<FecetProrrogaOficio>
     */
    List<FecetProrrogaOficio> getProrrogasFirmadas(final BigDecimal idOficio);
    /**
     * @param idOficio
     * @return List<FecetProrrogaOficio>
     */
    List<FecetProrrogaOficio> getProrrogasPendientes(final BigDecimal idOficio);
    /**
     * @param fecetProrrogaOficio
     * @return List<FecetAnexosProrrogaOficio>
     */
    List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficio(final FecetProrrogaOficio fecetProrrogaOficio);
    /**
     * @param prorroga
     * @param motivoRechazoFirmante
     * @param status
     * @param listaDocProrroga
     */
    @PistaAuditoria(idOperacion=ConstantesAuditoria.NO_APROBAR_PRORROGA_OFICIO_FIRMANTE)
    BigDecimal rechazaFirmaProrrogaFirmante(FecetProrrogaOficio prorroga, String motivoRechazoFirmante, BigDecimal status,
                                      List<DocumentoVO> listaDocProrroga, FecetOficio oficio);
}
