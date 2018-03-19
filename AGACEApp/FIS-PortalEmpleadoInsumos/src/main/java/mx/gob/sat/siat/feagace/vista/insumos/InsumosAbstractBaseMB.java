/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos;

import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class InsumosAbstractBaseMB extends AbstractManagedBean {

    private static final long serialVersionUID = -4319394205177223144L;

    private List<FecetDocumento> lstDocJustificacion;
    private String rutaArchivoDescargable;
    private String nombreArchivoDescargable;

    public InsumosAbstractBaseMB() {
        lstDocJustificacion = new ArrayList<FecetDocumento>();
    }
    
    public StreamedContent getDescargaArchivo(){
        return getDescargaArchivo(rutaArchivoDescargable, nombreArchivoDescargable);
    }

    public int getNoDocJustificacion() {
        return lstDocJustificacion != null ? lstDocJustificacion.size() : 0;
    }

    public List<FecetDocumento> getLstDocJustificacion() {
        return lstDocJustificacion;
    }

    public void setLstDocJustificacion(List<FecetDocumento> lstDocJustificacion) {
        this.lstDocJustificacion = lstDocJustificacion;
    }

    public String getRutaArchivoDescargable() {
        return rutaArchivoDescargable;
    }

    public void setRutaArchivoDescargable(String rutaArchivoDescargable) {
        this.rutaArchivoDescargable = rutaArchivoDescargable;
    }

    public String getNombreArchivoDescargable() {
        return nombreArchivoDescargable;
    }

    public void setNombreArchivoDescargable(String nombreArchivoDescargable) {
        this.nombreArchivoDescargable = nombreArchivoDescargable;
    }

}
