/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.vista.insumos.validar;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import static mx.gob.sat.siat.feagace.vista.util.FacesUtil.addInfoMessage;

/**
 * 
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 * 
 */
@SessionScoped
@ManagedBean(name = "validarProcedenciaInsumoManagedBean")
public class ValidarProcedenciaInsumoManagedBean extends ValidarProcedenciaInsumoManagedBeanAbstract {

    private static final long serialVersionUID = 5658978872724642802L;

    private String mensajeValidacion;

    public String validarRFC() {
        ValidaMediosContactoBO validaMediosContactoBO;

        logger.info("Validando medios de contacto.");
        validaMediosContactoBO = validaMediosContacto(getVpInsumoDTOHelper().getInsumoSeleccionado().getFecetContribuyente().getRfc().toUpperCase());

        if (validaMediosContactoBO.isFlag()) {
            return activarComplementarInsumo();
        } else {
            logger.info("No cumple con los permisos necesarios para registrar un Insumo.");

            mensajeValidacion = validaMediosContactoBO.getMessage();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('confirmarValidacion').show();");
            return null;
        }
    }

    public void cargaDocumentos() {
        reseteaPaneles();
        cargaListaDocumentos();
        getVpInsumoAttributesHelper().setMostrarPanelInsumo(true);
    }

    public void cargaArchivoRechazo(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                setArchivoRechazo(event.getFile());
                FecetDocrechazoinsumo documento = new FecetDocrechazoinsumo();
                documento.setFechaCreacion(new Date());
                documento.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getArchivoRechazo().getFileName())));
                documento.setArchivo(getArchivoRechazo().getInputstream());
                if (validaNombreArchivoRechazo(getVpInsumoDTOHelper().getListaDocRechazoInsumo(), documento.getNombreArchivo())) {
                    addErrorMessage(null, "Este archivo ya fue cargado", "");
                } else {
                    getVpInsumoDTOHelper().getListaDocRechazoInsumo().add(documento);
                    addInfoMessage(null, Constantes.ARCHIVO_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

   

    public void limpiaListas() {
        getVpInsumoDTOHelper().getListaDocRetroInsumo().clear();
        getVpInsumoDTOHelper().getListaDocRechazoInsumo().clear();
        getVpInsumoAttributesHelper().setMotivoRechazo(null);
        getVpInsumoAttributesHelper().setMotivoRetroalimentacion(null);
        setArchivoRetroalimentacion(null);
    }

    public void cargaArchivoRetroalimentar(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                setArchivoRetroalimentacion(event.getFile());
                FecetDocretroinsumo docRetroInsumo = new FecetDocretroinsumo();
                docRetroInsumo.setFechaCreacion(new Date());
                docRetroInsumo.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getArchivoRetroalimentacion().getFileName())));
                docRetroInsumo.setArchivo(getArchivoRetroalimentacion().getInputstream());
                if (validaNombreArchivoRetroalimentacion(getVpInsumoDTOHelper().getListaDocRetroInsumo(), docRetroInsumo.getNombreArchivo())) {
                    addErrorMessage(null, "Este archivo ya fue cargado", "");
                } else {
                    getVpInsumoDTOHelper().getListaDocRetroInsumo().add(docRetroInsumo);
                    addInfoMessage(null, Constantes.ARCHIVO_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

    private boolean validaNombreArchivoRetroalimentacion(List<FecetDocretroinsumo> listaDocumento,
            String nombre) {
        Boolean archivoDuplicado = false;
        for (FecetDocretroinsumo fecetDocretroinsumo : listaDocumento) {
            if (fecetDocretroinsumo.getNombreArchivo().equals(nombre)) {
                archivoDuplicado = true;
                break;
            }
        }
        return archivoDuplicado;
    }

    private boolean validaNombreArchivoRechazo(List<FecetDocrechazoinsumo> listaDocumento, String nombre) {
        Boolean archivoDuplicado = false;
        for (FecetDocrechazoinsumo fecetDocrechazoinsumo : listaDocumento) {
            if (fecetDocrechazoinsumo.getNombreArchivo().equals(nombre)) {
                archivoDuplicado = true;
                break;
            }
        }
        return archivoDuplicado;
    }

    public void validarRechazoInsumo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getVpInsumoDTOHelper().getListaDocRechazoInsumo() == null
                || getVpInsumoDTOHelper().getListaDocRechazoInsumo().isEmpty()) {
            addErrorMessage(null, "Debe cargar un documento", "");
        } else if (getVpInsumoAttributesHelper().getMotivoRechazo() == null
                || getVpInsumoAttributesHelper().getMotivoRechazo().equals("")) {
            addErrorMessage("formValidarInsumo:txaMotivoRechazo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
        } else {
            requestContext.execute("PF('confirmarRechazo').show();");
        }
    }

    public void validarRetroalimentarInsumo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getVpInsumoDTOHelper().getListaDocRetroInsumo() == null
                || getVpInsumoDTOHelper().getListaDocRetroInsumo().isEmpty()) {
            addErrorMessage(null, "Debe cargar un documento", "");
        } else if (getVpInsumoAttributesHelper().getMotivoRetroalimentacion() == null
                || getVpInsumoAttributesHelper().getMotivoRetroalimentacion().equals("")) {
            addErrorMessage("formValidarInsumo:txaMotivoRetroalimentar", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
        } else {
            requestContext.execute("PF('confirmarRetroalimentacion').show();");
        }
    }

    public void descartarDocumentoRetro() {
        List<FecetDocretroinsumo> toRemove = new ArrayList<FecetDocretroinsumo>();
        for (FecetDocretroinsumo documento : getVpInsumoDTOHelper().getListaDocRetroInsumo()) {
            if (getVpInsumoDTOHelper().getDocretroInsumoSeleccionado().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }

        getVpInsumoDTOHelper().getListaDocRetroInsumo().removeAll(toRemove);
        addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void descartarDocumentoRechazo() {
        List<FecetDocrechazoinsumo> toRemove = new ArrayList<FecetDocrechazoinsumo>();
        for (FecetDocrechazoinsumo documento : getVpInsumoDTOHelper().getListaDocRechazoInsumo()) {
            if (getVpInsumoDTOHelper().getDocrechazoInsumoSeleccionado().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }

        getVpInsumoDTOHelper().getListaDocRechazoInsumo().removeAll(toRemove);
        addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

  
    public void rechazarInsumo() throws NegocioException {
        try {
            FecetRechazoInsumo rechazoInsumo = new FecetRechazoInsumo();
            rechazoInsumo.setIdInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdInsumo());
            rechazoInsumo.setDescripcion(getVpInsumoAttributesHelper().getMotivoRechazo());
            rechazoInsumo.setFechaRechazo(new Date());
            rechazoInsumo.setRfcRechazo(getRFCSession());

            getValidarProcedenciaInsumoService().rechazarInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado(), rechazoInsumo, getVpInsumoDTOHelper().getListaDocRechazoInsumo());
           
            getVpInsumoAttributesHelper().setMotivoRechazo("");
            init();
            addInfoMessage(null, "El Insumo "
                    + getVpInsumoDTOHelper().getInsumoSeleccionado().getIdRegistro()
                    + " no ha sido aprobado y fue enviado al \u00e1rea correspondiente.", "");

            reseteaRechazo();
        } catch (NegocioException e) {
            logger.error("No se pudo registrar el rechazo del insumo " + e.getCause(), e);
        }
    }

    public void mostrarHistoricoRetroalimentacion() {
        getVpInsumoAttributesHelper().setIdInsumoHistorico("");
        getVpInsumoDTOHelper().setInsumosRetroalimentadosContador(new ArrayList<FecetRetroalimentacionInsumo>());
        getVpInsumoDTOHelper().setDocretroInsumoCargadoSeleccionado(new FecetDocretroinsumo());

        getVpInsumoAttributesHelper().setMostrarTablaArchivosRetro(false);

        getVpInsumoDTOHelper().setInsumosRetroalimentadosContador(getSeguimientoInsumosService().getInsumosRetroalimentados(getVpInsumoDTOHelper().getInsumoSeleccionado()));

        if (!getVpInsumoDTOHelper().getInsumosRetroalimentadosContador().isEmpty()) {
            getVpInsumoAttributesHelper().setIdInsumoHistorico(getVpInsumoDTOHelper().getInsumosRetroalimentadosContador().get(0).getIdInsumo().toString());
        }

    }

    public void mostrarArchivosRetroalimentacion() {
        getVpInsumoDTOHelper().setListaDocretroInsumoCargados(new ArrayList<FecetDocretroinsumo>());
        getVpInsumoAttributesHelper().setMostrarTablaArchivosRetro(true);

        getVpInsumoDTOHelper().setListaDocretroInsumoCargados(getValidarProcedenciaInsumoService().getDocumentosRetroalimentados(getVpInsumoDTOHelper().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo()));

    }

    public void limpiaMostrarArchivosRetroalimentacion() {
        getVpInsumoAttributesHelper().setMostrarTablaArchivosRetro(false);
    }

    public String activarComplementarInsumo() {
        getSession().setAttribute("insumo", getVpInsumoDTOHelper().getInsumoSeleccionado());
        getSession().setAttribute("empleado", getVpInsumoDTOHelper().getFececEmpleado());
        getSession().setAttribute("documentosInsumo", getVpInsumoDTOHelper().getListaDocumentoInsumo());

        return "../../propuestas/programador/indexCrear.jsf?faces-redirect=true";

    }

    public void setPropuestaDeInsumo() {
        getVpInsumoDTOHelper().setPropuesta(new FecetPropuesta());
        getVpInsumoDTOHelper().getPropuesta().setArchivo(getVpInsumoDTOHelper().getInsumoSeleccionado().getArchivo());
        getVpInsumoDTOHelper().getPropuesta().setFececArace(getVpInsumoDTOHelper().getInsumoSeleccionado().getFececArace());
         getVpInsumoDTOHelper().getPropuesta().setFececEstatus(getVpInsumoDTOHelper().getInsumoSeleccionado().getFececEstatus());
        getVpInsumoDTOHelper().getPropuesta().setFececSector(getVpInsumoDTOHelper().getInsumoSeleccionado().getFececSector());
        getVpInsumoDTOHelper().getPropuesta().setFececSubprograma(getVpInsumoDTOHelper().getInsumoSeleccionado().getFececSubprograma());
        getVpInsumoDTOHelper().getPropuesta().setFecetContribuyente(getVpInsumoDTOHelper().getInsumoSeleccionado().getFecetContribuyente());
        getVpInsumoDTOHelper().getPropuesta().setFechaBaja(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaBaja());
        getVpInsumoDTOHelper().getPropuesta().setFechaCreacion(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaCreacion());
        getVpInsumoDTOHelper().getPropuesta().setFechaFinPeriodo(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaFinPeriodo());
        getVpInsumoDTOHelper().getPropuesta().setFechaInicioPeriodo(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaInicioPeriodo());
        getVpInsumoDTOHelper().getPropuesta().setIdArace(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdArace());
        getVpInsumoDTOHelper().getPropuesta().setIdContribuyente(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdContribuyente());
        getVpInsumoDTOHelper().getPropuesta().setIdEstatus(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdEstatus());
        getVpInsumoDTOHelper().getPropuesta().setIdInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdInsumo());
        getVpInsumoDTOHelper().getPropuesta().setIdRegistro(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdRegistro());
        getVpInsumoDTOHelper().getPropuesta().setIdSector(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdSector());
        getVpInsumoDTOHelper().getPropuesta().setIdSubprograma(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdSubprograma());
        getVpInsumoDTOHelper().getPropuesta().setPrioridad(getVpInsumoDTOHelper().getInsumoSeleccionado().getPrioridad());
        getVpInsumoDTOHelper().getPropuesta().setRfcAdministrador(getVpInsumoDTOHelper().getInsumoSeleccionado().getRfcAdministrador());
        getVpInsumoDTOHelper().getPropuesta().setRfcCreacion(getVpInsumoDTOHelper().getInsumoSeleccionado().getRfcCreacion());
        getVpInsumoDTOHelper().getPropuesta().setRfcSubadministrador(getVpInsumoDTOHelper().getInsumoSeleccionado().getRfcSubadministrador());
    }

    public void cargaCombos() {
        cargaUnidad();
        cargaMetodoRevision();
        cargaTipoPropuesta();
        cargaCausaProgramacion();
        cargaTipoRevision();
        cargaTipoImpuesto();
        getVpInsumoDTOHelper().setListaImpuestos(new ArrayList<ImpuestoVO>());
    }

    private void cargaUnidad() {
        getVpInsumoDTOHelper().setListaUnidades(getValidarProcedenciaInsumoService().getCatalogoUnidadInsumos());

    }

    private void cargaMetodoRevision() {
        getVpInsumoDTOHelper().setListaMetodoRevision(getValidarProcedenciaInsumoService().getCatalogoMetodo());
    }

    private void cargaTipoPropuesta() {
        getVpInsumoDTOHelper().setListaTipoPropuesta(getValidarProcedenciaInsumoService().getCatalogoTipoPropuesta());
    }

    private void cargaCausaProgramacion() {
        getVpInsumoDTOHelper().setListaCausaProgramacion(getValidarProcedenciaInsumoService().getCatalogoCausaProgramacion());
    }

    private void cargaTipoRevision() {
        getVpInsumoDTOHelper().setListaTipoRevision(getValidarProcedenciaInsumoService().getCatalogoRevision());
    }

    private void cargaTipoImpuesto() {
        getVpInsumoDTOHelper().setListaTipoImpuesto(getValidarProcedenciaInsumoService().getCatalogoImpuesto());
    }

    public void reseteaMetodoSeleccionado() {
        getVpInsumoAttributesHelper().setIdMetodoSeleccionado(BigDecimal.valueOf(-1L));
    }

    public void reseteaRechazo() {
        getVpInsumoAttributesHelper().setMotivoRechazo(null);
        setArchivoRechazo(null);
    }

    public void reseteaRetroalimentacion() {
        getVpInsumoAttributesHelper().setMotivoRetroalimentacion(null);
        setArchivoRetroalimentacion(null);
    }

    public void validaMetodoRevisionFechas() {
        BigDecimal sedeUnidad = getVpInsumoAttributesHelper().getIdAraceSeleccionada();
        BigDecimal idMetodo = getVpInsumoAttributesHelper().getIdMetodoSeleccionado();
        boolean validaSedeUnidad = (sedeUnidad.equals(Constantes.ACAOCE) || sedeUnidad.equals(Constantes.ACOECE));
        boolean validadIdMetodo = (idMetodo.equals(Constantes.EHO) || idMetodo.equals(Constantes.REE) || idMetodo.equals(Constantes.UCA));
        if (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ACPPCE)) {
            if (!getVpInsumoAttributesHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))
                    && !getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                if ((sedeUnidad.equals(Constantes.ACAOCE) || sedeUnidad.equals(Constantes.ACOECE))
                        && idMetodo.equals(Constantes.ORG)) {
                    getVpInsumoAttributesHelper().setSegundoDigito(Constantes.P);
                    getVpInsumoAttributesHelper().setVisibleRevision(true);
                    getVpInsumoAttributesHelper().setVisibleFechaPre(true);
                    getVpInsumoAttributesHelper().setVisibleFechaInf(false);
                    getVpInsumoDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE);
                } else if (validaSedeUnidad && validadIdMetodo) {
                    getVpInsumoAttributesHelper().setSegundoDigito(Constantes.I);
                    getVpInsumoAttributesHelper().setVisibleRevision(false);
                    getVpInsumoAttributesHelper().setVisibleFechaPre(false);
                    getVpInsumoAttributesHelper().setVisibleFechaInf(true);
                    getVpInsumoDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
                } else {
                    boolean validaUnidad = sedeUnidad.equals(Constantes.ARACE_NORESTE)
                            || sedeUnidad.equals(Constantes.ARACE_OCCIDENTE)
                            || sedeUnidad.equals(Constantes.ARACE_CENTRO)
                            || sedeUnidad.equals(Constantes.ARACE_SUR);
                    if (sedeUnidad.equals(Constantes.ARACE_PACIFICO_NORTE)
                            || sedeUnidad.equals(Constantes.ARACE_NORTE_CENTRO) || validaUnidad) {
                        if (idMetodo.equals(Constantes.ORG)) {
                            getVpInsumoAttributesHelper().setVisibleRevision(true);
                        } else {
                            getVpInsumoAttributesHelper().setVisibleRevision(false);
                        }
                        getVpInsumoAttributesHelper().setSegundoDigito(Constantes.I);
                        getVpInsumoAttributesHelper().setVisibleFechaPre(false);
                        getVpInsumoAttributesHelper().setVisibleFechaInf(true);
                        getVpInsumoDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE_REGIONAL);
                    }
                }
            } else {
                if (getVpInsumoAttributesHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))) {
                    addErrorMessage("formValidarInsumo:cmbUnidad", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
                }

                if (getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                    addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
                }
            }
        } else {
            boolean areaOrigen = (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_PACIFICO_NORTE))
                    || (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_NORTE_CENTRO))
                    || (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_NORESTE));
            if (areaOrigen
                    || (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_OCCIDENTE))
                    || (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_CENTRO))
                    || (getVpInsumoAttributesHelper().getAreaOrigen().equals(Constantes.ARACE_SUR))) {
                if (!getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                    if (idMetodo.equals(Constantes.ORG)) {
                        getVpInsumoAttributesHelper().setSegundoDigito(Constantes.P);
                        getVpInsumoAttributesHelper().setVisibleRevision(true);
                        getVpInsumoAttributesHelper().setVisibleFechaPre(true);
                        getVpInsumoAttributesHelper().setVisibleFechaInf(false);
                        getVpInsumoDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_PRESENTADA_COMITE);
                    } else {
                        getVpInsumoAttributesHelper().setSegundoDigito(Constantes.I);
                        getVpInsumoAttributesHelper().setVisibleRevision(false);
                        getVpInsumoAttributesHelper().setVisibleFechaPre(false);
                        getVpInsumoAttributesHelper().setVisibleFechaInf(true);
                        getVpInsumoDTOHelper().getPropuesta().setInformePresentacion(Constantes.PROPUESTA_INFORMADA_COMITE);
                    }

                } else {
                    addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
                }
            }
        }
    }

    public String buscarAbreviacionMetodo(final BigDecimal idMetodo) {
        String abreviatura = "";
        for (FececMetodo metodo : getVpInsumoDTOHelper().getListaMetodoRevision()) {
            if (metodo.getIdMetodo().equals(idMetodo)) {
                abreviatura = metodo.getAbreviatura();
                break;
            }
        }
        return abreviatura;
    }

    public void descartarDocumento() {
        List<FecetDocExpediente> toRemove = new ArrayList<FecetDocExpediente>();
        for (FecetDocExpediente documento : getVpInsumoDTOHelper().getListaDocumento()) {
            if (getVpInsumoDTOHelper().getDocumentoSeleccionado().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        getVpInsumoDTOHelper().getListaDocumento().removeAll(toRemove);
        addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void agregarImpuesto() {
        if (validarImpuesto()) {
            getVpInsumoDTOHelper().getImpuestoVO().setDescripcion(buscarDescripcionImpuesto(getVpInsumoDTOHelper().getImpuestoVO().getIdImpuesto()));
            getVpInsumoDTOHelper().getListaImpuestos().add(getVpInsumoDTOHelper().getImpuestoVO());
            getVpInsumoDTOHelper().setImpuestoVO(new ImpuestoVO());
        }
    }

    public boolean validarImpuesto() {
        boolean datosValidos = true;

        for (ImpuestoVO impuestos : getVpInsumoDTOHelper().getListaImpuestos()) {
            if (impuestos.getDescripcion().equals(buscarDescripcionImpuesto(getVpInsumoDTOHelper().getImpuestoVO().getIdImpuesto()))) {
                addErrorMessage("formValidarInsumo:cmbImpuesto", "El impuesto ya habia sido registrado");
                datosValidos = false;
            }
        }

        if (getVpInsumoDTOHelper().getImpuestoVO().getIdImpuesto() == null) {
            addErrorMessage("formValidarInsumo:cmbImpuesto", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getImpuestoVO().getMonto() == null) {
            addErrorMessage("formValidarInsumo:txtMontoImpuesto", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getImpuestoVO().getFechaInicial() == null
                || getVpInsumoDTOHelper().getImpuestoVO().getFechaFin() == null) {
            addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "Campo(s) Obligatorio(s) *");
            datosValidos = false;
        } else {
            if (getVpInsumoDTOHelper().getImpuestoVO().getFechaInicial().before(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaInicioPeriodo())) {
                addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha Perido Del no puede ser menor a la fecha Periodo Propuesto Del");
                datosValidos = false;
            }
            if (getVpInsumoDTOHelper().getImpuestoVO().getFechaFin().after(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaFinPeriodo())) {
                addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha Perido Al no puede ser mayor a la fecha Periodo Propuesto Al");
                datosValidos = false;
            }
            if (getVpInsumoDTOHelper().getImpuestoVO().getFechaInicial().after(getVpInsumoDTOHelper().getImpuestoVO().getFechaFin())) {
                addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha final no puede ser menor a la inicial");
                datosValidos = false;
            }
        }
        return datosValidos;
    }

    private String buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto) {
        String descripcion = "";
        for (FececTipoImpuesto impuesto : getVpInsumoDTOHelper().getListaTipoImpuesto()) {
            if (impuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                descripcion = impuesto.getDescripcion();
                break;
            }
        }
        return descripcion;
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;

        if (getVpInsumoDTOHelper().getListaImpuestos() != null
                && getVpInsumoDTOHelper().getListaImpuestos().size() > 0) {
            for (ImpuestoVO imp : getVpInsumoDTOHelper().getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }

        return totalImpuesto;
    }

    void descartarImpuesto() {
        List<ImpuestoVO> toRemove = new ArrayList<ImpuestoVO>();
        for (ImpuestoVO impuesto : getVpInsumoDTOHelper().getListaImpuestos()) {
            if (getVpInsumoDTOHelper().getImpuestoSeleccionado().equals(impuesto)) {
                toRemove.add(impuesto);
                break;
            }
        }
        getVpInsumoDTOHelper().getListaImpuestos().removeAll(toRemove);
        addInfoMessage(null, "Se descarto el impuesto correctamente.", "");
    }

    public void registrarInsumoPropuesta() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (validarRegistroInsumo()) {

            requestContext.execute("PF('mensajeRegistrar').show();");

        }
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

}
