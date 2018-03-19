package mx.gob.sat.siat.feagace.vista.ordenes.carga;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.vista.ordenes.carga.CargarOrdenMBAbstract;

public class CargaOrdenMBSubAbstract extends CargarOrdenMBAbstract implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String seleccionGabinete;
    private List<String> listaGabinetes;
    private String localConMascara;
    private String rfcAuditor;
    private String campoClaveOrden;
    protected static final String FORM_PRIORIDAD = "formOrdenes:txtPrioridadAlta";
    protected static final String FORM_PANEL_LINKS = "formOrdenes:panelLinks";
    private boolean deshabilitarPrioridadAlta;
    private boolean mostrarRechazarOrden;
    private List<FecetPromocion> ordenesArchivoCargado;

    private boolean guardarRepAgente;
    private boolean mostrarDatosAgente;
    private boolean mostrarDatosRepresentante;

    private String nombreAsignarAgente;
    private String rfcAsignarAgente;
    private String nombreAsignarRepresentante;
    private String rfcAsignarRepresentante;
    private String tipoAsignar;
    
    public void setSeleccionGabinete(String seleccionGabinete) {
        this.seleccionGabinete = seleccionGabinete;
    }

    public String getSeleccionGabinete() {
        return seleccionGabinete;
    }

    public void setListaGabinetes(List<String> listaGabinetes) {
        this.listaGabinetes = listaGabinetes;
    }

    public List<String> getListaGabinetes() {
        return listaGabinetes;
    }

    public void setLocalConMascara(String localConMascara) {
        this.localConMascara = localConMascara;
    }

    public String getLocalConMascara() {
        return localConMascara;
    }
    
    public void setRfcAuditor(String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    public String getRfcAuditor() {
        return rfcAuditor;
    }

    public void setCampoClaveOrden(String campoClaveOrden) {
        this.campoClaveOrden = campoClaveOrden;
    }

    public String getCampoClaveOrden() {
        return campoClaveOrden;
    }
    
    public void setDeshabilitarPrioridadAlta(boolean deshabilitarPrioridadAlta) {
        this.deshabilitarPrioridadAlta = deshabilitarPrioridadAlta;
    }

    public boolean isDeshabilitarPrioridadAlta() {
        return deshabilitarPrioridadAlta;
    }

    public void setMostrarRechazarOrden(boolean mostrarRechazarOrden) {
        this.mostrarRechazarOrden = mostrarRechazarOrden;
    }

    public boolean isMostrarRechazarOrden() {
        return mostrarRechazarOrden;
    }

    public void setOrdenesArchivoCargado(List<FecetPromocion> ordenesArchivoCargado) {
        this.ordenesArchivoCargado = ordenesArchivoCargado;
    }

    public List<FecetPromocion> getOrdenesArchivoCargado() {
        return ordenesArchivoCargado;
    }
    
    public void setMostrarDatosAgente(boolean mostrarDatosAgente) {
        this.mostrarDatosAgente = mostrarDatosAgente;
    }

    public boolean isMostrarDatosAgente() {
        return mostrarDatosAgente;
    }

    public void setMostrarDatosRepresentante(boolean mostrarDatosRepresentante) {
        this.mostrarDatosRepresentante = mostrarDatosRepresentante;
    }

    public boolean isMostrarDatosRepresentante() {
        return mostrarDatosRepresentante;
    }

    public void setTipoAsignar(String tipoAsignar) {
        this.tipoAsignar = tipoAsignar;
    }

    public String getTipoAsignar() {
        return tipoAsignar;
    }

    public void setNombreAsignarAgente(String nombreAsignarAgente) {
        this.nombreAsignarAgente = nombreAsignarAgente;
    }

    public String getNombreAsignarAgente() {
        return nombreAsignarAgente;
    }

    public void setRfcAsignarAgente(String rfcAsignarAgente) {
        this.rfcAsignarAgente = rfcAsignarAgente;
    }

    public String getRfcAsignarAgente() {
        return rfcAsignarAgente;
    }

    public void setNombreAsignarRepresentante(String nombreAsignarRepresentante) {
        this.nombreAsignarRepresentante = nombreAsignarRepresentante;
    }

    public String getNombreAsignarRepresentante() {
        return nombreAsignarRepresentante;
    }

    public void setRfcAsignarRepresentante(String rfcAsignarRepresentante) {
        this.rfcAsignarRepresentante = rfcAsignarRepresentante;
    }

    public String getRfcAsignarRepresentante() {
        return rfcAsignarRepresentante;
    }

    public void setGuardarRepAgente(boolean guardarRepAgente) {
        this.guardarRepAgente = guardarRepAgente;
    }

    public boolean isGuardarRepAgente() {
        return guardarRepAgente;
    }

}
