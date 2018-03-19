/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FiltroInsumos;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FiltroConsultaInsumos extends
        BO<FiltroConsultaInsumos>
        implements Serializable {

    private static final long serialVersionUID = 3319563589925248017L;

    private List<AraceDTO> unidadAdmtvaDesahogoFiltro;
    private List<TipoEstatusEnum> estatusFiltro;
    private List<EmpleadoDTO> lstEmpleadosSubordinados;
    private SemaforoEnum semaforoSeleccionado;
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
        visibilidadACIACE = false;
        for (DetalleEmpleadoDTO detalleEmp : empleadoConsultaFiltro.getDetalleEmpleado()) {
            if (detalleEmp.getCentral() != null && detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ACIACE.getId()) {
                visibilidadACIACE = true;
            }
        }

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

    public FiltroInsumos getFiltroDAO() {
        FiltroInsumos filtroDAO = new FiltroInsumos();

        filtroDAO.setUnidadAdmtvaDesahogoFiltro(unidadAdmtvaDesahogoFiltro);
        filtroDAO.setEstatusFiltro(estatusFiltro);
        filtroDAO.setLstEmpleadosSubordinados(lstEmpleadosSubordinados);
        filtroDAO.setDiasConcluirPlazo(diasConcluirPlazo);
        filtroDAO.setEmpleadoConsultaFiltro(empleadoConsultaFiltro);
        filtroDAO.setInsumoFiltro(insumoFiltro);
        filtroDAO.setVisibilidadACIACE(visibilidadACIACE);
        filtroDAO.setEstusDeDocumentos(estusDeDocumentos);
        
        if (centralACPPCEReg) {
            filtroDAO.setCentralACPPCEReg(centralACPPCEReg);
            if (getRfcUsuarioACIACE() == null || getRfcUsuarioACIACE().isEmpty()) {
                setRfcUsuarioACIACE(new ArrayList<String>());
            }
            filtroDAO.setRfcUsuarioACIACE(getRfcUsuarioACIACE());
        }

        return filtroDAO;
    }

    public SemaforoEnum getSemaforoSeleccionado() {
        return semaforoSeleccionado;
    }

    public void setSemaforoSeleccionado(SemaforoEnum semaforoSeleccionado) {
        this.semaforoSeleccionado = semaforoSeleccionado;
    }

    public EstadoBooleanoEnum getEstusDeDocumentos() {
        return estusDeDocumentos;
    }

    public void setEstusDeDocumentos(EstadoBooleanoEnum estusDeDocumentos) {
        this.estusDeDocumentos = estusDeDocumentos;
    }

    public final boolean isCentralACPPCEReg() {
        return centralACPPCEReg;
    }

    public final void setCentralACPPCEReg(boolean centralACPPCEReg) {
        this.centralACPPCEReg = centralACPPCEReg;
    }

    public final List<String> getRfcUsuarioACIACE() {
        return rfcUsuarioACIACE;
    }

    public final void setRfcUsuarioACIACE(List<String> rfcUsuarioACIACE) {
        this.rfcUsuarioACIACE = rfcUsuarioACIACE;
    }

    public final void setVisibilidadACIACE(boolean visibilidadACIACE) {
        this.visibilidadACIACE = visibilidadACIACE;
    }

}
