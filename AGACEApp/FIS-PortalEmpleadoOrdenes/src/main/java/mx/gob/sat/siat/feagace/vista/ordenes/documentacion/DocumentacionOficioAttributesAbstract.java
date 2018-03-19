/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.service.GenerarOficioService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocumentacionOficioAttributesAbstract extends AbstractManagedBean {

    private static final long serialVersionUID = -5582993185041860753L;

    protected static final String MENSAJE_EXITO_CARGA_OFICIO = "msg.exito.carga.oficio.validacion";
    protected static final String MENSAJE_ERROR_CARGA_RESOLUCION = "msg.error.carga.prorroga.resolucion";
    protected static final String MENSAJE_CARGA_OFICIO = "msgExitoGuardarOficio";

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{cargarFirmaPruebasPromoService}")
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @ManagedProperty(value = "#{generarOficioService}")
    private transient GenerarOficioService generarOficioService;

    @ManagedProperty(value = "#{consultarPapelesTrabajoService}")
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private transient AuditoriaService auditoriaService;

    public void setCargarFirmaPruebasPromoService(final CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService) {
        this.cargarFirmaPruebasPromoService = cargarFirmaPruebasPromoService;
    }

    public CargarFirmaPruebasPromoService getCargarFirmaPruebasPromoService() {
        return cargarFirmaPruebasPromoService;
    }

    public GenerarOficioService getGenerarOficioService() {
        return generarOficioService;
    }

    public void setGenerarOficioService(GenerarOficioService generarOficioService) {
        this.generarOficioService = generarOficioService;
    }

    public ConsultarPapelesTrabajoService getConsultarPapelesTrabajoService() {
        return consultarPapelesTrabajoService;
    }

    public void setConsultarPapelesTrabajoService(ConsultarPapelesTrabajoService consultarPapelesTrabajoService) {
        this.consultarPapelesTrabajoService = consultarPapelesTrabajoService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

}
