package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReportePistaAuditoriaInternaDTO extends BaseModel{

    @SuppressWarnings("compatibility:-1492096775738623659")
    private static final long serialVersionUID = -88880728723040485L;
    
    private Date fechaYHora;
    private String rfcUsuario;
    private String nomUsuario;
    private String apePaterno;
    private String apeMaterno;
    private String ipMaquina;
    private String nomMaquina;
    private String modIngreso;
    private String operRealizada;
    
    private String numOreden;
    private String idRegistro;
    private Date fechaBusqInicio;
    private Date fechaBusqFin;
    private BigDecimal idEmpleado;
    private String rfcEmpleado;
    
    
    public ReportePistaAuditoriaInternaDTO() {
        super();
    }


    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora != null ? new Date(fechaYHora.getTime()) : null;
    }

    public Date getFechaYHora() {
        return (fechaYHora != null) ? (Date) fechaYHora.clone() : null;
    }

    public void setRfcUsuario(String rfcUsuario) {
        this.rfcUsuario = rfcUsuario;
    }

    public String getRfcUsuario() {
        return rfcUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public String getIpMaquina() {
        return ipMaquina;
    }

    public void setNomMaquina(String nomMaquina) {
        this.nomMaquina = nomMaquina;
    }

    public String getNomMaquina() {
        return nomMaquina;
    }

    public void setModIngreso(String modIngreso) {
        this.modIngreso = modIngreso;
    }

    public String getModIngreso() {
        return modIngreso;
    }

    public void setOperRealizada(String operRealizada) {
        this.operRealizada = operRealizada;
    }

    public String getOperRealizada() {
        return operRealizada;
    }

    public void setNumOreden(String numOreden) {
        this.numOreden = numOreden;
    }

    public String getNumOreden() {
        return numOreden;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setFechaBusqInicio(Date fechaBusqInicio) {
        this.fechaBusqInicio = fechaBusqInicio != null ? new Date(fechaBusqInicio.getTime()) : null;
    }

    public Date getFechaBusqInicio() {
        return (fechaBusqInicio != null) ? (Date) fechaBusqInicio.clone() :null;
    }

    public void setFechaBusqFin(Date fechaBusqFin) {
        this.fechaBusqFin = fechaBusqFin != null ? new Date(fechaBusqFin.getTime()) : null;
    }

    public Date getFechaBusqFin() {
        return (fechaBusqFin != null) ? (Date) fechaBusqFin.clone() : null;
    }


    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }


    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


    public String getRfcEmpleado() {
        return rfcEmpleado;
    }


    public void setRfcEmpleado(String rfcEmpleado) {
        this.rfcEmpleado = rfcEmpleado;
    }
}
