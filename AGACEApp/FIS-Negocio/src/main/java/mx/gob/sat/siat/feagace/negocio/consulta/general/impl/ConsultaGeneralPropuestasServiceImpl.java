package mx.gob.sat.siat.feagace.negocio.consulta.general.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaConsultaGeneralDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;

@Component("consultaGeneralPropuestasService")
public class ConsultaGeneralPropuestasServiceImpl extends ConsultaGeneralService implements ConsultaGeneralPropuestasService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPropuestaConsultaGeneralDao fecetPropuestaConsultaGeneralDao;

    @Override
    public List<EmpleadoDTO> getEmpleadoSolicitado(Integer unidadAministrativa, Integer perfilUsuario,
            ClvSubModulosAgace claveModulo) {

        List<EmpleadoDTO> lstEmpleadosCompletos = new ArrayList<EmpleadoDTO>();

        try {
            List<EmpleadoDTO> lstEmpleados = getEmpleadosXUnidadAdmva(unidadAministrativa, perfilUsuario, claveModulo);

            for (EmpleadoDTO empleado : lstEmpleados) {
                EmpleadoDTO empleadoCompleto = getEmpleadoCompleto(empleado.getRfc());
                lstEmpleadosCompletos.add(empleadoCompleto);
            }

        } catch (EmpleadoServiceException e) {
            logger.error("Error al consultar el Servicio de Empleados" + e);
        }

        return lstEmpleadosCompletos;
    }

    public EmpleadoDTO buscarEmpleado(String rfc) {
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(rfc);
        } catch (EmpleadoServiceException e) {
            logger.error("Error:" + e.getMessage());
        }
        return empleado;
    }

    @Override
    public void consultaPropuestasXSubordinado(ConsultaEjecutivaPropuestasBO consultaPropuestasBO, FiltroPropuestas filtroBusqueda, AgrupadorEstatusPropuestasEnum grupoEstatus, Map<EmpleadoDTO, Integer> propuestasEmpleado) {

        filtroBusqueda.setEstatusFiltro(consultaPropuestasBO.getGruposDeEstatusValidos().get(grupoEstatus).getEstatus());
        filtroBusqueda.setLstAccionesPropuesta(consultaPropuestasBO.getGruposDeEstatusValidos().get(grupoEstatus).getAcciones());
        if (filtroBusqueda.getLstEmpleadosSubordinados() != null) {
            for (EmpleadoDTO empleado : filtroBusqueda.getLstEmpleadosSubordinados()) {
                List<FecetPropuesta> lstPropuestaXEmpleado = null;
                if (completaFiltroConsulta(empleado, filtroBusqueda)) {
                    lstPropuestaXEmpleado = fecetPropuestaConsultaGeneralDao.contarEstatusGrupo(grupoEstatus, filtroBusqueda);
                    if (lstPropuestaXEmpleado != null && !lstPropuestaXEmpleado.isEmpty()) {
                        validaDuplicidad(lstPropuestaXEmpleado, consultaPropuestasBO.getLstPropuestasResult());
                        consultaPropuestasBO.getLstPropuestasResult().addAll(lstPropuestaXEmpleado);
                        setGrupoEstatus(lstPropuestaXEmpleado, grupoEstatus);
                    }
                }
                consultaPropuestasBO.setDetalleXEmpleado(contarPropuestasXEmpelado(lstPropuestaXEmpleado, empleado, propuestasEmpleado));
            }

            fillUnidadAdministrativa(consultaPropuestasBO.getLstPropuestasResult());
        } else {
            filtroBusqueda.setEmpleadoConsultaFiltro(null);
            List<FecetPropuesta> lstTotalPropuestas = fecetPropuestaConsultaGeneralDao.contarEstatusGrupo(grupoEstatus, filtroBusqueda);
            validaDuplicidad(lstTotalPropuestas, consultaPropuestasBO.getLstPropuestasResult());
            consultaPropuestasBO.getLstPropuestasResult().addAll(lstTotalPropuestas);
            setGrupoEstatus(lstTotalPropuestas, grupoEstatus);
            fillUnidadAdministrativa(consultaPropuestasBO.getLstPropuestasResult());
        }

    }

    private Map<EmpleadoDTO, Integer> contarPropuestasXEmpelado(List<FecetPropuesta> lstPropuesta, EmpleadoDTO empleado, Map<EmpleadoDTO, Integer> propuestasEmpleado) {

        if (propuestasEmpleado != null) {
            propuestasEmpleado.put(empleado, lstPropuesta != null && !lstPropuesta.isEmpty() ? lstPropuesta.size() : Constantes.CERO);
            return propuestasEmpleado;
        } else {
            Map<EmpleadoDTO, Integer> propuestasEmpleadoTmp = new HashMap<EmpleadoDTO, Integer>();
            propuestasEmpleadoTmp.put(empleado, lstPropuesta != null && !lstPropuesta.isEmpty() ? lstPropuesta.size() : Constantes.CERO);
            return propuestasEmpleadoTmp;
        }
    }

    private void setGrupoEstatus(List<FecetPropuesta> lstXGrupo, AgrupadorEstatusPropuestasEnum grupo) {
        if (lstXGrupo != null) {
            for (FecetPropuesta prop : lstXGrupo) {
                prop.setEstatusXGrupo(grupo);
            }
        }
    }

    private void fillUnidadAdministrativa(List<FecetPropuesta> lstDtoAgace) {

        for (FecetPropuesta propuesta : lstDtoAgace) {

            FececUnidadAdministrativa unidad;
            unidad = fillUnidadAdministrativa(propuesta.getIdArace().intValue());
            FececArace arace = new FececArace();
            arace.setIdArace(unidad.getIdUnidadAdministrativa());
            arace.setNombre(unidad.getNombre());
            propuesta.setFececArace(arace);
        }
    }

    private boolean completaFiltroConsulta(EmpleadoDTO empleadoConsulta, FiltroPropuestas filtroBusqueda) {
        boolean filtroCorrecto = true;

        if (!filtroBusqueda.isProgramacion()) {
            filtroBusqueda.setEmpleadoConsultaFiltro(empleadoConsulta);
        } else {
            if (filtroBusqueda.getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace() == 1 && filtroBusqueda.getTipoEmpleadoConsulta().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS)) {
                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordAdmin = empleadoConsulta.getSubordinados();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordProgramador = subordAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordSubadmin = subordAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                filtroBusqueda.setLstProgramadores(subordProgramador.get(TipoEmpleadoEnum.PROGRAMADOR));
                filtroBusqueda.setLstSubadministradores(subordSubadmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS));

                boolean filtroBusqueda1 = filtroBusqueda.getLstProgramadores() == null && filtroBusqueda.getLstSubadministradores() == null;

                if (filtroBusqueda1) {
                    filtroCorrecto = false;
                }
            } else if (filtroBusqueda.getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace() == 1 && filtroBusqueda.getTipoEmpleadoConsulta().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                EmpleadoDTO empleadoTmp = buscarEmpleado(empleadoConsulta.getRfc());
                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordSubAdmin = empleadoTmp.getSubordinados();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordProgramador = subordSubAdmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                filtroBusqueda.setLstProgramadores(subordProgramador.get(TipoEmpleadoEnum.PROGRAMADOR));
                filtroBusqueda.setLstSubadministradores(new ArrayList<EmpleadoDTO>());
            } else if (filtroBusqueda.getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace() != 1) {
                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordAdmin = empleadoConsulta.getSubordinados();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordProgramador = subordAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordSubadmin = subordAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                filtroBusqueda.setLstProgramadores(subordProgramador.get(TipoEmpleadoEnum.PROGRAMADOR));
                filtroBusqueda.setLstSubadministradores(subordSubadmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS));

                if (subordSubadmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS) == null) {
                    filtroBusqueda.setLstSubadministradores(new ArrayList<EmpleadoDTO>());
                    filtroBusqueda.getLstSubadministradores().add(empleadoConsulta);
                }
            }
        }

        return filtroCorrecto;
    }

    private void validaDuplicidad(List<FecetPropuesta> lstOrigen, List<FecetPropuesta> lstTotalPropuestas) {
        List<FecetPropuesta> lstResult = new ArrayList<FecetPropuesta>();

        for (FecetPropuesta propuestaOrigen : lstOrigen) {
            if (lstTotalPropuestas.contains(propuestaOrigen)) {
                lstResult.add(propuestaOrigen);
            }
        }
        lstOrigen.removeAll(lstResult);
    }

    @Override
    public boolean validaPeriflSubadminitrador(EmpleadoDTO empleado) {
        for (DetalleEmpleadoDTO detalleEmpleado : empleado.getDetalleEmpleado()) {
            if (detalleEmpleado.getTipoEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) {
                return true;
            }
        }

        return false;
    }
}
