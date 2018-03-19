package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.propuestas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasRule;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ConsultaGeneralManagedBean;

@ManagedBean(name = "consultaUGPropuestasMB")
@ViewScoped
public class ConsultaGeneralPropuestasManagedBean extends ConsultaGeneralManagedBean {

    private static final long serialVersionUID = 1L;

    private static final String MSJ_PROPUESTAS_RECHAZADAS = "No se mostrar\u00e1 la relaci\u00f3n Firmante/Auditor para aquellas propuestas que fueron rechazadas por el \u00e1rea de programaci\u00f3n. (No fueron consideradas por el programador para ser validadas por un Auditor o Firmante).";

    private List<FecetPropuesta> lstPropuestaGeneral;
    private FecetPropuesta propuestaSeleccionada;
    private StreamedContent xlsReporte;

    @PostConstruct
    public void init() {
        super.init();
        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setFlgMostrarMenuPrincipal(true);
        getHelper().setFlgMostrarTlbPropuestas(false);
        getHelper().setFlgMostrarPnlPropuesta(false);
        getHelper().setHabilitarUnidadAdministrativa(false);
        getHelper().setHabilitarbtnConsultar(false);
        getHelper().setMuestraBtnRegresaAuditor(false);
        getHelper().setMuestraBtnRegresaEstatus(false);
        getHelper().setMuestraBtnRegresaFirmante(false);
    }

    public void actualizarUnidadAdministraiva(final AjaxBehaviorEvent event) {

        if (getTipoConsulta() == 0) {
            getHelper().setHabilitarUnidadAdministrativa(false);
            getHelper().setHabilitarbtnConsultar(false);
            setIdArace(0);
        } else {
            setListaUnidadesAdministrativas(obtenerUnidadesAdministrativas());
            getHelper().setHabilitarUnidadAdministrativa(true);
            if (getTipoConsulta() == 1) {
                getListaUnidadesAdministrativas().remove(TipoAraceEnum.ACPPCE);
            }
            if (getTipoConsulta() == 2) {
                getListaUnidadesAdministrativas().remove(TipoAraceEnum.ACAOCE);
                getListaUnidadesAdministrativas().remove(TipoAraceEnum.ACOECE);
            }
        }
    }

    public void actualizarBtnConsultar(final AjaxBehaviorEvent event) {
        if (getIdArace() == 0) {
            getHelper().setHabilitarbtnConsultar(false);
        } else {
            getHelper().setHabilitarbtnConsultar(true);
        }
    }

    public void consultarEstatusPropuesta() {
        getHelper().setFlgMostrarTlbCategorias(true);
        getHelper().setFlgMostrarMenuPrincipal(false);
        if (getIdArace() == -1) {
            consultarEstatusXtodas();
        } else {
            consultarEstatusXarace();
        }
    }

    public void consultarEstatusXarace() {
        setConsultaPropuestasBO(new ConsultaEjecutivaPropuestasBO());
        getConsultaPropuestasBO().setDetalleXEstatus(
                new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        setFiltro(new FiltroPropuestas());
        setUnidadesAdmon(new ArrayList<AraceDTO>());
        AraceDTO araceDTO = new AraceDTO();
        getConsultaPropuestasBO().setProgramacion(Integer.valueOf("2").equals(getTipoConsulta()));
        getConsultaPropuestasBO()
                .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
        getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());
        araceDTO.setIdArace(getIdArace());
        getUnidadesAdmon().add(araceDTO);
        getFiltro().setUnidadAdmtvaDesahogoFiltro(getUnidadesAdmon());
        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());

        try {
            getConsultaPropuestasBO().setLstProgramadores(getEmpleadoService().getEmpleadosXUnidadAdmva(getIdArace(),
                    TipoEmpleadoEnum.PROGRAMADOR.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS));
            getFiltro().setLstProgramadores(getConsultaPropuestasBO().getLstProgramadores());
            getConsultaPropuestasBO()
                    .setLstSubadministradores(getEmpleadoService().getEmpleadosXUnidadAdmva(getIdArace(),
                                    TipoEmpleadoEnum.VALIDADOR_INSUMOS.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS));
            getFiltro().setLstSubadministradores(getConsultaPropuestasBO().getLstSubadministradores());
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }
        getConsultaEjecutivaPropuestasService().consultarEstatusPropuestasSuper(getConsultaPropuestasBO(), getFiltro());
    }

    public void consultarEstatusXtodas() {
        setConsultaPropuestasBO(new ConsultaEjecutivaPropuestasBO());
        setFiltro(new FiltroPropuestas());

        getConsultaPropuestasBO().setProgramacion(Integer.valueOf("2").equals(getTipoConsulta()));
        getConsultaPropuestasBO()
                .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
        getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());
        getConsultaPropuestasBO().setDetalleXEstatus(
                new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());

        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
        for (TipoAraceEnum araceEnum : getListaUnidadesAdministrativas()) {
            setUnidadesAdmon(new ArrayList<AraceDTO>());
            AraceDTO araceDTO = new AraceDTO();
            araceDTO.setIdArace((int) araceEnum.getId());
            getUnidadesAdmon().add(araceDTO);
            getFiltro().setUnidadAdmtvaDesahogoFiltro(getUnidadesAdmon());

            try {
                getConsultaPropuestasBO()
                        .setLstProgramadores(getEmpleadoService().getEmpleadosXUnidadAdmva(araceDTO.getIdArace(),
                                        TipoEmpleadoEnum.PROGRAMADOR.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS));
                getFiltro().setLstProgramadores(getConsultaPropuestasBO().getLstProgramadores());
                getConsultaPropuestasBO().setLstSubadministradores(getEmpleadoService().getEmpleadosXUnidadAdmva(
                        araceDTO.getIdArace(), TipoEmpleadoEnum.VALIDADOR_INSUMOS.getBigId().intValue(),
                        ClvSubModulosAgace.PROPUESTAS));
                getFiltro().setLstSubadministradores(getConsultaPropuestasBO().getLstSubadministradores());

                getConsultaEjecutivaPropuestasService().consultarEstatusPropuestasSuper(getConsultaPropuestasBO(),
                        getFiltro());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
        }

    }

    public List<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>> getDetalleEstatus() {
        if (getConsultaPropuestasBO().getDetalleXEstatus() != null) {
            Set<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>> estatusSet = getConsultaPropuestasBO()
                    .getDetalleXEstatus().entrySet();
            return new ArrayList<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>>(estatusSet);
        }
        return new ArrayList<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>>();
    }

    public void regresarMenu() {
        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setFlgMostrarMenuPrincipal(true);
        getHelper().setHabilitarUnidadAdministrativa(false);
        getHelper().setHabilitarbtnConsultar(false);
        setIdArace(0);
        setTipoConsulta(0);
    }

    public void consultaGeneralTotal() {
        getHelper().setFlgMostrarTlbPropuestas(true);
        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setMuestraBtnRegresaAuditor(false);
        getHelper().setMuestraBtnRegresaFirmante(false);
        getHelper().setMuestraBtnRegresaEstatus(true);
        setLstPropuestaGeneral(getConsultaPropuestasBO().getLstPropuestasResult());
    }

    public void regresarGrupoEstatus() {
        getHelper().setFlgMostrarTlbPropuestas(false);
        getHelper().setFlgMostrarTlbCategorias(true);
        if (getIdArace() == -1) {
            consultarEstatusXtodas();
        } else {
            consultarEstatusXarace();
        }
        limpiarFiltros();
    }

    public void consultarXGupoEstatusSeleccionado() {
        if (!getConsultaPropuestasBO().isProgramacion()) {
            if (getIdArace() == -1) {
                consultaXFirmanteAllAraces();
            } else {
                consultarXFirmante();
            }
        } else {
            if (getIdArace() == -1) {
                consultaXAdministradorAllAraces();
            } else {
                consultaXAdministrador();
            }
        }
    }

    public void consultaXSuboordinadoSeleccionado() {
        if (!getConsultaPropuestasBO().isProgramacion()) {
            consultarXAuditor();
        } else {
            consultaXSubadministrador();
        }
    }

    private void consultaXFirmanteAllAraces() {
        getConsultaPropuestasBO().setProgramacion(false);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getConsultaPropuestasBO().setDetalleXEstatus(
                new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        for (TipoAraceEnum araceEnum : getListaUnidadesAdministrativas()) {
            setUnidadesAdmon(new ArrayList<AraceDTO>());
            AraceDTO arace = new AraceDTO();
            arace.setIdArace((int) araceEnum.getId());
            getUnidadesAdmon().add(arace);
            getFiltro().setUnidadAdmtvaDesahogoFiltro(getUnidadesAdmon());
            getConsultaPropuestasBO()
                    .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
            getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());
            getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
        }

        setLstPropuestaGeneral(getConsultaPropuestasBO().getLstPropuestasResult());
        getHelper().setFlgMostrarTlbPropuestas(true);
        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setMuestraBtnRegresaEstatus(true);
        getHelper().setMuestraBtnRegresaAuditor(false);
        getHelper().setMuestraBtnRegresaFirmante(false);
    }

    private void consultaXAdministradorAllAraces() {
        getConsultaPropuestasBO().setProgramacion(true);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getConsultaPropuestasBO().setDetalleXEstatus(
                new EnumMap<AgrupadorEstatusPropuestasEnum, Integer>(AgrupadorEstatusPropuestasEnum.class));
        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        for (TipoAraceEnum araceEnum : getListaUnidadesAdministrativas()) {
            setUnidadesAdmon(new ArrayList<AraceDTO>());
            AraceDTO arace = new AraceDTO();
            arace.setIdArace((int) araceEnum.getId());
            getUnidadesAdmon().add(arace);
            getFiltro().setUnidadAdmtvaDesahogoFiltro(getUnidadesAdmon());
            getConsultaPropuestasBO()
                    .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
            getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

            try {
                getConsultaPropuestasBO()
                        .setLstProgramadores(getEmpleadoService().getEmpleadosXUnidadAdmva(arace.getIdArace(),
                                        TipoEmpleadoEnum.PROGRAMADOR.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS));
                getFiltro().setLstProgramadores(getConsultaPropuestasBO().getLstProgramadores());
                getConsultaPropuestasBO().setLstSubadministradores(getEmpleadoService().getEmpleadosXUnidadAdmva(
                        arace.getIdArace(), TipoEmpleadoEnum.VALIDADOR_INSUMOS.getBigId().intValue(),
                        ClvSubModulosAgace.PROPUESTAS));
                getFiltro().setLstSubadministradores(getConsultaPropuestasBO().getLstSubadministradores());

                getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
                setLstPropuestaGeneral(getConsultaPropuestasBO().getLstPropuestasResult());
                getHelper().setFlgMostrarTlbPropuestas(true);
                getHelper().setFlgMostrarTlbCategorias(false);
                getHelper().setMuestraBtnRegresaEstatus(true);
                getHelper().setMuestraBtnRegresaAuditor(false);
                getHelper().setMuestraBtnRegresaFirmante(false);
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void consultarXFirmante() {
        getConsultaPropuestasBO().setProgramacion(false);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.FIRMANTE);
        List<EmpleadoDTO> firmantes;
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        if (getFiltro().getUnidadAdmtvaDesahogoFiltro() != null
                && !getFiltro().getUnidadAdmtvaDesahogoFiltro().isEmpty()) {
            AraceDTO arace;
            arace = getFiltro().getUnidadAdmtvaDesahogoFiltro().get(0);
            firmantes = getConsultaGeneralPropuestasService().getEmpleadoSolicitado(arace.getIdArace(),
                    TipoEmpleadoEnum.FIRMANTE.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS);

            if (firmantes != null && !firmantes.isEmpty()) {
                getFiltro().setLstEmpleadosSubordinados(firmantes);
                getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
                getConsultaPropuestasBO()
                        .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
                getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

                getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(),
                        getFiltro(), grupoEstatus, propuestasEmpleado);
            }
        }

        if (grupoEstatus.equals(AgrupadorEstatusPropuestasEnum.PROPUESTA_RECHAZADA)) {
            addErrorMessage(null, MSJ_PROPUESTAS_RECHAZADAS);
        }

        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setFlgMostrarPanelFirmante(true);
    }

    private void consultarXAuditor() {
        getConsultaPropuestasBO().setProgramacion(false);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.AUDITOR);
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinadosFirmante = getHelper().getEmpleadoSeleccionado().getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordinadosAuditores = subordinadosFirmante.get(TipoEmpleadoEnum.FIRMANTE);
        List<EmpleadoDTO> lstAuditores = subordinadosAuditores.get(TipoEmpleadoEnum.AUDITOR);

        if (lstAuditores != null && !lstAuditores.isEmpty()) {
            getFiltro().setLstEmpleadosSubordinados(lstAuditores);
            getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
            getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
            getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

            getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
        }
        getHelper().setFlgMostrarPanelFirmante(false);
        getHelper().setFlgMostrarPanelAuditor(true);
    }

    public void consultaXAuditorSeleccionado() {

        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();
        List<EmpleadoDTO> empleadoXBuscar = new ArrayList<EmpleadoDTO>();
        empleadoXBuscar.add(getHelper().getAuditoeSeleccionado());
        getFiltro().setLstEmpleadosSubordinados(empleadoXBuscar);
        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
        getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
        getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

        if (!getConsultaPropuestasBO().isProgramacion()) {
            getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.AUDITOR);
        } else {
            getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.VALIDADOR_INSUMOS);
        }

        getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
        consultaTotalPropuestasXEmpleado();
    }

    private void consultaXAdministrador() {
        getConsultaPropuestasBO().setProgramacion(true);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
        List<EmpleadoDTO> administradores;
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        if (getFiltro().getUnidadAdmtvaDesahogoFiltro() != null
                && !getFiltro().getUnidadAdmtvaDesahogoFiltro().isEmpty()) {
            AraceDTO arace;
            arace = getFiltro().getUnidadAdmtvaDesahogoFiltro().get(0);
            administradores = getConsultaGeneralPropuestasService().getEmpleadoSolicitado(arace.getIdArace(),
                    TipoEmpleadoEnum.ASIGNADOR_INSUMOS.getBigId().intValue(), ClvSubModulosAgace.PROPUESTAS);

            if (administradores != null && !administradores.isEmpty()) {
                getFiltro().setLstEmpleadosSubordinados(administradores);
                getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
                getConsultaPropuestasBO()
                        .setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
                getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

                getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
            }
        }

        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setFlgMostrarPanelFirmante(true);
    }

    private void consultaXSubadministrador() {
        getConsultaPropuestasBO().setProgramacion(true);
        getConsultaPropuestasBO().setLstPropuestasResult(new ArrayList<FecetPropuesta>());
        AgrupadorEstatusPropuestasEnum grupoEstatus = getHelper().getGrupoEstatusSeleccionado();
        getFiltro().setTipoEmpleadoConsulta(TipoEmpleadoEnum.VALIDADOR_INSUMOS);
        Map<EmpleadoDTO, Integer> propuestasEmpleado = new HashMap<EmpleadoDTO, Integer>();

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinadosAdmin = getHelper().getEmpleadoSeleccionado().getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> subordinadosSubAdmin = subordinadosAdmin.get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
        List<EmpleadoDTO> lstSubadmin = subordinadosSubAdmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS);

        getFiltro().setProgramacion(getConsultaPropuestasBO().isProgramacion());
        getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_SUPER_CONSULTA_PROPUESTAS);
        getConsultaPropuestasBO().getRule().process(getConsultaPropuestasBO());

        if (lstSubadmin != null && !lstSubadmin.isEmpty()) {
            getFiltro().setLstEmpleadosSubordinados(lstSubadmin);
            getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
        } else {
            if (getFiltro().getUnidadAdmtvaDesahogoFiltro().get(0).getIdArace() != 1
                    && getConsultaGeneralPropuestasService().validaPeriflSubadminitrador(getHelper().getEmpleadoSeleccionado())) {
                getFiltro().setLstEmpleadosSubordinados(new ArrayList<EmpleadoDTO>());
                getFiltro().getLstEmpleadosSubordinados().add((getHelper().getEmpleadoSeleccionado()));
                getConsultaGeneralPropuestasService().consultaPropuestasXSubordinado(getConsultaPropuestasBO(), getFiltro(), grupoEstatus, propuestasEmpleado);
            }
        }
        getHelper().setFlgMostrarPanelFirmante(false);
        getHelper().setFlgMostrarPanelAuditor(true);
    }

    public void regresaGrupoEstatus() {
        getHelper().setFlgMostrarTlbCategorias(true);
        getHelper().setFlgMostrarPanelFirmante(false);
        consultarEstatusXarace();
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getDetalleEmpleado() {

        if (getConsultaPropuestasBO().getDetalleXEmpleado() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> empleadoSet = getConsultaPropuestasBO().getDetalleXEmpleado()
                    .entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(empleadoSet);
        }

        return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>();
    }

    public void regresarGrupoFirmantes() {
        getHelper().setFlgMostrarPanelFirmante(true);
        getHelper().setFlgMostrarTlbPropuestas(false);
        getHelper().setFlgMostrarTlbCategorias(false);
        getHelper().setMuestraBtnRegresaFirmante(false);
        if (getConsultaPropuestasBO().isProgramacion()) {
            consultaXAdministrador();
        } else {
            consultarXFirmante();
        }
        limpiarFiltros();
    }

    public void consultaTotalPropuestasXEmpleado() {
        setLstPropuestaGeneral(getConsultaPropuestasBO().getLstPropuestasResult());

        if (getHelper().isFlgMostrarPanelFirmante()) {
            getHelper().setFlgMostrarPanelFirmante(false);
            getHelper().setMuestraBtnRegresaEstatus(false);
            getHelper().setMuestraBtnRegresaFirmante(true);
            getHelper().setFlgMostrarTlbCategorias(false);
        } else {
            getHelper().setFlgMostrarPanelAuditor(false);
            getHelper().setMuestraBtnRegresaEstatus(false);
            getHelper().setMuestraBtnRegresaAuditor(true);
            getHelper().setFlgMostrarTlbCategorias(false);
        }

        getHelper().setFlgMostrarTlbPropuestas(true);

    }

    public void mostrarPanelDetallePropuesta() {
        getHelper().setFlgMostrarTlbPropuestas(false);
        getHelper().setFlgMostrarPnlPropuesta(true);
    }

    public void regresarDeDetallePropuestas() {
        getHelper().setFlgMostrarTlbPropuestas(true);
        getHelper().setFlgMostrarPnlPropuesta(false);
        limpiarFiltros();
    }

    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:tablaDetallePropuestas");
            dataTable.reset();
            if (!dataTable.getFilters().isEmpty()) {
                logger.info("dataTable.getFilters().isEmpty() :" + dataTable.getFilters().isEmpty());
                dataTable.getFilteredValue().clear();
                dataTable.setFilteredValue(null);
                dataTable.setFilters(null);
                dataTable.setFilterMetadata(null);
                dataTable.reset();
            }
        } catch (Exception e) {
            logger.error("no pudo limpiar" + e.getMessage());
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.reset(":form:tablaDetallePropuestas");
    }

    public List<FecetPropuesta> getLstPropuestaGeneral() {
        return lstPropuestaGeneral;
    }

    public void setLstPropuestaGeneral(List<FecetPropuesta> lstPropuestaGeneral) {
        this.lstPropuestaGeneral = lstPropuestaGeneral;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public void regresaPanelFirmante() {
        getHelper().setFlgMostrarPanelAuditor(false);
        getHelper().setFlgMostrarPanelFirmante(true);
        if (getConsultaPropuestasBO().isProgramacion()) {
            consultaXAdministrador();
        } else {
            consultarXFirmante();
        }
    }

    public void regresaPanelAuditor() {
        getHelper().setFlgMostrarPanelAuditor(true);
        getHelper().setFlgMostrarPnlPropuesta(false);
        getHelper().setFlgMostrarTlbPropuestas(false);
        getHelper().setMuestraBtnRegresaAuditor(false);
        if (getConsultaPropuestasBO().isProgramacion()) {
            consultaXSubadministrador();
        } else {
            consultarXAuditor();
        }
        limpiarFiltros();
    }

    public StreamedContent getXlsReporte() {

        File file = null;
        HSSFWorkbook workbook = getConsultaEjecutivaPropuestasService().creaExcel(getConsultaPropuestasBO().getLstPropuestasResult());

        FileOutputStream out = null;

        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            setXlsReporte(new DefaultStreamedContent(new FileInputStream(file), "application/xls", "reporte.xls"));
            file.deleteOnExit();
        } catch (IOException e) {
            logger.error("Error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("No se pudo limpiar la memoria", e);
                }
            }
        }

        return xlsReporte;
    }

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }
}
