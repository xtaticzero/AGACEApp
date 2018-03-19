/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DocRetroalimentacionDTO extends BaseModel{
    private static final long serialVersionUID = 2634166992326736257L;
    
    private BigDecimal idDocRetroalimentacion;    
    private BigDecimal idPropuesta;
    private BigDecimal idRetroalimentacion;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private Date fechaFin;
    private EstadoBooleanodeRegistroEnum blnEstatus;
    private transient InputStream archivo;

    public BigDecimal getIdDocRetroalimentacion() {
        return idDocRetroalimentacion;
    }

    public void setIdDocRetroalimentacion(BigDecimal idDocRetroalimentacion) {
        this.idDocRetroalimentacion = idDocRetroalimentacion;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public EstadoBooleanodeRegistroEnum getBlnEstatus() {
        return blnEstatus;
    }

    public void setBlnEstatus(EstadoBooleanodeRegistroEnum blnEstatus) {
        this.blnEstatus = blnEstatus;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public int hashCode() {
        int hash = Constantes.ENTERO_SIETE;
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.idDocRetroalimentacion != null ? this.idDocRetroalimentacion.hashCode() : 0);
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.idPropuesta != null ? this.idPropuesta.hashCode() : 0);
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.idRetroalimentacion != null ? this.idRetroalimentacion.hashCode() : 0);
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.rutaArchivo != null ? this.rutaArchivo.hashCode() : 0);
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.fechaCreacion != null ? this.fechaCreacion.hashCode() : 0);
        hash = Constantes.ENTERO_CUARENTAUNO * hash + (this.fechaFin != null ? this.fechaFin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocRetroalimentacionDTO other = (DocRetroalimentacionDTO) obj;
        if (this.idDocRetroalimentacion != other.idDocRetroalimentacion && (this.idDocRetroalimentacion == null || !this.idDocRetroalimentacion.equals(other.idDocRetroalimentacion))) {
            return false;
        }
        if (this.idPropuesta != other.idPropuesta && (this.idPropuesta == null || !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        if (this.idRetroalimentacion != other.idRetroalimentacion && (this.idRetroalimentacion == null || !this.idRetroalimentacion.equals(other.idRetroalimentacion))) {
            return false;
        }
        if ((this.rutaArchivo == null) ? (other.rutaArchivo != null) : !this.rutaArchivo.equals(other.rutaArchivo)) {
            return false;
        }
        if (this.fechaCreacion != other.fechaCreacion && (this.fechaCreacion == null || !this.fechaCreacion.equals(other.fechaCreacion))) {
            return false;
        }
        if (this.fechaFin != other.fechaFin && (this.fechaFin == null || !this.fechaFin.equals(other.fechaFin))) {
            return false;
        }
        return true;
    }
    
    
}
