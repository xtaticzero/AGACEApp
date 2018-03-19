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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.vista.carga.CargaPruebasPericialesAbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosFunctionHelper;

@SessionScoped
@ManagedBean(name = "cargaPruebasPericialesManagedBean")
public class CargaPruebasPericialesManagedBean extends CargaPruebasPericialesAbstractManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {
        HashMap mapOrden = (HashMap) getSession().getAttribute("mapPruebaPericial");
        try {
            if (mapOrden != null) {
                AgaceOrden ordenSession = (AgaceOrden) mapOrden.get("orden");
                PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) mapOrden.get("perfil");
                if (ordenSession != null && perfilSession != null) {
                    setOrdenSeleccionado(ordenSession);
                    setPerfil(perfilSession);
                    getSession().removeAttribute("mapPruebaPericial");

                } else {
                    throw new NegocioException("Se perdio la session");
                }
            }
        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public void handleFileUploadPruebaPericial(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Long tamanioMB = 1024L;
        setFechaHoy(new Date());

        setArchivoPruebaPericial(event.getFile());
        FecetDocPruebasPericiales dto = new FecetDocPruebasPericiales();
        dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoPruebaPericial().getFileName())));
        dto.setArchivo(getArchivoPruebaPericial().getInputstream());
        if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasPericiales(dto.getNombreArchivo(), getListaDocsPruebasPericiales())) {
            addErrorMessage(null, "No puede subir el mismo archivo", "");
        } else {
            if (getListaDocsPruebasPericiales() == null) {
                setListaDocsPruebasPericiales(new ArrayList<FecetDocPruebasPericiales>());
            }
            try {
                dto.setArchivo(getArchivoPruebaPericial().getInputstream());
                dto.setTamanioArchivo((getArchivoPruebaPericial().getSize() / tamanioMB));

                archivoTemp.setSessionUUID(getPerfil().getRfc());
                archivoTemp.setArchivoByte(getArchivoPruebaPericial().getContents());
                archivoTemp.setFecha(getFechaHoy());

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getListaDocsPruebasPericiales().add(dto);
                setVisibleBtnEliminarOrden(true);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }

        }
    }

    public void limpiarPruebaPericial() {
        if (getListaDocsPruebasPericiales() != null) {
            getListaDocsPruebasPericiales().clear();
            setListaDocsPruebasPericiales(null);
            setArchivoPruebaPericial(null);
            setVisibleBtnEliminarOrden(false);
            RequestContext.getCurrentInstance().update("formCargaPruebasPericiales:flUpPro");
        }
    }

    public String enviarFirma() {
        if (CargaDocumentosFunctionHelper.validaPruebasPericialesCargados(getListaDocsPruebasPericiales())) {
            PruebasPericialesDocsVO docs = new PruebasPericialesDocsVO();
            docs.setOrdenSeleccionado(getOrdenSeleccionado());
            docs.setListaDocsPruebasPericiales(getListaDocsPruebasPericiales());
            docs.setPerfil(getPerfil());
            docs.setPruebasPericiales(new FecetPruebasPericiales());
            getSession().setAttribute("pruebaPericialFirmar", docs);
            limpiarVariables();
            return "firmaPruebaPericial?faces-redirect=true";
        } else {
            addErrorMessage(null, getMessageResourceString(MSN_ERROR_SELECCIONAR_ARCHIVO), "");
            return null;
        }

    }

    public String regresa() {
        limpiarVariables();
        return "../asociarColaboradores/indexAsociar.jsf";
    }

    private void limpiarVariables() {
        setOrdenSeleccionado(null);
        setPerfil(null);
        setArchivoPruebaPericial(null);
        setListaDocsPruebasPericiales(null);
        setPruebaPericialNuevo(null);
        setDocPruebaPericialSeleccionada(null);
        setPruebaPericialSeleccionadaDescarga(null);

        setAcuse(null);
        setAcuseFilePruebaPericial(null);

        setVisibleBtnEliminarOrden(false);
    }

    public void descartarPruebaLista() {
        List<FecetDocPruebasPericiales> listaNueva = new ArrayList<FecetDocPruebasPericiales>();
        for (FecetDocPruebasPericiales pruebaPericial : getListaDocsPruebasPericiales()) {
            if (!pruebaPericial.getNombreArchivo().equals(getDocPruebaPericialSeleccionada().getNombreArchivo())) {
                listaNueva.add(pruebaPericial);
            }
        }
        setListaDocsPruebasPericiales(listaNueva);
    }
}
