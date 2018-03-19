/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

/**
 * @author sergio.vaca
 *
 */
public class FecebAccionPropuesta extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idPropuesta;
    private BigDecimal idDetalleAccion;
    private BigDecimal idAccion;
    private BigDecimal idAccionOrigen;
    private String observaciones;
    private Date fechaHora;
    private BigDecimal idAccionPropuesta;
    private BigDecimal idRetroalimentacion;
    private BigDecimal idRechazo;
    private BigDecimal idTransferencia;
    private BigDecimal idCancelacion;
    private FececAccionesFuncionario fececAccionesFuncionario;
    private AccionesFuncionarioEnum accionFuncionarioEnum;
    private FececMotivo fececMotivo;
    private EmpleadoDTO empleadoDto;
    private int numeroDocumentos;
    private BigDecimal idEmpleado;
    private String descripcionTipoEmpleado;

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public EmpleadoDTO getEmpleadoDto() {
        return empleadoDto;
    }

    public void setEmpleadoDto(EmpleadoDTO empleadoDto) {
        this.empleadoDto = empleadoDto;
    }

    public FececAccionesFuncionario getFececAccionesFuncionario() {
        return fececAccionesFuncionario;
    }

    public void setFececAccionesFuncionario(FececAccionesFuncionario fececAccionesFuncionario) {
        this.fececAccionesFuncionario = fececAccionesFuncionario;
    }

    public int getNumeroDocumentos() {
        return numeroDocumentos;
    }

    public void setNumeroDocumentos(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public BigDecimal getIdAccionPropuesta() {

        return idAccionPropuesta;
    }

    public void setIdAccionPropuesta(BigDecimal idAccionPropuesta) {
        this.idAccionPropuesta = idAccionPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdDetalleAccion() {
        return idDetalleAccion;
    }

    public void setIdDetalleAccion(BigDecimal idDetalleAccion) {
        this.idDetalleAccion = idDetalleAccion;
    }

    public BigDecimal getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(BigDecimal idAccion) {
        this.idAccion = idAccion;
    }

    public BigDecimal getIdAccionOrigen() {
        return idAccionOrigen;
    }

    public void setIdAccionOrigen(BigDecimal idAccionOrigen) {
        this.idAccionOrigen = idAccionOrigen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaHora() {
        return (fechaHora != null) ? (Date) fechaHora.clone() : null;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora != null ? new Date(fechaHora.getTime()) : null;
    }

    public BigDecimal getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    public BigDecimal getIdRechazo() {
        return idRechazo;
    }

    public void setIdRechazo(BigDecimal idRechazo) {
        this.idRechazo = idRechazo;
    }

    public BigDecimal getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(BigDecimal idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public BigDecimal getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(BigDecimal idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public AccionesFuncionarioEnum getAccionFuncionarioEnum() {
        return accionFuncionarioEnum;
    }

    public void setAccionFuncionarioEnum(AccionesFuncionarioEnum accionFuncionarioEnum) {
        this.accionFuncionarioEnum = accionFuncionarioEnum;
    }

    public void setDescripcionTipoEmpleado(String descripcionTipoEmpleado) {
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
    }

    public String getDescripcionTipoEmpleado() {
        return descripcionTipoEmpleado;
    }
}
