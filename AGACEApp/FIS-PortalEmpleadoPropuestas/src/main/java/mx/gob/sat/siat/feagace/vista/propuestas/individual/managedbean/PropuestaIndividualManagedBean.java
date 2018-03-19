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
import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.insumos.validar.ValidarProcedenciaInsumoManagedBean;
import mx.gob.sat.siat.feagace.vista.propuestas.individual.PropuestaIndividualManagedBeanAbstract;

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
                && getPropIndDTOHelper().getImpuestoVO().getIdImpuesto()
                .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
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
                if (getPropIndDTOHelper().getImpuestoSeleccionado().getIdImpuesto()
                        .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                    getPropIndBooleanHelper().setHabilitaImpuesto(false);
                    getPropIndBooleanHelper().setHabilitaCampoMonto(false);
                }
                break;
            }
        }
        getPropIndDTOHelper().getListaImpuestos().removeAll(toRemove);
        addMessage(ConstantesPropuestas.MSG_IMPUESTO,
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
            addErrorMessage(ConstantesPropuestas.MSG_IMPUESTO,
                    "Para agregar un Impuesto es necesario capturar la fecha Per\u00edodo Propuesto Inicio y Per\u00edodo Propuesto Fin de la Propuesta");
            addErrorMessage("formInsumo:txtFechaInicial", "Campo(s) Obligatorio(s) *");
        }
    }

    public void registrarInsumoPropuesta() {
        if (validarRegistroInsumo()) {
            /**
             * if (false) { mensajeDuplicidad = "Se encontraron coincidencias en
             * el RFC: -rfc- en el sistema: -sistema1-,-sistema2-," +
             * "sistema...-; por consiguiente no podr� ser almacenado el
             * registro. Favor de verificar.";
             * requestContext.execute("mensajeDuplicidad.show();"); } else {*
             */
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('mensajeRegistrar').show();");
            /**
             * }*
             */
        } else {
            if (!getPropIndBooleanHelper().isConImpuestos()) {
                String campoRequerido = getMessageResourceString(
                        "lbl.propuestas.impuestos.validacion.campos.requerido");
                addErrorMessage("formInsumo:cmbImpuesto", campoRequerido);
                addErrorMessage("formInsumo:cmbConcepto", campoRequerido);
                addErrorMessage("formInsumo:txtMonto", campoRequerido);
                addErrorMessage(CAMPO_PERIODO,
                        getMessageResourceString("lbl.propuestas.impuestos.validacion.fechas.obligatorio"));
            }
        }
    }

    private boolean validarRegistroInsumo() {
        boolean datosValidos = true;
        if (getPropIndBooleanHelper().isVisibleUnidad()
                && getPropIndDTOHelper().getPropuesta().getIdArace().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbUnidad", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdSubprograma().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSubprograma", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdMetodo().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbMetodo", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdTipoPropuesta().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbPropuesta", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdCausaProgramacion().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbCausa", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdSector().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSector", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getPrioridadSugerida().equals("-1")) {
            addErrorMessage("formInsumo:cmbPrioridad", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        datosValidos = validarRegistroInsumo2(datosValidos);
        return datosValidos;
    }

    private boolean validarRegistroInsumo2(boolean datosValidos) {
        boolean datosTemp = datosValidos;

        if (getPropIndBooleanHelper().isVisibleRevision()
                && getPropIndDTOHelper().getPropuesta().getIdRevision().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbRevision", CAMPO_OBLIGATORIO);
            datosTemp = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo() == null
                || getPropIndDTOHelper().getPropuesta().getFechaFinPeriodo() == null) {
            addErrorMessage("formInsumo:txtFechaInicial", "Campo(s) Obligatorio(s) *");
            datosTemp = false;
        } else {
            if (getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo()
                    .after(getPropIndDTOHelper().getPropuesta().getFechaFinPeriodo())) {
                addErrorMessage("formInsumo:txtFechaInicial", CAMPO_FECHA);
                datosTemp = false;
            }
        }
        if (getPropIndBooleanHelper().isVisibleFechaPre()
                && getPropIndDTOHelper().getPropuesta().getFechaPresentacion() == null) {
            addErrorMessage("formInsumo:txtFechaPresentacion", CAMPO_OBLIGATORIO);
            datosTemp = false;
        } else if (getPropIndBooleanHelper().isVisibleFechaPre() && getPropIndDTOHelper().getPropuesta()
                .getFechaPresentacion().before(getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo())) {
            addErrorMessage("formInsumo:msgPropuestaId",
                    String.format(getMessageResourceString("lbl.propuestas.preinf.validacion.fecha.inicio"),
                            getPropIndAttributeHelper().getPresentacionComite()));
            datosTemp = false;
        }
        if (getPropIndBooleanHelper().isVisibleFechaInf()
                && getPropIndDTOHelper().getPropuesta().getFechaInforme() == null) {
            addErrorMessage("formInsumo:txtFechaInformacion", CAMPO_OBLIGATORIO);
            datosTemp = false;
        } else if (getPropIndBooleanHelper().isVisibleFechaInf() && getPropIndDTOHelper().getPropuesta()
                .getFechaInforme().before(getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo())) {
            addErrorMessage("formInsumo:msgPropuestaId",
                    String.format(getMessageResourceString("lbl.propuestas.preinf.validacion.fecha.inicio"),
                            getPropIndAttributeHelper().getInformacionComite()));
            datosTemp = false;
        }

        if (getPropIndDTOHelper().getListaDocumento() != null
                && getPropIndDTOHelper().getListaDocumento().size() <= 0) {
            addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Debes agregar documentanci\u00f3n");
            datosTemp = false;
        }

        if (getPropIndDTOHelper().getListaImpuestos() != null && getPropIndDTOHelper().getListaImpuestos().size() > 0) {
            getPropIndBooleanHelper().setConImpuestos(true);
        } else {
            getPropIndBooleanHelper().setConImpuestos(false);
            datosTemp = false;
        }
        return datosTemp;
    }

    public void cargaArchivoExpediente(final FileUploadEvent event) {
        if (validaArchivoCargaPropuesta(event.getFile())) {
            try {
                getPropIndDTOHelper().setArchivoCarga(event.getFile());
                FecetDocExpediente documento = new FecetDocExpediente();
                documento.setFechaCreacion(new Date());
                documento.setNombre(CargaArchivoUtilPropuestas.limpiarPathArchivo(CargaArchivoUtilPropuestas
                        .aplicarCodificacionTexto(getPropIndDTOHelper().getArchivoCarga().getFileName())));
                documento.setArchivo(getPropIndDTOHelper().getArchivoCarga().getInputstream());
                if (!validaNombreArchivo(getPropIndDTOHelper().getListaDocumento(), documento.getNombre())) {
                    getPropIndDTOHelper().getListaDocumento().add(documento);
                    addMessage(ConstantesPropuestas.MSG_ARCHIVO, Constantes.ARCHIVO_CARGADO);
                } else {
                    addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "El archivo ya fue cargado");
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
            } else {
                if (!getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACAOCE)
                        && !getPropIndDTOHelper().getPropuesta().getIdArace().equals(Constantes.ACOECE)) {
                    lista = getRegistrarPropuestaIndividualService().asignarPropuesta(Constantes.USUARIO_PROGRAMADOR,
                            getPropIndDTOHelper().getPropuesta().getIdArace());

                    getPropIndDTOHelper()
                            .getDestinatarios().add(
                                    getRegistrarPropuestaIndividualService()
                                    .getDatosEmpleadoCentral(Constantes.USUARIO_CENTRAL,
                                            getPropIndDTOHelper().getPropuesta().getIdArace())
                                    .get(0).getCorreo());
                } else {
                    getPropIndDTOHelper().getDestinatarios()
                            .addAll(buscarDestinatarios(getPropIndDTOHelper().getEmpleadoDTO()));
                }
            }
            if (lista != null && lista.size() > 0) {
                getPropIndDTOHelper().getPropuesta().setProgramadorId(lista.get(0).getIdEmpleado());
                getPropIndDTOHelper().getDestinatarios()
                        .add(getRegistrarPropuestaIndividualService()
                                .getDatosEmpleado(getPropIndDTOHelper().getPropuesta().getProgramadorId()).getCorreo()
                                .toString());
            }
        } else {
            getPropIndDTOHelper().getPropuesta().setProgramadorId(null);
            getPropIndDTOHelper().getDestinatarios()
                    .addAll(buscarDestinatarios(getPropIndDTOHelper().getEmpleadoDTO()));
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
            getRegistrarPropuestaIndividualService().insertarPropuestaPropuestas(getPropIndDTOHelper().getPropuesta());
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
                        .setRutaArchivo(RutaArchivosUtilPropuestas.armarRutaDestinoPropuesta(getPropIndDTOHelper().getPropuesta())
                                + docExpItem.getNombre());
                docExpItem.setBlnActivo(ConstantesPropuestas.EXP_ACTIVO);
                getRegistrarPropuestaIndividualService().insertarDocumento(docExpItem);
                try {
                    CargaArchivoUtilPropuestas.guardarArchivo(docExpItem.getArchivo(), docExpItem.getRutaArchivo(),
                            docExpItem.getNombre());
                } catch (IOException e) {
                    logger.info("No se pudo guardar archivo");
                }
            }
            logger.info("El folio Generado...: [{}] , y es de tipo: [{}]", folio,
                    getPropIndDTOHelper().getPropuesta().getInformePresentacion());
            enviarNotificacionCreacionPropuesta(getPropIndDTOHelper().getPropuesta(),
                    getPropIndDTOHelper().getDestinatarios());
            if (getPropIndBooleanHelper().isComplementaInsumo()) {
                getValidarProcedenciaInsumoService()
                        .actualizarInsumoTerminado(getPropIndDTOHelper().getFecetInsumo().getIdInsumo());
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
                addMessage("formInsumo:msgPropuestaId", "La Propuesta con Id " + folio
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

    private void enviarNotificacionCreacionPropuesta(final FecetPropuesta propuesta, List<String> destinatarios) {
        try {

            HashSet<String> destNotificaciones = new HashSet<String>(destinatarios);
            getRegistrarPropuestaIndividualService().enviarNotificacionCorreoPropuesta(propuesta, destNotificaciones,
                    getRFCSession());
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
        getPropIndAttributeHelper().setMsjContactoAmparadoPPEE(null);
        if (!getPropIndBooleanHelper().isComplementaCambioMetodo()) {
            getPropIndAttributeHelper().setEstatusDeContacto(null);
            getPropIndDTOHelper().setContribuyente(new FecetContribuyente());
            getPropIndBooleanHelper().setHabilitarBuscarRFC(true);
            getPropIndDTOHelper().getPropuesta().setIdArace(new BigDecimal(-1));
        }
        getPropIndDTOHelper().getPropuesta().setIdSubprograma(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdMetodo(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdTipoPropuesta(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdCausaProgramacion(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdSector(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setIdRevision(new BigDecimal(-1));
        getPropIndDTOHelper().getPropuesta().setPrioridadSugerida(BigDecimal.ONE.negate().toString());
        getPropIndDTOHelper().getPropuesta().setFechaInicioPeriodo(null);
        getPropIndDTOHelper().getPropuesta().setFechaFinPeriodo(null);
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
        getPropIndDTOHelper().getListaConcepto().clear();
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
            addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Archivo invalido",
                    "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }
        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= ConstantesPropuestas.N_4196000L) {
            return true;
        } else {
            if (archivo.getSize() >= ConstantesPropuestas.N_4196000L) {
                addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Error al cargar el archivo.",
                        "Error al cargar el archivo. El archivo es demasiado grande, lo m\u00e1ximo permitido son 4MB.");
            } else {
                addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Error al cargar el archivo.",
                        "Error al cargar el archivo. El archivo es demasiado chico");
            }
        }
        return false;
    }

    public String verificarSICSEP(final String rfc, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        List<FecetPropuesta> propuestasSICSEP = null;
        String mensajeSICSEP = null;
        try {
            propuestasSICSEP = getConsultaAntecedentesService().consultaSICSEP(rfc, fechaInicioPeriodo,
                    fechaFinPeriodo);

            if (propuestasSICSEP != null && propuestasSICSEP.size() > 0) {
                logger.info("propuestasSICSEP Size: [{}]", propuestasSICSEP.size());
                mensajeSICSEP = String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.SICSEP);
            }
        } catch (NegocioException e) {
            mensajeSICSEP = String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.SICSEP);
        }
        return mensajeSICSEP;
    }

    public String verificarSUIEFI(final String rfc, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        List<FecetPropuesta> propuestasSUIEFI = null;
        String mensajeSUIEFI = null;
        try {
            propuestasSUIEFI = getConsultaAntecedentesService().consultaSUIEFI(rfc, fechaInicioPeriodo,
                    fechaFinPeriodo);

            if (propuestasSUIEFI != null && propuestasSUIEFI.size() > 0) {
                logger.info("propuestasSUIEFI Size: [{}]", propuestasSUIEFI.size());
                mensajeSUIEFI = String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.SIUIEFI);
            }
        } catch (NegocioException e) {
            mensajeSUIEFI = String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.SIUIEFI);
        }
        return mensajeSUIEFI;
    }

    public String verificarAGAFF(final String rfc, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        List<FecetPropuesta> propuestasAGAFF = null;
        String mensajeAGAFF = null;
        try {
            propuestasAGAFF = getConsultaAntecedentesService().consultaAGAFF(rfc, fechaInicioPeriodo, fechaFinPeriodo);
            /*   */
            if (propuestasAGAFF != null && propuestasAGAFF.size() > 0) {
                logger.info("propuestasAGAFF Size: [{}]", propuestasAGAFF.size());
                mensajeAGAFF = String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.AGAFF_DESCRIPCION);
            }
        } catch (Exception e) {
            mensajeAGAFF = String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.AGAFF_DESCRIPCION);
        }
        return mensajeAGAFF;
    }

    public String verificarAGACE(final String rfc, FecetPropuesta dto) {
        List<FecetPropuesta> propuestasAGACE = null;
        String mensajeAGACE = null;
        try {
            propuestasAGACE = getConsultaAntecedentesService().consultaAGACEPropuestas(rfc, dto);
            if (propuestasAGACE != null && propuestasAGACE.size() > 0) {
                logger.info("propuestasAGACE Size: [{}]", propuestasAGACE.size());
                mensajeAGACE = String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.AGACE_DESCRIPCION);
            }
        } catch (NegocioException e) {
            mensajeAGACE = String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.AGACE_DESCRIPCION);
        }
        return mensajeAGACE;
    }

    public String verificarInsumos(final String rfc, FecetPropuesta dto) {
        List<FecetInsumo> insumosAGACE = null;
        String mensajeInsumos = null;

        try {
            insumosAGACE = getConsultaAntecedentesService().consultaAGACEInsumos(rfc, dto);

            if (insumosAGACE != null && insumosAGACE.size() > 0) {
                logger.info("insumosAGACE Size: [{}]", insumosAGACE.size());
                mensajeInsumos = String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.INSUMOS_DESCRIPCION);
            }
        } catch (NegocioException e) {
            mensajeInsumos = String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.INSUMOS_DESCRIPCION);
        }
        return mensajeInsumos;
    }

    public List<String> verificarDuplicidadPropuestas(final String rfc, FecetPropuesta dto) {

        List<String> listaDatosEncontrados = new ArrayList<String>();

        String sicsep = this.verificarSICSEP(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo());
        String suiefi = this.verificarSUIEFI(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo());
        String agaff = this.verificarAGAFF(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo());
        String agace = this.verificarAGACE(rfc, dto);
        String insumos = this.verificarInsumos(rfc, dto);

        if (validarCoincidencia(sicsep)) {
            listaDatosEncontrados.add(sicsep);
        }

        if (validarCoincidencia(suiefi)) {
            listaDatosEncontrados.add(suiefi);
        }

        if (validarCoincidencia(agaff)) {
            listaDatosEncontrados.add(agaff);
        }

        if (validarCoincidencia(agace)) {
            listaDatosEncontrados.add(agace);
        }

        if (validarCoincidencia(insumos)) {
            listaDatosEncontrados.add(insumos);
        }

        return listaDatosEncontrados;

    }

    private boolean validarCoincidencia(String coincidencia) {
        if (coincidencia != null) {
            return true;
        }
        return false;
    }

    private List<String> buscarDestinatarios(EmpleadoDTO empleado) {
        List<String> destinatarios = new ArrayList<String>();
        destinatarios.add(empleado.getCorreo());
        destinatarios.add(getRegistrarPropuestaIndividualService()
                .getDatosEmpleadoCentral(Constantes.USUARIO_CENTRAL, getPropIndDTOHelper().getPropuesta().getIdArace())
                .get(0).getCorreo());
        return destinatarios;
    }
}
