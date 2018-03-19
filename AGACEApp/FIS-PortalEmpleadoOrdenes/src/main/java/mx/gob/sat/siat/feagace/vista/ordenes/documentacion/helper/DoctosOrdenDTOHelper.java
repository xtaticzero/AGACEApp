/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenDTOHelper extends DoctosOrdenVOHelper {

    private static final long serialVersionUID = -5166957449795228963L;

    private static final String PRORROGA = "Pr\u00f3rroga";
    private static final String PRUEBAS_PERICIALES = "Pruebas Periciales";

    private AgaceOrden ordenSeleccionada;
    private FecetOficio oficioSeleccionado;
    private FecetPromocion promocionSeleccionada;
    private FecetProrrogaOrden prorrogaOrdenSeleccionada;
    private FecetDocProrrogaOrden documentacionProrrogaOrdenSeleccionado;
    private FecetAnexosProrrogaOrden anexoProrrogaOrdenSeleccionado;
    private FecetOficio ofRechazadoSeleccionado;
    private FecetOficio ofDependienteSeleccionado;
    private FecetOficioAnexos anexoOficioSeleccionado;
    private FecetAlegato pruebaAlegatoSeleccionada;
    private AgaceOrden ordenSeleccionDescarga;
    private FecetAnexosRechazoOficio anexoRechazoOficioSeleccionado;
    private FecetOficioAnexos anexoSeleccionado;
    private FecetFlujoProrrogaOrden fecetFlujoProrrogaOrden;
    private FecetFlujoProrrogaOrden fecetFlujoProrrogaOrdenRechazo;
    private FecetAnexosProrrogaOrden anexoProrrogaOrdenSeleccionadoRechazo;
    private FecetCambioMetodo fecetCambioMetodo;
    private DocumentoOrdenModel papelTrabajoSeleccionado;
    private FecetPruebasPericiales pruebaPericialSeleccionada;
    private ColaboradorDocumentoDTO colaboradoresDTO;
    private FecetDocAsociado docmentoRLSeleccionado;
    private FecetOficio oficioSeleccionDescarga;

    public FecetOficio getOficioSeleccionDescarga() {
        return oficioSeleccionDescarga;
    }

    public void setOficioSeleccionDescarga(FecetOficio oficioSeleccionDescarga) {
        this.oficioSeleccionDescarga = oficioSeleccionDescarga;
    }

    public FecetPruebasPericiales getPruebaPericialSeleccionada() {
        return pruebaPericialSeleccionada;
    }

    public void setPruebaPericialSeleccionada(FecetPruebasPericiales pruebaPericialSeleccionada) {
        this.pruebaPericialSeleccionada = pruebaPericialSeleccionada;
    }

    public void setFecetCambioMetodo(final FecetCambioMetodo fecetCambioMetodo) {
        this.fecetCambioMetodo = fecetCambioMetodo;
    }

    public FecetCambioMetodo getFecetCambioMetodo() {
        return fecetCambioMetodo;
    }

    public void setAnexoOficioSeleccionado(final FecetOficioAnexos anexoOficioSeleccionado) {
        this.anexoOficioSeleccionado = anexoOficioSeleccionado;
    }

    public FecetOficioAnexos getAnexoOficioSeleccionado() {
        return anexoOficioSeleccionado;
    }

    public void setOfDependienteSeleccionado(final FecetOficio ofDependienteSeleccionado) {
        this.ofDependienteSeleccionado = ofDependienteSeleccionado;
    }

    public FecetOficio getOfDependienteSeleccionado() {
        return ofDependienteSeleccionado;
    }

    public void setFecetFlujoProrrogaOrden(final FecetFlujoProrrogaOrden fecetFlujoProrrogaOrden) {
        this.fecetFlujoProrrogaOrden = fecetFlujoProrrogaOrden;
    }

    public FecetFlujoProrrogaOrden getFecetFlujoProrrogaOrden() {
        return fecetFlujoProrrogaOrden;
    }

    public void setFecetFlujoProrrogaOrdenRechazo(final FecetFlujoProrrogaOrden fecetFlujoProrrogaOrdenRechazo) {
        this.fecetFlujoProrrogaOrdenRechazo = fecetFlujoProrrogaOrdenRechazo;
    }

    public FecetFlujoProrrogaOrden getFecetFlujoProrrogaOrdenRechazo() {
        return fecetFlujoProrrogaOrdenRechazo;
    }

    public void setAnexoRechazoOficioSeleccionado(final FecetAnexosRechazoOficio anexoRechazoOficioSeleccionado) {
        this.anexoRechazoOficioSeleccionado = anexoRechazoOficioSeleccionado;
    }

    public FecetAnexosRechazoOficio getAnexoRechazoOficioSeleccionado() {
        return anexoRechazoOficioSeleccionado;
    }

    public void setOfRechazadoSeleccionado(final FecetOficio ofRechazadoSeleccionado) {
        this.ofRechazadoSeleccionado = ofRechazadoSeleccionado;
    }

    public FecetOficio getOfRechazadoSeleccionado() {
        return ofRechazadoSeleccionado;
    }

    public void setAnexoSeleccionado(FecetOficioAnexos anexoSeleccionado) {
        this.anexoSeleccionado = anexoSeleccionado;
    }

    public FecetOficioAnexos getAnexoSeleccionado() {
        return anexoSeleccionado;
    }

    public void setOficioSeleccionado(final FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setAnexoProrrogaOrdenSeleccionado(final FecetAnexosProrrogaOrden anexoProrrogaOrdenSeleccionado) {
        this.anexoProrrogaOrdenSeleccionado = anexoProrrogaOrdenSeleccionado;
    }

    public FecetAnexosProrrogaOrden getAnexoProrrogaOrdenSeleccionado() {
        return anexoProrrogaOrdenSeleccionado;
    }

    public void setAnexoProrrogaOrdenSeleccionadoRechazo(
            final FecetAnexosProrrogaOrden anexoProrrogaOrdenSeleccionadoRechazo) {
        this.anexoProrrogaOrdenSeleccionadoRechazo = anexoProrrogaOrdenSeleccionadoRechazo;
    }

    public FecetAnexosProrrogaOrden getAnexoProrrogaOrdenSeleccionadoRechazo() {
        return anexoProrrogaOrdenSeleccionadoRechazo;
    }

    public void setDocumentacionProrrogaOrdenSeleccionado(
            final FecetDocProrrogaOrden documentacionProrrogaOrdenSeleccionado) {
        this.documentacionProrrogaOrdenSeleccionado = documentacionProrrogaOrdenSeleccionado;
    }

    public FecetDocProrrogaOrden getDocumentacionProrrogaOrdenSeleccionado() {
        return documentacionProrrogaOrdenSeleccionado;
    }

    public void setProrrogaOrdenSeleccionada(FecetProrrogaOrden prorrogaOrdenSeleccionada) {
        this.prorrogaOrdenSeleccionada = prorrogaOrdenSeleccionada;
    }

    public FecetProrrogaOrden getProrrogaOrdenSeleccionada() {
        return prorrogaOrdenSeleccionada;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    public void setPromocionSeleccionada(FecetPromocion promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocion getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegato pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public FecetAlegato getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public DocumentoOrdenModel getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }

    public void setPapelTrabajoSeleccionado(DocumentoOrdenModel papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }

    public ColaboradorDocumentoDTO getColaboradoresDTO() {
        return colaboradoresDTO;
    }

    public void setColaboradoresDTO(ColaboradorDocumentoDTO colaboradoresDTO) {
        this.colaboradoresDTO = colaboradoresDTO;
    }

    public FecetDocAsociado getDocmentoRLSeleccionado() {
        return docmentoRLSeleccionado;
    }

    public void setDocmentoRLSeleccionado(FecetDocAsociado docmentoRLSeleccionado) {
        this.docmentoRLSeleccionado = docmentoRLSeleccionado;
    }

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContPruebasPeri(
            List<FecetPruebasPericiales> listaPruebasPericiales) {
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
                solicitudContribuyente.setJustificacionFirmante(pruebaPericial.getFlujoPruebasPericiales().getJustificacionFirmante());
            }
            solicitudContribuyentes.add(solicitudContribuyente);
        }
        return solicitudContribuyentes;
    }

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContProrroga(
            List<FecetProrrogaOrden> listaProrrogaOrden) {
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
                solicitudContribuyente.setJustificacionFirmante(prorrogaOrden.getFecetFlujoProrrogaOrden().getJustificacionFirmante());
            }
            solicitudContribuyentes.add(solicitudContribuyente);
        }
        return solicitudContribuyentes;
    }

    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContFull(
            List<SolicitudContribuyenteVO> listaSolicitudContribuyenteFullParam,
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

    public List<SolicitudContribuyenteDocVO> llenaListaSolicitudContribuyenteDocVO(List<FecetDocProrrogaOrden> listaDocProrrogaOrden, List<FecetDocPruebasPericiales> listaDocPruebasPericiales) {
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

    public List<FecetAnexoPruebasPericiales> llenaAnexoSolicitudContPruebasPericiales(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContr) {
        List<FecetAnexoPruebasPericiales> anexoPruebasPericiales = new ArrayList<FecetAnexoPruebasPericiales>();
        for (SolicitudContribuyenteAnexoVO anexoSolicitudContr : listaSolicitudContr) {
            anexoPruebasPericiales.add(anexoSolicitudContr.getFecetAnexoPruebasPericiales());
        }
        return anexoPruebasPericiales;
    }

    public List<FecetAnexosProrrogaOrden> llenaAnexoSolicitudContProrroga(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContr) {
        List<FecetAnexosProrrogaOrden> anexoProrroga = new ArrayList<FecetAnexosProrrogaOrden>();
        for (SolicitudContribuyenteAnexoVO anexoSolicitudContr : listaSolicitudContr) {
            anexoProrroga.add(anexoSolicitudContr.getFecetAnexosProrrogaOrden());
        }
        return anexoProrroga;
    }

}
