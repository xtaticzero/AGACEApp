/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AccesoAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = -3859888081919806459L;

    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

}
