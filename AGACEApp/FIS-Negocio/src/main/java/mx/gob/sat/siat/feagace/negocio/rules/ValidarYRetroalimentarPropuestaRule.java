/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules;

import java.util.Date;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ValidarYRetroalimentarPropuestaRule {

    String TIPO_FOLIO_PROPUESTA_X_VALIDAR = "^.P.*";
    String TIPO_FOLIO_PROPUESTA_X_VALIDAR_CENTRAL_A_REGIONAL = "^.I.*";

    boolean esUnProgramadorActivo(String rfcEmpleado);

    boolean esCentral(TipoAraceEnum arace);

    boolean esRegional(TipoAraceEnum arace);

    boolean sePuedeAprobarRechazarPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException;

    boolean sePuedePostergarPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException;

    boolean sePuedeTransferirPropuesta(TipoEstatusEnum estatusActual) throws AgacePropuestasRulesException;

    boolean validarFolioEsPropuestasXValidar(String idRegistro);
    
    boolean validarFolioPropuestasCentralARegional(String idRegistro);
    
    boolean validaFolioEstatusConfirmXRegional(String rfcEmpleado, TipoAraceEnum arace, String idRegistro, TipoEstatusEnum estatusActual);

    boolean validarFechaPresentacionAComite(String idRegistroPropuesta, Date fechaPresentacionComite) throws AgacePropuestasRulesException;    

}
