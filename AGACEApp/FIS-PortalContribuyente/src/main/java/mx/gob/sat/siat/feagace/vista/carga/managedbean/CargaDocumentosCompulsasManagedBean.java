package mx.gob.sat.siat.feagace.vista.carga.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import mx.gob.sat.siat.feagace.vista.carga.CargaDocumentosCompulsasAbstractMB;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosCompulsasDTOHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosFunctionHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosStreamedHelper;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean(name = "cargaDocumentosCompulsasManagedBean")
public class CargaDocumentosCompulsasManagedBean extends CargaDocumentosCompulsasAbstractMB {

    @SuppressWarnings("compatibility:-2427210077872958079")
    private static final long serialVersionUID = 1L;

    private static final String PERFIL_SESSION = "perfil";

    public CargaDocumentosCompulsasManagedBean() {
        setCargaDoctosDTOHelper(new CargaDocumentosCompulsasDTOHelper());
        setCargaDoctosStreamedHelper(new CargaDocumentosStreamedHelper());
    }

    @PostConstruct
    public void init() {

        HashMap mapCompulsa = (HashMap) getSession().getAttribute("mapCompulsa");
        if (mapCompulsa != null) {
            FecetCompulsas compulsaSession = (FecetCompulsas) mapCompulsa.get("compulsa");
            PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) mapCompulsa.get(PERFIL_SESSION);
            if (compulsaSession != null && perfilSession != null) {
                getCargaDoctosDTOHelper().setCompulsaSeleccionado(compulsaSession);
                getCargaDoctosDTOHelper().setContribuyenteInfo(getCargarFirmaPruebasPromoService().obtenerContribuyenteInfo(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getIdOrdenAuditada()));
                getCargaDoctosDTOHelper().setPerfil(perfilSession);                
                List<String> leyendas
                        = getCargarFirmaPruebasPromoService().getLeyendaDoc(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOrden(),
                                getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOficio());
                getCargaDoctosDTOHelper().setLeyenda(leyendas.size() == 2 ? leyendas.get(1) : "");
                getCargaDoctosDTOHelper().setLeyendaDoc(leyendas.size() == 2 ? "Adjuntar " + leyendas.get(1) :
                                                               "");
                getSession().removeAttribute("mapCompulsa");
                cargaPromocionesCompulsas();
                cargaProrrogasCompulsas();
            }
        }
    }

    public void handleFileUploadPromocion(FileUploadEvent event) throws IOException {       
        if (getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas() == null) {
            FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
            getCargaDoctosStreamedHelper().setArchivoPromocionCompulsa(event.getFile());
            FecetPromocionOficio dto = new FecetPromocionOficio();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getCargaDoctosStreamedHelper().getArchivoPromocionCompulsa().getFileName())));
            dto.setArchivo(getCargaDoctosStreamedHelper().getArchivoPromocionCompulsa().getInputstream());
            getCargaDoctosDTOHelper().setListaPromocionesCompulsaCargadas(new ArrayList<FecetPromocionOficio>());

            archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
            archivoTemp.setArchivoByte(getCargaDoctosStreamedHelper().getArchivoPromocionCompulsa().getContents());
            archivoTemp.setFecha(fecha);

            dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

            getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas().add(dto);
            getCargaDoctosDTOHelper().setVisibleBtnEliminar(true);
            addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
        } else {
            addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
        }
        getCargaDoctosDTOHelper().setMostrarPruebasAlegatosCompulsa(true);
    }

    public void limpiarPromocion() {
        if (getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas() != null) {
            getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas().clear();
            getCargaDoctosDTOHelper().setListaPromocionesCompulsaCargadas(null);
            getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas().clear();
            getCargaDoctosDTOHelper().setListaPruebasAlegatosCompulsaCargadas(null);
            getCargaDoctosStreamedHelper().setArchivoPromocionCompulsa(null);
            getCargaDoctosDTOHelper().setMostrarPruebasAlegatosCompulsa(false);
            getCargaDoctosDTOHelper().setVisibleBtnEliminar(false);
            RequestContext.getCurrentInstance().update("formCargaDocumentosCompulsas:flUpPromo");
        }
    }

    public void handleFileUploadPruebasAlegatos(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Long tamanioMB = 1024L;
        getCargaDoctosStreamedHelper().setArchivoPruebasAlegatosCompulsa(event.getFile());
        FecetAlegatoOficio dto = new FecetAlegatoOficio();
        final Date fecha = new Date();
        dto.setFechaCarga(fecha);
        dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getCargaDoctosStreamedHelper().getArchivoPruebasAlegatosCompulsa().getFileName())));
        dto.setArchivo(getCargaDoctosStreamedHelper().getArchivoPruebasAlegatosCompulsa().getInputstream());
        if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasAlegatoOficio(dto.getNombreArchivo(),
                getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas())) {
            addErrorMessage(null, "No puede subir el mismo archivo", "");
        } else {
            if (getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas() == null
                    || getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas().isEmpty()) {
                getCargaDoctosDTOHelper().setListaPruebasAlegatosCompulsaCargadas(new ArrayList<FecetAlegatoOficio>());
            }
            try {
                dto.setArchivo(getCargaDoctosStreamedHelper().getArchivoPruebasAlegatosCompulsa().getInputstream());
                dto.setTamanioArchivo((getCargaDoctosStreamedHelper().getArchivoPruebasAlegatosCompulsa().getSize() / tamanioMB));

                archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
                archivoTemp.setArchivoByte(getCargaDoctosStreamedHelper().getArchivoPruebasAlegatosCompulsa().getContents());
                archivoTemp.setFecha(fecha);

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas().add(dto);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                RequestContext.getCurrentInstance().update("formCargaDocumentosCompulsas:flUpPruebas");
            } catch (IOException e) {
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }
    }

    public void descartarPruebaLista() {
        List<FecetAlegatoOficio> listaNueva = new ArrayList<FecetAlegatoOficio>();
        for (FecetAlegatoOficio alegato : getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas()) {
            if (!alegato.getNombreArchivo().equals(getCargaDoctosDTOHelper().getPruebaCompulsaSeleccionada().getNombreArchivo())) {
                listaNueva.add(alegato);
            }
        }
        getCargaDoctosDTOHelper().setListaPruebasAlegatosCompulsaCargadas(listaNueva);
    }

    private void cargaPromocionesCompulsas() {
        getCargaDoctosDTOHelper().setListaHistoricoPromocionesCompulsas(getCargarFirmaPruebasPromoService().getPromocionOficioContadorPruebasAlegatos(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOrden(), getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOficio()));
    }

    private void cargaProrrogasCompulsas() {
        getCargaDoctosDTOHelper().setMostrarProrrogas(getCargarFirmaProrrogaService().validaMetodoProrrogaOrden(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOrden()));
        if (getCargaDoctosDTOHelper().isMostrarProrrogas()) {
            getCargaDoctosDTOHelper().setListaProrrogaCompulsa(getCargarFirmaProrrogaService().getHistoricoProrrogaOficio(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOficio().getIdOficio()));
            if(getCargaDoctosDTOHelper().getListaProrrogaCompulsa().isEmpty()){
                getCargaDoctosDTOHelper().setMostrarBtnProrrogas(true);
            } else{
                getCargaDoctosDTOHelper().setMostrarBtnProrrogas(
                        getCargarFirmaProrrogaService().validaProrrogaEstatus(getCargaDoctosDTOHelper().getListaProrrogaCompulsa(), getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOficio()));
            }
        }
    }

    public String enviarFirma() {
        if (CargaDocumentosFunctionHelper.validaArchivosOficioCargados(getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas())) {
            if (CargaDocumentosFunctionHelper.validaLista(getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas())) {
                getCargaDoctosDTOHelper().setListaPruebasAlegatosCompulsaCargadas(new ArrayList<FecetAlegatoOficio>());
            }
            getCargaDoctosDTOHelper().setPromocionCompulsaSeleccionada(getCargaDoctosDTOHelper().getListaPromocionesCompulsaCargadas().get(0));
            getCargaDoctosDTOHelper().getPromocionCompulsaSeleccionada().setLeyenda(getCargaDoctosDTOHelper().getLeyenda());
            PromocionDocsVO docs = new PromocionDocsVO();
            docs.setNombreArchivo(getCargaDoctosStreamedHelper().getArchivoPromocionCompulsa().getFileName());
            docs.setListaPruebasAlegatosOficioCargadas(getCargaDoctosDTOHelper().getListaPruebasAlegatosCompulsaCargadas());
            docs.setOrdenSeleccionado(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOrden());
            docs.setPerfil(getCargaDoctosDTOHelper().getPerfil());
            docs.setSizeOficio(getCargaDoctosStreamedHelper().getArchivoPromocionCompulsa().getSize());
            docs.setPromocionOficioSeleccionado(getCargaDoctosDTOHelper().getPromocionCompulsaSeleccionada());

            docs.setOficioSeleccionado(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getOficio());
            
            docs.setIdOrdenAuditadaCompulsa(getCargaDoctosDTOHelper().getCompulsaSeleccionado().getIdOrdenAuditada());

            getSession().setAttribute("promocionCompulsaFirmar", docs);
            limpiarVariables();
            return "firmaPromocionCompulsa?faces-redirect=true";
        } else {
            addErrorMessage(null, getMessageResourceString("error.debe.seleccionar.archivo"), "");
            return null;
        }
    }

    public void getHistoricoPruebasAlegatosPromocionCompulsa() {
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatosCompulsa(getCargarFirmaPruebasPromoService().getPruebasAlegatosPromocionOficio(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getIdPromocionOficio()));
        getCargaDoctosDTOHelper().setIdPromocionCompulsaSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getIdPromocionOficio());
        getCargaDoctosDTOHelper().setFechaEnvioPromocionCompulsaSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getFechaCarga());
    }

    public void getDocsProrrogaCompulsas() {
        getCargaDoctosDTOHelper().setListaDocProrrogaCompulsa(getCargarFirmaProrrogaService().getDocsProrrogaOficio(getCargaDoctosDTOHelper().getProrrogaCompulsaSeleccionada().getIdProrrogaOficio()));
        getCargaDoctosDTOHelper().setIdProrrogaCompulsaSeleccionado(getCargaDoctosDTOHelper().getProrrogaCompulsaSeleccionada().getIdProrrogaOficio());
        getCargaDoctosDTOHelper().setFechaEnvioProrrogaCompulsaSeleccionada(getCargaDoctosDTOHelper().getProrrogaCompulsaSeleccionada().getFechaCarga());
    }

    public String cargaProrrogaCompulsa() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL_SESSION, getCargaDoctosDTOHelper().getPerfil());
        map.put("compulsa", getCargaDoctosDTOHelper().getCompulsaSeleccionado());
        getSession().setAttribute("mapCompulsa", map);
        return "../cargaDocumentacion/cargaProrrogasCompulsas.jsf";
    }

    public String regresa() {
        limpiarVariables();
        return "../asociarColaboradores/indexAsociar.jsf";
    }

    private void limpiarVariables() {
        getCargaDoctosStreamedHelper().setArchivoPromocionCompulsa(null);
        getCargaDoctosStreamedHelper().setArchivoPruebasAlegatosCompulsa(null);
        getCargaDoctosStreamedHelper().setArchivoProrroga(null);
        getCargaDoctosStreamedHelper().setAcuseFilePromocionCompulsa(null);
        getCargaDoctosStreamedHelper().setArchivoDescargaHistoricoPromocionCompulsa(null);
        getCargaDoctosStreamedHelper().setArchivoPromocionCompulsaHistoricoDescargaAcuse(null);
        getCargaDoctosStreamedHelper().setArchivoDescargaProrrogaCompulsaAcuse(null);
        getCargaDoctosStreamedHelper().setArchivoPruebaCompulsaHistoricoDescarga(null);
        getCargaDoctosStreamedHelper().setArchivoDocProrrogaCompulsaDescarga(null);
        getCargaDoctosDTOHelper().setCompulsaSeleccionado(null);
        getCargaDoctosDTOHelper().setPerfil(null);
        getCargaDoctosDTOHelper().setListaPromocionesCompulsaCargadas(null);
        getCargaDoctosDTOHelper().setListaPruebasAlegatosCompulsaCargadas(null);
        getCargaDoctosDTOHelper().setPruebaCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setAcuse(null);
        getCargaDoctosDTOHelper().setPromocionCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPromocionesCompulsas(null);
        getCargaDoctosDTOHelper().setPromocionHistoricoCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatosCompulsa(null);
        getCargaDoctosDTOHelper().setIdPromocionCompulsaSeleccionadaHistorico(null);
        getCargaDoctosDTOHelper().setFechaEnvioPromocionCompulsaSeleccionadaHistorico(null);
        getCargaDoctosDTOHelper().setLeyendaDoc(null);
        getCargaDoctosDTOHelper().setPruebaHistoricoCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setListaProrrogaCompulsa(null);
        getCargaDoctosDTOHelper().setProrrogaCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setListaDocProrrogaCompulsa(null);
        getCargaDoctosDTOHelper().setIdProrrogaCompulsaSeleccionado(null);
        getCargaDoctosDTOHelper().setFechaEnvioProrrogaCompulsaSeleccionada(null);
        getCargaDoctosDTOHelper().setDocProrrogaCompulsaSeleccionado(null);
        getCargaDoctosDTOHelper().setMostrarBtnProrrogas(false);
        getCargaDoctosDTOHelper().setMostrarProrrogas(false);
        getCargaDoctosDTOHelper().setMostrarPruebasAlegatosCompulsa(false);
        getCargaDoctosDTOHelper().setContribuyenteInfo(null);
    }

    public StreamedContent getArchivoDescargaHistoricoPromocionCompulsa() {
        getCargaDoctosStreamedHelper().setArchivoDescargaHistoricoPromocionCompulsa(
                getDescargaArchivo(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getRutaArchivo()
                        ,
                        getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getNombreArchivo()));
        return getCargaDoctosStreamedHelper().getArchivoDescargaHistoricoPromocionCompulsa();
    }

    public StreamedContent getArchivoPromocionCompulsaHistoricoDescargaAcuse() {
        getCargaDoctosStreamedHelper().setArchivoPromocionCompulsaHistoricoDescargaAcuse(
                getDescargaArchivo(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getRutaAcuse()
                        + getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getNombreAcuse(),
                        getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getNombreAcuse()));
        return getCargaDoctosStreamedHelper().getArchivoPromocionCompulsaHistoricoDescargaAcuse();
    }

    public StreamedContent getArchivoDescargaProrrogaCompulsaAcuse() {
        getCargaDoctosStreamedHelper().setArchivoDescargaProrrogaCompulsaAcuse(
                getDescargaArchivo(getCargaDoctosDTOHelper().getProrrogaCompulsaSeleccionada().getRutaAcuse()
                        ,
                        getCargaDoctosDTOHelper().getProrrogaCompulsaSeleccionada().getNombreAcuse()));
        return getCargaDoctosStreamedHelper().getArchivoDescargaProrrogaCompulsaAcuse();
    }

    public StreamedContent getArchivoPruebaCompulsaHistoricoDescarga() {
        getCargaDoctosStreamedHelper().setArchivoPruebaCompulsaHistoricoDescarga(
                getDescargaArchivo(getCargaDoctosDTOHelper().getPruebaHistoricoCompulsaSeleccionada().getRutaArchivo()
                        ,
                        getCargaDoctosDTOHelper().getPruebaHistoricoCompulsaSeleccionada().getNombreArchivo()));
        return getCargaDoctosStreamedHelper().getArchivoPruebaCompulsaHistoricoDescarga();
    }

    public StreamedContent getArchivoDocProrrogaCompulsaDescarga() {
        getCargaDoctosStreamedHelper().setArchivoDocProrrogaCompulsaDescarga(
                getDescargaArchivo(getCargaDoctosDTOHelper().getDocProrrogaCompulsaSeleccionado().getRutaArchivo()
                        ,
                        getCargaDoctosDTOHelper().getDocProrrogaCompulsaSeleccionado().getNombreArchivo()));
        return getCargaDoctosStreamedHelper().getArchivoDocProrrogaCompulsaDescarga();
    }

    public StreamedContent getPromocionCompulsaSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPromocionCompulsaSeleccionada().getIdArchivoTemp(), getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        getCargaDoctosStreamedHelper().setPromocionCompulsaSeleccionadaDescarga(
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPromocionCompulsaSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPromocionCompulsaSeleccionada().getNombreArchivo()));
        return getCargaDoctosStreamedHelper().getPromocionCompulsaSeleccionadaDescarga();
    }

    public StreamedContent getPruebaCompulsaSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPruebaCompulsaSeleccionada().getIdArchivoTemp(), getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        getCargaDoctosStreamedHelper().setPruebaCompulsaSeleccionadaDescarga(
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPruebaCompulsaSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPruebaCompulsaSeleccionada().getNombreArchivo()));
        return getCargaDoctosStreamedHelper().getPruebaCompulsaSeleccionadaDescarga();
    }
    
    public StreamedContent getArchivoPromocionOficioHistoricoDescargaAcuse() {
        getCargaDoctosStreamedHelper().setArchivoPromocionOficioHistoricoDescargaAcuse(
                getDescargaArchivo(getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getRutaAcuse()
                        ,
                        getCargaDoctosDTOHelper().getPromocionHistoricoCompulsaSeleccionada().getNombreAcuse()));
        return getCargaDoctosStreamedHelper().getArchivoPromocionOficioHistoricoDescargaAcuse();
    }
}
