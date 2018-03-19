package mx.gob.sat.siat.feagace.vista.propuestas.carga.managedbean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentacionMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentacionMasivaService;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.CargaDocumentacionMasivaHelper;

@ManagedBean(name = "cargaDocumentacionMasivaManagedBean")
@ViewScoped
public class CargaDocumentacionMasivaManagedBean extends AbstractManagedBean {

    private static final Logger LOGGER = Logger.getLogger(CargaDocumentacionMasivaManagedBean.class);
    @SuppressWarnings("compatibility:8289753959956738752")
    private static final long serialVersionUID = 1L;
    private CargaDocumentacionMasivaHelper cargaDocHelper;

    private boolean fileHabilitado;
    private boolean panelVisible;
    private boolean guardarHabilitado;
    private String archivosCargados;
    private String folio;
    private int row;

    private final String maxDocsStr = Propiedades.get("max.documentos");

    @ManagedProperty(value = "#{cargaDocumentacionMasivaService}")
    private transient CargaDocumentacionMasivaService cargaDocumentacionMasivaService;

    @ManagedProperty(value = "#{auditoriaPropuestas}")
    private transient PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService;

    public CargaDocumentacionMasivaManagedBean() {
        super();
    }

    @PostConstruct
    public void init() {

        folio = "";
        panelVisible = false;
        guardarHabilitado = true;
        cargaDocHelper = new CargaDocumentacionMasivaHelper();
        cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
        cargaDocHelper.setDocumentosIncorrectos(new ArrayList<String>());
        row = 0;
    }

    public void cancelar() {
        folio = "";
        guardarHabilitado = true;
        panelVisible = false;
        fileHabilitado = false;
        cargaDocHelper.setCargaDocumentacionMasivaDtoList(new ArrayList<CargaDocumentacionMasivaDTO>());
        cargaDocHelper.setDocumentosIncorrectos(new ArrayList<String>());
        cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
        row = 0;
        archivosCargados = null;
    }

    private boolean validaDuplicidad(UploadedFile uFile) {
        String nombreArchivo = uFile.getFileName();
        boolean esCorrecto = true;
        if (!cargaDocHelper.getUploadedFiles().isEmpty()) {
            for (UploadedFile archivo : cargaDocHelper.getUploadedFiles()) {
                if (nombreArchivo.equalsIgnoreCase(archivo.getFileName())) {
                    addErrorMessage(null, "Archivo inv\u00e1lido",
                            "El archivo ya ha sido agregado con anterioridad");
                    esCorrecto = false;
                }
            }
        }
        return esCorrecto;
    }

    public void upload(FileUploadEvent event) {
        folio = "";
        setPanelVisible(false);
        String nombreArchivo = event.getFile().getFileName();
        if (null != cargaDocHelper.getUploadedFiles()) {
            if (super.validaArchivoCargaInsumoPropuesta(event.getFile()) && validaDuplicidad(event.getFile())) {
                archivosCargados = archivosCargados == null ? nombreArchivo : archivosCargados
                        + ", " + nombreArchivo;
                cargaDocHelper.getUploadedFiles().add(event.getFile());
                addMessage(null, Constantes.ARCHIVO_CARGADO, archivosCargados);
                setGuardarHabilitado(false);

            } else {
                cargaDocHelper.getDocumentosIncorrectos().add(nombreArchivo);
            }
        } else {
            addErrorMessage(null, Constantes.NUMERO_MAXIMO_DOC, maxDocsStr);
            guardarHabilitado = true;
            cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
        }
    }

    public void cargaDocumentos() {
        setPanelVisible(false);
        if (null != cargaDocHelper.getUploadedFiles() && !cargaDocHelper.getUploadedFiles().isEmpty()) {
            cargaDocHelper.setCargaDocumentacionMasivaDtoList(new ArrayList<CargaDocumentacionMasivaDTO>());

            folio = getCargaDocumentacionMasivaService().getConsecutivo();
            setFolio(folio);
            for (UploadedFile archivoCargado : cargaDocHelper.getUploadedFiles()) {
                cargaDocHelper.setCargaDocumentacionMasivaDto(new CargaDocumentacionMasivaDTO());
                try {
                    String nombreArchivo = CargaPropuestasArchivoUtil.limpiarPathArchivo(archivoCargado.getFileName());
                    String destino = Constantes.DIRECTORIO_CARGA_DOCUMENTOS + folio + "/";
                    cargaDocHelper.getCargaDocumentacionMasivaDto().setRutaDocumento(Constantes.DIRECTORIO_CARGA_DOCUMENTOS);
                    cargaDocHelper.getCargaDocumentacionMasivaDto().setFolioDocumento(folio);
                    cargaDocHelper.getCargaDocumentacionMasivaDto().setNombreDocumento(nombreArchivo);
                    cargaDocHelper.getCargaDocumentacionMasivaDto().setEstatusCarga("OK");
                    cargaDocHelper.getCargaDocumentacionMasivaDto().setRow(++row);

                    File fileToDirectory = new File(destino);
                    boolean check = fileToDirectory.mkdirs();
                    if (check) {
                        LOGGER.info("MKDIRS OK");
                    }
                    if (!fileToDirectory.exists()) {
                        check = fileToDirectory.mkdirs();
                        logger.debug("Resultado de crear directorios : ", check);
                    }
                    InputStream is = archivoCargado.getInputstream();

                    getCargaDocumentacionMasivaService().cargaDocumento(destino, is, nombreArchivo);

                    cargaDocHelper.getCargaDocumentacionMasivaDtoList().add(cargaDocHelper.getCargaDocumentacionMasivaDto());
                } catch (IOException e) {
                    addErrorMessage(null, ConstantesError.ERROR_ESCRITURA_DOC);
                    LOGGER.error(ConstantesError.ERROR_ESCRITURA_DOC + e.getCause(), e);
                } catch (NegocioException e) {
                    addErrorMessage(null, ConstantesError.ERROR_GUARDAR);
                }
            }
            cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
            pistasAuditoriasPropuestasService.pistaCargarDocumentoMasivo(cargaDocHelper.getCargaDocumentacionMasivaDto().getFolioDocumento());
            addMessage(null, Constantes.CARGA_EXITOSA,
                    Constantes.MENSAJE_FOLIO.concat(cargaDocHelper.getCargaDocumentacionMasivaDto().getFolioDocumento()));
            setPanelVisible(true);
            setGuardarHabilitado(true);
            row = 0;

        } else {
            this.cancelar();
            addErrorMessage(null, ConstantesError.ERROR_GUARDAR);
            folio = "";
        }
    }

    public void setPanelVisible(boolean panelVisible) {
        this.panelVisible = panelVisible;
    }

    public boolean isPanelVisible() {
        return panelVisible;
    }

    public void setFileHabilitado(boolean fileHabilitado) {
        this.fileHabilitado = fileHabilitado;
    }

    public boolean isFileHabilitado() {
        return fileHabilitado;
    }

    public void setCargaDocumentacionMasivaService(CargaDocumentacionMasivaService cargaDocumentacionMasivaService) {
        this.cargaDocumentacionMasivaService = cargaDocumentacionMasivaService;
    }

    public CargaDocumentacionMasivaService getCargaDocumentacionMasivaService() {
        return cargaDocumentacionMasivaService;
    }

    public void setGuardarHabilitado(boolean guardarHabilitado) {
        this.guardarHabilitado = guardarHabilitado;
    }

    public boolean isGuardarHabilitado() {
        return guardarHabilitado;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFolio() {
        return folio;
    }

    public CargaDocumentacionMasivaHelper getCargaDocHelper() {
        return cargaDocHelper;
    }

    public void setCargaDocHelper(CargaDocumentacionMasivaHelper cargaDocHelper) {
        this.cargaDocHelper = cargaDocHelper;
    }

    public PistasAuditoriasPropuestasService getPistasAuditoriasPropuestasService() {
        return pistasAuditoriasPropuestasService;
    }

    public void setPistasAuditoriasPropuestasService(PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService) {
        this.pistasAuditoriasPropuestasService = pistasAuditoriasPropuestasService;
    }

}
