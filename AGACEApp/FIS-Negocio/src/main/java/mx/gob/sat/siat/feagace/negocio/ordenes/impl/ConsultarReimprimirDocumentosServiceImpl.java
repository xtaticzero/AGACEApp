package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.plantillador.helper.PlantilladorDocxHelper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.ConsultarReimprimirDocumentosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetPromocionAgenteAduanalService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesArchivosUtil;

@Service("consultarReimprimirDocumentosService")
public class ConsultarReimprimirDocumentosServiceImpl extends OrdenesServiceBase
        implements ConsultarReimprimirDocumentosService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient FecetAlegatoDao fecetAlegatoDao;

    @Autowired
    private transient FecetAlegatoAgenteAduanalDao fecetAlegatoAgenteAduanalDao;

    @Autowired
    private transient FecetAlegatoOficioDao fecetAlegatoOficioDao;

    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;

    @Autowired
    private transient FecetOficioAnexosDao fecetOficioAnexosDao;

    @Autowired
    private transient FecetPromocionOficioDao fecetPromocionOficioDao;

    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;

    @Autowired
    private transient FecetDocProrrogaOrdenDao fecetDocProrrogaOrdenDao;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient FecetDocProrrogaOficioDao fecetDocProrrogaOficioDao;

    @Autowired
    private transient FecetFlujoProrrogaOficioDao fecetFlujoProrrogaOficioDao;

    @Autowired
    private transient FecetFlujoProrrogaOrdenDao fecetFlujoProrrogaOrdenDao;

    @Autowired
    private transient FecetAnexosProrrogaOrdenDao fecetAnexosProrrogaOrdenDao;

    @Autowired
    private transient FecetAnexosProrrogaOficioDao fecetAnexosProrrogaOficio;

    @Autowired
    private transient PlazosServiceOrdenes plazosService;

    @Autowired
    private transient ContribuyenteService contribuyenteService;

    @Autowired
    private transient AsociadosService asociadosService;

    @Autowired
    private PlantilladorDocxHelper plantillador;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient FecetDocAsociadoDao fecetDocAsociadoDao;

    @Autowired
    private transient FecetPromocionAgenteAduanalService fecetPromocionAgenteAduanalService;

    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    @Autowired
    private transient FecetDocPruebasPericialesDao fecetDocPruebasPericialesDao;

    @Autowired
    private transient FecetFlujoPruebasPericialesDao fecetFlujoPruebasPericialesDao;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Autowired
    private transient FecetCompulsasDao fecetCompulsasDao;

    private static final int SIZE_BUFFER = 16384;

    @Override
    public List<FecetPromocion> buscarPromocionPorOrden(BigDecimal idOrden) {
        return fecetPromocionDao.getPromocionContadorPruebasAlegatos(idOrden);
    }

    @Override
    public List<FecetOficio> buscarOficioPorOrden(BigDecimal idOrden) {
        return fecetOficioDao.getOficioByIdOrden(idOrden);
    }

    @Override
    public List<FecetOficio> buscarOficiosEnviadosCont(BigDecimal idOrden) {
        return fecetOficioDao.getOficioByIdOrdenIdEstatus(idOrden,
                EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(),
                EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
    }

    @Override
    public List<FecetOficio> buscarOficioDependientePorOficio(BigDecimal idOficio) {
        return fecetOficioDao.getOficiosDependientesByIdOficioPrincipal(idOficio);
    }

    @Override
    public List<FecetProrrogaOrden> buscarProrrogaPorOrden(BigDecimal idOrden) {
        return fecetProrrogaOrdenDao.getProrrogaContadorDoc(idOrden);
    }

    @Override
    public List<FecetAlegato> buscarAlegatosPorPromocion(BigDecimal idPromocion) {
        return fecetAlegatoDao.findWhereIdPromocionEquals(idPromocion);
    }

    @Override
    public List<FecetOficioAnexos> buscarOficioAnexoPorOficio(BigDecimal idOficio) {
        return fecetOficioAnexosDao.getAnexosByIdOficio(idOficio);
    }

    @Override
    public List<FecetPromocionOficio> buscarPromocionOficioPorOficio(BigDecimal idOficio) {
        return fecetPromocionOficioDao.getPromocionContadorPruebasAlegatosOficio(idOficio);
    }

    @Override
    public FecetContribuyente buscarContribuyentePorRfc(String rfc) {
        FecetContribuyente contribuyente = null;
        try {
            contribuyente = contribuyenteService.getContribuyenteIDC(rfc);
        } catch (final NoExisteContribuyenteException exception) {
            logger.error("No existe rfc de contribuyente en servicio IDC.", exception);
        }
        return contribuyente;
    }

    @Override
    public FecetContribuyente buscarContribuyentePorID(BigDecimal idContribuyente) {
        List<FecetContribuyente> listFecetContribuyente = fecetContribuyenteDao
                .findWhereIdContribuyenteEquals(idContribuyente);
        if (listFecetContribuyente != null && !listFecetContribuyente.isEmpty()) {
            return listFecetContribuyente.get(0);
        }
        return null;
    }

    @Override
    public List<FecetAlegatoOficio> buscarAlegatoOficioPorPromocionOficio(BigDecimal idPromocionOficio) {
        return fecetAlegatoOficioDao.findWhereIdPromocionEquals(idPromocionOficio);
    }

    @Override
    public List<FecetDocProrrogaOrden> buscarDocProrrogaOrdenPorProrroga(BigDecimal idProrroga) {
        return fecetDocProrrogaOrdenDao.findWhereIdProrrogaOrdenEquals(idProrroga);
    }

    @Override
    public EmpleadoDTO buscarEmpleadoPorIdEmpleado(BigDecimal idEmpleado) {
        EmpleadoDTO empleado = null;
        try {
            empleado = empleadoService.getEmpleadoCompleto(idEmpleado.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
        return empleado;
    }

    @Override
    public List<FecetProrrogaOficio> buscarProrrogaOficioPorOficio(BigDecimal idOficio) {
        return fecetProrrogaOficioDao.getProrrogaOficioContadorDoc(idOficio);
    }

    @Override
    public List<FecetDocProrrogaOficio> buscarDocProrrogaOficioPorProrroga(BigDecimal idOficioProrroga) {
        return fecetDocProrrogaOficioDao.findWhereIdProrrogaOficioEquals(idOficioProrroga);
    }

    @Override
    public List<FecetFlujoProrrogaOficio> buscarFlujoProrrogaOficioPorProrroga(BigDecimal idOficioProrroga) {
        return fecetFlujoProrrogaOficioDao.buscarFlujoPorIdProrrogaOficio(idOficioProrroga);
    }

    @Override
    public List<FecetFlujoProrrogaOrden> buscarFlujoProrrogaOrdenPorProrroga(BigDecimal idOrdenProrroga) {
        return fecetFlujoProrrogaOrdenDao.buscarFlujoPorIdProrrogaOrden(idOrdenProrroga);
    }

    @Override
    public List<FecetAnexosProrrogaOrden> buscarAnexosProrrogaOrdenPorFlujoProrrogaOrden(
            BigDecimal idFlujoProrrogaOrden) {
        return fecetAnexosProrrogaOrdenDao.findWhereIdFlujoProrrogaOrdenEquals(idFlujoProrrogaOrden);
    }

    @Override
    public List<FecetAnexosProrrogaOficio> buscarAnexosProrrogaOficioPorFlujoProrrogaOficio(
            BigDecimal idFlujoProrrogaOficio) {
        return fecetAnexosProrrogaOficio.findWhereIdFlujoProrrogaOficioEquals(idFlujoProrrogaOficio);
    }

    @Override
    public List<AgaceOrden> buscarOrdenPorCriterio(String numeroOrden, String idRegistroPropuesta,
            String rfcContribuyente, String idEmpleado, Integer idArace) {
        return plazosService.filtraOrdenPorFecha(getAgaceOrdenDao().getOrdenesContribuyenteCriterio(numeroOrden,
                idRegistroPropuesta, rfcContribuyente, idEmpleado, idArace));
    }

    @Override
    public List<AgaceOrden> buscarOrden(String numeroOrden, String idRegistroPropuesta, String rfcContribuyente,
            String idEmpleado, Integer idArace) {
        return getAgaceOrdenDao().getOrdenesContribuyenteCriterio(numeroOrden, idRegistroPropuesta, rfcContribuyente,
                idEmpleado, idArace);
    }

    @Override
    public List<AgaceOrden> obtenerOrdenesAsociado(String rfcAsociado, BigDecimal idTipoAsociado,
            String rfcContribuyente) {
        return plazosService.filtraOrdenPorFecha(
                getAgaceOrdenDao().getOrdenesAsociadoConsulta(rfcAsociado, idTipoAsociado, rfcContribuyente));
    }

    @Override
    public StreamedContent obtenerArchivoDescarga(String ruta, String nombreArchivo) throws IOException {
        ByteArrayInputStream entrada = null;
        if (StringUtils.isNotBlank(ruta) && StringUtils.isNotBlank(nombreArchivo)) {
            final ByteArrayOutputStream salida = new ByteArrayOutputStream();
            FileInputStream archivoEntrada = null;
            try {
                archivoEntrada = new FileInputStream(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
                entrada = new ByteArrayInputStream(lecturaArchivo(archivoEntrada));
                if (nombreArchivo.toLowerCase().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
                    plantillador.generaPDFEnlinea(entrada, salida);
                    salida.close();
                    entrada.close();
                    entrada = new ByteArrayInputStream(salida.toByteArray());
                }
                String nombreDescarga = nombreArchivo
                        .replace(Constantes.ARCHIVO_WORD_DESPUES_2007, Constantes.ARCHIVO_PDF)
                        .replace(Constantes.ARCHIVO_WORD_DESPUES_2007.toUpperCase(), Constantes.ARCHIVO_PDF);
                return new DefaultStreamedContent(entrada, ConstantesArchivosUtil.CONTENT_TYPE_PDF,
                        aplicarCodificacionTexto(nombreDescarga));
            } finally {
                IOUtils.closeQuietly(salida);
                IOUtils.closeQuietly(entrada);
                if (archivoEntrada != null) {
                    IOUtils.closeQuietly(archivoEntrada);
                }
            }
        }
        return null;
    }

    @Override
    public void obtenerDocumentosExpediente(BigDecimal idOrden, List<FecetDocOrden> expedienteOrden,
            List<FecetOficio> oficiosFirmados) {
        expedienteOrden.addAll(fecetDocOrdenDao.findByFecetOrdenPdf(idOrden));
        oficiosFirmados.addAll(fecetOficioDao.getOficiosAdministrablesPorIdOrden(idOrden));
    }

    @Override
    public ColaboradorVO obtenerColaborador(AgaceOrden orden, ColaboradorVO colaborador) {
        return asociadosService.cargaColaborador(colaborador, orden);
    }

    @Override
    public ColaboradorVO obtenerApoderadoLegalContribuyente(String rfc, ColaboradorVO apoderadoLegal) {
        return asociadosService.obtenerApoderadoLegalContribuyente(rfc, apoderadoLegal);

    }

    @Override
    public List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado) {
        return fecetDocAsociadoDao.obtenerDocumentosPorAsociado(idAsociado);
    }

    @Override
    public List<FecetPromocionAgenteAduanal> obtenerDocumentacionAgenteAduanal(AgaceOrden orden,
            ColaboradorVO colaboradorVO) {
        return fecetPromocionAgenteAduanalService.obtenerRegistrosByOrdenAsociado(orden, colaboradorVO);
    }

    @Override
    public List<FecetAlegatoAgenteAduanal> obtenerAlegatosAgente(
            FecetPromocionAgenteAduanal fecetPromocionAgenteAduanal) {
        return fecetAlegatoAgenteAduanalDao
                .obtenerAlegatosAgenteByIdPromocion(fecetPromocionAgenteAduanal.getIdPromocionAgenteAduanal());
    }

    @Override
    public List<FecetPruebasPericiales> obtenerPruebasPericiales(BigDecimal idOrden) {
        List<FecetPruebasPericiales> registros = fecetPruebasPericialesDao.getPruebasPericialesContadorDoc(idOrden);
        if (registros != null && !registros.isEmpty()) {
            List<FecetFlujoPruebasPericiales> registrosFlujo;
            for (FecetPruebasPericiales fecetPruebasPericiales : registros) {
                registrosFlujo = fecetFlujoPruebasPericialesDao
                        .obtenerFlujoPruebaById(fecetPruebasPericiales.getIdPruebasPericiales());
                if (registrosFlujo != null && !registrosFlujo.isEmpty()) {
                    fecetPruebasPericiales.setFlujoPruebasPericiales(registrosFlujo.get(0));
                } else {
                    fecetPruebasPericiales.setFlujoPruebasPericiales(new FecetFlujoPruebasPericiales());
                    fecetPruebasPericiales.getFlujoPruebasPericiales().setTotalAnexos(0);
                }
            }
        }
        return registros;
    }

    @Override
    public List<FecetDocPruebasPericiales> obtenerDocPruebaPericialByIdPericial(BigDecimal idPruebaPericial) {
        return fecetDocPruebasPericialesDao.findWhereIdPruebasPericialesEquals(idPruebaPericial);
    }

    @Override
    public FecetOficio obtenerOficioById(final FecetOficio oficio) {
        return fecetOficioDao.getOficioByIdOficio(oficio.getIdOficioPrincipal()).get(0);
    }

    @Override
    public void asignarFechaOficio(FecetOficio oficio) {
        plazosService.asignarFechasNotificacion(oficio);
    }

    @Override
    public void filtrarOficios(List<FecetOficio> listOficio) {
        plazosService.filtarOficios(listOficio);
    }

    @Override
    public List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio) {
        return fecetOficioDao.getOficiosDependientesPorIdEstatusContribuyente(idOficio);
    }

    @Override
    public void obtenerfechaNotificacion(FecetOficio oficio) {
        plazosService.obtenerFechasNotificacion(oficio);
    }

    private String aplicarCodificacionTexto(String cadena) {
        try {
            return new String(cadena.getBytes(ConstantesArchivosUtil.UTF_8), ConstantesArchivosUtil.UTF_8);
        } catch (Exception e) {
            logger.error("Error al codificar el texto [{}]", e);
            return cadena;
        }
    }

    private byte[] lecturaArchivo(FileInputStream archivoEntrada) throws IOException {
        final ByteArrayOutputStream resultado = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[SIZE_BUFFER];
        while ((nRead = archivoEntrada.read(data, 0, data.length)) != -1) {
            resultado.write(data, 0, nRead);
        }
        resultado.flush();
        return resultado.toByteArray();
    }

    @Override
    public FecetCompulsas obtenerCompulsa(BigDecimal idOrdenCompulsa) {
        return fecetCompulsasDao.findByIdCompulsaOrden(idOrdenCompulsa);
    }

}
