/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 *
 * @author jose.aguilar
 */
public class FecetDocumento extends BaseModel {
    
    private static final long serialVersionUID = -2808806638759989896L;
    BigDecimal idDocumento;
    BigDecimal idTipoDocumento;
    BigDecimal idInsumo;
    BigDecimal idPropuesta;
    BigDecimal idOrden;
    String rutaArchivo;
    String nombre;
    Date fechaCreacion;
    Date fechaBaja;
    private transient InputStream archivo;

    public BigDecimal getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(BigDecimal idDocumento) {
        this.idDocumento = idDocumento;
    }

    public BigDecimal getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
