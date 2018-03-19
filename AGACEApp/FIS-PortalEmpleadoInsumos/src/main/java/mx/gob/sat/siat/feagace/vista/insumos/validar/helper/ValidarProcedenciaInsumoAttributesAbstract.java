package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.io.Serializable;

public class ValidarProcedenciaInsumoAttributesAbstract implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5311625502347162350L;

    private boolean visibleUnidad;
    private boolean visibleRevision;
    private boolean visibleFechaPre;
    private boolean visibleFechaInf;
    private boolean mostrarPanelOrdenes = false;
    private boolean mostrarPanelInsumo = false;
    private boolean mostrarOpcionesPropuesta = false;
    private Boolean mostrarTablaArchivosRetro;
    private boolean mostrarBotonProgramacion;

    private String estatusContacto;
    private String fechaRegional;
    private String mensajeDuplicidad;

    public void setMostrarTablaArchivosRetro(Boolean mostrarTablaArchivosRetro) {
        this.mostrarTablaArchivosRetro = mostrarTablaArchivosRetro;
    }

    public Boolean getMostrarTablaArchivosRetro() {
        return mostrarTablaArchivosRetro;
    }

    public void setEstatusContacto(String estatusContacto) {
        this.estatusContacto = estatusContacto;
    }

    public String getEstatusContacto() {
        return estatusContacto;
    }

    public void setVisibleUnidad(boolean visibleUnidad) {
        this.visibleUnidad = visibleUnidad;
    }

    public boolean isVisibleUnidad() {
        return visibleUnidad;
    }

    public void setVisibleRevision(boolean visibleRevision) {
        this.visibleRevision = visibleRevision;
    }

    public boolean isVisibleRevision() {
        return visibleRevision;
    }

    public void setVisibleFechaPre(boolean visibleFechaPre) {
        this.visibleFechaPre = visibleFechaPre;
    }

    public boolean isVisibleFechaPre() {
        return visibleFechaPre;
    }

    public void setVisibleFechaInf(boolean visibleFechaInf) {
        this.visibleFechaInf = visibleFechaInf;
    }

    public boolean isVisibleFechaInf() {
        return visibleFechaInf;
    }

    public void setFechaRegional(String fechaRegional) {
        this.fechaRegional = fechaRegional;
    }

    public String getFechaRegional() {
        return fechaRegional;
    }

    public void setMensajeDuplicidad(String mensajeDuplicidad) {
        this.mensajeDuplicidad = mensajeDuplicidad;
    }

    public String getMensajeDuplicidad() {
        return mensajeDuplicidad;
    }

    public void setMostrarPanelOrdenes(boolean mostrarPanelOrdenes) {
        this.mostrarPanelOrdenes = mostrarPanelOrdenes;
    }

    public boolean isMostrarPanelOrdenes() {
        return mostrarPanelOrdenes;
    }

    public void setMostrarPanelInsumo(boolean mostrarPanelInsumo) {
        this.mostrarPanelInsumo = mostrarPanelInsumo;
    }

    public boolean isMostrarPanelInsumo() {
        return mostrarPanelInsumo;
    }

    public void setMostrarOpcionesPropuesta(boolean mostrarOpcionesPropuesta) {
        this.mostrarOpcionesPropuesta = mostrarOpcionesPropuesta;
    }

    public boolean isMostrarOpcionesPropuesta() {
        return mostrarOpcionesPropuesta;
    }

    public final boolean isMostrarBotonProgramacion() {
        return mostrarBotonProgramacion;
    }

    public final void setMostrarBotonProgramacion(boolean mostrarBotonProgramacion) {
        this.mostrarBotonProgramacion = mostrarBotonProgramacion;
    }
}
