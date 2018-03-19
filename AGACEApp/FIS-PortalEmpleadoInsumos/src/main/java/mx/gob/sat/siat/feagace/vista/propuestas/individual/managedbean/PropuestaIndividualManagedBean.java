/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.vista.propuestas.individual.managedbean;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilInsumos;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilInsumos;
import mx.gob.sat.siat.feagace.vista.insumos.validar.ValidarProcedenciaInsumoManagedBean;
import mx.gob.sat.siat.feagace.vista.propuestas.individual.PropuestaIndividualManagedBeanAbstract;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
@ManagedBean(name = "propuestaIndividualManagedBean")
@ViewScoped
public class PropuestaIndividualManagedBean extends PropuestaIndividualManagedBeanAbstract {

    private static final long serialVersionUID = 4674427563623709676L;

    /**
     * Metodo que permite capturar un impuesto en cero
     *
     */
    public void agregarImpuestoCero() {
        if (getPropIndDTOHelper().getImpuestoVO().getIdImpuesto() != null
                && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().compareTo(BigDecimal.ZERO) > 0
                && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
            getPropIndDTOHelper().getImpuestoVO().setMonto(BigDecimal.ZERO);
            cargaConceptoImpuesto(getPropIndDTOHelper().getImpuestoVO().getIdImpuesto());
            getPropIndBooleanHelper().setHabilitaCampoMonto(true);
        } else {
            cargaConceptoImpuesto(getPropIndDTOHelper().getImpuestoVO().getIdImpuesto());
            getPropIndDTOHelper().getImpuestoVO().setMonto(null);
            getPropIndBooleanHelper().setHabilitaCampoMonto(false);
        }
    }

    /**
     * Metodo que activa el boton de buscar RFC
     *
     */
    public void activarBotonBuscarRFC() {
        if (getPropIndDTOHelper().getContribuyente().getRfc() != null) {
            getPropIndBooleanHelper().setHabilitarBuscarRFC(false);
        } else {
            getPropIndBooleanHelper().setHabilitarBuscarRFC(true);
        }
    }

    /**
     * Evento al seleccionar fecha de inicio de periodo de la propuesta
     *
     */
    public void handleDateSelectFechaFinPeriodo(SelectEvent event) {
        Date dateSeleccionado = (Date) event.getObject();
        if (dateSeleccionado != null) {
            getPropIndBooleanHelper().setHabilitarCamposImpuestos(false);
        } else {
            getPropIndBooleanHelper().setHabilitarCamposImpuestos(true);
        }
    }

    public void handleDateSelectFechaDescripcion() {
        BigDecimal tipo = getPropIndDTOHelper().getPropuesta().getIdTipoPropuesta();
        logger.debug(tipo + " tipo de propuesta");
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            getPropIndBooleanHelper().setTipoPropuesta(false);
        } else {
            getPropIndBooleanHelper().setTipoPropuesta(true);
        }
    }

    /**
     * Evento al seleccionar fecha de inicio de periodo en Impuestos
     *
     */
    public void handleDateSelectFechaFinImp(SelectEvent event) {
        Date dateSeleccionado = (Date) event.getObject();
        if (dateSeleccionado != null) {
            getPropIndBooleanHelper().setHabilitarCamposImpuestos(false);
        } else {
            getPropIndBooleanHelper().setHabilitarCamposImpuestos(true);
        }
    }

    public void descartarImpuesto() {
        List<ImpuestoVO> toRemove = new ArrayList<ImpuestoVO>();
        for (ImpuestoVO impuesto : getPropIndDTOHelper().getListaImpuestos()) {
            if (getPropIndDTOHelper().getImpuestoSeleccionado().equals(impuesto)) {
                toRemove.add(impuesto);
                if (getPropIndDTOHelper().getImpuestoSeleccionado().getIdImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                    getPropIndBooleanHelper().setHabilitaImpuesto(false);
                    getPropIndBooleanHelper().setHabilitaCampoMonto(false);
                }
                break;
            }
        }
        getPropIndDTOHelper().getListaImpuestos().removeAll(toRemove);
        FacesUtil.addInfoMessage(ConstantesPropuestas.MSG_IMPUESTO,
                getMessageResourceString("lbl.propuestas.impuestos.eliminar.impuesto"));
    }

    public void agregarImpuesto() {
        if (getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo() != null
                || getPropIndDTOHelper().getPropuesta().getFechaFinPeriodo() != null) {
            if (validarImpuesto()) {
                getPropIndDTOHelper().getImpuestoVO().setDescripcion(
                        buscarDescripcionImpuesto(getPropIndDTOHelper().getImpuestoVO().getIdImpuesto()));
                getPropIndDTOHelper().getImpuestoVO().setDescripcionConcepto(
                        buscarDescripcionConcepto(getPropIndDTOHelper().getImpuestoVO().getIdConcepto()));
                getPropIndDTOHelper().getListaImpuestos().add(getPropIndDTOHelper().getImpuestoVO());
                getPropIndDTOHelper().setImpuestoVO(new ImpuestoVO());
                getPropIndDTOHelper().getListaConcepto().clear();
            }
        } else {
            FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO,
                    "Para agregar un Impuesto es necesario capturar la fecha Per\u00edodo Propuesto Inicio y Per\u00edodo Propuesto Fin de la Propuesta");
            FacesUtil.addErrorMessage("formInsumo:txtFechaInicial", "Campo(s) Obligatorio(s) *");
        }
    }

    public void registrarInsumoPropuesta() {
        if (validarRegistroInsumo()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('mensajeRegistrar').show();");
        } else {
            if (!getPropIndBooleanHelper().isConImpuestos()) {
                String campoRequerido = getMessageResourceString(
                        "lbl.propuestas.impuestos.validacion.campos.requerido");
                FacesUtil.addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
                FacesUtil.addErrorMessage("formInsumo:cmbConcepto", campoRequerido);
                FacesUtil.addErrorMessage("formInsumo:txtMonto", campoRequerido);
                FacesUtil.addErrorMessage(CAMPO_PERIODO,
                        getMessageResourceString("lbl.propuestas.impuestos.validacion.fechas.obligatorio"));
            }
        }
    }

    public void cargaArchivoExpediente(final FileUploadEvent event) {
        if (validaArchivoCargaPropuesta(event.getFile())) {
            try {
                getPropIndDTOHelper().setArchivoCarga(event.getFile());
                FecetDocExpediente documento = new FecetDocExpediente();
                documento.setFechaCreacion(new Date());
                documento.setNombre(CargaArchivoUtilInsumos.limpiarPathArchivo(CargaArchivoUtilInsumos
                        .aplicarCodificacionTexto(getPropIndDTOHelper().getArchivoCarga().getFileName())));
                documento.setArchivo(getPropIndDTOHelper().getArchivoCarga().getInputstream());
                if (!validaNombreArchivo(getPropIndDTOHelper().getListaDocumento(), documento.getNombre())) {
                    getPropIndDTOHelper().getListaDocumento().add(documento);
                    FacesUtil.addInfoMessage(ConstantesPropuestas.MSG_ARCHIVO, Constantes.ARCHIVO_CARGADO);
                } else {
                    FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "El archivo ya fue cargado");
                }

            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

    public void registrarPropuesta() {
        try {
            validarDuplicidadAntecedentes(getPropIndDTOHelper().getContribuyente().getRfc(),
                    getPropIndDTOHelper().getPropuesta());
        } catch (Exception e) {
            logger.error("Error al registrar Propuesta [{}]", e);
        }
    }

    private void validarDuplicidadAntecedentes(final String rfcContribuyente, FecetPropuesta dto) {
        try {
            List<String> listaAntecedentes = this.verificarDuplicidadPropuestas(rfcContribuyente, dto);

            if (listaAntecedentes != null && listaAntecedentes.size() > 0) {
                StringBuilder texto = new StringBuilder();
                int numeroIncidencia = 1;
                for (int index = 0; index < listaAntecedentes.size(); index++) {
                    if (listaAntecedentes.get(index) != null && !listaAntecedentes.get(index).trim().isEmpty()) {
                        texto.append(numeroIncidencia).append(".- ").append(listaAntecedentes.get(index))
                                .append("<br>");
                        numeroIncidencia++;
                    }
                }
                String mensajeConfirmar = String
                        .format(getMessageResourceString("msj.conincidencias.confirmar.registro.propuesta"));
                texto.append("<br>").append(mensajeConfirmar).append("<br>");
                getPropIndAttributeHelper().setMensajeAntecedentes(texto.toString());
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('confirmarContinuarRegistrando').show();");

            } else {
                guardarPropuesta();
            }
        } catch (Exception e) {
            logger.error("Error al validar duplicidad de registro de la Propuesta [{}]", e);
        }
    }

    public String generarFolioPropuesta() {
        StringBuilder folio = new StringBuilder();
        folio.append("E");
        if (getPropIndAttributeHelper().getSegundoDigito() != null) {
            folio.append(getPropIndAttributeHelper().getSegundoDigito());
        }
        if (getPropIndAttributeHelper().getTerceroCuartoDigito() != null) {
            folio.append(getPropIndAttributeHelper().getTerceroCuartoDigito());
        }
        if (getPropIndAttributeHelper().getQuintoOctavoDigito() != null) {
            folio.append(getPropIndAttributeHelper().getQuintoOctavoDigito());
        }
        if (getPropIndAttributeHelper().getNovenoDoceavoDigito() != null) {
            folio.append(getPropIndAttributeHelper().getNovenoDoceavoDigito());
        }
        return folio.toString();
    }

    public void limpiarCamposOcultos() {
        if (!this.getPropIndBooleanHelper().isVisibleRevision()) {
            getPropIndDTOHelper().getPropuesta().setIdRevision(null);
        }
        if (!this.getPropIndBooleanHelper().isVisibleFechaInf()) {
            getPropIndDTOHelper().getPropuesta().setFechaInforme(null);
        }
        if (!this.getPropIndBooleanHelper().isVisibleFechaPre()) {
            getPropIndDTOHelper().getPropuesta().setFechaPresentacion(null);
        }
    }

    public void asignarPropuestaCentralRegional() {
        List<ProgramadorPropuestaAsignadaDTO> lista = null;
        if (getPropIndAttributeHelper().getAreaOrigen().equals(Constantes.ACPPCE)) {
            if (getPropIndBooleanHelper().isComplementaInsumo()) {
                if (getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACAOCE)
                        || getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACOECE)) {
                    lista = getRegistrarPropuestaIndividualService().asignarPropuesta(Constantes.USUARIO_PROGRAMADOR,
                            Constantes.ACPPCE);
                } else {
                    lista = getRegistrarPropuestaIndividualService().asignarPropuesta(Constantes.USUARIO_PROGRAMADOR,
                            getPropIndDTOHelper().getPropuesta().getIdArace());
                }
                getNotificacionService().obtenerCorreoEmpleado(getPropIndDTOHelper().getFecetInsumo().getRfcCreacion(), Constantes.USUARIO_INSUMOS, getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, getPropIndDTOHelper().getFecetInsumo().getFececUnidadAdministrativa().getIdUnidadAdministrativa(), getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);

                BigDecimal idArace = getPropIndDTOHelper().getPropuesta().getIdArace();
                getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, idArace, getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);
            } else { 
                // CENTRAL A REGIONAL
                if (!getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACAOCE)
                        && !getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACOECE)) {
                    lista = getRegistrarPropuestaIndividualService().asignarPropuesta(Constantes.USUARIO_PROGRAMADOR,
                            getPropIndDTOHelper().getPropuesta().getIdArace());
                    // OBTENEMOS AL CENTRAL PARA NOTIFICARLE
                    getPropIndDTOHelper().getDestinatarios()
                            .add(getRegistrarPropuestaIndividualService().getDatosEmpleadoCentral(Constantes.USUARIO_CONSULTOR_INSUMOS, getPropIndDTOHelper().getPropuesta().getIdArace())
                            .get(0).getCorreo());
                } else { 
                    // CENTRAL A CENTRAL
                    getPropIndDTOHelper().getDestinatarios().addAll(buscarDestinatarios(getPropIndDTOHelper().getEmpleadoDTO()));
                }
            }
            if (lista != null && lista.size() > 0) {
                getPropIndDTOHelper().getPropuesta().setProgramadorId(lista.get(0).getIdEmpleado());
                getNotificacionService().obtenerCorreoEmpleado(lista.get(0).getIdEmpleado().intValue(), Constantes.USUARIO_PROGRAMADOR, getPropIndDTOHelper().getDestinatarios(), ClvSubModulosAgace.PROPUESTAS);
            }
        } else {
            getPropIndDTOHelper().getPropuesta().setProgramadorId(null);
            getPropIndDTOHelper().getDestinatarios().addAll(buscarDestinatarios(getPropIndDTOHelper().getEmpleadoDTO()));
        }
    }

    public void guardarPropuesta() {
        try {
            getPropIndAttributeHelper().setNovenoDoceavoDigito(consecutivoPropuesta());
            String folio = generarFolioPropuesta();
            BigDecimal idContri = getRegistrarPropuestaIndividualService()
                    .insertarContribuyente(getPropIndDTOHelper().getContribuyente());
            getPropIndDTOHelper().getPropuesta().setIdContribuyente(idContri);
            getPropIndDTOHelper().getPropuesta().setIdRegistro(folio);
            getPropIndDTOHelper().getPropuesta().setIdEstatus(ConstantesPropuestas.PROPUESTA_REGISTRADA);
            getPropIndDTOHelper().getPropuesta().setFechaCreacion(new Date());
            getPropIndDTOHelper().getPropuesta().setOrigenPropuestaId(ConstantesPropuestas.PROPUESTA_INDIVIDUAL);
            getPropIndDTOHelper().getPropuesta().setRfcCreacion(getRFCSession());
            this.asignarPropuestaCentralRegional();
            logger.error("ID PRIORIDAD :" + getPropIndDTOHelper().getPropuesta().getPrioridadSugerida());
            if (getPropIndBooleanHelper().isComplementaInsumo()) {
                getPropIndDTOHelper().getPropuesta().setIdInsumo(getPropIndDTOHelper().getFecetInsumo().getIdInsumo());
                getPropIndDTOHelper().getPropuesta()
                        .setIdRegistroInsumo(getPropIndDTOHelper().getFecetInsumo().getIdRegistro());
                getPropIndDTOHelper().getPropuesta()
                        .setIdSubprograma(getPropIndDTOHelper().getFecetInsumo().getIdSubprograma());
                getPropIndDTOHelper().getPropuesta().setIdSector(getPropIndDTOHelper().getFecetInsumo().getIdSector());
                getPropIndDTOHelper().getPropuesta()
                        .setFechaInicioPeriodo(getPropIndDTOHelper().getFecetInsumo().getFechaInicioPeriodo());
                getPropIndDTOHelper().getPropuesta()
                        .setPrioridad(getPropIndDTOHelper().getFecetInsumo().getPrioridad());
                getPropIndDTOHelper().getPropuesta()
                        .setFechaFinPeriodo(getPropIndDTOHelper().getFecetInsumo().getFechaFinPeriodo());
            }
            this.limpiarCamposOcultos();
            getRegistrarPropuestaIndividualService().insertarPropuestaInsumos(getPropIndDTOHelper().getPropuesta());
            for (ImpuestoVO impuestoItem : getPropIndDTOHelper().getListaImpuestos()) {
                FecetImpuesto impuesto = new FecetImpuesto();
                impuesto.setIdPropuesta(getPropIndDTOHelper().getPropuesta().getIdPropuesta());
                impuesto.setIdTipoImpuesto(impuestoItem.getIdImpuesto());
                impuesto.setMonto(impuestoItem.getMonto());
                impuesto.setIdConcepto(impuestoItem.getIdConcepto());
                getRegistrarPropuestaIndividualService().insertarImpuesto(impuesto);
            }
            for (FecetDocExpediente docExpItem : getPropIndDTOHelper().getListaDocumento()) {
                docExpItem.setIdPropuesta(getPropIndDTOHelper().getPropuesta().getIdPropuesta());
                getPropIndDTOHelper().getPropuesta().setFecetContribuyente(getPropIndDTOHelper().getContribuyente());
                docExpItem
                        .setRutaArchivo(RutaArchivosUtilInsumos.armarRutaDestinoPropuesta(getPropIndDTOHelper().getPropuesta())
                                + docExpItem.getNombre());
                docExpItem.setBlnActivo(ConstantesPropuestas.EXP_ACTIVO);
                getRegistrarPropuestaIndividualService().insertarDocumento(docExpItem);
                try {
                    CargaArchivoUtilInsumos.guardarArchivo(docExpItem.getArchivo(), docExpItem.getRutaArchivo(),
                            docExpItem.getNombre());
                } catch (IOException e) {
                    logger.info("No se pudo guardar archivo");
                }
            }
            logger.info("El folio Generado...: [{}] , y es de tipo: [{}]", folio,
                    getPropIndDTOHelper().getPropuesta().getInformePresentacion());
            enviarNotificacionCreacionPropuesta(getPropIndDTOHelper().getPropuesta(), getPropIndDTOHelper().getDestinatarios());
            if (getPropIndBooleanHelper().isComplementaInsumo()) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('confirmarContinuarInsumos').show();");
            } else if (getPropIndBooleanHelper().isComplementaCambioMetodo()) {
                getRegistrarPropuestaIndividualService().nuevofolioCambioMetodo(
                        getPropIndDTOHelper().getPropuesta().getIdPropuesta(),
                        getPropIndDTOHelper().getPropuesta().getAgaceOrden().getIdOrden());
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('confirmarContinuarInsumos').show();");
            } else {
                init();
                FacesUtil.addInfoMessage("formInsumo:msgPropuestaId", "La Propuesta con Id " + folio
                        + " ha sido registrada y ser\u00e1 revisada por el \u00e1rea correspondiente.");
                limpiarFormulario();
            }
        } catch (Exception e) {
            logger.error("No se puede guardar el registro de Propuestas [{}]", e);
        }
    }

    public String regresarInsumos() {
        if (getPropIndBooleanHelper().isComplementaCambioMetodo()) {
            return "../../propuestas/programador/validarYRetroalimentar/indexValidarRetroalimentar.jsf?faces-redirect=true";
        } else {
            ValidarProcedenciaInsumoManagedBean vpiManagedBean = (ValidarProcedenciaInsumoManagedBean) getSession()
                    .getAttribute("validarProcedenciaInsumoManagedBean");
            if (vpiManagedBean != null) {
                vpiManagedBean.init();
            }
            return "../../insumos/subadministrador/indexValidar.jsf?faces-redirect=true";
        }
    }

    private void enviarNotificacionCreacionPropuesta(final FecetPropuesta propuesta, Set<String> destinatarios) {
        try {
            getRegistrarPropuestaIndividualService().enviarNotificacionCorreoPropuesta(propuesta, destinatarios);
        } catch (Exception e) {
            logger.error("No se puede enviar la notificacion [{}]", e);
        }
    }

    private boolean validaNombreArchivo(List<FecetDocExpediente> listaDocumento, String nombre) {
        for (FecetDocExpediente fecetDocExpediente : listaDocumento) {
            if (fecetDocExpediente.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    private Date obtenerFechaInicial() {
        Date fechaInicio = new Date();
        String fechaInicioStr = "01/01/" + getPropIndAttributeHelper().getCalendario().get(Calendar.YEAR);
        try {
            fechaInicio = getPropIndAttributeHelper().getFormatoFecha().parse(fechaInicioStr);
        } catch (ParseException e) {
            logger.error("No se pudo pasear la fecha inicial [{}] ", fechaInicioStr);
        }
        return fechaInicio;
    }

    private Date obtenerFechaFinal() {
        Date fechaFinal = new Date();
        String fechaFinalStr = "31/12/" + getPropIndAttributeHelper().getCalendario().get(Calendar.YEAR);
        try {
            fechaFinal = getPropIndAttributeHelper().getFormatoFecha().parse(fechaFinalStr);
        } catch (ParseException e) {
            logger.error("No se pudo pasear la fecha final [{}] ", fechaFinalStr);
        }
        return fechaFinal;
    }

    private BigDecimal consecutivoPropuestaAnio() {
        BigDecimal consecutivoActual = null;
        BigDecimal consecutivoNuevo = null;
        try {
            consecutivoActual = getRegistrarPropuestaIndividualService().getConsecutivoAnio(this.obtenerFechaInicial(),
                    this.obtenerFechaFinal());
            if (consecutivoActual != null) {
                logger.debug("Consecutivo Actual: [{}]", consecutivoActual);
                consecutivoNuevo = consecutivoActual.add(ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA);
            } else {
                consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            }
            getPropIndDTOHelper().getPropuesta().setConsecutivoAnio(consecutivoNuevo);
        } catch (NegocioException e) {
            consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        }
        logger.debug("Nuevo Consecutivo: [{}]", consecutivoNuevo);
        return consecutivoNuevo;
    }

    private String consecutivoPropuesta() {
        BigDecimal consecutivo;
        String claveFinal = null;
        String str3CerosIzq = "%04d";
        Formatter fmt = null;
        try {
            fmt = new Formatter();
            getPropIndDTOHelper().getPropuesta()
                    .setIdPropuesta(getRegistrarPropuestaIndividualService().getConsecutivo());
            consecutivo = this.consecutivoPropuestaAnio();
            claveFinal = fmt.format(str3CerosIzq, Long.parseLong(consecutivo.toString())).toString();
        } catch (NegocioException e) {
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        } finally {
            if (fmt != null) {
                fmt.close();
            }
        }
        return claveFinal;
    }

    public void limpiarFormulario() {

        getPropIndDTOHelper().getPropuesta().setIdMetodo(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdTipoPropuesta(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdCausaProgramacion(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdRevision(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setPrioridadSugerida(BigDecimal.ONE.negate().toString());
        getPropIndDTOHelper().getPropuesta().setPrioridad(false);
        getPropIndDTOHelper().getPropuesta().setFechaPresentacion(null);
        getPropIndDTOHelper().getPropuesta().setFechaInforme(null);
        getPropIndAttributeHelper().setInformacionComite(null);
        getPropIndAttributeHelper().setPresentacionComite(null);
        getPropIndDTOHelper().setListaDocumento(new ArrayList<FecetDocExpediente>());
        getPropIndDTOHelper().setListaImpuestos(new ArrayList<ImpuestoVO>());
        getPropIndBooleanHelper().setHabilitarCamposImpuestos(true);
        getPropIndBooleanHelper().setHabilitaImpuesto(false);
        getPropIndDTOHelper().getImpuestoVO().setIdImpuesto(new BigDecimal(-1));
        getPropIndDTOHelper().getImpuestoVO().setMonto(null);
        getPropIndBooleanHelper().setHabilitaCampoMonto(false);
        if (getPropIndDTOHelper().getListaConcepto() != null && !getPropIndDTOHelper().getListaConcepto().isEmpty()) {
            getPropIndDTOHelper().getListaConcepto().clear();
        }
        activarCampos();
    }

    public void agregarPistaAuditoria(final BigDecimal idOperacion, final BigDecimal idTabla, final String idRegistro,
            final String descripcion) {
        try {
            FecetBitacora bitacora = new FecetBitacora();
            InetAddress localHost = InetAddress.getLocalHost();
            bitacora.setIdOperacion(idOperacion);
            bitacora.setIdInterno(idTabla);
            bitacora.setIpmaquina(localHost.getHostAddress());
            bitacora.setNombremaquina(localHost.getHostName());
            bitacora.setRfcUsuario(getRFCSession());
            bitacora.setRfcAgenteAduanal(getRFCSession());
            bitacora.setRfcApoderadoLegal(getRFCSession());
            bitacora.setRfcRepresentanteLegal(getRFCSession());
            bitacora.setIdRegistro(idRegistro);
            bitacora.setDescripcion(descripcion);
            bitacora.setFecha(new Date());
            getPistasAuditoriaService().insertaPistaAuditoria(bitacora);
        } catch (Exception e) {
            logger.error("No se puedo guardar la pista de auditoria [{}] ", e);
        }
    }

    public Boolean validaArchivoCargaPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {
            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Archivo invalido",
                    "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }
        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= ConstantesPropuestas.N_4196000L) {
            return true;
        } else {
            if (archivo.getSize() >= ConstantesPropuestas.N_4196000L) {
                FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Error al cargar el archivo.",
                        "Error al cargar el archivo. El archivo es demasiado grande, lo m\u00e1ximo permitido son 4MB.");
            } else {
                FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Error al cargar el archivo.",
                        "Error al cargar el archivo. El archivo es demasiado chico");
            }
        }
        return false;
    }

    private List<String> buscarDestinatarios(EmpleadoDTO empleado) {
        List<String> destinatarios = new ArrayList<String>();
        destinatarios.add(empleado.getCorreo());
        destinatarios.add(getRegistrarPropuestaIndividualService().getDatosEmpleadoCentral(Constantes.USUARIO_CONSULTOR_INSUMOS, getPropIndDTOHelper().getPropuesta().getIdArace())
                .get(0).getCorreo());
        return destinatarios;
    }

    public StreamedContent getDescargaArchivo() {
        StreamedContent archivo = null;
        if (getPropIndDTOHelper().getRutaArchivo() != null && getPropIndDTOHelper().getNombreArchivo() != null) {
            String path = getPropIndDTOHelper().getRutaArchivo().replace(getPropIndDTOHelper().getNombreArchivo(), "");
            path = path.concat(getPropIndDTOHelper().getNombreArchivo());
            archivo = getDescargaArchivo(path, getPropIndDTOHelper().getNombreArchivo());
        }
        return archivo;
    }
}
