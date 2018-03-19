/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class FecetPromocionAgenteAduanal extends BaseModel {
    private static final long serialVersionUID = 1L;

    private BigDecimal idPromocionAgenteAduanal;
    private BigDecimal idOrden;
    private Date fechaCarga;
    private String rutaArchivo;
    private String nombreArchivo;
    private String tipoPromocion;
    private BigDecimal idAsociadoCarga;
    private BigDecimal idEmpleado;
    private Integer contadorPruebasAlegatos;
    private transient InputStream archivo;
    private BigDecimal idArchivoTemp;
    private String leyenda;
    private FecetAsociado asociado;

    public BigDecimal getIdPromocionAgenteAduanal() {
        return idPromocionAgenteAduanal;
    }

    public void setIdPromocionAgenteAduanal(BigDecimal idPromocionAgenteAduanal) {
        this.idPromocionAgenteAduanal = idPromocionAgenteAduanal;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public BigDecimal getIdAsociadoCarga() {
        return idAsociadoCarga;
    }

    public void setIdAsociadoCarga(BigDecimal idAsociadoCarga) {
        this.idAsociadoCarga = idAsociadoCarga;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public FecetPromocionAgenteAduanalPk createPk() {
        return new FecetPromocionAgenteAduanalPk(idPromocionAgenteAduanal);
    }

    public void setContadorPruebasAlegatos(Integer contadorPruebasAlegatos) {
        this.contadorPruebasAlegatos = contadorPruebasAlegatos;
    }

    public Integer getContadorPruebasAlegatos() {
        return contadorPruebasAlegatos;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setAsociado(FecetAsociado asociado) {
        this.asociado = asociado;
    }

    public FecetAsociado getAsociado() {
        return asociado;
    }
}
