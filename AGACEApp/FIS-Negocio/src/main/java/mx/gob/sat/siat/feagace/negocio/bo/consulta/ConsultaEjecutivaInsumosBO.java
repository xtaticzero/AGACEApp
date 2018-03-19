/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaInsumosBO extends
        BO<ConsultaEjecutivaInsumosBO> implements Serializable {

    private static final long serialVersionUID = 5801555822488281887L;

    private TipoEmpleadoEnum rolEmpleado;
    private EmpleadoDTO empleadoConsulta;
    private FecetInsumo insumoDetalle;
    private List<GrupoUnidadesAdminXGeneral> lstGrDeUdsDesahogo;
    private List<TipoEstatusEnum> lstEstatusValidos;
    private List<FecetInsumo> lstInsumoResult;
    private List<EmpleadoDTO> lstSubordinados;
    private List<FecetInsumo> lstInsumosXFiltro;
    private List<FecetInsumo> lstInsumoMutiplesFiltros;
    private List<FecetInsumo> lstInsumoTotalEmpleado;
    private Map<TipoEstatusEnum, Integer> detalleXEstatus;
    private Map<TipoEstatusEnum, Integer> detalleXEstatusFiltrado;
    private Map<EmpleadoDTO, Integer> detalleXEmpleado;
    private Map<SemaforoEnum, Integer> detalleXSemaforo;
    private Map<SemaforoEnum, Integer> detalleXSemaforoFiltrado;
    private Map<String, List<String>> subordinadosAciace;
    private boolean isAciace;
    private boolean centralACPPCE;
    private boolean administradorACPPCE;

    public ConsultaEjecutivaInsumosBO(ConsultaEjecutivaInsumosBO consultaBO) {
        this();
        if (consultaBO != null) {
            rolEmpleado = consultaBO.getRolEmpleado();
            empleadoConsulta = consultaBO.getEmpleadoConsulta();
            setMapGruposUnidades(consultaBO.getMapGruposUnidades());
            lstEstatusValidos = consultaBO.getLstEstatusValidos();
            detalleXEstatus = consultaBO.getDetalleXEstatus();
            detalleXEmpleado = consultaBO.getDetalleXEmpleado();
            detalleXSemaforo = consultaBO.getDetalleXSemaforo();
        }
    }

    protected ConsultaEjecutivaInsumosBO() {
        rolEmpleado = null;
        this.empleadoConsulta = null;
        setMapGruposUnidades(new HashMap<BigDecimal, GrupoUnidadesAdminXGeneral>());
        lstEstatusValidos = new ArrayList<TipoEstatusEnum>();
        detalleXEstatus = new EnumMap<TipoEstatusEnum, Integer>(TipoEstatusEnum.class);
        detalleXEmpleado = new HashMap<EmpleadoDTO, Integer>();
        detalleXSemaforo = new EnumMap<SemaforoEnum, Integer>(SemaforoEnum.class);
        subordinadosAciace = new HashMap<String, List<String>>();
    }

    public TipoEmpleadoEnum getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(TipoEmpleadoEnum rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public List<GrupoUnidadesAdminXGeneral> getLstGrDeUdsDesahogo() {
        return lstGrDeUdsDesahogo;
    }

    public void setLstGrDeUdsDesahogo(List<GrupoUnidadesAdminXGeneral> lstGrDeUdsDesahogo) {
        this.lstGrDeUdsDesahogo = lstGrDeUdsDesahogo;
    }

    public EmpleadoDTO getEmpleadoConsulta() {
        return empleadoConsulta;
    }

    public void setEmpleadoConsulta(EmpleadoDTO empleadoConsulta) {
        this.empleadoConsulta = empleadoConsulta;
    }

    public List<TipoEstatusEnum> getLstEstatusValidos() {
        return lstEstatusValidos;
    }

    public void setLstEstatusValidos(List<TipoEstatusEnum> lstEstatusValidos) {
        this.lstEstatusValidos = lstEstatusValidos;
    }

    public Map<TipoEstatusEnum, Integer> getDetalleXEstatus() {
        return detalleXEstatus;
    }

    public void setDetalleXEstatus(Map<TipoEstatusEnum, Integer> detalleXEstatus) {
        this.detalleXEstatus = detalleXEstatus;
    }

    public Map<EmpleadoDTO, Integer> getDetalleXEmpleado() {
        return detalleXEmpleado;
    }

    public void setDetalleXEmpleado(Map<EmpleadoDTO, Integer> detalleXEmpleado) {
        this.detalleXEmpleado = detalleXEmpleado;
    }

    public Map<SemaforoEnum, Integer> getDetalleXSemaforo() {
        return detalleXSemaforo;
    }

    public void setDetalleXSemaforo(Map<SemaforoEnum, Integer> detalleXSemaforo) {
        this.detalleXSemaforo = detalleXSemaforo;
    }

    public Map<SemaforoEnum, Integer> getDetalleXSemaforoFiltrado() {
        return detalleXSemaforoFiltrado;
    }

    public void setDetalleXSemaforoFiltrado(Map<SemaforoEnum, Integer> detalleXSemaforoFiltrado) {
        this.detalleXSemaforoFiltrado = detalleXSemaforoFiltrado;
    }

    public Integer getTotalXEstatus() {
        int total = 0;
        if (this.detalleXEstatus != null && !this.detalleXEstatus.isEmpty()) {
            for (Entry<TipoEstatusEnum, Integer> entry : this.detalleXEstatus.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public Integer getTotalXEmpleados() {
        int total = 0;
        if (this.detalleXEmpleado != null && !this.detalleXEmpleado.isEmpty()) {
            for (Entry<EmpleadoDTO, Integer> entry : this.detalleXEmpleado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public Integer getTotalXSemaforos() {
        int total = 0;
        if (this.detalleXSemaforo != null && !this.detalleXSemaforo.isEmpty()) {
            for (Entry<SemaforoEnum, Integer> entry : this.detalleXSemaforo.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public Integer getTotalXSemaforosFiltrados() {
        int total = 0;
        if (this.detalleXSemaforoFiltrado != null && !this.detalleXSemaforoFiltrado.isEmpty()) {
            for (Entry<SemaforoEnum, Integer> entry : this.detalleXSemaforoFiltrado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public Integer getTotalXEstatusFiltrado() {
        int total = 0;
        if (this.detalleXEstatusFiltrado != null && !this.detalleXEstatusFiltrado.isEmpty()) {
            for (Entry<TipoEstatusEnum, Integer> entry : this.detalleXEstatusFiltrado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    protected static ConsultaEjecutivaInsumosBO getInstansOfBO(EmpleadoDTO empleado) {
        ConsultaEjecutivaInsumosBO ceBO = new ConsultaEjecutivaInsumosBO();
        ceBO.setEmpleadoConsulta(empleado);
        return ceBO;
    }

    public List<FecetInsumo> getLstInsumoResult() {
        return lstInsumoResult;
    }

    public void setLstInsumoResult(List<FecetInsumo> lstInsumoResult) {
        this.lstInsumoResult = lstInsumoResult;
        lstInsumosXFiltro = null;
    }

    public List<FecetInsumo> getLstInsumosXFiltro() {
        return lstInsumosXFiltro;
    }

    public void setLstInsumosXFiltro(List<FecetInsumo> lstInsumosXFiltro) {
        this.lstInsumosXFiltro = lstInsumosXFiltro;
    }

    public List<EmpleadoDTO> getLstSubordinados() {
        return lstSubordinados;
    }

    public void setLstSubordinados(List<EmpleadoDTO> lstSubordinados) {
        this.lstSubordinados = lstSubordinados;
    }

    public boolean isIsAciace() {
        return isAciace;
    }

    public void setIsAciace(boolean isAciace) {
        this.isAciace = isAciace;
    }

    public List<FecetInsumo> getLstInsumoMutiplesFiltros() {
        return lstInsumoMutiplesFiltros;
    }

    public void setLstInsumoMutiplesFiltros(List<FecetInsumo> lstInsumoMutiplesFiltros) {
        this.lstInsumoMutiplesFiltros = lstInsumoMutiplesFiltros;
    }

    public FecetInsumo getInsumoDetalle() {
        return insumoDetalle;
    }

    public void setInsumoDetalle(FecetInsumo insumoDetalle) {
        this.insumoDetalle = insumoDetalle;
    }

    public List<FecetInsumo> getLstInsumoTotalEmpleado() {
        return lstInsumoTotalEmpleado;
    }

    public void setLstInsumoTotalEmpleado(List<FecetInsumo> lstInsumoTotalEmpleado) {
        this.lstInsumoTotalEmpleado = lstInsumoTotalEmpleado;
    }

    public Map<TipoEstatusEnum, Integer> getDetalleXEstatusFiltrado() {
        return detalleXEstatusFiltrado;
    }

    public void setDetalleXEstatusFiltrado(Map<TipoEstatusEnum, Integer> detalleXEstatusFiltrado) {
        this.detalleXEstatusFiltrado = detalleXEstatusFiltrado;
    }

    public final boolean isCentralACPPCE() {
        return centralACPPCE;
    }

    public final void setCentralACPPCE(boolean centralACPPCE) {
        this.centralACPPCE = centralACPPCE;
    }

    public final Map<String, List<String>> getSubordinadosAciace() {
        return subordinadosAciace;
    }

    public final void setSubordinadosAciace(Map<String, List<String>> subordinadosAciace) {
        this.subordinadosAciace = subordinadosAciace;
    }

    public final boolean isAdministradorACPPCE() {
        return administradorACPPCE;
    }

    public final void setAdministradorACPPCE(boolean administradorACPPCE) {
        this.administradorACPPCE = administradorACPPCE;
    }
}
