package mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.plantillador.service.PlantilladorService;
import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.firma.dao.FirmaProrrogaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.flujo.dao.FlujoProrrogaOrdenDAO;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.helper.FirmaOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaService;

@Service("firmaProrrogaService")
public class FirmaProrrogaServiceImpl extends FirmaServiceAbstract implements FirmaProrrogaService {

    private static final long serialVersionUID = -2939953597726158718L;

    @Autowired
    private transient FirmaProrrogaDAO firmaProrrogaDAO;

    @Autowired
    private transient FlujoProrrogaOrdenDAO flujoProrrogaOrdenDAO;

    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;

    @Autowired
    private transient PlantilladorService plantilladorService;

    @Autowired
    private FirmaOficioHelper firmaProrrogaHelper;

    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Override
    public FirmaDAO getFirmaDAO() {
        return firmaProrrogaDAO;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        FecetProrrogaOrden prorroga = (FecetProrrogaOrden) obj;
        if (firmas != null && !firmas.isEmpty()) {
            BigDecimal idProrrogaOrden = prorroga.getIdProrrogaOrden();
            FirmaDTO firma = firmas.get(String.valueOf(idProrrogaOrden.intValue()));
            logger.debug("::::::::::::: FIRMA ::::::: {} ", firma);
            String selloDigital = getBuzonSelladoraService().getSelloDigital(firma.getCadena());
            String docResolucionPdf = prorroga.getRutaResolucion().replace(Constantes.ARCHIVO_WORD_DESPUES_2007, Constantes.ARCHIVO_PDF);
            StringBuilder cadenaPDF = new StringBuilder(firmaProrrogaHelper.crearCadenaImprimible(firma.getCadena(), firma.getFirma(), selloDigital));
            List<EmpleadoSuplenteDTO> empleados = null;
            if (firma.getRfcSuplir() != null && !firma.getRfcSuplir().trim().isEmpty()) {
                try {
                    EmpleadoDTO firmante = getEmpleadoService().getEmpleadoCompleto(firma.getRfcFirmado());
                    EmpleadoDTO suplir = getEmpleadoService().getEmpleadoCompleto(firma.getRfcSuplir());
                    empleados = getFirmaDocumentoElectronicoDAO().obtenerDatosSuplente(firmante.getIdEmpleado(), suplir.getIdEmpleado());
                    if (empleados != null && !empleados.isEmpty()) {
                        for (EmpleadoSuplenteDTO empleadoDao : empleados) {
                            empleadoDao.setNombreFirmante(suplir.getNombreCompleto());
                            empleadoDao.setNombreSuplente(firmante.getNombreCompleto());
                        }
                    }
                } catch (EmpleadoServiceException e) {
                    logger.error("Error al traer empleados", e);
                    throw new IllegalArgumentException("Error al traer empleados", e);
                }
                if (empleados != null && !empleados.isEmpty()) {
                    firmaProrrogaHelper.crearCadenaSuplente(cadenaPDF, empleados.get(0));
                }
            }
            String rutaResolucion = generarPDFConFirma(prorroga.getRutaResolucion(), docResolucionPdf, String.valueOf(idProrrogaOrden), cadenaPDF.toString());
            prorroga.setIdEstatus(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus());
            prorroga.setCadenaFirmante(firma.getCadena());
            prorroga.setFirmaFirmante(firma.getFirma());
            prorroga.setFechaFirma(new Date());
            prorroga.setIdFirmante(prorroga.getOrden().getIdFirmante());
            FecetFlujoProrrogaOrden flujoProrrogaOrden = getFlujoProrroga(prorroga.getIdProrrogaOrden());
            prorroga.setAprobada(flujoProrrogaOrden.getAprobada());
            logger.debug(" ::: RutaResolucion ::: [{}]", rutaResolucion);
            prorroga.setRutaResolucion(docResolucionPdf);
            logger.debug(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            logger.debug(" EL ID DEL FIRMANTE ES DE LA PRORROGA ORDEN ES " + prorroga.getIdFirmante());
            logger.debug(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            fecetProrrogaOrdenDao.updateProrrogaFirma(prorroga);
            guardarFlujoProrroga(flujoProrrogaOrden);
            registrarActoAdministrativo(prorroga, prorroga.getOrden(), rutaResolucion, firma);
            getAuditoriaService().aprobarProrrogaOrdenFirmante(prorroga.getOrden());
            cargarFirmaProrrogaService.enviarCorreoProrroga(prorroga, Constantes.FIRMANTE, "APROBADA", null, null);
        }
    }

    private String generarPDFConFirma(String rutaWord, String rutaPdf, String numeroProrroga, String cadenaPDF) {
        if (rutaWord != null) {
            try {
                String rutaPDF = new StringBuffer(rutaPdf).toString();
                String rutaDOCX = new StringBuffer(rutaWord).toString();
                InputStream entrada = new FileInputStream(new File(rutaDOCX));
                OutputStream salida = new FileOutputStream(new File(rutaPDF));
                plantilladorService.generaPDF(entrada, salida, numeroProrroga, cadenaPDF);
                return rutaPDF;
            } catch (Exception e) {
                logger.error("No se encontro el Archivo " + e.getCause(), e);
                throw new IllegalArgumentException("No se encontro el Archivo " + UtileriasMapperDao.getNameFileFromPath(rutaWord), e);
            }
        }
        return null;
    }

    private void guardarFlujoProrroga(FecetFlujoProrrogaOrden flujoProrroga) {
        flujoProrroga.setIdEstatus(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus());
        flujoProrrogaOrdenDAO.update(flujoProrroga);
    }

    @Override
    public EmpleadoDTO findFirmante(BigDecimal idFirmante) {
        EmpleadoDTO empleado = null;
        try {
            empleado = empleadoService.getEmpleadoCompleto(idFirmante.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
        return empleado;
    }

    private FecetFlujoProrrogaOrden getFlujoProrroga(BigDecimal idProrrogaOrden) {
        long idFlujoProrrogaOrden = flujoProrrogaOrdenDAO.findLastIdFecetFlujoProrrogaOrden(idProrrogaOrden);
        return flujoProrrogaOrdenDAO.findByPrimaryKey(new BigDecimal(idFlujoProrrogaOrden));
    }

    private void registrarActoAdministrativo(FecetProrrogaOrden prorroga, AgaceOrden orden, String rutaResolucion, FirmaDTO firma) {
        firmaProrrogaHelper.checkArgument(prorroga == null);

        FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(orden.getIdContribuyente());
        firmaProrrogaHelper.checkArgument(contribuyente == null);

        EmpleadoDTO empleadoRespuesta = null;
        try {
            empleadoRespuesta = empleadoService.getEmpleadoCompleto(orden.getIdFirmante().intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
        firmaProrrogaHelper.checkArgument(empleadoRespuesta == null);

        if (empleadoRespuesta != null) {
            Long centroCosto = Long.valueOf("110");
            if (isActivoNyv() && firma.getCentroCosto() != null) {
                centroCosto = firma.getCentroCosto();
                empleadoRespuesta.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
            }

            FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(orden.getIdMetodo().longValue(), null, centroCosto, isActivoNyv(),
                    Boolean.valueOf(getVersion()));
            firmaProrrogaHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

            getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
            firmaProrrogaHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

            orden.setFecetContribuyente(contribuyente);

            Calendar fechaVigenciaFIEL = Calendar.getInstance();
            fechaVigenciaFIEL.setTime(new Date());
            fechaVigenciaFIEL.add(Calendar.DAY_OF_YEAR, 2);

            firmaProrrogaHelper.generarActoAdministrativo(orden, prorroga, empleadoRespuesta, actoAdmin, fechaVigenciaFIEL.getTime(), rutaResolucion);
            String referenciaOriginal = prorroga.getDatosNotificable().getNumeroReferencia();
            prorroga.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", referenciaOriginal, contribuyente.getRfc()));
            prorroga.getDatosNotificable().setInsertaNyV(true);
            prorroga.getDatosNotificable().setClaveUnidadAdmin(centroCosto.toString());
            prorroga.getDatosNotificable().setFececActosAdm(actoAdmin);

            try {
                getPlazosService().registrarActoAdministrativo(prorroga, actoAdmin);
            } catch (NegocioException e) {
                logger.error(e.getMessage());
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
                    prorroga.getDatosNotificable().setRfcContribuyente(fecetAsociado.getRfc());
                    prorroga.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", referenciaOriginal, fecetAsociado.getRfc()));
                    prorroga.getDatosNotificable().setInsertaNyV(false);

                    try {
                        getPlazosService().registrarActoAdministrativo(prorroga, actoAdmin);
                    } catch (NegocioException e) {
                        logger.error(e.getMessage());
                        throw new IllegalArgumentException(e.getMessage(), e);
                    }
                }
            }
        }
    }
}
