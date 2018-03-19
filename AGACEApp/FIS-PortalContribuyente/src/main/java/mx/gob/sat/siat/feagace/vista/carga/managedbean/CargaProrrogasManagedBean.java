package mx.gob.sat.siat.feagace.vista.carga.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.vista.carga.CargaProrrogasAbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosFunctionHelper;

@SessionScoped
@ManagedBean(name = "cargaProrrogasManagedBean")
public class CargaProrrogasManagedBean extends CargaProrrogasAbstractManagedBean {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {
        HashMap mapOrden = (HashMap) getSession().getAttribute("mapProrrogaOrden");
        HashMap mapOficio = (HashMap) getSession().getAttribute("mapProrrogaOficio");
        try {
            if (mapOrden != null) {
                setFromOrden(true);
                AgaceOrden ordenSession = (AgaceOrden) mapOrden.get("orden");
                PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) mapOrden.get("perfil");
                if (ordenSession != null && perfilSession != null) {
                    setOrdenSeleccionado(ordenSession);
                    setPerfil(perfilSession);
                    getSession().removeAttribute("mapProrrogaOrden");

                } else {
                    throw new NegocioException("Se perdio la session");
                }
            } else if (mapOficio != null) {
                setFromOrden(false);
                AgaceOrden ordenSession = (AgaceOrden) mapOficio.get("orden");
                PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) mapOficio.get("perfil");
                FecetOficio oficioSession = (FecetOficio) mapOficio.get("oficio");
                if (ordenSession != null && perfilSession != null && oficioSession != null) {
                    setOrdenSeleccionado(ordenSession);
                    setPerfil(perfilSession);
                    setOficioSeleccionado(oficioSession);
                    getSession().removeAttribute("mapProrrogaOficio");

                } else {
                    throw new NegocioException("Se perdio la session");
                }
            }
        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public void handleFileUploadProrroga(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Long tamanioMB = 1024L;
        final Date fecha = new Date();
        if (isFromOrden()) {
            setArchivoProrroga(event.getFile());
            FecetDocProrrogaOrden dto = new FecetDocProrrogaOrden();
            dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoProrroga().getFileName())));
            dto.setArchivo(getArchivoProrroga().getInputstream());
            dto.setFechaCarga(fecha);
            if (CargaDocumentosFunctionHelper.checarDuplicadoProrrogas(dto.getNombreArchivo(), getListaDocsProrrogasOrden())) {
                addErrorMessage(null, "No puede subir el mismo archivo", "");
            } else {
                if (getListaDocsProrrogasOrden() == null) {
                    setListaDocsProrrogasOrden(new ArrayList<FecetDocProrrogaOrden>());
                }
                try {
                    dto.setArchivo(getArchivoProrroga().getInputstream());
                    dto.setTamanioArchivo((getArchivoProrroga().getSize() / tamanioMB));

                    archivoTemp.setSessionUUID(getPerfil().getRfc());
                    archivoTemp.setArchivoByte(getArchivoProrroga().getContents());
                    archivoTemp.setFecha(fecha);

                    dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                    getListaDocsProrrogasOrden().add(dto);
                    setVisibleBtnEliminarOrden(true);
                    addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                } catch (IOException e) {
                    addErrorMessage(null, Constantes.FALLA_CARGA, "");
                }

            }
        } else {
            setArchivoProrrogaOficio(event.getFile());
            FecetDocProrrogaOficio dto = new FecetDocProrrogaOficio();
            dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoProrrogaOficio().getFileName())));
            dto.setArchivo(getArchivoProrrogaOficio().getInputstream());
            dto.setFechaCarga(fecha);
            if (CargaDocumentosFunctionHelper.checarDuplicadoProrrogasOficio(dto.getNombreArchivo(), getListaDocsProrrogasOficio())) {
                addErrorMessage(null, "No puede subir el mismo archivo", "");
            } else {
                if (getListaDocsProrrogasOficio() == null) {
                    setListaDocsProrrogasOficio(new ArrayList<FecetDocProrrogaOficio>());
                }
                try {
                    dto.setArchivo(getArchivoProrrogaOficio().getInputstream());
                    dto.setTamanioArchivo((getArchivoProrrogaOficio().getSize() / tamanioMB));

                    archivoTemp.setSessionUUID(getPerfil().getRfc());
                    archivoTemp.setArchivoByte(getArchivoProrrogaOficio().getContents());
                    archivoTemp.setFecha(fecha);

                    dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                    getListaDocsProrrogasOficio().add(dto);
                    setVisibleBtnEliminarOficio(true);
                    addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                } catch (IOException e) {
                    addErrorMessage(null, Constantes.FALLA_CARGA, "");
                }
            }
        }
    }

    public void limpiarProrrogas() {
        if (isFromOrden()) {
            if (getListaDocsProrrogasOrden() != null) {
                getListaDocsProrrogasOrden().clear();
                setListaDocsProrrogasOrden(null);
                setArchivoProrroga(null);
                setVisibleBtnEliminarOrden(false);
                RequestContext.getCurrentInstance().update("formCargaProrrogas:flUpPro");
            }
        } else {
            if (getListaDocsProrrogasOficio() != null) {
                getListaDocsProrrogasOficio().clear();
                setListaDocsProrrogasOficio(null);
                setArchivoProrrogaOficio(null);
                setVisibleBtnEliminarOficio(false);
                RequestContext.getCurrentInstance().update("formCargaProrrogasOficio:flUpPromo");
            }
        }

    }

    public String enviarFirma() {
        if (isFromOrden()) {
            if (CargaDocumentosFunctionHelper.validaProrrogasOrdenCargados(getListaDocsProrrogasOrden())) {
                ProrrogaDocsVO docs = new ProrrogaDocsVO();
                docs.setOrdenSeleccionado(getOrdenSeleccionado());
                docs.setListaDocsProrrogasOrden(getListaDocsProrrogasOrden());
                docs.setPerfil(getPerfil());
                docs.setProrrogaOrden(new FecetProrrogaOrden());
                getSession().setAttribute("prorrogaOrdenFirmar", docs);
                limpiarVariables();
                return "firmaProrrogaOrden?faces-redirect=true";
            } else {
                addErrorMessage(null, getMessageResourceString(MSN_ERROR_SELECCIONAR_ARCHIVO), "");
                return null;
            }
        } else {
            if (CargaDocumentosFunctionHelper.validaProrrogasOficioCargados(getListaDocsProrrogasOficio())) {
                ProrrogaDocsVO docs = new ProrrogaDocsVO();
                docs.setOrdenSeleccionado(getOrdenSeleccionado());
                docs.setListaDocsProrrogasOficio(getListaDocsProrrogasOficio());
                docs.setPerfil(getPerfil());
                docs.setOficioSeleccionado(getOficioSeleccionado());
                docs.setProrrogaOficio(new FecetProrrogaOficio());
                getSession().setAttribute("prorrogaOficioFirmar", docs);
                limpiarVariables();
                return "firmaProrrogaOficio?faces-redirect=true";
            } else {
                addErrorMessage(null, getMessageResourceString(MSN_ERROR_SELECCIONAR_ARCHIVO), "");
                return null;
            }
        }
    }

    public String regresa() {
        limpiarVariables();
        return "../asociarColaboradores/indexAsociar.jsf";
    }

    public String regresaDocumentosOficio() {
        limpiarVariables();
        return "cargaDocumentosOficio.jsf";
    }

    private void limpiarVariables() {
        setOrdenSeleccionado(null);
        setPerfil(null);
        setArchivoProrroga(null);
        setListaDocsProrrogasOrden(null);
        setProrrogaOrdenNuevo(null);
        setDocProrrogaSeleccionada(null);

        setProrrogaSeleccionadaDescarga(null);

        setAcuse(null);
        setAcuseFileProrroga(null);

        setFromOrden(false);

        setOficioSeleccionado(null);
        setListaDocsProrrogasOficio(null);
        setArchivoProrrogaOficio(null);
        setProrrogaOficioNuevo(null);
        setDocProrrogaOficioSeleccionada(null);

        setProrrogaOficioSeleccionadaDescarga(null);

        setVisibleBtnEliminarOrden(false);
        setVisibleBtnEliminarOficio(false);
    }

    public void descartarProrrogaLista() {
        if (isFromOrden()) {
            List<FecetDocProrrogaOrden> listaNueva = new ArrayList<FecetDocProrrogaOrden>();
            for (FecetDocProrrogaOrden doc : getListaDocsProrrogasOrden()) {
                if (!doc.getNombreArchivo().equals(getDocProrrogaSeleccionada().getNombreArchivo())) {
                    listaNueva.add(doc);
                }
            }
            setListaDocsProrrogasOrden(listaNueva);

            if (getListaDocsProrrogasOrden().isEmpty()) {
                setVisibleBtnEliminarOrden(false);
            }

        } else {
            List<FecetDocProrrogaOficio> listaNueva = new ArrayList<FecetDocProrrogaOficio>();
            for (FecetDocProrrogaOficio docs : getListaDocsProrrogasOficio()) {
                if (!docs.getNombreArchivo().equals(getDocProrrogaOficioSeleccionada().getNombreArchivo())) {
                    listaNueva.add(docs);
                }
            }
            setListaDocsProrrogasOficio(listaNueva);

            if (getListaDocsProrrogasOficio().isEmpty()) {
                setVisibleBtnEliminarOficio(false);
            }
        }
    }
}
