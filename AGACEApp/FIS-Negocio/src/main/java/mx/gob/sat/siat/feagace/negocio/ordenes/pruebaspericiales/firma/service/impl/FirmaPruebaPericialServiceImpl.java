package mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.impl;

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
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.pruebapericial.firma.dao.FirmaPruebaPericialDAO;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPericialesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper.FirmaPruebaPericialHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.FirmaPruebaPericialService;

@Service("firmaPruebaPericialService")
public class FirmaPruebaPericialServiceImpl extends FirmaServiceAbstract implements FirmaPruebaPericialService {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private transient FirmaPruebaPericialDAO firmaPruebaPericialDAO;
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;
    @Autowired
    private FirmaPruebaPericialHelper firmaPruebaPericialHelper;
    @Autowired
    private transient FecetFlujoPruebasPericialesDao fecetFlujoPruebasPericialesDao;
    @Autowired
    private transient PlantilladorService plantilladorService;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient CargarFirmaPruebasPericialesService cargarFirmaPruebasPericialesService;

    @Override
    public FirmaDAO getFirmaDAO() {
        return firmaPruebaPericialDAO;
    }

    @Override
    public void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante) {
        FecetPruebasPericiales pruebaPericial = (FecetPruebasPericiales) obj;
        if (firmas != null && !firmas.isEmpty()) {
            BigDecimal idPruebaPericial = pruebaPericial.getIdPruebasPericiales();
            FirmaDTO firma = firmas.get(String.valueOf(idPruebaPericial.intValue()));
            logger.debug("::::::::::::: FIRMA ::::::: {} ", firma);
            String selloDigital = getBuzonSelladoraService().getSelloDigital(firma.getCadena());
            String docResolucionPdf = pruebaPericial.getRutaResolucion().replace(Constantes.ARCHIVO_WORD_DESPUES_2007, Constantes.ARCHIVO_PDF);
            StringBuilder cadenaPDF = new StringBuilder(firmaPruebaPericialHelper.crearCadenaImprimible(firma.getCadena(), firma.getFirma(), selloDigital));
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
                    firmaPruebaPericialHelper.crearCadenaSuplente(cadenaPDF, empleados.get(0));
                }
            }
            String rutaResolucion = generarPDFConFirma(pruebaPericial.getRutaResolucion(), docResolucionPdf, String.valueOf(idPruebaPericial), cadenaPDF.toString());
            pruebaPericial.setIdEstatus(EstatusPruebasPericiales.RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV.getBigIdEstatus());
            pruebaPericial.setCadenaFirmante(firma.getCadena());
            pruebaPericial.setFirmaFirmante(firma.getFirma());
            pruebaPericial.setFechaFirma(new Date());
            pruebaPericial.setIdFirmante(pruebaPericial.getOrden().getIdFirmante());
            FecetFlujoPruebasPericiales flujoPruebasPericiales = getFlujoPruebasPericiales(pruebaPericial.getIdPruebasPericiales());
            pruebaPericial.setAprobada(flujoPruebasPericiales.getAprobada());
            logger.debug(" ::: RutaResolucion ::: [{}]", rutaResolucion);
            pruebaPericial.setRutaResolucion(docResolucionPdf);
            logger.debug(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            logger.debug(" EL ID DEL FIRMANTE ES DE LA PRORROGA ORDEN ES " + pruebaPericial.getIdFirmante());
            logger.debug(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            fecetPruebasPericialesDao.updatePruebasPericialesFirma(pruebaPericial);
            guardarFlujoPruebasPericiales(flujoPruebasPericiales);
            registrarActoAdministrativo(pruebaPericial, pruebaPericial.getOrden(), rutaResolucion, firma);
            cargarFirmaPruebasPericialesService.enviarCorreoPruebasPericiales(pruebaPericial, Constantes.FIRMANTE, "APROBADA");
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

    private void guardarFlujoPruebasPericiales(FecetFlujoPruebasPericiales flujoPruebaPericial) {
        flujoPruebaPericial.setIdEstatus(EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_APROBADA_FIRMANTE.getBigIdEstatus());
        fecetFlujoPruebasPericialesDao.update(flujoPruebaPericial);
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

    private FecetFlujoPruebasPericiales getFlujoPruebasPericiales(BigDecimal idPruebasPericiales) {
        String idFlujoPruebasPericiales = fecetFlujoPruebasPericialesDao.findLastIdFecetFlujoPruebasPericiales(idPruebasPericiales);
        return fecetFlujoPruebasPericialesDao.findByPrimaryKey(new BigDecimal(Integer.parseInt(idFlujoPruebasPericiales)));
    }

    private void registrarActoAdministrativo(FecetPruebasPericiales pruebaPericial, AgaceOrden orden, String rutaResolucion, FirmaDTO firma) {
        if (orden != null) {
            FecetContribuyente contribuyente = getFecetContribuyenteDao().findByPrimaryKey(orden.getIdContribuyente());
            EmpleadoDTO empleadoRespuesta = null;
            try {
                empleadoRespuesta = empleadoService.getEmpleadoCompleto(orden.getIdFirmante().intValue());
            } catch (EmpleadoServiceException e) {
                logger.error("Error al obtener datos del servicio de empleado agace", e);
            }

            firmaPruebaPericialHelper.checkArgument(pruebaPericial == null);
            firmaPruebaPericialHelper.checkArgument(contribuyente == null);
            firmaPruebaPericialHelper.checkArgument(empleadoRespuesta == null);

            if (pruebaPericial != null && empleadoRespuesta != null) {
                Long centroCosto = Long.valueOf("110");
                if (isActivoNyv() && firma.getCentroCosto() != null) {
                    centroCosto = firma.getCentroCosto();
                    empleadoRespuesta.setIdEmpleado(new BigDecimal(firma.getNumeroEmpleado()));
                }
                Long idTipoOficio = Long.valueOf(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO.getIdTipoOficio());
                FececActosAdm actoAdmin = getFececActosAdmDao().obtenerIdNyv(orden.getIdMetodo().longValue(), idTipoOficio, centroCosto, isActivoNyv(),
                        Boolean.valueOf(getVersion()));
                firmaPruebaPericialHelper.checkArgument(actoAdmin == null || actoAdmin.getIdNyv() == null);

                getPlazosService().obtenerDocumentosActoAdmin(actoAdmin);
                firmaPruebaPericialHelper.checkArgument(actoAdmin.getDocumentosActo() == null);

                orden.setFecetContribuyente(contribuyente);

                Calendar fechaVigenciaFIEL = Calendar.getInstance();
                fechaVigenciaFIEL.setTime(new Date());
                fechaVigenciaFIEL.add(Calendar.DAY_OF_YEAR, 2);

                firmaPruebaPericialHelper.generarActoAdministrativo(orden, pruebaPericial, empleadoRespuesta, actoAdmin, fechaVigenciaFIEL.getTime(), rutaResolucion);

                String referenciaOriginal = pruebaPericial.getDatosNotificable().getNumeroReferencia();
                pruebaPericial.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", referenciaOriginal, contribuyente.getRfc()));
                pruebaPericial.getDatosNotificable().setInsertaNyV(true);
                pruebaPericial.getDatosNotificable().setClaveUnidadAdmin(centroCosto.toString());
                pruebaPericial.getDatosNotificable().setFececActosAdm(actoAdmin);

                try {
                    getPlazosService().registrarActoAdministrativo(pruebaPericial, actoAdmin);
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
                        pruebaPericial.getDatosNotificable().setRfcContribuyente(fecetAsociado.getRfc());
                        pruebaPericial.getDatosNotificable().setNumeroReferencia(String.format("%s-%s", referenciaOriginal, fecetAsociado.getRfc()));
                        pruebaPericial.getDatosNotificable().setInsertaNyV(false);

                        try {
                            getPlazosService().registrarActoAdministrativo(pruebaPericial, actoAdmin);
                        } catch (NegocioException e) {
                            logger.error(e.getMessage());
                            throw new IllegalArgumentException(e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }
}
