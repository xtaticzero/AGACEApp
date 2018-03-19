package mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.plantillador.service.PlantilladorService;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentoelectronico.firma.FirmaDocumentoElectronicoDAO;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.constants.FirmadoOrdenesConstants;
import mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.helper.FirmarDocumentoElectronicoHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.service.FirmaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("firmaDocumentoElectronicoService")
public class FirmaDocumentoElectronicoServiceImpl extends FirmaServiceAbstract implements FirmaDocumentoElectronicoService {

    private static final long serialVersionUID = 1L;

    private static final String CADENA_PARAMETROS = "%s-%s";
    private static final String ERR_FILE = "No se encontro el Archivo ";

    @Autowired
    private transient FirmaDocumentoElectronicoDAO firmaDocumentoElectronicoDAO;

    @Autowired
    private FirmarDocumentoElectronicoHelper firmarDocumentoElectronicoHelper;

    @Autowired
    private transient PlantilladorService plantilladorService;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;

    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;

    @Autowired
    private transient NotificacionService notificacionService;

    @Override
    public FirmaDocumentoElectronicoDAO getFirmaDAO() {
        return firmaDocumentoElectronicoDAO;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        List<OrdenPorFirmar> listaOrdenes = (List<OrdenPorFirmar>) obj;

        try {
            firmarDocumentos(listaOrdenes, firmas, rfcFirmante);
            actualizaEstatus(listaOrdenes, rfcFirmante, firmas.get(listaOrdenes.get(0).getIdOrden()));
        } catch (DocumentoException de) {
            logger.error(de.getMessage());
            throw new IllegalArgumentException(de.getMessage(), de);
        } catch (EmpleadoServiceException em) {
            logger.error(em.getMessage());
            throw new IllegalArgumentException(em.getMessage(), em);
        } catch (Exception ne) {
            logger.error(ne.getMessage());
            throw new IllegalArgumentException(ne.getMessage(), ne);
        }
    }

    public void firmarDocumentos(final List<OrdenPorFirmar> lista, Map<String, FirmaDTO> mapJSonFirma, String rfcFirmante) throws DocumentoException, EmpleadoServiceException, NegocioException {
        for (OrdenPorFirmar ordenPorFirmar : lista) {
            List<EmpleadoSuplenteDTO> empleados = new ArrayList<EmpleadoSuplenteDTO>();
            boolean suplencia = false;
            Integer idOrden = Integer.parseInt(ordenPorFirmar.getIdOrden());
            FirmaDTO firma = mapJSonFirma.get(String.valueOf(idOrden.intValue()));
            if (firma.getRfcSuplir() != null && !firma.getRfcSuplir().trim().isEmpty()) {

                try {
                    EmpleadoDTO firmante = empleadoService.getEmpleadoCompleto(firma.getRfcFirmado());
                    EmpleadoDTO suplir = empleadoService.getEmpleadoCompleto(firma.getRfcSuplir());
                    empleados = firmaDocumentoElectronicoDAO.obtenerDatosSuplente(firmante.getIdEmpleado(), suplir.getIdEmpleado());
                    if (empleados != null && !empleados.isEmpty()) {
                        for (EmpleadoSuplenteDTO empleadoDao : empleados) {
                            empleadoDao.setNombreFirmante(suplir.getNombreCompleto());
                            empleadoDao.setNombreSuplente(firmante.getNombreCompleto());
                        }
                    }

                    suplencia = true;
                } catch (EmpleadoServiceException e) {
                    logger.error("Error al traer empleados", e);
                    throw new EmpleadoServiceException(e.getMessage(), e);
                }
            }
            logger.info("-- Se va a firmar orden: [{}] con suplencia: [{}]", ordenPorFirmar.getNumeroOrden(), suplencia);
            String rutaPdf = generarPDFConFirmaOrden(ordenPorFirmar, firma, suplencia, empleados);
            FecetDocOrden fecetDocOrden = firmarDocumentoElectronicoHelper.inicializarDocumentoOrden(ordenPorFirmar, rutaPdf);
            fecetDocOrdenDao.insert(fecetDocOrden);

            if (ordenPorFirmar.getOficios() != null && !ordenPorFirmar.getOficios().isEmpty()) {
                logger.info("-- Se van a firmar [{}] oficios de la orden: [{}]", ordenPorFirmar.getOficios().size(), ordenPorFirmar.getNumeroOrden());
                for (FecetOficio oficio : ordenPorFirmar.getOficios()) {
                    FirmaDTO firmaOficio = mapJSonFirma.get(idOrden + "|" + oficio.getIdOficio());
                    String selloDigital = getBuzonSelladoraService().getSelloDigital(firmaOficio.getCadena());
                    String nombrePdf = oficio.getFecetTipoOficio().getNombre().concat(Constantes.ARCHIVO_PDF);
                    String nombreOficioPdf
                            = firmarDocumentoElectronicoHelper.armarRutaDestinoPdfOficio(firmarDocumentoElectronicoHelper.construirPropuesta(ordenPorFirmar), oficio).concat(nombrePdf);
                    String rutaPdfGenerado = generarPDFConFirmaOficio(
                            oficio.getRutaArchivo(), nombreOficioPdf, firmaOficio, selloDigital, String.valueOf(oficio.getNumeroOficio()),
                            suplencia, empleados);
                    oficio.setIdEstatus(EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus());
                    oficio.setCadenaOriginal(firmaOficio.getCadena());
                    oficio.setFirmaElectronica(firmaOficio.getFirma());
                    oficio.setFechaFirma(new Date());
                    oficio.setRutaArchivo(rutaPdfGenerado);
                    firmaDocumentoElectronicoDAO.updateFirmante(oficio);

                    AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(oficio.getIdOrden());
                    logger.debug("oficio.getFecetTipoOficio().getIdTipoOficio() {} ",
                            oficio.getFecetTipoOficio().getIdTipoOficio());

                    registrarActoAdministrativo(oficio, orden, rfcFirmante, firmaOficio);
                    logger.debug("Guarda Oficio firmado");
                }
            }

        }
    }

    private String generarPDFConFirmaOrden(OrdenPorFirmar ordenPorFirmar, FirmaDTO firma, boolean suplencia, List<EmpleadoSuplenteDTO> empleados) {
        String jsonCadena = firma.getCadena();
        String jsonFirma = firma.getFirma();
        String rutaDOCX = ordenPorFirmar.getRutaArchivo();
        String rutaPDF = firmarDocumentoElectronicoHelper.armarRutaDestinoPdfDoctoOrden(firmarDocumentoElectronicoHelper.construirPropuesta(ordenPorFirmar));
        String numeroOrden = ordenPorFirmar.getNumeroOrden();
        String selloDigital = getBuzonSelladoraService().getSelloDigital(jsonCadena);
        if (rutaDOCX != null) {
            try {
                rutaPDF = rutaPDF.concat(numeroOrden).concat(FirmadoOrdenesConstants.EXTENSION_ARCHIVO_ORDEN_PDF);
                InputStream entrada = new FileInputStream(new File(rutaDOCX));
                OutputStream salida = new FileOutputStream(new File(rutaPDF));
                StringBuilder cadenaPDF = new StringBuilder();
                cadenaPDF.append(firmarDocumentoElectronicoHelper.crearCadenaImprimible(jsonCadena, jsonFirma, selloDigital));
                if (suplencia && empleados != null && !empleados.isEmpty()) {
                    firmarDocumentoElectronicoHelper.crearCadenaSuplente(cadenaPDF, empleados.get(0));
                }
                numeroOrden = numeroOrden.concat("/n/").concat(ordenPorFirmar.getNumeroOficio());
                plantilladorService.generaPDF(entrada, salida, numeroOrden, cadenaPDF.toString());
                logger.info("-- Se firmo orden: [{}]", ordenPorFirmar.getNumeroOrden());
                return rutaPDF;
            } catch (Exception e) {
                logger.error(ERR_FILE + e.getMessage(), e);
                throw new IllegalArgumentException(ERR_FILE + UtileriasMapperDao.getNameFileFromPath(ordenPorFirmar.getRutaArchivo()), e);
            }
        }
        return null;
    }

    private String generarPDFConFirmaOficio(String rutaWord, String rutaPdf, FirmaDTO firma, String selloDigital, String numeroOficio,
            boolean suplencia, List<EmpleadoSuplenteDTO> empleados) {
        if (rutaWord != null) {
            try {
                String rutaPDF = new StringBuffer(rutaPdf).toString();
                String rutaDOCX = new StringBuffer(rutaWord).toString();
                InputStream entrada = new FileInputStream(new File(rutaDOCX));
                OutputStream salida = new FileOutputStream(new File(rutaPDF));
                StringBuilder cadenaPDF = new StringBuilder();
                cadenaPDF.append(firmarDocumentoElectronicoHelper.crearCadenaImprimible(firma.getCadena(), firma.getFirma(), selloDigital));
                if (suplencia && empleados != null && !empleados.isEmpty()) {
                    firmarDocumentoElectronicoHelper.crearCadenaSuplente(cadenaPDF, empleados.get(0));
                }
                plantilladorService.generaPDF(entrada, salida, numeroOficio, cadenaPDF.toString());
                logger.info("-- Se firmo oficio: [{}] ", numeroOficio);
                return rutaPDF;
            } catch (Exception e) {
                logger.error(ERR_FILE + e.getCause(), e);
                throw new IllegalArgumentException(ERR_FILE + UtileriasMapperDao.getNameFileFromPath(rutaWord), e);
            }
        }
        return null;
    }

    public void actualizaEstatus(List<OrdenPorFirmar> listaOrdenes, String rfcFirmante, FirmaDTO firma) throws NegocioException {
        BigDecimal idFirmante = BigDecimal.ZERO;

        try {
            String rfc = rfcFirmante;
            if (firma.getRfcSuplir() != null && !firma.getRfcSuplir().isEmpty()) {
                rfc = firma.getRfcSuplir();
            }
            EmpleadoDTO empleadoDto = empleadoService.getEmpleadoCompleto(rfc);

            idFirmante = empleadoDto != null
                    ? empleadoDto.getIdEmpleado() : BigDecimal.ZERO;
        } catch (EmpleadoServiceException e1) {
            logger.error("Error al consultar el idEmpleado del rfc {}: {}", rfcFirmante, e1.getMessage());
            throw new NegocioException(e1.getMessage(), e1);
        }
        try {
            actualizaOrden(listaOrdenes, idFirmante, firma);
        } catch (NegocioException e) {
            logger.error("No se realizo el cambio de estatus de la orden: {}", e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    /**
     * Metodo que cambia el estatus de la orden firmada
     *
     * @param firma
     *
     */
    public void actualizaOrden(final List<OrdenPorFirmar> lista, BigDecimal idFirmante, FirmaDTO firma) throws NegocioException {
        for (OrdenPorFirmar ordenPorFirmar : lista) {
            fecetPropuestaDao.actualizaEstadoPropuesta(TipoEstatusEnum.PROPUESTA_ENVIADA_PARA_NOTIFICACION_AL_CONTRIBUYENTE,
                    new BigDecimal(ordenPorFirmar.getIdPropuesta()));
            AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(new BigDecimal(ordenPorFirmar.getIdOrden()));

            orden.setIdEstatus(new BigDecimal(TipoEstatusEnum.FIRMANTE_REGISTRO_FIRMADO_ENVIADO_NOTIFICACION_CONTRIBUYENTE.getId()));
            orden.setIdFirmante(idFirmante);
            orden.setIdOrden(new BigDecimal(ordenPorFirmar.getIdOrden()));

            try {
                registrarActoAdministrativo(orden, firma);
                agaceOrdenDao.updateDatosDeNyv(orden);
                //se actualiza la accion y se inserta el historico
                FecebAccionPropuesta accionPropuesta = firmarDocumentoElectronicoHelper.creaAccionPropuestaFirmar(ordenPorFirmar, idFirmante);
                fecebAccionPropuestaDao.insert(accionPropuesta);
                feceaPropuestaAccionDao.updateAccionIdPropuesta(new BigDecimal(ordenPorFirmar.getIdPropuesta()), AccionesFuncionarioEnum.FIRMA_ORDEN.getIdAccion(), null);
                logger.info("-- Se actualizo orden despues de firma: [{}]", ordenPorFirmar.getNumeroOrden());

                List<FecetPropuesta> listaPropuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(orden.getIdPropuesta());
                if (listaPropuesta != null && !listaPropuesta.isEmpty()) {
                    getAuditoriaService().firmarPropuesta(listaPropuesta.get(0));
                }
                enviarCorreo(ordenPorFirmar, LeyendasPropuestasEnum.ORDEN_FIRMADA.getIdLeyenda());
            } catch (NegocioException e) {
                logger.error("Error al registrar el acto administrativo de firma de la orden: {}", e.getMessage());
                throw new NegocioException(e.getMessage(), e);
            } catch (Exception ex) {
                logger.error("Error al enviar el correo de firma de la orden: {}", ex.getMessage());
                throw new NegocioException(ex.getMessage(), ex);
            }

        }
    }

    private void registrarActoAdministrativo(AgaceOrden orden, FirmaDTO firma) throws NegocioException {
        firmarDocumentoElectronicoHelper.checkArgument(orden == null);

        FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(orden.getIdContribuyente());
        firmarDocumentoElectronicoHelper.checkArgument(contribuyente == null);

        EmpleadoDTO empleadoRespuesta = null;
        try {
            empleadoRespuesta = empleadoService.getEmpleadoCompleto(orden.getIdFirmante().intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
        firmarDocumentoElectronicoHelper.checkArgument(empleadoRespuesta == null);
        if (empleadoRespuesta != null) {
            Long centroCosto = Long.valueOf("110");
            if (isActivoNyv() && firma.getCentroCosto() != null) {
                centroCosto = firma.getCentroCosto();
                empleadoRespuesta.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
            }
            FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(orden.getIdMetodo().longValue(), null, centroCosto, isActivoNyv(),
                    Boolean.valueOf(getVersion()));
            firmarDocumentoElectronicoHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

            getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
            firmarDocumentoElectronicoHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

            orden.setFecetContribuyente(contribuyente);

            Calendar fechaVigenciaFIEL = Calendar.getInstance();
            fechaVigenciaFIEL.setTime(new Date());
            fechaVigenciaFIEL.add(Calendar.DAY_OF_YEAR, 2);

            firmarDocumentoElectronicoHelper.generarActoAdministrativo(orden, empleadoRespuesta, fechaVigenciaFIEL.getTime(), actoAdmin);

            orden.getDatosNotificable().setNumeroReferencia(String.format(CADENA_PARAMETROS, orden.getNumeroOrden(), contribuyente.getRfc()));
            orden.getDatosNotificable().setInsertaNyV(true);
            orden.getDatosNotificable().setClaveUnidadAdmin(centroCosto.toString());
            orden.getDatosNotificable().setFececActosAdm(actoAdmin);

            getPlazosService().registrarActoAdministrativo(orden, actoAdmin);

            List<FecetAsociado> asociados = getFecetAsociadoDao().getAsociadosByContribuyente(contribuyente.getRfc(), orden.getId());
            List<String> rfcsEnviados = new ArrayList<String>();
            if (asociados != null && !asociados.isEmpty()) {
                for (FecetAsociado fecetAsociado : asociados) {
                    if (rfcsEnviados.contains(fecetAsociado.getRfc())) {
                        continue;
                    } else {
                        rfcsEnviados.add(fecetAsociado.getRfc());
                    }
                    orden.getDatosNotificable().setRfcContribuyente(fecetAsociado.getRfc());
                    orden.getDatosNotificable().setNumeroReferencia(String.format(CADENA_PARAMETROS, orden.getNumeroOrden(), fecetAsociado.getRfc()));
                    orden.getDatosNotificable().setInsertaNyV(false);

                    getPlazosService().registrarActoAdministrativo(orden, actoAdmin);
                }
            }
        }
    }

    private void registrarActoAdministrativo(FecetOficio oficio, AgaceOrden orden, String rfcFirmante, FirmaDTO firma) throws NegocioException, EmpleadoServiceException {
        if (orden != null) {
            FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(orden.getIdContribuyente());
            firmarDocumentoElectronicoHelper.checkArgument(contribuyente == null);

            EmpleadoDTO empleadoRespuesta = null;
            try {
                empleadoRespuesta = getEmpleadoService().getEmpleadoCompleto(rfcFirmante);
            } catch (EmpleadoServiceException e) {
                logger.error("Error al obtener datos del servicio de empleado agace", e);
                throw new EmpleadoServiceException(e.getMessage(), e);
            }
            firmarDocumentoElectronicoHelper.checkArgument(empleadoRespuesta == null);
            if (empleadoRespuesta != null && contribuyente != null) {
                Long centroCosto = Long.valueOf("110");
                if (isActivoNyv() && firma.getCentroCosto() != null) {
                    centroCosto = firma.getCentroCosto();
                    empleadoRespuesta.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
                }

                FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(orden.getIdMetodo().longValue(), oficio.getFecetTipoOficio().getIdTipoOficio().longValue(), centroCosto, isActivoNyv(),
                        Boolean.valueOf(getVersion()));
                firmarDocumentoElectronicoHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

                getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
                firmarDocumentoElectronicoHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

                orden.setFecetContribuyente(contribuyente);
                Calendar fechaFIEL = Calendar.getInstance();
                fechaFIEL.setTime(new Date());
                fechaFIEL.add(Calendar.DAY_OF_YEAR, 2);

                String referenciaOriginal = String.format("%s-%s-%s", orden.getIdRegistroPropuesta(), oficio.getFecetTipoOficio().getIdTipoOficio(), oficio.getIdOficio());

                firmarDocumentoElectronicoHelper.generarActoAdministrativo(oficio, empleadoRespuesta, fechaFIEL.getTime(), contribuyente.getRfc(), referenciaOriginal, actoAdmin);

                oficio.getDatosNotificable().setNumeroReferencia(String.format(CADENA_PARAMETROS, referenciaOriginal, contribuyente.getRfc()));
                oficio.getDatosNotificable().setInsertaNyV(true);
                oficio.getDatosNotificable().setClaveUnidadAdmin(centroCosto.toString());
                oficio.getDatosNotificable().setFececActosAdm(actoAdmin);

                getPlazosService().registrarActoAdministrativo(oficio, actoAdmin);

                List<FecetAsociado> asociados = getFecetAsociadoDao().getAsociadosByContribuyente(contribuyente.getRfc(), orden.getId());
                List<String> rfcsEnviados = new ArrayList<String>();
                if (asociados != null && !asociados.isEmpty()) {
                    for (FecetAsociado fecetAsociado : asociados) {
                        if (rfcsEnviados.contains(fecetAsociado.getRfc())) {
                            continue;
                        } else {
                            rfcsEnviados.add(fecetAsociado.getRfc());
                        }
                        oficio.getDatosNotificable().setRfcContribuyente(fecetAsociado.getRfc());
                        oficio.getDatosNotificable().setNumeroReferencia(String.format(CADENA_PARAMETROS, referenciaOriginal, fecetAsociado.getRfc()));
                        oficio.getDatosNotificable().setInsertaNyV(false);

                        getPlazosService().registrarActoAdministrativo(oficio, actoAdmin);
                    }
                }
            }
        }
    }

    private void enviarCorreo(OrdenPorFirmar ordenFirmada, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        FecetPropuesta propuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(new BigDecimal(ordenFirmada.getIdPropuesta())).get(0);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios, ClvSubModulosAgace.INSUMOS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios, ClvSubModulosAgace.INSUMOS);
        notificacionService.obtenerCorreoEmpleado(TipoEmpleadoEnum.CONSULTOR_INSUMOS.getBigId(), propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.INSUMOS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());
        data.put(Common.NUMERO_ORDEN.toString(), ordenFirmada.getNumeroOrden());
        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

}
