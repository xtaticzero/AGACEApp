package mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;

public class DetalleOficioUnifHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2467897078218715113L;

    private List<FecetPromocionOficio> listaPromociones;
    private List<FecetAlegatoOficio> listaPruebasAlegatos;
    private List<FecetProrrogaOficio> listaProrroga;
    private List<FecetDocProrrogaOrden> listaDocumentosProrroga;
    private List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO;
    private List<FecetAnexosProrrogaOficio> listaDocumentosRechazoProrroga;
    private List<FecetProrrogaOficio> listaProrrogaHistorico;
    private List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio;
    private List<DocumentoOrdenModel> listaPapelesTrabajoOficio;

    private FecetPromocionOficio promocionSeleccionada;
    private AgaceOrden ordenSeleccionada;
    private FecetProrrogaOficio prorrogaOficioSeleccionada;
    private FecetAlegatoOficio pruebaAlegatoSeleccionada;
    private FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionado;
    private FecetDocProrrogaOficio documentacionProrrogaOficioSeleccionado;
    private DocumentoOrdenModel papelTrabajoSeleccionado;

    private boolean prorrogaVisible;
    private boolean panelDocumentacionVisible;
    private boolean panelPapelesVisible;

    public List<SolicitudContribuyenteDocVO> llenaListaSolicitudContribuyenteDocVO(
            List<FecetDocProrrogaOrden> listaDocProrrogaOrden,
            List<FecetDocPruebasPericiales> listaDocPruebasPericiales) {
        List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDoc = new ArrayList<SolicitudContribuyenteDocVO>();
        if (listaDocProrrogaOrden != null) {
            for (FecetDocProrrogaOrden prorrogaOrdenDoc : listaDocProrrogaOrden) {
                SolicitudContribuyenteDocVO solicitudContribuyenteDoc = new SolicitudContribuyenteDocVO();
                solicitudContribuyenteDoc.setArchivo(prorrogaOrdenDoc.getArchivo());
                solicitudContribuyenteDoc.setId(prorrogaOrdenDoc.getIdProrrogaOrden());
                solicitudContribuyenteDoc.setIdDoc(prorrogaOrdenDoc.getIdDocProrrogaOrden());
                solicitudContribuyenteDoc.setNombreArchivo(prorrogaOrdenDoc.getNombreArchivo());
                solicitudContribuyenteDoc.setRutaArchivo(prorrogaOrdenDoc.getRutaArchivo());
                solicitudContribuyenteDoc.setFecetDocProrrogaOrden(prorrogaOrdenDoc);
                listaSolicitudContribuyenteDoc.add(solicitudContribuyenteDoc);
            }
        } else {
            for (FecetDocPruebasPericiales pruebaPericialDoc : listaDocPruebasPericiales) {
                SolicitudContribuyenteDocVO solicitudContribuyenteDoc = new SolicitudContribuyenteDocVO();
                solicitudContribuyenteDoc.setArchivo(pruebaPericialDoc.getArchivo());
                solicitudContribuyenteDoc.setId(pruebaPericialDoc.getIdPruebasPericiales());
                solicitudContribuyenteDoc.setIdDoc(pruebaPericialDoc.getIdDocPruebasPericiales());
                solicitudContribuyenteDoc.setNombreArchivo(pruebaPericialDoc.getNombreArchivo());
                solicitudContribuyenteDoc.setRutaArchivo(pruebaPericialDoc.getRutaArchivo());
                solicitudContribuyenteDoc.setFecetDocPruebasPericiales(pruebaPericialDoc);
                listaSolicitudContribuyenteDoc.add(solicitudContribuyenteDoc);
            }
        }

        return listaSolicitudContribuyenteDoc;
    }

    public List<FecetPromocionOficio> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPromociones(List<FecetPromocionOficio> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public FecetPromocionOficio getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public void setPromocionSeleccionada(FecetPromocionOficio promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public void setListaPruebasAlegatos(List<FecetAlegatoOficio> listaPruebasAlegatos) {
        this.listaPruebasAlegatos = listaPruebasAlegatos;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatos() {
        return listaPruebasAlegatos;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public void setListaProrroga(final List<FecetProrrogaOficio> listaProrroga) {
        this.listaProrroga = listaProrroga;
    }

    public List<FecetProrrogaOficio> getListaProrroga() {
        return listaProrroga;
    }

    public void setProrrogaOficioSeleccionada(final FecetProrrogaOficio prorrogaOficioSeleccionada) {
        this.prorrogaOficioSeleccionada = prorrogaOficioSeleccionada;
    }

    public FecetProrrogaOficio getProrrogaOficioSeleccionada() {
        return prorrogaOficioSeleccionada;
    }

    public void setListaDocumentosProrroga(final List<FecetDocProrrogaOrden> listaDocumentosProrroga) {
        this.listaDocumentosProrroga = listaDocumentosProrroga;
    }

    public List<FecetDocProrrogaOrden> getListaDocumentosProrroga() {
        return listaDocumentosProrroga;
    }

    public List<SolicitudContribuyenteDocVO> getListaSolicitudContribuyenteDocVO() {
        return listaSolicitudContribuyenteDocVO;
    }

    public void setListaSolicitudContribuyenteDocVO(
            List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO) {
        this.listaSolicitudContribuyenteDocVO = listaSolicitudContribuyenteDocVO;
    }

    public void setListaDocumentosRechazoProrroga(List<FecetAnexosProrrogaOficio> listaDocumentosRechazoProrroga) {
        this.listaDocumentosRechazoProrroga = listaDocumentosRechazoProrroga;
    }

    public List<FecetAnexosProrrogaOficio> getListaDocumentosRechazoProrroga() {
        return listaDocumentosRechazoProrroga;
    }

    public boolean isProrrogaVisible() {
        return prorrogaVisible;
    }

    public void setProrrogaVisible(boolean prorrogaVisible) {
        this.prorrogaVisible = prorrogaVisible;
    }

    public void setListaProrrogaHistorico(final List<FecetProrrogaOficio> listaProrrogaHistorico) {
        this.listaProrrogaHistorico = listaProrrogaHistorico;
    }

    public List<FecetProrrogaOficio> getListaProrrogaHistorico() {
        return listaProrrogaHistorico;
    }

    public void setListaAnexosProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio) {
        this.listaAnexosProrrogaOficio = listaAnexosProrrogaOficio;
    }

    public List<FecetAnexosProrrogaOficio> getListaAnexosProrrogaOficio() {
        return listaAnexosProrrogaOficio;
    }

    public boolean isPanelDocumentacionVisible() {
        return panelDocumentacionVisible;
    }

    public void setPanelDocumentacionVisible(boolean panelDocumentacionVisible) {
        this.panelDocumentacionVisible = panelDocumentacionVisible;
    }

    public FecetAlegatoOficio getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegatoOficio pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public void setAnexoProrrogaOficioSeleccionado(final FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionado) {
        this.anexoProrrogaOficioSeleccionado = anexoProrrogaOficioSeleccionado;
    }

    public FecetAnexosProrrogaOficio getAnexoProrrogaOficioSeleccionado() {
        return anexoProrrogaOficioSeleccionado;
    }

    public void setDocumentacionProrrogaOficioSeleccionado(
            final FecetDocProrrogaOficio documentacionProrrogaOficioSeleccionado) {
        this.documentacionProrrogaOficioSeleccionado = documentacionProrrogaOficioSeleccionado;
    }

    public FecetDocProrrogaOficio getDocumentacionProrrogaOficioSeleccionado() {
        return documentacionProrrogaOficioSeleccionado;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajoOficio() {
        return listaPapelesTrabajoOficio;
    }

    public void setListaPapelesTrabajoOficio(List<DocumentoOrdenModel> listaPapelesTrabajoOficio) {
        this.listaPapelesTrabajoOficio = listaPapelesTrabajoOficio;
    }

    public DocumentoOrdenModel getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }

    public void setPapelTrabajoSeleccionado(DocumentoOrdenModel papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }

    public boolean isPanelPapelesVisible() {
        return panelPapelesVisible;
    }

    public void setPanelPapelesVisible(boolean panelPapelesVisible) {
        this.panelPapelesVisible = panelPapelesVisible;
    }

}
