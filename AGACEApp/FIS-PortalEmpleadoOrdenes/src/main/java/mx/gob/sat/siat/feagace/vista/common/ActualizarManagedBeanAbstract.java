package mx.gob.sat.siat.feagace.vista.common;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.negocio.common.ActualizarFechaService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaRegistraNyVService;

public class ActualizarManagedBeanAbstract extends BaseManagedBean{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private AgaceOrden orden;
    private FecetOficio oficio;
    private FecetSuspensionDTO suspension;
    private String numeroOrden;
    private BigDecimal idOficio;

    private boolean mostrarFechaInicio;
    private boolean mostrarFechaFin;
    private boolean mostrarFechaBaja;
    private boolean mostrarFechaInsumo;
    private boolean mostrarBuscar;
    private String tipoComponente;
    private boolean visualizarFechaOrden;
    private boolean visualizarFechaInsumo;
    private boolean visualizarFechaOficio;
    private int tipoFechaOrden;
    private int tipoFechaInsumo;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaBaja;
    private String registroInsumo;
    private FecetSuspensionInsumo supensionInsumo;
    private FecetInsumo insumo;

    
    
    
    @ManagedProperty(value = "#{actualizarFechaService}")
    private transient ActualizarFechaService actualizarFechaService;

    @ManagedProperty(value = "#{consultaRegistraNyVService}")
    private transient ConsultaRegistraNyVService consultaRegistraNyVService;
    
    
    @PostConstruct
    public void init() {
        limpiarFormulario();
    }

    public void limpiarFormulario(){        
        setOrden( null);
        setOficio (null);
        setIdOficio (null);
        setNumeroOrden("");
        setMostrarFechaInicio(false);
        setMostrarFechaFin(false);
        setMostrarFechaBaja(false);
        setTipoComponente("");
        setVisualizarFechaOrden(false);
        setVisualizarFechaOficio(false);
        setVisualizarFechaInsumo(false);
        setTipoFechaOrden(0);
    }
    
    
    public void limpiarComponente(){        
        setOrden( null);
        setOficio (null);
        setIdOficio (null);
        setNumeroOrden("");
        setMostrarFechaInicio(false);
        setMostrarFechaFin(false);
        setMostrarFechaBaja(false);
        setVisualizarFechaOrden(false);
        setVisualizarFechaOficio(false);
        setTipoFechaOrden(0);
        setMostrarBuscar(false);
        setTipoFechaInsumo(0);
        setRegistroInsumo("");
        setMostrarFechaInsumo(false);
        setVisualizarFechaInsumo(false);
        setInsumo(null);
        
    }
    
    
    
    public AgaceOrden getOrden() {
        return orden;
    }
    public void setOrden(AgaceOrden orden) {
        this.orden = orden;
    }
    public FecetOficio getOficio() {
        return oficio;
    }
    public void setOficio(FecetOficio oficio) {
        this.oficio = oficio;
    }
    public FecetSuspensionDTO getSuspension() {
        return suspension;
    }
    public void setSuspension(FecetSuspensionDTO suspension) {
        this.suspension = suspension;
    }
    public String getNumeroOrden() {
        return numeroOrden;
    }
    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    public BigDecimal getIdOficio() {
        return idOficio;
    }
    public void setIdOficio(BigDecimal idOficio) {
        this.idOficio = idOficio;
    }
    
    

    public ActualizarFechaService getActualizarFechaService() {
        return actualizarFechaService;
    }

    public void setActualizarFechaService(ActualizarFechaService actualizarFechaService) {
        this.actualizarFechaService = actualizarFechaService;
    }

    public ConsultaRegistraNyVService getConsultaRegistraNyVService() {
        return consultaRegistraNyVService;
    }

    public void setConsultaRegistraNyVService(ConsultaRegistraNyVService consultaRegistraNyVService) {
        this.consultaRegistraNyVService = consultaRegistraNyVService;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public boolean isVisualizarFechaOrden() {
        return visualizarFechaOrden;
    }

    public void setVisualizarFechaOrden(boolean visualizarFechaOrden) {
        this.visualizarFechaOrden = visualizarFechaOrden;
    }

    public int getTipoFechaOrden() {
        return tipoFechaOrden;
    }

    public void setTipoFechaOrden(int tipoFechaOrden) {
        this.tipoFechaOrden = tipoFechaOrden;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    public boolean isMostrarFechaInicio() {
        return mostrarFechaInicio;
    }

    public void setMostrarFechaInicio(boolean mostrarFechaInicio) {
        this.mostrarFechaInicio = mostrarFechaInicio;
    }

    public boolean isMostrarFechaFin() {
        return mostrarFechaFin;
    }

    public void setMostrarFechaFin(boolean mostrarFechaFin) {
        this.mostrarFechaFin = mostrarFechaFin;
    }

    public boolean isMostrarFechaBaja() {
        return mostrarFechaBaja;
    }

    public void setMostrarFechaBaja(boolean mostrarFechaBaja) {
        this.mostrarFechaBaja = mostrarFechaBaja;
    }

    public String getRegistroInsumo() {
        return registroInsumo;
    }

    public void setRegistroInsumo(String registroInsumo) {
        this.registroInsumo = registroInsumo;
    }

    public boolean isMostrarFechaInsumo() {
        return mostrarFechaInsumo;
    }

    public void setMostrarFechaInsumo(boolean mostrarFechaInsumo) {
        this.mostrarFechaInsumo = mostrarFechaInsumo;
    }

    public boolean isVisualizarFechaInsumo() {
        return visualizarFechaInsumo;
    }

    public void setVisualizarFechaInsumo(boolean visualizarFechaInsumo) {
        this.visualizarFechaInsumo = visualizarFechaInsumo;
    }

    public int getTipoFechaInsumo() {
        return tipoFechaInsumo;
    }

    public void setTipoFechaInsumo(int tipoFechaInsumo) {
        this.tipoFechaInsumo = tipoFechaInsumo;
    }

    public boolean isVisualizarFechaOficio() {
        return visualizarFechaOficio;
    }

    public void setVisualizarFechaOficio(boolean visualizarFechaOficio) {
        this.visualizarFechaOficio = visualizarFechaOficio;
    }
    

    public boolean isMostrarBuscar() {
        return mostrarBuscar;
    }

    public void setMostrarBuscar(boolean mostrarBuscar) {
        this.mostrarBuscar = mostrarBuscar;
    }

    public FecetSuspensionInsumo getSupensionInsumo() {
        return supensionInsumo;
    }

    public void setSupensionInsumo(FecetSuspensionInsumo supensionInsumo) {
        this.supensionInsumo = supensionInsumo;
    }

    public FecetInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(FecetInsumo insumo) {
        this.insumo = insumo;
    }

}
