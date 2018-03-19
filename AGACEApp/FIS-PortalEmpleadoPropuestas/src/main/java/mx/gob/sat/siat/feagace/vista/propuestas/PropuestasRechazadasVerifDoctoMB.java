package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.OrigenCentralRegionalServ;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.vista.helper.propuestas.PropuestasRechazadasDtoHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.ContadorOrdenesValidarFirmarMB;

@ManagedBean(name = "propuestasRechazadasVerifDoctoMB")
@SessionScoped
public class PropuestasRechazadasVerifDoctoMB extends BaseManagedBean implements Serializable {

    private static final long serialVersionUID = -7429984867118684534L;

    private PropuestasRechazadasDtoHelper propRechazadasHelper;

    private StreamedContent documentoSeleccionDescarga;
    private StreamedContent documentoSeleccionDescargaOrden;
    private StreamedContent documentoSeleccionDescargaRechazo;

    static final String MSG_PROPUESTA_ENVIADA = "label.propuesta.enviada";

    @ManagedProperty(value = "#{propuestasRechazadasVerifDoctoService}")
    private transient PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{origenCentralRegionalService}")
    private transient OrigenCentralRegionalServ origenCentralRegionalService;

    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;

    @ManagedProperty(value = "#{contadorOrdenesValidarFirmar}")
    private ContadorOrdenesValidarFirmarMB contadorOrdenesValidarFirmar;

    public PropuestasRechazadasVerifDoctoMB() {
        propRechazadasHelper = new PropuestasRechazadasDtoHelper();
    }

    public String consultaContribuyenteBD() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserProfileDTO userProfileDTO = (UserProfileDTO) session.getAttribute("userProfile");
        session.setAttribute("essuplente", false);
        BigDecimal idEmpleado = BigDecimal.ZERO;
        idEmpleado = propuestasRechazadasVerifDoctoService.obtenerIdEmpleado(userProfileDTO.getRfc());

        // consultar los datos del contribuyente
        logger.info("Se va a consultar propRechazadasHelper.getRfc() {} idRegistro {}", propRechazadasHelper.getRfc(),
                propRechazadasHelper.getIdRegistro());
        try {
            propRechazadasHelper
                    .setContribuyenteAnt(contribuyenteService.getContribuyenteIDC(propRechazadasHelper.getRfc()));
        } catch (NoExisteContribuyenteException e) {
            logger.error("Error al consultar los datos del contribuyente Idc: {}", e.getMessage());
        }
        try {
            propRechazadasHelper.setPropuesta(origenCentralRegionalService
                    .obtienePropuestaByidPropuesta(new BigDecimal(propRechazadasHelper.getIdPropuesta())));
            tipoPropuesta();
        } catch (NegocioException e) {
            logger.error("Error al consultar los datos de la propuesta: {}", e.getMessage());
        }
        try {
            propRechazadasHelper.setContribuyente(origenCentralRegionalService
                    .getContribuyente(propRechazadasHelper.getPropuesta().getIdContribuyente()));
        } catch (NegocioException e) {
            logger.error("Error al consultar los datos del contribuyente: {}", e.getMessage());
        }
        propRechazadasHelper.setListaTipoImpuesto(registrarPropuestaIndividualService.getCatalogoImpuesto());
        try {
            propRechazadasHelper.setListaImpuestos(origenCentralRegionalService
                    .getImpuestosByPropuesta(propRechazadasHelper.getPropuesta().getIdPropuesta()));
        } catch (NegocioException e) {
            logger.error("Error al consultar los impuestos de la propuesta: {}", e.getMessage());
        }
        propRechazadasHelper.setListaDocumentosTabla(propuestasRechazadasVerifDoctoService
                .buscarDoctosExpedienteProp(new BigDecimal(propRechazadasHelper.getIdPropuesta())));
        BigDecimal idArace = propuestasRechazadasVerifDoctoService.obtenerIdArace(idEmpleado);
        propRechazadasHelper.setListaRechazos(propuestasRechazadasVerifDoctoService.buscarRechazosPorIdPropuesta(
                new BigDecimal(propRechazadasHelper.getIdPropuesta()), idArace,
                new BigDecimal(Constantes.ENTERO_SEIS)));
        propRechazadasHelper.setListaDocumentosOrden(propuestasRechazadasVerifDoctoService
                .buscarDoctosOrdenProp(new BigDecimal(propRechazadasHelper.getIdPropuesta())));
        List<FecetAsociado> listaRepr = propuestasRechazadasVerifDoctoService
                .getRepresentanteLegal(new BigDecimal(propRechazadasHelper.getIdPropuesta()));
        propRechazadasHelper.setRepresentanteLegal((!listaRepr.isEmpty()) ? listaRepr.get(0) : new FecetAsociado());
        List<FecetAsociado> listaAgente = propuestasRechazadasVerifDoctoService
                .getAgenteAduanal(new BigDecimal(propRechazadasHelper.getIdPropuesta()));
        propRechazadasHelper.setAgenteAduanal((listaAgente.size() > 0) ? listaAgente.get(0) : new FecetAsociado());
        comparaIdc();
        propRechazadasHelper.setBotonEnviarAuditor(false);
        return "/firmante/verificarProcedenciaDoctoElectronico/indexPropuestasRechazadasVerifDocto?faces-redirect=true";
    }

    public void tipoPropuesta() {
        BigDecimal tipo = propRechazadasHelper.getPropuesta().getIdTipoPropuesta();
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            propRechazadasHelper.setTipoPropuesta(false);
        } else {
            propRechazadasHelper.setTipoPropuesta(true);
        }
    }

    public void comparaIdc() {
        if (propRechazadasHelper.getContribuyente().equals(propRechazadasHelper.getContribuyenteAnt())) {
            propRechazadasHelper.setDivMostrar("divDatosContNoAct");
        } else {
            propRechazadasHelper.setDivMostrar("divDatosContAct");
        }
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (propRechazadasHelper.getListaImpuestos() != null) {
            for (FecetImpuesto imp : propRechazadasHelper.getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public String buscarDocumentosRechazo(String idRechazo) {
        propRechazadasHelper.setListaDocumentosRechazo(
                propuestasRechazadasVerifDoctoService.buscarDoctosRechazoPorIdRechazo(new BigDecimal(idRechazo)));
        return "pantalla";
    }

    public void visualizarDocumentos() {
        logger.info("--- Se van a obtener documentos de idRechazoPropuesta: {}",
                propRechazadasHelper.getIdRechazoPropuesta());
        buscarDocumentosRechazo(propRechazadasHelper.getIdRechazoPropuesta());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogoDocumentosRechazo').show();");
    }

    @Override
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (path != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(path)),
                        PropuestaVistaCargaArchivosUtil.obtenContentTypeArchivo(nombreDescarga),
                        PropuestaVistaCargaArchivosUtil.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]", e.getMessage());
                addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    public String enviarAuditor() {
        String correo;
        propuestasRechazadasVerifDoctoService
                .actualizaEstatusPropuesta(new BigDecimal(propRechazadasHelper.getIdPropuesta()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogConfirmarEnviarAuditor').hide();");
        propRechazadasHelper.setBotonEnviarAuditor(true);
        try {
            correo = propuestasRechazadasVerifDoctoService
                    .obtenerToCorreo(propRechazadasHelper.getPropuesta().getRfcAuditor());
            if (correo != null && !correo.isEmpty()) {
                propuestasRechazadasVerifDoctoService.enviaCorreo(
                        getMessageResourceString("asunto.correo.rechazadas.verificacion.docto"),
                        propRechazadasHelper.getIdRegistro(), ReportType.AVISO_RECHAZO_VERIFICACION_PROCEDENCIA_DOCTO,
                        correo);
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al enviar el correo: {}", e.getMessage());
        }
        return null;
    }

    public void muestraDialogoEnviarAuditor() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogConfirmarEnviarAuditor').show();");
    }

    public String regresar() {
        contadorOrdenesValidarFirmar.init();
        return "/firmante/validarFirmarDocElectronico/indexValidarFirmarOrdenes?faces-redirect=true";
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setOrigenCentralRegionalService(OrigenCentralRegionalServ origenCentralRegionalService) {
        this.origenCentralRegionalService = origenCentralRegionalService;
    }

    public OrigenCentralRegionalServ getOrigenCentralRegionalService() {
        return origenCentralRegionalService;
    }

    public void setRegistrarPropuestaIndividualService(
            RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
        this.registrarPropuestaIndividualService = registrarPropuestaIndividualService;
    }

    public RegistrarPropuestaIndividualService getRegistrarPropuestaIndividualService() {
        return registrarPropuestaIndividualService;
    }

    public void setDocumentoSeleccionDescarga(StreamedContent documentoSeleccionDescarga) {
        this.documentoSeleccionDescarga = documentoSeleccionDescarga;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        documentoSeleccionDescarga = getDescargaArchivo(
                propRechazadasHelper.getDocumentoSeleccionado().getRutaArchivo(),
                propRechazadasHelper.getDocumentoSeleccionado().getNombre());
        return documentoSeleccionDescarga;
    }

    public void setPropuestasRechazadasVerifDoctoService(
            PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService) {
        this.propuestasRechazadasVerifDoctoService = propuestasRechazadasVerifDoctoService;
    }

    public PropuestasRechazadasVerifDoctoService getPropuestasRechazadasVerifDoctoService() {
        return propuestasRechazadasVerifDoctoService;
    }

    public void setContadorOrdenesValidarFirmar(ContadorOrdenesValidarFirmarMB contadorOrdenesValidarFirmar) {
        this.contadorOrdenesValidarFirmar = contadorOrdenesValidarFirmar;
    }

    public ContadorOrdenesValidarFirmarMB getContadorOrdenesValidarFirmar() {
        return contadorOrdenesValidarFirmar;
    }

    public void setDocumentoSeleccionDescargaOrden(StreamedContent documentoSeleccionDescargaOrden) {
        this.documentoSeleccionDescargaOrden = documentoSeleccionDescargaOrden;
    }

    public StreamedContent getDocumentoSeleccionDescargaOrden() {
        documentoSeleccionDescargaOrden = getDescargaArchivo(
                propRechazadasHelper.getDocumentoSeleccionadoOrden().getRutaArchivo(),
                propRechazadasHelper.getDocumentoSeleccionadoOrden().getNombre());
        return documentoSeleccionDescargaOrden;
    }

    public void setDocumentoSeleccionDescargaRechazo(StreamedContent documentoSeleccionDescargaRechazo) {
        this.documentoSeleccionDescargaRechazo = documentoSeleccionDescargaRechazo;
    }

    public StreamedContent getDocumentoSeleccionDescargaRechazo() {
        documentoSeleccionDescargaRechazo = getDescargaArchivo(
                propRechazadasHelper.getDocumentoSeleccionadoRechazo().getRutaArchivo(),
                propRechazadasHelper.getDocumentoSeleccionadoRechazo().getNombre());
        return documentoSeleccionDescargaRechazo;
    }

    public PropuestasRechazadasDtoHelper getPropRechazadasHelper() {
        return propRechazadasHelper;
    }

    public void setPropRechazadasHelper(PropuestasRechazadasDtoHelper propRechazadasHelper) {
        this.propRechazadasHelper = propRechazadasHelper;
    }
}
