package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;


public class DoctosFirmanteHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private static final String PRORROGA = "Pr\u00f3rroga";
    private static final String PRUEBAS_PERICIALES = "Pruebas Periciales";
    
    private List<FececEstatus> listaEstatus;
    
  

    
    public List<SolicitudContribuyenteVO> llenaObjetoSolicitudContProrroga(
            List<FecetProrrogaOrden> listaProrrogaOrden) {
        List<SolicitudContribuyenteVO> solicitudContribuyentes = new ArrayList<SolicitudContribuyenteVO>();
        for (FecetProrrogaOrden prorrogaOrden : listaProrrogaOrden) {
            SolicitudContribuyenteVO solicitudContribuyente = new SolicitudContribuyenteVO();            
            solicitudContribuyente.setFechaCarga(prorrogaOrden.getFechaCarga());            
            solicitudContribuyente.setId(prorrogaOrden.getIdProrrogaOrden());            
            solicitudContribuyente.setIdOrden(prorrogaOrden.getIdOrden());  
            if (prorrogaOrden.getDescripcionEstado()==null){
                solicitudContribuyente.setEstatus(prorrogaOrden.getFececEstatus().getDescripcion());       
            }
            else{
              solicitudContribuyente.setEstatus(prorrogaOrden.getDescripcionEstado());
            }
            solicitudContribuyente.setDescripcion(PRORROGA);
            solicitudContribuyente.setTotalDocumentosContribuyente(prorrogaOrden.getTotalDocumentosContribuyente());
            solicitudContribuyente.setTotalDocumentosRechazo(prorrogaOrden.getTotalDocumentosRechazo());
            solicitudContribuyente.setProrrogaOrden(prorrogaOrden);
            if(prorrogaOrden.getFecetFlujoProrrogaOrden()!=null){
                solicitudContribuyente.setIdFlujo(prorrogaOrden.getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden());
                solicitudContribuyente.setJustificacion(prorrogaOrden.getFecetFlujoProrrogaOrden().getJustificacion());
                solicitudContribuyente.setNombreResolucion(prorrogaOrden.getNombreResolucion());
                solicitudContribuyente.setAprobada(prorrogaOrden.getAprobada());
                for(FececEstatus estatus : getListaEstatus()){
                    if(prorrogaOrden.getFecetFlujoProrrogaOrden().getIdEstatus().equals(estatus.getIdEstatus())){
                        solicitudContribuyente.setEstatus(estatus.getDescripcion());
                        break;
                    }
                }
            }
            solicitudContribuyentes.add(solicitudContribuyente);
        }
        return solicitudContribuyentes;
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
            if(pruebaPericial.getFlujoPruebasPericiales()!=null){
                solicitudContribuyente.setIdFlujo(pruebaPericial.getFlujoPruebasPericiales().getIdFlujoPruebasPericiales());
                solicitudContribuyente.setJustificacion(pruebaPericial.getFlujoPruebasPericiales().getJustificacion());
                solicitudContribuyente.setNombreResolucion(pruebaPericial.getNombreResolucion());
                solicitudContribuyente.setAprobada(pruebaPericial.getAprobada());
                for(FececEstatus estatus : getListaEstatus()){
                    if(pruebaPericial.getFlujoPruebasPericiales().getIdEstatus().equals(estatus.getIdEstatus())){
                        solicitudContribuyente.setEstatus(estatus.getDescripcion());
                        break;
                    }
                }
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
    
    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyentePruebasPericiales(List<FecetAnexoPruebasPericiales> listaAnexoPruebasPericiales){
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
    
    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyenteProrroga(List<FecetAnexosProrrogaOrden> listaAnexoProrroga){
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
    
    public List<SolicitudContribuyenteDocVO> llenaDocSolicitudContribuyenteProrroga(List<FecetDocProrrogaOrden> listaDocProrroga){
        List<SolicitudContribuyenteDocVO> listaDocSolicitudContribuyente = new ArrayList<SolicitudContribuyenteDocVO>();
        for (FecetDocProrrogaOrden docProrroga : listaDocProrroga) {
            SolicitudContribuyenteDocVO docSolicitudContribuyente = new SolicitudContribuyenteDocVO();
            docSolicitudContribuyente.setId(docProrroga.getIdDocProrrogaOrden());            
            docSolicitudContribuyente.setIdDoc(docProrroga.getIdProrrogaOrden());
            docSolicitudContribuyente.setNombreArchivo(docProrroga.getNombreArchivo());
            docSolicitudContribuyente.setRutaArchivo(docProrroga.getRutaArchivo());
            docSolicitudContribuyente.setFecetDocProrrogaOrden(docProrroga);
            listaDocSolicitudContribuyente.add(docSolicitudContribuyente);            
        }
        return listaDocSolicitudContribuyente;
    }
    
    public List<SolicitudContribuyenteDocVO> llenaDocSolicitudContribuyentePruebaPericial(List<FecetDocPruebasPericiales> listaDocPruebaPericial){
        List<SolicitudContribuyenteDocVO> listaDocSolicitudContribuyente = new ArrayList<SolicitudContribuyenteDocVO>();
        for (FecetDocPruebasPericiales docPruebaPericial : listaDocPruebaPericial) {
            SolicitudContribuyenteDocVO docSolicitudContribuyente = new SolicitudContribuyenteDocVO();
            docSolicitudContribuyente.setId(docPruebaPericial.getIdDocPruebasPericiales());            
            docSolicitudContribuyente.setIdDoc(docPruebaPericial.getIdPruebasPericiales());
            docSolicitudContribuyente.setNombreArchivo(docPruebaPericial.getNombreArchivo());
            docSolicitudContribuyente.setRutaArchivo(docPruebaPericial.getRutaArchivo());
            docSolicitudContribuyente.setFecetDocPruebasPericiales(docPruebaPericial);
            listaDocSolicitudContribuyente.add(docSolicitudContribuyente);            
        }
        return listaDocSolicitudContribuyente;
    }

    public List<FececEstatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<FececEstatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

}
