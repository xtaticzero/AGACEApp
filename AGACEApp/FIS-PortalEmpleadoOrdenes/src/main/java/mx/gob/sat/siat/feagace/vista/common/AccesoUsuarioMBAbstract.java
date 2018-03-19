/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.common;

import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.PistaAuditoriaDescargaDocumentosService;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AccesoUsuarioMBAbstract extends BaseManagedBean {

    private static final long serialVersionUID = -1176989363895073478L;

    @ManagedProperty(value = "#{empleadoService}")
    private transient EmpleadoService empleadoService;

    @ManagedProperty(value = "#{auditoriaDocumentos}")
    private transient PistaAuditoriaDescargaDocumentosService pistaAuditoriaDescargaDocumentosService;

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public PistaAuditoriaDescargaDocumentosService getPistaAuditoriaDescargaDocumentosService() {
        return pistaAuditoriaDescargaDocumentosService;
    }

    public void setPistaAuditoriaDescargaDocumentosService(
            PistaAuditoriaDescargaDocumentosService pistaAuditoriaDescargaDocumentosService) {
        this.pistaAuditoriaDescargaDocumentosService = pistaAuditoriaDescargaDocumentosService;
    }

}
