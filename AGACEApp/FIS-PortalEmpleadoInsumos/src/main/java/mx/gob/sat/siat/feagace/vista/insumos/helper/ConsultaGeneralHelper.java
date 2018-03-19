package mx.gob.sat.siat.feagace.vista.insumos.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;

public class ConsultaGeneralHelper implements Serializable {

    private static final long serialVersionUID = 696447182667227169L;

    private boolean detalleUnidadVisible;

    private boolean detalleEmpleadoVisible;

    private boolean habilitarUnidadAdministrativa;

    private boolean habilitarbtnConsultar;

    private boolean botonConsultarHabilitado;

    private boolean unidadRegistroHabilitado;

    private boolean unidadAdmonHabilitado;

    private boolean unidadAdmonRegistroHabilitado;

    private boolean flgMostrarTlbCategorias;

    private boolean flgMostrarMenuPrincipal;

    private boolean flgMostrarTlbPropuestas;

    private boolean flgMostrarPnlPropuesta;

    private boolean flgMostrarPanelAuditor;

    private boolean flgMostrarPanelFirmante;

    private transient AgrupadorEstatusPropuestasEnum grupoEstatusSeleccionado;

    private transient EmpleadoDTO empleadoSeleccionado;

    private transient EmpleadoDTO auditoeSeleccionado;

    private boolean muestraBtnRegresaFirmante;

    private boolean muestraBtnRegresaAuditor;

    private boolean muestraBtnRegresaEstatus;

    private boolean esVisiblePanelEstatus;

    private boolean esVisiblePanelOpciones;

    private boolean esVisiblePanelAdministrador;

    private boolean esVisiblePanelCentral;

    private boolean esVisiblePanelListaInsumos;

    private boolean esVisiblePanelDetalleInsumos;

    private boolean esConsultaEmpleados;

    public boolean isEsConsultaEmpleados() {
        return esConsultaEmpleados;
    }

    public void setEsConsultaEmpleados(boolean esConsultaEmpleados) {
        this.esConsultaEmpleados = esConsultaEmpleados;
    }

    public boolean isEsVisiblePanelOpciones() {
        return esVisiblePanelOpciones;
    }

    public void setEsVisiblePanelOpciones(boolean esVisiblePanelOpciones) {
        this.esVisiblePanelOpciones = esVisiblePanelOpciones;
    }

    public boolean isEsVisiblePanelEstatus() {
        return esVisiblePanelEstatus;
    }

    public void setEsVisiblePanelEstatus(boolean esVisiblePanelEstatus) {
        this.esVisiblePanelEstatus = esVisiblePanelEstatus;
    }

    public boolean isUnidadAdmonRegistroHabilitado() {
        return unidadAdmonRegistroHabilitado;
    }

    public void setUnidadAdmonRegistroHabilitado(boolean unidadAdmonRegistroHabilitado) {
        this.unidadAdmonRegistroHabilitado = unidadAdmonRegistroHabilitado;
    }

    public boolean isBotonConsultarHabilitado() {
        return botonConsultarHabilitado;
    }

    public void setBotonConsultarHabilitado(boolean botonConsultarHabilitado) {
        this.botonConsultarHabilitado = botonConsultarHabilitado;
    }

    public boolean isDetalleUnidadVisible() {
        return detalleUnidadVisible;
    }

    public void setDetalleUnidadVisible(boolean detalleUnidadVisible) {
        this.detalleUnidadVisible = detalleUnidadVisible;
    }

    public boolean isDetalleEmpleadoVisible() {
        return detalleEmpleadoVisible;
    }

    public void setDetalleEmpleadoVisible(boolean detalleEmpleadoVisible) {
        this.detalleEmpleadoVisible = detalleEmpleadoVisible;
    }

    public boolean isUnidadRegistroHabilitado() {
        return unidadRegistroHabilitado;
    }

    public void setUnidadRegistroHabilitado(boolean unidadRegistroHabilitado) {
        this.unidadRegistroHabilitado = unidadRegistroHabilitado;
    }

    public boolean isUnidadAdmonHabilitado() {
        return unidadAdmonHabilitado;
    }

    public void setUnidadAdmonHabilitado(boolean unidadAdmonHabilitado) {
        this.unidadAdmonHabilitado = unidadAdmonHabilitado;
    }

    public boolean isHabilitarUnidadAdministrativa() {
        return habilitarUnidadAdministrativa;
    }

    public void setHabilitarUnidadAdministrativa(boolean habilitarUnidadAdministrativa) {
        this.habilitarUnidadAdministrativa = habilitarUnidadAdministrativa;
    }

    public boolean isHabilitarbtnConsultar() {
        return habilitarbtnConsultar;
    }

    public void setHabilitarbtnConsultar(boolean habilitarbtnConsultar) {
        this.habilitarbtnConsultar = habilitarbtnConsultar;
    }

    public boolean isFlgMostrarTlbCategorias() {
        return flgMostrarTlbCategorias;
    }

    public void setFlgMostrarTlbCategorias(boolean flgMostrarTlbCategorias) {
        this.flgMostrarTlbCategorias = flgMostrarTlbCategorias;
    }

    public boolean isFlgMostrarMenuPrincipal() {
        return flgMostrarMenuPrincipal;
    }

    public void setFlgMostrarMenuPrincipal(boolean flgMostrarMenuPrincipal) {
        this.flgMostrarMenuPrincipal = flgMostrarMenuPrincipal;
    }

    public AgrupadorEstatusPropuestasEnum getGrupoEstatusSeleccionado() {
        return grupoEstatusSeleccionado;
    }

    public void setGrupoEstatusSeleccionado(AgrupadorEstatusPropuestasEnum grupoEstatusSeleccionado) {
        this.grupoEstatusSeleccionado = grupoEstatusSeleccionado;
    }

    public EmpleadoDTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(EmpleadoDTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public boolean isFlgMostrarPanelAuditor() {
        return flgMostrarPanelAuditor;
    }

    public void setFlgMostrarPanelAuditor(boolean flgMostrarPanelAuditor) {
        this.flgMostrarPanelAuditor = flgMostrarPanelAuditor;
    }

    public boolean isFlgMostrarPanelFirmante() {
        return flgMostrarPanelFirmante;
    }

    public void setFlgMostrarPanelFirmante(boolean flgMostrarPanelFirmante) {
        this.flgMostrarPanelFirmante = flgMostrarPanelFirmante;
    }

    public boolean isFlgMostrarTlbPropuestas() {
        return flgMostrarTlbPropuestas;
    }

    public void setFlgMostrarTlbPropuestas(boolean flgMostrarTlbPropuestas) {
        this.flgMostrarTlbPropuestas = flgMostrarTlbPropuestas;
    }

    public boolean isFlgMostrarPnlPropuesta() {
        return flgMostrarPnlPropuesta;
    }

    public void setFlgMostrarPnlPropuesta(boolean flgMostrarPnlPropuesta) {
        this.flgMostrarPnlPropuesta = flgMostrarPnlPropuesta;
    }

    public boolean isMuestraBtnRegresaFirmante() {
        return muestraBtnRegresaFirmante;
    }

    public void setMuestraBtnRegresaFirmante(boolean muestraBtnRegresaFirmante) {
        this.muestraBtnRegresaFirmante = muestraBtnRegresaFirmante;
    }

    public boolean isMuestraBtnRegresaAuditor() {
        return muestraBtnRegresaAuditor;
    }

    public void setMuestraBtnRegresaAuditor(boolean muestraBtnRegresaAuditor) {
        this.muestraBtnRegresaAuditor = muestraBtnRegresaAuditor;
    }

    public boolean isMuestraBtnRegresaEstatus() {
        return muestraBtnRegresaEstatus;
    }

    public void setMuestraBtnRegresaEstatus(boolean muestraBtnRegresaEstatus) {
        this.muestraBtnRegresaEstatus = muestraBtnRegresaEstatus;
    }

    public EmpleadoDTO getAuditoeSeleccionado() {
        return auditoeSeleccionado;
    }

    public void setAuditoeSeleccionado(EmpleadoDTO auditoeSeleccionado) {
        this.auditoeSeleccionado = auditoeSeleccionado;
    }

    public boolean isEsVisiblePanelListaInsumos() {
        return esVisiblePanelListaInsumos;
    }

    public void setEsVisiblePanelListaInsumos(boolean esVisiblePanelListaInsumos) {
        this.esVisiblePanelListaInsumos = esVisiblePanelListaInsumos;
    }

    public boolean isEsVisiblePanelDetalleInsumos() {
        return esVisiblePanelDetalleInsumos;
    }

    public void setEsVisiblePanelDetalleInsumos(boolean esVisiblePanelDetalleInsumos) {
        this.esVisiblePanelDetalleInsumos = esVisiblePanelDetalleInsumos;
    }

    public boolean isEsVisiblePanelAdministrador() {
        return esVisiblePanelAdministrador;
    }

    public void setEsVisiblePanelAdministrador(boolean esVisiblePanelAdministrador) {
        this.esVisiblePanelAdministrador = esVisiblePanelAdministrador;
    }

    public boolean isEsVisiblePanelCentral() {
        return esVisiblePanelCentral;
    }

    public void setEsVisiblePanelCentral(boolean esVisiblePanelCentral) {
        this.esVisiblePanelCentral = esVisiblePanelCentral;
    }

}
