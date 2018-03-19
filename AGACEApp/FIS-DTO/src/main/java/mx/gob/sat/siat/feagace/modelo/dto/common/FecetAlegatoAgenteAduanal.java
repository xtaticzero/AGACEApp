/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetAlegatoAgenteAduanal extends BaseModel {

    private static final long serialVersionUID = 1L;

    private BigDecimal idAlegatoAgenteAduanal;
    private BigDecimal idPromocionAgeneteAduanal;
    private Date fechaCarga;
    private String nombreArchivo;
    private String rutaArchivo;
    private Short blnActivo;
    private Date fechaFin;
    private transient InputStream archivo;
    private BigDecimal idArchivoTemp;

    public BigDecimal getIdAlegatoAgenteAduanal() {
        return idAlegatoAgenteAduanal;
    }

    public void setIdAlegatoAgenteAduanal(BigDecimal idAlegatoAgenteAduanal) {
        this.idAlegatoAgenteAduanal = idAlegatoAgenteAduanal;
    }

    public BigDecimal getIdPromocionAgeneteAduanal() {
        return idPromocionAgeneteAduanal;
    }

    public void setIdPromocionAgeneteAduanal(BigDecimal idPromocionAgeneteAduanal) {
        this.idPromocionAgeneteAduanal = idPromocionAgeneteAduanal;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
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

    public Short getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Short blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }

    public FecetAlegatoAgenteAduanalPk createPk() {
        return new FecetAlegatoAgenteAduanalPk(idAlegatoAgenteAduanal);
    }
}
