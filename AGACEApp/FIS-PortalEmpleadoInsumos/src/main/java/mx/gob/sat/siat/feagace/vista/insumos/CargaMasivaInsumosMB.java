/**
 *
 */
package mx.gob.sat.siat.feagace.vista.insumos;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;


import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.correo.model.MailMessageAttachment;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FoliosProcesadosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ReporteIncorrectoDto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.CommonServices;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.insumos.CargaMasivaInsumosService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.CargaValidaBooleanHelper;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.CargaMasivaInsumosMBHelper;
import mx.gob.sat.siat.feagace.vista.model.insumos.CargaMasivaInsumosDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * @author sergio.vaca
 *
 */
@ManagedBean(name = "cargaMasivaInsumosMB")
@ViewScoped
public class CargaMasivaInsumosMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    
    private static final String MSG_ERR_PERFIL = "No se es un perfil valido para este flujo";

    private static final String FOLIO_CARGA = "Folio de carga";
    private static final String FOLIO_CARGA_ERROR = "El folio que ingreso no es el correcto o no existe, verifique su informaci\u00f3n";
    private static final String ARCHIVO = "Archivo";
    private static final String ARCHIVO_ERROR = "No se adjunt\u00f3 el archivo formato de carga correctamente, favor de verificar.";
    private static final int CARGA_MASIVA_INSUMOS = 0;
    private static final String ERROR_AL_GENERAR_REPORTE = "Error al generar reporte";
    private static final String TABLES_RESET = "cargaMasivaInsumosForm:arcodeon:tablaInsumosResumen|cargaMasivaInsumosForm:arcodeon:tablaInsumosSinAdministrador|cargaMasivaInsumosForm:arcodeon:tablaInsumosError";

    @ManagedProperty(value = "#{cargaMasivaInsumosService}")
    private transient CargaMasivaInsumosService cargaMasivaInsumosService;
    @ManagedProperty(value = "#{cargaMasivaInsumosMBHelper}")
    private transient CargaMasivaInsumosMBHelper cargaMasivaInsumosMBHelper;
    @ManagedProperty(value = "#{commonServices}")
    private transient CommonServices commonServices;

    private EmpleadoDTO empleadoSession;
    private CargaMasivaInsumosDTO cargaMasiva;
    private CargaValidaBooleanHelper cargaValidaBooleanHelper;
    public CargaMasivaInsumosMB() {
        super();
    }

    @PostConstruct
    public void init() {
        try {
            getUsuarioACIACE(getRFCSession(), TipoEmpleadoEnum.USUARIO_INSUMOS);
            setCargaValidaBooleanHelper(new CargaValidaBooleanHelper());
            setCargaMasiva(new CargaMasivaInsumosDTO());            
            getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
            getCargaValidaBooleanHelper().setCancelarHabilitado(true);
            getCargaValidaBooleanHelper().setFileHabilitado(true);
            getCargaValidaBooleanHelper().setEventoActivo(true);
        } catch (NegocioException e) {
            logger.error("No se encontro informacion del empleado", e);
            informeErrorSession(e);
        }
    }

    public StreamedContent getLayout() {
        String documentosDescargas = (String) MetodosGenericos.getParametro("documentosDescargas");
        StreamedContent archivo = null;
        try {
            archivo = getCommonServices().getDescargaFormatoCarga(Constantes.UBICACION_LAYOUT.concat(documentosDescargas), documentosDescargas);
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(null, e.getMessage(), "");
        }
        return archivo;
    }

    public void cargaArchivoInsumosMasivos(final FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (validaArchivoCargaInsumos(file)) {
            getCargaMasiva().setArchivoCarga(event.getFile());
            getCargaValidaBooleanHelper().setFileHabilitado(false);
            getCargaMasiva().setNombreArchivo(file.getFileName());
            FacesUtil.addInfoMessage(null, Constantes.ARCHIVO_CARGADO, event.getFile().getFileName());
        }
    }

    public void validarArchivoInsumos() {        
        getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
        getCargaValidaBooleanHelper().setCancelarHabilitado(true);
        getCargaMasiva().setListRegistrosCorrectos(new ArrayList<FecetInsumo>());
        getCargaMasiva().setListRegistrosIncorrectos(new ArrayList<FecetInsumo>());        
        getCargaMasiva().setMapReporte(new HashMap<Integer, List<ReporteIncorrectoDto>>());
        getCargaMasiva().getMapReporte().put(Constantes.ENTERO_UNO, new ArrayList<ReporteIncorrectoDto>());
        getCargaMasiva().getMapReporte().put(Constantes.ENTERO_DOS, new ArrayList<ReporteIncorrectoDto>());
        if(getCargaMasiva().getTipoFieldset()!=null && !getCargaMasiva().getTipoFieldset().isEmpty()){
            RequestContext context = RequestContext.getCurrentInstance();
             for(String key :getCargaMasiva().getTipoFieldset()){
                    StringBuilder idFielset = new StringBuilder();
                    idFielset.append("PF('").append(key).append("').toggle();");
                    context.execute(idFielset.toString());
                    
            }
            getCargaMasiva().setTipoFieldset(null); 
        }
        
        if (getCargaMasiva().getArchivoCarga() == null) {
            FacesUtil.addErrorMessage(null, ARCHIVO, ARCHIVO_ERROR);
        } else if (getCargaMasiva().getFolioCargaDoc().isEmpty()) {
            FacesUtil.addErrorMessage(null, FOLIO_CARGA, FOLIO_CARGA_ERROR);
        } else if (!getCargaMasivaInsumosMBHelper().validaExistenciaFolioDoc(getCargaMasiva().getFolioCargaDoc())) {
            logger.error("Folio de Carga de Documentaci\u00F3n no encontrado");
            FacesUtil.addErrorMessage(null, "Folio Carga Documentaci\u00F3n Masiva", FOLIO_CARGA_ERROR);
        } else if (getCargaMasiva().getArchivoCarga().getFileName().endsWith(Constantes.ARCHIVO_EXCEL)) {
            try {
                String rutaDocumetos = getCargaMasivaInsumosMBHelper().obtenerRutaDocumentos(getCargaMasiva().getFolioCargaDoc());
                getCargaMasivaInsumosService().validarRegistros(getCargaMasiva().getArchivoCarga(), getCargaMasiva().getListRegistrosCorrectos(), getCargaMasiva().getListRegistrosIncorrectos(), getCargaMasiva().getMapReporte(),empleadoSession, rutaDocumetos);
            } catch (NegocioException e) {
                String[] msgs = e.getMessage().split("-");
                FacesUtil.addErrorMessage(null, msgs[0], msgs[1]);
            }
            getCargaMasiva().setFechaActual(new Date());
            ResumenCargaMasivaDTO resumen = new ResumenCargaMasivaDTO();
            resumen.setFechaCarga(getCargaMasiva().getFechaActual());
            resumen.setIdOrigen(CARGA_MASIVA_INSUMOS);
            getCargaMasiva().setResumen(new ArrayList<ResumenCargaMasivaDTO>());
            getCargaMasiva().getResumen().add(resumen);
            getCargaMasiva().setRegistrosCorrectos(getCargaMasiva().getListRegistrosCorrectos().size());
            getCargaMasiva().setRegistrosErroneos(getCargaMasiva().getListRegistrosIncorrectos().size());
            getCargaValidaBooleanHelper().setCancelarHabilitado(false);
            getCargaValidaBooleanHelper().setValidaInformacionHabilitado(false);
            getCargaValidaBooleanHelper().setProcesaInformacionHabilitado(false);
            getCargaValidaBooleanHelper().setExportarErrorHabilitado(false);
            getCargaValidaBooleanHelper().setExportarExitoHabilitado(true);
            getCargaValidaBooleanHelper().setBotonRegresarVisible(true);
            getCargaValidaBooleanHelper().setPanelVisibleCorrectos(getCargaMasiva().getRegistrosCorrectos() > 0);
            getCargaValidaBooleanHelper().setPanelVisibleErroneos(getCargaMasiva().getRegistrosErroneos() > 0);
            getCargaValidaBooleanHelper().setAccordionPanelVisible(true);
            getCargaMasiva().setIndiceActivoInicial("");
        }
    }

    public void cancelar() {
        getCargaValidaBooleanHelper().setCancelarHabilitado(true);
        getCargaValidaBooleanHelper().setValidaInformacionHabilitado(true);
        getCargaValidaBooleanHelper().setPanelVisibleCorrectos(false);
        getCargaValidaBooleanHelper().setPanelVisibleErroneos(false);
        getCargaValidaBooleanHelper().setFileHabilitado(true);
        getCargaMasiva().setFolioCargaDoc(null);
        getCargaMasiva().setArchivoCarga(null);
        getCargaMasiva().setFolioResultado(null);
        getCargaMasiva().setContadorCorrectos(0);
        getCargaMasiva().setContadorRegistrosSeleccionados(0);
        getCargaMasiva().setInsumoSeleccionado(null);
        getCargaMasiva().setListRegistrosCargados(null);
        getCargaMasiva().setHabilitarEliminar(false);
        getCargaValidaBooleanHelper().setBotonRegresarVisible(false);
        getCargaValidaBooleanHelper().setPanelConfirmacionVisible(false);
        getCargaValidaBooleanHelper().setAccordionPanelVisible(false);
        getCargaMasiva().setIndiceActivoInicial("");
        getCargaValidaBooleanHelper().setEventoActivo(true);
        for (String table : TABLES_RESET.split("\\|")) {
            limpiaFiltros(table);
        }
    }


    public void onTabChange(ToggleEvent event) {

    }

    public void realizarCarga() {
        RegistroInsumosDto resultadoCarga = getCargaMasivaInsumosService().insertarRegistrosMasivos(getCargaMasiva().getListRegistrosCargados());
        getCargaValidaBooleanHelper().setEventoActivo(false);
        getCargaValidaBooleanHelper().setPanelConfirmacionVisible(false);
        getCargaMasiva().setContadorCorrectos(resultadoCarga.getInsumosRegistrados().size());
        getCargaMasiva().getResumen().get(0).setTotalRegistros(resultadoCarga.getInsumosRegistrados().size());
        getCargaMasiva().setFolioResultado(getCargaMasivaInsumosService().insertaResumen(getCargaMasiva().getResumen().get(0)));
        getCargaMasiva().setRegistroInsumosDto(resultadoCarga);
        enviarCorreoCreacionInsumoAdministrador(resultadoCarga);
        enviarCorreoCreacionInsumoCentral(resultadoCarga);
        addMessage("Se cargaron los insumos exitosamente.");        
    }

    public StreamedContent getReporteCarga() {
        StreamedContent reporte = null;
        try {
            if (getCargaMasiva().getRegistroInsumosDto() != null && getCargaMasiva().getRegistroInsumosDto().getInsumosRegistrados() != null && !getCargaMasiva().getRegistroInsumosDto().getInsumosRegistrados().isEmpty()) {
                byte[] reporteByte = cargaMasivaInsumosService.generarReporte(getRFCSession(), getCargaMasiva().getFechaActual(),
                        getCargaMasiva().getFolioResultado(), getCargaMasiva().getRegistroInsumosDto().getInsumosRegistrados());
                reporte = new DefaultStreamedContent(new ByteArrayInputStream(reporteByte), "application/vnd.ms-excel.12", "cargaMasiva.xlsx");
            } else {
                FacesUtil.addErrorMessage(null, ERROR_AL_GENERAR_REPORTE, "No existen registros para realizar el reporte");
            }
        } catch (NoSeGeneroReporteException exception) {
            logger.error("Error al generar el reporte", exception);
            FacesUtil.addErrorMessage(null, "Error al generar el reporte");
        }
        return reporte;
    }

    private void enviarCorreoCreacionInsumoCentral(final RegistroInsumosDto registroInsumosDto) {
        if (registroInsumosDto.getInsumosRegistrados() != null && !registroInsumosDto.getInsumosRegistrados().isEmpty()) {
            Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_ASIGNADOS_CENTRAL);
            Set<String> destinatariosOriginal = new TreeSet<String>();
            Set<String> destinatariosAdminCentral = new TreeSet<String>();
            List<FecetInsumo> registrosAceptados = new ArrayList<FecetInsumo>();
            MailMessageAttachment reporte = null;
            byte[] reporteByte = null;
            Map<BigDecimal, FoliosProcesadosDto> folios = registroInsumosDto.getFolios();
            registrosAceptados.addAll(registroInsumosDto.getInsumosRegistrados());
            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatariosOriginal, ClvSubModulosAgace.INSUMOS);
            for (Entry<BigDecimal, FoliosProcesadosDto> folioLlave : folios.entrySet()) {
                destinatariosAdminCentral.clear();
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, folioLlave.getKey(), destinatariosAdminCentral, ClvSubModulosAgace.INSUMOS);
                destinatariosAdminCentral.addAll(destinatariosOriginal);
                try {
                    reporteByte = cargaMasivaInsumosService.generarReporte(getRFCSession(), getCargaMasiva().getFechaActual(),
                            getCargaMasiva().getFolioResultado(), obtenerRegistrosCorreoAdmin(registrosAceptados, folioLlave.getValue().getFolios()));
                    reporte = new MailMessageAttachment("resumenCargaMasiva.xlsx", "application/vnd.ms-excel.12", reporteByte);
                    getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_INSUMO_MASIVO, destinatariosAdminCentral, reporte);
                } catch (DocumentoException e) {
                    logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause());
                } catch (Exception e) {
                    logger.error("Error al enviar el correo [{}]", e.getCause(), e);
                }
            }
        }
    }

    private void enviarCorreoCreacionInsumoAdministrador(final RegistroInsumosDto registroInsumosDto) {
        if (registroInsumosDto.getInsumosRegistrados() != null && !registroInsumosDto.getInsumosRegistrados().isEmpty()) {
            Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_ASIGNADOS_ADMIN);
            Set<String> destinatarios = new TreeSet<String>();
            List<FecetInsumo> registrosAceptados = new ArrayList<FecetInsumo>();
            Map<BigDecimal, FoliosProcesadosDto> folios = registroInsumosDto.getFolios();
            MailMessageAttachment reporte = null;
            byte[] reporteByte = null;
            String rfcs[];
            registrosAceptados.addAll(registroInsumosDto.getInsumosRegistrados());
            for (Entry<BigDecimal, FoliosProcesadosDto> folioLlave : folios.entrySet()) {
                for (Entry<String, List<String>> entry : folioLlave.getValue().getFoliosAdministrador().entrySet()) {
                    destinatarios.clear();
                    rfcs = entry.getKey().split("-");
                    getNotificacionService().obtenerCorreoEmpleado(rfcs[0], Constantes.USUARIO_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(rfcs[1], Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
                    getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
                    try {
                        reporteByte = cargaMasivaInsumosService.generarReporte(getRFCSession(), getCargaMasiva().getFechaActual(),
                                getCargaMasiva().getFolioResultado(), obtenerRegistrosCorreoAdmin(registrosAceptados, entry.getValue()));
                        reporte = new MailMessageAttachment("resumenCargaMasiva.xlsx", "application/vnd.ms-excel.12", reporteByte);
                        getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_INSUMO_MASIVO, destinatarios, reporte);
                    } catch (DocumentoException e) {
                        logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause());
                    } catch (Exception e) {
                        logger.error("Error al enviar el correo [{}]", e.getCause(), e);
                    }
                }
            }
        }
    }

    private List<FecetInsumo> obtenerRegistrosCorreoAdmin(List<FecetInsumo> insumosAceptados, List<String> folios) {
        List<FecetInsumo> registros = new ArrayList<FecetInsumo>();
        Iterator<FecetInsumo> insumoIterator = insumosAceptados.iterator();
        FecetInsumo insumoAux;
        while (insumoIterator.hasNext()) {
            insumoAux = insumoIterator.next();
            if (folios.contains(insumoAux.getIdRegistro())) {
                insumoIterator.remove();
                registros.add(insumoAux);
            }
        }
        return registros;
    }

    private boolean validaArchivoCargaInsumos(final UploadedFile archivo) {
        boolean isValido = false;
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL)) {
            if (getCargaMasivaInsumosMBHelper().validateSizeFile(archivo)) {
                getCargaValidaBooleanHelper().setCancelarHabilitado(false);
                isValido = true;
            }
        } else {
            FacesUtil.addErrorMessage(null, "Archivo inv\u00e1lido", "Solo se aceptan archivos xls");
        }
        return isValido;
    }

    private void getUsuarioACIACE(final String rfc, final TipoEmpleadoEnum tipoEmpleado) throws NegocioException {
        try {
            setEmpleadoSession(obtenerEmpleadoAcceso(rfc, tipoEmpleado));
        } catch (NoExisteEmpleadoException e) {
            if(e.getMessage().equals(MSG_ERR_PERFIL)){
                throw new NegocioException(MSG_ERR_PERFIL, e);            
            }            
            logger.error("[{}]", e);
            throw new NegocioException("No se encuentra el perfil del empleado", e);
        }
    }

    public String getRegistrosErroneos() {

        if (cargaMasiva != null && cargaMasiva.getListRegistrosIncorrectos() != null && !cargaMasiva.getListRegistrosIncorrectos().isEmpty()) {
            int row = 0;
            int rowTmp = 0;
            int count = 0;
            
            for (FecetInsumo insumo : cargaMasiva.getListRegistrosIncorrectos()) {
                row=insumo.getRow();
                if(row!=rowTmp){
                    ++count;
                    rowTmp = row;
                }
            }
            return String.valueOf(count);
        }
        return "0";
    }
    
    public StreamedContent getXlsReporteIncorrectos(){
        String nombreExcel;
        nombreExcel = "ReporteExcel.xls";        
        byte[] reporte;            
        try {            
            reporte =cargaMasivaInsumosService.generarReporteExcel("/siat/fece/configuracion/reportes/insumos/ReporteRegistrosIncorrectosExcel.jasper", nombreExcel, getCargaMasiva().getMapReporte().get(Constantes.ENTERO_DOS));                         
            InputStream myInputStream = new ByteArrayInputStream(reporte);
            return new DefaultStreamedContent(myInputStream, "application/xls", "ReporteRegistrosIncorrectos.xls");            
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaInsumosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    public StreamedContent getXlsReporteCorrectos(){
        String nombreExcel;
        nombreExcel = "ReporteExcel.xls";        
        byte[] reporte;
        try {
            reporte =cargaMasivaInsumosService.generarReporteExcel("/siat/fece/configuracion/reportes/insumos/ReporteRegistrosCorrectosExcel.jasper", nombreExcel, getCargaMasiva().getMapReporte().get(Constantes.ENTERO_UNO));
            InputStream myInputStream = new ByteArrayInputStream(reporte);
            return new DefaultStreamedContent(myInputStream, "application/xls", "ReporteRegistrosCorrectos.xls");                        
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaInsumosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void elimiarRegistrosSeleccionados(){
        if( getCargaMasiva().getListRegistrosCargados()!= null && !getCargaMasiva().getListRegistrosCargados().isEmpty()){
            for(FecetInsumo insumoCargado :getCargaMasiva().getListRegistrosCargados()){
                for(FecetInsumo insumoCorrecto :getCargaMasiva().getListRegistrosCorrectos()){
                    if(insumoCargado.getNumeroRegistro() == insumoCorrecto.getNumeroRegistro()){
                        getCargaMasiva().getListRegistrosCorrectos().remove(insumoCorrecto);
                        getCargaMasiva().setRegistrosCorrectos(getCargaMasiva().getRegistrosCorrectos()-1);
                        getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getContadorRegistrosSeleccionados()-1);
                        break;
                    }
                }
            }
        }
        getCargaMasiva().getContadorRegistrosSeleccionados();
        getCargaMasiva().setListRegistrosCargados(new ArrayList<FecetInsumo>());
        if(getCargaMasiva().getContadorRegistrosSeleccionados()==0){
            getCargaMasiva().setHabilitarEliminar(false);
        }
    }
    
    public void registrarRegistrosSeleccionados(){
        RequestContext.getCurrentInstance().execute("PF('dialogoConfirmacion').show();");        
    }
    
    public void contarRegistroSeleccionado(SelectEvent event) {
        getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getListRegistrosCargados().size());
        getCargaMasiva().setHabilitarEliminar(true);
    }
    
    public void contarRegistroSinSeleccionado(UnselectEvent event) {
        getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getListRegistrosCargados().size());
        if(getCargaMasiva().getContadorRegistrosSeleccionados()>0){
            getCargaMasiva().setHabilitarEliminar(true);
        }else{
            getCargaMasiva().setHabilitarEliminar(false);
        } 
    }
    
    public void contarTodosRegistro(ToggleSelectEvent event) {
        if(getCargaMasiva().getListRegistrosCargados().isEmpty()){
            getCargaMasiva().setContadorRegistrosSeleccionados(0);
            getCargaMasiva().setHabilitarEliminar(false);
        }else{
            getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getListRegistrosCargados().size());
            getCargaMasiva().setHabilitarEliminar(true);
        }        
    }
    
    public void onRowSelect(SelectEvent event) {
        getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getListRegistrosCargados().size());
        getCargaMasiva().setHabilitarEliminar(true);        
    }
    
    public void onRowUnselect(UnselectEvent event) {
        getCargaMasiva().setContadorRegistrosSeleccionados(getCargaMasiva().getListRegistrosCargados().size());
        if(getCargaMasiva().getContadorRegistrosSeleccionados()>0){
            getCargaMasiva().setHabilitarEliminar(true);
        }else{
            getCargaMasiva().setHabilitarEliminar(false);
        } 
    }
    
    public void cargaDocumentosFolio() {        
        RequestContext.getCurrentInstance().execute("PF('dialogoDocumentacionCarga').show();");
    }
    
    public StreamedContent getDescargaArchivo(){
        return getDescargaArchivo(getCargaMasiva().getRutaArchivoDescargable(), getCargaMasiva().getNombreArchivoDescargable());
    }
    
    public void handleToggle(ToggleEvent event) {        
        UIComponent tog = event.getComponent();
        if(getCargaMasiva().getTipoFieldset()==null){
            getCargaMasiva().setTipoFieldset(new ArrayList<String>());
        }
        if (event.getVisibility().toString().equals("VISIBLE")) {
            getCargaMasiva().getTipoFieldset().add(tog.getId());            
        }else{
            getCargaMasiva().getTipoFieldset().remove(tog.getId());
        }
    }
    
    
    public final CargaMasivaInsumosService getCargaMasivaInsumosService() {
        return cargaMasivaInsumosService;
    }

    public final void setCargaMasivaInsumosService(CargaMasivaInsumosService cargaMasivaInsumosService) {
        this.cargaMasivaInsumosService = cargaMasivaInsumosService;
    }

    public final EmpleadoDTO getEmpleadoSession() {
        return empleadoSession;
    }

    public final void setEmpleadoSession(EmpleadoDTO empleadoSession) {
        this.empleadoSession = empleadoSession;
    }

    public final CargaMasivaInsumosDTO getCargaMasiva() {
        return cargaMasiva;
    }

    public final void setCargaMasiva(CargaMasivaInsumosDTO cargaMasiva) {
        this.cargaMasiva = cargaMasiva;
    }

    public final CargaValidaBooleanHelper getCargaValidaBooleanHelper() {
        return cargaValidaBooleanHelper;
    }

    public final void setCargaValidaBooleanHelper(CargaValidaBooleanHelper cargaValidaBooleanHelper) {
        this.cargaValidaBooleanHelper = cargaValidaBooleanHelper;
    }

    public final CargaMasivaInsumosMBHelper getCargaMasivaInsumosMBHelper() {
        return cargaMasivaInsumosMBHelper;
    }

    public final void setCargaMasivaInsumosMBHelper(CargaMasivaInsumosMBHelper cargaMasivaInsumosMBHelper) {
        this.cargaMasivaInsumosMBHelper = cargaMasivaInsumosMBHelper;
    }

    public final CommonServices getCommonServices() {
        return commonServices;
    }

    public final void setCommonServices(CommonServices commonServices) {
        this.commonServices = commonServices;
    }
    
    public void mantenerSesion() {
        logger.info("El componente hace llamadas ajax periódicamente para proceso de carga.");
    }
    
}
