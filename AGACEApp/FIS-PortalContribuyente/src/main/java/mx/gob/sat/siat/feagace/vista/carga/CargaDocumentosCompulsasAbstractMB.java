/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosCompulsasDTOHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosStreamedHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class CargaDocumentosCompulsasAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = 1442710264940937555L;

    private CargaDocumentosCompulsasDTOHelper cargaDoctosDTOHelper;
    private CargaDocumentosStreamedHelper cargaDoctosStreamedHelper;

    @ManagedProperty(value = "#{cargarFirmaPruebasPromoService}")
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @ManagedProperty(value = "#{cargarFirmaProrrogaService}")
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setCargarFirmaProrrogaService(CargarFirmaProrrogaService cargarFirmaProrrogaService) {
        this.cargarFirmaProrrogaService = cargarFirmaProrrogaService;
    }

    public CargarFirmaProrrogaService getCargarFirmaProrrogaService() {
        return cargarFirmaProrrogaService;
    }

    public void setCargarFirmaPruebasPromoService(CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService) {
        this.cargarFirmaPruebasPromoService = cargarFirmaPruebasPromoService;
    }

    public CargarFirmaPruebasPromoService getCargarFirmaPruebasPromoService() {
        return cargarFirmaPruebasPromoService;
    }

    public void setCargaDoctosDTOHelper(CargaDocumentosCompulsasDTOHelper cargaDoctosDTOHelper) {
        this.cargaDoctosDTOHelper = cargaDoctosDTOHelper;
    }

    public void setCargaDoctosStreamedHelper(CargaDocumentosStreamedHelper cargaDoctosStreamedHelper) {
        this.cargaDoctosStreamedHelper = cargaDoctosStreamedHelper;
    }

    public CargaDocumentosCompulsasDTOHelper getCargaDoctosDTOHelper() {
        return cargaDoctosDTOHelper;
    }

    public CargaDocumentosStreamedHelper getCargaDoctosStreamedHelper() {
        return cargaDoctosStreamedHelper;
    }

}
