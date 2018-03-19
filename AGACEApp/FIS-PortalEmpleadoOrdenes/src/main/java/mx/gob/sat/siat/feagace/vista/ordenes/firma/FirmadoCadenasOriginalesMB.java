package mx.gob.sat.siat.feagace.vista.ordenes.firma;

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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenFirmada;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmadoCadenasOriginalesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
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

    @PostConstruct
    public void configuraOrdenesFirmar() {
        logger.info("--- configuraOrdenesFirmar, va a obtener contexto");
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        listaOrdenesFirmar = new ArrayList<OrdenPorFirmar>();
        ordenesSeleccionadas = new ArrayList<OrdenPorFirmar>();
        firmadoCadenasOriginalesService.initializer();
        this.rfcLogueado = getRFCSession();
        metodoSeleccionado = Long.parseLong(MetodosGenericos.getParametro(METODO).toString());
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
            logger.info("--- Metodo seleccionado: {}", metodoSeleccionado);
            BigDecimal idSuplente = new BigDecimal(session.getAttribute("suplente").toString());
            this.idEmpleado = idSuplente;
            this.listaOrdenesFirmar = firmadoCadenasOriginalesService.getOrdenesPorFirmarPorMetodo(
                    new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE.getId()), new BigDecimal(metodoSeleccionado), idSuplente);
            ordenesFirmadas = firmadoCadenasOriginalesService.obtenerOrdenesFirmadas(this.idEmpleado, new BigDecimal(metodoSeleccionado));
        } else {
            UserProfileDTO userProfileDTO = (UserProfileDTO) session.getAttribute("userProfile");
            // aqui obtener metodo seleccionado
            if (MetodosGenericos.getParametro(METODO) != null) {
                metodoSeleccionado = Long.parseLong(MetodosGenericos.getParametro(METODO).toString());
            }
            logger.info("--- Metodo seleccionado: {}", metodoSeleccionado);
            try {
                BigDecimal idEmpleadoTmp;
                EmpleadoDTO empleadoDTO;

                empleadoDTO = getEmpleadoService().getEmpleadoCompleto(userProfileDTO.getRfc());

                if (empleadoDTO == null) {
                    throw new NoExisteEmpleadoException("No se encuentra el perfil del empleado");
                }

                idEmpleadoTmp = empleadoDTO.getIdEmpleado();
                this.idEmpleado = idEmpleadoTmp;
            } catch (NoExisteEmpleadoException e) {
                FacesUtil.addErrorMessage(null, "No se encuentra el empleado " + userProfileDTO.getRfc());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }

            this.listaOrdenesFirmar = firmadoCadenasOriginalesService.getOrdenesPorFirmarPorMetodo(
                    new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE.getId()), new BigDecimal(metodoSeleccionado), idEmpleado);
            ordenesFirmadas = firmadoCadenasOriginalesService.obtenerOrdenesFirmadas(this.idEmpleado, new BigDecimal(metodoSeleccionado));
        }

        logger.info("Numero de ordenes a firmar: " + listaOrdenesFirmar.size());
        return "firmarOrdenes?faces-redirect=true";
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        /**
         * ordenParaDescarga.getRutaArchivo()+"/"+ordenParaDescarga.
         * getNumeroOrden()+Constantes.ARCHIVO_PDF,
         */
        archivoSeleccionDescarga = getDocArchivoDescarga(ordenParaDescarga.getRutaArchivo(), ordenParaDescarga.getNombreArchivo());
        return archivoSeleccionDescarga;
    }

    public StreamedContent getDocArchivoDescarga(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (path != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilOrdenes.limpiarPathArchivo(path)),
                        CargaArchivoUtilOrdenes.obtenContentTypeArchivo(nombreDescarga), CargaArchivoUtilOrdenes.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]", e.getMessage());
                FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
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
    public void setFirmadoCadenasOriginalesService(FirmadoCadenasOriginalesService firmadoCadenasOriginalesService) {
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
    public void setOrdenesSeleccionadas(List<OrdenPorFirmar> ordenesSeleccionadas) {
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

    public void setArchivoSeleccionDescarga(StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public void setOrdenParaDescarga(OrdenFirmada ordenParaDescarga) {
        this.ordenParaDescarga = ordenParaDescarga;
    }

    public OrdenFirmada getOrdenParaDescarga() {
        return ordenParaDescarga;
    }

    public void setArchivoDocSeleccionDescarga(StreamedContent archivoDocSeleccionDescarga) {
        this.archivoDocSeleccionDescarga = archivoDocSeleccionDescarga;
    }

    public StreamedContent getArchivoDocSeleccionDescarga() {
        archivoDocSeleccionDescarga = getDocArchivoDescarga(ordenDocParaDescarga.getRutaArchivo(), ordenDocParaDescarga.getNombreArchivo());
        return archivoDocSeleccionDescarga;
    }

    public void setOrdenDocParaDescarga(OrdenPorFirmar ordenDocParaDescarga) {
        this.ordenDocParaDescarga = ordenDocParaDescarga;
    }

    public OrdenPorFirmar getOrdenDocParaDescarga() {
        return ordenDocParaDescarga;
    }
}
