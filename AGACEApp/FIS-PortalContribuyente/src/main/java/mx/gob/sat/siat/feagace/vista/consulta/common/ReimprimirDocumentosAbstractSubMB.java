package mx.gob.sat.siat.feagace.vista.consulta.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.PistaAuditoriaDescargaDocumentosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.ConsultarReimprimirDocumentosService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ConsultarReimprimirDocumentosRules;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOficioAsociadoRule;
import mx.gob.sat.siat.feagace.vista.consulta.helper.ReimprimirDocumentosDTOHelper;
import mx.gob.sat.siat.feagace.vista.helper.ConsultarReimprimirDocumentosHelper;

public class ReimprimirDocumentosAbstractSubMB extends ReimprimirDocumentosAbstractMB {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ReimprimirDocumentosDTOHelper reimprimirDocumentosDTOHelper;

    @ManagedProperty(value = "#{consultarReimprimirDocumentosService}")
    private transient ConsultarReimprimirDocumentosService consultarReimprimirDocumentosService;
    @ManagedProperty(value = "#{consultarReimprimirDocumentosHelper}")
    private transient ConsultarReimprimirDocumentosHelper consultarReimprimirDocumentosHelper;
    @ManagedProperty(value = "#{consultarReimprimirDocumentosRules}")
    private transient ConsultarReimprimirDocumentosRules consultarReimprimirDocumentosRules;
    @ManagedProperty(value = "#{validarMetodoOficioAsociadoRule}")
    private transient ValidarMetodoOficioAsociadoRule validarMetodoOficioAsociadoRule;
    @ManagedProperty(value = "#{auditoriaDocumentos}")
    private transient PistaAuditoriaDescargaDocumentosService pistaAuditoriaDescargaDocumentosService;

    protected void cargarColaboradores() {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdOrden());
        getReimprimirDocumentosDTOHelper().setColaboradoresDTO(new ColaboradorDocumentoDTO());
        getReimprimirDocumentosDTOHelper().setAsociados(new ArrayList<ColaboradorVO>());
        inicializaColaboradores();
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setRepresentanteLegal(consultarReimprimirDocumentosService.obtenerColaborador(orden, getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal()));
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setAgenteAduanal(consultarReimprimirDocumentosService.obtenerColaborador(orden, getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getAgenteAduanal()));
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setApoderadoLegalRepresentanteLegal(consultarReimprimirDocumentosService.obtenerColaborador(orden, getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal()));
        if (getReimprimirDocumentosDTOHelper().getPerfilSession().getRfcContribuyente() == null) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setApoderadoLegal(consultarReimprimirDocumentosService.obtenerApoderadoLegalContribuyente(getReimprimirDocumentosDTOHelper().getPerfilSession().getRfc(), getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal()));
        } else {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setApoderadoLegal(consultarReimprimirDocumentosService.obtenerApoderadoLegalContribuyente(getReimprimirDocumentosDTOHelper().getPerfilSession().getRfcContribuyente(), getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal()));
        }
        if (!getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal().isSinColaborador()) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentosRepresentante(consultarReimprimirDocumentosService.obtenerDocumentosPorAsociado(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal().getAsociado().getIdAsociado()));
            getReimprimirDocumentosDTOHelper().getAsociados().add(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal());
        }
        if (!getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal().isSinColaborador()) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentosAL(consultarReimprimirDocumentosService.obtenerDocumentosPorAsociado(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal().getAsociado().getIdAsociado()));
            getReimprimirDocumentosDTOHelper().getAsociados().add(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal());
        }
        if (!getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().isSinColaborador()) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentosALRL(consultarReimprimirDocumentosService.obtenerDocumentosPorAsociado(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().getAsociado().getIdAsociado()));
            getReimprimirDocumentosDTOHelper().getAsociados().add(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal());
        }
        if (!getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getAgenteAduanal().isSinColaborador()) {
            orden.setFeceaMetodo(new FececMetodo());
            orden.getFeceaMetodo().setIdMetodo(getReimprimirDocumentosDTOHelper().getOrdenConsultaSeleccionada().getIdMetodo());
            List<FecetPromocionAgenteAduanal> documentos = consultarReimprimirDocumentosService.obtenerDocumentacionAgenteAduanal(orden, getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getAgenteAduanal());
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentosAgente(documentos);
        }
    }

    private void inicializaColaboradores() {
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setRepresentanteLegal(new ColaboradorVO());
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getRepresentanteLegal().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setAgenteAduanal(new ColaboradorVO());
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getAgenteAduanal().setTipoAsociado(Constantes.ID_AGENTE_ADUANAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getAgenteAduanal().setNombreTipoAsociado(Constantes.AGENTE_ADUANAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setApoderadoLegalRepresentanteLegal(new ColaboradorVO());
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().setTipoAsociado(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().setNombreTipoAsociado(Constantes.APODERADO_LEGAL_REPRESENTANTE_LEGAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setApoderadoLegal(new ColaboradorVO());
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal().setTipoAsociado(Constantes.ID_APODERADO_LEGAL);
        getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getApoderadoLegal().setNombreTipoAsociado(Constantes.APODERADO_LEGAL);

    }

    public void cargaDocumentosAsociados() {
        if (getReimprimirDocumentosDTOHelper().getAsociadoSeleccionado().getTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL)) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentos(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getDocumentosAL());
            getReimprimirDocumentosDTOHelper().setTituloDoc("Documentos Apoderado Legal");
        } else if (getReimprimirDocumentosDTOHelper().getAsociadoSeleccionado().getTipoAsociado().equals(Constantes.ID_REPRESENTANTE_LEGAL)) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentos(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getDocumentosRepresentante());
            getReimprimirDocumentosDTOHelper().setTituloDoc("Documentos Representante Legal");
        } else if (getReimprimirDocumentosDTOHelper().getAsociadoSeleccionado().getTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL)) {
            getReimprimirDocumentosDTOHelper().getColaboradoresDTO().setDocumentos(getReimprimirDocumentosDTOHelper().getColaboradoresDTO().getDocumentosALRL());
            getReimprimirDocumentosDTOHelper().setTituloDoc("Documentos Apoderado Legal del Representante Legal");
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("consultarReimprimirDoc:pnlDoc");
        requestContext.execute("PF('dlgDoc').show();");

    }

    public ConsultarReimprimirDocumentosRules getConsultarReimprimirDocumentosRules() {
        return consultarReimprimirDocumentosRules;
    }

    public void setConsultarReimprimirDocumentosRules(ConsultarReimprimirDocumentosRules consultarReimprimirDocumentosRules) {
        this.consultarReimprimirDocumentosRules = consultarReimprimirDocumentosRules;
    }

    public ConsultarReimprimirDocumentosHelper getConsultarReimprimirDocumentosHelper() {
        return consultarReimprimirDocumentosHelper;
    }

    public void setConsultarReimprimirDocumentosHelper(ConsultarReimprimirDocumentosHelper consultarReimprimirDocumentosHelper) {
        this.consultarReimprimirDocumentosHelper = consultarReimprimirDocumentosHelper;
    }

    public ConsultarReimprimirDocumentosService getConsultarReimprimirDocumentosService() {
        return consultarReimprimirDocumentosService;
    }

    public void setConsultarReimprimirDocumentosService(ConsultarReimprimirDocumentosService consultarReimprimirDocumentosService) {
        this.consultarReimprimirDocumentosService = consultarReimprimirDocumentosService;
    }

    public ReimprimirDocumentosDTOHelper getReimprimirDocumentosDTOHelper() {
        return reimprimirDocumentosDTOHelper;
    }

    public void setReimprimirDocumentosDTOHelper(ReimprimirDocumentosDTOHelper reimprimirDocumentosDTOHelper) {
        this.reimprimirDocumentosDTOHelper = reimprimirDocumentosDTOHelper;
    }

    public ValidarMetodoOficioAsociadoRule getValidarMetodoOficioAsociadoRule() {
        return validarMetodoOficioAsociadoRule;
    }

    public void setValidarMetodoOficioAsociadoRule(ValidarMetodoOficioAsociadoRule validarMetodoOficioAsociadoRule) {
        this.validarMetodoOficioAsociadoRule = validarMetodoOficioAsociadoRule;
    }

    public PistaAuditoriaDescargaDocumentosService getPistaAuditoriaDescargaDocumentosService() {
        return pistaAuditoriaDescargaDocumentosService;
    }

    public void setPistaAuditoriaDescargaDocumentosService(
            PistaAuditoriaDescargaDocumentosService pistaAuditoriaDescargaDocumentosService) {
        this.pistaAuditoriaDescargaDocumentosService = pistaAuditoriaDescargaDocumentosService;
    }
}
