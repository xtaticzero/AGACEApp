package mx.gob.sat.siat.feagace.vista.propuestas.subadmin;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACAOCE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACOECE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORESTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORTE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_OCCIDENTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_PACIFICO_NORTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_SUR;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.EHO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ORG;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.REE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.UCA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DatosPropuestaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.FececMotivoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.OrigenCentralRegionalServ;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarDocumentoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.VerificarProcedenciaService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.AsignarDocumentoHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.PropuestaVO;
import mx.gob.sat.siat.feagace.vista.propuestas.subadmin.helper.VerificarProcedenciaHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.subadmin.vo.VerificarProcedenciaVO;

@ManagedBean(name = "verificarProcedenciaMB")
@ViewScoped
public class VerificarProcedenciaManagedBean extends BaseManagedBean {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{verificarProcedenciaService}")
    private transient VerificarProcedenciaService verificarProcedenciaService;

    @ManagedProperty(value = "#{origenCentralRegionalService}")
    private transient OrigenCentralRegionalServ origenCentralRegionalServ;

    @ManagedProperty(value = "#{asignarDocumentoService}")
    private transient AsignarDocumentoService asignarDocumentoService;

    private transient VerificarProcedenciaVO verificarProcVO;

    @ManagedProperty(value = "#{cargaDocumentoElectronicoService}")
    private transient CargaDocumentoElectronicoService cargaDocumentoElectronicoService;

    @ManagedProperty(value = "#{fececMotivoService}")
    private transient FececMotivoService fececMotivoService;

    @ManagedProperty(value = "#{propuestasRechazadasVerifDoctoService}")
    private transient PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService;

    @ManagedProperty(value = "#{notificacionService}")
    private transient NotificacionService notificacionService;

    @ManagedProperty(value = "#{validarContribuyenteService}")
    private transient ValidarContribuyenteService validarContribuyenteService;

    private StreamedContent documentoHistoDescargaOrden;
    private StreamedContent documentoSeleccionDescargaOrden;
    private StreamedContent documentoSeleccionDescargaOficio;

    private FecetDocExpediente docSeleccionado;
    private StreamedContent docSeleccionadoDescarga;
    private FecetRechazoPropuesta documentoSeleccionadoRechazo;
    private Date fechaCarga;

    private VerificarProcedenciaHelper verificarProcedenciaHelper;

    private static final String ARCHIVO_DUPLICADO = "El archivo ya fue cargado";
    private static final String ERROR_DOCUMENTO_ADJUNTO = "No se pudo adjuntar el documento ";
    private static final String ARCHIVO_CARGADO = "Archivo cargado exitosamente";
    private static final String TIPO_ARCHIVO_INVALIDO = "Debe subir un archivo de tipo: PDF, Word o Excel 2007 o superior";
    private static final String PERIODO_PROPUESTO = "Periodo Propuesto";
    private static final String COMA = ",";

    @PostConstruct
    public void init() {
        setVerificarProcedenciaHelper(new VerificarProcedenciaHelper());
        verificarProcVO = new VerificarProcedenciaVO();
        verificarProcVO.setPropuestaVO(new PropuestaVO());
        verificarProcVO.setMostrarInfoProps(true);
        verificarProcVO.setMostrarInfo(false);
        documentoSeleccionadoRechazo = new FecetRechazoPropuesta();

        List<FecetPropuesta> lista = verificarProcedenciaService.obtenerPropuestasAsignadasASubAdmin(getRFCSession());
        logger.debug("tamanio de lista:::: " + lista.size());
        verificarProcVO.setListaPropuestas(AsignarDocumentoHelper.llenarListaDeDto(lista));
        verificarProcVO.setListaMotivos(fececMotivoService.findWhereTipoMotivoEquals());

        for (PropuestaVO propVO : verificarProcVO.getListaPropuestas()) {
            propVO.setPresuntiva(asignarDocumentoService.calcularPresuntivaDePropuesta(propVO.getIdPropuesta()));
        }
    }

    public void validarContribuyente(ActionEvent actionEvent) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String idRegistro = params.get("idRegistro");
        for (PropuestaVO propVO : verificarProcVO.getListaPropuestas()) {
            if (idRegistro.equals(propVO.getIdRegistro())) {
                verificarProcVO.setPropuestaVO(propVO);
            }
        }
        verificarProcVO.setListaRechazo(new ArrayList<FecetRechazoPropuesta>());
        boolean validacionAmp = verificarProcedenciaService
                .validaEstatusContribuyente(verificarProcVO.getPropuestaVO().getRfcContribuyente());
        if (validacionAmp) {
            logger.info("amparado::: ?", validacionAmp);
            RequestContext.getCurrentInstance().execute("PF('msgAmp').show();");
        } else if (getValidarContribuyenteService()
                .validaPPEE(verificarProcVO.getPropuestaVO().getRfcContribuyente()) == 0) {
            //cambiar la condicion a que sea igual a cero cuando el servicio se habilite
            logger.info("validaPPEE::: ?", getVerificarProcedenciaService()
                    .validaPPEE(verificarProcVO.getPropuestaVO().getRfcContribuyente()));
            RequestContext.getCurrentInstance().execute("PF('msgPe').show();");

        } else {
            try {
                logger.info("Se obtienen datos del contributente: ");
                BigDecimal idContribuyente;
                idContribuyente = verificarProcVO.getPropuestaVO().getIdContribuyente();
                getVerificarProcedenciaHelper()
                        .setAdicionalContribuyente(verificarProcedenciaService.validarContribuyenteVersusIdc(
                                        verificarProcVO.getPropuestaVO().getRfcContribuyente(), idContribuyente));
                DatosPropuestaDTO datosPropDTO = new DatosPropuestaDTO();
                datosPropDTO.setIdPropuesta(verificarProcVO.getPropuestaVO().getIdPropuesta());

                getVerificarProcedenciaHelper().getAdicionalContribuyente().setDatosPreviosPropuesta(datosPropDTO);

                if (getVerificarProcedenciaHelper().getAdicionalContribuyente().isExistenCambios()) {
                    aceptarCambioDeContribuyente(actionEvent);
                } else {
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().setContribuyentePropuesta(
                            getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyente());
                    verificarProcVO.setMostrarInfoProps(false);
                    verificarProcVO.setMostrarInfo(false);
                    cargarDatosContribuyentePropuesta();
                }

            } catch (NegocioException e) {
                logger.error(e.getMessage());
                RequestContext.getCurrentInstance().execute("PF('msgPeerror').show();");
            }
        }
    }

    public void aceptarActualizacion(ActionEvent actionEvent) {
        verificarProcVO.setMostrarInfo(false);
        verificarProcVO.setMostrarInfoProps(false);
        cargarDatosContribuyentePropuesta();

    }

    public void aceptarCambioDeContribuyente(ActionEvent actionEvent) {
        getVerificarProcedenciaHelper().getAdicionalContribuyente().setContribuyentePropuesta(
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyenteIdc());
        getVerificarProcedenciaService().actualizaContribuyente(
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyenteIdc());
        RequestContext.getCurrentInstance().execute("PF('msgUpdateContribuyente').show();");
        verificarProcVO.setMostrarInfo(true);
        verificarProcVO.setMostrarInfoProps(false);
        verificarProcVO.setMostrarContribuyentePropuesta(false);
    }

    public void regresarBandejaPropuestas(ActionEvent actionEvent) {
        verificarProcVO.setMostrarContribuyentePropuesta(false);
        verificarProcVO.setMostrarInfoProps(true);
    }

    public void regresarBandejaPropuestas2(ActionEvent actionEvent) {
        verificarProcVO.setMostrarInfo(false);
        verificarProcVO.setMostrarInfoProps(true);
    }

    private void cargarDatosContribuyentePropuesta() {
        cargaDocumentoElectronicoService
                .obtenerDatosPreviosDePropuesta(getVerificarProcedenciaHelper().getAdicionalContribuyente());
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setListaDocumentosTabla(propuestasRechazadasVerifDoctoService
                        .buscarDoctosExpedienteProp(verificarProcVO.getPropuestaVO().getIdPropuesta()));
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setListaDocsOrden(verificarProcedenciaService.obtenerDocsOrden(getVerificarProcedenciaHelper()
                                .getAdicionalContribuyente().getDatosPreviosPropuesta().getIdPropuesta()));
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setListaDocsOficio(
                        verificarProcedenciaService
                        .oficiosPorFirmar(
                                verificarProcedenciaService
                                .obtenerOrden(
                                        getVerificarProcedenciaHelper().getAdicionalContribuyente()
                                        .getDatosPreviosPropuesta().getIdPropuesta())
                                .get(0).getIdOrden()));
        List<FecetAsociado> representantes = propuestasRechazadasVerifDoctoService
                .getRepresentanteLegal(getVerificarProcedenciaHelper().getAdicionalContribuyente()
                        .getDatosPreviosPropuesta().getIdPropuesta());
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setRepLegal(representantes.isEmpty() ? new FecetAsociado() : representantes.get(0));
        List<FecetAsociado> agentes = propuestasRechazadasVerifDoctoService
                .getAgenteAduanal(getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .getIdPropuesta());
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setAgenAduanal(agentes.isEmpty() ? new FecetAsociado() : agentes.get(0));
        verificarProcVO.setMostrarContribuyentePropuesta(true);
        setFechaCarga(getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .getListaDocsOrden().get(0).getFechaCreacion());
        validaMetodoArace(getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().getIdArace(),
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().getIdMetodo());
        if (getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta().getTipoPropuesta()
                .equals(PERIODO_PROPUESTO)) {
            getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                    .setTipoActivoPropuesta(true);
        } else {
            getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                    .setTipoActivoPropuesta(false);
        }
    }

    public void crearMensajeAprobacionUno(ActionEvent actionEvent) {
        verificarProcVO.setMensaje1(
                AsignarDocumentoHelper.crearMensajeAprobacion1(verificarProcVO.getPropuestaVO().getIdRegistro()));
        RequestContext.getCurrentInstance().execute("PF('msgAprob1').show();");
    }

    public void aprobarProcedencia(ActionEvent actionEvent) {
        try {
            verificarProcedenciaService.aprobarPropuesta(getVerificarProcedenciaHelper().getAdicionalContribuyente());
            verificarProcVO.setMensaje2(
                    AsignarDocumentoHelper.crearMensajeAprobacion2(verificarProcVO.getPropuestaVO().getIdRegistro()));

            /**
             * Obtener los datos del Administrador.
             */
            Set<String> destinatarios = new HashSet<String>();
            List<PropuestaVO> listaSelecccionados = new ArrayList<PropuestaVO>();
            listaSelecccionados.add(verificarProcVO.getPropuestaVO());

            enviarNotificacion(destinatarios, listaSelecccionados,
                    LeyendasPropuestasEnum.AVISO_ORDEN_FIRMA_DOCTO_ELECTRONICO.getIdLeyenda());

            RequestContext.getCurrentInstance().execute("PF('msgAprob2').show();");

        } catch (Exception e) {
            logger.error("enviarPropuestasRechazadas" + e.toString());
            for (StackTraceElement er : e.getStackTrace()) {
                logger.error("epr " + er.getLineNumber() + " " + e.getClass());
            }
        }
    }

    public void aceptarMensaje2(ActionEvent actionEvent) {
        init();
    }

    public void mostrarRechazo(ActionEvent action) {

        verificarProcVO.setMensajeRechazo1(AsignarDocumentoHelper.crearMensajeRechazo1());
        verificarProcVO.setMensajeRechazo2(AsignarDocumentoHelper.crearMensajeRechazo2());

        RequestContext.getCurrentInstance().execute("PF('rechazoPropuesta').show();");
    }

    public void crearMensajeRechazoUno(ActionEvent actionEvent) {
        RequestContext.getCurrentInstance().execute("PF('msgRech1').show();");
    }

    public void rechazarProcedencia(ActionEvent actionEvent) {
        RequestContext.getCurrentInstance().execute("PF('msgRech2').show();");
    }

    public void cargaArchivoRechazo(final FileUploadEvent event) {

        if (AsignarDocumentoHelper.validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().setFecetContribuyente(
                        getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyentePropuesta());
                verificarProcVO.setArchivoCarga(event.getFile());
                FecetRechazoPropuesta dto = new FecetRechazoPropuesta();
                final Date fecha = new Date();
                dto.setFechaRechazo(fecha);
                dto.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(
                        CargaArchivoUtilPropuestas.aplicarCodificacionTexto(verificarProcVO.getArchivoCarga().getFileName())));
                dto.setRutaArchivo(RutaArchivosUtilPropuestas.generarRutaArchivoValidaPropuestas(
                        RutaArchivosEnum.RUTA_DOCUMENTOS_RECHAZO_PROPUESTAS,
                        getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()));
                if (!AsignarDocumentoHelper.validaNombreArchivo(verificarProcVO.getListaRechazo(),
                        dto.getNombreArchivo())) {
                    dto.setArchivo(verificarProcVO.getArchivoCarga().getInputstream());
                    verificarProcVO.getListaRechazo().add(dto);
                    addMessage(null, ARCHIVO_CARGADO, "");
                } else {
                    addMessage(null, ARCHIVO_DUPLICADO, "");
                }
            } catch (IOException e) {
                logger.error(ERROR_DOCUMENTO_ADJUNTO, e);
            }
        } else {
            addErrorMessage(null, TIPO_ARCHIVO_INVALIDO, "");
        }
    }

    public void validarRechazoInsumo(ActionEvent event) {

        boolean bandera = true;

        if (verificarProcVO.getListaRechazo() == null || verificarProcVO.getListaRechazo().size() <= 0L) {

            addErrorMessage(null, "Debe cargar un documento", "");

            bandera = false;
        }

        if (getVerificarProcedenciaHelper().getListaSeleccionDocsOrden().isEmpty()
                && getVerificarProcedenciaHelper().getListaSeleccionOficios().isEmpty()) {
            addErrorMessage(null, "Seleccionar un orden u oficio a rechazar", "");

            bandera = false;
        }

        if (verificarProcVO.getPropuestaVO().getObservacion().trim().isEmpty()) {
            addErrorMessage(null, "Campo Observacion requerido", "");

            bandera = false;
        }

        if (bandera) {

            verificarProcVO.setMensajeRechazo1(cargaMensaje());
            RequestContext.getCurrentInstance().execute("PF('msgRech1').show();");
        }
    }

    public String rechazarInsumo() throws NegocioException {
        getVerificarProcedenciaHelper().setRechazoPropuesta(new FecetRechazoPropuesta());
        try {

            getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().setFecetContribuyente(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyentePropuesta());
            getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                    .setIdEstatus(new BigDecimal(Constantes.ENTERO_CIENTODIECIOCHO));

            verificarProcVO.setFechaHoy(new Date());

            getVerificarProcedenciaHelper().getRechazoPropuesta().setIdPropuesta(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().getIdPropuesta());
            getVerificarProcedenciaHelper().getRechazoPropuesta()
                    .setDescripcion(verificarProcVO.getPropuestaVO().getObservacion());
            getVerificarProcedenciaHelper().getRechazoPropuesta().setFechaRechazo(verificarProcVO.getFechaHoy());
            getVerificarProcedenciaHelper().getRechazoPropuesta().setIdEmpleado(
                    getVerificarProcedenciaService().getDatosEmpleadoSesion(getRFCSession()).getIdEmpleado());
            getVerificarProcedenciaHelper().getRechazoPropuesta().setRfcRechazo(getRFCSession());
            getVerificarProcedenciaHelper().getRechazoPropuesta().setEstatus(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta().getIdEstatus());
            getOrigenCentralRegionalServ().rechazarPropuesta(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta(),
                    getVerificarProcedenciaHelper().getRechazoPropuesta(), verificarProcVO.getListaRechazo());
            getVerificarProcedenciaService().rechazarPropuesta(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta(),
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getContribuyenteIdc(),
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                    .getListaDocsOrden());
            verificarProcVO.setMotivoRechazo("");

            if (getVerificarProcedenciaHelper().getListaSeleccionDocsOrden() != null
                    && !getVerificarProcedenciaHelper().getListaSeleccionDocsOrden().isEmpty()) {
                getVerificarProcedenciaService().inactivaDoctoOrden(
                        getVerificarProcedenciaHelper().getListaSeleccionDocsOrden().get(0).getIdDocExpediente());
                FecetRechazoOrden rechazoDocOrden = new FecetRechazoOrden();
                rechazoDocOrden.setEstatus(Constantes.CADENA_CERO);
                rechazoDocOrden.setDescripcion(String.valueOf(Constantes.ENTERO_DOCE));
                rechazoDocOrden.setFechaRechazo(new Date());
                rechazoDocOrden.setIdOrden(verificarProcedenciaService.obtenerOrden(getVerificarProcedenciaHelper()
                        .getAdicionalContribuyente().getDatosPreviosPropuesta().getIdPropuesta()).get(0).getIdOrden());
                rechazoDocOrden.setIdEmpleado(getVerificarProcedenciaService().getDatosEmpleadoSesion(getRFCSession()).getIdEmpleado());
                rechazoDocOrden.setIdDocOrden(
                        getVerificarProcedenciaHelper().getListaSeleccionDocsOrden().get(0).getIdDocExpediente());
                getVerificarProcedenciaService().rechazoDoctoOrden(rechazoDocOrden);
            }
            if (getVerificarProcedenciaHelper().getListaSeleccionOficios() != null
                    && !getVerificarProcedenciaHelper().getListaSeleccionOficios().isEmpty()) {
                for (FecetOficio oficio : getVerificarProcedenciaHelper().getListaSeleccionOficios()) {
                    getVerificarProcedenciaService().inactivaOficio(oficio.getIdOficio());
                    FecetRechazoOficio rechazoOficio = new FecetRechazoOficio();
                    rechazoOficio.setDescripcion(String.valueOf(Constantes.ENTERO_DOCE));
                    rechazoOficio.setEstatus(Constantes.CADENA_CERO);
                    rechazoOficio.setIdOficio(oficio.getIdOficio());
                    rechazoOficio.setFechaRechazo(new Date());
                    rechazoOficio.setIdEmpleado(getVerificarProcedenciaService().getDatosEmpleadoSesion(getRFCSession())
                            .getIdEmpleado());
                    getVerificarProcedenciaService().rechazoOficio(rechazoOficio);

                }
            }

            Set<String> destinatarios = new HashSet<String>();
            List<PropuestaVO> listaSelecccionados = new ArrayList<PropuestaVO>();
            listaSelecccionados.add(verificarProcVO.getPropuestaVO());

            enviarNotificacion(destinatarios, listaSelecccionados,
                    LeyendasPropuestasEnum.PROPUESTA_RECHAZADA_VERIFICACION_PROCEDENCIA_DOCTO.getIdLeyenda());

        } catch (NegocioException e) {
            logger.error("No se pudo rechazar la propuesta " + e.getCause(), e);
        } catch (Exception e) {
            logger.error("enviarPropuestasRechazadas" + e.toString());
            for (StackTraceElement er : e.getStackTrace()) {
                logger.error("epr " + er.getLineNumber() + " " + e.getClass());
            }
        }

        RequestContext.getCurrentInstance().execute("PF('msgRech2').show();");

        return "";
    }

    /**
     * Metodo que se utiliza para descargar los archivos
     *
     * @param path ruta en donde se encuentra el archivo
     * @param nombreDescarga nombre del archivo
     * @return objeto de tipo <code>StreamedContent</code>
     */
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilPropuestas.limpiarPathArchivo(path)),
                    CargaArchivoUtilPropuestas.obtenContentTypeArchivo(nombreDescarga),
                    CargaArchivoUtilPropuestas.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }

        return archivo;
    }

    private void enviarNotificacion(Set<String> destinatarios, List<PropuestaVO> propuestasSeleccionadas,
            BigDecimal idLeyenda) {

        String rfcFirmante = getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                .getRfcFirmante();
        destinatarios.add(verificarProcedenciaService.getDatosEmpleadoSesion(rfcFirmante).getCorreo());
        String rfcAuditor = getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                .getRfcAuditor();
        destinatarios.add(verificarProcedenciaService.getDatosEmpleadoSesion(rfcAuditor).getCorreo());
        String rfcProgramador = getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                .getRfcCreacion();
        destinatarios.add(verificarProcedenciaService.getDatosEmpleadoSesion(rfcProgramador).getCorreo());
        String rfcAdministrador = getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                .getRfcAdministrador();
        destinatarios
                .add(verificarProcedenciaService.getDatosEmpleadoSesion(rfcAdministrador).getCorreo());
        destinatarios.add(verificarProcedenciaService.getDatosEmpleadoSesion(getVerificarProcedenciaHelper()
                .getAdicionalContribuyente().getFecetPropuesta().getRfcSubadministrador()).getCorreo()
                .toString());

        for (PropuestaVO propuesta : propuestasSeleccionadas) {
            notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CENTRAL, propuesta.getIdArace(),
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);
            notificacionService.obtenerCorreoCentralAcppce(
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getFecetPropuesta()
                    .getRfcSubadministrador(),
                    TipoEmpleadoEnum.SUBADMINISTRADOR_EMISION_ORDENES.getId(), propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

            Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
            data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
            data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());
            verificarProcedenciaService.enviarCorreo(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
        }
    }

    public void mostrarHistorico() {
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setListaDocsHistorialOrden(
                        verificarProcedenciaService.obtenerDocsOrdenHistorial(getVerificarProcedenciaHelper()
                                .getAdicionalContribuyente().getDatosPreviosPropuesta().getIdPropuesta()));
        getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                .setListaDocsHistorialOficio(
                        verificarProcedenciaService
                        .oficiosHistorial(
                                verificarProcedenciaService
                                .obtenerOrden(
                                        getVerificarProcedenciaHelper().getAdicionalContribuyente()
                                        .getDatosPreviosPropuesta().getIdPropuesta())
                                .get(0).getIdOrden()));
        verificarProcVO.setMostrarContribuyentePropuesta(false);
        verificarProcVO.setMostrarHistorico(true);
    }

    public void ocultarHistorico() {
        verificarProcVO.setMostrarContribuyentePropuesta(true);
        verificarProcVO.setMostrarHistorico(false);
    }

    public void descartarDocumentoRechazo() {

        List<FecetRechazoPropuesta> descartarRechazo = new ArrayList<FecetRechazoPropuesta>();

        for (FecetRechazoPropuesta documento : verificarProcVO.getListaRechazo()) {

            if (getDocumentoSeleccionadoRechazo().equals(documento)) {
                descartarRechazo.add(documento);
                break;
            }
        }

        verificarProcVO.getListaRechazo().removeAll(descartarRechazo);
    }

    public void setVerificarProcedenciaService(VerificarProcedenciaService verificarProcedenciaService) {
        this.verificarProcedenciaService = verificarProcedenciaService;
    }

    public VerificarProcedenciaService getVerificarProcedenciaService() {
        return verificarProcedenciaService;
    }

    public void setAsignarDocumentoService(AsignarDocumentoService asignarDocumentoService) {
        this.asignarDocumentoService = asignarDocumentoService;
    }

    public AsignarDocumentoService getAsignarDocumentoService() {
        return asignarDocumentoService;
    }

    public void setVerificarProcVO(VerificarProcedenciaVO verificarProcVO) {
        this.verificarProcVO = verificarProcVO;
    }

    public VerificarProcedenciaVO getVerificarProcVO() {
        return verificarProcVO;
    }

    public void setCargaDocumentoElectronicoService(CargaDocumentoElectronicoService cargaDocumentoElectronicoService) {
        this.cargaDocumentoElectronicoService = cargaDocumentoElectronicoService;
    }

    public CargaDocumentoElectronicoService getCargaDocumentoElectronicoService() {
        return cargaDocumentoElectronicoService;
    }

    public void setOrigenCentralRegionalServ(OrigenCentralRegionalServ origenCentralRegionalServ) {
        this.origenCentralRegionalServ = origenCentralRegionalServ;
    }

    public OrigenCentralRegionalServ getOrigenCentralRegionalServ() {
        return origenCentralRegionalServ;
    }

    public void setFececMotivoService(FececMotivoService fececMotivoService) {
        this.fececMotivoService = fececMotivoService;
    }

    public FececMotivoService getFececMotivoService() {
        return fececMotivoService;
    }

    public void setPropuestasRechazadasVerifDoctoService(
            PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService) {
        this.propuestasRechazadasVerifDoctoService = propuestasRechazadasVerifDoctoService;
    }

    public PropuestasRechazadasVerifDoctoService getPropuestasRechazadasVerifDoctoService() {
        return propuestasRechazadasVerifDoctoService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setValidarContribuyenteService(ValidarContribuyenteService validarContribuyenteService) {
        this.validarContribuyenteService = validarContribuyenteService;
    }

    public ValidarContribuyenteService getValidarContribuyenteService() {
        return validarContribuyenteService;
    }

    public void setDocumentoSeleccionDescargaOrden(StreamedContent documentoSeleccionDescargaOrden) {
        this.documentoSeleccionDescargaOrden = documentoSeleccionDescargaOrden;
    }

    public StreamedContent getDocumentoSeleccionDescargaOrden() {

        documentoSeleccionDescargaOrden = getDescargaArchivo(
                getVerificarProcedenciaHelper().getDocumentoSeleccionadoOrden().getRutaArchivo(),
                getVerificarProcedenciaHelper().getDocumentoSeleccionadoOrden().getNombre());

        return documentoSeleccionDescargaOrden;
    }

    public StreamedContent getDocumentoHistoDescargaOrden() {

        documentoHistoDescargaOrden = getDescargaArchivo(
                getVerificarProcedenciaHelper().getDocumentoHistOrden().getRutaArchivo(),
                getVerificarProcedenciaHelper().getDocumentoHistOrden().getNombreArchivo());

        return documentoHistoDescargaOrden;
    }

    public StreamedContent getDocumentoSeleccionDescargaOficio() {

        documentoSeleccionDescargaOficio = getDescargaArchivo(
                getVerificarProcedenciaHelper().getDocumentoSeleccionadoOficio().getRutaArchivo(),
                getVerificarProcedenciaHelper().getDocumentoSeleccionadoOficio().getNombreArchivo());

        return documentoSeleccionDescargaOficio;
    }

    public void setDocSeleccionado(FecetDocExpediente docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public FecetDocExpediente getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setDocSeleccionadoDescarga(StreamedContent docSeleccionadoDescarga) {
        this.docSeleccionadoDescarga = docSeleccionadoDescarga;
    }

    public StreamedContent getDocSeleccionadoDescarga() {
        docSeleccionadoDescarga = getDescargaArchivo(this.docSeleccionado.getRutaArchivo(),
                this.docSeleccionado.getNombre());

        return docSeleccionadoDescarga;
    }

    public void setDocumentoSeleccionadoRechazo(FecetRechazoPropuesta documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public FecetRechazoPropuesta getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }

    public void setDocumentoSeleccionDescargaOficio(StreamedContent documentoSeleccionDescargaOficio) {
        this.documentoSeleccionDescargaOficio = documentoSeleccionDescargaOficio;
    }

    public void setDocumentoHistoDescargaOrden(StreamedContent documentoHistoDescargaOrden) {
        this.documentoHistoDescargaOrden = documentoHistoDescargaOrden;
    }

    public void validaMetodoArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo != null && sedeUnidad != null && !abreviaturaMetodo.equals(new BigDecimal(-1))) {
            boolean validaSedeUnidad = (sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE));
            boolean validaAbreviaturaMetodo = (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE)
                    || abreviaturaMetodo.equals(UCA));
            if ((sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE)) && abreviaturaMetodo.equals(ORG)) {
                if (getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .getPrioridadSugerida().equals("1")) {
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                            .setVisibleInfo(false);
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                            .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
                } else {
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                            .setVisibleInfo(true);
                    getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                            .setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComite"));
                }
            } else if (validaSedeUnidad && validaAbreviaturaMetodo) {
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setVisibleInfo(false);
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
            } else {
                validaUnidadArace(sedeUnidad, abreviaturaMetodo);
            }
        }
    }

    public void validaUnidadArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        boolean validaUnidad = sedeUnidad.equals(ARACE_NORESTE) || sedeUnidad.equals(ARACE_OCCIDENTE)
                || sedeUnidad.equals(ARACE_CENTRO) || sedeUnidad.equals(ARACE_SUR);
        if (sedeUnidad.equals(ARACE_PACIFICO_NORTE) || sedeUnidad.equals(ARACE_NORTE_CENTRO) || validaUnidad) {
            if (abreviaturaMetodo.equals(ORG)) {
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setVisibleInfo(true);
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setPresentacionComite(
                                getMessageResourceString("lbl.propuestas.fechaPresentacionComiteRegional"));
            } else {
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setVisibleInfo(false);
                getVerificarProcedenciaHelper().getAdicionalContribuyente().getDatosPreviosPropuesta()
                        .setInformacionComite(
                                getMessageResourceString("lbl.propuestas.fechaInformacionComiteRegional"));
            }
        }
    }

    public String cargaMensaje() {
        StringBuffer msgOrden = new StringBuffer();
        StringBuffer msgOficio = new StringBuffer();

        for (FecetDocExpediente orden : getVerificarProcedenciaHelper().getListaSeleccionDocsOrden()) {
            msgOrden.append(orden.getNombre().concat(COMA));
        }

        for (FecetOficio oficio : getVerificarProcedenciaHelper().getListaSeleccionOficios()) {
            msgOficio.append(oficio.getNombreArchivo().concat(COMA));
        }

        return getMessageResourceString("mensaje.dialog.rechazar.msg1", msgOrden, msgOficio);
    }

    public VerificarProcedenciaHelper getVerificarProcedenciaHelper() {
        return verificarProcedenciaHelper;
    }

    public void setVerificarProcedenciaHelper(VerificarProcedenciaHelper verificarProcedenciaHelper) {
        this.verificarProcedenciaHelper = verificarProcedenciaHelper;
    }

}
