package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

public interface ConsultarReimprimirDocumentosService {

    List<FecetPromocion> buscarPromocionPorOrden(BigDecimal idOrden);

    List<FecetOficio> buscarOficioPorOrden(BigDecimal idOrden);

    List<FecetOficio> buscarOficiosEnviadosCont(BigDecimal idOrden);

    List<FecetOficio> buscarOficioDependientePorOficio(BigDecimal idOficio);

    List<FecetOficioAnexos> buscarOficioAnexoPorOficio(BigDecimal idOficio);

    List<FecetPromocionOficio> buscarPromocionOficioPorOficio(BigDecimal idOficio);

    List<FecetProrrogaOrden> buscarProrrogaPorOrden(BigDecimal idOrden);

    List<FecetAlegato> buscarAlegatosPorPromocion(BigDecimal idPromocion);

    List<FecetAlegatoOficio> buscarAlegatoOficioPorPromocionOficio(BigDecimal idPromocion);

    FecetContribuyente buscarContribuyentePorRfc(String rfc);

    FecetContribuyente buscarContribuyentePorID(BigDecimal idContribuyente);

    List<FecetDocProrrogaOrden> buscarDocProrrogaOrdenPorProrroga(BigDecimal idProrroga);

    EmpleadoDTO buscarEmpleadoPorIdEmpleado(BigDecimal idEmpleado);

    List<FecetProrrogaOficio> buscarProrrogaOficioPorOficio(BigDecimal idOficio);

    List<FecetDocProrrogaOficio> buscarDocProrrogaOficioPorProrroga(BigDecimal idOficioProrroga);

    List<FecetFlujoProrrogaOficio> buscarFlujoProrrogaOficioPorProrroga(BigDecimal idOficioProrroga);

    List<FecetFlujoProrrogaOrden> buscarFlujoProrrogaOrdenPorProrroga(BigDecimal idOrdenProrroga);

    List<FecetAnexosProrrogaOrden> buscarAnexosProrrogaOrdenPorFlujoProrrogaOrden(BigDecimal idFlujoProrrogaOrden);

    List<FecetAnexosProrrogaOficio> buscarAnexosProrrogaOficioPorFlujoProrrogaOficio(BigDecimal idFlujoProrrogaOficio);

    List<AgaceOrden> buscarOrdenPorCriterio(String numeroOrden, String idRegistroPropuesta, String rfcContribuyente, String idEmpleado, Integer idArace);

    List<AgaceOrden> obtenerOrdenesAsociado(String rfcAsociado, BigDecimal idTipoAsociado, String rfcContribuyente);

    StreamedContent obtenerArchivoDescarga(String string, String nombreArchivo) throws IOException;

    void obtenerDocumentosExpediente(BigDecimal idOrden, List<FecetDocOrden> expedienteOrden, List<FecetOficio> oficiosFirmados);

    ColaboradorVO obtenerColaborador(AgaceOrden orden, ColaboradorVO colaborador);

    List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado);

    List<FecetPromocionAgenteAduanal> obtenerDocumentacionAgenteAduanal(AgaceOrden orden, ColaboradorVO colaboradorVO);

    List<FecetAlegatoAgenteAduanal> obtenerAlegatosAgente(FecetPromocionAgenteAduanal fecetPromocionAgenteAduanal);

    List<FecetPruebasPericiales> obtenerPruebasPericiales(BigDecimal idOrden);

    List<FecetDocPruebasPericiales> obtenerDocPruebaPericialByIdPericial(BigDecimal idPruebaPericial);

    FecetOficio obtenerOficioById(final FecetOficio oficio);

    List<AgaceOrden> buscarOrden(String numeroOrden, String idRegistroPropuesta, String rfcContribuyente, String idEmpleado, Integer idArace);

    ColaboradorVO obtenerApoderadoLegalContribuyente(String rfc, ColaboradorVO apoderadoLegal);

    void asignarFechaOficio(FecetOficio oficio);

    void filtrarOficios(List<FecetOficio> listOficio);

    List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio);

    void obtenerfechaNotificacion(FecetOficio oficio);

    FecetCompulsas obtenerCompulsa(BigDecimal idOrdenCompulsa);

}
