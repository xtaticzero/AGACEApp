package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCambioMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.oficio.firma.dao.FirmaOficioDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FirmanteSeguimientoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaOficioService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPericialesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmanteSeguimientoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Service("firmanteSeguimientoService")
public class FirmanteSeguimientoServiceImpl extends OrdenesServiceBase implements FirmanteSeguimientoService {

    private static final long serialVersionUID = 13423434576789789L;

    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;
    @Autowired
    private transient FirmanteSeguimientoDao firmanteSeguimientoDao;
    @Autowired
    private transient FecetCambioMetodoDao fecetCambioMetodoDao;
    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetAnexosRechazoOficioDao fecetAnexosRechazoOficioDao;
    @Autowired
    private transient FecetRechazoOficioDao fecetRechazoOficioDao;
    @Autowired
    private transient FecetAlegatoDao fecetAlegatoDao;
    @Autowired
    private transient FecetDocProrrogaOrdenDao fecetDocProrrogaOrdenDao;
    @Autowired
    private transient FecetAnexosProrrogaOrdenDao fecetAnexosProrrogaOrdenDao;
    @Autowired
    private transient FecetOficioAnexosDao fecetOficioAnexosDao;
    @Autowired
    private transient FecetFlujoProrrogaOrdenDao fecetFlujoProrrogaOrdenDao;
    @Autowired
    private transient FecetCompulsasDao compulsaDAO;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;
    @Autowired
    private transient CargarFirmaOficioService cargarFirmaOficioService;
    @Autowired
    private transient AsociadosService asociadosService;
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;
    @Autowired
    private transient FecetAnexoPruebasPericialesDao fecetAnexoPruebasPericialesDao;
    @Autowired
    private transient FecetFlujoPruebasPericialesDao fecetFlujoPruebasPericialesDao;
    @Autowired
    private transient FecetDocPruebasPericialesDao fecetDocPruebasPericialesDao;
    @Autowired
    private transient FecetPromocionAgenteAduanalDao fecetPromocionAgenteAduanalDao;
    @Autowired
    private transient FecetAlegatoAgenteAduanalDao fecetAlegatoAgenteAduanalDao;
    @Autowired
    private transient FececEstatusDao fececEstatusDao;
    @Autowired
    private transient FirmaOficioDAO firmaOficioDAO;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient CargarFirmaPruebasPericialesService cargarFirmaPruebasPericialesService;

    private static final BigDecimal ID_OFICIO_CAMBIO_METODO = new BigDecimal(19L);

    private boolean firmarOficios;
    private boolean rechazarOficios;
    private boolean firmarProrroga;
    private boolean rechazarProrroga;

    /**
     * @param idOrden
     * @return
     */
    @Override
    public Map<String, List<FecetOficio>> getOficiosFirmante(BigDecimal idOrden) {
        Map<String, List<FecetOficio>> oficios = new HashMap<String, List<FecetOficio>>();
        List<FecetOficio> oficiosPendientesDeFirmar = getOficiosPorFirmar(idOrden, Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);
        logger.info("oficiosPendientesDeFirmar {} ", oficiosPendientesDeFirmar.size());
        List<FecetOficio> oficiosFirmados = getOficiosFirmados(idOrden);
        for (FecetOficio oficiosPadre : oficiosFirmados) {
            logger.debug("OFICIO PRINCIPAL ", oficiosPadre.getIdOficio());
            List<FecetOficio> oficiosDependientesSinFirmar = getOficiosDependientesPorFirmar(oficiosPadre.getIdOficio());
            oficiosPendientesDeFirmar.addAll(oficiosDependientesSinFirmar);
        }
        llenarRfcANombreOficio(oficiosFirmados);
        llenarRfcANombreOficio(oficiosPendientesDeFirmar);
        oficios.put(Constantes.GET_OFICIOS_PENDIENTES, oficiosPendientesDeFirmar);
        oficios.put(Constantes.GET_OFICIOS_FIRMADOS, oficiosFirmados);
        return oficios;
    }

    private void llenarRfcANombreOficio(List<FecetOficio> listaOficio) {
        if (listaOficio != null) {
            for (FecetOficio oficio : listaOficio) {
                TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());

                if (oficioEnum != null) {
                    StringBuilder nombre = new StringBuilder();

                    if (oficio.getIdOficioPrincipal() != null) {
                        FecetOficio oficioPrincipal = fecetOficioDao.getOficioByIdOficio(oficio.getIdOficioPrincipal()).get(0);
                        if (oficioPrincipal != null) {
                            nombre.append(oficio.getFecetTipoOficio().getNombre());
                            nombre.append(llenarNombreOficio(oficioPrincipal));
                        }
                    } else {
                        nombre.append(llenarNombreOficioCompulsa(oficio));
                    }
                    oficio.getFecetTipoOficio().setNombre(nombre.toString());
                }
            }
        }
    }

    private String llenarNombreOficio(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder();

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    private String llenarNombreOficioCompulsa(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder(oficio.getFecetTipoOficio().getNombre());

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().equals("")) {
            nombre.append(" (").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    private List<FecetOficio> getOficiosPorFirmar(BigDecimal idOrden, BigDecimal idEstatusOficio) {
        return fecetOficioDao.getOficioByIdOrdenIdEstatusPorFirmar(idOrden, idEstatusOficio);
    }

    private List<FecetOficio> getOficiosDependientesPorFirmar(BigDecimal idOficioPrincipal) {
        return fecetOficioDao.getOficioByIdOficioIdEstatus(idOficioPrincipal, Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);
    }

    private List<FecetOficio> getOficiosFirmados(BigDecimal idOrden) {
        List<FecetOficio> oficios = fecetOficioDao.getOficioByIdOrdenIdEstatus(idOrden, EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(), EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
        AgaceOrden orden = getAgaceOrdenDao().findByPrimaryKey(idOrden);
        plazosService.asignarFechasNyVOficio(orden, oficios);
        return oficios;
    }

    /**
     * @param idOficio
     * @return detalle del oficio
     */
    public List<FecetOficio> getDetalleOficioPendienteFirma(BigDecimal idOficio) {
        List<FecetOficio> list = new ArrayList<FecetOficio>();
        list.add(fecetOficioDao.getOficioById(idOficio));
        return list;
    }

    @Override
    public Map<String, List<FecetProrrogaOrden>> getProrrogasOrden(BigDecimal idOrden) {
        Map<String, List<FecetProrrogaOrden>> prorrogas = new HashMap<String, List<FecetProrrogaOrden>>();
        prorrogas.put(Constantes.GET_PRORROGAS_PENDIENTES, getProrrogaPorFirmar(idOrden));
        prorrogas.put(Constantes.GET_PRORROGAS_FIRMADAS, getProrrogaFirmada(idOrden));
        return prorrogas;
    }

    @Override
    public Map<String, List<FecetPruebasPericiales>> getPruebasPericiales(BigDecimal idOrden) {
        Map<String, List<FecetPruebasPericiales>> prorrogas = new HashMap<String, List<FecetPruebasPericiales>>();
        prorrogas.put(Constantes.GET_PRUEBAS_PERICIALES_PENDIENTES, getPruebaPericialesPorFirmar(idOrden));
        prorrogas.put(Constantes.GET_PRUEBAS_PERICIALES_FIRMADAS, getPruebasPericialesFirmada(idOrden));
        return prorrogas;
    }

    private List<FecetProrrogaOrden> getProrrogaPorFirmar(BigDecimal idOrden) {
        return fecetProrrogaOrdenDao.getProrrogaPorOrdenPorFirmar(idOrden);

    }

    private List<FecetProrrogaOrden> getProrrogaFirmada(BigDecimal idOrden) {
        return fecetProrrogaOrdenDao.getProrrogaPorOrdenFirmada(idOrden);
    }

    private List<FecetPruebasPericiales> getPruebaPericialesPorFirmar(BigDecimal idOrden) {
        return fecetPruebasPericialesDao.getPruebasPericialesPorOrdenPorFirmar(idOrden);
    }

    private List<FecetPruebasPericiales> getPruebasPericialesFirmada(BigDecimal idOrden) {
        return fecetPruebasPericialesDao.getPruebasPericialesPorOrdenFirmada(idOrden);
    }

    @Override
    @PistaAuditoria
    public BigDecimal rechazaOficio(FecetOficio oficio, AgaceOrden orden, List<DocumentoVO> listaDocumentosAnexos,
            BigDecimal estatusOficioRechazado, String descripcionRechazo) {
        FecetRechazoOficio rechazoOficio = new FecetRechazoOficio();
        rechazoOficio.setIdOficio(oficio.getIdOficio());
        rechazoOficio.setDescripcion(descripcionRechazo);
        rechazoOficio.setFechaRechazo(new Date());
        rechazoOficio.setIdEmpleado(orden.getIdFirmante());
        rechazoOficio.setEstatus("1");
        final BigDecimal idRechazoOficio = fecetRechazoOficioDao.insert(rechazoOficio).getIdRechazoOficio();
        for (DocumentoVO doc : listaDocumentosAnexos) {
            String rootPath = oficio.getRutaArchivo();
            rootPath = rootPath.substring(0, rootPath.lastIndexOf('/'));
            FecetAnexosRechazoOficio anexos = new FecetAnexosRechazoOficio();
            anexos.setFechaCarga(new Date());
            anexos.setIdRechazoOficio(idRechazoOficio);
            anexos.setRutaArchivo(rootPath.concat("/oficioAnexoRechazado/").concat(doc.getNombreArchivo()));
            anexos.setIdEmpleado(orden.getIdFirmante());
            anexos.setArchivo(doc.getInputStream());
            try {
                ArchivoOrdenUtil.guardarArchivoAnexoOficioRechazado(rootPath, doc);
            } catch (IOException e) {
                logger.error("No se pudo crear el archivo Anexo Oficio Rechazado [{}] ", e);
            }

            fecetAnexosRechazoOficioDao.insert(anexos);

        }

        if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.ID_OFICIO_COMPULSA_TERCEROS)) {

            compulsaDAO.actualizaEstatusCompulsa(oficio.getIdOficio(),
                    Constantes.ESTATUS_COMPULSA_RECHAZADA_FIRMANTE, null);

        } else if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(ID_OFICIO_CAMBIO_METODO)) {

            FecetCambioMetodo dto = new FecetCambioMetodo();
            dto.setIdOrdenOrigen(orden.getIdOrden());
            dto.setIdEstatus(Constantes.ESTATUS_CAMBIO_METODO_APROBADO_FIRMANTE);
            fecetCambioMetodoDao.update(dto);

        }
        oficio.setIdEstatus(estatusOficioRechazado);

        final List<FecetOficio> listaOficiosDependientes
                = fecetOficioDao.getOficiosDependientesPorIdEstatus(oficio.getIdOficio(),
                        Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);

        for (FecetOficio oficioDependiente : listaOficiosDependientes) {
            oficioDependiente.setIdEstatus(estatusOficioRechazado);
            fecetOficioDao.updateFirmante(oficioDependiente);

        }

        fecetOficioDao.updateFirmante(oficio);

        cargarFirmaOficioService.enviarNotificacionOficioRechazo(orden, oficio, descripcionRechazo);
        return oficio.getIdOrden();
    }

    @Override
    public List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion) {
        return fecetAlegatoDao.findWhereIdPromocionEquals(idPromocion);
    }

    @Override
    public List<FecetAnexosProrrogaOrden> getAnexosRechazoProrrogaOrden(final FecetProrrogaOrden fecetProrrogaOrden) {
        List<FecetAnexosProrrogaOrden> anexos
                = fecetAnexosProrrogaOrdenDao.obtenerAnexosResolucionProrrogaAuditor(fecetProrrogaOrden.getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden());
        if (anexos != null && !anexos.isEmpty()) {
            try {
                EmpleadoDTO empleadoDTO = empleadoService.getEmpleadoCompleto(anexos.get(0).getFececEmpleado().getIdEmpleado().intValue());
                if (empleadoDTO != null) {
                    for (FecetAnexosProrrogaOrden anexo : anexos) {
                        anexo.getFececEmpleado().setNombre(empleadoDTO.getNombreCompleto());
                        anexo.getFececEmpleado().setCorreo(empleadoDTO.getCorreo());
                        anexo.getFececEmpleado().setRfc(empleadoDTO.getRfc());
                    }
                } else {
                    anexos = null;
                    logger.error("No existe empleado");
                }
            } catch (EmpleadoServiceException e) {
                // TODO Auto-generated catch block
                anexos = null;
                logger.error(e.getMessage());
            }

        }
        return anexos;
    }

    @Override
    public List<FecetAnexoPruebasPericiales> getAnexosRechazoPruebasPericiales(final FecetPruebasPericiales fecetPruebaPericial) {
        List<FecetAnexoPruebasPericiales> anexos
                = fecetAnexoPruebasPericialesDao.obtenerAnexosResolucionPruebaPericialAuditor(fecetPruebaPericial.getFlujoPruebasPericiales().getIdFlujoPruebasPericiales());
        if (anexos != null && !anexos.isEmpty()) {
            try {
                EmpleadoDTO empleadoDTO = empleadoService.getEmpleadoCompleto(anexos.get(0).getFececEmpleado().getIdEmpleado().intValue());
                if (empleadoDTO != null) {
                    for (FecetAnexoPruebasPericiales anexo : anexos) {
                        anexo.getFececEmpleado().setNombre(empleadoDTO.getNombreCompleto());
                        anexo.getFececEmpleado().setCorreo(empleadoDTO.getCorreo());
                        anexo.getFececEmpleado().setRfc(empleadoDTO.getRfc());
                    }
                } else {
                    anexos = null;
                    logger.error("No existe empleado");
                }
            } catch (EmpleadoServiceException e) {
                // TODO Auto-generated catch block
                anexos = null;
                logger.error(e.getMessage());
            }

        }

        return anexos;
    }

    @Override
    public List<FecetDocProrrogaOrden> getDocumentacionProrrogaContribuyente(final BigDecimal idProrrogaOrden) {
        return fecetDocProrrogaOrdenDao.findWhereIdProrrogaOrdenEquals(idProrrogaOrden);
    }

    @Override
    public List<FecetDocPruebasPericiales> getDocumentacionPruebaPericialContribuyente(final BigDecimal idPruebasPericiales) {
        return fecetDocPruebasPericialesDao.findWhereIdPruebasPericialesEquals(idPruebasPericiales);
    }

    @Override
    public List<FecetOficioAnexos> getAnexosOficioRechazo(final BigDecimal idOficio) {
        return fecetOficioAnexosDao.getAnexosRechazo(idOficio);
    }

    @Override
    public List<FecetPromocion> getPromocionContadorPruebasAlegatos(AgaceOrden orden) {
        List<FecetPromocion> listaPromociones = fecetPromocionDao.getPromocionContadorPruebasAlegatos(orden.getIdOrden());

        for (FecetPromocion promocion : listaPromociones) {
            promocion.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionOrdenPorMetodo(orden.getIdMetodo()));
            promocion.setExtemporanea(plazosService.esDocumentoExtemporaneoOrden(orden, promocion.getFechaCarga()) ? "1" : "0");
        }
        return listaPromociones;
    }

    @Override
    public List<FecetOficio> getOficiosDependientes(BigDecimal idOficio, BigDecimal estatusOficio) {
        final List<FecetOficio> listaOficiosDependientes
                = fecetOficioDao.getOficiosDependientesPorIdEstatus(idOficio, estatusOficio);
        return listaOficiosDependientes;
    }

    @Override
    public List<FecetOficio> getOficiosDependientesFirmados(BigDecimal idOficio) {
        final List<FecetOficio> listaOficiosDependientes
                = fecetOficioDao.getOficiosDependientesFirmados(idOficio);
        return listaOficiosDependientes;
    }

    @Override
    @PistaAuditoria
    public BigDecimal rechazaFirmaProrrogaFirmante(FecetProrrogaOrden prorroga, String motivoRechazoFirmante,
            BigDecimal status, List<DocumentoVO> listaDocProrroga) {

        final String idFlujoProrrogaOrden
                = fecetFlujoProrrogaOrdenDao.findLastIdFecetFlujoProrrogaOrden(prorroga.getIdProrrogaOrden());
        FecetFlujoProrrogaOrden flujoProrroga
                = fecetFlujoProrrogaOrdenDao.findByPrimaryKey(new BigDecimal(idFlujoProrrogaOrden));
        flujoProrroga.setIdEstatus(status);
        flujoProrroga.setFechaRechazoFirmante(new Date());
        flujoProrroga.setJustificacionFirmante(motivoRechazoFirmante);
        fecetFlujoProrrogaOrdenDao.update(flujoProrroga);

        String rutaDocProroga
                = RutaArchivosUtil.armarRutaAnexosProrrogaRechazadaFirmante(Constantes.DIRECTORIO_ARCHIVOS_ORDENES,
                        new BigDecimal(idFlujoProrrogaOrden));
        for (DocumentoVO docCargado : listaDocProrroga) {
            FecetAnexosProrrogaOrden anexos = new FecetAnexosProrrogaOrden();
            anexos.setIdFlujoProrrogaOrden(new BigDecimal(idFlujoProrrogaOrden));
            anexos.setNombreArchivo(docCargado.getNombreArchivo());
            anexos.setRutaArchivo(rutaDocProroga);
            anexos.setArchivo(docCargado.getInputStream());
            anexos.setFechaCreacion(new Date());
            anexos.setTipoAnexo("1");
            try {
                ArchivoOrdenUtil.guardarArchivoAnexoProrrogaOrden(anexos);
            } catch (IOException e) {
                logger.error("No se pudo crear el archivo Anexo Prorroga Orden Rechazado [{}] ", e);
            }
            anexos.setRutaArchivo(rutaDocProroga.concat(anexos.getNombreArchivo()));
            fecetAnexosProrrogaOrdenDao.insert(anexos);
            cargarFirmaProrrogaService.enviarCorreoProrroga(prorroga, Constantes.FIRMANTE, "RECHAZADA", null, null);
        }
        return prorroga.getOrden().getId();
    }

    @Override
    public void rechazaFirmaPruebaPericialFirmante(FecetPruebasPericiales pruebaPericial, String motivoRechazoFirmante,
            BigDecimal status, List<DocumentoVO> listaDocPruebasPericiales) {

        final String idFlujoPruebasPericiales
                = fecetFlujoPruebasPericialesDao.findLastIdFecetFlujoPruebasPericiales(pruebaPericial.getIdPruebasPericiales());
        FecetFlujoPruebasPericiales flujoPruebasPericiales
                = fecetFlujoPruebasPericialesDao.findByPrimaryKey(new BigDecimal(idFlujoPruebasPericiales));
        flujoPruebasPericiales.setIdEstatus(status);
        flujoPruebasPericiales.setFechaRechazoFirmante(new Date());
        flujoPruebasPericiales.setJustificacionFirmante(motivoRechazoFirmante);
        fecetFlujoPruebasPericialesDao.update(flujoPruebasPericiales);

        String rutaDocPruebasPericiales
                = RutaArchivosUtil.armarRutaAnexosPruebaPericialRechazadaFirmante(Constantes.DIRECTORIO_ARCHIVOS_ORDENES,
                        new BigDecimal(idFlujoPruebasPericiales));
        for (DocumentoVO docCargado : listaDocPruebasPericiales) {
            FecetAnexoPruebasPericiales anexos = new FecetAnexoPruebasPericiales();
            anexos.setIdFlujoPruebasPericiales(new BigDecimal(idFlujoPruebasPericiales));
            anexos.setNombreArchivo(docCargado.getNombreArchivo());
            anexos.setRutaArchivo(rutaDocPruebasPericiales);
            anexos.setArchivo(docCargado.getInputStream());
            anexos.setFechaCreacion(new Date());
            anexos.setTipoAnexo("1");
            try {
                ArchivoOrdenUtil.guardarArchivoAnexoPruebasPericiales(anexos);
            } catch (IOException e) {
                logger.error("No se pudo crear el archivo Anexo Prueba Pericial Rechazado [{}] ", e);
            }
            anexos.setRutaArchivo(rutaDocPruebasPericiales.concat(anexos.getNombreArchivo()));
            fecetAnexoPruebasPericialesDao.insert(anexos);
            cargarFirmaPruebasPericialesService.enviarCorreoPruebasPericiales(pruebaPericial, Constantes.FIRMANTE, "RECHAZADA");
        }

    }

    @Override
    public List<FecetPromocion> getPromocionContadorPruebasAlegatos(final BigDecimal idOrden) {
        return fecetPromocionDao.getPromocionContadorPruebasAlegatos(idOrden);
    }

    @Override
    public List<AgaceOrden> getOrdenesFirmarDocumentos(final BigDecimal idEmpleado) throws NoExisteEmpleadoException {
        List<AgaceOrden> listaOrdenes = firmanteSeguimientoDao.getFirmarOrdenesSeguimiento(idEmpleado);
        listaOrdenes = plazosService.filtraOrdenPorFecha(listaOrdenes);
        if (listaOrdenes.isEmpty()) {
            throw new NoExisteEmpleadoException("No existen Solicitudes para este Empleado");
        }
        for (AgaceOrden orden : listaOrdenes) {
            orden.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(orden.getSemaforo()));
            orden.setDescripcionSemaforo(BusinessUtil.obtenerDescripcionSemaforo(orden.getSemaforo()));
            orden.setMostrarNumeroOrden(plazosService.tieneAcuerdoConclusivo(orden));
        }
        return listaOrdenes;
    }

    @Override
    public FecetCompulsas obtenerDatosEncabezado(BigDecimal idOficio) {
        return compulsaDAO.getDatosGenerales(idOficio);
    }

    @Override
    public FecetCambioMetodo obtenerDatosEncabezadoCambioMetodo(BigDecimal idOrdenOrigen) {
        return fecetCambioMetodoDao.getDatosGenerales(idOrdenOrigen);
    }

    public void setFirmarOficios(boolean firmarOficios) {
        this.firmarOficios = firmarOficios;
    }

    public boolean isFirmarOficios() {
        return firmarOficios;
    }

    public void setRechazarOficios(boolean rechazarOficios) {
        this.rechazarOficios = rechazarOficios;
    }

    public boolean isRechazarOficios() {
        return rechazarOficios;
    }

    public void setFirmarProrroga(boolean firmarProrroga) {
        this.firmarProrroga = firmarProrroga;
    }

    public boolean isFirmarProrroga() {
        return firmarProrroga;
    }

    public void setRechazarProrroga(boolean rechazarProrroga) {
        this.rechazarProrroga = rechazarProrroga;
    }

    public boolean isRechazarProrroga() {
        return rechazarProrroga;
    }

    @Override
    public ColaboradorVO obtenerColaborador(AgaceOrden orden, BigDecimal idColaborador) {
        ColaboradorVO colaborador = new ColaboradorVO();
        colaborador.setTipoAsociado(idColaborador);
        if (idColaborador.equals(Constantes.ID_APODERADO_LEGAL)) {
            return asociadosService.obtenerApoderadoLegalContribuyente(orden.getFecetContribuyente().getRfc(), colaborador);
        } else {
            return asociadosService.cargaColaborador(colaborador, orden);
        }

    }

    public BigDecimal obtenerIdAraceFromPropuesta(BigDecimal idOrden) {
        return getAgaceOrdenDao().obtenerIdAraceFromPropuesta(idOrden);
    }

    public List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(BigDecimal idOrden) {
        return fecetPromocionAgenteAduanalDao.getPromocionAgenteAduanalContadorPruebasAlegatos(idOrden);
    }

    public List<FecetAlegatoAgenteAduanal> getAlegatosAgenteAduanal(final BigDecimal idPromocion) {
        return fecetAlegatoAgenteAduanalDao.findWhereIdPromocionEquals(idPromocion);
    }

    public String getDescripcion(BigDecimal idEstatus) {
        return fececEstatusDao.findByPrimaryKey(idEstatus).getDescripcion();
    }

    public List<FececEstatus> getListaEstatus() {
        return fececEstatusDao.findAll();
    }

    public List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio) {
        return firmaOficioDAO.getOficiosDependientesPorIdEstatus(idOficio, BigDecimal.valueOf(EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getIdEstatus()));
    }
}
