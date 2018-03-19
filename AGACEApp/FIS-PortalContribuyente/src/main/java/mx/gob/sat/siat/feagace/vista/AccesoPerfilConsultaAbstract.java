/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.impl.ContribuyenteServiceImpl;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.impl.AsociadosServiceImpl;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AccesoPerfilConsultaAbstract extends BaseManagedBean{
    
    private static final long serialVersionUID = 4227433280728066153L;
    
    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    public AccesoPerfilConsultaAbstract() {
        logger.info("AccesoPerfilConsultaAbstract");
    }

    @PostConstruct
    protected void init(){
        logger.info("AccesoPerfilConsultaAbstract.init");
        if(asociadosService==null){
            asociadosService = new AsociadosServiceImpl();
        }
        if(contribuyenteService == null){
            contribuyenteService = new ContribuyenteServiceImpl();
        }
    }
    
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
