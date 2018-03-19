/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.util.List;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FiltroInsumos extends BaseModel {

    private static final long serialVersionUID = -7377384369855511758L;

    private List<AraceDTO> unidadAdmtvaDesahogoFiltro;
    private List<TipoEstatusEnum> estatusFiltro;
    private List<EmpleadoDTO> lstEmpleadosSubordinados;
    private Integer diasConcluirPlazo;
    private EmpleadoDTO empleadoConsultaFiltro;
    private FecetInsumo insumoFiltro;
    private EstadoBooleanoEnum estusDeDocumentos;
    private boolean visibilidadACIACE;
    private boolean centralACPPCEReg; 
    private List<String> rfcUsuarioACIACE;

    public Integer getDiasConcluirPlazo() {
        return diasConcluirPlazo;
    }

    public void setDiasConcluirPlazo(Integer diasConcluirPlazo) {
        this.diasConcluirPlazo = diasConcluirPlazo;
    }

    public EmpleadoDTO getEmpleadoConsultaFiltro() {
        return empleadoConsultaFiltro;
    }

    public void setEmpleadoConsultaFiltro(EmpleadoDTO empleadoConsultaFiltro) {
        this.empleadoConsultaFiltro = empleadoConsultaFiltro;
    }

    public List<AraceDTO> getUnidadAdmtvaDesahogoFiltro() {
        return unidadAdmtvaDesahogoFiltro;
    }

    public void setUnidadAdmtvaDesahogoFiltro(List<AraceDTO> unidadAdmtvaDesahogoFiltro) {
        this.unidadAdmtvaDesahogoFiltro = unidadAdmtvaDesahogoFiltro;
    }

    public FecetInsumo getInsumoFiltro() {
        return insumoFiltro;
    }

    public void setInsumoFiltro(FecetInsumo insumoFiltro) {
        this.insumoFiltro = insumoFiltro;
    }

    public List<TipoEstatusEnum> getEstatusFiltro() {
        return estatusFiltro;
    }

    public void setEstatusFiltro(List<TipoEstatusEnum> estatusFiltro) {
        this.estatusFiltro = estatusFiltro;
    }

    public List<EmpleadoDTO> getLstEmpleadosSubordinados() {
        return lstEmpleadosSubordinados;
    }

    public void setLstEmpleadosSubordinados(List<EmpleadoDTO> lstEmpleadosSubordinados) {
        this.lstEmpleadosSubordinados = lstEmpleadosSubordinados;
    }

    public boolean isVisibilidadACIACE() {
        return visibilidadACIACE;
    }

    public void setVisibilidadACIACE(boolean visibilidadACIACE) {
        this.visibilidadACIACE = visibilidadACIACE;
    }

    public EstadoBooleanoEnum getEstusDeDocumentos() {
        return estusDeDocumentos;
    }

    public void setEstusDeDocumentos(EstadoBooleanoEnum estusDeDocumentos) {
        this.estusDeDocumentos = estusDeDocumentos;
    }

    public boolean isCentralACPPCEReg() {
        return centralACPPCEReg;
    }

    public void setCentralACPPCEReg(boolean centralACPPCEReg) {
        this.centralACPPCEReg = centralACPPCEReg;
    }

    public List<String> getRfcUsuarioACIACE() {
        return rfcUsuarioACIACE;
    }

    public void setRfcUsuarioACIACE(List<String> rfcUsuarioACIACE) {
        this.rfcUsuarioACIACE = rfcUsuarioACIACE;
    }

}
