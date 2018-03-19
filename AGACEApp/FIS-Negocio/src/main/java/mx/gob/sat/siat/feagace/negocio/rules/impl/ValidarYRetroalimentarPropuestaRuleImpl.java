/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.rules.ValidarYRetroalimentarPropuestaRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Component("validarYRetroalimentarPropuestaRule")
public class ValidarYRetroalimentarPropuestaRuleImpl extends ServiceAbstract implements ValidarYRetroalimentarPropuestaRule {

    private static final long serialVersionUID = 8425803282331295665L;

    private static final String ERROR_EMPLEADO_NO_EXISTE = "empleado.no.existente";

    @Override
    public boolean esUnProgramadorActivo(String rfcEmpleado) {
        try {
            EmpleadoDTO empleado = getEmpleadoXRfcTipo(rfcEmpleado, TipoEmpleadoEnum.PROGRAMADOR);
            return empleado.getRfc() != null && !empleado.getRfc().isEmpty();
        } catch (NoExisteEmpleadoException ex) {
            return false;
        }

    }

    @Override
    public boolean esCentral(TipoAraceEnum arace) {
        return (arace.equals(TipoAraceEnum.ACPPCE));
    }

    @Override
    public boolean esRegional(TipoAraceEnum arace) {

        for (TipoAraceEnum tipoArace : TipoAraceEnum.values()) {
            if (tipoArace.equals(TipoAraceEnum.ADACE_CENTRO) && arace.equals(TipoAraceEnum.ADACE_CENTRO)) {
                return true;
            }
            if (tipoArace.equals(TipoAraceEnum.ADACE_NOROESTE) && arace.equals(TipoAraceEnum.ADACE_NOROESTE)) {
                return true;
            }
            if (tipoArace.equals(TipoAraceEnum.ADACE_NORTE_CENTRO) && arace.equals(TipoAraceEnum.ADACE_NORTE_CENTRO)) {
                return true;
            }
            if (tipoArace.equals(TipoAraceEnum.ADACE_OCCIDENTE) && arace.equals(TipoAraceEnum.ADACE_OCCIDENTE)) {
                return true;
            }
            if (tipoArace.equals(TipoAraceEnum.ADACE_PACIFICO_NORTE) && arace.equals(TipoAraceEnum.ADACE_PACIFICO_NORTE)) {
                return true;
            }
            if (tipoArace.equals(TipoAraceEnum.ADACE_SUR) && arace.equals(TipoAraceEnum.ADACE_SUR)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sePuedeAprobarRechazarPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException {

        for (TipoEstatusEnum tipoEstatus : TipoEstatusEnum.values()) {
            if (tipoEstatus.equals(TipoEstatusEnum.PROPUESTA_REGISTRADA) && estatusActual.equals(TipoEstatusEnum.PROPUESTA_REGISTRADA)) {
                return true;
            }
            if (tipoEstatus.equals(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL)
                    && estatusActual.equals(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL)) {
                return true;
            }
            if (tipoEstatus.equals(TipoEstatusEnum.PROPUESTA_PENDIENTE) && estatusActual.equals(TipoEstatusEnum.PROPUESTA_PENDIENTE)) {
                return true;
            }
            if (tipoEstatus.equals(TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION)
                    && estatusActual.equals(TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION)) {
                return true;
            }
            if (tipoEstatus.equals(TipoEstatusEnum.PROPUESTA_ENVIADA_SOLICITUD_RETROALIMENTACION)
                    && estatusActual.equals(TipoEstatusEnum.PROPUESTA_ENVIADA_SOLICITUD_RETROALIMENTACION)) {
                return true;
            }
        }

        throw new AgacePropuestasRulesException("estado.propuesta");
    }

    @Override
    public boolean sePuedePostergarPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException {
        if ((estatusActual.equals(TipoEstatusEnum.PROPUESTA_REGISTRADA) || estatusActual.equals(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL))) {
            return true;

        }
        throw new AgacePropuestasRulesException("estado.propuesta");
    }

    @Override
    public boolean sePuedeTransferirPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException {
        if ((estatusActual.equals(TipoEstatusEnum.PROPUESTA_TRANSFERENCIA_APROBADA))) {
            return true;
        }
        throw new AgacePropuestasRulesException("estado.propuesta");
    }

    @Override
    public boolean validarFolioEsPropuestasXValidar(String idRegistro) {
        Pattern pat = Pattern.compile(TIPO_FOLIO_PROPUESTA_X_VALIDAR);
        Matcher mat = pat.matcher(idRegistro.toUpperCase());
        return mat.matches();
    }

    @Override
    public boolean validarFolioPropuestasCentralARegional(String idRegistro) {
        Pattern pat = Pattern.compile(TIPO_FOLIO_PROPUESTA_X_VALIDAR_CENTRAL_A_REGIONAL);
        Matcher mat = pat.matcher(idRegistro.toUpperCase());
        return mat.matches();
    }

    @Override
    public boolean validaFolioEstatusConfirmXRegional(String rfcCreacion, TipoAraceEnum arace, String idRegistro, TipoEstatusEnum estatusActual) {
        try {
            Pattern pat = Pattern.compile(TIPO_FOLIO_PROPUESTA_X_VALIDAR_CENTRAL_A_REGIONAL);
            Matcher mat = pat.matcher(idRegistro.toUpperCase());

            Pattern patCentral = Pattern.compile(TIPO_FOLIO_PROPUESTA_X_VALIDAR);
            Matcher matCentral = patCentral.matcher(idRegistro.toUpperCase());

            TipoAraceEnum araceEmpleado;
            EmpleadoDTO empleado = getEmpleadoXRfcTipo(rfcCreacion, TipoEmpleadoEnum.PROGRAMADOR);
            DetalleEmpleadoDTO detalle = null;

            if (empleado != null && empleado.getDetalleEmpleado() != null) {
                for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                    detalle = det;
                    break;
                }
                if (detalle != null) {
                    if (matCentral.matches()) {
                        return true;
                    }
                    araceEmpleado = getTipoAraceEnumById(detalle.getCentral().getIdArace());
                    if (esCentral(araceEmpleado) && esRegional(arace)) {
                        return !(mat.matches() && (estatusActual.equals(TipoEstatusEnum.PROPUESTA_REGISTRADA)));
                    } else {
                        return true;
                    }
                }

            }

        } catch (NoExisteEmpleadoException ex) {
            return true;
        } catch (Exception ex) {
            return true;
        }
        return true;
    }

    @Override
    public boolean validarFechaPresentacionAComite(String idRegistroPropuesta, Date fechaPresentacionComite) throws AgacePropuestasRulesException {
        boolean flgFechaValida;
        Date fechaTramite = new Date();

        if (fechaPresentacionComite == null) {
            return false;
        }

        flgFechaValida = DateUtil.verificarEsFechaAnteriorOIgual(fechaPresentacionComite, fechaTramite);

        if (!flgFechaValida) {
            throw new AgacePropuestasRulesException("fecha.presentacion.comite", idRegistroPropuesta,
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fechaPresentacionComite));
        }

        return flgFechaValida;
    }

    private EmpleadoDTO getEmpleadoXRfcTipo(String rfc, TipoEmpleadoEnum tipo) throws NoExisteEmpleadoException {
        try {

            if (rfc == null || tipo == null) {
                throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE);
            }

            EmpleadoDTO empleado = getEmpleadoCompleto(rfc);
            if (empleado != null && empleado.getDetalleEmpleado() != null) {
                for (DetalleEmpleadoDTO det : empleado.getDetalleEmpleado()) {
                    if (det.getTipoEmpleado().equals(tipo)) {
                        return empleado;
                    }
                }
            }
            throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE);
        } catch (EmpleadoServiceException ex) {
            throw new NoExisteEmpleadoException(ERROR_EMPLEADO_NO_EXISTE, ex);
        }
    }

    private TipoAraceEnum getTipoAraceEnumById(Integer idArace) {
        if (idArace != null) {
            for (TipoAraceEnum tipo : TipoAraceEnum.values()) {
                if ((int) tipo.getId() == idArace) {
                    return tipo;
                }
            }

        }
        return null;
    }

}
