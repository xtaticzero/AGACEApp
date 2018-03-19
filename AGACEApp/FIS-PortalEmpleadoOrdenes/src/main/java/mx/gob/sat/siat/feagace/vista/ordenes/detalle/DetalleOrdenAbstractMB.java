/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.detalle;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.propuestas.DetalleOrdenService;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenDTOHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenListHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper.DetalleOrdenStreamedHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleOrdenAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = 4247810717572935581L;

    @ManagedProperty(value = "#{detalleOrdenService}")
    private transient DetalleOrdenService detalleOrdenService;
    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;

    private DetalleOrdenDTOHelper detalleOrdenDtoHelper;
    private DetalleOrdenStreamedHelper detalleOrdenStreamedHelper;
    private DetalleOrdenListHelper detalleOrdenLstHelper;

    public void setDetalleOrdenService(DetalleOrdenService detalleOrdenService) {
        this.detalleOrdenService = detalleOrdenService;
    }

    public DetalleOrdenService getDetalleOrdenService() {
        return detalleOrdenService;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public DetalleOrdenDTOHelper getDetalleOrdenDtoHelper() {
        return detalleOrdenDtoHelper;
    }

    public void setDetalleOrdenDtoHelper(DetalleOrdenDTOHelper detalleOrdenDtoHelper) {
        this.detalleOrdenDtoHelper = detalleOrdenDtoHelper;
    }

    public DetalleOrdenStreamedHelper getDetalleOrdenStreamedHelper() {
        return detalleOrdenStreamedHelper;
    }

    public void setDetalleOrdenStreamedHelper(DetalleOrdenStreamedHelper detalleOrdenStreamedHelper) {
        this.detalleOrdenStreamedHelper = detalleOrdenStreamedHelper;
    }

    public DetalleOrdenListHelper getDetalleOrdenLstHelper() {
        return detalleOrdenLstHelper;
    }

    public void setDetalleOrdenLstHelper(DetalleOrdenListHelper detalleOrdenLstHelper) {
        this.detalleOrdenLstHelper = detalleOrdenLstHelper;
    }

}
