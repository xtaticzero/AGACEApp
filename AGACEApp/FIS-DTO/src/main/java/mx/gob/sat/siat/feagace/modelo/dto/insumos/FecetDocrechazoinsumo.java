/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocrechazoinsumo extends BaseModel {

    private static final long serialVersionUID = 2212584320459182575L;

    private static final int HASH_CONSTANT = 29;
    private static final int CONSTANT = 7;

    private BigDecimal idDocrechazoinsumo;

    private BigDecimal idRechazoInsumo;

    private String nombreArchivo;

    private String rutaArchivo;

    private Date fechaCreacion;

    private transient InputStream archivo;

    public FecetDocrechazoinsumo() {
    }

    public BigDecimal getIdDocrechazoinsumo() {
        return idDocrechazoinsumo;
    }

    public void setIdDocrechazoinsumo(BigDecimal idDocrechazoinsumo) {
        this.idDocrechazoinsumo = idDocrechazoinsumo;
    }

    public BigDecimal getIdRechazoInsumo() {
        return idRechazoInsumo;
    }

    public void setIdRechazoInsumo(BigDecimal idRechazoInsumo) {
        this.idRechazoInsumo = idRechazoInsumo;
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
        this.fechaCreacion = fechaCreacion != null ? new Date(
                fechaCreacion.getTime()) : null;
    }

    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public FecetDocrechazoinsumoPk createPk() {
        return new FecetDocrechazoinsumoPk(idDocrechazoinsumo);
    }

    @Override
    public int hashCode() {
        int hash = CONSTANT;
        hash = HASH_CONSTANT * hash + (this.idDocrechazoinsumo != null ? this.idDocrechazoinsumo.hashCode() : 0);
        hash = HASH_CONSTANT * hash + (this.rutaArchivo != null ? this.rutaArchivo.hashCode() : 0);
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
        final FecetDocrechazoinsumo other = (FecetDocrechazoinsumo) obj;
        if (this.idDocrechazoinsumo != other.idDocrechazoinsumo && (this.idDocrechazoinsumo == null || !this.idDocrechazoinsumo.equals(other.idDocrechazoinsumo))) {
            return false;
        }
        if ((this.rutaArchivo == null) ? (other.rutaArchivo != null) : !this.rutaArchivo.equals(other.rutaArchivo)) {
            return false;
        }
        return true;
    }

}
