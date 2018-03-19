/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.grafica;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.reportes.GenerarReportesService;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class GraficaPropuestasAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = -3222151521828973413L;

    @ManagedProperty(value = "#{generarReportesService}")
    private transient GenerarReportesService generarReportesService;
    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    private transient List<ByteArrayInputStream> listaGraficas;
    private List<BigDecimal> listaIdGraficas;
    private transient Map<BigDecimal, byte[]> listaGraficasId;

    public void setGenerarReportesService(GenerarReportesService generarReportesService) {
        this.generarReportesService = generarReportesService;
    }

    public GenerarReportesService getGenerarReportesService() {
        return generarReportesService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }
    
    public void setListaGraficas(List<ByteArrayInputStream> listaGraficas) {
        this.listaGraficas = listaGraficas;
    }

    public List<ByteArrayInputStream> getListaGraficas() {
        if (listaIdGraficas != null) {
            listaGraficas = new ArrayList<ByteArrayInputStream>();
            for (BigDecimal idGrafica : listaIdGraficas) {
                byte[] archivo = getArchivoTempService().consultaArchivoTemp(idGrafica, getRFCSession());
                logger.debug("Id imagenGrafica: "+idGrafica);
                logger.debug("Size imagenGrafica: " +archivo.length);
                ByteArrayInputStream in = new ByteArrayInputStream(archivo);
                listaGraficas.add(in);
            }
        }
        return listaGraficas;
    }

    public void setListaIdGraficas(List<BigDecimal> listaIdGraficas) {
        this.listaIdGraficas = listaIdGraficas;
    }

    public List<BigDecimal> getListaIdGraficas() {
        return listaIdGraficas;
    }

    public Map<BigDecimal, byte[]> getListaGraficasId() {
        if (listaGraficasId == null) {
            listaGraficasId = new HashMap<BigDecimal, byte[]>();
        }
        return listaGraficasId;
    }

}
