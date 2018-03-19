package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class RepPistaAuditModel extends BaseModel{
    @SuppressWarnings("compatibility:-8205715141152589267")
    private static final long serialVersionUID = 3433788462139387299L;
    
    private String numOrden;
    private String idRegistro;
    private String rfcUsuario;
    private String nomUsuario;
    
    private int contador;
    private int contOrden;
    private int contReg;
    private int contRfcUser;
    private int contNomUser;
    
    private Date fechaBusqInicio;
    private Date fechaBusqFin;
    private Date fechaInicio;
    private Date fechaFin;
    
    private boolean pnlTabla;
    private boolean pnlBusqueda;
    private boolean campoActivoOrden;
    private boolean campoActivoRegistro;
    private boolean campoActivoRfcUser;
    private boolean campoActivoUsuario;
    private boolean campoActivoCalendar;

    public RepPistaAuditModel() {
        super();
    }


    public void setNumOrden(String numOreden) {
        this.numOrden = numOreden;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
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

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }

    public void setContOrden(int contOrden) {
        this.contOrden = contOrden;
    }

    public int getContOrden() {
        return contOrden;
    }

    public void setContReg(int contReg) {
        this.contReg = contReg;
    }

    public int getContReg() {
        return contReg;
    }

    public void setContRfcUser(int contRfcUser) {
        this.contRfcUser = contRfcUser;
    }

    public int getContRfcUser() {
        return contRfcUser;
    }

    public void setContNomUser(int contNomUser) {
        this.contNomUser = contNomUser;
    }

    public int getContNomUser() {
        return contNomUser;
    }

    public void setFechaBusqInicio(Date fechaBusqInicio) {
        this.fechaBusqInicio = fechaBusqInicio != null ? new Date(fechaBusqInicio.getTime()) : null;
    }

    public Date getFechaBusqInicio() {
        return (fechaBusqInicio != null) ? (Date) fechaBusqInicio.clone() : null;
    }

    public void setFechaBusqFin(Date fechaBusqFin) {
        this.fechaBusqFin = fechaBusqFin != null ? new Date(fechaBusqFin.getTime()) : null;
    }

    public Date getFechaBusqFin() {
        return (fechaBusqFin != null) ? (Date) fechaBusqFin.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }
    public void setPnlTabla(boolean pnlTabla) {
        this.pnlTabla = pnlTabla;
    }

    public boolean isPnlTabla() {
        return pnlTabla;
    }

    public void setPnlBusqueda(boolean pnlBusqueda) {
        this.pnlBusqueda = pnlBusqueda;
    }

    public boolean isPnlBusqueda() {
        return pnlBusqueda;
    }

    public void setCampoActivoOrden(boolean campoActivoOrden) {
        this.campoActivoOrden = campoActivoOrden;
    }

    public boolean isCampoActivoOrden() {
        return campoActivoOrden;
    }

    public void setCampoActivoRegistro(boolean campoActivoRegistro) {
        this.campoActivoRegistro = campoActivoRegistro;
    }

    public boolean isCampoActivoRegistro() {
        return campoActivoRegistro;
    }

    public void setCampoActivoRfcUser(boolean campoActivoRfcUser) {
        this.campoActivoRfcUser = campoActivoRfcUser;
    }

    public boolean isCampoActivoRfcUser() {
        return campoActivoRfcUser;
    }

    public void setCampoActivoUsuario(boolean campoActivoUsuario) {
        this.campoActivoUsuario = campoActivoUsuario;
    }

    public boolean isCampoActivoUsuario() {
        return campoActivoUsuario;
    }

    public void setCampoActivoCalendar(boolean campoActivoCalendar) {
        this.campoActivoCalendar = campoActivoCalendar;
    }

    public boolean isCampoActivoCalendar() {
        return campoActivoCalendar;
    }
}
