/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class OrdenesServiceBase extends PropuestasServiceAbstract {

    private static final long serialVersionUID = 3285453023347959784L;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    public AgaceOrdenDao getAgaceOrdenDao() {
        return agaceOrdenDao;
    }

    public void setAgaceOrdenDao(AgaceOrdenDao agaceOrdenDao) {
        this.agaceOrdenDao = agaceOrdenDao;
    }

}
