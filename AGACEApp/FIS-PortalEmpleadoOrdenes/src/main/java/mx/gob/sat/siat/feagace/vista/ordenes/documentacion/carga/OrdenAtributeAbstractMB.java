/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetDocAsociadoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.service.GenerarOficioService;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.SuspensionService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class OrdenAtributeAbstractMB extends AbstractManagedBean {

    private static final long serialVersionUID = 7901643823194598398L;
    protected static final int SIZE_BUFFER = 1024;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{generarOficioService}")
    private transient GenerarOficioService generarOficioService;

    @ManagedProperty(value = "#{plazosService}")
    private transient PlazosServiceOrdenes plazosService;

    @ManagedProperty(value = "#{suspensionService}")
    private transient SuspensionService suspensionService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    @ManagedProperty(value = "#{consultarPapelesTrabajoService}")
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService;

    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;

    @ManagedProperty(value = "#{fecetDocAsociadoService}")
    private transient FecetDocAsociadoService fecetDocAsociadoService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private transient AuditoriaService auditoriaService;

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public void setPlazosService(PlazosServiceOrdenes plazosService) {
        this.plazosService = plazosService;
    }

    public PlazosServiceOrdenes getPlazosService() {
        return plazosService;
    }

    public SuspensionService getSuspensionService() {
        return suspensionService;
    }

    public void setSuspensionService(SuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public FecetDocAsociadoService getFecetDocAsociadoService() {
        return fecetDocAsociadoService;
    }

    public void setFecetDocAsociadoService(FecetDocAsociadoService fecetDocAsociadoService) {
        this.fecetDocAsociadoService = fecetDocAsociadoService;
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

    protected StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    protected StreamedContent getArchivoGenericoAnexoProrrogaOrden(final String path, final String nombre) {
        return getDescargaArchivo(path, nombre);
    }

    protected StreamedContent getArchivoGenericoOficioAnexo(final String path, final String nombre) {
        return getDescargaArchivo(path, nombre);
    }

    protected byte[] lecturaArchivo(InputStream archivoEntrada) {
        final ByteArrayOutputStream resultado = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[SIZE_BUFFER];
        try {
            while ((nRead = archivoEntrada.read(data, 0, data.length)) != -1) {
                resultado.write(data, 0, nRead);
            }
            resultado.flush();
        } catch (IOException e) {
            logger.error("Error al realizar la lectura de archivo para descarga", e);
        }
        return resultado.toByteArray();
    }

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

}
