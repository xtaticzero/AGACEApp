package mx.gob.sat.siat.feagace.vista.common;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.validator.ValidatorException;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.RepLegalVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilContribuyente;
import mx.gob.sat.siat.feagace.vista.validador.RfcValidador;

public class AsociarColaboradoresSubMBAbstract extends AsociarColaboradoresMBAbstract {

    

/**
 *
 */
private static final long serialVersionUID = 1L;

    protected void obtenApoderadoLegalRepresentanteLegal() {
        getAscColDTOHelper().setApoderadoLegalRLVO(getAsociadosService().cargaColaborador(getAscColDTOHelper().getApoderadoLegalRLVO(), getAscColDTOHelper().getOrdenSeleccionado()));
        getAscColDTOHelper().setApoderadoLegalRLOriginalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getApoderadoLegalRLVO()));

        if (getAscColDTOHelper().getApoderadoLegalRLVO().isSinColaborador()) {
            getAscColAttribHelper().setMostrarOrdenSinALRL(true);
        }
    }

    protected void obtenAgenteAduanal() {
        getAscColDTOHelper().setAgenteAduanalVO(getAsociadosService().cargaColaborador(getAscColDTOHelper().getAgenteAduanalVO(), getAscColDTOHelper().getOrdenSeleccionado()));
        getAscColDTOHelper().setAgenteAduanalOriginalVO(getHelper().respaldaColaborador(getAscColDTOHelper().getAgenteAduanalVO()));
    }

    public void llenaCamposRL() {
        if (!getAscColAttribHelper().getRfcRLLista().equals(Constantes.COMBO_SELECCIONA_CADENA)) {
            getAscColAttribHelper().setTipoColaborador(Constantes.JSF_REPRESENTANTE_LEGAL);
            for (RepLegalVO rep : getAscColDTOHelper().getContribuyente().getRepLegal()) {
                if (getAscColAttribHelper().getRfcRLLista().equals(rep.getRfc())) {

                    getAscColDTOHelper().setRepresentanteLegalVO(new ColaboradorVO());
                    getAscColDTOHelper().getRepresentanteLegalVO().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
                    getAscColDTOHelper().getRepresentanteLegalVO().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
                    getAscColDTOHelper().getRepresentanteLegalVO().setRfc(rep.getRfc());
                    getAscColAttribHelper().setTipoColaborador(Constantes.JSF_REPRESENTANTE_LEGAL);
                    buscarColaborador();
                    break;
                }
            }
        }
    }

    protected ValidaMediosContactoBO checkMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        validaMediosContactoBO.setValidaSoloMediosContacto(true);
        validaMediosContactoBO = getAsociadosService().validaMediosContactoAsociado(validaMediosContactoBO);
        return validaMediosContactoBO;
    }

    public String cargaDocumentosOrden() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(ORDEN, getAscColDTOHelper().getOrdenSeleccionado());
        getSession().setAttribute("mapOrden", map);
        return "../cargaDocumentacion/cargaDocumentosOrden.jsf";
    }

    public String cargaOficioSeleccionado() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(ORDEN, getAscColDTOHelper().getOrdenSeleccionado());
        map.put(OFICIO, getAscColDTOHelper().getOficioSeleccionado());
        getSession().setAttribute("mapOficio", map);
        return "../cargaDocumentacion/cargaDocumentosOficio.jsf";
    }

    public String cargaProrrogaOrden() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(ORDEN, getAscColDTOHelper().getOrdenSeleccionado());
        getSession().setAttribute("mapProrrogaOrden", map);
        //getAscColAttribHelper().setMostrarBtnProrrogas(false)
        return "../cargaDocumentacion/cargaProrrogasOrden.jsf";
    }

    public String cargaPruebaPericial() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(ORDEN, getAscColDTOHelper().getOrdenSeleccionado());
        getSession().setAttribute("mapPruebaPericial", map);
        //getAscColAttribHelper().setMostrarBtnPruebasPericiales(false)
        return "../cargaDocumentacion/cargaPruebasPericiales.jsf";
    }

    public String cargaProrrogaOficio() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(PERFIL, getAscColDTOHelper().getPerfil());
        map.put(ORDEN, getAscColDTOHelper().getOrdenSeleccionado());
        map.put(OFICIO, getAscColDTOHelper().getOficioSeleccionado());
        getSession().setAttribute("mapProrrogaOficio", map);
        return "../cargaDocumentacion/cargaProrrogasOficio.jsf";
    }

    public void confirmarAsociado() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            getAsociadosService().confirmarAsociado(getAscColDTOHelper().getPerfil().getRfcContribuyente() == null ? getAscColDTOHelper().getPerfil().getRfc()
                    : getAscColDTOHelper().getPerfil().getRfcContribuyente(),
                    getAscColDTOHelper().getRepresentanteLegalVO().getRfc(),
                    getAscColDTOHelper().getRepresentanteLegalVO().getAsociado().getIdTipoAsociado(),
                    getAscColDTOHelper().getOrdenSeleccionado().getIdOrden());
            getAscColDTOHelper().getRepresentanteLegalVO().setVisibleConfirmarColaborador(false);

        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            getAsociadosService().confirmarAsociado(getAscColDTOHelper().getPerfil().getRfcContribuyente() == null ? getAscColDTOHelper().getPerfil().getRfc()
                    : getAscColDTOHelper().getPerfil().getRfcContribuyente(), getAscColDTOHelper().getAgenteAduanalVO().getRfc(),
                    getAscColDTOHelper().getAgenteAduanalVO().getAsociado().getIdTipoAsociado(),
                    getAscColDTOHelper().getOrdenSeleccionado().getIdOrden());
            getAscColDTOHelper().getAgenteAduanalVO().setVisibleConfirmarColaborador(false);
        }
        requestContext.execute("PF('asociadoConfirmado').show();");
    }

    public void abrirDialogConfirmarAsociado() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('confirmarAsociado').show();");
    }

    public void getDocsProrrogasOrden() {
        getAscColListHelper().setListaDocsProrrogaOrden(getAsociadosService().getDocsProrrogaOrden(getAscColDTOHelper().getProrrogaOrdenSeleccionada().getIdProrrogaOrden()));
        getAscColAttribHelper().setIdProrrogaOrdenSeleccionada(getAscColDTOHelper().getProrrogaOrdenSeleccionada().getIdProrrogaOrden());
        getAscColAttribHelper().setFechaEnvioProrrogaOrdenSeleccionada(getAscColDTOHelper().getProrrogaOrdenSeleccionada().getFechaCarga());
    }

    public void getDependientesOficio() {
        getAscColListHelper().setListaOficiosDependientes(getAsociadosService().obtenerOficiosDependientes(getAscColDTOHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosOficio() {
        getAscColListHelper().setListaAnexosOficio(getAsociadosService().obtenerOficioAnexos(getAscColDTOHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void cargaDocumentosOrdenOficio() {
        getAscColAttribHelper().setNombreDocumentoOrden(CargaArchivoUtilContribuyente.getNombreArchivoOrdenPdf(getAscColDTOHelper().getOrdenSeleccionDescarga()));
        getAscColAttribHelper().setFechaOrdenDocExpediente(getAsociadosService().obtenerDocExpediente(getAscColDTOHelper().getOrdenSeleccionDescarga().getIdOrden()).get(0).getFechaCreacion());
        getAscColListHelper().setListaOficiosAdmin(getAsociadosService().getOficiosAdministrables(getAscColDTOHelper().getOrdenSeleccionDescarga().getIdOrden()));
        RequestContext.getCurrentInstance().execute("PF('docDialog').show();");
    }

    public void limpiarOficiosAdmin() {
        getAscColAttribHelper().setNombreDocumentoOrden(null);
        getAscColListHelper().setListaOficiosAdmin(new ArrayList<FecetOficio>());
    }

    public void buscarColaborador() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL)) {
            if (validaRFC(getAscColDTOHelper().getApoderadoLegalVO())) {
                getAscColDTOHelper().setApoderadoLegalVO(validaColaboradorBuscado(getAscColDTOHelper().getApoderadoLegalVO(), requestContext));
                getAscColAttribHelper().setMostrarGuardarApoderadoLegal(true);
                getAscColAttribHelper().setMostrarAdjuntarDocumentoAL(true);
            } else {
                getAscColAttribHelper().setMostrarGuardarApoderadoLegal(false);
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_REPRESENTANTE_LEGAL)) {
            if (validaRFC(getAscColDTOHelper().getRepresentanteLegalVO())) {
                getAscColDTOHelper().setRepresentanteLegalVO(validaColaboradorBuscado(getAscColDTOHelper().getRepresentanteLegalVO(), requestContext));
                getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(true);
                getAscColAttribHelper().setMostrarAdjuntarDocumentoRL(true);
            } else {
                getAscColAttribHelper().setMostrarGuardarRepresentanteLegal(false);
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            if (validaRFC(getAscColDTOHelper().getApoderadoLegalRLVO())) {
                getAscColDTOHelper().setApoderadoLegalRLVO(validaColaboradorBuscado(getAscColDTOHelper().getApoderadoLegalRLVO(), requestContext));
                getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(true);
                getAscColAttribHelper().setMostrarAdjuntarDocumentoALRL(true);
            } else {
                getAscColAttribHelper().setMostrarGuardarApoderadoLegalRL(false);
            }
        } else if (getAscColAttribHelper().getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            if (validaRFC(getAscColDTOHelper().getAgenteAduanalVO())) {
                getAscColDTOHelper().setAgenteAduanalVO(validaColaboradorBuscado(getAscColDTOHelper().getAgenteAduanalVO(), requestContext));
                getAscColAttribHelper().setMostrarGuardarAgenteAduanal(true);
            } else {
                getAscColAttribHelper().setMostrarGuardarAgenteAduanal(false);
            }
        }
    }

    protected ColaboradorVO validaColaboradorBuscado(ColaboradorVO colaboradorSelected, RequestContext requestContext) {
        ColaboradorVO colaborador = colaboradorSelected;
        FecetContribuyente contribuyenteIDC;
        if (!colaborador.getRfc().isEmpty()) {
            try {
                contribuyenteIDC = getContribuyenteService().getContribuyenteIDC(colaborador.getRfc());
                if (contribuyenteIDC != null) {
                    ValidaMediosContactoBO validaMediosContactoBO = checkMediosContacto(contribuyenteIDC.getRfc());
                    colaborador = getAsociadosService().configuraColaborador(colaborador, contribuyenteIDC);
                    if (validaMediosContactoBO.isFlag()) {
                        colaborador
                                = getAsociadosService().cargaListaMediosContacto(colaborador, validaMediosContactoBO);
                        colaborador.setMedioContactoBoolean(true);
                    } else {
                        getAscColAttribHelper().setMensajeNoMediosContacto(getHelper().construyeMensajeNoMediosContacto(colaborador));
                        requestContext.execute("PF('confirmarMediosContacto').show();");
                    }
                } else {
                    getAscColAttribHelper().setMensajeNoIdc(getHelper().construyeMensajeNoIdc(getAscColAttribHelper().getTipoColaborador()));
                    requestContext.execute("PF('noIdc').show();");
                }
            } catch (NoExisteContribuyenteException nece) {
                getAscColAttribHelper().setMensajeNoIdc(getHelper().construyeMensajeNoIdc(getAscColAttribHelper().getTipoColaborador()));
                requestContext.execute("PF('noIdc').show();");
                logger.error(nece.getMessage(), nece);
            }

        } else {
            contribuyenteIDC = null;
            colaborador.setDeshabilitarCampos(false);
            addErrorMessage(null, "Debe introducir un RFC", "");
        }

        return colaborador;
    }

    private boolean validaRFC(ColaboradorVO colaborador) {
        boolean flag;
        RfcValidador validaRfc = new RfcValidador();
        try {
            validaRfc.validaLongitud(colaborador.getRfc());
            validaRfc.validate(null, null, colaborador.getRfc());
            flag = true;
        } catch (ValidatorException ve) {
            addErrorMessage(null, ve.getFacesMessage().getDetail(), "");
            flag = false;
        }
        return flag;
    }

    public void getDocsPruebasPericiales() {
        getAscColListHelper().setListaDocsPruebasPericiales(getAsociadosService().getDocsPruebasPericiales(getAscColDTOHelper().getPruebaPericialSeleccionada().getIdPruebasPericiales()));
        getAscColAttribHelper().setIdPruebaPericialSeleccionada(getAscColDTOHelper().getPruebaPericialSeleccionada().getIdPruebasPericiales());
        getAscColAttribHelper().setFechaEnvioPruebaPericialSeleccionada(getAscColDTOHelper().getPruebaPericialSeleccionada().getFechaCarga());
    }

}
