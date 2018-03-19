package mx.gob.sat.siat.feagace.vista.propuestas.consulta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecebAccionPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.HistoricoPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.VerificarProcedenciaService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper.ConsultarPropuestasAsignadasHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ViewScoped
@ManagedBean(name = "consultarPropuestasAsignadasMB")
public class ConsultarPropuestasAsignadasMB extends AbstractManagedBean {
    /**
     * 
     */
    private static final long serialVersionUID = 6052810499554308403L;

    private ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper;
    private FecetPropuesta propuestaAnalizar;
    private String labelFechaComite;

    @ManagedProperty(value = "#{fecebAccionPropuestaService}")
    private transient FecebAccionPropuestaService fecebAccionPropuestaService;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;

    @ManagedProperty(value = "#{historicoDocPropuestaService}")
    private transient HistoricoPropuestaService historicoPropuestaService;

    @ManagedProperty(value = "#{validarRetroalimentarPropuestaService}")
    private transient ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;

    @ManagedProperty(value = "#{verificarProcedenciaService}")
    private transient VerificarProcedenciaService verificarProcedenciaService;

    public void init() {

        if (consultarPropAsignadaHelper == null) {
            consultarPropAsignadaHelper = new ConsultarPropuestasAsignadasHelper();
        }

        if (getSession().getAttribute("propuestaSelAnalizar") != null) {
            setPropuestaAnalizar((FecetPropuesta) getSession().getAttribute("propuestaSelAnalizar"));
        }

        setLabelFechaComite(validaFechaComite(getPropuestaAnalizar().getIdArace(), getPropuestaAnalizar().getFechaInforme(),
                getPropuestaAnalizar().getFechaPresentacion()));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void handleToggleAcciones(ToggleEvent event) {
        logger.error("Consultando Historial de Acciones de la propuesta");
        init();
        if (getPropuestaAnalizar() != null) {
            consultarPropAsignadaHelper.setListaHistoricoAccion(new ArrayList<FecebAccionPropuesta>());

            List<FecebAccionPropuesta> listaAccionSinTabla = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaSinTabla(getPropuestaAnalizar().getIdPropuesta());

            List<FecebAccionPropuesta> listaAccionTablaRechazo = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaRechazadas(getPropuestaAnalizar().getIdPropuesta(), AccionesFuncionarioEnum.RECHAZAR);

            List<FecebAccionPropuesta> listaAccionTablaCancelar = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaCancelada(getPropuestaAnalizar().getIdPropuesta(), AccionesFuncionarioEnum.CANCELAR);

            List<FecebAccionPropuesta> listaAccionTablaRetrolimentar = fecebAccionPropuestaService.obtenerAccionesPropuestaRetroalimentar(
                    getPropuestaAnalizar().getIdPropuesta(), AccionesFuncionarioEnum.RETROALIMENTAR, AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA);

            List<FecebAccionPropuesta> listaAccionTablaTransferir = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaTransferir(getPropuestaAnalizar().getIdPropuesta(), AccionesFuncionarioEnum.TRANSFERIR);

            agregarDocumentoLista(listaAccionSinTabla);
            agregarDocumentoLista(listaAccionTablaRechazo);
            agregarDocumentoLista(listaAccionTablaCancelar);
            agregarDocumentoLista(listaAccionTablaRetrolimentar);
            agregarDocumentoLista(listaAccionTablaTransferir);

            Collections.sort(consultarPropAsignadaHelper.getListaHistoricoAccion(), new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    FecebAccionPropuesta p1 = (FecebAccionPropuesta) o1;
                    FecebAccionPropuesta p2 = (FecebAccionPropuesta) o2;
                    return p1.getIdAccionPropuesta().compareTo(p2.getIdAccionPropuesta());
                }
            });

            for (FecebAccionPropuesta accion : consultarPropAsignadaHelper.getListaHistoricoAccion()) {
                try {
                    EmpleadoDTO empleado = consultarPropuestasAsignadasService.obtenerEmpleadoXId(accion.getIdEmpleado());
                    TipoEmpleadoEnum tipoEmpleadoEnum = TipoEmpleadoEnum.parse(accion.getFececAccionesFuncionario().getIdTipoEmpleado().intValue());
                    accion.setDescripcionTipoEmpleado(tipoEmpleadoEnum.getDescripcion());
                    empleado.setNombre(empleado.getNombreCompleto());
                    accion.setEmpleadoDto(empleado);
                } catch (Exception e) {
                    EmpleadoDTO empleado = new EmpleadoDTO();
                    accion.setEmpleadoDto(empleado);
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public void handleToggleRetrolimentacion(ToggleEvent event) {
        init();
        if (getPropuestaAnalizar().getIdPropuesta() != null) {
            consultarPropAsignadaHelper.setListaHistoricoRetroalimentar(
                    validarRetroalimentarPropuestaService.getLstHistorialRetroalimentacionPropuesta(getPropuestaAnalizar().getIdPropuesta()));
        }
    }

    public void handleToggleExpedienteOrden(ToggleEvent event) {
        init();
        consultarPropAsignadaHelper.setListaHistorialOrden(historicoPropuestaService.obtenerDocsOrdenHistorial(getPropuestaAnalizar().getIdPropuesta()));

        consultarPropAsignadaHelper.setListaHistorialOficio(null);
        if (consultarPropAsignadaHelper.getListaHistorialOrden() != null && !consultarPropAsignadaHelper.getListaHistorialOrden().isEmpty()) {

            consultarPropAsignadaHelper.setListaHistorialOficio(historicoPropuestaService
                    .oficiosHistorial(verificarProcedenciaService.obtenerOrden(getPropuestaAnalizar().getIdPropuesta()).get(0).getIdOrden()));
        }
    }

    private void agregarDocumentoLista(List<FecebAccionPropuesta> listaDocumetos) {
        if (listaDocumetos != null && !listaDocumetos.isEmpty()) {
            consultarPropAsignadaHelper.getListaHistoricoAccion().addAll(listaDocumetos);
        }
    }

    public void obtenerImpuestosRetro() {
        consultarPropAsignadaHelper.setListaImpuestos(getValidarRetroalimentarPropuestaService()
                .getImpuestosRetroPropuesta(consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacion()));

        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (consultarPropAsignadaHelper.getListaImpuestos() != null) {
            for (FecetImpuesto imp : consultarPropAsignadaHelper.getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }

        consultarPropAsignadaHelper.setPresuntiva(totalImpuesto);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoImpuestosRetro').show();");
    }

    public void obtenerDocumentosHistoricoAccion() {

        AccionesFuncionarioEnum accionesFuncionarioEnum = consultarPropAsignadaHelper.getHistoricoAccion().getAccionFuncionarioEnum();
        List<FecetDocExpediente> listaDocumentos = new ArrayList<FecetDocExpediente>();

        if (accionesFuncionarioEnum != null) {
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RECHAZAR) {
                listaDocumentos = historicoPropuestaService.buscarDoctosRechazoPorIdRechazo(consultarPropAsignadaHelper.getHistoricoAccion().getIdRechazo());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTAR) {
                listaDocumentos = historicoPropuestaService
                        .obtenerDocumentoByIdRetro(consultarPropAsignadaHelper.getHistoricoAccion().getIdRetroalimentacion());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.TRANSFERIR) {
                listaDocumentos = historicoPropuestaService
                        .obtenerDocumentoByIdTransferencia(consultarPropAsignadaHelper.getHistoricoAccion().getIdTransferencia());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.CANCELAR) {
                listaDocumentos = historicoPropuestaService
                        .obtenerDocumentoByIdCancelacion(consultarPropAsignadaHelper.getHistoricoAccion().getIdCancelacion());

            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA) {
                listaDocumentos = historicoPropuestaService
                        .obtenerDocumentoByIdRetro(consultarPropAsignadaHelper.getHistoricoAccion().getIdRetroalimentacion());
            }
        }
        consultarPropAsignadaHelper.setListaDocumentosExpediente(listaDocumentos);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionAccion').show();");
    }

    public void obtenerDocumentosRetroalimentados() {
        consultarPropAsignadaHelper.setListaDocumentos(getValidarRetroalimentarPropuestaService()
                .getDetalleDocRetroalimentacionByIdRetro(consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacion()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public void obtenerSolictudRetroalimentacion() {
        consultarPropAsignadaHelper.setListaSolicitudRetroalimentacion(getValidarRetroalimentarPropuestaService()
                .getLstOrigenRetroalimentacionPropuesta(consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoRetroalimentacion').show();");
    }

    public void obtenerDocumentosRetroalimentacion() {

        consultarPropAsignadaHelper.setListaDocumentos(getValidarRetroalimentarPropuestaService()
                .getDetalleDocRetroalimentacionByIdRetro(consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public String validaFechaComite(BigDecimal idArace, Date fechaInforme, Date fechaPresentacion) {

        String tituloEtiqueta = "";

        if (idArace.longValue() == TipoAraceEnum.ACAOCE.getId() || idArace.longValue() == TipoAraceEnum.ACOECE.getId()) {

            if (fechaInforme != null) {
                tituloEtiqueta = "Fecha de Informe a Comit\u00e9";
            }

            if (fechaPresentacion != null) {
                tituloEtiqueta = "Fecha de Presentaci\u00f3n a Comit\u00e9";
            }
        } else {
            if (fechaInforme != null) {
                tituloEtiqueta = "Fecha de Informe a Comit\u00e9 Regional";
            }

            if (fechaPresentacion != null) {
                tituloEtiqueta = "Fecha de Presentaci\u00f3n a Comit\u00e9 Regional";
            }
        }

        return tituloEtiqueta;
    }

    public StreamedContent getDescargarArchivo() {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilOrdenes.limpiarPathArchivo(consultarPropAsignadaHelper.getRutaArchivo())),
                    CargaArchivoUtilOrdenes.obtenContentTypeArchivo(consultarPropAsignadaHelper.getNombreArchivo()),
                    CargaArchivoUtilOrdenes.aplicarCodificacionTexto(consultarPropAsignadaHelper.getNombreArchivo()));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            FacesUtil.addErrorMessage(null, "No se encontr\u00f3 el documento seleccionado", "");
        }
        return archivo;
    }

    public void redireccionaRegreso() throws IOException {

        String urlRedireccion = "/faces/consultarDocumentos/consultaPropuestasOrdenes.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

    public ConsultarPropuestasAsignadasHelper getConsultarPropAsignadaHelper() {
        return consultarPropAsignadaHelper;
    }

    public void setConsultarPropAsignadaHelper(ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper) {
        this.consultarPropAsignadaHelper = consultarPropAsignadaHelper;
    }

    public FecebAccionPropuestaService getFecebAccionPropuestaService() {
        return fecebAccionPropuestaService;
    }

    public void setFecebAccionPropuestaService(FecebAccionPropuestaService fecebAccionPropuestaService) {
        this.fecebAccionPropuestaService = fecebAccionPropuestaService;
    }

    public FecetPropuesta getPropuestaAnalizar() {
        return propuestaAnalizar;
    }

    public void setPropuestaAnalizar(FecetPropuesta propuestaAnalizar) {
        this.propuestaAnalizar = propuestaAnalizar;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void setConsultarPropuestasAsignadasService(ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }

    public HistoricoPropuestaService getHistoricoPropuestaService() {
        return historicoPropuestaService;
    }

    public void setHistoricoPropuestaService(HistoricoPropuestaService historicoPropuestaService) {
        this.historicoPropuestaService = historicoPropuestaService;
    }

    public ValidarRetroalimentarPropuestaService getValidarRetroalimentarPropuestaService() {
        return validarRetroalimentarPropuestaService;
    }

    public void setValidarRetroalimentarPropuestaService(ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService) {
        this.validarRetroalimentarPropuestaService = validarRetroalimentarPropuestaService;
    }

    public String getLabelFechaComite() {
        return labelFechaComite;
    }

    public void setLabelFechaComite(String labelFechaComite) {
        this.labelFechaComite = labelFechaComite;
    }

    public VerificarProcedenciaService getVerificarProcedenciaService() {
        return verificarProcedenciaService;
    }

    public void setVerificarProcedenciaService(VerificarProcedenciaService verificarProcedenciaService) {
        this.verificarProcedenciaService = verificarProcedenciaService;
    }

}
