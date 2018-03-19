package mx.gob.sat.siat.feagace.vista;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilContribuyente;
import mx.gob.sat.siat.feagace.vista.common.AsociarColaboradoresSubMBAbstract;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresAttributeHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresDTOHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresFileStreamedHelper;
import mx.gob.sat.siat.feagace.vista.helper.AsociarColaboradoresListHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@SessionScoped
@ManagedBean(name = "asociarColaboradoresManagedBean")
public class AsociarColaboradoresManagedBean extends AsociarColaboradoresSubMBAbstract {

    private static final String MSG_VALIDACION = "Debe introducir un RFC y Nombre";
    private static final String MSG_SIN_DOCUMENTOS = "Debe agregar la documentaci\u00f3n correspondiente al Representante Legal que desea asociar";
    private static final String MSG_SIN_DOCUMENTOS_AL = "Debe agregar la documentaci\u00f3n correspondiente al Apoderado Legal que desea asociar";
    private static final String MSG_SIN_DOCUMENTOS_ALRL = "Debe agregar la documentaci\u00f3n correspondiente al Apoderado Legal del Representante Legal que desea asociar";
    private static final long serialVersionUID = 6765339202531440786L;
    private static final String LABEL_CONFIRMAR_SIN_ASOCIADO = "label.confirmar.sin.asociado";
    private static final String SIGNO_CIERRA = "?. ";
    private static final String LABEL_CONFIRMAR_SIN_ASOCIADO_END = "label.confirmar.sin.asociado.end";
    private static final String PF_CONFIRMAR_ORDEN_SIN_ASOCIADO_SHOW = "PF('confirmarOrdenSinAsociado').show();";

    public AsociarColaboradoresManagedBean() {
        setAscColAttribHelper(new AsociarColaboradoresAttributeHelper());
        setAscColDTOHelper(new AsociarColaboradoresDTOHelper());
        setAscColListHelper(new AsociarColaboradoresListHelper());
        setAscColFileStreamedHelper(new AsociarColaboradoresFileStreamedHelper());
    }

    @PostConstruct
    public void init() {
        try {
            PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) getSession().getAttribute(PERFIL);
            getAscColFileStreamedHelper().setArchivoDescargaAcuse((StreamedContent) getSession().getAttribute("promocionAcuse"));
            getAscColFileStreamedHelper().setArchivoDescargaAcuseProrroga((StreamedContent) getSession().getAttribute("prorrogaAcuse"));
            getAscColFileStreamedHelper().setArchivoDescargaAcuseCompulsa((StreamedContent) getSession().getAttribute("promocionCompulsa"));
            getAscColFileStreamedHelper().setArchivoDescargaAcuseProrrogaCompulsa((StreamedContent) getSession().getAttribute("prorrogaCompulsaAcuse"));
            getAscColFileStreamedHelper().setArchivoDescargaAcusePruebasPericiales((StreamedContent) getSession().getAttribute("pruebaPericialAcuse"));
            if (perfilSession != null) {
                getAscColAttribHelper().setMonstrarPanelOrdenes(false);
                getAscColAttribHelper().setMostrarPanelOrdenAsociarColaboradores(false);
                inicializaColaboradores();
                getAscColDTOHelper().setPerfil(perfilSession);
                perfilSession = null;
                getSession().removeAttribute(PERFIL);
                cargaVistaContribuyente();
            } else if (getAscColFileStreamedHelper().getArchivoDescargaAcuse() != null) {
                getSession().removeAttribute("promocionAcuse");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                cargaPromociones();
                requestContext.execute("PF('acuseCreado').show();");
            } else if (getAscColFileStreamedHelper().getArchivoDescargaAcuseProrroga() != null) {
                getSession().removeAttribute("prorrogaAcuse");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                cargaProrrogas();
                requestContext.execute("PF('acuseProrrogaCreado').show();");
            } else if (getAscColFileStreamedHelper().getArchivoDescargaAcuseCompulsa() != null) {
                getSession().removeAttribute("promocionCompulsa");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                cargaVistaContribuyente();
                requestContext.execute("PF('acuseCompulsaCreado').show();");
            } else if (getAscColFileStreamedHelper().getArchivoDescargaAcuseProrrogaCompulsa() != null) {
                getSession().removeAttribute("prorrogaCompulsaAcuse");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                cargaVistaContribuyente();
                requestContext.execute("PF('acuseProrrogaCompulsaCreado').show();");

            } else if (getAscColFileStreamedHelper().getArchivoDescargaAcusePruebasPericiales() != null) {
                getSession().removeAttribute("pruebaPericialAcuse");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                cargaVistaContribuyente();
                requestContext.execute("PF('acusePruebasPericialesCreado').show();");
            }
        } catch (Exception e) {
            addErrorMessage(null, e.getMessage());
            logger.error(e.getMessage());

        }

    }

    private void inicializaColaboradores() {
        getAscColDTOHelper().setApoderadoLegalVO(new ColaboradorVO());
        getAscColDTOHelper().getApoderadoLegalVO().setTipoAsociado(Constantes.ID_APODERADO_LEGAL);
        getAscColDTOHelper().getApoderadoLegalVO().setNombreTipoAsociado(Constantes.APODERADO_LEGAL);
        getAscColDTOHelper().setRepresentanteLegalVO(new ColaboradorVO());
        getAscColDTOHelper().getRepresentanteLegalVO().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
        getAscColDTOHelper().getRepresentanteLegalVO().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
        getAscColDTOHelper().setApoderadoLegalRLVO(new ColaboradorVO());
        getAscColDTOHelper().getApoderadoLegalRLVO().setTipoAsociado(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getAscColDTOHelper().getApoderadoLegalRLVO().setNombreTipoAsociado(Constantes.APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getAscColAttribHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
        getAscColDTOHelper().setAgenteAduanalVO(new ColaboradorVO());
        getAscColDTOHelper().getAgenteAduanalVO().setTipoAsociado(Constantes.ID_AGENTE_ADUANAL);
        getAscColDTOHelper().getAgenteAduanalVO().setNombreTipoAsociado(Constantes.AGENTE_ADUANAL);
    }

    private void cargaVistaContribuyente() {
        try {
            getAscColAttribHelper().setMonstrarPanelOrdenes(true);
            getAscColAttribHelper().setMostrarDatosApoderadoLegal(
                    getAsociadosValidacionService().validaVistaApoderadoLegal(getAscColDTOHelper().getPerfil().getIdTipoAsociado()));
            getAscColAttribHelper().setMostrarPanelOrdenAsociarColaboradores(false);
            if (getAscColDTOHelper().getPerfil().getRfcContribuyente() == null) {
                getAscColListHelper().setListaOrdenes(getAsociadosService().obtenerOrdenesContribuyente(getAscColDTOHelper().getPerfil().getRfc(),
                        getAscColDTOHelper().getPerfil().getIdTipoAsociado()));
                getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().obtenerApoderadoLegalContribuyente(getAscColDTOHelper().getPerfil().getRfc(),
                        getAscColDTOHelper().getApoderadoLegalVO()));
                cargaCompulsas();
            } else {
                if (getAscColDTOHelper().getPerfil().getIdTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL)) {
                    getAscColListHelper().setListaOrdenes(getAsociadosService().obtenerOrdenesContribuyente(
                            getAscColDTOHelper().getPerfil().getRfcContribuyente(), getAscColDTOHelper().getPerfil().getIdTipoAsociado()));
                } else {
                    getAscColListHelper().setListaOrdenes(getAsociadosService().obtenerOrdenesAsociado(getAscColDTOHelper().getPerfil().getRfc(),
                            getAscColDTOHelper().getPerfil().getIdTipoAsociado(), getAscColDTOHelper().getPerfil().getRfcContribuyente()));
                }
                getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().obtenerApoderadoLegalContribuyente(
                        getAscColDTOHelper().getPerfil().getRfcContribuyente(), getAscColDTOHelper().getApoderadoLegalVO()));
            }
            getAscColDTOHelper().setApoderadoLegalOriginalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getApoderadoLegalVO()));
            cargarDocApoderadoLegal();
            getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
            if (getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
                getAscColAttribHelper().setMostrarOrdenSinAL(true);
            }
        } catch (Exception e) {
            addErrorMessage(null, e.getMessage());
            logger.error(e.getMessage());

        }

    }

    public void cargaCompulsas() {
        getAscColListHelper().setListaCompulsas(getAsociadosService().obtenerCompulsasContribuyente(getAscColDTOHelper().getPerfil().getRfc()));
    }

    public void nuevaBusquedaColaborador() {
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().nuevaBusquedaColaborador(getAscColDTOHelper().getApoderadoLegalVO()));
            getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
            getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaAL(true);
            getAscColAttribHelper().setMostrarOrdenSinAL(false);
            getAscColListHelper().setDocumentosAL(new ArrayList<FecetDocAsociado>());
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setRepresentanteLegalVO(getAsociadosService().nuevaBusquedaColaborador(getAscColDTOHelper().getRepresentanteLegalVO()));
            getAscColAttribHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
            getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(false);
            getAscColListHelper().setDocumentosRL(new ArrayList<FecetDocAsociado>());
            getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaRL(true);
            getAscColAttribHelper().setMostrarOrdenSinRL(false);
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalRLVO(getAsociadosService().nuevaBusquedaColaborador(getAscColDTOHelper().getApoderadoLegalRLVO()));
            getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(false);
            getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaALRL(true);
            getAscColAttribHelper().setMostrarOrdenSinALRL(false);
            getAscColListHelper().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAscColDTOHelper().setAgenteAduanalVO(getAsociadosService().nuevaBusquedaColaborador(getAscColDTOHelper().getAgenteAduanalVO()));
            getAscColAttribHelper().setMostrarGuardarAgenteAduanal(false);
        }
    }

    public void cancelarNuevoColaborador() {
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getApoderadoLegalOriginalVO()));
            getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaAL(false);
            getAscColListHelper().setDocumentosAL(new ArrayList<FecetDocAsociado>());
            getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(false);
            getAscColListHelper().setDocumentosAL(getAscColListHelper().getDocumentosALOriginal());
            if (getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
                getAscColAttribHelper().setMostrarOrdenSinAL(true);
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setRepresentanteLegalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getRepresentanteLegalOriginalVO()));
            getAscColAttribHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
            getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaRL(false);
            getAscColListHelper().setDocumentosRL(new ArrayList<FecetDocAsociado>());
            getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(false);
            getAscColListHelper().setDocumentosRL(getAscColListHelper().getDocumentosRLOriginal());
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalRLVO(getAscColDTOHelper().getApoderadoLegalRLOriginalVO());
            getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaALRL(false);
            getAscColListHelper().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
            getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);
            getAscColListHelper().setDocumentosAL(getAscColListHelper().getDocumentosALOriginal());
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAscColDTOHelper().setAgenteAduanalVO(getAscColDTOHelper().getAgenteAduanalOriginalVO());
            getAscColAttribHelper().setMostrarGuardarAgenteAduanal(false);
        }
    }

    public void validaSinApoderadoLegal() {
        if (getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().sinColaborador(getAscColDTOHelper().getApoderadoLegalVO()));
            getAscColAttribHelper().setMostrarGuardarApoderadoLegal(true);
            getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaAL(false);
            getAscColListHelper().setDocumentosAL(new ArrayList<FecetDocAsociado>());
        } else {
            getAscColAttribHelper().setTipoColaborador(Constantes.JSF_APODERADO_LEGAL);
            cancelarNuevoColaborador();
        }
    }

    public void validaSinRepresentanteLegal() {
        if (getAscColDTOHelper().getRepresentanteLegalVO().isSinColaborador()) {
            getAscColAttribHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
            getAscColDTOHelper().setRepresentanteLegalVO(getAsociadosService().sinColaborador(getAscColDTOHelper().getRepresentanteLegalVO()));
            getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(true);
            getAscColListHelper().setDocumentosRL(new ArrayList<FecetDocAsociado>());
            getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaRL(false);

        } else {
            getAscColAttribHelper().setTipoColaborador(Constantes.JSF_REPRESENTANTE_LEGAL);
            cancelarNuevoColaborador();
        }

    }

    public void validaSinApoderadoLegalRL() {
        if (getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
            getAscColDTOHelper().setApoderadoLegalRLVO(getAsociadosService().sinColaborador(getAscColDTOHelper().getApoderadoLegalRLVO()));
            getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(true);
            getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);
            getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);
            getAscColAttribHelper().setMostrarCancelarBusquedaALRL(false);
            getAscColListHelper().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
        } else {
            getAscColAttribHelper().setTipoColaborador(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL);
            cancelarNuevoColaborador();
        }

    }

    public void validaSinAgenteAduanal() {
        getAscColDTOHelper().setAgenteAduanalVO(getAsociadosService().sinColaborador(getAscColDTOHelper().getAgenteAduanalVO()));
        getAscColAttribHelper().setMostrarGuardarAgenteAduanal(true);
    }

    public void preGuardarColaborador() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            if (getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
                if (!getAscColDTOHelper().getApoderadoLegalOriginalVO().isSinColaborador()) {
                    StringBuilder leyenda = new StringBuilder();
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO));
                    leyenda.append(" ");
                    leyenda.append(getAscColDTOHelper().getApoderadoLegalOriginalVO().getNombreTipoAsociado());
                    leyenda.append(": ");
                    leyenda.append(getAscColDTOHelper().getApoderadoLegalOriginalVO().getNombre());
                    leyenda.append(SIGNO_CIERRA);
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO_END));
                    getAscColAttribHelper().setLeyendaOrdenSinAsociado(leyenda.toString());
                    requestContext.execute(PF_CONFIRMAR_ORDEN_SIN_ASOCIADO_SHOW);
                } else {
                    guardarColaborador();
                }
            } else {
                guardarColaborador();
            }

        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            if (getAscColDTOHelper().getRepresentanteLegalVO().isSinColaborador()) {
                if (!getAscColDTOHelper().getRepresentanteLegalOriginalVO().isSinColaborador()) {
                    StringBuilder leyenda = new StringBuilder();
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO));
                    leyenda.append(" ");
                    leyenda.append(getAscColDTOHelper().getRepresentanteLegalOriginalVO().getNombreTipoAsociado());
                    leyenda.append(": ");
                    leyenda.append(getAscColDTOHelper().getRepresentanteLegalOriginalVO().getNombre());
                    leyenda.append(SIGNO_CIERRA);
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO_END));
                    getAscColAttribHelper().setLeyendaOrdenSinAsociado(leyenda.toString());
                    requestContext.execute(PF_CONFIRMAR_ORDEN_SIN_ASOCIADO_SHOW);
                } else {
                    guardarColaborador();
                }
            } else {
                guardarColaborador();
            }

        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            if (getAscColDTOHelper().getApoderadoLegalRLVO().isSinColaborador()) {
                if (!getAscColDTOHelper().getApoderadoLegalRLOriginalVO().isSinColaborador()) {
                    StringBuilder leyenda = new StringBuilder();
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO));
                    leyenda.append(" ");
                    leyenda.append(getAscColDTOHelper().getApoderadoLegalRLOriginalVO().getNombreTipoAsociado());
                    leyenda.append(": ");
                    leyenda.append(getAscColDTOHelper().getApoderadoLegalRLOriginalVO().getNombre());
                    leyenda.append(SIGNO_CIERRA);
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO_END));
                    getAscColAttribHelper().setLeyendaOrdenSinAsociado(leyenda.toString());
                    requestContext.execute(PF_CONFIRMAR_ORDEN_SIN_ASOCIADO_SHOW);
                } else {
                    guardarColaborador();
                }
            } else {
                guardarColaborador();
            }

        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            if (getAscColDTOHelper().getAgenteAduanalVO().isSinColaborador()) {
                if (!getAscColDTOHelper().getAgenteAduanalOriginalVO().isSinColaborador()) {
                    StringBuilder leyenda = new StringBuilder();
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO));
                    leyenda.append(" ");
                    leyenda.append(getAscColDTOHelper().getAgenteAduanalOriginalVO().getNombreTipoAsociado());
                    leyenda.append(": ");
                    leyenda.append(getAscColDTOHelper().getAgenteAduanalOriginalVO().getNombre());
                    leyenda.append(SIGNO_CIERRA);
                    leyenda.append(FacesUtil.getMessageResourceString(LABEL_CONFIRMAR_SIN_ASOCIADO_END));
                    getAscColAttribHelper().setLeyendaOrdenSinAsociado(leyenda.toString());
                    requestContext.execute(PF_CONFIRMAR_ORDEN_SIN_ASOCIADO_SHOW);
                } else {
                    guardarColaborador();
                }
            } else {
                guardarColaborador();
            }
        }
    }

    public void guardarColaborador() {
        String mensaje;
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            if (getHelper().validaCamposColaborador(getAscColDTOHelper().getApoderadoLegalVO())) {
                addErrorMessage(null, MSG_VALIDACION, "");
            } else {
                if (getAscColListHelper().getDocumentosAL().isEmpty() && !getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
                    addErrorMessage(null, MSG_SIN_DOCUMENTOS_AL, "");
                } else {
                    mensaje = getAsociadosService().asociaColaborador(getAscColDTOHelper().getPerfil().getRfcContribuyente() == null
                            ? getAscColDTOHelper().getPerfil().getRfc() : getAscColDTOHelper().getPerfil().getRfcContribuyente(),
                            getAscColDTOHelper().getApoderadoLegalVO(), null);
                    getAscColDTOHelper().setApoderadoLegalVO(getHelper().confirmaGuardado(getAscColDTOHelper().getApoderadoLegalVO()));
                    getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
                    getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(false);

                    if (!getAscColDTOHelper().getApoderadoLegalVO().isSinColaborador()) {
                        obtenApoderadoLegal();
                        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
                        String ruta = RutaArchivosUtilContribuyente.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_RL_CONTRIBUYENTE, getRFCSession(),
                                Constantes.APODERADO_LEGAL, getAscColDTOHelper().getApoderadoLegalVO().getRfc(),
                                carpetaUnica);
                        for (FecetDocAsociado documento : getAscColListHelper().getDocumentosAL()) {
                            documento.setIdAsociado(getAscColDTOHelper().getApoderadoLegalVO().getAsociado().getIdAsociado());
                            StringBuilder rutaArchivo = new StringBuilder(ruta);
                            rutaArchivo.append(documento.getNombreArchivo());
                            documento.setRutaArchivo(rutaArchivo.toString());
                            getFecetDocAsociadoService().insertarDocAsociado(documento);
                            guardarArchivo(documento);
                        }
                        cargarDocApoderadoLegal();
                    }
                    addMessage(null, mensaje, "");
                    cargaVistaContribuyente();
                    getAuditoriaService().dasociarApoderadoLegal(getAscColDTOHelper().getApoderadoLegalVO());
                }
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            if (getHelper().validaCamposColaborador(getAscColDTOHelper().getRepresentanteLegalVO())) {
                addErrorMessage(null, MSG_VALIDACION, "");
            } else {
                if (getAscColListHelper().getDocumentosRL().isEmpty() && !getAscColDTOHelper().getRepresentanteLegalVO().isSinColaborador()) {
                    addErrorMessage(null, MSG_SIN_DOCUMENTOS, "");
                } else {
                    mensaje = getAsociadosService().asociaColaborador(
                            getAscColDTOHelper().getPerfil().getRfcContribuyente() == null ? getAscColDTOHelper().getPerfil().getRfc()
                                    : getAscColDTOHelper().getPerfil().getRfcContribuyente(),
                            getAscColDTOHelper().getRepresentanteLegalVO(), getAscColDTOHelper().getOrdenSeleccionado());
                    getAscColDTOHelper().setRepresentanteLegalVO(getHelper().confirmaGuardado(getAscColDTOHelper().getRepresentanteLegalVO()));
                    getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(false);
                    getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(false);

                    if (!getAscColDTOHelper().getRepresentanteLegalVO().isSinColaborador()) {
                        obtenRepresentanteLegal();
                        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
                        String ruta = RutaArchivosUtilContribuyente.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_RL_CONTRIBUYENTE, getRFCSession(),
                                getAscColDTOHelper().getOrdenSeleccionado().getNumeroOrden(), getAscColDTOHelper().getRepresentanteLegalVO().getRfc(),
                                carpetaUnica);
                        for (FecetDocAsociado documento : getAscColListHelper().getDocumentosRL()) {
                            documento.setIdAsociado(getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdAsociado());
                            StringBuilder rutaArchivo = new StringBuilder(ruta);
                            rutaArchivo.append(documento.getNombreArchivo());
                            documento.setRutaArchivo(rutaArchivo.toString());
                            getFecetDocAsociadoService().insertarDocAsociado(documento);
                            guardarArchivo(documento);
                        }
                        cargarDocRepresentanteLegal();
                    }
                    getAuditoriaService().asociarRepresentanteLegal(getAscColDTOHelper().getOrdenSeleccionado());
                    addMessage(null, mensaje, "");
                }
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            if (getHelper().validaCamposColaborador(getAscColDTOHelper().getApoderadoLegalRLVO())) {
                addErrorMessage(null, MSG_VALIDACION, "");
            } else {
                if (getAscColListHelper().getDocumentosALRL().isEmpty() && !getAscColDTOHelper().getApoderadoLegalRLVO().isSinColaborador()) {
                    addErrorMessage(null, MSG_SIN_DOCUMENTOS_ALRL, "");
                } else {
                    mensaje = getAsociadosService().asociaColaborador(
                            getAscColDTOHelper().getPerfil().getRfcContribuyente() == null ? getAscColDTOHelper().getPerfil().getRfc()
                                    : getAscColDTOHelper().getPerfil().getRfcContribuyente(),
                            getAscColDTOHelper().getApoderadoLegalRLVO(), getAscColDTOHelper().getOrdenSeleccionado());
                    getAscColDTOHelper().setApoderadoLegalRLVO(getHelper().confirmaGuardado(getAscColDTOHelper().getApoderadoLegalRLVO()));
                    getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(false);
                    getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);

                    if (!getAscColDTOHelper().getApoderadoLegalRLVO().isSinColaborador()) {
                        obtenApoderadoLegalRepresentanteLegal();
                        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
                        String ruta = RutaArchivosUtilContribuyente.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_RL_CONTRIBUYENTE, getRFCSession(),
                                getAscColDTOHelper().getOrdenSeleccionado().getNumeroOrden(), getAscColDTOHelper().getApoderadoLegalRLVO().getRfc(),
                                carpetaUnica);
                        for (FecetDocAsociado documento : getAscColListHelper().getDocumentosALRL()) {
                            documento.setIdAsociado(getAscColDTOHelper().getApoderadoLegalRLVO().getAsociado().getIdAsociado());
                            StringBuilder rutaArchivo = new StringBuilder(ruta);
                            rutaArchivo.append(documento.getNombreArchivo());
                            documento.setRutaArchivo(rutaArchivo.toString());
                            getFecetDocAsociadoService().insertarDocAsociado(documento);
                            guardarArchivo(documento);
                        }
                        cargarDocApoderadoLegaRL();
                    }

                    getAuditoriaService().asociarApoderadoRepresentante(getAscColDTOHelper().getOrdenSeleccionado());
                    addMessage(null, mensaje, "");
                }
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            if (getHelper().validaCamposColaborador(getAscColDTOHelper().getAgenteAduanalVO())) {
                addErrorMessage(null, MSG_VALIDACION, "");
            } else {
                mensaje = getAsociadosService().asociaColaborador(
                        getAscColDTOHelper().getPerfil().getRfcContribuyente() == null ? getAscColDTOHelper().getPerfil().getRfc()
                                : getAscColDTOHelper().getPerfil().getRfcContribuyente(),
                        getAscColDTOHelper().getAgenteAduanalVO(), getAscColDTOHelper().getOrdenSeleccionado());
                getAscColDTOHelper().setAgenteAduanalVO(getHelper().confirmaGuardado(getAscColDTOHelper().getAgenteAduanalVO()));
                getAscColAttribHelper().setMostrarGuardarAgenteAduanal(false);
                getAuditoriaService().asociarAgenteAduanal(getAscColDTOHelper().getOrdenSeleccionado());
                addMessage(null, mensaje, "");
            }
        }

        // FALTA ENVIO DE NOTIFICACION AL CONTRIBUYENTE, GENERAR PISTA DE
        // AUDITORIA E INSERTAR EN BITACORA
    }

    private void cambiaDisabledEmailColaborador(boolean flag) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().cambiaEmailInputText(getAscColDTOHelper().getApoderadoLegalVO(), flag));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getApoderadoLegalVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setRepresentanteLegalVO(getAsociadosService().cambiaEmailInputText(getAscColDTOHelper().getRepresentanteLegalVO(), flag));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getRepresentanteLegalVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalRLVO(getAsociadosService().cambiaEmailInputText(getAscColDTOHelper().getApoderadoLegalRLVO(), flag));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getApoderadoLegalRLVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAscColDTOHelper().setAgenteAduanalVO(getAsociadosService().cambiaEmailInputText(getAscColDTOHelper().getAgenteAduanalVO(), flag));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getAgenteAduanalVO().getNombreTipoAsociado()));
        }
        requestContext.execute("PF('ingresaEmail').show();");
    }

    public void deshabilitaEmailColaborador() {
        cambiaDisabledEmailColaborador(false);
    }

    public void habilitaEmailColaborador() {
        cambiaDisabledEmailColaborador(true);
    }

    public void habilitaCamposColaborador() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().cambiaCamposTxt(getAscColDTOHelper().getApoderadoLegalVO(), false));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getApoderadoLegalVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setRepresentanteLegalVO(getAsociadosService().cambiaCamposTxt(getAscColDTOHelper().getRepresentanteLegalVO(), false));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getRepresentanteLegalVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            getAscColDTOHelper().setApoderadoLegalRLVO(getAsociadosService().cambiaCamposTxt(getAscColDTOHelper().getApoderadoLegalRLVO(), false));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getApoderadoLegalRLVO().getNombreTipoAsociado()));
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAscColDTOHelper().setAgenteAduanalVO(getAsociadosService().cambiaCamposTxt(getAscColDTOHelper().getAgenteAduanalVO(), false));
            getAscColAttribHelper()
                    .setMensajeNoEnvioCorreo(getHelper().construyeMensajeNoEnvioCorreo(getAscColDTOHelper().getAgenteAduanalVO().getNombreTipoAsociado()));
        }
        requestContext.execute("PF('ingresaEmail').show();");
    }

    public void cargaOrdenSeleccionada() {
        getAuditoriaService().detalleOrden(getAscColDTOHelper().getOrdenSeleccionado());
        cargaContribuyente();
        getAscColAttribHelper().setMonstrarPanelOrdenes(false);
        getAscColAttribHelper().setMostrarPanelOrdenAsociarColaboradores(true);
        cargaColaboradores();
        cargaPromociones();
        cargaProrrogas();
        cargaOficios();
        cargaPerfil();

        // VALIDAR METODO DE LA ORDEN
        cargaPruebasPericiales();

    }

    private void cargaPerfil() {
        if (!(getAscColDTOHelper().getPerfil().getIdTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL)
                || getAscColDTOHelper().getPerfil().getIdTipoAsociado().equals(Constantes.ID_CONTRIBUYENTE))) {
            getAscColDTOHelper().getPerfil().setIdAsociado(getAscColDTOHelper().getOrdenSeleccionado().getFecetAsociado().getIdAsociado());
            getAscColDTOHelper().getPerfil().setNombre(getAscColDTOHelper().getOrdenSeleccionado().getFecetAsociado().getNombre());
        }
    }

    public String cargaCompulsaSeleccionada() {
        getAuditoriaService().detalleCompulsa(getAscColDTOHelper().getCompulsaSeleccionada());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(COMPULSA, getAscColDTOHelper().getCompulsaSeleccionada());
        getSession().setAttribute("mapCompulsa", map);
        return "../cargaDocumentacion/cargaDocumentosCompulsas.jsf";
    }

    public void regresa() {
        if (getAscColAttribHelper().isMonstrarPanelOrdenes()) {
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            try {
                extContext.redirect("accesoPerfil.jsf");
            } catch (IOException e) {
                addErrorMessage(null, e.getMessage(), "");
            }
        } else {
            getAscColAttribHelper().setMonstrarPanelOrdenes(false);
            limpiaBtnColaboradores();
            getAscColAttribHelper().setMostrarPanelOrdenAsociarColaboradores(false);
            inicializaColaboradores();
            cargaVistaContribuyente();
        }
    }

    private void cargaContribuyente() {
        getAscColDTOHelper().setContribuyente(getAscColDTOHelper().getOrdenSeleccionado().getFecetContribuyente());
        ValidaMediosContactoBO validaMediosContactoBO = checkMediosContacto(getAscColDTOHelper().getContribuyente().getRfc());
        if (validaMediosContactoBO.isFlag()) {
            getAscColAttribHelper().setLeyendaContribuyenteMedios("SI");
        } else {
            getAscColAttribHelper().setLeyendaContribuyenteMedios("NO");
        }
    }

    private void cargaOficios() {
        getAscColListHelper().setListaOficiosFirmados(getAsociadosService().getOficiosFirmados(getAscColDTOHelper().getOrdenSeleccionado()));
    }

    private void cargaColaboradores() {
        // VALIDAR REGLA 072
        getAscColAttribHelper().setMostrarDatosRepresentanteLegal(
                getAsociadosValidacionService().validaVistaRepresentanteLegal(getAscColDTOHelper().getPerfil().getIdTipoAsociado()));

        if (getAscColAttribHelper().isMostrarDatosRepresentanteLegal()) {
            obtenRepresentanteLegal();
            cargarDocRepresentanteLegal();
        }
        getAscColAttribHelper().setMostrarDatosApoderadoLegalRL(
                getAsociadosValidacionService().validaVistaApoderadoLegalRL(getAscColDTOHelper().getPerfil().getIdTipoAsociado()));
        if (getAscColAttribHelper().isMostrarDatosApoderadoLegalRL()) {
            obtenApoderadoLegalRepresentanteLegal();
            cargarDocApoderadoLegaRL();
        }
        getAscColAttribHelper()
                .setMostrarDatosAgenteAduanal(getAsociadosValidacionService().validaVistaAgenteAduanual(getAscColDTOHelper().getPerfil().getIdTipoAsociado()));
        if (getAscColAttribHelper().isMostrarDatosAgenteAduanal()) {
            getAscColAttribHelper()
                    .setMostrarDatosAgenteAduanal(getAsociadosValidacionService().validaOrdenMetodo(getAscColDTOHelper().getOrdenSeleccionado()));
            if (getAscColAttribHelper().isMostrarDatosAgenteAduanal()) {
                obtenAgenteAduanal();
            }

        }
    }

    private void cargarDocRepresentanteLegal() {

        if (getAscColDTOHelper().getRepresentanteLegalVO().getAsociado() != null) {
            getAscColListHelper().setDocumentosRL(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdAsociado()));
        } else {
            getAscColListHelper().setDocumentosRL(new ArrayList<FecetDocAsociado>());
        }

        getAscColListHelper().setDocumentosRLOriginal(getAscColListHelper().getDocumentosRL());

    }

    private void cargarDocApoderadoLegal() {
        if (getAscColDTOHelper().getApoderadoLegalVO().getAsociado() != null) {
            getAscColListHelper().setDocumentosAL(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getAscColDTOHelper().getApoderadoLegalVO().getAsociado().getIdAsociado()));
        } else {
            getAscColListHelper().setDocumentosAL(new ArrayList<FecetDocAsociado>());
        }
        getAscColListHelper().setDocumentosALOriginal(getAscColListHelper().getDocumentosAL());

    }

    private void cargarDocApoderadoLegaRL() {
        if (getAscColDTOHelper().getApoderadoLegalRLVO().getAsociado() != null) {
            getAscColListHelper().setDocumentosALRL(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(getAscColDTOHelper().getApoderadoLegalRLVO().getAsociado().getIdAsociado()));
        } else {
            getAscColListHelper().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
        }
        getAscColListHelper().setDocumentosALRLOriginal(getAscColListHelper().getDocumentosALRL());

    }

    public void cargaPromociones() {
        getAscColListHelper().setListaPromociones(getAsociadosService().getPromocionContadorPruebasAlegatos(getAscColDTOHelper().getOrdenSeleccionado()));
    }

    public void cargaProrrogas() {
        getAscColAttribHelper().setMostrarProrrogas(getAsociadosValidacionService().validaMetodoProrrogaOrden(getAscColDTOHelper().getOrdenSeleccionado()));
        if (getAscColAttribHelper().isMostrarProrrogas()) {
            getAscColListHelper().setListaProrroga(getAsociadosService().getHistoricoProrrogaOrden(getAscColDTOHelper().getOrdenSeleccionado().getIdOrden()));
            for (FecetProrrogaOrden prorroga : getAscColListHelper().getListaProrroga()) {
                if (prorroga.getIdEstatus().equals(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus())
                        || prorroga.getIdEstatus().equals(EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus())) {
                    if (prorroga.getAprobada()) {
                        prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.aprobada"));
                    } else {
                        prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.no.aprobada"));
                    }
                }

                prorroga.setMostrarDocRes(getAsociadosValidacionService().validaDocResProrroga(prorroga));

            }
            if (getAscColListHelper().getListaProrroga().isEmpty()) {
                getAscColAttribHelper().setMostrarBtnProrrogas(true);
            } else {
                getAscColAttribHelper().setMostrarBtnProrrogas(getAsociadosValidacionService().validaProrrogaEstatus(getAscColListHelper().getListaProrroga(),
                        getAscColDTOHelper().getOrdenSeleccionado()));
            }
            if (getAscColDTOHelper().getOrdenSeleccionado().getDescripcionDiasRestanteDocumentos().equals(Constantes.PLAZO_RESTANTE_VENCIDO)) {
                getAscColAttribHelper().setMostrarBtnProrrogas(false);
            }

        }
    }

    private void cargaPruebasPericiales() {
        getAscColAttribHelper()
                .setMostrarPruebasPericiales(getAsociadosValidacionService().validaMetodoPruebasPericiales(getAscColDTOHelper().getOrdenSeleccionado()));
        getAscColAttribHelper().setMostrarPruebasPericiales(false);
        if (getAscColAttribHelper().isMostrarPruebasPericiales()) {
            getAscColListHelper()
                    .setListaPruebasPericiales(getAsociadosService().getHistoricoPruebasPericiales(getAscColDTOHelper().getOrdenSeleccionado().getIdOrden()));
            for (FecetPruebasPericiales pruebasPericiales : getAscColListHelper().getListaPruebasPericiales()) {
                if (pruebasPericiales.getIdEstatus().equals(EstatusPruebasPericiales.RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV.getBigIdEstatus())
                        || pruebasPericiales.getIdEstatus().equals(EstatusPruebasPericiales.PRUEBAS_PERICIALES_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus())) {
                    if (pruebasPericiales.getAprobada()) {
                        pruebasPericiales.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.pruebas.periciales.firmante.aprobada"));
                    } else {
                        pruebasPericiales.getFececEstatus()
                                .setDescripcion(FacesUtil.getMessageResourceString("estatus.pruebas.periciales.firmante.no.aprobada"));
                    }
                }

                pruebasPericiales.setMostrarDocRes(getAsociadosValidacionService().validaDocResPruebasPericiales(pruebasPericiales));

            }
            getAscColAttribHelper().setMostrarBtnPruebasPericiales(false);
            if (getAscColDTOHelper().getOrdenSeleccionado().getDiasRestantesDocumentos() > 0) {

                if (getAscColListHelper().getListaPruebasPericiales().isEmpty()) {
                    getAscColAttribHelper().setMostrarBtnPruebasPericiales(true);
                } else {
                    getAscColAttribHelper().setMostrarBtnPruebasPericiales(getAsociadosValidacionService()
                            .validaPruebasPericialesEstatus(getAscColListHelper().getListaPruebasPericiales(), getAscColDTOHelper().getOrdenSeleccionado()));
                }
            }

        }
    }

    private void obtenApoderadoLegal() {
        if (getAscColDTOHelper().getPerfil().getRfcContribuyente() == null) {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().obtenerApoderadoLegalContribuyente(getAscColDTOHelper().getPerfil().getRfc(),
                    getAscColDTOHelper().getApoderadoLegalVO()));
        } else {
            getAscColDTOHelper().setApoderadoLegalVO(getAsociadosService().obtenerApoderadoLegalContribuyente(
                    getAscColDTOHelper().getPerfil().getRfcContribuyente(), getAscColDTOHelper().getApoderadoLegalVO()));
        }
        getAscColDTOHelper().setApoderadoLegalOriginalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getApoderadoLegalVO()));
    }

    private void obtenRepresentanteLegal() {
        getAscColDTOHelper().setRepresentanteLegalVO(
                getAsociadosService().cargaColaborador(getAscColDTOHelper().getRepresentanteLegalVO(), getAscColDTOHelper().getOrdenSeleccionado()));
        getAscColDTOHelper().setRepresentanteLegalOriginalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getRepresentanteLegalVO()));

        if (getAscColDTOHelper().getRepresentanteLegalVO().isSinColaborador()) {
            getAscColAttribHelper().setMostrarOrdenSinRL(true);
        }
    }

    private void limpiaBtnColaboradores() {
        getAscColAttribHelper().setMostrarDatosApoderadoLegal(false);
        getAscColAttribHelper().setMostrarDatosAgenteAduanal(false);
        getAscColAttribHelper().setMostrarDatosRepresentanteLegal(false);
        getAscColAttribHelper().setMostrarDatosApoderadoLegalRL(false);
        getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
        getAscColAttribHelper().setMostrarGuardarAgenteAduanal(false);
        getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(false);
        getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(false);
        getAscColAttribHelper().setMostrarBtnProrrogas(false);
        getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(false);
        getAscColAttribHelper().setMostrarBtnPruebasPericiales(false);
        getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(false);
        getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(false);
        getAscColAttribHelper().setMostrarCancelarBusquedaAL(false);
        getAscColAttribHelper().setMostrarCancelarBusquedaRL(false);
        getAscColAttribHelper().setMostrarCancelarBusquedaALRL(false);
        getAscColAttribHelper().setMostrarOrdenSinAL(false);
        getAscColAttribHelper().setMostrarOrdenSinALRL(false);
        getAscColAttribHelper().setMostrarOrdenSinRL(false);

    }

    public void getPruebasAlegatosPromocion() {
        getAscColListHelper()
                .setListaPruebasAlegatos(getAsociadosService().getPruebasAlegatosPromocion(getAscColDTOHelper().getPromocionSeleccionada().getIdPromocion()));
        getAscColAttribHelper().setIdPromocionSeleccionada(getAscColDTOHelper().getPromocionSeleccionada().getIdPromocion());
        getAscColAttribHelper().setFechaEnvioPromocionSeleccionada(getAscColDTOHelper().getPromocionSeleccionada().getFechaCarga());
    }

    public String regresaTest() {
        limpiaBtnColaboradores();
        return "accesoPerfil.jsf";
    }

    public void fileUploadDocRL(FileUploadEvent event) {
        UploadedFile archivo = event.getFile();
        FecetDocAsociado documento = new FecetDocAsociado();
        documento.setNombreArchivo(archivo.getFileName());

        if (!duplicidadDocRL(documento)) {
            documento.setBlnActivo(BigDecimal.ONE);
            // documento.setIdAsociado(getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdAsociado())
            documento.setFechaCreacion(new Date());
            documento.setEliminar(true);
            getAscColListHelper().getDocumentosRL().add(documento);
            FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp();
            fecetArchivoTemp.setArchivo(archivo.getFileName());
            fecetArchivoTemp.setSessionUUID(getRFCSession());

            try {
                fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivo.getInputstream()));
                documento.setIdTemporal(getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }
    }

    public void fileUploadDocAL(FileUploadEvent event) {
        UploadedFile archivo = event.getFile();
        FecetDocAsociado documento = new FecetDocAsociado();
        documento.setNombreArchivo(archivo.getFileName());

        if (!duplicidadDocAL(documento)) {
            documento.setBlnActivo(BigDecimal.ONE);
            // documento.setIdAsociado(getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdAsociado())
            documento.setFechaCreacion(new Date());
            documento.setEliminar(true);
            getAscColListHelper().getDocumentosAL().add(documento);
            FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp();
            fecetArchivoTemp.setArchivo(archivo.getFileName());
            fecetArchivoTemp.setSessionUUID(getRFCSession());

            try {
                fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivo.getInputstream()));
                documento.setIdTemporal(getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }
    }

    public void fileUploadDocALRL(FileUploadEvent event) {
        UploadedFile archivo = event.getFile();
        FecetDocAsociado documento = new FecetDocAsociado();
        documento.setNombreArchivo(archivo.getFileName());

        if (!duplicidadDocALRL(documento)) {
            documento.setBlnActivo(BigDecimal.ONE);
            // documento.setIdAsociado(getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdAsociado())
            documento.setFechaCreacion(new Date());
            documento.setEliminar(true);
            getAscColListHelper().getDocumentosALRL().add(documento);
            FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp();
            fecetArchivoTemp.setArchivo(archivo.getFileName());
            fecetArchivoTemp.setSessionUUID(getRFCSession());

            try {
                fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivo.getInputstream()));
                documento.setIdTemporal(getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }
    }

    private boolean duplicidadDocRL(FecetDocAsociado documento) {

        if (getAscColListHelper().getDocumentosRL() == null) {
            getAscColListHelper().setDocumentosRL(new ArrayList<FecetDocAsociado>());
        }

        for (FecetDocAsociado dto : getAscColListHelper().getDocumentosRL()) {
            if (dto.getNombreArchivo().equals(documento.getNombreArchivo())) {
                addErrorMessage("msgDocumentoRL", "El archivo ya fue cargado.", dto.getNombreArchivo());
                return true;
            }
        }
        return false;
    }

    private boolean duplicidadDocAL(FecetDocAsociado documento) {

        if (getAscColListHelper().getDocumentosAL() == null) {
            getAscColListHelper().setDocumentosAL(new ArrayList<FecetDocAsociado>());
        }

        for (FecetDocAsociado dto : getAscColListHelper().getDocumentosAL()) {
            if (dto.getNombreArchivo().equals(documento.getNombreArchivo())) {
                addErrorMessage("msgDocumentoAL", "El archivo ya fue cargado.", dto.getNombreArchivo());
                return true;
            }
        }
        return false;
    }

    private boolean duplicidadDocALRL(FecetDocAsociado documento) {

        if (getAscColListHelper().getDocumentosALRL() == null) {
            getAscColListHelper().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
        }

        for (FecetDocAsociado dto : getAscColListHelper().getDocumentosALRL()) {
            if (dto.getNombreArchivo().equals(documento.getNombreArchivo())) {
                addErrorMessage("msgDocumentoAL", "El archivo ya fue cargado.", dto.getNombreArchivo());
                return true;
            }
        }
        return false;
    }

    public void eliminarDocRL() {
        if (getAscColListHelper().getDocumentosRL().contains(getAscColDTOHelper().getDocumentoSeleccionadoRL())) {
            getAscColListHelper().getDocumentosRL().remove(getAscColDTOHelper().getDocumentoSeleccionadoRL());
        }
    }

    public void eliminarDocAL() {
        if (getAscColListHelper().getDocumentosAL().contains(getAscColDTOHelper().getDocumentoSeleccionadoAL())) {
            getAscColListHelper().getDocumentosAL().remove(getAscColDTOHelper().getDocumentoSeleccionadoAL());
        }
    }

    public void eliminarDocALRL() {
        if (getAscColListHelper().getDocumentosALRL().contains(getAscColDTOHelper().getDocumentoSeleccionadoALRL())) {
            getAscColListHelper().getDocumentosALRL().remove(getAscColDTOHelper().getDocumentoSeleccionadoALRL());
        }
    }

}
