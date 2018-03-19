/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.bo.consulta;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaPropuestasBO extends ConsultaEjecutivaPropuestasAbstract {

    private static final long serialVersionUID = -4084693084386820132L;

    private TipoEmpleadoEnum rolEmpleado;
    private EmpleadoDTO empleadoConsulta;
    private FecetPropuesta propuestaDetalle;
    private FiltroConsultaPropuestas filtroDeterminarDetalle;
    private List<AraceDTO> lstUnidadesAdministrativasDesahogo;
    private List<FecetPropuesta> lstPropuestasResult;
    private List<EmpleadoDTO> lstSubordinados;
    private List<EmpleadoDTO> lstProgramadores;
    private List<EmpleadoDTO> lstSubadministradores;
    private List<FecetPropuesta> lstPropuestasXFiltro;
    private List<FecetPropuesta> lstPropuestasMutiplesFiltros;
    private List<FecetPropuesta> lstPropuestasTotalEmpleado;
    private List<TipoAccionPropuesta> lstAccionesValidas;
    private boolean isAcppce;

    protected static ConsultaEjecutivaPropuestasBO getInstansOfBO(EmpleadoDTO empleado) {
        ConsultaEjecutivaPropuestasBO ceBO = new ConsultaEjecutivaPropuestasBO();
        ceBO.setEmpleadoConsulta(empleado);
        return ceBO;
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
            setRule(ConsultaEjecutivaPropuestasRule.ES_ACPPCE);
            isAcppce = this.getRule().process(this);
        }
    }

    public FecetPropuesta getPropuestaDetalle() {
        return propuestaDetalle;
    }

    public void setPropuestaDetalle(FecetPropuesta propuestaDetalle) {
        this.propuestaDetalle = propuestaDetalle;
    }

    public FiltroConsultaPropuestas getFiltroDeterminarDetalle() {
        return filtroDeterminarDetalle;
    }

    public void setFiltroDeterminarDetalle(FiltroConsultaPropuestas filtroDeterminarDetalle) {
        this.filtroDeterminarDetalle = filtroDeterminarDetalle;
    }

    public List<AraceDTO> getLstUnidadesAdministrativasDesahogo() {
        return lstUnidadesAdministrativasDesahogo;
    }

    public void setLstUnidadesAdministrativasDesahogo(List<AraceDTO> lstUnidadesAdministrativasDesahogo) {
        this.lstUnidadesAdministrativasDesahogo = lstUnidadesAdministrativasDesahogo;
    }

    public List<FecetPropuesta> getLstPropuestasResult() {
        return lstPropuestasResult;
    }

    public void setLstPropuestasResult(List<FecetPropuesta> lstPropuestasResult) {
        this.lstPropuestasResult = lstPropuestasResult;
    }

    public List<EmpleadoDTO> getLstSubordinados() {
        return lstSubordinados;
    }

    public void setLstSubordinados(List<EmpleadoDTO> lstSubordinados) {
        this.lstSubordinados = lstSubordinados;
    }

    public List<EmpleadoDTO> getLstProgramadores() {
        return lstProgramadores;
    }

    public void setLstProgramadores(List<EmpleadoDTO> lstProgramadores) {
        this.lstProgramadores = lstProgramadores;
    }

    public List<FecetPropuesta> getLstPropuestasXFiltro() {
        return lstPropuestasXFiltro;
    }

    public void setLstPropuestasXFiltro(List<FecetPropuesta> lstPropuestasXFiltro) {
        this.lstPropuestasXFiltro = lstPropuestasXFiltro;
    }

    public List<FecetPropuesta> getLstPropuestasMutiplesFiltros() {
        return lstPropuestasMutiplesFiltros;
    }

    public void setLstPropuestasMutiplesFiltros(List<FecetPropuesta> lstPropuestasMutiplesFiltros) {
        this.lstPropuestasMutiplesFiltros = lstPropuestasMutiplesFiltros;
    }

    public List<FecetPropuesta> getLstPropuestasTotalEmpleado() {
        return lstPropuestasTotalEmpleado;
    }

    public void setLstPropuestasTotalEmpleado(List<FecetPropuesta> lstPropuestasTotalEmpleado) {
        this.lstPropuestasTotalEmpleado = lstPropuestasTotalEmpleado;
    }

    public List<TipoAccionPropuesta> getLstAccionesValidas() {
        return lstAccionesValidas;
    }

    public void setLstAccionesValidas(List<TipoAccionPropuesta> lstAccionesValidas) {
        this.lstAccionesValidas = lstAccionesValidas;
    }

    public boolean isIsAcppce() {
        return isAcppce;
    }

    public List<EmpleadoDTO> getLstSubadministradores() {
        return lstSubadministradores;
    }

    public void setLstSubadministradores(List<EmpleadoDTO> lstSubadministradores) {
        this.lstSubadministradores = lstSubadministradores;
    }

}
