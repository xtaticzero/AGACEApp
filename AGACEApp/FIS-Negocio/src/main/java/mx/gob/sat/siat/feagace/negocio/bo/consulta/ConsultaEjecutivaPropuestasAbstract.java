package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;

public class ConsultaEjecutivaPropuestasAbstract extends BO<ConsultaEjecutivaPropuestasBO> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<TipoEmpleadoEnum> lstRolesEmpleado;
    private TipoEmpleadoEnum tipoRolSeleccionado;
    private Map<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> gruposDeEstatusValidos;
    private Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatus;
    private Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatusFiltrado;
    private Map<EmpleadoDTO, Integer> detalleXEmpleado;
    private boolean adace;
    private boolean programacion;
    
    public void setDetalleXEmpleado(Map<EmpleadoDTO, Integer> detalleXEmpleado) {
        this.detalleXEmpleado = detalleXEmpleado;
    }
    
    public Map<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> getGruposDeEstatusValidos() {
        return gruposDeEstatusValidos;
    }

    public void setGruposDeEstatusValidos(Map<AgrupadorEstatusPropuestasEnum, ConsultaEstatusAccionesBO> gruposDeEstatusValidos) {
        this.gruposDeEstatusValidos = gruposDeEstatusValidos;
    }

    public Map<AgrupadorEstatusPropuestasEnum, Integer> getDetalleXEstatus() {
        return detalleXEstatus;
    }

    public void setDetalleXEstatus(Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatus) {
        this.detalleXEstatus = detalleXEstatus;
    }

    public Map<AgrupadorEstatusPropuestasEnum, Integer> getDetalleXEstatusFiltrado() {
        return detalleXEstatusFiltrado;
    }

    public void setDetalleXEstatusFiltrado(Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatusFiltrado) {
        this.detalleXEstatusFiltrado = detalleXEstatusFiltrado;
    }

    public Map<EmpleadoDTO, Integer> getDetalleXEmpleado() {
        return detalleXEmpleado;
    }
    
    public Integer getTotalXEstatus() {
        int total = 0;
        if (this.detalleXEstatus != null && !this.detalleXEstatus.isEmpty()) {
            for (Entry<AgrupadorEstatusPropuestasEnum, Integer> entry : this.detalleXEstatus.entrySet()) {
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

    public Integer getTotalXEstatusFiltrado() {
        int total = 0;
        if (this.detalleXEstatusFiltrado != null && !this.detalleXEstatusFiltrado.isEmpty()) {
            for (Entry<AgrupadorEstatusPropuestasEnum, Integer> entry : this.detalleXEstatusFiltrado.entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        return total;
    }

    public boolean isProgramacion() {
        return programacion;
    }

    public void setProgramacion(boolean programacion) {
        this.programacion = programacion;
    }

    public boolean isAdace() {
        return adace;
    }

    public void setAdace(boolean adace) {
        this.adace = adace;
    }

    public TipoEmpleadoEnum getTipoRolSeleccionado() {
        return tipoRolSeleccionado;
    }

    public void setTipoRolSeleccionado(TipoEmpleadoEnum tipoRolSeleccionado) {
        this.tipoRolSeleccionado = tipoRolSeleccionado;
    }

    public List<TipoEmpleadoEnum> getLstRolesEmpleado() {
        return lstRolesEmpleado;
    }

    public void setLstRolesEmpleado(List<TipoEmpleadoEnum> lstRolesEmpleado) {
        this.lstRolesEmpleado = lstRolesEmpleado;
    }

}
