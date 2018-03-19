package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;


public interface AsociadosService {

    List<FececTipoAsociado> construyeComboPerfil(String rfc);

    List<String> construyeComboPerfilRFC(String rfc, BigDecimal idTipoAsociado);

    ColaboradorVO obtenerApoderadoLegalContribuyente(String rfc, ColaboradorVO apoderadoLegal);

    void insertaColaborador(FecetAsociado colaborador);

    void eliminaColaboradorContribuyente(BigDecimal idOrden, BigDecimal idTipoAsociado);

    void actualizaColaboradorContribuyente(FecetAsociado apoderadoLegal, BigDecimal idOrden);

    ColaboradorVO configuraColaborador(ColaboradorVO colaborador, FecetContribuyente contribuyenteIDC);

    ColaboradorVO nuevaBusquedaColaborador(ColaboradorVO colaborador);

    ColaboradorVO sinColaborador(ColaboradorVO colaborador);

    String asociaColaborador(String rfcContribuyente, ColaboradorVO colaborador, AgaceOrden orden);

    ColaboradorVO cambiaEmailInputText(ColaboradorVO colaborador, boolean flag);

    ColaboradorVO cambiaCamposTxt(ColaboradorVO colaborador, boolean flag);

    ColaboradorVO cargaColaborador(ColaboradorVO colaborador, AgaceOrden orden);

    List<AgaceOrden> obtenerOrdenesContribuyente(String rfc, BigDecimal idTipoAsociado);

    BigDecimal obtenerIdAsociado(String rfc, BigDecimal idTipoAsociado, String rfcContribuyente);

    void confirmarAsociado(String rfcContribuyente, String rfc, BigDecimal idTipoAsociado, BigDecimal idOrden);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_HISTORICO_OFICIOS_ORDEN)
    List<FecetOficio> getOficiosFirmados(AgaceOrden orden);

    List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_HISTORICO_PROMOCIONES_ORDEN)
    List<FecetPromocion> getPromocionContadorPruebasAlegatos(AgaceOrden orden);
    
    @PistaAuditoria(idOperacion= ConstantesAuditoria.CONSULTA_HISTORICO_PRORROGAS_ORDEN)
    List<FecetProrrogaOrden> getHistoricoProrrogaOrden(final BigDecimal idOrden);

    List<FecetDocProrrogaOrden> getDocsProrrogaOrden(final BigDecimal idProrrogaOrden);

    ValidaMediosContactoBO validaMediosContactoAsociado(ValidaMediosContactoBO validaMediosContactoBO);

    ColaboradorVO cargaListaMediosContacto(ColaboradorVO colaborador, ValidaMediosContactoBO validaMediosContactoBO);

    List<AgaceOrden> obtenerOrdenesAsociado(String rfcAsociado, BigDecimal idTipoAsociado, String rfcContribuyente);

    List<FecetCompulsas> obtenerCompulsasContribuyente(String rfcContribuyente);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_OFICIOS_DEPENDIENTES)
    List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio);

    FecetOficio obtenerOficioCompulsa(BigDecimal idOrdenAuditada, BigDecimal idOrdenCompulsa);

    List<FecetOficioAnexos> obtenerOficioAnexos(BigDecimal idOficio);

    List<FecetOficio> getOficiosAdministrables(final BigDecimal idOrden);
    
    void actualizarEstatusNotificacionPruebasPericiales(List<AgaceOrden> listaOrdenes);
    
    List<FecetDocPruebasPericiales> getDocsPruebasPericiales(final BigDecimal idPruebaPericiales);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_HSITORICO_PRUEBAS_PERICIALES)
    List<FecetPruebasPericiales> getHistoricoPruebasPericiales(final BigDecimal idOrden);
    
    List<FecetDocOrden> obtenerDocExpediente(BigDecimal idOrden);
}
