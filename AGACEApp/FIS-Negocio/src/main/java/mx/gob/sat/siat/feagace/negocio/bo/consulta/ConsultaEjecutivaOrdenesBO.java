package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

public class ConsultaEjecutivaOrdenesBO extends BO<ConsultaEjecutivaOrdenesBO> implements Serializable {
    
    private static final long serialVersionUID = 2545378366024419698L;
    
    private TipoEmpleadoEnum rolEmpleado;
    private EmpleadoDTO empleadoConsulta;
    private AgaceOrden ordenDetalle;
    private List<AraceDTO> lstUnidadesAdministrativasDesahogo;
    private List<TipoEstatusEnum> lstEstatusValidos;
    private List<TipoMetodoEnum> lstMetodosValidos;
    private List<SemaforoEnum> lstSemaforosValidos;
    private List<AgaceOrden> lstOrdenResult;
    private List<EmpleadoDTO> lstSubordinados;
    private List<AgaceOrden> lstOrdenesXFiltro;
    private List<AgaceOrden> lstOrdenesMutiplesFiltros;
    private List<AgaceOrden> lstOrdenesTotalEmpleado;
    private Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> gruposDeEstatusValidos;
    private Map<AgrupadorEstatusOrdenesEnum, Integer> detalleXEstatus;
    private Map<AgrupadorEstatusOrdenesEnum, Integer> detalleXEstatusFiltrado;
    private Map<EmpleadoDTO, Integer> detalleXEmpleado;
    private Map<EmpleadoDTO, Integer> detalleXEmpleadoFiltrado;
    private Map<TipoMetodoEnum, Integer> detalleXMetodo;
    private Map<TipoMetodoEnum, Integer> detalleXMetodoFiltrado;
    private Map<SemaforoEnum, Integer> detalleXSemaforo;
    private Map<SemaforoEnum, Integer> detalleXSemaforoFiltrado;
    private boolean isAcppce;
    
    protected static ConsultaEjecutivaOrdenesBO getInstansOfBO(EmpleadoDTO empleado){
        ConsultaEjecutivaOrdenesBO consultaEjecutivaOrdenesBO = new ConsultaEjecutivaOrdenesBO();
        consultaEjecutivaOrdenesBO.setEmpleadoConsulta(empleado);
        return consultaEjecutivaOrdenesBO;
    }
    
    public TipoEmpleadoEnum getRolEmpleado() {
        return rolEmpleado;
    }
    
    public void setRolEmpleado(TipoEmpleadoEnum rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }
    
    public EmpleadoDTO getEmpleadoConsulta() {
        return empleadoConsulta;
    }
    
    public void setEmpleadoConsulta(EmpleadoDTO empleadoConsulta) {
        this.empleadoConsulta = empleadoConsulta;
        if (this.empleadoConsulta != null && this.empleadoConsulta.getDetalleEmpleado() != null) {
            setRule(ConsultaEjecutivaOrdenesRule.ES_ACPPCE);
            isAcppce = this.getRule().process(this);
        }
    }
    
    public AgaceOrden getOrdenDetalle() {
        return ordenDetalle;
    }
    
    public void setOrdenDetalle(AgaceOrden ordenDetalle) {
        this.ordenDetalle = ordenDetalle;
    }
    
    public List<AraceDTO> getLstUnidadesAdministrativasDesahogo() {
        return lstUnidadesAdministrativasDesahogo;
    }
    
    public void setLstUnidadesAdministrativasDesahogo(
            List<AraceDTO> lstUnidadesAdministrativasDesahogo) {
        this.lstUnidadesAdministrativasDesahogo = lstUnidadesAdministrativasDesahogo;
    }
    
    public List<TipoEstatusEnum> getLstEstatusValidos() {
        return lstEstatusValidos;
    }
    
    public void setLstEstatusValidos(List<TipoEstatusEnum> lstEstatusValidos) {
        this.lstEstatusValidos = lstEstatusValidos;
    }
    
    public List<AgaceOrden> getLstOrdenResult() {
        return lstOrdenResult;
    }
    
    public void setLstOrdenResult(List<AgaceOrden> lstOrdenResult) {
        this.lstOrdenResult = lstOrdenResult;
    }
    
    public List<EmpleadoDTO> getLstSubordinados() {
        return lstSubordinados;
    }
    
    public void setLstSubordinados(List<EmpleadoDTO> lstSubordinados) {
        this.lstSubordinados = lstSubordinados;
    }
    
    public List<AgaceOrden> getLstOrdenesXFiltro() {
        return lstOrdenesXFiltro;
    }
    
    public void setLstOrdenesXFiltro(List<AgaceOrden> lstOrdenesXFiltro) {
        this.lstOrdenesXFiltro = lstOrdenesXFiltro;
    }
    
    public List<AgaceOrden> getLstOrdenesMutiplesFiltros() {
        return lstOrdenesMutiplesFiltros;
    }
    
    public void setLstOrdenesMutiplesFiltros(
            List<AgaceOrden> lstOrdenesMutiplesFiltros) {
        this.lstOrdenesMutiplesFiltros = lstOrdenesMutiplesFiltros;
    }
    
    public List<AgaceOrden> getLstOrdenesTotalEmpleado() {
        return lstOrdenesTotalEmpleado;
    }
    
    public void setLstOrdenesTotalEmpleado(List<AgaceOrden> lstOrdenesTotalEmpleado) {
        this.lstOrdenesTotalEmpleado = lstOrdenesTotalEmpleado;
    }
    
    public Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> getGruposDeEstatusValidos() {
        return gruposDeEstatusValidos;
    }
    
    public void setGruposDeEstatusValidos(
            Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> gruposDeEstatusValidos) {
        this.gruposDeEstatusValidos = gruposDeEstatusValidos;
    }
    
    public Map<AgrupadorEstatusOrdenesEnum, Integer> getDetalleXEstatus() {
        return detalleXEstatus;
    }
    
    public void setDetalleXEstatus(
            Map<AgrupadorEstatusOrdenesEnum, Integer> detalleXEstatus) {
        this.detalleXEstatus = detalleXEstatus;
    }
    
    public Map<AgrupadorEstatusOrdenesEnum, Integer> getDetalleXEstatusFiltrado() {
        return detalleXEstatusFiltrado;
    }
    
    public void setDetalleXEstatusFiltrado(
            Map<AgrupadorEstatusOrdenesEnum, Integer> detalleXEstatusFiltrado) {
        this.detalleXEstatusFiltrado = detalleXEstatusFiltrado;
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
    
    public void setDetalleXSemaforoFiltrado(
            Map<SemaforoEnum, Integer> detalleXSemaforoFiltrado) {
        this.detalleXSemaforoFiltrado = detalleXSemaforoFiltrado;
    }
    
    public Map<TipoMetodoEnum, Integer> getDetalleXMetodo() {
        return detalleXMetodo;
    }

    public void setDetalleXMetodo(Map<TipoMetodoEnum, Integer> detalleXMetodo) {
        this.detalleXMetodo = detalleXMetodo;
    }

    public Map<TipoMetodoEnum, Integer> getDetalleXMetodoFiltrado() {
        return detalleXMetodoFiltrado;
    }

    public void setDetalleXMetodoFiltrado(
            Map<TipoMetodoEnum, Integer> detalleXMetodoFiltrado) {
        this.detalleXMetodoFiltrado = detalleXMetodoFiltrado;
    }
    
    public List<TipoMetodoEnum> getLstMetodosValidos() {
        return lstMetodosValidos;
    }

    public void setLstMetodosValidos(List<TipoMetodoEnum> lstMetodosValidos) {
        this.lstMetodosValidos = lstMetodosValidos;
    }
    
    public List<SemaforoEnum> getLstSemaforosValidos() {
        return lstSemaforosValidos;
    }

    public void setLstSemaforosValidos(List<SemaforoEnum> lstSemaforosValidos) {
        this.lstSemaforosValidos = lstSemaforosValidos;
    }
    
    public Map<EmpleadoDTO, Integer> getDetalleXEmpleadoFiltrado() {
        return detalleXEmpleadoFiltrado;
    }

    public void setDetalleXEmpleadoFiltrado(
            Map<EmpleadoDTO, Integer> detalleXEmpleadoFiltrado) {
        this.detalleXEmpleadoFiltrado = detalleXEmpleadoFiltrado;
    }

    public boolean isIsAcppce() {
        return isAcppce;
    }
    
    public Integer getTotalXEstatus() {
        int total = 0;
        if (this.detalleXEstatusFiltrado != null && !this.detalleXEstatusFiltrado.isEmpty()) {
            for (Entry<AgrupadorEstatusOrdenesEnum, Integer> entry : this.detalleXEstatusFiltrado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }
    
    public Integer getTotalXMetodo() {
        int total = 0;
        if (this.detalleXMetodo != null && !this.detalleXMetodo.isEmpty()) {
            for (Entry<TipoMetodoEnum, Integer> entry : this.detalleXMetodo.entrySet()) {
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
    
    public Integer getTotalXEmpleadoFiltrado() {
        int total = 0;
        if (this.detalleXEmpleadoFiltrado != null && !this.detalleXEmpleadoFiltrado.isEmpty()) {
            for (Entry<EmpleadoDTO, Integer> entry : this.detalleXEmpleadoFiltrado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public Integer getTotalXEstatusFiltrado() {
        int total = 0;
        if (this.detalleXEstatusFiltrado != null && !this.detalleXEstatusFiltrado.isEmpty()) {
            for (Entry<AgrupadorEstatusOrdenesEnum, Integer> entry : this.detalleXEstatusFiltrado.entrySet()) {
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
    
}
