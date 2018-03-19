/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.List;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.feagace.negocio.exception.ValidarYRetroalimentarPropuestaRuleException;
import mx.gob.sat.siat.feagace.negocio.rules.ContribuyenteAmparadoPpeeRule;
import mx.gob.sat.siat.sise.service.SiseService;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Component("amparadoPpeeBR")
public class ContribuyenteAmparadoPpeeRuleImpl extends BaseBusinessRules implements ContribuyenteAmparadoPpeeRule {

    private static final long serialVersionUID = -5728549352944832856L;

    private transient Logger logger = Logger.getLogger(ContribuyenteAmparadoPpeeRuleImpl.class);

    @Autowired
    @Qualifier("buzonTributarioService")
    private transient BuzonTributarioService buzonTributarioService;

    @Autowired
    @Qualifier("siseService")
    private transient SiseService siseService;

    @Override
    public boolean esContribuyenteAmparado(String rfc) throws ValidarYRetroalimentarPropuestaRuleException {

        try {
            List<MedioComunicacion> lstMedios = buzonTributarioService.obtenerMediosComunicacion(rfc);
            for (MedioComunicacion medio : lstMedios) {
                logger.error(new StringBuffer("buzonTributarioService:....").append(medio.getMedio()));
                if (medio.getAmparado() != 0) {
                    return true;
                }
            }
            return false;
        } catch (BuzonNoDisponibleException ex) {
            throw new ValidarYRetroalimentarPropuestaRuleException("usuario.amparado", ex);
        }
    }

    @Override
    public boolean esContribuyentePpee(String rfc) throws ValidarYRetroalimentarPropuestaRuleException {
        try {
            logger.error(new StringBuilder("SISE:....").append(siseService.verInformacion(rfc)));
            return siseService.verInformacion(rfc) != 0 ? true : false;

        } catch (Exception ex) {
            throw new ValidarYRetroalimentarPropuestaRuleException("usuario.ppe", ex);
        }

    }

    @Override
    public boolean esContribuyenteAmparadoOPpee(String rfc) throws ValidarYRetroalimentarPropuestaRuleException {
        return false;
    }

}
