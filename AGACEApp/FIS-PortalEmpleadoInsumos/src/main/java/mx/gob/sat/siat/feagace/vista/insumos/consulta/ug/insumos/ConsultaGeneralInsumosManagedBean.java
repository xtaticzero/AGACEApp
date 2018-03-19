package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.helper.ItemCombo;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ConsultaGeneralManagedBean;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXClaveItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXDescripcionItemFiltro;

@ManagedBean(name = "consultaUGInsumosMB")
@ViewScoped
public class ConsultaGeneralInsumosManagedBean extends ConsultaGeneralInsumosAttributes {

    @PostConstruct
    @Override
    public void init() {
        super.init();
        try {
            setListaUnidadesAdministrativas(obtenerUnidadesAdministrativas());
            setListaUnidadesRegistro(obtenerUnidadesRegistro());
            getHelper().setEsVisiblePanelOpciones(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void valueChange(final AjaxBehaviorEvent event) {
        if (getTipoConsulta() == ConsultaGeneralManagedBean.UNO) {
            bloquearConsultaUnidadAdmon();
            getHelper().setUnidadRegistroHabilitado(true);
        } else {
            bloquearConsultaRegistrados();
            getHelper().setUnidadAdmonHabilitado(true);
        }
        getHelper().setBotonConsultarHabilitado(false);
    }

    public void valueChangePlazo(final AjaxBehaviorEvent event) {
        if (getPlazoSeleccionado() != ConsultaGeneralManagedBean.DOSNEG) {
            getHelper().setMuestraBtnRegresaEstatus(true);
        } else {
            getHelper().setMuestraBtnRegresaEstatus(false);
        }
    }

    public void cambiarUnidadRegistro(final AjaxBehaviorEvent event) {
        if (getUnidadRegistro().intValue() == ConsultaGeneralManagedBean.CERO) {
            getHelper().setUnidadAdmonRegistroHabilitado(false);
        } else {
            getHelper().setUnidadAdmonRegistroHabilitado(true);
        }
    }

    public void cambiarUnidadAdmonRegistro(final AjaxBehaviorEvent event) {
        if (getUnidadAdmonRegistro().intValue() == ConsultaGeneralManagedBean.CERO) {
            getHelper().setBotonConsultarHabilitado(false);
        } else {
            getHelper().setBotonConsultarHabilitado(true);
        }
    }

    public void obtenerResumen() {
        setInsumos(getConsultaGeneralInsumosService().obtenerDetalleInsumos(getUnidadRegistro(), getUnidadAdmonRegistro(), getTipoConsulta()));
        if (getTipoConsulta() == ConsultaGeneralManagedBean.UNO) {
            setResumen(getConsultaGeneralInsumosService().obtenerResumenInsumos(getInsumos()));
        } else {
            setResumen(getConsultaGeneralInsumosService().obtenerResumenInsumosAsignados(getInsumos()));
        }
        setResumenSemaforos(getConsultaGeneralInsumosService().obtenerResumenInsumosSemaforos(getInsumos()));
        obtenerTotalEstatus();
        obtenerTotalSemaforo();
        cambiaPanelEstatus();
    }

    public void consultarXPlazoSeleccionado() {
        setInsumosFiltradosxPlazo(getConsultaGeneralInsumosService().filtroPlazoParaConcluir(getInsumos(), getPlazoSeleccionado()));
        setInsumosDetalle(getInsumosFiltradosxPlazo());
        getHelper().setEsConsultaEmpleados(false);
        cambiaPanelDetalle();
    }

    public void obtenerResumenAdmon() {
        List<EmpleadoDTO> empleados = getConsultaGeneralInsumosService().obtenerAdmonXUnidadAdmon(getUnidadAdmonRegistro());
        setInsumosAdmon(getConsultaGeneralInsumosService().obtenerDetalleInsumosAdmon(empleados, getEstatusSeleccionados().getKey(), usuariosCreacion(), getTipoConsulta()));
        setResumenAdmon((getConsultaGeneralInsumosService().obtenerResumenInsumosAdmon(empleados, getInsumosAdmon())));
        setResumenSemaforosAdmon(getConsultaGeneralInsumosService().obtenerResumenInsumosSemaforos(getInsumosAdmon()));
        obtenerTotalAdmon();
        obtenerTotalSemaforoAdmon();
    }

    private String usuariosCreacion() {
        List<EmpleadoDTO> empleadoCreacion = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder rfcs = new StringBuilder();
        if (getUnidadRegistro() != null
                && !getUnidadAdmonRegistro().equals(new BigDecimal(-1))) {
            try {
                if (getUnidadRegistro().equals(new BigDecimal(TipoAraceEnum.ACIACE.getId()))) {
                    empleadoCreacion = getEmpleadoService().getEmpleadosXUnidadAdmva((int) TipoAraceEnum.ACIACE.getId(), (int) TipoEmpleadoEnum.USUARIO_INSUMOS.getId(), ClvSubModulosAgace.INSUMOS);
                }

                if (getUnidadRegistro().equals(new BigDecimal(TipoAraceEnum.ACPPCE.getId()))) {
                    empleadoCreacion = getEmpleadoService().getEmpleadosXUnidadAdmva((int) TipoAraceEnum.ACPPCE.getId(), (int) TipoEmpleadoEnum.USUARIO_INSUMOS.getId(), ClvSubModulosAgace.INSUMOS);
                }

            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }

            if (empleadoCreacion != null && !empleadoCreacion.isEmpty()) {

                sb.append(" AND INSUMO.RFC_CREACION IN ( ");
                for (EmpleadoDTO empleado : empleadoCreacion) {
                    rfcs.append("'");
                    rfcs.append(empleado.getRfc());
                    rfcs.append("'");
                    rfcs.append(",");
                }
                sb.append(rfcs.substring(0, rfcs.length() - 1));
                sb.append(") ");

            }
        }
        return sb.toString();
    }

    public void totalInsumoDetalleAdmon() {
        setInsumosDetalle(getInsumosAdmon());
        getHelper().setEsConsultaEmpleados(false);
        cambiaPanelDetalle();
    }

    public void totalInsumoDetalleSubAdmon() {
        setInsumosDetalle(getInsumosSubAdmon());
        getHelper().setEsConsultaEmpleados(false);
        cambiaPanelDetalle();
    }

    public void obtenerResumenSubAdmon() {
        if (getNumeroSemaforo() != UNO_NEGATIVO && getNumeroSemaforo() != DOS_NEGATIVO) {
            List<EmpleadoDTO> empleados = getConsultaGeneralInsumosService().obtenerSubAdmin(getEmpleadoSeleccionado().getKey());
            setInsumosSubAdmon(getConsultaGeneralInsumosService().obtenerDetalleInsumosSubAdmon(getEmpleadoSeleccionado().getKey(), empleados, getEstatusSeleccionados().getKey(), usuariosCreacion(), getTipoConsulta()));
            setResumenSubAdmon((getConsultaGeneralInsumosService().obtenerResumenInsumosSubAdmon(empleados, getInsumosSubAdmon())));
            setResumenSemaforosSubAdmon(getConsultaGeneralInsumosService().obtenerResumenInsumosSemaforos(getInsumosSubAdmon()));
            obtenerTotalSubAdmon();
            obtenerTotalSemaforoEstatusSubAdmon();
            cambiaPanelSubAdmon();
        } else {
            setInsumosDetalle(getInsumosSubAdmon());
            getHelper().setEsConsultaEmpleados(false);
            cambiaPanelDetalle();
        }
    }

    public void consultarXEstatus() {
        if (validarConsultaXEmpleado() || getNumeroSemaforo() == 0) {
            if (getEstatusSeleccionados() != null) {
                setInsumosDetalle(getConsultaGeneralInsumosService().filtrarXEstatus(getInsumos(), getEstatusSeleccionados().getKey()));
            } else {
                limpiarFiltros();
                setInsumosDetalle(getInsumos());
            }
            getHelper().setEsConsultaEmpleados(false);
            cambiaPanelDetalle();
        } else {
            getHelper().setEsConsultaEmpleados(true);
            obtenerResumenAdmon();
            cambiaPanelAdmon();
        }
    }

    public void detalleInsumosEmpleado() {
        setInsumosDetalle(getInsumosSubAdmon());
        cambiaPanelEmpleado();
    }

    public void detalleInsumosTotal() {
        getHelper().setEsVisiblePanelAdministrador(false);
        getHelper().setEsVisiblePanelCentral(false);
        getHelper().setEsVisiblePanelListaInsumos(true);
    }

    public boolean validarConsultaXEmpleado() {
        return getUnidadAdmonRegistro().intValue() == UNONEG;
    }

    public void consultarXSemaforo() {
        if (getSemaforoSeleccionado() != null) {
            if (getNumeroSemaforo() == CERO) {
                setInsumosDetalle(getConsultaGeneralInsumosService().filtrarXSemaforo(getInsumos(), getSemaforoSeleccionado().getKey()));
                getHelper().setEsConsultaEmpleados(false);
            }
            if (getNumeroSemaforo() == UNO) {
                setInsumosDetalle(getConsultaGeneralInsumosService().filtrarXSemaforo(getInsumosAdmon(), getSemaforoSeleccionado().getKey()));
                getHelper().setEsConsultaEmpleados(true);
            }
            if (getNumeroSemaforo() == DOS) {
                setInsumosDetalle(getConsultaGeneralInsumosService().filtrarXSemaforo(getInsumosSubAdmon(), getSemaforoSeleccionado().getKey()));
                getHelper().setEsConsultaEmpleados(true);
            }
        } else {
            setInsumosDetalle(getInsumos());
        }
        cambiaPanelDetalle();
    }

    public void cambiaPanelEstatus() {
        getHelper().setEsVisiblePanelOpciones(false);
        getHelper().setEsVisiblePanelEstatus(true);
    }

    public void cambiaPanelDetalle() {
        getHelper().setEsVisiblePanelEstatus(false);
        getHelper().setEsVisiblePanelListaInsumos(true);
        getHelper().setEsVisiblePanelCentral(false);
        getHelper().setEsVisiblePanelAdministrador(false);

    }

    public void totalSemaforoDetalleCentral() {
        setInsumosDetalle(getInsumos());
        cambiaPanelDetalle();
        getHelper().setEsConsultaEmpleados(false);
    }

    public void totalSemaforoDetalleAdmin() {
        setInsumosDetalle(getInsumosAdmon());
        cambiaPanelDetalle();
        getHelper().setEsConsultaEmpleados(true);
    }

    public void totalSemaforoDetalleSubAdmin() {
        setInsumosDetalle(getInsumosSubAdmon());
        cambiaPanelDetalle();
        getHelper().setEsConsultaEmpleados(true);
    }

    public void cambiaPanelEmpleado() {
        getHelper().setEsVisiblePanelAdministrador(false);
        getHelper().setEsVisiblePanelListaInsumos(true);
    }

    public void cambiaPanelAdmon() {
        getHelper().setEsVisiblePanelEstatus(false);
        getHelper().setEsVisiblePanelCentral(true);
    }

    public void cambiaPanelSubAdmon() {
        getHelper().setEsVisiblePanelCentral(false);
        getHelper().setEsVisiblePanelAdministrador(true);
    }

    public void inicializarElementos() {
        setInsumos(new ArrayList<FecetInsumo>());
        setInsumosFiltradosxPlazo(new ArrayList<FecetInsumo>());
        setTipoConsulta(CERO);
        setUnidadRegistro(BigDecimal.ZERO);
        setUnidadAdministrativa(BigDecimal.ZERO);
        setUnidadAdmonRegistro(BigDecimal.ZERO);
        setResumen(new HashMap<TipoEstatusEnum, Integer>());
        setResumenSemaforos(new HashMap<SemaforoEnum, Integer>());
        setTotalEstatus(CERO);
        setTotalSemaforo(CERO);
        getHelper().setUnidadRegistroHabilitado(false);
        getHelper().setUnidadAdmonHabilitado(false);
        getHelper().setUnidadAdmonRegistroHabilitado(false);
        getHelper().setBotonConsultarHabilitado(false);
        getHelper().setEsConsultaEmpleados(false);
    }

    public void pantallaAnteriorEstatus() {
        getHelper().setEsVisiblePanelOpciones(true);
        getHelper().setEsVisiblePanelEstatus(false);
        inicializarElementos();
    }

    public void pantallaAnteriorDetalle() {

        getHelper().setEsVisiblePanelEstatus(true);
        setInsumos(getInsumos());
        limpiarFiltros();

        if (getNumeroSemaforo() == -1 && !validarConsultaXEmpleado()) {
            getHelper().setEsVisiblePanelEstatus(false);
            getHelper().setEsVisiblePanelCentral(true);
            setInsumos(getInsumosAdmon());
            obtenerTotalAdmon();
            obtenerTotalSemaforoAdmon();

        }

        if ((getNumeroSemaforo() == DOS_NEGATIVO || getNumeroSemaforo() == DOS) && !validarConsultaXEmpleado()) {
            getHelper().setEsVisiblePanelEstatus(false);
            getHelper().setEsVisiblePanelAdministrador(true);
            setInsumos(getInsumosSubAdmon());
            obtenerTotalSubAdmon();
            obtenerTotalSemaforoEstatusSubAdmon();
        }
        getHelper().setEsVisiblePanelListaInsumos(false);
    }

    public void pantallaAnteriorDetalleEmpleado() {
        getHelper().setEsVisiblePanelAdministrador(true);
        getHelper().setEsVisiblePanelListaInsumos(false);
        limpiarFiltros();
        if (getNumeroSemaforo() == 1) {
            pantallaAnteriorAdministrador();
        }

    }

    public void pantallaAnteriorCentral() {
        getHelper().setEsVisiblePanelEstatus(true);
        getHelper().setEsVisiblePanelCentral(false);
        obtenerResumen();
    }

    public void pantallaAnteriorAdministrador() {
        getHelper().setEsVisiblePanelAdministrador(false);
        getHelper().setEsVisiblePanelCentral(true);
        setInsumos(getInsumosAdmon());
        obtenerTotalAdmon();
        obtenerTotalSemaforoAdmon();
    }

    public void pantallaAnteriorLista() {
        getHelper().setEsVisiblePanelDetalleInsumos(false);
        getHelper().setEsVisiblePanelListaInsumos(true);
    }

    public void bloquearConsultaRegistrados() {
        getHelper().setUnidadAdmonRegistroHabilitado(false);
        getHelper().setUnidadRegistroHabilitado(false);
    }

    public void consultarDetalleInsumo() {
        getConsultaGeneralInsumosService().consultaHistoricoInsumos(getInsumosSeleccionado());
        getHelper().setEsVisiblePanelDetalleInsumos(true);
        getHelper().setEsVisiblePanelListaInsumos(false);
    }

    public void seleccionarRetroalimentacion() {
        if (getRetroalimentacionSeleccionada() != null) {
            logger.error("no es nulla la retro");
        }
    }

    public void seleccionarRechazo() {
        if (getRechazoSeleccionado() != null) {
            logger.error("no es nulla la retro");
        }
    }

    public void consultaHistoricoInsumo() {
        try {
            if (getInsumosSeleccionado().getRechazoInsumo() != null) {
                setLstRechazo(new ArrayList<FecetRechazoInsumo>());
                getLstRechazo().add(getInsumosSeleccionado().getRechazoInsumo());
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dlgInsumosRetroalimentados').show();");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void bloquearConsultaUnidadAdmon() {
        getHelper().setUnidadAdmonHabilitado(false);
    }

    public void obtenerTotalEstatus() {
        int total = 0;
        if (getResumen() != null && !this.getResumen().isEmpty()) {
            for (Entry<TipoEstatusEnum, Integer> entry : getResumen().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalEstatus(total);
    }

    public void obtenerTotalAdmon() {
        int total = 0;
        if (getResumenAdmon() != null && !this.getResumenAdmon().isEmpty()) {
            for (Entry<EmpleadoDTO, Integer> entry : getResumenAdmon().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalEstatus(total);
    }

    public void obtenerTotalSubAdmon() {
        int total = 0;
        if (getResumenSubAdmon() != null && !this.getResumenSubAdmon().isEmpty()) {
            for (Entry<EmpleadoDTO, Integer> entry : getResumenSubAdmon().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalEstatus(total);
    }

    public void obtenerTotalSemaforo() {
        int total = 0;
        if (getResumenSemaforos() != null && !getResumenSemaforos().isEmpty()) {
            for (Entry<SemaforoEnum, Integer> entry : getResumenSemaforos().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalSemaforo(total);
    }

    public void obtenerTotalSemaforoAdmon() {
        int total = 0;

        if (getResumenSemaforosAdmon() != null && !getResumenSemaforosAdmon().isEmpty()) {
            for (Entry<SemaforoEnum, Integer> entry : getResumenSemaforosAdmon().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalSemaforo(total);
    }

    public void obtenerTotalSemaforoEstatusSubAdmon() {
        int total = 0;

        if (getResumenSemaforosSubAdmon() != null && !getResumenSemaforosSubAdmon().isEmpty()) {
            for (Entry<SemaforoEnum, Integer> entry : getResumenSemaforosSubAdmon().entrySet()) {
                total = total + (entry.getValue() != null ? entry.getValue() : 0);
            }
        }
        setTotalSemaforo(total);
    }

    public List<Map.Entry<TipoEstatusEnum, Integer>> getResumenSet() {
        if (getResumen() != null) {
            Set<Map.Entry<TipoEstatusEnum, Integer>> productSet
                    = getResumen().entrySet();
            return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>(productSet);
        } else {
            Map<TipoEstatusEnum, Integer> vacio = new HashMap<TipoEstatusEnum, Integer>();
            Set<Map.Entry<TipoEstatusEnum, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>(productSet);
        }
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getResumenSemaforoSet() {
        if (getResumenSemaforos() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> productSet
                    = getResumenSemaforos().entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        } else {
            Map<SemaforoEnum, Integer> vacio = new HashMap<SemaforoEnum, Integer>();
            Set<Map.Entry<SemaforoEnum, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        }
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getResumenAdmonSet() {
        if (getResumenAdmon() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> productSet
                    = getResumenAdmon().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(productSet);
        } else {
            Map<EmpleadoDTO, Integer> vacio = new HashMap<EmpleadoDTO, Integer>();
            Set<Map.Entry<EmpleadoDTO, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(productSet);
        }
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getResumenSemaforoAdmonSet() {
        if (getResumenSemaforosAdmon() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> productSet
                    = getResumenSemaforosAdmon().entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        } else {
            Map<SemaforoEnum, Integer> vacio = new HashMap<SemaforoEnum, Integer>();
            Set<Map.Entry<SemaforoEnum, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        }
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getResumenSubAdmonSet() {
        if (getResumenSubAdmon() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> productSet
                    = getResumenSubAdmon().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(productSet);
        } else {
            Map<EmpleadoDTO, Integer> vacio = new HashMap<EmpleadoDTO, Integer>();
            Set<Map.Entry<EmpleadoDTO, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(productSet);
        }
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getResumenSemaforoSubAdmonSet() {
        if (getResumenSemaforosSubAdmon() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> productSet
                    = getResumenSemaforosSubAdmon().entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        } else {
            Map<SemaforoEnum, Integer> vacio = new HashMap<SemaforoEnum, Integer>();
            Set<Map.Entry<SemaforoEnum, Integer>> productSet = vacio.entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(productSet);
        }
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        int registro = 1;
        if (getInsumosDetalle() != null && !getInsumosDetalle().isEmpty()) {
            for (FecetInsumo insumo : getInsumosDetalle()) {
                HSSFRow row = sheet.getRow(registro);
                if (row != null) {
                    row.getCell(Constantes.ENTERO_SEIS).setCellValue(insumo.getDescripcionPlazoRestante());
                    row.getCell(Constantes.ENTERO_DIEZ).setCellValue(insumo.getDescripcionSemaforo());
                }
                registro++;
            }
        }

    }

    public Set<ItemCombo> getLstValoresFiltroPlazos() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());
        if (isListValid(getInsumosDetalle())) {
            for (FecetInsumo insumo : getInsumosDetalle()) {
                ItemCombo item = new ItemCombo();
                int plazo = insumo.getPlazoRestante();
                item.setValor(plazo);
                switch (plazo) {
                    case PLAZO_INICIAL:
                        item.setDescripcion("Sin atender");
                        break;
                    case 0:
                        item.setDescripcion("Plazo vencido");
                        break;
                    case UNO:
                        item.setDescripcion(plazo + DESCRIPCION_PLAZO);
                        break;
                    default:
                        item.setDescripcion(plazo + DESCRIPCION_PLAZO + "s");
                        break;
                }
                lstFiltro.add(item);
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroPrioridad() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());
        if (isListValid(getInsumosDetalle())) {
            for (FecetInsumo insumo : getInsumosDetalle()) {
                lstFiltro.add(new ItemCombo(insumo.getPrioridadDto().getValor(), insumo.getPrioridadDto().getIdPrioridad().intValue()));
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroEstatus() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());
        if (isListValid(getInsumosDetalle())) {
            for (FecetInsumo insumo : getInsumosDetalle()) {
                lstFiltro.add(new ItemCombo(insumo.getFececEstatus().getDescripcion(), (insumo.getFececEstatus().getIdEstatus().intValue())));
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroUnidadAdministrativa() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());
        if (isListValid(getInsumosDetalle())) {
            for (FecetInsumo insumo : getInsumosDetalle()) {
                lstFiltro.add(new ItemCombo(insumo.getFececUnidadAdministrativa().getNombre(), insumo.getFececUnidadAdministrativa().getIdUnidadAdministrativa().intValue()));
            }
        }
        return lstFiltro;
    }

    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:tablaDetalleInsumo");
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
        requestContext.reset(":form:tablaDetalleInsumo");
        requestContext.update(":form");
    }

}
