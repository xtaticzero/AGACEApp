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
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.firma.dao.FirmaProrrogaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.helper.FirmaOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaOficioService;

@Service("firmaProrrogaOficioService")
public class FirmaProrrogaOficioServiceImpl extends FirmaServiceAbstract implements FirmaProrrogaOficioService {

    private static final long serialVersionUID = 13456543L;

    @Autowired
    private transient FirmaProrrogaDAO firmaProrrogaDAO;

    @Autowired
    private transient FecetFlujoProrrogaOficioDao fecetFlujoProrrogaOficioDao;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient PlantilladorService plantilladorService;

    @Autowired
    private FirmaOficioHelper firmaProrrogaHelper;

    @Autowired
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Override
    public FirmaDAO getFirmaDAO() {
        return firmaProrrogaDAO;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        FecetProrrogaOficio prorroga = (FecetProrrogaOficio) obj;

        if (firmas != null && !firmas.isEmpty()) {
            BigDecimal idProrrogaOficio = prorroga.getIdProrrogaOficio();
            FirmaDTO firma = firmas.get(String.valueOf(idProrrogaOficio.intValue()));
            String selloDigital = getBuzonSelladoraService().getSelloDigital(firma.getCadena());
            FecetFlujoProrrogaOficio flujoProrrogaOficio = getFlujoProrroga(prorroga.getIdProrrogaOficio());
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
            String rutaResolucion = generarPDFConFirma(prorroga.getRutaResolucion(), docResolucionPdf, String.valueOf(idProrrogaOficio), cadenaPDF.toString());
            logger.debug(" ::: RutaResolucion ::: [{}]", rutaResolucion);
            prorroga.setIdEstatus(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus());
            prorroga.setCadenaFirmante(firma.getCadena());
            prorroga.setFirmaFirmante(firma.getFirma());
            prorroga.setFechaFirma(new Date());
            prorroga.setAprobada(flujoProrrogaOficio.getAprobada());
            prorroga.setRutaResolucion(docResolucionPdf);
            EmpleadoDTO empleado = finFirmantePorRfc(firma.getRfcFirmado());
            prorroga.setIdFirmante(empleado.getIdEmpleado());
            fecetProrrogaOficioDao.updateProrrogaFirma(prorroga);
            guardarFlujoProrroga(flujoProrrogaOficio);
            registrarActoAdministrativo(prorroga, empleado, firma);
            getAuditoriaService().aprobarProrrogaOficioFirmante(prorroga.getOficio());
            cargarFirmaProrrogaService.enviarCorreoProrroga(null, Constantes.FIRMANTE, "APROBADA", prorroga.getOficio(), prorroga);
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
                throw new IllegalArgumentException("No se encontro el Archivo ", e);
            }
        }
        return null;
    }

    private void guardarFlujoProrroga(FecetFlujoProrrogaOficio flujoProrroga) {
        flujoProrroga.setIdEstatus(EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus());
        fecetFlujoProrrogaOficioDao.update(flujoProrroga);
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

    private EmpleadoDTO finFirmantePorRfc(String rfcFirmante) {
        EmpleadoDTO empleado = null;
        try {
            empleado = empleadoService.getEmpleadoCompleto(rfcFirmante);
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
        return empleado;
    }

    private FecetFlujoProrrogaOficio getFlujoProrroga(BigDecimal idProrrogaOficio) {
        long idFlujoProrrogaOrden = new Long(fecetFlujoProrrogaOficioDao.findLastIdFecetFlujoProrrogaOficio(idProrrogaOficio));
        return fecetFlujoProrrogaOficioDao.findByPrimaryKey(new BigDecimal(idFlujoProrrogaOrden));
    }

    private void registrarActoAdministrativo(FecetProrrogaOficio prorroga, EmpleadoDTO empleado, FirmaDTO firma) {
        FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(prorroga.getOrden().getIdContribuyente());
        firmaProrrogaHelper.checkArgument(contribuyente == null);
        firmaProrrogaHelper.checkArgument(empleado == null);

        FecetOficio oficio = fecetOficioDao.getOficioById(prorroga.getIdOficio());
        firmaProrrogaHelper.checkArgument(oficio == null);

        BigDecimal idEmpleadoActual = empleado.getIdEmpleado();
        Long centroCosto = Long.valueOf("110");
        if (isActivoNyv() && firma.getCentroCosto() != null) {
            centroCosto = firma.getCentroCosto();
            empleado.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
        }

        FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(prorroga.getOrden().getIdMetodo().longValue(), oficio.getFecetTipoOficio().getIdTipoOficio().longValue(), centroCosto, isActivoNyv(),
                Boolean.valueOf(getVersion()));
        firmaProrrogaHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

        getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
        firmaProrrogaHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

        Calendar fechaVigenciaFIEL = Calendar.getInstance();
        fechaVigenciaFIEL.setTime(new Date());
        fechaVigenciaFIEL.add(Calendar.DAY_OF_YEAR, 2);

        String referenciaOriginal = String.format("%s-P-%s", prorroga.getOrden().getIdRegistroPropuesta(), prorroga.getId());
        firmaProrrogaHelper.generarActoAdministrativo(prorroga, empleado, actoAdmin, fechaVigenciaFIEL.getTime(), contribuyente.getRfc(), referenciaOriginal);
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

        List<FecetAsociado> asociados = getFecetAsociadoDao().getAsociadosByContribuyente(contribuyente.getRfc(), prorroga.getOrden().getId());
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
        empleado.setIdEmpleado(idEmpleadoActual);
    }
}
