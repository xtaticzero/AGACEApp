/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class DocumentoVista extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String ruta;
    private Date fechaCreacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

}
