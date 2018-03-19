/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FiltroConsultaPropuestas extends
        BO<FiltroConsultaPropuestas>
        implements Serializable {

    private static final long serialVersionUID = 6397262407261902970L;

    private List<AraceDTO> unidadAdmtvaDesahogoFiltro;
    private List<TipoEstatusEnum> estatusFiltro;
    private List<EmpleadoDTO> lstEmpleadosSubordinados;
    private List<EmpleadoDTO> lstProgramadores;
    private List<EmpleadoDTO> lstSubadministradores;
    private List<TipoAccionPropuesta> lstAccionesPropuesta;
    private EmpleadoDTO empleadoConsultaFiltro;
    private FecetPropuesta propuestaFiltro;
    private TipoEmpleadoEnum tipoEmpleadoConsulta;
    private EstadoBooleanoEnum estusDeDocumentos;
    private boolean visibilidadACPPCE;
    private boolean programacion;

    public EmpleadoDTO getEmpleadoConsultaFiltro() {
        return empleadoConsultaFiltro;
    }

    public void setEmpleadoConsultaFiltro(EmpleadoDTO empleadoConsultaFiltro) {
        this.empleadoConsultaFiltro = empleadoConsultaFiltro;
        visibilidadACPPCE = false;
        for (DetalleEmpleadoDTO detalleEmp : empleadoConsultaFiltro.getDetalleEmpleado()) {
            if (detalleEmp.getCentral() != null && detalleEmp.getCentral().getIdArace() == (int) TipoAraceEnum.ACPPCE.getId()) {
                visibilidadACPPCE = true;
            }
        }

    }

    public List<AraceDTO> getUnidadAdmtvaDesahogoFiltro() {
        return unidadAdmtvaDesahogoFiltro;
    }

    public void setUnidadAdmtvaDesahogoFiltro(List<AraceDTO> unidadAdmtvaDesahogoFiltro) {
        this.unidadAdmtvaDesahogoFiltro = unidadAdmtvaDesahogoFiltro;
    }

    public FecetPropuesta getPropuestaFiltro() {
        return propuestaFiltro;
    }

    public void setPropuestaFiltro(FecetPropuesta propuestaFiltro) {
        this.propuestaFiltro = propuestaFiltro;
    }

    public TipoEmpleadoEnum getTipoEmpleadoConsulta() {
        return tipoEmpleadoConsulta;
    }

    public void setTipoEmpleadoConsulta(TipoEmpleadoEnum tipoEmpleadoConsulta) {
        this.tipoEmpleadoConsulta = tipoEmpleadoConsulta;
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

    public boolean isVisibilidadACPPCE() {
        return visibilidadACPPCE;
    }

    public FiltroPropuestas getFiltroDAO() {
        FiltroPropuestas filtroDAO = new FiltroPropuestas();

        filtroDAO.setUnidadAdmtvaDesahogoFiltro(unidadAdmtvaDesahogoFiltro);
        filtroDAO.setEstatusFiltro(estatusFiltro);
        filtroDAO.setLstEmpleadosSubordinados(lstEmpleadosSubordinados);
        filtroDAO.setLstProgramadores(lstProgramadores);
        filtroDAO.setLstAccionesPropuesta(lstAccionesPropuesta);
        filtroDAO.setEmpleadoConsultaFiltro(empleadoConsultaFiltro);
        filtroDAO.setPropuestaFiltro(propuestaFiltro);
        filtroDAO.setVisibilidadACPPCE(visibilidadACPPCE);
        filtroDAO.setEstusDeDocumentos(estusDeDocumentos);
        filtroDAO.setLstSubadministradores(lstSubadministradores);
        filtroDAO.setProgramacion(isProgramacion());
        return filtroDAO;
    }

    public EstadoBooleanoEnum getEstusDeDocumentos() {
        return estusDeDocumentos;
    }

    public void setEstusDeDocumentos(EstadoBooleanoEnum estusDeDocumentos) {
        this.estusDeDocumentos = estusDeDocumentos;
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

}
