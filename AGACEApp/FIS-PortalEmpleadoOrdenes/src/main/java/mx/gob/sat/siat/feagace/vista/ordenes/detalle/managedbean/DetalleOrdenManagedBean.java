package mx.gob.sat.siat.feagace.vista.ordenes.detalle.managedbean;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.DetalleOrdenAbstractMB;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenDTOHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenListHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenStreamedHelper;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean(name = "detalleOrdenManagedBean")
public class DetalleOrdenManagedBean extends DetalleOrdenAbstractMB {

    @SuppressWarnings("compatibility:6765339202531440786")
    private static final long serialVersionUID = 1L;

    private PerfilContribuyenteVO perfil;

    private static final String PERFIL = "perfil";
    private static final String ORDEN = "orden";
    private static final String OFICIO = "oficio";

    public DetalleOrdenManagedBean() {
        setDetalleOrdenDtoHelper(new DetalleOrdenDTOHelper());
        setDetalleOrdenStreamedHelper(new DetalleOrdenStreamedHelper());
        setDetalleOrdenLstHelper(new DetalleOrdenListHelper());
    }

    @PostConstruct
    public void init() {

        PerfilContribuyenteVO perfilSession = (PerfilContribuyenteVO) getSession().getAttribute(PERFIL);
        getDetalleOrdenStreamedHelper().setArchivoDescargaAcuse((StreamedContent) getSession().getAttribute("promocionAcuse"));
        getDetalleOrdenStreamedHelper().setArchivoDescargaAcuseProrroga((StreamedContent) getSession().getAttribute("prorrogaAcuse"));

        getDetalleOrdenDtoHelper().setMonstrarPanelOrdenes(true);
        getDetalleOrdenDtoHelper().setMostrarPanelOrdenAsociarColaboradores(true);
        inicializaColaboradores();
        setPerfil(perfilSession);
        perfilSession = null;
        getSession().removeAttribute(PERFIL);
        cargaVistaContribuyente();

    }

    private void inicializaColaboradores() {
        getDetalleOrdenDtoHelper().setApoderadoLegalVO(new ColaboradorVO());
        getDetalleOrdenDtoHelper().getApoderadoLegalVO().setTipoAsociado(Constantes.ID_APODERADO_LEGAL);
        getDetalleOrdenDtoHelper().getApoderadoLegalVO().setNombreTipoAsociado(Constantes.APODERADO_LEGAL);
        getDetalleOrdenDtoHelper().setRepresentanteLegalVO(new ColaboradorVO());
        getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
        getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
        getDetalleOrdenDtoHelper().setApoderadoLegalRLVO(new ColaboradorVO());
        getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setTipoAsociado(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setNombreTipoAsociado(Constantes.APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getDetalleOrdenDtoHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
        getDetalleOrdenDtoHelper().setAgenteAduanalVO(new ColaboradorVO());
        getDetalleOrdenDtoHelper().getAgenteAduanalVO().setTipoAsociado(Constantes.ID_AGENTE_ADUANAL);
        getDetalleOrdenDtoHelper().getAgenteAduanalVO().setNombreTipoAsociado(Constantes.AGENTE_ADUANAL);
    }

    private void cargaVistaContribuyente() {
        getDetalleOrdenDtoHelper().setMonstrarPanelOrdenes(true);
        getDetalleOrdenDtoHelper().setMostrarPanelOrdenAsociarColaboradores(true);
        // SI EL PERFIL ES CONTRIBUYENTE SE VALIDA LAS ORDENES CON REGLA R065
        BigDecimal idOrden = (BigDecimal) getSession().getAttribute("idOrdenPropuesta");
        getDetalleOrdenDtoHelper().setOrdenes(getDetalleOrdenService().obtieneIdOrden(idOrden));
        getDetalleOrdenDtoHelper().setContribuyente(getDetalleOrdenService().obtieneContribuyentes(getDetalleOrdenDtoHelper().getOrdenes().getIdContribuyente()));
        getDetalleOrdenDtoHelper().getOrdenes().setFeceaMetodo(getDetalleOrdenService().obtieneMetodo(getDetalleOrdenDtoHelper().getOrdenes().getIdMetodo()).get(0));
        cargaOrdenSeleccionada(getDetalleOrdenDtoHelper().getOrdenes());
        cargaColaboradores(getDetalleOrdenDtoHelper().getOrdenes());
    }

    public void validaSinApoderadoLegal() {
        getDetalleOrdenDtoHelper().setApoderadoLegalVO(getAsociadosService().sinColaborador(getDetalleOrdenDtoHelper().getApoderadoLegalVO()));
    }

    public void validaSinRepresentanteLegal() {
        getDetalleOrdenDtoHelper().setRfcRLLista(Constantes.COMBO_SELECCIONA_CADENA);
        getDetalleOrdenDtoHelper().setRepresentanteLegalVO(getAsociadosService().sinColaborador(getDetalleOrdenDtoHelper().getRepresentanteLegalVO()));
    }

    public void validaSinApoderadoLegalRL() {
        getDetalleOrdenDtoHelper().setApoderadoLegalRLVO(getAsociadosService().sinColaborador(getDetalleOrdenDtoHelper().getApoderadoLegalRLVO()));
    }

    public void validaSinAgenteAduanal() {
        getDetalleOrdenDtoHelper().setAgenteAduanalVO(getAsociadosService().sinColaborador(getDetalleOrdenDtoHelper().getAgenteAduanalVO()));
    }

    public void cargaOrdenSeleccionada(AgaceOrden ordenes) {

        getDetalleOrdenDtoHelper().setMonstrarPanelOrdenes(true);
        getDetalleOrdenDtoHelper().setMostrarPanelOrdenAsociarColaboradores(true);
        cargaPromociones(ordenes);
        cargaProrrogas(ordenes);
        cargaOficios(ordenes);
    }

    private void cargaColaboradores(AgaceOrden ordenes) {
        logger.info("ordenes",ordenes);

        List<FecetAsociado> apoderadoLegal = getDetalleOrdenService().obtieneColaborador(new BigDecimal(Constantes.ENTERO_UNO), getDetalleOrdenDtoHelper().getOrdenes().getIdOrden());
        List<FecetAsociado> representanteLegal = getDetalleOrdenService().obtieneColaborador(new BigDecimal(Constantes.ENTERO_DOS), getDetalleOrdenDtoHelper().getOrdenes().getIdOrden());
        List<FecetAsociado> apodLegalRepLegal = getDetalleOrdenService().obtieneColaborador(new BigDecimal(Constantes.ENTERO_TRES), getDetalleOrdenDtoHelper().getOrdenes().getIdOrden());
        List<FecetAsociado> agenteAduanal = getDetalleOrdenService().obtieneColaborador(new BigDecimal(Constantes.ENTERO_CUATRO), getDetalleOrdenDtoHelper().getOrdenes().getIdOrden());

        if (apoderadoLegal != null && !apoderadoLegal.isEmpty()) {
            getDetalleOrdenDtoHelper().getApoderadoLegalVO().setNombre(apoderadoLegal.get(0).getNombre());
            getDetalleOrdenDtoHelper().getApoderadoLegalVO().setCorreo(apoderadoLegal.get(0).getCorreo());
            getDetalleOrdenDtoHelper().getApoderadoLegalVO().setRfc(apoderadoLegal.get(0).getRfc());
            getDetalleOrdenDtoHelper().getApoderadoLegalVO().setMedioContactoBoolean(Boolean.parseBoolean(apoderadoLegal.get(0).getMedioContacto()));
        }

        if (representanteLegal != null && !representanteLegal.isEmpty()) {
            getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setNombre(representanteLegal.get(0).getNombre());
            getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setCorreo(representanteLegal.get(0).getCorreo());
            getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setRfc(representanteLegal.get(0).getRfc());
            getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setMedioContactoBoolean(Boolean.parseBoolean(representanteLegal.get(0).getMedioContacto()));
        }

        if (apodLegalRepLegal != null && !apodLegalRepLegal.isEmpty()) {
            getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setNombre(apodLegalRepLegal.get(0).getNombre());
            getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setCorreo(apodLegalRepLegal.get(0).getCorreo());
            getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setRfc(apodLegalRepLegal.get(0).getRfc());
            getDetalleOrdenDtoHelper().getApoderadoLegalRLVO().setMedioContactoBoolean(Boolean.parseBoolean(apodLegalRepLegal.get(0).getMedioContacto()));
        }

        if (agenteAduanal != null && !agenteAduanal.isEmpty()) {
            getDetalleOrdenDtoHelper().getAgenteAduanalVO().setNombre(agenteAduanal.get(0).getNombre());
            getDetalleOrdenDtoHelper().getAgenteAduanalVO().setCorreo(agenteAduanal.get(0).getCorreo());
            getDetalleOrdenDtoHelper().getAgenteAduanalVO().setRfc(agenteAduanal.get(0).getRfc());
            getDetalleOrdenDtoHelper().getAgenteAduanalVO().setMedioContactoBoolean(Boolean.parseBoolean(agenteAduanal.get(0).getMedioContacto()));
        }
    }

    private void cargaOficios(AgaceOrden orden) {
        getDetalleOrdenLstHelper().setListaOficiosFirmados(getAsociadosService().getOficiosFirmados(orden));
    }

    public void cargaPromociones(AgaceOrden ordenes) {
        getDetalleOrdenLstHelper().setListaPromociones(getAsociadosService().getPromocionContadorPruebasAlegatos(ordenes));
    }

    public void cargaProrrogas(AgaceOrden ordenes) {
        getDetalleOrdenLstHelper().setListaProrroga(getAsociadosService().getHistoricoProrrogaOrden(getDetalleOrdenDtoHelper().getOrdenes().getIdOrden()));
    }

    public void regresa() {

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            extContext.redirect("../../../propuestas/programador/validarYRetroalimentar/indexValidarRetroalimentar.jsf?faces-redirect=true");
        } catch (IOException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    //N065    
    public void getPruebasAlegatosPromocion() {
        getDetalleOrdenLstHelper().setListaPruebasAlegatos(getAsociadosService().getPruebasAlegatosPromocion(getDetalleOrdenDtoHelper().getPromocionSeleccionada().getIdPromocion()));
        getDetalleOrdenDtoHelper().setIdPromocionSeleccionada(getDetalleOrdenDtoHelper().getPromocionSeleccionada().getIdPromocion());
        getDetalleOrdenDtoHelper().setFechaEnvioPromocionSeleccionada(getDetalleOrdenDtoHelper().getPromocionSeleccionada().getFechaCarga());
    }

    public String cargaDocumentosOrden() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getPerfil());
        map.put(ORDEN, getDetalleOrdenDtoHelper().getOrdenSeleccionado());
        getSession().setAttribute("mapOrden", map);
        return "../cargaDocumentacion/cargaDocumentosOrden.jsf";
    }

    public String cargaOficioSeleccionado() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getPerfil());
        map.put(ORDEN, getDetalleOrdenDtoHelper().getOrdenes());
        map.put(OFICIO, getDetalleOrdenDtoHelper().getOficioSeleccionado());
        getSession().setAttribute("mapOficio", map);
        return "../../../propuestas/programador/validarYRetroalimentar/cargaDocumentosOficio.jsf?faces-redirect=true";
    }

    public String cargaProrrogaOrden() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getPerfil());
        map.put(ORDEN, getDetalleOrdenDtoHelper().getOrdenSeleccionado());
        getSession().setAttribute("mapProrrogaOrden", map);
        return "../cargaDocumentacion/cargaProrrogasOrden.jsf";
    }

    public String cargaProrrogaOficio() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getPerfil());
        map.put(ORDEN, getDetalleOrdenDtoHelper().getOrdenSeleccionado());
        map.put(OFICIO, getDetalleOrdenDtoHelper().getOficioSeleccionado());
        getSession().setAttribute("mapProrrogaOficio", map);
        return "../cargaDocumentacion/cargaProrrogasOficio.jsf";
    }

    public String regresaTest() {
        return "accesoPerfil.jsf";
    }

    public void confirmarAsociado() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getDetalleOrdenDtoHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAsociadosService().confirmarAsociado(getPerfil().getRfcContribuyente() == null ? getPerfil().getRfc()
                    : getPerfil().getRfcContribuyente(),
                    getDetalleOrdenDtoHelper().getRepresentanteLegalVO().getRfc(),
                    getDetalleOrdenDtoHelper().getRepresentanteLegalVO().getAsociado().getIdTipoAsociado(),
                    getDetalleOrdenDtoHelper().getOrdenSeleccionado().getIdOrden());
            getDetalleOrdenDtoHelper().getRepresentanteLegalVO().setVisibleConfirmarColaborador(false);

        } else if (getDetalleOrdenDtoHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAsociadosService().confirmarAsociado(getPerfil().getRfcContribuyente() == null ? getPerfil().getRfc()
                    : getPerfil().getRfcContribuyente(), getDetalleOrdenDtoHelper().getAgenteAduanalVO().getRfc(),
                    getDetalleOrdenDtoHelper().getAgenteAduanalVO().getAsociado().getIdTipoAsociado(),
                    getDetalleOrdenDtoHelper().getOrdenSeleccionado().getIdOrden());
            getDetalleOrdenDtoHelper().getAgenteAduanalVO().setVisibleConfirmarColaborador(false);
        }
        requestContext.execute("PF('asociadoConfirmado').show();");
    }

    public void abrirDialogConfirmarAsociado() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('confirmarAsociado').show();");
    }

    public void getDocsProrrogasOrden() {
        getDetalleOrdenLstHelper().setListaDocsProrrogaOrden(getAsociadosService().getDocsProrrogaOrden(getDetalleOrdenDtoHelper().getProrrogaOrdenSeleccionada().getIdProrrogaOrden()));
        getDetalleOrdenDtoHelper().setIdProrrogaOrdenSeleccionada(getDetalleOrdenDtoHelper().getProrrogaOrdenSeleccionada().getIdProrrogaOrden());
        getDetalleOrdenDtoHelper().setFechaEnvioProrrogaOrdenSeleccionada(getDetalleOrdenDtoHelper().getProrrogaOrdenSeleccionada().getFechaCarga());
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

}
