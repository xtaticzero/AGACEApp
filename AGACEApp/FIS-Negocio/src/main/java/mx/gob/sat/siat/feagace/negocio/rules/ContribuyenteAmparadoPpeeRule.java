/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules;

import mx.gob.sat.siat.feagace.negocio.exception.ValidarYRetroalimentarPropuestaRuleException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ContribuyenteAmparadoPpeeRule {

    boolean esContribuyenteAmparado(String rfc) throws ValidarYRetroalimentarPropuestaRuleException;

    boolean esContribuyentePpee(String rfc) throws ValidarYRetroalimentarPropuestaRuleException;

    boolean esContribuyenteAmparadoOPpee(String rfc) throws ValidarYRetroalimentarPropuestaRuleException;
}
