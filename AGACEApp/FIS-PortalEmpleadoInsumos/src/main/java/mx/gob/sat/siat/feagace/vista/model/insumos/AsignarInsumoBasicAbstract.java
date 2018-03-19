package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;
import java.math.BigDecimal;

public class AsignarInsumoBasicAbstract implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean visualizaAsignacionSubadministrador;
    private boolean visualizaReasignacionAdministrador;
    private boolean muestraDialogoAceptarReasignar;
    private boolean muestraDialogoCancelarReasignar;
    private boolean muestraDialogoReasignar;
    private boolean muestraDialogoRechazarReasignar;
    private boolean muestraConfirmarRechazarReasignacion;
    private Boolean muestraDetalleContribuyente;
    private Boolean muestraInsumos;
    private Boolean muestraDialogoAsignar;
    private BigDecimal accionAdministrador;
    private boolean muestraDialogoReasignarResultado;
    
    public void setMuestraDetalleContribuyente(Boolean muestraDetalleContribuyente) {
        this.muestraDetalleContribuyente = muestraDetalleContribuyente;
    }

    public Boolean getMuestraDetalleContribuyente() {
        return muestraDetalleContribuyente;
    }

    public void setMuestraInsumos(Boolean muestraInsumos) {
        this.muestraInsumos = muestraInsumos;
    }

    public Boolean getMuestraInsumos() {
        return muestraInsumos;
    }

    public void setMuestraDialogoAsignar(Boolean muestraDialogoAsignar) {
        this.muestraDialogoAsignar = muestraDialogoAsignar;
    }

    public Boolean getMuestraDialogoAsignar() {
        return muestraDialogoAsignar;
    }

    public void setAccionAdministrador(BigDecimal accionAdministrador) {
        this.accionAdministrador = accionAdministrador;
    }

    public BigDecimal getAccionAdministrador() {
        return accionAdministrador;
    }

    public void setVisualizaAsignacionSubadministrador(boolean visualizaAsignacionSubadministrador) {
        this.visualizaAsignacionSubadministrador = visualizaAsignacionSubadministrador;
    }

    public boolean isVisualizaAsignacionSubadministrador() {
        return visualizaAsignacionSubadministrador;
    }

    public void setVisualizaReasignacionAdministrador(boolean visualizaReasignacionAdministrador) {
        this.visualizaReasignacionAdministrador = visualizaReasignacionAdministrador;
    }

    public boolean isVisualizaReasignacionAdministrador() {
        return visualizaReasignacionAdministrador;
    }
    
    public void setMuestraDialogoAceptarReasignar(boolean muestraDialogoAceptarReasignar) {
        this.muestraDialogoAceptarReasignar = muestraDialogoAceptarReasignar;
    }

    public boolean isMuestraDialogoAceptarReasignar() {
        return muestraDialogoAceptarReasignar;
    }

    public void setMuestraDialogoCancelarReasignar(boolean muestraDialogoCancelarReasignar) {
        this.muestraDialogoCancelarReasignar = muestraDialogoCancelarReasignar;
    }

    public boolean isMuestraDialogoCancelarReasignar() {
        return muestraDialogoCancelarReasignar;
    }

    public void setMuestraDialogoReasignar(boolean muestraDialogoReasignar) {
        this.muestraDialogoReasignar = muestraDialogoReasignar;
    }

    public boolean isMuestraDialogoReasignar() {
        return muestraDialogoReasignar;
    }

    public void setMuestraDialogoRechazarReasignar(boolean muestraDialogoRechazarReasignar) {
        this.muestraDialogoRechazarReasignar = muestraDialogoRechazarReasignar;
    }

    public boolean isMuestraDialogoRechazarReasignar() {
        return muestraDialogoRechazarReasignar;
    }

    public void setMuestraConfirmarRechazarReasignacion(boolean muestraConfirmarRechazarReasignacion) {
        this.muestraConfirmarRechazarReasignacion = muestraConfirmarRechazarReasignacion;
    }

    public boolean isMuestraConfirmarRechazarReasignacion() {
        return muestraConfirmarRechazarReasignacion;
    }

    public final boolean isMuestraDialogoReasignarResultado() {
        return muestraDialogoReasignarResultado;
    }

    public final void setMuestraDialogoReasignarResultado(boolean muestraDialogoReasignarResultado) {
        this.muestraDialogoReasignarResultado = muestraDialogoReasignarResultado;
    }
}
