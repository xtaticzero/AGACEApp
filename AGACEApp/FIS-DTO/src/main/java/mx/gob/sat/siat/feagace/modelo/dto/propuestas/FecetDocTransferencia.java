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

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FecetDocTransferencia extends BaseModel{
    private static final long serialVersionUID = -2068699435641661045L;
    
    private BigDecimal idPropuesta;
    private BigDecimal idDocTransferencia;
    private BigDecimal idTrnsferencia;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private transient InputStream archivo;
    private EstadoBooleanodeRegistroEnum blnActivo;

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdDocTransferencia() {
        return idDocTransferencia;
    }

    public void setIdDocTransferencia(BigDecimal idDocTransferencia) {
        this.idDocTransferencia = idDocTransferencia;
    }

    public BigDecimal getIdTrnsferencia() {
        return idTrnsferencia;
    }

    public void setIdTrnsferencia(BigDecimal idTrnsferencia) {
        this.idTrnsferencia = idTrnsferencia;
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
        this.fechaCreacion = (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;        
    }

    public EstadoBooleanodeRegistroEnum getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(EstadoBooleanodeRegistroEnum blnActivo) {
        this.blnActivo = blnActivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }
    
}
