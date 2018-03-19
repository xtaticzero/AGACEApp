package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CloseEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarOficioAdministrable;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.ReimprimirDocumentosAbstractMB;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.DetalleOficioUnifHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "detalleOficioUnificadoMB")
@ViewScoped
public class DetalleOficioUnificadoMB extends ReimprimirDocumentosAbstractMB {

    /**
     *
     */
    private static final long serialVersionUID = -2812662923591140557L;

    private DetalleOficioUnifHelper helper;
    private FecetOficio oficioSeleccionado;

    private transient StreamedContent archivoDescargaPromocionOficio;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaProrroga;
    private transient StreamedContent archivoDescargaAcuseProrrogaOficio;
    private transient StreamedContent archivoDescargaResolucionProrrogaOficio;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaAnexoProrrogaOficio;
    private transient StreamedContent archivoDescargaDocumentacionProrroga;
    private transient StreamedContent archivoAnexoProrrogaOficio;

    @ManagedProperty(value = "#{cargarFirmaPruebasPromoService}")
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;
    @ManagedProperty(value = "#{validarOficioAdministrable}")
    private transient ValidarOficioAdministrable validarOficioAdministrable;

    public void init() {

        if (getSession().getAttribute("oficioSeleccionadoU") != null) {

            helper = new DetalleOficioUnifHelper();

            setOficioSeleccionado((FecetOficio) getSession().getAttribute("oficioSeleccionadoU"));
            getHelper().setOrdenSeleccionada((AgaceOrden) getSession().getAttribute("ordenSeleccionadaU"));

            getSession().removeAttribute("oficioSeleccionadoU");
            getSession().removeAttribute("ordenSeleccionadaU");

            BigDecimal metodo = getHelper().getOrdenSeleccionada().getFeceaMetodo().getIdMetodo();
            if (!metodo.equals(Constantes.EHO) && !metodo.equals(Constantes.REE)) {
                getHelper().setProrrogaVisible(true);
            } else {
                getHelper().setProrrogaVisible(false);
            }

            List<FecetPromocionOficio> listaPromociones = getSeguimientoOrdenesService().getPromocionContadorPruebasAlegatosOficio(getOficioSeleccionado());
            for (FecetPromocionOficio promocion : listaPromociones) {
                promocion.setDescripcionTipoPromocion(
                        BusinessUtil.getTipoPromocionPorTipoOficioMetodo(getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio()));
                if (getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio())
                        || getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio()
                        .equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())) {
                    promocion.setExtemporanea("0");
                } else {
                    if (getValidarOficioAdministrable().esAdministrable(getOficioSeleccionado())) {
                        if (getValidarOficioAdministrable().tienePlazos(getOficioSeleccionado())) {
                            promocion.setExtemporanea(
                                    getPlazosService().esDocumentoExtemporaneoOficio(getOficioSeleccionado(), promocion.getFechaCarga()) ? "1" : "0");
                        } else {
                            promocion.setExtemporanea("0");
                        }
                    } else {
                        promocion.setExtemporanea(
                                getPlazosService().esDocumentoExtemporaneoOficio(getOficioSeleccionado(), promocion.getFechaCarga()) ? "1" : "0");
                    }
                }
            }
            getHelper().setListaPromociones(listaPromociones);

            getHelper().setListaProrroga(getSeguimientoOrdenesService().getProrrogaPorOficioEstatusPendienteAuditor(getOficioSeleccionado().getIdOficio()));

            getHelper().setListaProrrogaHistorico(getSeguimientoOrdenesService().getHistoricoProrrogaOficio(getOficioSeleccionado().getIdOficio()));

            if (getHelper().getListaProrrogaHistorico() != null && !getHelper().getListaProrrogaHistorico().isEmpty()) {
                for (FecetProrrogaOficio prorroga : getHelper().getListaProrrogaHistorico()) {
                    if (prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus()) || prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus())) {
                        if (prorroga.getAprobada()) {
                            prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.aprobada"));
                        } else {
                            prorroga.getFececEstatus().setDescripcion(FacesUtil.getMessageResourceString("estatus.firmante.no.aprobada"));
                        }
                    }
                }
            }

            if (getSession().getAttribute("muestraPapelesTrabajo") != null) {
                getHelper().setPanelPapelesVisible((Boolean) getSession().getAttribute("muestraPapelesTrabajo"));
                if (getHelper().isPanelPapelesVisible()) {
                    cargarPapelesTrabajoOficio();
                }
            }

        } else {
            logger.info("Sin información en sesión");
        }

    }

    public void cargarPapelesTrabajoOficio() {
        getHelper().setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService().getPapelesOnlyIdOfcio(getOficioSeleccionado().getId());

        if (listaPapelTrabajo != null) {
            for (PapelesTrabajo papel : listaPapelTrabajo) {
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getHelper().getListaPapelesTrabajoOficio().add(documento);
            }
        }
    }

    public void redireccionaRegreso() throws IOException {

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

    private StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {

        if (getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(getHelper().getPapelTrabajoSeleccionado().getIdDocumentoTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream",
                    getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
        }

        return getArchivoGenerico(getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo(),
                getHelper().getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
    }

    public void getDocumentacionRechazoProrroga() {
        getHelper().setListaDocumentosRechazoProrroga(getSeguimientoOrdenesService()
                .getAnexosRechazoProrrogaOficio(getHelper().getProrrogaOficioSeleccionada().getFecetFlujoProrrogaOficio().getIdFlujoProrrogaOficio()));
    }

    public void getDocumentacionProrrogaContribuyente() {
        getHelper().setListaDocumentosProrroga(
                getSeguimientoOrdenesService().getDocumentacionProrrogaContribuyente(getHelper().getProrrogaOficioSeleccionada().getIdProrrogaOficio()));
        // getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getIdProrrogaOrden())); LINEA ORIGINAL
        getHelper().setListaSolicitudContribuyenteDocVO(getHelper().llenaListaSolicitudContribuyenteDocVO(getHelper().getListaDocumentosProrroga(), null));
    }

    public void getDocumentacionProrrogaContribuyenteOficio() {
        getHelper().setListaDocumentosProrrogaOficio(
                getSeguimientoOrdenesService().getDocumentacionContribuyenteProrrogaOficio(getHelper().getProrrogaOficioSeleccionada().getIdProrrogaOficio()));
    }

    public void getPruebasAlegatosPromocion() {
        getHelper().setListaPruebasAlegatos(
                getCargarFirmaPruebasPromoService().getPruebasAlegatosPromocionOficio(getHelper().getPromocionSeleccionada().getIdPromocionOficio()));
        getHelper().setPanelDocumentacionVisible(true);
        logger.debug("oficios obtenidos [{}]", getHelper().getListaPruebasAlegatos().size());
    }

    public void cierraVentana(CloseEvent event) {
        getHelper().setPanelDocumentacionVisible(false);
    }

    public void getAnexosProrrogaOficio() {
        getHelper().setListaAnexosProrrogaOficio(getSeguimientoOrdenesService()
                .getAnexosProrrogaOficioHistorico(getHelper().getProrrogaOficioSeleccionada().getFecetFlujoProrrogaOficio().getIdFlujoProrrogaOficio()));
    }

    public void setArchivoAnexoProrrogaOficio(StreamedContent archivoAnexoProrrogaOficio) {
        this.archivoAnexoProrrogaOficio = archivoAnexoProrrogaOficio;
    }

    public StreamedContent getArchivoAnexoProrrogaOficio() {
        this.archivoAnexoProrrogaOficio = getDescargaArchivo(getHelper().getAnexoProrrogaOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoProrrogaOficioSeleccionado().getNombreArchivo());
        return archivoAnexoProrrogaOficio;
    }

    public void setArchivoDescargaDocumentacionProrroga(StreamedContent archivoDescargaDocumentacionProrroga) {
        this.archivoDescargaDocumentacionProrroga = archivoDescargaDocumentacionProrroga;
    }

    public StreamedContent getArchivoDescargaDocumentacionProrroga() {
        this.archivoDescargaDocumentacionProrroga = getDescargaArchivo(getHelper().getDocumentacionProrrogaOficioSeleccionado().getRutaArchivo(),
                getHelper().getDocumentacionProrrogaOficioSeleccionado().getNombreArchivo());
        return archivoDescargaDocumentacionProrroga;
    }

    public void setArchivoDescargaAnexoProrrogaOficio(final StreamedContent archivoDescargaAnexoProrrogaOficio) {
        this.archivoDescargaAnexoProrrogaOficio = archivoDescargaAnexoProrrogaOficio;
    }

    public StreamedContent getArchivoDescargaAnexoProrrogaOficio() {
        archivoDescargaAnexoProrrogaOficio = getDescargaArchivo(getHelper().getAnexoProrrogaOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoProrrogaOficioSeleccionado().getNombreArchivo());

        return archivoDescargaAnexoProrrogaOficio;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        archivoDescargaPruebasAlegatos = getDescargaArchivo(getHelper().getPruebaAlegatoSeleccionada().getRutaArchivo(),
                getHelper().getPruebaAlegatoSeleccionada().getNombreArchivo());

        return archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaResolucionProrrogaOficio() {
        this.archivoDescargaResolucionProrrogaOficio = getDescargaArchivo(getHelper().getProrrogaOficioSeleccionada().getRutaResolucion(),
                getHelper().getProrrogaOficioSeleccionada().getNombreResolucion());

        return archivoDescargaResolucionProrrogaOficio;
    }

    public void setArchivoDescargaResolucionProrrogaOficio(StreamedContent archivoDescargaResolucionProrrogaOficio) {
        this.archivoDescargaResolucionProrrogaOficio = archivoDescargaResolucionProrrogaOficio;
    }

    public void setArchivoDescargaAcuseProrrogaOficio(StreamedContent archivoDescargaAcuseProrrogaOficio) {
        this.archivoDescargaAcuseProrrogaOficio = archivoDescargaAcuseProrrogaOficio;
    }

    public StreamedContent getArchivoDescargaAcuseProrrogaOficio() {
        this.archivoDescargaAcuseProrrogaOficio = getDescargaArchivo(getHelper().getProrrogaOficioSeleccionada().getRutaAcuse(),
                getHelper().getProrrogaOficioSeleccionada().getNombreAcuse());

        return this.archivoDescargaAcuseProrrogaOficio;
    }

    public StreamedContent getArchivoDescargaPromocionOficio() {
        this.archivoDescargaPromocionOficio = getDescargaArchivo(getHelper().getPromocionSeleccionada().getRutaArchivo(),
                getHelper().getPromocionSeleccionada().getNombreArchivo());

        return archivoDescargaPromocionOficio;
    }

    public void setArchivoDescargaPromocionOficio(StreamedContent archivoDescargaPromocionOficio) {
        this.archivoDescargaPromocionOficio = archivoDescargaPromocionOficio;
    }

    public void setArchivoDescargaAcuse(final StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        archivoDescargaAcuse = getDescargaArchivo(getHelper().getPromocionSeleccionada().getRutaAcuse(),
                getHelper().getPromocionSeleccionada().getNombreAcuse());

        return archivoDescargaAcuse;
    }

    public void setArchivoDescargaProrroga(StreamedContent archivoDescargaProrroga) {
        this.archivoDescargaProrroga = archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaProrroga() {
        archivoDescargaProrroga = getDescargaArchivo(getHelper().getProrrogaOficioSeleccionada().getRutaAcuse(),
                getHelper().getProrrogaOficioSeleccionada().getNombreAcuse());

        return archivoDescargaProrroga;
    }

    public DetalleOficioUnifHelper getHelper() {
        return helper;
    }

    public void setHelper(DetalleOficioUnifHelper helper) {
        this.helper = helper;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public CargarFirmaPruebasPromoService getCargarFirmaPruebasPromoService() {
        return cargarFirmaPruebasPromoService;
    }

    public void setCargarFirmaPruebasPromoService(CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService) {
        this.cargarFirmaPruebasPromoService = cargarFirmaPruebasPromoService;
    }

    public ValidarOficioAdministrable getValidarOficioAdministrable() {
        return validarOficioAdministrable;
    }

    public void setValidarOficioAdministrable(ValidarOficioAdministrable validarOficioAdministrable) {
        this.validarOficioAdministrable = validarOficioAdministrable;
    }

}
