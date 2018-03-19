/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FiltroPropuestas {

    private List<AraceDTO> unidadAdmtvaDesahogoFiltro;
    private List<TipoEstatusEnum> estatusFiltro;
    private List<EmpleadoDTO> lstEmpleadosSubordinados;
    private List<EmpleadoDTO> lstProgramadores;
    private List<EmpleadoDTO> lstSubadministradores;
    private List<TipoAccionPropuesta> lstAccionesPropuesta;
    private EmpleadoDTO empleadoConsultaFiltro;
    private FecetPropuesta propuestaFiltro;
    private EstadoBooleanoEnum estusDeDocumentos;
    private TipoEmpleadoEnum tipoEmpleadoConsulta;
    private boolean visibilidadACPPCE;
    private boolean programacion;
    private boolean regional;

    public List<AraceDTO> getUnidadAdmtvaDesahogoFiltro() {
        return unidadAdmtvaDesahogoFiltro;
    }

    public void setUnidadAdmtvaDesahogoFiltro(List<AraceDTO> unidadAdmtvaDesahogoFiltro) {
        this.unidadAdmtvaDesahogoFiltro = unidadAdmtvaDesahogoFiltro;
    }

    public List<TipoEstatusEnum> getEstatusFiltro() {
        return estatusFiltro;
    }

    public void setEstatusFiltro(List<TipoEstatusEnum> estatusFiltro) {
        this.estatusFiltro = estatusFiltro;
    }

    public List<EmpleadoDTO> getLstEmpleadosSubordinados() {
        return lstEmpleadosSubordinados;
    }

    public void setLstEmpleadosSubordinados(List<EmpleadoDTO> lstEmpleadosSubordinados) {
        this.lstEmpleadosSubordinados = lstEmpleadosSubordinados;
    }

    public List<EmpleadoDTO> getLstProgramadores() {
        return lstProgramadores;
    }

    public void setLstProgramadores(List<EmpleadoDTO> lstProgramadores) {
        this.lstProgramadores = lstProgramadores;
    }

    public List<TipoAccionPropuesta> getLstAccionesPropuesta() {
        return lstAccionesPropuesta;
    }

    public void setLstAccionesPropuesta(List<TipoAccionPropuesta> lstAccionesPropuesta) {
        this.lstAccionesPropuesta = lstAccionesPropuesta;
    }

    public EmpleadoDTO getEmpleadoConsultaFiltro() {
        return empleadoConsultaFiltro;
    }

    public void setEmpleadoConsultaFiltro(EmpleadoDTO empleadoConsultaFiltro) {
        this.empleadoConsultaFiltro = empleadoConsultaFiltro;
    }

    public FecetPropuesta getPropuestaFiltro() {
        return propuestaFiltro;
    }

    public void setPropuestaFiltro(FecetPropuesta propuestaFiltro) {
        this.propuestaFiltro = propuestaFiltro;
    }

    public EstadoBooleanoEnum getEstusDeDocumentos() {
        return estusDeDocumentos;
    }

    public void setEstusDeDocumentos(EstadoBooleanoEnum estusDeDocumentos) {
        this.estusDeDocumentos = estusDeDocumentos;
    }

    public boolean isVisibilidadACPPCE() {
        return visibilidadACPPCE;
    }

    public void setVisibilidadACPPCE(boolean visibilidadACPPCE) {
        this.visibilidadACPPCE = visibilidadACPPCE;
    }

    public TipoEmpleadoEnum getTipoEmpleadoConsulta() {
        return tipoEmpleadoConsulta;
    }

    public void setTipoEmpleadoConsulta(TipoEmpleadoEnum tipoEmpleadoConsulta) {
        this.tipoEmpleadoConsulta = tipoEmpleadoConsulta;
    }
    
    public List<EmpleadoDTO> getLstSubadministradores() {
        return lstSubadministradores;
    }

    public void setLstSubadministradores(List<EmpleadoDTO> lstSubadministradores) {
        this.lstSubadministradores = lstSubadministradores;
    }

    public boolean isProgramacion() {
        return programacion;
    }

    public void setProgramacion(boolean programacion) {
        this.programacion = programacion;
    }

    public boolean isRegional() {
        return regional;
    }

    public void setRegional(boolean regional) {
        this.regional = regional;
    }

}
