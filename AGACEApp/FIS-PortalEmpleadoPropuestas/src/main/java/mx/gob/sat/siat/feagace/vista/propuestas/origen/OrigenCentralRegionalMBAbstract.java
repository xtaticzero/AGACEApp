package mx.gob.sat.siat.feagace.vista.propuestas.origen;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.CAMPO_OBLIGATORIO_IMPUESTO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.OrigenCentralRegionalServ;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;
import mx.gob.sat.siat.feagace.vista.propuestas.origen.helper.OrigenCentralHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.origen.helper.OrigenCentralListaHelper;

public class OrigenCentralRegionalMBAbstract extends AbstractManagedBean {

    private static final long serialVersionUID = 812326991014102894L;

    @ManagedProperty(value = "#{origenCentralRegionalService}")
    private transient OrigenCentralRegionalServ origenCentralRegionalServ;
    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;
    @ManagedProperty(value = "#{validarRetroalimentarPropuestaService}")
    private transient ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;

    private OrigenCentralListaHelper listaHelper;
    private OrigenCentralHelper atrributeHelper;
    private boolean activarImpuesto;
    private boolean activarMontoImpuesto;
    private boolean complementado;
    private boolean accion;
    private boolean informe;
    private boolean presenta;
    private boolean tipoPropuesta;

    @PostConstruct
    public void init() {
        setListaHelper(new OrigenCentralListaHelper());
        setAtrributeHelper(new OrigenCentralHelper());
        getAtrributeHelper().setFechaActualPeriodo(new Date());
        getAtrributeHelper().setFechaInformeComReg(null);
        getAtrributeHelper().setFechaPresentacionComReg(null);
        getListaHelper().setCentralRegDTOs(new ArrayList<PropuestaOrigenCentralRegDTO>());
        getListaHelper().setToRemoveImpuesto(new ArrayList<FecetImpuesto>());
        getListaHelper().setListaMetodo(new ArrayList<String>());
        setActivarImpuesto(false);
        getListaHelper().setListaUnidadAdministrativa(registrarPropuestaIndividualService.getCatalogoUnidad());
        try {

            getAtrributeHelper().setEmpleadoDTO(getEmpleadoService().getEmpleadoCompleto(getRFCSession()));
            getListaHelper()
                    .setCentralRegDTOs(obtieneArace(origenCentralRegionalServ.getPropuestasOrigenCentralRegionales(
                                            getAtrributeHelper().getEmpleadoDTO().getIdEmpleado(), new BigDecimal(getAtrributeHelper()
                                                    .getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()))));
            if (getListaHelper().getCentralRegDTOs() != null && !getListaHelper().getCentralRegDTOs().isEmpty()) {
                for (PropuestaOrigenCentralRegDTO dto : getListaHelper().getCentralRegDTOs()) {
                    if (!getListaHelper().getListaMetodo().contains(dto.getMetodo().getAbreviatura())) {
                        getListaHelper().getListaMetodo().add(dto.getMetodo().getAbreviatura());
                    }
                }
            }
            listaUnidadAdministrativaRegional();
            obtenerListaFechasComiteSegunUsuario();
        } catch (NegocioException e) {
            addErrorMessage(null, "No se pudieron cargar las propuestas.", e.getCause().toString());
            logger.error("Error al obtener los datos" + e);
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
            informeErrorSession(e);
        }
    }

    public void cambiaComplemento() {
        setComplementado(true);
    }

    public void limpiarFechas() {
        getAtrributeHelper().setFechaInformeComReg(null);
        getAtrributeHelper().setFechaPresentacionComReg(null);
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.execute("PF('registroCentralProp').hide();");

    }

    public void listaUnidadAdministrativaRegional() {
        List<UnidadAdministrativaEnum> unidadAdmin = new ArrayList<UnidadAdministrativaEnum>();
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_CENTRO);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_NOROESTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_NORTE_CENTRO);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_OCCIDENTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_PACIFICO_NORTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_SUR);
        getListaHelper().setListaUnidadAdminRegional(unidadAdmin);
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

    public void handleDateSelectFechaFinPeriodo(SelectEvent event) {
        getAtrributeHelper().getPropuesta().setFechaFinPeriodo(null);
    }

    private void obtenerListaFechasComiteSegunUsuario() {
        getListaHelper().setListaFechasComiteEnum(new ArrayList<TipoFechasComiteEnum>());

        UnidadAdministrativaEnum unidadAministrativa = UnidadAdministrativaEnum
                .parse(getAtrributeHelper().getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (getListaHelper().getListaUnidadAdminRegional().contains(unidadAministrativa)) {
            getListaHelper().getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL);
            getListaHelper().getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL);
        } else {
            getListaHelper().getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_INFORME_COMITE);
            getListaHelper().getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE);
        }
    }

    public void cargaCombos() {
        getListaHelper().setListPrioridad(registrarPropuestaIndividualService.getCatalogoPrioridad());
        getListaHelper().setListaSubprograma(registrarPropuestaIndividualService.getCatalogoSubprograma());
        getListaHelper().setListaMetodoRevision(registrarPropuestaIndividualService.getCatalogoMetodoOrigen());
        getListaHelper().setListaTipoPropuesta(registrarPropuestaIndividualService.getCatalogoTipoPropuesta());
        getListaHelper().setListaCausaProgramacion(registrarPropuestaIndividualService.getCatalogoCausaProgramacion());
        getListaHelper().setListaSector(registrarPropuestaIndividualService.getCatalogoSector());
        getListaHelper().setListaTipoRevision(registrarPropuestaIndividualService.getCatalogoRevision());
        getListaHelper().setListaTipoImpuesto(registrarPropuestaIndividualService.getCatalogoImpuesto());
        getListaHelper().setListaDocumentosTabla(validarRetroalimentarPropuestaService
                .getExpedientePropuesta(getAtrributeHelper().getPropuesta().getIdPropuesta()));
        cargaImpuestos();
    }

    public List<PropuestaOrigenCentralRegDTO> obtieneArace(List<PropuestaOrigenCentralRegDTO> propuesta) {
        for (PropuestaOrigenCentralRegDTO lista : propuesta) {
            for (AraceDTO arace : getListaHelper().getListaUnidadAdministrativa()) {
                if (lista.getPropuesta().getIdArace().intValue() == arace.getIdArace()) {
                    FececArace araces = new FececArace();
                    araces.setIdArace(new BigDecimal(arace.getIdArace()));
                    araces.setNombre(arace.getNombre());
                    lista.getPropuesta().setFececArace(araces);
                }
            }
        }
        return propuesta;

    }

    protected void cargaImpuestos() {
        try {
            getListaHelper().setListaImpuestos(origenCentralRegionalServ
                    .getImpuestosByPropuesta(getAtrributeHelper().getPropuesta().getIdPropuesta()));
            if (getListaHelper().getListaImpuestos() != null && !getListaHelper().getListaImpuestos().isEmpty()) {
                for (FecetImpuesto impuesto : getListaHelper().getListaImpuestos()) {
                    if (impuesto.getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                        setActivarImpuesto(true);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("No se pudo obtener el tipo de impuesto " + e.getCause(), e);
        }
    }

    public boolean validarImpuesto() {
        boolean datosValidos = true;

        for (FecetImpuesto impuestos : getListaHelper().getListaImpuestos()) {
            if (impuestos.getIdTipoImpuesto().equals(getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto())
                    && impuestos.getIdConcepto().equals(getAtrributeHelper().getImpuestoVO().getIdConcepto())) {
                addErrorMessage("formInsumo:cmbImpuesto", "El impuesto ya hab\u00eda sido registrado");
                datosValidos = false;
            }
        }
        if (getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto() == null) {
            addErrorMessage("formInsumo:cmbImpuesto", CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto().compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage("formInsumo:cmbImpuesto", CAMPO_OBLIGATORIO_IMPUESTO);
                datosValidos = false;
            }
        }
        if (getAtrributeHelper().getImpuestoVO().getMonto() == null) {
            addErrorMessage("formInsumo:txtMonto", CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getAtrributeHelper().getImpuestoVO().getMonto().compareTo(BigDecimal.ZERO) <= 0 && !getAtrributeHelper()
                    .getImpuestoVO().getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                addErrorMessage("formInsumo:txtMonto", "Debe ser mayor a 0");
                datosValidos = false;
            }
        }

        if (getAtrributeHelper().getImpuestoVO().getIdConcepto() == null) {
            addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getAtrributeHelper().getImpuestoVO().getIdConcepto().compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO_IMPUESTO);
                datosValidos = false;
            }

        }
        return datosValidos;
    }

    public void validaFormularioCompleto(final String formularioMostrar) {
        boolean formularioCompleto = true;
        if (getAtrributeHelper().getPropuesta().getIdArace() == null
                || getAtrributeHelper().getPropuesta().getIdArace().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbUnidadAdministrativa", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getIdMetodo() == null
                || getAtrributeHelper().getPropuesta().getIdMetodo().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbMetodo", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getIdCausaProgramacion() == null
                || getAtrributeHelper().getPropuesta().getIdCausaProgramacion().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbCausa", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getIdTipoPropuesta() == null
                || getAtrributeHelper().getPropuesta().getIdTipoPropuesta().equals(BigDecimal.valueOf(-1L))) {

            addErrorMessage("formInsumo:cmbPropuesta", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;

        }

        if ((getAtrributeHelper().getPropuesta().getIdRevision() == null
                || getAtrributeHelper().getPropuesta().getIdRevision().equals(BigDecimal.valueOf(-1L)))
                && (getAtrributeHelper().getPropuesta().getIdMetodo() != null && getAtrributeHelper().getPropuesta()
                .getIdMetodo().equals(new BigDecimal(Constantes.ENTERO_DOS)))) {
            addErrorMessage("formInsumo:cmbRevision", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getIdSubprograma() == null
                || getAtrributeHelper().getPropuesta().getIdSubprograma().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSubprograma", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getIdSector() == null
                || getAtrributeHelper().getPropuesta().getIdSector().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSector", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getPrioridadSugerida() == null
                || getAtrributeHelper().getPropuesta().getPrioridadSugerida().equals("-1")) {
            addErrorMessage("formInsumo:cmbPrioridad", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getFechaInicioPeriodo() == null) {
            addErrorMessage("formInsumo:txtFechaInicial", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getAtrributeHelper().getPropuesta().getFechaFinPeriodo() == null) {
            addErrorMessage("formInsumo:txtFechaFinal", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (formularioCompleto) {

            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute(formularioMostrar);
        }
    }

    public void validaRegistrar() {

        boolean bandera = true;

        if (getAtrributeHelper().getFechaInformeComReg() != null && getAtrributeHelper().getFechaInformeComReg()
                .before(getAtrributeHelper().getPropuesta().getFechaInicioPeriodo())) {
            addErrorMessage("formInsumo:fechInformeComReg",
                    "La fecha de Informe Comite no puede ser menor a la fecha del Periodo Propuesto Inicio, favor de verificar.");
            bandera = false;
        }

        if (getAtrributeHelper().getFechaInformeComReg() == null && isInforme()) {
            addErrorMessage("formInsumo:fechInformeComReg", CAMPO_OBLIGATORIO_IMPUESTO);
            bandera = false;
        }
        if (getAtrributeHelper().getFechaPresentacionComReg() == null && isPresenta()) {
            addErrorMessage("formInsumo:fechPresentacionComReg", CAMPO_OBLIGATORIO_IMPUESTO);
            bandera = false;

        }
        if (bandera) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formRegistro");
            requestContext.execute("PF('confirmarRegistro').show();");

        }

    }

    public void cargaConceptoImpuesto(BigDecimal idImpuesto) {
        getListaHelper().setListConcepto(registrarPropuestaIndividualService.getConceptoByImpuesto(idImpuesto));
    }

    public FececTipoImpuesto buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto) {
        FececTipoImpuesto tipoImpuestoTemp = new FececTipoImpuesto();
        for (FececTipoImpuesto tipoImpuesto : getListaHelper().getListaTipoImpuesto()) {
            if (tipoImpuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                tipoImpuestoTemp = tipoImpuesto;
                break;
            }
        }
        return tipoImpuestoTemp;
    }

    public OrigenCentralRegionalServ getOrigenCentralRegionalServ() {
        return origenCentralRegionalServ;
    }

    public void setOrigenCentralRegionalServ(OrigenCentralRegionalServ origenCentralRegionalServ) {
        this.origenCentralRegionalServ = origenCentralRegionalServ;
    }

    public RegistrarPropuestaIndividualService getRegistrarPropuestaIndividualService() {
        return registrarPropuestaIndividualService;
    }

    public void setRegistrarPropuestaIndividualService(
            RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
        this.registrarPropuestaIndividualService = registrarPropuestaIndividualService;
    }

    public ValidarRetroalimentarPropuestaService getValidarRetroalimentarPropuestaService() {
        return validarRetroalimentarPropuestaService;
    }

    public void setValidarRetroalimentarPropuestaService(
            ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService) {
        this.validarRetroalimentarPropuestaService = validarRetroalimentarPropuestaService;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        getAtrributeHelper().getPropuesta().setFecetContribuyente(getAtrributeHelper().getContribuyente());
        getAtrributeHelper().setDocumentoSeleccionDescarga(
                getDescargaArchivo(getAtrributeHelper().getDocumentoSeleccionado().getRutaArchivo(),
                        getAtrributeHelper().getDocumentoSeleccionado().getNombre()));

        return getAtrributeHelper().getDocumentoSeleccionDescarga();
    }

    public OrigenCentralListaHelper getListaHelper() {
        return listaHelper;
    }

    public void setListaHelper(OrigenCentralListaHelper listaHelper) {
        this.listaHelper = listaHelper;
    }

    public OrigenCentralHelper getAtrributeHelper() {
        return atrributeHelper;
    }

    public void setAtrributeHelper(OrigenCentralHelper atrributeHelper) {
        this.atrributeHelper = atrributeHelper;
    }

    public boolean isActivarImpuesto() {
        return activarImpuesto;
    }

    public void setActivarImpuesto(boolean activarImpuesto) {
        this.activarImpuesto = activarImpuesto;
    }

    public boolean isActivarMontoImpuesto() {
        return activarMontoImpuesto;
    }

    public void setActivarMontoImpuesto(boolean activarMontoImpuesto) {
        this.activarMontoImpuesto = activarMontoImpuesto;
    }

    public boolean isComplementado() {
        return complementado;
    }

    public void setComplementado(boolean complementado) {
        this.complementado = complementado;
    }

    public boolean isAccion() {
        return accion;
    }

    public void setAccion(boolean accion) {
        this.accion = accion;
    }

    public boolean isInforme() {
        return informe;
    }

    public void setInforme(boolean informe) {
        this.informe = informe;
    }

    public boolean isPresenta() {
        return presenta;
    }

    public void setPresenta(boolean presenta) {
        this.presenta = presenta;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

}
