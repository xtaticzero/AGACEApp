package mx.gob.sat.siat.feagace.vista.propuestas.individual;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarProcedenciaInsumoService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualAttributeHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualBooleanHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.PropuestaIndividualDTOHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class PropuestaIndividualBasicMB extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;
    public static final String CAMPO_OBLIGATORIO = "Campo Obligatorio *";
    public static final String CAMPO_PERIODO = "formInsumo:txtPeriodo";

    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;

    @ManagedProperty(value = "#{validarContribuyenteService}")
    private transient ValidarContribuyenteService validarContribuyenteService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{validarProcedenciaInsumoService}")
    private transient ValidarProcedenciaInsumoService validarProcedenciaInsumoService;

    @ManagedProperty(value = "#{pistasAuditoriaService}")
    private transient PistasAuditoriaService pistasAuditoriaService;
    
    private PropuestaIndividualBooleanHelper propIndBooleanHelper;
    private PropuestaIndividualAttributeHelper propIndAttributeHelper;
    private PropuestaIndividualDTOHelper propIndDTOHelper;
    
    protected List<String> verificarDuplicidadPropuestas(final String rfc, FecetPropuesta dto) {
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
    
    protected boolean validarRegistroInsumo() {
        boolean datosValidos = true;
        if (getPropIndBooleanHelper().isVisibleUnidad()
                && getPropIndDTOHelper().getPropuesta().getIdArace().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbUnidad", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdMetodo().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbMetodo", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdTipoPropuesta().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbPropuesta", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getIdCausaProgramacion().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbCausa", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getPrioridadSugerida().equals("-1")) {
            FacesUtil.addErrorMessage("formInsumo:cmbPrioridad", CAMPO_OBLIGATORIO);
            datosValidos = false;
        }
        datosValidos = validarRegistroInsumo2(datosValidos);
        return datosValidos;
    }
    
    private boolean validarRegistroInsumo2(boolean datosValidos) {
        boolean datosTemp = datosValidos;

        if (getPropIndBooleanHelper().isVisibleRevision()
                && getPropIndDTOHelper().getPropuesta().getIdRevision().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formInsumo:cmbRevision", CAMPO_OBLIGATORIO);
            datosTemp = false;
        }
        if (getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo() == null
                || getPropIndDTOHelper().getPropuesta().getFechaFinPeriodo() == null) {
            FacesUtil.addErrorMessage("formInsumo:txtFechaInicial", "Campo(s) Obligatorio(s) *");
            datosTemp = false;
        } else {
            if (getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo()
                    .after(getPropIndDTOHelper().getPropuesta().getFechaFinPeriodo())) {
                FacesUtil.addErrorMessage("formInsumo:txtFechaInicial",
                        "La fecha Del no puede ser mayor a la fecha Al");
                datosTemp = false;
            }
        }
        if (getPropIndBooleanHelper().isVisibleFechaPre()
                && getPropIndDTOHelper().getPropuesta().getFechaPresentacion() == null) {
            FacesUtil.addErrorMessage("formInsumo:txtFechaPresentacion", CAMPO_OBLIGATORIO);
            datosTemp = false;
        } else if (getPropIndBooleanHelper().isVisibleFechaPre() && getPropIndDTOHelper().getPropuesta()
                .getFechaPresentacion().before(getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo())) {
            FacesUtil.addErrorMessage("formInsumo:msgPropuestaId",
                    String.format(getMessageResourceString("lbl.propuestas.preinf.validacion.fecha.inicio"),
                            getPropIndAttributeHelper().getPresentacionComite()));
            datosTemp = false;
        }
        if (getPropIndBooleanHelper().isVisibleFechaInf()
                && getPropIndDTOHelper().getPropuesta().getFechaInforme() == null) {
            FacesUtil.addErrorMessage("formInsumo:txtFechaInformacion", CAMPO_OBLIGATORIO);
            datosTemp = false;
        } else if (getPropIndBooleanHelper().isVisibleFechaInf() && getPropIndDTOHelper().getPropuesta()
                .getFechaInforme().before(getPropIndDTOHelper().getPropuesta().getFechaInicioPeriodo())) {
            FacesUtil.addErrorMessage("formInsumo:msgPropuestaId",
                    String.format(getMessageResourceString("lbl.propuestas.preinf.validacion.fecha.inicio"),
                            getPropIndAttributeHelper().getInformacionComite()));
            datosTemp = false;
        }

        if (getPropIndDTOHelper().getListaDocumento() != null
                && getPropIndDTOHelper().getListaDocumento().size() <= 0) {
            FacesUtil.addErrorMessage(ConstantesPropuestas.MSG_ARCHIVO, "Debes agregar documentanci\u00f3n");
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

    public void setRegistrarPropuestaIndividualService(RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
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
