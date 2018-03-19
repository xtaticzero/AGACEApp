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
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.carga.CargaDocumentosAbstractMB;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosAttributesHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosDTOHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosFunctionHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;


@SessionScoped
@ManagedBean(name = "cargaDocumentosManagedBean")
public class CargaDocumentosManagedBean extends CargaDocumentosAbstractMB {

    private static final long serialVersionUID = 1L;

    public CargaDocumentosManagedBean() {
        setCargaDoctosAttributesHelper(new CargaDocumentosAttributesHelper());
        setCargaDoctosDTOHelper(new CargaDocumentosDTOHelper());
    }

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {
        HashMap mapOrden = (HashMap)getSession().getAttribute("mapOrden");
        HashMap mapOficio = (HashMap)getSession().getAttribute("mapOficio");

        if (mapOrden != null) {
            getCargaDoctosAttributesHelper().setFromOrden(true);
            AgaceOrden ordenSession = (AgaceOrden)mapOrden.get("orden");
            PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO)mapOrden.get(PERFIL_SESSION);
            if (ordenSession != null && perfilSession != null) {
                getCargaDoctosDTOHelper().setOrdenSeleccionado(ordenSession);
                
                getCargaDoctosDTOHelper().setPerfil(perfilSession);
                getSession().removeAttribute("mapOrden");
                List<String> leyendas =
                    getCargarFirmaPruebasPromoService().getLeyendaDoc(getCargaDoctosDTOHelper().getOrdenSeleccionado(),
                                                                      null);
                getCargaDoctosAttributesHelper().setLeyenda(leyendas.isEmpty() ? "" : leyendas.get(0));
                getCargaDoctosAttributesHelper().setLeyendaDoc(leyendas.isEmpty() ? "" :
                                                               "Adjuntar " + leyendas.get(0));
            } else {
                addErrorMessage(null, "Se perdio la sesion", "");
            }
        } else if (mapOficio != null) {
            getCargaDoctosAttributesHelper().setFromOrden(false);
            AgaceOrden ordenSession = (AgaceOrden)mapOficio.get("orden");
            PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO)mapOficio.get(PERFIL_SESSION);
            FecetOficio oficioSession = (FecetOficio)mapOficio.get("oficio");
            if (ordenSession != null && perfilSession != null && oficioSession != null) {
                getCargaDoctosDTOHelper().setOrdenSeleccionado(ordenSession);
                getCargaDoctosDTOHelper().setPerfil(perfilSession);
                getCargaDoctosDTOHelper().setOficioSeleccionado(oficioSession);
                List<String> leyendas =
                    getCargarFirmaPruebasPromoService().getLeyendaDoc(getCargaDoctosDTOHelper().getOrdenSeleccionado(),
                                                                      getCargaDoctosDTOHelper().getOficioSeleccionado());
                getCargaDoctosAttributesHelper().setLeyenda(leyendas.size() == 2 ? leyendas.get(1) : "");
                getCargaDoctosAttributesHelper().setLeyendaDoc(leyendas.size() == 2 ? "Adjuntar " + leyendas.get(1) :
                                                               "");
                getSession().removeAttribute("mapOficio");
                cargaPromocionesOficio();
                cargaProrrogasOficio();

            } else {
                oficioSession = null;
                addErrorMessage(null, "Se perdio la sesion", "");
            }
        }
        
    }
    
    public void validaDiasExpiradosOrden(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if(getCargaDoctosDTOHelper().getOrdenSeleccionado().getDiasRestantesDocumentos().equals(0)){            
            requestContext.execute("PF('avisoDiasExp').show();");
        }
        else{
            requestContext.execute("PF('confirmarGuardado').show();");
        }
    }

    public void handleFileUploadPromocion(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            if (getCargaDoctosDTOHelper().getListaPromocionesCargadas() == null) {
                setArchivoPromocion(event.getFile());
                FecetPromocion dto = new FecetPromocion();
                final Date fecha = new Date();
                dto.setFechaCarga(fecha);
                dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoPromocion().getFileName())));
                dto.setArchivo(getArchivoPromocion().getInputstream());
                getCargaDoctosDTOHelper().setListaPromocionesCargadas(new ArrayList<FecetPromocion>());

                archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
                archivoTemp.setArchivoByte(getArchivoPromocion().getContents());
                archivoTemp.setFecha(fecha);

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getCargaDoctosDTOHelper().getListaPromocionesCargadas().add(dto);
                getCargaDoctosAttributesHelper().setVisibleBtnEliminarOrden(true);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } else {
                addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
            }
            getCargaDoctosAttributesHelper().setMostrarPruebasAlegatos(true);
        } else {           
            if (getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas() == null) {
                setArchivoPromocionOficio(event.getFile());
                FecetPromocionOficio dto = new FecetPromocionOficio();
                final Date fecha = new Date();
                dto.setFechaCarga(fecha);
                dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoPromocionOficio().getFileName())));
                dto.setArchivo(getArchivoPromocionOficio().getInputstream());
                getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(new ArrayList<FecetPromocionOficio>());

                archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
                archivoTemp.setArchivoByte(getArchivoPromocionOficio().getContents());
                archivoTemp.setFecha(fecha);

                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

                getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas().add(dto);
                getCargaDoctosAttributesHelper().setVisibleBtnEliminarOficio(true);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } else {
                addErrorMessage(null, "Error al cargar el archivo, solo puede cargar un solo archivo", "");
            }
            getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(true);
        }

    }

    public void handleFileUploadPruebasAlegatos(FileUploadEvent event) throws IOException {
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Long tamanioMB = 1024L;
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            setArchivoPruebasAlegatos(event.getFile());
            FecetAlegato dto = new FecetAlegato();
            final Date fecha = new Date();
            dto.setFechaCarga(fecha);
            dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoPruebasAlegatos().getFileName())));
            dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
            if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasAlegato(dto.getNombreArchivo(),
                                                                            getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas())) {

                addErrorMessage(null, "No puede subir el mismo archivo", "");

            } else {
                if (getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas() == null ||
                    getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas().isEmpty()) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(new ArrayList<FecetAlegato>());
                }
                try {
                    dto.setArchivo(getArchivoPruebasAlegatos().getInputstream());
                    dto.setTamanioArchivo((getArchivoPruebasAlegatos().getSize() / tamanioMB));

                    archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
                    archivoTemp.setArchivoByte(getArchivoPruebasAlegatos().getContents());
                    archivoTemp.setFecha(fecha);

                    dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

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
            dto.setNombreArchivo(ContribuyenteCargaArchivosUtil.limpiarPathArchivo(ContribuyenteCargaArchivosUtil.aplicarCodificacionTexto(getArchivoPruebasAlegatosOficio().getFileName())));
            dto.setArchivo(getArchivoPruebasAlegatosOficio().getInputstream());
            if (CargaDocumentosFunctionHelper.checarDuplicadoPruebasAlegatoOficio(dto.getNombreArchivo(),
                                                                                  getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas())) {
                addErrorMessage(null, "No puede subir el mismo archivo", "");

            } else {
                if (getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas() == null ||
                    getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas().isEmpty()) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
                }
                try {
                    dto.setArchivo(getArchivoPruebasAlegatosOficio().getInputstream());
                    dto.setTamanioArchivo((getArchivoPruebasAlegatosOficio().getSize() / tamanioMB));

                    archivoTemp.setSessionUUID(getCargaDoctosDTOHelper().getPerfil().getRfc());
                    archivoTemp.setArchivoByte(getArchivoPruebasAlegatosOficio().getContents());
                    archivoTemp.setFecha(fecha);

                    dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

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
                getCargaDoctosAttributesHelper().setVisibleBtnEliminarOrden(false);
                RequestContext.getCurrentInstance().update("formCargaDocumentosOrden:flUpPromo");
            }
        } else {
            if (getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas() != null) {
                getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas().clear();
                getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(null);
                getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(null);
                setArchivoPromocionOficio(null);
                getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(false);
                getCargaDoctosAttributesHelper().setVisibleBtnEliminarOficio(false);
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

    public String enviarFirma() {
        if (getCargaDoctosAttributesHelper().isFromOrden()) {
            if (CargaDocumentosFunctionHelper.validaArchivosCargados(getCargaDoctosDTOHelper().getListaPromocionesCargadas())) {
                if (CargaDocumentosFunctionHelper.validaLista(getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas())) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(new ArrayList<FecetAlegato>());
                }
                getCargaDoctosDTOHelper().setPromocionSeleccionada(getCargaDoctosDTOHelper().getListaPromocionesCargadas().get(0));
                getCargaDoctosDTOHelper().getPromocionSeleccionada().setLeyenda(getCargaDoctosAttributesHelper().getLeyenda());
                PromocionDocsVO docs = new PromocionDocsVO();
                docs.setNombreArchivo(getArchivoPromocion().getFileName());
                docs.setOrdenSeleccionado(getCargaDoctosDTOHelper().getOrdenSeleccionado());
                docs.setPromocionSeleccionado(getCargaDoctosDTOHelper().getPromocionSeleccionada());
                docs.setPerfil(getCargaDoctosDTOHelper().getPerfil());
                docs.setSizeOrden(getArchivoPromocion().getSize());
                docs.setListaPruebasAlegatosCargadas(getCargaDoctosDTOHelper().getListaPruebasAlegatosCargadas());
                getSession().setAttribute("promocionFirmar", docs);
                limpiarVariables();                          
                return "firmaPromocion?faces-redirect=true";
            } else {
                addErrorMessage(null, getMessageResourceString("error.debe.seleccionar.archivo"), "");
                return null;
            }
        } else {
            if (CargaDocumentosFunctionHelper.validaArchivosOficioCargados(getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas())) {
                if (CargaDocumentosFunctionHelper.validaLista(getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas())) {
                    getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
                }
                getCargaDoctosDTOHelper().setPromocionOficioSeleccionada(getCargaDoctosDTOHelper().getListaPromocionesOficioCargadas().get(0));
                getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().setLeyenda(getCargaDoctosAttributesHelper().getLeyenda());
                PromocionDocsVO docs = new PromocionDocsVO();
                docs.setOrdenSeleccionado(getCargaDoctosDTOHelper().getOrdenSeleccionado());
                docs.setNombreArchivo(getArchivoPromocionOficio().getFileName());
                docs.setPromocionOficioSeleccionado(getCargaDoctosDTOHelper().getPromocionOficioSeleccionada());
                docs.setOficioSeleccionado(getCargaDoctosDTOHelper().getOficioSeleccionado());
                docs.setPerfil(getCargaDoctosDTOHelper().getPerfil());
                docs.setSizeOficio(getArchivoPromocionOficio().getSize());
                docs.setListaPruebasAlegatosOficioCargadas(getCargaDoctosDTOHelper().getListaPruebasAlegatosOficioCargadas());
                getSession().setAttribute("promocionOficioFirmar", docs);
                limpiarVariables();
                return "firmaPromocionOficio?faces-redirect=true";
            } else {
                addErrorMessage(null, getMessageResourceString(MSN_ERROR_ARCHIVO), "");
                return null;
            }
        }
    }

    public String regresa() {
        limpiarVariables();
        return "../asociarColaboradores/indexAsociar.jsf";
    }

    private void limpiarVariables() {
        getCargaDoctosAttributesHelper().setMostrarPruebasAlegatos(false);
        getCargaDoctosAttributesHelper().setMostrarPruebasAlegatosOficio(false);
        getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(false);
        getCargaDoctosAttributesHelper().setRutaArchivoAcusePromocion(null);
        getCargaDoctosAttributesHelper().setIdPromocionOficioSeleccionadaHistorico(null);
        getCargaDoctosAttributesHelper().setFechaEnvioPromocionOficioSeleccionadaHistorico(null);

        getCargaDoctosDTOHelper().setOrdenSeleccionado(null);
        getCargaDoctosDTOHelper().setPerfil(null);
        getCargaDoctosDTOHelper().setListaPromocionesCargadas(null);
        getCargaDoctosDTOHelper().setListaPruebasAlegatosCargadas(null);
        getCargaDoctosDTOHelper().setPruebaSeleccionada(null);
        getCargaDoctosDTOHelper().setAcuse(null);
        getCargaDoctosDTOHelper().setPromocionSeleccionada(null);
        getCargaDoctosDTOHelper().setOficioSeleccionado(null);
        getCargaDoctosDTOHelper().setListaPromocionesOficioCargadas(null);
        getCargaDoctosDTOHelper().setListaPruebasAlegatosOficioCargadas(null);
        getCargaDoctosDTOHelper().setPromocionOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setPruebaOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPromocionesOficio(null);
        getCargaDoctosDTOHelper().setPromocionHistoricoOficioSeleccionada(null);
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatos(null);

        setArchivoPromocion(null);
        setArchivoPruebasAlegatos(null);
        setArchivoProrroga(null);
        setAcuseFilePromocion(null);
        setArchivoPromocionOficio(null);
        setArchivoPruebasAlegatosOficio(null);
        setAcuseFilePromocionOficio(null);
        setArchivoDescargaHistoricoPromocionOficio(null);
        setArchivoPromocionOficioHistoricoDescargaAcuse(null);

    }

    /**
     *
     */
    public void getHistoricoPruebasAlegatosPromocionOficio() {
        getCargaDoctosDTOHelper().setListaHistoricoPruebasAlegatos(getCargarFirmaPruebasPromoService().getPruebasAlegatosPromocionOficio(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getIdPromocionOficio()));
        getCargaDoctosAttributesHelper().setIdPromocionOficioSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getIdPromocionOficio());
        getCargaDoctosAttributesHelper().setFechaEnvioPromocionOficioSeleccionadaHistorico(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getFechaCarga());
    }

    private void cargaPromocionesOficio() {
        try {
            getCargaDoctosDTOHelper().setListaHistoricoPromocionesOficio(getCargarFirmaPruebasPromoService().getPromocionOficioContadorPruebasAlegatos(
                    getCargaDoctosDTOHelper().getOrdenSeleccionado(), getCargaDoctosDTOHelper().getOficioSeleccionado()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(null, "ERROR AL CARGAR PROMOCIONES DE OFICIO", "");
        }
    }

    private void cargaProrrogasOficio() {
        getCargaDoctosAttributesHelper().setMostrarProrrogasOficio(getCargarFirmaProrrogaService().validaMetodoProrrogaOficio(getCargaDoctosDTOHelper().getOrdenSeleccionado(), getCargaDoctosDTOHelper().getOficioSeleccionado()));
        if (getCargaDoctosAttributesHelper().isMostrarProrrogasOficio()) {
            getCargaDoctosDTOHelper().setListaProrrogaOficio(getCargarFirmaProrrogaService().getHistoricoProrrogaOficioContribuyente(getCargaDoctosDTOHelper().getOficioSeleccionado().getIdOficio()));
            for (FecetProrrogaOficio prorroga : getCargaDoctosDTOHelper().getListaProrrogaOficio()) {
                if (prorroga.getIdEstatus().equals(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus()) || prorroga.getIdEstatus().equals(EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus())) {
                    if (prorroga.getAprobada()) {
                        prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.aprobada"));
                    } else {
                        prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.no.aprobada"));
                    }
                }
                
                prorroga.setMostrarDocRes(getCargarFirmaProrrogaService().validaDocResProrroga(prorroga));
            }
            
            
            if(getCargarFirmaProrrogaService().validaOficioAdministrable(getCargaDoctosDTOHelper().getOficioSeleccionado())){
                if (getCargarFirmaProrrogaService()
                        .validaOficioConProrrogas(getCargaDoctosDTOHelper().getOficioSeleccionado())) {
                    if (getCargaDoctosDTOHelper().getListaProrrogaOficio().isEmpty()) {
                        getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(true);
                    } else {
                        getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(getCargarFirmaProrrogaService()
                                .validaProrrogaEstatus(getCargaDoctosDTOHelper().getListaProrrogaOficio(),
                                        getCargaDoctosDTOHelper().getOficioSeleccionado()));
                    }
                } else {
                    getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(false);
                    getCargaDoctosAttributesHelper().setMostrarProrrogasOficio(false);
                    
                }
                if (getCargarFirmaProrrogaService().validaOficioConPlazos(getCargaDoctosDTOHelper().getOficioSeleccionado())
                        && getCargaDoctosDTOHelper().getOficioSeleccionado().getDescripcionPlazoRestante().equals(Constantes.PLAZO_RESTANTE_VENCIDO)) {
                    getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(false);
                }

            }
            else{                
                if(getCargaDoctosDTOHelper().getListaProrrogaOficio().isEmpty()){
                    getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(true);
                } else{
                    getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(
                            getCargarFirmaProrrogaService().validaProrrogaEstatus(getCargaDoctosDTOHelper().getListaProrrogaOficio(), getCargaDoctosDTOHelper().getOficioSeleccionado()));
                }
                if(getCargaDoctosDTOHelper().getOficioSeleccionado().getDescripcionPlazoRestante().equals(Constantes.PLAZO_RESTANTE_VENCIDO)){
                    getCargaDoctosAttributesHelper().setMostrarBtnProrrogasOficio(false);
                }
            }     
        }
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
