package mx.gob.sat.siat.feagace.vista.propuestas.asignar.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public class AsignarPropuestaAttributoHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1223025349142622765L;

    private FecetPropuesta propuestaSeleccionada;
    private Boolean muestraPropuestas;
    private Boolean muestraDialogoAsignar;
    private Boolean esPrioridad3;
    private FececEmpleado fececEmpleado;
    private String rfcFirmanteSeleccionado;
    private String rfcFirmanteSeleccionadoPrior3;
    private String tipoFechaComite;
    private String tipoFechaComitePrior3;
    private String mensaje1;
    private String mensaje2;
    private Date fechaPrimerPedimento;
    private Date fechaUltimoPedimento;
    private BigDecimal presuntivaMayor;
    private BigDecimal presuntivaMenor;
    private Boolean habilitaTipoRevision;
    private String idMetodo;
    private String idTipoPropuesta;
    private String idTipoRevision;
    private String idSubprograma;
    private EmpleadoDTO firmanteSeleccionado;
    private EmpleadoDTO empleado;
    private String prioridadGrid;
    private String prioridadFiltros;

    public String getRfcFirmanteSeleccionadoPrior3() {
        return rfcFirmanteSeleccionadoPrior3;
    }

    public void setRfcFirmanteSeleccionadoPrior3(String rfcFirmanteSeleccionadoPrior3) {
        this.rfcFirmanteSeleccionadoPrior3 = rfcFirmanteSeleccionadoPrior3;
    }

    public String getTipoFechaComite() {
        return tipoFechaComite;
    }

    public void setTipoFechaComite(String tipoFechaComite) {
        this.tipoFechaComite = tipoFechaComite;
    }

    public String getTipoFechaComitePrior3() {
        return tipoFechaComitePrior3;
    }

    public void setTipoFechaComitePrior3(String tipoFechaComitePrior3) {
        this.tipoFechaComitePrior3 = tipoFechaComitePrior3;
    }

    public String getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(String idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    public void setIdTipoPropuesta(String idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }

    public String getIdTipoRevision() {
        return idTipoRevision;
    }

    public void setIdTipoRevision(String idTipoRevision) {
        this.idTipoRevision = idTipoRevision;
    }

    public String getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(String idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    public BigDecimal getPresuntivaMayor() {
        return presuntivaMayor;
    }

    public void setPresuntivaMayor(BigDecimal presuntivaMayor) {
        this.presuntivaMayor = presuntivaMayor;
    }

    public BigDecimal getPresuntivaMenor() {
        return presuntivaMenor;
    }

    public void setPresuntivaMenor(BigDecimal presuntivaMenor) {
        this.presuntivaMenor = presuntivaMenor;
    }

    public Boolean getHabilitaTipoRevision() {
        return habilitaTipoRevision;
    }

    public void setHabilitaTipoRevision(Boolean habilitaTipoRevision) {
        this.habilitaTipoRevision = habilitaTipoRevision;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public Boolean getMuestraPropuestas() {
        return muestraPropuestas;
    }

    public void setMuestraPropuestas(Boolean muestraPropuestas) {
        this.muestraPropuestas = muestraPropuestas;
    }

    public Date getFechaPrimerPedimento() {
        if (fechaPrimerPedimento != null) {
            return new Date(fechaPrimerPedimento.getTime());
        } else {
            return null;
        }
    }

    public void setFechaPrimerPedimento(Date fechaPrimerPedimento) {
        if (fechaPrimerPedimento != null) {
            this.fechaPrimerPedimento = new Date(fechaPrimerPedimento.getTime());
        } else {
            this.fechaPrimerPedimento = null;
        }
    }

    public Date getFechaUltimoPedimento() {
        if (fechaUltimoPedimento != null) {
            return new Date(fechaUltimoPedimento.getTime());
        } else {
            return null;
        }
    }

    public void setFechaUltimoPedimento(Date fechaUltimoPedimento) {
        if (fechaUltimoPedimento != null) {
            this.fechaUltimoPedimento = new Date(fechaUltimoPedimento.getTime());
        } else {
            this.fechaUltimoPedimento = null;
        }
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public EmpleadoDTO getFirmanteSeleccionado() {
        return firmanteSeleccionado;
    }

    public void setFirmanteSeleccionado(EmpleadoDTO firmanteSeleccionado) {
        this.firmanteSeleccionado = firmanteSeleccionado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void ocultaDialogoAsignar() {
        setMuestraDialogoAsignar(false);
    }

    public void setpropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetPropuesta getpropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setmuestraPropuestas(Boolean muestraPropuestas) {
        this.muestraPropuestas = muestraPropuestas;
    }

    public Boolean getmuestraPropuestas() {
        return muestraPropuestas;
    }

    public void setRfcFirmanteSeleccionado(String rfcFirmanteSeleccionado) {
        this.rfcFirmanteSeleccionado = rfcFirmanteSeleccionado;
    }

    public String getRfcFirmanteSeleccionado() {
        return rfcFirmanteSeleccionado;
    }

    public void setMuestraDialogoAsignar(Boolean muestraDialogoAsignar) {
        this.muestraDialogoAsignar = muestraDialogoAsignar;
    }

    public Boolean getMuestraDialogoAsignar() {
        return muestraDialogoAsignar;
    }

    public Boolean getEsPrioridad3() {
        return esPrioridad3;
    }

    public void setEsPrioridad3(Boolean esPrioridad3) {
        this.esPrioridad3 = esPrioridad3;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public String getPrioridadGrid() {
        return prioridadGrid;
    }

    public void setPrioridadGrid(String prioridadGrid) {
        this.prioridadGrid = prioridadGrid;
    }

    public String getPrioridadFiltros() {
        return prioridadFiltros;
    }

    public void setPrioridadFiltros(String prioridadFiltros) {
        this.prioridadFiltros = prioridadFiltros;
    }

}