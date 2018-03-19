package mx.gob.sat.siat.feagace.vista.propuestas.carga.managedbean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.carga.CargaMasivaXExcelMCAService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import mx.gob.sat.siat.feagace.negocio.util.carga.constantes.TipoListaCargaPropuestaEnum;
import mx.gob.sat.siat.feagace.negocio.util.carga.exception.CargaRegistroXExcelException;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.CargaMasivaPropuestasMBHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaMasivaAttributeHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaValidaBooleanHelper;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "cargaMasivaCartasManagedBean")
@ViewScoped
public class CargaMasivaCartasManagedBean extends AbstractManagedBean {

    public static final String ARCHIVO_REPORTES = Propiedades.get("jasper.ubicacion.reportes");
    public static final String RUTA_IMAGENES = Propiedades.get("jasper.ubicacion.acuse.imagenes");

    private static final String ERROR = "Error: ";

    private static final long serialVersionUID = -3790405310555686166L;

    private transient StreamedContent pdfPropuestasCorrectas;
    private transient UploadedFile archivoCarga;

    @ManagedProperty(value = "#{cargaMasivaPropuestasService}")
    private transient CargaMasivaPropuestasService cargaMasivaPropuestasService;

    private List<CargaMasivaPropuestasDTO> cargaMasivaPropuestaCorrecta;
    private List<CargaMasivaPropuestasDTO> cargaMasivaPropuestaIncorrecta;

    private EmpleadoDTO empleadoLogueado;

    @ManagedProperty(value = "#{cargaMasivaPropuestasMBHelper}")
    private CargaMasivaPropuestasMBHelper helper;
    private CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO;
    private CargaValidaBooleanHelper cargaValidaBooleanHelper;
    private CargaMasivaAttributeHelper cargaMasivaAttributeHelper;
    @ManagedProperty(value = "#{cargaMasivaXExcelMCAService}")
    private transient CargaMasivaXExcelMCAService cargaMasivaXExcelMCAService;

    @PostConstruct
    public void init() {
        logger.debug("Iniciando bean CargaMasivaCartasManagedBean ");
        try {
            if (getEmpleadoService().validarExistenciaTipoEmpleado(getEmpleadoService().getEmpleadoCompleto(getRFCSession()),
                    TipoEmpleadoEnum.PROGRAMADOR)) {
                setEmpleadoLogueado(getEmpleadoService().getEmpleadoCompleto(getRFCSession()));
                setCargaValidaBooleanHelper(new CargaValidaBooleanHelper());
                setCargaMasivaAttributeHelper(new CargaMasivaAttributeHelper());
                getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
                getCargaValidaBooleanHelper().setCancelarHabilitado(true);
                getCargaValidaBooleanHelper().setFileHabilitado(true);
                getCargaValidaBooleanHelper().setProcesadoExito(true);
            } else {
                addErrorMessage(null, ERROR_LOGGEO);
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            addErrorMessage(null, ERROR_LOGGEO);
        }
    }

    public void procesaExito() {
        for (CargaMasivaPropuestasDTO cargaMasivaPropuestas : getCargaMasivaPropuestaCorrecta()) {
            getCargaMasivaPropuestasService().insertPropuestaMasiva(cargaMasivaPropuestas, getEmpleadoLogueado());
        }
        getCargaMasivaAttributeHelper().setFechaActual(new Date());
        getCargaValidaBooleanHelper().setProcesadoExito(false);
        getCargaMasivaAttributeHelper().setContadorCorrectos(cargaMasivaPropuestaCorrecta.size());
        ResumenCargaMasivaDTO resumen = new ResumenCargaMasivaDTO();
        resumen.setFechaCarga(getCargaMasivaAttributeHelper().getFechaActual());
        resumen.setIdOrigen(ConstantesPropuestasMasivas.CARGA_MASIVA_MCA);
        resumen.setTotalRegistros(getCargaMasivaAttributeHelper().getContadorCorrectos());
        try {
            getCargaMasivaAttributeHelper()
                    .setFolioResultado(getCargaMasivaPropuestasService().insertaResumen(resumen));
        } catch (NegocioException e) {
            logger.error(e.getMessage(), e);
        }
        addMessage(null, "Se cargaron las cartas exitosamente.", "");
        getCargaValidaBooleanHelper().setPanelConfirmacionVisible(false);
    }

    public void getCartasInvitacionXLS() {
        getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
        getCargaValidaBooleanHelper().setCancelarHabilitado(true);
        setCargaMasivaPropuestaCorrecta(new ArrayList<CargaMasivaPropuestasDTO>());
        setCargaMasivaPropuestaIncorrecta(new ArrayList<CargaMasivaPropuestasDTO>());
        if (getArchivoCarga() == null) {
            addErrorMessage(null, ConstantesPropuestasMasivas.MSJ_ERROR_ARCHIVO_INVALIDO,
                    "No se adjunt\u00f3 el archivo formato de carga correctamente, favor de verificar.");
            return;
        }

        if (getCargaMasivaAttributeHelper().getFolioCargaDoc() == null
                || getCargaMasivaAttributeHelper().getFolioCargaDoc().trim().equals("")) {
            addErrorMessage(null, "",
                    "El folio que ingreso no es el correcto o no existe, verifique su informaci\u00f3n");
            return;
        }

        if (CargaMasivaPropuestasMBHelper
                .validaExistenciaFolioDoc(getCargaMasivaAttributeHelper().getFolioCargaDoc())) {
            try {
                if (getArchivoCarga().getFileName().endsWith(".xls")) {
                    cargaLayoutXLS();
                }
                getCargaMasivaAttributeHelper().setRegistrosCorrectos(getCargaMasivaPropuestaCorrecta().size());
                getCargaValidaBooleanHelper().setCancelarHabilitado(false);
                getCargaValidaBooleanHelper().setValidaInformacionHabilitado(false);
                getCargaValidaBooleanHelper().setProcesaInformacionHabilitado(false);
                getCargaValidaBooleanHelper().setExportarErrorHabilitado(false);
                getCargaValidaBooleanHelper().setExportarExitoHabilitado(true);
                getCargaValidaBooleanHelper().setBotonRegresarVisible(true);
                getCargaValidaBooleanHelper()
                        .setPanelVisibleCorrectos(getCargaMasivaAttributeHelper().getRegistrosCorrectos() > 0);
                getCargaValidaBooleanHelper()
                        .setPanelVisibleErroneos(getCargaMasivaAttributeHelper().getRegistrosErroneos() > 0);
                getCargaValidaBooleanHelper().setAccordionPanelVisible(true);
                getCargaMasivaAttributeHelper().setIndiceActivoInicial("");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                addErrorMessage(null, ConstantesPropuestasMasivas.MSJ_ERROR_ARCHIVO_INVALIDO,
                        "Existe un error al procesar el archivo");
            }
        } else {
            addErrorMessage(null, "", "El folio que ingreso no es el correcto o no existe, verifique su informaci\u00f3n");
        }
    }

    public void cargaLayoutXLS() {
        try {
            if (empleadoLogueado != null) {
                Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> validacionExcel = cargaMasivaXExcelMCAService
                        .validarMCADeExcel(getArchivoCarga().getInputstream(), empleadoLogueado,
                                getCargaMasivaAttributeHelper().getFolioCargaDoc(), getRFCSession());

                List<CargaMasivaPropuestasDTO> lstPropuestasIncorrectas = validacionExcel.get(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE);
                List<CargaMasivaPropuestasDTO> lstPropuestasCorrectas = validacionExcel.get(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE);
                getCargaMasivaPropuestaIncorrecta().addAll(lstPropuestasIncorrectas);
                getCargaMasivaPropuestaCorrecta().addAll(lstPropuestasCorrectas);
                cuentaRegistrosErroneos(getCargaMasivaPropuestaIncorrecta());
            }
        } catch (CargaRegistroXExcelException e1) {
            logger.error(e1.getMessage());
        } catch (IOException e1) {
            logger.error(e1.getMessage());
        }
    }

    private void cuentaRegistrosErroneos(List<CargaMasivaPropuestasDTO> lstRegistrosErrores) {

        HashSet<Integer> registrosIncorrectos = new HashSet<Integer>();

        for (CargaMasivaPropuestasDTO error : lstRegistrosErrores) {
            registrosIncorrectos.add(error.getRow());
        }

        getCargaMasivaAttributeHelper().setRegistrosErroneos(registrosIncorrectos.size());
    }

    public void validaPropuesta(FacesContext context, UIComponent component, Object value) {
        if (value.equals("")) {
            FacesMessage facesMessage = new FacesMessage("Error al cargar el archivo");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }

    public void cargaArchivoPropuestasMasivas(final FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (validaArchivoCargaPropuesta(file)) {
            this.archivoCarga = event.getFile();
            getCargaValidaBooleanHelper().setFileHabilitado(false);
            getCargaMasivaAttributeHelper().setNombreArchivo(file.getFileName());
            addMessage(null, Constantes.ARCHIVO_CARGADO, event.getFile().getFileName());
        }
    }

    public Boolean validaArchivoCargaPropuesta(final UploadedFile archivo) {
        boolean isValido = false;
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL)) {
            if (getHelper().validateSizeFile(archivo)) {
                getCargaValidaBooleanHelper().setCancelarHabilitado(false);
                isValido = true;
            }
        } else {
            addErrorMessage(null, "Archivo inv\u00e1lido", "Solo se aceptan archivos xls");
        }
        return isValido;
    }

    public void cancelar() {
        getCargaValidaBooleanHelper().setCancelarHabilitado(true);
        getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
        getCargaValidaBooleanHelper().setPanelVisibleCorrectos(false);
        getCargaValidaBooleanHelper().setPanelVisibleErroneos(false);
        getCargaValidaBooleanHelper().setFileHabilitado(true);
        getCargaMasivaAttributeHelper().setFolioCargaDoc(null);
        archivoCarga = null;
        getCargaValidaBooleanHelper().setBotonRegresarVisible(false);
        getCargaValidaBooleanHelper().setPanelConfirmacionVisible(false);
        getCargaValidaBooleanHelper().setAccordionPanelVisible(false);
        getCargaMasivaAttributeHelper().setIndiceActivoInicial("");
        getCargaValidaBooleanHelper().setProcesadoExito(true);
    }

    public void obtenerXlss() {
        File file = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            logger.error("Creando archivo temporal");
            file = File.createTempFile("reportePropuestas", "xls");
            logger.error(
                    "Creaci�n de reporte, ruta del archivo Jasper: " + ARCHIVO_REPORTES + Constantes.ARCHIVO_ACUSE);
            JasperReport jasperReport = (JasperReport) JRLoader
                    .loadObjectFromFile(ARCHIVO_REPORTES + Constantes.ARCHIVO_ACUSE);
            Map<String, Object> map = new HashMap<String, Object>();

            logger.error("Introduciendo ruta de imagenes:");
            logger.error("Ruta de logo SHCP: " + RUTA_IMAGENES + Constantes.LOGO_SHCP_JPG);
            logger.error("Ruta de logo SAT: " + RUTA_IMAGENES + Constantes.LOGO_SAT_JPG);
            logger.error("Cargando datos del reporte");

            map.put("rfcCreacion", getRFCSession());
            map.put("logoSHCP", RUTA_IMAGENES + Constantes.LOGO_SHCP_JPG);
            map.put("logoSAT", RUTA_IMAGENES + Constantes.LOGO_SAT_JPG);
            map.put("fechaCreacion", df.format(getCargaMasivaAttributeHelper().getFechaActual()));
            map.put("folioCarga", getCargaMasivaAttributeHelper().getFolioResultado());
            map.put("totalRegistros", String.valueOf(getCargaMasivaAttributeHelper().getContadorCorrectos()));

            Collection<Map<String, ?>> lista = new ArrayList<Map<String, ?>>();
            for (CargaMasivaPropuestasDTO cargaMasivaPropuestas : cargaMasivaPropuestaCorrecta) {
                Map<String, String> mapList = new HashMap<String, String>();
                mapList.put("rfcContribuyente", cargaMasivaPropuestas.getRfcContribuyente());
                mapList.put("periodoInicio",
                        df.format(cargaMasivaPropuestas.getFecetPropuesta().getFechaInicioPeriodo()));
                mapList.put("periodoFin", df.format(cargaMasivaPropuestas.getFecetPropuesta().getFechaFinPeriodo()));
                mapList.put("presuntivaFormat", cargaMasivaPropuestas.getPresuntivaFormat());
                mapList.put("folio", cargaMasivaPropuestas.getFecetPropuesta().getIdRegistro());
                mapList.put("metodo", cargaMasivaPropuestas.getMetodoString());

                mapList.put("arace", "" + cargaMasivaPropuestas.getFecetPropuesta().getIdArace());
                mapList.put("subprograma", getCargaMasivaPropuestasService()
                        .getSubprogramaById(cargaMasivaPropuestas.getFecetPropuesta().getIdSubprograma()).getClave());
                mapList.put("observaciones", arrayToString(cargaMasivaPropuestas.getAdvertencias()));
                lista.add(mapList);
            }

            JasperPrint jasperPrint;
            JRMapCollectionDataSource jRMapCollectionDataSource = new JRMapCollectionDataSource(lista);
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, jRMapCollectionDataSource);

            JRXlsExporter xlsExporter = new JRXlsExporter();
            xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(file));
            xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
            xlsExporter.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
            xlsExporter.exportReport();

            setPdfPropuestasCorrectas(
                    new DefaultStreamedContent(new FileInputStream(file), "application/xls", "reporte.xls"));
            /**
             * abstractManagedBean.insetarPistaAuditoria(Constantes.
             * BITACORA_O_EXPORTACION_INFORMACION_EXITOSA, null,
             * getRFCSession(), "Se genero reporte de carga masiva de propuestas
             * (correcta) que genero el usuario: " + getRFCSession());*
             */
        } catch (IOException e) {
            logger.error(ERROR + e.getMessage(), e);
        } catch (JRException e) {
            logger.error(ERROR + e.getMessage(), e);
        } catch (Exception e) {
            logger.error(ERROR + e.getMessage(), e);
        }
    }

    public void obtenerPDF() {
        File file = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            logger.error("Creando archivo temporal");
            file = File.createTempFile("reportePropuestas", "pdf");
            logger.error(
                    "Creacion de reporte, ruta del archivo Jasper: " + ARCHIVO_REPORTES + Constantes.ARCHIVO_ACUSE);
            JasperReport jasperReport = (JasperReport) JRLoader
                    .loadObjectFromFile(ARCHIVO_REPORTES + Constantes.ARCHIVO_ACUSE);
            Map<String, Object> map = new HashMap<String, Object>();

            logger.error("Introduciendo ruta de imagenes:");
            logger.error("Ruta de logo SHCP: " + RUTA_IMAGENES + Constantes.LOGO_SHCP_JPG);
            logger.error("Ruta de logo SAT: " + RUTA_IMAGENES + Constantes.LOGO_SAT_JPG);
            logger.error("Cargando datos del reporte");

            map.put("rfcCreacion", getRFCSession());
            map.put("logoSHCP", RUTA_IMAGENES + Constantes.LOGO_SHCP_JPG);
            map.put("logoSAT", RUTA_IMAGENES + Constantes.LOGO_SAT_JPG);
            map.put("fechaCreacion", df.format(getCargaMasivaAttributeHelper().getFechaActual()));
            map.put("folioCarga", getCargaMasivaAttributeHelper().getFolioResultado());
            map.put("totalRegistros", String.valueOf(getCargaMasivaAttributeHelper().getContadorCorrectos()));

            Collection<Map<String, ?>> lista = new ArrayList<Map<String, ?>>();
            for (CargaMasivaPropuestasDTO cargaMasivaPropuestas : cargaMasivaPropuestaCorrecta) {
                Map<String, String> mapList = new HashMap<String, String>();
                mapList.put("rfcContribuyente", cargaMasivaPropuestas.getRfcContribuyente());
                mapList.put("periodoInicio",
                        df.format(cargaMasivaPropuestas.getFecetPropuesta().getFechaInicioPeriodo()));
                mapList.put("periodoFin", df.format(cargaMasivaPropuestas.getFecetPropuesta().getFechaFinPeriodo()));
                mapList.put("presuntivaFormat", cargaMasivaPropuestas.getPresuntivaFormat());
                mapList.put("folio", cargaMasivaPropuestas.getFecetPropuesta().getIdRegistro());
                mapList.put("metodo", cargaMasivaPropuestas.getMetodoString());

                mapList.put("arace", "" + cargaMasivaPropuestas.getFecetPropuesta().getIdArace());
                mapList.put("subprograma", getCargaMasivaPropuestasService()
                        .getSubprogramaById(cargaMasivaPropuestas.getFecetPropuesta().getIdSubprograma()).getClave());
                mapList.put("observaciones", arrayToString(cargaMasivaPropuestas.getAdvertencias()));

                lista.add(mapList);
            }
            JasperPrint jasperPrint;
            JRMapCollectionDataSource jRMapCollectionDataSource = new JRMapCollectionDataSource(lista);
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, jRMapCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(file));
            setPdfPropuestasCorrectas(
                    new DefaultStreamedContent(new FileInputStream(file), "application/pdf", "reporte.pdf"));
        } catch (IOException e) {
            logger.error(ERROR + e.getMessage(), e);
        } catch (JRException e) {
            logger.error(ERROR + e.getMessage(), e);
        } catch (Exception e) {
            logger.error(ERROR + e.getMessage(), e);
        }
    }

    public String arrayToString(List<String> cadenas) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iter = cadenas.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
            sb.append(", ");
        }

        String coincidencias = sb.toString().trim();

        if (coincidencias.length() > 0 || !coincidencias.isEmpty()) {
            coincidencias = coincidencias.replace(coincidencias.substring(coincidencias.length() - 1), ".");
        }

        return coincidencias;
    }

    public void onTabChange(TabChangeEvent event) {
        String indiceActivo = ((AccordionPanel) event.getComponent()).getActiveIndex();
        if (getCargaValidaBooleanHelper().isPanelVisibleCorrectos() && indiceActivo.equals("0")
                && getCargaValidaBooleanHelper().isProcesadoExito()) {
            getCargaValidaBooleanHelper().setPanelConfirmacionVisible(true);
        } else {
            getCargaValidaBooleanHelper().setPanelConfirmacionVisible(false);
        }
    }

    public StreamedContent getDownload() {
        String documentosDescargas = (String) MetodosGenericos.getParametro("documentosDescargas");
        StreamedContent sc = null;
        try {
            sc = getCommonServices().getDescargaFormatoCarga(Constantes.UBICACION_LAYOUT + documentosDescargas,
                    documentosDescargas);
        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
        return sc;
    }

    public CargaMasivaPropuestasMBHelper getHelper() {
        return helper;
    }

    public void setHelper(CargaMasivaPropuestasMBHelper helper) {
        this.helper = helper;
    }

    public void setCargaMasivaPropuestaCorrecta(List<CargaMasivaPropuestasDTO> cargaMasivaPropuestaCorrecta) {
        this.cargaMasivaPropuestaCorrecta = cargaMasivaPropuestaCorrecta;
    }

    public List<CargaMasivaPropuestasDTO> getCargaMasivaPropuestaCorrecta() {
        return cargaMasivaPropuestaCorrecta;
    }

    public void setPdfPropuestasCorrectas(StreamedContent pdfPropuestasCorrectas) {
        this.pdfPropuestasCorrectas = pdfPropuestasCorrectas;
    }

    public void setCargaMasivaPropuestasService(CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        this.cargaMasivaPropuestasService = cargaMasivaPropuestasService;
    }

    public CargaMasivaPropuestasService getCargaMasivaPropuestasService() {
        return cargaMasivaPropuestasService;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public void setCargaMasivaPropuestaIncorrecta(List<CargaMasivaPropuestasDTO> cargaMasivaPropuestaIncorrecta) {
        this.cargaMasivaPropuestaIncorrecta = cargaMasivaPropuestaIncorrecta;
    }

    public List<CargaMasivaPropuestasDTO> getCargaMasivaPropuestaIncorrecta() {
        return cargaMasivaPropuestaIncorrecta;
    }

    public CargaMasivaPropuestasDTO getCargaMasivaPropuestasDTO() {
        return cargaMasivaPropuestasDTO;
    }

    public void setCargaMasivaPropuestasDTO(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        this.cargaMasivaPropuestasDTO = cargaMasivaPropuestasDTO;
    }

    public StreamedContent getPdfPropuestasCorrectas() {
        return pdfPropuestasCorrectas;
    }

    public CargaValidaBooleanHelper getCargaValidaBooleanHelper() {
        return cargaValidaBooleanHelper;
    }

    public void setCargaValidaBooleanHelper(CargaValidaBooleanHelper cargaValidaBooleanHelper) {
        this.cargaValidaBooleanHelper = cargaValidaBooleanHelper;
    }

    public CargaMasivaAttributeHelper getCargaMasivaAttributeHelper() {
        return cargaMasivaAttributeHelper;
    }

    public void setCargaMasivaAttributeHelper(CargaMasivaAttributeHelper cargaMasivaAttributeHelper) {
        this.cargaMasivaAttributeHelper = cargaMasivaAttributeHelper;
    }

    public EmpleadoDTO getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public void setEmpleadoLogueado(EmpleadoDTO empleadoLogueado) {
        this.empleadoLogueado = empleadoLogueado;
    }

    public CargaMasivaXExcelMCAService getCargaMasivaXExcelMCAService() {
        return cargaMasivaXExcelMCAService;
    }

    public void setCargaMasivaXExcelMCAService(CargaMasivaXExcelMCAService cargaMasivaXExcelMCAService) {
        this.cargaMasivaXExcelMCAService = cargaMasivaXExcelMCAService;
    }

    public void mantenerSesion() {
        logger.info("El componente hace llamadas ajax periódicamente para proceso de carga.");
    }
}
