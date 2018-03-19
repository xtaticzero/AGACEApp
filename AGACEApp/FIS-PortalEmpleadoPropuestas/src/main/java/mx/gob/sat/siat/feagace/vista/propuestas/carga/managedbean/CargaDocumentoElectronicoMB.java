package mx.gob.sat.siat.feagace.vista.propuestas.carga.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.helper.CargaDocumentoElectronicoMBHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.CargaDocumentoElectronicoMBAbstract;

@SessionScoped
@ManagedBean(name = "cargaDocumentoElectronicoMB")
public class CargaDocumentoElectronicoMB extends CargaDocumentoElectronicoMBAbstract {

    private static final long serialVersionUID = 5660745565248848548L;

    private List<FecetDocOrden> ordenesArchivoCargado;
    private List<FecetOficio> oficiosArchivoCargado;
    private transient UploadedFile archivoOrden;
    private FecetPropuesta propuestaSeleccionada;
    private String tipoColaborador;
    private String mensajeNoEnvioCorreo;
    private BigDecimal estatus1;
    private BigDecimal estatus2;
    private BigDecimal accion;

    @ManagedProperty(value = "#{cargaDocumentoElectronicoService}")
    private transient CargaDocumentoElectronicoService cargaDocumentoElectronicoService;
    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;
    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;
    @ManagedProperty(value = "#{cargaDocumentoElectronicoMBHelper}")
    private transient CargaDocumentoElectronicoMBHelper cargaDocumentoElectronicoMBHelper;
    @ManagedProperty(value = "#{auditoriaPropuestas}")
    private transient PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService;

    private List<FecetTipoOficio> oficios;
    private BigDecimal tipoOficioSeleccionado;
    private FecetOficio oficioSeleccionado;
    private FecetDocOrden docOrdenSeleccionado;
    private FecetOficio docOficioSeleccionado;

    @PostConstruct
    public void init() {
        setAgenteAduanalVO(new ColaboradorVO());
        inicializaColaboradores();
        this.oficios = new ArrayList<FecetTipoOficio>();
        this.tipoOficioSeleccionado = null;
        this.oficiosArchivoCargado = new ArrayList<FecetOficio>();
        this.oficioSeleccionado = new FecetOficio();
        setPermiteOficio(false);
        this.docOficioSeleccionado = new FecetOficio();
        this.docOrdenSeleccionado = new FecetDocOrden();
    }

    public void cargaGenerarRevision() {
        if (!isColaboradorGuardado()) {
            setColaboradorGuardado(false);
        }
        setMuestraOficio(false);
        setPermiteOficio(determinaPermisoCarga(propuestaSeleccionada));
        if (isPermiteOficio()) {
            setMuestraOficio(true);
        }
        setMuestraAgenteAduanal(validarMostrarAgenteAduanal(propuestaSeleccionada.getIdMetodo()));
    }

    private boolean determinaPermisoCarga(FecetPropuesta propuesta) {

        String tipoOficioActivo = "1";
        String oficioMetodoActivo = "1";

        oficios = cargaDocumentoElectronicoService.getOficiosAdministrables(propuesta.getIdMetodo(), tipoOficioActivo,
                oficioMetodoActivo);

        if (oficios != null && !oficios.isEmpty()) {
            return true;
        }
        return false;
    }

    private void inicializaColaboradores() {
        setAgenteAduanalVO(new ColaboradorVO());
        getAgenteAduanalVO().setTipoAsociado(Constantes.ID_AGENTE_ADUANAL);
        getAgenteAduanalVO().setNombreTipoAsociado(Constantes.AGENTE_ADUANAL);
    }

    public FecetDocOrden upload(FileUploadEvent event) {
        FecetDocOrden documento = new FecetDocOrden();
        if (validaArchivoCargaPropuesta(event.getFile())) {
            try {
                String diagonal = "/";
                String carpetaDocOrden = "documentoOrden";
                String carpetaUnica = (DateUtil
                        .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
                boolean isFileCargado = true;
                final Long tamanioMaximoMB = 4196000L;

                setArchivoCarga(event.getFile());
                if (isFileCargado && validaArchivoCarga(event.getFile(), 1L)) {
                    archivoOrden = event.getFile();
                    if (archivoOrden.getSize() > 0L && archivoOrden.getSize() <= tamanioMaximoMB) {
                        this.ordenesArchivoCargado = new ArrayList<FecetDocOrden>();
                        String nombreArchivo = CargaPropuestasArchivoUtil.limpiarPathArchivo(
                                CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getArchivoCarga().getFileName()));
                        String destinoTmp = RutaArchivosUtilPropuestas.armarRutaDestinoPropuesta(propuestaSeleccionada);
                        String destino = destinoTmp + carpetaDocOrden + diagonal + carpetaUnica + diagonal;

                        documento.setIdDocOrden(null);
                        documento.setFechaCreacion(new Date());
                        documento.setNombreArchivo(nombreArchivo);
                        documento.setRutaArchivo(destino);
                        documento.setEstatus("1");
                        documento.setDocumentoPdf("1");

                        logger.error(documento.getRutaArchivo());

                        if (validaNombreArchivo(this.ordenesArchivoCargado, documento.getNombreArchivo())) {
                            addErrorMessage(null, "El archivo ya fue cargado", "Verifique por favor");
                        } else {
                            documento.setArchivo(getArchivoCarga().getInputstream());
                            this.ordenesArchivoCargado.add(documento);
                            addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                        }
                    } else {
                        if (archivoOrden.getSize() >= tamanioMaximoMB) {
                            archivoOrden = null;
                            addErrorMessage(null,
                                    "Error al cargar el archivo. El archivo es demasiado grande");
                        } else {
                            archivoOrden = null;
                            addErrorMessage(null,
                                    "Error al cargar el archivo. El archivo es demasiado chico");
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
        return documento;
    }

    public void eliminaArchivo() {
        this.ordenesArchivoCargado = new ArrayList<FecetDocOrden>();
    }

    public void uploadOficio(FileUploadEvent event) {
        Long conversor = -1L;
        BigDecimal tOficio = new BigDecimal(conversor);

        if (tipoOficioSeleccionado != null && !tipoOficioSeleccionado.equals(tOficio)) {
            if (validaArchivoCarga(event.getFile(), 1L)) {
                FecetOficio oficio = new FecetOficio();
                FecetTipoOficio tipoOficio = new FecetTipoOficio();
                try {
                    int estatusOficio = EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getIdEstatus();
                    tipoOficio.setIdTipoOficio(tipoOficioSeleccionado);
                    tipoOficio.setBlnActivo(new BigDecimal(1L));
                    setOficioCarga(event.getFile());

                    oficio.setFecetTipoOficio(tipoOficio);
                    oficio.setNombreArchivo(CargaPropuestasArchivoUtil.limpiarPathArchivo(
                            CargaPropuestasArchivoUtil.aplicarCodificacionTexto(getOficioCarga().getFileName())));
                    oficio.setFechaCreacion(new Date());
                    oficio.setDocumentoPdf("0");
                    oficio.setSuspencionPlazo("0");
                    oficio.setIdEstatus(new BigDecimal(estatusOficio));

                    if (!validaNombreTipoOficio(this.oficiosArchivoCargado,
                            oficio.getFecetTipoOficio().getIdTipoOficio())) {
                        oficio.setArchivo(getOficioCarga().getInputstream());
                        this.oficiosArchivoCargado.add(oficio);
                        addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                    } else {
                        addErrorMessage(null, "El oficio ya fue cargado", "");
                    }
                } catch (IOException e) {
                    logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
                }
            } else {
                addErrorMessage(null, "S\u00f3lo se aceptan archivos WORD 2007 o superior", "");
            }
        } else {
            addErrorMessage(null, "Debes seleccionar un tipo de oficio", "");
        }
    }

    private boolean validaNombreTipoOficio(List<FecetOficio> listaOficios, BigDecimal tipoOficio) {

        for (FecetOficio oficio : listaOficios) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(tipoOficio)) {
                return true;
            }
        }
        return false;
    }

    public void descartarOficio() {

        List<FecetOficio> descartaOficio = new ArrayList<FecetOficio>();
        for (FecetOficio oficioEliminar : oficiosArchivoCargado) {
            if (oficioSeleccionado.equals(oficioEliminar)) {
                descartaOficio.add(oficioEliminar);
                break;
            }
        }

        oficiosArchivoCargado.removeAll(descartaOficio);
    }

    private boolean validaNombreArchivo(List<FecetDocOrden> listaDocumento, String nombre) {
        for (FecetDocOrden fecetDocOrden : listaDocumento) {
            if (fecetDocOrden.getNombreArchivo().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public String cancelarRevision() {
        limpiar();
        return "/propuestas/auditor/detallePropAsignadas.jsf?faces-redirect=true";
    }

    public void limpiar() {
        this.ordenesArchivoCargado = new ArrayList<FecetDocOrden>();
        this.oficiosArchivoCargado = new ArrayList<FecetOficio>();
        this.oficios.clear();
        cargaGenerarRevision();
        setAgenteAduanalVO(new ColaboradorVO());
        this.oficioSeleccionado = new FecetOficio();
        getAgenteAduanalVO().setDeshabilitarCorreo(false);
        getAgenteAduanalVO().setDeshabilitarCampos(false);
        setColaboradorGuardado(false);
    }

    public String registrarEstatusPropuesta() {
        try {
            String carpetaBaseOficios = "documentosOficio";
            String diagonal = "/";
            String tipoOficio = "tipoOficio";
            String carpetaUnica = (DateUtil
                    .getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            List<ColaboradorVO> colaboradores = new ArrayList<ColaboradorVO>();

            if (this.getOrdenesArchivoCargado() != null && this.getOficiosArchivoCargado() != null) {
                colaboradores.add(getAgenteAduanalVO());

                for (FecetDocOrden docOrden : ordenesArchivoCargado) {
                    getCargaDocumentoElectronicoService().cargaDocumento(docOrden.getRutaArchivo(),
                            docOrden.getArchivo(), docOrden.getNombreArchivo());
                }

                for (FecetOficio miOficio : this.oficiosArchivoCargado) {
                    String rutaTmp = RutaArchivosUtilPropuestas.armarRutaDestinoPropuesta(propuestaSeleccionada);
                    String rutaArchivo = rutaTmp + carpetaBaseOficios + diagonal + tipoOficio
                            + miOficio.getFecetTipoOficio().getIdTipoOficio() + diagonal + carpetaUnica + diagonal;
                    miOficio.setRutaArchivo(rutaArchivo + miOficio.getNombreArchivo());
                    getCargaDocumentoElectronicoService().cargaDocumento(rutaArchivo, miOficio.getArchivo(),
                            miOficio.getNombreArchivo());
                }

                if (getPropuestaSeleccionada().getFeceaMetodo() != null
                        && getPropuestaSeleccionada().getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.EHO.getId()))) {
                    getPistasAuditoriasPropuestasService().pistaGuardarAgenteAduanal(getPropuestaSeleccionada());
                }

                getCargaDocumentoElectronicoService().actualizarPropuesta(getPropuestaSeleccionada().getIdPropuesta(),
                        getPropuestaSeleccionada().getFecetContribuyente().getRfc(), getEmpleado(),
                        this.getOrdenesArchivoCargado(), colaboradores, this.oficiosArchivoCargado);

                limpiar();
                return "/propuestas/auditor/detallePropAsignadas.jsf?faces-redirect=true";
            } else {
                addErrorMessage(null, "Debe adjuntar un archivo", "");
            }
        } catch (NegocioException e) {
            logger.error(e.getMessage());
            addErrorMessage(e.getMessage());
        }
        return null;
    }

    public void guardarColaborador() {
        StringBuilder mensaje = new StringBuilder();
        if (!getAgenteAduanalVO().getRfc().isEmpty() && !getAgenteAduanalVO().getNombre().isEmpty()) {
            if (Constantes.JSF_AGENTE_ADUANAL.equals(tipoColaborador) && !getAgenteAduanalVO().isSinColaborador()) {
                mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_4);
                mensaje.append(getAgenteAduanalVO().getNombre());
                mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_2);
                addMessage(null, mensaje.toString(), "");
                getAgenteAduanalVO().setDeshabilitarCorreo(false);
                setColaboradorGuardado(true);
            }
        } else {
            addErrorMessage(null, "Debes buscar un Agente Aduanal para poder guardarlo", "");
        }

    }

    public void validaAceptarAdjuntar() {
        String validar = cargaDocumentoElectronicoMBHelper.validaAceptarAdjuntar(this.getOrdenesArchivoCargado(),
                this.isMuestraOficio(), this.oficiosArchivoCargado, tipoOficioSeleccionado, getAgenteAduanalVO(),
                isMuestraAgenteAduanal());
        if (validar != null) {
            addErrorMessage(null, validar, "");
        } else {
            if (isMuestraAgenteAduanal() && !isColaboradorGuardado()) {
                addErrorMessage(null, "Debes guardar el Agente Aduanal para poder Generar Revisi\u00f3n", "");
            } else {
                RequestContext.getCurrentInstance().execute("PF('aceptarAdjunar').show();");
            }
        }
    }

    public void buscarColaborador() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            setAgenteAduanalVO(validaColaboradorBuscado(getAgenteAduanalVO(), requestContext));
        }
    }

    public void validaRfc() {
        Pattern patron = Pattern.compile(Constantes.PATRON_RFC);
        Matcher esValido = null;

        if (getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            esValido = patron.matcher(getAgenteAduanalVO().getRfc().toUpperCase());
            if (esValido.find()) {
                buscarColaborador();
            } else {
                addErrorMessage(null, Constantes.MENSAJE_RFC_INCORRECTO, "");
            }
        }
    }

    private boolean checkMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = getConsultaMediosContactoService()
                .validaMediosContacto(rfc.toUpperCase());
        return validaMediosContactoBO.isFlag();
    }

    private ColaboradorVO validaColaboradorBuscado(ColaboradorVO colaboradorSelected, RequestContext requestContext) {
        ColaboradorVO colaborador = colaboradorSelected;
        FecetContribuyente contribuyenteIDC = null;
        if (!colaborador.getRfc().isEmpty()) {
            try {
                contribuyenteIDC = getContribuyenteService().getContribuyenteIDC(colaborador.getRfc());
                if (contribuyenteIDC != null) {
                    getCargaDocumentoElectronicoMBHelper().configurarColaborador(colaborador, contribuyenteIDC);
                    if (checkMediosContacto(contribuyenteIDC.getRfc())) {
                        colaborador.setMedioContactoBoolean(true);
                    } else {
                        requestContext.execute("PF('confirmarMediosContacto').show();");
                    }
                } else {
                    requestContext.execute("PF('noIdc').show();");
                }
            } catch (NoExisteContribuyenteException nece) {

                requestContext.execute("PF('noIdc').show();");
            }
        } else {
            colaborador.setDeshabilitarCampos(false);
            addErrorMessage(null, "Debe introducir un RFC", "");
        }
        return colaborador;
    }

    private void cambiaDisabledEmailColaborador(boolean flag) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getTipoColaborador().equals(Constantes.JSF_AGENTE_ADUANAL)) {
            setAgenteAduanalVO(cargaDocumentoElectronicoMBHelper.validaEmailInputText(getAgenteAduanalVO(), flag));
            setMensajeNoEnvioCorreo(cargaDocumentoElectronicoMBHelper
                    .construyeMensajeNoEnvioCorreo(getAgenteAduanalVO().getNombreTipoAsociado()));
        }
        requestContext.execute("PF('ingresaEmail').show();");
    }

    public void deshabilitaEmailColaborador() {
        cambiaDisabledEmailColaborador(false);
    }

    public void habilitaEmailColaborador() {
        cambiaDisabledEmailColaborador(true);
    }

    public CargaDocumentoElectronicoService getCargaDocumentoElectronicoService() {
        return cargaDocumentoElectronicoService;
    }

    public void setCargaDocumentoElectronicoService(CargaDocumentoElectronicoService cargaDocumentoElectronicoService) {
        this.cargaDocumentoElectronicoService = cargaDocumentoElectronicoService;
    }

    public List<FecetDocOrden> getOrdenesArchivoCargado() {
        return ordenesArchivoCargado;
    }

    public void setOrdenesArchivoCargado(List<FecetDocOrden> ordenesArchivoCargado) {
        this.ordenesArchivoCargado = ordenesArchivoCargado;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public CargaDocumentoElectronicoMBHelper getCargaDocumentoElectronicoMBHelper() {
        return cargaDocumentoElectronicoMBHelper;
    }

    public void setCargaDocumentoElectronicoMBHelper(
            CargaDocumentoElectronicoMBHelper cargaDocumentoElectronicoMBHelper) {
        this.cargaDocumentoElectronicoMBHelper = cargaDocumentoElectronicoMBHelper;
    }

    public String getMensajeNoEnvioCorreo() {
        return mensajeNoEnvioCorreo;
    }

    public void setMensajeNoEnvioCorreo(String mensajeNoEnvioCorreo) {
        this.mensajeNoEnvioCorreo = mensajeNoEnvioCorreo;
    }

    public BigDecimal getEstatus1() {
        return estatus1;
    }

    public void setEstatus1(BigDecimal estatus1) {
        this.estatus1 = estatus1;
    }

    public BigDecimal getEstatus2() {
        return estatus2;
    }

    public void setEstatus2(BigDecimal estatus2) {
        this.estatus2 = estatus2;
    }

    public BigDecimal getAccion() {
        return accion;
    }

    public void setAccion(BigDecimal accion) {
        this.accion = accion;
    }

    public List<FecetTipoOficio> getOficios() {
        return oficios;
    }

    public void setOficios(List<FecetTipoOficio> oficios) {
        this.oficios = oficios;
    }

    public BigDecimal getTipoOficioSeleccionado() {
        return tipoOficioSeleccionado;
    }

    public void setTipoOficioSeleccionado(BigDecimal tipoOficioSeleccionado) {
        this.tipoOficioSeleccionado = tipoOficioSeleccionado;
    }

    public List<FecetOficio> getOficiosArchivoCargado() {
        return oficiosArchivoCargado;
    }

    public void setOficiosArchivoCargado(List<FecetOficio> oficiosArchivoCargado) {
        this.oficiosArchivoCargado = oficiosArchivoCargado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public StreamedContent getDocumentoDescarga() {
        byte[] stream;
        try {
            stream = IOUtils.toByteArray(docOrdenSeleccionado.getArchivo());
            docOrdenSeleccionado.setArchivo(new ByteArrayInputStream(stream));
            InputStream myInputStream = new ByteArrayInputStream(stream);
            return new DefaultStreamedContent(myInputStream, "application/octet-stream",
                    docOrdenSeleccionado.getNombreArchivo());
        } catch (IOException e) {
            return null;
        }
    }

    public StreamedContent getOficioDescarga() {

        byte[] stream;
        try {
            stream = IOUtils.toByteArray(docOficioSeleccionado.getArchivo());
            docOficioSeleccionado.setArchivo(new ByteArrayInputStream(stream));
            InputStream myInputStream = new ByteArrayInputStream(stream);
            return new DefaultStreamedContent(myInputStream, "application/octet-stream",
                    docOficioSeleccionado.getNombreArchivo());
        } catch (IOException e) {
            return null;
        }
    }

    public FecetDocOrden getDocOrdenSeleccionado() {

        return docOrdenSeleccionado;
    }

    public void setDocOrdenSeleccionado(FecetDocOrden docOrdenSeleccionado) {
        this.docOrdenSeleccionado = docOrdenSeleccionado;
    }

    public FecetOficio getDocOficioSeleccionado() {
        return docOficioSeleccionado;
    }

    public void setDocOficioSeleccionado(FecetOficio docOficioSeleccionado) {
        this.docOficioSeleccionado = docOficioSeleccionado;
    }

    public PistasAuditoriasPropuestasService getPistasAuditoriasPropuestasService() {
        return pistasAuditoriasPropuestasService;
    }

    public void setPistasAuditoriasPropuestasService(PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService) {
        this.pistasAuditoriasPropuestasService = pistasAuditoriasPropuestasService;
    }

}
