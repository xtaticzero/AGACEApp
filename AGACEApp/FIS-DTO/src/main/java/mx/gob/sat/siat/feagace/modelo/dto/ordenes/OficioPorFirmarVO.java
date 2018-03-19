package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class OficioPorFirmarVO extends BaseModel implements Serializable {
    @SuppressWarnings("compatibility:8085287187463388922")
    private static final long serialVersionUID = 1L;
    
    private AgaceOrden ordenSeleccionado;
    private FecetOficio oficioSeleccionado;
    private FecetProrrogaOrden prorrogaOrden;
    private FecetProrrogaOficio prorrogaOficio;
    private String rfcAuditado;
    private String claveAdministracion;
    private String rfcFirmante;
    private List<FecetOficio> oficiosDependientes;
    

    public OficioPorFirmarVO() {
        
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setProrrogaOrden(FecetProrrogaOrden prorrogaOrden) {
        this.prorrogaOrden = prorrogaOrden;
    }

    public FecetProrrogaOrden getProrrogaOrden() {
        return prorrogaOrden;
    }

    public void setProrrogaOficio(FecetProrrogaOficio prorrogaOficio) {
        this.prorrogaOficio = prorrogaOficio;
    }

    public FecetProrrogaOficio getProrrogaOficio() {
        return prorrogaOficio;
    }

    public void setRfcAuditado(String rfcAuditado) {
        this.rfcAuditado = rfcAuditado;
    }

    public String getRfcAuditado() {
        return rfcAuditado;
    }
    
    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public String getRfcFirmante() {
        return rfcFirmante;
    }

    public void setClaveAdministracion(String claveAdministracion) {
        this.claveAdministracion = claveAdministracion;
    }

    public String getClaveAdministracion() {
        return claveAdministracion;
    }

    public void setOficiosDependientes(List<FecetOficio> oficiosDependientes) {
        this.oficiosDependientes = oficiosDependientes;
    }

    public List<FecetOficio> getOficiosDependientes() {
        return oficiosDependientes;
    }
}
