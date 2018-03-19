package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.AdministradorReasignacion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;

public class AsignarInsumoComplexAbstract extends AsignarInsumoBasicAbstract {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<FecetInsumo> listaInsumosPorAsignar;
    private List<FecetDocExpInsumo> listaExpedientes;
    private List<FecetInsumo> listaEnviarInsumos;
    private List<EmpleadoDTO> listaSubAdministrador;
    private transient List<AdministradorReasignacion> listaAdministrador;
    private List<ContadorInsumosSubAdmin> listaContadorInsumos;
    private List<FecetReasignacionInsumo> listaReasignaciones;
    private List<FecetInsumo> listaReasignacionesInsumos;
    private List<String> listaEstatusInsumo;
    private List<FecetInsumo> insumosSeleccionados;
    private List<FecetInsumo> insumosReasignacionSeleccionados;
    private List<FecetReasignacionInsumo> reasignacionesAceptadas;
    private List<FecetInsumo> reasignacionesRechazadas;
    private List<FecetDocumento> listaDocumentosJustificacion;
    
    public void setListaInsumosPorAsignar(List<FecetInsumo> listaInsumosPorAsignar) {
        this.listaInsumosPorAsignar = listaInsumosPorAsignar;
    }

    public List<FecetInsumo> getListaInsumosPorAsignar() {
        return listaInsumosPorAsignar;
    }
    
    public void setListaExpedientes(List<FecetDocExpInsumo> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public List<FecetDocExpInsumo> getListaExpedientes() {
        return listaExpedientes;
    }
    
    public void setListaEnviarInsumos(List<FecetInsumo> listaEnviarInsumos) {
        this.listaEnviarInsumos = listaEnviarInsumos;
    }

    public List<FecetInsumo> getListaEnviarInsumos() {
        return listaEnviarInsumos;
    }

    public void setListaSubAdministrador(List<EmpleadoDTO> listaSubAdministrador) {
        this.listaSubAdministrador = listaSubAdministrador;
    }

    public List<EmpleadoDTO> getListaSubAdministrador() {
        return listaSubAdministrador;
    }

    public void setListaAdministrador(List<AdministradorReasignacion> listaAdministrador) {
        this.listaAdministrador = listaAdministrador;
    }

    public List<AdministradorReasignacion> getListaAdministrador() {
        return listaAdministrador;
    }

    public void setListaContadorInsumos(List<ContadorInsumosSubAdmin> listaContadorInsumos) {
        this.listaContadorInsumos = listaContadorInsumos;
    }

    public List<ContadorInsumosSubAdmin> getListaContadorInsumos() {
        return listaContadorInsumos;
    }

    public void setListaReasignaciones(List<FecetReasignacionInsumo> listaReasignaciones) {
        this.listaReasignaciones = listaReasignaciones;
    }

    public List<FecetReasignacionInsumo> getListaReasignaciones() {
        return listaReasignaciones;
    }

    public void setListaReasignacionesInsumos(List<FecetInsumo> listaReasignacionesInsumos) {
        this.listaReasignacionesInsumos = listaReasignacionesInsumos;
    }

    public List<FecetInsumo> getListaReasignacionesInsumos() {
        return listaReasignacionesInsumos;
    }
    
    public List<String> getListaEstatusInsumo() {
        return listaEstatusInsumo;
    }

    public void setListaEstatusInsumo(List<String> listaEstatusInsumo) {
        this.listaEstatusInsumo = listaEstatusInsumo;
    }

    public final List<FecetInsumo> getInsumosSeleccionados() {
        return insumosSeleccionados;
    }

    public final void setInsumosSeleccionados(List<FecetInsumo> insumosSeleccionados) {
        this.insumosSeleccionados = insumosSeleccionados;
    }

    public final List<FecetInsumo> getInsumosReasignacionSeleccionados() {
        return insumosReasignacionSeleccionados;
    }

    public final void setInsumosReasignacionSeleccionados(List<FecetInsumo> insumosReasignacionSeleccionados) {
        this.insumosReasignacionSeleccionados = insumosReasignacionSeleccionados;
    }

    public final List<FecetReasignacionInsumo> getReasignacionesAceptadas() {
        return reasignacionesAceptadas;
    }

    public final void setReasignacionesAceptadas(List<FecetReasignacionInsumo> reasignacionesAceptadas) {
        this.reasignacionesAceptadas = reasignacionesAceptadas;
    }

    public final List<FecetInsumo> getReasignacionesRechazadas() {
        return reasignacionesRechazadas;
    }

    public final void setReasignacionesRechazadas(List<FecetInsumo> reasignacionesRechazadas) {
        this.reasignacionesRechazadas = reasignacionesRechazadas;
    }
    
    public List<FecetDocumento> getListaDocumentosJustificacion() {
        return listaDocumentosJustificacion;
    }

    public void setListaDocumentosJustificacion(List<FecetDocumento> listaDocumentosJustificacion) {
        this.listaDocumentosJustificacion = listaDocumentosJustificacion;
    }
}
