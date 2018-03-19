/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class AsociarColaboradoresDTOHelper extends AsociarColaboradoresDTOAbstractHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ColaboradorVO apoderadoLegalVO;
    private ColaboradorVO apoderadoLegalRLVO;
    private ColaboradorVO agenteAduanalVO;
    private ColaboradorVO representanteLegalVO;
    private ColaboradorVO apoderadoLegalOriginalVO;
    private ColaboradorVO apoderadoLegalRLOriginalVO;
    private ColaboradorVO agenteAduanalOriginalVO;
    private ColaboradorVO representanteLegalOriginalVO;

    private FecetDocProrrogaOrden docProrrogaOrdenSeleccionado;
    private FecetProrrogaOrden prorrogaOrdenSeleccionada;
    private FecetPruebasPericiales pruebaPericialSeleccionada;
    private FecetDocPruebasPericiales docPruebaPericialSeleccionado;

    public FecetDocPruebasPericiales getDocPruebaPericialSeleccionado() {
        return docPruebaPericialSeleccionado;
    }

    public void setDocPruebaPericialSeleccionado(FecetDocPruebasPericiales docPruebaPericialSeleccionado) {
        this.docPruebaPericialSeleccionado = docPruebaPericialSeleccionado;
    }

    public FecetPruebasPericiales getPruebaPericialSeleccionada() {
        return pruebaPericialSeleccionada;
    }

    public void setPruebaPericialSeleccionada(FecetPruebasPericiales pruebaPericialSeleccionada) {
        this.pruebaPericialSeleccionada = pruebaPericialSeleccionada;
    }

    public void setApoderadoLegalVO(ColaboradorVO apoderadoLegalVO) {
        this.apoderadoLegalVO = apoderadoLegalVO;
    }

    public ColaboradorVO getApoderadoLegalVO() {
        return apoderadoLegalVO;
    }

    public void setApoderadoLegalRLVO(ColaboradorVO apoderadoLegalRLVO) {
        this.apoderadoLegalRLVO = apoderadoLegalRLVO;
    }

    public ColaboradorVO getApoderadoLegalRLVO() {
        return apoderadoLegalRLVO;
    }

    public void setAgenteAduanalVO(ColaboradorVO agenteAduanalVO) {
        this.agenteAduanalVO = agenteAduanalVO;
    }

    public ColaboradorVO getAgenteAduanalVO() {
        return agenteAduanalVO;
    }

    public void setRepresentanteLegalVO(ColaboradorVO representanteLegalVO) {
        this.representanteLegalVO = representanteLegalVO;
    }

    public ColaboradorVO getRepresentanteLegalVO() {
        return representanteLegalVO;
    }

    public void setProrrogaOrdenSeleccionada(FecetProrrogaOrden prorrogaOrdenSeleccionada) {
        this.prorrogaOrdenSeleccionada = prorrogaOrdenSeleccionada;
    }

    public FecetProrrogaOrden getProrrogaOrdenSeleccionada() {
        return prorrogaOrdenSeleccionada;
    }

    public void setDocProrrogaOrdenSeleccionado(FecetDocProrrogaOrden docProrrogaOrdenSeleccionado) {
        this.docProrrogaOrdenSeleccionado = docProrrogaOrdenSeleccionado;
    }

    public FecetDocProrrogaOrden getDocProrrogaOrdenSeleccionado() {
        return docProrrogaOrdenSeleccionado;
    }

    public void setApoderadoLegalOriginalVO(ColaboradorVO apoderadoLegalOriginalVO) {
        this.apoderadoLegalOriginalVO = apoderadoLegalOriginalVO;
    }

    public ColaboradorVO getApoderadoLegalOriginalVO() {
        return apoderadoLegalOriginalVO;
    }

    public void setApoderadoLegalRLOriginalVO(ColaboradorVO apoderadoLegalRLOriginalVO) {
        this.apoderadoLegalRLOriginalVO = apoderadoLegalRLOriginalVO;
    }

    public ColaboradorVO getApoderadoLegalRLOriginalVO() {
        return apoderadoLegalRLOriginalVO;
    }

    public void setAgenteAduanalOriginalVO(ColaboradorVO agenteAduanalOriginalVO) {
        this.agenteAduanalOriginalVO = agenteAduanalOriginalVO;
    }

    public ColaboradorVO getAgenteAduanalOriginalVO() {
        return agenteAduanalOriginalVO;
    }

    public void setRepresentanteLegalOriginalVO(ColaboradorVO representanteLegalOriginalVO) {
        this.representanteLegalOriginalVO = representanteLegalOriginalVO;
    }

    public ColaboradorVO getRepresentanteLegalOriginalVO() {
        return representanteLegalOriginalVO;
    }

}
