package mx.gob.sat.siat.feagace.vista.ordenes.doctoelectronico.firma;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenFirmada;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmadoCadenasOriginalesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.OrdenesPorValidarReService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.ordenes.helper.FirmadoCadenasOriginalesHelper;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;

@ManagedBean(name = "firmadoCadenasOriginalesMB")
@SessionScoped
public class FirmadoCadenasOriginalesMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private static final String METODO = "metodo";

    private List<OrdenPorFirmar> ordenesSeleccionadas;
    private List<OrdenPorFirmar> listaOrdenesFirmar;

    @ManagedProperty(value = "#{firmadoCadenasOriginalesService}")
    private transient FirmadoCadenasOriginalesService firmadoCadenasOriginalesService;

    @ManagedProperty(value = "#{ordenesPorValidarReService}")
    private transient OrdenesPorValidarReService ordenesPorValidarReService;

    @ManagedProperty(value = "#{firmadoCadenasOriginalesHelper}")
    private transient FirmadoCadenasOriginalesHelper firmadoCadenasOriginalesHelper;

    private Long metodoSeleccionado;

    static final String EXITO = "exito.guardado";
    static final String CONSULTAR_ORDENES = "Error al consultar las ordenes por firmar";
    static final String SIN_SELECCION = "error.lista.sin.seleccion";
    static final String APPLET = "appletFirmado";
    static final String SELECCIONAR_ELEMENTO = "error.lista.sin.seleccion";
    static final String PROCESA_FIRMADO = "error.procesa.firmado";
    static final String SIN_RECHAZO_DESCRIPCION = "error.rechazo.orden.sin.descripcion";
    static final String ORDEN_RECHAZO_ESTADO = "error.rechazo.orden.estatus";
    private String rfcLogueado = null;
    private AgaceOrden rechazoSeleccionado;
    private String rechazoDescripcion;
    private String contextoJarFirmante;

    // Archivo PDF
    private transient StreamedContent archivoSeleccionDescarga;
    private transient OrdenFirmada ordenParaDescarga;

    // Archivo DOC
    private transient StreamedContent archivoDocSeleccionDescarga;
    private transient OrdenPorFirmar ordenDocParaDescarga;

    private Integer totalFirmar;

    private List<OrdenFirmada> ordenesFirmadas;
    private BigDecimal idEmpleado;
    private transient HttpSession session = null;

    private List<FecetDocOrden> listaDocumentosOrden;
    private List<FecetOficio> oficiosPendientesDeFirmar;
    private FecetDocOrden docSeleccionado;
    private FecetOficio oficioSeleccionado;
    // Archivo doc oficio
    private transient StreamedContent archivoDescargaDocumentacion;
    private List<FecetDocOrden> listaDocumentosPdfOrden;
    private List<FecetOficio> oficiosFirmadosOrden;
    private FecetDocOrden docSeleccionadoPdf;
    private FecetOficio oficioSeleccionadoPdf;
    private transient StreamedContent archivoDescargaPdfOficio;

    @PostConstruct
    public void configuraOrdenesFirmar() {
        logger.info("--- configuraOrdenesFirmar, va a obtener contexto");
        session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        listaOrdenesFirmar = new ArrayList<OrdenPorFirmar>();
        ordenesSeleccionadas = new ArrayList<OrdenPorFirmar>();
        firmadoCadenasOriginalesService.initializer();
        this.rfcLogueado = getRFCSession();
        metodoSeleccionado = Long.parseLong(MetodosGenericos.getParametro(
                METODO).toString());
        this.contextoJarFirmante = getContextoAplicativo();
    }

    /**
     * Metodo cargarOrdenesFirmar
     *
     * @return String
     */
    public String cargarOrdenesFirmar() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        boolean isSuplente = (Boolean) session.getAttribute("essuplente");
        if (isSuplente) {
            if (MetodosGenericos.getParametro(METODO) != null) {
                metodoSeleccionado = Long.parseLong(MetodosGenericos.getParametro(METODO).toString());
            }
            BigDecimal idSuplente = new BigDecimal(session.getAttribute("suplente").toString());
            this.idEmpleado = idSuplente;
            this.listaOrdenesFirmar = firmadoCadenasOriginalesService
                    .getOrdenesPorFirmarPorMetodo(
                            new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE.getId()),
                            new BigDecimal(metodoSeleccionado), idSuplente);
            ordenesFirmadas = firmadoCadenasOriginalesService
                    .obtenerOrdenesFirmadas(this.idEmpleado, new BigDecimal(metodoSeleccionado));
        } else {
            UserProfileDTO userProfileDTO = (UserProfileDTO) session
                    .getAttribute("userProfile");
            if (MetodosGenericos.getParametro(METODO) != null) {
                metodoSeleccionado = Long.parseLong(MetodosGenericos.getParametro(METODO).toString());
            }
            try {

                EmpleadoDTO empleadoLogueado = ordenesPorValidarReService.obtenerDatosFirmanteSuplente(userProfileDTO.getRfc());

                BigDecimal idEmpleadoTmp = empleadoLogueado.getIdEmpleado();
                this.idEmpleado = idEmpleadoTmp;
            } catch (Exception e) {
                addErrorMessage(null, "No se encuentra el empleado " + userProfileDTO.getRfc());
            }
            this.listaOrdenesFirmar = firmadoCadenasOriginalesService
                    .getOrdenesPorFirmarPorMetodo(
                            new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE.getId()),
                            new BigDecimal(metodoSeleccionado), idEmpleado);
            ordenesFirmadas = firmadoCadenasOriginalesService
                    .obtenerOrdenesFirmadas(this.idEmpleado, new BigDecimal(metodoSeleccionado));
        }

        logger.info("Numero de ordenes a firmar: " + listaOrdenesFirmar.size());
        return "firmarOrdenes?faces-redirect=true";
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        archivoSeleccionDescarga = getDocArchivoDescarga(
                ordenParaDescarga.getRutaArchivo(),
                ordenParaDescarga.getNombreArchivo());
        return archivoSeleccionDescarga;
    }

    public StreamedContent getDocArchivoDescarga(final String path,
            final String nombreDescarga) {
        StreamedContent archivo = null;
        if (path != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(
                        CargaArchivoUtilPropuestas.limpiarPathArchivo(path)),
                        CargaArchivoUtilPropuestas
                        .obtenContentTypeArchivo(nombreDescarga),
                        CargaArchivoUtilPropuestas
                        .aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]",
                        e.getMessage());
                addErrorMessage(null,
                        "No se encontro el documento seleccionado", "");
            }
        } else {
            addErrorMessage(null,
                    "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    public String regresarPrincipal() {
        return "indexValidarFirmarOrdenes?faces-redirect=true";
    }

    /**
     * Metodo setFirmadoCadenasOriginalesService
     *
     * @param firmadoCadenasOriginalesService
     */
    public void setFirmadoCadenasOriginalesService(
            FirmadoCadenasOriginalesService firmadoCadenasOriginalesService) {
        this.firmadoCadenasOriginalesService = firmadoCadenasOriginalesService;
    }

    /**
     * Metodo getFirmadoCadenasOriginalesService
     *
     * @return Object
     */
    public FirmadoCadenasOriginalesService getFirmadoCadenasOriginalesService() {
        return firmadoCadenasOriginalesService;
    }

    /**
     * Metodo setOrdenesSeleccionadas
     *
     * @param ordenesSeleccionadas
     */
    public void setOrdenesSeleccionadas(
            List<OrdenPorFirmar> ordenesSeleccionadas) {
        this.ordenesSeleccionadas = ordenesSeleccionadas;
    }

    /**
     * Metodo getOrdenesSeleccionadas
     *
     * @return List
     */
    public List<OrdenPorFirmar> getOrdenesSeleccionadas() {
        return ordenesSeleccionadas;
    }

    /**
     * Metodo setListaOrdenesFirmar
     *
     * @param listaOrdenesFirmar
     */
    public void setListaOrdenesFirmar(List<OrdenPorFirmar> listaOrdenesFirmar) {
        this.listaOrdenesFirmar = listaOrdenesFirmar;
    }

    /**
     * Metodo getListaOrdenesFirmar
     *
     * @return List
     */
    public List<OrdenPorFirmar> getListaOrdenesFirmar() {
        return listaOrdenesFirmar;
    }

    /**
     * Metodo setMetodoSeleccionado
     *
     * @param metodoSeleccionado
     */
    public void setMetodoSeleccionado(Long metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }

    /**
     * Metodo getMetodoSeleccionado
     *
     * @return BigDecimal
     */
    public Long getMetodoSeleccionado() {
        return metodoSeleccionado;
    }

    /**
     * Metodo setRfc12
     *
     * @param rfcLogueado
     */
    public void setRfcLogueado(final String rfcLogueado) {
        this.rfcLogueado = rfcLogueado;
    }

    /**
     * Metodo getRfc12
     *
     * @return
     */
    public String getRfcLogueado() {
        return rfcLogueado;
    }

    /**
     * @param rechazoDescripcion
     */
    public void setRechazoDescripcion(String rechazoDescripcion) {
        this.rechazoDescripcion = rechazoDescripcion;
    }

    public String getRechazoDescripcion() {
        return rechazoDescripcion;
    }

    /**
     * @param rechazoSeleccionado
     */
    public void setRechazoSeleccionado(AgaceOrden rechazoSeleccionado) {
        this.rechazoSeleccionado = rechazoSeleccionado;
    }

    public AgaceOrden getRechazoSeleccionado() {
        return rechazoSeleccionado;
    }

    public void setContextoJarFirmante(String contextoJarFirmante) {
        this.contextoJarFirmante = contextoJarFirmante;
    }

    public String getContextoJarFirmante() {
        return contextoJarFirmante;
    }

    public void setOrdenesFirmadas(List<OrdenFirmada> ordenesFirmadas) {
        this.ordenesFirmadas = ordenesFirmadas;
    }

    public List<OrdenFirmada> getOrdenesFirmadas() {
        return ordenesFirmadas;
    }

    public void setTotalFirmar(Integer totalFirmar) {
        this.totalFirmar = totalFirmar;
    }

    public Integer getTotalFirmar() {
        return totalFirmar;
    }

    public void setArchivoSeleccionDescarga(
            StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public void setOrdenParaDescarga(OrdenFirmada ordenParaDescarga) {
        this.ordenParaDescarga = ordenParaDescarga;
    }

    public OrdenFirmada getOrdenParaDescarga() {
        return ordenParaDescarga;
    }

    public void setArchivoDocSeleccionDescarga(
            StreamedContent archivoDocSeleccionDescarga) {
        this.archivoDocSeleccionDescarga = archivoDocSeleccionDescarga;
    }

    public StreamedContent getArchivoDocSeleccionDescarga() {
        archivoDocSeleccionDescarga = getDocArchivoDescarga(
                docSeleccionado.getRutaArchivo(),
                docSeleccionado.getNombreArchivo());
        return archivoDocSeleccionDescarga;
    }

    public void setOrdenDocParaDescarga(OrdenPorFirmar ordenDocParaDescarga) {
        this.ordenDocParaDescarga = ordenDocParaDescarga;
    }

    public OrdenPorFirmar getOrdenDocParaDescarga() {
        return ordenDocParaDescarga;
    }

    public List<FecetDocOrden> getListaDocumentosOrden() {
        return listaDocumentosOrden;
    }

    public void setListaDocumentosOrden(List<FecetDocOrden> listaDocumentosOrden) {
        this.listaDocumentosOrden = listaDocumentosOrden;
    }

    public List<FecetOficio> getOficiosPendientesDeFirmar() {
        return oficiosPendientesDeFirmar;
    }

    public void setOficiosPendientesDeFirmar(
            List<FecetOficio> oficiosPendientesDeFirmar) {
        this.oficiosPendientesDeFirmar = oficiosPendientesDeFirmar;
    }

    public FecetDocOrden getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setDocSeleccionado(FecetDocOrden docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        archivoDescargaDocumentacion = getDocArchivoDescarga(
                getOficioSeleccionado().getRutaArchivo(),
                getOficioSeleccionado().getNombreArchivo());
        return archivoDescargaDocumentacion;
    }

    public void cargaDocumentosOrden() {
        setListaDocumentosOrden(ordenesPorValidarReService
                .obtenerDocOrden(new BigDecimal(ordenDocParaDescarga.getIdOrden())));
        setOficiosPendientesDeFirmar(ordenesPorValidarReService
                .getOficiosPorFirmar(new BigDecimal(ordenDocParaDescarga.getIdOrden())));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosOrdenFirma').show();");
    }

    public OrdenesPorValidarReService getOrdenesPorValidarReService() {
        return ordenesPorValidarReService;
    }

    public void setOrdenesPorValidarReService(
            OrdenesPorValidarReService ordenesPorValidarReService) {
        this.ordenesPorValidarReService = ordenesPorValidarReService;
    }

    public void cargaPdfsOrden() {
        setListaDocumentosPdfOrden(firmadoCadenasOriginalesHelper
                .creaListaDocumentosPdfOrden(ordenParaDescarga));
        setOficiosFirmadosOrden(ordenParaDescarga.getOficiosFirmados());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosFirmadosPdf').show();");
    }

    public List<FecetDocOrden> getListaDocumentosPdfOrden() {
        return listaDocumentosPdfOrden;
    }

    public void setListaDocumentosPdfOrden(
            List<FecetDocOrden> listaDocumentosPdfOrden) {
        this.listaDocumentosPdfOrden = listaDocumentosPdfOrden;
    }

    public List<FecetOficio> getOficiosFirmadosOrden() {
        return oficiosFirmadosOrden;
    }

    public void setOficiosFirmadosOrden(List<FecetOficio> oficiosFirmadosOrden) {
        this.oficiosFirmadosOrden = oficiosFirmadosOrden;
    }

    public FecetDocOrden getDocSeleccionadoPdf() {
        return docSeleccionadoPdf;
    }

    public void setDocSeleccionadoPdf(FecetDocOrden docSeleccionadoPdf) {
        this.docSeleccionadoPdf = docSeleccionadoPdf;
    }

    public FirmadoCadenasOriginalesHelper getFirmadoCadenasOriginalesHelper() {
        return firmadoCadenasOriginalesHelper;
    }

    public void setFirmadoCadenasOriginalesHelper(
            FirmadoCadenasOriginalesHelper firmadoCadenasOriginalesHelper) {
        this.firmadoCadenasOriginalesHelper = firmadoCadenasOriginalesHelper;
    }

    public FecetOficio getOficioSeleccionadoPdf() {
        return oficioSeleccionadoPdf;
    }

    public void setOficioSeleccionadoPdf(FecetOficio oficioSeleccionadoPdf) {
        this.oficioSeleccionadoPdf = oficioSeleccionadoPdf;
    }

    public StreamedContent getArchivoDescargaPdfOficio() {
        archivoDescargaPdfOficio = getDocArchivoDescarga(
                oficioSeleccionadoPdf.getRutaArchivo(),
                oficioSeleccionadoPdf.getNombreArchivo());
        return archivoDescargaPdfOficio;
    }

    public void setArchivoDescargaDocumentacion(
            StreamedContent archivoDescargaDocumentacion) {
        this.archivoDescargaDocumentacion = archivoDescargaDocumentacion;
    }

    public void setArchivoDescargaPdfOficio(
            StreamedContent archivoDescargaPdfOficio) {
        this.archivoDescargaPdfOficio = archivoDescargaPdfOficio;
    }

}
