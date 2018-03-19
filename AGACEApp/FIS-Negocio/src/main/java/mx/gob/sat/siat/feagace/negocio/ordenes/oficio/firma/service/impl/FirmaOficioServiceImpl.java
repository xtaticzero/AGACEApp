package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.plantillador.service.PlantilladorService;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececUnidadAdministrativaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleNyVDAO;
import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCambioMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.oficio.firma.dao.FirmaOficioDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.plazos.PlazosDAO;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OficioPorFirmarVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.helper.FirmaOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.service.FirmaOficioService;
import mx.gob.sat.siat.feagace.negocio.rules.FirmaOficioRule;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("firmaOficioService")
public class FirmaOficioServiceImpl extends FirmaServiceAbstract implements FirmaOficioService {

    private static final long serialVersionUID = 5151124595662472565L;

    private static final BigDecimal ID_LEYENDA_OFICIO = new BigDecimal(54);

    @Autowired
    private transient FirmaOficioDAO firmaOficioDAO;

    @Autowired
    private transient PlantilladorService plantilladorService;

    @Autowired
    private FirmaOficioHelper firmaOficioHelper;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient FecetCompulsasDao compulsaDAO;

    @Autowired
    private transient FecetCambioMetodoDao cambioMetodoDao;

    @Autowired
    private transient FecetOficioDao oficioDao;

    @Autowired
    private transient FecetDetalleNyVDAO detalleDao;

    @Autowired
    private transient FececUnidadAdministrativaDao fececUnidadAdministrativa;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FirmaOficioRule firmaOficioRule;

    @Autowired
    private transient ValidarContribuyenteService validarContribuyenteService;

    @Autowired
    private transient FecetDetalleNyVDAO fecetDetalleNyVDAO;
    @Autowired
    private transient PlazosDAO plazosDAO;

    private static final int SEIS = 6;
    private static final int CUATRO = 4;
    private static final int QUINCE = 15;
    private static final int CIENTO_DOS = 102;
    private static final String STR4CEROSIZQ = "%05d";

    @Override
    public FirmaDAO getFirmaDAO() {
        return firmaOficioDAO;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        OficioPorFirmarVO oficioPorFirmarVO = (OficioPorFirmarVO) obj;
        FecetOficio oficio = oficioPorFirmarVO.getOficioSeleccionado();
        BigDecimal idOficio = oficio.getIdOficio();
        FirmaDTO firmaOficioPadre = firmas.get(String.valueOf(idOficio.intValue()));
        String selloDigital = getBuzonSelladoraService().getSelloDigital(firmaOficioPadre.getCadena());
        String nombreOficioPdf = oficio.getRutaArchivo().replace(Constantes.ARCHIVO_WORD_DESPUES_2007, Constantes.ARCHIVO_PDF);
        boolean suplencia = false;
        List<EmpleadoSuplenteDTO> empleados = null;
        StringBuilder cadenaPDF = new StringBuilder(firmaOficioHelper.crearCadenaImprimible(firmaOficioPadre.getCadena(), firmaOficioPadre.getFirma(), selloDigital));
        if (firmaOficioPadre.getRfcSuplir() != null && !firmaOficioPadre.getRfcSuplir().trim().isEmpty()) {
            try {
                EmpleadoDTO firmante = getEmpleadoService().getEmpleadoCompleto(firmaOficioPadre.getRfcFirmado());
                EmpleadoDTO suplir = getEmpleadoService().getEmpleadoCompleto(firmaOficioPadre.getRfcSuplir());
                empleados = getFirmaDocumentoElectronicoDAO().obtenerDatosSuplente(firmante.getIdEmpleado(), suplir.getIdEmpleado());
                if (empleados != null && !empleados.isEmpty()) {
                    for (EmpleadoSuplenteDTO empleadoDao : empleados) {
                        empleadoDao.setNombreFirmante(suplir.getNombreCompleto());
                        empleadoDao.setNombreSuplente(firmante.getNombreCompleto());
                    }
                }
                suplencia = true;
            } catch (EmpleadoServiceException e) {
                logger.error("Error al traer empleados", e);
                throw new IllegalArgumentException("Error al traer empleados", e);
            }

            if (empleados != null && !empleados.isEmpty()) {
                suplencia = true;
                firmaOficioHelper.crearCadenaSuplente(cadenaPDF, empleados.get(0));
            }
        }

        AgaceOrden orden = oficioPorFirmarVO.getOrdenSeleccionado();
        List<FecetPropuesta> propuestas = fecetPropuestaDao.findWhereIdRegistroEquals(orden.getIdRegistroPropuesta());
        if (propuestas == null || propuestas.isEmpty()) {
            throw new IllegalArgumentException("No existe propuesta");
        }

        oficio.setIdEstatus(EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus());
        oficio.setCadenaOriginal(firmaOficioPadre.getCadena());
        oficio.setFirmaElectronica(firmaOficioPadre.getFirma());
        oficio.setFechaFirma(new Date());
        oficio.setNumeroOficio(firmaOficioHelper.generaNumeroOficion(fececUnidadAdministrativa.obtenerClavesFolioOficio(propuestas.get(0).getIdArace()), oficio.getIdOficio()));
        String rutaPdfGenerado = generarPDFConFirma(oficio.getRutaArchivo(), nombreOficioPdf, oficio.getNumeroOficio(), cadenaPDF.toString());
        oficio.setRutaArchivo(rutaPdfGenerado);
        firmaOficioDAO.updateFirmante(oficio);

        validaRegistroActoAdmin(oficio, orden, rfcFirmante, firmaOficioPadre);

        FecetCompulsas compulsa = new FecetCompulsas();
        if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())) {
            AgaceOrdenPk pk = getAgaceOrdenDao().insert(creaOrdenCompulsa(orden, oficio));
            getCompulsaDAO().actualizaEstatusCompulsa(oficio.getIdOficio(),
                    Constantes.ESTATUS_COMPULSA_APROBADA_FIRMANTE, pk.getIdOrden());
            compulsa = compulsaDAO.getCompulsaPorOficio(oficio.getIdOficio());

        } else if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.CAMBIO_METODO.getBigIdTipoOficio())) {
            FecetCambioMetodo dto = new FecetCambioMetodo();
            dto.setIdOrdenOrigen(orden.getIdOrden());
            dto.setIdEstatus(Constantes.ESTATUS_CAMBIO_METODO_APROBADO_FIRMANTE);
            cambioMetodoDao.update(dto);
            orden.setIdEstatus(Constantes.ESTATUS_ORDEN_CONCLUIDA_CAMBIO_METODO);
            AgaceOrdenPk agaceOrdenPk = new AgaceOrdenPk();
            agaceOrdenPk.setIdOrden(orden.getIdOrden());
            agaceOrdenDao.updateEstatus(agaceOrdenPk, orden);
        }

        if (oficio.getFecetTipoOficio().getAgrupador() != AgrupadorOficiosEnum.CONCLUSIVOS) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())) {
                compulsa.setOficio(oficio);
                getPlazosService().suspenderPlazoCompulsa(compulsa);
            } else {
                if (firmaOficioRule.validarOficioSuspension(oficio)) {
                    getPlazosService().suspenderPlazoOf(orden, oficio);
                }
            }
        }
        String nombreOficioDependientePdf = null;
        for (FecetOficio oficioDependiente : oficioPorFirmarVO.getOficiosDependientes()) {
            BigDecimal idOficioDependiente = oficioDependiente.getIdOficio();
            FirmaDTO firmaOficioDependiente = firmas.get(String.valueOf(idOficioDependiente.intValue()));
            String selloDigitalDependiente = getBuzonSelladoraService().getSelloDigital(firmaOficioDependiente.getCadena());
            nombreOficioDependientePdf = oficioDependiente.getRutaArchivo().replace(Constantes.ARCHIVO_WORD_DESPUES_2007, Constantes.ARCHIVO_PDF);
            oficioDependiente.setNumeroOficio(firmaOficioHelper.generaNumeroOficion(fececUnidadAdministrativa.obtenerClavesFolioOficio(propuestas.get(0).getIdArace()), oficioDependiente.getIdOficio()));
            StringBuilder cadenaPDFDependiente = new StringBuilder(firmaOficioHelper.crearCadenaImprimible(firmaOficioDependiente.getCadena(), firmaOficioDependiente.getFirma(), selloDigitalDependiente));

            if (suplencia) {
                firmaOficioHelper.crearCadenaSuplente(cadenaPDFDependiente, empleados.get(0));
            }
            oficioDependiente.setIdEstatus(EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus());
            oficioDependiente.setCadenaOriginal(firmaOficioDependiente.getCadena());
            oficioDependiente.setFirmaElectronica(firmaOficioDependiente.getFirma());
            oficioDependiente.setFechaFirma(new Date());
            String rutaPdfGeneradoDependiente = generarPDFConFirma(oficioDependiente.getRutaArchivo(), nombreOficioDependientePdf, oficioDependiente.getNumeroOficio(), cadenaPDFDependiente.toString());
            oficioDependiente.setRutaArchivo(rutaPdfGeneradoDependiente);
            validaRegistroActoAdmin(oficioDependiente, orden, rfcFirmante, firmaOficioDependiente);
            try {
                firmaOficioDAO.updateFirmante(oficioDependiente);
            } catch (Exception e) {
                logger.error("No se pudo actualizar el registro" + e.getCause(), e);
                throw new IllegalArgumentException("Ocurrio un error al actualizar el registro: " + e.getMessage(), e);
            }
        }
        getAuditoriaService().crearPistaAuditoriaOficio(oficio);
        enviarNotificacionOficio(orden, oficio);
        logger.debug("Guarda Oficio firmado");
    }

    private void enviarNotificacionOficio(AgaceOrden orden, FecetOficio oficio) {
        if (orden != null && oficio != null) {
            Set<String> destinatarios = new TreeSet<String>();
            getNotificacionService().obtenerCorreoEmpleado(orden.getIdAuditor().intValue(), Constantes.USUARIO_AUDITOR, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            Map<String, String> dataCorreo = getNotificacionService().obtenerDatosCorreo(ID_LEYENDA_OFICIO);
            dataCorreo.put("NombreOficio", oficio.getNumeroOficio());
            dataCorreo.put("NúmeroOrden", orden.getNumeroOrden());
            dataCorreo.put("numeroOrden", orden.getNumeroOrden());
            dataCorreo.put("metodo", orden.getFeceaMetodo().getNombre());
            dataCorreo.put(Constantes.DATOS_EXTRA, String.format(Constantes.CONTENIDO_EXTRA, orden.getFecetContribuyente().getNombre(),
                    orden.getFecetContribuyente().getRfc(), oficio.getFecetTipoOficio().getNombre()));
            try {
                getNotificacionService().enviarNotificacionGenerico(dataCorreo, ReportType.AVISOS_ORDEN_GENERICO, destinatarios);
            } catch (BusinessException e) {
                logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
            }
        }
    }

    private String generarPDFConFirma(String rutaWord, String rutaPdf, String numeroOficio, String cadenaPDF) {
        if (rutaWord != null) {
            try {
                String rutaPDF = new StringBuffer(rutaPdf).toString();
                String rutaDOCX = new StringBuffer(rutaWord).toString();
                InputStream entrada = new FileInputStream(new File(rutaDOCX));
                OutputStream salida = new FileOutputStream(new File(rutaPDF));
                plantilladorService.generaPDF(entrada, salida, numeroOficio, cadenaPDF);
                return rutaPDF;
            } catch (Exception e) {
                logger.error("No se encontro el Archivo " + e.getCause(), e);
                throw new IllegalArgumentException("No se encontro el Archivo :" + UtileriasMapperDao.getNameFileFromPath(rutaWord), e);
            }
        }
        return null;
    }

    @SuppressWarnings("resource")
    private AgaceOrden creaOrdenCompulsa(AgaceOrden orden, FecetOficio oficio) {
        FecetCompulsas fecetCompulsas = compulsaDAO.getDatosGenerales(oficio.getIdOficio());
        FecetOficio fecetOficio = oficioDao.getOficioById(oficio.getIdOficio());
        logger.debug("fecetCompulsas {} ", fecetCompulsas);
        logger.debug("fecetOficio {} ", fecetOficio);
        Formatter fmt = new Formatter();
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        StringBuilder sb = new StringBuilder();
        AgaceOrden miniOrden = new AgaceOrden();
        miniOrden.setIdMetodo(orden.getIdMetodo());
        sb.append("ECOM");
        sb.append(orden.getNumeroOrden().substring(CUATRO, SEIS));
        sb.append(String.valueOf(fmt.format(STR4CEROSIZQ, agaceOrdenDao.getClaveOrden())));
        sb.append("-");
        sb.append(year.substring(2));
        miniOrden.setNumeroOrden(sb.toString());
        miniOrden.setFechaCreacion(Calendar.getInstance().getTime());
        miniOrden.setPrioridad(orden.getPrioridad());
        miniOrden.setPrioridadSugerida(orden.getPrioridadSugerida());
        miniOrden.setDiasRestantesPlazo(QUINCE);
        miniOrden.setDiasHabiles(false);
        miniOrden.setSuspencionPlazo(false);
        miniOrden.setDiasRestantesDocumentos(QUINCE);
        miniOrden.setSemaforo(1);
        miniOrden.setIdContribuyente(fecetCompulsas.getIdContribuyenteCompulsado());
        miniOrden.setIdEstatus(new BigDecimal(CIENTO_DOS));
        miniOrden.setIdAuditor(orden.getIdAuditor());
        miniOrden.setIdFirmante(orden.getIdFirmante());
        miniOrden.setIdPropuesta(orden.getIdPropuesta());
        miniOrden.setIdRegistroPropuesta(orden.getIdRegistroPropuesta());
        miniOrden.setIdRevision(orden.getIdRevision());
        miniOrden.setBlnCompulsa(true);
        miniOrden.setIdNyV(fecetOficio.getIdNyV());
        return miniOrden;
    }

    public AgaceOrdenDao getAgaceOrdenDao() {
        return agaceOrdenDao;
    }

    public void setAgaceOrdenDao(AgaceOrdenDao agaceOrdenDao) {
        this.agaceOrdenDao = agaceOrdenDao;
    }

    public FecetCompulsasDao getCompulsaDAO() {
        return compulsaDAO;
    }

    public void setCompulsaDAO(FecetCompulsasDao compulsaDAO) {
        this.compulsaDAO = compulsaDAO;
    }

    public FecetCambioMetodoDao getCambioMetodoDao() {
        return cambioMetodoDao;
    }

    public void setCambioMetodoDao(FecetCambioMetodoDao cambioMetodoDao) {
        this.cambioMetodoDao = cambioMetodoDao;
    }

    public FecetDetalleNyVDAO getDetalleDao() {
        return detalleDao;
    }

    public void setDetalleDao(FecetDetalleNyVDAO detalleDao) {
        this.detalleDao = detalleDao;
    }

    private void validaRegistroActoAdmin(FecetOficio oficio, AgaceOrden orden, String rfcFirmante, FirmaDTO firma) {
        boolean seRegistroActo = false;

        if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio()) && oficio.getRfcCompulsado() != null) {
            validarContribuyenteService.validaEstatusContacto(oficio.getRfcCompulsado());
            seRegistroActo = true;
            if (validarContribuyenteService.getEstatusDeContacto().equals(ConstantesPropuestas.CON_MEDIOS)) {
                registrarActoAdministrativo(oficio, orden, rfcFirmante, firma);
            } else {
                registrarDetalleNyV(oficio);
            }
        }
        if (!seRegistroActo && (firmaOficioRule.validarIsOficioMedidaApremio(oficio) || firmaOficioRule.validarIsOficioCompulsa(oficio))) {
            seRegistroActo = true;
            registrarDetalleNyV(oficio);
        }

        if (!seRegistroActo) {
            registrarActoAdministrativo(oficio, orden, rfcFirmante, firma);
        }
    }

    private void registrarDetalleNyV(FecetOficio oficio) {
        FecetDetalleNyV fecetDetalleNyV = new FecetDetalleNyV();
        fecetDetalleNyV.setFecNotificacionContNyV(new Date());
        fecetDetalleNyV.setFecNotificacionNyV(new Date());
        fecetDetalleNyV.setFecSurteEfectosNyV(new Date());
        fecetDetalleNyVDAO.guardaFolioNyV(fecetDetalleNyV);
        fecetDetalleNyVDAO.guardaDetalleNyV(fecetDetalleNyV);
        oficio.setFecetDetalleNyV(fecetDetalleNyV);
        plazosDAO.actualizaNotificableIdNyV(oficio);
    }

    private void registrarActoAdministrativo(FecetOficio oficio, AgaceOrden orden, String rfcFirmante, FirmaDTO firma) {
        FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(orden.getIdContribuyente());
        firmaOficioHelper.checkArgument(contribuyente == null);

        EmpleadoDTO empleadoRespuesta = null;
        try {
            empleadoRespuesta = getEmpleadoService().getEmpleadoCompleto(rfcFirmante);
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
            throw new IllegalArgumentException("Error al obtener datos del servicio de empleado agace", e);
        }
        firmaOficioHelper.checkArgument(empleadoRespuesta == null);
        if (empleadoRespuesta != null && contribuyente != null) {
            Long centroCosto = Long.valueOf("110");
            if (isActivoNyv() && firma.getCentroCosto() != null) {
                centroCosto = firma.getCentroCosto();
                empleadoRespuesta.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
            }
            FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(orden.getIdMetodo().longValue(), oficio.getFecetTipoOficio().getIdTipoOficio().longValue(), centroCosto, isActivoNyv(),
                    Boolean.valueOf(getVersion()));
            firmaOficioHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

            getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
            firmaOficioHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

            orden.setFecetContribuyente(contribuyente);
            Calendar fechaFIEL = Calendar.getInstance();
            fechaFIEL.setTime(new Date());
            fechaFIEL.add(Calendar.DAY_OF_YEAR, 2);

            firmaOficioHelper.generarActoAdministrativo(oficio, empleadoRespuesta, actoAdmin, fechaFIEL.getTime(), contribuyente.getRfc(), oficio.getNumeroOficio());
            oficio.getDatosNotificable().setClaveUnidadAdmin(centroCosto.toString());
            oficio.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", oficio.getNumeroOficio(), contribuyente.getRfc()));
            oficio.getDatosNotificable().setInsertaNyV(true);
            oficio.getDatosNotificable().setFececActosAdm(actoAdmin);

            try {
                getPlazosService().registrarActoAdministrativo(oficio, actoAdmin);
            } catch (NegocioException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }

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
                    oficio.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", oficio.getNumeroOficio(), fecetAsociado.getRfc()));
                    oficio.getDatosNotificable().setInsertaNyV(false);

                    try {
                        getPlazosService().registrarActoAdministrativo(oficio, actoAdmin);
                    } catch (NegocioException e) {
                        throw new IllegalArgumentException(e.getMessage(), e);
                    }
                }
            }
        }
    }
}
