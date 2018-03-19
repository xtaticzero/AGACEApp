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
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
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
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilInsumos;
import mx.gob.sat.siat.feagace.vista.insumos.helper.ValidarProcedenciaInsumoHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
@ViewScoped
@ManagedBean(name = "validarProcedenciaInsumoManagedBean")
public class ValidarProcedenciaInsumoManagedBean extends ValidarProcedenciaInsumoManagedBeanAbstract {

    private static final long serialVersionUID = 5658978872724642802L;

    private ValidarProcedenciaInsumoHelper validarProcedenciaInsumoHelper = new ValidarProcedenciaInsumoHelper();

    public void validarRFC() {
        ValidaMediosContactoBO validaMediosContactoBO;
        logger.info("Validando medios de contacto.");
        validaMediosContactoBO = validaMediosContacto(getVpInsumoDTOHelper().getInsumoSeleccionado().getFecetContribuyente().getRfc().toUpperCase());
        String dialog;
        if (validaMediosContactoBO.isFlag()) {
            getVpInsumoDTOHelper().getInsumoSeleccionado();
            if (getVpInsumoDTOHelper().getInsumoSeleccionado().getSemaforo() != null
                    && getVpInsumoDTOHelper().getInsumoSeleccionado().getSemaforo() == Constantes.ENTERO_OCHO) {
                getValidarProcedenciaInsumoService().aceptarInsumoVencido(getVpInsumoDTOHelper().getInsumoSeleccionado());
            } else {
                getValidarProcedenciaInsumoService().aceptarInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado());
            }
            setMensajeValidacion(FacesUtil.getMessageResourceString("msj.insumo.aceptado"));
            dialog = "confirmarAceptarInsumo";
        } else {
            logger.info("No cumple con los permisos necesarios para registrar un Insumo.");
            setMensajeValidacion(validaMediosContactoBO.getMessage());
            dialog = "confirmarValidacion";
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(String.format("PF('%s').show();", dialog));
    }

    public void cargaDocumentos() {
        reseteaPaneles();
        setJustificacionDetalle(getVpInsumoDTOHelper().getInsumoSeleccionado().getJustificacion());
        cargaListaDocumentos();
        getVpInsumoAttributesHelper().setMostrarPanelInsumo(true);
    }

    public void cargaArchivoRechazo(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                setArchivoRechazo(event.getFile());
                FecetDocrechazoinsumo documento = new FecetDocrechazoinsumo();
                documento.setFechaCreacion(new Date());
                documento.setNombreArchivo(CargaArchivoUtilInsumos.limpiarPathArchivo(CargaArchivoUtilInsumos.aplicarCodificacionTexto(getArchivoRechazo().getFileName())));
                documento.setArchivo(getArchivoRechazo().getInputstream());
                if (validarProcedenciaInsumoHelper.validaNombreArchivoRechazo(getVpInsumoDTOHelper().getListaDocRechazoInsumo(), documento.getNombreArchivo())) {
                    FacesUtil.addErrorMessage(null, "Este archivo ya fue cargado", "");
                } else {
                    getVpInsumoDTOHelper().getListaDocRechazoInsumo().add(documento);
                    FacesUtil.addInfoMessage(null, Constantes.ARCHIVO_CARGADO, "");
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
                docRetroInsumo.setNombreArchivo(CargaArchivoUtilInsumos.limpiarPathArchivo(CargaArchivoUtilInsumos.aplicarCodificacionTexto(getArchivoRetroalimentacion().getFileName())));
                docRetroInsumo.setArchivo(getArchivoRetroalimentacion().getInputstream());
                if (validarProcedenciaInsumoHelper.validaNombreArchivoRetroalimentacion(getVpInsumoDTOHelper().getListaDocRetroInsumo(), docRetroInsumo.getNombreArchivo())) {
                    FacesUtil.addErrorMessage(null, "Este archivo ya fue cargado", "");
                } else {
                    getVpInsumoDTOHelper().getListaDocRetroInsumo().add(docRetroInsumo);
                    FacesUtil.addInfoMessage(null, Constantes.ARCHIVO_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

    public void validarRechazoInsumo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getVpInsumoDTOHelper().getListaDocRechazoInsumo() == null
                || getVpInsumoDTOHelper().getListaDocRechazoInsumo().isEmpty()) {
            FacesUtil.addErrorMessage(null, "Debe cargar un documento", "");
        } else if (getVpInsumoAttributesHelper().getMotivoRechazo() == null
                || getVpInsumoAttributesHelper().getMotivoRechazo().trim().equals("")) {
            getVpInsumoAttributesHelper().setMotivoRechazo("");
            FacesUtil.addErrorMessage("formValidarInsumo:txaMotivoRechazo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
        } else {
            requestContext.execute("PF('confirmarRechazo').show();");
        }
    }

    public void validarRetroalimentarInsumo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (getVpInsumoDTOHelper().getListaDocRetroInsumo() == null
                || getVpInsumoDTOHelper().getListaDocRetroInsumo().isEmpty()) {
            FacesUtil.addErrorMessage(null, "Debe cargar un documento", "");
        } else if (getVpInsumoAttributesHelper().getMotivoRetroalimentacion() == null
                || getVpInsumoAttributesHelper().getMotivoRetroalimentacion().trim().equals("")) {
            getVpInsumoAttributesHelper().setMotivoRetroalimentacion("");
            FacesUtil.addErrorMessage("formValidarInsumo:txaMotivoRetroalimentar", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
        } else {
            requestContext.execute("PF('confirmarRetroalimentacion').show();");
        }
    }

    public void descartarDocumentoRetro() {
        Iterator<FecetDocretroinsumo> iterador = getVpInsumoDTOHelper().getListaDocRetroInsumo().iterator();
        FecetDocretroinsumo documento;
        while (iterador.hasNext()) {
            documento = iterador.next();
            if (getVpInsumoDTOHelper().getDocretroInsumoSeleccionado().getNombreArchivo().equals(documento.getNombreArchivo())) {
                iterador.remove();
                break;
            }
        }
        FacesUtil.addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void descartarDocumentoRechazo() {
        Iterator<FecetDocrechazoinsumo> iterador = getVpInsumoDTOHelper().getListaDocRechazoInsumo().iterator();
        FecetDocrechazoinsumo documento;
        while (iterador.hasNext()) {
            documento = iterador.next();
            if (getVpInsumoDTOHelper().getDocrechazoInsumoSeleccionado().getNombreArchivo().equals(documento.getNombreArchivo())) {
                iterador.remove();
                break;
            }
        }
        FacesUtil.addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void retroalimentarInsumo() throws NegocioException {
        try {
            FecetRetroalimentacionInsumo retroalimentacion = new FecetRetroalimentacionInsumo();
            retroalimentacion.setIdRetroalimentacionInsumo(getValidarProcedenciaInsumoService().getIdFecetRetroalimentacionInsumo());
            retroalimentacion.setIdInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdInsumo());
            retroalimentacion.setMotivoSubadministrador(getVpInsumoAttributesHelper().getMotivoRetroalimentacion());
            retroalimentacion.setFechaCreacion(getVpInsumoDTOHelper().getInsumoSeleccionado().getFechaCreacion());
            retroalimentacion.setRfcRetroalimentacion(getRFCSession());
            retroalimentacion.setEstatus("2");
            getValidarProcedenciaInsumoService().retroalimentarInsumo(retroalimentacion, getVpInsumoDTOHelper().getListaDocRetroInsumo(),
                    getVpInsumoDTOHelper().getInsumoSeleccionado(), getArchivoRetroalimentacion().getInputstream(), getVpInsumoDTOHelper().getEmpleadoDTO());
            enviarCorreoSolicitudRetroalimentacionInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado());
            getVpInsumoAttributesHelper().setMotivoRetroalimentacion("");
            init();
            FacesUtil.addInfoMessage(null, "El Insumo "
                    + getVpInsumoDTOHelper().getInsumoSeleccionado().getIdRegistro()
                    + ", ha sido enviado al \u00e1rea correspondiente para su retroalimentaci\u00f3n.", "");
            reseteaRetroalimentacion();
        } catch (NegocioException e) {
            getVpInsumoAttributesHelper().setMotivoRetroalimentacion("");
            logger.error("No se pudo registrar la retroalimentacion del insumo " + e.getCause(), e);
        } catch (IOException e) {
            throw new NegocioException("No se pudo guardar el archivo ", e);
        }
    }

    public void rechazarInsumo() throws NegocioException {
        try {
            FecetRechazoInsumo rechazoInsumo = new FecetRechazoInsumo();
            rechazoInsumo.setIdInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdInsumo());
            rechazoInsumo.setDescripcion(getVpInsumoAttributesHelper().getMotivoRechazo());
            rechazoInsumo.setFechaRechazo(new Date());
            rechazoInsumo.setRfcRechazo(getRFCSession());
            if (getVpInsumoDTOHelper().getInsumoSeleccionado().getSemaforo() != null
                    && getVpInsumoDTOHelper().getInsumoSeleccionado().getSemaforo() != Constantes.ENTERO_OCHO) {
                getValidarProcedenciaInsumoService().rechazarInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado(), rechazoInsumo, getVpInsumoDTOHelper().getListaDocRechazoInsumo());
            } else {
                getValidarProcedenciaInsumoService().rechazarInsumoVencido(getVpInsumoDTOHelper().getInsumoSeleccionado(), rechazoInsumo, getVpInsumoDTOHelper().getListaDocRechazoInsumo());
            }
            enviarCorreoRechazoInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado());
            getVpInsumoAttributesHelper().setMotivoRechazo("");
            init();
            FacesUtil.addInfoMessage(null, "El Insumo "
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
        getSession().setAttribute("empleado", getVpInsumoDTOHelper().getEmpleadoDTO());
        getSession().setAttribute("documentosInsumo", getVpInsumoDTOHelper().getListaDocumentoInsumo());

        return "../../propuestas/programador/indexCrear.jsf?faces-redirect=true";

    }

    public void setPropuestaDeInsumo() {
        getVpInsumoDTOHelper().setPropuesta(new FecetPropuesta());
        getVpInsumoDTOHelper().getPropuesta().setArchivo(getVpInsumoDTOHelper().getInsumoSeleccionado().getArchivo());
        getVpInsumoDTOHelper().getPropuesta().setFececArace(getVpInsumoDTOHelper().getInsumoSeleccionado().getFececArace());
        getVpInsumoDTOHelper().getPropuesta().setEmpleadoDto(getVpInsumoDTOHelper().getInsumoSeleccionado().getEmpleado());
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
                    FacesUtil.addErrorMessage("formValidarInsumo:cmbUnidad", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
                }

                if (getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
                    FacesUtil.addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
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
                    FacesUtil.addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
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
        FacesUtil.addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void agregarImpuesto() {
        if (validarProcedenciaInsumoHelper.validarImpuesto(getVpInsumoDTOHelper())) {
            getVpInsumoDTOHelper().getImpuestoVO().setDescripcion(validarProcedenciaInsumoHelper.buscarDescripcionImpuesto(getVpInsumoDTOHelper().getImpuestoVO().getIdImpuesto(), getVpInsumoDTOHelper()));
            getVpInsumoDTOHelper().getListaImpuestos().add(getVpInsumoDTOHelper().getImpuestoVO());
            getVpInsumoDTOHelper().setImpuestoVO(new ImpuestoVO());
        }
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

    public void descartarImpuesto() {
        List<ImpuestoVO> toRemove = new ArrayList<ImpuestoVO>();
        for (ImpuestoVO impuesto : getVpInsumoDTOHelper().getListaImpuestos()) {
            if (getVpInsumoDTOHelper().getImpuestoSeleccionado().equals(impuesto)) {
                toRemove.add(impuesto);
                break;
            }
        }
        getVpInsumoDTOHelper().getListaImpuestos().removeAll(toRemove);
        FacesUtil.addInfoMessage(null, "Se descarto el impuesto correctamente.", "");
    }

    public void registrarInsumoPropuesta() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (validarRegistroInsumo()) {
            requestContext.execute("PF('mensajeRegistrar').show();");
        }
    }

    public void visualizaRetroalimentacionSubAdmin() {
        getVpInsumoDTOHelper().setInsumosRetroalimentadoCargado(new ArrayList<FecetRetroalimentacionInsumo>());
        getVpInsumoDTOHelper().getInsumosRetroalimentadoCargado().add(getVpInsumoDTOHelper().getRetroalimentacionInsumo());
        getVpInsumoDTOHelper().setListaDocRetroInsumo(getValidarProcedenciaInsumoService().getDocumentosRetroalimentadosByIdRetroInsumo(getVpInsumoDTOHelper().getRetroalimentacionInsumo().getIdRetroalimentacionInsumo(), Constantes.USUARIO_VALIDADOR_INSUMOS));
        if (getVpInsumoDTOHelper().getListaDocRetroInsumo() != null && !getVpInsumoDTOHelper().getListaDocRetroInsumo().isEmpty()) {
            getVpInsumoDTOHelper().getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(new BigDecimal(getVpInsumoDTOHelper().getListaDocRetroInsumo().size()));
        } else {
            getVpInsumoDTOHelper().getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(BigDecimal.ZERO);
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('historicoSolicitudRetroInsumo').show();");

    }

    public void visualizaDocRetroalimentacionSubAdmin() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }

    public void visualizaDocRetroalimentacionAciace() {
        getVpInsumoDTOHelper().setListaDocRetroInsumo(getValidarProcedenciaInsumoService().getDocumentosRetroalimentadosByIdRetroInsumo(getVpInsumoDTOHelper().getRetroalimentacionInsumo().getIdRetroalimentacionInsumo(), Constantes.USUARIO_INSUMOS));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }
}
