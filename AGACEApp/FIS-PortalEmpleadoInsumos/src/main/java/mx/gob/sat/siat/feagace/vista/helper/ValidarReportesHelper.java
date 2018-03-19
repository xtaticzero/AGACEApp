package mx.gob.sat.siat.feagace.vista.helper;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ValidacionReportesDTO;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class ValidarReportesHelper extends BaseHelper {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ValidacionFechasReportesHelper validacionFechasReportesHelper;

    private ValidacionReportesDTO validacion = new ValidacionReportesDTO();

    public ValidacionReportesDTO validarFormulario(ReportesVO vo) {
        this.setValidacion(new ValidacionReportesDTO());
        this.getValidacion().setErrorTotal(ConstantesReportes.N_0);
        if (vo != null && vo.getTipoReporteEjecutivoSeleccionado() != null && vo.getTipoReporteEjecutivoSeleccionado().trim().equals(getMessageResourceString("lbl.reporte.titulo.gerencial"))) {
            logger.debug("Validacion Formulario Gerencial");
            this.getValidacion().setErrorTotal(this.validacionGerencial(vo));
        } else if (vo != null && vo.getTipoReporteEjecutivoSeleccionado() != null && vo.getTipoReporteEjecutivoSeleccionado().trim().equals(getMessageResourceString("lbl.reporte.titulo.ejecutivo"))) {
            logger.debug("Validacion Formulario Ejecutivo");
            this.getValidacion().setErrorTotal(this.validacionEjecutivoRequeridos(vo));
            if (this.getValidacion().getErrorTotal() == ConstantesReportes.N_0) {
                this.getValidacion().setErrorTotal(this.validacionFechasEjecutivo(vo));
                if (this.getValidacion().getErrorTotal() == ConstantesReportes.N_0) {
                    this.getValidacion().setErrorTotal(this.validacionCamposObligatoriosGrafica(vo));
                }
            } else {
                this.getValidacion().setMensaje("Debe llenar todos los campos indicados como obligatorios.");
            }
        } else if (vo != null) {
            logger.debug("Validacion Formulario Gerencial");
            this.getValidacion().setErrorTotal(this.validacionGerencial(vo));
        }
        return this.getValidacion();
    }

    private int validacionGerencial(ReportesVO vo) {
        int requerido = 0;
        if (vo.getFechaInicioPeriodo() == null || vo.getFechaFinPeriodo() == null) {
            requerido++;
            this.getValidacion().setMensaje("Debe llenar todos los campos indicados como obligatorios.");
        } else {
            if (vo.getFechaInicioPeriodo().after(vo.getFechaFinPeriodo())) {
                requerido++;
                this.getValidacion().setMensaje("La fecha Per\u00edodo Fin no puede ser menor a la fecha Per\u00edodo Inicio.");
            }
        }
        return requerido;
    }

    private int validacionEjecutivoRequeridos(ReportesVO vo) {
        int requerido = 0;
        if (vo.getTipoFecha() != null && !vo.getTipoFecha().trim().isEmpty()) {
            boolean[] condicionesAlmenosUnaPrimarias = new boolean[]{
                vo.getFechaInicioPeriodo() == null,
                vo.getFechaFinPeriodo() == null
            };

            boolean[] condicionesAlmenosUnaSecundarias = new boolean[]{
                vo.getMesInicial() == null,
                vo.getAnioInicial() == null,
                vo.getMesFinal() == null,
                vo.getAnioFinal() == null
            };

            if ((vo.getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_FECHAS))) && (ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAlmenosUnaPrimarias))) {
                requerido++;
            } else if ((vo.getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) && ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAlmenosUnaSecundarias)) {
                requerido++;
            }
        } else {
            requerido++;
        }
        if (vo.getTipoGrafica() == null) {
            requerido++;
        }

        return requerido;
    }

    private int validacionFechasEjecutivo(ReportesVO vo) {
        int requerido = 0;
        Date fechaAtual = new Date();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaAtual);
        if ((vo.getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_FECHAS))) && (vo.getFechaInicioPeriodo().after(vo.getFechaFinPeriodo()))) {

            requerido++;
            this.getValidacion().setMensaje("La fecha Per\u00edodo Fin no puede ser menor a la fecha Per\u00edodo Inicio.");

        }
        if (vo.getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) {
            Date fechaFinalActual = getValidacionFechasReportesHelper().crearFecha(fecha.get(Calendar.MONTH) + ConstantesReportes.N_1, fecha.get(Calendar.YEAR), true);
            Date fechaInicial = getValidacionFechasReportesHelper().crearFecha(vo.getMesInicial(), vo.getAnioInicial(), false);
            Date fechaFinal = getValidacionFechasReportesHelper().crearFecha(vo.getMesFinal(), vo.getAnioFinal(), true);
            logger.debug("Fecha Meses-Año Inicial: [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(fechaInicial));
            logger.debug("Fecha Meses-Año Final: [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(fechaFinal));
            if (fechaInicial.after(fechaFinal)) {
                requerido++;
                this.getValidacion().setMensaje("El Mes-A\u00f1o Final no puede ser menor al Mes-A\u00f1o Inicial.");
            } else if (this.validacionFechasMaximoEjecutivo(fechaInicial, fechaFinal, fechaFinalActual) > ConstantesReportes.N_0) {
                requerido++;
                this.getValidacion().setMensaje("El Mes-A\u00f1o Inicial y Mes-A\u00f1o Final no puede ser mayor al Mes-A\u00f1o actual.");
            } else if (this.validacionFechasMesAnioMaximoEjecutivo(fechaInicial, fechaFinal) > ConstantesReportes.N_0) {
                requerido++;
                this.getValidacion().setMensaje("No se permite ingresar un per\u00edodo mayor a 2 a\u00f1os");
            }
        }
        return requerido;
    }

    private int validacionFechasMaximoEjecutivo(Date fechaInicial, Date fechaFinal, Date fechaActual) {
        int requerido = 0;
        logger.debug("Fecha Meses-Año (Actual): [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(fechaActual));
        if (fechaInicial.after(fechaActual)) {
            requerido++;
        }
        if (fechaFinal.after(fechaActual)) {
            requerido++;
        }
        return requerido;
    }

    private int validacionFechasMesAnioMaximoEjecutivo(Date fechaInicial, Date fechaFinal) {
        int requerido = 0;
        Date fechaMaximo = getValidacionFechasReportesHelper().crearFechaFinalbyInicial(fechaInicial, ConstantesReportes.N_2);
        logger.debug("Fecha Meses-Año (2 Años): [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(fechaMaximo));
        if (fechaFinal.after(fechaMaximo)) {
            requerido++;
        }
        return requerido;
    }

    private int validacionCamposObligatoriosGrafica(ReportesVO vo) {
        int campoRequerido = 0;
        int error = 0;
        if (vo.isMostrarMetodo()) {
            campoRequerido++;
        }
        if (vo.isMostrarEstatus()) {
            campoRequerido++;
        }
        if (vo.isMostrarEntidad()) {
            campoRequerido++;
        }
        if (vo.isMostrarUnidad()) {
            campoRequerido++;
        }

        if (vo.getTipoGrafica().equals(ConstantesReportes.TIPO_GRAFICA_BAR) && campoRequerido != 2) {
            error++;
            this.getValidacion().setMensaje(ConstantesReportes.ERROR_GRAFICA_BAR);
        }
        if (vo.getTipoGrafica().equals(ConstantesReportes.TIPO_GRAFICA_PIE) && campoRequerido != 1) {
            error++;
            this.getValidacion().setMensaje(ConstantesReportes.ERROR_GRAFICA_PIE);
        }
        return error;
    }
    
    private String getMessageResourceString(String key, Object... params) {
        String value = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msj");

        value = formatMessage(bundle, key, params);
        return value;
    }
    
    private String formatMessage(ResourceBundle bundle, String key, Object... params) {
        String text = null;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
            logger.error("Error en ", e);
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        }
        return text;
    }

    public void setValidacion(ValidacionReportesDTO validacion) {
        this.validacion = validacion;
    }

    public ValidacionReportesDTO getValidacion() {
        return validacion;
    }

    public void setValidacionFechasReportesHelper(ValidacionFechasReportesHelper validacionFechasReportesHelper) {
        this.validacionFechasReportesHelper = validacionFechasReportesHelper;
    }

    public ValidacionFechasReportesHelper getValidacionFechasReportesHelper() {
        return validacionFechasReportesHelper;
    }
}
