package mx.gob.sat.siat.feagace.vista.carga.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosFunctionHelper;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@SessionScoped
@ManagedBean(name = "cargaProrrogasCompulsasManagedBean")
public class CargaProrrogasCompulsasManagedBean extends BaseManagedBean {

    @SuppressWarnings("compatibility:6818064327524044193")
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{cargarFirmaProrrogaService}")
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    private PerfilContribuyenteVO perfil;
    private static final String PERFIL_SESSION = "perfil";

    private FecetCompulsas compulsaSeleccionado;
    private transient UploadedFile archivoProrrogaCompulsa;
    private List<FecetDocProrrogaOficio> listaDocsProrrogasCompulsa;
    private FecetDocProrrogaOficio docProrrogaOficioSeleccionado;
    private transient StreamedContent docProrrogaOficioSeleccionadoDescarga;
    private AcuseRevisionElectronica acuse;
    private transient StreamedContent acuseFileProrrogaCompulsa;

    private FecetProrrogaOficio prorrogaCompulsaNuevo;
    private boolean visibleBtnEliminar;

    @PostConstruct
    public void init() {
        HashMap mapCompulsa = (HashMap) getSession().getAttribute("mapCompulsa");
        if (mapCompulsa != null) {
            FecetCompulsas compulsaSession = (FecetCompulsas) mapCompulsa.get("compulsa");
            PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) mapCompulsa.get(PERFIL_SESSION);
            if (compulsaSession != null && perfilSession != null) {
                setCompulsaSeleccionado(compulsaSession);
                setPerfil(perfilSession);
                getSession().removeAttribute("mapCompulsa");
            } else {
                addErrorMessage("Se perdio la session");
            }
        }
    }

    public void handleFileUploadProrroga(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Date fecha = new Date();
        final Long tamanioMB = 1024L;
        setArchivoProrrogaCompulsa(event.getFile());
        FecetDocProrrogaOficio dto = new FecetDocProrrogaOficio();
        dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoProrrogaCompulsa().getFileName())));
        dto.setArchivo(getArchivoProrrogaCompulsa().getInputstream());
        if (CargaDocumentosFunctionHelper.checarDuplicadoProrrogasOficio(dto.getNombreArchivo(), getListaDocsProrrogasCompulsa())) {
            addErrorMessage(null, "No puede subir el mismo archivo", "");
        } else {
            if (getListaDocsProrrogasCompulsa() == null) {
                setListaDocsProrrogasCompulsa(new ArrayList<FecetDocProrrogaOficio>());
            }
            try {
                dto.setArchivo(getArchivoProrrogaCompulsa().getInputstream());
                dto.setTamanioArchivo((getArchivoProrrogaCompulsa().getSize() / tamanioMB));

                archivoTemp.setSessionUUID(getPerfil().getRfc());
                archivoTemp.setArchivoByte(getArchivoProrrogaCompulsa().getContents());
                archivoTemp.setFecha(fecha);

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getListaDocsProrrogasCompulsa().add(dto);
                setVisibleBtnEliminar(true);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }
    }

    public void limpiarProrrogas() {
        if (getListaDocsProrrogasCompulsa() != null) {
            getListaDocsProrrogasCompulsa().clear();
            setListaDocsProrrogasCompulsa(null);
            setArchivoProrrogaCompulsa(null);
            setVisibleBtnEliminar(false);
            RequestContext.getCurrentInstance().update("formCargaProrrogasOficio:flUpPromo");
        }
    }

    public String enviarFirma() {
        if (CargaDocumentosFunctionHelper.validaProrrogasOficioCargados(getListaDocsProrrogasCompulsa())) {
            ProrrogaDocsVO docs = new ProrrogaDocsVO();
            docs.setOrdenSeleccionado(getCompulsaSeleccionado().getOrden());
            docs.setListaDocsProrrogasOficio(getListaDocsProrrogasCompulsa());
            docs.setPerfil(getPerfil());
            docs.setOficioSeleccionado(getCompulsaSeleccionado().getOficio());
            
            docs.setIdOrdenAuditadaCompulsa(getCompulsaSeleccionado().getIdOrdenAuditada());

            docs.setProrrogaOficio(new FecetProrrogaOficio());
            getSession().setAttribute("prorrogaCompulsaFirmar", docs);
            limpiarVariables();

            return "firmaProrrogaCompulsa?faces-redirect=true";
        } else {
            addErrorMessage(null, getMessageResourceString("error.debe.seleccionar.archivo"), "");
            return null;
        }

    }

    public String regresaCompulsas() {
        limpiarVariables();
        return "cargaDocumentosCompulsas.jsf";
    }

    private void limpiarVariables() {
        perfil = null;
        compulsaSeleccionado = null;
        archivoProrrogaCompulsa = null;
        listaDocsProrrogasCompulsa = null;
        acuse = null;
        acuseFileProrrogaCompulsa = null;
        prorrogaCompulsaNuevo = null;
    }

    public void setCargarFirmaProrrogaService(CargarFirmaProrrogaService cargarFirmaProrrogaService) {
        this.cargarFirmaProrrogaService = cargarFirmaProrrogaService;
    }

    public CargarFirmaProrrogaService getCargarFirmaProrrogaService() {
        return cargarFirmaProrrogaService;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setCompulsaSeleccionado(FecetCompulsas compulsaSeleccionado) {
        this.compulsaSeleccionado = compulsaSeleccionado;
    }

    public FecetCompulsas getCompulsaSeleccionado() {
        return compulsaSeleccionado;
    }

    public void setArchivoProrrogaCompulsa(UploadedFile archivoProrrogaCompulsa) {
        this.archivoProrrogaCompulsa = archivoProrrogaCompulsa;
    }

    public UploadedFile getArchivoProrrogaCompulsa() {
        return archivoProrrogaCompulsa;
    }

    public void setListaDocsProrrogasCompulsa(List<FecetDocProrrogaOficio> listaDocsProrrogasCompulsa) {
        this.listaDocsProrrogasCompulsa = listaDocsProrrogasCompulsa;
    }

    public List<FecetDocProrrogaOficio> getListaDocsProrrogasCompulsa() {
        return listaDocsProrrogasCompulsa;
    }

    public void setAcuse(AcuseRevisionElectronica acuse) {
        this.acuse = acuse;
    }

    public AcuseRevisionElectronica getAcuse() {
        return acuse;
    }

    public void setAcuseFileProrrogaCompulsa(StreamedContent acuseFileProrrogaCompulsa) {
        this.acuseFileProrrogaCompulsa = acuseFileProrrogaCompulsa;
    }

    public StreamedContent getAcuseFileProrrogaCompulsa() {
        return acuseFileProrrogaCompulsa;
    }

    public void setProrrogaCompulsaNuevo(FecetProrrogaOficio prorrogaCompulsaNuevo) {
        this.prorrogaCompulsaNuevo = prorrogaCompulsaNuevo;
    }

    public FecetProrrogaOficio getProrrogaCompulsaNuevo() {
        return prorrogaCompulsaNuevo;
    }

    public void setVisibleBtnEliminar(boolean visibleBtnEliminar) {
        this.visibleBtnEliminar = visibleBtnEliminar;
    }

    public boolean isVisibleBtnEliminar() {
        return visibleBtnEliminar;
    }

    public void setDocProrrogaOficioSeleccionado(FecetDocProrrogaOficio docProrrogaOficioSeleccionado) {
        this.docProrrogaOficioSeleccionado = docProrrogaOficioSeleccionado;
    }

    public FecetDocProrrogaOficio getDocProrrogaOficioSeleccionado() {
        return docProrrogaOficioSeleccionado;
    }

    public void setDocProrrogaOficioSeleccionadoDescarga(StreamedContent docProrrogaOficioSeleccionadoDescarga) {
        this.docProrrogaOficioSeleccionadoDescarga = docProrrogaOficioSeleccionadoDescarga;
    }

    public StreamedContent getDocProrrogaOficioSeleccionadoDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getDocProrrogaOficioSeleccionado().getIdArchivoTemp(), getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.docProrrogaOficioSeleccionadoDescarga
                = new DefaultStreamedContent(byteArchivo, getDocProrrogaOficioSeleccionado().getNombreArchivo(),
                        getDocProrrogaOficioSeleccionado().getNombreArchivo());
        return docProrrogaOficioSeleccionadoDescarga;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }
}
