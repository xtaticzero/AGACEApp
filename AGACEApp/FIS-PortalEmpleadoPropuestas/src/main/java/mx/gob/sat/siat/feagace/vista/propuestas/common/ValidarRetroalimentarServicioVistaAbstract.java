/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.common;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.CAMPO_OBLIGATORIO_IMPUESTO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ENTERO_CERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.CausaRechazoPropuestaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CausaRechazoRetroalimentacionEnum;
import mx.gob.sat.siat.feagace.modelo.enums.MotivoRetroalimentacionEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarHelper;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarListHelper;

/**
 *
 * @author Ing. Juan Adrian Cuervo Zarate
 */
public abstract class ValidarRetroalimentarServicioVistaAbstract extends ValidarRetroalimentarAbstractMB {

    /**
     *
     */
    private static final long serialVersionUID = -5461254450405914665L;

    @ManagedProperty(value = "#{validarRetroalimentarPropuestaService}")
    private ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;
    @ManagedProperty(value = "#{consultaAntecedentesService}")
    private transient ConsultaAntecedentesPropuestasService consultaAntecedentesService;

    @ManagedProperty(value = "#{auditoriaPropuestas}")
    private transient PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService;

    private ValidarYRetroalimentarHelper validarYRetroalimentarHelper;
    private ValidarYRetroalimentarListHelper retroalimentarLstHelper;
    private String tipoFieldset;

    private static final String BR = "<br>";
    protected static final String ERROR_IMPUESTO = "error.propuestas.validar.impuesto";

    public void retroPropuesta() {
        try {
            if (validaFormulario()) {
                validarDuplicidadAntecedentes(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                        .getFecetContribuyente().getRfc(),
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
            }
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
                        texto.append(numeroIncidencia).append(".- ").append(listaAntecedentes.get(index)).append(BR);
                        numeroIncidencia++;
                    }
                }
                String mensajeConfirmar = String.format(getMessageResourceString("msj.conincidencias.confirmar.retro"));
                texto.append(BR).append(mensajeConfirmar).append(BR);
                getValidarYRetroalimentarHelper().setMensajeAntecedentes(texto.toString());
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dlgConfirmRetro').show();");

            } else {
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dlgConfirmRetro').show();");
            }

        } catch (Exception e) {
            logger.error("Error al validar duplicidad de registro de la Propuesta [{}]", e);
        }
    }

    public List<String> verificarDuplicidadPropuestas(final String rfc, FecetPropuesta dto) {
        List<String> listaDatosEncontrados = new ArrayList<String>();
        listaDatosEncontrados.add(this.verificarSICSEP(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo()));
        listaDatosEncontrados.add(this.verificarSUIEFI(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo()));
        listaDatosEncontrados.add(this.verificarAGAFF(rfc, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo()));
        listaDatosEncontrados.add(this.verificarAGACE(rfc, dto));
        listaDatosEncontrados.add(this.verificarInsumos(rfc, dto));

        return listaDatosEncontrados;
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
        StringBuilder mensajeAGACE = new StringBuilder();
        try {
            propuestasAGACE = getConsultaAntecedentesService().consultaAGACEPropuestas(rfc, dto);
            if (propuestasAGACE != null && propuestasAGACE.size() > 0) {
                logger.info("propuestasAGACE Size: [{}]", propuestasAGACE.size());
                mensajeAGACE.append(String.format(getMessageResourceString(ConstantesPropuestas.MSG_SIREGISTROS),
                        ConstantesPropuestas.AGACE_DESCRIPCION));
                for (FecetPropuesta propuesta : propuestasAGACE) {
                    mensajeAGACE.append(BR);
                    mensajeAGACE.append("- ID de Registro:").append(propuesta.getIdRegistro()).append(BR);
                    mensajeAGACE.append("- RFC:").append(propuesta.getFecetContribuyente().getRfc()).append(BR);
                    mensajeAGACE.append("- Unidad Administrativa:")
                            .append(propuesta.getUnidadAdministrativa().getNombre()).append(BR);
                    mensajeAGACE.append("- Subprograma:").append(propuesta.getFececSubprograma().getDescripcion())
                            .append(BR);
                    mensajeAGACE.append("- Método:").append(propuesta.getFeceaMetodo().getAbreviatura()).append(BR);
                    mensajeAGACE.append("- Periodo:")
                            .append((propuesta.getFechaInicioPeriodo() != null)
                                            ? DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                                                    propuesta.getFechaInicioPeriodo())
                                            : "")
                            .append(" - ")
                            .append((propuesta.getFechaFinPeriodo() != null)
                                            ? DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                                                    propuesta.getFechaFinPeriodo())
                                            : "");
                    mensajeAGACE.append(BR);
                }
            }
        } catch (NegocioException e) {
            mensajeAGACE.append(String.format(getMessageResourceString(ConstantesPropuestas.MSG_NOREGISTROS),
                    ConstantesPropuestas.AGACE_DESCRIPCION));
        }
        return mensajeAGACE.toString();
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

    public void handleDateSelectFechaFinPeriodo(SelectEvent event) {
        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().setFechaFinPeriodo(null);
    }

    public CausaRechazoPropuestaEnum[] getCausaRechazoPropuestaValues() {
        return CausaRechazoPropuestaEnum.values();
    }

    public CausaRechazoRetroalimentacionEnum[] getCausaRechazoRetroalimentacionPropuestaValues() {
        return CausaRechazoRetroalimentacionEnum.values();
    }

    public MotivoRetroalimentacionEnum[] getCausaRetroalimentacionValues() {
        return MotivoRetroalimentacionEnum.values();
    }

    public ValidarRetroalimentarPropuestaService getValidarRetroalimentarPropuestaService() {
        return validarRetroalimentarPropuestaService;
    }

    public void setValidarRetroalimentarPropuestaService(
            ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService) {
        this.validarRetroalimentarPropuestaService = validarRetroalimentarPropuestaService;
    }

    public ValidarYRetroalimentarHelper getValidarYRetroalimentarHelper() {
        return validarYRetroalimentarHelper;
    }

    public void setValidarYRetroalimentarHelper(ValidarYRetroalimentarHelper validarYRetroalimentarHelper) {
        this.validarYRetroalimentarHelper = validarYRetroalimentarHelper;
    }

    public ValidarYRetroalimentarListHelper getRetroalimentarLstHelper() {
        return retroalimentarLstHelper;
    }

    public void setRetroalimentarLstHelper(ValidarYRetroalimentarListHelper retroalimentarLstHelper) {
        this.retroalimentarLstHelper = retroalimentarLstHelper;
    }

    public ConsultaAntecedentesPropuestasService getConsultaAntecedentesService() {
        return consultaAntecedentesService;
    }

    public void setConsultaAntecedentesService(ConsultaAntecedentesPropuestasService consultaAntecedentesService) {
        this.consultaAntecedentesService = consultaAntecedentesService;
    }

    public String getTipoFieldset() {
        return tipoFieldset;
    }

    public void setTipoFieldset(String tipoFieldset) {
        this.tipoFieldset = tipoFieldset;
    }

    public PistasAuditoriasPropuestasService getPistasAuditoriasPropuestasService() {
        return pistasAuditoriasPropuestasService;
    }

    public void setPistasAuditoriasPropuestasService(PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService) {
        this.pistasAuditoriasPropuestasService = pistasAuditoriasPropuestasService;
    }

    public boolean validaFormulario() {
        boolean formularioCompleto = true;
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdSubprograma() == null
                || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdSubprograma()
                .equals(BigDecimal.ZERO)) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbSubprograma", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdTipoPropuesta() == null
                || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdTipoPropuesta()
                .equals(BigDecimal.ZERO)) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbPropuesta", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo() == null
                || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo()
                .equals(BigDecimal.ZERO)) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbMetodo", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        } else {
            if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo()
                    .equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))
                    && (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRevision() == null
                    || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRevision()
                    .equals(BigDecimal.ZERO))) {
                addErrorMessage("formValidaRetroalimentarPropuesta:cmbRevision", CAMPO_OBLIGATORIO_IMPUESTO);
                formularioCompleto = false;
            }
        }
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida() == null
                || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida()
                .length() == 0) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbPrioridad", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdSector() == null
                || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdSector()
                .equals(BigDecimal.ZERO)) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbSector", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFechaFinPeriodo() == null) {
            addErrorMessage("formValidaRetroalimentarPropuesta:txtFechaFinal", CAMPO_OBLIGATORIO_IMPUESTO);
            formularioCompleto = false;
        }

        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getLstImpuestos()
                .size() == ENTERO_CERO) {
            addErrorMessage(getMessageResourceString(ERROR_IMPUESTO));
            formularioCompleto = false;
        }

        return formularioCompleto;
    }

}
