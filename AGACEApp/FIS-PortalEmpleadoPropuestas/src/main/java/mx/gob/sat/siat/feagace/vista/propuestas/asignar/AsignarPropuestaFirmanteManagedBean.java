/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.asignar;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasCatalogoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultaAsignacionPropuestaService;
import mx.gob.sat.siat.feagace.vista.helper.AsignarPropuestaFirmanteHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.asignar.helper.AsignarPropuestaAttributoHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.asignar.helper.AsignarPropuestaListaHelper;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean(name = "asignarPropuestaFirmanteMB")
public class AsignarPropuestaFirmanteManagedBean extends BaseManagedBean {

    private static final long serialVersionUID = 5015645008156247523L;
    private static final String TITULO_PERIODO = "Periodo";
    private static final String TITULO_PERIODO_INICIAL = "Inicio";
    private static final String TITULO_PERIODO_FINAL = "Fin";
    private static final String MENSAJE_VALIDA_BUSQUEDA = "msgValidaBusqueda";

    @ManagedProperty(value = "#{consultaAsignacionPropuestaService}")
    private transient ConsultaAsignacionPropuestaService consultaAsignacionPropuestaService;
    @ManagedProperty(value = "#{asignarPropuestaFirmanteHelper}")
    private transient AsignarPropuestaFirmanteHelper asignarPropuestaFirmanteHelper;
    @ManagedProperty(value = "#{propuestasCatalogoService}")
    private transient PropuestasCatalogoService propuestasCatalogoService;

    private String tituloFechas = TITULO_PERIODO;
    private String tituloFechaInicial = TITULO_PERIODO_INICIAL;
    private String tituloFechafinal = TITULO_PERIODO_FINAL;
    private AsignarPropuestaListaHelper asignarListaHelper;
    private AsignarPropuestaAttributoHelper asignarAttributoHelper;

    public List<BigDecimal> listaIdAraceRegional() {
        List<BigDecimal> listaAdaceRegional = new ArrayList<BigDecimal>();
        listaAdaceRegional.add(Constantes.ARACE_PACIFICO_NORTE);
        listaAdaceRegional.add(Constantes.ARACE_NORTE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_NORESTE);
        listaAdaceRegional.add(Constantes.ARACE_OCCIDENTE);
        listaAdaceRegional.add(Constantes.ARACE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_SUR);

        return listaAdaceRegional;
    }

    @PostConstruct
    public void init() {
        try {
            setAsignarListaHelper(new AsignarPropuestaListaHelper());
            setAsignarAttributoHelper(new AsignarPropuestaAttributoHelper());
            getAsignarListaHelper().setListaPropuestasAsignadas(new ArrayList<PropuestaOrigenCentralRegDTO>());
            getAsignarListaHelper().setListaPropuestasAsignadasPrior3(new ArrayList<PropuestaOrigenCentralRegDTO>());
            getAsignarListaHelper().setListaPropuestasPorAsignar(new ArrayList<PropuestaOrigenCentralRegDTO>());
            getAsignarListaHelper().setListaPropuestasPrior3(new ArrayList<PropuestaOrigenCentralRegDTO>());
            getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
            getAsignarAttributoHelper().setMuestraPropuestas(true);
            getAsignarAttributoHelper().setEsPrioridad3(false);
            getAsignarListaHelper().setListaFirmantes(new ArrayList<FececFirmante>());
            getAsignarAttributoHelper().setRfcFirmanteSeleccionado(null);
            getAsignarAttributoHelper().setRfcFirmanteSeleccionadoPrior3(null);
            getAsignarAttributoHelper().setPropuestaSeleccionada(null);
            getAsignarListaHelper().setFirmantesEmpleado(new ArrayList<EmpleadoDTO>());
            getAsignarAttributoHelper().setFirmanteSeleccionado(new EmpleadoDTO());
            limpiaFiltros("formPropuestas:tablaPropuestasAsignar");
            getAsignarAttributoHelper().setEmpleado(consultaAsignacionPropuestaService.traeDatosCentral(getRFCSession()));
            cargaFirmante();
            cargaPrioridad();
            cargaPropuestaAsignarFirmante();
            cargaContadorPropuestas();
            cargaCatalogos();
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        }

    }

    public void validaSeleccionFirmante() {
        if (getAsignarListaHelper().getListaPropuestasAsignadas() != null
                && !getAsignarListaHelper().getListaPropuestasAsignadas().isEmpty()) {
            if (!getAsignarAttributoHelper().getRfcFirmanteSeleccionado().equals("-1")) {
                getAsignarAttributoHelper().setMuestraDialogoAsignar(true);
                getAsignarAttributoHelper().setEsPrioridad3(false);
                getAsignarAttributoHelper().setFirmanteSeleccionado(asignarPropuestaFirmanteHelper.obtenerFirmante(
                        getAsignarAttributoHelper().getRfcFirmanteSeleccionado(),
                        getAsignarListaHelper().getFirmantesEmpleado()));
                getAsignarAttributoHelper().setMensaje1(asignarPropuestaFirmanteHelper.crearMensaje1(
                        getAsignarListaHelper().getListaPropuestasAsignadas().size(),
                        getAsignarAttributoHelper().getFirmanteSeleccionado().getNombre()));
                getAsignarAttributoHelper().setMensaje2(asignarPropuestaFirmanteHelper.crearMensaje2(
                        getAsignarListaHelper().getListaPropuestasAsignadas().size(),
                        getAsignarAttributoHelper().getFirmanteSeleccionado().getNombre()));
                RequestContext.getCurrentInstance().execute("PF('confirmarAsignarPropuesta').show();");
                return;
            } else {
                getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
                addErrorMessage("msgExitoGuarar", Constantes.VERIFICAR, "Se debe seleccionar un Firmante");
            }
        } else {
            getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
            addErrorMessage("msgExitoGuardar", Constantes.VERIFICAR, "Se debe seleccionar una propuesta");
        }
    }

    public void validaSeleccionFirmantePrior3() {
        if (getAsignarListaHelper().getListaPropuestasAsignadasPrior3() != null
                && !getAsignarListaHelper().getListaPropuestasAsignadasPrior3().isEmpty()) {
            if (!getAsignarAttributoHelper().getRfcFirmanteSeleccionadoPrior3().equals("-1")) {
                getAsignarAttributoHelper().setMuestraDialogoAsignar(true);
                getAsignarAttributoHelper().setEsPrioridad3(true);
                getAsignarAttributoHelper().setFirmanteSeleccionado(asignarPropuestaFirmanteHelper.obtenerFirmante(
                        getAsignarAttributoHelper().getRfcFirmanteSeleccionadoPrior3(),
                        getAsignarListaHelper().getFirmantesEmpleado()));
                getAsignarAttributoHelper().setMensaje1(asignarPropuestaFirmanteHelper.crearMensaje1(
                        getAsignarListaHelper().getListaPropuestasAsignadasPrior3().size(),
                        getAsignarAttributoHelper().getFirmanteSeleccionado().getNombre()));
                getAsignarAttributoHelper().setMensaje2(asignarPropuestaFirmanteHelper.crearMensaje2(
                        getAsignarListaHelper().getListaPropuestasAsignadasPrior3().size(),
                        getAsignarAttributoHelper().getFirmanteSeleccionado().getNombre()));
                RequestContext.getCurrentInstance().execute("PF('confirmarAsignarPropuesta').show();");
                return;
            } else {
                getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
                addErrorMessage(MENSAJE_VALIDA_BUSQUEDA, Constantes.VERIFICAR,
                        "Se debe seleccionar un Firmante");
            }
        } else {
            getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
            addErrorMessage(MENSAJE_VALIDA_BUSQUEDA, Constantes.VERIFICAR,
                    "Se debe seleccionar una propuesta");

        }
    }

    public void asignarFirmante() {
        if (!getAsignarAttributoHelper().getEsPrioridad3()) {
            asignaFirmante(getAsignarListaHelper().getListaPropuestasAsignadas(),
                    getAsignarAttributoHelper().getRfcFirmanteSeleccionado());
        } else {
            asignaFirmante(getAsignarListaHelper().getListaPropuestasAsignadasPrior3(),
                    getAsignarAttributoHelper().getRfcFirmanteSeleccionadoPrior3());
        }

        getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
    }

    private void asignaFirmante(List<PropuestaOrigenCentralRegDTO> listaPropuestasAsignar, String rfcFirmante) {

        for (PropuestaOrigenCentralRegDTO propuestasSeleccionadas : listaPropuestasAsignar) {
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(propuestasSeleccionadas.getPropuesta().getIdPropuesta());
            propuestasSeleccionadas.getPropuesta().setIdArace(new BigDecimal(
                    getAsignarAttributoHelper().getEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace()));
            propuesta.setRfcFirmante(rfcFirmante);
            FececEstatus fececEstatus = new FececEstatus();
            fececEstatus.setIdEstatus(BigDecimal.valueOf(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_FIRMANTE.getId()));
            propuesta.setFececEstatus(fececEstatus);
            consultaAsignacionPropuestaService.guardarAsignacionFirmante(propuesta);
            consultaAsignacionPropuestaService.enviarCorreoConfirmacion(propuestasSeleccionadas,
                    LeyendasPropuestasEnum.PROPUESTA_ASIGNADA_VALIDACION.getIdLeyenda(),
                    getAsignarAttributoHelper().getEmpleado().getRfc(), propuesta.getRfcFirmante());
        }
        RequestContext.getCurrentInstance().execute("PF('msg2').show();");
    }

    private void cargaPropuestaAsignarFirmante() {
        getAsignarListaHelper().setListaPropuestasPorAsignar(consultaAsignacionPropuestaService
                .getPropuestasAsignarFirmante(new BigDecimal(getAsignarAttributoHelper().getEmpleado()
                                .getDetalleEmpleado().get(0).getCentral().getIdArace()), getAsignarListaHelper().getLstPrioridadGrid(), true));
    }

    private void cargaFirmante() {
        /**
         * ID_ADMINISTRADOR UUSA640409EG5 (ADMINISTRADOR)*
         */

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subor = getAsignarAttributoHelper()
                .getEmpleado().getSubordinados();

        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> suborFirma = subor.get(TipoEmpleadoEnum.CENTRAL);
        getAsignarListaHelper().setFirmantesEmpleado(suborFirma.get(TipoEmpleadoEnum.FIRMANTE));
    }

    private void cargaContadorPropuestas() {

        /**
         * ID_ADMINISTRADOR UUSA640409EG5 (ADMINISTRADOR)
         */
        /**
         * this.listaContadorPropuestas =
         * consultaAsignacionPropuestaService.getContadorPropuestas(BigDecimal.
         * valueOf(97L));
         */
        getAsignarListaHelper().setListaContadorPropuestas(consultaAsignacionPropuestaService.getContadorPropuestas(
                getAsignarListaHelper().getFirmantesEmpleado(), new BigDecimal(getAsignarAttributoHelper().getEmpleado()
                        .getDetalleEmpleado().get(0).getCentral().getIdArace())));

    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public boolean filtraFecha(Object value, Object filter, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return sdf.format(value).contains(String.valueOf(filterText));
    }

    private void cargaCatalogos() {

        getAsignarListaHelper().setListaMetodos(propuestasCatalogoService.obtenListaMetodos());
        getAsignarListaHelper().setListaTipoPropuestas(propuestasCatalogoService.obtenListaTipoPropuestas());
        getAsignarListaHelper().setListaTipoRevision(propuestasCatalogoService.obtenListaTipoRevision());
        getAsignarListaHelper().setListaSubprograma(propuestasCatalogoService.obtenListaSubprograma());
        getAsignarListaHelper().setListaFechasComiteEnum(obtenerListaFechasComiteSegunUsuario());

    }

    private List<TipoFechasComiteEnum> obtenerListaFechasComiteSegunUsuario() {
        List<TipoFechasComiteEnum> listaTipoFechasComite = new ArrayList<TipoFechasComiteEnum>();
        if (listaIdAraceRegional().contains(new BigDecimal(
                getAsignarAttributoHelper().getEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace()))) {
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL);
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL);
        } else {
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_INFORME_COMITE);
            listaTipoFechasComite.add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE);
        }
        return listaTipoFechasComite;
    }

    public void evaluaTipoMetodo() {
        String metodo = getAsignarAttributoHelper().getIdMetodo();
        if (metodo.trim().equalsIgnoreCase("2")) {
            getAsignarAttributoHelper().setHabilitaTipoRevision(true);
        } else {
            getAsignarAttributoHelper().setHabilitaTipoRevision(false);
        }
    }

    public void cambiaTitulos() {
        String eventValue = getAsignarAttributoHelper().getIdTipoPropuesta();
        if (eventValue.trim().equalsIgnoreCase("-1")) {
            setTituloFechas(TITULO_PERIODO);
            setTituloFechaInicial(TITULO_PERIODO_INICIAL);
            setTituloFechafinal(TITULO_PERIODO_FINAL);
        } else if (eventValue.trim().equalsIgnoreCase("1")) {
            setTituloFechas("Pedimento");
            setTituloFechaInicial("Fecha primer pedimento");
            setTituloFechafinal("Fecha último pedimento");
        } else if (eventValue.trim().equalsIgnoreCase("2")) {
            setTituloFechas("Periodo propuesto");
            setTituloFechaInicial(TITULO_PERIODO_INICIAL);
            setTituloFechafinal(TITULO_PERIODO_FINAL);
        }
    }

    public void buscarPropuestaPrior3() {

        // Validamos que las variables no lleven valores por default para la
        // consulta
        if (getAsignarAttributoHelper().getPresuntivaMenor() != null
                && (getAsignarAttributoHelper().getPresuntivaMenor().compareTo(BigDecimal.ZERO) <= 0)) {
            getAsignarAttributoHelper().setPresuntivaMenor(null);
        }
        if (getAsignarAttributoHelper().getPresuntivaMayor() != null
                && (getAsignarAttributoHelper().getPresuntivaMayor().compareTo(BigDecimal.ZERO) <= 0)) {
            getAsignarAttributoHelper().setPresuntivaMayor(null);
        }

        if ((getAsignarAttributoHelper().getPresuntivaMayor() != null
                && getAsignarAttributoHelper().getPresuntivaMayor().compareTo(BigDecimal.ZERO) >= 0)
                && (getAsignarAttributoHelper().getPresuntivaMenor() == null)) {
            getAsignarAttributoHelper().setPresuntivaMenor(BigDecimal.ZERO);
        }
        if (getAsignarAttributoHelper().getIdMetodo() != null
                && getAsignarAttributoHelper().getIdMetodo().equalsIgnoreCase("-1")) {
            getAsignarAttributoHelper().setIdMetodo(null);
        }
        if (getAsignarAttributoHelper().getIdTipoPropuesta() != null
                && getAsignarAttributoHelper().getIdTipoPropuesta().equalsIgnoreCase("-1")) {
            getAsignarAttributoHelper().setIdTipoPropuesta(null);
        }
        if (getAsignarAttributoHelper().getIdTipoRevision() != null
                && getAsignarAttributoHelper().getIdTipoRevision().equalsIgnoreCase("-1")) {
            getAsignarAttributoHelper().setIdTipoRevision(null);
        }
        if (getAsignarAttributoHelper().getIdSubprograma() != null
                && getAsignarAttributoHelper().getIdSubprograma().equalsIgnoreCase("-1")) {
            getAsignarAttributoHelper().setIdSubprograma(null);
        }

        getAsignarListaHelper()
                .setListaPropuestasPrior3(consultaAsignacionPropuestaService.getPropuestasAsignarFirmante(
                                new BigDecimal(getAsignarAttributoHelper().getEmpleado().getDetalleEmpleado().get(0)
                                        .getCentral().getIdArace()), getAsignarListaHelper().getLstPrioridadFiltros(), false,
                                getAsignarAttributoHelper().getFechaPrimerPedimento(),
                                getAsignarAttributoHelper().getFechaUltimoPedimento(),
                                getAsignarAttributoHelper().getIdMetodo(), getAsignarAttributoHelper().getIdTipoPropuesta(),
                                getAsignarAttributoHelper().getIdTipoRevision(),
                                getAsignarAttributoHelper().getIdSubprograma()));

        if ((getAsignarAttributoHelper().getPresuntivaMayor() != null
                && getAsignarAttributoHelper().getPresuntivaMenor() != null)
                && (getAsignarAttributoHelper().getPresuntivaMayor().compareTo(BigDecimal.ZERO) == 1
                && getAsignarAttributoHelper().getPresuntivaMenor().compareTo(BigDecimal.ZERO) == 1)) {
            ArrayList<PropuestaOrigenCentralRegDTO> listaPropuestas = new ArrayList<PropuestaOrigenCentralRegDTO>();
            for (PropuestaOrigenCentralRegDTO propuesta : getAsignarListaHelper().getListaPropuestasPrior3()) {
                if (((getAsignarAttributoHelper().getPresuntivaMenor()
                        .compareTo(propuesta.getPropuesta().getPresuntiva()) == -1)
                        || (getAsignarAttributoHelper().getPresuntivaMenor()
                        .compareTo(propuesta.getPropuesta().getPresuntiva()) == 0))
                        && (getAsignarAttributoHelper().getPresuntivaMayor()
                        .compareTo(propuesta.getPropuesta().getPresuntiva()) == 1)
                        || (getAsignarAttributoHelper().getPresuntivaMayor()
                        .compareTo(propuesta.getPropuesta().getPresuntiva()) == 0)) {
                    listaPropuestas.add(propuesta);
                }
            }
            getAsignarListaHelper().getListaPropuestasPrior3().clear();
            getAsignarListaHelper().getListaPropuestasPrior3().addAll(listaPropuestas);
        }

        if (getAsignarAttributoHelper().getFechaPrimerPedimento() != null
                && getAsignarAttributoHelper().getFechaUltimoPedimento() != null) {
            Calendar hoy = Calendar.getInstance();
            Date dateHoy = hoy.getTime();
            if (getAsignarAttributoHelper().getFechaUltimoPedimento()
                    .before(getAsignarAttributoHelper().getFechaPrimerPedimento())) {
                addErrorMessage(MENSAJE_VALIDA_BUSQUEDA, Constantes.VERIFICAR,
                        "La fecha de Fin no puede ser menor que la de Inicio.");
            }
            if (getAsignarAttributoHelper().getFechaUltimoPedimento().after(dateHoy)
                    || getAsignarAttributoHelper().getFechaPrimerPedimento().after(dateHoy)) {
                addErrorMessage(MENSAJE_VALIDA_BUSQUEDA, Constantes.VERIFICAR,
                        "Las Fechas de búsqueda no pueden ser posteriores al día de hoy.");
            }
        }

        if ((getAsignarAttributoHelper().getPresuntivaMayor() != null
                && getAsignarAttributoHelper().getPresuntivaMenor() != null)
                && (getAsignarAttributoHelper().getPresuntivaMayor()
                .compareTo(getAsignarAttributoHelper().getPresuntivaMenor()) == -1)) {
            addErrorMessage(MENSAJE_VALIDA_BUSQUEDA, Constantes.VERIFICAR,
                    "La Presuntiva Mayor NO puede ser menor que la Presuntiva Menor");
        }
    }

    public void limpiarBusqueda() {
        getAsignarAttributoHelper().setFechaPrimerPedimento(null);
        getAsignarAttributoHelper().setFechaUltimoPedimento(null);
        getAsignarAttributoHelper().setPresuntivaMayor(null);
        getAsignarAttributoHelper().setPresuntivaMenor(null);
        getAsignarAttributoHelper().setIdMetodo(Constantes.COMBO_SELECCIONA_CADENA);
        getAsignarAttributoHelper().setIdTipoPropuesta(Constantes.COMBO_SELECCIONA_CADENA);
        getAsignarAttributoHelper().setIdTipoRevision(Constantes.COMBO_SELECCIONA_CADENA);
        getAsignarAttributoHelper().setIdSubprograma(Constantes.COMBO_SELECCIONA_CADENA);
        getAsignarAttributoHelper().setHabilitaTipoRevision(false);
        setTituloFechas(TITULO_PERIODO);
        setTituloFechaInicial(TITULO_PERIODO_INICIAL);
        setTituloFechafinal(TITULO_PERIODO_FINAL);
        getAsignarAttributoHelper().setMuestraDialogoAsignar(false);
        getAsignarListaHelper().setListaPropuestasPrior3(new ArrayList<PropuestaOrigenCentralRegDTO>());
    }

    private void limpiaFiltros(final String componente) {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(componente);
            dataTable.setFilteredValue(null);
            dataTable.reset();
        } catch (Exception e) {
            logger.error("No existe el componente: [{}]", componente);
        }

    }

    public void cargaPrioridad() {
        getAsignarListaHelper().setLstPrioridadGrid(consultaAsignacionPropuestaService.traePrioridadGrid());
        getAsignarListaHelper().setLstPrioridadFiltros(consultaAsignacionPropuestaService.traePrioridadFiltro());
        getAsignarAttributoHelper().setPrioridadGrid(asignarPropuestaFirmanteHelper.crearStringPrioridad(getAsignarListaHelper().getLstPrioridadGrid()));
        getAsignarAttributoHelper().setPrioridadFiltros(asignarPropuestaFirmanteHelper.crearStringPrioridad(getAsignarListaHelper().getLstPrioridadFiltros()));
    }

    public void setConsultaAsignacionPropuestaService(ConsultaAsignacionPropuestaService consultaAsignacionPropuestaService) {
        this.consultaAsignacionPropuestaService = consultaAsignacionPropuestaService;
    }

    public ConsultaAsignacionPropuestaService getConsultaAsignacionPropuestaService() {
        return consultaAsignacionPropuestaService;
    }

    public void setAsignarPropuestaFirmanteHelper(AsignarPropuestaFirmanteHelper asignarPropuestaFirmanteHelper) {
        this.asignarPropuestaFirmanteHelper = asignarPropuestaFirmanteHelper;
    }

    public AsignarPropuestaFirmanteHelper getAsignarPropuestaFirmanteHelper() {
        return asignarPropuestaFirmanteHelper;
    }

    public PropuestasCatalogoService getPropuestasCatalogoService() {
        return propuestasCatalogoService;
    }

    public void setPropuestasCatalogoService(PropuestasCatalogoService propuestasCatalogoService) {
        this.propuestasCatalogoService = propuestasCatalogoService;
    }

    public String getTituloFechas() {
        return tituloFechas;
    }

    public void setTituloFechas(String tituloFechas) {
        this.tituloFechas = tituloFechas;
    }

    public String getTituloFechaInicial() {
        return tituloFechaInicial;
    }

    public void setTituloFechaInicial(String tituloFechaInicial) {
        this.tituloFechaInicial = tituloFechaInicial;
    }

    public String getTituloFechafinal() {
        return tituloFechafinal;
    }

    public void setTituloFechafinal(String tituloFechafinal) {
        this.tituloFechafinal = tituloFechafinal;
    }

    public AsignarPropuestaListaHelper getAsignarListaHelper() {
        return asignarListaHelper;
    }

    public void setAsignarListaHelper(AsignarPropuestaListaHelper asignarListaHelper) {
        this.asignarListaHelper = asignarListaHelper;
    }

    public AsignarPropuestaAttributoHelper getAsignarAttributoHelper() {
        return asignarAttributoHelper;
    }

    public void setAsignarAttributoHelper(AsignarPropuestaAttributoHelper asignarAttributoHelper) {
        this.asignarAttributoHelper = asignarAttributoHelper;
    }

}
