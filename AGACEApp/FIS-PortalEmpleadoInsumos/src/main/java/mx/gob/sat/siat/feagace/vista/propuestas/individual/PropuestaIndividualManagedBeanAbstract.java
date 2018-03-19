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
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualAttributeHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualBooleanHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualDTOHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public abstract class PropuestaIndividualManagedBeanAbstract extends PropuestaIndividualBasicMB {

    private static final long serialVersionUID = 642433226173456282L;
    
    private static final int UNIDAD_ADMIN = 19;

    @Autowired
    private FecetDocExpediente documentoSeleccionado;

    public PropuestaIndividualManagedBeanAbstract() {
        setPropIndBooleanHelper(new PropuestaIndividualBooleanHelper());
        setPropIndAttributeHelper(new PropuestaIndividualAttributeHelper());
        setPropIndDTOHelper(new PropuestaIndividualDTOHelper());
    }

    @SuppressWarnings("unchecked")
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

            getPropIndBooleanHelper().setMostrarFormulario(false);
            getPropIndBooleanHelper().setTipoPropuesta(true);

            getPropIndDTOHelper().setDestinatarios(new TreeSet<String>());
            getPropIndDTOHelper().setContribuyente(new FecetContribuyente());
            getPropIndDTOHelper().setPropuesta(new FecetPropuesta());
            getPropIndDTOHelper().setImpuestoVO(new ImpuestoVO());

            FecetInsumo insumo = (FecetInsumo) getSession().getAttribute("insumo");
            validarYRetroalimentarHelper = (ValidarYRetroalimentarHelper) getSession().getAttribute("cambioMetodo");

            if (insumo == null && validarYRetroalimentarHelper == null) {
                getPropIndBooleanHelper().setComplementaInsumo(false);
                getPropIndBooleanHelper().setComplementaCambioMetodo(false);
                this.getEmpleadoProgramador(getRFCSession(), TipoEmpleadoEnum.PROGRAMADOR);
                this.identificarAreaOrigenPropuesta();
            } else if (insumo != null) {
                getPropIndDTOHelper().setFecetInsumo(insumo);
                getPropIndBooleanHelper().setComplementaInsumo(true);
                getPropIndBooleanHelper().setHabilitaBotonRFC(true);
                getPropIndDTOHelper().setDocumentoInsumoSeleccionado(new FecetDocExpInsumo());
                getPropIndDTOHelper().setListaDocumentoInsumo((List<FecetDocExpInsumo>) getSession().getAttribute("documentosInsumo"));
                getPropIndDTOHelper().setEmpleadoDTO((EmpleadoDTO) getSession().getAttribute("empleado"));
                getPropIndDTOHelper().setContribuyente(insumo.getFecetContribuyente());
                getPropIndDTOHelper().setPropuesta(new FecetPropuesta());
                getPropIndDTOHelper().getPropuesta().setIdSubprograma(insumo.getIdSubprograma());
                getPropIndDTOHelper().getPropuesta().setIdSector(insumo.getIdSector());
                getPropIndDTOHelper().getPropuesta().setFechaInicioPeriodo(insumo.getFechaInicioPeriodo());
                getPropIndDTOHelper().getPropuesta().setPrioridad(insumo.getPrioridad());
                getPropIndDTOHelper().getPropuesta().setFechaFinPeriodo(insumo.getFechaFinPeriodo());
                getPropIndDTOHelper().setListaDocumento(new ArrayList<FecetDocExpediente>());
                getPropIndAttributeHelper().setAreaOrigen(ACPPCE);
                cargaCombos();
                activarCampos();
                for (AraceDTO fececArace : getPropIndDTOHelper().getListaUnidades()) {
                    if (fececArace.getIdArace() == insumo.getIdUnidadAdministrativa().intValue()) {
                        getPropIndAttributeHelper().setUnidadInsumo(fececArace.getNombre());
                    }
                }
                getPropIndDTOHelper().setListaUnidades(getRegistrarPropuestaIndividualService().getCatalogoUnidad());
                getPropIndBooleanHelper().setMostrarFormulario(true);
            } else if (validarYRetroalimentarHelper != null) {
                getPropIndBooleanHelper().setComplementaCambioMetodo(true);
                getPropIndBooleanHelper().setHabilitaBotonRFC(true);
                getPropIndDTOHelper().setDocumentoInsumoSeleccionado(new FecetDocExpInsumo());
                getPropIndDTOHelper().setListaDocumento(new ArrayList<FecetDocExpediente>());
                getPropIndDTOHelper().setEmpleadoDTO((EmpleadoDTO) getSession().getAttribute("empleado"));
                getPropIndDTOHelper().setContribuyente(validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada().getFecetContribuyente());
                getPropIndDTOHelper().setPropuesta(validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada());
                getPropIndDTOHelper().setListaDocumentoExp(getPropIndDTOHelper().getPropuesta().getListaDocumentos());
                getPropIndAttributeHelper().setAreaOrigen(ACPPCE);
                cargaCombos();
                activarCampos();
                for (AraceDTO fececArace : getPropIndDTOHelper().getListaUnidades()) {
                    if (fececArace.getIdArace() == validarYRetroalimentarHelper.getPropuestasXValidarSeleccionada().getIdArace().intValue()) {
                        getPropIndAttributeHelper().setUnidadInsumo(fececArace.getNombre());
                    }
                }
                reseteaMetodoSeleccionado();
                cargaCambioMetodoRevision();
                getPropIndBooleanHelper().setMostrarFormulario(true);
            }
        } catch (NegocioException e) {
            logger.error("No se encontro el usuario logueado: " + getRFCSession());
            this.informeErrorUsuario(e);
        }
    }

    public void getEmpleadoProgramador(final String rfc, final TipoEmpleadoEnum tipoEmpleado) throws NegocioException {
        try {
            getPropIndDTOHelper().setEmpleadoDTO(obtenerEmpleadoAcceso(rfc, tipoEmpleado));
        } catch (NoExisteEmpleadoException e) {
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
            if (getPropIndDTOHelper().getContribuyente().getRfc() != null) {
                logger.info("Contribuyente IDC: [{}]", getPropIndDTOHelper().getContribuyente().getRfc().toUpperCase());
                getPropIndDTOHelper().setContribuyente(getContribuyenteService().getContribuyenteIDC(getPropIndDTOHelper().getContribuyente().getRfc().toUpperCase()));
                if (getPropIndDTOHelper().getContribuyente().getNombre() != null) {
                    validaEstatusContacto(getPropIndDTOHelper().getContribuyente().getRfc().toUpperCase());
                } else {
                    FacesUtil.addErrorMessage("formInsumo:msgPropuestaId", "No se encuentra registrada informaci\u00f3n para el RFC: " + getPropIndDTOHelper().getContribuyente().getRfc().toUpperCase() + "; favor de verificar.");
                    getPropIndDTOHelper().setContribuyente(new FecetContribuyente());
                }
            }
        } catch (NoExisteContribuyenteException e) {
            FacesUtil.addErrorMessage("formInsumo:msgPropuestaId", "No se encuentra registrada informaci\u00f3n para el RFC: " + getPropIndDTOHelper().getContribuyente().getRfc().toUpperCase() + "; favor de verificar.");
            logger.error("Error al consultar RFC en el IDC [{}]", e);
        }
    }

    public void identificarAreaOrigenPropuesta() {
        AraceDTO central = getPropIndDTOHelper().getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral();
        BigDecimal idUnidad = new BigDecimal(central.getIdArace());
        boolean validaAraceR1 = idUnidad.equals(Constantes.ARACE_CENTRO)
                || idUnidad.equals(Constantes.ARACE_NORESTE)
                || idUnidad.equals(Constantes.ARACE_NORTE_CENTRO);
        boolean validaAraceR2 = idUnidad.equals(Constantes.ARACE_OCCIDENTE)
                || idUnidad.equals(Constantes.ARACE_PACIFICO_NORTE)
                || idUnidad.equals(Constantes.ARACE_SUR);
        if (idUnidad.equals(Constantes.ACAOCE)
                || idUnidad.equals(Constantes.ACOECE)
                || idUnidad.equals(Constantes.ACPPCE)) {
            getPropIndAttributeHelper().setNombreAreaOrigen(central.getSede());
            getPropIndAttributeHelper().setAreaOrigen(idUnidad);
        } else if (validaAraceR1 || validaAraceR2) {
            getPropIndAttributeHelper().setNombreAreaOrigen(central.getNombre());
            getPropIndAttributeHelper().setAreaOrigen(idUnidad);
            getPropIndAttributeHelper().setTerceroCuartoDigito(this.claveOperativa(getPropIndAttributeHelper().getAreaOrigen()));
        }
    }

    public void desactivarFormulario() {
        getPropIndBooleanHelper().setMostrarFormulario(false);
    }

    public void cargaCombos() {
        //Carga Unidad
        try {
            //traer lista unidades Administrativas de sesion
            getPropIndDTOHelper().setListaUnidades(new ArrayList<AraceDTO>());
        } catch (Exception e) {
            logger.error("Error al realizar la cargar de unidaddes", e);
        }
        //Carga Subprograma
        getPropIndDTOHelper().setListaSubprograma(getRegistrarPropuestaIndividualService().getCatalogoSubprograma(new BigDecimal(UNIDAD_ADMIN)));
        //Carga Metodo Revision
        getPropIndDTOHelper().setListaMetodoRevision(getRegistrarPropuestaIndividualService().getCatalogoMetodo());
        // Carga Tipo Propuesta
        getPropIndDTOHelper().setListaTipoPropuesta(getRegistrarPropuestaIndividualService().getCatalogoTipoPropuesta());
        //Carga Causa Programacion
        getPropIndDTOHelper().setListaCausaProgramacion(getRegistrarPropuestaIndividualService().getCatalogoCausaProgramacion());
        // Carga Sector
        getPropIndDTOHelper().setListaSector(getRegistrarPropuestaIndividualService().getCatalogoSector(new BigDecimal(UNIDAD_ADMIN)));
        //Carga Tipo Revision
        getPropIndDTOHelper().setListaTipoRevision(getRegistrarPropuestaIndividualService().getCatalogoRevision());
        // Carga Tipo Impuesto
        getPropIndDTOHelper().setListaTipoImpuesto(getRegistrarPropuestaIndividualService().getCatalogoImpuesto());
        // Carga Prioridad Sugerida
        getPropIndDTOHelper().setListaPrioridad(getRegistrarPropuestaIndividualService().getCatalogoPrioridad());
        getPropIndDTOHelper().setListaImpuestos(new ArrayList<ImpuestoVO>());
    }

    public void cargaConceptoImpuesto(BigDecimal idImpuesto) {
        getPropIndDTOHelper().setListaConcepto(getRegistrarPropuestaIndividualService().getConceptoByImpuesto(idImpuesto));
    }

    public void cargaCambioMetodoRevision() {
        List<FececMetodo> toRemove = new ArrayList<FececMetodo>();
        for (FececMetodo metodo : getPropIndDTOHelper().getListaMetodoRevision()) {
            if (!getPropIndDTOHelper().getPropuesta().getCambioMetodoOrden().getIdMetodo().equals(metodo.getIdMetodo())) {
                toRemove.add(metodo);
            }
        }
        getPropIndDTOHelper().getListaMetodoRevision().removeAll(toRemove);
    }

    public boolean validarAraceParte1() {
        boolean araceParte1 = false;
        if (getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO)
                || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORESTE)) {
            araceParte1 = true;
        }
        return araceParte1;
    }

    public boolean validarAraceParte2() {
        boolean araceParte2 = false;
        if (getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_OCCIDENTE) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_CENTRO) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_SUR)) {
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
            getPropIndBooleanHelper().setVisibleUnidad(true);
            getPropIndBooleanHelper().setVisibleRevision(false);
            getPropIndBooleanHelper().setVisibleFechaPre(false);
            getPropIndBooleanHelper().setVisibleFechaInf(false);
            getPropIndAttributeHelper().setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
            getPropIndAttributeHelper().setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComite"));
        } else if (validarAraceParte3()) {
            getPropIndBooleanHelper().setVisibleUnidad(false);
            getPropIndBooleanHelper().setVisibleRevision(false);
            getPropIndBooleanHelper().setVisibleFechaPre(false);
            getPropIndBooleanHelper().setVisibleFechaInf(false);
            getPropIndAttributeHelper().setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComiteRegional"));
            getPropIndAttributeHelper().setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComiteRegional"));
        }
    }

    public void reseteaMetodoSeleccionado() {
        BigDecimal idUnidadAdministrativa = getPropIndDTOHelper().getPropuesta().getIdArace();
        getPropIndDTOHelper().getPropuesta().setIdMetodo(new BigDecimal(-1));
        getPropIndBooleanHelper().setVisibleRevision(false);
        getPropIndBooleanHelper().setVisibleFechaPre(false);
        getPropIndBooleanHelper().setVisibleFechaInf(false);
        getPropIndDTOHelper().getPropuesta().setIdArace(idUnidadAdministrativa);
        if (getPropIndDTOHelper().getPropuesta().getIdArace() != null && getPropIndDTOHelper().getPropuesta().getIdArace().compareTo(BigDecimal.ZERO) > 0) {
            getPropIndAttributeHelper().setTerceroCuartoDigito(this.claveOperativa(getPropIndDTOHelper().getPropuesta().getIdArace()));
        }
        mostrarTipoRevision();
    }

    public void reseteaRevisionMetodo() {
        getPropIndDTOHelper().getPropuesta().setIdCausaProgramacion(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdTipoPropuesta(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdSector(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdSubprograma(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setPrioridadSugerida(null);
        getPropIndBooleanHelper().setVisibleRevision(false);
        getPropIndBooleanHelper().setVisibleFechaPre(false);
        getPropIndBooleanHelper().setVisibleFechaInf(false);
    }

    public void seleccionMetodo() {
        if (!getPropIndAttributeHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))
                && !getPropIndAttributeHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
            mostrarTipoRevision();
        } else {
            if (getPropIndAttributeHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage("formValidarInsumo:cmbUnidad", CAMPO_OBLIGATORIO);
            }

            if (getPropIndAttributeHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage("formValidarInsumo:cmbMetodo", CAMPO_OBLIGATORIO);
            }
        }
    }

    public void mostrarTipoRevision() {
        BigDecimal sedeUnidad;
        if (getPropIndDTOHelper().getPropuesta().getIdArace() != null) {
            sedeUnidad = getPropIndDTOHelper().getPropuesta().getIdArace();
        } else {
            sedeUnidad = new BigDecimal(getPropIndDTOHelper().getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace());
            getPropIndDTOHelper().getPropuesta().setIdArace(sedeUnidad);
        }

        if (getPropIndAttributeHelper().getAreaOrigen().equals(ACPPCE)) {
            validaMetodoArace(sedeUnidad, getPropIndDTOHelper().getPropuesta().getIdMetodo());
        } else {
            boolean validaAreaOrigen2
                    = getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORESTE) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_OCCIDENTE) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_CENTRO)
                    || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_SUR);
            boolean validaAreaOrigen
                    = getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE) || getPropIndAttributeHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO) || validaAreaOrigen2;
            if (validaAreaOrigen) {
                validaMetodoArace2(getPropIndDTOHelper().getPropuesta().getIdMetodo());
            }
        }
    }

    public void validaMetodoArace2(final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo.equals(ORG)) {
            getPropIndAttributeHelper().setSegundoDigito(P);
            getPropIndBooleanHelper().setVisibleRevision(true);
            getPropIndBooleanHelper().setVisibleFechaPre(true);
            getPropIndBooleanHelper().setVisibleFechaInf(false);
            getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE_REGIONAL);
        } else if (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE) || abreviaturaMetodo.equals(UCA)) {
            getPropIndAttributeHelper().setSegundoDigito(I);
            getPropIndBooleanHelper().setVisibleRevision(false);
            getPropIndBooleanHelper().setVisibleFechaPre(false);
            getPropIndBooleanHelper().setVisibleFechaInf(true);
            getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            getPropIndDTOHelper().getPropuesta().setIdRevision(null);
        } else if (abreviaturaMetodo.equals(Constantes.MCA)) {
            getPropIndAttributeHelper().setSegundoDigito(I);
            getPropIndBooleanHelper().setVisibleRevision(false);
            getPropIndBooleanHelper().setVisibleFechaPre(false);
            getPropIndBooleanHelper().setVisibleFechaInf(true);
            getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            getPropIndDTOHelper().getPropuesta().setIdRevision(null);
        }
    }

    public void validaMetodoArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo != null && sedeUnidad != null && !abreviaturaMetodo.equals(new BigDecimal(-1))) {
            boolean validaSedeUnidad = (sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE));
            boolean validaAbreviaturaMetodo
                    = (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE) || abreviaturaMetodo.equals(UCA));
            boolean validaMetodoMCA = abreviaturaMetodo.equals(Constantes.MCA);
            if ((sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE)) && abreviaturaMetodo.equals(ORG)) {
                if (getPropIndDTOHelper().getPropuesta().getPrioridadSugerida().equals("1")) {
                    getPropIndAttributeHelper().setSegundoDigito(I);
                    getPropIndBooleanHelper().setVisibleRevision(true);
                    getPropIndBooleanHelper().setVisibleFechaPre(false);
                    getPropIndBooleanHelper().setVisibleFechaInf(true);
                    getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
                } else if (getPropIndDTOHelper().getPropuesta().getPrioridadSugerida().equals("2") || getPropIndDTOHelper().getPropuesta().getPrioridadSugerida().equals("3")) {
                    getPropIndAttributeHelper().setSegundoDigito(P);
                    getPropIndBooleanHelper().setVisibleRevision(true);
                    getPropIndBooleanHelper().setVisibleFechaPre(true);
                    getPropIndBooleanHelper().setVisibleFechaInf(false);
                    getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE);
                } else {
                    getPropIndAttributeHelper().setSegundoDigito("");
                    getPropIndBooleanHelper().setVisibleRevision(false);
                    getPropIndBooleanHelper().setVisibleFechaPre(false);
                    getPropIndBooleanHelper().setVisibleFechaInf(false);
                }
            } else if (validaSedeUnidad && (validaAbreviaturaMetodo || validaMetodoMCA)) {
                getPropIndAttributeHelper().setSegundoDigito(I);
                getPropIndBooleanHelper().setVisibleRevision(false);
                getPropIndBooleanHelper().setVisibleFechaPre(false);
                getPropIndBooleanHelper().setVisibleFechaInf(true);
                getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
            } else {
                validaUnidadArace(sedeUnidad, abreviaturaMetodo);
            }
        }
    }

    public void validaUnidadArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        boolean validaUnidad
                = sedeUnidad.equals(ARACE_NORESTE) || sedeUnidad.equals(ARACE_OCCIDENTE) || sedeUnidad.equals(ARACE_CENTRO)
                || sedeUnidad.equals(ARACE_SUR);
        if (sedeUnidad.equals(ARACE_PACIFICO_NORTE) || sedeUnidad.equals(ARACE_NORTE_CENTRO) || validaUnidad) {
            if (abreviaturaMetodo.equals(ORG)) {
                getPropIndBooleanHelper().setVisibleRevision(true);
                getPropIndAttributeHelper().setSegundoDigito(I);
                getPropIndBooleanHelper().setVisibleFechaPre(false);
                getPropIndBooleanHelper().setVisibleFechaInf(true);
                getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            } else {
                getPropIndBooleanHelper().setVisibleRevision(false);
                getPropIndDTOHelper().getPropuesta().setIdRevision(null);

                getPropIndAttributeHelper().setSegundoDigito(I);
                getPropIndBooleanHelper().setVisibleFechaPre(false);
                getPropIndBooleanHelper().setVisibleFechaInf(true);
                getPropIndDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
            }
        }
    }

    public void descartarDocumento() {
        List<FecetDocExpediente> toRemove = new ArrayList<FecetDocExpediente>();
        for (FecetDocExpediente documento : getPropIndDTOHelper().getListaDocumento()) {
            if (this.documentoSeleccionado.equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        getPropIndDTOHelper().getListaDocumento().removeAll(toRemove);
        FacesUtil.addInfoMessage("formInsumo:msgExitoDescartarOficioAnexo", "Se descart\u00f3 el documento correctamente.");
    }

    public boolean validarImpuesto() {
        String campoRequerido = getMessageResourceString("lbl.propuestas.impuestos.validacion.campos.requerido");
        boolean datosValidos = true;
        boolean condicion = false;
        for (ImpuestoVO impuestos : getPropIndDTOHelper().getListaImpuestos()) {
            condicion = false;
            if (impuestos.getDescripcion().equals(buscarDescripcionImpuesto(getPropIndDTOHelper().getImpuestoVO().getIdImpuesto()))) {
                condicion = impuestos.getDescripcionConcepto().equals(buscarDescripcionConcepto(getPropIndDTOHelper().getImpuestoVO().getIdConcepto()));
                if (condicion) {
                    FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO, getMessageResourceString("lbl.propuestas.impuestos.existente"));
                    datosValidos = false;
                }
            }
        }

        if (getPropIndDTOHelper().getListaImpuestos() != null && getPropIndDTOHelper().getListaImpuestos().size() > 0) {
            if (getPropIndDTOHelper().getImpuestoVO().getIdImpuesto() != null && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) > 0
                    && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO, getMessageResourceString("lbl.propuestas.sin.impuestos.error"));
                datosValidos = false;
            }
        } else {
            if (getPropIndDTOHelper().getImpuestoVO().getIdImpuesto() != null && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) > 0
                    && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                getPropIndBooleanHelper().setHabilitaImpuesto(true);
            }
        }

        if (getPropIndDTOHelper().getImpuestoVO().getIdImpuesto() == null) {
            FacesUtil.addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
            datosValidos = false;
            getPropIndBooleanHelper().setHabilitaImpuesto(false);
        } else {
            if (getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) <= 0) {
                FacesUtil.addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
                datosValidos = false;
                getPropIndBooleanHelper().setHabilitaImpuesto(false);
            }
        }

        if (getPropIndDTOHelper().getImpuestoVO().getIdConcepto().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO);
            datosValidos = false;
            getPropIndBooleanHelper().setHabilitaImpuesto(false);
        } else {
            if (getPropIndDTOHelper().getImpuestoVO().getIdConcepto().compareTo(BigDecimal.ZERO) <= 0) {
                FacesUtil.addErrorMessage("formInsumo:cmbConcepto", CAMPO_OBLIGATORIO);
                datosValidos = false;
                getPropIndBooleanHelper().setHabilitaImpuesto(false);
            }
        }

        if (getPropIndDTOHelper().getImpuestoVO().getMonto() == null) {
            FacesUtil.addErrorMessage("formInsumo:txtMonto", campoRequerido);
            datosValidos = false;
        } else {

            boolean flgTipoImpuesto = (!getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA));
            boolean flgMontoNoCero = (getPropIndDTOHelper().getImpuestoVO().getMonto().compareTo(BigDecimal.ZERO) <= Constantes.CERO);

            if (flgTipoImpuesto && flgMontoNoCero) {
                FacesUtil.addErrorMessage("formInsumo:txtMonto", getMessageResourceString("lbl.propuestas.impuestos.validacion.importe.cero"));
                datosValidos = false;
            }
        }

        return datosValidos;
    }

    public String buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto) {
        String descripcion = "";
        for (FececTipoImpuesto impuesto : getPropIndDTOHelper().getListaTipoImpuesto()) {
            if (impuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                descripcion = impuesto.getDescripcion();
                break;
            }
        }
        return descripcion;
    }

    public String buscarDescripcionConcepto(final BigDecimal idConcepto) {
        String descripcionCocnepto = "";
        for (FececConcepto impuesto : getPropIndDTOHelper().getListaConcepto()) {
            if (impuesto.getIdConcepto() == idConcepto.intValue()) {
                descripcionCocnepto = impuesto.getDescripcion();
                break;
            }
        }
        return descripcionCocnepto;
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getPropIndDTOHelper().getListaImpuestos() != null) {
            for (ImpuestoVO imp : getPropIndDTOHelper().getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public void validaEstatusContacto(String rfc) {
        logger.info("--- Va a consultar medio contacto rfc:" + rfc);
        getPropIndAttributeHelper().setMnjestatusDeContacto(null);
        getValidarContribuyenteService().validaEstatusContacto(rfc);
        String mensajeEstatus = getValidarContribuyenteService().getEstatusDeContacto();
        String msjAmparadoPPEE = getValidarContribuyenteService().getMsjContactoAmparadoPPEE();
        if (mensajeEstatus != null) {
            getPropIndAttributeHelper().setEstatusDeContacto(getMessageResourceString(mensajeEstatus));
        }
        if (msjAmparadoPPEE != null) {
            getPropIndAttributeHelper().setMsjContactoAmparadoPPEE(getMessageResourceString(msjAmparadoPPEE));
        }
        getPropIndAttributeHelper().setMsjContactoAmparadoPPEE(null);
        if (getPropIndAttributeHelper().getEstatusDeContacto() != null && getPropIndAttributeHelper().getEstatusDeContacto().equals(getMessageResourceString(ConstantesPropuestas.CON_MEDIOS)) && getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() == null) {
            getPropIndDTOHelper().setPropuesta(new FecetPropuesta());
            getPropIndDTOHelper().setListaDocumento((new ArrayList<FecetDocExpediente>()));
            cargaCombos();
            activarCampos();
            getPropIndBooleanHelper().setMostrarFormulario(true);
        } else if (getPropIndAttributeHelper().getEstatusDeContacto() != null && getPropIndAttributeHelper().getEstatusDeContacto().equals(getMessageResourceString(ConstantesPropuestas.SIN_MEDIOS)) && getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() == null) {
            getPropIndAttributeHelper().setEstatusDeContacto(getMessageResourceString(ConstantesPropuestas.NO_MEDIOS));
            getPropIndAttributeHelper().setMnjestatusDeContacto(getMessageResourceString(ConstantesPropuestas.SIN_MEDIOS));
        } else {
            if (getPropIndAttributeHelper().getMsjContactoAmparadoPPEE() != null) {
                FecetDetalleContribuyente dto = new FecetDetalleContribuyente();
                FecetDetalleContribuyente existeDetalle = null;
                existeDetalle = this.getRegistrarPropuestaIndividualService().findByRfc(rfc);
                getPropIndAttributeHelper().setMnjestatusDeContacto(null);
                if (getPropIndAttributeHelper().getMsjContactoAmparadoPPEE().equals(getMessageResourceString(ConstantesPropuestas.CON_PPE))) {
                    dto.setPpee(new BigDecimal(1L));
                    dto.setAmparado(new BigDecimal(0L));
                    getPropIndDTOHelper().setContribuyente(new FecetContribuyente());
                    getPropIndDTOHelper().getContribuyente().setRfc(rfc);
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
            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
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
}
