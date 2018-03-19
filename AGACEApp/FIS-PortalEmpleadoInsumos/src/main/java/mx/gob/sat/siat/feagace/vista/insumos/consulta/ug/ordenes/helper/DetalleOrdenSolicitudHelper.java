package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public class DetalleOrdenSolicitudHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2443660649861605788L;

    private static final String PRORROGA = "Pr\u00f3rroga";
    private static final String PRUEBAS_PERICIALES = "Pruebas Periciales";

    private SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionada;
    private SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionado;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoHistoricoVOSeleccionada;

    private List<FecetProrrogaOrden> listaProrroga;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyente;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentrado;
    private List<FecetProrrogaOrden> listaProrrogaHistorico;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteHistorico;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentradoHistorico;
    private List<FecetPruebasPericiales> listaPruebasPericiales;
    private List<FecetPruebasPericiales> listaPruebasPericialesHistorico;
    private List<FecetDocProrrogaOrden> listaDocumentosProrroga;
    private List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO;
    private List<FecetDocPruebasPericiales> listaDocumentosPruebaPericial;
    private List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistorico;

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContFull(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteFullParam,
            List<SolicitudContribuyenteVO> listaSolicitudContribuyente) {
        List<SolicitudContribuyenteVO> listaSolicitudContribuyenteFull = listaSolicitudContribuyenteFullParam;
        if (listaSolicitudContribuyenteFull == null) {
            listaSolicitudContribuyenteFull = new ArrayList<SolicitudContribuyenteVO>();
        }
        for (SolicitudContribuyenteVO solicitudContribuyenteVO : listaSolicitudContribuyente) {
            listaSolicitudContribuyenteFull.add(solicitudContribuyenteVO);
        }
        return listaSolicitudContribuyenteFull;
    }

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContProrroga(List<FecetProrrogaOrden> listaProrrogaOrden) {
        List<SolicitudContribuyenteVO> solicitudContribuyentes = new ArrayList<SolicitudContribuyenteVO>();
        for (FecetProrrogaOrden prorrogaOrden : listaProrrogaOrden) {
            SolicitudContribuyenteVO solicitudContribuyente = new SolicitudContribuyenteVO();
            solicitudContribuyente.setFechaCarga(prorrogaOrden.getFechaCarga());
            solicitudContribuyente.setId(prorrogaOrden.getIdProrrogaOrden());
            solicitudContribuyente.setIdOrden(prorrogaOrden.getIdOrden());

            if (prorrogaOrden.getDescripcionEstado() == null) {
                solicitudContribuyente.setEstatus(prorrogaOrden.getFececEstatus().getDescripcion());
            } else {
                solicitudContribuyente.setEstatus(prorrogaOrden.getDescripcionEstado());
            }

            solicitudContribuyente.setDescripcion(PRORROGA);
            solicitudContribuyente.setTotalDocumentosContribuyente(prorrogaOrden.getTotalDocumentosContribuyente());
            solicitudContribuyente.setTotalDocumentosRechazo(prorrogaOrden.getTotalDocumentosRechazo());
            solicitudContribuyente.setProrrogaOrden(prorrogaOrden);
            if (prorrogaOrden.getFecetFlujoProrrogaOrden() != null) {
                solicitudContribuyente.setIdFlujo(prorrogaOrden.getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden());
                solicitudContribuyente.setJustificacion(prorrogaOrden.getFecetFlujoProrrogaOrden().getJustificacion());
            }
            solicitudContribuyentes.add(solicitudContribuyente);
        }
        return solicitudContribuyentes;
    }

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContPruebasPeri(List<FecetPruebasPericiales> listaPruebasPericiales) {
        List<SolicitudContribuyenteVO> solicitudContribuyentes = new ArrayList<SolicitudContribuyenteVO>();
        for (FecetPruebasPericiales pruebaPericial : listaPruebasPericiales) {
            SolicitudContribuyenteVO solicitudContribuyente = new SolicitudContribuyenteVO();
            solicitudContribuyente.setFechaCarga(pruebaPericial.getFechaCarga());
            solicitudContribuyente.setId(pruebaPericial.getIdPruebasPericiales());
            solicitudContribuyente.setIdOrden(pruebaPericial.getIdOrden());

            if (pruebaPericial.getDescripcionEstado() == null) {
                solicitudContribuyente.setEstatus(pruebaPericial.getFececEstatus().getDescripcion());
            } else {
                solicitudContribuyente.setEstatus(pruebaPericial.getDescripcionEstado());
            }
            solicitudContribuyente.setDescripcion(PRUEBAS_PERICIALES);
            solicitudContribuyente.setTotalDocumentosContribuyente(pruebaPericial.getTotalDocumentosContribuyente());
            solicitudContribuyente.setTotalDocumentosRechazo(pruebaPericial.getTotalDocumentosRechazo());
            solicitudContribuyente.setPruebasPericiales(pruebaPericial);
            if (pruebaPericial.getFlujoPruebasPericiales() != null) {
                solicitudContribuyente.setIdFlujo(pruebaPericial.getFlujoPruebasPericiales().getIdFlujoPruebasPericiales());
                solicitudContribuyente.setJustificacion(pruebaPericial.getFlujoPruebasPericiales().getJustificacion());
            }
            solicitudContribuyentes.add(solicitudContribuyente);
        }
        return solicitudContribuyentes;
    }

    public List<SolicitudContribuyenteDocVO> llenaListaSolicitudContribuyenteDocVO(List<FecetDocProrrogaOrden> listaDocProrrogaOrden,
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

    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyentePruebasPericiales(
            List<FecetAnexoPruebasPericiales> listaAnexoPruebasPericiales) {
        List<SolicitudContribuyenteAnexoVO> listaAnexoSolicitudContribuyente = new ArrayList<SolicitudContribuyenteAnexoVO>();
        for (FecetAnexoPruebasPericiales anexoPruebaPericial : listaAnexoPruebasPericiales) {
            SolicitudContribuyenteAnexoVO anexoSolicitudContribuyente = new SolicitudContribuyenteAnexoVO();
            anexoSolicitudContribuyente.setBlnActivo(anexoPruebaPericial.getBlnActivo());
            anexoSolicitudContribuyente.setFechaCreacion(anexoPruebaPericial.getFechaCreacion());
            anexoSolicitudContribuyente.setId(anexoPruebaPericial.getIdAnexoPruebasPericiales());
            anexoSolicitudContribuyente.setIdFlujo(anexoPruebaPericial.getIdFlujoPruebasPericiales());
            anexoSolicitudContribuyente.setNombreArchivo(anexoPruebaPericial.getNombreArchivo());
            anexoSolicitudContribuyente.setRutaArchivo(anexoPruebaPericial.getRutaArchivo());
            anexoSolicitudContribuyente.setFecetAnexoPruebasPericiales(anexoPruebaPericial);
            listaAnexoSolicitudContribuyente.add(anexoSolicitudContribuyente);
        }
        return listaAnexoSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyenteProrroga(List<FecetAnexosProrrogaOrden> listaAnexoProrroga) {
        List<SolicitudContribuyenteAnexoVO> listaAnexoSolicitudContribuyente = new ArrayList<SolicitudContribuyenteAnexoVO>();
        for (FecetAnexosProrrogaOrden anexoProrroga : listaAnexoProrroga) {
            SolicitudContribuyenteAnexoVO anexoSolicitudContribuyente = new SolicitudContribuyenteAnexoVO();
            anexoSolicitudContribuyente.setBlnActivo(anexoProrroga.getBlnActivo());
            anexoSolicitudContribuyente.setFechaCreacion(anexoProrroga.getFechaCreacion());
            anexoSolicitudContribuyente.setId(anexoProrroga.getIdAnexosProrrogaOrden());
            anexoSolicitudContribuyente.setIdFlujo(anexoProrroga.getIdFlujoProrrogaOrden());
            anexoSolicitudContribuyente.setNombreArchivo(anexoProrroga.getNombreArchivo());
            anexoSolicitudContribuyente.setRutaArchivo(anexoProrroga.getRutaArchivo());
            anexoSolicitudContribuyente.setFecetAnexosProrrogaOrden(anexoProrroga);
            listaAnexoSolicitudContribuyente.add(anexoSolicitudContribuyente);
        }
        return listaAnexoSolicitudContribuyente;
    }

    public List<FecetProrrogaOrden> getListaProrroga() {
        return listaProrroga;
    }

    public void setListaProrroga(List<FecetProrrogaOrden> listaProrroga) {
        this.listaProrroga = listaProrroga;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyente() {
        return listaSolicitudContribuyente;
    }

    public void setListaSolicitudContribuyente(List<SolicitudContribuyenteVO> listaSolicitudContribuyente) {
        this.listaSolicitudContribuyente = listaSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteConcentrado() {
        return listaSolicitudContribuyenteConcentrado;
    }

    public void setListaSolicitudContribuyenteConcentrado(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentrado) {
        this.listaSolicitudContribuyenteConcentrado = listaSolicitudContribuyenteConcentrado;
    }

    public void setListaProrrogaHistorico(final List<FecetProrrogaOrden> listaProrrogaHistorico) {
        this.listaProrrogaHistorico = listaProrrogaHistorico;
    }

    public List<FecetProrrogaOrden> getListaProrrogaHistorico() {
        return listaProrrogaHistorico;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteHistorico() {
        return listaSolicitudContribuyenteHistorico;
    }

    public void setListaSolicitudContribuyenteHistorico(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteHistorico) {
        this.listaSolicitudContribuyenteHistorico = listaSolicitudContribuyenteHistorico;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteConcentradoHistorico() {
        return listaSolicitudContribuyenteConcentradoHistorico;
    }

    public void setListaSolicitudContribuyenteConcentradoHistorico(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentradoHistorico) {
        this.listaSolicitudContribuyenteConcentradoHistorico = listaSolicitudContribuyenteConcentradoHistorico;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericiales() {
        return listaPruebasPericiales;
    }

    public void setListaPruebasPericiales(List<FecetPruebasPericiales> listaPruebasPericiales) {
        this.listaPruebasPericiales = listaPruebasPericiales;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericialesHistorico() {
        return listaPruebasPericialesHistorico;
    }

    public void setListaPruebasPericialesHistorico(List<FecetPruebasPericiales> listaPruebasPericialesHistorico) {
        this.listaPruebasPericialesHistorico = listaPruebasPericialesHistorico;
    }

    public SolicitudContribuyenteVO getSolicitudContribuyenteVOSeleccionada() {
        return solicitudContribuyenteVOSeleccionada;
    }

    public void setSolicitudContribuyenteVOSeleccionada(SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionada) {
        this.solicitudContribuyenteVOSeleccionada = solicitudContribuyenteVOSeleccionada;
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

    public void setListaSolicitudContribuyenteDocVO(List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO) {
        this.listaSolicitudContribuyenteDocVO = listaSolicitudContribuyenteDocVO;
    }

    public List<FecetDocPruebasPericiales> getListaDocumentosPruebaPericial() {
        return listaDocumentosPruebaPericial;
    }

    public void setListaDocumentosPruebaPericial(List<FecetDocPruebasPericiales> listaDocumentosPruebaPericial) {
        this.listaDocumentosPruebaPericial = listaDocumentosPruebaPericial;
    }

    public SolicitudContribuyenteDocVO getDocumentacionSolicitudContribuyenteSeleccionado() {
        return documentacionSolicitudContribuyenteSeleccionado;
    }

    public void setDocumentacionSolicitudContribuyenteSeleccionado(SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionado) {
        this.documentacionSolicitudContribuyenteSeleccionado = documentacionSolicitudContribuyenteSeleccionado;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaAnexosSolicitudContribuyenteHistorico() {
        return listaAnexosSolicitudContribuyenteHistorico;
    }

    public void setListaAnexosSolicitudContribuyenteHistorico(List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistorico) {
        this.listaAnexosSolicitudContribuyenteHistorico = listaAnexosSolicitudContribuyenteHistorico;
    }

    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteAnexoHistoricoVOSeleccionada() {
        return solicitudContribuyenteAnexoHistoricoVOSeleccionada;
    }

    public void setSolicitudContribuyenteAnexoHistoricoVOSeleccionada(SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoHistoricoVOSeleccionada) {
        this.solicitudContribuyenteAnexoHistoricoVOSeleccionada = solicitudContribuyenteAnexoHistoricoVOSeleccionada;
    }

}
