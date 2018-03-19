package mx.gob.sat.siat.feagace.vista.propuestas.individual;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACAOCE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACOECE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACPPCE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORESTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORTE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_OCCIDENTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_PACIFICO_NORTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_SUR;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.EHO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.I;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ORG;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.P;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.REE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.UCA;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarProcedenciaInsumoService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualAttributeHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualBooleanHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualDTOHelper;

public abstract class PropuestaIndividualManagedBeanAbstract extends BaseManagedBean {

    private static final long serialVersionUID = 642433226173456282L;

    public static final String CAMPO_OBLIGATORIO = "Campo Obligatorio *";
    public static final String CAMPO_PERIODO = "formInsumo:txtPeriodo";
    public static final String CAMPO_FECHA = "La fecha de Inicio no puede ser mayor que la de Fin";

    private PropuestaIndividualBooleanHelper propIndBooleanHelper;
    private PropuestaIndividualAttributeHelper propIndAttributeHelper;
    private PropuestaIndividualDTOHelper propIndDTOHelper;

    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;

    @ManagedProperty(value = "#{validarContribuyenteService}")
    private transient ValidarContribuyenteService validarContribuyenteService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{validarProcedenciaInsumoService}")
    private transient ValidarProcedenciaInsumoService validarProcedenciaInsumoService;

    /**
     * pistasAuditoriaService es la referencia del servicio de las Pistas de
     * Auditoria.
     */
    @ManagedProperty(value = "#{pistasAuditoriaService}")
    private transient PistasAuditoriaService pistasAuditoriaService;

    /**
     * consultaAntecedentes es la referencia del servicio de consulta de
     * antedentes del Contribuyente.
     */
    @ManagedProperty(value = "#{consultaAntecedentesService}")
    private transient ConsultaAntecedentesPropuestasService consultaAntecedentesService;

    @Autowired
    private FecetDocExpediente documentoSeleccionado;

    public PropuestaIndividualManagedBeanAbstract() {
        propIndBooleanHelper = new PropuestaIndividualBooleanHelper();
        propIndAttributeHelper = new PropuestaIndividualAttributeHelper();
        propIndDTOHelper = new PropuestaIndividualDTOHelper();
    }

    @PostConstruct
    public void init() {
        try {
            ValidarYRetroalimentarHelper validarYRetroalimentarHelper;

            getPropIndAttributeHelper().setFechaActualImpuesto(new Date());
            getPropIndAttributeHelper().setFechaActualPeriodo(new Date());
            getPropIndAttributeHelper().setIdAraceSeleccionada(BigDecimal.valueOf(-1L));
            getPropIndAttributeHelper().setIdMetodoSeleccionado(BigDecimal.valueOf(-1L));
            getPropIndAttributeHelper().setIdPrioridadSeleccionada(BigDecimal.valueOf(-1L));
            getPropIndAttributeHelper().setQuintoOctavoDigito(new SimpleDateFormat("yyyy").format(new Date()));

            propIndBooleanHelper.setMostrarFormulario(false);
            propIndBooleanHelper.setTipoPropuesta(true);

            propIndDTOHelper.setDestinatarios(new ArrayList<String>());
            propIndDTOHelper.setContribuyente(new FecetContribuyente());
            propIndDTOHelper.setPropuesta(new FecetPropuesta());
            propIndDTOHelper.setImpuestoVO(new ImpuestoVO());

            FecetInsumo insumo = (FecetInsumo) getSession().getAttribute("insumo");
            validarYRetroalimentarHelper = (ValidarYRetroalimentarHelper) getSession().getAttribute("cambioMetodo");

            if (insumo == null && validarYRetroalimentarHelper == null) {
                propIndBooleanHelper.setComplementaInsumo(false);
                propIndBooleanHelper.setComplementaCambioMetodo(false);
                this.getEmpleadoProgramador(getRFCSession(), Constantes.USUARIO_PROGRAMADOR,
                        Constantes.EMPLEADO_ACTIVO);
                this.identificarAreaOrigenPropuesta();
            } else if (insumo != null) {

                propIndDTOHelper.setFecetInsumo(insumo);
                propIndBooleanHelper.setComplementaInsumo(true);
                propIndBooleanHelper.setHabilitaBotonRFC(true);
                propIndDTOHelper.setDocumentoInsumoSeleccionado(new FecetDocExpInsumo());
                propIndDTOHelper.setListaDocumentoInsumo(
                        (List<FecetDocExpInsumo>) getSession().getAttribute("documentosInsumo"));
                propIndDTOHelper.setFececEmpleado((FececEmpleado) getSession().getAttribute("empleado"));
                propIndDTOHelper.setContribuyente(insumo.getFecetContribuyente());
                propIndDTOHelper.setPropuesta(new FecetPropuesta());
                propIndDTOHelper.getPropuesta().setIdSubprograma(insumo.getIdSubprograma());
                propIndDTOHelper.getPropuesta().setIdSector(insumo.getIdSector());
                propIndDTOHelper.getPropuesta().setFechaInicioPeriodo(insumo.getFechaInicioPeriodo());
                propIndDTOHelper.getPropuesta().setPrioridad(insumo.getPrioridad());
                propIndDTOHelper.getPropuesta().setFechaFinPeriodo(insumo.getFechaFinPeriodo());
                propIndDTOHelper.setListaDocumento(new ArrayList<FecetDocExpediente>());
                getPropIndAttributeHelper().setAreaOrigen(ACPPCE);
                cargaCombos();
                activarCampos();
                for (AraceDTO fececArace : propIndDTOHelper.getListaUnidades()) {
                    if (fececArace.getIdArace().equals(insumo.getIdArace().intValue())) {
                        getPropIndAttributeHelper().setUnidadInsumo(fececArace.getNombre());
                    }
                }
                propIndBooleanHelper.setMostrarFormulario(true);
            } else if (validarYRetroalimentarHelper != null) {
                propIndBooleanHelper.setComplementaCambioMetodo(true);
                propIndBooleanHelper.setHabilitaBotonRFC(true);
                propIndDTOHelper.setDocumentoInsumoSeleccionado(new FecetDocExpInsumo());
                propIndDTOHelper.setListaDocumento(new ArrayList<FecetDocExpediente>());
                propIndDTOHelper.setFececEmpleado((FececEmpleado) getSession().getAttribute("empleado"));
                propIndDTOHelper.setContribuyente(
                        validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada().getFecetContribuyente());
                propIndDTOHelper.setPropuesta(validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada());
                propIndDTOHelper.setListaDocumentoExp(propIndDTOHelper.getPropuesta().getListaDocumentos());
                this.identificarAreaOrigenPropuesta();
                cargaCombos();
                activarCampos();
                for (AraceDTO fececArace : propIndDTOHelper.getListaUnidades()) {
                    if (fececArace.getIdArace().equals(
                            validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada().getIdArace().intValue())) {
                        getPropIndAttributeHelper().setUnidadInsumo(fececArace.getNombre());
                    }
                }
                reseteaMetodoSeleccionado();
                cargaCambioMetodoRevision();
                propIndBooleanHelper.setMostrarFormulario(true);
            }

        } catch (NegocioException e) {
            logger.error("No se encontro el usuario logueado: " + getRFCSession());
            this.informeErrorUsuario(e);
        }
    }

    public void invocaCambioMetodo() {

    }

    public void getEmpleadoProgramador(final String rfc, final BigDecimal idTipoEmpleado,
            final BigDecimal idEstatusEmpleado) throws NegocioException {

        try {
            EmpleadoDTO programadorLogueado = registrarPropuestaIndividualService.validarUsuarioEntrante(rfc);

            if (programadorLogueado != null) {
                propIndDTOHelper.setEmpleadoDTO(programadorLogueado);
            } else {
                throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
            }

        } catch (Exception e) {
            logger.error("[{}]", e);
            throw new NegocioException("No se pudo obtener la informacion del usuario logueado", e);
        }
    }

    public String claveOperativa(BigDecimal clave) {
        String claveCompleta = "";
        if (clave.toString().length() > 1) {
            claveCompleta = clave.toString();
        } else {
            claveCompleta = "0" + clave.toString();
        }
        return claveCompleta;
    }

    public void buscarRfcContribuyente() {
        try {
            getPropIndAttributeHelper().setMsjContactoAmparadoPPEE(null);
            getPropIndAttributeHelper().setEstatusDeContacto(null);
            desactivarFormulario();
            if (propIndDTOHelper.getContribuyente().getRfc() != null) {
                logger.info("Contribuyente IDC: [{}]", propIndDTOHelper.getContribuyente().getRfc().toUpperCase());
                propIndDTOHelper.setContribuyente(contribuyenteService
                        .getContribuyenteIDC(propIndDTOHelper.getContribuyente().getRfc().toUpperCase()));
                if (propIndDTOHelper.getContribuyente().getNombre() != null) {
                    validaEstatusContacto(propIndDTOHelper.getContribuyente().getRfc().toUpperCase());
                } else {
                    addErrorMessage("formInsumo:msgPropuestaId",
                            "No se encuentra registrada informaci\u00f3n para el RFC: "
                            + propIndDTOHelper.getContribuyente().getRfc().toUpperCase()
                            + "; favor de verificar.");
                    propIndDTOHelper.setContribuyente(new FecetContribuyente());
                }
            }
        } catch (NoExisteContribuyenteException e) {
            addErrorMessage("formInsumo:msgPropuestaId",
                    "No se encuentra registrada informaci\u00f3n para el RFC: "
                    + propIndDTOHelper.getContribuyente().getRfc().toUpperCase() + "; favor de verificar.");
            logger.error("Error al consultar RFC en el IDC [{}]", e);
        }
    }

    public void identificarAreaOrigenPropuesta() {
        propIndDTOHelper.getEmpleadoDTO();

        boolean validaAraceR1 = propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_CENTRO.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_NORESTE.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_NORTE_CENTRO.intValue());
        boolean validaAraceR2 = propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_OCCIDENTE.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_PACIFICO_NORTE.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ARACE_SUR.intValue());
        if (propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ACAOCE.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ACOECE.intValue())
                || propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()
                .equals(Constantes.ACPPCE.intValue())) {
            getPropIndAttributeHelper().setNombreAreaOrigen(
                    propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getSede());
            getPropIndAttributeHelper().setAreaOrigen(new BigDecimal(
                    propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()));
        } else if (validaAraceR1 || validaAraceR2) {
            getPropIndAttributeHelper().setNombreAreaOrigen(
                    propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getNombre());
            getPropIndAttributeHelper().setAreaOrigen(new BigDecimal(
                    propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()));
            getPropIndAttributeHelper()
                    .setTerceroCuartoDigito(this.claveOperativa(getPropIndAttributeHelper().getAreaOrigen()));
        }
    }

    public void desactivarFormulario() {
        propIndBooleanHelper.setMostrarFormulario(false);
    }

    public void cargaCombos() {
        cargaUnidad();
        cargaSubprograma();
        cargaMetodoRevision();
        cargaTipoPropuesta();
        cargaCausaProgramacion();
        cargaSector();
        cargaTipoRevision();
        cargaTipoImpuesto();
        cargaPrioridadSugerida();
        propIndDTOHelper.setListaImpuestos(new ArrayList<ImpuestoVO>());
    }

    public void cargaConceptoImpuesto(BigDecimal idImpuesto) {
        propIndDTOHelper.setListaConcepto(registrarPropuestaIndividualService.getConceptoByImpuesto(idImpuesto));
    }

    public void cargaPrioridadSugerida() {
        propIndDTOHelper.setListaPrioridad(registrarPropuestaIndividualService.getCatalogoPrioridad());
    }

    public void cargaUnidad() {
        propIndDTOHelper.setListaUnidades(registrarPropuestaIndividualService.getCatalogoUnidad());
    }

    public void cargaSubprograma() {
        propIndDTOHelper.setListaSubprograma(registrarPropuestaIndividualService.getCatalogoSubprograma());
    }

    public void cargaMetodoRevision() {
        propIndDTOHelper.setListaMetodoRevision(registrarPropuestaIndividualService.getCatalogoMetodo());
    }

    public void cargaCambioMetodoRevision() {

        List<FececMetodo> toRemove = new ArrayList<FececMetodo>();
        for (FececMetodo metodo : propIndDTOHelper.getListaMetodoRevision()) {
            if (!propIndDTOHelper.getPropuesta().getCambioMetodoOrden().getIdMetodo().equals(metodo.getIdMetodo())) {
                toRemove.add(metodo);
            }
        }
        propIndDTOHelper.getListaMetodoRevision().removeAll(toRemove);

    }

    public void cargaTipoPropuesta() {
        propIndDTOHelper.setListaTipoPropuesta(registrarPropuestaIndividualService.getCatalogoTipoPropuesta());
    }

    public void cargaCausaProgramacion() {
        propIndDTOHelper.setListaCausaProgramacion(registrarPropuestaIndividualService.getCatalogoCausaProgramacion());
    }

    public void cargaSector() {
        propIndDTOHelper.setListaSector(registrarPropuestaIndividualService.getCatalogoSector());
    }

    public void cargaTipoRevision() {
        propIndDTOHelper.setListaTipoRevision(registrarPropuestaIndividualService.getCatalogoRevision());
    }

    public void cargaTipoImpuesto() {
        propIndDTOHelper.setListaTipoImpuesto(registrarPropuestaIndividualService.getCatalogoImpuesto());
    }

    public boolean validarAraceParte1() {
        boolean araceParte1 = false;
        if (getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE)
                || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO)
                || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORESTE)) {
            araceParte1 = true;
        }
        return araceParte1;
    }

    public boolean validarAraceParte2() {
        boolean araceParte2 = false;
        if (getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_OCCIDENTE)
                || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_CENTRO)
                || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_SUR)) {
            araceParte2 = true;
        }
        return araceParte2;
    }

    public boolean validarAraceParte3() {
        boolean araceParte3 = false;
        if (validarAraceParte1()) {
            araceParte3 = true;
        }
        if (validarAraceParte2()) {
            araceParte3 = true;
        }
        return araceParte3;
    }

    public void activarCampos() {
        if (getPropIndAttributeHelper().getAreaOrigen().equals(ACPPCE)) {
            propIndBooleanHelper.setVisibleUnidad(true);
            propIndBooleanHelper.setVisibleRevision(false);
            propIndBooleanHelper.setVisibleFechaPre(false);
            propIndBooleanHelper.setVisibleFechaInf(false);
            getPropIndAttributeHelper()
                    .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
            getPropIndAttributeHelper()
                    .setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComite"));
        } else if (validarAraceParte3()) {
            propIndBooleanHelper.setVisibleUnidad(false);
            propIndBooleanHelper.setVisibleRevision(false);
            propIndBooleanHelper.setVisibleFechaPre(false);
            propIndBooleanHelper.setVisibleFechaInf(false);
            getPropIndAttributeHelper()
                    .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComiteRegional"));
            getPropIndAttributeHelper()
                    .setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComiteRegional"));
        }
    }

    public void reseteaMetodoSeleccionado() {
        BigDecimal idUnidadAdministrativa = propIndDTOHelper.getPropuesta().getIdArace();
        propIndDTOHelper.getPropuesta().setIdMetodo(new BigDecimal(-1));
        propIndBooleanHelper.setVisibleRevision(false);
        propIndBooleanHelper.setVisibleFechaPre(false);
        propIndBooleanHelper.setVisibleFechaInf(false);
        propIndDTOHelper.getPropuesta().setIdArace(idUnidadAdministrativa);
        if (propIndDTOHelper.getPropuesta().getIdArace() != null
                && propIndDTOHelper.getPropuesta().getIdArace().compareTo(BigDecimal.ZERO) > 0) {
            getPropIndAttributeHelper()
                    .setTerceroCuartoDigito(this.claveOperativa(propIndDTOHelper.getPropuesta().getIdArace()));
        }
        mostrarTipoRevision();
    }

    public void reseteaRevisionMetodo() {
        propIndDTOHelper.getPropuesta().setIdCausaProgramacion(new BigDecimal(-1));
        propIndDTOHelper.getPropuesta().setIdTipoPropuesta(new BigDecimal(-1));
        propIndDTOHelper.getPropuesta().setIdSector(new BigDecimal(-1));
        propIndDTOHelper.getPropuesta().setIdSubprograma(new BigDecimal(-1));
        propIndDTOHelper.getPropuesta().setPrioridadSugerida(null);
        propIndBooleanHelper.setVisibleRevision(false);
        propIndBooleanHelper.setVisibleFechaPre(false);
        propIndBooleanHelper.setVisibleFechaInf(false);
    }

    public void seleccionMetodo() {
        if (!getPropIndAttributeHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))
                && !getPropIndAttributeHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
            mostrarTipoRevision();
        } else {
            if (getPropIndAttributeHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))) {
                addErrorMessage("formValidarInsumo:cmbUnidad", CAMPO_OBLIGATORIO);
            }

            if (getPropIndAttributeHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                addErrorMessage("formValidarInsumo:cmbMetodo", CAMPO_OBLIGATORIO);
            }
        }
    }

    public void mostrarTipoRevision() {
        BigDecimal sedeUnidad;
        if (propIndDTOHelper.getPropuesta().getIdArace() != null) {
            sedeUnidad = propIndDTOHelper.getPropuesta().getIdArace();
        } else {
            sedeUnidad = new BigDecimal(
                    propIndDTOHelper.getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace());
            propIndDTOHelper.getPropuesta().setIdArace(sedeUnidad);
        }

        if (getPropIndAttributeHelper().getAreaOrigen().equals(ACPPCE)) {
            validaMetodoArace(sedeUnidad, propIndDTOHelper.getPropuesta().getIdMetodo());
        } else {
            boolean validaAreaOrigen2 = getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORESTE)
                    || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_OCCIDENTE)
                    || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_CENTRO)
                    || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_SUR);
            boolean validaAreaOrigen = getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE)
                    || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO) || validaAreaOrigen2;
            if (validaAreaOrigen) {
                validaMetodoArace2(propIndDTOHelper.getPropuesta().getIdMetodo());
            }
        }
    }

    public void validaMetodoArace2(final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo.equals(ORG) && !propIndDTOHelper.getPropuesta().getPrioridadSugerida().equals("1")) {
            getPropIndAttributeHelper().setSegundoDigito(P);
            propIndBooleanHelper.setVisibleRevision(true);
            propIndBooleanHelper.setVisibleFechaPre(true);
            propIndBooleanHelper.setVisibleFechaInf(false);
            propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE_REGIONAL);

        } else if (abreviaturaMetodo.equals(ORG)
                && propIndDTOHelper.getPropuesta().getPrioridadSugerida().equals("1")) {
            getPropIndAttributeHelper().setSegundoDigito(I);
            propIndBooleanHelper.setVisibleRevision(true);
            propIndBooleanHelper.setVisibleFechaPre(false);
            propIndBooleanHelper.setVisibleFechaInf(true);
            propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
        } else if (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE) || abreviaturaMetodo.equals(UCA)) {
            getPropIndAttributeHelper().setSegundoDigito(I);
            propIndBooleanHelper.setVisibleRevision(false);
            propIndBooleanHelper.setVisibleFechaPre(false);
            propIndBooleanHelper.setVisibleFechaInf(true);
            propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            propIndDTOHelper.getPropuesta().setIdRevision(null);
        } else if (abreviaturaMetodo.equals(Constantes.MCA)) {
            getPropIndAttributeHelper().setSegundoDigito(I);
            propIndBooleanHelper.setVisibleRevision(false);
            propIndBooleanHelper.setVisibleFechaPre(false);
            propIndBooleanHelper.setVisibleFechaInf(true);
            propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            propIndDTOHelper.getPropuesta().setIdRevision(null);
        }
    }

    public void validaMetodoArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo != null && sedeUnidad != null && !abreviaturaMetodo.equals(new BigDecimal(-1))) {
            boolean validaSedeUnidad = (sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE));
            boolean validaAbreviaturaMetodo = (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE)
                    || abreviaturaMetodo.equals(UCA));
            boolean validaMetodoMCA = abreviaturaMetodo.equals(Constantes.MCA);
            if ((sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE)) && abreviaturaMetodo.equals(ORG)) {
                if (propIndDTOHelper.getPropuesta().getPrioridadSugerida().equals("1")) {
                    getPropIndAttributeHelper().setSegundoDigito(I);
                    propIndBooleanHelper.setVisibleRevision(true);
                    propIndBooleanHelper.setVisibleFechaPre(false);
                    propIndBooleanHelper.setVisibleFechaInf(true);
                    propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
                } else if (!propIndDTOHelper.getPropuesta().getPrioridadSugerida().equals("1")) {
                    getPropIndAttributeHelper().setSegundoDigito(P);
                    propIndBooleanHelper.setVisibleRevision(true);
                    propIndBooleanHelper.setVisibleFechaPre(true);
                    propIndBooleanHelper.setVisibleFechaInf(false);
                    propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE);
                } else {
                    getPropIndAttributeHelper().setSegundoDigito("");
                    propIndBooleanHelper.setVisibleRevision(false);
                    propIndBooleanHelper.setVisibleFechaPre(false);
                    propIndBooleanHelper.setVisibleFechaInf(false);
                }
            } else if (validaSedeUnidad && (validaAbreviaturaMetodo || validaMetodoMCA)) {
                getPropIndAttributeHelper().setSegundoDigito(I);
                propIndBooleanHelper.setVisibleRevision(false);
                propIndBooleanHelper.setVisibleFechaPre(false);
                propIndBooleanHelper.setVisibleFechaInf(true);
                propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
            } else {
                validaUnidadArace(sedeUnidad, abreviaturaMetodo);
            }
        }
    }

    public void validaUnidadArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        boolean validaUnidad = sedeUnidad.equals(ARACE_NORESTE) || sedeUnidad.equals(ARACE_OCCIDENTE)
                || sedeUnidad.equals(ARACE_CENTRO) || sedeUnidad.equals(ARACE_SUR);
        if (sedeUnidad.equals(ARACE_PACIFICO_NORTE) || sedeUnidad.equals(ARACE_NORTE_CENTRO) || validaUnidad) {
            if (abreviaturaMetodo.equals(ORG)) {
                propIndBooleanHelper.setVisibleRevision(true);
                if (propIndBooleanHelper.isComplementaInsumo()) {
                    getPropIndAttributeHelper().setSegundoDigito(P);
                    propIndBooleanHelper.setVisibleFechaPre(true);
                    propIndBooleanHelper.setVisibleFechaInf(false);
                    propIndDTOHelper.getPropuesta()
                            .setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE_REGIONAL);
                } else {

                    getPropIndAttributeHelper().setSegundoDigito(I);
                    propIndBooleanHelper.setVisibleFechaPre(false);
                    propIndBooleanHelper.setVisibleFechaInf(true);
                    propIndDTOHelper.getPropuesta()
                            .setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);

                }

            } else {
                propIndBooleanHelper.setVisibleRevision(false);
                propIndDTOHelper.getPropuesta().setIdRevision(null);

                getPropIndAttributeHelper().setSegundoDigito(I);
                propIndBooleanHelper.setVisibleFechaPre(false);
                propIndBooleanHelper.setVisibleFechaInf(true);
                propIndDTOHelper.getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            }
        }
    }

    public void descartarDocumento() {
        List<FecetDocExpediente> toRemove = new ArrayList<FecetDocExpediente>();
        for (FecetDocExpediente documento : propIndDTOHelper.getListaDocumento()) {
            if (this.documentoSeleccionado.equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        propIndDTOHelper.getListaDocumento().removeAll(toRemove);
        addMessage("formInsumo:msgExitoDescartarOficioAnexo",
                "Se descart\u00f3 el documento correctamente.");
    }

    public boolean validarImpuesto() {
        String campoRequerido = getMessageResourceString("lbl.propuestas.impuestos.validacion.campos.requerido");
        boolean datosValidos = true;
        for (ImpuestoVO impuestos : propIndDTOHelper.getListaImpuestos()) {
            if (impuestos.getDescripcion()
                    .equals(buscarDescripcionImpuesto(propIndDTOHelper.getImpuestoVO().getIdImpuesto()))
                    && impuestos.getDescripcionConcepto()
                    .equals(buscarDescripcionConcepto(propIndDTOHelper.getImpuestoVO().getIdConcepto()))) {
                addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO,
                        getMessageResourceString("lbl.propuestas.impuestos.existente"));
                datosValidos = false;
            }
        }

        if (propIndDTOHelper.getListaImpuestos() != null && propIndDTOHelper.getListaImpuestos().size() > 0) {
            if (propIndDTOHelper.getImpuestoVO().getIdImpuesto() != null
                    && propIndDTOHelper.getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) > 0
                    && propIndDTOHelper.getImpuestoVO().getIdImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {

                addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO,
                        getMessageResourceString("lbl.propuestas.sin.impuestos.error"));
                datosValidos = false;
            }
        } else {
            if (propIndDTOHelper.getImpuestoVO().getIdImpuesto() != null
                    && propIndDTOHelper.getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) > 0
                    && propIndDTOHelper.getImpuestoVO().getIdImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {

                propIndBooleanHelper.setHabilitaImpuesto(true);
            }
        }

        if (propIndDTOHelper.getImpuestoVO().getIdImpuesto() == null) {
            addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
            datosValidos = false;
            propIndBooleanHelper.setHabilitaImpuesto(false);
        } else {
            if (propIndDTOHelper.getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
                datosValidos = false;
                propIndBooleanHelper.setHabilitaImpuesto(false);
            }
        }

        if (propIndDTOHelper.getImpuestoVO().getIdConcepto().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO);
            datosValidos = false;
            propIndBooleanHelper.setHabilitaImpuesto(false);
        } else {
            if (propIndDTOHelper.getImpuestoVO().getIdConcepto().compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO);
                datosValidos = false;
                propIndBooleanHelper.setHabilitaImpuesto(false);
            }
        }

        if (propIndDTOHelper.getImpuestoVO().getMonto() == null) {
            addErrorMessage("formInsumo:txtMonto", campoRequerido);
            datosValidos = false;
        } else {

            boolean flgTipoImpuesto = (!propIndDTOHelper.getImpuestoVO().getIdImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA));
            boolean flgMontoNoCero = (propIndDTOHelper.getImpuestoVO().getMonto()
                    .compareTo(BigDecimal.ZERO) <= Constantes.CERO);

            if (flgTipoImpuesto && flgMontoNoCero) {
                addErrorMessage("formInsumo:txtMonto",
                        getMessageResourceString("lbl.propuestas.impuestos.validacion.importe.cero"));
                datosValidos = false;
            }
        }

        return datosValidos;
    }

    public String buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto) {
        String descripcion = "";
        for (FececTipoImpuesto impuesto : propIndDTOHelper.getListaTipoImpuesto()) {
            if (impuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                descripcion = impuesto.getDescripcion();
                break;
            }
        }
        return descripcion;
    }

    public String buscarDescripcionConcepto(final BigDecimal idConcepto) {
        String descripcionCocnepto = "";
        for (FececConcepto impuesto : propIndDTOHelper.getListaConcepto()) {
            if (impuesto.getIdConcepto() == idConcepto.intValue()) {
                descripcionCocnepto = impuesto.getDescripcion();
                break;
            }
        }
        return descripcionCocnepto;
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (propIndDTOHelper.getListaImpuestos() != null) {
            for (ImpuestoVO imp : propIndDTOHelper.getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public void validaEstatusContacto(String rfc) {
        logger.info("--- Va a consultar medio contacto rfc:" + rfc);
        getPropIndAttributeHelper().setMnjestatusDeContacto(null);
        validarContribuyenteService.validaEstatusContacto(rfc);
        String mensajeEstatus = validarContribuyenteService.getEstatusDeContacto();
        String msjAmparadoPPEE = validarContribuyenteService.getMsjContactoAmparadoPPEE();
        if (mensajeEstatus != null) {
            getPropIndAttributeHelper().setEstatusDeContacto(getMessageResourceString(mensajeEstatus));
        }
        if (msjAmparadoPPEE != null) {
            getPropIndAttributeHelper().setMsjContactoAmparadoPPEE(getMessageResourceString(msjAmparadoPPEE));
        }
        if (getPropIndAttributeHelper().getEstatusDeContacto() != null
                && getPropIndAttributeHelper().getEstatusDeContacto()
                .equals(getMessageResourceString(ConstantesPropuestas.CON_MEDIOS))
                && getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() == null) {
            propIndDTOHelper.setPropuesta(new FecetPropuesta());
            propIndDTOHelper.setListaDocumento((new ArrayList<FecetDocExpediente>()));
            cargaCombos();
            activarCampos();
            propIndBooleanHelper.setMostrarFormulario(true);
        } else if (getPropIndAttributeHelper().getEstatusDeContacto() != null
                && getPropIndAttributeHelper().getEstatusDeContacto()
                .equals(getMessageResourceString(ConstantesPropuestas.SIN_MEDIOS))
                && getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() == null) {
            getPropIndAttributeHelper().setEstatusDeContacto(getMessageResourceString(ConstantesPropuestas.NO_MEDIOS));
            getPropIndAttributeHelper()
                    .setMnjestatusDeContacto(getMessageResourceString(ConstantesPropuestas.SIN_MEDIOS));
        } else {
            if (getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() != null) {
                FecetDetalleContribuyente dto = new FecetDetalleContribuyente();
                FecetDetalleContribuyente existeDetalle = null;
                existeDetalle = this.getRegistrarPropuestaIndividualService().findByRfc(rfc);
                getPropIndAttributeHelper().setMnjestatusDeContacto(null);
                if (getPropIndAttributeHelper().getMsjContactoAmparadoPPEE()
                        .equals(getMessageResourceString(ConstantesPropuestas.CON_PPE))) {
                    dto.setPpee(new BigDecimal(1L));
                    dto.setAmparado(new BigDecimal(0L));
                    propIndDTOHelper.setContribuyente(new FecetContribuyente());
                    propIndDTOHelper.getContribuyente().setRfc(rfc);
                } else {
                    dto.setPpee(new BigDecimal(0L));
                    dto.setAmparado(new BigDecimal(1L));
                }
                if (existeDetalle != null && existeDetalle.getIdDetalleContribuyente() != null) {
                    logger.debug("Detalle Contribuyente RFC: [{}] ya existe", rfc);
                } else {
                    dto.setFechaConsulta(new Date());
                    dto.setRfcContribuyente(rfc);
                    this.getRegistrarPropuestaIndividualService().insertarDetalleContribuyente(dto);
                }
            }
        }
    }

    public void informeErrorUsuario(Exception e) {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("mensaje", e);
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setRegistrarPropuestaIndividualService(
            RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
        this.registrarPropuestaIndividualService = registrarPropuestaIndividualService;
    }

    public RegistrarPropuestaIndividualService getRegistrarPropuestaIndividualService() {
        return registrarPropuestaIndividualService;
    }

    public void setValidarContribuyenteService(ValidarContribuyenteService validarContribuyenteService) {
        this.validarContribuyenteService = validarContribuyenteService;
    }

    public ValidarContribuyenteService getValidarContribuyenteService() {
        return validarContribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setPistasAuditoriaService(PistasAuditoriaService pistasAuditoriaService) {
        this.pistasAuditoriaService = pistasAuditoriaService;
    }

    public PistasAuditoriaService getPistasAuditoriaService() {
        return pistasAuditoriaService;
    }

    public void setConsultaAntecedentesService(ConsultaAntecedentesPropuestasService consultaAntecedentesService) {
        this.consultaAntecedentesService = consultaAntecedentesService;
    }

    public ConsultaAntecedentesPropuestasService getConsultaAntecedentesService() {
        return consultaAntecedentesService;
    }

    public void setValidarProcedenciaInsumoService(ValidarProcedenciaInsumoService validarProcedenciaInsumoService) {
        this.validarProcedenciaInsumoService = validarProcedenciaInsumoService;
    }

    public ValidarProcedenciaInsumoService getValidarProcedenciaInsumoService() {
        return validarProcedenciaInsumoService;
    }

    public PropuestaIndividualBooleanHelper getPropIndBooleanHelper() {
        return propIndBooleanHelper;
    }

    public void setPropIndBooleanHelper(PropuestaIndividualBooleanHelper propIndBooleanHelper) {
        this.propIndBooleanHelper = propIndBooleanHelper;
    }

    public PropuestaIndividualAttributeHelper getPropIndAttributeHelper() {
        return propIndAttributeHelper;
    }

    public void setPropIndAttributeHelper(PropuestaIndividualAttributeHelper propIndAttributeHelper) {
        this.propIndAttributeHelper = propIndAttributeHelper;
    }

    public PropuestaIndividualDTOHelper getPropIndDTOHelper() {
        return propIndDTOHelper;
    }

    public void setPropIndDTOHelper(PropuestaIndividualDTOHelper propIndDTOHelper) {
        this.propIndDTOHelper = propIndDTOHelper;
    }

}
