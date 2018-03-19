/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenVOHelper implements Serializable {

    private static final long serialVersionUID = -4677497478993353176L;

    private SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionada;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoSeleccionado;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteResolucionSeleccionado;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteResolucionRechazoSeleccionado;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoRechazoSeleccionado;
    private SolicitudContribuyenteVO solicitudContribuyenteHistoricoVOSeleccionada;
    private SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoHistoricoVOSeleccionada;
    private SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionado;
    
    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteAnexoHistoricoVOSeleccionada() {
        return solicitudContribuyenteAnexoHistoricoVOSeleccionada;
    }

    public void setSolicitudContribuyenteAnexoHistoricoVOSeleccionada(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoHistoricoVOSeleccionada) {
        this.solicitudContribuyenteAnexoHistoricoVOSeleccionada = solicitudContribuyenteAnexoHistoricoVOSeleccionada;
    }

    public SolicitudContribuyenteVO getSolicitudContribuyenteHistoricoVOSeleccionada() {
        return solicitudContribuyenteHistoricoVOSeleccionada;
    }

    public void setSolicitudContribuyenteHistoricoVOSeleccionada(
            SolicitudContribuyenteVO solicitudContribuyenteHistoricoVOSeleccionada) {
        this.solicitudContribuyenteHistoricoVOSeleccionada = solicitudContribuyenteHistoricoVOSeleccionada;
    }

    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteAnexoRechazoSeleccionado() {
        return solicitudContribuyenteAnexoRechazoSeleccionado;
    }

    public void setSolicitudContribuyenteAnexoRechazoSeleccionado(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoRechazoSeleccionado) {
        this.solicitudContribuyenteAnexoRechazoSeleccionado = solicitudContribuyenteAnexoRechazoSeleccionado;
    }

    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteResolucionRechazoSeleccionado() {
        return solicitudContribuyenteResolucionRechazoSeleccionado;
    }

    public void setSolicitudContribuyenteResolucionRechazoSeleccionado(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteResolucionRechazoSeleccionado) {
        this.solicitudContribuyenteResolucionRechazoSeleccionado = solicitudContribuyenteResolucionRechazoSeleccionado;
    }
    
    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteResolucionSeleccionado() {
        return solicitudContribuyenteResolucionSeleccionado;
    }

    public void setSolicitudContribuyenteResolucionSeleccionado(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteResolucionSeleccionado) {
        this.solicitudContribuyenteResolucionSeleccionado = solicitudContribuyenteResolucionSeleccionado;
    }

    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteAnexoSeleccionado() {
        return solicitudContribuyenteAnexoSeleccionado;
    }

    public void setSolicitudContribuyenteAnexoSeleccionado(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoSeleccionado) {
        this.solicitudContribuyenteAnexoSeleccionado = solicitudContribuyenteAnexoSeleccionado;
    }
    
    public SolicitudContribuyenteVO getSolicitudContribuyenteVOSeleccionada() {
        return solicitudContribuyenteVOSeleccionada;
    }

    public void setSolicitudContribuyenteVOSeleccionada(SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionada) {
        this.solicitudContribuyenteVOSeleccionada = solicitudContribuyenteVOSeleccionada;
    }
    
    public SolicitudContribuyenteDocVO getDocumentacionSolicitudContribuyenteSeleccionado() {
        return documentacionSolicitudContribuyenteSeleccionado;
    }

    public void setDocumentacionSolicitudContribuyenteSeleccionado(
            SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionado) {
        this.documentacionSolicitudContribuyenteSeleccionado = documentacionSolicitudContribuyenteSeleccionado;
    }

}
