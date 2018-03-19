package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class GraficaDTO extends BaseModel{

    @SuppressWarnings("compatibility:-7792812611145926417")
    private static final long serialVersionUID = 1L;
    
    private String tituloGrafica;
    private String tipoGrafica;
    private Date fechaInicio;
    private Date fechaFinal;
    private Integer nivelGrafica;
    private List<GraficaValoresDTO> listaValores;
    private boolean presentaEstatus;
    private boolean presentaEntidad;
    private boolean presentaMetodo;
    private boolean presentaUnidadAdministrativa;
    private String pathGrafica;
    private String nombreGrafica;
    

    public void setTituloGrafica(String tituloGrafica) {
        this.tituloGrafica = tituloGrafica;
    }

    public String getTituloGrafica() {
        return tituloGrafica;
    }

    public void setTipoGrafica(String tipoGrafica) {
        this.tipoGrafica = tipoGrafica;
    }

    public String getTipoGrafica() {
        return tipoGrafica;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()): null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal != null ? new Date(fechaFinal.getTime()): null;
    }

    public Date getFechaFinal() {
        return (fechaFinal != null) ? (Date) fechaFinal.clone() : null;
    }

    public void setNivelGrafica(Integer nivelGrafica) {
        this.nivelGrafica = nivelGrafica;
    }

    public Integer getNivelGrafica() {
        return nivelGrafica;
    }

    public void setListaValores(List<GraficaValoresDTO> listaValores) {
        this.listaValores = listaValores;
    }

    public List<GraficaValoresDTO> getListaValores() {
        return listaValores;
    }

    public void setPresentaEstatus(boolean presentaEstatus) {
        this.presentaEstatus = presentaEstatus;
    }

    public boolean isPresentaEstatus() {
        return presentaEstatus;
    }

    public void setPresentaEntidad(boolean presentaEntidad) {
        this.presentaEntidad = presentaEntidad;
    }

    public boolean isPresentaEntidad() {
        return presentaEntidad;
    }

    public void setPathGrafica(String pathGrafica) {
        this.pathGrafica = pathGrafica;
    }

    public String getPathGrafica() {
        return pathGrafica;
    }

    public void setNombreGrafica(String nombreGrafica) {
        this.nombreGrafica = nombreGrafica;
    }

    public String getNombreGrafica() {
        return nombreGrafica;
    }

    public void setPresentaMetodo(boolean presentaMetodo) {
        this.presentaMetodo = presentaMetodo;
    }

    public boolean isPresentaMetodo() {
        return presentaMetodo;
    }

    public void setPresentaUnidadAdministrativa(boolean presentaUnidadAdministrativa) {
        this.presentaUnidadAdministrativa = presentaUnidadAdministrativa;
    }

    public boolean isPresentaUnidadAdministrativa() {
        return presentaUnidadAdministrativa;
    }
}
