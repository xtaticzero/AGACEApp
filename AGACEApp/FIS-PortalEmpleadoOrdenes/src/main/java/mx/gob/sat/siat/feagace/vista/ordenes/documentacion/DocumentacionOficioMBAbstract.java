package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public abstract class DocumentacionOficioMBAbstract extends DocumentacionOficioAttributesAbstract {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private AgaceOrden ordenSeleccionada;
    private List<FecetPromocionOficio> listaPromociones;
    private List<FecetProrrogaOficio> listaProrroga;
    private List<FecetProrrogaOficio> listaProrrogaHistorico;
    private List<FecetDocProrrogaOficio> listaDocumentosProrroga;
    private List<FecetAnexosProrrogaOficio> listaDocumentosRechazoProrroga;
    private List<FecetOficioAnexos> listaOficioAnexosRechazo;
    private List<FecetOficio> listaOficiosRechazados;

    private FecetOficio oficioSeleccionado;

    private FecetPromocionOficio promocionSeleccionada;
    private FecetDocProrrogaOficio documentacionProrrogaOficioSeleccionado;
    private FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionado;

    private transient StreamedContent archivoDescargaPromocion;

    private transient StreamedContent archivoDescargaDocumentacionProrroga;
    private transient StreamedContent archivoAnexoProrrogaOficio;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaPromocionOficio;

    private List<FecetAlegatoOficio> listaPruebasAlegatos;

    private BigDecimal idPromocionSeleccionada;
    private Date fechaEnvioPromocionSeleccionada;
    private FecetAlegatoOficio pruebaAlegatoSeleccionada;

    private Boolean visualizarTabAvisoContribuyente;
    private Boolean recargarInformacion;

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegatoOficio pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public FecetAlegatoOficio getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public void setIdPromocionSeleccionada(BigDecimal idPromocionSeleccionada) {
        this.idPromocionSeleccionada = idPromocionSeleccionada;
    }

    public BigDecimal getIdPromocionSeleccionada() {
        return idPromocionSeleccionada;
    }

    public void setFechaEnvioPromocionSeleccionada(Date fechaEnvioPromocionSeleccionada) {
        this.fechaEnvioPromocionSeleccionada = (fechaEnvioPromocionSeleccionada != null)
                ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public Date getFechaEnvioPromocionSeleccionada() {
        return (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public void setListaPromociones(List<FecetPromocionOficio> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<FecetPromocionOficio> getListaPromociones() {
        return listaPromociones;
    }

    public void setPromocionSeleccionada(FecetPromocionOficio promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocionOficio getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public void setListaPruebasAlegatos(List<FecetAlegatoOficio> listaPruebasAlegatos) {
        this.listaPruebasAlegatos = listaPruebasAlegatos;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatos() {
        return listaPruebasAlegatos;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        archivoDescargaPromocion = getDescargaArchivo(promocionSeleccionada.getRutaArchivo(),
                promocionSeleccionada.getNombreArchivo());

        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaAcuse(final StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        archivoDescargaAcuse = getDescargaArchivo(promocionSeleccionada.getRutaAcuse(),
                promocionSeleccionada.getNombreAcuse());

        return archivoDescargaAcuse;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        archivoDescargaPruebasAlegatos = getDescargaArchivo(pruebaAlegatoSeleccionada.getRutaArchivo(),
                pruebaAlegatoSeleccionada.getNombreArchivo());

        return archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaDocumentacionProrroga(StreamedContent archivoDescargaDocumentacionProrroga) {
        this.archivoDescargaDocumentacionProrroga = archivoDescargaDocumentacionProrroga;
    }

    public StreamedContent getArchivoDescargaDocumentacionProrroga() {
        this.archivoDescargaDocumentacionProrroga = getDescargaArchivo(
                documentacionProrrogaOficioSeleccionado.getRutaArchivo(),
                documentacionProrrogaOficioSeleccionado.getNombreArchivo());
        return archivoDescargaDocumentacionProrroga;
    }

    public void setArchivoAnexoProrrogaOficio(StreamedContent archivoAnexoProrrogaOficio) {
        this.archivoAnexoProrrogaOficio = archivoAnexoProrrogaOficio;
    }

    public StreamedContent getArchivoAnexoProrrogaOficio() {
        this.archivoAnexoProrrogaOficio = getDescargaArchivo(anexoProrrogaOficioSeleccionado.getRutaArchivo(),
                anexoProrrogaOficioSeleccionado.getNombreArchivo());
        return archivoAnexoProrrogaOficio;
    }

    public void setListaDocumentosProrroga(final List<FecetDocProrrogaOficio> listaDocumentosProrroga) {
        this.listaDocumentosProrroga = listaDocumentosProrroga;
    }

    public List<FecetDocProrrogaOficio> getListaDocumentosProrroga() {
        return listaDocumentosProrroga;
    }

    public void setDocumentacionProrrogaOficioSeleccionado(
            final FecetDocProrrogaOficio documentacionProrrogaOficioSeleccionado) {
        this.documentacionProrrogaOficioSeleccionado = documentacionProrrogaOficioSeleccionado;
    }

    public FecetDocProrrogaOficio getDocumentacionProrrogaOficioSeleccionado() {
        return documentacionProrrogaOficioSeleccionado;
    }

    public void setListaDocumentosRechazoProrroga(List<FecetAnexosProrrogaOficio> listaDocumentosRechazoProrroga) {
        this.listaDocumentosRechazoProrroga = listaDocumentosRechazoProrroga;
    }

    public List<FecetAnexosProrrogaOficio> getListaDocumentosRechazoProrroga() {
        return listaDocumentosRechazoProrroga;
    }

    public void setAnexoProrrogaOficioSeleccionado(final FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionado) {
        this.anexoProrrogaOficioSeleccionado = anexoProrrogaOficioSeleccionado;
    }

    public FecetAnexosProrrogaOficio getAnexoProrrogaOficioSeleccionado() {
        return anexoProrrogaOficioSeleccionado;
    }

    public void setListaProrroga(final List<FecetProrrogaOficio> listaProrroga) {
        this.listaProrroga = listaProrroga;
    }

    public List<FecetProrrogaOficio> getListaProrroga() {
        return listaProrroga;
    }

    public void setListaProrrogaHistorico(final List<FecetProrrogaOficio> listaProrrogaHistorico) {
        this.listaProrrogaHistorico = listaProrrogaHistorico;
    }

    public List<FecetProrrogaOficio> getListaProrrogaHistorico() {
        return listaProrrogaHistorico;
    }

    public void setListaOficiosRechazados(List<FecetOficio> listaOficiosRechazados) {
        this.listaOficiosRechazados = listaOficiosRechazados;
    }

    public List<FecetOficio> getListaOficiosRechazados() {
        return listaOficiosRechazados;
    }

    public void setListaOficioAnexosRechazo(final List<FecetOficioAnexos> listaOficioAnexosRechazo) {
        this.listaOficioAnexosRechazo = listaOficioAnexosRechazo;
    }

    public List<FecetOficioAnexos> getListaOficioAnexosRechazo() {
        return listaOficioAnexosRechazo;
    }

    public void setOficioSeleccionado(final FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setRecargarInformacion(final Boolean recargarInformacion) {
        this.recargarInformacion = recargarInformacion;
    }

    public Boolean getRecargarInformacion() {
        return recargarInformacion;
    }

    public void setVisualizarTabAvisoContribuyente(final Boolean visualizarTabAvisoContribuyente) {
        this.visualizarTabAvisoContribuyente = visualizarTabAvisoContribuyente;
    }

    public Boolean getVisualizarTabAvisoContribuyente() {
        return visualizarTabAvisoContribuyente;
    }

    public StreamedContent getArchivoDescargaPromocionOficio() {
        this.archivoDescargaPromocionOficio = getDescargaArchivo(promocionSeleccionada.getRutaArchivo(),
                promocionSeleccionada.getNombreArchivo());

        return archivoDescargaPromocionOficio;
    }

    public void setArchivoDescargaPromocionOficio(StreamedContent archivoDescargaPromocionOficio) {
        this.archivoDescargaPromocionOficio = archivoDescargaPromocionOficio;
    }

}
