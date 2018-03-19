/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenLstVOHelper implements Serializable {

    private static final long serialVersionUID = -5674660091323510371L;
    
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyente;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteHistorico;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentrado;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentradoHistorico;
    private List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO;
    private List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoVO;
    private List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteResolucionVO;
    private List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteResolucionRechazoVO;
    private List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoRechazoVO;
    private List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistorico;
    private List<SolicitudContribuyenteAnexoVO> listaResolucionSolicitudContribuyenteHistorico;
    private List<SolicitudContribuyenteAnexoVO> listaResolucionSolicitudContribuyenteHistoricoRechazo;
    private List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistoricoRechazo;
    private List<SolicitudContribuyenteAnexoVO> listaDocumentosAnexosRechazoSolicitudContribuyente;

    public List<SolicitudContribuyenteAnexoVO> getListaDocumentosAnexosRechazoSolicitudContribuyente() {
        return listaDocumentosAnexosRechazoSolicitudContribuyente;
    }

    public void setListaDocumentosAnexosRechazoSolicitudContribuyente(
            List<SolicitudContribuyenteAnexoVO> listaDocumentosAnexosRechazoSolicitudContribuyente) {
        this.listaDocumentosAnexosRechazoSolicitudContribuyente = listaDocumentosAnexosRechazoSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaAnexosSolicitudContribuyenteHistorico() {
        return listaAnexosSolicitudContribuyenteHistorico;
    }

    public void setListaAnexosSolicitudContribuyenteHistorico(
            List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistorico) {
        this.listaAnexosSolicitudContribuyenteHistorico = listaAnexosSolicitudContribuyenteHistorico;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaResolucionSolicitudContribuyenteHistorico() {
        return listaResolucionSolicitudContribuyenteHistorico;
    }

    public void setListaResolucionSolicitudContribuyenteHistorico(
            List<SolicitudContribuyenteAnexoVO> listaResolucionSolicitudContribuyenteHistorico) {
        this.listaResolucionSolicitudContribuyenteHistorico = listaResolucionSolicitudContribuyenteHistorico;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaResolucionSolicitudContribuyenteHistoricoRechazo() {
        return listaResolucionSolicitudContribuyenteHistoricoRechazo;
    }

    public void setListaResolucionSolicitudContribuyenteHistoricoRechazo(
            List<SolicitudContribuyenteAnexoVO> listaResolucionSolicitudContribuyenteHistoricoRechazo) {
        this.listaResolucionSolicitudContribuyenteHistoricoRechazo = listaResolucionSolicitudContribuyenteHistoricoRechazo;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaAnexosSolicitudContribuyenteHistoricoRechazo() {
        return listaAnexosSolicitudContribuyenteHistoricoRechazo;
    }

    public void setListaAnexosSolicitudContribuyenteHistoricoRechazo(
            List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyenteHistoricoRechazo) {
        this.listaAnexosSolicitudContribuyenteHistoricoRechazo = listaAnexosSolicitudContribuyenteHistoricoRechazo;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaSolicitudContribuyenteResolucionRechazoVO() {
        return listaSolicitudContribuyenteResolucionRechazoVO;
    }

    public void setListaSolicitudContribuyenteResolucionRechazoVO(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteResolucionRechazoVO) {
        this.listaSolicitudContribuyenteResolucionRechazoVO = listaSolicitudContribuyenteResolucionRechazoVO;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaSolicitudContribuyenteAnexoRechazoVO() {
        return listaSolicitudContribuyenteAnexoRechazoVO;
    }

    public void setListaSolicitudContribuyenteAnexoRechazoVO(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoRechazoVO) {
        this.listaSolicitudContribuyenteAnexoRechazoVO = listaSolicitudContribuyenteAnexoRechazoVO;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaSolicitudContribuyenteResolucionVO() {
        return listaSolicitudContribuyenteResolucionVO;
    }

    public void setListaSolicitudContribuyenteResolucionVO(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteResolucionVO) {
        this.listaSolicitudContribuyenteResolucionVO = listaSolicitudContribuyenteResolucionVO;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaSolicitudContribuyenteAnexoVO() {
        return listaSolicitudContribuyenteAnexoVO;
    }

    public void setListaSolicitudContribuyenteAnexoVO(
            List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoVO) {
        this.listaSolicitudContribuyenteAnexoVO = listaSolicitudContribuyenteAnexoVO;
    }
    
    public List<SolicitudContribuyenteDocVO> getListaSolicitudContribuyenteDocVO() {
        return listaSolicitudContribuyenteDocVO;
    }

    public void setListaSolicitudContribuyenteDocVO(List<SolicitudContribuyenteDocVO> listaSolicitudContribuyenteDocVO) {
        this.listaSolicitudContribuyenteDocVO = listaSolicitudContribuyenteDocVO;
    }
    
    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteConcentradoHistorico() {
        return listaSolicitudContribuyenteConcentradoHistorico;
    }

    public void setListaSolicitudContribuyenteConcentradoHistorico(
            List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentradoHistorico) {
        this.listaSolicitudContribuyenteConcentradoHistorico = listaSolicitudContribuyenteConcentradoHistorico;
    }
    
    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteConcentrado() {
        return listaSolicitudContribuyenteConcentrado;
    }

    public void setListaSolicitudContribuyenteConcentrado(
            List<SolicitudContribuyenteVO> listaSolicitudContribuyenteConcentrado) {
        this.listaSolicitudContribuyenteConcentrado = listaSolicitudContribuyenteConcentrado;
    }
    
    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyente() {
        return listaSolicitudContribuyente;
    }

    public void setListaSolicitudContribuyente(List<SolicitudContribuyenteVO> listaSolicitudContribuyente) {
        this.listaSolicitudContribuyente = listaSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteHistorico() {
        return listaSolicitudContribuyenteHistorico;
    }

    public void setListaSolicitudContribuyenteHistorico(
            List<SolicitudContribuyenteVO> listaSolicitudContribuyenteHistorico) {
        this.listaSolicitudContribuyenteHistorico = listaSolicitudContribuyenteHistorico;
    }
}
