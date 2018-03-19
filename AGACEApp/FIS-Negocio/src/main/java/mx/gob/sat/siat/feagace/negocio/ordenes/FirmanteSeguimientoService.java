package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

public interface FirmanteSeguimientoService {

    List<FecetPromocion> getPromocionContadorPruebasAlegatos(final BigDecimal idOrden);

    Map<String, List<FecetOficio>> getOficiosFirmante(final BigDecimal idOrden);

    Map<String, List<FecetProrrogaOrden>> getProrrogasOrden(final BigDecimal idOrden);

    List<FecetOficio> getOficiosDependientes(final BigDecimal idOficio, final BigDecimal estatusOficio);

    /**
     * Rechaza Firma Oficio
     *
     * @param oficio
     * @param orden
     * @param listaDocumentosAnexos
     * @param estatus
     * @param descripcionRechazo
     * @throws IOException
     */
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.VALIDAR_FIRMAR_NO_APROBAR_OFICIO_ORDEN)
    BigDecimal rechazaOficio(FecetOficio oficio, AgaceOrden orden, List<DocumentoVO> listaDocumentosAnexos,
            BigDecimal estatus, String descripcionRechazo) throws IOException;

    /**
     * Rechaza Firma Prorroga
     *
     * @param prorroga
     * @param status
     * @param listaDocProrroga
     */
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.NO_APROBAR_PRORROGA_ORDEN_FIRMANTE)
    BigDecimal rechazaFirmaProrrogaFirmante(final FecetProrrogaOrden prorroga, final String motivoRechazoFirmante,
            final BigDecimal status, final List<DocumentoVO> listaDocProrroga);

    /**
     * @param idEmpleado
     * @return
     */
    
    List<AgaceOrden> getOrdenesFirmarDocumentos(final BigDecimal idEmpleado) throws NoExisteEmpleadoException; 

    /**
     * Prorrga Orden
     */
    List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion);

    List<FecetAnexosProrrogaOrden> getAnexosRechazoProrrogaOrden(final FecetProrrogaOrden fecetProrrogaOrden);

    List<FecetDocProrrogaOrden> getDocumentacionProrrogaContribuyente(final BigDecimal idProrrogaOrden);

    List<FecetOficioAnexos> getAnexosOficioRechazo(final BigDecimal idOficio);

    List<FecetPromocion> getPromocionContadorPruebasAlegatos(AgaceOrden orden);

    FecetCompulsas obtenerDatosEncabezado(BigDecimal idOficio);

    FecetCambioMetodo obtenerDatosEncabezadoCambioMetodo(BigDecimal idOrden);
    
    List<FecetOficio> getDetalleOficioPendienteFirma(BigDecimal idOficio);
    
    List<FecetOficio> getOficiosDependientesFirmados(BigDecimal idOficio);
    
    ColaboradorVO obtenerColaborador(AgaceOrden ordenSeleccionada, BigDecimal idRepresentanteLegal);
    
    Map<String, List<FecetPruebasPericiales>> getPruebasPericiales(BigDecimal idOrden);
    
    List<FecetAnexoPruebasPericiales> getAnexosRechazoPruebasPericiales(final FecetPruebasPericiales fecetPruebaPericial);
    
    void rechazaFirmaPruebaPericialFirmante(FecetPruebasPericiales pruebaPericial, String motivoRechazoFirmante,
            BigDecimal status, List<DocumentoVO> listaDocPruebasPericiales);
    
    BigDecimal obtenerIdAraceFromPropuesta(BigDecimal idOrden);
    
    List<FecetDocPruebasPericiales> getDocumentacionPruebaPericialContribuyente(final BigDecimal idPruebasPericiales);
    
    List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(BigDecimal idOrden);
    
    List<FecetAlegatoAgenteAduanal> getAlegatosAgenteAduanal(final BigDecimal idPromocion);
    
    String getDescripcion(BigDecimal idEstatus);
    
    List<FececEstatus> getListaEstatus();
    
    List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio);
}

