/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.insumos;

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
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.insumos.CargaMasivaInsumosService;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.CargaDocumentacionMasivaHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 * @author sergio.vaca
 *
 */
@ManagedBean(name = "cargaDoctoMasivaInsumosMB")
@ViewScoped
public class CargaDoctoMasivaInsumosMB extends AbstractManagedBean {
    private static final Logger LOGGER = Logger.getLogger(CargaDoctoMasivaInsumosMB.class);

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{cargaMasivaInsumosService}")
    private transient CargaMasivaInsumosService cargaMasivaInsumosService;

    private transient CargaDocumentacionMasivaHelper cargaDocHelper;

    private boolean fileHabilitado;
    private boolean panelVisible;
    private boolean guardarHabilitado;
    private boolean muestraPreviaCarga;
    private int row;
    private String archivosCargados;
    private String folio;
    private final String maxDocsStr = Propiedades.get("max.documentos");

    public CargaDoctoMasivaInsumosMB() {
        super();
    }
    
    @PostConstruct
    public void init() {
        panelVisible = false;
        guardarHabilitado = true;
        muestraPreviaCarga = false;
        row = 0;
        folio = "";
        cargaDocHelper = new CargaDocumentacionMasivaHelper();
        cargaDocHelper.setDocumentosIncorrectos(new ArrayList<String>());
        inicializaDocumentos();
    }

    public void cancelar() {
        panelVisible = false;
        fileHabilitado = false;
        guardarHabilitado = true;
        muestraPreviaCarga = false;
        folio = "";
        row = 0;
        archivosCargados = null;
        cargaDocHelper.setCargaDocumentacionMasivaDtoList(new ArrayList<CargaDocumentacionMasivaDTO>());
        cargaDocHelper.setDocumentosIncorrectos(new ArrayList<String>());
        inicializaDocumentos();
    }

    public void upload(FileUploadEvent event) {
        folio = "";
        setPanelVisible(false);
        String nombreArchivo = event.getFile().getFileName();
        if (null != cargaDocHelper.getUploadedFiles()) {
            if (!validaFormatoArchivoCargaMasiva(event.getFile())) {
                cargaDocHelper.getDocumentosFormato().add(nombreArchivo);
            } else if (!validaTamanioArchivo(event.getFile())) {
                cargaDocHelper.getDocumentosTamanio().add(nombreArchivo);
            } else if (!validaDuplicidad(event.getFile())) {
                cargaDocHelper.getDocumentosDuplicidad().add(nombreArchivo);
            } else {
                cargaDocHelper.getDocumentosCorrectos().add(nombreArchivo);
                cargaDocHelper.getUploadedFiles().add(event.getFile());
                setGuardarHabilitado(false);
            }
            setMuestraPreviaCarga(true);
        } else {
            FacesUtil.addErrorMessage(null, Constantes.NUMERO_MAXIMO_DOC, maxDocsStr);
            guardarHabilitado = true;
            cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
        }
    }
    
    public void cargaDocumentos() {
        setPanelVisible(false);
        if (null != cargaDocHelper.getUploadedFiles() && !cargaDocHelper.getUploadedFiles().isEmpty()) {
            cargaDocHelper.setCargaDocumentacionMasivaDtoList(new ArrayList<CargaDocumentacionMasivaDTO>());
            folio = getCargaMasivaInsumosService().getConsecutivoArchivo();
            setFolio(folio);
            String nombreArchivo = null;
            for (UploadedFile archivoCargado : cargaDocHelper.getUploadedFiles()) {
                cargaDocHelper.setCargaDocumentacionMasivaDto(new CargaDocumentacionMasivaDTO());
                try {
                    nombreArchivo = VistaArchivoInsumoUtil.limpiarPathArchivo(archivoCargado.getFileName());
                    String destino = Constantes.DIRECTORIO_CARGA_DOCUMENTOS_INSUMOS + folio + "/";
                    cargaDocHelper.getCargaDocumentacionMasivaDto()
                            .setRutaDocumento(Constantes.DIRECTORIO_CARGA_DOCUMENTOS_INSUMOS);
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
                    getCargaMasivaInsumosService().cargaDocumento(destino, is, nombreArchivo);
                    cargaDocHelper.getCargaDocumentacionMasivaDtoList().add(cargaDocHelper.getCargaDocumentacionMasivaDto());
                } catch (IOException e) {
                    LOGGER.error(ConstantesError.ERROR_ESCRITURA_DOC + e.getCause(), e);
                    cargaDocHelper.getDocumentosIncorrectos().add(nombreArchivo);
                } catch (NegocioException e) {
                    LOGGER.error(ConstantesError.ERROR_ESCRITURA_DOC + e.getCause(), e);
                    cargaDocHelper.getDocumentosIncorrectos().add(nombreArchivo);
                }
            }
            inicializaDocumentos();
            FacesUtil.addInfoMessage(null, Constantes.CARGA_EXITOSA, Constantes.MENSAJE_FOLIO
                    .concat(cargaDocHelper.getCargaDocumentacionMasivaDto().getFolioDocumento()));
            setPanelVisible(true);
            setGuardarHabilitado(true);
            setMuestraPreviaCarga(false);
            row = 0;
        } else {
            this.cancelar();
            FacesUtil.addErrorMessage(null, ConstantesError.ERROR_GUARDAR);
            folio = "";
        }
    }

    private boolean validaDuplicidad(UploadedFile uFile) {
        String nombreArchivo = uFile.getFileName();
        boolean esCorrecto = true;
        if (!cargaDocHelper.getUploadedFiles().isEmpty()) {
            for (UploadedFile archivo : cargaDocHelper.getUploadedFiles()) {
                if (nombreArchivo.equals(archivo.getFileName())) {
                    esCorrecto = false;
                    break;
                }
            }
        }
        return esCorrecto;
    }
    
    private void inicializaDocumentos() {
        cargaDocHelper.setUploadedFiles(new ArrayList<UploadedFile>());
        cargaDocHelper.setDocumentosCorrectos(new ArrayList<String>());
        cargaDocHelper.setDocumentosFormato(new ArrayList<String>());
        cargaDocHelper.setDocumentosTamanio(new ArrayList<String>());
        cargaDocHelper.setDocumentosDuplicidad(new ArrayList<String>());
    }

    public final CargaDocumentacionMasivaHelper getCargaDocHelper() {
        return cargaDocHelper;
    }

    public final void setCargaDocHelper(CargaDocumentacionMasivaHelper cargaDocHelper) {
        this.cargaDocHelper = cargaDocHelper;
    }

    public final boolean isFileHabilitado() {
        return fileHabilitado;
    }

    public final void setFileHabilitado(boolean fileHabilitado) {
        this.fileHabilitado = fileHabilitado;
    }

    public final boolean isPanelVisible() {
        return panelVisible;
    }

    public final void setPanelVisible(boolean panelVisible) {
        this.panelVisible = panelVisible;
    }

    public final boolean isGuardarHabilitado() {
        return guardarHabilitado;
    }

    public final void setGuardarHabilitado(boolean guardarHabilitado) {
        this.guardarHabilitado = guardarHabilitado;
    }

    public final int getRow() {
        return row;
    }

    public final void setRow(int row) {
        this.row = row;
    }

    public final String getArchivosCargados() {
        return archivosCargados;
    }

    public final void setArchivosCargados(String archivosCargados) {
        this.archivosCargados = archivosCargados;
    }

    public final String getFolio() {
        return folio;
    }

    public final void setFolio(String folio) {
        this.folio = folio;
    }

    public final String getMaxDocsStr() {
        return maxDocsStr;
    }

    public final CargaMasivaInsumosService getCargaMasivaInsumosService() {
        return cargaMasivaInsumosService;
    }

    public final void setCargaMasivaInsumosService(CargaMasivaInsumosService cargaMasivaInsumosService) {
        this.cargaMasivaInsumosService = cargaMasivaInsumosService;
    }

    public final boolean isMuestraPreviaCarga() {
        return muestraPreviaCarga;
    }

    public final void setMuestraPreviaCarga(boolean muestraPreviaCarga) {
        this.muestraPreviaCarga = muestraPreviaCarga;
    }
}
