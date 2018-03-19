/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;


public class FecetTipoOficio extends BaseModel{

    @SuppressWarnings("compatibility:-3756531615595423169")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idTipoOficio;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private AgrupadorOficiosEnum agrupador;
    private Date fechaFin;
    private BigDecimal blnActivo;


    public BigDecimal getIdTipoOficio() {
        return idTipoOficio;
    }

    public void setIdTipoOficio(BigDecimal idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }
    
    public AgrupadorOficiosEnum getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(AgrupadorOficiosEnum agrupador) {
		this.agrupador = agrupador;
	}

	public static FecetTipoOficio construirTipoOficio(TiposOficiosOrdenesEnum tipoOficio){
        FecetTipoOficio newtipoOficio = new FecetTipoOficio();
        newtipoOficio.setIdTipoOficio(tipoOficio.getBigIdTipoOficio());
        return newtipoOficio;
    }
	
	public static FecetTipoOficio construirTipoOficioMedidasApremio(BigDecimal tipoOficio){
        FecetTipoOficio newtipoOficio = new FecetTipoOficio();
        newtipoOficio.setIdTipoOficio(tipoOficio);
        return newtipoOficio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }
}
