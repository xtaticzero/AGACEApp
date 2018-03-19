package mx.gob.sat.siat.feagace.vista.propuestas.carga.managedbean;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaDocumentosFunctionHelper;

import mx.gob.sat.siat.feagace.vista.propuestas.carga.CargaDocumentosAbstractMB;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaDocumentosAttributesHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaDocumentosDTOHelper;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@SessionScoped
@ManagedBean(name = "cargaDocumentosManagedBean")
public class CargaDocumentosManagedBean extends CargaDocumentosAbstractMB {

    @SuppressWarnings("compatibility:-722738167860924319")
    private static final long serialVersionUID = 1L;

    private static final String PERFIL_SESSION = "perfil";

    public CargaDocumentosManagedBean() {
        setCargaDoctosAttributesHelper(new CargaDocumentosAttributesHelper());
        setCargaDoctosDTOHelper(new CargaDocumentosDTOHelper());
    }

    @PostConstruct
    public void init() {
        try {
            HashMap mapOrden = (HashMap) getSession().getAttribute("mapOrden");
            HashMap mapOficio = (HashMap) getSession().getAttribute("mapOficio");
            if (mapOrden != null) {
                getCargaDoctosAttributesHelper().setFromOrden(true);
                AgaceOrden ordenSession = (AgaceOrden) mapOrden.get("orden");
                if (ordenSession != null) {
                    getCargaDoctosDTOHelper().setOrdenSeleccionado(ordenSession);
                    getSession().removeAttribute("mapOrden");
                    List<String> leyendas = getCargarFirmaPruebasPromoService().getLeyendaDoc(getCargaDoctosDTOHelper().getOrdenSeleccionado(), null);
                    getCargaDoctosAttributesHelper().setLeyenda(leyendas.isEmpty() ? "" : leyendas.get(0));
                    getCargaDoctosAttributesHelper().setLeyendaDoc(leyendas.isEmpty() ? "" : "Adjuntar " + leyendas.get(0));
                    getCargaDoctosAttributesHelper().setContextoJarFirmante(getContextoAplicativo());
                } else {
                    throw new NegocioException("Se perdio la session");
                }
            } else if (mapOficio != null) {
                getCargaDoctosAttributesHelper().setFromOrden(false);
                AgaceOrden ordenSession = (AgaceOrden) mapOficio.get("orden");
                FecetOficio oficioSession = (FecetOficio) mapOficio.get("oficio");
                if (ordenSession != null && oficioSession != null) {
                    getCargaDoctosDTOHelper().setOrdenSeleccionado(ordenSession);
                    getCargaDoctosDTOHelper().setOficioSeleccionado(oficioSession);
                    List<String> leyendas
                            = getCargarFirmaPruebasPromoService().getLeyendaDoc(
                                    getCargaDoctosDTOHelper().getOrdenSeleccionado(),
                                    getCargaDoctosDTOHelper().getOficioSeleccionado());
                    getCargaDoctosAttributesHelper().setLeyenda(leyendas.size() == 2 ? leyendas.get(1) : "");
                    getCargaDoctosAttributesHelper().setLeyendaDoc(leyendas.size() == 2 ? "Adjuntar " + leyendas.get(1) : "");
                    getSession().removeAttribute("mapOficio");
                    cargaPromocionesOficio();
                    cargaProrrogasOficio();
                    getCargaDoctosAttributesHelper().setContextoJarFirmante(getContextoAplicativo());
                } else {
                    oficioSession = null;
                    addErrorMessage(null, "Se perdio la sesion", "");
                }
            }

        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public void handleFileUploadPromocion(FileUploadEvent event) throws IOException {
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            setArchivoPromocion(event.getFile());
            FecetPromocion dto = new FecetPromocion();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaPropuestasArchivoUtil.limpiarPathArchivo(CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getArchivoPromocion().getFileName())));
            dto.setArchivo(getArchivoPromocion().getInputstream());
            if (getCargaDoctosDTOHelper().getListaPromocionesCargadas() == null) {
                getCargaDoctosDTOHelper().setListaPromocionesCargadas(new ArrayList<FecetPromocion>());
                getCargaDoctosDTOHelper().getListaPromocionesCargadas().add(dto);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } else {
                addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
            }
            getCargaDoctosAttributesHelper().setMostrarPruebasAlegatos(true);
        } else {
            setArchivoPromocionOficio(event.getFile());
            FecetPromocionOficio dto = new FecetPromocionOficio();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaPropuestasArchivoUtil.limpiarPathArchivo(CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getArchivoPromocionOficio().getFileName())));
            dto.setArchivo(getArchivoPromocionOficio().getInputstream());
            if (getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas() == null) {
                getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(new ArrayList<FecetPromocionOficio>());
                getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas().add(dto);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } else {
                addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
            }
            getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(true);
        }

    }

    public void handleFileUploadPruebasAlegatos(FileUploadEvent event) throws IOException {
        final Long tamanioMB = 1024L;
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            setArchivoPruebasAlegatos(event.getFile());
            FecetAlegato dto = new FecetAlegato();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaPropuestasArchivoUtil.limpiarPathArchivo(CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getArchivoPruebasAlegatos().getFileName())));
            dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
            if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasAlegato(dto.getNombreArchivo(), getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas())) {

                addErrorMessage(null, "No puede subir el mismo archivo", "");

            } else {
                if (getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas() == null || getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas().isEmpty()) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(new ArrayList<FecetAlegato>());
                }
                try {
                    dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
                    dto.setTamanioArchivo((getArchivoPruebasAlegatos().getSize() / tamanioMB));
                    getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas().add(dto);
                    addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                    RequestContext.getCurrentInstance().update("formCargaDocumentosOrden:flUpPruebas");
                } catch (IOException e) {
                    addErrorMessage(null, Constantes.FALLA_CARGA, "");
                }
            }
        } else {
            setArchivoPruebasAlegatosOficio(event.getFile());
            FecetAlegatoOficio dto = new FecetAlegatoOficio();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(CargaPropuestasArchivoUtil.limpiarPathArchivo(CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getArchivoPruebasAlegatosOficio().getFileName())));
            dto.setArchivo(getArchivoPruebasAlegatosOficio().getInputstream());
            if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasAlegatoOficio(dto.getNombreArchivo(),
                    getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas())) {
                addErrorMessage(null, "No puede subir el mismo archivo", "");

            } else {
                if (getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas() == null
                        || getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas().isEmpty()) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
                }
                try {
                    dto.setArchivo(getArchivoPruebasAlegatosOficio().getInputstream());
                    dto.setTamanioArchivo((getArchivoPruebasAlegatosOficio().getSize() / tamanioMB));
                    getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas().add(dto);
                    addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
                    RequestContext.getCurrentInstance().update("formCargaDocumentosOficio:flUpPruebas");
                } catch (IOException e) {
                    addErrorMessage(null, Constantes.FALLA_CARGA, "");
                }

            }
        }
    }

    public void limpiarPromocion() {
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            if (getCargaDoctosDTOHelper().getListaPromocionesCargadas() != null) {
                getCargaDoctosDTOHelper().getListaPromocionesCargadas().clear();
                getCargaDoctosDTOHelper().setListaPromocionesCargadas(null);
                getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(null);
                setArchivoPromocion(null);
                getCargaDoctosAttributesHelper().setMostrarPruebasAlegatos(false);
                getCargaDoctosDTOHelper().setPromocionSeleccionada(null);
                RequestContext.getCurrentInstance().update("formCargaDocumentosOrden:flUpPromo");
            }
        } else {
            if (getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas() != null) {
                getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas().clear();
                getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(null);
                getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(null);
                setArchivoPromocionOficio(null);
                getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(false);
                RequestContext.getCurrentInstance().update("formCargaDocumentosOficio:flUpPromo");
            }
        }

    }

    public void descartarPruebaLista() {
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            List<FecetAlegato> listaNueva = new ArrayList<FecetAlegato>();
            for (FecetAlegato alegato : getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas()) {
                if (!alegato.getNombreArchivo().equals(getCargaDoctosDTOHelper().getPruebaSeleccionada().getNombreArchivo())) {
                    listaNueva.add(alegato);
                }
            }
            getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(listaNueva);
        } else {
            List<FecetAlegatoOficio> listaNueva = new ArrayList<FecetAlegatoOficio>();
            for (FecetAlegatoOficio alegato : getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas()) {
                if (!alegato.getNombreArchivo().equals(getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getNombreArchivo())) {
                    listaNueva.add(alegato);
                }
            }
            getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(listaNueva);
        }
    }

    public String regresa() {
        limpiarVariables();
        return "/propuestas/programador/validarYRetroalimentar/ordenCambioMetodo.jsf?faces-redirect=true";
    }

    private void limpiarVariables() {
        setArchivoPromocion(null);
        setArchivoPruebasAlegatos(null);
        setArchivoProrroga(null);
        setArchivoPromocionOficioHistoricoDescargaAcuse(null);
        setArchivoPromocionOficio(null);
        setArchivoPruebasAlegatosOficio(null);
        setAcuseFilePromocionOficio(null);
        setArchivoDescargaHistoricoPromocionOficio(null);
        setAcuseFilePromocion(null);

        getCargaDoctosDTOHelper().setOrdenSeleccionado(null);
        getCargaDoctosDTOHelper().setPerfil(null);
        getCargaDoctosDTOHelper().setListaPromocionesCargadas(null);
        getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(null);
        getCargaDoctosDTOHelper().setPruebaSeleccionada(null);
        getCargaDoctosDTOHelper().setListaCadenas(null);
        getCargaDoctosDTOHelper().setPromocionSeleccionada(null);
        getCargaDoctosDTOHelper().setOficioSeleccionado(null);
        getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(null);
        getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(null);
        getCargaDoctosDTOHelper().setPromocionOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setPruebaOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPromocionesOficio(null);
        getCargaDoctosDTOHelper().setPromocionHistoricoOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatos(null);
        getCargaDoctosDTOHelper().setAcuse(null);

        getCargaDoctosAttributesHelper().setIdPromocionOficioSeleccionadaHistorico(null);
        getCargaDoctosAttributesHelper().setFechaEnvioPromocionOficioSeleccionadaHistorico(null);
        getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(false);
        getCargaDoctosAttributesHelper().setContextoJarFirmante(null);
        getCargaDoctosAttributesHelper().setEstadoRegresa(false);
        getCargaDoctosAttributesHelper().setRfc12(null);
        getCargaDoctosAttributesHelper().setNumCertificado(null);
        getCargaDoctosAttributesHelper().setJsonFirmado(null);
        getCargaDoctosAttributesHelper().setRutaArchivoAcusePromocion(null);
        getCargaDoctosAttributesHelper().setMostrarPruebasAlegatos(false);
    }

    public void getHistoricoPruebasAlegatosPromocionOficio() {
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatos(getCargarFirmaPruebasPromoService().getPruebasAlegatosPromocionOficio(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getIdPromocionOficio()));
        getCargaDoctosAttributesHelper().setIdPromocionOficioSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getIdPromocionOficio());
        getCargaDoctosAttributesHelper().setFechaEnvioPromocionOficioSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getFechaCarga());
    }

    private void cargaPromocionesOficio() {
        getCargaDoctosDTOHelper().setListaHistoricoPromocionesOficio(getCargarFirmaPruebasPromoService().getPromocionOficioContadorPruebasAlegatos(getCargaDoctosDTOHelper().getOficioSeleccionado()));
    }

    private void cargaProrrogasOficio() {
        getCargaDoctosDTOHelper().setListaProrrogaOficio(getCargarFirmaProrrogaService().getHistoricoProrrogaOficio(getCargaDoctosDTOHelper().getOficioSeleccionado().getIdOficioPrincipal()));
    }

    private String getContextoAplicativo() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return externalContext.getRequestContextPath() + "/faces/resources/applet/AppletFirmaM.jar";
    }

    public void cargaProrrogas() {
        getCargaDoctosDTOHelper().setListaProrrogaOficio(getCargarFirmaProrrogaService().getHistoricoProrrogaOficio(getCargaDoctosDTOHelper().getOficioSeleccionado().getIdOficio()));
    }

    public String cargaProrrogaOficio() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL_SESSION, getCargaDoctosDTOHelper().getPerfil());
        map.put("orden", getCargaDoctosDTOHelper().getOrdenSeleccionado());
        map.put("oficio", getCargaDoctosDTOHelper().getOficioSeleccionado());
        getSession().setAttribute("mapProrrogaOficio", map);
        return "../cargaDocumentacion/cargaProrrogasOficio.jsf";
    }

    public void getDocsProrrogaOficio() {
        getCargaDoctosDTOHelper().setListaDocProrrogaOficio(getCargarFirmaProrrogaService().getDocsProrrogaOficio(getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getIdProrrogaOficio()));
        getCargaDoctosAttributesHelper().setIdProrrogaOficioSeleccionado(getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getIdProrrogaOficio());
        getCargaDoctosAttributesHelper().setFechaEnvioProrrogaOficioSeleccionada(getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getFechaCarga());
    }

}
